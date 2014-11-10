package com.spirit.medios.gui.panel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPEmpleadoEquipo extends MantenimientoModelImpl {
	public JPEmpleadoEquipo() {
		initComponents();
		setName("Empleados por Equipo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblEquipoTrabajo = new JLabel();
		cmbEquipoTrabajo = new JComboBox();
		lblTipoOrden = new JLabel();
		txtTipoOrden = new JTextField();
		lblRol = new JLabel();
		cmbRol = new JComboBox();
		cbJefe = new JCheckBox();
		spTblEmpleadoEquipo = new JScrollPane();
		tblEmpleadoEquipo = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpleado, cc.xy(3, 3));
		add(txtEmpleado, cc.xywh(5, 3, 9, 1));
		add(btnBuscarEmpleado, cc.xywh(15, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblEquipoTrabajo ----
		lblEquipoTrabajo.setText("Equipo Trabajo:");
		add(lblEquipoTrabajo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEquipoTrabajo, cc.xywh(5, 5, 3, 1));

		//---- lblTipoOrden ----
		lblTipoOrden.setText("Tipo de Orden:");
		add(lblTipoOrden, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtTipoOrden, cc.xywh(13, 5, 3, 1));

		//---- lblRol ----
		lblRol.setText("Rol:");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblRol, cc.xy(3, 7));
		add(cmbRol, cc.xywh(5, 7, 9, 1));

		//---- cbJefe ----
		cbJefe.setText("Jefe");
		add(cbJefe, cc.xy(17, 7));

		//======== spTblEmpleadoEquipo ========
		{

			//---- tblEmpleadoEquipo ----
			tblEmpleadoEquipo.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"Empleado", "Equipo de Trabajo", "Rol", "Jefe"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblEmpleadoEquipo.setViewportView(tblEmpleadoEquipo);
		}
		add(spTblEmpleadoEquipo, cc.xywh(3, 11, 17, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblEquipoTrabajo;
	private JComboBox cmbEquipoTrabajo;
	private JLabel lblTipoOrden;
	private JTextField txtTipoOrden;
	private JLabel lblRol;
	private JComboBox cmbRol;
	private JCheckBox cbJefe;
	private JScrollPane spTblEmpleadoEquipo;
	private JTable tblEmpleadoEquipo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbJefe() {
		return cbJefe;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public void setBtnBuscarEmpleado(JButton btnBuscarEmpleado) {
		this.btnBuscarEmpleado = btnBuscarEmpleado;
	}

	public JComboBox getCmbEquipoTrabajo() {
		return cmbEquipoTrabajo;
	}

	public void setCmbEquipoTrabajo(JComboBox cmbEquipoTrabajo) {
		this.cmbEquipoTrabajo = cmbEquipoTrabajo;
	}

	public JComboBox getCmbRol() {
		return cmbRol;
	}

	public void setCmbRol(JComboBox cmbRol) {
		this.cmbRol = cmbRol;
	}

	public JTable getTblEmpleadoEquipo() {
		return tblEmpleadoEquipo;
	}

	public void setTblEmpleadoEquipo(JTable tblEmpleadoEquipo) {
		this.tblEmpleadoEquipo = tblEmpleadoEquipo;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public void setTxtEmpleado(JTextField txtEmpleado) {
		this.txtEmpleado = txtEmpleado;
	}

	public JTextField getTxtTipoOrden() {
		return txtTipoOrden;
	}

	public void setTxtTipoOrden(JTextField txtTipoOrden) {
		this.txtTipoOrden = txtTipoOrden;
	}
}
