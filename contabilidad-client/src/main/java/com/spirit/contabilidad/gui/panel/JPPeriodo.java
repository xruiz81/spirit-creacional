package com.spirit.contabilidad.gui.panel;

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

/**
 * @author xruiz
 */
public abstract class JPPeriodo extends MantenimientoModelImpl {
	
	public JPPeriodo() {
		initComponents();
		setName("Ejercicios Contables");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		cmbFechaInicio = new DateComboBox();
		lblFechaInicio = new JLabel();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		spTblPeriodos = new JScrollPane();
		tblPeriodos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX14),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(83)),
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
				new RowSpec(Sizes.DLUY14),
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

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 5, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(13, 5));

		//======== spTblPeriodos ========
		{
			
			//---- tblPeriodos ----
			tblPeriodos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"Código", "Fecha Inicio", "Fecha Fin", "Status"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblPeriodos.getTableHeader().setReorderingAllowed(false);
			spTblPeriodos.setViewportView(tblPeriodos);
		}
		add(spTblPeriodos, cc.xywh(3, 9, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JScrollPane spTblPeriodos;
	private JTable tblPeriodos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpTblPeriodos() {
		return spTblPeriodos;
	}

	public void setSpTblPeriodos(JScrollPane spTblPeriodos) {
		this.spTblPeriodos = spTblPeriodos;
	}

	public JTable getTblPeriodos() {
		return tblPeriodos;
	}

	public void setTblPeriodos(JTable tblPeriodos) {
		this.tblPeriodos = tblPeriodos;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

}
