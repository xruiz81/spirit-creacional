package com.spirit.rrhh.gui.model;

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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.record.formula.TblPtg;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.general.gui.model.DepartamentoModel;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.rrhh.entity.EmpleadoOrganizacionData;
import com.spirit.rrhh.entity.EmpleadoOrganizacionIf;
import com.spirit.rrhh.entity.EmpleadoVacacionesIf;

import com.spirit.rrhh.gui.panel.JPOrganizacion;
import com.spirit.util.Archivos;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.ExtensionFileFilter;
import com.spirit.util.FileInputStreamSerializable;
import com.spirit.util.LabelAccessory;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class OrganizacionModel extends JPOrganizacion {
	private static final int MAX_LONGITUD_DESCRIPCION = 300;
	public static Locale esLocale = new Locale("es");
	protected EmpleadoIf empleadoIf;
	private File selectedFile;
	private String si = "Si";
	private String no = "No";
	private Object[] options = { si, no };
	private Vector<EmpleadoOrganizacionIf> empleadoOrganizacionVector = new Vector<EmpleadoOrganizacionIf>();
	private Vector<EmpleadoOrganizacionIf> empleadoOrganizacionEliminadasVector = new Vector<EmpleadoOrganizacionIf>();
	private DefaultTableModel modelOrganizacion;
	private DefaultTableModel modelOrganizacionReportes;
	private Vector<File> archivosOrganizacion = new Vector<File>();
	private Map<Long, EmpleadoIf> mapaEmpleado = new HashMap<Long, EmpleadoIf>();
	private Map<Long, OficinaIf> mapaOficina = new HashMap<Long, OficinaIf>();
	protected EmpleadoOrganizacionIf empleadoOrganizacionIf;
	final JPopupMenu popupArchivoOrganizacion = new JPopupMenu();
	private static final String TODOS = "TODOS";

	private Map<Long, DepartamentoIf> mapaDepartamento = new HashMap<Long, DepartamentoIf>();

	public OrganizacionModel() {
		initKeyListeners();
		initListeners();
		cargarComboDepartamento();
		cargarComboTipoEmpleado();
		showSaveMode();
		cargarMapas();
		cargarComboDepartamentoReporte();
		cargarComboOficina();
	}

	public void initKeyListeners() {
		getBtnEmpleado().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnEmpleado().setToolTipText("Buscar Empleado");
		getBtnArchivo().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/attachFile.png"));
		getBtnArchivo().setToolTipText("Agregar Archivo");
		getBtnVisualizarArchivo().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnVisualizarArchivo().setToolTipText("Visualizar Archivo");
		getBtnConsultar().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/consultar.png"));
		getBtnConsultar().setToolTipText("Consultar");

		getBtnAgregarDetalle().setText("");
		getBtnActualizarDetalle().setText("");
		getBtnEliminarDetalle().setText("");
		getBtnAgregarDetalle().setIcon(
				SpiritResourceManager
						.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAgregarDetalle().setToolTipText("Agregar Detalle");
		getBtnActualizarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnActualizarDetalle().setToolTipText("Actualizar Detalle");
		getBtnEliminarDetalle()
				.setIcon(
						SpiritResourceManager
								.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnEliminarDetalle().setToolTipText("Eliminar Detalle");

		getCmbFechaInicio().setLocale(Utilitarios.esLocale);
		getCmbFechaFin().setLocale(Utilitarios.esLocale);
		getCmbFechaInicio().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaFin().setFormat(Utilitarios.setFechaUppercase());
		getCmbFechaInicio().setEditable(false);
		getCmbFechaFin().setEditable(false);
		getCmbFechaInicio().setShowNoneButton(false);
		getCmbFechaFin().setShowNoneButton(false);

		getTxtDescripcion().addKeyListener(
				new TextChecker(MAX_LONGITUD_DESCRIPCION, true, 0));
	}

	private void cargarComboOficina() {
		try {
			List oficinas = (ArrayList) SessionServiceLocator
					.getOficinaSessionService().findOficinaByEmpresaId(
							Parametros.getIdEmpresa());
			oficinas.add(TODOS);
			refreshCombo(getCmbOficina(), oficinas);
			getCmbOficina().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void cargarComboDepartamentoReporte() {
		try {
			List departamentos = (ArrayList) SessionServiceLocator
					.getDepartamentoSessionService()
					.findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(departamentos, ordenadorDepartamentoNombre);
			departamentos.add(TODOS);
			refreshCombo(getCmbDepartamento(), departamentos);
			getCmbDepartamento().setSelectedItem(TODOS);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void cargarComboDepartamento() {
		try {
			List departamentos = (ArrayList) SessionServiceLocator
					.getDepartamentoSessionService()
					.findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(departamentos, ordenadorDepartamentoNombre);
			refreshCombo(getCmbDepartamentoEmpleado(), departamentos);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void cargarComboTipoEmpleado() {
		try {
			List tiposEmpleados = (List) SessionServiceLocator
					.getTipoEmpleadoSessionService()
					.findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
			Collections.sort(tiposEmpleados, ordenadorEmpleadoNombre);
			refreshCombo(getCmbCargo(), tiposEmpleados);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error",
					SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	Comparator<TipoEmpleadoIf> ordenadorEmpleadoNombre = new Comparator<TipoEmpleadoIf>() {
		public int compare(TipoEmpleadoIf te1, TipoEmpleadoIf te2) {
			if (te1.getNombre() == null) {
				return -1;
			} else if (te2.getNombre() == null) {
				return 1;
			} else {
				return (te1.getNombre().compareTo(te2.getNombre()));
			}
		}
	};

	Comparator<EmpleadoIf> ordenadorEmpleadoPorApellido = new Comparator<EmpleadoIf>() {
		public int compare(EmpleadoIf e1, EmpleadoIf e2) {
			return e1.getApellidos().compareTo(e2.getApellidos());
		}
	};

	Comparator<DepartamentoIf> ordenadorDepartamentoNombre = new Comparator<DepartamentoIf>() {
		public int compare(DepartamentoIf te1, DepartamentoIf te2) {
			if (te1.getNombre() == null) {
				return -1;
			} else if (te2.getNombre() == null) {
				return 1;
			} else {
				return (te1.getNombre().compareTo(te2.getNombre()));
			}
		}
	};

	private void initListeners() {

		/*
		 * getCmbFechaFin().addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { Date fechaInicio =
		 * getCmbFechaInicio().getDate(); Date fechaFin =
		 * getCmbFechaFin().getDate();
		 * 
		 * if(!fechaFin.after(fechaInicio)){ SpiritAlert.createAlert("La fecha
		 * fin debe ser después de la fecha inicio, se colocara a la fecha fin
		 * un día despues de la fecha inicio", SpiritAlert.INFORMATION);
		 * getCmbFechaFin().setDate(getCmbFechaInicio().getDate());
		 * getCmbFechaFin().getCalendar().add(Calendar.DAY_OF_MONTH, 1); } } });
		 */
		getBtnConsultar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				cargarTablaReportes();
			}
		});
		getBtnAgregarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarDetalle();
				// cleanPeriodoOrganizacion();
			}
		});
		getBtnActualizarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblOrganizacion().getSelectedRow() != -1) {
					actualizarDetalle();
					cleanPeriodoOrganizacion();
				} else {
					SpiritAlert.createAlert(
							"Debe seleccionar una fila de la tabla.",
							SpiritAlert.INFORMATION);
				}
			}
		});

		getBtnEliminarDetalle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getTblOrganizacion().getSelectedRow() != -1) {
					eliminarDetalle();
					cleanPeriodoOrganizacion();
				} else {
					SpiritAlert.createAlert(
							"Debe seleccionar una fila de la tabla.",
							SpiritAlert.INFORMATION);
				}
			}
		});

		getBtnEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoIf = null;
				EmpleadoCriteria empleadoCriteria = new EmpleadoCriteria(
						"Empleados", Parametros.getIdEmpresa());
				JDPopupFinderModel popupFinder = new JDPopupFinderModel(
						Parametros.getMainFrame(), empleadoCriteria,
						JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if (popupFinder.getElementoSeleccionado() != null) {
					clean();
					empleadoIf = (EmpleadoIf) popupFinder
							.getElementoSeleccionado();
					getSelectedObject();
				}
			}
		});
		getBtnArchivo().addActionListener(oActionListenerAgregarArchivo);

		getBtnVisualizarArchivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					if (!getTxtArchivo().getText().equals(""))
						Archivos.abrirArchivoDesdeServidor(getTxtArchivo()
								.getText());
				} catch (IOException e) {
					SpiritAlert.createAlert("Se ha producido un error",
							SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});
		getTblOrganizacion().addMouseListener(oMouseAdapterTblOrganizacion);
		getTblOrganizacion().addKeyListener(oKeyAdapterTblOrganizacion);
	}

	MouseListener oMouseAdapterTblOrganizacion = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3)
					&& getTblOrganizacion().getModel().getRowCount() > 0)
				popupArchivoOrganizacion.show(evt.getComponent(), evt.getX(),
						evt.getY());
			else
				enableSelectedRowForUpdate(evt);
		}
	};

	public static Date stringToDate(String cadena) throws ParseException {

		Date fecha = null;
		SimpleDateFormat ts = new SimpleDateFormat("dd-MMM-yyyy", esLocale);

		try {
			fecha = new Date(ts.parse(cadena).getTime());

		} catch (ParseException e) {
			// SpiritAlert.createAlert("Se ha producido un error",
			// SpiritAlert.ERROR);
			e.printStackTrace();
			throw new ParseException(e.getMessage(), 0);
		}

		// System.out.println("Fecha:-" + fecha);
		return fecha;

	}

	KeyListener oKeyAdapterTblOrganizacion = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	public void cleanPeriodoOrganizacion() {

		getCmbFechaInicio().setCalendar(new GregorianCalendar());
		getCmbFechaFin().setCalendar(new GregorianCalendar());

		getTxtDescripcion().setText("");
		getTxtArchivo().setText("");
		getCmbDepartamentoEmpleado().removeAll();
		getCmbCargo().removeAll();
		cargarComboDepartamento();
		cargarComboTipoEmpleado();
	}

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (getTblOrganizacion().getSelectedRow() != -1) {
			EmpleadoOrganizacionIf empleadoOrganizacionIf = (EmpleadoOrganizacionIf) empleadoOrganizacionVector
					.get(((JTable) evt.getSource()).getSelectedRow());

			try {
				modelOrganizacion = (DefaultTableModel) getTblOrganizacion()
						.getModel();

				getCmbFechaInicio().setDate(
						stringToDate(modelOrganizacion.getValueAt(
								getTblOrganizacion().getSelectedRow(), 2)
								.toString()));
				getCmbFechaInicio().validate();
				getCmbFechaInicio().repaint();
				getCmbFechaFin().setDate(
						stringToDate(modelOrganizacion.getValueAt(
								getTblOrganizacion().getSelectedRow(), 3)
								.toString()));
				getCmbFechaInicio().validate();
				getCmbFechaInicio().repaint();
				// DepartamentoIf departamento =
				// SessionServiceLocator.getDepartamentoSessionService().getDepartamento(empleadoOrganizacionIf.getDepartamento());
				List<DepartamentoIf> departamentos = (ArrayList<DepartamentoIf>) SessionServiceLocator
						.getDepartamentoSessionService()
						.findDepartamentoByNombre(
								modelOrganizacion.getValueAt(
										getTblOrganizacion().getSelectedRow(),
										0).toString());

				// getCmbDepartamentoEmpleado().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDepartamentoEmpleado(),departamento.getId()));
				getCmbDepartamentoEmpleado().setSelectedIndex(
						ComboBoxComponent.getIndexToSelectItem(
								getCmbDepartamentoEmpleado(), departamentos
										.get(0).getId()));
				getCmbDepartamentoEmpleado().validate();
				getCmbDepartamentoEmpleado().repaint();

				// TipoEmpleadoIf tipoEmpleado =
				// SessionServiceLocator.getTipoEmpleadoSessionService().getTipoEmpleado(empleadoOrganizacionIf.getTipoEmpleadoId());
				List<TipoEmpleadoIf> tiposEmpleado = (ArrayList<TipoEmpleadoIf>) SessionServiceLocator
						.getTipoEmpleadoSessionService()
						.findTipoEmpleadoByNombre(
								modelOrganizacion.getValueAt(
										getTblOrganizacion().getSelectedRow(),
										1).toString());

				// getCmbCargo().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCargo(),tipoEmpleado.getId()));
				getCmbCargo().setSelectedIndex(
						ComboBoxComponent.getIndexToSelectItem(getCmbCargo(),
								tiposEmpleado.get(0).getId()));
				getCmbCargo().validate();
				getCmbCargo().repaint();

			} catch (Exception e) {
				System.out.print(e);
			}

			getTxtDescripcion().setText(
					modelOrganizacion.getValueAt(
							getTblOrganizacion().getSelectedRow(), 4)
							.toString());
			if (modelOrganizacion.getValueAt(getTblOrganizacion()
					.getSelectedRow(), 5) == null)
				getTxtArchivo().setText("");
			else
				getTxtArchivo().setText(
						modelOrganizacion.getValueAt(
								getTblOrganizacion().getSelectedRow(), 5)
								.toString());
		}
	}

	/**
	 * Action Listener que me permite adjuntar un archivo
	 */

	ActionListener oActionListenerAgregarArchivo = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {

			Component parent = (Component) actionEvent.getSource();
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAccessory(new LabelAccessory(fileChooser));

			FileFilter filterJPG = new ExtensionFileFilter(null, new String[] {
					"JPG", "JPEG" });
			fileChooser.addChoosableFileFilter(filterJPG);
			FileFilter filterGIF = new ExtensionFileFilter("gif",
					new String[] { "gif" });
			fileChooser.addChoosableFileFilter(filterGIF);
			FileFilter filterDOC = new ExtensionFileFilter("doc",
					new String[] { "doc" });
			fileChooser.addChoosableFileFilter(filterDOC);
			fileChooser.setFileFilter(fileChooser.getAcceptAllFileFilter());
			int status = fileChooser.showOpenDialog(parent);

			if (status == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				boolean existe;
				try {
					existe = SessionServiceLocator
							.getFileManagerSessionService().existeArchivo(
									Parametros.getUrlCarpetaServidor(),
									selectedFile.getName());
					if (!existe) {
						/**
						 * valido que botón ha sido presionado y le asigno al
						 * correspondiente textbox el path del archivo que haya
						 * seleccionado
						 */
						if ((actionEvent.getSource() == getBtnArchivo()))
							getTxtArchivo().setText(
									fileChooser.getSelectedFile()
											.getAbsolutePath());

						/**
						 * ejecuto el archivo con su respectivo programa para
						 * poder ser previsualizado
						 */
						try {
							int opcion = JOptionPane.showOptionDialog(null,
									"Desea previsualizar el archivo?",
									"Confirmación", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE, null,
									options, no);
							if (opcion == JOptionPane.YES_OPTION) {
								String filename = selectedFile
										.getAbsolutePath();
								Archivos.abrirArchivoLocal(filename);
							}
						} catch (IOException e) {
							SpiritAlert.createAlert("Se ha producido un error",
									SpiritAlert.ERROR);
							e.printStackTrace();
						}
					} else {
						int opcion = JOptionPane.showOptionDialog(null,
								"Archivo ya existe, desea reemplazarlo?",
								"Confirmación", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								"No");
						if (opcion == JOptionPane.YES_OPTION) {
							if ((actionEvent.getSource() == getBtnArchivo()))
								getTxtArchivo().setText(
										fileChooser.getSelectedFile()
												.getAbsolutePath());
						} else {
							if ((actionEvent.getSource() == getBtnArchivo()))
								getTxtArchivo().setText("");
						}
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
					SpiritAlert.createAlert(e1.getMessage(), SpiritAlert.ERROR);
				}
			} else if (status == JFileChooser.CANCEL_OPTION) {

			}
		}
	};

	private void eliminarDetalle() {
		if (getTblOrganizacion().getSelectedRow() != -1) {
			int filaSeleccionada = getTblOrganizacion().getSelectedRow();

			//
			if ((filaSeleccionada <= (empleadoOrganizacionVector.size() - 1))
					&& (filaSeleccionada >= 0)) {
				EmpleadoOrganizacionIf empleadoOrganizacion = empleadoOrganizacionVector
						.get(filaSeleccionada);
				if (empleadoOrganizacion.getId() != null) {
					empleadoOrganizacionEliminadasVector
							.add(empleadoOrganizacion);
				}

				archivosOrganizacion.remove(getTblOrganizacion()
						.getSelectedRow());
				empleadoOrganizacionVector.remove(getTblOrganizacion()
						.getSelectedRow());
				modelOrganizacion.removeRow(getTblOrganizacion()
						.getSelectedRow());
			} else {
				SpiritAlert.createAlert(
						"Erro al eliminar, indice fuera de rango.",
						SpiritAlert.WARNING);
			}

		} else {
			SpiritAlert.createAlert("Debe elegir el registro a eliminar.",
					SpiritAlert.WARNING);
		}
	}

	public void getSelectedObject() {

		// seteo empleado
		getTxtEmpleado().setText(
				empleadoIf.getNombres() + " " + empleadoIf.getApellidos());

		try {
			// busco fecha Ingreso
			// java.sql.Date fechaInicioContrato = null;

			/*
			 * busco contrato Map aMap = new HashMap(); aMap.put("estado", "A");
			 * aMap.put("empleadoId", empleadoIf.getId()); Collection contratos =
			 * SessionServiceLocator.getContratoSessionService().findContratoByQuery(aMap);
			 * Iterator contratosIt = contratos.iterator();
			 * while(contratosIt.hasNext()){ ContratoIf contrato =
			 * (ContratoIf)contratosIt.next(); java.sql.Date fechaInicio =
			 * contrato.getFechaInicio();
			 * 
			 * if(fechaInicioContrato == null ||
			 * fechaInicio.compareTo(fechaInicioContrato) == 1){
			 * fechaInicioContrato = fechaInicio; } }
			 */

			// ////////REGISTROS DE Organizacion////////////////////
			modelOrganizacion = (DefaultTableModel) getTblOrganizacion()
					.getModel();

			ArrayList empleadoOrganizacion = (ArrayList) SessionServiceLocator
					.getEmpleadoOrganizacionSessionService()
					.findEmpleadoOrganizacionByEmpleadoId(empleadoIf.getId());
			Iterator empleadoOrganizacionIt = empleadoOrganizacion.iterator();

			// ordenar en forma descendente segun saldo de dias
			// Collections.sort((ArrayList)empleadoOrganizacion,ordenadorEmpleadoOrganizacionPorSaldoDias);

			while (empleadoOrganizacionIt.hasNext()) {
				EmpleadoOrganizacionIf empleadoOrganizacionIf = (EmpleadoOrganizacionIf) empleadoOrganizacionIt
						.next();

				empleadoOrganizacionVector.add(empleadoOrganizacionIf);

				// se pone un null por los archivos que ya estan en el servidor
				archivosOrganizacion.add(null);

				Vector<String> filaDetalle = new Vector<String>();
				List departamentos = (ArrayList) SessionServiceLocator
						.getDepartamentoSessionService()
						.findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
				Iterator departamentoIt = departamentos.iterator();
				String dep = "";
				while (departamentoIt.hasNext()) {
					DepartamentoIf departamentoIf = (DepartamentoIf) departamentoIt
							.next();
					if (departamentoIf.getId().equals(
							empleadoOrganizacionIf.getDepartamento()))
						dep = departamentoIf.getNombre();

				}
				List tiposEmpleados = (List) SessionServiceLocator
						.getTipoEmpleadoSessionService()
						.findTipoEmpleadoByEmpresaId(Parametros.getIdEmpresa());
				Iterator tipoEmpleadoIt = tiposEmpleados.iterator();
				String car = "";
				while (tipoEmpleadoIt.hasNext()) {
					TipoEmpleadoIf tipoEmpleadoIf = (TipoEmpleadoIf) tipoEmpleadoIt
							.next();
					if (tipoEmpleadoIf.getId().equals(
							empleadoOrganizacionIf.getTipoEmpleadoId()))
						car = tipoEmpleadoIf.getNombre();

				}

				ArrayList departamento = (ArrayList) SessionServiceLocator
						.getDepartamentoSessionService()
						.findDepartamentoByCodigo(
								empleadoOrganizacionIf.getDepartamento()
										.toString());
				ArrayList cargo = (ArrayList) SessionServiceLocator
						.getTipoEmpleadoSessionService()
						.findTipoEmpleadoByCodigo(
								empleadoOrganizacionIf.getTipoEmpleadoId()
										.toString());
				Date fechaInicio = empleadoOrganizacionIf.getFechaInicio();
				Date fechaFin = empleadoOrganizacionIf.getFechaFin();

				filaDetalle.add(dep);
				filaDetalle.add(car);
				filaDetalle.add(Utilitarios
						.getFechaCortaUppercase(empleadoOrganizacionIf
								.getFechaInicio()));
				filaDetalle.add(Utilitarios
						.getFechaCortaUppercase(empleadoOrganizacionIf
								.getFechaFin()));
				filaDetalle.add(empleadoOrganizacionIf.getDescripcion());
				filaDetalle.add(empleadoOrganizacionIf.getArchivoAdjunto());

				modelOrganizacion.addRow(filaDetalle);

			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	public void agregarDetalle() {
		try {
			if (validateFieldsDetalle(true)) {

				agregandoDetalle();

			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo ingresar el detalle.",
					SpiritAlert.ERROR);
		}
	}

	public void actualizarDetalle() {
		try {
			if (validateFieldsDetalle(false)) {

				int fila = getTblOrganizacion().getSelectedRow();
				empleadoOrganizacionIf = (EmpleadoOrganizacionIf) empleadoOrganizacionVector
						.get(fila);

				EmpleadoOrganizacionData data = new EmpleadoOrganizacionData();

				data.setEmpleadoId(empleadoIf.getId());

				data.setFechaInicio(new java.sql.Timestamp(getCmbFechaInicio()
						.getDate().getTime()));
				data.setFechaFin(new java.sql.Timestamp(getCmbFechaFin()
						.getDate().getTime()));
				DepartamentoIf departamentoif = (DepartamentoIf) getCmbDepartamentoEmpleado()
						.getSelectedItem();
				data.setDepartamento(departamentoif.getId());
				TipoEmpleadoIf tipoEmpleadoIf = (TipoEmpleadoIf) getCmbCargo()
						.getSelectedItem();
				data.setTipoEmpleadoId(tipoEmpleadoIf.getId());
				data.setDescripcion(this.getTxtDescripcion().getText());

				if (!getTxtArchivo().getText().equals("")
						&& selectedFile != null)
					data.setArchivoAdjunto(selectedFile.getPath());

				if (selectedFile != null) {
					archivosOrganizacion.add(selectedFile);
				}
				empleadoOrganizacionEliminadasVector
						.add(empleadoOrganizacionVector.get(fila));
				empleadoOrganizacionVector.add(data);
				// empleadoOrganizacionVector.set(fila, data);

				// tabla
				modelOrganizacion.setValueAt(departamentoif.getNombre(), fila,
						0);
				modelOrganizacion.setValueAt(tipoEmpleadoIf.getNombre(), fila,
						1);

				modelOrganizacion.setValueAt(Utilitarios
						.getFechaCortaUppercase(getCmbFechaInicio().getDate()),
						fila, 2);
				modelOrganizacion.setValueAt(Utilitarios
						.getFechaCortaUppercase(getCmbFechaFin().getDate()),
						fila, 3);

				modelOrganizacion.setValueAt(getTxtDescripcion().getText(),
						fila, 4);
				modelOrganizacion
						.setValueAt(getTxtArchivo().getText(), fila, 5);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se pudo ingresar el detalle.",
					SpiritAlert.ERROR);
		}
	}

	public boolean validateFieldsDetalle(boolean agregarDetalle) throws GenericBusinessException {
		Date fechaInicio = getCmbFechaInicio().getDate();
		Date fechaFin = getCmbFechaFin().getDate();
		

		if (empleadoIf == null) {
			SpiritAlert.createAlert("Debe seleccionar un empleado.",
					SpiritAlert.WARNING);
			getBtnEmpleado().grabFocus();
			return false;
		}

		if (fechaFin.before(fechaInicio)) {
			SpiritAlert.createAlert(
					"La Fecha Fin no puede estar antes de la Fecha Inicio.",
					SpiritAlert.WARNING);
			getCmbFechaFin().grabFocus();
			return false;
		}

		int filas = modelOrganizacion.getRowCount();
		int fila = 0;
		long filaseleccionada = Long.parseLong(getTblOrganizacion().getSelectedRow()+"");
		List<EmpleadoOrganizacionIf> empleados = new ArrayList<EmpleadoOrganizacionIf>();
		while (fila < filas) {
			ArrayList empleadoOrganizacion = (ArrayList) SessionServiceLocator
			.getEmpleadoOrganizacionSessionService()
			.findEmpleadoOrganizacionByEmpleadoId(empleadoIf.getId());
			Iterator empleadoOrganizacionIt = empleadoOrganizacion.iterator();

	// ordenar en forma descendente segun saldo de dias
	// Collections.sort((ArrayList)empleadoOrganizacion,ordenadorEmpleadoOrganizacionPorSaldoDias);
			EmpleadoOrganizacionIf empleado=null;
	while (empleadoOrganizacionIt.hasNext()) {
		empleado = (EmpleadoOrganizacionIf) empleadoOrganizacionIt
				.next();
	}

			try {
				empleado.setId(Long.parseLong(fila+""));
				empleado.setFechaInicio(new java.sql.Timestamp(stringToDate(
						modelOrganizacion.getValueAt(fila, 2).toString())
						.getTime()));

				empleado.setFechaFin(new java.sql.Timestamp(stringToDate(
						modelOrganizacion.getValueAt(fila, 3).toString())
						.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			empleados.add(empleado);
			fila++;
		}
		for (EmpleadoOrganizacionIf empleadoOrganizacion : empleados) {
			Date fechaInicioTemp = empleadoOrganizacion.getFechaInicio();
			Date fechaFinTemp = empleadoOrganizacion.getFechaFin();
			if (agregarDetalle) {
				if (!((fechaInicio.before(fechaInicioTemp) && fechaFin
						.before(fechaInicioTemp)) || (fechaFinTemp
						.before(fechaInicio) && fechaFinTemp.before(fechaFin)))) {
					SpiritAlert.createAlert(
							"La Fecha Fin se cruza con fechas previas.",
							SpiritAlert.WARNING);
					getCmbFechaInicio().grabFocus();
					return false;
				}
			} else {
				if (!(empleadoOrganizacion.getId().equals(filaseleccionada)) && 
				(!((fechaInicio.before(fechaInicioTemp) && fechaFin
						.before(fechaInicioTemp)) || (fechaFinTemp
						.before(fechaInicio) && fechaFinTemp.before(fechaFin))))) {
					SpiritAlert.createAlert(
							"La Fecha Fin se cruza con fechas previas.",
							SpiritAlert.WARNING);
					getCmbFechaInicio().grabFocus();
					return false;
				}

			}
		}

		return true;
	}

	public void cargarMapas() {
		try {
			mapaEmpleado.clear();
			Collection empleados = SessionServiceLocator
					.getEmpleadoSessionService().findEmpleadoByEmpresaId(
							Parametros.getIdEmpresa());
			Iterator empleadosIt = empleados.iterator();
			while (empleadosIt.hasNext()) {
				EmpleadoIf empleadoIf = (EmpleadoIf) empleadosIt.next();
				mapaEmpleado.put(empleadoIf.getId(), empleadoIf);
			}

			mapaDepartamento.clear();
			Collection departamentos = SessionServiceLocator
					.getDepartamentoSessionService()
					.findDepartamentoByEmpresaId(Parametros.getIdEmpresa());
			Iterator departamentosIt = departamentos.iterator();
			while (departamentosIt.hasNext()) {
				DepartamentoIf departamentoIf = (DepartamentoIf) departamentosIt
						.next();
				mapaDepartamento.put(departamentoIf.getId(), departamentoIf);
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}

	private void agregandoDetalle() throws GenericBusinessException {
		modelOrganizacion = (DefaultTableModel) getTblOrganizacion().getModel();

		Vector<String> filaDetalle = new Vector<String>();

		DepartamentoIf departamentoSeleccionado = (DepartamentoIf) getCmbDepartamentoEmpleado()
				.getSelectedItem();
		Long idDepartamentoSeleccionado = departamentoSeleccionado.getId();

		TipoEmpleadoIf tipoEmpleadoSeleccionado = (TipoEmpleadoIf) getCmbCargo()
				.getSelectedItem();
		Long idTipoEmpleadoSeleccionado = tipoEmpleadoSeleccionado.getId();

		filaDetalle.add(getCmbDepartamentoEmpleado().getSelectedItem()
				.toString());
		filaDetalle.add(getCmbCargo().getSelectedItem().toString());

		filaDetalle.add(Utilitarios.getFechaCortaUppercase(getCmbFechaInicio()
				.getDate()));
		filaDetalle.add(Utilitarios.getFechaCortaUppercase(getCmbFechaFin()
				.getDate()));

		filaDetalle.add(getTxtDescripcion().getText());
		filaDetalle.add(getTxtArchivo().getText());

		modelOrganizacion.addRow(filaDetalle);

		EmpleadoOrganizacionData data = new EmpleadoOrganizacionData();

		data.setEmpleadoId(empleadoIf.getId());

		data.setDepartamento(idDepartamentoSeleccionado);
		data.setTipoEmpleadoId(idTipoEmpleadoSeleccionado);
		data.setFechaInicio(new java.sql.Timestamp(getCmbFechaInicio()
				.getDate().getTime()));
		data.setFechaFin(new java.sql.Timestamp(getCmbFechaFin().getDate()
				.getTime()));
		data.setDescripcion(this.getTxtDescripcion().getText());

		if (!getTxtArchivo().getText().equals(""))
			data.setArchivoAdjunto(selectedFile.getPath());

		if (selectedFile != null) {
			archivosOrganizacion.add(selectedFile);
		} else {
			// necesario sino se cae al eliminar detalles
			archivosOrganizacion.add(null);
		}

		empleadoOrganizacionVector.add(data);
		cleanPeriodoOrganizacion();
	}

	private void cleanTableOrganizacion() {
		DefaultTableModel model = (DefaultTableModel) getTblOrganizacion()
				.getModel();
		for (int i = this.getTblOrganizacion().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	private void cleanTableOrganizacionReporte() {
		DefaultTableModel model = (DefaultTableModel) getTblOrganizacionReporte()
				.getModel();
		for (int i = this.getTblOrganizacionReporte().getRowCount(); i > 0; --i)
			model.removeRow(i - 1);
	}

	@Override
	public void clean() {
		empleadoOrganizacionVector = new Vector<EmpleadoOrganizacionIf>();
		empleadoOrganizacionEliminadasVector = new Vector<EmpleadoOrganizacionIf>();
		empleadoIf = null;
		empleadoOrganizacionIf = null;

		getTxtEmpleado().setText("");
		getTxtArchivo().setText("");

		cleanPeriodoOrganizacion();

		cleanTableOrganizacion();
		cleanTableOrganizacionReporte();

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
	public void report() {
		// TODO Auto-generated method stub

	}

	public void save() {
		try {
			if (empleadoOrganizacionVector.size() > 0
					|| empleadoOrganizacionEliminadasVector.size() > 0) {

				// archivos
				Collection<FileInputStreamSerializable> archivosColeccion = new Vector<FileInputStreamSerializable>();
				for (File archivo : archivosOrganizacion) {
					if (archivo != null) {
						archivosColeccion.add(new FileInputStreamSerializable(
								archivo, archivo.getName()));
					} else
						archivosColeccion.add(null);
				}

				String slashUrl = Parametros.getUrlCarpetaServidor().substring(
						Parametros.getUrlCarpetaServidor().length() - 1,
						Parametros.getUrlCarpetaServidor().length());
				String slashRuta = Parametros.getRutaWindowsCarpetaServidor()
						.substring(
								Parametros.getRutaWindowsCarpetaServidor()
										.length() - 1,
								Parametros.getRutaWindowsCarpetaServidor()
										.length());

				SessionServiceLocator.getEmpleadoOrganizacionSessionService()
						.procesarEmpleadoOrganizacion(
								empleadoOrganizacionVector,
								empleadoOrganizacionEliminadasVector,
								archivosColeccion,
								Parametros.getUrlCarpetaServidor(),
								Parametros.getRutaWindowsCarpetaServidor());
				SpiritAlert.createAlert("Organizacion grabadas con éxito.",
						SpiritAlert.INFORMATION);
				showSaveMode();

			} else {
				SpiritAlert.createAlert("No existen datos para grabar.",
						SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}

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

		clean();
		setSaveMode();

	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {

	}

	public void cargarTablaReportes() {
		cleanTableOrganizacionReporte();

		modelOrganizacionReportes = (DefaultTableModel) getTblOrganizacionReporte()
				.getModel();

		Long idOficina = 0L;
		Long idDepartamento = 0L;
		if (!getCmbOficina().getSelectedItem().equals(TODOS)) {
			OficinaIf oficinaIf = (OficinaIf) getCmbOficina().getSelectedItem();
			idOficina = oficinaIf.getId();
		}
		if (!getCmbDepartamento().getSelectedItem().equals(TODOS)) {
			DepartamentoIf departamentoIf = (DepartamentoIf) getCmbDepartamento()
					.getSelectedItem();
			idDepartamento = departamentoIf.getId();
		}

		//try {
			// vector empleadosReporte
			List<EmpleadoIf> empleadosOrganizacionReporte = new ArrayList<EmpleadoIf>();

			Collection OrganizacionReporte = null; /*SessionServiceLocator
					.getEmpleadoOrganizacionSessionService()
					.findEmpleadoOrganizacionByOficinaIdAndByDepartamentoId(
							idOficina, idDepartamento);*/
			Iterator OrganizacionReporteIt = OrganizacionReporte.iterator();
			while (OrganizacionReporteIt.hasNext()) {
				EmpleadoOrganizacionIf empleadoOrganizacionIf = (EmpleadoOrganizacionIf) OrganizacionReporteIt
						.next();

				Date fechaInicio = empleadoOrganizacionIf.getFechaInicio();
				Date fechaFin = empleadoOrganizacionIf.getFechaFin();
				// empleadosOrganizacionReporte.add(empleadoOrganizacionIf);
			}

			// ordeno vector empleado
			// Collections.sort(empleadosOrganizacionReporte,
			// ordenadorEmpleadoPorApellido);

			// recorro el vector
			for (EmpleadoIf empleado : empleadosOrganizacionReporte) {

				DepartamentoIf departamento = mapaDepartamento.get(empleado
						.getDepartamentoId());
				OficinaIf oficina = mapaOficina.get(empleado.getOficinaId());
				Vector<String> filaDetalle = new Vector<String>();

				/*
				 * String nombre = empleado.getNombres().split(" ")[0];
				 * if(nombre.equals("MARIA")){ nombre = empleado.getNombres(); }
				 */

				String apellido = empleado.getApellidos().split(" ")[0];

				filaDetalle.add(apellido + " " + empleado.getNombres());

				filaDetalle.add(oficina.getNombre().replaceAll("CREACIONAL/",
						""));
				filaDetalle.add(departamento.getNombre());

				modelOrganizacionReportes.addRow(filaDetalle);
			}

		/*}

		catch (GenericBusinessException e) {
			e.printStackTrace();
		}*/

	}

}
