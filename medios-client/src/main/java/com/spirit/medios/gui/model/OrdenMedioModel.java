package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.MapaComercialIf;
import com.spirit.medios.entity.OrdenMedioData;
import com.spirit.medios.entity.OrdenMedioDetalleData;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioDetalleMapaIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioDetalleIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PlanMedioMesIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.criteria.OrdenMedioCriteria;
import com.spirit.medios.gui.panel.JPOrdenMedio;
import com.spirit.medios.gui.reporteData.OrdenMedioReporteData;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaRetencionIf;
import com.spirit.util.MyTableCellEditorTextEditable;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class OrdenMedioModel extends JPOrdenMedio {
	
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_ENVIADA = "ENVIADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_INGRESADA = "INGRESADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_ANULADA = "ANULADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_EMITIDA = "EMITIDA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_ORDENADA = "ORDENADA";
	private static final String NOMBRE_ESTADO_ORDEN_MEDIO_PREPAGADA = "PREPAGADA";
	private static final String ESTADO_ORDEN_ENVIADA = "E";
	private static final String ESTADO_ORDEN_INGRESADA = "I";
	private static final String ESTADO_ORDEN_ANULADA = "A";
	private static final String ESTADO_ORDEN_EMITIDA = "M";
	private static final String ESTADO_ORDEN_ORDENADA = "D";
	private static final String ESTADO_ORDEN_PREPAGADA = "R";
	
	private static final String ORDEN_MEDIO_TIPO_CANAL = "C";
	private static final String ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL = "P";
	private static final String ORDEN_MEDIO_TIPO_VERSION = "V";
	
	private DecimalFormat formatoEntero = new DecimalFormat("###0");
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	
	
	public OrdenMedioModel(){
		showSaveMode();
		initListeners();
	}
	
	public void initListeners(){
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					String codigoOrden = getTxtCodigo().getText();
					if(codigoOrden != null && !codigoOrden.equals("")){
						Collection ordenes = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByCodigo(codigoOrden);
						if(ordenes.size() > 0){
							Iterator ordenesIt = ordenes.iterator();
							while(ordenesIt.hasNext()){
								OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesIt.next();
								//si la orden no esta anulada
								if(!ordenMedioIf.getEstado().equals("A")){
									
									ClienteOficinaIf proveedorOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenMedioIf.getProveedorId());
									getTxtMedioOficina().setText(proveedorOficinaIf.getDescripcion());
									
									getTxtFecha().setText(Utilitarios.getFechaUppercase(ordenMedioIf.getFechaOrden()));
									
									if (ordenMedioIf.getEstado().compareTo(ESTADO_ORDEN_ENVIADA) == 0)
										getTxtEstado().setText(NOMBRE_ESTADO_ORDEN_MEDIO_ENVIADA);
									else if (ordenMedioIf.getEstado().compareTo(ESTADO_ORDEN_INGRESADA) == 0)
										getTxtEstado().setText(NOMBRE_ESTADO_ORDEN_MEDIO_INGRESADA);
									else if (ordenMedioIf.getEstado().compareTo(ESTADO_ORDEN_ANULADA) == 0)
										getTxtEstado().setText(NOMBRE_ESTADO_ORDEN_MEDIO_ANULADA);
									else if (ordenMedioIf.getEstado().compareTo(ESTADO_ORDEN_EMITIDA) == 0)
										getTxtEstado().setText(NOMBRE_ESTADO_ORDEN_MEDIO_EMITIDA);
									else if (ordenMedioIf.getEstado().compareTo(ESTADO_ORDEN_ORDENADA) == 0)
										getTxtEstado().setText(NOMBRE_ESTADO_ORDEN_MEDIO_ORDENADA);
									else if (ordenMedioIf.getEstado().compareTo(ESTADO_ORDEN_PREPAGADA) == 0)
										getTxtEstado().setText(NOMBRE_ESTADO_ORDEN_MEDIO_PREPAGADA);
									
									PlanMedioIf planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(ordenMedioIf.getPlanMedioId());
									
									reporteOrdenMedio(ordenMedioIf, proveedorOficinaIf, planMedioIf);
								}
							}
						}else{
							SpiritAlert.createAlert("No se encuentran ordenes con ese Código.", SpiritAlert.WARNING);
						}						
					}else{
						SpiritAlert.createAlert("Debe ingresar un Código.", SpiritAlert.WARNING);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}				
			}
		});
	}
	
	public void reporteOrdenMedio(OrdenMedioIf ordenMedioIf, ClienteOficinaIf proveedorOficina, PlanMedioIf planMedioIf) {
		try {
			String programa = "";
			String comercial = "";
			String hora = "";
			int cunias = 0;
			int cuniasExtra = 0;
			Integer totalCunias = 0;
			long comercialId = 0;
			BigDecimal valor = new BigDecimal(0);
			String productos = "";
			String versiones = "";
			// ONLY TO ORDENES DE MEDIO AGRUPADAS POR CANAL
			String[] comercialSplit = new String[2];
			// ONLY TO ORDENES DE MEDIO AGRUPADAS POR CANAL
			ArrayList<String> productosVersion = new ArrayList<String>();
			String[] productoSplit = new String[20];
			String[] versionSplit = new String[20];
			
			List<OrdenMedioReporteData> ordenMedioReporteDataLista = new ArrayList<OrdenMedioReporteData>();
			String fileName = "jaspers/medios/RPOrdenMediosTv.jasper";
			HashMap parametrosMap = new HashMap();
			
			Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> ordenMedioDetallesHashM = new HashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
			
			Collection ordenesMedioDetalle = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
			Iterator ordenesMedioDetalleIt = ordenesMedioDetalle.iterator();
			while(ordenesMedioDetalleIt.hasNext()){
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenesMedioDetalleIt.next();
				
				ArrayList<OrdenMedioDetalleMapaIf> ordenesMedioDetalleMapa = (ArrayList<OrdenMedioDetalleMapaIf>)SessionServiceLocator.getOrdenMedioDetalleMapaSessionService().findOrdenMedioDetalleMapaByOrdenMedioDetalleId(ordenMedioDetalleIf.getId());
				
				ordenMedioDetallesHashM.put(ordenMedioDetalleIf, ordenesMedioDetalleMapa);
			}
			
			BigDecimal porcentajeDescuento = new BigDecimal(0);
			String fechaActual = Utilitarios.dateHoraHoy();
			String year = fechaActual.substring(0, 4);
			String month = fechaActual.substring(5, 7);
			String day = fechaActual.substring(8, 10);
			String fechaEmision = day + " "	+ Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year;
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(proveedorOficina.getClienteId());
			ClienteIf clienteIf = (ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(ordenMedioIf.getClienteOficinaId()).iterator().next();
			OrdenTrabajoDetalleIf ordenTrabajoDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioIf.getOrdenTrabajoDetalleId());
			OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalle.getOrdenId());
	
			parametrosMap.put("usuario", Parametros.getUsuario());
			EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
			parametrosMap.put("empresa", empresa.getNombre());
			OficinaIf oficina = (OficinaIf) Parametros.getOficina();
			parametrosMap.put("direccion", oficina.getDireccion());
			parametrosMap.put("telefono", "Teléfono: " + oficina.getTelefono() + "    Fax: " + oficina.getFax());
			parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
			parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
			parametrosMap.put("sirvanse", "Transmitir");
			
			TipoProveedorIf tipoProveedor = SessionServiceLocator.getTipoProveedorSessionService().getTipoProveedor(proveedor.getTipoproveedorId());
			String numeroOrden = "";						
			if (tipoProveedor.getNombre().equals("RADIO")) {
				numeroOrden = "Orden de Transmisión Radio No. "	+ ordenMedioIf.getCodigo();
			} else if (tipoProveedor.getNombre().equals("TELEVISION")) {
				numeroOrden = "Orden de Transmisión Televisión No. " + ordenMedioIf.getCodigo();
			} else {
				numeroOrden = "Orden de Transmisión Cine No. " + ordenMedioIf.getCodigo();
			}
			numeroOrden = numeroOrden + " - " + ordenMedioIf.getRevision();
	
			String tipoOrden = "";
			if (tipoProveedor.getNombre().equals("TELEVISION")) {
				tipoOrden = "TELEVISION";
			}
			parametrosMap.put("tipoOrden", tipoOrden);
			
			// lista temporal de Auspicios para escribir la palabra AUSPICIO antes que comienze un orden medio detalle que sea parte de un auspicio
			ArrayList<String> auspiciosListTemp = new ArrayList<String>();
	
			Map<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>> mapaComercialOrdenMedioDetalle = new LinkedHashMap<OrdenMedioDetalleIf, ArrayList<OrdenMedioDetalleMapaIf>>();
			Iterator ordenMedioDetallesHashMIt = ordenMedioDetallesHashM.keySet().iterator();

			versiones = "";

			////////////////////////////////////////////////////////////
			// CUANDO LAS ORDENES DE MEDIO FUERON AGRUPADAS POR CANAL //
			////////////////////////////////////////////////////////////
			
			if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) {

				while (ordenMedioDetallesHashMIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetallesHashMIt.next();

					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);

					comercialId = ordenMedioDetalleIf.getComercialId();
					programa = ordenMedioDetalleIf.getPrograma();
					comercial = ordenMedioDetalleIf.getComercial();
					hora = ordenMedioDetalleIf.getHora().toString();
					
					// 0 version, 1 derecho de programa, 2 orden
					comercialSplit = comercial.split(",");
					
					ComercialIf comercialIf = SessionServiceLocator.getComercialSessionService().getComercial(comercialId);
			
					String productosCanal = "";
					String productoCanalVersion = "";
					String versionesCanal = "";
					String versionCanal = "";

					// PARA ESCOGER EL PRODUCTO O LETRA CON LA DURACION DE LA CUÑA PARA EL REPORTE
					Long derechoPrograma = comercialIf.getDerechoprogramaId();
					DerechoProgramaIf derechoProgramaIf = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoPrograma);
					
					if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0) {
						
						ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
						productosCanal = productoClienteIf.getNombre();
						versionesCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
				
					} else {
						Map aMap = new HashMap();
						aMap.put("campanaId", comercialIf.getCampanaId());
						aMap.put("productoClienteId", comercialIf.getProductoClienteId());
						Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
				
						if (!comercialIfColl.isEmpty()) {
					
							for (ComercialIf comercialIfTemp : comercialIfColl) {
								Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
								DerechoProgramaIf derechoProgramaIfTemp = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoProgramaTemp);
								ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
								productosCanal = productoClienteIf.getNombre();
								versionesCanal = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") "	+ comercialIf.getNombre();
								break;
							}
						}
					}

					versionCanal = versionesCanal; 						

					// PARA EVITAR QUE SE REPITAN LOS PRODUCTOS
					if (productos.length() > 0) {
						productoSplit = productos.split(", ");
						boolean isProducto = false;
						
						for (int i = 0; i < productoSplit.length; i++) {								
							String productoVersionTemp = productoSplit[i];
							
							if (productoVersionTemp.compareTo(productosCanal) == 0) {
								isProducto = true;
							}
						}
				
						if (!isProducto) {
							productos = productos + productosCanal + ", "; 
						}
					} else {
						productos = productosCanal + ", ";
					}

					if (versiones.length() > 0) {
						versionSplit = versiones.split(", ");
						boolean isVersion = false;
						
						for (int j = 0; j < versionSplit.length; j++) {
							String versionTemp = versionSplit[j];
							
							if (versionTemp.compareTo(versionCanal) == 0) {
								isVersion = true;
							}
						}
				
						if (!isVersion) {
							versiones = versiones + versionesCanal + ", ";
						}
					} else {
						versiones = versionesCanal + ", ";
					}

					if (ordenMedioDetalleIf.getPauta().compareTo("A") == 0 && ordenMedioDetalleIf.getAuspicioDescripcion() != null) {
						String auspicioDescripcion = "AUSPICIO - " + ordenMedioDetalleIf.getAuspicioDescripcion().trim();
						boolean isAuspicio = false;
				
						if (auspiciosListTemp.size() > 0) {
							for (int i = 0; i < auspiciosListTemp.size(); i++) {
								if (auspicioDescripcion.compareTo(auspiciosListTemp.get(i)) == 0) {
									isAuspicio = true;
								}
							}
						}
				
						if (!isAuspicio) {
							auspiciosListTemp.add(auspicioDescripcion);
							OrdenMedioReporteData ordenMedioReporteDataAuspicio = new OrdenMedioReporteData();
							ordenMedioReporteDataAuspicio.setHora("");
							ordenMedioReporteDataAuspicio.setPrograma(auspicioDescripcion);
							ordenMedioReporteDataAuspicio.setVersion(ordenMedioDetalleIf.getComercial().split(",")[0]);
							ordenMedioReporteDataAuspicio.setValor("");
							ordenMedioReporteDataAuspicio.setCunias("");
							ordenMedioReporteDataAuspicio.setTotal("");
							ordenMedioReporteDataAuspicio.setFechaEmision(fechaEmision);
							ordenMedioReporteDataAuspicio.setProveedorOficina(proveedorOficina.getDescripcion());
							ordenMedioReporteDataAuspicio.setProveedor(proveedor.getNombreLegal());
							ordenMedioReporteDataAuspicio.setRucProveedor(proveedor.getIdentificacion());
							ordenMedioReporteDataAuspicio.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
							ordenMedioReporteDataAuspicio.setCliente(clienteIf.getNombreLegal());
							ordenMedioReporteDataAuspicio.setOrdenTrabajo(ordenTrabajoIf.getCodigo());
							ordenMedioReporteDataAuspicio.setPlanMedio(planMedioIf.getCodigo());
					
							String elaboradoPor = "";
					
							if (ordenMedioIf.getEmpleadoId() != null) {
								EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
								elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
							}
							
							ordenMedioReporteDataAuspicio.setElaboradoPor(elaboradoPor);
							ordenMedioReporteDataAuspicio.setOrdenCanje("");
							ordenMedioReporteDataAuspicio.setNota2("");
							ordenMedioReporteDataAuspicio.setNotaPersonalizada(ordenMedioIf.getObservacion());
							CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
							ordenMedioReporteDataAuspicio.setCampania(campana.getNombre());
							ordenMedioReporteDataAuspicio.setNumeroOrden(numeroOrden);
							ordenMedioReporteDataAuspicio.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2) : productos);
							ordenMedioReporteDataAuspicio.setVersiones(versiones.substring(0, versiones.length() - 2));
							ordenMedioReporteDataAuspicio.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
							//suma de todo ordenMedio
							ordenMedioReporteDataAuspicio.setSuma(ordenMedioIf.getValorSubtotal()); 
							porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
							BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
							BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision() : new BigDecimal(0);
							
							if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1 && porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {
								porcentajeDescuento = porcentajeNegociacionComision;
							}
							
							BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
							BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
							BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra() : new BigDecimal(0);
							BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
					
							ProductoIf productoProveedor = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
							GenericoIf genericoProveedor = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedor.getGenericoId());
							BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
					
							ordenMedioReporteDataAuspicio.setDescuento(descuentoOrden);
							ordenMedioReporteDataAuspicio.setBonificacion(bonificacionOrden);
							ordenMedioReporteDataAuspicio.setIva(ivaOrden);
							ordenMedioReporteDataAuspicio.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
							ordenMedioReporteDataAuspicio.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion))	+ "%");
							BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
							ordenMedioReporteDataAuspicio.setTotalOrden(totalOrden);

							//busco si el proveedor tiene valores de retencion renta o iva
							Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
							
							if(proveedorRetenciones.size() > 0){
								fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
							}
							
							Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
							while(proveedorRetencionesIt.hasNext()){
								ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
								
								SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
								SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
								
								double retencionRenta = 0D;
								if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
									double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
									double totalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();
									retencionRenta = totalOrdenDouble * (porcentajeRetencionRenta / 100D);
									
									ordenMedioReporteDataAuspicio.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
									ordenMedioReporteDataAuspicio.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
								}
								
								double retencionIva = 0D;
								if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
									double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
									double ivaOrdenDouble = ivaOrden.doubleValue();
									retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
									
									ordenMedioReporteDataAuspicio.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
									ordenMedioReporteDataAuspicio.setRetencionIva(BigDecimal.valueOf(retencionIva));
									//ordenMedioReporteDataAuspicio.setRetencionIva(Utilitarios.redondeoValor(BigDecimal.valueOf(retencionIva)));
								}
								
								double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
								ordenMedioReporteDataAuspicio.setTotalPagar(BigDecimal.valueOf(totalPagar));
							}
							
							//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
							for (int i = 0; i < 31; i++) {									
								setDiaLetraPauta(ordenMedioReporteDataAuspicio,	i + 1, planMedioIf);
							}
							
							ordenMedioReporteDataLista.add(ordenMedioReporteDataAuspicio);
						}
					}

					totalCunias = 0;
					BigDecimal valor_tarifa = ordenMedioDetalleIf.getValorTarifa();
					OrdenMedioReporteData ordenMedioReporteData = new OrdenMedioReporteData();
					ordenMedioReporteData.setFechaEmision(fechaEmision);
					ordenMedioReporteData.setProveedorOficina(proveedorOficina.getDescripcion());
					ordenMedioReporteData.setProveedor(proveedor.getNombreLegal());
					ordenMedioReporteData.setRucProveedor(proveedor.getIdentificacion());
					ordenMedioReporteData.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
					ordenMedioReporteData.setCliente(clienteIf.getNombreLegal());
					ordenMedioReporteData.setOrdenTrabajo(ordenTrabajoIf.getCodigo());
					ordenMedioReporteData.setPlanMedio(planMedioIf.getCodigo());
					
					String elaboradoPor = "";
			
					if (ordenMedioIf.getEmpleadoId() != null) {
						EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
						elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
					}
					
					ordenMedioReporteData.setElaboradoPor(elaboradoPor);
					ordenMedioReporteData.setOrdenCanje("");
					ordenMedioReporteData.setNota2("");
					ordenMedioReporteData.setNotaPersonalizada(ordenMedioIf.getObservacion());
					CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
					ordenMedioReporteData.setCampania(campana.getNombre());
					ordenMedioReporteData.setNumeroOrden(numeroOrden);
					ordenMedioReporteData.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2) : productos);
					ordenMedioReporteData.setVersiones(versiones.substring(0, versiones.length() - 2));
			
					// variable para indicar si se encuentra o no la OrdenMedioReporteData en la lista de ordenMedioReporteDataLista
					boolean equal = false;
			
					// variable indice de la OrdenMedioReporteData en la lista de ordenMedioReporteDataLista
					int indice = -1;
			
					if (ordenMedioReporteDataLista.size() > 0) {
				
						// PARA VERIFICAR QUE LA ORDEN MEDIO REPORT EXISTE Y AGREGARLE DATOS INCLUIR OTROS PRODUCTOS DENTRO DE UN REPORDATA QUE YA CONTIENE PRODUCTOS
						for (int i = 0; i < ordenMedioReporteDataLista.size(); i++) {
							OrdenMedioReporteData ordenMedioReporteDataTemp = ordenMedioReporteDataLista.get(i);
							
							if (ordenMedioReporteDataTemp.getHora().compareTo(hora) == 0 
									&& ordenMedioReporteDataTemp.getPrograma().compareTo(programa + " (" + comercialSplit[1]	+ ")") == 0
									&& ordenMedioReporteDataTemp.getValorBig().equals(valor_tarifa)
									&& ordenMedioReporteDataTemp.getVersion().equals(comercialSplit[0])
									&& ordenMedioReporteDataTemp.getNumeroOrden().equals(String.valueOf(ordenMedioIf.getNumeroOrdenAgrupacion()))) {
									// ordenMedioReporteDataTemp.getValor().compareTo(formatoDecimal.format(valor_tarifa)) == 0 ){
								equal = true;
							}
					
							if (equal) {
								ordenMedioReporteData = ordenMedioReporteDataTemp;
								indice = i;
								break;
							}
						}
					}
					
					if (indice != -1) {
						totalCunias = Integer.valueOf(ordenMedioReporteData.getCunias());
						java.sql.Date fechaPrograma = new java.sql.Date(1999, 12, 15);
				
						for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList) {
							totalCunias = totalCunias + ordenMedioDetalleMapaIf.getFrecuencia();
							fechaPrograma = Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha());
					
							// SE SETEAN LAS FRECUENCIAS EN LOS DIAS CORRESPONDIENTES DE LA ORDEN MEDIO REPORTE DATA
							setDiaPautaByCanal(ordenMedioDetalleMapaIf,	ordenMedioReporteData, comercialSplit[0]);
						}
						
						ordenMedioReporteData.setCunias(totalCunias.toString());
				
						BigDecimal tarifaTemp = ordenMedioReporteData.getValorBig();
						BigDecimal totalCuniasBD = new BigDecimal(totalCunias);
						BigDecimal totalDetalle = tarifaTemp.multiply(totalCuniasBD);
						ordenMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(totalDetalle)));
						ordenMedioReporteDataLista.add(indice, ordenMedioReporteData);
						ordenMedioReporteDataLista.remove(indice + 1);
				
					} else {
						ordenMedioReporteData.setHora(ordenMedioDetalleIf.getHora().toString());
						ordenMedioReporteData.setPrograma(ordenMedioDetalleIf.getPrograma() + " (" + comercialSplit[1] + ")");
						ordenMedioReporteData.setVersion(ordenMedioDetalleIf.getComercial().split(",")[0]);
						valor_tarifa = ordenMedioDetalleIf.getValorTarifa();
						ordenMedioReporteData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(valor_tarifa)));
						ordenMedioReporteData.setValorBig(valor_tarifa);
						totalCunias = 0;

						java.sql.Date fechaPrograma = new java.sql.Date(1999, 12, 15);
				
						//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
						for (int i = 0; i < 31; i++) {
							setDiaLetraPauta(ordenMedioReporteData,	i + 1, planMedioIf);
						}
				
						for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList) {
							totalCunias = totalCunias + ordenMedioDetalleMapaIf.getFrecuencia();
							fechaPrograma = Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha());
							
							//SE SETEAN LAS FRECUENCIAS EN LOS DIAS CORRESPONDIENTES DE LA ORDEN MEDIO REPORTE DATA
							setDiaPautaByCanal(ordenMedioDetalleMapaIf,	ordenMedioReporteData, comercialSplit[0]);
						}

						ordenMedioReporteData.setCunias(String.valueOf(totalCunias));
						BigDecimal totalDetalle = ordenMedioDetalleIf.getValorSubtotal();
						// valor total
						ordenMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(totalDetalle)));

						ordenMedioReporteData.setDia(fechaPrograma.getDate());
						ordenMedioReporteData.setFechaPrograma(fechaPrograma);
						porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
						ordenMedioReporteData.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
						
						// suma de todo ordenMedio
						ordenMedioReporteData.setSuma(ordenMedioIf.getValorSubtotal()); 
				
						BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
						BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision() : new BigDecimal(0);
				
						if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1 
								&& porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {										
							porcentajeDescuento = porcentajeNegociacionComision;
						}
						
						BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
						BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
						BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra() : new BigDecimal(0);
						BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
				
						ProductoIf productoProveedor = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
						GenericoIf genericoProveedor = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedor.getGenericoId());
						BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
							
						ordenMedioReporteData.setDescuento(descuentoOrden);
						ordenMedioReporteData.setBonificacion(bonificacionOrden);
						ordenMedioReporteData.setIva(ivaOrden);
						ordenMedioReporteData.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
						ordenMedioReporteData.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)) + "%");
						BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
						ordenMedioReporteData.setTotalOrden(totalOrden);
						
						//busco si el proveedor tiene valores de retencion renta o iva
						Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
						
						if(proveedorRetenciones.size() > 0){
							fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
						}
						
						Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
						while(proveedorRetencionesIt.hasNext()){
							ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
							
							SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
							SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
							
							double retencionRenta = 0D;
							if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
								double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
								double totalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();
								retencionRenta = totalOrdenDouble * (porcentajeRetencionRenta / 100D);
								
								ordenMedioReporteData.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
								ordenMedioReporteData.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
							}
							
							double retencionIva = 0D;
							if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
								double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
								double ivaOrdenDouble = ivaOrden.doubleValue();
								retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
								
								ordenMedioReporteData.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
								ordenMedioReporteData.setRetencionIva(BigDecimal.valueOf(retencionIva));
							}
							
							double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
							ordenMedioReporteData.setTotalPagar(BigDecimal.valueOf(totalPagar));
						}									
						
						ordenMedioReporteDataLista.add(ordenMedioReporteData);
					}
				}
			}
			
			/////////////////////////////////////////////////////////////////////////////////////
			// CUANDO LAS ORDENES DE MEDIO FUERON AGRUPADAS POR PRODUCTO COMERCIAL O COMERCIAL //
			/////////////////////////////////////////////////////////////////////////////////////
			
			else if (planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_PRODUCTO_COMERCIAL) == 0 
					|| planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_VERSION) == 0) {

				while (ordenMedioDetallesHashMIt.hasNext()) {
					OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) ordenMedioDetallesHashMIt.next();

					comercialId = ordenMedioDetalleIf.getComercialId();
					ComercialIf comercialIf = SessionServiceLocator.getComercialSessionService().getComercial(comercialId);
			
					String versionesProductoComercial = "";
					String versionProductoComercial = "";

					//PARA ESCOGER EL PRODUCTO O LETRA CON LA DURACION DE LA CUÑA PARA EL REPORTE
					Long derechoPrograma = comercialIf.getDerechoprogramaId();
					DerechoProgramaIf derechoProgramaIf = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoPrograma);
			
					if (derechoProgramaIf.getNombre().trim().compareTo("CUÑA") == 0) {
						
						ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
						productos = productoClienteIf.getNombre();
						versionesProductoComercial = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
				
					} else {							
						Map aMap = new HashMap();
						aMap.put("campanaId", comercialIf.getCampanaId());
						aMap.put("productoClienteId", comercialIf.getProductoClienteId());
						Collection<ComercialIf> comercialIfColl = SessionServiceLocator.getComercialSessionService().findComercialByQuery(aMap);
						
						if (!comercialIfColl.isEmpty()) {
							for (ComercialIf comercialIfTemp : comercialIfColl) {
								Long derechoProgramaTemp = comercialIfTemp.getDerechoprogramaId();
								DerechoProgramaIf derechoProgramaIfTemp =SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(derechoProgramaTemp);
								ProductoClienteIf productoClienteIf = SessionServiceLocator.getProductoClienteSessionService().getProductoCliente(comercialIf.getProductoClienteId());
								productos = productoClienteIf.getNombre();
								versionesProductoComercial = comercialIf.getVersion() + " (" + comercialIf.getDuracion() + ") " + comercialIf.getNombre();
								break;									
							}
						}
					}						

					versionProductoComercial = versionesProductoComercial;
			
					if (versiones.length() > 0) {
						versionSplit = versiones.split(", ");
						boolean isVersion = false;
							
						for (int j = 0; j < versionSplit.length; j++) {
							String versionTemp = versionSplit[j];
							if (versionTemp.compareTo(versionProductoComercial) == 0) {
								isVersion = true;
							}
						}
						
						if (!isVersion) {
							versiones = versiones + versionesProductoComercial + ", ";
						}
						
					} else {
						versiones = versionesProductoComercial + ", ";
					}						

					if (ordenMedioDetalleIf.getPauta().compareTo("A") == 0
							&& ordenMedioDetalleIf.getAuspicioDescripcion() != null) {
						String auspicioDescripcion = "AUSPICIO - " + ordenMedioDetalleIf.getAuspicioDescripcion().trim();
						boolean isAuspicio = false;
				
						if (auspiciosListTemp.size() > 0) {
							for (int i = 0; i < auspiciosListTemp.size(); i++) {
								if (auspicioDescripcion.compareTo(auspiciosListTemp.get(i)) == 0) {
									isAuspicio = true;
								}
							}
						}
				
						if (!isAuspicio) {
							auspiciosListTemp.add(auspicioDescripcion);
							
							OrdenMedioReporteData ordenMedioReporteDataAuspicio = new OrdenMedioReporteData();

							ordenMedioReporteDataAuspicio.setHora("");
							ordenMedioReporteDataAuspicio.setPrograma(auspicioDescripcion);
							ordenMedioReporteDataAuspicio.setVersion("");
							ordenMedioReporteDataAuspicio.setValor("");
							ordenMedioReporteDataAuspicio.setCunias("");
							ordenMedioReporteDataAuspicio.setTotal("");
							ordenMedioReporteDataAuspicio.setFechaEmision(fechaEmision);
							ordenMedioReporteDataAuspicio.setProveedorOficina(proveedorOficina.getDescripcion());
							ordenMedioReporteDataAuspicio.setProveedor(proveedor.getNombreLegal());
							ordenMedioReporteDataAuspicio.setRucProveedor(proveedor.getIdentificacion());
							ordenMedioReporteDataAuspicio.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
							ordenMedioReporteDataAuspicio.setCliente(clienteIf.getNombreLegal());
							ordenMedioReporteDataAuspicio.setOrdenTrabajo(ordenTrabajoIf.getCodigo());
							ordenMedioReporteDataAuspicio.setPlanMedio(planMedioIf.getCodigo());
							
							String elaboradoPor = "";
							
							if (ordenMedioIf.getEmpleadoId() != null) {
								EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
								elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
							}
					
							ordenMedioReporteDataAuspicio.setElaboradoPor(elaboradoPor);
							ordenMedioReporteDataAuspicio.setOrdenCanje("");
							ordenMedioReporteDataAuspicio.setNota2("");
							ordenMedioReporteDataAuspicio.setNotaPersonalizada(ordenMedioIf.getObservacion());
							CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
							ordenMedioReporteDataAuspicio.setCampania(campana.getNombre());
							ordenMedioReporteDataAuspicio.setNumeroOrden(numeroOrden);
							ordenMedioReporteDataAuspicio.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2)	: productos);
							ordenMedioReporteDataAuspicio.setVersiones(versiones.substring(0, versiones.length() - 2));
							ordenMedioReporteDataAuspicio.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
							//suma de todo ordenMedio
							ordenMedioReporteDataAuspicio.setSuma(ordenMedioIf.getValorSubtotal()); 
					
							porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
							BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
							BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision(): new BigDecimal(0);
					
							if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1
									&& porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {
								porcentajeDescuento = porcentajeNegociacionComision;
							}
							
							BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
							BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
							BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra(): new BigDecimal(0);
							BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
					
							ProductoIf productoProveedor = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
							GenericoIf genericoProveedor = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedor.getGenericoId());
							BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
					
							ordenMedioReporteDataAuspicio.setDescuento(descuentoOrden);
							ordenMedioReporteDataAuspicio.setBonificacion(bonificacionOrden);
							ordenMedioReporteDataAuspicio.setIva(ivaOrden);
							ordenMedioReporteDataAuspicio.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
							ordenMedioReporteDataAuspicio.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)) + "%");
							BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
							ordenMedioReporteDataAuspicio.setTotalOrden(totalOrden);
					
							//busco si el proveedor tiene valores de retencion renta o iva
							Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
							
							if(proveedorRetenciones.size() > 0){
								fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
							}
							
							Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
							while(proveedorRetencionesIt.hasNext()){
								ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
								
								SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
								SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
								
								double retencionRenta = 0D;
								if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
									double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
									double totalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();;
									retencionRenta = totalOrdenDouble * (porcentajeRetencionRenta / 100D);
									
									ordenMedioReporteDataAuspicio.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
									ordenMedioReporteDataAuspicio.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
								}
								
								double retencionIva = 0D;
								if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
									double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
									double ivaOrdenDouble = ivaOrden.doubleValue();
									retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
									
									ordenMedioReporteDataAuspicio.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
									ordenMedioReporteDataAuspicio.setRetencionIva(BigDecimal.valueOf(retencionIva));
									//ordenMedioReporteDataAuspicio.setRetencionIva(Utilitarios.redondeoValor(BigDecimal.valueOf(retencionIva)));
								}
								
								double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
								ordenMedioReporteDataAuspicio.setTotalPagar(BigDecimal.valueOf(totalPagar));
							}
							
							//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
							for (int i = 0; i < 31; i++) {
								setDiaLetraPauta(ordenMedioReporteDataAuspicio, i + 1, planMedioIf);
							}
					
							ordenMedioReporteDataLista.add(ordenMedioReporteDataAuspicio);
						}
					}
			
					ArrayList<OrdenMedioDetalleMapaIf> ordenMedioDetalleMapaList = ordenMedioDetallesHashM.get(ordenMedioDetalleIf);

					OrdenMedioReporteData ordenMedioReporteData = new OrdenMedioReporteData();
					ordenMedioReporteData.setFechaEmision(fechaEmision);
					ordenMedioReporteData.setProveedorOficina(proveedorOficina.getDescripcion());
					ordenMedioReporteData.setProveedor(proveedor.getNombreLegal());
					ordenMedioReporteData.setRucProveedor(proveedor.getIdentificacion());
					ordenMedioReporteData.setMes(Utilitarios.getFechaMesAnioUppercase(ordenMedioIf.getFechaOrden()));
					ordenMedioReporteData.setCliente(clienteIf.getNombreLegal());
					ordenMedioReporteData.setOrdenTrabajo(ordenTrabajoIf.getCodigo());
					ordenMedioReporteData.setPlanMedio(planMedioIf.getCodigo());
					String elaboradoPor = "";
					
					if (ordenMedioIf.getEmpleadoId() != null) {
						EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(ordenMedioIf.getEmpleadoId());
						elaboradoPor = empleado.getNombres() + " " + empleado.getApellidos().split(" ")[0];
					}
					
					ordenMedioReporteData.setElaboradoPor(elaboradoPor);
					ordenMedioReporteData.setOrdenCanje("");
					ordenMedioReporteData.setNota2("");
					ordenMedioReporteData.setNotaPersonalizada(ordenMedioIf.getObservacion());
					
					CampanaIf campana = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajoIf.getCampanaId());
					ordenMedioReporteData.setCampania(campana.getNombre());
					ordenMedioReporteData.setNumeroOrden(numeroOrden);
					ordenMedioReporteData.setProducto((planMedioIf.getOrdenMedioTipo().compareTo(ORDEN_MEDIO_TIPO_CANAL) == 0) ? productos.substring(0, productos.length() - 2) : productos);
					ordenMedioReporteData.setVersiones(versiones.substring(0, versiones.length() - 2));
			
					totalCunias = 0;
			
					java.sql.Date fechaPrograma = new java.sql.Date(1999, 12, 15);
			
					//SE SETEAN LOS DIAS EN LETRA PARA EL REPORTE
					for (int i = 0; i < 31; i++) {
						setDiaLetraPauta(ordenMedioReporteData, i + 1, planMedioIf);
					}
			
					for (OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf : ordenMedioDetalleMapaList) {
						totalCunias = totalCunias + ordenMedioDetalleMapaIf.getFrecuencia();
						fechaPrograma = Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha());
				
						//SE SETEAN LAS FRECUENCIAS EN LOS DIAS CORRESPONDIENTES DE LA ORDEN MEDIO REPORTE DATA
						setDiaPauta(ordenMedioDetalleMapaIf, ordenMedioReporteData);
					}

					comercialId = ordenMedioDetalleIf.getComercialId();
					programa = ordenMedioDetalleIf.getPrograma();
					comercial = ordenMedioDetalleIf.getComercial();
					hora = ordenMedioDetalleIf.getHora();

					ordenMedioReporteData.setHora(ordenMedioDetalleIf.getHora().toString());
					ordenMedioReporteData.setPrograma(ordenMedioDetalleIf.getPrograma() + " (" + ordenMedioDetalleIf.getComercial().split(",")[1] + ")");
					ordenMedioReporteData.setVersion(ordenMedioDetalleIf.getComercial().split(",")[0]);
			
					BigDecimal valor_tarifa = ordenMedioDetalleIf.getValorTarifa();
					ordenMedioReporteData.setValor(formatoDecimal.format(Utilitarios.redondeoValor(valor_tarifa)));
					ordenMedioReporteData.setCunias(String.valueOf(totalCunias));
					BigDecimal totalDetalle = ordenMedioDetalleIf.getValorSubtotal();
					//valor total
					ordenMedioReporteData.setTotal(formatoDecimal.format(Utilitarios.redondeoValor(totalDetalle)));
					ordenMedioReporteData.setDia(fechaPrograma.getDate());
											
					ordenMedioReporteData.setFechaPrograma(fechaPrograma);
					porcentajeDescuento = ordenMedioDetalleIf.getPorcentajeDescuento();
					ordenMedioReporteData.setPorcentajeIVA(formatoEntero.format(Parametros.getIVA()));
					// suma de todo ordenMedio
					ordenMedioReporteData.setSuma(ordenMedioIf.getValorSubtotal()); 
			
					BigDecimal porcentajeCanje = ordenMedioIf.getPorcentajeCanje() != null ? ordenMedioIf.getPorcentajeCanje() : new BigDecimal(0);
					BigDecimal porcentajeNegociacionComision = ordenMedioIf.getPorcentajeNegociacionComision() != null ? ordenMedioIf.getPorcentajeNegociacionComision() : new BigDecimal(0);
			
					if (porcentajeCanje.compareTo(new BigDecimal(0)) == 1 && porcentajeCanje.compareTo(new BigDecimal(100)) == -1) {
						porcentajeDescuento = porcentajeNegociacionComision;
					}
					
					BigDecimal descuentoOrden = ordenMedioIf.getValorSubtotal().multiply(porcentajeDescuento.divide(new BigDecimal(100)));
					BigDecimal subTotalOrden = ordenMedioIf.getValorSubtotal().subtract(descuentoOrden);
					BigDecimal porcentajeBonificacion = ordenMedioIf.getPorcentajeBonificacionCompra() != null ? ordenMedioIf.getPorcentajeBonificacionCompra() : new BigDecimal(0);
					BigDecimal bonificacionOrden = subTotalOrden.multiply(porcentajeBonificacion.divide(new BigDecimal(100)));
					
					ProductoIf productoProveedor = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
					GenericoIf genericoProveedor = SessionServiceLocator.getGenericoSessionService().getGenerico(productoProveedor.getGenericoId());
					
					BigDecimal ivaOrden = genericoProveedor.getCobraIva().equals("S") ? subTotalOrden.subtract(bonificacionOrden).multiply(BigDecimal.valueOf(Parametros.IVA / 100D)) : BigDecimal.ZERO;
			
					ordenMedioReporteData.setDescuento(descuentoOrden);
					ordenMedioReporteData.setBonificacion(bonificacionOrden);
					ordenMedioReporteData.setIva(ivaOrden);
					ordenMedioReporteData.setPorcentajeDescuento(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeDescuento)) + "%");
					ordenMedioReporteData.setPorcentajeBonificacion(formatoDecimal.format(Utilitarios.redondeoValor(porcentajeBonificacion)) + "%");
					BigDecimal totalOrden = subTotalOrden.subtract(bonificacionOrden).add(ivaOrden);
					ordenMedioReporteData.setTotalOrden(totalOrden);
					
					//busco si el proveedor tiene valores de retencion renta o iva
					Collection proveedorRetenciones = SessionServiceLocator.getClienteRetencionSessionService().findClienteRetencionByClienteId(proveedor.getId());
					
					if(proveedorRetenciones.size() > 0){
						fileName = "jaspers/medios/RPOrdenMediosTvConRetencion.jasper";
					}
					
					Iterator proveedorRetencionesIt = proveedorRetenciones.iterator();
					while(proveedorRetencionesIt.hasNext()){
						ClienteRetencionIf clienteRetencion = (ClienteRetencionIf)proveedorRetencionesIt.next();
						
						SriAirIf sriAir = SessionServiceLocator.getSriAirSessionService().getSriAir(clienteRetencion.getSriAirId());
						SriIvaRetencionIf sriIvaRetencion = SessionServiceLocator.getSriIvaRetencionSessionService().getSriIvaRetencion(clienteRetencion.getSriIvaRetencionId());
						
						double retencionRenta = 0D;
						if(sriAir.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
							double porcentajeRetencionRenta = sriAir.getPorcentaje().doubleValue();
							double subTotalOrdenDouble = subTotalOrden.subtract(bonificacionOrden).doubleValue();
							retencionRenta = subTotalOrdenDouble * (porcentajeRetencionRenta / 100D);
							
							ordenMedioReporteData.setPorcentajeRetencionRenta(formatoDecimal.format(porcentajeRetencionRenta));
							ordenMedioReporteData.setRetencionRenta(BigDecimal.valueOf(retencionRenta));
						}
						
						double retencionIva = 0D;
						if(sriIvaRetencion.getPorcentaje().compareTo(new BigDecimal(0)) == 1){
							double porcentajeRetencionIva = sriIvaRetencion.getPorcentaje().doubleValue();
							double ivaOrdenDouble = ivaOrden.doubleValue();
							retencionIva = ivaOrdenDouble * (porcentajeRetencionIva / 100D);
							
							ordenMedioReporteData.setPorcentajeRetencionIva(formatoDecimal.format(porcentajeRetencionIva));
							ordenMedioReporteData.setRetencionIva(BigDecimal.valueOf(retencionIva));
						}
						
						double totalPagar = totalOrden.doubleValue() - retencionRenta - retencionIva;
						ordenMedioReporteData.setTotalPagar(BigDecimal.valueOf(totalPagar));
					}
					
					ordenMedioReporteDataLista.add(ordenMedioReporteData);
				}
			}
	
			ReportModelImpl.processReport(fileName, parametrosMap,ordenMedioReporteDataLista, true);
			ordenMedioReporteDataLista.clear();
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void setDiaPauta(OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf,
			OrdenMedioReporteData ordenMedioReporteData) {
		Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha())
				.getDate();

		int frecuencia = ordenMedioDetalleMapaIf.getFrecuencia();
		int frecuenciaAcumulada = 0;
		switch (Utilitarios.fromTimestampToSqlDate(
				ordenMedioDetalleMapaIf.getFecha()).getDate()) {
		case 1:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia01() != null && !ordenMedioReporteData
					.getDia01().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia01()) : 0;
			ordenMedioReporteData.setDia01(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 2:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia02() != null && !ordenMedioReporteData
					.getDia02().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia02()) : 0;
			ordenMedioReporteData.setDia02(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 3:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia03() != null && !ordenMedioReporteData
					.getDia03().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia03()) : 0;
			ordenMedioReporteData.setDia03(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 4:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia04() != null && !ordenMedioReporteData
					.getDia04().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia04()) : 0;
			ordenMedioReporteData.setDia04(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 5:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia05() != null && !ordenMedioReporteData
					.getDia05().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia05()) : 0;
			ordenMedioReporteData.setDia05(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 6:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia06() != null && !ordenMedioReporteData
					.getDia06().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia06()) : 0;
			ordenMedioReporteData.setDia06(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 7:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia07() != null && !ordenMedioReporteData
					.getDia07().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia07()) : 0;
			ordenMedioReporteData.setDia07(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 8:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia08() != null && !ordenMedioReporteData
					.getDia08().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia08()) : 0;
			ordenMedioReporteData.setDia08(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 9:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia09() != null && !ordenMedioReporteData
					.getDia09().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia09()) : 0;
			ordenMedioReporteData.setDia09(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 10:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia10() != null && !ordenMedioReporteData
					.getDia10().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia10()) : 0;
			ordenMedioReporteData.setDia10(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 11:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia11() != null && !ordenMedioReporteData
					.getDia11().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia11()) : 0;
			ordenMedioReporteData.setDia11(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 12:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia12() != null && !ordenMedioReporteData
					.getDia12().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia12()) : 0;
			ordenMedioReporteData.setDia12(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 13:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia13() != null && !ordenMedioReporteData
					.getDia13().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia13()) : 0;
			ordenMedioReporteData.setDia13(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 14:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia14() != null && !ordenMedioReporteData
					.getDia14().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia14()) : 0;
			ordenMedioReporteData.setDia14(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 15:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia15() != null && !ordenMedioReporteData
					.getDia15().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia15()) : 0;
			ordenMedioReporteData.setDia15(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 16:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia16() != null && !ordenMedioReporteData
					.getDia16().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia16()) : 0;
			ordenMedioReporteData.setDia16(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 17:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia17() != null && !ordenMedioReporteData
					.getDia17().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia17()) : 0;
			ordenMedioReporteData.setDia17(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 18:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia18() != null && !ordenMedioReporteData
					.getDia18().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia18()) : 0;
			ordenMedioReporteData.setDia18(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 19:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia19() != null && !ordenMedioReporteData
					.getDia19().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia19()) : 0;
			ordenMedioReporteData.setDia19(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 20:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia20() != null && !ordenMedioReporteData
					.getDia20().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia20()) : 0;
			ordenMedioReporteData.setDia20(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 21:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia21() != null && !ordenMedioReporteData
					.getDia21().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia21()) : 0;
			ordenMedioReporteData.setDia21(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 22:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia22() != null && !ordenMedioReporteData
					.getDia22().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia22()) : 0;
			ordenMedioReporteData.setDia22(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 23:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia23() != null && !ordenMedioReporteData
					.getDia23().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia23()) : 0;
			ordenMedioReporteData.setDia23(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 24:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia24() != null && !ordenMedioReporteData
					.getDia24().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia24()) : 0;
			ordenMedioReporteData.setDia24(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 25:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia25() != null && !ordenMedioReporteData
					.getDia25().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia25()) : 0;
			ordenMedioReporteData.setDia25(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 26:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia26() != null && !ordenMedioReporteData
					.getDia26().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia26()) : 0;
			ordenMedioReporteData.setDia26(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 27:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia27() != null && !ordenMedioReporteData
					.getDia27().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia27()) : 0;
			ordenMedioReporteData.setDia27(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 28:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia28() != null && !ordenMedioReporteData
					.getDia28().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia28()) : 0;
			ordenMedioReporteData.setDia28(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 29:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia29() != null && !ordenMedioReporteData
					.getDia29().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia29()) : 0;
			ordenMedioReporteData.setDia29(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 30:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia30() != null && !ordenMedioReporteData
					.getDia30().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia30()) : 0;
			ordenMedioReporteData.setDia30(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 31:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia31() != null && !ordenMedioReporteData
					.getDia31().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia31()) : 0;
			ordenMedioReporteData.setDia31(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		default:
			System.out
					.println("No se encontraron coincidencias con ningún día de la semana");
		}
	}
	
	private void setDiaLetraPauta(OrdenMedioReporteData ordenMedioReporteData,
			int dia, PlanMedioIf planMedioIf) {

		Date fechaPlanMedio = Utilitarios.fromTimestampToSqlDate(planMedioIf
				.getFechaInicio());
		int mes = fechaPlanMedio.getMonth();
		int anio = fechaPlanMedio.getYear();
		Date fechaDiaria = new Date(anio, mes, dia);
		String[] fechaFormateada = Utilitarios.getFechaEEEddMMMToReporte(
				fechaDiaria).split("-");

		switch (dia) {
		case 1:
			ordenMedioReporteData.setDiaLetra01(fechaFormateada[0]);
			break;
		case 2:
			ordenMedioReporteData.setDiaLetra02(fechaFormateada[0]);
			break;
		case 3:
			ordenMedioReporteData.setDiaLetra03(fechaFormateada[0]);
			break;
		case 4:
			ordenMedioReporteData.setDiaLetra04(fechaFormateada[0]);
			break;
		case 5:
			ordenMedioReporteData.setDiaLetra05(fechaFormateada[0]);
			break;
		case 6:
			ordenMedioReporteData.setDiaLetra06(fechaFormateada[0]);
			break;
		case 7:
			ordenMedioReporteData.setDiaLetra07(fechaFormateada[0]);
			break;
		case 8:
			ordenMedioReporteData.setDiaLetra08(fechaFormateada[0]);
			break;
		case 9:
			ordenMedioReporteData.setDiaLetra09(fechaFormateada[0]);
			break;
		case 10:
			ordenMedioReporteData.setDiaLetra10(fechaFormateada[0]);
			break;
		case 11:
			ordenMedioReporteData.setDiaLetra11(fechaFormateada[0]);
			break;
		case 12:
			ordenMedioReporteData.setDiaLetra12(fechaFormateada[0]);
			break;
		case 13:
			ordenMedioReporteData.setDiaLetra13(fechaFormateada[0]);
			break;
		case 14:
			ordenMedioReporteData.setDiaLetra14(fechaFormateada[0]);
			break;
		case 15:
			ordenMedioReporteData.setDiaLetra15(fechaFormateada[0]);
			break;
		case 16:
			ordenMedioReporteData.setDiaLetra16(fechaFormateada[0]);
			break;
		case 17:
			ordenMedioReporteData.setDiaLetra17(fechaFormateada[0]);
			break;
		case 18:
			ordenMedioReporteData.setDiaLetra18(fechaFormateada[0]);
			break;
		case 19:
			ordenMedioReporteData.setDiaLetra19(fechaFormateada[0]);
			break;
		case 20:
			ordenMedioReporteData.setDiaLetra20(fechaFormateada[0]);
			break;
		case 21:
			ordenMedioReporteData.setDiaLetra21(fechaFormateada[0]);
			break;
		case 22:
			ordenMedioReporteData.setDiaLetra22(fechaFormateada[0]);
			break;
		case 23:
			ordenMedioReporteData.setDiaLetra23(fechaFormateada[0]);
			break;
		case 24:
			ordenMedioReporteData.setDiaLetra24(fechaFormateada[0]);
			break;
		case 25:
			ordenMedioReporteData.setDiaLetra25(fechaFormateada[0]);
			break;
		case 26:
			ordenMedioReporteData.setDiaLetra26(fechaFormateada[0]);
			break;
		case 27:
			ordenMedioReporteData.setDiaLetra27(fechaFormateada[0]);
			break;
		case 28:
			ordenMedioReporteData.setDiaLetra28(fechaFormateada[0]);
			break;
		case 29:
			ordenMedioReporteData.setDiaLetra29(fechaFormateada[0]);
			break;
		case 30:
			ordenMedioReporteData.setDiaLetra30(fechaFormateada[0]);
			break;
		case 31:
			ordenMedioReporteData.setDiaLetra31(fechaFormateada[0]);
			break;
		default:
			System.out
					.println("error, no coincidio con ningun dia de la semana");
		}
	}
	
	private void setDiaPautaByCanal(
			OrdenMedioDetalleMapaIf ordenMedioDetalleMapaIf,
			OrdenMedioReporteData ordenMedioReporteData, String version) {
		Utilitarios.fromTimestampToSqlDate(ordenMedioDetalleMapaIf.getFecha())
				.getDate();

		String dataDia = "";

		int frecuencia = ordenMedioDetalleMapaIf.getFrecuencia();
		int frecuenciaAcumulada = 0;
		switch (Utilitarios.fromTimestampToSqlDate(
				ordenMedioDetalleMapaIf.getFecha()).getDate()) {
		case 1:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia01() != null && !ordenMedioReporteData
					.getDia01().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia01()) : 0;
			ordenMedioReporteData.setDia01(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 2:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia02() != null && !ordenMedioReporteData
					.getDia02().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia02()) : 0;
			ordenMedioReporteData.setDia02(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 3:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia03() != null && !ordenMedioReporteData
					.getDia03().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia03()) : 0;
			ordenMedioReporteData.setDia03(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 4:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia04() != null && !ordenMedioReporteData
					.getDia04().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia04()) : 0;
			ordenMedioReporteData.setDia04(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 5:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia05() != null && !ordenMedioReporteData
					.getDia05().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia05()) : 0;
			ordenMedioReporteData.setDia05(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 6:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia06() != null && !ordenMedioReporteData
					.getDia06().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia06()) : 0;
			ordenMedioReporteData.setDia06(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 7:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia07() != null && !ordenMedioReporteData
					.getDia07().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia07()) : 0;
			ordenMedioReporteData.setDia07(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 8:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia08() != null && !ordenMedioReporteData
					.getDia08().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia08()) : 0;
			ordenMedioReporteData.setDia08(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 9:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia09() != null && !ordenMedioReporteData
					.getDia09().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia09()) : 0;
			ordenMedioReporteData.setDia09(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 10:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia10() != null && !ordenMedioReporteData
					.getDia10().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia10()) : 0;
			ordenMedioReporteData.setDia10(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 11:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia11() != null && !ordenMedioReporteData
					.getDia11().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia11()) : 0;
			ordenMedioReporteData.setDia11(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 12:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia12() != null && !ordenMedioReporteData
					.getDia12().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia12()) : 0;
			ordenMedioReporteData.setDia12(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 13:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia13() != null && !ordenMedioReporteData
					.getDia13().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia13()) : 0;
			ordenMedioReporteData.setDia13(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 14:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia14() != null && !ordenMedioReporteData
					.getDia14().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia14()) : 0;
			ordenMedioReporteData.setDia14(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 15:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia15() != null && !ordenMedioReporteData
					.getDia15().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia15()) : 0;
			ordenMedioReporteData.setDia15(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 16:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia16() != null && !ordenMedioReporteData
					.getDia16().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia16()) : 0;
			ordenMedioReporteData.setDia16(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 17:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia17() != null && !ordenMedioReporteData
					.getDia17().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia17()) : 0;
			ordenMedioReporteData.setDia17(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 18:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia18() != null && !ordenMedioReporteData
					.getDia18().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia18()) : 0;
			ordenMedioReporteData.setDia18(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 19:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia19() != null && !ordenMedioReporteData
					.getDia19().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia19()) : 0;
			ordenMedioReporteData.setDia19(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 20:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia20() != null && !ordenMedioReporteData
					.getDia20().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia20()) : 0;
			ordenMedioReporteData.setDia20(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 21:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia21() != null && !ordenMedioReporteData
					.getDia21().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia21()) : 0;
			ordenMedioReporteData.setDia21(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 22:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia22() != null && !ordenMedioReporteData
					.getDia22().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia22()) : 0;
			ordenMedioReporteData.setDia22(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 23:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia23() != null && !ordenMedioReporteData
					.getDia23().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia23()) : 0;
			ordenMedioReporteData.setDia23(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 24:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia24() != null && !ordenMedioReporteData
					.getDia24().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia24()) : 0;
			ordenMedioReporteData.setDia24(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 25:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia25() != null && !ordenMedioReporteData
					.getDia25().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia25()) : 0;
			ordenMedioReporteData.setDia25(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 26:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia26() != null && !ordenMedioReporteData
					.getDia26().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia26()) : 0;
			ordenMedioReporteData.setDia26(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 27:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia27() != null && !ordenMedioReporteData
					.getDia27().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia27()) : 0;
			ordenMedioReporteData.setDia27(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 28:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia28() != null && !ordenMedioReporteData
					.getDia28().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia28()) : 0;
			ordenMedioReporteData.setDia28(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 29:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia29() != null && !ordenMedioReporteData
					.getDia29().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia29()) : 0;
			ordenMedioReporteData.setDia29(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 30:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia30() != null && !ordenMedioReporteData
					.getDia30().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia30()) : 0;
			ordenMedioReporteData.setDia30(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		case 31:
			frecuenciaAcumulada = (ordenMedioReporteData.getDia31() != null && !ordenMedioReporteData
					.getDia31().equals("")) ? Integer
					.parseInt(ordenMedioReporteData.getDia31()) : 0;
			ordenMedioReporteData.setDia31(String.valueOf(frecuenciaAcumulada
					+ frecuencia));
			break;
		default:
			System.out
					.println("No se encontraron coincidencias con ningún día de la semana");
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtMedioOficina().setText("");
		getTxtFecha().setText("");
		getTxtEstado().setText("");
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}