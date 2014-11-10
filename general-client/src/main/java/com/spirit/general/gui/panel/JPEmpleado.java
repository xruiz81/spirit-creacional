package com.spirit.general.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
 * Created by JFormDesigner on Thu Mar 04 16:26:35 COT 2010
 */

public abstract class JPEmpleado extends SpiritModelImpl {
	public JPEmpleado() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		spPanelEmpleado = new JScrollPane();
		panelEmpleado = new JPanel();
		txtCodigo = new JTextField();
		lblCodigo = new JLabel();
		fsIdentificacion = compFactory.createSeparator("Identificaci\u00f3n:");
		lblNombres = new JLabel();
		txtNombres = new JTextField();
		lblApellidos = new JLabel();
		lblTipoIdentificacion = new JLabel();
		cmbTipoIdentificacion = new JComboBox();
		lblIdentificacion = new JLabel();
		txtIdentificacion = new JTextField();
		txtApellidos = new JTextField();
		lblProfesion = new JLabel();
		lblDomicilio = new JLabel();
		lblTelefono = new JLabel();
		lblEmail = new JLabel();
		fsDatosPersonales = compFactory.createSeparator("Datos Personales:");
		txtProfesion = new JTextField();
		lblBanco = new JLabel();
		cmbBanco = new JComboBox();
		txtDomicilio = new JTextField();
		lblTipoCuenta = new JLabel();
		cmbTipoCuenta = new JComboBox();
		txtTelefono = new JTextField();
		lblNumeroCuenta = new JLabel();
		txtNumeroCuenta = new JTextField();
		lblCelular = new JLabel();
		txtCelular = new JTextField();
		txtEmail = new JTextField();
		fsDatosOficina = compFactory.createSeparator("Datos Oficina:");
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		lblJefe = new JLabel();
		txtJefe = new JTextField();
		btnBuscarJefe = new JButton();
		btnBorrarJefe = new JButton();
		lblTipoEmpleado = new JLabel();
		cmbTipoEmpleado = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblExtesion = new JLabel();
		txtExtension = new JTextField();
		lblNivel = new JLabel();
		txtNivel = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Empleado");
		setLayout(new FormLayout(
			"default:grow",
			"fill:15dlu:grow"));

		//======== spPanelEmpleado ========
		{

			//======== panelEmpleado ========
			{
				panelEmpleado.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(16)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));
				((FormLayout)panelEmpleado.getLayout()).setColumnGroups(new int[][] {{1, 17}, {7, 15}});
				panelEmpleado.add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblCodigo ----
				lblCodigo.setText("C\u00f3digo:");
				panelEmpleado.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(fsIdentificacion, cc.xywh(3, 5, 13, 1));

