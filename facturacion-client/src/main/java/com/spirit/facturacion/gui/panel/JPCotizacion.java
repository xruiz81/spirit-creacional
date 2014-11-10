package com.spirit.facturacion.gui.panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;



/**
 * @author Antonio Seiler
 */
public class JPCotizacion extends JPanel {
	public JPCotizacion() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblMonedaId = new JLabel();
		cmbMonedaId = new JComboBox();
		lblMonedaEquivId = new JLabel();
		txtMonedaEquivId = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec("max(default;10dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				new RowSpec("max(default;10dlu)"),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//---- lblMonedaId ----
		lblMonedaId.setText("Moneda:");
		add(lblMonedaId, cc.xy(3, 3));
		add(cmbMonedaId, cc.xy(5, 3));

		//---- lblMonedaEquivId ----
		lblMonedaEquivId.setText("Moneda Equivalencia:");
		add(lblMonedaEquivId, cc.xy(3, 5));
		add(txtMonedaEquivId, cc.xy(5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblMonedaId;
	private JComboBox cmbMonedaId;
	private JLabel lblMonedaEquivId;
	private JTextField txtMonedaEquivId;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
