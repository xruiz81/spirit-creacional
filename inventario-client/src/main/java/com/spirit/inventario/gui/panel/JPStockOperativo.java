package com.spirit.inventario.gui.panel;


import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Fri Oct 02 11:44:27 COT 2009
 */
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPStockOperativo extends MantenimientoModelImpl {
	public JPStockOperativo() {
		initComponents();
		setName("Stock Operativo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label2 = new JLabel();
		cmbBodega = new JComboBox();
		label3 = new JLabel();
		cmbCopiarDe = new DateComboBox();
		btnCopiarDe = new JButton();
		label1 = new JLabel();
		btnConsultar = new JButton();
		cmbMesAnio = new DateComboBox();
		label4 = new JLabel();
		txtParametro = new JTextField();
		btnActualizarTodos = new JButton();
		cmbParametro = new JComboBox();
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
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- label2 ----
		label2.setText("Bodega:");
		add(label2, cc.xy(3, 3));
		add(cmbBodega, cc.xywh(5, 3, 5, 1));

		//---- label3 ----
		label3.setText("Copiar de:");
		add(label3, cc.xy(13, 3));
		add(cmbCopiarDe, cc.xywh(15, 3, 9, 1));

		//---- btnCopiarDe ----
		btnCopiarDe.setText("Copiar");
		add(btnCopiarDe, cc.xy(25, 3));

		//---- label1 ----
		label1.setText("Mes / Anio");
		add(label1, cc.xy(3, 5));

		//---- btnConsultar ----
		btnConsultar.setText("C");
		add(btnConsultar, cc.xy(7, 5));
		add(cmbMesAnio, cc.xy(5, 5));

		//---- label4 ----
		label4.setText("Actualizar:");
		add(label4, cc.xy(13, 5));
		add(txtParametro, cc.xy(19, 5));

		//---- btnActualizarTodos ----
		btnActualizarTodos.setText("P");
		add(btnActualizarTodos, cc.xy(21, 5));

		//---- cmbParametro ----
		cmbParametro.setModel(new DefaultComboBoxModel(new String[] {
			"Min",
			"Max",
			"T.M.R."
		}));
		add(cmbParametro, cc.xywh(15, 5, 3, 1));

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
		add(spTblStock, cc.xywh(3, 7, 27, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label2;
	private JComboBox cmbBodega;
	private JLabel label3;
	private DateComboBox cmbCopiarDe;
	private JButton btnCopiarDe;
	private JLabel label1;
	private JButton btnConsultar;
	private DateComboBox cmbMesAnio;
	private JLabel label4;
	private JTextField txtParametro;
	private JButton btnActualizarTodos;
	private JComboBox cmbParametro;
	private JScrollPane spTblStock;
	private JTable tblStockOperativo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JTextField getTxtParametro() {
		return txtParametro;
	}

	public void setTxtParametro(JTextField txtParametro) {
		this.txtParametro = txtParametro;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public DateComboBox getCmbCopiarDe() {
		return cmbCopiarDe;
	}

	public JButton getBtnCopiarDe() {
		return btnCopiarDe;
	}

	public DateComboBox getCmbMesAnio() {
		return cmbMesAnio;
	}

	public JButton getBtnActualizarTodos() {
		return btnActualizarTodos;
	}

	public JComboBox getCmbParametro() {
		return cmbParametro;
	}

	public JTable getTblStockOperativo() {
		return tblStockOperativo;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}
}
