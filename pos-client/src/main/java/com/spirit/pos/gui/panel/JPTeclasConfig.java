
package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
public abstract class JPTeclasConfig extends SpiritModelImpl {
	public JPTeclasConfig() {
		initComponents();
		setName("Teclas Configuracion");
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel2 = new JPanel();
		label5 = new JLabel();
		label52 = new JLabel();
		cmbNombreTecla2 = new JLabel();
		txtMascara = new JTextField();
		cmbNombreTecla = new JLabel();
		txtNombreTecla = new JTextField();
		label1 = new JLabel();
		cmbCodigo = new JComboBox();
		taDescripcion = new JTextArea();
		lblDescripcion = new JLabel();
		separator1 = new JSeparator();
		txtDatos = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(41)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(25)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(30)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- label5 ----
			label5.setText("PRESIONE UNA TECLA");
			label5.setFont(new Font("Tahoma", Font.BOLD, 12));
			label5.setForeground(Color.blue);
			panel2.add(label5, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- label52 ----
			label52.setText(" PARA OBTENER LOS DATOS");
			label52.setFont(new Font("Tahoma", Font.BOLD, 12));
			label52.setForeground(Color.blue);
			panel2.add(label52, cc.xy(1, 3));
		}
		add(panel2, cc.xy(3, 11));

		//---- cmbNombreTecla2 ----
		cmbNombreTecla2.setText("M\u00e1scara:");
		cmbNombreTecla2.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(cmbNombreTecla2, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtMascara, cc.xy(5, 13));

		//---- cmbNombreTecla ----
		cmbNombreTecla.setText("Nombre Tecla:");
		cmbNombreTecla.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(cmbNombreTecla, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtNombreTecla ----
		txtNombreTecla.setEditable(true);
		add(txtNombreTecla, cc.xy(5, 15));

		//---- label1 ----
		label1.setText("C\u00f3digo:");
		label1.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label1, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCodigo, cc.xywh(5, 3, 3, 1));
		add(taDescripcion, cc.xywh(5, 5, 3, 3));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n:");
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- separator1 ----
		separator1.setFocusTraversalPolicyProvider(true);
		add(separator1, cc.xywh(3, 9, 6, 1));
		add(txtDatos, cc.xy(5, 11));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel2;
	private JLabel label5;
	private JLabel label52;
	private JLabel cmbNombreTecla2;
	private JTextField txtMascara;
	private JLabel cmbNombreTecla;
	private JTextField txtNombreTecla;
	private JLabel label1;
	private JComboBox cmbCodigo;
	private JTextArea taDescripcion;
	private JLabel lblDescripcion;
	private JSeparator separator1;
	private JTextField txtDatos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public void setLabel5(JLabel label5) {
		this.label5 = label5;
	}

	public JLabel getLabel52() {
		return label52;
	}

	public void setLabel52(JLabel label52) {
		this.label52 = label52;
	}

	public JLabel getCmbNombreTecla2() {
		return cmbNombreTecla2;
	}

	public void setCmbNombreTecla2(JLabel cmbNombreTecla2) {
		this.cmbNombreTecla2 = cmbNombreTecla2;
	}

	public JTextField getTxtMascara() {
		return txtMascara;
	}

	public void setTxtMascara(JTextField txtMascara) {
		this.txtMascara = txtMascara;
	}

	public JLabel getCmbNombreTecla() {
		return cmbNombreTecla;
	}

	public void setCmbNombreTecla(JLabel cmbNombreTecla) {
		this.cmbNombreTecla = cmbNombreTecla;
	}

	public JTextField getTxtNombreTecla() {
		return txtNombreTecla;
	}

	public void setTxtNombreTecla(JTextField txtNombreTecla) {
		this.txtNombreTecla = txtNombreTecla;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JComboBox getCmbCodigo() {
		return cmbCodigo;
	}

	public void setCmbCodigo(JComboBox cmbCodigo) {
		this.cmbCodigo = cmbCodigo;
	}

	public JTextArea getTaDescripcion() {
		return taDescripcion;
	}

	public void setTaDescripcion(JTextArea taDescripcion) {
		this.taDescripcion = taDescripcion;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JSeparator getSeparator1() {
		return separator1;
	}

	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
	}

	public JTextField getTxtDatos() {
		return txtDatos;
	}

	public void setTxtDatos(JTextField txtDatos) {
		this.txtDatos = txtDatos;
	}
	
	
	
}
