package com.spirit.sri.gui.model;

import static com.spirit.sri.dimm.DimmConstantes.nivel1;
import static com.spirit.sri.dimm.DimmConstantes.nivel2;
import static com.spirit.sri.dimm.DimmConstantes.nivel3;
import static com.spirit.sri.dimm.DimmConstantes.nivel4;
import static com.spirit.sri.dimm.DimmConstantes.nivel5;
import static com.spirit.sri.dimm.DimmConstantes.nivelRaiz;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.sri.at.Anulados;
import com.spirit.sri.at.DetalleAnulados;
import com.spirit.sri.at.DetalleVentas;
import com.spirit.sri.at.Exportaciones;
import com.spirit.sri.at.Fideicomisos;
import com.spirit.sri.at.Iva;
import com.spirit.sri.at.Recap;
import com.spirit.sri.at.RendFinancieros;
import com.spirit.sri.at.Ventas;
import com.spirit.sri.dimm.TipoDeclaracion;
import com.spirit.sri.dimm.UtilesDimm;
import com.spirit.sri.dimm.anexoTransaccional.GeneracionAnexoTransaccional;
import com.spirit.sri.dimm.reoc.GeneracionReoc;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaBienIf;
import com.spirit.sri.entity.SriIvaServicioIf;
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

public class DimmModel extends JPDimm implements IfTablas {

	private static final long serialVersionUID = 3473304962664492109L;

	private boolean errorGeneral = false;
	private DecimalFormat formatoSecuencial = new DecimalFormat("0000000");
	private Map<String, Date> mapaQuery;
	TableCellRenderer cellRenderer;
	private int numeroFila=1;
	private String nombreArchivo = "";
	
	private int mesConsulta = 0;
	private int anioConsulta = 0;
	
	private GeneracionReoc generacionReoc = new GeneracionReoc();
	private GeneracionAnexoTransaccional generacionAnexoTransaccional = new GeneracionAnexoTransaccional();
	
	private HashMap<String, Double> mapaImpuestos = null;
	
	private HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion = null;
	private HashMap<String, SriTipoTransaccionIf> mapaCodigoTransaccion = null;
	private HashMap<String, String> mapaCodigoTipoIdentificacionCompraXCodigoSpirit = null;
	private HashMap<String, String> mapaCodigoTipoIdentificacionCompraXCodigoSri = null;
	
	private HashMap<String, String> mapaTipoComprobantes = null;
	private HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes = null;
	private HashMap<Long, SriAirIf> mapaAir= null;

	private AirPopupActionListener ventasActionListener = null, comprasActionListener = null;
	private RetencionActionListener retencionActionListener = null;
	private JMenuItem itemAirCompra = null,itemAirVenta = null, itemRetencionCompra = null;

	HashMap<Integer, Integer> mapaCodigoIva = null;
	HashMap<String, SriIvaBienIf> mapaCodigoIvaBien = null;
	HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio = null;
	HashMap<String, String> mapaCodigoTipoIdentificacionVenta = null;
	
	public DimmModel() {
		clean();
		initKeyListeners();
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
		mapaCodigoIva = IniciarMapas.cargarCodigosIva();
		mapaCodigoIvaBien = IniciarMapas.cargarCodigosIvaBien();
		mapaCodigoIvaServicio = IniciarMapas.cargarCodigosIvaServicio();
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
		mapaCodigoTipoIdentificacionVenta = (HashMap<String, String>) objectosTiposIdentificaion[0]; 

		iniciarComponentes();
		showFindMode();
	}
	
