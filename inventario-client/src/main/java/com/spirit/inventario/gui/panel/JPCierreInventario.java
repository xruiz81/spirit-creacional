package com.spirit.inventario.gui.panel;

import javax.swing.JButton;
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
import com.spirit.client.model.ReportModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPCierreInventario extends ReportModelImpl {
	public JPCierreInventario() {
		initComponents();
		setName("CIERRE DE INVENTARIO");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label2 = new JLabel();
		txtBodega = new JTextField();
		btnRefresh = new JButton();
		btnBuscarBodega = new JButton();
		btnCerrarInventario = new JButton();
		spTblStock = new JScrollPane();
		tblStock = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("KARDEX");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- label2 ----
		label2.setText("Bodega:");
		add(label2, cc.xy(3, 3));

		//---- txtBodega ----
		txtBodega.setEnabled(false);
		txtBodega.setEditable(false);
		add(txtBodega, cc.xywh(5, 3, 3, 1));

		//---- btnRefresh ----
		btnRefresh.setText("R");
		add(btnRefresh, cc.xy(11, 3));

		//---- btnBuscarBodega ----
		btnBuscarBodega.setText("B");
		add(btnBuscarBodega, cc.xy(9, 3));

		//---- btnCerrarInventario ----
		btnCerrarInventario.setText("Cerrar Inventario");
		add(btnCerrarInventario, cc.xy(15, 3));

		//======== spTblStock ========
		{
			
			//---- tblStock ----
			tblStock.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"Bodega", "Producto", "Lote", "A\u00f1o / Mes", "Cantidad", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, true
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblStock.setViewportView(tblStock);
		}
		add(spTblStock, cc.xywh(3, 5, 15, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label2;
	private JTextField txtBodega;
	private JButton btnRefresh;
	private JButton btnBuscarBodega;
	private JButton btnCerrarInventario;
	private JScrollPane spTblStock;
	private JTable tblStock;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JTextField getTxtBodega() {
		return txtBodega;
	}

	public void setTxtBodega(JTextField txtBodega) {
		this.txtBodega = txtBodega;
	}

	public JButton getBtnBuscarBodega() {
		return btnBuscarBodega;
	}

	public void setBtnBuscarBodega(JButton btnBuscarBodega) {
		this.btnBuscarBodega = btnBuscarBodega;
	}

	public JButton getBtnCerrarInventario() {
		return btnCerrarInventario;
	}

	public void setBtnCerrarInventario(JButton btnCerrarInventario) {
		this.btnCerrarInventario = btnCerrarInventario;
	}

	public JTable getTblStock() {
		return tblStock;
	}

	public void setTblStock(JTable tblStock) {
		this.tblStock = tblStock;
	}

	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}
}
