package com.spirit.general.gui.panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

public abstract class JPOficina extends MantenimientoModelImpl{
	public JPOficina() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblCiudad = new JLabel();
		cmbCiudad = new JComboBox();
		lblAdministrador = new JLabel();
		txtAdministrador = new JTextField();
		btnBuscarAdministrador = new JButton();
		lblDireccion = new JLabel();
		txtDireccion = new JTextField();
		lblTelefono = new JLabel();
		txtTelefono = new JTextField();
		lblFax = new JLabel();
		txtFax = new JTextField();
		lblPreimpresoSerie = new JLabel();
		txtPreimpresoSerie = new JFormattedTextField();
		spTblOficina = new JScrollPane();
		tblOficina = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Oficina");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;30dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(30), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
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
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtNombre, cc.xywh(5, 5, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCiudad ----
		lblCiudad.setText("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCiudad, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbCiudad, cc.xywh(5, 7, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblAdministrador ----
		lblAdministrador.setText("Administrador:");
		lblAdministrador.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblAdministrador, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtAdministrador, cc.xywh(5, 9, 11, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(btnBuscarAdministrador, cc.xywh(17, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblDireccion ----
		lblDireccion.setText("Direcci\u00f3n:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDireccion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtDireccion, cc.xywh(5, 11, 15, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTelefono ----
		lblTelefono.setText("Tel\u00e9fono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTelefono, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtTelefono, cc.xywh(5, 13, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFax ----
		lblFax.setText("Fax:");
		add(lblFax, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtFax, cc.xywh(11, 13, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblPreimpresoSerie ----
		lblPreimpresoSerie.setText("Preimpreso (Serie):");
		lblPreimpresoSerie.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPreimpresoSerie, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtPreimpresoSerie, cc.xywh(5, 15, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== spTblOficina ========
		{
			
			//---- tblOficina ----
			tblOficina.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"Codigo", "Nombre", "Ciudad", "Administrador"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblOficina.setViewportView(tblOficina);
		}
		add(spTblOficina, cc.xywh(3, 19, 19, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarAdministrador.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarAdministrador.setToolTipText("Buscar Administrador");
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblCiudad;
	private JComboBox cmbCiudad;
	private JLabel lblAdministrador;
	private JTextField txtAdministrador;
	private JButton btnBuscarAdministrador;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblFax;
	private JTextField txtFax;
	private JLabel lblPreimpresoSerie;
	private JFormattedTextField txtPreimpresoSerie;
	private JScrollPane spTblOficina;
	private JTable tblOficina;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarAdministrador() {
		return btnBuscarAdministrador;
	}

	public void setBtnBuscarAdministrador(JButton btnBuscarAdministrador) {
		this.btnBuscarAdministrador = btnBuscarAdministrador;
	}

	public JComboBox getCmbCiudad() {
		return cmbCiudad;
	}

	public void setCmbCiudad(JComboBox cmbCiudad) {
		this.cmbCiudad = cmbCiudad;
	}

	public JTextField getTxtAdministrador() {
		return txtAdministrador;
	}

	public void setTxtAdministrador(JTextField txtAdministrador) {
		this.txtAdministrador = txtAdministrador;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JTextField getTxtFax() {
		return txtFax;
	}

	public void setTxtFax(JTextField txtFax) {
		this.txtFax = txtFax;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JFormattedTextField getTxtPreimpresoSerie() {
		return txtPreimpresoSerie;
	}

	public void setTxtPreimpresoSerie(JFormattedTextField txtPreimpresoSerie) {
		this.txtPreimpresoSerie = txtPreimpresoSerie;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JScrollPane getSpTblOficina() {
		return spTblOficina;
	}

	public void setSpTblOficina(JScrollPane spTblOficina) {
		this.spTblOficina = spTblOficina;
	}

	public JTable getTblOficina() {
		return tblOficina;
	}

	public void setTblOficina(JTable tblOficina) {
		this.tblOficina = tblOficina;
	}	
}
