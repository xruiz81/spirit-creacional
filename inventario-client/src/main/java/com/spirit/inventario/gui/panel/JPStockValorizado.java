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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.ReportModelImpl;



/**
 * @author SHOCKIE
 */
public abstract class JPStockValorizado extends ReportModelImpl{
	public JPStockValorizado() {
		initComponents();
		setName("Stock Valorizado");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblBodega = new JLabel();
		txtBodega = new JTextField();
		btnBuscarBodega = new JButton();
		lblFechaCorte = new JLabel();
		cmbFechaCorte = new DateComboBox();
		btnConsultar = new JButton();
		spStockValorizado = new JScrollPane();
		tblStockValorizado = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("KARDEX");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblBodega ----
		lblBodega.setText("Bodega:");
		add(lblBodega, cc.xy(3, 3));

		//---- txtBodega ----
		txtBodega.setEnabled(false);
		txtBodega.setEditable(false);
		add(txtBodega, cc.xywh(5, 3, 3, 1));

		//---- btnBuscarBodega ----
		btnBuscarBodega.setText("B");
		add(btnBuscarBodega, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblFechaCorte ----
		lblFechaCorte.setText("Fecha de corte:");
		add(lblFechaCorte, cc.xy(3, 5));
		add(cmbFechaCorte, cc.xywh(5, 5, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(11, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== spStockValorizado ========
		{

			//---- tblStockValorizado ----
			tblStockValorizado.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"SKU", "MODELO", "PRESENTACION", "COLOR", "STOCK", "PR. UNITARIO", "STOCK VALORIZADO"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spStockValorizado.setViewportView(tblStockValorizado);
		}
		add(spStockValorizado, cc.xywh(3, 9, 10, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblBodega;
	private JTextField txtBodega;
	private JButton btnBuscarBodega;
	private JLabel lblFechaCorte;
	private DateComboBox cmbFechaCorte;
	private JButton btnConsultar;
	private JScrollPane spStockValorizado;
	private JTable tblStockValorizado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblBodega() {
		return lblBodega;
	}

	public void setLblBodega(JLabel lblBodega) {
		this.lblBodega = lblBodega;
	}

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

	public JLabel getLblFechaCorte() {
		return lblFechaCorte;
	}

	public void setLblFechaCorte(JLabel lblFechaCorte) {
		this.lblFechaCorte = lblFechaCorte;
	}

	public DateComboBox getCmbFechaCorte() {
		return cmbFechaCorte;
	}

	public void setCmbFechaCorte(DateComboBox cmbFechaCorte) {
		this.cmbFechaCorte = cmbFechaCorte;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JScrollPane getSpStockValorizado() {
		return spStockValorizado;
	}

	public void setSpStockValorizado(JScrollPane spStockValorizado) {
		this.spStockValorizado = spStockValorizado;
	}

	public JTable getTblStockValorizado() {
		return tblStockValorizado;
	}

	public void setTblStockValorizado(JTable tblStockValorizado) {
		this.tblStockValorizado = tblStockValorizado;
	}
}
