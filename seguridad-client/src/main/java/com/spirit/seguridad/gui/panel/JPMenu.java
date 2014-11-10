package com.spirit.seguridad.gui.panel;

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
 * @author xruiz
 */

public abstract class JPMenu extends SpiritModelImpl {
	public JPMenu() {
		initComponents();
		setName("Menu");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblModulo = new JLabel();
		cmbModulo = new JComboBox();
		cmbMenu = new JComboBox();
		lblPanel = new JLabel();
		txtPanel = new JTextField();
		lblMenu = new JLabel();
		spTblMenu = new JScrollPane();
		tblMenu = new JTable();
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
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(39)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(67)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xywh(5, 3, 3, 1));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 13, 1));

		//---- lblModulo ----
		lblModulo.setText("M\u00f3dulo:");
		add(lblModulo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbModulo, cc.xywh(5, 7, 5, 1));
		add(cmbMenu, cc.xywh(15, 7, 3, 1));

		//---- lblPanel ----
		lblPanel.setText("Panel:");
		add(lblPanel, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtPanel, cc.xywh(5, 9, 13, 1));

		//---- lblMenu ----
		lblMenu.setText("Men\u00fa:");
		add(lblMenu, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== spTblMenu ========
		{
			
			//---- tblMenu ----
			tblMenu.setModel(new DefaultTableModel(
				new Object[][] {
					{null, "", null, null, null},
				},
				new String[] {
					"Código", "Nombre", "Módulo", "Menú", "Panel"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblMenu.getTableHeader().setReorderingAllowed(false);
			spTblMenu.setViewportView(tblMenu);
		}
		add(spTblMenu, cc.xywh(3, 13, 19, 2));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblModulo;
	private JComboBox cmbModulo;
	private JComboBox cmbMenu;
	private JLabel lblPanel;
	private JTextField txtPanel;
	private JLabel lblMenu;
	private JScrollPane spTblMenu;
	private JTable tblMenu;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpTblMenu() {
		return spTblMenu;
	}

	public void setSpTblMenu(JScrollPane spTblMenu) {
		this.spTblMenu = spTblMenu;
	}

	public JTable getTblMenu() {
		return tblMenu;
	}

	public void setTblMenu(JTable tblMenu) {
		this.tblMenu = tblMenu;
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

	public JTextField getTxtPanel() {
		return txtPanel;
	}

	public void setTxtPanel(JTextField txtPanel) {
		this.txtPanel = txtPanel;
	}

	public JComboBox getCmbMenu() {
		return cmbMenu;
	}

	public void setCmbMenu(JComboBox cmbMenu) {
		this.cmbMenu = cmbMenu;
	}

	public JComboBox getCmbModulo() {
		return cmbModulo;
	}

	public void setCmbModulo(JComboBox cmbModulo) {
		this.cmbModulo = cmbModulo;
	}
}
