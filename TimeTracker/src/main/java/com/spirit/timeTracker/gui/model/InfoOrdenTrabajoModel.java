package com.spirit.timeTracker.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.entity.TipoOrdenIf;
import com.spirit.medios.entity.CampanaArchivoIf;
import com.spirit.medios.entity.CampanaBriefIf;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.entity.TipoBriefIf;
import com.spirit.medios.gui.model.OrdenTrabajoDetalleReporteData;
import com.spirit.medios.handler.EstadoOrdenTrabajo;
import com.spirit.timeTracker.gui.main.JPInfoOrdenTrabajo;
import com.spirit.timeTracker.gui.model.cache.MapaCache;
import com.spirit.util.Archivos;
import com.spirit.util.Utilitarios;


public class InfoOrdenTrabajoModel extends JPInfoOrdenTrabajo {
	
	private static final long serialVersionUID = -4672206864900601037L;
	
	private ClienteIf clienteIf;
	private ClienteOficinaIf clienteOficinaIf;
	DefaultTableModel modelArchivosCampana;
	final JPopupMenu popupArchivosCampana = new JPopupMenu();
	private ArrayList<OrdenTrabajoDetalleReporteData> ordenTrabajoDetalleColeccion = new ArrayList<OrdenTrabajoDetalleReporteData>();
	private ArrayList<ProductoClienteIf> productoClienteColeccion = new ArrayList<ProductoClienteIf>();
	private OrdenTrabajoIf ordenTrabajoIf;
	
	public InfoOrdenTrabajoModel() {
		
	}
	
	private void anchoColumnasTablas() {
		TableColumn anchoColumna = getTblArchivosCampana().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblArchivosCampana().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(250);
		getTxtObservacion().setEditable(true);
		getBtnPropuesta().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnPropuesta().setToolTipText("Ver Archivo");
		getBtnImprimir().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/print.png"));
		getBtnImprimir().setToolTipText("Imprimir Orden");
	}
	
