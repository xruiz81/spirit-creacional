package com.spirit.contabilidad.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
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
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPPlantillasContables extends SpiritModelImpl {
	public JPPlantillasContables() {
		initComponents();
		setName("Plantillas Contables");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		spTblPlantillaContable = new JScrollPane();
		tblPlantillaContable = new JTable();
		lblEventoContable = new JLabel();
		cmbEventoContable = new JComboBox();
		separador = new JSeparator();
		lblCuenta = new JLabel();
		txtCuenta = new JTextField();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		btnCuenta = new JButton();
		lblCuentaPredeterminada = new JLabel();
		txtCuentaPredeterminada = new JTextField();
		btnCuentaPredeterminada = new JButton();
		lblNemonico = new JLabel();
		txtNemonico = new JTextField();
		lblTipo = new JLabel();
		cmbTipoCuenta = new JComboBox();
		lblGlosa = new JLabel();
		txtGlosa = new JTextField();
		panel1 = new JPanel();
		btnAgregarRegistro = new JButton();
		btnActualizarRegistro = new JButton();
		btnRemoverRegistro = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DLUY8, FormSpec.NO_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.DLUY8, FormSpec.NO_GROW),
				new RowSpec(Sizes.dluY(10)),
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.DLUY5, FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//======== spTblPlantillaContable ========
		{

			//---- tblPlantillaContable ----
			tblPlantillaContable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Cuenta", "Cta. Predeterminada", "Debe", "Haber", "Nem\u00f3nico"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, true, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblPlantillaContable.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
			spTblPlantillaContable.setViewportView(tblPlantillaContable);
		}
		add(spTblPlantillaContable, cc.xywh(3, 18, 13, 5));

		//---- lblEventoContable ----
		lblEventoContable.setText("Evento Contable:");
		add(lblEventoContable, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEventoContable, cc.xy(5, 3));
		add(separador, cc.xywh(3, 5, 14, 1));

		//---- lblCuenta ----
		lblCuenta.setText("Cuenta:");
		add(lblCuenta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCuenta, cc.xy(5, 7));

		//---- lblReferencia ----
		lblReferencia.setText("Referencia:");
		add(lblReferencia, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtReferencia, cc.xywh(13, 7, 3, 1));

		//---- btnCuenta ----
		btnCuenta.setToolTipText("Buscar Cuenta");
		btnCuenta.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnCuenta, cc.xywh(7, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblCuentaPredeterminada ----
		lblCuentaPredeterminada.setText("Cuenta predeterminada:");
		add(lblCuentaPredeterminada, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCuentaPredeterminada, cc.xy(5, 9));

		//---- btnCuentaPredeterminada ----
		btnCuentaPredeterminada.setToolTipText("Buscar Cuenta Predeterminada");
		btnCuentaPredeterminada.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnCuentaPredeterminada.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnCuentaPredeterminada, cc.xywh(7, 9, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblNemonico ----
		lblNemonico.setText("Nem\u00f3nico:");
		add(lblNemonico, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNemonico, cc.xywh(13, 9, 3, 1));

		//---- lblTipo ----
		lblTipo.setText("Tipo de Cuenta:");
		add(lblTipo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoCuenta, cc.xy(5, 11));

		//---- lblGlosa ----
		lblGlosa.setText("Glosa:");
		add(lblGlosa, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtGlosa, cc.xywh(5, 13, 11, 1));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnAgregarRegistro ----
			btnAgregarRegistro.setToolTipText("Agregar Registro");
			btnAgregarRegistro.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
			panel1.add(btnAgregarRegistro, cc.xy(1, 1));

			//---- btnActualizarRegistro ----
			btnActualizarRegistro.setToolTipText("Actualizar Registro");
			btnActualizarRegistro.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
			panel1.add(btnActualizarRegistro, cc.xy(3, 1));

			//---- btnRemoverRegistro ----
			btnRemoverRegistro.setToolTipText("Eliminar Registro");
			btnRemoverRegistro.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
			panel1.add(btnRemoverRegistro, cc.xy(5, 1));
		}
		add(panel1, cc.xywh(3, 16, 13, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spTblPlantillaContable;
	private JTable tblPlantillaContable;
	private JLabel lblEventoContable;
	private JComboBox cmbEventoContable;
	private JSeparator separador;
	private JLabel lblCuenta;
	private JTextField txtCuenta;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JButton btnCuenta;
	private JLabel lblCuentaPredeterminada;
	private JTextField txtCuentaPredeterminada;
	private JButton btnCuentaPredeterminada;
	private JLabel lblNemonico;
	private JTextField txtNemonico;
	private JLabel lblTipo;
	private JComboBox cmbTipoCuenta;
	private JLabel lblGlosa;
	private JTextField txtGlosa;
	private JPanel panel1;
	private JButton btnAgregarRegistro;
	private JButton btnActualizarRegistro;
	private JButton btnRemoverRegistro;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnActualizarRegistro() {
		return btnActualizarRegistro;
	}

	public void setBtnActualizarRegistro(JButton btnActualizarRegistro) {
		this.btnActualizarRegistro = btnActualizarRegistro;
	}

	public JButton getBtnAgregarRegistro() {
		return btnAgregarRegistro;
	}

	public void setBtnAgregarRegistro(JButton btnAgregarRegistro) {
		this.btnAgregarRegistro = btnAgregarRegistro;
	}

	public JButton getBtnCuenta() {
		return btnCuenta;
	}

	public void setBtnCuenta(JButton btnCuenta) {
		this.btnCuenta = btnCuenta;
	}

	public JButton getBtnRemoverRegistro() {
		return btnRemoverRegistro;
	}

	public void setBtnRemoverRegistro(JButton btnRemoverRegistro) {
		this.btnRemoverRegistro = btnRemoverRegistro;
	}

	public JComboBox getCmbEventoContable() {
		return cmbEventoContable;
	}

	public void setCmbEventoContable(JComboBox cmbEventoContable) {
		this.cmbEventoContable = cmbEventoContable;
	}

	public JComboBox getCmbTipoCuenta() {
		return cmbTipoCuenta;
	}

	public void setCmbTipoCuenta(JComboBox cmbTipoCuenta) {
		this.cmbTipoCuenta = cmbTipoCuenta;
	}

	public JTable getTblPlantillaContable() {
		return tblPlantillaContable;
	}

	public void setTblPlantillaContable(JTable tblPlantillaContable) {
		this.tblPlantillaContable = tblPlantillaContable;
	}

	public JTextField getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(JTextField txtCuenta) {
		this.txtCuenta = txtCuenta;
	}

	public JTextField getTxtGlosa() {
		return txtGlosa;
	}

	public void setTxtGlosa(JTextField txtGlosa) {
		this.txtGlosa = txtGlosa;
	}

	public JTextField getTxtNemonico() {
		return txtNemonico;
	}

	public void setTxtNemonico(JTextField txtNemonico) {
		this.txtNemonico = txtNemonico;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextField getTxtCuentaPredeterminada() {
		return txtCuentaPredeterminada;
	}

	public void setTxtCuentaPredeterminada(JTextField txtCuentaPredeterminada) {
		this.txtCuentaPredeterminada = txtCuentaPredeterminada;
	}

	public JButton getBtnCuentaPredeterminada() {
		return btnCuentaPredeterminada;
	}

	public void setBtnCuentaPredeterminada(JButton btnCuentaPredeterminada) {
		this.btnCuentaPredeterminada = btnCuentaPredeterminada;
	}
}
