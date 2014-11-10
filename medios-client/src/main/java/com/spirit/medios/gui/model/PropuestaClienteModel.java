package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.medios.entity.OrdenMedioDetalleIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.PresupuestosData;
import com.spirit.medios.entity.PropuestaData;
import com.spirit.medios.entity.PropuestaDetalleData;
import com.spirit.medios.entity.PropuestaDetalleIf;
import com.spirit.medios.entity.PropuestaIf;
import com.spirit.medios.gui.criteria.PropuestaClienteCriteria;
import com.spirit.medios.gui.panel.JPPropuestaCliente;
import com.spirit.medios.handler.EstadoPresupuesto;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class PropuestaClienteModel extends JPPropuestaCliente {

	private static final long serialVersionUID = 3311883101043428012L;
	private JDPopupFinderModel popupFinder;
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria clienteCriteria;
	private ClienteOficinaCriteria clienteOficinaCriteria;
	ArrayList lista;
	PropuestaIf propuesta;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected ClienteOficinaIf clienteOficinaIf;
	protected OrdenTrabajoIf ordenTrabajoIf;
	final JPopupMenu popupMenuPropuestaDetalle = new JPopupMenu();
	JMenuItem itemEliminarPropuestaDetalle;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	Vector PropuestaDetalleColeccion = new Vector();
	private String fecha;
	OrdenTrabajoIf ordenIf;
	
	private static final int MAX_LONGITUD_CODIGO = 8;
	private static final int MAX_LONGITUD_OBSERVACION = 100;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0, 1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0, 1);
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	private static final String NOMBRE_MENU_PROPUESTACLIENTE = "PROPUESTA CLIENTE";
	
	Double valor = 0.00;
	java.util.Date fechaCreacion = new java.util.Date();
	DefaultTableModel modelPropuestaDetalle;
	
	public PropuestaClienteModel() {
		anchoColumnasTabla();
		initKeyListeners();
		this.showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblPropuestaDetalle().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblPropuestaDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(200);
		anchoColumna = getTblPropuestaDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(100);	
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtObservacion().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION));
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
		getTblPropuestaDetalle().getColumnModel().getColumn(2).setCellRenderer(dtcrColumn);
	}
	
	public void report() {
		if (getMode() == SpiritMode.UPDATE_MODE) {
			try {
				DefaultTableModel tblModelReporte = (DefaultTableModel) getTblPropuestaDetalle().getModel();
				String fileName = "jaspers/medios/RPPropuestaCliente.jasper";
				HashMap parametrosMap = new HashMap();
				
				MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_PROPUESTACLIENTE).iterator().next();
				parametrosMap.put("codigoReporte", menu.getCodigo());
				
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario());
				parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
				
				if(fecha != null)
					parametrosMap.put("fecha", propuesta.getFecha().toString());
				else
					parametrosMap.put("fecha", Utilitarios.dateNow());
				
				parametrosMap.put("codigo", propuesta.getCodigo());
				parametrosMap.put("corporacion", corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
				parametrosMap.put("cliente", clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				parametrosMap.put("oficina", clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
				parametrosMap.put("ordenTrabajo", ordenIf.getCodigo() + " - " + ordenIf.getDescripcion());
				parametrosMap.put("observacion", propuesta.getObservacion());
				parametrosMap.put("valor", formatoDecimal.format(propuesta.getValor()));
				
				if(ESTADO_ACTIVO.equals(propuesta.getEstado()))
					parametrosMap.put("estado", NOMBRE_ESTADO_ACTIVO);
				else if(ESTADO_INACTIVO.equals(propuesta.getEstado()))
					parametrosMap.put("estado", NOMBRE_ESTADO_INACTIVO);
				
				ReportModelImpl.processReport(fileName, parametrosMap, tblModelReporte, true);
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			catch (ParseException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void refresh() {
		if(clienteOficinaIf != null)
			cargarComboOrdenTrabajo();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void save() {
		if (validateFields()) {
			try {
				PropuestaIf propuesta = registrarPropuesta();
				SessionServiceLocator.getPropuestaSessionService().procesarPropuesta(propuesta, PropuestaDetalleColeccion);
				SpiritAlert.createAlert("Propuesta guardada con éxito",SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Error al guardar la información",SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (validateFields()) {
			try {
				PropuestaIf propuesta = registrarPropuestaForUpdate();

				deletePropuestaDetalles();
				SessionServiceLocator.getPropuestaSessionService().actualizarPropuesta(propuesta,PropuestaDetalleColeccion);
				SpiritAlert.createAlert("Propuesta actualizada con éxito",SpiritAlert.INFORMATION);

			} catch (GenericBusinessException e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Error al actualizar la Propuesta!",SpiritAlert.ERROR);
				e.printStackTrace();
			}
			this.clean();
			this.showSaveMode();
		}
	}

	public void delete() {
		try {	
			SessionServiceLocator.getPropuestaSessionService().eliminarPropuesta(propuesta.getId());
			SpiritAlert.createAlert("Propuesta eliminada con éxito",SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();

		} catch (GenericBusinessException e) {
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Error al eliminar la Propuesta!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private PropuestaIf registrarPropuesta() throws GenericBusinessException {
		PropuestaData data = new PropuestaData();

		data.setCodigo(this.getTxtCodigo().getText());
		data.setOrdentrabajoId(((OrdenTrabajoIf) this.getCmbOrdenTrabajo().getSelectedItem()).getId());
		data.setUsuarioId(((UsuarioIf) Parametros.getUsuarioIf()).getId());
		data.setFecha(new java.sql.Date(fechaCreacion.getYear(),fechaCreacion.getMonth(),fechaCreacion.getDate()));
		data.setValor(BigDecimal.valueOf(valor));
		data.setObservacion(this.getTxtObservacion().getText());
		data.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));
		
		return data;
	}
	
	private PropuestaIf registrarPropuestaForUpdate() {

		propuesta.setOrdentrabajoId(((OrdenTrabajoIf) this.getCmbOrdenTrabajo().getSelectedItem()).getId());
		propuesta.setValor(BigDecimal.valueOf(valor));		
		propuesta.setObservacion(this.getTxtObservacion().getText());
		propuesta.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));

		return propuesta;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText().toUpperCase());
		else
			aMap.put("codigo", "%");
		
		if (getCmbEstado().getSelectedItem() != null)
			aMap.put("estado", getCmbEstado().getSelectedItem().toString().substring(0, 1));
		
		if (getCmbOrdenTrabajo().getSelectedItem() != null)
			aMap.put("ordentrabajoId", ((OrdenTrabajoIf) getCmbOrdenTrabajo().getSelectedItem()).getId());
		
		return aMap;
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			int tamanoLista = 0;
			Long clienteId = null;
			if (!("".equals(getTxtCorporacion().getText())) && !("".equals(getTxtCliente().getText()))){
				clienteId = clienteIf.getId();
				tamanoLista = SessionServiceLocator.getPropuestaSessionService()
					.findPropuestaByQueryAndByIdClienteSize(mapa,clienteId, Parametros.getIdEmpresa());
			}
			else
				tamanoLista = SessionServiceLocator.getPropuestaSessionService()
					.findPropuestaByQuerySize(mapa, Parametros.getIdEmpresa());	
			if (tamanoLista > 0) {
				PropuestaClienteCriteria propuestaClienteCriteria = new PropuestaClienteCriteria(clienteId);
				propuestaClienteCriteria.setResultListSize(tamanoLista);
				propuestaClienteCriteria.setQueryBuilded(mapa);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										propuestaClienteCriteria,
										JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado() != null )
					getSelectedObject();
			} else {
				SpiritAlert.createAlert("No se encontraron registros",SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información",SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText())
			    && "".equals(this.getTxtCliente().getText())
			    && "".equals(this.getTxtOficina().getText())
			    && "".equals(this.getTxtFecha().getText())
			    && "".equals(this.getTxtObservacion().getText())
			    && (this.getCmbOrdenTrabajo().getSelectedItem() == null)
			    && (this.getCmbEstado().getSelectedItem() == null)) {
				return true;
			} else {
				return false;
			}
	}

	public void clean() {
		
		corporacionIf = null;
		clienteIf = null;
		
		this.cleanTabla();
		this.getTxtCodigo().setText("");
		this.getTxtCorporacion().setText("");
		this.getTxtCliente().setText("");
		this.getTxtOficina().setText("");
		this.getTxtObservacion().setText("");
		this.getTxtFecha().setText("");
		
		if(getMode()==SpiritMode.SAVE_MODE || getMode()==SpiritMode.UPDATE_MODE){
			//mando a limpiar los combos
			this.getCmbOrdenTrabajo().removeAllItems();
			this.getCmbEstado().removeAllItems();
		}
		
		this.getTxtValor().setText("0.0");
		valor = 0.0;
		
		this.getCmbEstado().setSelectedItem(null);
		
		this.getCmbOrdenTrabajo().setSelectedItem(null);
		this.getCmbOrdenTrabajo().removeAllItems();
		this.getCmbOrdenTrabajo().removeActionListener(oActionListenerCargarTabla);
		
		//Quito el mouse listener que tenia en el modo SAVE 
		getTblPropuestaDetalle().removeMouseListener(oMouseAdapterTblPropuestaDetalle);
		itemEliminarPropuestaDetalle.removeActionListener(oActionListenerEliminarPropuestaDetalle);
		popupMenuPropuestaDetalle.remove(itemEliminarPropuestaDetalle);
		
		this.repaint();
	}

	public void showFindMode() {
		this.getTxtCodigo().setBackground(Parametros.findColor);
		this.getCmbEstado().setBackground(Parametros.findColor);
		this.getTxtCliente().setBackground(Parametros.findColor);
		this.getCmbOrdenTrabajo().setBackground(Parametros.findColor);
		this.getTxtCodigo().setEnabled(true);
		this.getTxtCodigo().setEditable(true);
		this.getTxtCorporacion().setEnabled(true);
		this.getTxtCliente().setEnabled(true);
		this.getTxtOficina().setEnabled(true);
		this.getCmbOrdenTrabajo().setEnabled(true);
		this.getCmbEstado().setEnabled(true);
		this.getTxtCodigo().grabFocus();
		
		getTblPropuestaDetalle().removeMouseListener(oMouseAdapterTblPropuestaDetalle);
		itemEliminarPropuestaDetalle.removeActionListener(oActionListenerEliminarPropuestaDetalle);
		popupMenuPropuestaDetalle.remove(itemEliminarPropuestaDetalle);
		
		getTxtCodigo().grabFocus();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtFecha().setText(Utilitarios.fechaAhora());
		getTxtFecha().setEnabled(true);
		getTxtFecha().setEditable(false);
		this.getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		this.getCmbEstado().setBackground(Parametros.saveUpdateColor);
		this.getTxtCliente().setBackground(getBackground());
		this.getCmbOrdenTrabajo().setBackground(Parametros.saveUpdateColor);
		getTxtCodigo().setEnabled(true);
		getTxtValor().setEnabled(true);
		getTxtValor().setEditable(false);
		getTxtValor().setText("0.0");
		getTxtCorporacion().setEnabled(true);
		getTxtCorporacion().setEditable(false);
		getTxtCliente().setEnabled(true);
		getTxtCliente().setEditable(false);
		getTxtOficina().setEnabled(true);
		getTxtOficina().setEditable(false);	
			
		getCmbOrdenTrabajo().setEnabled(false);
		
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getBtnBuscarOficina().setEnabled(true);
		
		itemEliminarPropuestaDetalle = new JMenuItem("Eliminar");
		itemEliminarPropuestaDetalle.addActionListener(oActionListenerEliminarPropuestaDetalle);
		popupMenuPropuestaDetalle.add(itemEliminarPropuestaDetalle);
		getTblPropuestaDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
			
		getCmbEstado().setEnabled(true);
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
		
		getTxtCodigo().grabFocus();	
	}
	
	public void showUpdateMode() {
		this.getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		this.getCmbEstado().setBackground(Parametros.saveUpdateColor);
		this.getTxtCliente().setBackground(getBackground());
		this.getCmbOrdenTrabajo().setBackground(Parametros.saveUpdateColor);
		this.getTxtCodigo().setEnabled(false);
		this.getTxtCorporacion().setEnabled(true);
		this.getTxtCliente().setEnabled(true);
		this.getTxtOficina().setEnabled(true);
		this.getCmbOrdenTrabajo().setEnabled(true);
		this.getCmbEstado().setEnabled(true);
		this.getTxtObservacion().setEnabled(true);
		this.getTxtValor().setEnabled(true);
		this.getTxtValor().setEditable(false);
		
		//Manejo los eventos del Combo de Orden Trabajo
		getCmbOrdenTrabajo().addActionListener(oActionListenerCargarTabla);
		
		itemEliminarPropuestaDetalle = new JMenuItem("Eliminar");
		itemEliminarPropuestaDetalle.addActionListener(oActionListenerEliminarPropuestaDetalle);
		popupMenuPropuestaDetalle.add(itemEliminarPropuestaDetalle);
		
		getTblPropuestaDetalle().addMouseListener(oMouseAdapterTblPropuestaDetalle);
		
		getTxtCodigo().grabFocus();
		//report();
	}
		
	private void initListeners() {
		getBtnLLenarTablaPresupuestos().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					//TABLA PRESUPUESTO
					Collection presupuestoRegistros = SessionServiceLocator.getPresupuestoSessionService().getPresupuestoList();
					Iterator presupuestoRegistrosIt = presupuestoRegistros.iterator();
					while(presupuestoRegistrosIt.hasNext()){
						PresupuestoIf presupuestoIf = (PresupuestoIf)presupuestoRegistrosIt.next();
						
						PresupuestosData presupuestosData = new PresupuestosData();
						presupuestosData.setCodigo(presupuestoIf.getCodigo());
						presupuestosData.setTipo("P");
						presupuestosData.setClienteOficinaId(presupuestoIf.getClienteOficinaId());
						presupuestosData.setConcepto(presupuestoIf.getConcepto());
						//java.sql.Date fecha = new java.sql.Date(presupuestoIf.getFecha().getYear(), presupuestoIf.getFecha().getMonth(), presupuestoIf.getFecha().getDate());
						presupuestosData.setFecha(presupuestoIf.getFecha());
						presupuestosData.setFechaAprobacion(presupuestoIf.getFechaAceptacion());
						
						BigDecimal subtotal = presupuestoIf.getValorbruto();
						presupuestosData.setSubtotal(subtotal);
						
						BigDecimal descuentoEspecial = presupuestoIf.getDescuentoEspecial();
						BigDecimal descuentoVenta = presupuestoIf.getDescuento();
						BigDecimal descuentosVarios = presupuestoIf.getDescuentosVarios();
						BigDecimal descuento = presupuestoIf.getDescuento().add(presupuestoIf.getDescuentoEspecial()).add(presupuestoIf.getDescuentosVarios());
						presupuestosData.setDescuento(descuento);
						
						BigDecimal porcentajeComisionAgencia = presupuestoIf.getPorcentajeComisionAgencia();					
						BigDecimal subtotal2 = subtotal.subtract(descuentoEspecial).subtract(descuentoVenta).subtract(descuentosVarios);					
						BigDecimal comisionAgencia = subtotal2.multiply(porcentajeComisionAgencia.divide(new BigDecimal(100)));
						presupuestosData.setComision(comisionAgencia);
						
						presupuestosData.setIva(presupuestoIf.getIva());
						presupuestosData.setTotal(presupuestoIf.getValor());
						
						//ESTADO
						//F:Facturado, A:Aprobado, R:Prepagado, T:Cotizado, H:Historico
						//F:(F,D); A:A, R:R, T:(T,P,N), H:(C,H)
						if(presupuestoIf.getEstado().equals(EstadoPresupuesto.FACTURADO.getLetra())){
							presupuestosData.setEstado("F");
						}else if(presupuestoIf.getEstado().equals(EstadoPresupuesto.APROBADO.getLetra())){
							presupuestosData.setEstado("A");
						}else if(presupuestoIf.getEstado().equals(EstadoPresupuesto.PREPAGADO.getLetra())){
							presupuestosData.setEstado("R");
						}else if(presupuestoIf.getEstado().equals(EstadoPresupuesto.COTIZADO.getLetra()) || 
								presupuestoIf.getEstado().equals(EstadoPresupuesto.PENDIENTE.getLetra())){
							presupuestosData.setEstado("T");
						}else if(presupuestoIf.getEstado().equals(EstadoPresupuesto.CANCELADO.getLetra())){
							presupuestosData.setEstado("H");
						}
						
						presupuestosData.setPrepago(presupuestoIf.getPrepago());
						presupuestosData.setRevision("01");
						
						SessionServiceLocator.getPresupuestosSessionService().addPresupuestos(presupuestosData);			
					}
					
					//TABLA PLAN MEDIO
					/*Collection planMedioRegistros = SessionServiceLocator.getPlanMedioSessionService().getPlanMedioList();
					Iterator planMedioRegistrosIt = planMedioRegistros.iterator();
					while(planMedioRegistrosIt.hasNext()){
						PlanMedioIf planMedioIf = (PlanMedioIf)planMedioRegistrosIt.next();
						
						PresupuestosData presupuestosData = new PresupuestosData();
						presupuestosData.setCodigo(planMedioIf.getCodigo());
						presupuestosData.setTipo("M");
						
						OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(planMedioIf.getOrdenTrabajoDetalleId());
						OrdenTrabajoIf ordenTrabajoIf = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoDetalleIf.getOrdenId());
						presupuestosData.setClienteOficinaId(ordenTrabajoIf.getClienteoficinaId());
						
						presupuestosData.setConcepto(planMedioIf.getConcepto());
						//java.sql.Date fecha = new java.sql.Date(presupuestoIf.getFecha().getYear(), presupuestoIf.getFecha().getMonth(), presupuestoIf.getFecha().getDate());
						presupuestosData.setFecha(planMedioIf.getFechaInicio());
						presupuestosData.setFechaAprobacion(planMedioIf.getFechaAprobacion());
						
						//valor, iva, total
						BigDecimal subtotalPlanMedio = new BigDecimal(0);
						BigDecimal ivaSubTotal = new BigDecimal(0);
						BigDecimal porcentajeDescuentoVentaPlan = new BigDecimal(0); 
						BigDecimal porcentajeComisionAgenciaPlan = new BigDecimal(0); 
						
						Collection ordenesMedio = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByPlanMedioId(planMedioIf.getId());
						Iterator ordenesMedioIt = ordenesMedio.iterator();
						while(ordenesMedioIt.hasNext()){
							OrdenMedioIf ordenMedioIf = (OrdenMedioIf)ordenesMedioIt.next();
							//if(!ordenMedioIf.getEstado().equals("A")){
								Collection ordenesMedioDetalle = SessionServiceLocator.getOrdenMedioDetalleSessionService().findOrdenMedioDetalleByOrdenMedioId(ordenMedioIf.getId());
								Iterator ordenesMedioDetalleIt = ordenesMedioDetalle.iterator();
								while(ordenesMedioDetalleIt.hasNext()){
									OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf)ordenesMedioDetalleIt.next();
									
									subtotalPlanMedio = subtotalPlanMedio.add(ordenMedioDetalleIf.getValorSubtotal());
									//estos porcentajes son los mismos en todos los detalles
									porcentajeDescuentoVentaPlan = ordenMedioIf.getPorcentajeDescuentoVenta();
									porcentajeComisionAgenciaPlan = ordenMedioIf.getPorcentajeComisionAgencia();
								}
							//}
						}
						
						//valor	
						BigDecimal descuentoVentaPlan = subtotalPlanMedio.multiply(porcentajeDescuentoVentaPlan.divide(new BigDecimal(100)));
						BigDecimal comisionAgenciaPlan = (subtotalPlanMedio.subtract(descuentoVentaPlan)).multiply(porcentajeComisionAgenciaPlan.divide(new BigDecimal(100)));
						BigDecimal subTotal = subtotalPlanMedio.subtract(descuentoVentaPlan).add(comisionAgenciaPlan);
						
						//iva
						double ivaActual = Parametros.getIVA() / 100D; 
						ivaSubTotal = subTotal.multiply(BigDecimal.valueOf(ivaActual));
						
						//total
						BigDecimal totalTotal = subTotal.add(ivaSubTotal);
						
						presupuestosData.setSubtotal(subTotal);						
						presupuestosData.setDescuento(descuentoVentaPlan);						
						presupuestosData.setComision(comisionAgenciaPlan);						
						presupuestosData.setIva(ivaSubTotal);
						presupuestosData.setTotal(totalTotal);
						
						//ESTADO
						//F:Facturado, A:Aprobado, R:Prepagado, T:Cotizado, H:Historico
						//F:(F,D); A:A, R:R, T:(T,P,N), H:(C,H)
						if(planMedioIf.getEstado().equals("F") || planMedioIf.getEstado().equals("D")){
							presupuestosData.setEstado("F");
						}else if(planMedioIf.getEstado().equals("A")){
							presupuestosData.setEstado("A");
						}else if(planMedioIf.getEstado().equals("R")){
							presupuestosData.setEstado("R");
						}else if(planMedioIf.getEstado().equals("P") || planMedioIf.getEstado().equals("N")){
							presupuestosData.setEstado("T");
						}else if(planMedioIf.getEstado().equals("H")){
							presupuestosData.setEstado("H");
						}
						
						presupuestosData.setPrepago(planMedioIf.getPrepago());
						presupuestosData.setRevision(planMedioIf.getRevision());
						
						SessionServiceLocator.getPresupuestosSessionService().addPresupuestos(presupuestosData);			
					}*/
					
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
		});
		
		getTblPropuestaDetalle().addMouseListener(oMouseAdapterTblPropuestaDetalle);
		//Manejo los eventos de Buscar Corporación
		int numeroListenersCorporacion = getBtnBuscarCorporacion().getActionListeners().length;
		if(numeroListenersCorporacion == 0){
			getBtnBuscarCorporacion().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(500);	
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if ( popupFinder.getElementoSeleccionado() != null ){
						corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
						getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
						getTxtCliente().setText("");
						getTxtOficina().setText("");
						clienteIf = null;
						clienteOficinaIf = null;
						getCmbOrdenTrabajo().setEnabled(false);
						getCmbOrdenTrabajo().removeAllItems();
						getCmbOrdenTrabajo().removeActionListener(oActionListenerCargarTabla);
						cleanTabla();
						getTxtValor().setText("");
						valor = 0.0;
					}
				}
			});
		}
		
		//Manejo los eventos de Buscar Cliente
		int numeroListenersCliente = getBtnBuscarCliente().getActionListeners().length;
		if(numeroListenersCliente == 0){
			getBtnBuscarCliente().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					Long idCorporacion = 0L;
					
					if (corporacionIf != null)
						idCorporacion = corporacionIf.getId();
					
					clienteCriteria = new ClienteCriteria("Clientes",idCorporacion,CODIGO_TIPO_CLIENTE);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(80);
					anchoColumnas.add(300);
					anchoColumnas.add(300);
					popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),	clienteCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
					if ( popupFinder.getElementoSeleccionado() != null ){
						clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
						getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
						if (corporacionIf == null) {
							try {
								corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
								getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
							} catch (GenericBusinessException e) {
								SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
								e.printStackTrace();
							}
						}
						
						//Seteo los text fields en blanco
						getTxtOficina().setText("");
						clienteOficinaIf = null;
	   					//Deshablilito el combo de Orden Trabajo y Orden Trabajo Detalle
						getCmbOrdenTrabajo().setEnabled(false);
						//Remuevo Los Items de Orden Trabajo y de Orden Trabajo Detalle
						getCmbOrdenTrabajo().removeAllItems();
						
						try {
							Collection<ClienteOficinaIf> oficinas = SessionServiceLocator.getClienteOficinaSessionService().findClienteOficinaByClienteId(clienteIf.getId());
							if ( oficinas.size() == 1 ){
								clienteOficinaIf = oficinas.iterator().next();
								setClienteOficina();
							}
						} catch (Exception e) {
							e.printStackTrace();
							SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente", SpiritAlert.ERROR);
						}
						
					}
				}
			});
		}
		
		//Manejo los eventos de Buscar Cliente Oficina
		int numeroListenersOficina = getBtnBuscarOficina().getActionListeners().length;
		if(numeroListenersOficina == 0){
			getBtnBuscarOficina().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evento) {
					Long idCorporacion = 0L;
					Long idCliente = 0L;
					String tipoCliente = CODIGO_TIPO_CLIENTE;
					String tituloVentanaBusqueda = "Oficinas de Clientes";
										
					if (corporacionIf != null)
						idCorporacion = corporacionIf.getId();
						
					if (clienteIf != null)
						idCliente = clienteIf.getId();
					
					clienteOficinaCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
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
								getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
								
								if (corporacionIf == null) {
									corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
									getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
								}
							} catch (GenericBusinessException e) {
								SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
								e.printStackTrace();
							}
						}
						setClienteOficina();
					}
				}				
			});
		}
	}
	
	private void setClienteOficina() {
		getTxtOficina().setText(clienteOficinaIf.getCodigo() 
				+ " - " + clienteOficinaIf.getDescripcion());
		
		//Hablilito el combo de Orden Trabajo
		getCmbOrdenTrabajo().setEnabled(true);
		//Remuevo Los Items de Orden Trabajo y de Orden Trabajo Detalle
		getCmbOrdenTrabajo().removeAllItems();
		
		//Mando a Cargar el combo de Orden Trabajo por por ClienteOficina
		cargarComboOrdenTrabajo();
		//mando a llamar al listener del combo de orden
		getCmbOrdenTrabajo().addActionListener(oActionListenerCargarTabla);
	}

	private void cargarComboOrdenTrabajo() {
		try {
			List ordenTrabajo = (List) SessionServiceLocator.getOrdenTrabajoSessionService().
			findOrdenTrabajoByClienteOficinaIdAndEstadoAndEmpresaId(
					clienteOficinaIf.getId(), Parametros.getIdEmpresa());
			refreshCombo(getCmbOrdenTrabajo(), ordenTrabajo);
			
			if(getCmbOrdenTrabajo().getItemCount()==0){
				getCmbOrdenTrabajo().setEnabled(false);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void getSelectedObject(){	
		setUpdateMode();
		
		propuesta = (PropuestaIf) popupFinder.getElementoSeleccionado();
					
		try {
			
			OrdenTrabajoIf ordenT = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(propuesta.getOrdentrabajoId());
			
			clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenT.getClienteoficinaId());
			clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
			corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
			
			getTxtCodigo().setText(propuesta.getCodigo());
			getTxtOficina().setText(clienteOficinaIf.getCodigo() + " - " + clienteOficinaIf.getDescripcion());
			getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
			getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
			
			getCmbOrdenTrabajo().removeAllItems();
			
			//remuevo el listener para que no cargue dos veces al inicio los detalles
			getCmbOrdenTrabajo().removeActionListener(oActionListenerCargarTabla);
			
				Collection ordentrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().findOrdenTrabajoByClienteoficinaId(clienteOficinaIf.getId());
				Iterator itcampana = ordentrabajo.iterator();
				while(itcampana.hasNext()){
					ordenIf = (OrdenTrabajoIf) itcampana.next();
					getCmbOrdenTrabajo().addItem(ordenIf);
					if(ordenIf.getId().compareTo(ordenT.getId())==0)
						getCmbOrdenTrabajo().setSelectedItem(ordenIf);
					
				}
				
				getCmbOrdenTrabajo().repaint();

				getTxtFecha().setText(
						Utilitarios.getFechaUppercase(propuesta.getFecha())
						);
				
				
				if(ESTADO_ACTIVO.equals(propuesta.getEstado()))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else if(ESTADO_INACTIVO.equals(propuesta.getEstado()))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				
				getTxtObservacion().setText(propuesta.getObservacion());
				getTxtValor().setText(formatoDecimal.format(propuesta.getValor()));
				
				valor = propuesta.getValor().doubleValue();
								
			} catch (GenericBusinessException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			}
			
			try {
				modelPropuestaDetalle = (DefaultTableModel) getTblPropuestaDetalle().getModel();
				
				Collection listaPlantillaDetalle = SessionServiceLocator.getPropuestaDetalleSessionService().findPropuestaDetalleByPropuestaId(propuesta.getId());
				Iterator it = listaPlantillaDetalle.iterator();
				
				while(it.hasNext()){
					
					PropuestaDetalleIf propuestaDetalleIf = (PropuestaDetalleIf) it.next();
					
					Vector<String> filaPlantillaDetalle = new Vector<String>();
					
					PropuestaDetalleColeccion.add(propuestaDetalleIf);
					
					///validación para presupuesto
					if(propuestaDetalleIf.getPresupuestoId()!=null){
						
						PresupuestoIf presupuestoIf = SessionServiceLocator.getPresupuestoSessionService().getPresupuesto(propuestaDetalleIf.getPresupuestoId());
						
						filaPlantillaDetalle.add("PRESUPUESTO"+" - "+presupuestoIf.getCodigo());
						filaPlantillaDetalle.add(presupuestoIf.getConcepto());
						filaPlantillaDetalle.add(formatoDecimal.format(presupuestoIf.getValor()));
					}
					
					//validación para plan medios
					if(propuestaDetalleIf.getPlanmedioId()!=null){
						
						PlanMedioIf planMedioIf = SessionServiceLocator.getPlanMedioSessionService().getPlanMedio(propuestaDetalleIf.getPlanmedioId());
						filaPlantillaDetalle.add("PLAN MEDIO"+" - "+planMedioIf.getCodigo());
						filaPlantillaDetalle.add(planMedioIf.getConcepto());
						//por le momento se va ahcer referencia al valor de la tarifa, hasta sabeer que valor va ahi
						if(planMedioIf.getValorDescuento() != null){
							filaPlantillaDetalle.add(formatoDecimal.format(planMedioIf.getValorDescuento()));
						}
						else{
							filaPlantillaDetalle.add("0.0");
						}
					}
					
					modelPropuestaDetalle.addRow(filaPlantillaDetalle);
					
				}
			
			} catch (GenericBusinessException e1) {
				e1.printStackTrace();
			}
			this.showUpdateMode();
	}
	
	public void cargarDetalles(){
		
		boolean detailExists = false;

		//para presupuesto
		try {
			
			//agrego los detalles de presupuesto a la propuesta
			
			modelPropuestaDetalle = (DefaultTableModel) getTblPropuestaDetalle().getModel();
			
			Collection listaPlantillaDetalle = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByOrdenTrabajoId(ordenTrabajoIf.getId());
			Iterator it = listaPlantillaDetalle.iterator();
			
			if(listaPlantillaDetalle.size()>0){
				while(it.hasNext()){
					
					
					PresupuestoIf presupuestoIf = (PresupuestoIf) it.next();
					
					boolean isExistePresupuesto = false;
					int cont = 0;
					//Si la coleccion tiene algun elemento
					if(PropuestaDetalleColeccion.size()!=0){
						//Recorro la coleccion de producto cliente
		    			for(int i=0;i<PropuestaDetalleColeccion.size();i++){
		    				
		    				PropuestaDetalleIf propuestaIf = (PropuestaDetalleIf) PropuestaDetalleColeccion.get(i);
		    				
		    				//Si el presupuesto cargado ya esta en lista, entons muestro un mensaje de error
		    				if(propuestaIf.getPresupuestoId()!=null){
		    					if(propuestaIf.getPresupuestoId().equals(presupuestoIf.getId())){
			    					cont ++;
			    					isExistePresupuesto = true;
			    					if (cont==1)
			    						SpiritAlert.createAlert("Uno de los Presupuestos o Planes de Medios de esta orden ya se encuentra agregada !!!", SpiritAlert.INFORMATION);
			    					break;
			    				}
		    				}
		    				
		    			}
		    		}
					//Si el presupuesto no existe lo inserto en la lista
					if(isExistePresupuesto==false){
						
						Vector<String> filaPlantillaDetalle = new Vector<String>();
						
						filaPlantillaDetalle.add("PRESUPUESTO"+" - "+presupuestoIf.getCodigo());
						filaPlantillaDetalle.add(presupuestoIf.getConcepto());
						filaPlantillaDetalle.add(formatoDecimal.format(presupuestoIf.getValor()));
						
						PropuestaDetalleData data = new PropuestaDetalleData();
						
						data.setPresupuestoId(presupuestoIf.getId());
						
						valor = valor + presupuestoIf.getValor().doubleValue(); 
						
						PropuestaDetalleColeccion.add(data);
						
						System.out.println("nombre:"+filaPlantillaDetalle.get(0));
						System.out.println("concepto:"+filaPlantillaDetalle.get(1));
						System.out.println("valor:"+filaPlantillaDetalle.get(2));
						
						modelPropuestaDetalle.addRow(filaPlantillaDetalle);
						
						this.getTxtValor().setText(formatoDecimal.format(valor));
					}
				}
				
				detailExists = true;
			}
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		//para plan de medio
		
		try {
			//agrego los detalles de plan de medios a la factura
			
			modelPropuestaDetalle = (DefaultTableModel) getTblPropuestaDetalle().getModel();
			
			Collection listaPlantillaDetalle = SessionServiceLocator.getPlanMedioSessionService().findPlanMedioByOrdenTrabajoId(ordenTrabajoIf.getId());
			Iterator it = listaPlantillaDetalle.iterator();
			
			if(listaPlantillaDetalle.size()>0){
				while(it.hasNext()){
					
					PlanMedioIf planMedioIf = (PlanMedioIf) it.next();
					
					boolean isExistePlanMedio = false;
					int cont = 0;
					//Si la coleccion tiene algun elemento
					if(PropuestaDetalleColeccion.size()!=0){
						//Recorro la coleccion de producto cliente
		    			for(int i=0;i<PropuestaDetalleColeccion.size();i++){
		    				
		    				//Vector<String> propuestaIf = new Vector<String>();
		    				
		    				PropuestaDetalleIf propuestaIf = (PropuestaDetalleIf) PropuestaDetalleColeccion.get(i);
		    				
		    				//Si el plan medio cargado ya esta en lista, entons muestro un mensaje de error
		    				if(propuestaIf.getPlanmedioId()!=null){
		    					if(propuestaIf.getPlanmedioId().equals(planMedioIf.getId())){
		    						isExistePlanMedio = true;
			    					if (cont==1)
			    						SpiritAlert.createAlert("Uno de los Presupuestos o Planes de Medio de esta Orden ya se encuentra agregada !!!", SpiritAlert.INFORMATION);
			    					break;
			    				}
		    				}
		    				
		    				
		    			}
		    		}
					
					//Si el plan medio no existe lo inserto en la lista
					if(isExistePlanMedio==false){
						
						Vector<String> filaPlantillaDetalle = new Vector<String>();
						
						filaPlantillaDetalle.add("PLAN MEDIO"+" - "+planMedioIf.getCodigo());
						filaPlantillaDetalle.add(planMedioIf.getConcepto());
						//por le momento se va ahcer referencia al valor de la tarifa, hasta sabeer que valor va ahi
						if(planMedioIf.getValorDescuento() != null){
							filaPlantillaDetalle.add(formatoDecimal.format(planMedioIf.getValorDescuento().doubleValue()));
						}
						else{
							filaPlantillaDetalle.add("0.0");
						}
						
						PropuestaDetalleData data = new PropuestaDetalleData();
						
						data.setPlanmedioId(planMedioIf.getId());
						
						//por le momento se va ahcer referencia al valor de la tarifa, hasta sabeer que valor va ahi
						if(planMedioIf.getValorDescuento() != null){
							valor = valor + planMedioIf.getValorDescuento().doubleValue(); 
						}
						
						PropuestaDetalleColeccion.add(data);
						
						System.out.println("nombre:"+filaPlantillaDetalle.get(0));
						System.out.println("concepto:"+filaPlantillaDetalle.get(1));
						System.out.println("valor:"+filaPlantillaDetalle.get(2));
						
						modelPropuestaDetalle.addRow(filaPlantillaDetalle);
						
						this.getTxtValor().setText(formatoDecimal.format(valor));
					}
				}
				
				detailExists = true;
			}
			
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
		
		if (!detailExists)
			SpiritAlert.createAlert(
					"La orden de trabajo seleccionada no tiene un Presupuesto o Plan de Medios asociado"
					,SpiritAlert.WARNING);
	}
	
	public void cleanTabla() {
		
		//elimino primero los detalles que ya estan agregados
		DefaultTableModel model = (DefaultTableModel) this.getTblPropuestaDetalle().getModel();
		
		for(int i= this.getTblPropuestaDetalle().getRowCount();i>0;--i)
			model.removeRow(i-1);
			
		PropuestaDetalleColeccion = new Vector();
		
		valor = 0.00;
		
		this.getTxtValor().setText("");
		
		this.repaint();
	}
	
	ActionListener oActionListenerEliminarPropuestaDetalle = new ActionListener() {
    	public void actionPerformed(ActionEvent evento) {
    		    		
    		if (getTblPropuestaDetalle().getSelectedRow()!=-1)
    		{
    			if(getMode()==SpiritMode.UPDATE_MODE || getMode()==SpiritMode.SAVE_MODE ){
    				// Elimino el registro de la coleccion
					String valorTemp = (String) getTblPropuestaDetalle().getModel().getValueAt(getTblPropuestaDetalle().getSelectedRow(),2);
					Double valorInt = Double.valueOf(Utilitarios.removeDecimalFormat(valorTemp.toString()));
					
					valor = valor - valorInt;
					PropuestaDetalleColeccion.remove(getTblPropuestaDetalle().getSelectedRow());
					modelPropuestaDetalle.removeRow(getTblPropuestaDetalle().getSelectedRow());
					
					if(valor<0)
						getTxtValor().setText("0.0");
					else
						getTxtValor().setText(formatoDecimal.format(valor));
					
					SpiritAlert.createAlert("El detalle ha sido eliminado con éxito",SpiritAlert.INFORMATION);
    			}
    		} else {
    			SpiritAlert.createAlert("Debe elegir el registro del detalle a eliminar !!!"
    					,SpiritAlert.WARNING);
    		}
    	}
    };
    
    //Mouse Listener de la Tabla propuesta de talle carga los datos en la tabla cada vez que da click sobre un item
	//detalle de la tabla
	MouseListener oMouseAdapterTblPropuestaDetalle = new MouseAdapter() {
    	public void mouseReleased(MouseEvent evt) {
            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblPropuestaDetalle().getModel().getRowCount()>0) {
            	popupMenuPropuestaDetalle.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
	};
	
	ActionListener oActionListenerCargarTabla = new ActionListener() {
    	public void actionPerformed(ActionEvent evento) {
    		
    		ordenTrabajoIf = (OrdenTrabajoIf) getCmbOrdenTrabajo().getModel().getSelectedItem();
			
			if(getMode()==SpiritMode.SAVE_MODE || getMode()==SpiritMode.UPDATE_MODE){
				
				if(getCmbOrdenTrabajo().getModel().getSelectedItem()!=null){
					
					if(PropuestaDetalleColeccion.size()!= 0){
						cleanTabla();
						cargarDetalles();
					}
					else{
						cargarDetalles();
					}
					
				}
			}
    }};
    
    public boolean validateFields() {

		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar un Código para la Propuesta !!"
					,SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
				
		if ("".equals(getTxtCorporacion().getText())) {
			SpiritAlert.createAlert("Debe seleccionar una Corporación para la Propuesta !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCorporacion().grabFocus();
			return false;			
		}
		
		if ("".equals(getTxtCliente().getText())) {
			SpiritAlert.createAlert("Debe seleccionar un cliente para la Propuesta !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCliente().grabFocus();
			return false;			
		}
		
		if ("".equals(getTxtOficina().getText())) {
			SpiritAlert.createAlert("Debe seleccionar una Oficina del cliente para la Propuesta !!"
					,SpiritAlert.WARNING);
			getBtnBuscarOficina().grabFocus();
			return false;			
		}
		
		if (getCmbOrdenTrabajo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar una Orden de Trabajo para la Propuesta !!"
					,SpiritAlert.WARNING);
			getCmbOrdenTrabajo().grabFocus();
			return false;			
		}
		
		if (PropuestaDetalleColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe seleccionar un detalle para la Propuesta !!"
					,SpiritAlert.WARNING);
			getCmbOrdenTrabajo().grabFocus();
			return false;			
		}
		
		if (this.getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un Estado para la Propuesta !!"
					,SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;			
		}
		
		Collection propuestas = null;
		boolean codigoRepetido = false;
		
		try {
			propuestas = SessionServiceLocator.getPropuestaSessionService().findPropuestaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator propuestaIt = propuestas.iterator();
		
		while (propuestaIt.hasNext()) {
			PropuestaIf propuestaIf = (PropuestaIf) propuestaIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(propuestaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(propuestaIf.getCodigo())) 
					if (this.propuesta.getId().equals(propuestaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la Propuesta está duplicado !!"
					,SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}	
		
		return true;

	}
    
    public void deletePropuestaDetalles(){
		try{
			//Creo la coleccion de las propuestas detalle
			Collection propuestaDetalleColeccionTemp = SessionServiceLocator.getPropuestaDetalleSessionService().findPropuestaDetalleByPropuestaId(propuesta.getId());
			Iterator itPropuestaColeccion = propuestaDetalleColeccionTemp.iterator();
	 
			//Creo instancia de cada uno de las propuestas detalle
			while (itPropuestaColeccion.hasNext()) {
				PropuestaDetalleIf propuestaDetalleIf = (PropuestaDetalleIf) itPropuestaColeccion.next();
					
				//Elimino el detalle de la propuesta encontrado
				SessionServiceLocator.getPropuestaDetalleSessionService().deletePropuestaDetalle(propuestaDetalleIf.getId());				
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("La propuesta tiene datos referenciados y no puede ser eliminada!"
					,SpiritAlert.ERROR);
			this.clean();
		}	
		
    }

}
