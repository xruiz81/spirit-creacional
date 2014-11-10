package com.spirit.general.gui.panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;

/*
 * Created by JFormDesigner on Sun Aug 28 10:19:22 COT 2005
 */

/**
 * @author Leonardo Reyes
 */
public abstract class JPEmpleadoCuenta extends SpiritModelImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1625263537445159273L;

	public JPEmpleadoCuenta() {
		initComponents();
		setName("Empleado Cuenta");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblEmpleado = new JLabel();
		cmbEmpleado = new JComboBox();
		txtEmpleado = new JTextField();
		fsPermisos = compFactory.createSeparator("Permisos");
		treeCuentas = new JTree();
		panel1 = new JPanel();
		btnAsignaUno = new JButton();
		btnAsignaTodos = new JButton();
		btnQuitaUno = new JButton();
		btnQuitaTodos = new JButton();
		treePermiso = new JTree();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout(new ColumnSpec[] {
				new ColumnSpec("max(default;10dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;10dlu):grow") }, new RowSpec[] {
				new RowSpec("max(default;10dlu)"),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW) }));
		((FormLayout) getLayout()).setColumnGroups(new int[][] { { 1, 13 },
				{ 3, 5, 9, 11 } });

		// ---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT,
				CellConstraints.DEFAULT));
		add(cmbEmpleado, cc.xy(5, 3));
		add(txtEmpleado, cc.xywh(7, 3, 5, 1));
		add(fsPermisos, cc.xywh(3, 5, 9, 1));
		add(treeCuentas, cc.xywh(3, 7, 3, 1));

		// ======== panel1 ========
		{
			panel1.setLayout(new FormLayout(ColumnSpec.decodeSpecs("default"),
					new RowSpec[] { FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC }));

			// ---- btnAsignaUno ----
			btnAsignaUno.setText("text");
			panel1.add(btnAsignaUno, cc.xy(1, 1));

			// ---- btnAsignaTodos ----
			btnAsignaTodos.setText("text");
			panel1.add(btnAsignaTodos, cc.xy(1, 3));

			// ---- btnQuitaUno ----
			btnQuitaUno.setText("text");
			panel1.add(btnQuitaUno, cc.xy(1, 5));

			// ---- btnQuitaTodos ----
			btnQuitaTodos.setText("text");
			panel1.add(btnQuitaTodos, cc.xy(1, 7));
		}
		add(panel1, cc.xy(7, 7));
		add(treePermiso, cc.xywh(9, 7, 3, 1));
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel lblEmpleado;

	private JComboBox cmbEmpleado;

	private JTextField txtEmpleado;

	private JComponent fsPermisos;

	private JTree treeCuentas;

	private JPanel panel1;

	private JButton btnAsignaUno;

	private JButton btnAsignaTodos;

	private JButton btnQuitaUno;

	private JButton btnQuitaTodos;

	private JTree treePermiso;

	public JButton getBtnAsignaTodos() {
		return btnAsignaTodos;
	}

	public void setBtnAsignaTodos(JButton btnAsignaTodos) {
		this.btnAsignaTodos = btnAsignaTodos;
	}

	public JButton getBtnAsignaUno() {
		return btnAsignaUno;
	}

	public void setBtnAsignaUno(JButton btnAsignaUno) {
		this.btnAsignaUno = btnAsignaUno;
	}

	public JButton getBtnQuitaTodos() {
		return btnQuitaTodos;
	}

	public void setBtnQuitaTodos(JButton btnQuitaTodos) {
		this.btnQuitaTodos = btnQuitaTodos;
	}

	public JButton getBtnQuitaUno() {
		return btnQuitaUno;
	}

	public void setBtnQuitaUno(JButton btnQuitaUno) {
		this.btnQuitaUno = btnQuitaUno;
	}

	public JComboBox getCmbEmpleado() {
		return cmbEmpleado;
	}

	public void setCmbEmpleado(JComboBox cmbEmpleado) {
		this.cmbEmpleado = cmbEmpleado;
	}

	public JTree getTreeCuentas() {
		return treeCuentas;
	}

	public void setTreeCuentas(JTree treeCuentas) {
		this.treeCuentas = treeCuentas;
	}

	public JTree getTreePermiso() {
		return treePermiso;
	}

	public void setTreePermiso(JTree treePermiso) {
		this.treePermiso = treePermiso;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public void setTxtEmpleado(JTextField txtEmpleado) {
		this.txtEmpleado = txtEmpleado;
	}

	// JFormDesigner - End of variables declaration //GEN-END:variables

}
