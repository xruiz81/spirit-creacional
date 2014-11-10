package com.spirit.cartera.gui.panel;

import javax.swing.JComboBox;
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
public class JPCarteraDetalle extends JPanel {
	public JPCarteraDetalle() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPCarteraDetalle = new JPanel();
		lblCarteraId = new JLabel();
		cmbCarteraId = new JComboBox();
		lblCarteraAplId = new JLabel();
		cmbCarteraAplId = new JComboBox();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblFechaAplicacion = new JLabel();
		txtFechaAplicacion = new JTextField();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		lblCartera = new JLabel();
		txtCartera = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== JPCarteraDetalle ========
		{
			JPCarteraDetalle.setLayout(new FormLayout(
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
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblCarteraId ----
			lblCarteraId.setText("Cartera:");
			JPCarteraDetalle.add(lblCarteraId, cc.xy(3, 3));
			JPCarteraDetalle.add(cmbCarteraId, cc.xy(5, 3));
			
			//---- lblCarteraAplId ----
			lblCarteraAplId.setText("Cartera Apl:");
			JPCarteraDetalle.add(lblCarteraAplId, cc.xy(3, 5));
			JPCarteraDetalle.add(cmbCarteraAplId, cc.xy(5, 5));
			
			//---- lblValor ----
			lblValor.setText("Valor:");
			JPCarteraDetalle.add(lblValor, cc.xy(3, 7));
			JPCarteraDetalle.add(txtValor, cc.xy(5, 7));
			
			//---- lblFechaAplicacion ----
			lblFechaAplicacion.setText("Fecha Aplicaci\u00f3n:");
			JPCarteraDetalle.add(lblFechaAplicacion, cc.xy(3, 9));
			JPCarteraDetalle.add(txtFechaAplicacion, cc.xy(5, 9));
			
			//---- lblFechaCreacion ----
			lblFechaCreacion.setText("Fecha Creaci\u00f3n:");
			JPCarteraDetalle.add(lblFechaCreacion, cc.xy(3, 11));
			JPCarteraDetalle.add(txtFechaCreacion, cc.xy(5, 11));
			
			//---- lblCartera ----
			lblCartera.setText("Cartera:");
			JPCarteraDetalle.add(lblCartera, cc.xy(3, 13));
			JPCarteraDetalle.add(txtCartera, cc.xy(5, 13));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPCarteraDetalle;
	private JLabel lblCarteraId;
	private JComboBox cmbCarteraId;
	private JLabel lblCarteraAplId;
	private JComboBox cmbCarteraAplId;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblFechaAplicacion;
	private JTextField txtFechaAplicacion;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JLabel lblCartera;
	private JTextField txtCartera;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
