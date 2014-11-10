package com.spirit.pos.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.pos.client.HotKeyComponentPOS;
import com.spirit.pos.entity.DonaciondetalleIf;
import com.spirit.pos.entity.VentasPagosIf;
import com.spirit.pos.gui.panel.JPDonacionesClientes;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.Utilitarios;



public class DonacionesClientesModel extends JPDonacionesClientes {
	private static final long serialVersionUID = -6273160673721006851L;
	private static String NOMBRE_MENU_MOVIMIENTO_CARTERA = "SALDO ACTUAL CLIENTE"; 
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private DefaultTableModel tableModel;
	ClienteIf operadorNegocio = null;
	java.sql.Date fechaInicial = null;
	java.sql.Date fechaFinal = null;
	double totalDonado;
	 
	double saldo;
	 
	boolean isTotal = false;

	private ClienteCriteria clienteCriteria;
	private JDPopupFinderModel popupFinder;

	public DonacionesClientesModel() {
		getTblDonaciones().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		showSaveMode();
		initListeners();
		anchoColumnasTabla();
		new HotKeyComponentPOS(this);
	}

	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDonaciones().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(25);
		anchoColumna = getTblDonaciones().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblDonaciones().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(85);
		anchoColumna = getTblDonaciones().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblDonaciones().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(250);
		anchoColumna = getTblDonaciones().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(80);
	}

	private void initListeners(){

		cargarComboFundaciones();



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
		getCmbFechaFinal().setLocale(Utilitarios.esLocale);
		getCmbFechaFinal().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFinal().setEditable(false);
		getCmbFechaFinal().setShowNoneButton(false);

		getBtnResetearFechas().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getCmbFechaInicial().setSelectedItem(null);
				getCmbFechaInicial().validate();
				getCmbFechaInicial().repaint();
				getCmbFechaFinal().setSelectedItem(null);
				getCmbFechaFinal().validate();
				getCmbFechaFinal().repaint();
			}
		});


		getBtnBuscarOperadorNegocio().setIcon(
				SpiritResourceManager
				.getImageIcon("images/pos/FindDocument.png"));
		getBtnBuscarOperadorNegocio().setText("");
		

		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				String tipoCliente = "CL";
				String tituloVentanaBusqueda = "";			
				clienteCriteria = new ClienteCriteria(tituloVentanaBusqueda, idCorporacion, tipoCliente);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(80);
				anchoColumnas.add(300);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					cleanTable();
					operadorNegocio = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtOperadorNegocio().setText(operadorNegocio.toString());
				}
			}
		});
	}

	public void cargarComboFundaciones(){
		SpiritComboBoxModel cmbModelTipoCliente;
		try {
			TipoClienteIf tipoClienteIf; 
			Collection<TipoClienteIf> tipoclientes  = SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByCodigo("FU");			 
							 
			if ( tipoclientes.size() == 1 ) 
			{
				tipoClienteIf = tipoclientes.iterator().next();					
				cmbModelTipoCliente = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getClienteSessionService().findClienteByTipoclienteId(tipoClienteIf.getId()));
				
				ClienteData empTodos = new ClienteData();
				empTodos.setNombreLegal("TODOS");				
				cmbModelTipoCliente.addElement(empTodos);
				
				/*ClienteEJB empTodos = new ClienteEJB();
				empTodos.setNombreLegal("TODOS");				
				cmbModelTipoCliente.addElement(empTodos);*/
				
				
				getCmbFundaciones().setModel(cmbModelTipoCliente);
			}
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GenericBusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void report() {
		try {				
			if (getTblDonaciones().getModel().getRowCount() > 0) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "�Desea generar el reporte de Saldo Actual del Cliente?", "Confirmaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/pos/RPDonacionClientes.jasper";
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

					parametrosMap.put("rucOperadorNegocio", "");
					parametrosMap.put("operadorNegocio",  "");		
					String fini="";
					if(fechaInicial!=null) fini=fechaInicial.toString();					
					parametrosMap.put("fechaInicial",fini);					
					String ffin="";
					if(fechaFinal!=null) ffin=fechaFinal.toString();					
					parametrosMap.put("fechaFinal", ffin);	
					parametrosMap.put("totalDonacion", new Double(getTxtSaldo().getText().toString().replace(",","")));
					
					ReportModelImpl.processReport(fileName, parametrosMap, (DefaultTableModel) getTblDonaciones().getModel(), true);
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
		totalDonado = 0D;
		 
		saldo = 0D;	
		getTxtSaldo().setText("");
		cleanTable();
	}

	public void cleanTable() {
		//getMovimientoCarteraColeccion().clear();
		DefaultTableModel model = (DefaultTableModel) getTblDonaciones().getModel();
		for(int i= this.getTblDonaciones().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	
	private Long findClienteOficinaByCliente(Long idCliente) throws GenericBusinessException {
		ClienteOficinaIf tipoDocumento = null;
		Long clienteoficinaId=0L;
		
		Iterator it = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(idCliente).iterator();
		if (it.hasNext())
		{
			tipoDocumento = (ClienteOficinaIf) it.next();			
			clienteoficinaId=tipoDocumento.getId();			 
		}
		return clienteoficinaId;
	}
	
	public void llenar_datos_creditos(){
		totalDonado = 0D;		 
		Long vacio=0L;			
		Long fundacionId=0L;		
		ClienteIf fundacionEscogida = ((ClienteIf) this.getCmbFundaciones().getSelectedItem());
		
		Long clienteOficinaId=0L;		
		java.util.Date fecha = (Date) getCmbFechaInicial().getDate();
		
	 
		if (fecha != null)	fechaInicial = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());

		fecha = (Date) getCmbFechaFinal().getDate();
		
		 

		if (fecha != null) fechaFinal = new java.sql.Date(fecha.getYear(),fecha.getMonth(),fecha.getDate());
				
		boolean bandera=false;
		OficinaIf oficina = (OficinaIf) Parametros.getOficina();	
		if(oficina.getCodigo().equals("MATRIZ"))
		{			
			if(fechaInicial==null)
			{		
				SpiritAlert.createAlert("Debe escoger una fecha Inicial. Muchas gracias.", SpiritAlert.INFORMATION);			
			}
			else{

				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
				String strFecha = "2009-07-22";
				Date fechaBase = null;
				try {
					fechaBase = formatoDelTexto.parse(strFecha);
				} catch (ParseException ex) {
					ex.printStackTrace();
				}		

				long fechaBaseMs = fechaBase.getTime();

				long fechaFinalMs = fechaInicial.getTime();
				long diferencia = fechaFinalMs - fechaBaseMs;
				double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));


				if(dias>0)	bandera=true;
			}
		}				
		else{
			bandera=true;
		}
		
				
		if(bandera){	 

		VentasPagosIf vt;
		try {
			if(operadorNegocio!=null) 
				clienteOficinaId=findClienteOficinaByCliente(operadorNegocio.getId());
			else
				clienteOficinaId=null;
			
			
			Long tipoDonacion = findTipoPagoIf("DO");
			if(fundacionEscogida==null)
			{
				SpiritAlert.createAlert("Debe escoger una Fundaci�n", SpiritAlert.INFORMATION);
			}
			else{		
				Iterator ventasPagosIterator;
				ArrayList ventaspagosArray = new ArrayList();
				
				if(fundacionEscogida.getNombreLegal().equals("TODAS")) fundacionId=null; 
				else fundacionId=fundacionEscogida.getId();
				
				//ventaspagosArray = (ArrayList)getVentasPagosSessionService().findDonacionesClientes(tipoDonacion, fechaInicial, fechaFinal,fundacionId,clienteOficinaId);
				//ventaspagosArray = (ArrayList)getVentasPagosSessionService().findDonacionesDetalle(fechaInicial, fechaFinal,fundacionId,colorId,modeloId,tallaId,tipoProductoId,clienteId);
				ventaspagosArray = (ArrayList)SessionServiceLocator.getVentasPagosSessionService().findDonacionesDetalle((fechaInicial != null)?new java.sql.Timestamp(fechaInicial.getTime()):null, (fechaFinal != null)?new java.sql.Timestamp(fechaFinal.getTime()):null,fundacionId,null,null,null,null,clienteOficinaId);


				if(ventaspagosArray.size()>0){
					ventasPagosIterator = ventaspagosArray.iterator();
					int numero = 1;
					while (ventasPagosIterator.hasNext()) {
						//Object[] ventasPagos = (Object[]) ventasPagosIterator.next();
						DonaciondetalleIf donacionDetalle = (DonaciondetalleIf) ventasPagosIterator.next();		
						/*VentasPagosIf ventaspagosIf = (VentasPagosIf) ventasPagos[0];
						FacturaIf facturaIf = (FacturaIf) ventasPagos[1];
						ClienteIf clienteFundacionIf = (ClienteIf) ventasPagos[2];*/						
						tableModel = (DefaultTableModel) getTblDonaciones().getModel();
						if (agregarDetalles(numero,donacionDetalle))
							numero++;
					}
				}
			}
 
			getTblDonaciones().validate();
			getTblDonaciones().repaint();

			saldo = totalDonado;
			getTxtSaldo().setText(formatoDecimal.format(Utilitarios.redondeoValor(saldo)));		

		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			SpiritAlert.createAlert("Solo puede consultar valores mayor a la fecha 2009-07-23. Muchas gracias.", SpiritAlert.INFORMATION);
			
		}
	}



	private TipoDocumentoIf findTipoDocumentoByCodigo(String codigo) throws GenericBusinessException {
		TipoDocumentoIf tipoDocumento = null;
		Iterator it = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByCodigo(codigo).iterator();
		if (it.hasNext())
			tipoDocumento = (TipoDocumentoIf) it.next();
		return tipoDocumento;
	}

	private String findClienteByOficina(Long idOficina) throws GenericBusinessException {
		ClienteIf tipoDocumento = null;
		String nombreCliente="";
		Iterator it = SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(idOficina).iterator();
		if (it.hasNext())
		{tipoDocumento = (ClienteIf) it.next();
		nombreCliente=tipoDocumento.getNombreLegal();			 
		}
		return nombreCliente;
	}

	private String findClienteById(Long id) throws GenericBusinessException {
		ClienteIf tipoDocumento = null;
		String nombreFundacion="";
		Iterator it = SessionServiceLocator.getClienteSessionService().findClienteById(id).iterator();
		if (it.hasNext())
		{tipoDocumento = (ClienteIf) it.next();
		nombreFundacion=tipoDocumento.getNombreLegal();			 
		}
		return nombreFundacion;
	}


	private Long findTipoPagoIf(String codigo) throws GenericBusinessException {
		TipoPagoIf tipoPago = null;
		Long idtipoPago=0L;
		Iterator it = SessionServiceLocator.getTipoPagoSessionService().findTipoPagoByCodigo("DO").iterator();
		if (it.hasNext())
		{tipoPago = (TipoPagoIf) it.next();
		idtipoPago=tipoPago.getId();
		}


		return idtipoPago;
	}

	private boolean agregarDetalles(int numero,DonaciondetalleIf donacionDetalle) {
		Vector<Object> fila = new Vector<Object>();
		double totalDebitosMovimiento = 0D;
		double totalCreditosMovimiento = 0D;
		double saldoMovimiento = 0D;

		Long idDev=0L;
		Long idFactura=0L;
		Long idNotaVenta=0L;

		TipoDocumentoIf tipoDocumento = null;
		fila.add("<html><b>" + String.valueOf(numero) + "</b></html>");

		String fecha = donacionDetalle.getFecha().toString();
		fila.add(fecha);
		////////////////////////
		String transaccion = donacionDetalle.getPreimpreso();
		if(transaccion==null) transaccion="";
		fila.add(transaccion);	
		//////////////////////////////
		String nombrecliente = donacionDetalle.getNombrecliente();
		if(nombrecliente==null) nombrecliente="";
		fila.add(nombrecliente);	
		 
		String fundacionString=donacionDetalle.getFundacion();
		if(fundacionString==null) 
			{
			fundacionString="";
			fundacionString=donacionDetalle.getNombrefundaciondevuelta();
			}
		fila.add(fundacionString);	
		
		
		
		//String nombreCliente= findClienteByOficina(facturaCabecera.getClienteoficinaId());
		/*String nombreCliente= cliente;		
		fila.add(nombreCliente);*/		
		fila.add("<html><b>" + donacionDetalle.getTotal().toString()+ "</b></html>"); 
		totalDonado +=new Double(donacionDetalle.getTotal().toString()).doubleValue();		
		//totalDonado += ventasPagos.getValor().doubleValue();
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


/*
	public String nombreVendedor(Long idVendedor){
		String nombre="SIN VENDEDOR";		
		Iterator vendedorIt;
		try {
			vendedorIt=getEmpleadoSessionService().findEmpleadoById(idVendedor).iterator();

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
*/


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

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
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