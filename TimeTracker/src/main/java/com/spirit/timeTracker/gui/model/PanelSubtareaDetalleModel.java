package com.spirit.timeTracker.gui.model;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFormattedTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.Parametros;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.timeTracker.componentes.GenericCellRenderer;
import com.spirit.timeTracker.componentes.TiempoCellRenderer;
import com.spirit.timeTracker.componentes.TimeCellEditor;
import com.spirit.timeTracker.gui.main.JPSubtareasDetalle;

public class PanelSubtareaDetalleModel extends JPSubtareasDetalle {

	private static final long serialVersionUID = 1L;

	public static int COLUMNA_FECHA = 0;
	public static int COLUMNA_HORA_INICIO = 1;
	public static int COLUMNA_HORA_FIN = 2;
	public static int COLUMNA_TIEMPO = 3;

	public PanelSubtareaDetalleModel() {
		modificarTabla();
	}

	private void modificarTabla() {
		GenericCellRenderer cellRendererCentrado = new GenericCellRenderer(
				GenericCellRenderer.CENTER);
		TiempoCellRenderer tiempoCellRenderer = new TiempoCellRenderer(true);

		// MODO DE SELECCION DE FILAS
		getTblSubtareaDetalle().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// FECHA
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA).setPreferredWidth(100);
		//getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA).setMinWidth(150);
		// getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA).setMaxWidth(65);

		// HORA INICIO
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setCellRenderer(cellRendererCentrado);
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setPreferredWidth(50);
		//getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setMinWidth(50);
		//getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_INICIO).setMaxWidth(50);

		// HORA FIN
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setCellRenderer(cellRendererCentrado);
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setPreferredWidth(50);
		//getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setMinWidth(50);
		//getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_HORA_FIN).setMaxWidth(50);

		// TIEMPO
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO).setPreferredWidth(50);
		// getTblSubTareas().getColumnModel().getColumn(COLUMNA_TIEMPO).setMinWidth(55);
		
		getTblSubtareaDetalle().setModel(
				new DefaultTableModel(new Object[][] {}, new String[] {
						"Fecha", "Hora Inicio", "Hora Fin", "Tiempo" }) {

					public boolean isCellEditable(int rowIndex, int columnIndex) {
						if (columnIndex != COLUMNA_TIEMPO) {
							return false;
						} else {
							// INGRESAR LOS CONTROLES PARA EDITAR
							UsuarioIf usuarioIf = (UsuarioIf) Parametros
									.getUsuarioIf();
							if ("SEJ".contains(usuarioIf.getTipousuarioTimetracker())) {
								return true;
							} else
								return false;
						}
					}
				});
		JFormattedTextField textField = new JFormattedTextField();
		final TimeCellEditor timeCellEditor = new TimeCellEditor(textField,
				getTblSubtareaDetalle());
		timeCellEditor.setClickCountToStart(2);
		textField.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				timeCellEditor.cancelCellEditing();

			}

			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA)
		.setMinWidth(120);
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_FECHA)
		.setMaxWidth(120);
		
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO)
				.setCellEditor(timeCellEditor);
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO)
				.setCellRenderer(tiempoCellRenderer);
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO)
				.setMinWidth(60);
		getTblSubtareaDetalle().getColumnModel().getColumn(COLUMNA_TIEMPO)
				.setMaxWidth(60);
	}
}
