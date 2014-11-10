package com.spirit.contabilidad.gui.panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public class JDAgregarNota extends JDialog {
	public JDAgregarNota(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAgregarNota(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		lblCaracteresRestantes = new JLabel();
		lblTotalCaracteresRestantes = new JLabel();
		spNota = new JScrollPane();
		txtNota = new JTextArea();
		buttonBar = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Agregar Nota");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(85)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(20)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;105dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblCaracteresRestantes ----
				lblCaracteresRestantes.setText("Caracteres restantes:");
				lblCaracteresRestantes.setFont(new Font("Tahoma", Font.BOLD, 11));
				contentPanel.add(lblCaracteresRestantes, cc.xywh(1, 1, 3, 1));

				//---- lblTotalCaracteresRestantes ----
				lblTotalCaracteresRestantes.setText("500");
				lblTotalCaracteresRestantes.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblTotalCaracteresRestantes.setHorizontalAlignment(SwingConstants.RIGHT);
				contentPanel.add(lblTotalCaracteresRestantes, cc.xy(5, 1));

				//======== spNota ========
				{

					//---- txtNota ----
					txtNota.setRows(5);
					txtNota.setColumns(50);
					txtNota.setLineWrap(true);
					txtNota.setWrapStyleWord(true);
					spNota.setViewportView(txtNota);
				}
				contentPanel.add(spNota, cc.xywh(1, 3, 11, 5));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.GLUE_COLSPEC,
						FormFactory.BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.BUTTON_COLSPEC
					},
					RowSpec.decodeSpecs("pref")));

				//---- btnAceptar ----
				btnAceptar.setText("Aceptar");
				buttonBar.add(btnAceptar, cc.xy(2, 1));

				//---- btnCancelar ----
				btnCancelar.setText("Cancelar");
				buttonBar.add(btnCancelar, cc.xy(4, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel lblCaracteresRestantes;
	private JLabel lblTotalCaracteresRestantes;
	private JScrollPane spNota;
	private JTextArea txtNota;
	private JPanel buttonBar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextArea getTxtNota() {
		return txtNota;
	}

	public void setTxtNota(JTextArea txtNota) {
		this.txtNota = txtNota;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JLabel getLblTotalCaracteresRestantes() {
		return lblTotalCaracteresRestantes;
	}

	public void setLblTotalCaracteresRestantes(JLabel lblTotalCaracteresRestantes) {
		this.lblTotalCaracteresRestantes = lblTotalCaracteresRestantes;
	}
}
