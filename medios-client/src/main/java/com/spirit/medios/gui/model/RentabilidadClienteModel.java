package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OverComisionIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.gui.panel.JPRentabilidadCliente;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.Utilitarios;

public class RentabilidadClienteModel extends JPRentabilidadCliente {
	
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	private JDPopupFinderModel popupFinder;
	private CorporacionIf corporacionIf;
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private DefaultTableModel modelTblRentabilidad;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static final String TIPO_REFERENCIA_PRESUPUESTO = "P";
	private Map<Long, ClienteIf> mapaCliente = new HashMap<Long, ClienteIf>();
	private Map<Long, ClienteOficinaIf> mapaClienteOficina = new HashMap<Long, ClienteOficinaIf>();
	private Map<Long, ProductoIf> productosFeeMap = new HashMap<Long, ProductoIf>();
	private static final String TODOS = "TODOS";
	private String estado = TODOS;
	private String si = "Si"; 
	private String no = "No"; 
	private Object[] options ={si,no};
	
	
	public RentabilidadClienteModel(){
		initKeyListeners();
		anchoColumnasTabla();
		cargarMapas();
		showSaveMode();
		initListeners();
	}
	
	private void initKeyListeners() {
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEditable(false);
		getTxtClienteOficina().setEditable(false);
		getBtnCorporacion().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCorporacion().setToolTipText("Buscar Corporación");
		getBtnCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnCliente().setToolTipText("Buscar Cliente");
		getBtnClienteOficina().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnClienteOficina().setToolTipText("Buscar Cliente Oficina");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");
		
		getBtnLimpiarDatosCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		getBtnLimpiarDatosCliente().setToolTipText("Limpiar datos cliente");
		
		getCmbMesInicio().setLocale(Utilitarios.esLocale);
		getCmbMesFin().setLocale(Utilitarios.esLocale);
		getCmbMesInicio().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMesFin().setFormat(Utilitarios.setFechaMesAnioUppercase());
		getCmbMesInicio().setEditable(false);
		getCmbMesFin().setEditable(false);
		getCmbMesInicio().setShowNoneButton(false);
		getCmbMesFin().setShowNoneButton(false);
	}
	
	private void anchoColumnasTabla() {
		//setSorterTable(getTblRentabilidad());
		getTblRentabilidad().getTableHeader().setReorderingAllowed(false);
		getTblRentabilidad().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//getTblRentabilidad().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn anchoColumna = getTblRentabilidad().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(5);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(6);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(7);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(8);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(9);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(10);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(11);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(12);
		anchoColumna.setPreferredWidth(100);
		anchoColumna = getTblRentabilidad().getColumnModel().getColumn(13);
		anchoColumna.setPreferredWidth(100);
				
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblRentabilidad().getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(11).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(12).setCellRenderer(tableCellRenderer);
		getTblRentabilidad().getColumnModel().getColumn(13).setCellRenderer(tableCellRenderer);
		//TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		//getTblRentabilidad().getColumnModel().getColumn(6).setCellRenderer(tableCellRendererCenter);
		
	}
	
	public void cargarMapas() {
		try {
			mapaCliente.clear();
			Collection clientes = SessionServiceLocator
					.getClienteSessionService().findClienteByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesIt = clientes.iterator();
			while (clientesIt.hasNext()) {
				ClienteIf cliente = (ClienteIf) clientesIt.next();
				mapaCliente.put(cliente.getId(), cliente);
			}
			
			mapaClienteOficina.clear();
			Collection clientesOficina = SessionServiceLocator
					.getClienteOficinaSessionService().findClienteOficinaByEmpresaId(Parametros.getIdEmpresa());
			Iterator clientesOficinaIt = clientesOficina.iterator();
			while (clientesOficinaIt.hasNext()) {
				ClienteOficinaIf clienteOficina = (ClienteOficinaIf) clientesOficinaIt
						.next();
				mapaClienteOficina.put(clienteOficina.getId(), clienteOficina);
			}
			//productos FEE
			Collection tiposProductoFee = SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByCodigo("FEE");
			Iterator tiposProductoFeeIt = tiposProductoFee.iterator();
			while(tiposProductoFeeIt.hasNext()){
				TipoProductoIf tipoProductoFee = (TipoProductoIf)tiposProductoFeeIt.next();
				
				Collection genericosFee = SessionServiceLocator.getGenericoSessionService().findGenericoByTipoproductoId(tipoProductoFee.getId());
				Iterator genericosFeeIt = genericosFee.iterator();
				while(genericosFeeIt.hasNext()){
					GenericoIf genericoFee = (GenericoIf)genericosFeeIt.next();
					
					Collection productosFee = SessionServiceLocator.getProductoSessionService().findProductoByGenericoId(genericoFee.getId());
					Iterator productosFeeIt = productosFee.iterator();
					while(productosFeeIt.hasNext()){
						ProductoIf productoFee = (ProductoIf)productosFeeIt.next();
						
						productosFeeMap.put(productoFee.getId(), productoFee);
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initListeners(){
		getBtnCorporacion().addActionListener(oActionListenerBtnCorporacion);
		getBtnCliente().addActionListener(oActionListenerBtnCliente);
		getBtnClienteOficina().addActionListener(oActionListenerBtnOficina);
		
		getBtnLimpiarDatosCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionIf = null;
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtCorporacion().setText("");
				getTxtCliente().setText("");
				getTxtClienteOficina().setText("");
				cleanTable();
			}
		});
		
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(validateFields()){
					cleanTable();
					cargarTabla();
					SpiritAlert.createAlert("Consulta lista.", SpiritAlert.INFORMATION);
				}
			}
		});
		
