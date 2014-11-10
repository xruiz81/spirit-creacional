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

/**
 * @author Antonio Seiler
 */
public abstract class JPLinea extends MantenimientoModelImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1728646056876032461L;
	public JPLinea() {
		initComponents();
		setName("Linea");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblPadre = new JLabel();
		cmbPadre = new JComboBox();
		lblNivel = new JLabel();
		txtNivel = new JTextField();
		cmbAceptaItem = new JComboBox();
		lblAceptaItem = new JLabel();
		spTblLinea = new JScrollPane();
		tblLinea = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo descriptivo de la l\u00ednea");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo de la l\u00ednea");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setToolTipText("Nombre de la l\u00ednea");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre de la l\u00ednea");
		add(txtNombre, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblPadre ----
		lblPadre.setText("Padre:");
		lblPadre.setToolTipText("Pertenece a otra l\u00ednea?");
		add(lblPadre, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbPadre ----
		cmbPadre.setToolTipText("Pertenece a otra l\u00ednea");
		add(cmbPadre, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNivel ----
		lblNivel.setText("Nivel:");
		lblNivel.setToolTipText("Nivel");
		add(lblNivel, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNivel ----
		txtNivel.setToolTipText("Nivel");
		add(txtNivel, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cmbAceptaItem ----
		cmbAceptaItem.setToolTipText("Acepta Item?");
		add(cmbAceptaItem, cc.xywh(5, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblAceptaItem ----
		lblAceptaItem.setText("Acepta Item:");
		lblAceptaItem.setToolTipText("Acepta Item?");
		add(lblAceptaItem, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//======== spTblLinea ========
		{
			
			//---- tblLinea ----
			tblLinea.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Padre"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblLinea.getTableHeader().setReorderingAllowed(false);
			spTblLinea.setViewportView(tblLinea);
		}
		add(spTblLinea, cc.xywh(3, 15, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblPadre;
	private JComboBox cmbPadre;
	private JLabel lblNivel;
	private JTextField txtNivel;
	private JComboBox cmbAceptaItem;
	private JLabel lblAceptaItem;
	private JScrollPane spTblLinea;
	private JTable tblLinea;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbAceptaItem() {
		return cmbAceptaItem;
	}

	public void setCmbAceptaItem(JComboBox cmbAceptaItem) {
		this.cmbAceptaItem = cmbAceptaItem;
	}

	public JComboBox getCmbPadre() {
		return cmbPadre;
	}

	public void setCmbPadre(JComboBox cmbPadre) {
		this.cmbPadre = cmbPadre;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtNivel() {
		return txtNivel;
	}

	public void setTxtNivel(JTextField txtNivel) {
		this.txtNivel = txtNivel;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JScrollPane getSpTblLinea() {
		return spTblLinea;
	}

	public void setSpTblLinea(JScrollPane spTblLinea) {
		this.spTblLinea = spTblLinea;
	}

	public JTable getTblLinea() {
		return tblLinea;
	}

	public void setTblLinea(JTable tblLinea) {
		this.tblLinea = tblLinea;
	}
}
