package com.spirit.compras.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.compras.entity.SolicitudCompraArchivoData;
import com.spirit.compras.entity.SolicitudCompraArchivoIf;
import com.spirit.compras.entity.SolicitudCompraData;
import com.spirit.compras.entity.SolicitudCompraDetalleData;
import com.spirit.compras.entity.SolicitudCompraDetalleIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.gui.criteria.ProductoCompraCriteria;
import com.spirit.compras.gui.criteria.SolicitudCompraCriteria;
import com.spirit.compras.gui.panel.JPSolicitudCompra;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoArchivoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
//COMENTED 18 JULIO
//import com.spirit.medios.entity.OrdenMedioDetalleIf;
//import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.entity.PresupuestoIf;
//COMENTED 18 JULIO
//import com.spirit.medios.gui.criteria.OrdenMedioCriteria;
import com.spirit.medios.gui.criteria.PresupuestoCriteria;
import com.spirit.util.Archivos;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.NumberTextField;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class SolicitudCompraModel extends JPSolicitudCompra {
	
	private static final long serialVersionUID = -6950469987672585995L;
	
	private static final int MAX_LONGITUD_OBSERVACION = 3000;
	private static final int MAX_LONGITUD_CANTIDAD = 8;		
	private static final int MAX_LONGITUD_REFERENCIA = 10;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String NOMBRE_ESTADO_PENDIENTE = "PENDIENTE";
	private static final String NOMBRE_ESTADO_APROBADO = "APROBADO";
	private static final String NOMBRE_ESTADO_REALIZADO = "REALIZADO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final String ESTADO_PENDIENTE = "P";
	private static final String ESTADO_APROBADO = "B";
	private static final String ESTADO_REALIZADO = "R";
	private static final String NOMBRE_TIPO_REFERENCIA_PRESUPUESTO = "PRESUPUESTO";
	private static final String TIPO_REFERENCIA_PRESUPUESTO = NOMBRE_TIPO_REFERENCIA_PRESUPUESTO.substring(0,1);
	//COMENTED 18 JULIO
	//private static final String NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS = "ORDEN DE MEDIOS";
	//private static final String TIPO_REFERENCIA_ORDEN_MEDIOS = NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS.substring(0,1);
	//END COMENTED 18 JULIO
	private static final String NOMBRE_TIPO_REFERENCIA_NINGUNO = "NINGUNO";
	private static final String TIPO_REFERENCIA_NINGUNO = NOMBRE_TIPO_REFERENCIA_NINGUNO.substring(0,1);
	Calendar calendarFecha = new GregorianCalendar();
	Calendar calendarFechaEntrega = new GregorianCalendar();
	protected PresupuestoIf presupuestoIf;
	//COMENTED 18 JULIO
	//protected OrdenMedioIf ordenMedioIf;
	
	private ProductoIf productoIf;
	private EmpleadoCriteria empleadoCriteria;
	private EmpleadoIf empleadoIf;
	private boolean actualizarDetalle = false;
	private DefaultTableModel tableDetalleModel, modelSolicitudCompraArchivo;
	private SolicitudCompraDetalleIf solicitudCompraDetalleIf;
	private Vector<SolicitudCompraDetalleIf> vectorSolicitudCompraDetalleIf = new Vector<SolicitudCompraDetalleIf>();
	private int solicitudCompraDetalleSeleccionada;
	private List<SolicitudCompraDetalleIf> solicitudCompraDetalleEliminadas = new ArrayList<SolicitudCompraDetalleIf>();
	private SolicitudCompraIf solicitudCompraIf;
	long idTareaSolicitudCompra = 0L;
	String tipoReferencia = "";
	private final JPopupMenu popupMenuArchivo = new JPopupMenu();
	private Vector<SolicitudCompraArchivoIf> archivosSolicitudCompraColeccion = new Vector<SolicitudCompraArchivoIf>();
	private Vector<File> archivosColeccion = new Vector<File>();
	private Vector<String> archivosNombreColeccion = new Vector<String>();
	private Vector<SolicitudCompraArchivoIf> archivosEliminadosColeccion = new Vector<SolicitudCompraArchivoIf>();
	
	
	public SolicitudCompraModel(){
		showSaveMode();
		initKeyListeners();
		setSorterTable(getTblDetalle());
		initListeners();
		cargarCombos();
		alineacionTabla();
		anchoColumnasTabla();
		setSorterTable(getTblDetalle());
		getTblDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDetalle().addMouseListener(oMouseAdapterTblDetalle);
		getTblDetalle().addKeyListener(oKeyAdapterTblDetalle);
		new HotKeyComponent(this);
	}
	
	public SolicitudCompraModel(Object solicitudCompra, long idTareaSolicitudCompra) {
		initKeyListeners();
		initListeners();
		cargarCombos();
		alineacionTabla();
		anchoColumnasTabla();
		setSorterTable(getTblDetalle());
		getTblDetalle().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTblDetalle().addMouseListener(oMouseAdapterTblDetalle);
		getTblDetalle().addKeyListener(oKeyAdapterTblDetalle);
		new HotKeyComponent(this);
		getSelectedObject(solicitudCompra);
		this.idTareaSolicitudCompra = idTareaSolicitudCompra;
	}
	
	private void initKeyListeners() {
		getTxtSolicitud().addKeyListener(new TextChecker(MAX_LONGITUD_OBSERVACION, true, 0));
		getTxtCantidad().addKeyListener(new TextChecker(MAX_LONGITUD_CANTIDAD));
		getTxtReferencia().addKeyListener(new TextChecker(MAX_LONGITUD_REFERENCIA));
		getTxtCantidad().addKeyListener(new NumberTextField());
		getTxtEmpleado().setEditable(false);
		getTxtProducto().setEditable(false);
		getCmbFechaEntrega().setLocale(Utilitarios.esLocale);
		getCmbFechaEntrega().setShowNoneButton(false);
		getCmbFechaEntrega().setEditable(false);
	}
	
	public void alineacionTabla() {
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
		getTblDetalle().getColumnModel().getColumn(2).setCellRenderer(dtcrColumn);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblDetalle().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(230);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(230);
		anchoColumna = getTblDetalle().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(30);
		
		TableColumn anchoColumnaArchivos = getTblArchivo().getColumnModel().getColumn(0);
		anchoColumnaArchivos.setPreferredWidth(100);
		anchoColumnaArchivos = getTblArchivo().getColumnModel().getColumn(1);
		anchoColumnaArchivos.setPreferredWidth(400);
	}
	
	public void cargarCombos(){
		cargarComboBodega();
		cargarComboTipoArchivo();
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_PENDIENTE);
		getCmbEstado().addItem(NOMBRE_ESTADO_APROBADO);
		getCmbEstado().addItem(NOMBRE_ESTADO_REALIZADO);
		getCmbEstado().setSelectedIndex(0);
	}
	
	private void cargarComboTipoArchivo()  {
		try {
			SpiritComboBoxModel cmbModelTipoArchivo = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivoList());
			getCmbTipoArchivo().setModel(cmbModelTipoArchivo);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void cargarComboBodega() {
		try {
			List bodegas = (List)SessionServiceLocator.getBodegaSessionService().findBodegaByOficinaId(Parametros.getIdOficina());
			refreshCombo(getCmbBodega(), bodegas);	
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void initListeners() {		
		getBtnEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarEmpleado();        
			}
		});
		
		getBtnProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				buscarProducto();
			}
		});
		
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalle();
			}
		});
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarDetalle();
			}
		});
		getBtnRemoverDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				removerDetalle();
			}
		});
		
		getCmbTipoReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbTipoReferencia().getSelectedItem() != null) {
					String tipo = getCmbTipoReferencia().getSelectedItem().toString();
					
					if (tipo.equals(NOMBRE_TIPO_REFERENCIA_NINGUNO)) {
						tipoReferencia = TIPO_REFERENCIA_NINGUNO;
						getBtnBuscarReferencia().setEnabled(false);
					} else {
						if (tipo.equals(NOMBRE_TIPO_REFERENCIA_PRESUPUESTO))
							tipoReferencia = TIPO_REFERENCIA_PRESUPUESTO;
						//COMENTED 18 JULIO
						//else if (tipo.equals(NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS))
							//tipoReferencia = TIPO_REFERENCIA_ORDEN_MEDIOS;
						//END COMENTED 18 JULIO
						
						getBtnBuscarReferencia().setEnabled(true);
					}
					
					if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE) {
						if (getVectorSolicitudCompraDetalleIf().size() != 0) {
							Iterator it = getVectorSolicitudCompraDetalleIf().iterator();
							while (it.hasNext()) {
								SolicitudCompraDetalleIf detalleRemovido = (SolicitudCompraDetalleIf) it.next();
								if (detalleRemovido.getId() != null)
									solicitudCompraDetalleEliminadas.add(detalleRemovido);
							}
						}
					
						//MODIFIED 18 JULIO
						//if((tipoReferencia == TIPO_REFERENCIA_PRESUPUESTO) || (tipoReferencia == TIPO_REFERENCIA_ORDEN_MEDIOS)){
						if((tipoReferencia == TIPO_REFERENCIA_PRESUPUESTO)){// || (tipoReferencia == TIPO_REFERENCIA_ORDEN_MEDIOS)){
							cleanTablaDetalle();
							getVectorSolicitudCompraDetalleIf().clear();
						}
					}
					
					presupuestoIf = null;
					//COMENTED 18 JULIO
					//ordenMedioIf = null;
					getTxtReferencia().setText("");
				}
			}
		});
		
		getTxtReferencia().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
			//public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					buscarReferencia(true);
				}
			}			
		});
		
		getBtnBuscarReferencia().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventoInicio) {
				buscarReferencia(true);
				//buscarReferencia(false);
			}
		});
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla de archivo
		JMenuItem itemEliminarArchivo = new JMenuItem("Eliminar");
		popupMenuArchivo.add(itemEliminarArchivo);
		// Añado el listener de menupopup
		itemEliminarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivo();
			}
		});

		// Opcion Que Permite Visualizar un archivo deseado de la tabla de archivos
		JMenuItem itemVerArchivo = new JMenuItem("Visualizar Archivo");
		popupMenuArchivo.add(itemVerArchivo);
		// Añado el listener de menupopup
		itemVerArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblArchivo().getSelectedRow() != -1) {
					try {
						String urlArchivo = (String) getTblArchivo().getModel().getValueAt(getTblArchivo().getSelectedRow(), 1);
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el registro de la lista a visualizar !",SpiritAlert.WARNING);
				}
			}
		});
		
		getBtnVerArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (!getTxtArchivo().getText().equals("")) {
					try {
						String urlArchivo = getTxtArchivo().getText();
						Archivos.abrirArchivoDesdeServidor(urlArchivo);
					} catch (IOException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				} else {
					SpiritAlert.createAlert("Debe elegir el archivo a visualizar !",SpiritAlert.WARNING);
				}
			}
		});

		// Listenner de la tabla de reunion archivo
		getTblArchivo().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (getTblArchivo().getSelectedRow() != -1) {
					for (int i = 0; i < getCmbTipoArchivo().getItemCount(); i++) {
						TipoArchivoIf bean = (TipoArchivoIf) getCmbTipoArchivo().getItemAt(i);
						if (bean.getNombre().compareTo(
							getTblArchivo().getModel().getValueAt(getTblArchivo().getSelectedRow(), 0).toString()) == 0)
							getCmbTipoArchivo().setSelectedItem(bean);
							getCmbTipoArchivo().repaint();
					}

					getTxtArchivo().setText(getTblArchivo().getModel().getValueAt(getTblArchivo().getSelectedRow(), 1).toString());
				}
			}

			public void mouseReleased(MouseEvent evt) {
				if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblArchivo().getModel().getRowCount() > 0) {
					popupMenuArchivo.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}
		});
		
		getBtnAgregarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarArchivo();
			}
		});
		
		getBtnActualizarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarArchivo();
			}
		});
		
		getBtnEliminarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarArchivo();
			}
		});

		getBtnBuscarArchivo().addActionListener(oActionListenerAgregarArchivo);
	}
	
	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {

			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));
			FileFilter filterJPG = new ExtensionFileFilter(null, new String[] {	"JPG", "JPEG" });
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif", new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc", new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);

			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Object[] options ={"Si","No"};
				try {
					boolean existe = SessionServiceLocator.getFileManagerSessionService().existeArchivo(Parametros.getUrlCarpetaServidor(), selectedFile.getName());
					if (!existe){
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */

						if ((actionEvent.getSource() == getBtnBuscarArchivo()))
							getTxtArchivo().setText(fileChooser.getSelectedFile().toString());

						/**
						 * ejecuto el archivo con su respectivo programa para poder ser
						 * previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null, "Desea previsualizar el archivo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
							if (opcion == JOptionPane.YES_OPTION) {
								String filename = selectedFile.getAbsolutePath();
								Archivos.abrirArchivoLocal(filename);
							}

						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}

					} else {
						int opcion = JOptionPane.showOptionDialog(null, "Archivo ya existe, desea reemplazarlo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnBuscarArchivo()))
								getTxtArchivo().setText(fileChooser.getSelectedFile().toString());
						} else {
							if ((actionEvent.getSource() == getBtnBuscarArchivo()))
								getTxtArchivo().setText("");
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			}
		}
	};
	
	private void agregarArchivo() {
		try {
			if (validateFieldsArchivo()) {				
				boolean isExisteArchivo = false;
				TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo().getSelectedItem();

				// Si la coleccion tiene algun elemento
				if (archivosSolicitudCompraColeccion.size() != 0) {
					// Recorro la coleccion de Archivos 
					for (int i = 0; i < archivosSolicitudCompraColeccion.size(); i++) {
						SolicitudCompraArchivoIf solicitudCompraArchivoTemp = (SolicitudCompraArchivoIf) archivosSolicitudCompraColeccion.get(i);
						// Si la reunion Archivo cargado ya esta en lista, entonces muestro un mensaje de error
						if (solicitudCompraArchivoTemp.getTipoArchivoId().equals(tipoArchivo.getId()) && solicitudCompraArchivoTemp.getUrl().equals(getTxtArchivo().getText().replaceAll(" ","_"))) {
							isExisteArchivo = true;
							break;
						}
					}
				}

				modelSolicitudCompraArchivo = (DefaultTableModel) getTblArchivo().getModel();
				Vector<String> filaArchivo = new Vector<String>();				
				
				if (isExisteArchivo == false) {
					SolicitudCompraArchivoData data = new SolicitudCompraArchivoData();

					data.setTipoArchivoId(tipoArchivo.getId());
					data.setUrl(getTxtArchivo().getText());
					archivosSolicitudCompraColeccion.add(data);
					
					File archivo = new File(getTxtArchivo().getText());
					archivosColeccion.add(archivo);
											
					archivosNombreColeccion.add(archivo.getName());
					
					filaArchivo.add(tipoArchivo.getNombre());
					filaArchivo.add(getTxtArchivo().getText());
					modelSolicitudCompraArchivo.addRow(filaArchivo);
					
					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);
					
					getTxtArchivo().setText("");
					getTxtArchivo().grabFocus();
					
				} else {
					SpiritAlert.createAlert("El Archivo ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					
					// Reinicio los componentes
					if (getCmbTipoArchivo().getItemCount() > 0)
						getCmbTipoArchivo().setSelectedIndex(0);
					
					getTxtArchivo().setText("");
					getTxtArchivo().grabFocus();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(" No se pudo ingresar el detalle !!!", SpiritAlert.ERROR);
		}
	}
	
	private void actualizarArchivo() {
		int filaSeleccionada = getTblArchivo().getSelectedRow();
		if (filaSeleccionada >= 0) {
			TipoArchivoIf tipoArchivo = (TipoArchivoIf) getCmbTipoArchivo().getSelectedItem();

			modelSolicitudCompraArchivo = (DefaultTableModel) getTblArchivo().getModel();
			Vector<String> filaArchivo = new Vector<String>();
			
			if (validateFieldsArchivo()) {
				SolicitudCompraArchivoData data = new SolicitudCompraArchivoData();

				data.setId(archivosSolicitudCompraColeccion.get(filaSeleccionada).getId());
				data.setTipoArchivoId(tipoArchivo.getId());
				data.setUrl(getTxtArchivo().getText());
				
				// Agregar a la coleccion para grabar al final toda la coleccion
				archivosSolicitudCompraColeccion.add(filaSeleccionada, data);
				archivosSolicitudCompraColeccion.remove(filaSeleccionada+1);
				
				File archivo = new File(getTxtArchivo().getText());
				archivosColeccion.add(archivo);
									
				filaArchivo.add(tipoArchivo.getNombre());
				filaArchivo.add(getTxtArchivo().getText());

				modelSolicitudCompraArchivo.insertRow(filaSeleccionada, filaArchivo);
				modelSolicitudCompraArchivo.removeRow(filaSeleccionada+1);
				
				// Reinicio los componentes
				if (getCmbTipoArchivo().getItemCount() > 0)
					getCmbTipoArchivo().setSelectedIndex(0);
				
				getTxtArchivo().setText("");
				getTxtArchivo().grabFocus();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
		}
	}
	
	private void eliminarArchivo() {
		if (getTblArchivo().getSelectedRow() != -1) {
			int filaSeleccionada = getTblArchivo().getSelectedRow();
			SolicitudCompraArchivoIf solicitudCompraArchivo = archivosSolicitudCompraColeccion.get(filaSeleccionada);
			if (solicitudCompraArchivo.getId() != null){
				archivosEliminadosColeccion.add(solicitudCompraArchivo);
			}
			archivosColeccion.remove(getTblArchivo().getSelectedRow());
			archivosSolicitudCompraColeccion.remove(getTblArchivo().getSelectedRow());
			modelSolicitudCompraArchivo.removeRow(getTblArchivo().getSelectedRow());
			getTxtArchivo().setText("");
			
		} else {
			SpiritAlert.createAlert("Debe elegir el registro de la lista a eliminar !!!", SpiritAlert.WARNING);
		}
	}
	
	private void buscarReferencia(boolean eventoEnter) {
		if (NOMBRE_TIPO_REFERENCIA_PRESUPUESTO.equals(getCmbTipoReferencia().getSelectedItem().toString()))
			presupuestoIf = buscarPresupuesto(eventoEnter);
		
		//COMENTED 18 JULIO
		//if (NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS.equals(getCmbTipoReferencia().getSelectedItem().toString()))
		//	ordenMedioIf = buscarOrdenMedios(eventoEnter);
		
		if (getMode() == SpiritMode.SAVE_MODE || getMode() == SpiritMode.UPDATE_MODE) {
			if (getVectorSolicitudCompraDetalleIf().size() != 0) {
				Iterator it = getVectorSolicitudCompraDetalleIf().iterator();
				while (it.hasNext()) {
					SolicitudCompraDetalleIf detalleRemovido = (SolicitudCompraDetalleIf) it.next();
					if (detalleRemovido.getId() != null)
						solicitudCompraDetalleEliminadas.add(detalleRemovido);
				}
				
				cleanTablaDetalle();
				getVectorSolicitudCompraDetalleIf().clear();
			}
			
			if (presupuestoIf != null)
				cargarDetallesPresupuesto();
			//COMENTED 18 JULIO
			//else if (ordenMedioIf != null)
			//	cargarDetallesOrdenMedio();
		}
	}
	
	private void buscarEmpleado() {
		empleadoCriteria = new EmpleadoCriteria("Empleados",Parametros.getIdEmpresa());
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			empleadoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
			getTxtEmpleado().setText(empleadoIf.getCodigo() + " - " + empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
		}
	}
	
	private void buscarProducto() {
		String tipoProducto = "";
		Long idReferencia = 0L;

		if (presupuestoIf != null) {
			tipoProducto = "P";
			idReferencia = presupuestoIf.getId();
		}
		//COMENTED 18 JULIO
		/*else if (ordenMedioIf != null) {
			tipoProducto = "M";
			idReferencia = ordenMedioIf.getId();
		}*/
		
		String mmpg = "";
		ProductoCompraCriteria productoCriteria = new ProductoCompraCriteria("Producto", idReferencia, tipoProducto, "R", "A", true, mmpg);
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.add(30);
		anchoColumnas.add(100);
		anchoColumnas.add(30);
		anchoColumnas.add(200);
		JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null) {
			productoIf = (ProductoIf) popupFinder.getElementoSeleccionado();	
			if (!productoRepetido())
				setProductoDetalle(popupFinder.getElementoSeleccionado());
			else
				SpiritAlert.createAlert("El producto seleccionado ya consta en el detalle!!!", SpiritAlert.WARNING);
		}
	}
	
	private PresupuestoIf buscarPresupuesto(boolean eventoEnter) {
		PresupuestoIf presupuesto = null;
		String codigo = getTxtReferencia().getText();
		
		try {
			Map parameterMap = new HashMap();
			if (eventoEnter){
				parameterMap.put("codigo", codigo + "%");
			}
			parameterMap.put("estado", "A"); // A = PRESUPUESTO ACEPTADO
			
			int tamanoLista = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQueryAndEmpresaIdListSize(parameterMap, Parametros.getIdEmpresa());
			
			if (tamanoLista == 1 && eventoEnter) {
				Iterator presupuestoIterator = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByQuery(parameterMap, Parametros.getIdEmpresa()).iterator();
				if (presupuestoIterator.hasNext()){
					presupuesto = (PresupuestoIf) presupuestoIterator.next();
				}
			} else if (tamanoLista > 1 || (tamanoLista == 1 && !eventoEnter)) {
				PresupuestoCriteria presupuestoCriteria = new PresupuestoCriteria(Parametros.getIdEmpresa());
				presupuestoCriteria.setResultListSize(tamanoLista);
				presupuestoCriteria.setQueryBuilded(parameterMap);
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(60);
				anchoColumnas.add(30);
				anchoColumnas.add(220);
				anchoColumnas.add(220);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), presupuestoCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null)
					presupuesto = (PresupuestoIf) popupFinder.getElementoSeleccionado();
			} 
			
			if (presupuesto == null) {
				if (tamanoLista <= 0 && eventoEnter)
					SpiritAlert.createAlert("No se halló el presupuesto", SpiritAlert.WARNING);
				getTxtReferencia().setText("");
			} else{
				getTxtReferencia().setText(presupuesto.getCodigo());
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		return presupuesto;
	}
	
	//COMENTED 18 JULIO
	/*private OrdenMedioIf buscarOrdenMedios(boolean eventoEnter) {
		OrdenMedioIf ordenMedios = null;
		String codigo = getTxtReferencia().getText();
		
		try {
			Map parameterMap = new HashMap();
			if (eventoEnter)
				parameterMap.put("codigo", codigo + "%");
			parameterMap.put("estado", "E"); // A = PRESUPUESTO ACEPTADO
			int tamanoLista = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQueryAndEmpresaIdListSize(parameterMap, Parametros.getIdEmpresa());
			if (tamanoLista == 1 && eventoEnter) {
				Iterator ordenMediosIterator = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByQuery(parameterMap, Parametros.getIdEmpresa()).iterator();
				if (ordenMediosIterator.hasNext())
					ordenMedios = (OrdenMedioIf) ordenMediosIterator.next();
			} else if (tamanoLista > 1 || (tamanoLista == 1 && !eventoEnter)) {
				OrdenMedioCriteria ordenMedioCriteria = new OrdenMedioCriteria(Parametros.getIdEmpresa());
				ordenMedioCriteria.setResultListSize(tamanoLista);
				ordenMedioCriteria.setQueryBuilded(parameterMap);
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), ordenMedioCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null)
					ordenMedios = (OrdenMedioIf) popupFinder.getElementoSeleccionado();
			} /*else if (tamanoLista == 0) {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}* /
			
			if (ordenMedios == null) {
				if (tamanoLista <= 0 && eventoEnter)
					SpiritAlert.createAlert("No se halló la orden de medios", SpiritAlert.WARNING);
				getTxtReferencia().setText("");
			} else
				getTxtReferencia().setText(ordenMedios.getCodigo());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		}
		
		return ordenMedios;
	}*///END COMENTED 18 JULIO
	
	private boolean productoRepetido() {
		if (getVectorSolicitudCompraDetalleIf().size()>0) {
			for (int i=0; i<getVectorSolicitudCompraDetalleIf().size(); i++) {
				if (productoIf.getId().compareTo(((SolicitudCompraDetalleIf) getVectorSolicitudCompraDetalleIf().get(i)).getProductoId()) == 0)
					return true;
			}
		}
		
		return false;
	}

	private void setProductoDetalle(Object objetoSeleccionado) {
		productoIf = (ProductoIf) objetoSeleccionado;
		getTxtProducto().setText(productoIf.getCodigo());

		try {
			GenericoIf genericoIf = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			if ("S".equals(genericoIf.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre()+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - " + genericoIf.getNombre());
			
			if (genericoIf.getCambioDescripcion() != null && genericoIf.getCambioDescripcion().equals("S")) {
				getTxtProducto().setEnabled(true);
			}
			getTxtCantidad().setText("");
			getTxtCantidad().setEnabled(true);
			getTxtCantidad().setEditable(true);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	MouseListener oMouseAdapterTblDetalle = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedDetalleRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblDetalle = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedDetalleRowForUpdate(evt);
		}
	};
	
	private void enableSelectedDetalleRowForUpdate(ComponentEvent evt) {
		int filaSeleccionada = getTblDetalle().convertRowIndexToModel(getTblDetalle().getSelectedRow());
		if (filaSeleccionada >= 0) {
			setSolicitudCompraDetalleSeleccionada(filaSeleccionada);
			solicitudCompraDetalleIf = (SolicitudCompraDetalleIf) getVectorSolicitudCompraDetalleIf().get(getSolicitudCompraDetalleSeleccionada());
			setSolicitudCompraDetalle(solicitudCompraDetalleIf);
		}
	}

	private void setSolicitudCompraDetalle(SolicitudCompraDetalleIf solicitudCompraDetalle) {
		try {
			productoIf = SessionServiceLocator.getProductoSessionService().getProducto(solicitudCompraDetalle.getProductoId());
			GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			if ("S".equals(generico.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				getTxtProducto().setText(productoIf.getCodigo() + " - " + generico.getNombre()+ " - " + presentacion.getNombre());
			} else
				getTxtProducto().setText(productoIf.getCodigo() + " - " + generico.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		getTxtCantidad().setText(String.valueOf(solicitudCompraDetalle.getCantidad().intValue()));
		solicitudCompraDetalleIf = solicitudCompraDetalle;
	}
	
	private void agregarDetalle() {
		setActualizarDetalle(false);
		if (validateDetalleFields()) {
			tableDetalleModel = (DefaultTableModel) getTblDetalle().getModel();
			//Vector SolicitudCompraDetalle para luego guardar en la base
			SolicitudCompraDetalleData bean = new SolicitudCompraDetalleData();
			setSolicitudCompraDetalleIf(bean);
			setearDetalle();
			vectorSolicitudCompraDetalleIf.add(getSolicitudCompraDetalleIf());
			// Vector fila para agregar a la tabla
			Vector<String> fila = new Vector<String>();
			agregarFilaTablaDetalle(fila);
			tableDetalleModel.addRow(fila);
			cleanDetalle();
		}
	}
	
	private void actualizarDetalle() {
		setActualizarDetalle(true);
		int filaSeleccionada = getTblDetalle().convertRowIndexToModel(getTblDetalle().getSelectedRow());
		if (filaSeleccionada >= 0) {
			if (validateDetalleFields()) {
				String si = "Si"; 
				String no = "No"; 
				Object[] options ={si,no}; 
				int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea actualizar la fila seleccionada?!", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if (opcion == JOptionPane.YES_OPTION) {
					setearDetalle();
					vectorSolicitudCompraDetalleIf.add(filaSeleccionada, getSolicitudCompraDetalleIf());
					vectorSolicitudCompraDetalleIf.remove(filaSeleccionada + 1);
					//Vector<String> fila = new Vector<String>();
					//tableDetalleModel.insertRow(getTblDetalle().getSelectedRow(), fila);
					//tableDetalleModel.removeRow(getTblDetalle().getSelectedRow() + 1);
					List fila = new LinkedList();
					agregarFilaTablaDetalle(fila);
					tableDetalleModel = (DefaultTableModel) getTblDetalle().getModel();
					tableDetalleModel.setValueAt(fila.get(0), filaSeleccionada, 0);
					tableDetalleModel.setValueAt(fila.get(1), filaSeleccionada, 1);
					tableDetalleModel.setValueAt(fila.get(2), filaSeleccionada, 2);
					cleanDetalle();
				}
			}
		} else
			SpiritAlert.createAlert("Debe seleccionar una fila para ser actualizada !", SpiritAlert.WARNING);
	}
	
	private void removerDetalle() {
		int filaSeleccionada = getTblDetalle().convertRowIndexToModel(getTblDetalle().getSelectedRow());
		if (filaSeleccionada >= 0) {
			String si = "Si"; 
			String no = "No"; 
			Object[] options = {si,no}; 
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea remover la fila seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				solicitudCompraDetalleEliminadas.add(vectorSolicitudCompraDetalleIf.get(filaSeleccionada));
				vectorSolicitudCompraDetalleIf.remove(filaSeleccionada);
				tableDetalleModel.removeRow(filaSeleccionada);
			}
			cleanDetalle();
		} else {
			SpiritAlert.createAlert("Debe seleccionar una fila para ser removida !", SpiritAlert.WARNING);
		}
	}
	
	private void setearDetalle() {
		getSolicitudCompraDetalleIf().setProductoId(productoIf.getId());
		getSolicitudCompraDetalleIf().setCantidad(BigDecimal.valueOf(Double.parseDouble(getTxtCantidad().getText())));
	}
	
	private void agregarFilaTablaDetalle(List<String> fila) {
		try {
			ProductoIf productoIf = SessionServiceLocator.getProductoSessionService().getProducto(getSolicitudCompraDetalleIf().getProductoId());
			ClienteOficinaIf oficinaProveedor = null;
			ClienteIf proveedor = null;
			if (productoIf.getProveedorId() != null) {
				oficinaProveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
				proveedor = SessionServiceLocator.getClienteSessionService().getCliente(oficinaProveedor.getClienteId());
			}
			
			if (proveedor != null)
				fila.add(proveedor.getIdentificacion() + " - " + proveedor.getNombreLegal());
			else
				fila.add("NO TIENE PROVEEDOR");
			GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			if ("S".equals(generico.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				fila.add(productoIf.getCodigo() + " - " + generico.getNombre()+ " - " + presentacion.getNombre());
			} else
				fila.add(productoIf.getCodigo() + " - " + generico.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		fila.add(getTxtCantidad().getText());
	}
	
	public void cargarDetallesPresupuesto() {
		try {
			tableDetalleModel = (DefaultTableModel) getTblDetalle().getModel();
			Collection listaPlantillaDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
			Iterator it = listaPlantillaDetalle.iterator();
			
			while (it.hasNext()) {
				PresupuestoDetalleIf presupuestoDetalleIf = (PresupuestoDetalleIf) it.next();
				SolicitudCompraDetalleData data = new SolicitudCompraDetalleData();
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				ClienteOficinaIf oficinaProveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(presupuestoDetalleIf.getProveedorId());
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(oficinaProveedor.getClienteId());
				filaPlantillaDetalle.add(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial());
				ProductoIf productoIf = SessionServiceLocator.getProductoSessionService().getProducto(presupuestoDetalleIf.getProductoId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				if ("S".equals(generico.getUsaLote())) {
					PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
					filaPlantillaDetalle.add(productoIf.getCodigo() + " - " + generico.getNombre()+ " - " + presentacion.getNombre());
				} else
					filaPlantillaDetalle.add(productoIf.getCodigo() + " - " + generico.getNombre());
				filaPlantillaDetalle.add(String.valueOf(Double.valueOf(presupuestoDetalleIf.getCantidad().doubleValue()).intValue()));
				data.setProductoId(presupuestoDetalleIf.getProductoId());
				data.setCantidad(presupuestoDetalleIf.getCantidad());
				getVectorSolicitudCompraDetalleIf().add(data);
				tableDetalleModel.addRow(filaPlantillaDetalle);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	//COMENTED 18 JULIO
	/*public void cargarDetallesOrdenMedio() {
		try {
			tableDetalleModel = (DefaultTableModel) getTblDetalle().getModel();
			Collection listaPlantillaDetalle = SessionServiceLocator.getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(presupuestoIf.getId());
			Iterator it = listaPlantillaDetalle.iterator();
			
			while (it.hasNext()) {
				OrdenMedioDetalleIf ordenMedioDetalleIf = (OrdenMedioDetalleIf) it.next();
				SolicitudCompraDetalleData data = new SolicitudCompraDetalleData();
				Vector<String> filaPlantillaDetalle = new Vector<String>();
				ClienteOficinaIf oficinaProveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(ordenMedioIf.getProveedorId());
				ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(oficinaProveedor.getClienteId());
				filaPlantillaDetalle.add(proveedor.getIdentificacion() + " - " + proveedor.getRazonSocial());
				ProductoIf productoIf = SessionServiceLocator.getProductoSessionService().getProducto(ordenMedioIf.getProductoProveedorId());
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
				filaPlantillaDetalle.add(productoIf.getCodigo() + " - " + generico.getNombre());
				//filaPlantillaDetalle.add(String.valueOf(ordenMedioDetalleIf.getFrecuencia()));
				data.setProductoId(ordenMedioIf.getProductoProveedorId());
				//data.setCantidad(BigDecimal.valueOf(ordenMedioDetalleIf.getFrecuencia().doubleValue()));
				getVectorSolicitudCompraDetalleIf().add(data);
				tableDetalleModel.addRow(filaPlantillaDetalle);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}*///END COMENTED 18 JULIO
	
	public boolean validateDetalleFields() {
		if ((("".equals(getTxtProducto().getText())) || (getTxtProducto().getText() == null))) {
			SpiritAlert.createAlert("Debe seleccionar un Producto !!",SpiritAlert.WARNING);
			getTxtProducto().grabFocus();
			return false;
		}
		if ((("".equals(getTxtCantidad().getText())) || (getTxtCantidad().getText() == null))) {
			SpiritAlert.createAlert("Debe ingresar una Cantidad !!",SpiritAlert.WARNING);
			getTxtCantidad().grabFocus();
			return false;
		}
		if (Double.valueOf(getTxtCantidad().getText()) <= 0D) {
			SpiritAlert.createAlert("La cantidad debe deber ser mayor que 0 !!",SpiritAlert.WARNING);
			getTxtCantidad().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsArchivo() {
		if (this.getCmbTipoArchivo().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el tipo de archivo !!!", SpiritAlert.WARNING);
			getCmbTipoArchivo().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtArchivo().getText())) {
			SpiritAlert.createAlert("Debe ingresar la URL del archivo !!!", SpiritAlert.WARNING);
			this.getBtnBuscarArchivo().grabFocus();
			return false;
		}
		
		return true;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		Long proveedorId = 0L, empleadoId = 0L;
		
		if (getTxtCodigo().getText().equals("") == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if (getCmbBodega().getSelectedIndex() >= 0)
			aMap.put("bodegaId", ((BodegaIf)getCmbBodega().getSelectedItem()).getId());
				
		if ((empleadoIf != null) && !getTxtEmpleado().getText().equals("")) {
			empleadoId = empleadoIf.getId();
			aMap.put("empleadoId", empleadoId);
		}
		
		if (getCmbEstado().getSelectedIndex() >= 0) {
			if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
				aMap.put("estado", ESTADO_ACTIVO);
			else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
				aMap.put("estado", ESTADO_INACTIVO);
			else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE))
				aMap.put("estado", ESTADO_PENDIENTE);
			else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
				aMap.put("estado", ESTADO_APROBADO);
			else if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_REALIZADO))
				aMap.put("estado", ESTADO_REALIZADO);
		}
		
		return aMap;
	}
	
	public void find() {
		try {			
			Map mapa = buildQuery();
			int tamanoLista = SessionServiceLocator.getSolicitudCompraSessionService().findSolicitudCompraByQueryAndEmpresaIdSize(mapa, Parametros.getIdEmpresa());
			if (tamanoLista > 0) {
				try {
					SolicitudCompraCriteria solicitudCompraCriteria = new SolicitudCompraCriteria(Parametros.getIdEmpresa(), 0L, false);
					solicitudCompraCriteria.setResultListSize(tamanoLista);
					solicitudCompraCriteria.setQueryBuilded(mapa);
					Vector<Integer> anchoColumnas = new Vector<Integer>();
					anchoColumnas.add(40);
					anchoColumnas.add(40);
					anchoColumnas.add(40);
					anchoColumnas.add(40);
					anchoColumnas.add(200);
					anchoColumnas.add(40);
					JDPopupFinderModel popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), solicitudCompraCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, anchoColumnas, null);
					if (popupFinder.getElementoSeleccionado() != null)
						getSelectedObject(popupFinder.getElementoSeleccionado());
				} catch(Exception e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
		} catch (Exception e) {
			SpiritAlert.createAlert("Error general en la búsqueda de información", SpiritAlert.ERROR);
		}
	}
	
	private void getSelectedObject(Object solicitudSeleccionada){
		solicitudCompraIf = (SolicitudCompraIf) solicitudSeleccionada;
		
		try {
			getTxtCodigo().setText(solicitudCompraIf.getCodigo());
			getCmbBodega().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbBodega(), solicitudCompraIf.getBodegaId()));
			getCmbBodega().repaint();
			empleadoIf = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(solicitudCompraIf.getEmpleadoId());
			getTxtEmpleado().setText(empleadoIf.getCodigo() + " - " + empleadoIf.getNombres() + " " + empleadoIf.getApellidos());
			getCmbFechaEntrega().setDate(solicitudCompraIf.getFechaEntrega());
			
			if(solicitudCompraIf.getEstado().equals(ESTADO_ACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else if(solicitudCompraIf.getEstado().equals(ESTADO_INACTIVO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			else if(solicitudCompraIf.getEstado().equals(ESTADO_PENDIENTE))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_PENDIENTE);
			else if(solicitudCompraIf.getEstado().equals(ESTADO_APROBADO))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_APROBADO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_REALIZADO);
			
			getTxtSolicitud().setText(solicitudCompraIf.getObservacion());
			
			if (TIPO_REFERENCIA_PRESUPUESTO.equals(solicitudCompraIf.getTipoReferencia())) {
				getCmbTipoReferencia().setSelectedItem(NOMBRE_TIPO_REFERENCIA_PRESUPUESTO);
				Iterator it = SessionServiceLocator.getPresupuestoSessionService().findPresupuestoByCodigo(solicitudCompraIf.getReferencia()).iterator();
				if (it.hasNext()) {
					presupuestoIf = (PresupuestoIf) it.next();
					//COMENTED 18 JULIO
					//ordenMedioIf = null;
					getBtnBuscarReferencia().setEnabled(true);
				}
			}//COMENTED 18 JULIO
			/* else if (NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS.equals(solicitudCompraIf.getTipoReferencia())) {
				getCmbTipoReferencia().setSelectedItem(NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS);
				Iterator it = SessionServiceLocator.getOrdenMedioSessionService().findOrdenMedioByCodigo(solicitudCompraIf.getReferencia()).iterator();
				if (it.hasNext()) {
					ordenMedioIf = (OrdenMedioIf) it.next();
					presupuestoIf = null;
					getBtnBuscarReferencia().setEnabled(true);
				}
			}*///END COMENTED 18 JULO 
			else if (TIPO_REFERENCIA_NINGUNO.equals(solicitudCompraIf.getTipoReferencia())) {
				getCmbTipoReferencia().setSelectedItem(NOMBRE_TIPO_REFERENCIA_NINGUNO);
				presupuestoIf = null;
				//COMENTED 18 JULIO
				//ordenMedioIf = null;
				getBtnBuscarReferencia().setEnabled(false);
			}
			
			getTxtReferencia().setText(solicitudCompraIf.getReferencia());
			cleanTablaDetalle();
			//if(!TIPO_REFERENCIA_NINGUNO.equals(solicitudCompraIf.getTipoReferencia())){
				cargarTablaDetalle();
			//}
			
			// Cargo los archivos
			Collection solicitudCompraArchivoColeccion = SessionServiceLocator.getSolicitudCompraArchivoSessionService().findSolicitudCompraArchivoBySolicitudCompraId(solicitudCompraIf.getId());
			Iterator solicitudCompraArchivoColeccionIt = solicitudCompraArchivoColeccion.iterator();

			modelSolicitudCompraArchivo = (DefaultTableModel) getTblArchivo().getModel();

			while (solicitudCompraArchivoColeccionIt.hasNext()) {
				SolicitudCompraArchivoIf solicitudCompraArchivoIf = (SolicitudCompraArchivoIf) solicitudCompraArchivoColeccionIt.next();

				Vector<String> filaArchivo = new Vector<String>();

				archivosSolicitudCompraColeccion.add(solicitudCompraArchivoIf);				
				archivosColeccion.add(null);

				TipoArchivoIf tipoArchivo = (TipoArchivoIf) SessionServiceLocator.getTipoArchivoSessionService().getTipoArchivo(solicitudCompraArchivoIf.getTipoArchivoId());
				filaArchivo.add(tipoArchivo.getNombre());
				
				if(solicitudCompraArchivoIf.getUrl() != null) 
					filaArchivo.add(solicitudCompraArchivoIf.getUrl());
				else 
					filaArchivo.add("");
					
				modelSolicitudCompraArchivo.addRow(filaArchivo);
			}
			
			showUpdateMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarTablaDetalle() {
		try {
			Collection detalles = SessionServiceLocator.getSolicitudCompraDetalleSessionService().findSolicitudCompraDetalleBySolicitudcompraId(solicitudCompraIf.getId());
			Iterator detallesIterator = detalles.iterator();
			
			if (!getVectorSolicitudCompraDetalleIf().isEmpty())
				getVectorSolicitudCompraDetalleIf().removeAllElements();
			
			tableDetalleModel = (DefaultTableModel) getTblDetalle().getModel();
			while (detallesIterator.hasNext()) {
				SolicitudCompraDetalleIf solicitudCompraDetalleIf = (SolicitudCompraDetalleIf) detallesIterator.next();				
				Vector<String> fila = new Vector<String>();
				getVectorSolicitudCompraDetalleIf().add(solicitudCompraDetalleIf);
				agregarColumnasTablaDetalle(solicitudCompraDetalleIf, fila);
				tableDetalleModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTablaDetalle(SolicitudCompraDetalleIf solicitudCompraDetalleIf, Vector<String> fila) {
		try {
			ProductoIf productoIf = SessionServiceLocator.getProductoSessionService().getProducto(solicitudCompraDetalleIf.getProductoId());
			ClienteOficinaIf oficinaProveedor = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(productoIf.getProveedorId());
			ClienteIf proveedor = SessionServiceLocator.getClienteSessionService().getCliente(oficinaProveedor.getClienteId());
			fila.add(proveedor.getIdentificacion() + " - " + proveedor.getNombreLegal());
			GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(productoIf.getGenericoId());
			if ("S".equals(generico.getUsaLote())) {
				PresentacionIf presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(productoIf.getPresentacionId());
				fila.add(productoIf.getCodigo() + " - " + generico.getNombre()+ " - " + presentacion.getNombre());
			} else
				fila.add(productoIf.getCodigo() + " - " + generico.getNombre());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		fila.add(solicitudCompraDetalleIf.getCantidad().toString());
	}
	
	public void save() {
		try {
			if (validateFields()) {
				SolicitudCompraIf solicitudCompra = registrarSolicitudCompra();
				List<SolicitudCompraDetalleIf> solicitudCompraDetalleColeccion = (List<SolicitudCompraDetalleIf>) generarSolicitudCompraDetalleColleccion();
				SolicitudCompraIf solicitudCompraIf = SessionServiceLocator.getSolicitudCompraSessionService().procesarSolicitudCompra(solicitudCompra, solicitudCompraDetalleColeccion, Parametros.getIdEmpresa());
				
				//Para los archivos
				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					String nombreCarpetaArchivos = Parametros.getUsuario();
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
					
					for (File archivo: archivosColeccion){
		    			if(!archivo.getName().equals("")){
		    				FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
	    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+nombreCarpetaArchivos.replaceAll(" ", "_")+slashUrl, archivo.getName());
	    					if (n == 3){
	    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
	    					}		    				
		    			}
		    		}
					SessionServiceLocator.getSolicitudCompraSessionService().actualizarArchivosSolicitudCompra(solicitudCompraIf, archivosSolicitudCompraColeccion, archivosEliminadosColeccion, Parametros.getRutaWindowsCarpetaServidor()+nombreCarpetaArchivos.replaceAll(" ", "_")+slashRuta+slashRuta);
				}
				
				SpiritAlert.createAlert("Solicitud de Compra guardada con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(),SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				registrarSolicitudCompraForUpdate();
				List<SolicitudCompraDetalleIf> solicitudCompraDetalleColeccion = (List<SolicitudCompraDetalleIf>) generarSolicitudCompraDetalleColleccionForUpdate();
				SessionServiceLocator.getSolicitudCompraSessionService().actualizarSolicitudCompra(solicitudCompraIf, solicitudCompraDetalleColeccion, solicitudCompraDetalleEliminadas, idTareaSolicitudCompra);
				
				//Para los archivos
				if ( !Parametros.getUrlCarpetaServidor().equals("") ){
					String nombreCarpetaArchivos = Parametros.getUsuario();
					String slashUrl = Parametros.getUrlCarpetaServidor().substring(Parametros.getUrlCarpetaServidor().length()-1,Parametros.getUrlCarpetaServidor().length());
					String slashRuta = Parametros.getRutaWindowsCarpetaServidor().substring(Parametros.getRutaWindowsCarpetaServidor().length()-1,Parametros.getRutaWindowsCarpetaServidor().length());
					
					for (File archivo: archivosColeccion){
		    			if((archivo != null) && (!archivo.getName().equals(""))){
		    				FileInputStreamSerializable archivoFuente = new FileInputStreamSerializable(archivo);
	    					int n = SessionServiceLocator.getFileManagerSessionService().guardarArchivoZip(archivoFuente, Parametros.getUrlCarpetaServidor()+nombreCarpetaArchivos.replaceAll(" ", "_")+slashUrl, archivo.getName());
	    					if (n == 3){
	    						SpiritAlert.createAlert("Archivo: "+archivo.getName()+" no se guard\u00f3",SpiritAlert.WARNING);
	    					}		    				
		    			}
		    		}
					SessionServiceLocator.getSolicitudCompraSessionService().actualizarArchivosSolicitudCompra(solicitudCompraIf, archivosSolicitudCompraColeccion, archivosEliminadosColeccion, Parametros.getRutaWindowsCarpetaServidor()+nombreCarpetaArchivos.replaceAll(" ", "_")+slashRuta+slashRuta);
				}
				
				SpiritAlert.createAlert("Solicitud de Compra actualizada con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getSolicitudCompraSessionService().eliminarSolicitudCompra(solicitudCompraIf.getId(),idTareaSolicitudCompra);
			SpiritAlert.createAlert("Solicitud de Compra eliminada con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			showSaveMode();
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}
	
	public void authorize() {
		/*try {
			SessionServiceLocator.getSolicitudCompraSessionService().autorizarSolicitudCompra(true, idTareaSolicitudCompra);
			SpiritAlert.createAlert("Autorizacion realizada con éxito!!", SpiritAlert.INFORMATION);
			showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			e.printStackTrace();
		}*/
	}
	
	private SolicitudCompraIf registrarSolicitudCompra(){
		SolicitudCompraData data = new SolicitudCompraData();
		java.sql.Date fechaCreacion = new java.sql.Date(getCmbFechaEntrega().getDate().getTime());
		String codigo = getCodigoSolicitudCompra(new java.sql.Date(fechaCreacion.getYear(), fechaCreacion.getMonth(), fechaCreacion.getDate()));
		data.setCodigo(codigo);
		data.setOficinaId(Parametros.getIdOficina());
		try {
			data.setFecha(new java.sql.Date(Utilitarios.dateHoy().getTime()));
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		data.setUsuarioId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		data.setBodegaId(((BodegaIf)getCmbBodega().getSelectedItem()).getId());
		data.setEmpleadoId(empleadoIf.getId());
		data.setFechaEntrega(new java.sql.Date(getCmbFechaEntrega().getDate().getTime()));
		data.setTipoReferencia(getCmbTipoReferencia().getSelectedItem().toString().substring(0,1));
		data.setReferencia(getTxtReferencia().getText());
		
		if(getCmbTipoReferencia().getSelectedItem().toString().equals(NOMBRE_TIPO_REFERENCIA_NINGUNO)
				&& (getVectorSolicitudCompraDetalleIf().size() <= 0)){
			data.setEstado(ESTADO_PENDIENTE);
		}else{
			data.setEstado(ESTADO_ACTIVO);
		}
		
		if (getTxtSolicitud().getText() != null)
			data.setObservacion(getTxtSolicitud().getText());
		
		return data;
	}
	
	private String getCodigoSolicitudCompra(java.sql.Date fechaSolicitudCompra) {
		String codigo = "";
		String anioSolicitudCompra = Utilitarios.getYearFromDate(fechaSolicitudCompra);
		codigo += anioSolicitudCompra + "-";
		return codigo;
	}
	
	private void registrarSolicitudCompraForUpdate() {
		solicitudCompraIf.setOficinaId(Parametros.getIdOficina());
		try {
			solicitudCompraIf.setFecha(new java.sql.Date(Utilitarios.dateHoy().getTime()));
		} catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		solicitudCompraIf.setUsuarioId(((UsuarioIf)Parametros.getUsuarioIf()).getId());
		solicitudCompraIf.setBodegaId(((BodegaIf)getCmbBodega().getSelectedItem()).getId());
		solicitudCompraIf.setEmpleadoId(empleadoIf.getId());
		solicitudCompraIf.setFechaEntrega(new java.sql.Date(getCmbFechaEntrega().getDate().getTime()));
		solicitudCompraIf.setTipoReferencia(getCmbTipoReferencia().getSelectedItem().toString().substring(0,1));
		solicitudCompraIf.setReferencia(getTxtReferencia().getText());
		
		if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO))
			solicitudCompraIf.setEstado(ESTADO_ACTIVO);
		else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_INACTIVO))
			solicitudCompraIf.setEstado(ESTADO_INACTIVO);
		else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_PENDIENTE))
			solicitudCompraIf.setEstado(ESTADO_PENDIENTE);
		else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_APROBADO))
			solicitudCompraIf.setEstado(ESTADO_APROBADO);
		else if (getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_REALIZADO))
			solicitudCompraIf.setEstado(ESTADO_REALIZADO);
		
		if (getTxtSolicitud().getText() != null)
			solicitudCompraIf.setObservacion(getTxtSolicitud().getText());
	}
	
	private Collection generarSolicitudCompraDetalleColleccion() {
		Collection solicitudCompraDetalleColeccion = new ArrayList<SolicitudCompraDetalleIf>();
		
		for (int i = 0; i < getVectorSolicitudCompraDetalleIf().size(); i++) {
			SolicitudCompraDetalleData solicitudCompraDetalle = new SolicitudCompraDetalleData();
			solicitudCompraDetalle.setId(getVectorSolicitudCompraDetalleIf().get(i).getId());
			solicitudCompraDetalle.setSolicitudcompraId(getVectorSolicitudCompraDetalleIf().get(i).getSolicitudcompraId());
			solicitudCompraDetalle.setProductoId(getVectorSolicitudCompraDetalleIf().get(i).getProductoId());
			solicitudCompraDetalle.setCantidad(getVectorSolicitudCompraDetalleIf().get(i).getCantidad());
			solicitudCompraDetalleColeccion.add(solicitudCompraDetalle);
		}
		
		return solicitudCompraDetalleColeccion;
	}
	
	private Collection generarSolicitudCompraDetalleColleccionForUpdate() {
		Collection solicitudCompraDetalleColeccion = new ArrayList<SolicitudCompraDetalleIf>();
		
		for (int i = 0; i < getVectorSolicitudCompraDetalleIf().size(); i++) {
			SolicitudCompraDetalleData solicitudCompraDetalle = new SolicitudCompraDetalleData();
			solicitudCompraDetalle.setId(getVectorSolicitudCompraDetalleIf().get(i).getId());
			solicitudCompraDetalle.setSolicitudcompraId(solicitudCompraIf.getId());
			solicitudCompraDetalle.setProductoId(getVectorSolicitudCompraDetalleIf().get(i).getProductoId());
			solicitudCompraDetalle.setCantidad(getVectorSolicitudCompraDetalleIf().get(i).getCantidad());
			solicitudCompraDetalleColeccion.add(solicitudCompraDetalle);
		}
		
		return solicitudCompraDetalleColeccion;
	}
	
	private void verificarDetalles(ClienteOficinaIf proveedor) {
		try {
			tableDetalleModel = (DefaultTableModel) getTblDetalle().getModel();
			Iterator it = vectorSolicitudCompraDetalleIf.iterator();
			int i = 0;
			
			while (it.hasNext()) {
				SolicitudCompraDetalleIf detalle = (SolicitudCompraDetalleIf) it.next();
				ProductoIf producto = SessionServiceLocator.getProductoSessionService().getProducto(detalle.getProductoId());
				ClienteOficinaIf proveedorProducto = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(producto.getProveedorId());
				if (proveedorProducto.getId().compareTo(proveedor.getId()) != 0) {
					tableDetalleModel.removeRow(i);
					i--;
					it.remove();
					solicitudCompraDetalleEliminadas.add(detalle);
				}
				
				i++;
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void clean() {
		getTxtCodigo().setText("");
		//getCmbBodega().setSelectedIndex(-1);
		getTxtEmpleado().setText("");
		presupuestoIf = null;
		//COMENTED 18 JULIO
		//ordenMedioIf = null;
		getTxtReferencia().setText("");
		getTxtSolicitud().setText("");
		getTxtArchivo().setText("");
		cleanTablaDetalle();
		cleanTableArchivo();
		
		try {
			calendarFechaEntrega.setTime(Utilitarios.dateHoy());
			getCmbFechaEntrega().setCalendar(calendarFechaEntrega);
			getCmbFechaEntrega().setFormat(Utilitarios.setFechaUppercase());		
		} catch (ParseException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}
	
	public void cleanDetalle(){
		getTxtProducto().setText("");
		getTxtCantidad().setText("");
	}
	
	private void cleanTableArchivo() {
		DefaultTableModel model = (DefaultTableModel) this.getTblArchivo().getModel();
		
		for (int i = this.getTblArchivo().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}
	
	public void cleanTablaDetalle(){
		DefaultTableModel model = (DefaultTableModel) this.getTblDetalle().getModel();

		for (int i = this.getTblDetalle().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);

		getVectorSolicitudCompraDetalleIf().clear();
		cleanDetalle();
		this.repaint();
	}
	
	public void report() {
		// TODO Auto-generated method stub
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpSolicitudCompra().getSelectedIndex();
		int tabCount = this.getJtpSolicitudCompra().getTabCount();
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpSolicitudCompra().setSelectedIndex(selectedTab);
	}
	
	public boolean validateFields() {
		String strEmpleado = getTxtEmpleado().getText();
		String strReferencia = getTxtReferencia().getText();
		
		if(getCmbBodega().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar una Bodega !!", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getCmbBodega().grabFocus();
			return false;
		}
		if((("".equals(strEmpleado)) || (strEmpleado == null))){
			SpiritAlert.createAlert("Debe seleccionar un Empleado !!", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getBtnEmpleado().grabFocus();
			return false;
		}
		if ((("".equals(getCmbFechaEntrega().getSelectedItem())) || (getCmbFechaEntrega().getSelectedItem() == null))) {
			SpiritAlert.createAlert("Debe escoger una Fecha de Entrega !!",	SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getCmbFechaEntrega().grabFocus();
			return false;
		}
		if(getCmbEstado().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Estado !!", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getCmbEstado().grabFocus();
			return false;
		}
		if (vectorSolicitudCompraDetalleIf.isEmpty() && (tipoReferencia != TIPO_REFERENCIA_NINGUNO)) {
			SpiritAlert.createAlert("Debe agregar por lo menos un Detalle !!", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(1);
			getBtnProducto().grabFocus();
			return false;
		}
		
		/*if ("".equals(strReferencia)) {
			SpiritAlert.createAlert("Debe ingresar la referencia !!", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getTxtReferencia().grabFocus();
			return false;
		}*/
		
		if (NOMBRE_TIPO_REFERENCIA_PRESUPUESTO.equals(getCmbTipoReferencia().getSelectedItem().toString()) && presupuestoIf == null) {
			SpiritAlert.createAlert("Debe ingresar un presupuesto válido", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getBtnBuscarReferencia().grabFocus();
			return false;
		}
		
		//COMENTED 18 JULIO
		/*if (NOMBRE_TIPO_REFERENCIA_ORDEN_MEDIOS.equals(getCmbTipoReferencia().getSelectedItem().toString()) && ordenMedioIf == null) {
			SpiritAlert.createAlert("Debe ingresar una orden de medios válida", SpiritAlert.WARNING);
			getJtpSolicitudCompra().setSelectedIndex(0);
			getBtnBuscarReferencia().grabFocus();
			return false;
		}*////END COMENTED 18 JULIO
		
		return true;
	}
	
	public boolean verificarDetallesSolicitudCompra(Vector solicitudCompraDetalleColeccion) {
		Iterator detallesIterator = getVectorSolicitudCompraDetalleIf().iterator();
		int i = 0;
		
		while (detallesIterator.hasNext()) {
			SolicitudCompraDetalleIf solicitudCompraDetalle = (SolicitudCompraDetalleIf) detallesIterator.next();
			setSolicitudCompraDetalle(solicitudCompraDetalle);
			setActualizarDetalle(true);
			if (!validateDetalleFields()) {
				int fila = getTblDetalle().convertRowIndexToModel(i);
				getTblDetalle().getSelectionModel().setSelectionInterval(fila, fila);
				return false;
			}
			
			i++;
		}
		
		return true;
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void showSaveMode() {
		getTxtCodigo().setBackground(getBackground());
		getTxtCodigo().setEditable(false);
		getCmbBodega().setBackground(Parametros.saveUpdateColor);
		getTxtEmpleado().setBackground(getBackground());
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		
		if(getCmbEstado().getItemCount()>0)
			getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		
		setSaveMode();
		clean();
		cleanDetalle();
		getBtnProducto().setEnabled(true);
		vectorSolicitudCompraDetalleIf.clear();
		getVectorSolicitudCompraDetalleIf().clear();
		solicitudCompraDetalleEliminadas.clear();
		archivosSolicitudCompraColeccion.clear();
		archivosColeccion.clear();
		archivosNombreColeccion.clear();
		archivosEliminadosColeccion.clear();
		getCmbFechaEntrega().setEnabled(true);
		getTxtSolicitud().setEnabled(true);
		getTxtCantidad().setEnabled(true);
		getJtpSolicitudCompra().setSelectedIndex(0);
		getCmbBodega().grabFocus();
	}
	
	public void showFindMode() {
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtCodigo().setEditable(true);
		getCmbBodega().setBackground(Parametros.findColor);
		getTxtEmpleado().setBackground(Parametros.findColor);
		getCmbEstado().setBackground(Parametros.findColor);
		getCmbFechaEntrega().setEnabled(false);
		getTxtSolicitud().setEnabled(false);
		getTxtCantidad().setEnabled(false);
		clean();
		cleanDetalle();
		getJtpSolicitudCompra().setSelectedIndex(0);
		getCmbEstado().setSelectedIndex(-1);
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		getTxtCodigo().setBackground(getBackground());
		getTxtCodigo().setEditable(false);
		getCmbBodega().setBackground(Parametros.saveUpdateColor);
		getTxtEmpleado().setBackground(getBackground());
		getCmbEstado().setBackground(Parametros.saveUpdateColor);
		setUpdateMode();
		getCmbFechaEntrega().setEnabled(true);
		getTxtSolicitud().setEnabled(true);
		getTxtCantidad().setEnabled(true);
		getBtnProducto().setEnabled(true);
		getJtpSolicitudCompra().setSelectedIndex(0);
	}
	
	public void addDetail() {
		agregarDetalle();
	}
	
	public void updateDetail() {
		actualizarDetalle();
	}
	
	public void deleteDetail() {
		removerDetalle();
	}
	
	public void refresh() {
		cargarComboBodega();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public boolean isActualizarDetalle() {
		return actualizarDetalle;
	}
	
	public void setActualizarDetalle(boolean actualizarDetalle) {
		this.actualizarDetalle = actualizarDetalle;
	}
	
	public SolicitudCompraDetalleIf getSolicitudCompraDetalleIf() {
		return solicitudCompraDetalleIf;
	}
	
	public void setSolicitudCompraDetalleIf(SolicitudCompraDetalleIf solicitudCompraDetalleIf) {
		this.solicitudCompraDetalleIf = solicitudCompraDetalleIf;
	}
	
	public int getSolicitudCompraDetalleSeleccionada() {
		return solicitudCompraDetalleSeleccionada;
	}
	
	public void setSolicitudCompraDetalleSeleccionada(int solicitudCompraDetalleSeleccionada) {
		this.solicitudCompraDetalleSeleccionada = solicitudCompraDetalleSeleccionada;
	}
	
	public Vector<SolicitudCompraDetalleIf> getVectorSolicitudCompraDetalleIf() {
		return vectorSolicitudCompraDetalleIf;
	}
	
	public void setVectorSolicitudCompraDetalleIf(Vector<SolicitudCompraDetalleIf> vectorSolicitudCompraDetalleIf) {
		this.vectorSolicitudCompraDetalleIf = vectorSolicitudCompraDetalleIf;
	}
}
