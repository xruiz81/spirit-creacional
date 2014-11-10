package com.spirit.nomina.gui.panel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPSalarioMinimoVital extends MantenimientoModelImpl {
	public JPSalarioMinimoVital() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		scpSmv = new JScrollPane();
		tblSmv = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Salario Minimo Vital");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblValor ----
		lblValor.setText("Valor:");
		add(lblValor, cc.xy(3, 3));
		add(txtValor, cc.xywh(5, 3, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha  Inicio: ");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 5, 5, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(3, 7));
		add(cmbFechaFin, cc.xywh(5, 7, 5, 1));

		//======== scpSmv ========
		{
			
			//---- tblSmv ----
			tblSmv.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"Valor", "Fecha Inicio", "Fecha Fin"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			scpSmv.setViewportView(tblSmv);
		}
		add(scpSmv, cc.xywh(3, 11, 13, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JScrollPane scpSmv;
	private JTable tblSmv;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblValor() {
		return lblValor;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JScrollPane getScpSmv() {
		return scpSmv;
	}

	public JTable getTblSmv() {
		return tblSmv;
	}
}
