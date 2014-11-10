package com.spirit.facturacion.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;

public abstract class JDInformacionEnvio extends JDialogModelImpl {
	public JDInformacionEnvio(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDInformacionEnvio(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		lblMetodoEnvio = new JLabel();
		txtMetodoEnvio = new JTextField();
		lblNombresCliente = new JLabel();
		txtNombresClientes = new JTextField();
		lblCorreoCliente = new JLabel();
		txtCorreoCliente = new JTextField();
		tabbedPane1 = new JTabbedPane();
		panelInforamcionEnvio = new JScrollPane();
		txtInformacionEnvio = new JTextArea();
		panelInformacionFacturacion = new JScrollPane();
		txtInformacionFacturacion = new JTextArea();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(300), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(250), FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(100)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(80)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblMetodoEnvio ----
			lblMetodoEnvio.setText("M\u00e9todo de Env\u00edo: ");
			lblMetodoEnvio.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel1.add(lblMetodoEnvio, cc.xy(1, 1));
			
			//---- txtMetodoEnvio ----
			txtMetodoEnvio.setEditable(false);
			panel1.add(txtMetodoEnvio, cc.xywh(3, 1, 3, 1));
			
			//---- lblNombresCliente ----
			lblNombresCliente.setText("Nombres Cliente:");
			lblNombresCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel1.add(lblNombresCliente, cc.xy(1, 3));
			
			//---- txtNombresClientes ----
			txtNombresClientes.setEditable(false);
			panel1.add(txtNombresClientes, cc.xywh(3, 3, 3, 1));
			
			//---- lblCorreoCliente ----
			lblCorreoCliente.setText("Correo Cliente: ");
			lblCorreoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel1.add(lblCorreoCliente, cc.xy(1, 5));
			
			//---- txtCorreoCliente ----
			txtCorreoCliente.setEditable(false);
			panel1.add(txtCorreoCliente, cc.xywh(3, 5, 3, 1));
		}
		contentPane.add(panel1, cc.xy(3, 3));

		//======== tabbedPane1 ========
		{
			
			//======== panelInforamcionEnvio ========
			{
				panelInforamcionEnvio.setViewportView(txtInformacionEnvio);
			}
			tabbedPane1.addTab("Env\u00edo", panelInforamcionEnvio);
			
			
			//======== panelInformacionFacturacion ========
			{
				panelInformacionFacturacion.setViewportView(txtInformacionFacturacion);
			}
			tabbedPane1.addTab("Facturaci\u00f3n", panelInformacionFacturacion);
			
		}
		contentPane.add(tabbedPane1, cc.xy(3, 7));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JLabel lblMetodoEnvio;
	private JTextField txtMetodoEnvio;
	private JLabel lblNombresCliente;
	private JTextField txtNombresClientes;
	private JLabel lblCorreoCliente;
	private JTextField txtCorreoCliente;
	private JTabbedPane tabbedPane1;
	private JScrollPane panelInforamcionEnvio;
	private JTextArea txtInformacionEnvio;
	private JScrollPane panelInformacionFacturacion;
	private JTextArea txtInformacionFacturacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getPanel1() {
		return panel1;
	}

	public JLabel getLblMetodoEnvio() {
		return lblMetodoEnvio;
	}

	public JTextField getTxtMetodoEnvio() {
		return txtMetodoEnvio;
	}

	public JLabel getLblNombresCliente() {
		return lblNombresCliente;
	}

	public JTextField getTxtNombresClientes() {
		return txtNombresClientes;
	}

	public JLabel getLblCorreoCliente() {
		return lblCorreoCliente;
	}

	public JTextField getTxtCorreoCliente() {
		return txtCorreoCliente;
	}

	public JTabbedPane getTabbedPane1() {
		return tabbedPane1;
	}

	public JScrollPane getPanelInforamcionEnvio() {
		return panelInforamcionEnvio;
	}

	public JTextArea getTxtInformacionEnvio() {
		return txtInformacionEnvio;
	}

	public JScrollPane getPanelInformacionFacturacion() {
		return panelInformacionFacturacion;
	}

	public JTextArea getTxtInformacionFacturacion() {
		return txtInformacionFacturacion;
	}
}
