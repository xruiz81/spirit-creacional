package com.spirit.inventario.gui.panel;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;

/**
 * @author xruiz
 */
public abstract class JPStock extends MantenimientoModelImpl {
	public JPStock() {
		initComponents();
		setName("Stock");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblBodega = new JLabel();
		cmbBodega = new JComboBox();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		lblAnioMes = new JLabel();
		cmbFecha = new DateComboBox();
		lblLote = new JLabel();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		lblReserva = new JLabel();
		cmbLote = new JComboBox();
		lblTransito = new JLabel();
		txtTransito = new JTextField();
		lblPiso = new JLabel();
		txtPiso = new JTextField();
		lblFila = new JLabel();
		txtFila = new JTextField();
		lblPercha = new JLabel();
		txtPercha = new JTextField();
		lblNivelPercha = new JLabel();
		txtNivelPercha = new JTextField();
		txtReserva = new JTextField();
		spTblStock = new JScrollPane();
		tblStock = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblBodega ----
		lblBodega.setText("Bodega:");
		add(lblBodega, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbBodega, cc.xywh(5, 3, 9, 1));

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		add(lblProducto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtProducto, cc.xywh(5, 5, 9, 1));
		add(btnProducto, cc.xywh(15, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblAnioMes ----
		lblAnioMes.setText("A\u00f1o / Mes:");
		add(lblAnioMes, cc.xywh(19, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFecha, cc.xywh(21, 5, 3, 1));

		//---- lblLote ----
		lblLote.setText("Lote:");
		add(lblLote, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblCantidad ----
		lblCantidad.setText("Cantidad:");
		add(lblCantidad, cc.xywh(19, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCantidad, cc.xy(21, 7));

		//---- lblReserva ----
		lblReserva.setText("Reserva:");
		add(lblReserva, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbLote, cc.xywh(5, 7, 9, 1));

		//---- lblTransito ----
		lblTransito.setText("Transito:");
		add(lblTransito, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtTransito, cc.xy(11, 9));

		//---- lblPiso ----
		lblPiso.setText("Piso:");
		add(lblPiso, cc.xywh(19, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtPiso, cc.xy(21, 9));

		//---- lblFila ----
		lblFila.setText("Fila:");
		add(lblFila, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtFila, cc.xywh(5, 11, 17, 1));
		add(txtReserva, cc.xy(5, 9));

		//---- lblPercha ----
		lblPercha.setText("Percha:");
		add(lblPercha, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtPercha, cc.xywh(5, 13, 17, 1));

		//---- lblNivelPercha ----
		lblNivelPercha.setText("Nivel Percha:");
		add(lblNivelPercha, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNivelPercha, cc.xywh(5, 15, 17, 1));

		//======== spTblStock ========
		{
			
			//---- tblStock ----
			tblStock.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"Bodega", "Producto", "Lote", "A\u00f1o / Mes", "Cantidad"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblStock.setViewportView(tblStock);
		}
		add(spTblStock, cc.xywh(3, 19, 23, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnProducto.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnProducto.setToolTipText("Buscar Producto");
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblBodega;
	private JComboBox cmbBodega;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JLabel lblAnioMes;
	private DateComboBox cmbFecha;
	private JLabel lblLote;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JLabel lblReserva;
	private JComboBox cmbLote;
	private JLabel lblTransito;
	private JTextField txtTransito;
	private JLabel lblPiso;
	private JTextField txtPiso;
	private JLabel lblFila;
	private JTextField txtFila;
	private JLabel lblPercha;
	private JTextField txtPercha;
	private JLabel lblNivelPercha;
	private JTextField txtNivelPercha;
	private JTextField txtReserva;
	private JScrollPane spTblStock;
	private JTable tblStock;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public void setCmbBodega(JComboBox cmbBodega) {
		this.cmbBodega = cmbBodega;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public JComboBox getCmbLote() {
		return cmbLote;
	}

	public void setCmbLote(JComboBox cmbLote) {
		this.cmbLote = cmbLote;
	}

	public JScrollPane getSpTblStock() {
		return spTblStock;
	}

	public void setSpTblStock(JScrollPane spTblStock) {
		this.spTblStock = spTblStock;
	}

	public JTable getTblStock() {
		return tblStock;
	}

	public void setTblStock(JTable tblStock) {
		this.tblStock = tblStock;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtFila() {
		return txtFila;
	}

	public void setTxtFila(JTextField txtFila) {
		this.txtFila = txtFila;
	}

	public JTextField getTxtNivelPercha() {
		return txtNivelPercha;
	}

	public void setTxtNivelPercha(JTextField txtNivelPercha) {
		this.txtNivelPercha = txtNivelPercha;
	}

	public JTextField getTxtPercha() {
		return txtPercha;
	}

	public void setTxtPercha(JTextField txtPercha) {
		this.txtPercha = txtPercha;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public void setTxtPiso(JTextField txtPiso) {
		this.txtPiso = txtPiso;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JTextField getTxtReserva() {
		return txtReserva;
	}

	public void setTxtReserva(JTextField txtReserva) {
		this.txtReserva = txtReserva;
	}

	public JTextField getTxtTransito() {
		return txtTransito;
	}

	public void setTxtTransito(JTextField txtTransito) {
		this.txtTransito = txtTransito;
	}
}
