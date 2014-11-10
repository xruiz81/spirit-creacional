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
 * @author Antonio Seiler
 */
public abstract class JPLote extends MantenimientoModelImpl {
	public JPLote() {
		initComponents();
		setName("Lote");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		lblFechaElaboracion = new JLabel();
		cmbFechaElaboracion = new DateComboBox();
		lblFechaVencimiento = new JLabel();
		cmbFechaVencimiento = new DateComboBox();
		cmbEstado = new JComboBox();
		lblEstado = new JLabel();
		spTblLote = new JScrollPane();
		tblLote = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(45)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xywh(5, 3, 3, 1));

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		add(lblProducto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtProducto, cc.xywh(5, 5, 5, 1));
		btnProducto.setToolTipText("Buscar Producto");
		btnProducto.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		add(btnProducto, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaElaboracion ----
		lblFechaElaboracion.setText("Fecha de Elaboraci\u00f3n:");
		add(lblFechaElaboracion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaElaboracion, cc.xywh(5, 7, 3, 1));

		//---- lblFechaVencimiento ----
		lblFechaVencimiento.setText("Fecha de Vencimiento:");
		add(lblFechaVencimiento, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaVencimiento, cc.xywh(5, 9, 3, 1));
		add(cmbEstado, cc.xy(5, 11));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== spTblLote ========
		{
			
			//---- tblLote ----
			tblLote.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"Código", "Producto", "Fecha de Elaboración", "Fecha de Vencimiento", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblLote.getTableHeader().setReorderingAllowed(false);
			spTblLote.setViewportView(tblLote);
		}
		add(spTblLote, cc.xywh(3, 15, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JLabel lblFechaElaboracion;
	private DateComboBox cmbFechaElaboracion;
	private JLabel lblFechaVencimiento;
	private DateComboBox cmbFechaVencimiento;
	private JComboBox cmbEstado;
	private JLabel lblEstado;
	private JScrollPane spTblLote;
	private JTable tblLote;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFechaElaboracion() {
		return cmbFechaElaboracion;
	}

	public void setCmbFechaElaboracion(DateComboBox cmbFechaElaboracion) {
		this.cmbFechaElaboracion = cmbFechaElaboracion;
	}

	public DateComboBox getCmbFechaVencimiento() {
		return cmbFechaVencimiento;
	}

	public void setCmbFechaVencimiento(DateComboBox cmbFechaVencimiento) {
		this.cmbFechaVencimiento = cmbFechaVencimiento;
	}

	public JScrollPane getSpTblLote() {
		return spTblLote;
	}

	public void setSpTblLote(JScrollPane spTblLote) {
		this.spTblLote = spTblLote;
	}

	public JTable getTblLote() {
		return tblLote;
	}

	public void setTblLote(JTable tblLote) {
		this.tblLote = tblLote;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}
}
