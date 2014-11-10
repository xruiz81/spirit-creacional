package com.spirit.contabilidad.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract  class JPCuenta extends SpiritModelImpl {

	private static final long serialVersionUID = 4595252674153839157L;

	public JPCuenta() {
		initComponents();
		setName("Cuentas Contables");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		spCuenta = new JScrollPane();
		treeCuenta = new JTree();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		txtNombre = new JFormattedTextField();
		txtNombreCorto = new JFormattedTextField();
		fsCaracteristicas = compFactory.createSeparator("Caracter\u00edsticas:");
		lblTipoCuenta = new JLabel();
		cmbTipoCuenta = new JComboBox();
		lblCodigo = new JLabel();
		txtCodigo = new JFormattedTextField();
		lblNombre = new JLabel();
		lblNombreCorto = new JLabel();
		lblPadre = new JLabel();
		txtPadre = new JTextField();
		btnPadre = new JButton();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		cbImputable = new JCheckBox();
		cbBanco = new JCheckBox();
		cbEfectivo = new JCheckBox();
		cbResultado = new JCheckBox();
		cbActivoFijo = new JCheckBox();
		lblNivel = new JLabel();
		txtNivel = new JFormattedTextField();
		fsActivoFijo = compFactory.createSeparator("Depreciaci\u00f3n/Amortizaci\u00f3n:");
		lblRelacionada = new JLabel();
		btnRelacionada = new JButton();
		txtRelacionada = new JTextField();
		lblTipoResultado = new JLabel();
		cmbTipoResultado = new JComboBox();
		cbLinea = new JCheckBox();
		fsCuentaResultado = compFactory.createSeparator("Cuenta de Resultado:");
		cbDepartamento = new JCheckBox();
		cbCentroGasto = new JCheckBox();
		cbEmpleado = new JCheckBox();
		cbCliente = new JCheckBox();
		popup = new JPopupMenu();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(130), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
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
				new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spCuenta ========
		{
			spCuenta.setViewportView(treeCuenta);
		}
		add(spCuenta, cc.xywh(3, 3, 1, 30));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan Cuenta:");
		add(lblPlanCuenta, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(9, 3, 7, 1));
		add(txtNombre, cc.xywh(9, 9, 9, 1));
		add(txtNombreCorto, cc.xywh(9, 11, 3, 1));
		add(fsCaracteristicas, cc.xywh(7, 17, 13, 1));

		//---- lblTipoCuenta ----
		lblTipoCuenta.setText("Tipo Cuenta:");
		add(lblTipoCuenta, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoCuenta, cc.xywh(9, 5, 7, 1));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(7, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xywh(9, 7, 5, 1));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(7, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblNombreCorto ----
		lblNombreCorto.setText("Nombre Corto:");
		add(lblNombreCorto, cc.xywh(7, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPadre ----
		lblPadre.setText("Padre:");
		add(lblPadre, cc.xywh(7, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtPadre, cc.xywh(9, 13, 9, 1));

		//---- btnPadre ----
		btnPadre.setToolTipText("Buscar cuenta padre");
		btnPadre.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		add(btnPadre, cc.xywh(19, 13, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(7, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVA",
			"INACTIVA"
		}));
		add(cmbEstado, cc.xy(9, 15));

		//---- cbImputable ----
		cbImputable.setText("Imputable");
		add(cbImputable, cc.xy(7, 19));
		
		//---- cbBanco ----
		cbBanco.setText("Banco");
		add(cbBanco, cc.xywh(17, 19, 3, 1));

		//---- cbEfectivo ----
		cbEfectivo.setText("Efectivo");
		add(cbEfectivo, cc.xy(11, 19));

		//---- cbResultado ----
		cbResultado.setText("Resultado");
		add(cbResultado, cc.xywh(13, 19, 3, 1));

		//---- cbActivoFijo ----
		cbActivoFijo.setText("Activo Fijo");
		add(cbActivoFijo, cc.xy(9, 19));

		//---- lblNivel ----
		lblNivel.setText("Nivel:");
		add(lblNivel, cc.xywh(17, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtNivel ----
		txtNivel.setHorizontalAlignment(JTextField.RIGHT);
		add(txtNivel, cc.xy(19, 15));
		add(fsActivoFijo, cc.xywh(7, 22, 13, 1));

		//---- lblRelacionada ----
		lblRelacionada.setText("Relacionada:");
		add(lblRelacionada, cc.xywh(7, 24, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- btnRelacionada ----
		btnRelacionada.setToolTipText("Buscar cuenta relacionada");
		btnRelacionada.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		add(btnRelacionada, cc.xywh(19, 24, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
		add(txtRelacionada, cc.xywh(9, 24, 9, 1));

		//---- lblTipoResultado ----
		lblTipoResultado.setText("Tipo Resultado:");
		add(lblTipoResultado, cc.xywh(7, 28, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoResultado, cc.xywh(9, 28, 9, 1));

		//---- cbLinea ----
		cbLinea.setText("L\u00ednea");
		cbLinea.setFocusTraversalPolicyProvider(false);
		cbLinea.setVerticalAlignment(SwingConstants.TOP);
		add(cbLinea, cc.xy(7, 30));
		add(fsCuentaResultado, cc.xywh(7, 26, 13, 1));

		//---- cbDepartamento ----
		cbDepartamento.setText("Departamento");
		cbDepartamento.setFocusTraversalPolicyProvider(false);
		cbDepartamento.setVerticalAlignment(SwingConstants.TOP);
		add(cbDepartamento, cc.xy(11, 30));

		//---- cbCentroGasto ----
		cbCentroGasto.setText("Centro Gasto");
		cbCentroGasto.setFocusTraversalPolicyProvider(false);
		cbCentroGasto.setVerticalAlignment(SwingConstants.TOP);
		add(cbCentroGasto, cc.xy(9, 30));

		//---- cbEmpleado ----
		cbEmpleado.setText("Empleado");
		cbEmpleado.setFocusTraversalPolicyProvider(false);
		cbEmpleado.setVerticalAlignment(SwingConstants.TOP);
		add(cbEmpleado, cc.xywh(17, 30, 3, 1));

		//---- cbCliente ----
		cbCliente.setText("Cliente");
		cbCliente.setFocusTraversalPolicyProvider(false);
		cbCliente.setVerticalAlignment(SwingConstants.TOP);
		add(cbCliente, cc.xywh(13, 30, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spCuenta;
	private JTree treeCuenta;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JFormattedTextField txtNombre;
	private JFormattedTextField txtNombreCorto;
	private JComponent fsCaracteristicas;
	private JLabel lblTipoCuenta;
	private JComboBox cmbTipoCuenta;
	private JLabel lblCodigo;
	private JFormattedTextField txtCodigo;
	private JLabel lblNombre;
	private JLabel lblNombreCorto;
	private JLabel lblPadre;
	private JTextField txtPadre;
	private JButton btnPadre;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JCheckBox cbImputable;
	private JCheckBox cbBanco;
	private JCheckBox cbEfectivo;
	private JCheckBox cbResultado;
	private JCheckBox cbActivoFijo;
	private JLabel lblNivel;
	private JFormattedTextField txtNivel;
	private JComponent fsActivoFijo;
	private JLabel lblRelacionada;
	private JButton btnRelacionada;
	private JTextField txtRelacionada;
	private JLabel lblTipoResultado;
	private JComboBox cmbTipoResultado;
	private JCheckBox cbLinea;
	private JComponent fsCuentaResultado;
	private JCheckBox cbDepartamento;
	private JCheckBox cbCentroGasto;
	private JCheckBox cbEmpleado;
	private JCheckBox cbCliente;
	protected JPopupMenu popup;
	protected JMenuItem menuItem;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnPadre() {
		return btnPadre;
	}

	public void setBtnPadre(JButton btnPadre) {
		this.btnPadre = btnPadre;
	}

	public JButton getBtnRelacionada() {
		return btnRelacionada;
	}

	public void setBtnRelacionada(JButton btnRelacionada) {
		this.btnRelacionada = btnRelacionada;
	}

	public JCheckBox getCbActivoFijo() {
		return cbActivoFijo;
	}

	public void setCbActivoFijo(JCheckBox cbActivoFijo) {
		this.cbActivoFijo = cbActivoFijo;
	}

	public JCheckBox getCbCentroGasto() {
		return cbCentroGasto;
	}

	public void setCbCentroGasto(JCheckBox cbCentroGasto) {
		this.cbCentroGasto = cbCentroGasto;
	}

	public JCheckBox getCbCliente() {
		return cbCliente;
	}

	public void setCbCliente(JCheckBox cbCliente) {
		this.cbCliente = cbCliente;
	}

	public JCheckBox getCbDepartamento() {
		return cbDepartamento;
	}

	public void setCbDepartamento(JCheckBox cbDepartamento) {
		this.cbDepartamento = cbDepartamento;
	}

	public JCheckBox getCbEfectivo() {
		return cbEfectivo;
	}

	public void setCbEfectivo(JCheckBox cbEfectivo) {
		this.cbEfectivo = cbEfectivo;
	}

	public JCheckBox getCbEmpleado() {
		return cbEmpleado;
	}

	public void setCbEmpleado(JCheckBox cbEmpleado) {
		this.cbEmpleado = cbEmpleado;
	}

	public JCheckBox getCbResultado() {
		return cbResultado;
	}

	public void setCbResultado(JCheckBox cbResultado) {
		this.cbResultado = cbResultado;
	}

	public JCheckBox getCbImputable() {
		return cbImputable;
	}
	
	public JCheckBox getCbBanco() {
		return cbBanco;
	}

	public void setCbImputable(JCheckBox cbImputable) {
		this.cbImputable = cbImputable;
	}
	
	public void setCbBanco(JCheckBox cbBanco) {
		this.cbBanco = cbBanco;
	}

	public JCheckBox getCbLinea() {
		return cbLinea;
	}

	public void setCbLinea(JCheckBox cbLinea) {
		this.cbLinea = cbLinea;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JComboBox getCmbTipoCuenta() {
		return cmbTipoCuenta;
	}

	public void setCmbTipoCuenta(JComboBox cmbTipoCuenta) {
		this.cmbTipoCuenta = cmbTipoCuenta;
	}

	public JComboBox getCmbTipoResultado() {
		return cmbTipoResultado;
	}

	public void setCmbTipoResultado(JComboBox cmbTipoResultado) {
		this.cmbTipoResultado = cmbTipoResultado;
	}

	public JFormattedTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JFormattedTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JFormattedTextField getTxtNivel() {
		return txtNivel;
	}

	public void setTxtNivel(JFormattedTextField txtNivel) {
		this.txtNivel = txtNivel;
	}

	public JFormattedTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JFormattedTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JFormattedTextField getTxtNombreCorto() {
		return txtNombreCorto;
	}

	public void setTxtNombreCorto(JFormattedTextField txtNombreCorto) {
		this.txtNombreCorto = txtNombreCorto;
	}

	public JTextField getTxtPadre() {
		return txtPadre;
	}

	public void setTxtPadre(JTextField txtPadre) {
		this.txtPadre = txtPadre;
	}

	public JTextField getTxtRelacionada() {
		return txtRelacionada;
	}

	public void setTxtRelacionada(JTextField txtRelacionada) {
		this.txtRelacionada = txtRelacionada;
	}

	public JTree getTreeCuenta() {
		return treeCuenta;
	}

	public void setTreeCuenta(JTree treeCuenta) {
		this.treeCuenta = treeCuenta;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}
}
