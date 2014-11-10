package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPVentasTipoProducto extends SpiritModelImpl {
	public JPVentasTipoProducto() {
		initComponents();
		setName("VENTAS TIPO PRODUCTO");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spTblDonaciones = new JScrollPane();
		tblVentas = new JTable();
		lblTipoProducto = new JLabel();
		lblModelo = new JLabel();
		cmbModelo = new JComboBox();
		lblColor = new JLabel();
		cmbColor = new JComboBox();
		cmbTipoProducto = new JComboBox();
		btnConsultar = new JButton();
		lblTalla = new JLabel();
		cmbTalla = new JComboBox();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnResetearFechas = new JButton();
		lblFechaFinal = new JLabel();
		lblSaldo = new JLabel();
		txtTotalVentas = new JTextField();
		cmbFechaFinal = new DateComboBox();
		lblNumVendidos = new JLabel();
		txtNumVendidos = new JTextField();
		lblSaldo2 = new JLabel();
		txtTotalDev = new JTextField();
		lblNumDevueltos = new JLabel();
		txtNumDevueltos = new JTextField();
		txtTotal = new JTextField();
		lblSaldo3 = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(45)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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

		//======== spTblDonaciones ========
		{
			
			//---- tblVentas ----
			tblVentas.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"#", "MODELO", "COLOR", "TALLA", "TIPO", "FECHA", "No. Vend.", "Valor", "Desc.", "ivaVentas", "tot VENTAS", "No.Dev", "val Dev", "ivaDev", "tot DEV"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, true, true, true, false, true, false, true, true, true, true, true, true, true, true
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblVentas.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spTblDonaciones.setViewportView(tblVentas);
		}
		add(spTblDonaciones, cc.xywh(3, 15, 13, 5));

		//---- lblTipoProducto ----
		lblTipoProducto.setText("Tipo Producto:");
		lblTipoProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTipoProducto, cc.xy(3, 3));

		//---- lblModelo ----
		lblModelo.setText("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblModelo, cc.xy(7, 3));

		//---- cmbModelo ----
		cmbModelo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbModelo, cc.xy(9, 3));

		//---- lblColor ----
		lblColor.setText("Color:");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblColor, cc.xy(3, 5));

		//---- cmbColor ----
		cmbColor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbColor, cc.xy(5, 5));

		//---- cmbTipoProducto ----
		cmbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTipoProducto, cc.xy(5, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblTalla ----
		lblTalla.setText("Talla:");
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTalla, cc.xy(7, 5));

		//---- cmbTalla ----
		cmbTalla.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTalla, cc.xy(9, 5));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xy(3, 7));
		add(cmbFechaInicial, cc.xy(5, 7));

		//---- btnResetearFechas ----
		btnResetearFechas.setText("Resetear fechas");
		btnResetearFechas.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnResetearFechas, cc.xywh(7, 7, 4, 1));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xy(3, 9));

		//---- lblSaldo ----
		lblSaldo.setText("T O T A L   VENTAS:");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xywh(13, 23, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- txtTotalVentas ----
		txtTotalVentas.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalVentas.setEditable(false);
		txtTotalVentas.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtTotalVentas, cc.xy(15, 23));
		add(cmbFechaFinal, cc.xy(5, 9));

		//---- lblNumVendidos ----
		lblNumVendidos.setText("No. VENDIDOS: ");
		lblNumVendidos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblNumVendidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumVendidos.setBackground(new Color(236, 233, 216));
		add(lblNumVendidos, cc.xy(5, 25));

		//---- txtNumVendidos ----
		txtNumVendidos.setEditable(false);
		add(txtNumVendidos, cc.xy(7, 25));

		//---- lblSaldo2 ----
		lblSaldo2.setText("T O T A L   DEVOLUCIONES:");
		lblSaldo2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo2.setBackground(new Color(236, 233, 216));
		add(lblSaldo2, cc.xywh(13, 25, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- txtTotalDev ----
		txtTotalDev.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalDev.setEditable(false);
		add(txtTotalDev, cc.xy(15, 25));

		//---- lblNumDevueltos ----
		lblNumDevueltos.setText("No. DEVUELTOS:");
		lblNumDevueltos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblNumDevueltos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumDevueltos.setBackground(new Color(236, 233, 216));
		add(lblNumDevueltos, cc.xy(5, 27));

		//---- txtNumDevueltos ----
		txtNumDevueltos.setEditable(false);
		add(txtNumDevueltos, cc.xy(7, 27));

		//---- txtTotal ----
		txtTotal.setHorizontalAlignment(JTextField.RIGHT);
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(txtTotal, cc.xy(15, 27));

		//---- lblSaldo3 ----
		lblSaldo3.setText("T O T A L  :");
		lblSaldo3.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo3.setBackground(new Color(236, 233, 216));
		add(lblSaldo3, cc.xywh(13, 27, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spTblDonaciones;
	private JTable tblVentas;
	private JLabel lblTipoProducto;
	private JLabel lblModelo;
	private JComboBox cmbModelo;
	private JLabel lblColor;
	private JComboBox cmbColor;
	private JComboBox cmbTipoProducto;
	private JButton btnConsultar;
	private JLabel lblTalla;
	private JComboBox cmbTalla;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JButton btnResetearFechas;
	private JLabel lblFechaFinal;
	private JLabel lblSaldo;
	private JTextField txtTotalVentas;
	private DateComboBox cmbFechaFinal;
	private JLabel lblNumVendidos;
	private JTextField txtNumVendidos;
	private JLabel lblSaldo2;
	private JTextField txtTotalDev;
	private JLabel lblNumDevueltos;
	private JTextField txtNumDevueltos;
	private JTextField txtTotal;
	private JLabel lblSaldo3;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JScrollPane getSpTblDonaciones() {
		return spTblDonaciones;
	}

	public void setSpTblDonaciones(JScrollPane spTblDonaciones) {
		this.spTblDonaciones = spTblDonaciones;
	}

	public JTable getTblVentas() {
		return tblVentas;
	}

	public void setTblVentas(JTable tblVentas) {
		this.tblVentas = tblVentas;
	}

	public JLabel getLblTipoProducto() {
		return lblTipoProducto;
	}

	public void setLblTipoProducto(JLabel lblTipoProducto) {
		this.lblTipoProducto = lblTipoProducto;
	}

	public JLabel getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(JLabel lblModelo) {
		this.lblModelo = lblModelo;
	}

	public JComboBox getCmbModelo() {
		return cmbModelo;
	}

	public void setCmbModelo(JComboBox cmbModelo) {
		this.cmbModelo = cmbModelo;
	}

	public JLabel getLblColor() {
		return lblColor;
	}

	public void setLblColor(JLabel lblColor) {
		this.lblColor = lblColor;
	}

	public JComboBox getCmbColor() {
		return cmbColor;
	}

	public void setCmbColor(JComboBox cmbColor) {
		this.cmbColor = cmbColor;
	}

	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}

	public void setCmbTipoProducto(JComboBox cmbTipoProducto) {
		this.cmbTipoProducto = cmbTipoProducto;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JLabel getLblTalla() {
		return lblTalla;
	}

	public void setLblTalla(JLabel lblTalla) {
		this.lblTalla = lblTalla;
	}

	public JComboBox getCmbTalla() {
		return cmbTalla;
	}

	public void setCmbTalla(JComboBox cmbTalla) {
		this.cmbTalla = cmbTalla;
	}

	public JLabel getLblFechaInicial() {
		return lblFechaInicial;
	}

	public void setLblFechaInicial(JLabel lblFechaInicial) {
		this.lblFechaInicial = lblFechaInicial;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public void setCmbFechaInicial(DateComboBox cmbFechaInicial) {
		this.cmbFechaInicial = cmbFechaInicial;
	}

	public JButton getBtnResetearFechas() {
		return btnResetearFechas;
	}

	public void setBtnResetearFechas(JButton btnResetearFechas) {
		this.btnResetearFechas = btnResetearFechas;
	}

	public JLabel getLblFechaFinal() {
		return lblFechaFinal;
	}

	public void setLblFechaFinal(JLabel lblFechaFinal) {
		this.lblFechaFinal = lblFechaFinal;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

	public JTextField getTxtTotalVentas() {
		return txtTotalVentas;
	}

	public void setTxtTotalVentas(JTextField txtTotalVentas) {
		this.txtTotalVentas = txtTotalVentas;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public void setCmbFechaFinal(DateComboBox cmbFechaFinal) {
		this.cmbFechaFinal = cmbFechaFinal;
	}

	public JLabel getLblNumVendidos() {
		return lblNumVendidos;
	}

	public void setLblNumVendidos(JLabel lblNumVendidos) {
		this.lblNumVendidos = lblNumVendidos;
	}

	public JTextField getTxtNumVendidos() {
		return txtNumVendidos;
	}

	public void setTxtNumVendidos(JTextField txtNumVendidos) {
		this.txtNumVendidos = txtNumVendidos;
	}

	public JLabel getLblSaldo2() {
		return lblSaldo2;
	}

	public void setLblSaldo2(JLabel lblSaldo2) {
		this.lblSaldo2 = lblSaldo2;
	}

	public JTextField getTxtTotalDev() {
		return txtTotalDev;
	}

	public void setTxtTotalDev(JTextField txtTotalDev) {
		this.txtTotalDev = txtTotalDev;
	}

	public JLabel getLblNumDevueltos() {
		return lblNumDevueltos;
	}

	public void setLblNumDevueltos(JLabel lblNumDevueltos) {
		this.lblNumDevueltos = lblNumDevueltos;
	}

	public JTextField getTxtNumDevueltos() {
		return txtNumDevueltos;
	}

	public void setTxtNumDevueltos(JTextField txtNumDevueltos) {
		this.txtNumDevueltos = txtNumDevueltos;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JLabel getLblSaldo3() {
		return lblSaldo3;
	}

	public void setLblSaldo3(JLabel lblSaldo3) {
		this.lblSaldo3 = lblSaldo3;
	}
	
	
	
}
