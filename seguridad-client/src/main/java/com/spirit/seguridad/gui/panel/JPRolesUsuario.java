package com.spirit.seguridad.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPRolesUsuario extends SpiritModelImpl {
	public JPRolesUsuario() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblEmpresa = new JLabel();
		cmbEmpresa = new JComboBox();
		fsRoles = compFactory.createSeparator("Roles");
		lblTodosRoles = new JLabel();
		lblRolesSeleccionados = new JLabel();
		scrollPaneTodosRoles = new JScrollPane();
		listTodosRoles = new JList();
		scrollPaneRolesSeleccionados = new JScrollPane();
		listRolesSeleccionados = new JList();
		btnAgregarRoles = new JButton();
		btnQuitarRoles = new JButton();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblUsuario = new JLabel();
		txtUsuario = new JTextField();
		lblTipoUsuario = new JLabel();
		cmbTipoUsuario = new JComboBox();
		lblTipoUsuarioTimeTracker = new JLabel();
		cmbTipoUsuarioTimeTracker = new JComboBox();
		lblClave = new JLabel();
		txtClave = new JPasswordField();
		lblConfirmarClave = new JLabel();
		txtConfirmarClave = new JPasswordField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Usuario y Roles");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(150)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(163)),
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
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
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

		//---- lblEmpresa ----
		lblEmpresa.setText("Empresa:");
		add(lblEmpresa, cc.xy(3, 3));
		add(cmbEmpresa, cc.xy(5, 3));
		add(fsRoles, cc.xywh(3, 19, 13, 1));

		//---- lblTodosRoles ----
		lblTodosRoles.setText("Todos");
		lblTodosRoles.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblTodosRoles, cc.xy(5, 23));

		//---- lblRolesSeleccionados ----
		lblRolesSeleccionados.setText("Seleccionados");
		lblRolesSeleccionados.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblRolesSeleccionados, cc.xy(15, 23));

		//======== scrollPaneTodosRoles ========
		{
			
			//---- listTodosRoles ----
			listTodosRoles.setBorder(new EtchedBorder());
			scrollPaneTodosRoles.setViewportView(listTodosRoles);
		}
		add(scrollPaneTodosRoles, cc.xywh(5, 25, 3, 7));

		//======== scrollPaneRolesSeleccionados ========
		{
			
			//---- listRolesSeleccionados ----
			listRolesSeleccionados.setBorder(new EtchedBorder());
			scrollPaneRolesSeleccionados.setViewportView(listRolesSeleccionados);
		}
		add(scrollPaneRolesSeleccionados, cc.xywh(15, 25, 1, 7));

		//---- btnAgregarRoles ----
		btnAgregarRoles.setText(">");
		add(btnAgregarRoles, cc.xy(11, 27));

		//---- btnQuitarRoles ----
		btnQuitarRoles.setText("<");
		add(btnQuitarRoles, cc.xy(11, 29));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtEmpleado, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(btnBuscarEmpleado, cc.xywh(7, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblUsuario ----
		lblUsuario.setText("Usuario:");
		add(lblUsuario, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtUsuario, cc.xywh(5, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTipoUsuario ----
		lblTipoUsuario.setText("Tipo Usuario:");
		add(lblTipoUsuario, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbTipoUsuario, cc.xy(5, 9));

		//---- lblTipoUsuarioTimeTracker ----
		lblTipoUsuarioTimeTracker.setText("Tipo TimeTracker: ");
		add(lblTipoUsuarioTimeTracker, cc.xy(3, 11));
		add(cmbTipoUsuarioTimeTracker, cc.xy(5, 11));

		//---- lblClave ----
		lblClave.setText("Clave:");
		add(lblClave, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtClave, cc.xy(5, 13));

		//---- lblConfirmarClave ----
		lblConfirmarClave.setText("Confirmar clave:");
		add(lblConfirmarClave, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtConfirmarClave, cc.xy(5, 15));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEmpresa;
	private JComboBox cmbEmpresa;
	private JComponent fsRoles;
	private JLabel lblTodosRoles;
	private JLabel lblRolesSeleccionados;
	private JScrollPane scrollPaneTodosRoles;
	private JList listTodosRoles;
	private JScrollPane scrollPaneRolesSeleccionados;
	private JList listRolesSeleccionados;
	private JButton btnAgregarRoles;
	private JButton btnQuitarRoles;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblTipoUsuario;
	private JComboBox cmbTipoUsuario;
	private JLabel lblTipoUsuarioTimeTracker;
	private JComboBox cmbTipoUsuarioTimeTracker;
	private JLabel lblClave;
	private JPasswordField txtClave;
	private JLabel lblConfirmarClave;
	private JPasswordField txtConfirmarClave;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblEmpresa() {
		return lblEmpresa;
	}

	public JComboBox getCmbEmpresa() {
		return cmbEmpresa;
	}

	public JComponent getFsRoles() {
		return fsRoles;
	}

	public JLabel getLblTodosRoles() {
		return lblTodosRoles;
	}

	public JLabel getLblRolesSeleccionados() {
		return lblRolesSeleccionados;
	}

	public JScrollPane getScrollPaneTodosRoles() {
		return scrollPaneTodosRoles;
	}

	public JList getListTodosRoles() {
		return listTodosRoles;
	}

	public JScrollPane getScrollPaneRolesSeleccionados() {
		return scrollPaneRolesSeleccionados;
	}

	public JList getListRolesSeleccionados() {
		return listRolesSeleccionados;
	}

	public JButton getBtnAgregarRoles() {
		return btnAgregarRoles;
	}

	public JButton getBtnQuitarRoles() {
		return btnQuitarRoles;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JLabel getLblTipoUsuario() {
		return lblTipoUsuario;
	}

	public JComboBox getCmbTipoUsuario() {
		return cmbTipoUsuario;
	}

	public JLabel getLblTipoUsuarioTimeTracker() {
		return lblTipoUsuarioTimeTracker;
	}

	public JComboBox getCmbTipoUsuarioTimeTracker() {
		return cmbTipoUsuarioTimeTracker;
	}

	public JLabel getLblClave() {
		return lblClave;
	}

	public JPasswordField getTxtClave() {
		return txtClave;
	}

	public JLabel getLblConfirmarClave() {
		return lblConfirmarClave;
	}

	public JPasswordField getTxtConfirmarClave() {
		return txtConfirmarClave;
	}
}
