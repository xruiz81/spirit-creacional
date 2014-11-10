package com.spirit.seguridad.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPRoles extends SpiritModelImpl {

	public JPRoles() {
		initComponents();
		setName("Administracion de Roles");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		fsPermisos = compFactory.createSeparator("Permisos");
		lblMenuOriginal = new JLabel();
		lblMenuPersonalizado = new JLabel();
		btnQuitar = new JButton();
		txtCodigo = new JTextField();
		lblCodigo = new JLabel();
		txtNombre = new JTextField();
		lblNombre = new JLabel();
		lblEmpresa = new JLabel();
		btnEmpresa = new JButton();
		txtEmpresa = new JTextField();
		cmbStatus = new JComboBox();
		lblStatus = new JLabel();
		spTreeMenuOriginal = new JScrollPane();
		treeMenuOriginal = new JTree();
		lblFuncion = new JLabel();
		spTreeMenuPersonalizado = new JScrollPane();
		treeMenuPersonalizado = new JTree();
		panelFunciones = new JPanel();
		cbGrabarActualizar = new JCheckBox();
		cbBorrar = new JCheckBox();
		cbAutorizar = new JCheckBox();
		cbConsultar = new JCheckBox();
		cbDuplicar = new JCheckBox();
		cbImprimir = new JCheckBox();
		cbGenerarGrafico = new JCheckBox();
		btnAgregar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX4),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX4),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(200)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX4),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY8),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY7),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
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
				new RowSpec(Sizes.DLUY8)
			}));
		add(fsPermisos, cc.xywh(3, 13, 15, 1));

		//---- lblMenuOriginal ----
		lblMenuOriginal.setText("Men\u00fa Original");
		lblMenuOriginal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblMenuOriginal, cc.xywh(3, 15, 5, 1));

		//---- lblMenuPersonalizado ----
		lblMenuPersonalizado.setText("Men\u00fa Personalizado");
		lblMenuPersonalizado.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblMenuPersonalizado, cc.xy(17, 15));

		//---- btnQuitar ----
		btnQuitar.setText("<");
		add(btnQuitar, cc.xy(13, 21));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 3, 1));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblEmpresa ----
		lblEmpresa.setText("Empresa:");
		add(lblEmpresa, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(btnEmpresa, cc.xywh(9, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(txtEmpresa, cc.xywh(5, 7, 3, 1));
		add(cmbStatus, cc.xy(5, 9));

		//---- lblStatus ----
		lblStatus.setText("Status:");
		add(lblStatus, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== spTreeMenuOriginal ========
		{
			
			//---- treeMenuOriginal ----
			treeMenuOriginal.setBorder(new EtchedBorder());
			spTreeMenuOriginal.setViewportView(treeMenuOriginal);
		}
		add(spTreeMenuOriginal, cc.xywh(3, 17, 7, 7));

		//---- lblFuncion ----
		lblFuncion.setText("Funciones");
		lblFuncion.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblFuncion, cc.xy(21, 15));

		//======== spTreeMenuPersonalizado ========
		{
			
			//---- treeMenuPersonalizado ----
			treeMenuPersonalizado.setBorder(new EtchedBorder());
			spTreeMenuPersonalizado.setViewportView(treeMenuPersonalizado);
		}
		add(spTreeMenuPersonalizado, cc.xywh(17, 17, 1, 7));

		//======== panelFunciones ========
		{
			panelFunciones.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default"),
				new RowSpec[] {
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
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- cbGrabarActualizar ----
			cbGrabarActualizar.setText("Grabar / Actualizar");
			panelFunciones.add(cbGrabarActualizar, cc.xy(1, 1));
			
			//---- cbBorrar ----
			cbBorrar.setText("Borrar");
			panelFunciones.add(cbBorrar, cc.xy(1, 3));
			
			//---- cbAutorizar ----
			cbAutorizar.setText("Autorizar");
			panelFunciones.add(cbAutorizar, cc.xy(1, 7));
			
			//---- cbConsultar ----
			cbConsultar.setText("Consultar");
			panelFunciones.add(cbConsultar, cc.xy(1, 5));
			
			//---- cbDuplicar ----
			cbDuplicar.setText("Duplicar");
			panelFunciones.add(cbDuplicar, cc.xy(1, 9));
			
			//---- cbImprimir ----
			cbImprimir.setText("Imprimir");
			panelFunciones.add(cbImprimir, cc.xy(1, 11));
			
			//---- cbGenerarGrafico ----
			cbGenerarGrafico.setText("Generar Gr\u00e1fico");
			panelFunciones.add(cbGenerarGrafico, cc.xy(1, 13));
		}
		add(panelFunciones, cc.xywh(21, 17, 1, 7));

		//---- btnAgregar ----
		btnAgregar.setText(">");
		add(btnAgregar, cc.xy(13, 19));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnQuitar.setText("");
		btnAgregar.setText("");
		btnQuitar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goPreviousRecord.png"));
		btnAgregar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/goNextRecord.png"));
		btnEmpresa.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JComponent fsPermisos;
	private JLabel lblMenuOriginal;
	private JLabel lblMenuPersonalizado;
	private JButton btnQuitar;
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JTextField txtNombre;
	private JLabel lblNombre;
	private JLabel lblEmpresa;
	private JButton btnEmpresa;
	private JTextField txtEmpresa;
	private JComboBox cmbStatus;
	private JLabel lblStatus;
	private JScrollPane spTreeMenuOriginal;
	private JTree treeMenuOriginal;
	private JLabel lblFuncion;
	private JScrollPane spTreeMenuPersonalizado;
	private JTree treeMenuPersonalizado;
	private JPanel panelFunciones;
	private JCheckBox cbGrabarActualizar;
	private JCheckBox cbBorrar;
	private JCheckBox cbAutorizar;
	private JCheckBox cbConsultar;
	private JCheckBox cbDuplicar;
	private JCheckBox cbImprimir;
	private JCheckBox cbGenerarGrafico;
	private JButton btnAgregar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JCheckBox getCbAutorizar() {
		return cbAutorizar;
	}

	public void setCbAutorizar(JCheckBox cbAutorizar) {
		this.cbAutorizar = cbAutorizar;
	}

	public JCheckBox getCbBorrar() {
		return cbBorrar;
	}

	public void setCbBorrar(JCheckBox cbBorrar) {
		this.cbBorrar = cbBorrar;
	}

	public JCheckBox getCbConsultar() {
		return cbConsultar;
	}

	public void setCbConsultar(JCheckBox cbConsultar) {
		this.cbConsultar = cbConsultar;
	}

	public JCheckBox getCbGenerarGrafico() {
		return cbGenerarGrafico;
	}

	public void setCbGenerarGrafico(JCheckBox cbGenerarGrafico) {
		this.cbGenerarGrafico = cbGenerarGrafico;
	}

	public JCheckBox getCbGrabarActualizar() {
		return cbGrabarActualizar;
	}

	public void setCbGrabarActualizar(JCheckBox cbGrabarActualizar) {
		this.cbGrabarActualizar = cbGrabarActualizar;
	}

	public JCheckBox getCbImprimir() {
		return cbImprimir;
	}

	public void setCbImprimir(JCheckBox cbImprimir) {
		this.cbImprimir = cbImprimir;
	}

	public JComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(JComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public JTree getTreeMenuOriginal() {
		return treeMenuOriginal;
	}

	public void setTreeMenuOriginal(JTree treeMenuOriginal) {
		this.treeMenuOriginal = treeMenuOriginal;
	}

	public JTree getTreeMenuPersonalizado() {
		return treeMenuPersonalizado;
	}

	public void setTreeMenuPersonalizado(JTree treeMenuPersonalizado) {
		this.treeMenuPersonalizado = treeMenuPersonalizado;
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

	public JCheckBox getCbDuplicar() {
		return cbDuplicar;
	}

	public void setCbDuplicar(JCheckBox cbDuplicar) {
		this.cbDuplicar = cbDuplicar;
	}

	public JButton getBtnEmpresa() {
		return btnEmpresa;
	}

	public void setBtnEmpresa(JButton btnEmpresa) {
		this.btnEmpresa = btnEmpresa;
	}

	public JTextField getTxtEmpresa() {
		return txtEmpresa;
	}

	public void setTxtEmpresa(JTextField txtEmpresa) {
		this.txtEmpresa = txtEmpresa;
	}
	
	
}
