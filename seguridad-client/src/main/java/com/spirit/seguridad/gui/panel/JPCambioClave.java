package com.spirit.seguridad.gui.panel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPCambioClave extends SpiritModelImpl {

	public JPCambioClave() {
		initComponents();
		setName("Cambio de Claves");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		txtEmpleado = new JTextField();
		lblEmpleado = new JLabel();
		btnBuscarEmpleado = new JButton();
		lblUsuario = new JLabel();
		txtUsuario = new JTextField();
		lblClaveAnterior = new JLabel();
		txtClaveAnterior = new JPasswordField();
		lblClaveNueva = new JLabel();
		txtClaveNueva = new JPasswordField();
		lblConfirmarClave = new JLabel();
		txtConfirmarClave = new JPasswordField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY14),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY14),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY14),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY14),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY14),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));
		add(txtEmpleado, cc.xywh(5, 3, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		btnBuscarEmpleado.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarEmpleado.setToolTipText("Buscar empleado");
		add(btnBuscarEmpleado, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblUsuario ----
		lblUsuario.setText("Usuario:");
		add(lblUsuario, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtUsuario, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblClaveAnterior ----
		lblClaveAnterior.setText("Clave Anterior:");
		add(lblClaveAnterior, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtClaveAnterior, cc.xywh(5, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblClaveNueva ----
		lblClaveNueva.setText("Clave Nueva:");
		add(lblClaveNueva, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtClaveNueva, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblConfirmarClave ----
		lblConfirmarClave.setText("Confirmar Clave:");
		add(lblConfirmarClave, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtConfirmarClave, cc.xywh(5, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTextField txtEmpleado;
	private JLabel lblEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblClaveAnterior;
	private JPasswordField txtClaveAnterior;
	private JLabel lblClaveNueva;
	private JPasswordField txtClaveNueva;
	private JLabel lblConfirmarClave;
	private JPasswordField txtConfirmarClave;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPasswordField getTxtClaveAnterior() {
		return txtClaveAnterior;
	}

	public void setTxtClaveAnterior(JPasswordField txtClaveAnterior) {
		this.txtClaveAnterior = txtClaveAnterior;
	}

	public JPasswordField getTxtClaveNueva() {
		return txtClaveNueva;
	}

	public void setTxtClaveNueva(JPasswordField txtClaveNueva) {
		this.txtClaveNueva = txtClaveNueva;
	}

	public JPasswordField getTxtConfirmarClave() {
		return txtConfirmarClave;
	}

	public void setTxtConfirmarClave(JPasswordField txtConfirmarClave) {
		this.txtConfirmarClave = txtConfirmarClave;
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

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public void setBtnBuscarEmpleado(JButton btnBuscarEmpleado) {
		this.btnBuscarEmpleado = btnBuscarEmpleado;
	}
}
