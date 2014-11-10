package com.spirit.general.gui.panel;
import javax.swing.JButton;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPEmpresa extends MantenimientoModelImpl {
	private static final long serialVersionUID = -5998989019899294220L;

	public JPEmpresa() {
		initComponents();
		setName("Empresa");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblRuc = new JLabel();
		txtRuc = new JTextField();
		lblLogo = new JLabel();
		txtLogo = new JTextField();
		lblWeb = new JLabel();
		txtWeb = new JTextField();
		spTblEmpresa = new JScrollPane();
		tblEmpresa = new JTable();
		btnCargarLogo = new JButton();
		lblEmailContador = new JLabel();
		txtEmailContador = new JTextField();
		lblRucContador = new JLabel();
		txtRucContador = new JTextField();
		cmbTipoIdentificacionRepresentante = new JComboBox();
		lblNumeroIdentificacion = new JLabel();
		txtNumeroIdentificacion = new JTextField();
		lblTipoIdentificacionRepresentante = new JLabel();
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
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;50dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(40), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(16)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 15}});

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo descriptivo para la empresa");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo para la empresa");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setToolTipText("Nombre de la Empresa");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre de la Empresa");
		add(txtNombre, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblRuc ----
		lblRuc.setText("RUC:");
		lblRuc.setToolTipText("RUC de la Empresa");
		add(lblRuc, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtRuc, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblLogo ----
		lblLogo.setText("Logo URL:");
		lblLogo.setToolTipText("Archivo que contiene el  logo de la empresa");
		add(lblLogo, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtLogo ----
		txtLogo.setToolTipText("Archivo que contiene el  logo de la empresa");
		add(txtLogo, cc.xywh(5, 9, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblWeb ----
		lblWeb.setText("Web URL:");
		lblWeb.setToolTipText("P\u00e1gina Web de la Empresa");
		add(lblWeb, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtWeb ----
		txtWeb.setToolTipText("P\u00e1gina Web de la Empresa");
		add(txtWeb, cc.xywh(5, 11, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblEmpresa ========
		{
			
			//---- tblEmpresa ----
			tblEmpresa.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "RUC"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblEmpresa.setViewportView(tblEmpresa);
		}
		add(spTblEmpresa, cc.xywh(3, 23, 11, 5));

		//---- btnCargarLogo ----
		add(btnCargarLogo, cc.xywh(13, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblEmailContador ----
		lblEmailContador.setText("Email contador:");
		add(lblEmailContador, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtEmailContador, cc.xywh(5, 13, 5, 1));

		//---- lblRucContador ----
		lblRucContador.setText("R.U.C. Contador:");
		add(lblRucContador, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtRucContador, cc.xywh(5, 15, 3, 1));
		add(cmbTipoIdentificacionRepresentante, cc.xywh(5, 17, 3, 1));

		//---- lblNumeroIdentificacion ----
		lblNumeroIdentificacion.setText("N\u00famero identificaci\u00f3n representante:");
		add(lblNumeroIdentificacion, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNumeroIdentificacion, cc.xywh(5, 19, 3, 1));

		//---- lblTipoIdentificacionRepresentante ----
		lblTipoIdentificacionRepresentante.setText("Tipo Identificaci\u00f3n representante:");
		add(lblTipoIdentificacionRepresentante, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnCargarLogo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		btnCargarLogo.setToolTipText("Cargar logo de la empresa");
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblRuc;
	private JTextField txtRuc;
	private JLabel lblLogo;
	private JTextField txtLogo;
	private JLabel lblWeb;
	private JTextField txtWeb;
	private JScrollPane spTblEmpresa;
	private JTable tblEmpresa;
	private JButton btnCargarLogo;
	private JLabel lblEmailContador;
	private JTextField txtEmailContador;
	private JLabel lblRucContador;
	private JTextField txtRucContador;
	private JComboBox cmbTipoIdentificacionRepresentante;
	private JLabel lblNumeroIdentificacion;
	private JTextField txtNumeroIdentificacion;
	private JLabel lblTipoIdentificacionRepresentante;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnCargarLogo() {
		return btnCargarLogo;
	}

	public void setBtnCargarLogo(JButton btnCargarLogo) {
		this.btnCargarLogo = btnCargarLogo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtLogo() {
		return txtLogo;
	}

	public void setTxtLogo(JTextField txtLogo) {
		this.txtLogo = txtLogo;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtRuc() {
		return txtRuc;
	}

	public void setTxtRuc(JTextField txtRuc) {
		this.txtRuc = txtRuc;
	}

	public JTextField getTxtWeb() {
		return txtWeb;
	}

	public void setTxtWeb(JTextField txtWeb) {
		this.txtWeb = txtWeb;
	}

	public JTable getTblEmpresa() {
		return tblEmpresa;
	}

	public void setTblEmpresa(JTable tblEmpresa) {
		this.tblEmpresa = tblEmpresa;
	}

	public JComboBox getCmbTipoIdentificacionRepresentante() {
		return cmbTipoIdentificacionRepresentante;
	}

	public void setCmbTipoIdentificacionRepresentante(
			JComboBox cmbTipoIdentificacionRepresentante) {
		this.cmbTipoIdentificacionRepresentante = cmbTipoIdentificacionRepresentante;
	}

	public JTextField getTxtEmailContador() {
		return txtEmailContador;
	}

	public void setTxtEmailContador(JTextField txtEmailContador) {
		this.txtEmailContador = txtEmailContador;
	}

	public JTextField getTxtNumeroIdentificacion() {
		return txtNumeroIdentificacion;
	}

	public void setTxtNumeroIdentificacion(JTextField txtNumeroIdentificacion) {
		this.txtNumeroIdentificacion = txtNumeroIdentificacion;
	}

	public JTextField getTxtRucContador() {
		return txtRucContador;
	}

	public void setTxtRucContador(JTextField txtRucContador) {
		this.txtRucContador = txtRucContador;
	}
}
