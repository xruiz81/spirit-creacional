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
 * @author Antonio Seiler
 */
public abstract class JPKardex extends ReportModelImpl{
	public JPKardex() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		txtOficina = new JTextField();
		btnEliminarOficina = new JButton();
		btnBuscarOficina = new JButton();
		label2 = new JLabel();
		txtBodega = new JTextField();
		btnEliminarBodega = new JButton();
		btnBuscarBodega = new JButton();
		label3 = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		btnEliminarProducto = new JButton();
		label4 = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnConsulta = new JButton();
		label5 = new JLabel();
		cmbFechaFinal = new DateComboBox();
		btnGenerarKardex = new JButton();
		scrollPane1 = new JScrollPane();
		tblKardex = new JTable();
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

		//---- label1 ----
		label1.setText("Oficina:");
		add(label1, cc.xy(3, 3));

		//---- txtOficina ----
		txtOficina.setEnabled(false);
		txtOficina.setEditable(false);
		add(txtOficina, cc.xywh(5, 3, 3, 1));

		//---- btnEliminarOficina ----
		btnEliminarOficina.setText("E");
		add(btnEliminarOficina, cc.xy(13, 3));

		//---- btnBuscarOficina ----
		btnBuscarOficina.setText("B");
		add(btnBuscarOficina, cc.xy(11, 3));

		//---- label2 ----
		label2.setText("Bodega:");
		add(label2, cc.xy(3, 5));

		//---- txtBodega ----
		txtBodega.setEnabled(false);
		txtBodega.setEditable(false);
		add(txtBodega, cc.xywh(5, 5, 3, 1));

		//---- btnEliminarBodega ----
		btnEliminarBodega.setText("E");
		add(btnEliminarBodega, cc.xy(13, 5));

		//---- btnBuscarBodega ----
		btnBuscarBodega.setText("B");
		add(btnBuscarBodega, cc.xy(11, 5));

		//---- label3 ----
		label3.setText("Producto:");
		add(label3, cc.xy(3, 7));

		//---- txtProducto ----
		txtProducto.setEnabled(false);
		txtProducto.setEditable(false);
		add(txtProducto, cc.xywh(5, 7, 3, 1));

		//---- btnBuscarProducto ----
		btnBuscarProducto.setText("B");
		add(btnBuscarProducto, cc.xy(11, 7));

		//---- btnEliminarProducto ----
		btnEliminarProducto.setText("E");
		add(btnEliminarProducto, cc.xy(13, 7));

		//---- label4 ----
		label4.setText("Fecha inicial:");
		add(label4, cc.xy(3, 9));
		add(cmbFechaInicial, cc.xy(5, 9));

		//---- btnConsulta ----
		btnConsulta.setText("Consulta");
		add(btnConsulta, cc.xywh(11, 9, 3, 1));

		//---- label5 ----
		label5.setText("Fecha Final:");
		add(label5, cc.xy(3, 11));
		add(cmbFechaFinal, cc.xy(5, 11));

		//---- btnGenerarKardex ----
		btnGenerarKardex.setText("Reporte");
		add(btnGenerarKardex, cc.xywh(11, 11, 3, 1));

		//======== scrollPane1 ========
		{
			
			//---- tblKardex ----
			tblKardex.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Sucursal", "Bodega", "Lote", "Producto", "Fecha", "Movimento", "Cantidad"
				}
			));
			scrollPane1.setViewportView(tblKardex);
		}
		add(scrollPane1, cc.xywh(3, 13, 16, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTextField txtOficina;
	private JButton btnEliminarOficina;
	private JButton btnBuscarOficina;
	private JLabel label2;
	private JTextField txtBodega;
	private JButton btnEliminarBodega;
	private JButton btnBuscarBodega;
	private JLabel label3;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JButton btnEliminarProducto;
	private JLabel label4;
	private DateComboBox cmbFechaInicial;
	private JButton btnConsulta;
	private JLabel label5;
	private DateComboBox cmbFechaFinal;
	private JButton btnGenerarKardex;
	private JScrollPane scrollPane1;
	private JTable tblKardex;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public JButton getBtnBuscarOficina() {
		return btnBuscarOficina;
	}

	public JTextField getTxtBodega() {
		return txtBodega;
	}

	public JButton getBtnBuscarBodega() {
		return btnBuscarBodega;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public JButton getBtnGenerarKardex() {
		return btnGenerarKardex;
	}

	public JButton getBtnEliminarOficina() {
		return btnEliminarOficina;
	}

	public void setBtnEliminarOficina(JButton btnEliminarOficina) {
		this.btnEliminarOficina = btnEliminarOficina;
	}

	public JButton getBtnEliminarBodega() {
		return btnEliminarBodega;
	}

	public void setBtnEliminarBodega(JButton btnEliminarBodega) {
		this.btnEliminarBodega = btnEliminarBodega;
	}

	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}

	public void setBtnEliminarProducto(JButton btnEliminarProducto) {
		this.btnEliminarProducto = btnEliminarProducto;
	}

	public JButton getBtnConsulta() {
		return btnConsulta;
	}

	public void setBtnConsulta(JButton btnConsulta) {
		this.btnConsulta = btnConsulta;
	}

	public JTable getTblKardex() {
		return tblKardex;
	}

	public void setTblKardex(JTable tblKardex) {
		this.tblKardex = tblKardex;
	}
	
}
