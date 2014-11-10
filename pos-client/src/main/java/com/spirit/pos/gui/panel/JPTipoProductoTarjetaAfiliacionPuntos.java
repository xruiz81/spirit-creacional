package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPTipoProductoTarjetaAfiliacionPuntos extends SpiritModelImpl {
	public JPTipoProductoTarjetaAfiliacionPuntos() {
		initComponents();
		setName("Puntos por tipo de producto");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoTarjetaAfiliacion = new JLabel();
		cmbTipoTarjetaAfiliacion = new JComboBox();
		separator1 = new JSeparator();
		lblSaldo3 = new JLabel();
		txtPuntosDinero = new JTextField();
		label1 = new JLabel();
		lblSaldo5 = new JLabel();
		txtDsctoReferido = new JTextField();
		lblSaldo4 = new JLabel();
		txtDsctoPropietario = new JTextField();
		separator2 = new JSeparator();
		lblTipoProducto = new JLabel();
		cmbTipoProducto = new JComboBox();
		lblSaldo = new JLabel();
		txtPuntosReferido = new JTextField();
		lblSaldo2 = new JLabel();
		txtPuntosPropietario = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//---- lblTipoTarjetaAfiliacion ----
		lblTipoTarjetaAfiliacion.setText("Tipo Tarjeta de Afiliaci\u00f3n:");
		lblTipoTarjetaAfiliacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTipoTarjetaAfiliacion, cc.xy(3, 3));

		//---- cmbTipoTarjetaAfiliacion ----
		cmbTipoTarjetaAfiliacion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTipoTarjetaAfiliacion, cc.xywh(5, 3, 3, 1));

		//---- separator1 ----
		separator1.setForeground(new Color(153, 51, 0));
		add(separator1, cc.xywh(3, 5, 5, 1));

		//---- lblSaldo3 ----
		lblSaldo3.setText("Relaci\u00f3n Puntos/Dinero:");
		lblSaldo3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblSaldo3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo3.setBackground(new Color(236, 233, 216));
		add(lblSaldo3, cc.xy(3, 7));

		//---- txtPuntosDinero ----
		txtPuntosDinero.setHorizontalAlignment(JTextField.RIGHT);
		txtPuntosDinero.setEditable(true);
		txtPuntosDinero.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtPuntosDinero, cc.xy(5, 7));

		//---- label1 ----
		label1.setText("Ejemplo: (Pts/$) = 50/1");
		add(label1, cc.xy(7, 7));

		//---- lblSaldo5 ----
		lblSaldo5.setText("Dscto Referido:");
		lblSaldo5.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblSaldo5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo5.setBackground(new Color(236, 233, 216));
		add(lblSaldo5, cc.xy(3, 9));

		//---- txtDsctoReferido ----
		txtDsctoReferido.setHorizontalAlignment(JTextField.RIGHT);
		txtDsctoReferido.setEditable(true);
		txtDsctoReferido.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtDsctoReferido, cc.xy(5, 9));

		//---- lblSaldo4 ----
		lblSaldo4.setText("Dscto. Propietario:");
		lblSaldo4.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblSaldo4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo4.setBackground(new Color(236, 233, 216));
		add(lblSaldo4, cc.xy(3, 11));

		//---- txtDsctoPropietario ----
		txtDsctoPropietario.setHorizontalAlignment(JTextField.RIGHT);
		txtDsctoPropietario.setEditable(true);
		txtDsctoPropietario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtDsctoPropietario, cc.xy(5, 11));

		//---- separator2 ----
		separator2.setForeground(new Color(153, 51, 0));
		add(separator2, cc.xywh(3, 13, 5, 1));

		//---- lblTipoProducto ----
		lblTipoProducto.setText("Tipo Producto:");
		lblTipoProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTipoProducto, cc.xy(3, 15));

		//---- cmbTipoProducto ----
		cmbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTipoProducto, cc.xywh(5, 15, 3, 1));

		//---- lblSaldo ----
		lblSaldo.setText("Puntos por item REFERIDO:");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xywh(3, 17, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- txtPuntosReferido ----
		txtPuntosReferido.setHorizontalAlignment(JTextField.RIGHT);
		txtPuntosReferido.setEditable(true);
		txtPuntosReferido.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtPuntosReferido, cc.xy(5, 17));

		//---- lblSaldo2 ----
		lblSaldo2.setText("Puntos por item PROPIETARIO:");
		lblSaldo2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		lblSaldo2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo2.setBackground(new Color(236, 233, 216));
		add(lblSaldo2, cc.xy(3, 19));

		//---- txtPuntosPropietario ----
		txtPuntosPropietario.setHorizontalAlignment(JTextField.RIGHT);
		txtPuntosPropietario.setEditable(true);
		txtPuntosPropietario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtPuntosPropietario, cc.xy(5, 19));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoTarjetaAfiliacion;
	private JComboBox cmbTipoTarjetaAfiliacion;
	private JSeparator separator1;
	private JLabel lblSaldo3;
	private JTextField txtPuntosDinero;
	private JLabel label1;
	private JLabel lblSaldo5;
	private JTextField txtDsctoReferido;
	private JLabel lblSaldo4;
	private JTextField txtDsctoPropietario;
	private JSeparator separator2;
	private JLabel lblTipoProducto;
	private JComboBox cmbTipoProducto;
	private JLabel lblSaldo;
	private JTextField txtPuntosReferido;
	private JLabel lblSaldo2;
	private JTextField txtPuntosPropietario;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblTipoTarjetaAfiliacion() {
		return lblTipoTarjetaAfiliacion;
	}

	public void setLblTipoTarjetaAfiliacion(JLabel lblTipoTarjetaAfiliacion) {
		this.lblTipoTarjetaAfiliacion = lblTipoTarjetaAfiliacion;
	}

	public JComboBox getCmbTipoTarjetaAfiliacion() {
		return cmbTipoTarjetaAfiliacion;
	}

	public void setCmbTipoTarjetaAfiliacion(JComboBox cmbTipoTarjetaAfiliacion) {
		this.cmbTipoTarjetaAfiliacion = cmbTipoTarjetaAfiliacion;
	}

	public JSeparator getSeparator1() {
		return separator1;
	}

	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
	}

	public JLabel getLblSaldo3() {
		return lblSaldo3;
	}

	public void setLblSaldo3(JLabel lblSaldo3) {
		this.lblSaldo3 = lblSaldo3;
	}

	public JTextField getTxtPuntosDinero() {
		return txtPuntosDinero;
	}

	public void setTxtPuntosDinero(JTextField txtPuntosDinero) {
		this.txtPuntosDinero = txtPuntosDinero;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLblSaldo5() {
		return lblSaldo5;
	}

	public void setLblSaldo5(JLabel lblSaldo5) {
		this.lblSaldo5 = lblSaldo5;
	}

	public JTextField getTxtDsctoReferido() {
		return txtDsctoReferido;
	}

	public void setTxtDsctoReferido(JTextField txtDsctoReferido) {
		this.txtDsctoReferido = txtDsctoReferido;
	}

	public JLabel getLblSaldo4() {
		return lblSaldo4;
	}

	public void setLblSaldo4(JLabel lblSaldo4) {
		this.lblSaldo4 = lblSaldo4;
	}

	public JTextField getTxtDsctoPropietario() {
		return txtDsctoPropietario;
	}

	public void setTxtDsctoPropietario(JTextField txtDsctoPropietario) {
		this.txtDsctoPropietario = txtDsctoPropietario;
	}

	public JSeparator getSeparator2() {
		return separator2;
	}

	public void setSeparator2(JSeparator separator2) {
		this.separator2 = separator2;
	}

	public JLabel getLblTipoProducto() {
		return lblTipoProducto;
	}

	public void setLblTipoProducto(JLabel lblTipoProducto) {
		this.lblTipoProducto = lblTipoProducto;
	}

	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}

	public void setCmbTipoProducto(JComboBox cmbTipoProducto) {
		this.cmbTipoProducto = cmbTipoProducto;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

	public JTextField getTxtPuntosReferido() {
		return txtPuntosReferido;
	}

	public void setTxtPuntosReferido(JTextField txtPuntosReferido) {
		this.txtPuntosReferido = txtPuntosReferido;
	}

	public JLabel getLblSaldo2() {
		return lblSaldo2;
	}

	public void setLblSaldo2(JLabel lblSaldo2) {
		this.lblSaldo2 = lblSaldo2;
	}

	public JTextField getTxtPuntosPropietario() {
		return txtPuntosPropietario;
	}

	public void setTxtPuntosPropietario(JTextField txtPuntosPropietario) {
		this.txtPuntosPropietario = txtPuntosPropietario;
	}
	
	
}
