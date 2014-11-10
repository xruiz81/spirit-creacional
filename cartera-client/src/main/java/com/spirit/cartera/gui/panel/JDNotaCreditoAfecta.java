package com.spirit.cartera.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.util.Utilitarios;

public class JDNotaCreditoAfecta extends JDialog {
	public JDNotaCreditoAfecta(Frame owner) {
		super(owner);
		initComponents();
	}
	
	public JDNotaCreditoAfecta(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		panelDesglose = new JPanel();
		goodiesFormsSeparator1 = compFactory.createSeparator("Cruce de Notas Cr\u00e9dito");
		lblDocumento = new JLabel();
		lblFechaAplicacion = new JLabel();
		cmbFechaAplicacion = new DateComboBox();
		cmbDocumento = new JComboBox();
		lblSaldoCrédito = new JLabel();
		txtSaldoCredito = new JTextField();
		lblFactura = new JLabel();
		txtFactura = new JTextField();
		btnBuscarFactura = new JButton();
		lblValorAfecta = new JLabel();
		txtValorAfecta = new JTextField();
		lblSaldoFactura = new JLabel();
		txtSaldoFactura = new JTextField();
		panel1 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		btnAplicar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Nota Cr\u00e9dito Afecta");
		setResizable(true);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default",
			"default"));

		//======== panelDesglose ========
		{
			panelDesglose.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;115dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(10)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;150dlu)"),
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
					new RowSpec(Sizes.dluY(10)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12))
				}));
			panelDesglose.add(goodiesFormsSeparator1, cc.xywh(3, 3, 11, 1));
			
			//---- lblDocumento ----
			lblDocumento.setText("Documento:");
			panelDesglose.add(lblDocumento, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- lblFechaAplicacion ----
			lblFechaAplicacion.setText("Fecha Aplicaci\u00f3n:");
			panelDesglose.add(lblFechaAplicacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			panelDesglose.add(cmbFechaAplicacion, cc.xy(5, 5));
			panelDesglose.add(cmbDocumento, cc.xy(11, 5));
			
			//---- lblSaldoCrédito ----
			lblSaldoCrédito.setText("Saldo Cr\u00e9dito:");
			panelDesglose.add(lblSaldoCrédito, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtSaldoCredito ----
			txtSaldoCredito.setHorizontalAlignment(JTextField.RIGHT);
			txtSaldoCredito.setEditable(false);
			panelDesglose.add(txtSaldoCredito, cc.xy(5, 7));
			
			//---- lblFactura ----
			lblFactura.setText("Factura:");
			panelDesglose.add(lblFactura, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtFactura ----
			txtFactura.setEditable(false);
			panelDesglose.add(txtFactura, cc.xy(11, 7));
			
			//---- btnBuscarFactura ----
			btnBuscarFactura.setText("B");
			panelDesglose.add(btnBuscarFactura, cc.xywh(13, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
			
			//---- lblValorAfecta ----
			lblValorAfecta.setText("Valor Afecta:");
			panelDesglose.add(lblValorAfecta, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtValorAfecta ----
			txtValorAfecta.setHorizontalAlignment(JTextField.RIGHT);
			panelDesglose.add(txtValorAfecta, cc.xy(5, 9));
			
			//---- lblSaldoFactura ----
			lblSaldoFactura.setText("Saldo Factura:");
			panelDesglose.add(lblSaldoFactura, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- txtSaldoFactura ----
			txtSaldoFactura.setEditable(false);
			txtSaldoFactura.setHorizontalAlignment(JTextField.RIGHT);
			panelDesglose.add(txtSaldoFactura, cc.xy(11, 9));
			
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
				
				//---- btnAceptar ----
				btnAceptar.setText("Aceptar");
				panel1.add(btnAceptar, cc.xy(1, 1));
				
				//---- btnCancelar ----
				btnCancelar.setText("Cancelar");
				panel1.add(btnCancelar, cc.xy(3, 1));
				
				//---- btnAplicar ----
				btnAplicar.setText("Aplicar");
				panel1.add(btnAplicar, cc.xy(5, 1));
			}
			panelDesglose.add(panel1, cc.xywh(11, 13, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		}
		contentPane.add(panelDesglose, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panelDesglose;
	private JComponent goodiesFormsSeparator1;
	private JLabel lblDocumento;
	private JLabel lblFechaAplicacion;
	private DateComboBox cmbFechaAplicacion;
	private JComboBox cmbDocumento;
	private JLabel lblSaldoCrédito;
	private JTextField txtSaldoCredito;
	private JLabel lblFactura;
	private JTextField txtFactura;
	private JButton btnBuscarFactura;
	private JLabel lblValorAfecta;
	private JTextField txtValorAfecta;
	private JLabel lblSaldoFactura;
	private JTextField txtSaldoFactura;
	private JPanel panel1;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAplicar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnBuscarFactura() {
		return btnBuscarFactura;
	}

	public void setBtnBuscarFactura(JButton btnBuscarFactura) {
		this.btnBuscarFactura = btnBuscarFactura;
	}

	public DateComboBox getCmbFechaAplicacion() {
		return cmbFechaAplicacion;
	}

	public void setCmbFechaAplicacion(DateComboBox cmbFechaAplicacion) {
		this.cmbFechaAplicacion = cmbFechaAplicacion;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JTextField getTxtFactura() {
		return txtFactura;
	}

	public void setTxtFactura(JTextField txtFactura) {
		this.txtFactura = txtFactura;
	}

	public JTextField getTxtSaldoCredito() {
		return txtSaldoCredito;
	}

	public void setTxtSaldoCredito(JTextField txtSaldoCredito) {
		this.txtSaldoCredito = txtSaldoCredito;
	}

	public JTextField getTxtSaldoFactura() {
		return txtSaldoFactura;
	}

	public void setTxtSaldoFactura(JTextField txtSaldoFactura) {
		this.txtSaldoFactura = txtSaldoFactura;
	}

	public JTextField getTxtValorAfecta() {
		return txtValorAfecta;
	}

	public void setTxtValorAfecta(JTextField txtValorAfecta) {
		this.txtValorAfecta = txtValorAfecta;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnAplicar() {
		return btnAplicar;
	}

	public void setBtnAplicar(JButton btnAplicar) {
		this.btnAplicar = btnAplicar;
	}
}
