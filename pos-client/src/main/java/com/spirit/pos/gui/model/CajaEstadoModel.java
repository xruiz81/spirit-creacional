package com.spirit.pos.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
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

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.pos.client.HotKeyComponentPOS;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.gui.panel.JPCajaEstado;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;
 


public class CajaEstadoModel extends JPCajaEstado {
	private static final long serialVersionUID = -6273160673721006851L;
	private static String NOMBRE_MENU_MOVIMIENTO_CARTERA = "SALDO ACTUAL CLIENTE"; 
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	EmpleadoIf operadorNegocio = null;
	double totalDebitos;
	double totalCreditos;
	double saldo;
	double devo;
	java.sql.Date fechaInicialSeleccionada;
	java.sql.Date fechaFinalSeleccionada;
	boolean isTotal = false;

	public CajaEstadoModel() {
		getTblCajaEstado().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		showSaveMode();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponentPOS(this);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblCajaEstado().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(10);
		anchoColumna = getTblCajaEstado().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(75);
		anchoColumna = getTblCajaEstado().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(125);
		anchoColumna = getTblCajaEstado().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(275);
		
	}

	private void initListeners(){

		//cargarComboVendedores();
		
		cargarComboOficina();
		cargarComboCaja();
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				setCursor(SpiritCursor.hourglassCursor);
				clean();
				llenar_datos_creditos();
				//cargarTabla();
				setCursor(SpiritCursor.normalCursor);
			}
		});

		getCmbFechaInicial().setLocale(Utilitarios.esLocale);
		getCmbFechaInicial().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicial().setEditable(false);
		getCmbFechaInicial().setShowNoneButton(false);
	 
		
		getBtnResetearFechas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCmbFechaInicial().setSelectedItem(null);
				getCmbFechaInicial().validate();
				getCmbFechaInicial().repaint();
				 
			}
		});
		
		getCmbOficina().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
		
			cargarComboCaja();	
			}
		});  
	}

	private void cargarComboOficina(){
		try { 
			List oficinas = (List) SessionServiceLocator.getOficinaSessionService().getOficinaList();
			refreshCombo(getCmbOficina(),oficinas);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	//caja general ofcina tal
	private void cargarComboCaja(){
		try {
			System.out.println("En carga combo caja de solo x oficina A:"+Parametros.getIdOficina());
		
			
			Long oficinaId=((OficinaIf) this.getCmbOficina().getSelectedItem()).getId();
			
			System.out.println("En carga combo caja de solo x oficina B:"+oficinaId);
			
			List listaPrecios = (List) SessionServiceLocator.getCajaSessionService().findCajaByOficinaId(oficinaId);
			refreshCombo(getCmbCaja(),listaPrecios);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void report() {
		try {				
			if (getTblCajaEstado().getModel().getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Saldo Actual del Cliente?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/pos/RPVentasRealizadasVendedor.jasper";
					HashMap parametrosMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_MOVIMIENTO_CARTERA).iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					OficinaIf oficina = (OficinaIf) Parametros.getOficina();
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);

					parametrosMap.put("rucOperadorNegocio", operadorNegocio.getIdentificacion());
					parametrosMap.put("operadorNegocio",  operadorNegocio.getNombres()+operadorNegocio.getApellidos());
					parametrosMap.put("totalDebitos", totalDebitos);
					parametrosMap.put("totalCreditos", (totalCreditos * -1));
					parametrosMap.put("saldo", saldo);
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblCajaEstado().getModel(), true);
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
		//operadorNegocio = null;
		//getTxtOperadorNegocio().setText("");
		totalDebitos = 0D;
		totalCreditos = 0D;
		saldo = 0D;	
		devo = 0D;
	 
		cleanTable();
	}

	public void cleanTable() {
		//getMovimientoCarteraColeccion().clear();
		DefaultTableModel model = (DefaultTableModel) getTblCajaEstado().getModel();
		for(int i= this.getTblCajaEstado().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}


	public void llenar_datos_creditos(){
		totalDebitos = 0D;
		totalCreditos = 0D;
		Long vacio=0L;		
	//	EmpleadoIf empleado_vendedor = ((EmpleadoIf) this.getCmbVendedor().getSelectedItem());
		
		java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
		java.sql.Date fechaInicial = null;

		if (getCmbFechaInicial().getSelectedItem() != null)
			fechaInicialSeleccionada = new java.sql.Date(((Date) getCmbFechaInicial().getDate()).getYear(), ((Date) getCmbFechaInicial().getDate()).getMonth(), ((Date) getCmbFechaInicial().getDate()).getDate());
		else
			fechaInicialSeleccionada = null;

		if (fecha != null)
			fechaInicial = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());


		if(fechaInicial==null)
		{
			SpiritAlert.createAlert("Debe escoger una fecha", SpiritAlert.INFORMATION);
		}
		else{		
			Iterator ventasConsolidadoIt;
			
			
			String cajaCodigo=((CajaIf) this.getCmbCaja().getSelectedItem()).getCodigo();
			ventasConsolidadoIt = SessionServiceLocator.getVentasConsolidadoSessionService().findVentasConsolidadoByFechaAperturaVarioscajaCodigo(new java.sql.Timestamp(fechaInicial.getTime()),cajaCodigo).iterator();
			
			int numero = 1;
			BigDecimal suma=new BigDecimal("0");
			while (ventasConsolidadoIt.hasNext()) 
			{
				tableModel = (DefaultTableModel) getTblCajaEstado().getModel();					
				VentasConsolidadoIf ventasConsolidadoCabecera= (VentasConsolidadoIf) ventasConsolidadoIt.next();
				if (agregarDetalles(numero, ventasConsolidadoCabecera)){
					numero++;
				}					
			}
			getTblCajaEstado().validate();
			getTblCajaEstado().repaint();

			/*devo = totalCreditos;
			getTxtTotalDevoluciones().setText(formatoDecimal.format(Utilitarios.redondeoValor(devo)));
			
			saldo = totalDebitos;
			getTxtSaldo().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));		*/
		}
	}


	private TipoDocumentoIf findTipoDocumentoByCodigo(String codigo) throws GenericBusinessException {
		TipoDocumentoIf tipoDocumento = null;
		Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(codigo).iterator();
		if (it.hasNext())
			tipoDocumento = (TipoDocumentoIf) it.next();
		return tipoDocumento;
	}

	private boolean agregarDetalles(int numero, VentasConsolidadoIf ventasConsolidado) {
		Vector<Object> fila = new Vector<Object>();
		double totalDebitosMovimiento = 0D;
		double totalCreditosMovimiento = 0D;
		double saldoMovimiento = 0D;
		Long idDev=0L;
		Long idFactura=0L;
		Long idNotaVenta=0L;		
		
		
		
		System.out.println("VENTASCONSOLIDADO NOMBRE>>>"+ventasConsolidado.getCajeroNombre());

		TipoDocumentoIf tipoDocumento = null;
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");
		
		fila.add(ventasConsolidado.getCajeroNombre());
		if(ventasConsolidado.getFechaApertura()==null) fila.add("");
		else fila.add(ventasConsolidado.getFechaApertura().toString());
		if(ventasConsolidado.getFechaCierre()==null) fila.add("");
		else fila.add(ventasConsolidado.getFechaCierre().toString());
		
		
		String estado="CERRADO";
		
		if(ventasConsolidado.getFechaApertura()!=null && ventasConsolidado.getFechaCierre()==null)
			estado="ACTIVO";
		fila.add(estado);
		
		 
		tableModel.addRow(fila);		
		fila = new Vector<Object>();
		fila.add("");
		fila.add("");		
		fila.add("");
		fila.add("");
		 
		tableModel.addRow(fila);

		return true;
	}



	public String nombreVendedor(Long idVendedor){
		String nombre="SIN VENDEDOR";		
		Iterator vendedorIt;
		try {
			vendedorIt=SessionServiceLocator.getEmpleadoSessionService().findEmpleadoById(idVendedor).iterator();

			if(vendedorIt.hasNext()){
				EmpleadoIf empleado_vendedor = (EmpleadoIf)vendedorIt.next();	
				nombre=empleado_vendedor.getNombres()+" "+empleado_vendedor.getApellidos();
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nombre;
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


	public void delete() {
		// TODO Auto-generated method stub
		
	}

	
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}


	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}


	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}


	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}


	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

 
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}
}