				//---- lblNombres ----
				lblNombres.setText("Nombres:");
				panelEmpleado.add(lblNombres, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(txtNombres, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblApellidos ----
				lblApellidos.setText("Apellidos:");
				panelEmpleado.add(lblApellidos, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

				//---- lblTipoIdentificacion ----
				lblTipoIdentificacion.setText("Tipo Identificaci\u00f3n:");
				panelEmpleado.add(lblTipoIdentificacion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(cmbTipoIdentificacion, cc.xywh(5, 11, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblIdentificacion ----
				lblIdentificacion.setText("N\u00famero Identificaci\u00f3n:");
				panelEmpleado.add(lblIdentificacion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(txtIdentificacion, cc.xywh(5, 13, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				panelEmpleado.add(txtApellidos, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblProfesion ----
				lblProfesion.setText("Profesi\u00f3n:");
				panelEmpleado.add(lblProfesion, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

				//---- lblDomicilio ----
				lblDomicilio.setText("Domicilio:");
				panelEmpleado.add(lblDomicilio, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

				//---- lblTelefono ----
				lblTelefono.setText("Tel\u00e9fono:");
				panelEmpleado.add(lblTelefono, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

				//---- lblEmail ----
				lblEmail.setText("E-mail:");
				panelEmpleado.add(lblEmail, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(fsDatosPersonales, cc.xywh(3, 15, 13, 1));
				panelEmpleado.add(txtProfesion, cc.xywh(5, 17, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblBanco ----
				lblBanco.setText("Banco:");
				panelEmpleado.add(lblBanco, cc.xywh(13, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEmpleado.add(cmbBanco, cc.xy(15, 17));
				panelEmpleado.add(txtDomicilio, cc.xywh(5, 19, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblTipoCuenta ----
				lblTipoCuenta.setText("Tipo de Cuenta:");
				panelEmpleado.add(lblTipoCuenta, cc.xy(13, 19));
				panelEmpleado.add(cmbTipoCuenta, cc.xy(15, 19));
				panelEmpleado.add(txtTelefono, cc.xywh(5, 21, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblNumeroCuenta ----
				lblNumeroCuenta.setText("No. de Cuenta:");
				panelEmpleado.add(lblNumeroCuenta, cc.xywh(13, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtNumeroCuenta ----
				txtNumeroCuenta.setHorizontalAlignment(SwingConstants.RIGHT);
				panelEmpleado.add(txtNumeroCuenta, cc.xy(15, 21));

				//---- lblCelular ----
				lblCelular.setText("Celular:");
				panelEmpleado.add(lblCelular, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(txtCelular, cc.xywh(5, 23, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				panelEmpleado.add(txtEmail, cc.xywh(5, 25, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				panelEmpleado.add(fsDatosOficina, cc.xywh(3, 27, 13, 1));

				//---- lblOficina ----
				lblOficina.setText("Oficina:");
				panelEmpleado.add(lblOficina, cc.xywh(3, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(cmbOficina, cc.xywh(5, 29, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblDepartamento ----
				lblDepartamento.setText("Departamento:");
				panelEmpleado.add(lblDepartamento, cc.xywh(13, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(cmbDepartamento, cc.xywh(15, 29, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblJefe ----
				lblJefe.setText("Jefe:");
				panelEmpleado.add(lblJefe, cc.xywh(3, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(txtJefe, cc.xywh(5, 31, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- btnBuscarJefe ----
				btnBuscarJefe.setIcon(null);
				btnBuscarJefe.setToolTipText("Buscar jefe");
				panelEmpleado.add(btnBuscarJefe, cc.xywh(9, 31, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- btnBorrarJefe ----
				btnBorrarJefe.setToolTipText("Borro jefe seleccionado");
				panelEmpleado.add(btnBorrarJefe, cc.xywh(11, 31, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblTipoEmpleado ----
				lblTipoEmpleado.setText("Tipo Empleado:");
				panelEmpleado.add(lblTipoEmpleado, cc.xywh(13, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(cmbTipoEmpleado, cc.xywh(15, 31, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblEstado ----
				lblEstado.setText("Estado:");
				panelEmpleado.add(lblEstado, cc.xywh(3, 33, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(cmbEstado, cc.xywh(5, 33, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblExtesion ----
				lblExtesion.setText("Ext.Oficina:");
				panelEmpleado.add(lblExtesion, cc.xywh(13, 33, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(txtExtension, cc.xywh(15, 33, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblNivel ----
				lblNivel.setText("Nivel:");
				panelEmpleado.add(lblNivel, cc.xywh(13, 35, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelEmpleado.add(txtNivel, cc.xywh(15, 35, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			}
			spPanelEmpleado.setViewportView(panelEmpleado);
		}
		add(spPanelEmpleado, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spPanelEmpleado;
	private JPanel panelEmpleado;
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JComponent fsIdentificacion;
	private JLabel lblNombres;
	private JTextField txtNombres;
	private JLabel lblApellidos;
	private JLabel lblTipoIdentificacion;
	private JComboBox cmbTipoIdentificacion;
	private JLabel lblIdentificacion;
	private JTextField txtIdentificacion;
	private JTextField txtApellidos;
	private JLabel lblProfesion;
	private JLabel lblDomicilio;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JComponent fsDatosPersonales;
	private JTextField txtProfesion;
	private JLabel lblBanco;
	private JComboBox cmbBanco;
	private JTextField txtDomicilio;
	private JLabel lblTipoCuenta;
	private JComboBox cmbTipoCuenta;
	private JTextField txtTelefono;
	private JLabel lblNumeroCuenta;
	private JTextField txtNumeroCuenta;
	private JLabel lblCelular;
	private JTextField txtCelular;
	private JTextField txtEmail;
	private JComponent fsDatosOficina;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JLabel lblJefe;
	private JTextField txtJefe;
	private JButton btnBuscarJefe;
	private JButton btnBorrarJefe;
	private JLabel lblTipoEmpleado;
	private JComboBox cmbTipoEmpleado;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblExtesion;
	private JTextField txtExtension;
	private JLabel lblNivel;
	private JTextField txtNivel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpPanelEmpleado() {
		return spPanelEmpleado;
	}

	public JPanel getPanelEmpleado() {
		return panelEmpleado;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JComponent getFsIdentificacion() {
		return fsIdentificacion;
	}

	public JLabel getLblNombres() {
		return lblNombres;
	}

	public JTextField getTxtNombres() {
		return txtNombres;
	}

	public JLabel getLblApellidos() {
		return lblApellidos;
	}

	public JLabel getLblTipoIdentificacion() {
		return lblTipoIdentificacion;
	}

	public JComboBox getCmbTipoIdentificacion() {
		return cmbTipoIdentificacion;
	}

	public JLabel getLblIdentificacion() {
		return lblIdentificacion;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public JTextField getTxtApellidos() {
		return txtApellidos;
	}

	public JLabel getLblProfesion() {
		return lblProfesion;
	}

	public JLabel getLblDomicilio() {
		return lblDomicilio;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public JComponent getFsDatosPersonales() {
		return fsDatosPersonales;
	}

	public JTextField getTxtProfesion() {
		return txtProfesion;
	}

	public JTextField getTxtDomicilio() {
		return txtDomicilio;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JLabel getLblCelular() {
		return lblCelular;
	}

	public JTextField getTxtCelular() {
		return txtCelular;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JComponent getFsDatosOficina() {
		return fsDatosOficina;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JLabel getLblDepartamento() {
		return lblDepartamento;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public JLabel getLblJefe() {
		return lblJefe;
	}

	public JTextField getTxtJefe() {
		return txtJefe;
	}

	public JButton getBtnBuscarJefe() {
		return btnBuscarJefe;
	}

	public JButton getBtnBorrarJefe() {
		return btnBorrarJefe;
	}

	public JLabel getLblTipoEmpleado() {
		return lblTipoEmpleado;
	}

	public JComboBox getCmbTipoEmpleado() {
		return cmbTipoEmpleado;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JLabel getLblExtesion() {
		return lblExtesion;
	}

	public JTextField getTxtExtension() {
		return txtExtension;
	}

	public JLabel getLblNivel() {
		return lblNivel;
	}

	public JTextField getTxtNivel() {
		return txtNivel;
	}

	public JComboBox getCmbBanco() {
		return cmbBanco;
	}

	public JComboBox getCmbTipoCuenta() {
		return cmbTipoCuenta;
	}

	public JTextField getTxtNumeroCuenta() {
		return txtNumeroCuenta;
	}
}
