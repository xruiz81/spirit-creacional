package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPTipoProductoDonacion extends SpiritModelImpl {
	public JPTipoProductoDonacion() {
		initComponents();
		setName("Donacion por tipo de producto");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoProducto = new JLabel();
		txtValorItem = new JTextField();
		cmbTipoProducto = new JComboBox();
		lblSaldo = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoProducto ----
		lblTipoProducto.setText("Tipo Producto:");
		lblTipoProducto.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblTipoProducto, cc.xy(3, 3));

		//---- txtValorItem ----
		txtValorItem.setHorizontalAlignment(JTextField.RIGHT);
		txtValorItem.setEditable(true);
		txtValorItem.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtValorItem, cc.xy(5, 5));

		//---- cmbTipoProducto ----
		cmbTipoProducto.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(cmbTipoProducto, cc.xywh(5, 3, 3, 1));

		//---- lblSaldo ----
		lblSaldo.setText("V A L O R por item:");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoProducto;
	private JTextField txtValorItem;
	private JComboBox cmbTipoProducto;
	private JLabel lblSaldo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblTipoProducto() {
		return lblTipoProducto;
	}

	public void setLblTipoProducto(JLabel lblTipoProducto) {
		this.lblTipoProducto = lblTipoProducto;
	}

	public JTextField getTxtValorItem() {
		return txtValorItem;
	}

	public void setTxtValorItem(JTextField txtValorItem) {
		this.txtValorItem = txtValorItem;
	}

	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}

	public void setCmbTipoProducto(JComboBox cmbTipoProducto) {
		this.cmbTipoProducto = cmbTipoProducto;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}
	
	
	
	
}
