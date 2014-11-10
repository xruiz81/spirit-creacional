package com.spirit.pos.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.pos.client.HotKeyComponentPOS;
import com.spirit.pos.gui.panel.JPSaldoActualCliente;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class SaldoActualClienteModel extends JPSaldoActualCliente {
	private static final long serialVersionUID = -6273160673721006851L;
	private static String NOMBRE_MENU_MOVIMIENTO_CARTERA = "SALDO ACTUAL CLIENTE";
	private static final String NOMBRE_TIPO_CARTERA_CLIENTE = "CLIENTE";
	private static final String TIPO_CARTERA_CLIENTE = NOMBRE_TIPO_CARTERA_CLIENTE.substring(0, 1);
	private static final String NOMBRE_TIPO_CARTERA_PROVEEDOR = "PROVEEDOR";
	private static final String TIPO_CARTERA_PROVEEDOR = NOMBRE_TIPO_CARTERA_PROVEEDOR.substring(0, 1);
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private DefaultTableModel tableModel;
	ClienteIf operadorNegocio = null;
	ClienteOficinaIf clienteOficinaIf=null;
	double totalDebitos;
	double totalCreditos;
	double saldo;
	java.sql.Date fechaInicialSeleccionada;
	java.sql.Date fechaFinalSeleccionada;
	boolean isTotal = false;

	public SaldoActualClienteModel() {
		getTblSaldoActualCliente().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		showSaveMode();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponentPOS(this);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblSaldoActualCliente().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(10);
		anchoColumna = getTblSaldoActualCliente().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = getTblSaldoActualCliente().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblSaldoActualCliente().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(275);
		anchoColumna = getTblSaldoActualCliente().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(80);
		anchoColumna = getTblSaldoActualCliente().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(80);
	}

	private void initListeners(){
		
		getBtnBuscarOperadorNegocio().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/FindDocument.png"));
		getBtnBuscarOperadorNegocio().setText("");
		
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L; 

				clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, "CL");
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					cleanTable();
					operadorNegocio = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtOperadorNegocio().setText(operadorNegocio.toString());
					
					clienteOficinaIf = null;
					try {
						Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(operadorNegocio.getId());

						if (oficinas.size() >0) {
							clienteOficinaIf = oficinas.iterator().next();							
						}
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert
						.createAlert(
								"Se ha producido un error al consultar la oficina del cliente",
								SpiritAlert.ERROR);
					}
					
				}
			}
		});

		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				llenar_datos_creditos();
				//cargarTabla();
				setCursor(SpiritCursor.normalCursor);
			}
		});


	}

	public void report() {
		try {				
			if (getTblSaldoActualCliente().getModel().getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Saldo Actual del Cliente?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/pos/RPSaldoActualCliente.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MOVIMIENTO_CARTERA).iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", "");
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);					
					parametrosMap.put("rucOperadorNegocio", operadorNegocio.getIdentificacion());
					parametrosMap.put("operadorNegocio",  operadorNegocio.getRazonSocial());
					parametrosMap.put("totalDebitos", totalDebitos);
					parametrosMap.put("totalCreditos", (totalCreditos * -1));
					parametrosMap.put("saldo", saldo);
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblSaldoActualCliente().getModel(), true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void refresh() {

	}

	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void clean() {		
		totalDebitos = 0D;
		totalCreditos = 0D;
		saldo = 0D;
		getTxtTotalDebitos().setText("");
		getTxtTotalCreditos().setText("");
		getTxtSaldo().setText("");
		cleanTable();
	}

	public void cleanTable() {	
		DefaultTableModel model = (DefaultTableModel) getTblSaldoActualCliente().getModel();
		for(int i= this.getTblSaldoActualCliente().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}


	public void llenar_datos_creditos(){
		if(operadorNegocio==null)
		{
			SpiritAlert.createAlert("Debe escoger un Cliente", SpiritAlert.INFORMATION);
		}
		else{
			try {
				TipoDocumentoIf tipoDocumentoIf = (TipoDocumentoIf)SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo("DEV").iterator().next();

				HashMap<String, Object> mapa = new HashMap<String, Object>();
				mapa.clear();

				mapa.put("tipodocumentoId",tipoDocumentoIf.getId());
				mapa.put("clienteoficinaId",clienteOficinaIf.getId());
				Iterator carterasIt;
				Vector<Vector> detalles_id_saldo =new Vector<Vector>();
				System.out.println("DOCU!-<"+tipoDocumentoIf.getId());
				System.out.println("clienteoficinaId!-<"+clienteOficinaIf.getId());
				
				carterasIt = SessionServiceLocator.getCarteraSessionService().findCarteraCreditoDisponible(mapa,Parametros.getIdEmpresa()).iterator();
				int numero = 1;
				System.out.println("-carterasIT->"+carterasIt);
				BigDecimal suma=new BigDecimal("0");
				while (carterasIt.hasNext()) 
				{
					tableModel = (DefaultTableModel) getTblSaldoActualCliente().getModel();					
					CarteraIf carteraCabecera= (CarteraIf) carterasIt.next();
					if (agregarDetalles(numero, carteraCabecera)){
						numero++;
					}					
				}
				getTblSaldoActualCliente().validate();
				getTblSaldoActualCliente().repaint();
				getTxtTotalDebitos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalDebitos)));
				getTxtTotalCreditos().setText(formatoDecimal.format(Utilitarios.redondeoValor(totalCreditos)));
				
				saldo = totalDebitos - totalCreditos;
				//saldo = totalDebitos;
				getTxtSaldo().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));

			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}


	private boolean agregarDetalles(int numero, CarteraIf carterasPorDevoluciones) {
		Vector<Object> fila = new Vector<Object>();
		double totalDebitosMovimiento = 0D;
		double totalCreditosMovimiento = 0D;
		double saldoMovimiento = 0D;
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");
		String fecha = carterasPorDevoluciones.getFechaEmision().toString();
		System.out.println("FECHA>"+carterasPorDevoluciones.getFechaEmision());
		fila.add(fecha);
		String transaccion = carterasPorDevoluciones.getPreimpreso();
		fila.add((transaccion != null && !transaccion.equals(""))?transaccion:carterasPorDevoluciones.getCodigo());
		String detalle = carterasPorDevoluciones.getComentario();
		fila.add((detalle != null && !detalle.equals(""))?detalle:"");
		if (carterasPorDevoluciones.getTipo().equals("C")) {
			totalDebitos += carterasPorDevoluciones.getValor().doubleValue();
			totalDebitosMovimiento += carterasPorDevoluciones.getValor().doubleValue();
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(carterasPorDevoluciones.getValor().doubleValue())));
			fila.add("0");
		} else {
			totalCreditos += carterasPorDevoluciones.getValor().doubleValue();
			totalCreditosMovimiento += carterasPorDevoluciones.getValor().doubleValue();
			fila.add("0");
			fila.add(formatoDecimal.format(Utilitarios.redondeoValor(carterasPorDevoluciones.getValor().doubleValue())));			
		}
		tableModel.addRow(fila);		
		// Agregamos a la tabla todos los movimientos en los que se han usando ese credito
		Iterator carterasDetalleIt;
		try {
			carterasDetalleIt = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(carterasPorDevoluciones.getId()).iterator();
			if (carterasDetalleIt.hasNext()) 
			{
				CarteraDetalleIf carteraDetalleDev= (CarteraDetalleIf) carterasDetalleIt.next();
				Long idDetalle=carteraDetalleDev.getId();				
				Iterator carterasAfectaIt;
				carterasAfectaIt = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(idDetalle).iterator();				
				while (carterasAfectaIt.hasNext()) 
				{
					CarteraAfectaIf carteraAfecta= (CarteraAfectaIf) carterasAfectaIt.next();					
					fila = new Vector<Object>();
					fila.add("");
					fila.add(carteraAfecta.getFechaCreacion().toString());	
					Long carteraDetalleId=carteraAfecta.getCarteraafectaId();
					Iterator carteraIt; 
					List<CarteraIf> carteraColeccion =  (ArrayList)SessionServiceLocator.getCarteraSessionService().findCarteraByCarteraDetalleId(carteraDetalleId);				 
					carteraIt = carteraColeccion.iterator();					
					if (carteraIt.hasNext()) 
					{
						CarteraIf carteraIf= (CarteraIf) carteraIt.next();					
						fila.add(carteraIf.getPreimpreso());
						fila.add(carteraIf.getComentario());
					}
					else{
						fila.add("");
						fila.add("");
					}										
					totalCreditos += carteraAfecta.getValor().doubleValue();
					totalCreditosMovimiento += carteraAfecta.getValor().doubleValue();
					fila.add("");
					fila.add(carteraAfecta.getValor().toString());					
					tableModel.addRow(fila);				 
				}
			}

		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fila = new Vector<Object>();
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("<html><b>T O T A L E S :</b></html>");
		if (totalDebitosMovimiento != 0)
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalDebitosMovimiento)) + "</b></html>");
		else
			fila.add("<html><b>0</b></html>");


		if (totalCreditosMovimiento != 0)
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(totalCreditosMovimiento)) + "</b></html>");
		else
			fila.add("<html><b>0</b></html>");
		tableModel.addRow(fila);

		fila = new Vector<Object>();
		fila.add("");		
		fila.add("");
		fila.add("");
		fila.add("<html><b>S A L D O :</b></html>");
		saldoMovimiento = totalDebitosMovimiento - totalCreditosMovimiento;


		if (saldoMovimiento > 0) {
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(saldoMovimiento)) + "</b></html>");
			fila.add("<html><b>" + "0" + "</b></html>");
		} else if (saldoMovimiento < 0) {
			fila.add("<html><b>" + "0" + "</b></html>");
			fila.add("<html><b>" + formatoDecimal.format(Utilitarios.redondeoValor(saldoMovimiento)) + "</b></html>");
		} else {
			fila.add("<html><b>" + "0" + "</b></html>");
			fila.add("<html><b>" + "0" + "</b></html>");
		}
		tableModel.addRow(fila);

		fila = new Vector<Object>();
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");
		fila.add("");
		fila.add("");
		tableModel.addRow(fila);

		return true;
	}







	DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
		private Color whiteColor = new Color(255, 255, 255);
		private Color grayColor = new Color(204, 204, 204);
		private Color blackColor = new Color(0, 0, 0);

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			c.setForeground(blackColor);

			if(column == 0)
				setHorizontalAlignment(JLabel.RIGHT);
			if(column == 1)
				setHorizontalAlignment(JLabel.CENTER);        
			if(column == 2)
				setHorizontalAlignment(JLabel.CENTER);
			if(column == 3)
				setHorizontalAlignment(JLabel.LEFT);
			if(column == 4)
				setHorizontalAlignment(JLabel.RIGHT);
			if(column == 5)
				setHorizontalAlignment(JLabel.RIGHT);

			if(((String) value).equals("<html><b>T O T A L E S :</b></html>") || ((String) value).equals("<html><b>S A L D O :</b></html>")) {
				c.setBackground(grayColor);
				setHorizontalAlignment(JLabel.RIGHT);
			} else {
				c.setBackground(whiteColor);
			}

			String detalle = (String) table.getValueAt(row, 2);
			if ((column >= 0 && column <= 4) && (detalle.equals("<html><b>T O T A L E S :</b></html>") || detalle.equals("<html><b>S A L D O :</b></html>"))) {
				c.setBackground(grayColor);
			}

			return c;
		}
	};

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}
}