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
public abstract class JPDonacionesClientes extends SpiritModelImpl {
	public JPDonacionesClientes() {
		initComponents();
		setName("DONACIONES CLIENTES");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblFundaciones = new JLabel();
		cmbFundaciones = new JComboBox();
		btnConsultar = new JButton();
		spTblDonaciones = new JScrollPane();
		tblDonaciones = new JTable();
		lblOperadorNegocio = new JLabel();
		txtOperadorNegocio = new JTextField();
		btnBuscarOperadorNegocio = new JButton();
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
					{null, null, null, null, null, null},
					{null, null, null, null, null, null},
				},
				new String[] {
					"#", "F. Emisi\u00f3n", "Transacci\u00f3n", "Detalle - Cliente", "FUNDACION", "TOTAL"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, true, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblDonaciones.setViewportView(tblDonaciones);
		}
		add(spTblDonaciones, cc.xywh(3, 15, 13, 5));

		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Cliente:");
		lblOperadorNegocio.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblOperadorNegocio, cc.xy(3, 5));

		//---- txtOperadorNegocio ----
		txtOperadorNegocio.setEditable(false);
		add(txtOperadorNegocio, cc.xywh(5, 5, 3, 1));

		//---- btnBuscarOperadorNegocio ----
		btnBuscarOperadorNegocio.setText("B");
		add(btnBuscarOperadorNegocio, cc.xy(9, 5));

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
		add(cmbFechaFinal, cc.xy(5, 9));

		//---- lblSaldo ----
		lblSaldo.setText("T O T A L :");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xy(13, 21));

		//---- txtSaldo ----
		txtSaldo.setHorizontalAlignment(JTextField.RIGHT);
		txtSaldo.setEditable(false);
		txtSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtSaldo, cc.xy(15, 21));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblFundaciones;
	private JComboBox cmbFundaciones;
	private JButton btnConsultar;
	private JScrollPane spTblDonaciones;
	private JTable tblDonaciones;
	private JLabel lblOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JButton btnBuscarOperadorNegocio;
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

	public JLabel getLblOperadorNegocio() {
		return lblOperadorNegocio;
	}

	public void setLblOperadorNegocio(JLabel lblOperadorNegocio) {
		this.lblOperadorNegocio = lblOperadorNegocio;
	}

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
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
