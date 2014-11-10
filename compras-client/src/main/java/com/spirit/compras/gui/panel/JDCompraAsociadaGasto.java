package com.spirit.compras.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
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

public abstract class JDCompraAsociadaGasto extends JDialogModelImpl {
	public JDCompraAsociadaGasto(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDCompraAsociadaGasto(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCompra = new JLabel();
		txtCompra = new JTextField();
		btnBuscarCompra = new JButton();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spTblDetalleGasto = new JScrollPane();
		tblDetalleGasto = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setName("Compras Asociadas");
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCompra ----
		lblCompra.setText("Compra: ");
		contentPane.add(lblCompra, cc.xy(3, 3));

		//---- txtCompra ----
		txtCompra.setEditable(false);
		contentPane.add(txtCompra, cc.xywh(5, 3, 3, 1));

		//---- btnBuscarCompra ----
		btnBuscarCompra.setText("B");
		contentPane.add(btnBuscarCompra, cc.xy(9, 3));

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
			
			//---- btnAgregarDetalle ----
			btnAgregarDetalle.setText("A");
			panel1.add(btnAgregarDetalle, cc.xy(1, 1));
			
			//---- btnActualizarDetalle ----
			btnActualizarDetalle.setText("U");
			panel1.add(btnActualizarDetalle, cc.xy(3, 1));
			
			//---- btnEliminarDetalle ----
			btnEliminarDetalle.setText("D");
			panel1.add(btnEliminarDetalle, cc.xy(5, 1));
		}
		contentPane.add(panel1, cc.xywh(3, 7, 5, 1));

		//======== spTblDetalleGasto ========
		{
			
			//---- tblDetalleGasto ----
			tblDetalleGasto.setModel(new DefaultTableModel(
				new Object[][] {
					{"", null, null, null},
				},
				new String[] {
					"Codigo", "Preimpreso", "Valor", "Descripci\u00f3n"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
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
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCompra;
	private JTextField txtCompra;
	private JButton btnBuscarCompra;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spTblDetalleGasto;
	private JTable tblDetalleGasto;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblCompra() {
		return lblCompra;
	}

	public JTextField getTxtCompra() {
		return txtCompra;
	}

	public JButton getBtnBuscarCompra() {
		return btnBuscarCompra;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public JScrollPane getSpTblDetalleGasto() {
		return spTblDetalleGasto;
	}

	public JTable getTblDetalleGasto() {
		return tblDetalleGasto;
	}
}
