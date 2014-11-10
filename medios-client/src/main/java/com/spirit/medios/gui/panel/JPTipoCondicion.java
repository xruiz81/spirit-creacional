package com.spirit.medios.gui.panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPTipoCondicion extends SpiritModelImpl {

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		textField1 = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== JPTipoCondicion ========
		{
			setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;10dlu):grow")
				},
				new RowSpec[] {
					new RowSpec("max(default;10dlu)"),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblCodigo ----
			lblCodigo.setText("C\u00f3digo:");
			add(lblCodigo, cc.xy(3, 3));
			add(txtCodigo, cc.xy(5, 3));
			
			//---- lblNombre ----
			lblNombre.setText("Nombre:");
			add(lblNombre, cc.xy(3, 5));
			add(textField1, cc.xywh(5, 5, 3, 1));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField textField1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
