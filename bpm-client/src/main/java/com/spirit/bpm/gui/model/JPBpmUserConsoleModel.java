package com.spirit.bpm.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.spirit.bpm.gui.helper.ComentarioListRender;
import com.spirit.bpm.gui.helper.IconStateManager;
import com.spirit.bpm.gui.helper.TareasContextMenu;
import com.spirit.bpm.gui.panel.JDAsignarTarea;
import com.spirit.bpm.gui.panel.JDDescripcion;
import com.spirit.bpm.gui.panel.JPBpmUserConsole;
import com.spirit.bpm.gui.tbl.TareaTableModel;
import com.spirit.bpm.impl.ProcesoManager;
import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.BamApi;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elements.UserBpm;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.general.entity.UsuarioIf;

public class JPBpmUserConsoleModel extends JPBpmUserConsole {
	private static final long serialVersionUID = 1L;
	private TareaTableModel<Tarea> tareaTableModel = null;
	private SpiritBPMConnectorIf spiritBPMConnectorIf;
	private ClientParams clientParams = null;
	private final JPanel me = this;
	private ListSelectionListener tableSelectionListener = new ListSelectionListener() {

		public void valueChanged(ListSelectionEvent e) {
			if (e == null || !e.getValueIsAdjusting()) {
				selectTarea((Tarea) tareaTableModel.getSelected());
			}
		}
	};

	public JPBpmUserConsoleModel() {
		setName("INBOX");
		init();
	}

	@Override
	public void refresh() {
		try {
			consultarTareas();
		} catch (BPMException e) {
			handleException(e);
		}
	}

	private void handleException(BPMException bpmException) {
		bpmException.printStackTrace();
		SpiritAlert.createAlert(bpmException.getMessage(), SpiritAlert.ERROR);
	}

	private void actualizarBam() throws BPMException {
		BamApi bam = spiritBPMConnectorIf.getBam(clientParams);
		IconStateManager.setText(lblTareasPendientes, bam.getCasosPendientes());
		IconStateManager.setText(lblTareasVencidas, bam.getCasosPendientes());
		IconStateManager.setText(lblTareasTerminadas, bam.getCasosTerminados());
		IconStateManager.setText(lblFechaBamHasta, bam.getFechaDesde());
	}

	private void init() {
		spiritBPMConnectorIf = ProcesoManager.getBpmClient();
		UsuarioIf usuarioIf = (UsuarioIf) Parametros.getUsuarioIf();
		clientParams = new ClientParams();
		clientParams.setUser(usuarioIf.getUsuario());
		clientParams.setPassword(usuarioIf.getClave());
		try {
			tareaTableModel = new TareaTableModel<Tarea>();
			tareaTableModel.setTableRef(tblBusquedaTareas);
			tblBusquedaTareas.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);

			tblBusquedaTareas.getSelectionModel().addListSelectionListener(
					tableSelectionListener);

			lstComentarios.setCellRenderer(new ComentarioListRender());

			addFocusListener(new FocusListener() {

				//@Override
				public void focusGained(FocusEvent e) {
					refresh();
				}

				//@Override
				public void focusLost(FocusEvent e) {

				}
			});

			final TareasContextMenu busquedaTareasContextMenu = new TareasContextMenu();
			ActionListener actionListener = new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						Tarea tareaSeleccionada = (Tarea) tareaTableModel
								.getSelected();
						if (e.getActionCommand().equalsIgnoreCase("iniciar")) {
							ProcesoManager.iniciarTask(clientParams,tareaSeleccionada);
						} else if (e.getActionCommand().equalsIgnoreCase(
								"asignar")) {
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
						} else if (e.getActionCommand().equalsIgnoreCase("terminar")) {
							if (SpiritAlert.confirmDialog(me,
									"¿Desea dar por terminada la tarea "
											+ tareaSeleccionada.getInfo()+ " ?")) {
								spiritBPMConnectorIf.terminarTarea(clientParams, tareaSeleccionada);
								SpiritAlert.createAlert("Tarea "
												+ tareaSeleccionada.getInfo()
												+ " terminada",
												SpiritAlert.INFORMATION);
							}
						} else if (e.getActionCommand().equalsIgnoreCase(
								"suspender")) {
							if (SpiritAlert.confirmDialog(me,
									"¿Desea suspender la tarea "
											+ tareaSeleccionada.getInfo()
											+ " ?")) {
								spiritBPMConnectorIf.suspenderTarea(
										clientParams, tareaSeleccionada);
								SpiritAlert.createAlert("Tarea "
										+ tareaSeleccionada.getInfo()
										+ " suspendida",
										SpiritAlert.INFORMATION);
							}
						} else if (e.getActionCommand().equalsIgnoreCase(
								"reanudar")) {
							ProcesoManager.continuarTask(clientParams, tareaSeleccionada);
							spiritBPMConnectorIf.reaunudarTarea(clientParams,
									tareaSeleccionada);
						} else if (e.getActionCommand().equalsIgnoreCase(
								"continuar")) {
							ProcesoManager.continuarTask(clientParams,
									tareaSeleccionada);
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
						consultarTareas();
					} catch (BPMException e1) {
						handleException(e1);
					}
				}
			};

			busquedaTareasContextMenu.setListener(actionListener);

			tblBusquedaTareas.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent evt) {
					if (SwingUtilities.isRightMouseButton(evt)) {
						Tarea tareaSeleccionada = (Tarea) tareaTableModel
								.getSelected();
						busquedaTareasContextMenu
								.habilitarOpcionesUser(tareaSeleccionada);
						busquedaTareasContextMenu.show(evt.getComponent(), evt
								.getX(), evt.getY());
					}
				}
			});

			chkTareasPendientes.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						consultarTareas();
					} catch (BPMException e1) {
						handleException(e1);
					}
				}
			});
			consultarTareas();
		} catch (BPMException ex) {
			ex.printStackTrace();
		}
		selectTarea(null);
	}

	@SuppressWarnings("unchecked")
	private void consultarTareas() throws BPMException {
		List<Tarea> tareas = null;
		if (chkTareasPendientes.isSelected()) {
			tareas = spiritBPMConnectorIf
					.consultarTareasPendientes(clientParams);
		} else {
			tareas = spiritBPMConnectorIf.consultarTareas(clientParams);
		}
		tareaTableModel.fillData(tareas);
		actualizarBam();
	}

	private void selectTarea(Tarea tarea) {
		if (tarea == null)
			tarea = new Tarea();
		IconStateManager.setText(lblNombre, tarea.getNombre());
		txaDescripcion.setText(tarea.getDescripcion());
		IconStateManager.setLabel(lblEstado, tarea.getEstado());
		IconStateManager.setLabel(lblPrioridad, tarea.getPrioridad());
		IconStateManager.setText(lblFechaInicio, tarea.getFechaInicio());
		IconStateManager
				.setText(lblFechaAsignacion, tarea.getFechaAsignacion());
		IconStateManager.fechaEsperada(lblFechaEsperada, tarea);
		IconStateManager.setText(lblFechaFin, tarea.getFechaFinalizacion());
		IconStateManager.setText(lblIniciadoPor, tarea.getIniciadoPor());
		IconStateManager.setText(lblProceso, tarea.getProceso());
		IconStateManager
				.setText(lblTiempoEjecucion, tarea.getTiempoEjecucion());

		Object[] listaComentarios = tarea.getComentarios() == null ? new Object[0]
				: tarea.getComentarios().toArray();
		lstComentarios.setListData(listaComentarios);
		pnlTarea.setTitleAt(2, "<html><b>Comentarios("
				+ listaComentarios.length + ")</b></html>");
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
