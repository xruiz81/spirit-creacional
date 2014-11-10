package com.spirit.timeTracker.gui.model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import timeTracker.tiempo.SubTarea;

import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.timeTracker.gui.main.JPInfoSubtarea;
import com.spirit.timeTracker.gui.model.cache.MapaCache;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.TextChecker;

public class InfoSubtareaModel extends JPInfoSubtarea {

	private static final long serialVersionUID = 1L;

	private SubTarea subTarea = null;
	private static final int MAX_LONGITUD_DESCRIPCION = 3000;
	
	public InfoSubtareaModel() {
	}

	public InfoSubtareaModel(SubTarea subTarea) {
		this.subTarea = subTarea;
		arreglarBotonGuardar();
		getBtnGuardar().addActionListener(guardarActionListener);
		getTxtDescripcion().addKeyListener(
				new TextChecker(MAX_LONGITUD_DESCRIPCION, true));

		getCmbUsrAsignado().setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel c = (JLabel) super.getListCellRendererComponent(list,
						value, index, isSelected, cellHasFocus);
				if (value instanceof EmpleadoIf) {
					EmpleadoIf empleadoIf = (EmpleadoIf) value;
					c.setText(empleadoIf.getNombres() + " "
							+ empleadoIf.getApellidos());
				}
				return c;
			}
		});

		OrdenTrabajoDetalleIf ordenTrabajoDetalleIf;
		try {
			ordenTrabajoDetalleIf = SessionServiceLocator
					.getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(
							subTarea.getTiempoParcial().getIdOrdenTrabajoDetalle());
			refreshCombo(
					getCmbUsrAsignado(),
					MapaCache
							.obtenerEquipoTrabajoPorEmpleadoId(ordenTrabajoDetalleIf
									.getAsignadoaId()));
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		getTxtId().setText(
		// subTarea.getId() != 0 &&
				(subTarea.getId() != null) ? String.valueOf(subTarea.getId())
						: "---");

		getTxtFechaInicio().setText(
				timeTracker.tiempo.Utiles.convertirFecha(
						subTarea.getFechaInicio()).toString());

		getTxtFechaFin().setText(
				timeTracker.tiempo.Utiles
						.convertirFecha(subTarea.getFechaFin()).toString());

		getTxtDescripcion().setText(subTarea.getDescripcion());
		if (subTarea.getUsuarioAsignadoId() != null) {
			getCmbUsrAsignado().setSelectedIndex(
					ComboBoxComponent.getIndexToSelectItem(getCmbUsrAsignado(),
							subTarea.getUsuarioAsignadoId()));
		} else{
			//Muy importante que este seleccionado un usuario para que no grabe usuario asignado con null
			getCmbUsrAsignado().setSelectedIndex(0);
		}
	}

	ActionListener guardarActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			EmpleadoIf empleadoIf = (EmpleadoIf) getCmbUsrAsignado()
					.getSelectedItem();
			if (empleadoIf != null) {
				subTarea.setUsuarioAsignadoId(empleadoIf.getId());
			} else {
				SpiritAlert.createAlert(
						"Debe asignar la tarea a alguien del equipo.",
						SpiritAlert.WARNING);
				getCmbUsrAsignado().requestFocus();
				return;
			}

			subTarea.setDescripcion(getTxtDescripcion().getText());
			Utiles.getTablaSubTareasGlobal().setValueAt(
					subTarea.getDescripcion(),
					Utiles.getTablaSubTareasGlobal().getSelectedRow(),
					PanelListaSubTareasModel.COLUMNA_DESCRIPCION_SUBTAREA);
		}
	};

	private void arreglarBotonGuardar() {
		getBtnGuardar().setText(" ");
		getBtnGuardar().setToolTipText("Guardar informacion subtarea");
		getBtnGuardar().setIcon(
				SpiritResourceManager
						.getImageIcon("images/timetracker/saveSubtask.png"));

		// DefaultTableModel modeloTablaSubtarea = (DefaultTableModel)
		// Utiles.getTablaSubTareasGlobal().getModel();

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void showSaveMode() {
		// TODO Auto-generated method stub

	}

	public void showFindMode() {
		// TODO Auto-generated method stub

	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void addDetail() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	public void report() {
		// TODO Auto-generated method stub

	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

}
