package com.spirit.pos.gui.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;

/**
 * @author Antonio Seiler
 */
public abstract class JDResumenVentas extends JDialogModelImpl  {


	public JDResumenVentas(Frame owner) {
		super(owner);
		initComponents();
	}
	
	public JDResumenVentas(Dialog owner) {
		super(owner);
		initComponents();
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel5 = new JPanel();
		txtTitulo = new JTextField();
		panel4 = new JPanel();
		label8 = new JLabel();
		scrollPane1 = new JScrollPane();
		tblDetalles = new JTable();
		label2 = new JLabel();
		panel42 = new JPanel();
		label3 = new JLabel();
		panel422 = new JPanel();
		panel3 = new JPanel();
		label4 = new JLabel();
		lblsubtotal = new JLabel();
		lbldescuento = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		lblimpuestos = new JLabel();
		panel7 = new JPanel();
		label7 = new JLabel();
		lbltotal = new JLabel();
		scrollPane2 = new JScrollPane();
		tblFormaPago = new JTable();
		panel2 = new JPanel();
		lblPuntos = new JLabel();
		lblPuntosSumados = new JLabel();
		panel6 = new JPanel();
		btnRegresar = new JButton();
		btnProcesarCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("RESUMEN VENTA");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(300)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(88)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
					new RowSpec(Sizes.dluY(50)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//======== panel5 ========
			{
				panel5.setBackground(new Color(51, 51, 51));
				panel5.setLayout(new FormLayout(
					"130dlu, center:default",
					"default"));
				
				//---- txtTitulo ----
				txtTitulo.setBackground(new Color(51, 51, 51));
				txtTitulo.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
				txtTitulo.setForeground(Color.orange);
				txtTitulo.setText("RESUMEN DE VENTAS");
				panel5.add(txtTitulo, cc.xywh(2, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
			}
			panel1.add(panel5, cc.xywh(1, 2, 5, 1));
			
			//======== panel4 ========
			{
				panel4.setBorder(new LineBorder(new Color(0, 0, 102), 3));
				panel4.setLayout(new BorderLayout());
			}
			panel1.add(panel4, cc.xywh(1, 5, 5, 1));
			
			//---- label8 ----
			label8.setText("Detalles:");
			label8.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
			panel1.add(label8, cc.xy(1, 4));
			
			//======== scrollPane1 ========
			{
				scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				//---- tblDetalles ----
				tblDetalles.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null},
					},
					new String[] {
						"TIPO", "C\u00f3digo", "Descripci\u00f3n", "Cantidad", "Valor", "Desc.", "Imp."
					}
				));
				tblDetalles.setFont(new Font("MS Sans Serif", Font.BOLD, 12));
				scrollPane1.setViewportView(tblDetalles);
			}
			panel1.add(scrollPane1, cc.xywh(1, 10, 5, 3));
			
			//---- label2 ----
			label2.setText("Formas de pago:");
			label2.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
			panel1.add(label2, cc.xy(1, 20));
			
			//======== panel42 ========
			{
				panel42.setBorder(new LineBorder(new Color(0, 0, 102), 3));
				panel42.setLayout(new BorderLayout());
			}
			panel1.add(panel42, cc.xywh(1, 17, 5, 1));
			
			//---- label3 ----
			label3.setText("Totales:");
			label3.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
			panel1.add(label3, cc.xy(1, 16));
			
			//======== panel422 ========
			{
				panel422.setBorder(new LineBorder(new Color(0, 0, 102), 3));
				panel422.setLayout(new BorderLayout());
			}
			panel1.add(panel422, cc.xywh(1, 21, 5, 1));
			
