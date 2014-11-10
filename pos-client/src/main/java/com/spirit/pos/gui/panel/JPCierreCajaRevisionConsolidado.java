package com.spirit.pos.gui.panel;
import java.awt.Font;

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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPCierreCajaRevisionConsolidado extends MantenimientoModelImpl {
	public JPCierreCajaRevisionConsolidado() {
		initComponents();
		setName("Cierre Caja Revision Consolidado");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spTblDatosConsolidado = new JScrollPane();
		tblDatosConsolidado = new JTable();
		label1 = new JLabel();
		label13 = new JLabel();
		label12 = new JLabel();
		lblCajero22 = new JLabel();
		cmbOficina = new JComboBox();
		lblCajero2 = new JLabel();
		cmbCaja = new JComboBox();
		label54 = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnConsultar = new JButton();
		txtFechaApertura = new JTextField();
		txtValorInicial = new JTextField();
		txtFechaCierre = new JTextField();
		label132 = new JLabel();
		txtValorFinal = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spTblDatosConsolidado ========
		{
			
			//---- tblDatosConsolidado ----
			tblDatosConsolidado.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, "", "", null, null, null, null, "", null, null, null, null, null, null},
				},
				new String[] {
					"CAJERO", "F. Apertura", "F. Cierre", "Efec.", "T.C", "Cheq.", "G.C", "Cred. Cliente", "Dev.", "Donac.", "Caja INICIAL", "Mov. Ingr.", "Mov. Egr.", "Desc. EFEC.", "Desc. Doc", "Multas", "Caja FINAL"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblDatosConsolidado.setFont(new Font("Tahoma", Font.PLAIN, 11));
			spTblDatosConsolidado.setViewportView(tblDatosConsolidado);
		}
		add(spTblDatosConsolidado, cc.xywh(3, 13, 9, 5));

		//---- label1 ----
		label1.setText("FECHA APERTURA:");
		label1.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label1, cc.xy(3, 19));

		//---- label13 ----
		label13.setText("VALOR INICIAL:");
		label13.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label13, cc.xy(7, 19));

		//---- label12 ----
		label12.setText("FECHA CIERRE:");
		label12.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label12, cc.xy(3, 21));

		//---- lblCajero22 ----
		lblCajero22.setText("Oficina:");
		lblCajero22.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero22, cc.xy(3, 5));
		add(cmbOficina, cc.xy(5, 5));

		//---- lblCajero2 ----
		lblCajero2.setText("Caja-PC:");
		lblCajero2.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero2, cc.xy(3, 7));
		add(cmbCaja, cc.xy(5, 7));

		//---- label54 ----
		label54.setText("Fecha Apertura:");
		label54.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label54, cc.xy(3, 9));
		add(cmbFechaInicial, cc.xy(5, 9));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(7, 9));

		//---- txtFechaApertura ----
		txtFechaApertura.setEditable(false);
		txtFechaApertura.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(txtFechaApertura, cc.xy(5, 19));

		//---- txtValorInicial ----
		txtValorInicial.setEditable(false);
		txtValorInicial.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(txtValorInicial, cc.xy(9, 19));

		//---- txtFechaCierre ----
		txtFechaCierre.setEditable(false);
		txtFechaCierre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(txtFechaCierre, cc.xy(5, 21));

		//---- label132 ----
		label132.setText("VALOR FINAL:");
		label132.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label132, cc.xy(7, 21));

		//---- txtValorFinal ----
		txtValorFinal.setEditable(false);
		txtValorFinal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(txtValorFinal, cc.xy(9, 21));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spTblDatosConsolidado;
	private JTable tblDatosConsolidado;
	private JLabel label1;
	private JLabel label13;
	private JLabel label12;
	private JLabel lblCajero22;
	private JComboBox cmbOficina;
	private JLabel lblCajero2;
	private JComboBox cmbCaja;
	private JLabel label54;
	private DateComboBox cmbFechaInicial;
	private JButton btnConsultar;
	private JTextField txtFechaApertura;
	private JTextField txtValorInicial;
	private JTextField txtFechaCierre;
	private JLabel label132;
	private JTextField txtValorFinal;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JScrollPane getSpTblDatosConsolidado() {
		return spTblDatosConsolidado;
	}

	public void setSpTblDatosConsolidado(JScrollPane spTblDatosConsolidado) {
		this.spTblDatosConsolidado = spTblDatosConsolidado;
	}

	public JTable getTblDatosConsolidado() {
		return tblDatosConsolidado;
	}

	public void setTblDatosConsolidado(JTable tblDatosConsolidado) {
		this.tblDatosConsolidado = tblDatosConsolidado;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLabel13() {
		return label13;
	}

	public void setLabel13(JLabel label13) {
		this.label13 = label13;
	}

	public JLabel getLabel12() {
		return label12;
	}

	public void setLabel12(JLabel label12) {
		this.label12 = label12;
	}

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

	public JLabel getLblCajero2() {
		return lblCajero2;
	}

	public void setLblCajero2(JLabel lblCajero2) {
		this.lblCajero2 = lblCajero2;
	}

	public JComboBox getCmbCaja() {
		return cmbCaja;
	}

	public void setCmbCaja(JComboBox cmbCaja) {
		this.cmbCaja = cmbCaja;
	}

	public JLabel getLabel54() {
		return label54;
	}

	public void setLabel54(JLabel label54) {
		this.label54 = label54;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public void setCmbFechaInicial(DateComboBox cmbFechaInicial) {
		this.cmbFechaInicial = cmbFechaInicial;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JTextField getTxtFechaApertura() {
		return txtFechaApertura;
	}

	public void setTxtFechaApertura(JTextField txtFechaApertura) {
		this.txtFechaApertura = txtFechaApertura;
	}

	public JTextField getTxtValorInicial() {
		return txtValorInicial;
	}

	public void setTxtValorInicial(JTextField txtValorInicial) {
		this.txtValorInicial = txtValorInicial;
	}

	public JTextField getTxtFechaCierre() {
		return txtFechaCierre;
	}

	public void setTxtFechaCierre(JTextField txtFechaCierre) {
		this.txtFechaCierre = txtFechaCierre;
	}

	public JLabel getLabel132() {
		return label132;
	}

	public void setLabel132(JLabel label132) {
		this.label132 = label132;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public void setTxtValorFinal(JTextField txtValorFinal) {
		this.txtValorFinal = txtValorFinal;
	}
	
	
}
