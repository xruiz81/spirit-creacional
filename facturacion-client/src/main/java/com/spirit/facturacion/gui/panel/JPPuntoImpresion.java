package com.spirit.facturacion.gui.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;



/**
 * @author Antonio Seiler
 */
public class JPPuntoImpresion extends JPanel {
	public JPPuntoImpresion() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCajaId = new JLabel();
		txtCajaId = new JTextField();
		lblSerie = new JLabel();
		txtSerie = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec("max(default;10dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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

		//---- lblCajaId ----
		lblCajaId.setText("Caja:");
		add(lblCajaId, cc.xy(3, 3));
		add(txtCajaId, cc.xy(5, 3));

		//---- lblSerie ----
		lblSerie.setText("Serie:");
		add(lblSerie, cc.xy(3, 5));
		add(txtSerie, cc.xy(5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCajaId;
	private JTextField txtCajaId;
	private JLabel lblSerie;
	private JTextField txtSerie;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
