package com.spirit.sri.gui.model;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.sri.at.Anulados;
import com.spirit.sri.at.DetalleAnulados;
import com.spirit.sri.at.Ventas;
import com.spirit.sri.dimm.UtilesDimm;
import com.spirit.sri.dimm.reoc.GenerarReocActionListener;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriProveedorRetencionIf;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.sri.entity.SriTipoTransaccionIf;
import com.spirit.sri.gui.components.AirPopupActionListener;
import com.spirit.sri.gui.components.RetencionActionListener;
import com.spirit.sri.gui.panel.JPDimm;
import com.spirit.sri.gui.util.IfTablas;
import com.spirit.sri.gui.util.IniciarMapas;
import com.spirit.sri.gui.util.TableCellRenderer;
import com.spirit.sri.reoc.Air;
import com.spirit.sri.reoc.Compras;
import com.spirit.sri.reoc.DetalleAir;
import com.spirit.sri.reoc.DetalleCompras;
import com.spirit.sri.reoc.Reoc;
import com.spirit.util.Utilitarios;

public class DimmModel_0 extends JPDimm implements IfTablas {

	private static final long serialVersionUID = 3473304962664492109L;

	private boolean errorGeneral = false;
	private DecimalFormat formatoDosEnteros = new DecimalFormat("00");
	//private DecimalFormat formatoDosDecimales = new DecimalFormat("#######.##");
	//private Format formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	//private Format formatoFechaCaducidad = new SimpleDateFormat("MM/yyyy");
	private DecimalFormat formatoSecuencial = new DecimalFormat("0000000");
	//private DecimalFormat formatoAutorizacion = new DecimalFormat("0000000");
	private Map<String, Date> mapaQuery;
	TableCellRenderer cellRenderer;
	private int numeroFila=1;
	private String nombreArchivo = "";
	
	//private Iva ivaGlobal = null;
	private Reoc reoc = null;
	private int mesConsulta = 0;
	private int anioConsulta = 0;
	
	private GenerarReocActionListener generarReocActionListener = new GenerarReocActionListener();

	private HashMap<String, Double> mapaImpuestos = null;
	
	private HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion = null;
	private HashMap<String, SriTipoTransaccionIf> mapaCodigoTransaccion = null;
	private HashMap<String, String> mapaCodigoTipoIdentificacionCompraXCodigoSpirit = null;
	private HashMap<String, String> mapaCodigoTipoIdentificacionCompraXCodigoSri = null;
	
	private HashMap<String, String> mapaTipoComprobantes = null;
	private HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes = null;
	private HashMap<Long, SriAirIf> mapaAir= null;

	private String nivelRaiz="\n",nivel1="\n\t",nivel2="\n\t\t"
					,nivel3="\n\t\t\t",nivel4="\n\t\t\t\t",nivel5="\n\t\t\t\t\t";

	//private SriIvaBienIf retencionIvaBien = null;
	//private SriIvaServicioIf retencionIvaServicio = null;

	private AirPopupActionListener ventasActionListener = null, comprasActionListener = null;
	private RetencionActionListener retencionActionListener = null;
	private JMenuItem itemAirCompra = null,itemAirVenta = null, itemRetencionCompra = null;

	public DimmModel_0(){
		initListeners();
		new HotKeyComponent(this);
		setSorterTable(getTblCompras());
		setSorterTable(getTblVentas());
		setSorterTable(getTblExportaciones());
		setSorterTable(getTblAnulaciones());

		//cargarRetencionesProveedor();
		//cargarRetencionesCliente();
		//mapaSustentoTributario = IniciarMapas.cargarSustentoTributario();

		mapaImpuestos = IniciarMapas.cargarImpuestos();
		//mapaCodigoIva = IniciarMapas.cargarCodigosIva();
		//mapaCodigoIvaBien = IniciarMapas.cargarCodigosIvaBien();
		//mapaCodigoIvaServicio = IniciarMapas.cargarCodigosIvaServicio();
		//mapaCodigoIce = IniciarMapas.cargarCodigosIce();
		mapaAir = IniciarMapas.cargarAir();

		Object[] objectosTiposComprobantes = IniciarMapas.cargarCodigoTipoComprobantes();
		mapaTipoComprobantes = (HashMap<String, String>)objectosTiposComprobantes[0];
		mapaCodigoTipoComprobantes = (HashMap<Long, SriTipoComprobanteIf>)objectosTiposComprobantes[1];

		mapaCodigoIdentificacion = IniciarMapas.cargarCodigoIdentificacion();
		mapaCodigoTransaccion = IniciarMapas.cargarTipoTransacciones();
		
		Object[] objectosTiposIdentificaion = IniciarMapas.cargarCodigosTipoIdentificacion("COMPRA",mapaCodigoTransaccion,mapaCodigoIdentificacion);
		mapaCodigoTipoIdentificacionCompraXCodigoSpirit = (HashMap<String, String>) objectosTiposIdentificaion[0];
		mapaCodigoTipoIdentificacionCompraXCodigoSri = (HashMap<String, String>) objectosTiposIdentificaion[1];
		
		objectosTiposIdentificaion = IniciarMapas.cargarCodigosTipoIdentificacion("VENTA",mapaCodigoTransaccion,mapaCodigoIdentificacion);
		//mapaCodigoTipoIdentificacionVenta = (HashMap<String, String>) objectosTiposIdentificaion[0]; 

		iniciarComponentes();
		showFindMode();
	}

	private void iniciarComponentes(){
		cellRenderer = new TableCellRenderer();
		Calendar cal = new GregorianCalendar();
		getCmbMes().setSelectedIndex(cal.get(Calendar.MONTH));
		getSpnAnio().setValue(cal.get(Calendar.YEAR));
		modificarTablaCompras();
		modificarTablaVentas();
		modificarTablaAnulaciones();

		//POPUPS
		crearPopupCompra();
		crearPopupVenta();
	}

	private void crearPopupCompra(){
		comprasActionListener = new AirPopupActionListener(false);
		retencionActionListener = new RetencionActionListener();

		JPopupMenu menu = new JPopupMenu();
		menu.addPopupMenuListener(popupComprasListener);

		itemAirCompra = new JMenuItem("AIR");
		itemAirCompra.addActionListener(comprasActionListener);
		menu.add(itemAirCompra);

		itemRetencionCompra = new JMenuItem("RETENCIONES");
		itemRetencionCompra.addActionListener(retencionActionListener);
		menu.add(itemRetencionCompra);

		getTblCompras().setComponentPopupMenu(menu);
	}

	private void crearPopupVenta(){
		ventasActionListener = new AirPopupActionListener(false);

		JPopupMenu menu = new JPopupMenu();
		menu.addPopupMenuListener(popupVentasListener);

		itemAirVenta = new JMenuItem("AIR");
		itemAirVenta.addActionListener(ventasActionListener);
		menu.add(itemAirVenta);

		getTblVentas().setComponentPopupMenu(menu);
	}

