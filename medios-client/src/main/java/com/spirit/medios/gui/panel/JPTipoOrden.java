package com.spirit.medios.gui.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Tatiana Vásconez
 */
public abstract class JPTipoOrden extends SpiritModelImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4568109443888866687L;
	public JPTipoOrden() {
		initComponents();
		setName("TipoOrden");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPTipoOrden = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== JPTipoOrden ========
		{
			JPTipoOrden.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
					new RowSpec("max(default;10dlu)"),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblCodigo ----
			lblCodigo.setText("C\u00f3digo:");
			JPTipoOrden.add(lblCodigo, cc.xy(3, 3));
			JPTipoOrden.add(txtCodigo, cc.xy(5, 3));
			
			//---- lblNombre ----
			lblNombre.setText("Nombre:");
			JPTipoOrden.add(lblNombre, cc.xy(3, 5));
			JPTipoOrden.add(txtNombre, cc.xywh(5, 5, 3, 1));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPTipoOrden;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
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
