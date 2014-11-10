package com.spirit.medios.gui.panel;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.swing.*;
/*
 * Created by JFormDesigner on Mon Nov 26 15:42:04 COT 2012
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author xruiz
 */
public abstract class JPTimetracker2Actividades extends SpiritModelImpl {
	public JPTimetracker2Actividades() {
		initComponents();
		setName("Timetracker2 Actividades");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpActividades = new JideTabbedPane();
		panelActividad = new JPanel();
		lblCodigoActividad = new JLabel();
		txtCodigoActividad = new JTextField();
		lblEstadoActividad = new JLabel();
		cmbEstadoActividad = new JComboBox();
		lblActividad = new JLabel();
		txtActividad = new JTextField();
		spTblActividad = new JScrollPane();
		tblActividad = new JTable();
		panelEmpleado = new JPanel();
		lblActividadSeleccionada = new JLabel();
		txtActividadSeleccionada = new JTextField();
		lblEstadoEmpleado = new JLabel();
		cmbEstadoEmpleado = new JComboBox();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		btnBorrarEmpleado = new JButton();
		panelBotonesProducto = new JPanel();
		btnAgregarEmpleado = new JButton();
		btnActualizarEmpleado = new JButton();
		btnEliminarEmpleado = new JButton();
		spTblEmpleado = new JScrollPane();
		tblEmpleado = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== jtpActividades ========
		{

			//======== panelActividad ========
			{
				panelActividad.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(120)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblCodigoActividad ----
				lblCodigoActividad.setText("C\u00f3digo:");
				panelActividad.add(lblCodigoActividad, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelActividad.add(txtCodigoActividad, cc.xy(5, 3));

				//---- lblEstadoActividad ----
				lblEstadoActividad.setText("Estado:");
				panelActividad.add(lblEstadoActividad, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbEstadoActividad ----
				cmbEstadoActividad.setModel(new DefaultComboBoxModel(new String[] {
					"ACTIVO",
					"INACTIVO"
				}));
				panelActividad.add(cmbEstadoActividad, cc.xy(11, 3));

				//---- lblActividad ----
				lblActividad.setText("Actividad:");
				panelActividad.add(lblActividad, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelActividad.add(txtActividad, cc.xywh(5, 5, 5, 1));

				//======== spTblActividad ========
				{
					spTblActividad.setPreferredSize(new Dimension(452, 100));

					//---- tblActividad ----
					tblActividad.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null},
						},
						new String[] {
							"C\u00f3digo", "Actividad", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblActividad.setPreferredScrollableViewportSize(new Dimension(450, 300));
					tblActividad.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblActividad.setAutoCreateColumnsFromModel(true);
					spTblActividad.setViewportView(tblActividad);
				}
				panelActividad.add(spTblActividad, cc.xywh(3, 9, 13, 5));
			}
			jtpActividades.addTab("Actividad", panelActividad);


			//======== panelEmpleado ========
			{
				panelEmpleado.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(170)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblActividadSeleccionada ----
				lblActividadSeleccionada.setText("Actividad:");
				panelEmpleado.add(lblActividadSeleccionada, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtActividadSeleccionada ----
				txtActividadSeleccionada.setEditable(false);
				panelEmpleado.add(txtActividadSeleccionada, cc.xywh(5, 3, 3, 1));

				//---- lblEstadoEmpleado ----
				lblEstadoEmpleado.setText("Estado:");
				panelEmpleado.add(lblEstadoEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbEstadoEmpleado ----
				cmbEstadoEmpleado.setModel(new DefaultComboBoxModel(new String[] {
					"ACTIVO",
					"INACTIVO"
				}));
				panelEmpleado.add(cmbEstadoEmpleado, cc.xy(5, 5));

				//---- lblEmpleado ----
				lblEmpleado.setText("Empleado:");
				panelEmpleado.add(lblEmpleado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEmpleado.add(txtEmpleado, cc.xywh(5, 7, 3, 1));
				panelEmpleado.add(btnBuscarEmpleado, cc.xywh(9, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				panelEmpleado.add(btnBorrarEmpleado, cc.xywh(11, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== panelBotonesProducto ========
				{
					panelBotonesProducto.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarEmpleado ----
					btnAgregarEmpleado.setText("A");
					panelBotonesProducto.add(btnAgregarEmpleado, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- btnActualizarEmpleado ----
					btnActualizarEmpleado.setText("U");
					panelBotonesProducto.add(btnActualizarEmpleado, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- btnEliminarEmpleado ----
					btnEliminarEmpleado.setText("E");
					panelBotonesProducto.add(btnEliminarEmpleado, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				panelEmpleado.add(panelBotonesProducto, cc.xywh(3, 11, 5, 1));

				//======== spTblEmpleado ========
				{
					spTblEmpleado.setPreferredSize(new Dimension(452, 100));

					//---- tblEmpleado ----
					tblEmpleado.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null},
						},
						new String[] {
							"Empleado", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblEmpleado.setPreferredScrollableViewportSize(new Dimension(450, 300));
					tblEmpleado.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblEmpleado.setAutoCreateColumnsFromModel(true);
					spTblEmpleado.setViewportView(tblEmpleado);
				}
				panelEmpleado.add(spTblEmpleado, cc.xywh(3, 13, 11, 5));
			}
			jtpActividades.addTab("Empleado", panelEmpleado);

		}
		add(jtpActividades, cc.xywh(1, 1, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpActividades;
	private JPanel panelActividad;
	private JLabel lblCodigoActividad;
	private JTextField txtCodigoActividad;
	private JLabel lblEstadoActividad;
	private JComboBox cmbEstadoActividad;
	private JLabel lblActividad;
	private JTextField txtActividad;
	private JScrollPane spTblActividad;
	private JTable tblActividad;
	private JPanel panelEmpleado;
	private JLabel lblActividadSeleccionada;
	private JTextField txtActividadSeleccionada;
	private JLabel lblEstadoEmpleado;
	private JComboBox cmbEstadoEmpleado;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JButton btnBorrarEmpleado;
	private JPanel panelBotonesProducto;
	private JButton btnAgregarEmpleado;
	private JButton btnActualizarEmpleado;
	private JButton btnEliminarEmpleado;
	private JScrollPane spTblEmpleado;
	private JTable tblEmpleado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideTabbedPane getJtpActividades() {
		return jtpActividades;
	}

	public JPanel getPanelActividad() {
		return panelActividad;
	}

	public JLabel getLblCodigoActividad() {
		return lblCodigoActividad;
	}

	public JTextField getTxtCodigoActividad() {
		return txtCodigoActividad;
	}

	public JLabel getLblEstadoActividad() {
		return lblEstadoActividad;
	}

	public JComboBox getCmbEstadoActividad() {
		return cmbEstadoActividad;
	}

	public JLabel getLblActividad() {
		return lblActividad;
	}

	public JTextField getTxtActividad() {
		return txtActividad;
	}

	public JScrollPane getSpTblActividad() {
		return spTblActividad;
	}

	public JTable getTblActividad() {
		return tblActividad;
	}

	public JPanel getPanelEmpleado() {
		return panelEmpleado;
	}

	public JLabel getLblActividadSeleccionada() {
		return lblActividadSeleccionada;
	}

	public JTextField getTxtActividadSeleccionada() {
		return txtActividadSeleccionada;
	}

	public JLabel getLblEstadoEmpleado() {
		return lblEstadoEmpleado;
	}

	public JComboBox getCmbEstadoEmpleado() {
		return cmbEstadoEmpleado;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public JButton getBtnBorrarEmpleado() {
		return btnBorrarEmpleado;
	}

	public JPanel getPanelBotonesProducto() {
		return panelBotonesProducto;
	}

	public JButton getBtnAgregarEmpleado() {
		return btnAgregarEmpleado;
	}

	public JButton getBtnActualizarEmpleado() {
		return btnActualizarEmpleado;
	}

	public JButton getBtnEliminarEmpleado() {
		return btnEliminarEmpleado;
	}

	public JScrollPane getSpTblEmpleado() {
		return spTblEmpleado;
	}

	public JTable getTblEmpleado() {
		return tblEmpleado;
	}
}