	private void initKeyListeners(){
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
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
		
		getBtnGenerarReoc().addActionListener(generacionReoc.getGenerarReocActionListener());
		
		getBtnConsultar().addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					if ( estaAlgunaCasillaSeleccionada() ){
						if ( estaSoloCasillaCompraSeleccionada() )
							consultarReoc();
						else 
							consultarAnexoTransaccional();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error en Consulta !!", SpiritAlert.ERROR);
				}
			}
		});
		
		getBtnGenerarAnexoTransaccional().addActionListener(generacionAnexoTransaccional.getGenerarAnexoTransaccionalActionListener());
	}
	
	private boolean estaAlgunaCasillaSeleccionada(){
		return getChbCompras().isSelected() || getChbVentas().isSelected() || 
			getChbAnulaciones().isSelected() || getChbImportaciones().isSelected();
	}
	
	private boolean estaSoloCasillaCompraSeleccionada(){
		return getChbCompras().isSelected() && !getChbVentas().isSelected() && 
			!getChbAnulaciones().isSelected() && !getChbImportaciones().isSelected();
	}
	
	private void consultarReoc() throws InterruptedException {
		clean();
		final DecimalFormat formatoDosEnteros = new DecimalFormat("00");
		final Cursor cursor = getCursor();
		setCursor(cursor.getPredefinedCursor(cursor.WAIT_CURSOR));
		Runnable consultaTotal = new Runnable(){
			public void run() {
				try{
					buildQuery(TipoDeclaracion.REOC);
					
					generacionReoc.getGenerarReocActionListener().setReocGlobal(null);
					getTxtTextoXML().append(nivelRaiz+"<reoc>");
					
					//**** COMPRAS ****
					Reoc reoc = new Reoc();
					generacionReoc.getGenerarReocActionListener().setReocGlobal( reoc );
					llenarInfoEmpresaReoc( generacionReoc.getGenerarReocActionListener().getReocGlobal() );
					
					reoc.setAnio(anioConsulta);
					getTxtTextoXML().append(nivel1+"<anio>"+reoc.getAnio()+"</anio>");
					
					reoc.setMes( formatoDosEnteros.format(mesConsulta+1) );
					getTxtTextoXML().append(nivel1+"<mes>"+reoc.getMes()+"</mes>");
					
					numeroFila = 1;
					Compras compras = new Compras();
					if ( getChbCompras().isSelected() ){
						getTxtTextoXML().append(nivel1+"<compras>");
						getTxtErrores().append("*-------------COMPRAS-------------*\n");
						numeroFila = cargarComprasReoc(numeroFila,compras);
						getTxtErrores().append("\n*-------------COMPRAS - NOTAS DE CREDITO-------------*\n");
						numeroFila = cargarNotaCreditoCompraReoc(numeroFila,compras);
						cargarNotaDebitoCompraReoc(compras);
						
						reoc.setCompras(compras);
						
						getTxtTextoXML().append(nivel1+"</compras>");
					}
					//ivaGlobal.setCompras(compras);
					
					generacionReoc.getGenerarReocActionListener().setNombreArchivo(nombreArchivo);
		
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
	
	
	private void consultarAnexoTransaccional() throws InterruptedException {
		clean();
		final Cursor cursor = getCursor();
		setCursor(cursor.getPredefinedCursor(cursor.WAIT_CURSOR));
		Runnable consultaTotal = new Runnable(){
			public void run() {
				try{
					
					buildQuery(TipoDeclaracion.ANEXO_TRANSACCIONAL);
					
					generacionAnexoTransaccional.getGenerarAnexoTransaccionalActionListener().setIvaGlobal(null);
					
					Iva ivaGlobal = new Iva();
					generacionAnexoTransaccional.getGenerarAnexoTransaccionalActionListener().setIvaGlobal(ivaGlobal);
					
					getTxtTextoXML().append(nivelRaiz+"<iva>");
					llenarInfoEmpresaAnexoTransaccional(ivaGlobal);
		
					//**** COMPRAS ****
					numeroFila = 1;
					if ( getChbCompras().isSelected() ){
						com.spirit.sri.at.Compras compras = new com.spirit.sri.at.Compras();
						getTxtTextoXML().append(nivel1+"<compras>");
						if ( getChbCompras().isSelected() ){
							getTxtErrores().append("*-------------COMPRAS-------------*\n");
							numeroFila = cargarComprasAnexoTransaccional(numeroFila,compras);
							getTxtErrores().append("\n*-------------COMPRAS - NOTAS DE CREDITO-------------*\n");
							numeroFila = cargarNotaCreditoCompraAnexoTransaccional(numeroFila,compras);
							cargarNotaDebitoCompraAnextoTransaccional(compras);
						}
						ivaGlobal.setCompras(compras);
						getTxtTextoXML().append(nivel1+"</compras>");
					}
					
					//**** VENTAS ****
					numeroFila = 1;
					if ( getChbVentas().isSelected() ){
						Ventas ventas = new Ventas();
						getTxtTextoXML().append(nivel1+"<ventas>");
						if ( getChbVentas().isSelected() ){
							
							/*
							getTxtErrores().append("\n*-------------VENTAS FACTURA-------------*\n");
							numeroFila = cargarVentasAnexoTransaccional(numeroFila,"FAC",ventas);
							getTxtErrores().append("\n*-------------VENTAS FACTURAS COMISION-------------*\n");
							numeroFila = cargarVentasAnexoTransaccional(numeroFila,"FCO",ventas);
							getTxtErrores().append("\n*-------------VENTAS FACTURAS DE REEMBOLSO-------------*\n");
							numeroFila = cargarVentasAnexoTransaccional(numeroFila,"FAR",ventas);
							getTxtErrores().append("\n*-------------VENTAS FACTURAS EXTERIOR-------------*\n");
							numeroFila = cargarVentasAnexoTransaccional(numeroFila,"FAE",ventas);
							*/
							Set<String> codigosDocumentos = new HashSet<String>();
							codigosDocumentos.add("FAC");
							codigosDocumentos.add("FCO");
							codigosDocumentos.add("FAR");
							codigosDocumentos.add("FAE");
							numeroFila = cargarVentasAnexoTransaccional(numeroFila,codigosDocumentos,ventas);
							
							getTxtErrores().append("\n*-------------VENTAS - NOTAS DE CREDITO-------------*\n");
							numeroFila = cargarNotaCreditoVentaAnexoTransaccional(numeroFila,ventas);
							
							cargarNotaDebitoVenta(ventas);
						}
						ivaGlobal.setVentas(ventas);
						getTxtTextoXML().append(nivel1+"</ventas>");
					}
					
					//**** ANULADOS *****
					numeroFila=1;
					if ( getChbAnulaciones().isSelected() ){
						Anulados anulados = new Anulados();
						getTxtTextoXML().append(nivel1+"<anulados>");
						if ( getChbAnulaciones().isSelected() ){
							getTxtErrores().append("\n*-------------FACTURAS ANULADAS-------------*\n");
							//numeroFila = cargarFacturasAnuladasAnexoTransaccional(numeroFila,"FAC",anulados);
							numeroFila = cargarCarterasAnuladasAnexoTransaccional(numeroFila, "FAC", anulados);
							getTxtErrores().append("\n*-------------FACTURAS REEMBOLSO ANULADAS-------------*\n");
							//numeroFila = cargarFacturasAnuladasAnexoTransaccional(numeroFila,"FAR",anulados);
							numeroFila = cargarCarterasAnuladasAnexoTransaccional(numeroFila, "FAR", anulados);
							getTxtErrores().append("\n*-------------NOTAS DE CREDITO ANULADAS-------------*\n");
							numeroFila = cargarCarterasAnuladasAnexoTransaccional(numeroFila, "NCC", anulados);
							getTxtErrores().append("\n*-------------RETENCIONES ANULADAS-------------*\n");
							numeroFila = cargarCarterasAnuladasAnexoTransaccional(numeroFila, "CRE", anulados);
						}
						ivaGlobal.setAnulados(anulados);
						getTxtTextoXML().append(nivel1+"</anulados>");
					}
					
					//**** IMPORTACIONES ****
					numeroFila=1;
					//Importaciones importaciones = new Importaciones();
					//getTxtTextoXML().append("\n<importaciones>");
					//if ( getChbImportaciones().isSelected() ){
						
					//}
					//ivaGlobal.setImportaciones(importaciones);
					//getTxtTextoXML().append("\n</importaciones>");
		
					//**** EXPORTACIONES ****
					if ( getChbImportaciones().isSelected() ){
						ivaGlobal.setExportaciones(new Exportaciones());
						getTxtTextoXML().append(nivel1+"<exportaciones></exportaciones>");
						ivaGlobal.setRecap(new Recap());
						getTxtTextoXML().append(nivel1+"<recap></recap>");
						ivaGlobal.setFideicomisos(new Fideicomisos());
						getTxtTextoXML().append(nivel1+"<fideicomisos></fideicomisos>");

						ivaGlobal.setRendFinancieros(new RendFinancieros());
						getTxtTextoXML().append(nivel1+"<rendFinacieros></rendFinacieros>");
					}

					getTxtTextoXML().append(nivelRaiz+"</iva>");
					
					generacionAnexoTransaccional.getGenerarAnexoTransaccionalActionListener().setNombreArchivo(nombreArchivo);
		
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
			}
		};
		Thread t = new Thread(consultaTotal);
		t.start();
	}
	
	PopupMenuListener popupComprasListener = new PopupMenuListener(){
		public void popupMenuCanceled(PopupMenuEvent e) {}
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			itemAirCompra.setEnabled(false);
			itemRetencionCompra.setEnabled(false);
			int filaSeleccionada = getTblCompras().getSelectedRow();
			if (filaSeleccionada!=-1){
				int filaSelTransf = getTblCompras().convertRowIndexToModel(filaSeleccionada);
				DetalleCompras detalleCompra = generacionReoc.getGenerarReocActionListener().getReocGlobal().getCompras().getDetalleCompras(filaSelTransf);
				if (detalleCompra!=null){
					try {
						HashMap<Double, String> mapaValorRetencion = new HashMap<Double, String>();
						ClienteIf cliente = (ClienteIf)SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(detalleCompra.getIdProv()).iterator().next();
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
		
		/*if ( generacionReoc.getGenerarReocActionListener() != null ) {
			try {
				generacionReoc.getGenerarReocActionListener().refresacarParametroDirectrio();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			}
		}
			
		if ( generacionReoc.getGenerarReocActionListener() != null ) {
			try {
				generacionAnexoTransaccional.getGenerarAnexoTransaccionalActionListener().refresacarParametroDirectrio();
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			}
		}*/
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}

	private void buildQuery(TipoDeclaracion tipoDeclaracion) throws GenericBusinessException, ParseException, GeneralSecurityException{
		mapaQuery = null;
		mapaQuery =  new HashMap<String, Date>();
		
		anioConsulta = ((Integer)getSpnAnio().getValue()).intValue();
		String mes = (String)getCmbMes().getSelectedItem();
		mesConsulta = Utilitarios.getMesInt(mes);
		
		DecimalFormat formatoDosEnteros = new DecimalFormat("00");
		int anioRestado = anioConsulta - 1900;
		Date fechaInicio = new Date(anioRestado,mesConsulta,1);
		Date fechaFin = Utilitarios.fechaHoy(mesConsulta, anioRestado);

		mapaQuery.put("fechaInicio", fechaInicio);
		mapaQuery.put("fechaFin", fechaFin);

		nombreArchivo = tipoDeclaracion.getInicialesParaNombreArchivo() + formatoDosEnteros.format( mesConsulta+1 ) + anioConsulta ;
	}

	private int cargarComprasReoc(int numeroFila,Compras compras) throws GenericBusinessException{
		try{ 
			
			Collection<Object> detalles = detalles = SessionServiceLocator.getSriManagerSessionService().generarDetallesCompraReoc(numeroFila, mapaQuery, Parametros.getIdEmpresa()
					,mapaAir,mapaCodigoIdentificacion, mapaCodigoTipoIdentificacionCompraXCodigoSpirit, mapaImpuestos,Parametros.getIdEmpresa());
			
			DefaultTableModel modelo = (DefaultTableModel)getTblCompras().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleCompras){
					double base0 = 0.0;
					double baseGrav = 0.0;
					double baseNoGrav = 0.0;
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
						
						getTxtTextoXML().append(nivel3+"<air>");
						Air air = detalleCompra.getAir();
						DetalleAir[] detallesAir = air.getDetalleAir();
						for(int i=0;i<detallesAir.length;i++){
							DetalleAir detalleAir = detallesAir[i];
							getTxtTextoXML().append(nivel4+"<detalleAir>");
							getTxtTextoXML().append(nivel5+"<codRetAir>"+detalleAir.getCodRetAir()+"</codRetAir>");
							getTxtTextoXML().append(nivel5+"<porcentaje>"+detalleAir.getPorcentaje()+"</porcentaje>");
							base0 += detalleAir.getBase0().doubleValue();
							getTxtTextoXML().append(nivel5+"<base0>"+detalleAir.getBase0()+"</base0>");
							baseGrav += detalleAir.getBaseGrav().doubleValue();
							getTxtTextoXML().append(nivel5+"<baseGrav>"+detalleAir.getBaseGrav()+"</baseGrav>");
							baseNoGrav += detalleAir.getBaseNoGrav().doubleValue();
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
	
					}
					compras.addDetalleCompras(detalleCompra);
					getTxtTextoXML().append(nivel2+"</detalleCompras>");
	
					Object[] fila = new Object[]{"1",numeroFila
							,mapaCodigoTipoIdentificacionCompraXCodigoSri.get(detalleCompra.getTpIdProv())
							,detalleCompra.getIdProv()
							,mapaTipoComprobantes.get( String.valueOf(detalleCompra.getTipoComp() ) )
							,detalleCompra.getEstab()+"-"+detalleCompra.getPtoEmi()+"-"+detalleCompra.getSec()
							,detalleCompra.getAut()
							,detalleCompra.getFechaEmiCom(),
							base0,baseGrav,baseNoGrav,0.00};
					modelo.addRow(fila);
	
				} else if (objeto instanceof String){
					compras.addDetalleCompras(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","","0-0-0","","",0.00,0.00,0.00,0.00};
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
	
	private int cargarComprasAnexoTransaccional(int numeroFila,com.spirit.sri.at.Compras compras) throws GenericBusinessException{
		try{ 
			
			//johy
			Collection<Object> detalles = detalles = SessionServiceLocator.getSriManagerSessionService().generarDetallesCompraAnexoTransaccional(numeroFila, mapaQuery, Parametros.getIdEmpresa()
					,mapaAir,mapaCodigoIdentificacion, mapaCodigoTipoIdentificacionCompraXCodigoSpirit, mapaImpuestos,Parametros.getIdEmpresa());
			
			DefaultTableModel modelo = (DefaultTableModel)getTblCompras().getModel();
			for (Object objeto : detalles){
				if (objeto instanceof com.spirit.sri.at.DetalleCompras){
					com.spirit.sri.at.DetalleCompras detalleCompra = (com.spirit.sri.at.DetalleCompras) objeto;
					{
						getTxtTextoXML().append(nivel2+"<detalleCompras>");
						getTxtTextoXML().append(nivel3+"<codSustento>"+detalleCompra.getCodSustento()+"</codSustento>");
						getTxtTextoXML().append(nivel3+"<tpIdProv>"+detalleCompra.getTpIdProv()+"</tpIdProv>");
						getTxtTextoXML().append(nivel3+"<idProv>"+detalleCompra.getIdProv()+"</idProv>");
						getTxtTextoXML().append(nivel3+"<tipoComprobante>"+detalleCompra.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel3+"<fechaRegistro>"+detalleCompra.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel3+"<establecimiento>"+detalleCompra.getEstablecimiento()+"</establecimiento>");
						getTxtTextoXML().append(nivel3+"<puntoEmision>"+detalleCompra.getPuntoEmision()+"</puntoEmision>");
						getTxtTextoXML().append(nivel3+"<secuencial>"+detalleCompra.getSecuencial()+"</secuencial>");
						getTxtTextoXML().append(nivel3+"<fechaEmision>"+detalleCompra.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel3+"<autorizacion>"+detalleCompra.getAutorizacion()+"</autorizacion>");
						
						getTxtTextoXML().append(nivel3+"<baseNoGraIva>"+detalleCompra.getBaseNoGraIva()+"</baseNoGraIva>");
						getTxtTextoXML().append(nivel3+"<baseImponible>"+detalleCompra.getBaseImponible()+"</baseImponible>");
						getTxtTextoXML().append(nivel3+"<baseImpGrav>"+detalleCompra.getBaseImpGrav()+"</baseImpGrav>");
						getTxtTextoXML().append(nivel3+"<montoIce>"+detalleCompra.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel3+"<montoIva>"+detalleCompra.getMontoIva()+"</montoIva>");
						getTxtTextoXML().append(nivel3+"<valorRetBienes>"+detalleCompra.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel3+"<valorRetServicios>"+detalleCompra.getValorRetServicios()+"</valorRetServicios>");
						getTxtTextoXML().append(nivel3+"<valRetServ100>"+detalleCompra.getValRetServ100()+"</valRetServ100>");
						
						getTxtTextoXML().append(nivel3+"<air>");
						com.spirit.sri.at.Air air = detalleCompra.getAir();
						com.spirit.sri.at.DetalleAir[] detallesAir = air.getDetalleAir();
						for(int i=0;i<detallesAir.length;i++){
							com.spirit.sri.at.DetalleAir detalleAir = detallesAir[i];
							getTxtTextoXML().append(nivel4+"<detalleAir>");
							getTxtTextoXML().append(nivel5+"<codRetAir>"+detalleAir.getCodRetAir()+"</codRetAir>");
							getTxtTextoXML().append(nivel5+"<baseImpAir>"+detalleAir.getBaseImpAir()+"</baseImpAir>");							
							getTxtTextoXML().append(nivel5+"<porcentajeAir>"+detalleAir.getPorcentajeAir()+"</porcentajeAir>");
							getTxtTextoXML().append(nivel5+"<valRetAir>"+detalleAir.getValRetAir()+"</valRetAir>");
							getTxtTextoXML().append(nivel4+"</detalleAir>");
								
						}
						getTxtTextoXML().append(nivel3+"<air/>");
	
						if (detalleCompra.getEstabRetencion1()!=null){
							getTxtTextoXML().append(nivel3+"<estabRetencion1>"+detalleCompra.getEstabRetencion1()+"</estabRetencion1>");
							getTxtTextoXML().append(nivel3+"<ptoEmiRetencion1>"+detalleCompra.getPtoEmiRetencion1()+"</ptoEmiRetencion1>");
							getTxtTextoXML().append(nivel3+"<secRetencion1>"+String.valueOf(detalleCompra.getSecRetencion1())+"</secRetencion1>");
							getTxtTextoXML().append(nivel3+"<autRetencion1>"+detalleCompra.getAutRetencion1()+"</autRetencion1>");
							getTxtTextoXML().append(nivel3+"<fechaEmiRet1>"+detalleCompra.getFechaEmiRet1()+"</fechaEmiRet1>");
						}
						if (detalleCompra.getEstabRetencion2()!=null){
							getTxtTextoXML().append(nivel3+"<estabRetencion2>"+detalleCompra.getEstabRetencion2()+"</estabRetencion2>");
							getTxtTextoXML().append(nivel3+"<ptoEmiRetencion2>"+detalleCompra.getPtoEmiRetencion2()+"</ptoEmiRetencion2>");
							getTxtTextoXML().append(nivel3+"<secRetencion2>"+String.valueOf(detalleCompra.getSecRetencion2())+"</secRetencion2>");
							getTxtTextoXML().append(nivel3+"<autRetencion2>"+detalleCompra.getAutRetencion2()+"</autRetencion2>");
							getTxtTextoXML().append(nivel3+"<fechaEmiRet2>"+detalleCompra.getFechaEmiRet2()+"</fechaEmiRet2>");
						}
	
						getTxtTextoXML().append(nivel3+"<docModificado>"+detalleCompra.getDocModificado()+"</docModificado>");
						getTxtTextoXML().append(nivel3+"<fechaEmiModificado>00/00/0000</fechaEmiModificado>");
						getTxtTextoXML().append(nivel3+"<estabModificado>"+detalleCompra.getEstabModificado()+"</estabModificado>");
						getTxtTextoXML().append(nivel3+"<ptoEmiModificado>"+detalleCompra.getPtoEmiModificado()+"</ptoEmiModificado>");
						getTxtTextoXML().append(nivel3+"<secModificado>"+detalleCompra.getSecModificado()+"</secModificado>");
						getTxtTextoXML().append(nivel3+"<autModificado>"+detalleCompra.getAutModificado()+"</autModificado>");
						getTxtTextoXML().append(nivel3+"<montoTituloOneroso>0.00</montoTituloOneroso>");
						getTxtTextoXML().append(nivel3+"<montoTituloGratuito>0.00</montoTituloGratuito>");
						
					}
					compras.addDetalleCompras(detalleCompra);
					getTxtTextoXML().append(nivel2+"</detalleCompras>");
	
					Object[] fila = new Object[]{"1",numeroFila
							,mapaCodigoTipoIdentificacionCompraXCodigoSri.get(detalleCompra.getTpIdProv())
							,detalleCompra.getIdProv()
							,mapaTipoComprobantes.get( String.valueOf(detalleCompra.getTipoComprobante() ) )
							,detalleCompra.getEstablecimiento()+"-"+detalleCompra.getPuntoEmision()+"-"+detalleCompra.getSecuencial()
							,detalleCompra.getAutorizacion()
							,detalleCompra.getFechaEmision()
							,detalleCompra.getBaseImponible()
							,detalleCompra.getBaseImpGrav()
							,detalleCompra.getBaseNoGraIva()
							,detalleCompra.getMontoIva() };
					modelo.addRow(fila);
	
				} else if (objeto instanceof String){
					compras.addDetalleCompras(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","","0-0-0","","",0.00,0.00,0.00,0.00};
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

	private int cargarNotaCreditoCompraReoc(int numeroFila,Compras compras) throws GenericBusinessException{
		try{ 

			Collection<Object> detalles = detalles = SessionServiceLocator.getSriManagerSessionService()
				.generarNotasCreditoCompraReoc(numeroFila,Parametros.getIdEmpresa(),mapaAir, mapaQuery, 
					mapaCodigoIdentificacion, mapaCodigoTipoIdentificacionCompraXCodigoSpirit);

			DefaultTableModel modelo = (DefaultTableModel)getTblCompras().getModel();
			for (Iterator itDetalles = detalles.iterator();itDetalles.hasNext();){
				Object objeto = itDetalles.next();
				if (objeto instanceof DetalleCompras){
					double base0 = 0.0;
					double baseGrav = 0.0;
					double baseNoGrav = 0.0;
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

						getTxtTextoXML().append(nivel3+"<air>");
						Air air = detalleCompra.getAir();
						DetalleAir[] detallesAir = air.getDetalleAir();
						for(int i=0;i<detallesAir.length;i++){
							DetalleAir detalleAir = detallesAir[i];
							getTxtTextoXML().append(nivel4+"<detalleAir>");
							getTxtTextoXML().append(nivel5+"<codRetAir>"+detalleAir.getCodRetAir()+"</codRetAir>");
							getTxtTextoXML().append(nivel5+"<porcentaje>"+detalleAir.getPorcentaje()+"</porcentaje>");
							base0 += detalleAir.getBase0().doubleValue();
							getTxtTextoXML().append(nivel5+"<base0>"+detalleAir.getBase0()+"</base0>");
							baseGrav += detalleAir.getBaseGrav().doubleValue();
							getTxtTextoXML().append(nivel5+"<baseGrav>"+detalleAir.getBaseGrav()+"</baseGrav>");
							baseNoGrav += detalleAir.getBaseNoGrav().doubleValue();
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

					}
					compras.addDetalleCompras(detalleCompra);
					getTxtTextoXML().append(nivel2+"</detalleCompras>");

					Object[] fila = new Object[]{"1",numeroFila
							,mapaCodigoTipoIdentificacionCompraXCodigoSri.get(detalleCompra.getTpIdProv())
							,detalleCompra.getIdProv()
							,mapaTipoComprobantes.get( String.valueOf(detalleCompra.getTipoComp() ) )
							,detalleCompra.getEstab()+"-"+detalleCompra.getPtoEmi()+"-"+detalleCompra.getSec()
							,detalleCompra.getAut()
							,detalleCompra.getFechaEmiCom(),
							base0,baseGrav,baseNoGrav,0.00};
					modelo.addRow(fila);

				} else if (objeto instanceof String){
					compras.addDetalleCompras(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","","0-0-0","","",0.00,0.00,0.00,0.00};
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
	
	private int cargarNotaCreditoCompraAnexoTransaccional(int numeroFila,com.spirit.sri.at.Compras compras) throws GenericBusinessException{
		
		
		try{
			Collection<Object> detalles = SessionServiceLocator.getSriManagerSessionService()
				.generarNotasCreditoCompraAnexoTransaccional(numeroFila, Parametros.getIdEmpresa(),
					mapaQuery,mapaImpuestos, mapaCodigoIva);
				
			DefaultTableModel modelo = (DefaultTableModel)getTblCompras().getModel();
			for ( Object objeto : detalles ){
				if (objeto instanceof com.spirit.sri.at.DetalleCompras){
					com.spirit.sri.at.DetalleCompras detalleCompra = (com.spirit.sri.at.DetalleCompras) objeto;
					{
						getTxtTextoXML().append(nivel2+"<detalleCompras>");
						getTxtTextoXML().append(nivel3+"<tpIdProv>"+detalleCompra.getTpIdProv()+"</tpIdProv>");
						getTxtTextoXML().append(nivel3+"<idProv>"+detalleCompra.getIdProv()+"</idProv>");
						getTxtTextoXML().append(nivel3+"<tipoComprobante>"+detalleCompra.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel3+"<fechaRegistro>"+detalleCompra.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel3+"<establecimiento>"+detalleCompra.getEstablecimiento()+"</establecimiento>");
						getTxtTextoXML().append(nivel3+"<puntoEmision>"+detalleCompra.getPuntoEmision()+"</puntoEmision>");
						getTxtTextoXML().append(nivel3+"<secuencial>"+detalleCompra.getSecuencial()+"</secuencial>");
						getTxtTextoXML().append(nivel3+"<fechaEmision>"+detalleCompra.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel3+"<autorizacion>"+detalleCompra.getAutorizacion()+"</autorizacion>");
						
						getTxtTextoXML().append(nivel3+"<baseNoGraIva>"+detalleCompra.getBaseNoGraIva()+"</baseNoGraIva>");
						getTxtTextoXML().append(nivel3+"<baseImponible>"+detalleCompra.getBaseImponible()+"</baseImponible>");
						getTxtTextoXML().append(nivel3+"<baseImpGrav>"+detalleCompra.getBaseImpGrav()+"</baseImpGrav>");
						getTxtTextoXML().append(nivel3+"<montoIce>"+detalleCompra.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel3+"<montoIva>"+detalleCompra.getMontoIva()+"</montoIva>");
						getTxtTextoXML().append(nivel3+"<valorRetBienes>"+detalleCompra.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel3+"<valorRetServicios>"+detalleCompra.getValorRetServicios()+"</valorRetServicios>");
						getTxtTextoXML().append(nivel3+"<valRetServ100>"+detalleCompra.getValRetServ100()+"</valRetServ100>");
						
						getTxtTextoXML().append(nivel3+"<air>");
						getTxtTextoXML().append(nivel3+"<air/>");
	
						getTxtTextoXML().append(nivel3+"<docModificado>"+detalleCompra.getDocModificado()+"</docModificado>");
						getTxtTextoXML().append(nivel3+"<estabModificado>"+detalleCompra.getEstabModificado()+"</estabModificado>");
						getTxtTextoXML().append(nivel3+"<ptoEmiModificado>"+detalleCompra.getPtoEmiModificado()+"</ptoEmiModificado>");
						getTxtTextoXML().append(nivel3+"<secModificado>"+detalleCompra.getSecModificado()+"</secModificado>");
						getTxtTextoXML().append(nivel3+"<autModificado>"+detalleCompra.getAutModificado()+"</autModificado>");
						getTxtTextoXML().append(nivel3+"<montoTituloOneroso>0.00</montoTituloOneroso>");
						getTxtTextoXML().append(nivel3+"<montoTituloGratuito>0.00</montoTituloGratuito>");
					}
					compras.addDetalleCompras(detalleCompra);
					getTxtTextoXML().append(nivel2+"</detalleCompras>");
					Object[] fila = new Object[]{"1",numeroFila
							,mapaCodigoTipoIdentificacionCompraXCodigoSri.get(detalleCompra.getTpIdProv())
							,detalleCompra.getIdProv()
							,mapaTipoComprobantes.get( String.valueOf(detalleCompra.getTipoComprobante() ) )
							,detalleCompra.getEstablecimiento()+"-"+detalleCompra.getPuntoEmision()+"-"+detalleCompra.getSecuencial()
							,detalleCompra.getAutorizacion()
							,detalleCompra.getFechaEmision()
							,detalleCompra.getBaseImponible()
							,detalleCompra.getBaseImpGrav()
							,detalleCompra.getBaseNoGraIva()
							,detalleCompra.getMontoIva() };
					modelo.addRow(fila);
	
				} else if (objeto instanceof String){
					compras.addDetalleCompras(null);
					getTxtErrores().append((String)objeto);
					Object[] fila = new Object[]{"0",numeroFila,"","","","0-0-0","","",0.00,0.00,0.00,0.00};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Notas de Credito - Compras", SpiritAlert.ERROR);
		}
		return numeroFila;
	}
	
	private void cargarNotaDebitoCompraReoc(Compras compras) throws GenericBusinessException{
	}
	
	private void cargarNotaDebitoCompraAnextoTransaccional(com.spirit.sri.at.Compras compras) throws GenericBusinessException{
		
		
		//TODO
		
	}

	//private int cargarVentasAnexoTransaccional(int numeroFila,String codigoDocumento, Ventas ventas){
	private int cargarVentasAnexoTransaccional(int numeroFila,Set<String> codigosDocumentos, Ventas ventas){
		try{
			Collection<Object> detalles = SessionServiceLocator.getSriManagerSessionService().generarDetallesVentaAnexoTransaccional(
					numeroFila, Parametros.getIdEmpresa(), mapaQuery, codigosDocumentos, 
					mapaCodigoIvaServicio, mapaCodigoIvaBien, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes, 
					mapaCodigoTipoIdentificacionVenta, mapaImpuestos, mapaCodigoIva);
	
			String nombreDocumento = "";
			DefaultTableModel modelo = (DefaultTableModel)getTblVentas().getModel();
			for (Object objeto : detalles){
				if (objeto instanceof DetalleVentas){
					DetalleVentas detalleVenta = (DetalleVentas) objeto;
					String nombreComprobante = mapaTipoComprobantes.get(String.valueOf(detalleVenta.getTipoComprobante()));
					if ( !nombreDocumento.equals(nombreComprobante) )
						getTxtErrores().append("\n*-------------"+nombreComprobante+"-------------*\n");
						nombreDocumento = nombreComprobante;
					{
						getTxtTextoXML().append(nivel2+"<detalleVentas>");
						getTxtTextoXML().append(nivel3+"<tpIdCliente>"+detalleVenta.getTpIdCliente()+"</tpIdCliente>");
						getTxtTextoXML().append(nivel3+"<idCliente>"+detalleVenta.getIdCliente()+"</idCliente>");
						getTxtTextoXML().append(nivel3+"<tipoComprobante>"+detalleVenta.getTipoComprobante()+"</tipoComprobante>");
						//getTxtTextoXML().append(nivel2+"<fechaRegistro>"+detalleVenta.getfeFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel3+"<numeroComprobantes>"+detalleVenta.getNumeroComprobantes()+"</numeroComprobantes>");
						//getTxtTextoXML().append(nivel2+"<fechaEmision>"+detalleVenta.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel3+"<baseNoGraIva>"+detalleVenta.getBaseNoGraIva()+"</baseNoGraIva>");
						getTxtTextoXML().append(nivel3+"<baseImponible>"+detalleVenta.getBaseImponible()+"</baseImponible>");
						//getTxtTextoXML().append(nivel2+"<ivaPresuntivo>"+detalleVenta.getIvaPresuntivo()+"</ivaPresuntivo>");
						getTxtTextoXML().append(nivel3+"<baseImpGrav>"+detalleVenta.getBaseImpGrav()+"</baseImpGrav>");
						//getTxtTextoXML().append(nivel2+"<porcentajeIva>"+detalleVenta.getPorcentajeIva()+"</porcentajeIva>");
						getTxtTextoXML().append(nivel3+"<montoIva>"+detalleVenta.getMontoIva()+"</montoIva>");
						/*getTxtTextoXML().append(nivel2+"<baseImpIce>"+detalleVenta.getBaseImpIce()+"</baseImpIce>");
						getTxtTextoXML().append(nivel2+"<porcentajeIce>"+detalleVenta.getPorcentajeIce()+"</porcentajeIce>");
						getTxtTextoXML().append(nivel2+"<montoIce>"+detalleVenta.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel2+"<montoIvaBienes>"+detalleVenta.getMontoIvaBienes()+"</montoIvaBienes>");
						getTxtTextoXML().append(nivel2+"<porRetBienes>"+detalleVenta.getPorRetBienes()+"</porRetBienes>");
						getTxtTextoXML().append(nivel2+"<valorRetBienes>"+detalleVenta.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel2+"<montoIvaServicios>"+detalleVenta.getMontoIvaServicios()+"</montoIvaServicios>");
						getTxtTextoXML().append(nivel2+"<porRetServicios>"+detalleVenta.getPorRetServicios()+"</porRetServicios>");
						getTxtTextoXML().append(nivel2+"<valorRetServicios>"+detalleVenta.getValorRetServicios()+"</valorRetServicios>");
						getTxtTextoXML().append(nivel2+"<retPresuntiva>"+detalleVenta.getRetPresuntiva()+"</retPresuntiva>");*/
						getTxtTextoXML().append(nivel3+"<valorRetIva>"+detalleVenta.getValorRetIva()+"</valorRetIva>");
						getTxtTextoXML().append(nivel3+"<valorRetRenta>"+detalleVenta.getValorRetRenta()+"</valorRetRenta>");
	
						/*getTxtTextoXML().append(nivel2+"<air>");
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
						getTxtTextoXML().append(nivel2+"<air/>");*/
	
						ventas.addDetalleVentas(detalleVenta);
						getTxtTextoXML().append(nivel2+"</detalleVentas>");
	
						Object[] fila = new Object[]{"1",numeroFila,detalleVenta.getIdCliente()
								,mapaTipoComprobantes.get(String.valueOf(detalleVenta.getTipoComprobante()))
								,detalleVenta.getNumeroComprobantes()
								,detalleVenta.getBaseImponible(),detalleVenta.getBaseImpGrav(),detalleVenta.getMontoIva()};
								//,detalleVenta.getMontoIvaBienes(),detalleVenta.getMontoIvaServicios()};
								//,0.00,0.00};
						modelo.addRow(fila);
	
					}
				} else if (objeto instanceof String){
					ventas.addDetalleVentas(null);
					getTxtErrores().append((String)objeto);
					//Object[] fila = new Object[]{"0",numeroFila,"","","",0.0,0.0,0.0,0.0,0.0};
					Object[] fila = new Object[]{"0",numeroFila,"","","",0.0,0.0,0.0};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(GenericBusinessException e){
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Ventas", SpiritAlert.ERROR);
		}
		
		return numeroFila;
	}
	
	private int cargarNotaCreditoVentaAnexoTransaccional(int numeroFila,Ventas ventas) {
		
		try{
			Collection<Object> detalles = SessionServiceLocator.getSriManagerSessionService().generarNotasCreditoVenta(numeroFila, Parametros.getIdEmpresa(), mapaQuery, mapaCodigoIvaServicio
					, mapaCodigoIvaBien, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes, mapaCodigoTipoIdentificacionVenta, mapaImpuestos, mapaCodigoIva);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblVentas().getModel();
			for (Object objeto : detalles){
				//Object objeto = itDetalles.next();
				if (objeto instanceof DetalleVentas){
					DetalleVentas detalleVenta = (DetalleVentas) objeto;
					{
						getTxtTextoXML().append(nivel2+"<detalleVentas>");
						getTxtTextoXML().append(nivel3+"<tpIdCliente>"+detalleVenta.getTpIdCliente()+"</tpIdCliente>");
						getTxtTextoXML().append(nivel3+"<idCliente>"+detalleVenta.getIdCliente()+"</idCliente>");
						getTxtTextoXML().append(nivel3+"<tipoComprobante>"+detalleVenta.getTipoComprobante()+"</tipoComprobante>");
						//getTxtTextoXML().append(nivel2+"<fechaRegistro>"+detalleVenta.getFechaRegistro()+"</fechaRegistro>");
						getTxtTextoXML().append(nivel3+"<numeroComprobantes>"+detalleVenta.getNumeroComprobantes()+"</numeroComprobantes>");
						//getTxtTextoXML().append(nivel2+"<fechaEmision>"+detalleVenta.getFechaEmision()+"</fechaEmision>");
						getTxtTextoXML().append(nivel3+"<baseNoGraIva>"+detalleVenta.getBaseNoGraIva()+"</baseNoGraIva>");
						getTxtTextoXML().append(nivel3+"<baseImponible>"+detalleVenta.getBaseImponible()+"</baseImponible>");
						//getTxtTextoXML().append(nivel2+"<ivaPresuntivo>"+detalleVenta.getIvaPresuntivo()+"</ivaPresuntivo>");
						getTxtTextoXML().append(nivel3+"<baseImpGrav>"+detalleVenta.getBaseImpGrav()+"</baseImpGrav>");
						//getTxtTextoXML().append(nivel2+"<porcentajeIva>"+detalleVenta.getPorcentajeIva()+"</porcentajeIva>");
						getTxtTextoXML().append(nivel3+"<montoIva>"+detalleVenta.getMontoIva()+"</montoIva>");
						
						/*getTxtTextoXML().append(nivel2+"<baseImpIce>"+detalleVenta.getBaseImpIce()+"</baseImpIce>");
						getTxtTextoXML().append(nivel2+"<porcentajeIce>"+detalleVenta.getPorcentajeIce()+"</porcentajeIce>");
						getTxtTextoXML().append(nivel2+"<montoIce>"+detalleVenta.getMontoIce()+"</montoIce>");
						getTxtTextoXML().append(nivel2+"<montoIvaBienes>"+detalleVenta.getMontoIvaBienes()+"</montoIvaBienes>");
						getTxtTextoXML().append(nivel2+"<porRetBienes>"+detalleVenta.getPorRetBienes()+"</porRetBienes>");
						getTxtTextoXML().append(nivel2+"<valorRetBienes>"+detalleVenta.getValorRetBienes()+"</valorRetBienes>");
						getTxtTextoXML().append(nivel2+"<montoIvaServicios>"+detalleVenta.getMontoIvaServicios()+"</montoIvaServicios>");
						getTxtTextoXML().append(nivel2+"<porRetServicios>"+detalleVenta.getPorRetServicios()+"</porRetServicios>");
						getTxtTextoXML().append(nivel2+"<valorRetServicios>"+detalleVenta.getValorRetServicios()+"</valorRetServicios>");
						getTxtTextoXML().append(nivel2+"<retPresuntiva>"+detalleVenta.getRetPresuntiva()+"</retPresuntiva>");*/
						getTxtTextoXML().append(nivel3+"<valorRetIva>"+detalleVenta.getValorRetIva()+"</valorRetIva>");
						getTxtTextoXML().append(nivel3+"<valorRetRenta>"+detalleVenta.getValorRetRenta()+"</valorRetRenta>");
						
						/*getTxtTextoXML().append(nivel2+"<air>");
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
						getTxtTextoXML().append(nivel2+"<air/>");*/
	
						ventas.addDetalleVentas(detalleVenta);
						getTxtTextoXML().append(nivel2+"</detalleVentas>");
	
						Object[] fila = new Object[]{"1",numeroFila,detalleVenta.getIdCliente()
								,mapaTipoComprobantes.get(String.valueOf(detalleVenta.getTipoComprobante()))
								,detalleVenta.getNumeroComprobantes()
								,detalleVenta.getBaseImponible(),detalleVenta.getBaseImpGrav(),detalleVenta.getMontoIva()};
								//,detalleVenta.getMontoIvaBienes(),detalleVenta.getMontoIvaServicios()};
						modelo.addRow(fila);
	
					}
				} else if (objeto instanceof String){
					ventas.addDetalleVentas(null);
					getTxtErrores().append((String)objeto);
					//Object[] fila = new Object[]{"0",numeroFila,"","","",0.0,0.0,0.0,0.0,0.0};
					Object[] fila = new Object[]{"0",numeroFila,"","","",0.0,0.0,0.0};
					modelo.addRow(fila);
				}
				numeroFila++;
			}
		} catch(Exception e){
			e.printStackTrace();
			SpiritAlert.createAlert("Error al cargar Notas de Credito - Ventas", SpiritAlert.ERROR);
		}
		
		return numeroFila;
	}
	
	private int cargarFacturasAnuladasAnexoTransaccional(int numeroFila,String codigoDocumento, Anulados anulados) throws GenericBusinessException{
		try{
			Collection<Object> detalles = SessionServiceLocator.getSriManagerSessionService().generarDetallesAnulaciones(numeroFila, Parametros.getIdEmpresa(), mapaQuery
					, codigoDocumento, mapaCodigoIdentificacion, mapaCodigoTipoComprobantes);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblAnulaciones().getModel();
			for (Object objeto : detalles){
				//Object objeto = itDetalles.next();
				if (objeto instanceof DetalleAnulados){
					DetalleAnulados detalleAnulados = (DetalleAnulados) objeto;
					{
						getTxtTextoXML().append(nivel2+"<detalleAnulados>");
						getTxtTextoXML().append(nivel3+"<tipoComprobante>"+detalleAnulados.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel3+"<establecimiento>"+detalleAnulados.getEstablecimiento()+"</establecimiento>");
						getTxtTextoXML().append(nivel3+"<puntoEmision>"+detalleAnulados.getPuntoEmision()+"</puntoEmision>");
						getTxtTextoXML().append(nivel3+"<secuencialInicio>"+detalleAnulados.getSecuencialInicio()+"</secuencialInicio>");
						getTxtTextoXML().append(nivel3+"<secuencialFin>"+detalleAnulados.getSecuencialFin()+"</secuencialFin>");
						getTxtTextoXML().append(nivel3+"<autorizacion>"+detalleAnulados.getAutorizacion()+"</autorizacion>");
						//getTxtTextoXML().append(nivel2+"<fechaAnulacion>"+detalleAnulados.getFechaAnulacion()+"</fechaAnulacion>");
	
						anulados.addDetalleAnulados(detalleAnulados);
						getTxtTextoXML().append(nivel2+"</detalleAnulados>");
	
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
	
	private int cargarCarterasAnuladasAnexoTransaccional(int numeroFila,String codigoDocumento, Anulados anulados) throws GenericBusinessException{
		try{
			Collection<Object> detalles = SessionServiceLocator.getSriManagerSessionService().generarDetalleAnulacionesCartera(
					numeroFila, Parametros.getIdEmpresa(), mapaQuery, codigoDocumento, mapaCodigoTipoComprobantes);
	
			DefaultTableModel modelo = (DefaultTableModel)getTblAnulaciones().getModel();
			for (Object objeto : detalles){
				//Object objeto = itDetalles.next();
				if (objeto instanceof DetalleAnulados){
					DetalleAnulados detalleAnulados = (DetalleAnulados) objeto;
					{
						getTxtTextoXML().append(nivel2+"<detalleAnulados>");
						getTxtTextoXML().append(nivel3+"<tipoComprobante>"+detalleAnulados.getTipoComprobante()+"</tipoComprobante>");
						getTxtTextoXML().append(nivel3+"<establecimiento>"+detalleAnulados.getEstablecimiento()+"</establecimiento>");
						getTxtTextoXML().append(nivel3+"<puntoEmision>"+detalleAnulados.getPuntoEmision()+"</puntoEmision>");
						getTxtTextoXML().append(nivel3+"<secuencialInicio>"+detalleAnulados.getSecuencialInicio()+"</secuencialInicio>");
						getTxtTextoXML().append(nivel3+"<secuencialFin>"+detalleAnulados.getSecuencialFin()+"</secuencialFin>");
						getTxtTextoXML().append(nivel3+"<autorizacion>"+
							(detalleAnulados.getAutorizacion()!=null?detalleAnulados.getAutorizacion():"")+"</autorizacion>");
						//getTxtTextoXML().append(nivel2+"<fechaAnulacion>"+detalleAnulados.getFechaAnulacion()+"</fechaAnulacion>");
	
						anulados.addDetalleAnulados(detalleAnulados);
						getTxtTextoXML().append(nivel2+"</detalleAnulados>");
	
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
		
		
		//reoc = null;
		//generacionReoc = new GeneracionReoc();
		
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

		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_IDENTIFICACION_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_TIPO_IDENTIFICACION_COMPRA).setPreferredWidth(115);
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
		
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_CERO_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_CERO_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_CERO_COMPRA).setMaxWidth(130);
		
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_GRAVADA_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_GRAVADA_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_GRAVADA_COMPRA).setMaxWidth(130);
		
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_NO_OBJETO_IVA_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_NO_OBJETO_IVA_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_NO_OBJETO_IVA_COMPRA).setMaxWidth(130);
		
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_NO_OBJETO_IVA_COMPRA).setMinWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_NO_OBJETO_IVA_COMPRA).setPreferredWidth(110);
		getTblCompras().getColumnModel().getColumn(COLUMNA_BASE_NO_OBJETO_IVA_COMPRA).setMaxWidth(130);
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
		getTblVentas().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_VENTA).setPreferredWidth(370);
		getTblVentas().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_VENTA).setMaxWidth(420);

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
		
		/*getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_BIEN_VENTA).setMinWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_BIEN_VENTA).setPreferredWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_BIEN_VENTA).setMaxWidth(95);
		
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_SERVICIO_VENTA).setMinWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_SERVICIO_VENTA).setPreferredWidth(75);
		getTblVentas().getColumnModel().getColumn(COLUMNA_IVA_SERVICIO_VENTA).setMaxWidth(95);*/
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

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_ANULACION).setMinWidth(190);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_ANULACION).setPreferredWidth(370);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_TIPO_COMPROBANTE_ANULACION).setMaxWidth(420);

		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_ESTABLECIMIENTO_ANULACION).setMinWidth(90);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_ESTABLECIMIENTO_ANULACION).setPreferredWidth(100);
		getTblAnulaciones().getColumnModel().getColumn(COLUMNA_ESTABLECIMIENTO_ANULACION).setMaxWidth(120);
		
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

	private void llenarInfoEmpresaReoc(Reoc reoc) throws GenericBusinessException{
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
	
	private void llenarInfoEmpresaAnexoTransaccional(Iva ivaGlobal) throws GenericBusinessException{
		
		try{
			DecimalFormat formatoDosEnteros = new DecimalFormat("00");
			EmpresaIf empresa = (EmpresaIf)SessionServiceLocator.getEmpresaSessionService().findEmpresaById(Parametros.getIdEmpresa()).iterator().next();
			ivaGlobal.setNumeroRuc(empresa.getRuc());
			getTxtTextoXML().append(nivel1+"<numeroRuc>"+empresa.getRuc()+"</numeroRuc>");
			ivaGlobal.setRazonSocial(empresa.getNombre());
			getTxtTextoXML().append(nivel1+"<razonSocial>"+empresa.getNombre()+"</razonSocial>");
			
			ivaGlobal.setAnio((Integer)getSpnAnio().getValue());
			getTxtTextoXML().append(nivel1+"<anio>"+(Integer)getSpnAnio().getValue()+"</anio>");
			String mes = formatoDosEnteros.format( Utilitarios.getMesInt( (String)getCmbMes().getSelectedItem() ) + 1 );
			ivaGlobal.setMes(mes);
			getTxtTextoXML().append(nivel1+"<mes>"+mes+"</mes>");

		} catch(GenericBusinessException e){
			throw new GenericBusinessException("Error a llenar Informacion de esta Empresa");
		}
		
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
