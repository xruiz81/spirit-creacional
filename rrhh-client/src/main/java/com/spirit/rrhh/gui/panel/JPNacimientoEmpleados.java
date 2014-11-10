package com.spirit.rrhh.gui.panel;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Thu Oct 17 12:06:09 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author TVASCONEZ
 */
public abstract class JPNacimientoEmpleados extends SpiritModelImpl  {
	public JPNacimientoEmpleados() {
		initComponents();
		setName("Celebraciones por Mes");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		btnEmpleado = new JButton();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnLimpiarEmpleado = new JButton();
		lblMes = new JLabel();
		cmbMes = new DateComboBox();
		cbFechaCompleta = new JCheckBox();
		btnConsultar = new JButton();
		cbAnioCompleto = new JCheckBox();
		cbTelefono = new JCheckBox();
		spTblNacimiento = new JScrollPane();
		tblNacimiento = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
		add(btnEmpleado, cc.xywh(9, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(true);
		add(txtEmpleado, cc.xywh(5, 3, 3, 1));
		add(btnLimpiarEmpleado, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblMes ----
		lblMes.setText("Mes:");
		add(lblMes, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMes, cc.xy(5, 5));

		//---- cbFechaCompleta ----
		cbFechaCompleta.setText("Ver fecha completa");
		add(cbFechaCompleta, cc.xy(7, 5));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(5, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbAnioCompleto ----
		cbAnioCompleto.setText("Ver todo el a\u00f1o");
		add(cbAnioCompleto, cc.xy(7, 7));

		//---- cbTelefono ----
		cbTelefono.setText("Ver tel\u00e9fono");
		add(cbTelefono, cc.xy(7, 9));

		//======== spTblNacimiento ========
		{
			spTblNacimiento.setPreferredSize(new Dimension(452, 100));

			//---- tblNacimiento ----
			tblNacimiento.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"Empleado", "Fecha de Nacimiento", "Tel\u00e9fono"
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
			tblNacimiento.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblNacimiento.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblNacimiento.setAutoCreateColumnsFromModel(true);
			spTblNacimiento.setViewportView(tblNacimiento);
		}
		add(spTblNacimiento, cc.xywh(3, 13, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JButton btnEmpleado;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnLimpiarEmpleado;
	private JLabel lblMes;
	private DateComboBox cmbMes;
	private JCheckBox cbFechaCompleta;
	private JButton btnConsultar;
	private JCheckBox cbAnioCompleto;
	private JCheckBox cbTelefono;
	private JScrollPane spTblNacimiento;
	private JTable tblNacimiento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JButton getBtnLimpiarEmpleado() {
		return btnLimpiarEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public DateComboBox getCmbMes() {
		return cmbMes;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}
	
	public JCheckBox getCbFechaCompleta() {
		return cbFechaCompleta;
	}
	
	public JScrollPane getSpTblNacimiento() {
		return spTblNacimiento;
	}

	public JTable getTblNacimiento() {
		return tblNacimiento;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JCheckBox getCbAnioCompleto() {
		return cbAnioCompleto;
	}

	public JCheckBox getCbTelefono() {
		return cbTelefono;
	}
	
}
