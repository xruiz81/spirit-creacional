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
public abstract class JPDonacionesTipoProducto extends SpiritModelImpl {
	public JPDonacionesTipoProducto() {
		initComponents();
		setName("DONACIONES TIPO PRODUCTO");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblFundaciones = new JLabel();
		cmbFundaciones = new JComboBox();
		btnConsultar = new JButton();
		spTblDonaciones = new JScrollPane();
		tblDonaciones = new JTable();
		lblTipoProducto = new JLabel();
		lblModelo = new JLabel();
		cmbModelo = new JComboBox();
		lblColor = new JLabel();
		cmbColor = new JComboBox();
		cmbTipoProducto = new JComboBox();
		lblTalla = new JLabel();
		cmbTalla = new JComboBox();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnResetearFechas = new JButton();
		lblFechaFinal = new JLabel();
		cmbFechaFinal = new DateComboBox();
		lblSaldo = new JLabel();
		txtSaldo = new JTextField();
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
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblFundaciones ----
		lblFundaciones.setText("Fundaciones:");
		lblFundaciones.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFundaciones, cc.xy(3, 3));

		//---- cmbFundaciones ----
		cmbFundaciones.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbFundaciones, cc.xywh(5, 3, 5, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== spTblDonaciones ========
		{
			
			//---- tblDonaciones ----
			tblDonaciones.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null},
				},
				new String[] {
					"#", "T. Producto", "Fundaci\u00f3n", "Fecha Fact.", "No. Vendidos", "No. Dev.", "Cantidad", "Valor"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					true, false, false, true, false, true, true, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblDonaciones.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spTblDonaciones.setViewportView(tblDonaciones);
		}
		add(spTblDonaciones, cc.xywh(3, 17, 13, 5));

		//---- lblTipoProducto ----
		lblTipoProducto.setText("Tipo Producto:");
		lblTipoProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTipoProducto, cc.xy(3, 5));

		//---- lblModelo ----
		lblModelo.setText("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblModelo, cc.xy(7, 5));

		//---- cmbModelo ----
		cmbModelo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbModelo, cc.xy(9, 5));

		//---- lblColor ----
		lblColor.setText("Color:");
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblColor, cc.xy(3, 7));

		//---- cmbColor ----
		cmbColor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbColor, cc.xy(5, 7));

		//---- cmbTipoProducto ----
		cmbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTipoProducto, cc.xy(5, 5));

		//---- lblTalla ----
		lblTalla.setText("Talla:");
		lblTalla.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTalla, cc.xy(7, 7));

		//---- cmbTalla ----
		cmbTalla.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTalla, cc.xy(9, 7));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xy(3, 9));
		add(cmbFechaInicial, cc.xy(5, 9));

		//---- btnResetearFechas ----
		btnResetearFechas.setText("Resetear fechas");
		btnResetearFechas.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnResetearFechas, cc.xywh(7, 9, 4, 1));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xy(3, 11));
		add(cmbFechaFinal, cc.xy(5, 11));

		//---- lblSaldo ----
		lblSaldo.setText("T O T A L :");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xy(13, 23));

		//---- txtSaldo ----
		txtSaldo.setHorizontalAlignment(JTextField.RIGHT);
		txtSaldo.setEditable(false);
		txtSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtSaldo, cc.xy(15, 23));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblFundaciones;
	private JComboBox cmbFundaciones;
	private JButton btnConsultar;
	private JScrollPane spTblDonaciones;
	private JTable tblDonaciones;
	private JLabel lblTipoProducto;
	private JLabel lblModelo;
	private JComboBox cmbModelo;
	private JLabel lblColor;
	private JComboBox cmbColor;
	private JComboBox cmbTipoProducto;
	private JLabel lblTalla;
	private JComboBox cmbTalla;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JButton btnResetearFechas;
	private JLabel lblFechaFinal;
	private DateComboBox cmbFechaFinal;
	private JLabel lblSaldo;
	private JTextField txtSaldo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblFundaciones() {
		return lblFundaciones;
	}

	public void setLblFundaciones(JLabel lblFundaciones) {
		this.lblFundaciones = lblFundaciones;
	}

	public JComboBox getCmbFundaciones() {
		return cmbFundaciones;
	}

	public void setCmbFundaciones(JComboBox cmbFundaciones) {
		this.cmbFundaciones = cmbFundaciones;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JScrollPane getSpTblDonaciones() {
		return spTblDonaciones;
	}

	public void setSpTblDonaciones(JScrollPane spTblDonaciones) {
		this.spTblDonaciones = spTblDonaciones;
	}

	public JTable getTblDonaciones() {
		return tblDonaciones;
	}

	public void setTblDonaciones(JTable tblDonaciones) {
		this.tblDonaciones = tblDonaciones;
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

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public void setCmbFechaFinal(DateComboBox cmbFechaFinal) {
		this.cmbFechaFinal = cmbFechaFinal;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

	public JTextField getTxtSaldo() {
		return txtSaldo;
	}

	public void setTxtSaldo(JTextField txtSaldo) {
		this.txtSaldo = txtSaldo;
	}
	
	
	
}
