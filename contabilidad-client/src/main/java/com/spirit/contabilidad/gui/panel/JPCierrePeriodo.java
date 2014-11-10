package com.spirit.contabilidad.gui.panel;

import javax.swing.JComboBox;
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
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPCierrePeriodo extends SpiritModelImpl {
	public JPCierrePeriodo() {
		initComponents();
		setName("Cierre de Periodo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblPeriodo = new JLabel();
		cmbPeriodo = new JComboBox();
		txtPeriodo = new JTextField();
		spTblPeriodoDetalle = new JScrollPane();
		tblPeriodoDetalle = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(180)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
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
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblPeriodo ----
		lblPeriodo.setText("Período:");
		add(lblPeriodo, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		add(cmbPeriodo, cc.xy(5, 3));
		add(txtPeriodo, cc.xy(9, 3));

		//======== spTblPeriodoDetalle ========
		{
			
			//---- tblPeriodoDetalle ----
			tblPeriodoDetalle.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"A\u00f1o", "Mes", "Status"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, true
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblPeriodoDetalle.setViewportView(tblPeriodoDetalle);
		}
		add(spTblPeriodoDetalle, cc.xywh(3, 7, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblPeriodo;
	private JComboBox cmbPeriodo;
	private JTextField txtPeriodo;
	private JScrollPane spTblPeriodoDetalle;
	private JTable tblPeriodoDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbPeriodo() {
		return cmbPeriodo;
	}

	public void setCmbPeriodo(JComboBox cmbPeriodo) {
		this.cmbPeriodo = cmbPeriodo;
	}

	public JScrollPane getSpTblPeriodoDetalle() {
		return spTblPeriodoDetalle;
	}

	public void setSpTblPeriodoDetalle(JScrollPane spTblPeriodoDetalle) {
		this.spTblPeriodoDetalle = spTblPeriodoDetalle;
	}

	public JTable getTblPeriodoDetalle() {
		return tblPeriodoDetalle;
	}

	public void setTblPeriodoDetalle(JTable tblPeriodoDetalle) {
		this.tblPeriodoDetalle = tblPeriodoDetalle;
	}

	public JTextField getTxtPeriodo() {
		return txtPeriodo;
	}

	public void setTxtPeriodo(JTextField txtPeriodo) {
		this.txtPeriodo = txtPeriodo;
	}
}