	private void initListeners(){
		
		getBtnGenerarReoc().addActionListener(generarReocActionListener);
		
		getBtnConsultar().addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					consultar();
				} catch (InterruptedException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error en Consulta !!", SpiritAlert.ERROR);
				}
			}
		});
	}
	
	private void consultar() throws InterruptedException {
		clean();
		final Cursor cursor = getCursor();
		setCursor(cursor.getPredefinedCursor(cursor.WAIT_CURSOR));
		Runnable consultaTotal = new Runnable(){
			public void run() {
				try{
					buildQuery();
					generarReocActionListener.setReocGlobal(null);
					getTxtTextoXML().append(nivelRaiz+"<reoc>");
					//ivaGlobal = new Iva();
		
					//**** COMPRAS ****
					reoc = new Reoc();
					llenarInfoEmpresa(reoc);
					
					reoc.setAnio(anioConsulta);
					getTxtTextoXML().append(nivel1+"<anio>"+reoc.getAnio()+"</anio>");
					
					reoc.setMes( formatoDosEnteros.format(mesConsulta+1) );
					getTxtTextoXML().append(nivel1+"<mes>"+reoc.getMes()+"</mes>");
					
					numeroFila = 1;
					Compras compras = new Compras();
					if ( getChbCompras().isSelected() ){
						getTxtTextoXML().append(nivel1+"<compras>");
						getTxtErrores().append("*-------------COMPRAS-------------*\n");
						numeroFila = cargarCompras(numeroFila,compras);
						getTxtErrores().append("\n*-------------COMPRAS - NOTAS DE CREDITO-------------*\n");
						numeroFila = cargarNotaCreditoCompra(numeroFila,compras);
						cargarNotaDebitoCompra(compras);
		
						reoc.setCompras(compras);
						
						getTxtTextoXML().append(nivel1+"</compras>");
					}
					//ivaGlobal.setCompras(compras);
					
					
					
					
					
					
					generarReocActionListener.setReocGlobal(reoc);
					generarReocActionListener.setNombreArchivo(nombreArchivo);
		
				} catch(Exception e){
					errorGeneral = true;
					e.printStackTrace();
					if ( e instanceof ParseException )
						SpiritAlert.createAlert("Error en la conversi\u00f3n de la fecha", SpiritAlert.WARNING);
					else if ( e instanceof GenericBusinessException )
						SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
					else
						SpiritAlert.createAlert("Error en la obtencion de datos", SpiritAlert.WARNING);
				}
				setCursor(cursor.getPredefinedCursor(cursor.DEFAULT_CURSOR));
				getTxtTextoXML().append(nivelRaiz+"</reoc>");
			}	
		};
		Thread t = new Thread(consultaTotal);
		t.start();
	}
	
	
	/*private void consultar() {
		clean();
		try{
			buildQuery();
			generarActionListener.setIvaGlobal(null);
			ivaGlobal = new Iva();

			llenarInfoEmpresa();

			//**** COMPRAS ****
			numeroFila = 1;
			Compras compras = new Compras();
			getTxtTextoXML().append(nivelRaiz+"<compras>");
			if ( getChbCompras().isSelected() ){
				getTxtErrores().append("*-------------COMRPAS-------------*\n");
				numeroFila = cargarCompras(numeroFila,compras);
				getTxtErrores().append("\n*-------------COMPRAS - NOTAS DE CREDITO-------------*\n");
				numeroFila = cargarNotaCreditoCompra(numeroFila,compras);
				cargarNotaDebitoCompra(compras);
			}
			ivaGlobal.setCompras(compras);
			getTxtTextoXML().append(nivelRaiz+"</compras>");

			
			//**** VENTAS ****
			numeroFila = 1;
			Ventas ventas = new Ventas();
			getTxtTextoXML().append(nivelRaiz+"<ventas>");
			if ( getChbVentas().isSelected() ){
				getTxtErrores().append("\n*-------------VENTAS FACTURA-------------*\n");
				numeroFila = cargarVentas(numeroFila,"FAC",ventas);
				getTxtErrores().append("\n*-------------VENTAS FACTURAS DE REEMBOLSO-------------*\n");
				numeroFila = cargarVentas(numeroFila,"FAR",ventas);
				getTxtErrores().append("\n*-------------VENTAS - NOTAS DE CREDITO-------------*\n");
				numeroFila = cargarNotaCreditoVenta(numeroFila,ventas);
				cargarNotaDebitoVenta(ventas);
			}
			ivaGlobal.setVentas(ventas);
			getTxtTextoXML().append(nivelRaiz+"</ventas>");
			
			//**** ANULADOS *****
			numeroFila=1;
			Anulados anulados = new Anulados();
			getTxtTextoXML().append("\n<anulados>");
			if ( getChbAnulaciones().isSelected() ){
				getTxtErrores().append("\n*-------------FACTURAS ANULADAS-------------*\n");
				numeroFila = cargarFacturasAnuladas(numeroFila,"FAC",anulados);
				getTxtErrores().append("\n*-------------FACTURAS REEMBOLSO ANULADAS-------------*\n");
				numeroFila = cargarFacturasAnuladas(numeroFila,"FAR",anulados);
			}
			ivaGlobal.setAnulados(anulados);
			getTxtTextoXML().append("\n</anulados>");
			
			//**** IMPORTACIONES ****
			numeroFila=1;
			Importaciones importaciones = new Importaciones();
			getTxtTextoXML().append("\n<importaciones>");
			if ( getChbImportaciones().isSelected() ){
				
			}
			ivaGlobal.setImportaciones(importaciones);
			getTxtTextoXML().append("\n</importaciones>");

			//**** EXPORTACIONES ****
			{
				ivaGlobal.setExportaciones(new Exportaciones());
				getTxtTextoXML().append("\n<exportaciones></exportaciones>");
				ivaGlobal.setRecap(new Recap());
				getTxtTextoXML().append("\n<recap></recap>");
				ivaGlobal.setFideicomisos(new Fideicomisos());
				getTxtTextoXML().append("\n<fideicomisos></fideicomisos>");
	
				ivaGlobal.setRendFinancieros(new RendFinancieros());
				getTxtTextoXML().append("\n<rendFinacieros></rendFinacieros>");
			}

			generarActionListener.setIvaGlobal(ivaGlobal);
			generarActionListener.setNombreArchivo(nombreArchivo);

		} catch(Exception e){
			errorGeneral = true;
			e.printStackTrace();
			if ( e instanceof ParseException )
				SpiritAlert.createAlert("Error en la conversi\u00f3n de la fecha", SpiritAlert.WARNING);
			else if ( e instanceof GenericBusinessException )
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.WARNING);
			else
				SpiritAlert.createAlert("Error en la obtencion de datos", SpiritAlert.WARNING);
		}
	}*/

	PopupMenuListener popupComprasListener = new PopupMenuListener(){
		public void popupMenuCanceled(PopupMenuEvent e) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			itemAirCompra.setEnabled(false);
			itemRetencionCompra.setEnabled(false);
			int filaSeleccionada = getTblCompras().getSelectedRow();
			if (filaSeleccionada!=-1){
				int filaSelTransf = getTblCompras().convertRowIndexToModel(filaSeleccionada);
				DetalleCompras detalleCompra = reoc.getCompras().getDetalleCompras(filaSelTransf);
				if (detalleCompra!=null){
					try {
						HashMap<Double, String> mapaValorRetencion = new HashMap<Double, String>();
						ClienteIf cliente = (ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(detalleCompra.getIdProv()).iterator().next();
						Map<String,String> mapaParametros = new HashMap<String, String>();
						mapaParametros.put("tipoPersona", cliente.getTipoPersona());
						mapaParametros.put("llevaContabilidad", cliente.getLlevaContabilidad());
						Collection retenciones = SessionServiceLocator.getProveedorRetencionSessionService().findSriProveedorRetencionByQuery(mapaParametros);
						Collection<SriAirIf> porcentajesAir = new Vector<SriAirIf>();
						for (Iterator itRetenciones = retenciones.iterator();itRetenciones.hasNext();){
							SriProveedorRetencionIf retencion = (SriProveedorRetencionIf)itRetenciones.next();
							if ( !mapaValorRetencion.containsKey(retencion.getRetefuente().doubleValue()) ){
								Collection airs = SessionServiceLocator.getAirSessionService().findSriAirByPorcentaje(retencion.getRetefuente());
								for (Iterator itAirs = airs.iterator();itAirs.hasNext();){
									SriAirIf air = (SriAirIf)itAirs.next();
									porcentajesAir.add(air);
								}
								mapaValorRetencion.put(retencion.getRetefuente().doubleValue(), "");
							}
						}
						comprasActionListener.setDetalleCompra(detalleCompra);
						comprasActionListener.setPorcentajesAir(porcentajesAir);

						retencionActionListener.setDetalle(detalleCompra);
						itemAirCompra.setEnabled(true);
						itemRetencionCompra.setEnabled(true);
					} catch (GenericBusinessException e1) {
						e1.printStackTrace();
						SpiritAlert.createAlert("No se puede obtener informaci\u00f3n del cliente", SpiritAlert.WARNING);
					}
				}
			}
		}
	};

	PopupMenuListener popupVentasListener = new PopupMenuListener(){
		
		public void popupMenuCanceled(PopupMenuEvent e) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			/*
			itemAirVenta.setEnabled(false);
			int filaSeleccionada = getTblVentas().getSelectedRow();
			if (filaSeleccionada!=-1){
				int filaSelecTransf = getTblVentas().convertRowIndexToModel(filaSeleccionada);
				DetalleVentas detalleVenta =  ivaGlobal.getVentas().getDetalleVentas(filaSelecTransf);
				if (detalleVenta != null){
					try {
						ClienteIf cliente = (ClienteIf)getClienteSessionService().findClienteByIdentificacion(detalleVenta.getIdCliente()).iterator().next();
						SriClienteRetencionIf retencion = (SriClienteRetencionIf)getClienteRetencionSessionService().findSriClienteRetencionByContribuyenteEspecial(
								cliente.getContribuyenteEspecial()).iterator().next();
						Collection porcentajesAir = getAirSessionService().findSriAirByPorcentaje(retencion.getRetefuente());
						ventasActionListener.setDetalleVenta(detalleVenta);
						ventasActionListener.setPorcentajesAir(porcentajesAir);
						itemAirVenta.setEnabled(true);
					} catch (GenericBusinessException e1) {
						e1.printStackTrace();
						SpiritAlert.createAlert("No se puede obtener informaci\u00f3n del cliente", SpiritAlert.WARNING);
					}
				}
			}
			*/
		}
	};

	public void refresh() {
		try {
			UtilesDimm.refrescarParametroDirectorio(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
		/*if ( generarReocActionListener != null )
			try {
				generarReocActionListener.refresacarParametroDirectrio();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			}*/
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}

	private void buildQuery() throws GenericBusinessException, ParseException{
		mapaQuery = null;
		mapaQuery = new HashMap<String, Date>();
		String mes = (String)getCmbMes().getSelectedItem();
		anioConsulta = ((Integer)getSpnAnio().getValue()).intValue();
		int anioRestado = anioConsulta - 1900;
		mesConsulta = Utilitarios.getMesInt(mes);
		Date fechaInicio = new Date(anioRestado,mesConsulta,1);
		Date fechaFin = Utilitarios.fechaHoy(mesConsulta, anioRestado);

		mapaQuery.put("fechaInicio", fechaInicio);
		mapaQuery.put("fechaFin", fechaFin);

		nombreArchivo = "REOC" + formatoDosEnteros.format( mesConsulta+1 ) + anioConsulta ;
	}

	private int cargarCompras(int numeroFila,Compras compras) throws GenericBusinessException{
		try{ 
			
			
			Collection<Object> detalles = detalles = SessionServiceLocator.getSriManagerSessionService().generarDetallesCompraReoc(numeroFila, mapaQuery, Parametros.getIdEmpresa()
					,mapaAir,mapaCodigoIdentificacion, mapaCodigoTipoIdentificacionCompraXCodigoSpirit, mapaImpuestos,Parametros.getIdEmpresa());
			
			DefaultTableModel modelo = (DefaultTableModel)getTblCompras().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleCompras){
					DetalleCompras detalleCompra = (DetalleCompras) objeto;
					{
						getTxtTextoXML().append(nivel2+"<detalleCompras>");
						getTxtTextoXML().append(nivel3+"<tpIdProv>"+detalleCompra.getTpIdProv()+"</tpIdProv>");
						getTxtTextoXML().append(nivel3+"<idProv>"+detalleCompra.getIdProv()+"</idProv>");
						getTxtTextoXML().append(nivel3+"<tipoComp>"+detalleCompra.getTipoComp()+"</tipoComp>");
						getTxtTextoXML().append(nivel3+"<aut>"+detalleCompra.getAut()+"</aut>");
						getTxtTextoXML().append(nivel3+"<estab>"+detalleCompra.getEstab()+"</estab>");
						getTxtTextoXML().append(nivel3+"<ptoEmi>"+detalleCompra.getPtoEmi()+"</ptoEmi>");
						getTxtTextoXML().append(nivel3+"<sec>"+detalleCompra.getSec()+"</sec>");
						getTxtTextoXML().append(nivel3+"<fechaEmiCom>"+detalleCompra.getFechaEmiCom()+"</fechaEmiCom>");
						
						/*getTxtTextoXML().append(nivel2+"<fechaRegistro>"+detalleCompra.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel2+"<fechaCaducidad>"+detalleCompra.getFechaCaducidad()+"</fechaCaducidad>");
						getTxtTextoXML().append(nivel2+"<baseImponible>"+detalleCompra.getBaseImponible()+"</baseImponible>");
						getTxtTextoXML().append(nivel2+"<baseImpGrav>"+detalleCompra.getBaseImpGrav()+"</baseImpGrav>");
						getTxtTextoXML().append(nivel2+"<porcentajeIva>"+detalleCompra.getPorcentajeIva()+"</porcentajeIva>");
						getTxtTextoXML().append(nivel2+"<montoIva>"+detalleCompra.getMontoIva()+"</montoIva>");
						getTxtTextoXML().append(nivel2+"<baseImpIce>"+detalleCompra.getBaseImpIce()+"</baseImpIce>");
						getTxtTextoXML().append(nivel2+"<porcentajeIce>"+detalleCompra.getPorcentajeIce()+"</porcentajeIce>");
						getTxtTextoXML().append(nivel2+"<montoIce>"+detalleCompra.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel2+"<montoIvaBienes>"+detalleCompra.getMontoIvaBienes()+"</montoIvaBienes>");
						getTxtTextoXML().append(nivel2+"<porRetBienes>"+detalleCompra.getPorRetBienes()+"</porRetBienes>");
						getTxtTextoXML().append(nivel2+"<valorRetBienes>"+detalleCompra.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel2+"<montoIvaServicios>"+detalleCompra.getMontoIvaServicios()+"</montoIvaServicios>");
						getTxtTextoXML().append(nivel2+"<porRetServicios>"+detalleCompra.getPorRetServicios()+"</porRetServicios>");
						getTxtTextoXML().append(nivel2+"<valorRetServicios>"+detalleCompra.getValorRetServicios()+"</valorRetServicios>");
						 */
						getTxtTextoXML().append(nivel3+"<air>");
						Air air = detalleCompra.getAir();
						DetalleAir[] detallesAir = air.getDetalleAir();
						for(int i=0;i<detallesAir.length;i++){
							DetalleAir detalleAir = detallesAir[i];
							getTxtTextoXML().append(nivel4+"<detalleAir>");
							getTxtTextoXML().append(nivel5+"<codRetAir>"+detalleAir.getCodRetAir()+"</codRetAir>");
							getTxtTextoXML().append(nivel5+"<porcentaje>"+detalleAir.getPorcentaje()+"</porcentaje>");
							getTxtTextoXML().append(nivel5+"<base0>"+detalleAir.getBase0()+"</base0>");
							getTxtTextoXML().append(nivel5+"<baseGrav>"+detalleAir.getBaseGrav()+"</baseGrav>");
							getTxtTextoXML().append(nivel5+"<baseNoGrav>"+detalleAir.getBaseNoGrav()+"</baseNoGrav>");
							getTxtTextoXML().append(nivel5+"<valRetAir>"+detalleAir.getValRetAir()+"</valRetAir>");
							getTxtTextoXML().append(nivel4+"</detalleAir>");
						}
						getTxtTextoXML().append(nivel3+"<air/>");
	
						if (detalleCompra.getEstabRet()!=null){
							getTxtTextoXML().append(nivel3+"<estabRet>"+detalleCompra.getEstabRet()+"</estabRet>");
							getTxtTextoXML().append(nivel3+"<ptoEmiRet>"+detalleCompra.getPtoEmiRet()+"</ptoEmiRet>");;
							getTxtTextoXML().append(nivel3+"<secRet>"+String.valueOf(detalleCompra.getSecRet())+"</secRet>");
							getTxtTextoXML().append(nivel3+"<autRet>"+detalleCompra.getAutRet()+"</autRet>");
							getTxtTextoXML().append(nivel3+"<fechaEmiRet>"+detalleCompra.getFechaEmiRet()+"</fechaEmiRet>");
						}
						if (detalleCompra.getEstabRet1()!=null){
							getTxtTextoXML().append(nivel3+"<estabRet1>"+detalleCompra.getEstabRet1()+"</estabRet1>");
							getTxtTextoXML().append(nivel3+"<ptoEmiRet1>"+detalleCompra.getPtoEmiRet1()+"</ptoEmiRet1>");
							getTxtTextoXML().append(nivel3+"<secRet1>"+String.valueOf(detalleCompra.getSecRet1())+"</secRet1>");
							getTxtTextoXML().append(nivel3+"<autRet1>"+detalleCompra.getAutRet1()+"</autRet1>");
							getTxtTextoXML().append(nivel3+"<fechaEmiRet1>"+detalleCompra.getFechaEmiRet1()+"</fechaEmiRet1>");
						}
	
						/*getTxtTextoXML().append(nivel2+"<docModificado>0</docModificado>");
						getTxtTextoXML().append(nivel2+"<fechaEmiModificado>00/00/0000</fechaEmiModificado>");
						getTxtTextoXML().append(nivel2+"<estabModificado>000</estabModificado>");
						getTxtTextoXML().append(nivel2+"<ptoEmiModificado>000</ptoEmiModificado>");
						getTxtTextoXML().append(nivel2+"<secModificado>0</secModificado>");
						getTxtTextoXML().append(nivel2+"<autModificado>000000000</autModificado>");
						getTxtTextoXML().append(nivel2+"<montoTituloOneroso>0.00</montoTituloOneroso>");
						getTxtTextoXML().append(nivel2+"<montoTituloGratuito>0.00</montoTituloGratuito>");
						*/
					}
					compras.addDetalleCompras(detalleCompra);
					getTxtTextoXML().append(nivel2+"</detalleCompras>");
	
					Object[] fila = new Object[]{"1",numeroFila
							,mapaCodigoTipoIdentificacionCompraXCodigoSri.get(detalleCompra.getTpIdProv())
							,detalleCompra.getIdProv()
							,mapaTipoComprobantes.get( String.valueOf(detalleCompra.getTipoComp() ) )
							,detalleCompra.getEstab()+"-"+detalleCompra.getPtoEmi()+"-"+detalleCompra.getSec()
							,detalleCompra.getAut()
							,detalleCompra.getFechaEmiCom()};
					modelo.addRow(fila);
	
				} else if (objeto instanceof String){
					compras.addDetalleCompras(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","","0-0-0","",""};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Compras", SpiritAlert.ERROR);
		}
		return numeroFila;
	}

	private int cargarNotaCreditoCompra(int numeroFila,Compras compras) throws GenericBusinessException{
		/*
		try{
			Collection<Object> detalles = getSriManagerSessionService().generarNotasCreditoCompra(numeroFila,Parametros.getIdEmpresa(), mapaQuery, mapaCodigoIdentificacion, mapaCodigoTipoIdentificacionCompra, mapaImpuestos, mapaCodigoIva);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblCompras().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleCompras){
					DetalleCompras detalleCompra = (DetalleCompras) objeto;
					{
						getTxtTextoXML().append(nivel1+"<detalleCompras>");
						getTxtTextoXML().append(nivel2+"<codSustento>"+detalleCompra.getCodSustento()+"</codSustento>");
						getTxtTextoXML().append(nivel2+"<devIva>"+detalleCompra.getDevIva()+"</devIva>");
						getTxtTextoXML().append(nivel2+"<tpIdProv>"+detalleCompra.getTpIdProv()+"</tpIdProv>");
						getTxtTextoXML().append(nivel2+"<idProv>"+detalleCompra.getIdProv()+"</idProv>");
						getTxtTextoXML().append(nivel2+"<tipoComprobante>"+detalleCompra.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel2+"<fechaRegistro>"+detalleCompra.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel2+"<establecimiento>"+detalleCompra.getEstablecimiento()+"</establecimiento>");
						getTxtTextoXML().append(nivel2+"<puntoEmision>"+detalleCompra.getPuntoEmision()+"</puntoEmision>");
						getTxtTextoXML().append(nivel2+"<secuencial>"+detalleCompra.getSecuencial()+"</secuencial>");
						getTxtTextoXML().append(nivel2+"<fechaEmision>"+detalleCompra.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel2+"<autorizacion>"+detalleCompra.getAutorizacion()+"</autorizacion>");
						getTxtTextoXML().append(nivel2+"<fechaCaducidad>"+detalleCompra.getFechaCaducidad()+"</fechaCaducidad>");
						getTxtTextoXML().append(nivel2+"<baseImponible>"+detalleCompra.getBaseImponible()+"</baseImponible>");
						getTxtTextoXML().append(nivel2+"<baseImpGrav>"+detalleCompra.getBaseImpGrav()+"</baseImpGrav>");
						getTxtTextoXML().append(nivel2+"<porcentajeIva>"+detalleCompra.getPorcentajeIva()+"</porcentajeIva>");
						getTxtTextoXML().append(nivel2+"<montoIva>"+detalleCompra.getMontoIva()+"</montoIva>");
						getTxtTextoXML().append(nivel2+"<baseImpIce>"+detalleCompra.getBaseImpIce()+"</baseImpIce>");
						getTxtTextoXML().append(nivel2+"<porcentajeIce>"+detalleCompra.getPorcentajeIce()+"</porcentajeIce>");
						getTxtTextoXML().append(nivel2+"<montoIce>"+detalleCompra.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel2+"<montoIvaBienes>"+detalleCompra.getMontoIvaBienes()+"</montoIvaBienes>");
						getTxtTextoXML().append(nivel2+"<porRetBienes>"+detalleCompra.getPorRetBienes()+"</porRetBienes>");
						getTxtTextoXML().append(nivel2+"<valorRetBienes>"+detalleCompra.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel2+"<montoIvaServicios>"+detalleCompra.getMontoIvaServicios()+"</montoIvaServicios>");
						getTxtTextoXML().append(nivel2+"<porRetServicios>"+detalleCompra.getPorRetServicios()+"</porRetServicios>");
						getTxtTextoXML().append(nivel2+"<valorRetServicios>"+detalleCompra.getValorRetServicios()+"</valorRetServicios>");
	
						getTxtTextoXML().append(nivel2+"<air>");
						getTxtTextoXML().append(nivel2+"<air/>");
	
						getTxtTextoXML().append(nivel2+"<docModificado>"+detalleCompra.getDocModificado()+"</docModificado>");
						getTxtTextoXML().append(nivel2+"<fechaEmiModificado>"+detalleCompra.getFechaEmiModificado()+"</fechaEmiModificado>");
						getTxtTextoXML().append(nivel2+"<estabModificado>"+detalleCompra.getEstabModificado()+"</estabModificado>");
						getTxtTextoXML().append(nivel2+"<ptoEmiModificado>"+detalleCompra.getPtoEmiModificado()+"</ptoEmiModificado>");
						getTxtTextoXML().append(nivel2+"<secModificado>"+detalleCompra.getSecModificado()+"</secModificado>");
						getTxtTextoXML().append(nivel2+"<autModificado>"+detalleCompra.getAutModificado()+"</autModificado>");
						
						getTxtTextoXML().append(nivel2+"<montoTituloOneroso>0.00</montoTituloOneroso>");
						getTxtTextoXML().append(nivel2+"<montoTituloGratuito>0.00</montoTituloGratuito>");
					}
					compras.addDetalleCompras(detalleCompra);
					getTxtTextoXML().append(nivel1+"</detalleCompras>");
	
					Object[] fila = new Object[]{"1",numeroFila
							,mapaSustentoTributario.get(detalleCompra.getCodSustento())
							,detalleCompra.getIdProv()
							,mapaTipoComprobantes.get(String.valueOf(detalleCompra.getTipoComprobante()))
							,detalleCompra.getEstablecimiento()+"-"+detalleCompra.getPuntoEmision()
							,detalleCompra.getSecuencial(),detalleCompra.getAutorizacion()
							,detalleCompra.getBaseImponible(),detalleCompra.getBaseImpGrav()
							,detalleCompra.getMontoIva()
							,detalleCompra.getMontoIvaBienes(),detalleCompra.getMontoIvaServicios()};
					modelo.addRow(fila);
	
				} else if (objeto instanceof String){
					compras.addDetalleCompras(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","","0-0","","",0.0,0.0,0.0,0.0,0.0};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Notas de Credito - Compras", SpiritAlert.ERROR);
		}*/
		return numeroFila;
	}
	
	private void cargarNotaDebitoCompra(Compras compras) throws GenericBusinessException{
	}

	private int cargarVentas(int numeroFila,String codigoDocumento, Ventas ventas) throws GenericBusinessException{
		/*
		try{
			Collection<Object> detalles = getSriManagerSessionService().generarDetallesVenta(numeroFila, Parametros.getIdEmpresa(), mapaQuery, codigoDocumento, mapaCodigoIvaServicio, mapaCodigoIvaBien, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes, mapaCodigoTipoIdentificacionVenta, mapaImpuestos, mapaCodigoIva);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblVentas().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleVentas){
					DetalleVentas detalleVenta = (DetalleVentas) objeto;
					{
						getTxtTextoXML().append(nivel1+"<detalleVentas>");
						getTxtTextoXML().append(nivel2+"<tpIdCliente>"+detalleVenta.getTpIdCliente()+"</tpIdCliente>");
						getTxtTextoXML().append(nivel2+"<idCliente>"+detalleVenta.getIdCliente()+"</idCliente>");
						getTxtTextoXML().append(nivel2+"<tipoComprobante>"+detalleVenta.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel2+"<fechaRegistro>"+detalleVenta.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel2+"<numeroComprobantes>"+detalleVenta.getNumeroComprobantes()+"</numeroComprobantes>");
						getTxtTextoXML().append(nivel2+"<fechaEmision>"+detalleVenta.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel2+"<baseImponible>"+detalleVenta.getBaseImponible()+"</baseImponible>");
						getTxtTextoXML().append(nivel2+"<ivaPresuntivo>"+detalleVenta.getIvaPresuntivo()+"</ivaPresuntivo>");
						getTxtTextoXML().append(nivel2+"<baseImpGrav>"+detalleVenta.getBaseImpGrav()+"</baseImpGrav>");
						getTxtTextoXML().append(nivel2+"<porcentajeIva>"+detalleVenta.getPorcentajeIva()+"</porcentajeIva>");
						getTxtTextoXML().append(nivel2+"<montoIva>"+detalleVenta.getMontoIva()+"</montoIva>");
						getTxtTextoXML().append(nivel2+"<baseImpIce>"+detalleVenta.getBaseImpIce()+"</baseImpIce>");
						getTxtTextoXML().append(nivel2+"<porcentajeIce>"+detalleVenta.getPorcentajeIce()+"</porcentajeIce>");
						getTxtTextoXML().append(nivel2+"<montoIce>"+detalleVenta.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel2+"<montoIvaBienes>"+detalleVenta.getMontoIvaBienes()+"</montoIvaBienes>");
						getTxtTextoXML().append(nivel2+"<porRetBienes>"+detalleVenta.getPorRetBienes()+"</porRetBienes>");
						getTxtTextoXML().append(nivel2+"<valorRetBienes>"+detalleVenta.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel2+"<montoIvaServicios>"+detalleVenta.getMontoIvaServicios()+"</montoIvaServicios>");
						getTxtTextoXML().append(nivel2+"<porRetServicios>"+detalleVenta.getPorRetServicios()+"</porRetServicios>");
						getTxtTextoXML().append(nivel2+"<valorRetServicios>"+detalleVenta.getValorRetServicios()+"</valorRetServicios>");
						getTxtTextoXML().append(nivel2+"<retPresuntiva>"+detalleVenta.getRetPresuntiva()+"</retPresuntiva>");
	
						getTxtTextoXML().append(nivel2+"<air>");
						Air air = detalleVenta.getAir();
						DetalleAir[] detallesAir = air.getDetalleAir();
						for(int i=0;i<detallesAir.length;i++){
							DetalleAir detalleAir = detallesAir[i];
							getTxtTextoXML().append(nivel3+"<detalleAir>");
							getTxtTextoXML().append(nivel4+"<codRetAir>"+detalleAir.getCodRetAir()+"</codRetAir>");
							getTxtTextoXML().append(nivel4+"<baseImpAir>"+detalleAir.getBaseImpAir()+"</baseImpAir>");
							getTxtTextoXML().append(nivel4+"<porcentajeAir>"+detalleAir.getPorcentajeAir()+"</porcentajeAir>");
							getTxtTextoXML().append(nivel4+"<valRetAir>"+detalleAir.getValRetAir()+"</valRetAir>");
							getTxtTextoXML().append(nivel3+"</detalleAir>");
						}
						getTxtTextoXML().append(nivel2+"<air/>");
	
						ventas.addDetalleVentas(detalleVenta);
						getTxtTextoXML().append(nivel1+"</detalleVentas>");
	
						Object[] fila = new Object[]{"1",numeroFila,detalleVenta.getIdCliente()
								,mapaTipoComprobantes.get(String.valueOf(detalleVenta.getTipoComprobante()))
								,detalleVenta.getNumeroComprobantes()
								,detalleVenta.getBaseImponible(),detalleVenta.getBaseImpGrav(),detalleVenta.getMontoIva()
								,detalleVenta.getMontoIvaBienes(),detalleVenta.getMontoIvaServicios()};
						modelo.addRow(fila);
	
					}
				} else if (objeto instanceof String){
					ventas.addDetalleVentas(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","",0.0,0.0,0.0,0.0,0.0};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Ventas", SpiritAlert.ERROR);
		}
		*/
		return numeroFila;
	}
	
	private int cargarNotaCreditoVenta(int numeroFila,Ventas ventas) throws GenericBusinessException{
		/*
		try{
			Collection<Object> detalles = getSriManagerSessionService().generarNotasCreditoVenta(numeroFila, Parametros.getIdEmpresa(), mapaQuery, mapaCodigoIvaServicio
					, mapaCodigoIvaBien, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes, mapaCodigoTipoIdentificacionVenta, mapaImpuestos, mapaCodigoIva);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblVentas().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleVentas){
					DetalleVentas detalleVenta = (DetalleVentas) objeto;
					{
						getTxtTextoXML().append(nivel1+"<detalleVentas>");
						getTxtTextoXML().append(nivel2+"<tpIdCliente>"+detalleVenta.getTpIdCliente()+"</tpIdCliente>");
						getTxtTextoXML().append(nivel2+"<idCliente>"+detalleVenta.getIdCliente()+"</idCliente>");
						getTxtTextoXML().append(nivel2+"<tipoComprobante>"+detalleVenta.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel2+"<fechaRegistro>"+detalleVenta.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel2+"<numeroComprobantes>"+detalleVenta.getNumeroComprobantes()+"</numeroComprobantes>");
						getTxtTextoXML().append(nivel2+"<fechaEmision>"+detalleVenta.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel2+"<baseImponible>"+detalleVenta.getBaseImponible()+"</baseImponible>");
						getTxtTextoXML().append(nivel2+"<ivaPresuntivo>"+detalleVenta.getIvaPresuntivo()+"</ivaPresuntivo>");
						getTxtTextoXML().append(nivel2+"<baseImpGrav>"+detalleVenta.getBaseImpGrav()+"</baseImpGrav>");
						getTxtTextoXML().append(nivel2+"<porcentajeIva>"+detalleVenta.getPorcentajeIva()+"</porcentajeIva>");
						getTxtTextoXML().append(nivel2+"<montoIva>"+detalleVenta.getMontoIva()+"</montoIva>");
						getTxtTextoXML().append(nivel2+"<baseImpIce>"+detalleVenta.getBaseImpIce()+"</baseImpIce>");
						getTxtTextoXML().append(nivel2+"<porcentajeIce>"+detalleVenta.getPorcentajeIce()+"</porcentajeIce>");
						getTxtTextoXML().append(nivel2+"<montoIce>"+detalleVenta.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel2+"<montoIvaBienes>"+detalleVenta.getMontoIvaBienes()+"</montoIvaBienes>");
						getTxtTextoXML().append(nivel2+"<porRetBienes>"+detalleVenta.getPorRetBienes()+"</porRetBienes>");
						getTxtTextoXML().append(nivel2+"<valorRetBienes>"+detalleVenta.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel2+"<montoIvaServicios>"+detalleVenta.getMontoIvaServicios()+"</montoIvaServicios>");
						getTxtTextoXML().append(nivel2+"<porRetServicios>"+detalleVenta.getPorRetServicios()+"</porRetServicios>");
						getTxtTextoXML().append(nivel2+"<valorRetServicios>"+detalleVenta.getValorRetServicios()+"</valorRetServicios>");
						getTxtTextoXML().append(nivel2+"<retPresuntiva>"+detalleVenta.getRetPresuntiva()+"</retPresuntiva>");
	
						getTxtTextoXML().append(nivel2+"<air>");
						Air air = detalleVenta.getAir();
						DetalleAir[] detallesAir = air.getDetalleAir();
						for(int i=0;i<detallesAir.length;i++){
							DetalleAir detalleAir = detallesAir[i];
							getTxtTextoXML().append(nivel3+"<detalleAir>");
							getTxtTextoXML().append(nivel4+"<codRetAir>"+detalleAir.getCodRetAir()+"</codRetAir>");
							getTxtTextoXML().append(nivel4+"<baseImpAir>"+detalleAir.getBaseImpAir()+"</baseImpAir>");
							getTxtTextoXML().append(nivel4+"<porcentajeAir>"+detalleAir.getPorcentajeAir()+"</porcentajeAir>");
							getTxtTextoXML().append(nivel4+"<valRetAir>"+detalleAir.getValRetAir()+"</valRetAir>");
							getTxtTextoXML().append(nivel3+"</detalleAir>");
						}
						getTxtTextoXML().append(nivel2+"<air/>");
	
						ventas.addDetalleVentas(detalleVenta);
						getTxtTextoXML().append(nivel1+"</detalleVentas>");
	
						Object[] fila = new Object[]{"1",numeroFila,detalleVenta.getIdCliente()
								,mapaTipoComprobantes.get(String.valueOf(detalleVenta.getTipoComprobante()))
								,detalleVenta.getNumeroComprobantes()
								,detalleVenta.getBaseImponible(),detalleVenta.getBaseImpGrav(),detalleVenta.getMontoIva()
								,detalleVenta.getMontoIvaBienes(),detalleVenta.getMontoIvaServicios()};
						modelo.addRow(fila);
	
					}
				} else if (objeto instanceof String){
					ventas.addDetalleVentas(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","",0.0,0.0,0.0,0.0,0.0};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Notas de Credito - Ventas", SpiritAlert.ERROR);
		}
		*/
		return numeroFila;
	}
	
	private int cargarFacturasAnuladas(int numeroFila,String codigoDocumento, Anulados anulados) throws GenericBusinessException{
		try{
			Collection<Object> detalles = SessionServiceLocator.getSriManagerSessionService().generarDetallesAnulaciones(numeroFila, Parametros.getIdEmpresa(), mapaQuery
					, codigoDocumento, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblAnulaciones().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleAnulados){
					DetalleAnulados detalleAnulados = (DetalleAnulados) objeto;
					{
						getTxtTextoXML().append(nivel1+"<detalleAnulados>");
						getTxtTextoXML().append(nivel2+"<tipoComprobante>"+detalleAnulados.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel2+"<establecimiento>"+detalleAnulados.getEstablecimiento()+"</establecimiento>");
						getTxtTextoXML().append(nivel2+"<puntoEmision>"+detalleAnulados.getPuntoEmision()+"</puntoEmision>");
						getTxtTextoXML().append(nivel2+"<secuencialInicio>"+detalleAnulados.getSecuencialInicio()+"</secuencialInicio>");
						getTxtTextoXML().append(nivel2+"<secuencialFin>"+detalleAnulados.getSecuencialFin()+"</secuencialFin>");
						getTxtTextoXML().append(nivel2+"<autorizacion>"+detalleAnulados.getAutorizacion()+"</autorizacion>");
						//getTxtTextoXML().append(nivel2+"<fechaAnulacion>"+detalleAnulados.getFechaAnulacion()+"</fechaAnulacion>");
	
						anulados.addDetalleAnulados(detalleAnulados);
						getTxtTextoXML().append(nivel1+"</detalleAnulados>");
	
						Object[] fila = new Object[]{"1",numeroFila
								,mapaTipoComprobantes.get( String.valueOf( detalleAnulados.getTipoComprobante() ) )
								,detalleAnulados.getEstablecimiento()
								,detalleAnulados.getPuntoEmision()
								,formatoSecuencial.format( detalleAnulados.getSecuencialInicio() )
								,formatoSecuencial.format( detalleAnulados.getSecuencialFin() )
								,(detalleAnulados.getAutorizacion()!=null?detalleAnulados.getAutorizacion():"")
								,""
								//,(detalleAnulados.get.getFechaAnulacion()!=null? detalleAnulados.getFechaAnulacion():"") 
								};
						modelo.addRow(fila);
	
					}
				} else if (objeto instanceof String){
					anulados.addDetalleAnulados(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","000","000"
							,"0000000","0000000","0000000",""};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Ventas", SpiritAlert.ERROR);
		}
		return numeroFila;
	}
	
	private void cargarNotaDebitoVenta(Ventas ventas) throws GenericBusinessException{
		getTxtErrores().append("\n*-------------NOTA DE DEBITO DE VENTA-------------*\n");
	}

	@Override
	public void clean() {
		reoc = null;
		getTxtTextoXML().setText("");
		getTxtErrores().setText("");
		limpiarTabla(getTblCompras());
		limpiarTabla(getTblVentas());
		limpiarTabla(getTblAnulaciones());
		limpiarTabla(getTblExportaciones());
	}

	private void modificarTablaCompras(){
		//MODO DE SELECCION
		getTblCompras().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//TableCellRenderer cellRenderer = new TableCellRenderer();
		getTblCompras().getColumnModel().getColumn(COLUMNA_IMAGEN_COMPRA).setCellRenderer(cellRenderer);
		getTblCompras().getColumnModel().getColumn(COLUMNA_IMAGEN_COMPRA).setMinWidth(55);
		getTblCompras().getColumnModel().getColumn(COLUMNA_IMAGEN_COMPRA).setMaxWidth(55);

		getTblCompras().getColumnModel().getColumn(COLUMNA_NUMERO_COMPRA).setMinWidth(58);
		getTblCompras().getColumnModel().getColumn(COLUMNA_NUMERO_COMPRA).setMaxWidth(58);

		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_IDENTIFICACION_COMPRA).setMinWidth(100);
		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_IDENTIFICACION_COMPRA).setPreferredWidth(100);
		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_IDENTIFICACION_COMPRA).setMaxWidth(130);

		getTblCompras().getColumnModel().getColumn(COLUMNA_PROVEEDOR_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_PROVEEDOR_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_PROVEEDOR_COMPRA).setMaxWidth(130);

		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_COMPRA).setMinWidth(220);
		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_COMPRA).setPreferredWidth(250);
		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_COMPRA).setMaxWidth(290);

		getTblCompras().getColumnModel().getColumn(COLUMNA_SERIE_SECUENCIAL_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_SERIE_SECUENCIAL_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_SERIE_SECUENCIAL_COMPRA).setMaxWidth(130);

		getTblCompras().getColumnModel().getColumn(COLUMNA_AUTORIZARION_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_AUTORIZARION_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_AUTORIZARION_COMPRA).setMaxWidth(130);
		
		getTblCompras().getColumnModel().getColumn(COLUMNA_FECHA_EMISION_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_FECHA_EMISION_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_FECHA_EMISION_COMPRA).setMaxWidth(130);
		
	}

	private void modificarTablaVentas(){
		//MODO DE SELECCION
		getTblVentas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//TableCellRenderer cellRenderer = new TableCellRenderer();
		getTblVentas().getColumnModel().getColumn(COLUMNA_IMAGEN_VENTA).setCellRenderer(cellRenderer);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IMAGEN_VENTA).setMinWidth(55);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IMAGEN_VENTA).setMaxWidth(55);

		getTblVentas().getColumnModel().getColumn(COLUMNA_NUMERO_VENTA).setMinWidth(58);
		getTblVentas().getColumnModel().getColumn(COLUMNA_NUMERO_VENTA).setMaxWidth(58);

		getTblVentas().getColumnModel().getColumn(COLUMNA_CLIENTE_VENTA).setMinWidth(85);
		getTblVentas().getColumnModel().getColumn(COLUMNA_CLIENTE_VENTA).setPreferredWidth(85);
		getTblVentas().getColumnModel().getColumn(COLUMNA_CLIENTE_VENTA).setMaxWidth(100);

		getTblVentas().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_VENTA).setMinWidth(190);
		getTblVentas().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_VENTA).setPreferredWidth(350);
		getTblVentas().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_VENTA).setMaxWidth(400);

		getTblVentas().getColumnModel().getColumn(COLUMNA_NUMERO_EMISIONES_VENTA).setMinWidth(100);
		getTblVentas().getColumnModel().getColumn(COLUMNA_NUMERO_EMISIONES_VENTA).setPreferredWidth(100);
		getTblVentas().getColumnModel().getColumn(COLUMNA_NUMERO_EMISIONES_VENTA).setMaxWidth(100);

		getTblVentas().getColumnModel().getColumn(COLUMNA_BASE_CERO_VENTA).setMinWidth(115);
		getTblVentas().getColumnModel().getColumn(COLUMNA_BASE_CERO_VENTA).setPreferredWidth(115);
		getTblVentas().getColumnModel().getColumn(COLUMNA_BASE_CERO_VENTA).setMaxWidth(130);

		getTblVentas().getColumnModel().getColumn(COLUMNA_BASE_GRAVADA_VENTA).setMinWidth(115);
		getTblVentas().getColumnModel().getColumn(COLUMNA_BASE_GRAVADA_VENTA).setPreferredWidth(115);
		getTblVentas().getColumnModel().getColumn(COLUMNA_BASE_GRAVADA_VENTA).setMaxWidth(130);

		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_VENTA).setMinWidth(60);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_VENTA).setPreferredWidth(60);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_VENTA).setMaxWidth(80);
		
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_BIEN_VENTA).setMinWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_BIEN_VENTA).setPreferredWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_BIEN_VENTA).setMaxWidth(95);
		
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_SERVICIO_VENTA).setMinWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_SERVICIO_VENTA).setPreferredWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_SERVICIO_VENTA).setMaxWidth(95);
	}
	
	private void modificarTablaAnulaciones(){
		//MODO DE SELECCION
		getTblAnulaciones().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//TableCellRenderer cellRenderer = new TableCellRenderer();
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_IMAGEN_ANULACION).setCellRenderer(cellRenderer);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_IMAGEN_ANULACION).setMinWidth(55);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_IMAGEN_ANULACION).setMaxWidth(55);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_NUMERO_ANULACION).setMinWidth(58);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_NUMERO_ANULACION).setMaxWidth(58);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_ANULACION).setMinWidth(110);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_ANULACION).setPreferredWidth(110);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_ANULACION).setMaxWidth(130);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_ESTABLECIMIENTO_ANULACION).setMinWidth(80);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_ESTABLECIMIENTO_ANULACION).setPreferredWidth(80);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_ESTABLECIMIENTO_ANULACION).setMaxWidth(100);
		
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_PUNTO_EMISION_ANULACION).setMinWidth(90);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_PUNTO_EMISION_ANULACION).setPreferredWidth(90);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_PUNTO_EMISION_ANULACION).setMaxWidth(110);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_SECUENCIAL_INICIAL_ANULACION).setMinWidth(115);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_SECUENCIAL_INICIAL_ANULACION).setPreferredWidth(115);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_SECUENCIAL_INICIAL_ANULACION).setMaxWidth(125);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_SECUENCIAL_FINAL_ANULACION).setMinWidth(115);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_SECUENCIAL_FINAL_ANULACION).setPreferredWidth(115);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_SECUENCIAL_FINAL_ANULACION).setMaxWidth(125);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_AUTORIZACION_ANULACION).setMinWidth(120);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_AUTORIZACION_ANULACION).setPreferredWidth(120);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_AUTORIZACION_ANULACION).setMaxWidth(130);

	}

	@Override
	public void delete() {
	}

	@Override
	public void find() {
	}

	@Override
	public void report() {
	}

	@Override
	public void save() {
	}

	@Override
	public void update() {
	}

	@Override
	public boolean validateFields() {
		return false;
	}

	public void addDetail() {
	}

	public boolean isEmpty() {
		return false;
	}

	private void llenarInfoEmpresa(Reoc reoc) throws GenericBusinessException{
		try{
			
			EmpresaIf empresa = (EmpresaIf) SessionServiceLocator.getEmpresaSessionService().findEmpresaById(Parametros.getIdEmpresa()).iterator().next();
			reoc.setNumeroRuc(empresa.getRuc());
			
		} catch(GenericBusinessException e){
			throw new GenericBusinessException("Error a llenar Informacion de esta Empresa");
		}
		/*
		try{
			EmpresaIf empresa = (EmpresaIf)getEmpresaSessionService().findEmpresaById(Parametros.getIdEmpresa()).iterator().next();
			ivaGlobal.setNumeroRuc(empresa.getRuc());
			getTxtTextoXML().append("\n<numeroRuc>"+empresa.getRuc()+"</numeroRuc>");
			ivaGlobal.setRazonSocial(empresa.getNombre());
			getTxtTextoXML().append("\n<razonSocial>"+empresa.getNombre()+"</razonSocial>");
			
			OficinaIf oficina = (OficinaIf)getOficinaSessionService().findOficinaByEmpresaId(empresa.getId()).iterator().next();
			ivaGlobal.setDireccionMatriz(oficina.getDireccion());
			getTxtTextoXML().append(nivelRaiz+"<direccionMatriz>"+oficina.getDireccion()+"</direccionMatriz>");
			String telefono = oficina.getTelefono()!=null?oficina.getTelefono():"";
			ivaGlobal.setTelefono(telefono);
			getTxtTextoXML().append(nivelRaiz+"<telefono>"+telefono+"</telefono>");
			String fax = oficina.getFax()!=null?oficina.getFax():"";
			ivaGlobal.setFax(fax);
			getTxtTextoXML().append(nivelRaiz+"<fax>"+fax+"</fax>");

			ivaGlobal.setEmail("wluna@creacional.com");
			getTxtTextoXML().append(nivelRaiz+"<email>wluna@creacional.com</email>");
			ivaGlobal.setTpIdRepre("2");
			getTxtTextoXML().append(nivelRaiz+"<tpIdRepre>2</tpIdRepre>");
			ivaGlobal.setIdRepre("0925673345");
			getTxtTextoXML().append(nivelRaiz+"<idRepre>0925673345</idRepre>");
			ivaGlobal.setRucContador("0909435414001");
			getTxtTextoXML().append(nivelRaiz+"<rucContador>0909435414001</rucContador>");

			ivaGlobal.setAnio((Integer)getSpnAnio().getValue());
			getTxtTextoXML().append(nivelRaiz+"<anio>"+(Integer)getSpnAnio().getValue()+"</anio>");
			String mes = formatoDosEnteros.format( Utilitarios.getMesInt( (String)getCmbMes().getSelectedItem() ) + 1 );
			ivaGlobal.setMes(mes);
			getTxtTextoXML().append(nivelRaiz+"<mes>"+mes+"</mes>");

		} catch(GenericBusinessException e){
			throw new GenericBusinessException("Error a llenar Informacion de esta Empresa");
		}
		*/
	}

	public void showFindMode() {
		//refresh();
		clean();
	}

	public void showSaveMode() {
		//setSaveMode();
		showFindMode();
	}

	public void showUpdateMode() {
		showFindMode();
	}

	public void updateDetail() {
	}
	
	public void deleteDetail() {
		
	}
	
}
