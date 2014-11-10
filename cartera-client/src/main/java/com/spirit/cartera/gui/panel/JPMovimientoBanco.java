package com.spirit.cartera.gui.panel;

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
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author Antonio Seiler
 */
public abstract class JPMovimientoBanco extends SpiritModelImpl {
	public JPMovimientoBanco() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPMovimientoBanco = new JPanel();
		lblCuentaId = new JLabel();
		cmbCuentaId = new JComboBox();
		lblDocumentoId = new JLabel();
		cmbDocumentoId = new JComboBox();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblFecha = new JLabel();
		txtFecha = new JTextField();
		lblValor = new JLabel();
		txtValor = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== JPMovimientoBanco ========
		{
			JPMovimientoBanco.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
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
			
			//---- lblCuentaId ----
			lblCuentaId.setText("Nro. de Cuenta:");
			JPMovimientoBanco.add(lblCuentaId, cc.xy(3, 3));
			JPMovimientoBanco.add(cmbCuentaId, cc.xy(5, 3));
			
			//---- lblDocumentoId ----
			lblDocumentoId.setText("Documento:");
			JPMovimientoBanco.add(lblDocumentoId, cc.xy(3, 5));
			JPMovimientoBanco.add(cmbDocumentoId, cc.xy(5, 5));
			
			//---- lblReferencia ----
			lblReferencia.setText("Referencia:");
			JPMovimientoBanco.add(lblReferencia, cc.xy(3, 7));
			JPMovimientoBanco.add(txtReferencia, cc.xywh(5, 7, 3, 1));
			
			//---- lblFecha ----
			lblFecha.setText("Fecha:");
			JPMovimientoBanco.add(lblFecha, cc.xy(3, 9));
			JPMovimientoBanco.add(txtFecha, cc.xy(5, 9));
			
			//---- lblValor ----
			lblValor.setText("Valor:");
			JPMovimientoBanco.add(lblValor, cc.xy(3, 11));
			
			//---- txtValor ----
			txtValor.setHorizontalAlignment(JTextField.RIGHT);
			JPMovimientoBanco.add(txtValor, cc.xy(5, 11));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPMovimientoBanco;
	private JLabel lblCuentaId;
	private JComboBox cmbCuentaId;
	private JLabel lblDocumentoId;
	private JComboBox cmbDocumentoId;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblValor;
	private JTextField txtValor;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
