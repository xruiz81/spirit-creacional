package com.spirit.inventario.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
import com.spirit.client.model.ReportModelImpl;
/*
 * Created by JFormDesigner on Tue Oct 06 12:30:49 COT 2009
 */



/**
 * @author Antonio Seiler
 */
public abstract class JPConsultaStockOperativo extends ReportModelImpl {
	public JPConsultaStockOperativo() {
		initComponents();
		setName("Consulta Stock Operativo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label2 = new JLabel();
		txtBodega = new JTextField();
		btnBuscarBodega = new JButton();
		label5 = new JLabel();
		txtGenerico = new JTextField();
		btnBuscarGenerico = new JButton();
		btnEliminarGenerico = new JButton();
		label3 = new JLabel();
		txtModelo = new JTextField();
		btnBuscarModelo = new JButton();
		btnEliminarModelo = new JButton();
		label4 = new JLabel();
		txtTalla = new JTextField();
		btnBuscarTalla = new JButton();
		btnEliminarTalla = new JButton();
		label6 = new JLabel();
		txtColor = new JTextField();
		btnBuscarColor = new JButton();
		btnEliminarColor = new JButton();
		label1 = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		btnEliminarProducto = new JButton();
		label8 = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnRefresh = new JButton();
		btnGenSolicitudTransferencia = new JButton();
		spTblStock = new JScrollPane();
		tblStockOperativo = new JTable();
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
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
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

		//---- label5 ----
		label5.setText("Generico:");
		add(label5, cc.xy(3, 5));

		//---- txtGenerico ----
		txtGenerico.setEditable(false);
		add(txtGenerico, cc.xywh(5, 5, 3, 1));

		//---- btnBuscarGenerico ----
		btnBuscarGenerico.setText("B");
		add(btnBuscarGenerico, cc.xy(11, 5));

		//---- btnEliminarGenerico ----
		btnEliminarGenerico.setText("E");
		add(btnEliminarGenerico, cc.xy(13, 5));

		//---- label3 ----
		label3.setText("Modelo:");
		add(label3, cc.xy(3, 7));

		//---- txtModelo ----
		txtModelo.setEditable(false);
		add(txtModelo, cc.xywh(5, 7, 3, 1));

		//---- btnBuscarModelo ----
		btnBuscarModelo.setText("B");
		add(btnBuscarModelo, cc.xy(11, 7));

		//---- btnEliminarModelo ----
		btnEliminarModelo.setText("E");
		add(btnEliminarModelo, cc.xy(13, 7));

		//---- label4 ----
		label4.setText("Talla:");
		add(label4, cc.xy(3, 9));

		//---- txtTalla ----
		txtTalla.setEditable(false);
		add(txtTalla, cc.xywh(5, 9, 3, 1));

		//---- btnBuscarTalla ----
		btnBuscarTalla.setText("B");
		add(btnBuscarTalla, cc.xy(11, 9));

		//---- btnEliminarTalla ----
		btnEliminarTalla.setText("E");
		add(btnEliminarTalla, cc.xy(13, 9));

		//---- label6 ----
		label6.setText("Color:");
		add(label6, cc.xy(3, 11));

		//---- txtColor ----
		txtColor.setEditable(false);
		add(txtColor, cc.xywh(5, 11, 3, 1));

		//---- btnBuscarColor ----
		btnBuscarColor.setText("B");
		add(btnBuscarColor, cc.xy(11, 11));

		//---- btnEliminarColor ----
		btnEliminarColor.setText("E");
		add(btnEliminarColor, cc.xy(13, 11));

		//---- label1 ----
		label1.setText("Producto:");
		add(label1, cc.xy(3, 13));

		//---- txtProducto ----
		txtProducto.setEditable(false);
		add(txtProducto, cc.xywh(5, 13, 5, 1));

		//---- btnProducto ----
		btnProducto.setText("B");
		add(btnProducto, cc.xy(11, 13));

		//---- btnEliminarProducto ----
		btnEliminarProducto.setText("E");
		add(btnEliminarProducto, cc.xy(13, 13));

		//---- label8 ----
		label8.setText("Fecha Inicio:");
		add(label8, cc.xy(17, 13));
		add(cmbFechaInicial, cc.xy(19, 13));

		//---- btnRefresh ----
		btnRefresh.setText("R");
		add(btnRefresh, cc.xy(21, 13));

		//---- btnGenSolicitudTransferencia ----
		btnGenSolicitudTransferencia.setText("Gen. Sol. Transf.");
		add(btnGenSolicitudTransferencia, cc.xy(23, 13));

		//======== spTblStock ========
		{
			
			//---- tblStockOperativo ----
			tblStockOperativo.setModel(new DefaultTableModel(
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
			spTblStock.setViewportView(tblStockOperativo);
		}
		add(spTblStock, cc.xywh(3, 15, 23, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label2;
	private JTextField txtBodega;
	private JButton btnBuscarBodega;
	private JLabel label5;
	private JTextField txtGenerico;
	private JButton btnBuscarGenerico;
	private JButton btnEliminarGenerico;
	private JLabel label3;
	private JTextField txtModelo;
	private JButton btnBuscarModelo;
	private JButton btnEliminarModelo;
	private JLabel label4;
	private JTextField txtTalla;
	private JButton btnBuscarTalla;
	private JButton btnEliminarTalla;
	private JLabel label6;
	private JTextField txtColor;
	private JButton btnBuscarColor;
	private JButton btnEliminarColor;
	private JLabel label1;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JButton btnEliminarProducto;
	private JLabel label8;
	private DateComboBox cmbFechaInicial;
	private JButton btnRefresh;
	private JButton btnGenSolicitudTransferencia;
	private JScrollPane spTblStock;
	private JTable tblStockOperativo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JTextField getTxtBodega() {
		return txtBodega;
	}

	public JButton getBtnBuscarBodega() {
		return btnBuscarBodega;
	}

	public JTextField getTxtGenerico() {
		return txtGenerico;
	}

	public JButton getBtnBuscarGenerico() {
		return btnBuscarGenerico;
	}

	public JButton getBtnEliminarGenerico() {
		return btnEliminarGenerico;
	}

	public JTextField getTxtModelo() {
		return txtModelo;
	}

	public JButton getBtnBuscarModelo() {
		return btnBuscarModelo;
	}

	public JButton getBtnEliminarModelo() {
		return btnEliminarModelo;
	}

	public JTextField getTxtTalla() {
		return txtTalla;
	}

	public JButton getBtnBuscarTalla() {
		return btnBuscarTalla;
	}

	public JButton getBtnEliminarTalla() {
		return btnEliminarTalla;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public JButton getBtnBuscarColor() {
		return btnBuscarColor;
	}

	public JButton getBtnEliminarColor() {
		return btnEliminarColor;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
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

	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public JTable getTblStockOperativo() {
		return tblStockOperativo;
	}

	public JButton getBtnGenSolicitudTransferencia() {
		return btnGenSolicitudTransferencia;
	}
}
