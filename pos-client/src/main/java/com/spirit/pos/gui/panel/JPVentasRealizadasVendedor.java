package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public abstract class JPVentasRealizadasVendedor extends SpiritModelImpl {
	public JPVentasRealizadasVendedor() {
		initComponents();
		setName("VENTAS REALIZADAS POR VENDEDOR");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCajero22 = new JLabel();
		cmbOficina = new JComboBox();
		lblVendedor = new JLabel();
		cmbVendedor = new JComboBox();
		spTblVentasRealizadas = new JScrollPane();
		tblVentasRealizadas = new JTable();
		btnConsultar = new JButton();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnResetearFechas = new JButton();
		lblFechaFinal = new JLabel();
		lblSaldo = new JLabel();
		txtVentas = new JTextField();
		cmbFechaFinal = new DateComboBox();
		panel1 = new JPanel();
		lblSaldo22 = new JLabel();
		txtTotalPagarFinal = new JTextField();
		txtTotalDevoluciones = new JTextField();
		lblTotalCreditos = new JLabel();
		lblSaldo3 = new JLabel();
		lblSaldo2 = new JLabel();
		txtVentasNetas = new JTextField();
		txtTotalDescuentos = new JTextField();
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
				new ColumnSpec(Sizes.dluX(120)),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCajero22 ----
		lblCajero22.setText("Oficina:");
		lblCajero22.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero22, cc.xy(3, 3));
		add(cmbOficina, cc.xy(5, 3));

		//---- lblVendedor ----
		lblVendedor.setText("Vendedor");
		lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblVendedor, cc.xy(3, 5));

		//---- cmbVendedor ----
		cmbVendedor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbVendedor, cc.xywh(5, 5, 5, 1));

		//======== spTblVentasRealizadas ========
		{
			
			//---- tblVentasRealizadas ----
			tblVentasRealizadas.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"#", "F. Emisi\u00f3n", "Transacci\u00f3n", "Detalle", "VENDEDOR", "Ventas/Dev BRUTAS", "DSCTO", "DSCTO Ventas", "TOTAL", "IVA", "TOTAL A PAGAR"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, true, true, true, true, true, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblVentasRealizadas.setViewportView(tblVentasRealizadas);
		}
		add(spTblVentasRealizadas, cc.xywh(3, 15, 13, 5));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(11, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xy(3, 7));
		add(cmbFechaInicial, cc.xy(5, 7));

		//---- btnResetearFechas ----
		btnResetearFechas.setText("Resetear fechas");
		btnResetearFechas.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnResetearFechas, cc.xy(7, 7));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xy(3, 9));

		//---- lblSaldo ----
		lblSaldo.setText("V E N T A S  BRUTAS:");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xy(3, 21));

		//---- txtVentas ----
		txtVentas.setHorizontalAlignment(JTextField.RIGHT);
		txtVentas.setEditable(false);
		txtVentas.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtVentas, cc.xy(5, 21));
		add(cmbFechaFinal, cc.xy(5, 9));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				},
				RowSpec.decodeSpecs("default")));
			
			//---- lblSaldo22 ----
			lblSaldo22.setText("TOTAL A PAGAR:");
			lblSaldo22.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
			lblSaldo22.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSaldo22.setBackground(new Color(236, 233, 216));
			panel1.add(lblSaldo22, cc.xy(1, 1));
			
			//---- txtTotalPagarFinal ----
			txtTotalPagarFinal.setHorizontalAlignment(JTextField.RIGHT);
			txtTotalPagarFinal.setEditable(false);
			txtTotalPagarFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
			panel1.add(txtTotalPagarFinal, cc.xy(3, 1));
		}
		add(panel1, cc.xywh(13, 21, 3, 1));

		//---- txtTotalDevoluciones ----
		txtTotalDevoluciones.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalDevoluciones.setEditable(false);
		add(txtTotalDevoluciones, cc.xy(5, 23));

		//---- lblTotalCreditos ----
		lblTotalCreditos.setText("DEVOLUCIONES:");
		lblTotalCreditos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalCreditos, cc.xy(3, 23));

		//---- lblSaldo3 ----
		lblSaldo3.setText("DESCUENTOS:");
		lblSaldo3.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo3.setBackground(new Color(236, 233, 216));
		add(lblSaldo3, cc.xy(3, 25));

		//---- lblSaldo2 ----
		lblSaldo2.setText("V E N T A S  NETAS:");
		lblSaldo2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		lblSaldo2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo2.setBackground(new Color(236, 233, 216));
		add(lblSaldo2, cc.xy(3, 27));

		//---- txtVentasNetas ----
		txtVentasNetas.setHorizontalAlignment(JTextField.RIGHT);
		txtVentasNetas.setEditable(false);
		txtVentasNetas.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtVentasNetas, cc.xy(5, 27));

		//---- txtTotalDescuentos ----
		txtTotalDescuentos.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalDescuentos.setEditable(false);
		add(txtTotalDescuentos, cc.xy(5, 25));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCajero22;
	private JComboBox cmbOficina;
	private JLabel lblVendedor;
	private JComboBox cmbVendedor;
	private JScrollPane spTblVentasRealizadas;
	private JTable tblVentasRealizadas;
	private JButton btnConsultar;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JButton btnResetearFechas;
	private JLabel lblFechaFinal;
	private JLabel lblSaldo;
	private JTextField txtVentas;
	private DateComboBox cmbFechaFinal;
	private JPanel panel1;
	private JLabel lblSaldo22;
	private JTextField txtTotalPagarFinal;
	private JTextField txtTotalDevoluciones;
	private JLabel lblTotalCreditos;
	private JLabel lblSaldo3;
	private JLabel lblSaldo2;
	private JTextField txtVentasNetas;
	private JTextField txtTotalDescuentos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblCajero22() {
		return lblCajero22;
	}

	public void setLblCajero22(JLabel lblCajero22) {
		this.lblCajero22 = lblCajero22;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JLabel getLblVendedor() {
		return lblVendedor;
	}

	public void setLblVendedor(JLabel lblVendedor) {
		this.lblVendedor = lblVendedor;
	}

	public JComboBox getCmbVendedor() {
		return cmbVendedor;
	}

	public void setCmbVendedor(JComboBox cmbVendedor) {
		this.cmbVendedor = cmbVendedor;
	}

	public JScrollPane getSpTblVentasRealizadas() {
		return spTblVentasRealizadas;
	}

	public void setSpTblVentasRealizadas(JScrollPane spTblVentasRealizadas) {
		this.spTblVentasRealizadas = spTblVentasRealizadas;
	}

	public JTable getTblVentasRealizadas() {
		return tblVentasRealizadas;
	}

	public void setTblVentasRealizadas(JTable tblVentasRealizadas) {
		this.tblVentasRealizadas = tblVentasRealizadas;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
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

	public JTextField getTxtVentas() {
		return txtVentas;
	}

	public void setTxtVentas(JTextField txtVentas) {
		this.txtVentas = txtVentas;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public void setCmbFechaFinal(DateComboBox cmbFechaFinal) {
		this.cmbFechaFinal = cmbFechaFinal;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JLabel getLblSaldo22() {
		return lblSaldo22;
	}

	public void setLblSaldo22(JLabel lblSaldo22) {
		this.lblSaldo22 = lblSaldo22;
	}

	public JTextField getTxtTotalPagarFinal() {
		return txtTotalPagarFinal;
	}

	public void setTxtTotalPagarFinal(JTextField txtTotalPagarFinal) {
		this.txtTotalPagarFinal = txtTotalPagarFinal;
	}

	public JTextField getTxtTotalDevoluciones() {
		return txtTotalDevoluciones;
	}

	public void setTxtTotalDevoluciones(JTextField txtTotalDevoluciones) {
		this.txtTotalDevoluciones = txtTotalDevoluciones;
	}

	public JLabel getLblTotalCreditos() {
		return lblTotalCreditos;
	}

	public void setLblTotalCreditos(JLabel lblTotalCreditos) {
		this.lblTotalCreditos = lblTotalCreditos;
	}

	public JLabel getLblSaldo3() {
		return lblSaldo3;
	}

	public void setLblSaldo3(JLabel lblSaldo3) {
		this.lblSaldo3 = lblSaldo3;
	}

	public JLabel getLblSaldo2() {
		return lblSaldo2;
	}

	public void setLblSaldo2(JLabel lblSaldo2) {
		this.lblSaldo2 = lblSaldo2;
	}

	public JTextField getTxtVentasNetas() {
		return txtVentasNetas;
	}

	public void setTxtVentasNetas(JTextField txtVentasNetas) {
		this.txtVentasNetas = txtVentasNetas;
	}

	public JTextField getTxtTotalDescuentos() {
		return txtTotalDescuentos;
	}

	public void setTxtTotalDescuentos(JTextField txtTotalDescuentos) {
		this.txtTotalDescuentos = txtTotalDescuentos;
	}
	
	
	
}