			//======== panel3 ========
			{
				panel3.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(73)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(80)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- label4 ----
				label4.setText("Subtotal:");
				label4.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel3.add(label4, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblsubtotal ----
				lblsubtotal.setText("0.00");
				lblsubtotal.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
				lblsubtotal.setForeground(new Color(0, 0, 102));
				panel3.add(lblsubtotal, cc.xywh(5, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lbldescuento ----
				lbldescuento.setText("0.00");
				lbldescuento.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
				lbldescuento.setForeground(new Color(0, 0, 102));
				panel3.add(lbldescuento, cc.xywh(5, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- label5 ----
				label5.setText("Descuento:");
				label5.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel3.add(label5, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- label6 ----
				label6.setText("Impuestos:");
				label6.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel3.add(label6, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblimpuestos ----
				lblimpuestos.setText("0.00");
				lblimpuestos.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
				lblimpuestos.setForeground(new Color(0, 0, 102));
				panel3.add(lblimpuestos, cc.xywh(5, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//======== panel7 ========
				{
					panel7.setBorder(new LineBorder(new Color(0, 0, 102)));
					panel7.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(73)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(80)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						RowSpec.decodeSpecs("default")));
					
					//---- label7 ----
					label7.setText("TOTAL:");
					label7.setFont(new Font("Tahoma", Font.BOLD, 24));
					panel7.add(label7, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lbltotal ----
					lbltotal.setText("0.00");
					lbltotal.setFont(new Font("MS Sans Serif", Font.BOLD, 24));
					lbltotal.setForeground(new Color(0, 0, 102));
					panel7.add(lbltotal, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				}
				panel3.add(panel7, cc.xywh(3, 7, 7, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
			}
			panel1.add(panel3, cc.xy(5, 18));
			
			//======== scrollPane2 ========
			{
				
				//---- tblFormaPago ----
				tblFormaPago.setFont(new Font("MS Sans Serif", Font.BOLD, 12));
				tblFormaPago.setModel(new DefaultTableModel(
					new Object[][] {
						{"", null, null, null},
						{null, null, null, null},
					},
					new String[] {
						"Forma pago", "Valor", "Nombre", "Tipo"
					}
				));
				scrollPane2.setViewportView(tblFormaPago);
			}
			panel1.add(scrollPane2, cc.xywh(1, 22, 5, 3));
			
			//======== panel2 ========
			{
				panel2.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(50), FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("default")));
				
				//---- lblPuntos ----
				lblPuntos.setText("Puntos dados al referido:");
				lblPuntos.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
				panel2.add(lblPuntos, cc.xy(1, 1));
				
				//---- lblPuntosSumados ----
				lblPuntosSumados.setText("0.00");
				lblPuntosSumados.setFont(new Font("MS Sans Serif", Font.BOLD, 18));
				lblPuntosSumados.setForeground(new Color(0, 0, 102));
				panel2.add(lblPuntosSumados, cc.xy(3, 1));
			}
			panel1.add(panel2, cc.xywh(1, 26, 5, 1));
			
			//======== panel6 ========
			{
				panel6.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- btnRegresar ----
				btnRegresar.setText("<< Regresar pantalla anterior");
				btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnRegresar.setForeground(new Color(0, 0, 102));
				panel6.add(btnRegresar, cc.xy(1, 1));
				
				//---- btnProcesarCancelar ----
				btnProcesarCancelar.setText("Procesar >>");
				btnProcesarCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnProcesarCancelar.setForeground(new Color(0, 0, 102));
				panel6.add(btnProcesarCancelar, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			panel1.add(panel6, cc.xywh(1, 28, 5, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
		}
		contentPane.add(panel1);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JPanel panel5;
	private JTextField txtTitulo;
	private JPanel panel4;
	private JLabel label8;
	private JScrollPane scrollPane1;
	private JTable tblDetalles;
	private JLabel label2;
	private JPanel panel42;
	private JLabel label3;
	private JPanel panel422;
	private JPanel panel3;
	private JLabel label4;
	private JLabel lblsubtotal;
	private JLabel lbldescuento;
	private JLabel label5;
	private JLabel label6;
	private JLabel lblimpuestos;
	private JPanel panel7;
	private JLabel label7;
	private JLabel lbltotal;
	private JScrollPane scrollPane2;
	private JTable tblFormaPago;
	private JPanel panel2;
	private JLabel lblPuntos;
	private JLabel lblPuntosSumados;
	private JPanel panel6;
	private JButton btnRegresar;
	private JButton btnProcesarCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public void setPanel5(JPanel panel5) {
		this.panel5 = panel5;
	}

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	public void setTxtTitulo(JTextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JLabel getLabel8() {
		return label8;
	}

	public void setLabel8(JLabel label8) {
		this.label8 = label8;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public void setScrollPane1(JScrollPane scrollPane1) {
		this.scrollPane1 = scrollPane1;
	}

	public JTable getTblDetalles() {
		return tblDetalles;
	}

	public void setTblDetalles(JTable tblDetalles) {
		this.tblDetalles = tblDetalles;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JPanel getPanel42() {
		return panel42;
	}

	public void setPanel42(JPanel panel42) {
		this.panel42 = panel42;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	public JPanel getPanel422() {
		return panel422;
	}

	public void setPanel422(JPanel panel422) {
		this.panel422 = panel422;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JLabel getLabel4() {
		return label4;
	}

	public void setLabel4(JLabel label4) {
		this.label4 = label4;
	}

	public JLabel getLblsubtotal() {
		return lblsubtotal;
	}

	public void setLblsubtotal(JLabel lblsubtotal) {
		this.lblsubtotal = lblsubtotal;
	}

	public JLabel getLbldescuento() {
		return lbldescuento;
	}

	public void setLbldescuento(JLabel lbldescuento) {
		this.lbldescuento = lbldescuento;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public void setLabel5(JLabel label5) {
		this.label5 = label5;
	}

	public JLabel getLabel6() {
		return label6;
	}

	public void setLabel6(JLabel label6) {
		this.label6 = label6;
	}

	public JLabel getLblimpuestos() {
		return lblimpuestos;
	}

	public void setLblimpuestos(JLabel lblimpuestos) {
		this.lblimpuestos = lblimpuestos;
	}

	public JPanel getPanel7() {
		return panel7;
	}

	public void setPanel7(JPanel panel7) {
		this.panel7 = panel7;
	}

	public JLabel getLabel7() {
		return label7;
	}

	public void setLabel7(JLabel label7) {
		this.label7 = label7;
	}

	public JLabel getLbltotal() {
		return lbltotal;
	}

	public void setLbltotal(JLabel lbltotal) {
		this.lbltotal = lbltotal;
	}

	public JScrollPane getScrollPane2() {
		return scrollPane2;
	}

	public void setScrollPane2(JScrollPane scrollPane2) {
		this.scrollPane2 = scrollPane2;
	}

	public JTable getTblFormaPago() {
		return tblFormaPago;
	}

	public void setTblFormaPago(JTable tblFormaPago) {
		this.tblFormaPago = tblFormaPago;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JLabel getLblPuntos() {
		return lblPuntos;
	}

	public void setLblPuntos(JLabel lblPuntos) {
		this.lblPuntos = lblPuntos;
	}

	public JLabel getLblPuntosDineroSumados() {
		return lblPuntosSumados;
	}

	public void setLblPuntosDineroSumados(JLabel lblPuntosSumados) {
		this.lblPuntosSumados = lblPuntosSumados;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public void setPanel6(JPanel panel6) {
		this.panel6 = panel6;
	}

	public JButton getBtnRegresar() {
		return btnRegresar;
	}

	public void setBtnRegresar(JButton btnRegresar) {
		this.btnRegresar = btnRegresar;
	}

	public JButton getBtnProcesarCancelar() {
		return btnProcesarCancelar;
	}

	public void setBtnProcesarCancelar(JButton btnProcesarCancelar) {
		this.btnProcesarCancelar = btnProcesarCancelar;
	}
	
	
}
