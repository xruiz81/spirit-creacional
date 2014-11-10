package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CuentasPorCobrarEJB;
import com.spirit.cartera.entity.CuentasPorPagarEJB;
import com.spirit.cartera.gui.panel.JPCruceCuentas;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.LineaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.DeepCopy;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CruceCuentasModel extends JPCruceCuentas {
	private static final long serialVersionUID = 3689189244410908744L;
	private static final int MAX_LONGITUD_TXTCELL = 100;
	private int selectedCuentaPorCobrar = -1;
	private int selectedCuentaPorPagar = -1;
	private List<CarteraIf> cuentasPorCobrarColeccion = new ArrayList<CarteraIf>();
	private List<CarteraIf> cuentasPorPagarColeccion = new ArrayList<CarteraIf>();
	private CarteraIf cuentaPorCobrarForCruce;
	private CarteraIf cuentaPorPagarForCruce;
	private ClienteOficinaIf operadorNegocioOficina;
	private JDPopupFinderModel popupFinder;
	private ClienteOficinaCriteria operadorNegocioOficinaCriteria;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private double valorAfecta = 0D;
	
	public CruceCuentasModel() {
		setSorterTable(getTblCuentasPorCobrar());
		setSorterTable(getTblCuentasPorPagar());
		getTblCuentasPorCobrar().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblCuentasPorPagar().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initListeners();
		iniciarComponentes();
		showSaveMode();
		setColumnsWidth();
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes() {
		getCmbFechaCruce().setLocale(Utilitarios.esLocale);
		getCmbFechaCruce().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaCruce().setEditable(false);
		getCmbFechaCruce().setShowNoneButton(false);
	}
	
	private void setColumnsWidth() {
		// tblCuentasPorCobrar
		TableColumn anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
		
		// tblCuentasPorPagar
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblCuentasPorPagar().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(50);
		
		getTblCuentasPorCobrar().getTableHeader().setReorderingAllowed(false);
		getTblCuentasPorPagar().getTableHeader().setReorderingAllowed(false);
		TableColumn txtColumna = getTblCuentasPorCobrar().getColumnModel().getColumn(4);
		JTextField txtCell = new JTextField();
		txtCell.addKeyListener(new TextChecker(MAX_LONGITUD_TXTCELL));
		txtCell.addKeyListener(new NumberTextFieldDecimal());
		txtColumna.setCellEditor(new DefaultCellEditor(txtCell));
		((DefaultCellEditor)getTblCuentasPorCobrar().getCellEditor(0,4)).setClickCountToStart(1);
		getTblCuentasPorCobrar().setSurrendersFocusOnKeystroke(true);
	}
	
	private void initListeners() {
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					Long idCorporacion = 0L;
					Long idCliente = 0L;
					String tipoCliente = "AM";
					String tituloVentanaBusqueda = "Operadores de Negocio";
					
					operadorNegocioOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.addElement(70);
					anchoColumnas.addElement(200);
					anchoColumnas.addElement(80);
					anchoColumnas.addElement(230);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), operadorNegocioOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null) {
						operadorNegocioOficina = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
						ClienteIf operadorNegocio = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocioOficina.getClienteId());
						getTxtOperadorNegocio().setText(operadorNegocio.getRazonSocial());
						cleanTable(getTblCuentasPorCobrar());
						cleanTable(getTblCuentasPorPagar());
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
				}
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				consultar();
			}
		});
		
		getBtnActualizarValorAfecta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarValorAfecta();
			}
		});
		
		getTblCuentasPorCobrar().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				seleccionarCuentaPorCobrar(evt);
			}
	    });
	}
	
	private void consultar() {
		cleanTable(getTblCuentasPorCobrar());
		cleanTable(getTblCuentasPorPagar());
		cargarTablaCuentasPorCobrar();
		cargarTablaCuentasPorPagar();
		valorAfecta = 0D;
		getTxtValorAfecta().setText("");
	}
	
	private void seleccionarCuentaPorCobrar(int selectedRow) {
		setCuentaPorCobrarSeleccionada(selectedRow);
		cuentaPorCobrarForCruce = (CarteraIf) cuentasPorCobrarColeccion.get(getTblCuentasPorCobrar().convertRowIndexToModel(getCuentaPorCobrarSeleccionada()));
	}
	
	private void seleccionarCuentaPorCobrar(ComponentEvent evt) {
		if (getTblCuentasPorCobrar().getSelectedRow() != -1) {
			int row = getTblCuentasPorCobrar().getSelectedRow();
			//CarteraIf cuentaPorCobrar = (CarteraIf) cuentasPorCobrarColeccion.get(getTblCuentasPorCobrar().convertRowIndexToModel(row));
			/*if ((Boolean) getTblCuentasPorCobrar().getValueAt(row, 0))
				valorAfecta += cuentaPorCobrar.getSaldo().doubleValue();
			else
				valorAfecta -= cuentaPorCobrar.getSaldo().doubleValue();*/
			actualizarValorAfecta();
			
			if (!((Boolean) getTblCuentasPorCobrar().getValueAt(row, 0)).booleanValue()) {
				CarteraIf cartera = cuentasPorCobrarColeccion.get(row);
				getTblCuentasPorCobrar().setValueAt(formatoDecimal.format(Utilitarios.redondeoValor(cartera.getSaldo().doubleValue())),row,4);
			}
		}
	}

	private void actualizarValorAfecta() {
		valorAfecta = recalcularValorAfecta();
		getTxtValorAfecta().setText(formatoDecimal.format(Utilitarios.redondeoValor(valorAfecta)));
	}
	
	private void seleccionarCuentaPorPagar(int selectedRow) {
		setCuentaPorPagarSeleccionada(selectedRow);
		cuentaPorPagarForCruce = (CarteraIf) cuentasPorPagarColeccion.get(getTblCuentasPorPagar().convertRowIndexToModel(getCuentaPorPagarSeleccionada()));
	}
	
	private double recalcularValorAfecta() {
		int rows = getTblCuentasPorCobrar().getRowCount();
		double valorAfecta = 0D;
		for (int i=0; i<rows; i++) {
			double saldo = Double.parseDouble(Utilitarios.removeDecimalFormat(getTblCuentasPorCobrar().getValueAt(i,4).toString()));
			CarteraIf cartera = cuentasPorCobrarColeccion.get(i);
			if (cartera.getSaldo().doubleValue() >= saldo) {
				if ((Boolean) getTblCuentasPorCobrar().getValueAt(i, 0))
					valorAfecta += saldo;
			} else {
				SpiritAlert.createAlert("Valor ingresado supera al saldo", SpiritAlert.INFORMATION);
				getTblCuentasPorCobrar().setValueAt(new Boolean(false),i,0);
			}
		}
		
		return valorAfecta;
	}
	
	private void cargarTablaCuentasPorCobrar() {
		try {
			cuentasPorCobrarColeccion.clear();
			DefaultTableModel tableModel = (DefaultTableModel) getTblCuentasPorCobrar().getModel();
			java.util.Date fecha = new java.util.Date();
			java.sql.Date fechaCorte = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());
			ArrayList cuentasPorCobrarList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorCobrar(Parametros.getIdEmpresa(), operadorNegocioOficina.getClienteId(), fechaCorte);
			Iterator it = cuentasPorCobrarList.iterator();
			
			while (it.hasNext()) {
				CuentasPorCobrarEJB cuentaPorCobrar = (CuentasPorCobrarEJB) it.next();
				if (cuentaPorCobrar.getSaldo().doubleValue() > 0D) {
					Vector<Object> fila = new Vector<Object>();
					fila.add(new Boolean(false));
					fila.add(cuentaPorCobrar.getPreimpreso());
					fila.add((cuentaPorCobrar.getFechaFactura()!=null)?cuentaPorCobrar.getFechaFactura():cuentaPorCobrar.getFechaEmision());
					fila.add(cuentaPorCobrar.getComentario());
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(cuentaPorCobrar.getSaldo().doubleValue())));
					tableModel.addRow(fila);
					cuentasPorCobrarColeccion.add(SessionServiceLocator.getCarteraSessionService().getCartera(cuentaPorCobrar.getCarteraId()));
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void cargarTablaCuentasPorPagar() {
		try {
			cuentasPorPagarColeccion.clear();
			DefaultTableModel tableModel = (DefaultTableModel) getTblCuentasPorPagar().getModel();
			java.util.Date fecha = new java.util.Date();
			java.sql.Date fechaCorte = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());
			ArrayList cuentasPorPagarList = (ArrayList) SessionServiceLocator.getCarteraSessionService().findCuentasPorPagar(Parametros.getIdEmpresa(), operadorNegocioOficina.getClienteId(), 0L, fechaCorte, false);
			Iterator it = cuentasPorPagarList.iterator();
			
			while (it.hasNext()) {
				CuentasPorPagarEJB cuentaPorPagar = (CuentasPorPagarEJB) it.next();
				if (cuentaPorPagar.getSaldo().doubleValue() > 0D) {
					Vector<Object> fila = new Vector<Object>();
					fila.add(cuentaPorPagar.getPreimpreso());
					fila.add((cuentaPorPagar.getFechaCompra()!=null)?cuentaPorPagar.getFechaCompra():cuentaPorPagar.getFechaEmision());
					fila.add(cuentaPorPagar.getComentario());
					fila.add(formatoDecimal.format(Utilitarios.redondeoValor(cuentaPorPagar.getSaldo().doubleValue())));
					tableModel.addRow(fila);
					cuentasPorPagarColeccion.add(SessionServiceLocator.getCarteraSessionService().getCartera(cuentaPorPagar.getCarteraId()));
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}
	
	private void cleanTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (int i = table.getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	public void showSaveMode() {
		setSaveMode();
		cleanTable(getTblCuentasPorCobrar());
		cleanTable(getTblCuentasPorPagar());
	}
	
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}
	
	public void save() {
		if (validateFields()) {
			int numeroCuentasPorCobrar = getTblCuentasPorCobrar().getRowCount();
			Map cuentasPorCobrarMap = new HashMap();
			Map valoresAplica = new HashMap();
			List<CarteraIf> cuentasPorCobrarSeleccionadas = new ArrayList<CarteraIf>();
			
			for (int i=0; i<numeroCuentasPorCobrar; i++) {
				boolean seleccionado = (Boolean) getTblCuentasPorCobrar().getValueAt(i,0);
				
				if (seleccionado) {
					cuentasPorCobrarMap.put(i,i);
				}
			}
			
			Iterator cuentasPorCobrarMapIt = cuentasPorCobrarMap.keySet().iterator();
			
			while (cuentasPorCobrarMapIt.hasNext()) {
				int indice = (Integer) cuentasPorCobrarMapIt.next();
				getTblCuentasPorCobrar().setRowSelectionInterval(indice,indice);
				seleccionarCuentaPorCobrar(indice);
				cuentasPorCobrarSeleccionadas.add((CarteraIf) DeepCopy.copy(cuentaPorCobrarForCruce));
				valoresAplica.put(cuentaPorCobrarForCruce.getId(), Double.valueOf(Utilitarios.removeDecimalFormat(getTblCuentasPorCobrar().getValueAt(indice, 4).toString())));
			}
			
			if (cuentasPorCobrarSeleccionadas.size() > 0) {
				cruzarCuentasPorCobrar(cuentasPorCobrarSeleccionadas, valoresAplica);
				SpiritAlert.createAlert("Cruce de cuentas realizado con éxito", SpiritAlert.INFORMATION);
				consultar();
			} else {
				SpiritAlert.createAlert("Debe seleccionar las cuentas por cobrar a cruzar!", SpiritAlert.INFORMATION);
			}
		}
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void find() {
		// TODO Auto-generated method stub
		
	}
	
	public void delete() {
		// TODO Auto-generated method stub
	}
	
	public void authorize() {
		// TODO Auto-generated method stub
	}
	
	private void cruzarCuentasPorCobrar(List<CarteraIf> cuentasPorCobrarSeleccionadas, Map valoresAplica) {
		try {
			int selectedRow = getTblCuentasPorPagar().getSelectedRow();
			seleccionarCuentaPorPagar(selectedRow);
			CarteraIf cuentaPorPagar = cuentaPorPagarForCruce;
			Map parameterMap = new HashMap();
			parameterMap.put("EMPRESA_ID", Parametros.getIdEmpresa());
			parameterMap.put("OFICINA_ID", Parametros.getIdOficina());
			parameterMap.put("USUARIO_ID", ((UsuarioIf) Parametros.getUsuarioIf()).getId());
			Iterator it = SessionServiceLocator.getMonedaSessionService().findMonedaByCodigo("USD").iterator();
			MonedaIf moneda = (it.hasNext())?(MonedaIf) it.next():null;
			parameterMap.put("MONEDA_ID", (moneda!=null)?moneda.getId():null);
			parameterMap.put("FECHA_CRUCE", new java.sql.Date(getCmbFechaCruce().getDate().getTime()));
			it = SessionServiceLocator.getLineaSessionService().findLineaByCodigo("PUBL").iterator();
			LineaIf linea = (it.hasNext())?(LineaIf) it.next():null;
			parameterMap.put("LINEA_ID", (linea!=null)?linea.getId():null);
			parameterMap.put("OPERADOR_NEGOCIO_OFICINA_ID", operadorNegocioOficina.getId());
			ClienteIf operadorNegocio = SessionServiceLocator.getClienteSessionService().getCliente(operadorNegocioOficina.getClienteId());
			parameterMap.put("OPERADOR_NEGOCIO", operadorNegocio);
			double saldoCuentaPorPagar = cuentaPorPagar.getSaldo().doubleValue();
			if (saldoCuentaPorPagar < valorAfecta)
				valorAfecta = saldoCuentaPorPagar;
			parameterMap.put("VALOR_AFECTA", Double.valueOf(valorAfecta));
			SessionServiceLocator.getCarteraSessionService().procesarCruceCuentas(cuentasPorCobrarSeleccionadas, cuentaPorPagar, valoresAplica, parameterMap);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Ocurri\u00f3 un error al realizar el cruce de cuentas", SpiritAlert.ERROR);
		}
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void clean() {
		
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean validateFields() {
		if (operadorNegocioOficina == null) {
			SpiritAlert.createAlert("Debe seleccionar el operador de negocio!", SpiritAlert.WARNING);
			getBtnBuscarOperadorNegocio().grabFocus();
			return false;
		}
		
		if (getCmbFechaCruce().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la fecha del cruce!", SpiritAlert.WARNING);
			getCmbFechaCruce().grabFocus();
			return false;
		}
		
		if (getTblCuentasPorPagar().getSelectedRow() < 0) {
			SpiritAlert.createAlert("Debe seleccionar la cuenta por pagar a cruzar!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
	
	public void refresh() {
		showSaveMode();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	private int getCuentaPorPagarSeleccionada() {
		return this.selectedCuentaPorPagar;
	}
	
	private void setCuentaPorPagarSeleccionada(int selectedCuentaPorPagar) {
		this.selectedCuentaPorPagar = selectedCuentaPorPagar;
	}
	
	private int getCuentaPorCobrarSeleccionada() {
		return this.selectedCuentaPorCobrar;
	}
	
	private void setCuentaPorCobrarSeleccionada(int selectedCuentaPorCobrar) {
		this.selectedCuentaPorCobrar = selectedCuentaPorCobrar;
	}
}
