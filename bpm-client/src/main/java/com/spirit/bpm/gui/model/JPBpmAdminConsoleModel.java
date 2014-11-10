package com.spirit.bpm.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.spirit.bpm.gui.helper.ComentarioListRender;
import com.spirit.bpm.gui.helper.IconStateManager;
import com.spirit.bpm.gui.helper.InstanciaProcesoContextMenu;
import com.spirit.bpm.gui.helper.ProcesoContextMenu;
import com.spirit.bpm.gui.helper.TareasContextMenu;
import com.spirit.bpm.gui.panel.JDAsignarTarea;
import com.spirit.bpm.gui.panel.JDDescripcion;
import com.spirit.bpm.gui.panel.JPBpmAdminConsole;
import com.spirit.bpm.gui.tbl.InstanciaProcesoTableModel;
import com.spirit.bpm.gui.tbl.ProcesoTableModel;
import com.spirit.bpm.gui.tbl.TareaAdminTableModel;
import com.spirit.bpm.impl.ProcesoManager;
import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.BamAdminApi;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Proceso;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elements.UserBpm;
import com.spirit.bpm.process.elemets.state.EnumInstanceState;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.general.entity.UsuarioIf;

public class JPBpmAdminConsoleModel extends JPBpmAdminConsole {

	private TareaAdminTableModel<Tarea> tareaTableModel = null;
	private InstanciaProcesoTableModel<InstanciaProceso> instanciaProcesoTableModel = null;
	private ProcesoTableModel<Proceso> procesoTableModel = null;
	private SpiritBPMConnectorIf spiritBPMConnectorIf;
	private ClientParams clientParams = null;
	private final JPanel me = this;
	private ListSelectionListener tableSelectionListener = new ListSelectionListener() {

		public void valueChanged(ListSelectionEvent e) {
			if (e == null || !e.getValueIsAdjusting()) {
				selectInstanciaProceso((InstanciaProceso) instanciaProcesoTableModel
						.getSelected());
			}
		}
	};

	private static final long serialVersionUID = 1L;

	public JPBpmAdminConsoleModel() {
		setName("ADMIN INBOX");
		init();
	}

	private void handleException(BPMException bpmException) {
		bpmException.printStackTrace();
		SpiritAlert.createAlert(bpmException.getMessage(), SpiritAlert.ERROR);
	}

	@Override
	public void refresh() {
		try {
			consultarInstanciasProceso();
		} catch (BPMException e) {
			handleException(e);
		}
	}

