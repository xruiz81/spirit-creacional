package com.spirit.rrhh.gui.panel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Thu Jun 03 16:09:41 COT 2010
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Avillacres
 */
public abstract class JPVencimientosContrato extends SpiritModelImpl {
	public JPVencimientosContrato() {
		initComponents();
		setName("Vencimientos de Contrato");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnEmpleado = new JButton();
		btnLimpiarEmpleado = new JButton();
		lblMes = new JLabel();
		cmbMes = new DateComboBox();
		cbAnioCompleto = new JCheckBox();
		btnConsultar = new JButton();
		spTblVencimientos = new JScrollPane();
		tblVencimientos = new JTable();
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

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(true);
		add(txtEmpleado, cc.xywh(5, 3, 3, 1));
		add(btnEmpleado, cc.xywh(9, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
		add(btnLimpiarEmpleado, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblMes ----
		lblMes.setText("Mes:");
		add(lblMes, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMes, cc.xy(5, 5));

		//---- cbAnioCompleto ----
		cbAnioCompleto.setText("Ver todo el a\u00f1o");
		add(cbAnioCompleto, cc.xy(7, 5));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(5, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//======== spTblVencimientos ========
		{
			spTblVencimientos.setPreferredSize(new Dimension(452, 100));

			//---- tblVencimientos ----
			tblVencimientos.setModel(new DefaultTableModel(
				new Object[][] {
					{"", null, null, "", "", null},
				},
				new String[] {
					"Oficina", "Departamento", "Empleado", "Fecha de Contrato", "90 d\u00edas", "360 d\u00edas"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblVencimientos.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblVencimientos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblVencimientos.setAutoCreateColumnsFromModel(true);
			spTblVencimientos.setViewportView(tblVencimientos);
		}
		add(spTblVencimientos, cc.xywh(3, 11, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnEmpleado;
	private JButton btnLimpiarEmpleado;
	private JLabel lblMes;
	private DateComboBox cmbMes;
	private JCheckBox cbAnioCompleto;
	private JButton btnConsultar;
	private JScrollPane spTblVencimientos;
	private JTable tblVencimientos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JButton getBtnLimpiarEmpleado() {
		return btnLimpiarEmpleado;
	}

	public JLabel getLblMes() {
		return lblMes;
	}

	public DateComboBox getCmbMes() {
		return cmbMes;
	}

	public JCheckBox getCbAnioCompleto() {
		return cbAnioCompleto;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblVencimientos() {
		return spTblVencimientos;
	}

	public JTable getTblVencimientos() {
		return tblVencimientos;
	}
}
