package com.spirit.inventario.gui.panel;

import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Mon Sep 07 16:03:03 COT 2009
 */
import com.spirit.client.model.ReportModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPConsultaTomaInventario extends ReportModelImpl {
	public JPConsultaTomaInventario() {
		initComponents();
		setName("Consulta Tomas de Inventario");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label2 = new JLabel();
		txtBodega = new JTextField();
		btnBuscarBodega = new JButton();
		btnEliminarBodega = new JButton();
		label1 = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		btnEliminarProducto = new JButton();
		label3 = new JLabel();
		cmbFechaInicial = new DateComboBox();
		label4 = new JLabel();
		cmbFechaFinal = new DateComboBox();
		btnRefresh = new JButton();
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
				new ColumnSpec("max(default;70dlu)"),
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

		//---- btnBuscarBodega ----
		btnBuscarBodega.setText("B");
		add(btnBuscarBodega, cc.xy(11, 3));

		//---- btnEliminarBodega ----
		btnEliminarBodega.setText("E");
		add(btnEliminarBodega, cc.xy(13, 3));

		//---- label1 ----
		label1.setText("Producto:");
		add(label1, cc.xy(3, 5));

		//---- txtProducto ----
		txtProducto.setEditable(false);
		add(txtProducto, cc.xywh(5, 5, 5, 1));

		//---- btnProducto ----
		btnProducto.setText("B");
		add(btnProducto, cc.xy(11, 5));

		//---- btnEliminarProducto ----
		btnEliminarProducto.setText("E");
		add(btnEliminarProducto, cc.xy(13, 5));

		//---- label3 ----
		label3.setText("Fecha Inicial:");
		add(label3, cc.xy(3, 7));
		add(cmbFechaInicial, cc.xy(5, 7));

		//---- label4 ----
		label4.setText("Fecha Final:");
		add(label4, cc.xy(3, 9));
		add(cmbFechaFinal, cc.xy(5, 9));

		//---- btnRefresh ----
		btnRefresh.setText("R");
		add(btnRefresh, cc.xy(11, 9));

		//======== spTblStock ========
		{
			
			//---- tblStock ----
			tblStock.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, "", ""},
				},
				new String[] {
					"Bodega", "Producto", "Lote", "A\u00f1o / Mes", "Cantidad", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, true, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblStock.setViewportView(tblStock);
		}
		add(spTblStock, cc.xywh(3, 11, 15, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label2;
	private JTextField txtBodega;
	private JButton btnBuscarBodega;
	private JButton btnEliminarBodega;
	private JLabel label1;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JButton btnEliminarProducto;
	private JLabel label3;
	private DateComboBox cmbFechaInicial;
	private JLabel label4;
	private DateComboBox cmbFechaFinal;
	private JButton btnRefresh;
	private JScrollPane spTblStock;
	private JTable tblStock;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JTextField getTxtBodega() {
		return txtBodega;
	}

	public JButton getBtnBuscarBodega() {
		return btnBuscarBodega;
	}

	public JButton getBtnEliminarBodega() {
		return btnEliminarBodega;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public JTable getTblStock() {
		return tblStock;
	}
}
