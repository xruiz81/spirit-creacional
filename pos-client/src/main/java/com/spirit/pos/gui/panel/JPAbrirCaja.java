package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPAbrirCaja extends MantenimientoModelImpl {
 	public JPAbrirCaja() {
 		initComponents();
 		setName("Abrir Caja");
 	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		textPane1 = new JTextPane();
		label5 = new JLabel();
		lblFecha = new JLabel();
		label1 = new JLabel();
		lblCajero = new JLabel();
		label32 = new JLabel();
		cmbPC = new JComboBox();
		label3 = new JLabel();
		label2 = new JLabel();
		cmbTurno = new JComboBox();
		txtImporte = new JTextField();
		btnActivarCaja = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setBackground(new Color(236, 233, 216));
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.CENTER, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== panel1 ========
		{
			panel1.setBorder(new LineBorder(new Color(0, 102, 0), 2, true));
			panel1.setBackground(new Color(236, 233, 216));
			panel1.setForeground(new Color(0, 0, 102));
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(55)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(55)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
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
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12))
				}));
			
			//---- textPane1 ----
			textPane1.setText("El usuario tiene una Caja Abierta debe primero cerrar la otra para poder abrir otra");
			textPane1.setEditable(false);
			textPane1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			panel1.add(textPane1, cc.xywh(3, 3, 5, 1, CellConstraints.FILL, CellConstraints.FILL));
			
			//---- label5 ----
			label5.setText("Fecha:");
			label5.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel1.add(label5, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panel1.add(lblFecha, cc.xywh(5, 5, 5, 1));
			
			//---- label1 ----
			label1.setText("Cajero: ");
			label1.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel1.add(label1, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panel1.add(lblCajero, cc.xywh(5, 7, 5, 1));
			
			//---- label32 ----
			label32.setText("Caja Registro:");
			label32.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel1.add(label32, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panel1.add(cmbPC, cc.xywh(5, 9, 3, 1));
			
			//---- label3 ----
			label3.setText("Turno:");
			label3.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel1.add(label3, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- label2 ----
			label2.setText("Valor Inicial:");
			label2.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel1.add(label2, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- cmbTurno ----
			cmbTurno.setModel(new DefaultComboBoxModel(new String[] {
				"Turno 1:     8:00  -  13:00",
				"Turno 2:    13:00 -  18:00",
				"Turno 3:    18:00 -  22:00"
			}));
			cmbTurno.setFont(new Font("Tahoma", Font.PLAIN, 11));
			panel1.add(cmbTurno, cc.xywh(5, 11, 3, 1));
			
			//---- txtImporte ----
			txtImporte.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtImporte.setEditable(false);
			panel1.add(txtImporte, cc.xy(5, 13));
			
			//---- btnActivarCaja ----
			btnActivarCaja.setText("ACTIVAR CAJA");
			btnActivarCaja.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel1.add(btnActivarCaja, cc.xywh(3, 15, 5, 1, CellConstraints.CENTER, CellConstraints.CENTER));
		}
		add(panel1, cc.xy(3, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JTextPane textPane1;
	private JLabel label5;
	private JLabel lblFecha;
	private JLabel label1;
	private JLabel lblCajero;
	private JLabel label32;
	private JComboBox cmbPC;
	private JLabel label3;
	private JLabel label2;
	private JComboBox cmbTurno;
	private JTextField txtImporte;
	private JButton btnActivarCaja;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JTextPane getTextPane1() {
		return textPane1;
	}

	public void setTextPane1(JTextPane textPane1) {
		this.textPane1 = textPane1;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public void setLabel5(JLabel label5) {
		this.label5 = label5;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLblCajero() {
		return lblCajero;
	}

	public void setLblCajero(JLabel lblCajero) {
		this.lblCajero = lblCajero;
	}

	public JLabel getLabel32() {
		return label32;
	}

	public void setLabel32(JLabel label32) {
		this.label32 = label32;
	}

	public JComboBox getCmbPC() {
		return cmbPC;
	}

	public void setCmbPC(JComboBox cmbPC) {
		this.cmbPC = cmbPC;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JComboBox getCmbTurno() {
		return cmbTurno;
	}

	public void setCmbTurno(JComboBox cmbTurno) {
		this.cmbTurno = cmbTurno;
	}

	public JTextField getTxtImporte() {
		return txtImporte;
	}

	public void setTxtImporte(JTextField txtImporte) {
		this.txtImporte = txtImporte;
	}

	public JButton getBtnActivarCaja() {
		return btnActivarCaja;
	}

	public void setBtnActivarCaja(JButton btnActivarCaja) {
		this.btnActivarCaja = btnActivarCaja;
	}
	
	
	
}
