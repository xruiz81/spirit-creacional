package com.spirit.general.gui.panel;

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
import com.spirit.client.model.MantenimientoModelImpl;

/*
 * Created by JFormDesigner on Thu Dec 14 23:45:51 COT 2006
 */

/**
 * @author xruiz
 */
public abstract class JPProvincia extends MantenimientoModelImpl {
	 
		private static final long serialVersionUID = -2073849637195936378L;

		public JPProvincia() {
			initComponents();
			setName("Provincia");
		}
		
		private void initComponents() {
			// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
			lblCodigo = new JLabel();
			txtCodigo = new JTextField();
			lblNombre = new JLabel();
			txtNombre = new JTextField();
			lblPais = new JLabel();
			cmbPais = new JComboBox();
			spTblProvincia = new JScrollPane();
			tblProvincia = new JTable();
			CellConstraints cc = new CellConstraints();

			//======== this ========
			setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(35)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(170)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY6),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
				}));

			//---- lblCodigo ----
			lblCodigo.setText("C\u00f3digo:");
			lblCodigo.setToolTipText("C\u00f3digo descriptivo de la Provincia");
			add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

			//---- txtCodigo ----
			txtCodigo.setToolTipText("C\u00f3digo descriptivo de la Provincia");
			add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

			//---- lblNombre ----
			lblNombre.setText("Nombre:");
			lblNombre.setToolTipText("Nombre de la Provincia");
			add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

			//---- txtNombre ----
			txtNombre.setToolTipText("Nombre de la Provincia");
			add(txtNombre, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

			//---- lblPais ----
			lblPais.setText("Pa\u00eds:");
			lblPais.setToolTipText("Pa\u00eds al que pertenece la provincia");
			add(lblPais, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

			//---- cmbPais ----
			cmbPais.setToolTipText("Pa\u00eds al que pertenece la provincia");
			add(cmbPais, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

			//======== spTblProvincia ========
			{
				
				//---- tblProvincia ----
				tblProvincia.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
					},
					new String[] {
						"Codigo", "Nombre", "Pais"
					}
				) {
					boolean[] columnEditable = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return columnEditable[columnIndex];
					}
				});
				spTblProvincia.setViewportView(tblProvincia);
			}
			add(spTblProvincia, cc.xywh(3, 11, 7, 5));
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		private JLabel lblCodigo;
		private JTextField txtCodigo;
		private JLabel lblNombre;
		private JTextField txtNombre;
		private JLabel lblPais;
		private JComboBox cmbPais;
		private JScrollPane spTblProvincia;
		private JTable tblProvincia;
		// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JComboBox getCmbPais() {
		return cmbPais;
	}
	public void setCmbPais(JComboBox cmbPais) {
		this.cmbPais = cmbPais;
	}
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}
	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
	public JTable getTblProvincia() {
		return tblProvincia;
	}
	public void setTblProvincia(JTable tblProvincia) {
		this.tblProvincia = tblProvincia;
	}
}
