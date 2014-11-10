package com.spirit.compras.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import com.spirit.client.model.JDialogModelImpl;

public abstract class JDCompraDetalleGasto extends JDialogModelImpl {
	public JDCompraDetalleGasto(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDCompraDetalleGasto(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblEtiquetaGasto = new JLabel();
		lblGasto = new JLabel();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblEtiquetaTotal = new JLabel();
		lblTotal = new JLabel();
		panel1 = new JPanel();
		btnAplicarTodos = new JButton();
		btnProrratear = new JButton();
		spTblDetalleGasto = new JScrollPane();
		tblDetalleGasto = new JTable();
		panel2 = new JPanel();
		btnGuardar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setName("Detalle de Gastos");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.dluX(100), FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
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
				new RowSpec(RowSpec.CENTER, Sizes.dluY(120), FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblEtiquetaGasto ----
		lblEtiquetaGasto.setText("Nombre Gasto: ");
		contentPane.add(lblEtiquetaGasto, cc.xy(3, 3));

		//---- lblGasto ----
		lblGasto.setText(" ");
		lblGasto.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblGasto, cc.xywh(5, 3, 3, 1));

		//---- lblValor ----
		lblValor.setText("Valor: ");
		contentPane.add(lblValor, cc.xy(3, 5));
		contentPane.add(txtValor, cc.xy(5, 5));

		//---- lblEtiquetaTotal ----
		lblEtiquetaTotal.setText("Total: ");
		contentPane.add(lblEtiquetaTotal, cc.xy(7, 5));

		//---- lblTotal ----
		lblTotal.setText(" ");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblTotal, cc.xy(9, 5));

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
			
			//---- btnAplicarTodos ----
			btnAplicarTodos.setText("Aplicar a todos");
			panel1.add(btnAplicarTodos, cc.xy(1, 1));
			
			//---- btnProrratear ----
			btnProrratear.setText("Prorratear");
			panel1.add(btnProrratear, cc.xy(3, 1));
		}
		contentPane.add(panel1, cc.xywh(3, 7, 7, 1));

		//======== spTblDetalleGasto ========
		{
			
			//---- tblDetalleGasto ----
			tblDetalleGasto.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"Producto", "Valor"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Double.class
				};
				boolean[] columnEditable = new boolean[] {
					false, true
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblDetalleGasto.setViewportView(tblDetalleGasto);
		}
		contentPane.add(spTblDetalleGasto, cc.xywh(3, 9, 9, 3));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- btnGuardar ----
			btnGuardar.setText("Guardar");
			panel2.add(btnGuardar, cc.xy(3, 1));
			
			//---- btnCancelar ----
			btnCancelar.setText("Cancelar");
			panel2.add(btnCancelar, cc.xy(5, 1));
		}
		contentPane.add(panel2, cc.xywh(7, 13, 5, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEtiquetaGasto;
	private JLabel lblGasto;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblEtiquetaTotal;
	private JLabel lblTotal;
	private JPanel panel1;
	private JButton btnAplicarTodos;
	private JButton btnProrratear;
	private JScrollPane spTblDetalleGasto;
	private JTable tblDetalleGasto;
	private JPanel panel2;
	private JButton btnGuardar;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblEtiquetaGasto() {
		return lblEtiquetaGasto;
	}

	public JLabel getLblGasto() {
		return lblGasto;
	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JLabel getLblEtiquetaTotal() {
		return lblEtiquetaTotal;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JButton getBtnAplicarTodos() {
		return btnAplicarTodos;
	}

	public JButton getBtnProrratear() {
		return btnProrratear;
	}

	public JScrollPane getSpTblDetalleGasto() {
		return spTblDetalleGasto;
	}

	public JTable getTblDetalleGasto() {
		return tblDetalleGasto;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
}
