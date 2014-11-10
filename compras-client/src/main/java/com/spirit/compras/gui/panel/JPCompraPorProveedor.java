package com.spirit.compras.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPCompraPorProveedor extends ReportModelImpl {

	public JPCompraPorProveedor() {
		initComponents();
		setName("Compras por Proveedor");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblProveedor = new JLabel();
		btnBuscarProveedor = new JButton();
		txtProveedor = new JTextField();
		lblFecha = new JLabel();
		cmbFecha = new DateComboBox();
		cmbEstado = new JComboBox();
		lblEstado = new JLabel();
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
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- btnBuscarProveedor ----
		btnBuscarProveedor.setToolTipText("Buscar proveedor");
		btnBuscarProveedor.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		add(btnBuscarProveedor, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtProveedor, cc.xywh(5, 3, 5, 1));

		//---- lblFecha ----
		lblFecha.setText("Fecha:");
		add(lblFecha, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFecha, cc.xywh(5, 5, 3, 1));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"INACTIVA",
			"ENVIADA",
			"AUTORIZADA",
			"TODAS"
		}));
		add(cmbEstado, cc.xy(5, 7));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblProveedor;
	private JButton btnBuscarProveedor;
	private JTextField txtProveedor;
	private JLabel lblFecha;
	private DateComboBox cmbFecha;
	private JComboBox cmbEstado;
	private JLabel lblEstado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public void setBtnBuscarProveedor(JButton btnBuscarProveedor) {
		this.btnBuscarProveedor = btnBuscarProveedor;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}
}
