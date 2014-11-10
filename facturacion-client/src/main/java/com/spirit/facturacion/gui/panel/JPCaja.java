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
public class JPCaja extends JPanel {
	public JPCaja() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPCaja = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblnombre = new JLabel();
		txtNombre = new JTextField();
		lblOficina = new JLabel();
		cmbOficinaId = new JComboBox();
		lblTurno = new JLabel();
		cmbTurno = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		CellConstraints cc = new CellConstraints();

		//======== JPCaja ========
		{
			JPCaja.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
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
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblCodigo ----
			lblCodigo.setText("C\u00f3digo:");
			JPCaja.add(lblCodigo, cc.xy(3, 3));
			JPCaja.add(txtCodigo, cc.xy(5, 3));
			
			//---- lblnombre ----
			lblnombre.setText("Nombre:");
			JPCaja.add(lblnombre, cc.xy(3, 5));
			JPCaja.add(txtNombre, cc.xywh(5, 5, 5, 1));
			
			//---- lblOficina ----
			lblOficina.setText("Oficina:");
			JPCaja.add(lblOficina, cc.xy(3, 7));
			JPCaja.add(cmbOficinaId, cc.xy(5, 7));
			
			//---- lblTurno ----
			lblTurno.setText("Turno:");
			JPCaja.add(lblTurno, cc.xy(3, 9));
			JPCaja.add(cmbTurno, cc.xy(5, 9));
			
			//---- lblEstado ----
			lblEstado.setText("Estado:");
			JPCaja.add(lblEstado, cc.xy(3, 11));
			JPCaja.add(cmbEstado, cc.xy(5, 11));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPCaja;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblnombre;
	private JTextField txtNombre;
	private JLabel lblOficina;
	private JComboBox cmbOficinaId;
	private JLabel lblTurno;
	private JComboBox cmbTurno;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