	public void initListeners(){
		final String si = "Si"; 
		final String no = "No"; 
		final Object[] options ={si,no}; 
		getBtnPropuesta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				int opcion = JOptionPane.showOptionDialog(null, "Desea visualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, si);
				if (opcion == JOptionPane.YES_OPTION) {
					try {
						String urlArchivo = getTxtPropuesta().getText();
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}
		});
		
		getBtnGuardar().setText("");
		getBtnGuardar().setIcon(com.spirit.timeTracker.gui.model.SpiritResourceManager.getImageIcon("images/icons/funcion/saveElement.png"));
		
		getBtnGuardar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					ordenTrabajoIf.setObservacion(getTxtObservacion().getText());
					SessionServiceLocator.getOrdenTrabajoSessionService().saveOrdenTrabajo(ordenTrabajoIf);
					SpiritAlert.createAlert("Observación actualizada", SpiritAlert.INFORMATION);
				} catch (GenericBusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		// Opcion Que Permite Visualizar un archivo deseado de la tabla
		JMenuItem itemVerBriefArchivo = new JMenuItem("Visualizar Archivo");
		popupArchivosCampana.add(itemVerBriefArchivo);
		// Añado el listener de menupopup
		itemVerBriefArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblArchivosCampana().getSelectedRow() != -1) {
					try {
						String urlArchivo = (String) getTblArchivosCampana().getModel().getValueAt(getTblArchivosCampana().getSelectedRow(), getTblArchivosCampana().getColumnModel().getColumnIndex("URL"));
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					} catch (Exception e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !!!", SpiritAlert.WARNING);
				}
			}
		});
		
		// Listenner de la tabla
		getTblArchivosCampana().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				if (evt.getClickCount() > 1 && getTblArchivosCampana().getModel().getRowCount() > 0) {
					popupArchivosCampana.show(evt.getComponent(), evt.getX(),evt.getY());
				}
			}
		});
		
		getBtnImprimir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				report(ordenTrabajoIf);
			}
		});
	}
	
	public InfoOrdenTrabajoModel(OrdenTrabajoIf ordenTrabajo ) throws GenericBusinessException {
		ordenTrabajoIf = ordenTrabajo;
		anchoColumnasTablas();
		initListeners();
		
		if (ordenTrabajo!=null){
			try {
				clienteOficinaIf = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenTrabajo.getClienteoficinaId());
				//clienteIf = SessionServiceLocator.getClienteSessionService().getCliente(clienteOficinaIf.getClienteId());
				clienteIf = MapaCache.verificarClienteEnMapa(MapaCache.getMapaClientes(), clienteOficinaIf.getClienteId());
				CampanaIf campanaIf = SessionServiceLocator.getCampanaSessionService().getCampana(ordenTrabajo.getCampanaId());
				
				getTxtCodigo().setText(ordenTrabajo.getCodigo());
				getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
				EmpleadoIf empleado = MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(), ordenTrabajo.getEmpleadoId());
				getTxtEjecutivo().setText(empleado.getNombres() + " " + empleado.getApellidos());
				
				if(empleado.getJefeId() != null){
					EmpleadoIf director = MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(), empleado.getJefeId());
					getTxtDirector().setText(director.getNombres() + " " + director.getApellidos());
				}else{
					getTxtDirector().setText("");
				}				
				
				if (campanaIf != null)
					getTxtCampana().setText(campanaIf.toString());
				
				if (ordenTrabajo.getFechaCreacion() != null)
					getTxtFechaCreacion().setText(Utilitarios.getFechaUppercase(ordenTrabajo.getFechaCreacion()) );
								
				if (ordenTrabajo.getFechalimite() != null)
					getTxtFechaLimite().setText(Utilitarios.getFechaUppercase(ordenTrabajo.getFechalimite()) );
				
				if (ordenTrabajo.getFechaentrega() != null)
					getTxtFechaEntrega().setText(Utilitarios.getFechaUppercase(ordenTrabajo.getFechaentrega()) );
								
				
				getTxtDescripcion().setText(ordenTrabajo.getDescripcion());
				getTxtObservacion().setText(ordenTrabajo.getObservacion());
				
				if(ordenTrabajo.getUrlPropuesta() != null) {
					getTxtPropuesta().setText(ordenTrabajo.getUrlPropuesta());
					getBtnPropuesta().setEnabled(true);
				} else{
					getBtnPropuesta().setEnabled(false);				
				}
				
				String estadoLetra = ordenTrabajo.getEstado();
				EstadoOrdenTrabajo estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
				getTxtEstado().setText(estado.toString());
				
				//Cargo archivos de Campaña
				modelArchivosCampana = (DefaultTableModel) getTblArchivosCampana().getModel();
				for(int i= this.getTblArchivosCampana().getRowCount();i>0;--i)
					modelArchivosCampana.removeRow(i-1);
				
				Collection campanaBriefColeccion = SessionServiceLocator.getCampanaBriefSessionService().findCampanaBriefByCampanaId(campanaIf.getId());
				Iterator itCampanaBriefColeccion = campanaBriefColeccion.iterator();

				modelArchivosCampana = (DefaultTableModel) getTblArchivosCampana().getModel();

				while (itCampanaBriefColeccion.hasNext()) {
					CampanaBriefIf campanaBriefIf = (CampanaBriefIf) itCampanaBriefColeccion.next();
					if(campanaBriefIf.getUrlDescripcion() != null){
						Vector<String> filaBriefCampana = new Vector<String>();
						//TipoBriefIf tipoBrief = SessionServiceLocator.getTipoBriefSessionService().getTipoBrief(campanaBriefIf.getTipoBriefId());
						TipoBriefIf tipoBrief = MapaCache.verificarTipoBriefEnMapa(MapaCache.getMapaTipoBrief(), campanaBriefIf.getTipoBriefId());
						filaBriefCampana.add(tipoBrief.getNombre());
						filaBriefCampana.add(campanaBriefIf.getUrlDescripcion().toString());
						
						modelArchivosCampana.addRow(filaBriefCampana);
					}					
				}

				Collection campanaArchivoColeccion = SessionServiceLocator.getCampanaArchivoSessionService().findCampanaArchivoByCampanaId(campanaIf.getId());
				Iterator itCampanaArchivoColeccion = campanaArchivoColeccion.iterator();

				modelArchivosCampana = (DefaultTableModel) getTblArchivosCampana().getModel();

				while (itCampanaArchivoColeccion.hasNext()) {
					CampanaArchivoIf campanaArchivoIf = (CampanaArchivoIf) itCampanaArchivoColeccion.next();
					if(campanaArchivoIf.getUrlDescripcion() != null){
						Vector<String> filaCampanaArchivo = new Vector<String>();
						TipoArchivoIf tipoArchivo = (TipoArchivoIf) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivo(campanaArchivoIf.getTipoArchivoId());
						filaCampanaArchivo.add(tipoArchivo.getNombre());
						filaCampanaArchivo.add(campanaArchivoIf.getUrlDescripcion());
						
						modelArchivosCampana.addRow(filaCampanaArchivo);
					}					
				}
				
				//Cargo los datos para el reporte
				ordenTrabajoDetalleColeccion = null;
				ordenTrabajoDetalleColeccion = new ArrayList<OrdenTrabajoDetalleReporteData>();
				
				Collection<OrdenTrabajoDetalleIf> listaOrdenDetalle = SessionServiceLocator.getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(ordenTrabajo.getId());
				
				for ( OrdenTrabajoDetalleIf ordenTrabajoDetalleIf : listaOrdenDetalle ) {
												
					//SubtipoOrdenIf subtipo = SessionServiceLocator.getSubtipoOrdenSessionService().getSubtipoOrden(ordenTrabajoDetalleIf.getSubtipoId());
					SubtipoOrdenIf subtipo = MapaCache.verificarSubTipoOrdenEnMapa(MapaCache.getMapaSubTipoOrden(), ordenTrabajoDetalleIf.getSubtipoId());
					
					OrdenTrabajoDetalleReporteData ordenTrabajoDetalleReporteData = new OrdenTrabajoDetalleReporteData();
					EmpleadoIf empleadoAsignadoA =  MapaCache.verificarEmpleadoEnMapa(MapaCache.getMapaEmpleados(),ordenTrabajoDetalleIf.getAsignadoaId());
					ordenTrabajoDetalleReporteData.setAsignadoA(empleadoAsignadoA.getNombres().split(" ")[0] + " " + empleadoAsignadoA.getApellidos().split(" ")[0]);
					ordenTrabajoDetalleReporteData.setDescripcion(ordenTrabajoDetalleIf.getDescripcion());
						
					estadoLetra = ordenTrabajoDetalleIf.getEstado();
					estado = EstadoOrdenTrabajo.getEstadoOrdenTrabajoByLetra(estadoLetra);
					ordenTrabajoDetalleReporteData.setEstado(estado.toString());
					
					ordenTrabajoDetalleReporteData.setFechaEntrega(ordenTrabajoDetalleIf.getFechaentrega()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoDetalleIf.getFechaentrega()):"N/A");
					ordenTrabajoDetalleReporteData.setSubtipo(subtipo.getNombre());
					//TipoOrdenIf tipo = SessionServiceLocator.getTipoOrdenSessionService().getTipoOrden(subtipo.getTipoordenId());
					TipoOrdenIf tipo = MapaCache.verificarTipoOrdenEnMapa(MapaCache.getMapaTipoOrden(),subtipo.getTipoordenId());
					
					ordenTrabajoDetalleReporteData.setTipo(tipo.getNombre());
					ordenTrabajoDetalleColeccion.add(ordenTrabajoDetalleReporteData);
				}
				
				//Lleno la lista de productos del cliente, que tambien sirve para el reporte
				productoClienteColeccion = null;
				productoClienteColeccion = new ArrayList<ProductoClienteIf>();
				
				Collection<OrdenTrabajoProductoIf> listaOrdenProducto = SessionServiceLocator.getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(ordenTrabajo.getId());
				for (OrdenTrabajoProductoIf ordenTrabajoProductoIf : listaOrdenProducto) {
					ProductoClienteIf productoClienteIf = MapaCache.verificarProductoClienteEnMapa(MapaCache.getMapaProductoCliente(), ordenTrabajoProductoIf.getProductoClienteId());
					productoClienteColeccion.add(productoClienteIf);
				}

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				throw new GenericBusinessException("Error al obtener datos de la Orden de Trabajo");
			}
		} else{
			SpiritAlert.createAlert("No existe la orden de trabajo seleccionada", SpiritAlert.WARNING);
		}		
	}
	
	public void report(OrdenTrabajoIf ordenTrabajoIf) {
		try {
			if(ordenTrabajoIf != null && !productoClienteColeccion.isEmpty()){
				String fileName = "jaspers/medios/RPOrdenTrabajo.jasper";
				HashMap parametrosMap = new HashMap();
				parametrosMap.put("codigoReporte", "");
				EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
				parametrosMap.put("empresa", empresa.getNombre());
				parametrosMap.put("ruc", "RUC: " + empresa.getRuc());
				parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
				parametrosMap.put("usuario", Parametros.getUsuario());
				parametrosMap.put("numeroOrden", "Orden de Trabajo No. " + ordenTrabajoIf.getCodigo());
				
				OficinaIf oficina = (OficinaIf) Parametros.getOficina();
				CiudadIf ciudad = (CiudadIf) Parametros.getCiudad();
				String ciudadNombre = ciudad.getNombre().substring(0,1).concat(ciudad.getNombre().substring(1, ciudad.getNombre().length()).toLowerCase());
				String fechaActual = Utilitarios.dateHoraHoy();
				String year = fechaActual.substring(0,4);
				String month = fechaActual.substring(5,7);
				String day = fechaActual.substring(8,10);
				String fechaEmision = day + " " + Utilitarios.getNombreMes(Integer.parseInt(month)).toLowerCase() + " del " + year;
				parametrosMap.put("lugarFechaEmision", ciudadNombre + ", " + fechaEmision);		
				
				parametrosMap.put("cliente", clienteIf.getNombreLegal());
				parametrosMap.put("descripcion", ordenTrabajoIf.getDescripcion());
				parametrosMap.put("observacion", getTxtObservacion().getText());
				parametrosMap.put("fechaEntrega", ordenTrabajoIf.getFechaentrega()!=null?Utilitarios.getFechaCortaUppercase( ordenTrabajoIf.getFechaentrega()):"N/A");
				parametrosMap.put("fechaLimite",  ordenTrabajoIf.getFechalimite()!=null?Utilitarios.getFechaCortaUppercase(ordenTrabajoIf.getFechalimite()):"N/A");
				parametrosMap.put("estado", getTxtEstado().getText());
				
				String productos = "";
				String marcas = "";
				String marcaRepedita = "";
				for(ProductoClienteIf productoClienteIf : productoClienteColeccion){
					productos = productos + productoClienteIf.getNombre() + ", ";
					if(!marcas.contains(productoClienteIf.getMarcaProductoNombre())){
						marcas = marcas + productoClienteIf.getMarcaProductoNombre() + ", ";
					}
				}
				productos = productos.substring(0,productos.length()-2);
				marcas = marcas.substring(0,marcas.length()-2);
				parametrosMap.put("productos", productos);
				parametrosMap.put("marca", marcas);
				
				ReportModelImpl.processReport(fileName, parametrosMap, ordenTrabajoDetalleColeccion, true);
			}else if(productoClienteColeccion.isEmpty()){
				SpiritAlert.createAlert("No se puede generar el reporte, la Orden es Genérica.", SpiritAlert.WARNING);
			}else{
				SpiritAlert.createAlert("No existe información para imprimir", SpiritAlert.WARNING);
			}			
			
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}	
	}	
}
