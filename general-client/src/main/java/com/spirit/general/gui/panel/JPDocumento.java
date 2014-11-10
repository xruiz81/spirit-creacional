package com.spirit.general.gui.panel;
import javax.swing.JComboBox;
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



public abstract class JPDocumento extends MantenimientoModelImpl {
	public JPDocumento() {
		initComponents();
		setName("Documento");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		txtCodigo = new JTextField();
		txtNombre = new JTextField();
		txtAbreviado = new JTextField();
		cmbTipoDocumento = new JComboBox();
		cmbEstado = new JComboBox();
		cmbBonificacion = new JComboBox();
		cmbPrecioEspecial = new JComboBox();
		cmbDescuentoEspecial = new JComboBox();
		cmbMulta = new JComboBox();
		lblMulta = new JLabel();
		lblCodigo = new JLabel();
		lblNombre = new JLabel();
		lblAbreviado = new JLabel();
		lblTipoDocumento = new JLabel();
		lblEstado = new JLabel();
		lblDiferido = new JLabel();
		cmbDiferido = new JComboBox();
		lblCheque = new JLabel();
		cmbCheque = new JComboBox();
		lblBonificacion = new JLabel();
		lblPrecioEspecial = new JLabel();
		lblProtesto = new JLabel();
		cmbProtesto = new JComboBox();
		lblDescuentoEspecial = new JLabel();
		lblRetencionRenta = new JLabel();
		cmbRetencionRenta = new JComboBox();
		lblPideAutorizacion = new JLabel();
		cmbPideAutorizacion = new JComboBox();
		lblInteres = new JLabel();
		cmbInteres = new JComboBox();
		lblRetencionIva = new JLabel();
		cmbRetencionIva = new JComboBox();
		spTblDocumento = new JScrollPane();
		tblDocumento = new JTable();
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
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(45)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
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
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(txtNombre, cc.xywh(5, 5, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(txtAbreviado, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbTipoDocumento, cc.xywh(5, 9, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbEstado, cc.xywh(5, 11, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbBonificacion, cc.xywh(5, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbPrecioEspecial, cc.xy(17, 13));
		add(cmbDescuentoEspecial, cc.xy(5, 15));
		add(cmbMulta, cc.xy(17, 15));

		//---- lblMulta ----
		lblMulta.setText("Cobra multa:");
		add(lblMulta, cc.xywh(15, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblAbreviado ----
		lblAbreviado.setText("Abreviado:");
		add(lblAbreviado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo Documento:");
		add(lblTipoDocumento, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblDiferido ----
		lblDiferido.setText("Es diferido:");
		add(lblDiferido, cc.xywh(15, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDiferido, cc.xy(17, 11));

		//---- lblCheque ----
		lblCheque.setText("Es cheque:");
		add(lblCheque, cc.xywh(21, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCheque, cc.xy(23, 11));

		//---- lblBonificacion ----
		lblBonificacion.setText("Es de Bonificaci\u00f3n:");
		add(lblBonificacion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPrecioEspecial ----
		lblPrecioEspecial.setText("Precio especial:");
		add(lblPrecioEspecial, cc.xywh(15, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblProtesto ----
		lblProtesto.setText("Admite protesto:");
		add(lblProtesto, cc.xywh(21, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbProtesto, cc.xy(23, 13));

		//---- lblDescuentoEspecial ----
		lblDescuentoEspecial.setText("Descuento especial:");
		add(lblDescuentoEspecial, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblRetencionRenta ----
		lblRetencionRenta.setText("Retiene renta:");
		lblRetencionRenta.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblRetencionRenta, cc.xy(21, 15));
		add(cmbRetencionRenta, cc.xy(23, 15));

		//---- lblPideAutorizacion ----
		lblPideAutorizacion.setText("Pide Autorizaci\u00f3n:");
		add(lblPideAutorizacion, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbPideAutorizacion, cc.xywh(5, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblInteres ----
		lblInteres.setText("Cobra inter\u00e9s:");
		add(lblInteres, cc.xywh(15, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbInteres, cc.xy(17, 17));

		//---- lblRetencionIva ----
		lblRetencionIva.setText("Retiene IVA:");
		add(lblRetencionIva, cc.xywh(21, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbRetencionIva, cc.xy(23, 17));

		//======== spTblDocumento ========
		{

			//---- tblDocumento ----
			tblDocumento.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"Codigo", "Nombre", "Tipo Documento", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblDocumento.setViewportView(tblDocumento);
		}
		add(spTblDocumento, cc.xywh(3, 21, 23, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtAbreviado;
	private JComboBox cmbTipoDocumento;
	private JComboBox cmbEstado;
	private JComboBox cmbBonificacion;
	private JComboBox cmbPrecioEspecial;
	private JComboBox cmbDescuentoEspecial;
	private JComboBox cmbMulta;
	private JLabel lblMulta;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JLabel lblAbreviado;
	private JLabel lblTipoDocumento;
	private JLabel lblEstado;
	private JLabel lblDiferido;
	private JComboBox cmbDiferido;
	private JLabel lblCheque;
	private JComboBox cmbCheque;
	private JLabel lblBonificacion;
	private JLabel lblPrecioEspecial;
	private JLabel lblProtesto;
	private JComboBox cmbProtesto;
	private JLabel lblDescuentoEspecial;
	private JLabel lblRetencionRenta;
	private JComboBox cmbRetencionRenta;
	private JLabel lblPideAutorizacion;
	private JComboBox cmbPideAutorizacion;
	private JLabel lblInteres;
	private JComboBox cmbInteres;
	private JLabel lblRetencionIva;
	private JComboBox cmbRetencionIva;
	private JScrollPane spTblDocumento;
	private JTable tblDocumento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbBonificacion() {
		return cmbBonificacion;
	}

	public void setCmbBonificacion(JComboBox cmbBonificacion) {
		this.cmbBonificacion = cmbBonificacion;
	}

	public JComboBox getCmbDescuentoEspecial() {
		return cmbDescuentoEspecial;
	}

	public void setCmbDescuentoEspecial(JComboBox cmbDescuentoEspecial) {
		this.cmbDescuentoEspecial = cmbDescuentoEspecial;
	}

	public JComboBox getCmbDiferido() {
		return cmbDiferido;
	}

	public void setCmbDiferido(JComboBox cmbDiferido) {
		this.cmbDiferido = cmbDiferido;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbInteres() {
		return cmbInteres;
	}

	public void setCmbInteres(JComboBox cmbInteres) {
		this.cmbInteres = cmbInteres;
	}

	public JComboBox getCmbMulta() {
		return cmbMulta;
	}

	public void setCmbMulta(JComboBox cmbMulta) {
		this.cmbMulta = cmbMulta;
	}

	public JComboBox getCmbPideAutorizacion() {
		return cmbPideAutorizacion;
	}

	public void setCmbPideAutorizacion(JComboBox cmbPideAutorizacion) {
		this.cmbPideAutorizacion = cmbPideAutorizacion;
	}

	public JComboBox getCmbPrecioEspecial() {
		return cmbPrecioEspecial;
	}

	public void setCmbPrecioEspecial(JComboBox cmbPrecioEspecial) {
		this.cmbPrecioEspecial = cmbPrecioEspecial;
	}

	public JComboBox getCmbProtesto() {
		return cmbProtesto;
	}

	public void setCmbProtesto(JComboBox cmbProtesto) {
		this.cmbProtesto = cmbProtesto;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JTextField getTxtAbreviado() {
		return txtAbreviado;
	}

	public void setTxtAbreviado(JTextField txtAbreviado) {
		this.txtAbreviado = txtAbreviado;
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

	public JComboBox getCmbCheque() {
		return cmbCheque;
	}

	public void setCmbCheque(JComboBox cmbCheque) {
		this.cmbCheque = cmbCheque;
	}

	public JComboBox getCmbRetencionRenta() {
		return cmbRetencionRenta;
	}

	public void setCmbRetencionRenta(JComboBox cmbRetencionRenta) {
		this.cmbRetencionRenta = cmbRetencionRenta;
	}

	public JComboBox getCmbRetencionIva() {
		return cmbRetencionIva;
	}

	public void setCmbRetencionIva(JComboBox cmbRetencionIva) {
		this.cmbRetencionIva = cmbRetencionIva;
	}

	public JScrollPane getSpTblDocumento() {
		return spTblDocumento;
	}

	public void setSpTblDocumento(JScrollPane spTblDocumento) {
		this.spTblDocumento = spTblDocumento;
	}

	public JTable getTblDocumento() {
		return tblDocumento;
	}

	public void setTblDocumento(JTable tblDocumento) {
		this.tblDocumento = tblDocumento;
	}
}
