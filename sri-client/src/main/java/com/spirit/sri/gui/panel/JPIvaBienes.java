package com.spirit.sri.gui.panel;

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

/**
 * @author xruiz
 */
public abstract class JPIvaBienes extends MantenimientoModelImpl {
	public JPIvaBienes() {
		initComponents();
		setName("IVA Bienes");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblPorcentaje = new JLabel();
		txtPorcentaje = new JTextField();
		spTblIvaBienes = new JScrollPane();
		tblIvaBienes = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Tipo de Sustento Tributario");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblPorcentaje ----
		lblPorcentaje.setText("Porcentaje:");
		lblPorcentaje.setToolTipText("Nombre del Centro de Gasto");
		lblPorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPorcentaje, cc.xywh(3, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtPorcentaje ----
		txtPorcentaje.setToolTipText("Nombre del Centro de Gasto");
		add(txtPorcentaje, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblIvaBienes ========
		{
			
			//---- tblIvaBienes ----
			tblIvaBienes.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"C\u00f3digo", "Porcentaje"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblIvaBienes.setViewportView(tblIvaBienes);
		}
		add(spTblIvaBienes, cc.xywh(3, 9, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	//JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblPorcentaje;
	private JTextField txtPorcentaje;
	private JScrollPane spTblIvaBienes;
	private JTable tblIvaBienes;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpTblIvaBienes() {
		return spTblIvaBienes;
	}

	public void setSpTblIvaBienes(JScrollPane spTblIvaBienes) {
		this.spTblIvaBienes = spTblIvaBienes;
	}

	public JTable getTblIvaBienes() {
		return tblIvaBienes;
	}

	public void setTblIvaBienes(JTable tblIvaBienes) {
		this.tblIvaBienes = tblIvaBienes;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtPorcentaje() {
		return txtPorcentaje;
	}

	public void setTxtPorcentaje(JTextField txtPorcentaje) {
		this.txtPorcentaje = txtPorcentaje;
	}	
}
