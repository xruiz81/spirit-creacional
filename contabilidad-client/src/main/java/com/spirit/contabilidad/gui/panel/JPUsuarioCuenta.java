package com.spirit.contabilidad.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPUsuarioCuenta extends SpiritModelImpl {
	private static final long serialVersionUID = 1625263537445159273L;

	public JPUsuarioCuenta() {
		initComponents();
		setName("Cuentas por Usuario");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblCuentasContables = new JLabel();
		lblCuentasPermitidas = new JLabel();
		btnQuitar = new JButton();
		txtUsuario = new JTextField();
		lblUsuario = new JLabel();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		fsPermisos = compFactory.createSeparator("Permisos");
		scrollPaneCuentasContables = new JScrollPane();
		treeCuentasContables = new JTree();
		scrollPaneCuentasPermitidas = new JScrollPane();
		treeCuentasPermitidas = new JTree();
		btnAsignar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(228)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY5),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xy(3, 3));
		
		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 3, 3, 1));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xywh(9, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblCuentasContables ----
		lblCuentasContables.setText("Cuentas Contables");
		lblCuentasContables.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblCuentasContables, cc.xywh(3, 15, 7, 1));

		//---- lblCuentasPermitidas ----
		lblCuentasPermitidas.setText("Cuentas Permitidas");
		lblCuentasPermitidas.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblCuentasPermitidas, cc.xy(17, 15));

		//---- btnQuitar ----
		btnQuitar.setText("<");
		add(btnQuitar, cc.xy(13, 21));
		
		//---- txtUsuario ----
		txtUsuario.setEditable(false);
		add(txtUsuario, cc.xy(5, 5));

		//---- lblUsuario ----
		lblUsuario.setText("Usuario:");
		lblUsuario.setFocusTraversalPolicyProvider(true);
		add(lblUsuario, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de Cuenta:");
		add(lblPlanCuenta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(5, 7, 5, 1));
		add(fsPermisos, cc.xywh(3, 11, 15, 1));

		//======== scrollPaneCuentasContables ========
		{
			
			//---- treeCuentasContables ----
			//treeCuentasContables.setBorder(new EtchedBorder());
			//treeCuentasContables.setRootVisible(false);
			scrollPaneCuentasContables.setViewportView(treeCuentasContables);
		}
		add(scrollPaneCuentasContables, cc.xywh(3, 17, 7, 7));

		//======== scrollPaneCuentasPermitidas ========
		{
			
			//---- treeCuentasPermitidas ----
			//treeCuentasPermitidas.setBorder(new EtchedBorder());
			//treeCuentasPermitidas.setRootVisible(false);
			scrollPaneCuentasPermitidas.setViewportView(treeCuentasPermitidas);
		}
		add(scrollPaneCuentasPermitidas, cc.xywh(17, 17, 1, 7));

		//---- btnAsignar ----
		btnAsignar.setText(">");
		add(btnAsignar, cc.xy(13, 19));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnQuitar.setText("");
		btnQuitar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goPreviousRecord.png"));
		btnQuitar.setToolTipText("Quitar Cuenta");
		btnAsignar.setText("");
		btnAsignar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goNextRecord.png"));
		btnAsignar.setToolTipText("Agregar Cuenta");
		btnBuscarEmpleado.setText("");
		btnBuscarEmpleado.setToolTipText("Buscar empleado");
		btnBuscarEmpleado.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblCuentasContables;
	private JLabel lblCuentasPermitidas;
	private JButton btnQuitar;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JComponent fsPermisos;
	private JScrollPane scrollPaneCuentasContables;
	private JTree treeCuentasContables;
	private JScrollPane scrollPaneCuentasPermitidas;
	private JTree treeCuentasPermitidas;
	private JButton btnAsignar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnAsignar() {
		return btnAsignar;
	}

	public void setBtnAsignar(JButton btnAsignar) {
		this.btnAsignar = btnAsignar;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JTree getTreeCuentasContables() {
		return treeCuentasContables;
	}

	public void setTreeCuentasContables(JTree treeCuentasContables) {
		this.treeCuentasContables = treeCuentasContables;
	}

	public JTree getTreeCuentasPermitidas() {
		return treeCuentasPermitidas;
	}

	public void setTreeCuentasPermitidas(JTree treeCuentasPermitidas) {
		this.treeCuentasPermitidas = treeCuentasPermitidas;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public void setBtnBuscarEmpleado(JButton btnBuscarEmpleado) {
		this.btnBuscarEmpleado = btnBuscarEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public void setTxtEmpleado(JTextField txtEmpleado) {
		this.txtEmpleado = txtEmpleado;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
}
