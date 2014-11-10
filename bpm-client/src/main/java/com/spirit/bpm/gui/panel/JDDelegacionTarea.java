package com.spirit.bpm.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;



/**
 * @author Antonio Seiler
 */
public class JDDelegacionTarea extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JDDelegacionTarea(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDDelegacionTarea(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		lblObservacion = new JLabel();
		spObservacion = new JScrollPane();
		txtObservacion = new JTextArea();
		lblDelegado = new JLabel();
		cmbDelegado = new JComboBox();
		btnEnviar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Delegar Tarea");
		setResizable(false);
		setModal(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, 1.0),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblObservacion ----
			lblObservacion.setText("Observaci\u00f3n");
			panel1.add(lblObservacion, cc.xy(3, 3));
			
			//======== spObservacion ========
			{
				
				//---- txtObservacion ----
				txtObservacion.setRows(4);
				txtObservacion.setColumns(50);
				spObservacion.setViewportView(txtObservacion);
			}
			panel1.add(spObservacion, cc.xywh(3, 5, 9, 4));
			
			//---- lblDelegado ----
			lblDelegado.setText("Delegado");
			panel1.add(lblDelegado, cc.xy(3, 9));
			panel1.add(cmbDelegado, cc.xywh(3, 11, 5, 1));
			
			//---- btnEnviar ----
			btnEnviar.setText("Enviar");
			panel1.add(btnEnviar, cc.xy(5, 13));
			
			//---- btnCancelar ----
			btnCancelar.setText("Cancelar");
			panel1.add(btnCancelar, cc.xy(9, 13));
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JLabel lblObservacion;
	private JScrollPane spObservacion;
	private JTextArea txtObservacion;
	private JLabel lblDelegado;
	private JComboBox cmbDelegado;
	private JButton btnEnviar;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