	private void init() {
		spiritBPMConnectorIf = ProcesoManager.getBpmClient();
		UsuarioIf usuarioIf = (UsuarioIf) Parametros.getUsuarioIf();
		clientParams = new ClientParams();
		clientParams.setUser(usuarioIf.getUsuario());
		clientParams.setPassword(usuarioIf.getClave());
		try {
			tareaTableModel = new TareaAdminTableModel<Tarea>();
			tareaTableModel.setTableRef(tblTareas);
			tblTareas.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);

			addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					refresh();
				}
			});

			instanciaProcesoTableModel = new InstanciaProcesoTableModel<InstanciaProceso>();
			instanciaProcesoTableModel.setTableRef(tblInstanciaProcesos);
			tblInstanciaProcesos.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			tblInstanciaProcesos.getSelectionModel().addListSelectionListener(
					tableSelectionListener);

			procesoTableModel = new ProcesoTableModel<Proceso>();
			procesoTableModel.setTableRef(tblProcesos);
			tblProcesos.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);
			procesoTableModel.fillData(spiritBPMConnectorIf
					.obtenerProcesos(clientParams));
			lstComentarios.setCellRenderer(new ComentarioListRender());

			chkPendientes.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						consultarInstanciasProceso();
					} catch (BPMException e1) {
						handleException(e1);
					}
				}
			});

			final TareasContextMenu busquedaTareasContextMenu = new TareasContextMenu();
			ActionListener actionListener = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						Tarea tareaSeleccionada = (Tarea) tareaTableModel
								.getSelected();
						if (e.getActionCommand().equalsIgnoreCase("asignar")) {
							JDAsignarTarea jAsignarTarea = new JDAsignarTarea(
									null, true);
							jAsignarTarea.setUserList(spiritBPMConnectorIf
									.getUsers(clientParams));
							jAsignarTarea.setTarea(tareaSeleccionada);
							jAsignarTarea.setLocationRelativeTo(me);
							jAsignarTarea.setVisible(true);
							int returnStatus = jAsignarTarea.getReturnStatus();
							if (returnStatus == JDAsignarTarea.RET_OK) {
								UserBpm userBpm = jAsignarTarea.getUser();
								spiritBPMConnectorIf.asignarTarea(clientParams,
										tareaSeleccionada, userBpm
												.getUsername());
								SpiritAlert.createAlert("Tarea {"
										+ tareaSeleccionada.getNombre()
										+ "} asignada exitosamente a usuario :"
										+ userBpm.getUsername() + " ["
										+ userBpm.getNombreCompleto() + "]",
										SpiritAlert.INFORMATION);
							}
						} else if (e.getActionCommand().equalsIgnoreCase(
								"Comentar")) {
							JDDescripcion jDescripcion = new JDDescripcion(
									null, true);
							jDescripcion.setLocationRelativeTo(me);
							jDescripcion.setVisible(true);
							if (jDescripcion.getReturnStatus() == JDDescripcion.RET_OK) {
								spiritBPMConnectorIf.commentar(clientParams,
										tareaSeleccionada, jDescripcion
												.getDescripcion());
							}
						}
						consultarInstanciasProceso();
					} catch (BPMException e1) {
						handleException(e1);
					}
				}
			};

			busquedaTareasContextMenu.setListener(actionListener);

			tblTareas.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent evt) {
					if (SwingUtilities.isRightMouseButton(evt)) {
						Tarea tareaSeleccionada = (Tarea) tareaTableModel
								.getSelected();
						busquedaTareasContextMenu
								.habilitarOpcionesAdmin(tareaSeleccionada);
						busquedaTareasContextMenu.show(evt.getComponent(), evt
								.getX(), evt.getY());
					}
				}
			});

			final ProcesoContextMenu procesoContextMenu = new ProcesoContextMenu();
			procesoContextMenu.setListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					Proceso proceso = (Proceso) procesoTableModel.getSelected();
					try {
						if (e.getActionCommand().equalsIgnoreCase(
								"nueva instancia")) {
							if (SpiritAlert.confirmDialog(me,
									"¿Desea iniciar una nueva instancia del proceso "
											+ proceso.getNombre() + " ?")) {
								JDDescripcion jDescripcion = new JDDescripcion(
										null, true);
								jDescripcion.setLocationRelativeTo(me);
								jDescripcion.setVisible(true);
								if (jDescripcion.getReturnStatus() == JDDescripcion.RET_OK) {
									ProcesoManager.iniciarInstancia(
											clientParams, proceso, jDescripcion
													.getDescripcion());

									SpiritAlert.createAlert("Proceso "
											+ proceso.getNombre()
											+ " instanciado con exito",
											SpiritAlert.INFORMATION);
									consultarInstanciasProceso();
								}
							}
						}
					} catch (BPMException e1) {
						handleException(e1);
					}
				}
			});

			tblProcesos.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent evt) {
					if (SwingUtilities.isRightMouseButton(evt)) {
						Proceso proceso = (Proceso) procesoTableModel
								.getSelected();
						procesoContextMenu.habilitarOpcionesAdmin(proceso);
						procesoContextMenu.show(evt.getComponent(), evt.getX(),
								evt.getY());
					}
				}
			});

			final InstanciaProcesoContextMenu instanciaProcesoContextMenu = new InstanciaProcesoContextMenu();
			instanciaProcesoContextMenu.setListener(new ActionListener() {
				//@Override
				public void actionPerformed(ActionEvent e) {
					InstanciaProceso instanciaProceso = (InstanciaProceso) instanciaProcesoTableModel
							.getSelected();
					try {
						if (e.getActionCommand().equalsIgnoreCase("cancelar")) {
							if (SpiritAlert.confirmDialog(me,
									"¿Desea cancelar instancia del proceso "
											+ instanciaProceso.getProceso()
											+ " ?")) {

								spiritBPMConnectorIf.cancelarInstanciaProceso(
										clientParams, instanciaProceso);

								SpiritAlert.createAlert("Instancia del proceso"
										+ instanciaProceso.getProceso()
										+ " cancelada con exito",
										SpiritAlert.INFORMATION);
								consultarInstanciasProceso();
							}
						}
					} catch (BPMException e1) {
						handleException(e1);
					}

				}
			});

			tblInstanciaProcesos.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent evt) {
					if (SwingUtilities.isRightMouseButton(evt)) {
						InstanciaProceso proceso = (InstanciaProceso) instanciaProcesoTableModel
								.getSelected();
						instanciaProcesoContextMenu
								.habilitarOpcionesAdmin(proceso);
						instanciaProcesoContextMenu.show(evt.getComponent(),
								evt.getX(), evt.getY());
					}
				}
			});

			consultarInstanciasProceso();
		} catch (BPMException ex) {
			ex.printStackTrace();
		}

		selectInstanciaProceso(null);
	}

	private void actualizarBam() throws BPMException {
		BamAdminApi bamAdminApi = spiritBPMConnectorIf
				.getAdminBam(clientParams);
		IconStateManager.setText(lblProcesosActivos, bamAdminApi
				.getProcesosActivos());
		IconStateManager.setText(lblProcesosTerminados, bamAdminApi
				.getProcesosTerminados());
		IconStateManager.setText(lblFechaBamDesde, bamAdminApi.getFechaDesde());
	}

	@SuppressWarnings("unchecked")
	private void selectInstanciaProceso(InstanciaProceso proceso) {
		if (proceso == null)
			proceso = new InstanciaProceso();
		IconStateManager.setText(lblNombre, proceso.getProceso());
		txaDescripcion.setText(proceso.getDescripcion());
		IconStateManager.setLabel(lblEstado, proceso.getEstado());
		IconStateManager.setText(lblFechaInicio, proceso.getFechaIniciado());
		IconStateManager.setText(lblFechaUltimaAct, proceso
				.getFechaUltimaActualizacion());
		IconStateManager.setText(lblFechaUltimaAct, proceso
				.getFechaUltimaActualizacion());
		IconStateManager.setText(lblTiempoEjecucion, proceso
				.getTiempoEjecucion());
		IconStateManager.setText(lblFechaFin, proceso.getFechaFinalizado());
		IconStateManager.setText(lblIniciadoPor, proceso.getIniciadoPor());
		IconStateManager.setText(lblFinalizadoPor, proceso.getFinalizadoPor());
		Object[] listaComentarios = proceso.getComentarios() == null ? new Object[0]
				: proceso.getComentarios().toArray();
		lstComentarios.setListData(listaComentarios);
		pnlProceso.setTitleAt(2, "<html><b>Comentarios("
				+ listaComentarios.length + ")</b></html>");
		List<Tarea> listaTareas = proceso.getTareas() == null ? new ArrayList<Tarea>()
				: proceso.getTareas();
		tbpnlTareas.setTitleAt(0, "<html><b>Tareas(" + listaTareas.size()
				+ ")</b></html>");
		tareaTableModel.fillData(listaTareas);
	}

	@SuppressWarnings("unchecked")
	private void consultarInstanciasProceso() throws BPMException {
		List<InstanciaProceso> instanciaProcesoList = null;
		if (chkPendientes.isSelected()) {
			instanciaProcesoList = spiritBPMConnectorIf
					.consultarProcesosActivos(clientParams,
							EnumInstanceState.INICIADA);

		} else {
			instanciaProcesoList = spiritBPMConnectorIf
					.consultarProcesosActivos(clientParams,
							EnumInstanceState.ALL);
		}
		instanciaProcesoTableModel.fillData(instanciaProcesoList);
		actualizarBam();
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showSaveMode() {
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

}
