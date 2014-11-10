package com.spirit.facturacion.gui.panel;

import javax.swing.JComboBox;
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
 * @author Antonio Seiler
 */
public abstract class JPPrecioHistorico extends SpiritModelImpl {
	public JPPrecioHistorico() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPPrecioHistorico = new JPanel();
		lblPrecioId = new JLabel();
		cmbPrecioId = new JComboBox();
		lblFechaIni = new JLabel();
		txtFechaIni = new JTextField();
		lblFechaFinal = new JLabel();
		txtFechaFinal = new JTextField();
		lblPrecio = new JLabel();
		txtPrecio = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== JPPrecioHistorico ========
		{
			JPPrecioHistorico.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;10dlu)")
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
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblPrecioId ----
			lblPrecioId.setText("Precio:");
			JPPrecioHistorico.add(lblPrecioId, cc.xy(3, 3));
			JPPrecioHistorico.add(cmbPrecioId, cc.xy(5, 3));
			
			//---- lblFechaIni ----
			lblFechaIni.setText("Fecha Inicial:");
			JPPrecioHistorico.add(lblFechaIni, cc.xy(3, 5));
			JPPrecioHistorico.add(txtFechaIni, cc.xy(5, 5));
			
			//---- lblFechaFinal ----
			lblFechaFinal.setText("Fecha Final:");
			JPPrecioHistorico.add(lblFechaFinal, cc.xy(3, 7));
			JPPrecioHistorico.add(txtFechaFinal, cc.xy(5, 7));
			
			//---- lblPrecio ----
			lblPrecio.setText("Precio:");
			JPPrecioHistorico.add(lblPrecio, cc.xy(3, 9));
			
			//---- txtPrecio ----
			txtPrecio.setHorizontalAlignment(JTextField.RIGHT);
			JPPrecioHistorico.add(txtPrecio, cc.xy(5, 9));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPPrecioHistorico;
	private JLabel lblPrecioId;
	private JComboBox cmbPrecioId;
	private JLabel lblFechaIni;
	private JTextField txtFechaIni;
	private JLabel lblFechaFinal;
	private JTextField txtFechaFinal;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
