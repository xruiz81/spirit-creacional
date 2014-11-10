package com.spirit.inventario.gui.panel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.ReportModelImpl;

public abstract class JPDemoServicioWebConsultaStock extends ReportModelImpl {
	private static final long serialVersionUID = 5764239149021257988L;

	public JPDemoServicioWebConsultaStock() {
		setName("Demo Servicio Web Consulta Stock");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		sDemoServicioWebConsultaStock = compFactory.createSeparator("Demo Servicio Web Consulta Stock");
		lblIntervaloMinutos = new JLabel();
		txtIntervaloMinutos = new JTextField();
		lblRecuperarTodos = new JLabel();
		rbSiRecuperarTodos = new JRadioButton();
		rbNoRecuperarTodos = new JRadioButton();
		btnEjecutar = new JButton();
		lblSku = new JLabel();
		lblStock = new JLabel();
		spResultados = new JScrollPane();
		txtResultados = new JTextArea();
		lblTotalItemsDevueltos = new JLabel();
		lblNumeroItems = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
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
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));
		add(sDemoServicioWebConsultaStock, cc.xywh(3, 3, 9, 1));

		//---- lblIntervaloMinutos ----
		lblIntervaloMinutos.setText("Intervalo [min]:");
		add(lblIntervaloMinutos, cc.xy(3, 5));

		//---- txtIntervaloMinutos ----
		txtIntervaloMinutos.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtIntervaloMinutos, cc.xywh(5, 5, 3, 1));

		//---- lblRecuperarTodos ----
		lblRecuperarTodos.setText("Recuperar todos:");
		add(lblRecuperarTodos, cc.xy(3, 7));

		//---- rbSiRecuperarTodos ----
		rbSiRecuperarTodos.setText("S\u00ed");
		rbSiRecuperarTodos.setSelected(true);
		add(rbSiRecuperarTodos, cc.xy(5, 7));

		//---- rbNoRecuperarTodos ----
		rbNoRecuperarTodos.setText("No");
		add(rbNoRecuperarTodos, cc.xy(7, 7));

		//---- btnEjecutar ----
		btnEjecutar.setText("Ejecutar");
		add(btnEjecutar, cc.xy(9, 7));

		//---- lblSku ----
		lblSku.setText("SKU");
		lblSku.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblSku, cc.xy(3, 11));

		//---- lblStock ----
		lblStock.setText("| STOCK");
		lblStock.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblStock, cc.xywh(5, 11, 3, 1));

		//======== spResultados ========
		{

			//---- txtResultados ----
			txtResultados.setRows(10);
			spResultados.setViewportView(txtResultados);
		}
		add(spResultados, cc.xywh(3, 12, 9, 4));

		//---- lblTotalItemsDevueltos ----
		lblTotalItemsDevueltos.setText("Total items devueltos:");
		lblTotalItemsDevueltos.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblTotalItemsDevueltos, cc.xywh(3, 19, 3, 1));

		//---- lblNumeroItems ----
		lblNumeroItems.setText("#items");
		lblNumeroItems.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblNumeroItems, cc.xywh(7, 19, 3, 1));

		//---- bgRecuperarTodos ----
		ButtonGroup bgRecuperarTodos = new ButtonGroup();
		bgRecuperarTodos.add(rbSiRecuperarTodos);
		bgRecuperarTodos.add(rbNoRecuperarTodos);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JComponent sDemoServicioWebConsultaStock;
	private JLabel lblIntervaloMinutos;
	private JTextField txtIntervaloMinutos;
	private JLabel lblRecuperarTodos;
	private JRadioButton rbSiRecuperarTodos;
	private JRadioButton rbNoRecuperarTodos;
	private JButton btnEjecutar;
	private JLabel lblSku;
	private JLabel lblStock;
	private JScrollPane spResultados;
	private JTextArea txtResultados;
	private JLabel lblTotalItemsDevueltos;
	private JLabel lblNumeroItems;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComponent getsDemoServicioWebConsultaStock() {
		return sDemoServicioWebConsultaStock;
	}

	public void setsDemoServicioWebConsultaStock(
			JComponent sDemoServicioWebConsultaStock) {
		this.sDemoServicioWebConsultaStock = sDemoServicioWebConsultaStock;
	}

	public JLabel getLblIntervaloMinutos() {
		return lblIntervaloMinutos;
	}

	public void setLblIntervaloMinutos(JLabel lblIntervaloMinutos) {
		this.lblIntervaloMinutos = lblIntervaloMinutos;
	}

	public JTextField getTxtIntervaloMinutos() {
		return txtIntervaloMinutos;
	}

	public void setTxtIntervaloMinutos(JTextField txtIntervaloMinutos) {
		this.txtIntervaloMinutos = txtIntervaloMinutos;
	}

	public JLabel getLblRecuperarTodos() {
		return lblRecuperarTodos;
	}

	public void setLblRecuperarTodos(JLabel lblRecuperarTodos) {
		this.lblRecuperarTodos = lblRecuperarTodos;
	}

	public JRadioButton getRbSiRecuperarTodos() {
		return rbSiRecuperarTodos;
	}

	public void setRbSiRecuperarTodos(JRadioButton rbSiRecuperarTodos) {
		this.rbSiRecuperarTodos = rbSiRecuperarTodos;
	}

	public JRadioButton getRbNoRecuperarTodos() {
		return rbNoRecuperarTodos;
	}

	public void setRbNoRecuperarTodos(JRadioButton rbNoRecuperarTodos) {
		this.rbNoRecuperarTodos = rbNoRecuperarTodos;
	}

	public JButton getBtnEjecutar() {
		return btnEjecutar;
	}

	public void setBtnEjecutar(JButton btnEjecutar) {
		this.btnEjecutar = btnEjecutar;
	}

	public JLabel getLblSku() {
		return lblSku;
	}

	public void setLblSku(JLabel lblSku) {
		this.lblSku = lblSku;
	}

	public JLabel getLblStock() {
		return lblStock;
	}

	public void setLblStock(JLabel lblStock) {
		this.lblStock = lblStock;
	}

	public JScrollPane getSpResultados() {
		return spResultados;
	}

	public void setSpResultados(JScrollPane spResultados) {
		this.spResultados = spResultados;
	}

	public JTextArea getTxtResultados() {
		return txtResultados;
	}

	public void setTxtResultados(JTextArea txtResultados) {
		this.txtResultados = txtResultados;
	}

	public JLabel getLblTotalItemsDevueltos() {
		return lblTotalItemsDevueltos;
	}

	public void setLblTotalItemsDevueltos(JLabel lblTotalItemsDevueltos) {
		this.lblTotalItemsDevueltos = lblTotalItemsDevueltos;
	}

	public JLabel getLblNumeroItems() {
		return lblNumeroItems;
	}

	public void setLblNumeroItems(JLabel lblNumeroItems) {
		this.lblNumeroItems = lblNumeroItems;
	}
}
