package com.spirit.general.gui.panel;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPSubtipoOrden extends MantenimientoModelImpl {
	public JPSubtipoOrden() {
		initComponents();
		setName("Subtipo de Orden");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		txtCodigo = new JTextField();
		lblTipoOrden = new JLabel();
		cmbTipoOrden = new JComboBox();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		spSubtipoOrden = new JScrollPane();
		tblSubtipoOrden = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
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
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xy(3, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xy(3, 5));
		add(txtNombre, cc.xywh(5, 5, 3, 1));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblTipoOrden ----
		lblTipoOrden.setText("Tipo Orden:");
		add(lblTipoOrden, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoOrden, cc.xywh(5, 7, 5, 1));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo Proveedor:");
		lblTipoProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoProveedor, cc.xy(3, 9));
		add(cmbTipoProveedor, cc.xywh(5, 9, 5, 1));

		//======== spSubtipoOrden ========
		{
			
			//---- tblSubtipoOrden ----
			tblSubtipoOrden.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Tipo de Orden", "Tipo de Proveedor"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblSubtipoOrden.setPreferredScrollableViewportSize(new Dimension(450, 150));
			spSubtipoOrden.setViewportView(tblSubtipoOrden);
		}
		add(spSubtipoOrden, cc.xywh(3, 13, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JLabel lblTipoOrden;
	private JComboBox cmbTipoOrden;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JScrollPane spSubtipoOrden;
	private JTable tblSubtipoOrden;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbTipoOrden() {
		return cmbTipoOrden;
	}

	public void setCmbTipoOrden(JComboBox cmbTipoOrden) {
		this.cmbTipoOrden = cmbTipoOrden;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public void setCmbTipoProveedor(JComboBox cmbTipoProveedor) {
		this.cmbTipoProveedor = cmbTipoProveedor;
	}

	public JTable getTblSubtipoOrden() {
		return tblSubtipoOrden;
	}

	public void setTblSubtipoOrden(JTable tblSubtipoOrden) {
		this.tblSubtipoOrden = tblSubtipoOrden;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
}