		getCmbEstadoPresupuesto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbEstadoPresupuesto().getSelectedItem() != null){
					if(getCmbEstadoPresupuesto().getSelectedItem().equals("COTIZADO")){
						estado = "COTIZADO";
						getCbEstadosAprobadosFacturados().setEnabled(false);
						getCbEstadosAprobadosFacturados().setSelected(false);
					}else if(getCmbEstadoPresupuesto().getSelectedItem().equals("APROBADO")){
						estado = "APROBADO";
						getCbEstadosAprobadosFacturados().setEnabled(false);
						getCbEstadosAprobadosFacturados().setSelected(false);
					}else if(getCmbEstadoPresupuesto().getSelectedItem().equals("FACTURADO")){
						estado = "FACTURADO";
						getCbEstadosAprobadosFacturados().setEnabled(false);
						getCbEstadosAprobadosFacturados().setSelected(false);
					}else if(getCmbEstadoPresupuesto().getSelectedItem().equals("PREPAGADO")){
						estado = "PREPAGADO";
						getCbEstadosAprobadosFacturados().setEnabled(false);
						getCbEstadosAprobadosFacturados().setSelected(false);
					}else{
						estado = TODOS;
						getCbEstadosAprobadosFacturados().setEnabled(true);
					}
				}
				else{
					estado = TODOS;
				}
			}
		});
	}
	
	public void cargarTabla(){
		try {
			Map<Long, PresupuestoIf> presupuestosFee = new HashMap<Long, PresupuestoIf>();
			Map<Long, PresupuestoIf> presupuestosSinOrden = new HashMap<Long, PresupuestoIf>();
			Map<Integer, Double> presupuestosFeeMesValores = new HashMap<Integer, Double>();
			Map<Long, PresupuestoIf> presupuestosPresupuesto = new HashMap<Long, PresupuestoIf>();
			Map<Integer, Double> presupuestosPresupuestoMesValores = new HashMap<Integer, Double>();			
			Map<Long, PlanMedioIf> presupuestosPlanMedios = new HashMap<Long, PlanMedioIf>();		
						
			ArrayList<ClienteOficinaIf> clienteOficinas = new ArrayList<ClienteOficinaIf>();
			//si se selecciono una oficina
			if(clienteOficinaIf != null){
				clienteOficinas.add(clienteOficinaIf);
			}
			//si se selecciono un cliente
			else if(clienteIf != null){
				clienteOficinas.addAll((ArrayList<ClienteOficinaIf>)SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId()));
			}
			//si se selecciono una corporacion
			else /*if(!corporacionIf.getCodigo().equals("SICO"))*/{
				//CLIENTES DE LA CORPORACION
				Collection clientes = SessionServiceLocator.getClienteSessionService().findClienteByCorporacionId(corporacionIf.getId());
				Iterator clientesIt = clientes.iterator();
				while(clientesIt.hasNext()){
					ClienteIf cliente = (ClienteIf)clientesIt.next();
					//CLIENTES OFICINAS POR CADA CLIENTE DE LA CORPORACION
					clienteOficinas.addAll((ArrayList<ClienteOficinaIf>)SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(cliente.getId()));
				}
			}
					
			//POR CADA UNO DE LOS CLIENTES OFICINA ENCONTRADOS BUSCAMOS CADA ITEM DEL INFORME DE RENTABILIDAD
			for(ClienteOficinaIf clienteOficina : clienteOficinas){
				
				//si quiere ver subtotales por cada cliente oficina debo resetear las variables por cada oficina
				if(getCbDesglosarClienteOficina().isSelected()){
					presupuestosFee = new HashMap<Long, PresupuestoIf>();
					presupuestosSinOrden = new HashMap<Long, PresupuestoIf>();
					presupuestosFeeMesValores = new HashMap<Integer, Double>();
					presupuestosPresupuesto = new HashMap<Long, PresupuestoIf>();
					presupuestosPresupuestoMesValores = new HashMap<Integer, Double>();			
					presupuestosPlanMedios = new HashMap<Long, PlanMedioIf>();
				}
				
				//PRESUPUESTOS EN EL RANGO DE MESES SELECCIONADOS
				java.sql.Date fechaInicioTemp = new java.sql.Date(getCmbMesInicio().getDate().getTime());
				java.sql.Date fechaFinTemp = new java.sql.Date(getCmbMesFin().getDate().getTime());
				java.sql.Date fechaInicio = new java.sql.Date(fechaInicioTemp.getYear(), fechaInicioTemp.getMonth(), 1);
				java.sql.Date fechaFin = new java.sql.Date(fechaFinTemp.getYear(), fechaFinTemp.getMonth(), Utilitarios.getLastDayOfMonth(fechaFinTemp.getMonth(), fechaFinTemp.getYear()));
				
				Map mapaPresupuestoDetalle = new LinkedHashMap();			
				mapaPresupuestoDetalle.put("clienteOficinaId", clienteOficina.getId());			
								
				Timestamp timeInicio = Utilitarios.resetTimestampStartDate(new Timestamp(fechaInicio.getTime()));
				Timestamp timeFin =  Utilitarios.resetTimestampEndDate(new Timestamp(fechaFin.getTime()));
				
				boolean fechaAprobacion = false;
				String agruparBy = "";
				String tipoProveedor = TODOS;
				boolean aprobadosFacturados = false;
				if(getCbEstadosAprobadosFacturados().isSelected()){
					aprobadosFacturados = true;
				}
				boolean buscarPresupuestosPrepago = false;
				boolean noBuscarOrdenesEmitidas = false;
				
				//PRESUPUESTOS EN EL RANGO DE MESES SELECCIONADOS
				presupuestoFeeVentasCompras(mapaPresupuestoDetalle, timeInicio, timeFin, fechaAprobacion, 
						agruparBy, tipoProveedor, aprobadosFacturados, buscarPresupuestosPrepago, 
						noBuscarOrdenesEmitidas, presupuestosFee, presupuestosFeeMesValores, presupuestosPresupuesto, 
						presupuestosPresupuestoMesValores, clienteOficina.getId(), presupuestosSinOrden);
				
				
				//PLANES DE MEDIOS EN EL RANGO DE MESES SELECCIONADOS				
				pautasVentasCompras(presupuestosPresupuestoMesValores,
						presupuestosPlanMedios, clienteOficina, timeInicio,
						timeFin, fechaAprobacion, aprobadosFacturados,
						buscarPresupuestosPrepago, noBuscarOrdenesEmitidas);
				
				
				//OVER COMISION
				/*double ganancia = 0D;
								
				Collection overComisiones = SessionServiceLocator.getOverComisionSessionService().findOverComisionByClienteOficinaAndByFechaInicioFechaFin(clienteOficina.getId(), timeInicio, timeFin);
				Iterator overComisionesIt = overComisiones.iterator();
				while(overComisionesIt.hasNext()){
					OverComisionIf overComisionIf = (OverComisionIf)overComisionesIt.next();
					
				}				
				
				//MES
				int mes = planMedio.getFechaInicio().getMonth();
				
				presupuestosPlanMedios.put(planMedio.getId(), planMedio);
				
				if(presupuestosPresupuestoMesValores.get(mes) == null){
					presupuestosPresupuestoMesValores.put(mes, ganancia);
				}else{
					double gananciaTemp = presupuestosPresupuestoMesValores.get(mes);
					double gananciaAcumulada = gananciaTemp + ganancia;
					presupuestosPresupuestoMesValores.put(mes, gananciaAcumulada);
				}		*/	
				
				
				
				//PRESENTACION DE DATOS SI SE HACE POR CADA CLIENTE OFICINA
				//si se quiere ver los totales desglosados entonces la presentación de información se hace ahora pora cada oficina
				if(getCbDesglosarClienteOficina().isSelected()){
					//si es escoje SIN CORPORACION saldran muchos "clientes" con valor 0 entonces filtro solo los que tienen valor
					if(!corporacionIf.getCodigo().equals("SICO") 
							|| (corporacionIf.getCodigo().equals("SICO") 
									&& (!presupuestosFee.isEmpty() 
											|| !presupuestosSinOrden.isEmpty() 
											|| !presupuestosPresupuesto.isEmpty() 
											|| !presupuestosPlanMedios.isEmpty()))){
						
						//MODEL DE LA TABLA DE RENTABILIDAD QUE VAMOS A LLENAR
						modelTblRentabilidad =  (DefaultTableModel) getTblRentabilidad().getModel();
						
						//LLENO LA FILA FEE
						Vector<String> filaFee = filaFee(presupuestosFeeMesValores, clienteOficina.getDescripcion());
						//AGREGO FILA FEE AL MODEL DE LA TABLA
						modelTblRentabilidad.addRow(filaFee);
						
						//LLENO LA FILA VENTAS - COMPRAS
						Vector<String> filaVentasCompras = filaVentasCompras(presupuestosPresupuestoMesValores);
						//AGREGO FILA VENTAS - COMPRAS AL MODEL DE LA TABLA
						modelTblRentabilidad.addRow(filaVentasCompras);
						
						//LLENO LA FILA OVER COMISION
						Vector<String> filaOverComision = new Vector<String>();
						filaOverComision.add("(+) OVER COMISION: ");
						//AGREGO FILA OVER COMISION AL MODEL DE LA TABLA
						modelTblRentabilidad.addRow(filaOverComision);
						
						//LLENO LA FILA NOTAS DE CREDITO
						Vector<String> filaNotasCredito = new Vector<String>();
						filaNotasCredito.add("(+) NOTAS DE CREDITO: ");
						//AGREGO FILA NOTAS DE CREDITO AL MODEL DE LA TABLA
						modelTblRentabilidad.addRow(filaNotasCredito);
					}					
				}
			}
			
			//PRESENTACION DE DATOS SI SE HACE AGRUPADOS POR CLIENTE O POR CORPORACION
			//si no se quiere ver los totales desglosados la presentación de información se hace ahora al final
			if(!getCbDesglosarClienteOficina().isSelected()){
				//MODEL DE LA TABLA DE RENTABILIDAD QUE VAMOS A LLENAR
				modelTblRentabilidad =  (DefaultTableModel) getTblRentabilidad().getModel();
				
				//LLENO LA FILA FEE
				Vector<String> filaFee = filaFee(presupuestosFeeMesValores, "");
				//AGREGO FILA FEE AL MODEL DE LA TABLA
				modelTblRentabilidad.addRow(filaFee);
				
				//LLENO LA FILA VENTAS - COMPRAS
				Vector<String> filaVentasCompras = filaVentasCompras(presupuestosPresupuestoMesValores);
				//AGREGO FILA VENTAS - COMPRAS AL MODEL DE LA TABLA
				modelTblRentabilidad.addRow(filaVentasCompras);
				
				//LLENO LA FILA OVER COMISION
				Vector<String> filaOverComision = new Vector<String>();
				filaOverComision.add("(+) OVER COMISION: ");
				//AGREGO FILA OVER COMISION AL MODEL DE LA TABLA
				modelTblRentabilidad.addRow(filaOverComision);
				
				//LLENO LA FILA NOTAS DE CREDITO
				Vector<String> filaNotasCredito = new Vector<String>();
				filaNotasCredito.add("(+) NOTAS DE CREDITO: ");
				//AGREGO FILA NOTAS DE CREDITO AL MODEL DE LA TABLA
				modelTblRentabilidad.addRow(filaNotasCredito);
			}		
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private Vector<String> filaVentasCompras(
			Map<Integer, Double> presupuestosPresupuestoMesValores) {
		Vector<String> filaVentasCompras = new Vector<String>();
		filaVentasCompras.add("(+) VENTAS - COMPRAS: ");
		double totalVentasCompras = 0D;
		for(int i=0; i<12; i++){				
			if(presupuestosPresupuestoMesValores.get(i) != null){
				filaVentasCompras.add(formatoDecimal.format(presupuestosPresupuestoMesValores.get(i)));
				totalVentasCompras = totalVentasCompras + presupuestosPresupuestoMesValores.get(i);
			}else{
				filaVentasCompras.add(formatoDecimal.format(new BigDecimal(0)));
			}
		}
		filaVentasCompras.add(formatoDecimal.format(totalVentasCompras));
		return filaVentasCompras;
	}

	private Vector<String> filaFee(
			Map<Integer, Double> presupuestosFeeMesValores, String descripcion) {
		
		Vector<String> filaFee = new Vector<String>();
		
		if(!descripcion.equals("")){
			filaFee.add(descripcion + "\n (+) FEE: ");
		}else{
			filaFee.add("(+) FEE: ");
		}
		
		double totalFee = 0D;
		for(int i=0; i<12; i++){				
			if(presupuestosFeeMesValores.get(i) != null){
				filaFee.add(formatoDecimal.format(presupuestosFeeMesValores.get(i)));
				totalFee = totalFee + presupuestosFeeMesValores.get(i);
			}else{
				filaFee.add(formatoDecimal.format(new BigDecimal(0)));
			}
		}
		filaFee.add(formatoDecimal.format(totalFee));
		return filaFee;
	}

	private void pautasVentasCompras(Map<Integer, Double> presupuestosPresupuestoMesValores, Map<Long, PlanMedioIf> presupuestosPlanMedios,	ClienteOficinaIf clienteOficina, 
			Timestamp timeInicio, Timestamp timeFin, boolean fechaAprobacion, boolean aprobadosFacturados, boolean buscarPresupuestosPrepago, boolean noBuscarOrdenesEmitidas) throws GenericBusinessException {
		
		//PARA VENTAS - COMPRAS
					
		//ORDENES MEDIO DETALLE
		Map<String,Long> mapaOrden = new LinkedHashMap<String, Long>();				
		if (clienteOficina != null) 
			mapaOrden.put("clienteOficinaId", clienteOficina.getId());				
		
		Map mapaGeneral = new LinkedHashMap();
		
		//Pauta B = Pauta Regular Auspicio			
		String tipoPauta = "B";
		
		ArrayList<Object[]> ordenesMedioDetalle = (ArrayList)SessionServiceLocator.getOrdenMedioSessionService().
								findOrdenMedioDetalleByQueryAndQueryGeneralByProductoAndByFechas(mapaOrden,mapaGeneral,tipoPauta,timeInicio,timeFin, fechaAprobacion, Parametros.getIdEmpresa(), estado, aprobadosFacturados, "", noBuscarOrdenesEmitidas, buscarPresupuestosPrepago);
		
		//ordenesMedioDetalle.clear();
		
		//ORDENES DE MEDIO DETALLE
		Map<Long,Vector<Object[]>> mapaPlanMedioOrdenesMedioDetalleVector = new HashMap<Long,Vector<Object[]>>(); 
		Vector<Object[]> ordenesMedioDetalleDelPlan = new Vector<Object[]>();
		Map<Long,PlanMedioIf> mapaPlanMedio = new HashMap<Long,PlanMedioIf>();
		
		Iterator ordenesMedioDetalleIt = ordenesMedioDetalle.iterator();
		
		//Agrupo en un mapa los planes con su vector de ordenes medio detalle
		while(ordenesMedioDetalleIt.hasNext()){
			Object[] ordenesMedioDetalleObj = (Object[])ordenesMedioDetalleIt.next();
			OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenesMedioDetalleObj[1];
			OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenesMedioDetalleObj[0];
			PlanMedioIf planMedio = (PlanMedioIf)ordenesMedioDetalleObj[3];
			mapaPlanMedio.put(planMedio.getId(), planMedio);
			
			if(mapaPlanMedioOrdenesMedioDetalleVector.get(planMedio.getId()) == null){
				ordenesMedioDetalleDelPlan = new Vector<Object[]>();
				Object[] ordenDetalleOrdenCabecera = new Object[2];
				ordenDetalleOrdenCabecera[0] = ordenMedioDetalle;
				ordenDetalleOrdenCabecera[1] = ordenMedio;
				ordenesMedioDetalleDelPlan.add(ordenDetalleOrdenCabecera);
				mapaPlanMedioOrdenesMedioDetalleVector.put(planMedio.getId(), ordenesMedioDetalleDelPlan);
			}else{
				ordenesMedioDetalleDelPlan = mapaPlanMedioOrdenesMedioDetalleVector.get(planMedio.getId());
				Object[] ordenDetalleOrdenCabecera = new Object[2];
				ordenDetalleOrdenCabecera[0] = ordenMedioDetalle;
				ordenDetalleOrdenCabecera[1] = ordenMedio;
				ordenesMedioDetalleDelPlan.add(ordenDetalleOrdenCabecera);
				mapaPlanMedioOrdenesMedioDetalleVector.put(planMedio.getId(), ordenesMedioDetalleDelPlan);
			}				
		}
		
		Iterator mapaPlanMedioOrdenesMedioDetalleVectorIt =  mapaPlanMedioOrdenesMedioDetalleVector.keySet().iterator();
		while(mapaPlanMedioOrdenesMedioDetalleVectorIt.hasNext()){
			Long planMedioId = (Long)mapaPlanMedioOrdenesMedioDetalleVectorIt.next();
			PlanMedioIf planMedio = mapaPlanMedio.get(planMedioId);
			Vector<Object[]> ordenesMedioDetallePlan = mapaPlanMedioOrdenesMedioDetalleVector.get(planMedioId);
			
			//VALORES PAUTA (VENTA)
			double subtotalPlanMedio = 0D;
			double porcentajeDescuentoVentaPlan = 0D; 
			double porcentajeComisionAgenciaPlan = 0D; 
			for(Object[] ordenMedioDetalleOrdenMedioCabecera : ordenesMedioDetallePlan){
				OrdenMedioDetalleIf ordenMedioDetalle = (OrdenMedioDetalleIf)ordenMedioDetalleOrdenMedioCabecera[0];
				OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioDetalleOrdenMedioCabecera[1];
				
				subtotalPlanMedio = subtotalPlanMedio + ordenMedioDetalle.getValorSubtotal().doubleValue();
				//estos porcentajes son los mismos en todos los detalles
				porcentajeDescuentoVentaPlan = ordenMedio.getPorcentajeDescuentoVenta().doubleValue();
				porcentajeComisionAgenciaPlan = ordenMedio.getPorcentajeComisionAgencia().doubleValue();
			}
			
			//valor	
			double descuentoVentaPlan = subtotalPlanMedio * (porcentajeDescuentoVentaPlan / 100D);
			double comisionAgenciaPlan = (subtotalPlanMedio - descuentoVentaPlan) * (porcentajeComisionAgenciaPlan / 100D);
			double subTotal = subtotalPlanMedio - descuentoVentaPlan + comisionAgenciaPlan;
												
			
			//VALORES ORDENES(COMPRA)
			double valorNeto = 0D;
			
			//ordenes
			Vector<OrdenMedioIf> ordenesDelPlan = new Vector<OrdenMedioIf>();
			
			Collection ordenes = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedio.getId());
			Iterator ordenesIt = ordenes.iterator();
			while(ordenesIt.hasNext()){
				OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesIt.next();
				//si no esta anulada
				if(!ordenMedioIf.getEstado().equals("A")){
					ordenesDelPlan.add(ordenMedioIf);					
				}						
			}
			
			for(OrdenMedioIf ordenDelPlan : ordenesDelPlan){
				double valorBruto = ordenDelPlan.getValorSubtotal().doubleValue();
				double descuento = ordenDelPlan.getValorDescuento().doubleValue();
				valorNeto = valorNeto + valorBruto - descuento;
			}
			
			
			//MES
			int mes = planMedio.getFechaInicio().getMonth();
			
			/*if(mes == 10){
				System.out.println("aqui");
			}*/
			
			presupuestosPlanMedios.put(planMedio.getId(), planMedio);
			
			double ganancia = subTotal - valorNeto;
			
			if(ganancia > 1D){
				System.out.println("aqui");
			}
			
			if(presupuestosPresupuestoMesValores.get(mes) == null){
				presupuestosPresupuestoMesValores.put(mes, ganancia);
			}else{
				double gananciaTemp = presupuestosPresupuestoMesValores.get(mes);
				double gananciaAcumulada = gananciaTemp + ganancia;
				presupuestosPresupuestoMesValores.put(mes, gananciaAcumulada);
			}		
		}
	}
	
	public void presupuestoFeeVentasCompras(Map mapaPresupuestoDetalle, Timestamp timeInicio, Timestamp timeFin, 
			boolean fechaAprobacion, String agruparBy, String tipoProveedor, boolean aprobadosFacturados, 
			boolean buscarPresupuestosPrepago, boolean noBuscarOrdenesEmitidas, Map<Long, PresupuestoIf> presupuestosFee, 
			Map<Integer, Double> presupuestosFeeMesValores, Map<Long, PresupuestoIf> presupuestosPresupuesto, 
			Map<Integer, Double> presupuestosPresupuestoMesValores, Long clienteOficinaId, Map<Long, PresupuestoIf> presupuestosSinOrden){
		
		try {		
			//PARA EL FEE
			Collection presupuestosDetalleFee = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoDetalleByClienteByCodigoProductoProveedorByFechaInicioFechaFinAndByEstado(null, clienteOficinaId, "FEE", timeInicio, timeFin, estado, aprobadosFacturados);
			Iterator presupuestosDetalleFeeIt = presupuestosDetalleFee.iterator();
			while(presupuestosDetalleFeeIt.hasNext()){
				Object[] presupuestosDetalle = (Object[])presupuestosDetalleFeeIt.next();
				PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestosDetalle[0];
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestosDetalle[1];
				
				//si no es reporte, si no se repitirian los valores
				if(presupuestoDetalleIf.getReporte().equals("N")){
					//Valores por cada presupuesto detalle
					double subtotal = presupuestoDetalleIf.getPrecioventa().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
					double porcentajeDescuentoEspecial =  presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue():0D;
					double descuentoEspecial = subtotal * (porcentajeDescuentoEspecial/100D);
					double porcentajeDescuentoVenta =  presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue():0D;
					double descuentoVenta = (subtotal-descuentoEspecial)*(porcentajeDescuentoVenta/100D);
					double porcentajeDescuentosVarios = presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue():0D;
					double descuentosVarios = (subtotal-descuentoEspecial)*(porcentajeDescuentosVarios/100D);
					double porcentajeComisionAgencia = presupuestoIf.getPorcentajeComisionAgencia()!=null?presupuestoIf.getPorcentajeComisionAgencia().doubleValue():0D;
					double comisionAgencia = (subtotal-descuentoEspecial-descuentoVenta-descuentosVarios)*(porcentajeComisionAgencia/100D);
									
					double subTotal = subtotal-descuentoEspecial-descuentoVenta-descuentosVarios+comisionAgencia;
					
					presupuestosFee.put(presupuestoIf.getId(), presupuestoIf);							
					
					int mes = presupuestoIf.getFecha().getMonth();
					
					if(presupuestosFeeMesValores.get(mes) == null){
						presupuestosFeeMesValores.put(mes, subTotal);
					}else{
						double mesSubtotal = presupuestosFeeMesValores.get(mes);
						double gananciaAcumulada = subTotal + mesSubtotal;
						presupuestosFeeMesValores.put(mes, gananciaAcumulada);
					}
				}				
			}
						
			//PARA VENTAS - COMPRAS
			ArrayList presupuestosDetalle = (ArrayList)SessionServiceLocator.getPresupuestoSessionService().
			findPresupuestosDetalleByQueryByProductoAndByFechas(mapaPresupuestoDetalle, timeInicio, timeFin, 
					fechaAprobacion, Parametros.getIdEmpresa(), agruparBy, estado, tipoProveedor, 
					aprobadosFacturados, buscarPresupuestosPrepago, "", noBuscarOrdenesEmitidas);
			
			//Presupuestos detalle
			Map<Long,Vector<Object>> mapaPresupuestoPresupuestoDetalleVector = new HashMap<Long,Vector<Object>>(); 
			Vector<Object> presupuestosDetalleDelPresupuesto = new Vector<Object>();
			Map<Long,PresupuestoIf> mapaPresupuesto = new HashMap<Long,PresupuestoIf>();
		
			//Agrupo en un mapa los planes con su vector de ordenes medio detalle
			Iterator presupuestosDetalleIt = presupuestosDetalle.iterator();
			while(presupuestosDetalleIt.hasNext()){
				Object[] presupuestoDetalle = (Object[])presupuestosDetalleIt.next();
				Long presupuestoId = (Long)presupuestoDetalle[16];
				PresupuestoIf presupuesto = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(presupuestoId);
				mapaPresupuesto.put(presupuesto.getId(), presupuesto);
				
				if(mapaPresupuestoPresupuestoDetalleVector.get(presupuesto.getId()) == null){
					presupuestosDetalleDelPresupuesto = new Vector<Object>();
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}else{
					presupuestosDetalleDelPresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuesto.getId());
					presupuestosDetalleDelPresupuesto.add(presupuestoDetalle);
					mapaPresupuestoPresupuestoDetalleVector.put(presupuesto.getId(), presupuestosDetalleDelPresupuesto);
				}				
			}
		
			Iterator mapaPresupuestoPresupuestoDetalleVectorIt =  mapaPresupuestoPresupuestoDetalleVector.keySet().iterator();
			while(mapaPresupuestoPresupuestoDetalleVectorIt.hasNext()){
				Long presupuestoId = (Long)mapaPresupuestoPresupuestoDetalleVectorIt.next();
				PresupuestoIf presupuesto = mapaPresupuesto.get(presupuestoId);
				Vector<Object> presupuestosDetallePresupuesto = mapaPresupuestoPresupuestoDetalleVector.get(presupuestoId);
				
				Map<Long, OrdenCompraIf> ordenesDelPresupuestoMap = new HashMap<Long, OrdenCompraIf>();
				
				for(int i = 0; i < presupuestosDetallePresupuesto.size(); i++){
					Object[] presupuestoDetalle = (Object[])presupuestosDetallePresupuesto.get(i);
				
					//LLENO EL MAPA DE ORDENES DE COMPRA
					if(presupuestoDetalle[26] != null){
						OrdenCompraIf ordeCompraIf = (OrdenCompraIf)presupuestoDetalle[26];
						ordenesDelPresupuestoMap.put(ordeCompraIf.getId(), ordeCompraIf);
					}
				}										
				
				//VALORES PAUTA (VENTA)
				double subtotal = presupuesto.getValorbruto().doubleValue();
				double descuentoEspecialPresupuesto = presupuesto.getDescuentoEspecial().doubleValue();
				double descuentoVenta = presupuesto.getDescuento().doubleValue();
				double descuentosVariosPresupuesto = presupuesto.getDescuentosVarios().doubleValue();
				double porcentajeComisionAgenciaPresupuesto = presupuesto.getPorcentajeComisionAgencia().doubleValue();				
				double comisionAgenciaPresupuesto = (subtotal-descuentoEspecialPresupuesto-descuentoVenta-descuentosVariosPresupuesto)*(porcentajeComisionAgenciaPresupuesto/100D);
				double subTotal = subtotal-descuentoEspecialPresupuesto-descuentoVenta-descuentosVariosPresupuesto+comisionAgenciaPresupuesto;
								
				//VALORES ORDENES (COMPRA)
				double valorNeto = 0D;
				
				Iterator ordenesDelPresupuestoMapIt = ordenesDelPresupuestoMap.keySet().iterator();
				while(ordenesDelPresupuestoMapIt.hasNext()){
					Long ordenCompraId = (Long)ordenesDelPresupuestoMapIt.next();
					OrdenCompraIf ordenDelPresupuesto = ordenesDelPresupuestoMap.get(ordenCompraId);
					//valor, iva, total
					double valorBruto = ordenDelPresupuesto.getValor().doubleValue();
					double porcentajeDescuentoEspecial = ordenDelPresupuesto.getPorcentajeDescuentoEspecial().doubleValue() / 100D;
					double descuentoEspecial = valorBruto * porcentajeDescuentoEspecial;
					double descuentoAgencia = ordenDelPresupuesto.getDescuentoAgenciaCompra().doubleValue();
					double porcentajeDescuentosVarios = ordenDelPresupuesto.getPorcentajeDescuentosVariosCompra().doubleValue() / 100D;
					double descuentosVarios = (valorBruto - descuentoEspecial) * porcentajeDescuentosVarios;
					valorNeto = valorNeto + valorBruto - descuentoEspecial - descuentoAgencia - descuentosVarios;
				}
				
				//MES
				int mes = presupuesto.getFecha().getMonth();
				
				presupuestosPresupuesto.put(presupuesto.getId(), presupuesto);
				
				double ganancia = subTotal - valorNeto;
				
				if(ganancia > 1D){
					System.out.println("aqui");
				}
				
				if(presupuestosPresupuestoMesValores.get(mes) == null){
					presupuestosPresupuestoMesValores.put(mes, ganancia);
				}else{
					double gananciaTemp = presupuestosPresupuestoMesValores.get(mes);
					double gananciaAcumulada = gananciaTemp + ganancia;
					presupuestosPresupuestoMesValores.put(mes, gananciaAcumulada);
				}
			}
			
			//PARA PRESUPUESTOS DONDE NO SE HA GENERADO ORDENES
			//HAY QUE VALIDAR QUE NO SE REPITAN LOS QUE YA ESTAN EN FEE
			Collection presupuestosDetalleSinOrden = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoDetalleByClienteOficinaByOrdenIdNullByFechaInicioFechaFinAndByEstado(clienteOficinaId, timeInicio, timeFin, estado, aprobadosFacturados);
			Iterator presupuestosDetalleSinOrdenIt = presupuestosDetalleSinOrden.iterator();
			while(presupuestosDetalleSinOrdenIt.hasNext()){
				Object[] presupuestoDetalleSinOrden = (Object[])presupuestosDetalleSinOrdenIt.next();
				PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestoDetalleSinOrden[0];
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf)presupuestoDetalleSinOrden[1];
				
				//VALIDO SI ESTE PRESUPUESTO NO FUE UTILIZADO YA EN LOS FEE PARA NO DUPLICAR VALORES
				if(presupuestosFee.get(presupuestoIf.getId()) == null){

					//si no es reporte, si no se repitirian los valores
					if(presupuestoDetalleIf.getReporte().equals("N")){
						//Valores por cada presupuesto detalle
						double subtotal = presupuestoDetalleIf.getPrecioventa().doubleValue() * presupuestoDetalleIf.getCantidad().doubleValue();
						double porcentajeDescuentoEspecial =  presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentoEspecialVenta().doubleValue():0D;
						double descuentoEspecial = subtotal * (porcentajeDescuentoEspecial/100D);
						double porcentajeDescuentoVenta =  presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentoAgenciaVenta().doubleValue():0D;
						double descuentoVenta = (subtotal-descuentoEspecial)*(porcentajeDescuentoVenta/100D);
						double porcentajeDescuentosVarios = presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta()!=null?presupuestoDetalleIf.getPorcentajeDescuentosVariosVenta().doubleValue():0D;
						double descuentosVarios = (subtotal-descuentoEspecial)*(porcentajeDescuentosVarios/100D);
						double porcentajeComisionAgencia = presupuestoIf.getPorcentajeComisionAgencia()!=null?presupuestoIf.getPorcentajeComisionAgencia().doubleValue():0D;
						double comisionAgencia = (subtotal-descuentoEspecial-descuentoVenta-descuentosVarios)*(porcentajeComisionAgencia/100D);
										
						double subTotal = subtotal-descuentoEspecial-descuentoVenta-descuentosVarios+comisionAgencia;
						
						presupuestosSinOrden.put(presupuestoIf.getId(), presupuestoIf);							
						
						int mes = presupuestoIf.getFecha().getMonth();
						
						if(subTotal > 1D){
							System.out.println("aqui");
						}
						
						if(presupuestosPresupuestoMesValores.get(mes) == null){
							presupuestosPresupuestoMesValores.put(mes, subTotal);
						}else{
							double mesSubtotal = presupuestosPresupuestoMesValores.get(mes);
							double gananciaAcumulada = subTotal + mesSubtotal;
							presupuestosPresupuestoMesValores.put(mes, gananciaAcumulada);
						}
					}		
				}		
			}
			
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	ActionListener oActionListenerBtnCorporacion = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			corporacionCriteria = new CorporacionCriteria("Corporaciones",	Parametros.getIdEmpresa());
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(80);
			anchoColumnas.add(500);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas,	null);
			if (popupFinder.getElementoSeleccionado() != null) {
				corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
				getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				clienteIf = null;
				clienteOficinaIf = null;
				getTxtCliente().setText("");
				getTxtClienteOficina().setText("");
			}
		}
	};

	ActionListener oActionListenerBtnCliente = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			Long idCorporacion = 0L;

			if (corporacionIf != null) {
				idCorporacion = corporacionIf.getId();
			}

			clienteCriteria = new ClienteCriteria("Clientes", idCorporacion, CODIGO_TIPO_CLIENTE);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.add(80);
			anchoColumnas.add(300);
			anchoColumnas.add(300);
			
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas,	null);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
				getTxtCliente().setText(clienteIf.getNombreLegal());

				if (clienteIf.getInformacionAdc() != null && !clienteIf.getInformacionAdc().equals("")) {
					SpiritAlert.createAlert("INFORMACIÓN: \n" + clienteIf.getInformacionAdc(), SpiritAlert.INFORMATION);
				}

				if (corporacionIf == null) {
					try {
						corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
						getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error al setear el nombre de la Corporación", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}

				clienteOficinaIf = null;
				getTxtClienteOficina().setText("");

				try {
					Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
					if (oficinas.size() == 1) {
						clienteOficinaIf = oficinas.iterator().next();
						setClienteOficina();
					}
				} catch (Exception e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente",	SpiritAlert.ERROR);
				}
			}
		}
	};

	ActionListener oActionListenerBtnOficina = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			Long idCorporacion = 0L;
			Long idCliente = 0L;
			String tipoCliente = "CL";
			String tituloVentanaBusqueda = "Oficinas de Clientes";

			if (corporacionIf != null)
				idCorporacion = corporacionIf.getId();

			if (clienteIf != null)
				idCliente = clienteIf.getId();

			clienteOficinaCriteria = new ClienteOficinaCriteria( tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
			Vector<Integer> anchoColumnas = new Vector<Integer>();
			anchoColumnas.addElement(70);
			anchoColumnas.addElement(200);
			anchoColumnas.addElement(80);
			anchoColumnas.addElement(230);
			
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), clienteOficinaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
			
			if (popupFinder.getElementoSeleccionado() != null) {
				clienteOficinaIf = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();

				if (clienteIf == null) {
					try {
						clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
						getTxtCliente().setText(clienteIf.getNombreLegal());

						if (corporacionIf == null) {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getNombre());
						}
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
				setClienteOficina();
			}
		}
	};

	private void setClienteOficina() {
		getTxtClienteOficina().setText(clienteOficinaIf.getCodigo() + " - "	+ clienteOficinaIf.getDescripcion());
	}

	public void clean() {
		getCmbMesInicio().setCalendar(new GregorianCalendar());
		getCmbMesFin().setCalendar(new GregorianCalendar());
		cleanTable();
	}
	
	private void cleanTable() {
		modelTblRentabilidad = (DefaultTableModel) getTblRentabilidad().getModel();
		for(int i= this.getTblRentabilidad().getRowCount();i>0;--i)
			modelTblRentabilidad.removeRow(i-1);
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

	public void report() {
		try {				
			if (getTblRentabilidad().getModel().getRowCount() > 0) {
				//int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte del Timetracker?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				//if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/medios/RPRentabilidadPorCliente.jasper";
										
					HashMap parametrosMap = new HashMap();
					
					if(clienteOficinaIf != null){
						parametrosMap.put("cliente", clienteOficinaIf.getDescripcion());
					}else{
						parametrosMap.put("cliente", "");
					}
					
					CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
					parametrosMap.put("ciudad", ciudad.getNombre());
					String fechaActual = Utilitarios.dateHoraHoy();
					String year = fechaActual.substring(0,4);
					String month = fechaActual.substring(5,7);
					String day = fechaActual.substring(8,10);
					String fechaEmision = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " del " + year;
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", fechaEmision);
					
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
										
					ReportModelImpl.processReport(fileName, parametrosMap, modelTblRentabilidad, true);
				//}
			} else{
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.INFORMATION);
			}
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public boolean validateFields() {
		
		if(corporacionIf == null){
			SpiritAlert.createAlert("Debe seleccionar una Corporación.",SpiritAlert.WARNING);
			getBtnCorporacion().grabFocus();
			return false;
		}
		
		java.sql.Date fechaInicioTemp = new java.sql.Date(getCmbMesInicio().getDate().getTime());
		java.sql.Date fechaFinTemp = new java.sql.Date(getCmbMesFin().getDate().getTime());
		if(fechaInicioTemp.getYear() != fechaFinTemp.getYear()){
			SpiritAlert.createAlert("Las fechas Inicio y Fin deben ser del mismo año.",SpiritAlert.WARNING);
			getCmbMesInicio().grabFocus();
			return false;
		}
		
		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void deleteDetail() {
		// TODO Auto-generated method stub
		
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}

}
