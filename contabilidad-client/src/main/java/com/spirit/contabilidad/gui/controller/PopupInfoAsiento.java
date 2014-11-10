package com.spirit.contabilidad.gui.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.popup.JidePopup;
import com.spirit.client.MainFrameModel;
import com.spirit.client.model.SpiritModel;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.gui.model.AsientoModel;
import com.spirit.general.gui.controller.PanelHandler;

/**
 * @author xruiz
 */

public class PopupInfoAsiento extends JidePopup {
	
	private AsientoIf asientoIf;
	private static Map panels;
		
	public PopupInfoAsiento(String tipoAsiento, String subTipoAsiento, AsientoIf asiento, String referencia, String glosa) {
		initComponents();
		panels = MainFrameModel.get_panels();
		
		getTxtNumero().setText(asiento.getNumero()); 
		getTxtTipoAsiento().setText(tipoAsiento);
		getTxtSubTipoAsiento().setText(subTipoAsiento);
		getTxtObservaciones().setText(asiento.getObservacion());
		getTxtReferencia().setText(referencia);
		getTxtGlosa().setText(glosa);
		this.asientoIf = asiento;
		initListeners();
	}
	
	private void initListeners(){
		//Listener del comboPlanCuenta
		getBtnIrAsiento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				abrirAsiento(asientoIf);
			}
		});
	}
	
	private void abrirAsiento(AsientoIf asiento) {
		SpiritModel panelOrdenTrabajo = (SpiritModel) new AsientoModel(asiento);
		
		String si = "Si"; 
		String no = "No"; 
		Object[] options ={si,no};
		
		if (panels.size()>0 && panels.get("Asiento")!=null){
			int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Asiento?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
			if (opcion == JOptionPane.YES_OPTION) {
				MainFrameModel.get_dockingManager().removeFrame("Asiento");
				DockableFrame panel = PanelHandler.createPanelesApp(panelOrdenTrabajo);
				MainFrameModel.get_dockingManager().addFrame(panel);
				MainFrameModel.get_dockingManager().showFrame(panel.getName());	
			}
		}else{
			DockableFrame panel = PanelHandler.createPanelesApp(panelOrdenTrabajo);
			MainFrameModel.get_dockingManager().addFrame(panel);
			MainFrameModel.get_dockingManager().showFrame(panel.getName());	
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		contentPane = new JPanel();
		label2 = new JLabel();
		goodiesFormsSeparator = compFactory.createSeparator("");
		panelForm = new JPanel();
		lblAsiento = new JLabel();
		lblNumero = new JLabel();
		txtNumero = new JTextField();
		lblTipoAsiento = new JLabel();
		txtTipoAsiento = new JTextField();
		lblSubTipoAsiento = new JLabel();
		txtSubTipoAsiento = new JTextField();
		label1 = new JLabel();
		txtObservaciones = new JTextField();
		btnIrAsiento = new JButton();
		panelForm2 = new JPanel();
		txtReferencia = new JTextField();
		txtGlosa = new JTextField();
		lblCuenta = new JLabel();
		lblReferencia = new JLabel();
		lblGlosa = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setResizable(false);
		setMovable(true);
		setLayout(new FormLayout(
			"default:grow",
			"default, fill:default:grow"));

		//======== contentPane ========
		{
			contentPane.setLayout(new FormLayout(
				"default:grow",
				"fill:pref, default, default, default, default"));

			//---- label2 ----
			label2.setText("Informaci\u00f3n");
			contentPane.add(label2, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			contentPane.add(goodiesFormsSeparator, cc.xywh(1, 2, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));

			//======== panelForm ========
			{
				panelForm.setBorder(Borders.DIALOG_BORDER);
				panelForm.setLayout(new FormLayout(
					"default, 50dlu, 120dlu, default",
					"default, default, default, default, default, fill:pref:grow"));

				//---- lblAsiento ----
				lblAsiento.setText("ASIENTO");
				lblAsiento.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelForm.add(lblAsiento, cc.xy(2, 1));

				//---- lblNumero ----
				lblNumero.setText("N\u00famero:");
				panelForm.add(lblNumero, cc.xywh(2, 2, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelForm.add(txtNumero, cc.xy(3, 2));

				//---- lblTipoAsiento ----
				lblTipoAsiento.setText("Tipo Asiento:");
				panelForm.add(lblTipoAsiento, cc.xywh(2, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelForm.add(txtTipoAsiento, cc.xy(3, 3));

				//---- lblSubTipoAsiento ----
				lblSubTipoAsiento.setText("Sub-Tipo Asiento:");
				panelForm.add(lblSubTipoAsiento, cc.xywh(2, 4, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelForm.add(txtSubTipoAsiento, cc.xy(3, 4));

				//---- label1 ----
				label1.setText("Observaciones:");
				panelForm.add(label1, cc.xywh(2, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelForm.add(txtObservaciones, cc.xy(3, 5));

				//---- btnIrAsiento ----
				btnIrAsiento.setText("Ir");
				panelForm.add(btnIrAsiento, cc.xywh(3, 6, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			contentPane.add(panelForm, cc.xy(1, 3));

			//======== panelForm2 ========
			{
				panelForm2.setBorder(Borders.DIALOG_BORDER);
				panelForm2.setLayout(new FormLayout(
					"default, 50dlu, 120dlu, default",
					"default, default, default, fill:pref:grow"));
				panelForm2.add(txtReferencia, cc.xy(3, 2));
				panelForm2.add(txtGlosa, cc.xy(3, 3));

				//---- lblCuenta ----
				lblCuenta.setText("CUENTA");
				lblCuenta.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelForm2.add(lblCuenta, cc.xy(2, 1));

				//---- lblReferencia ----
				lblReferencia.setText("Referencia:");
				panelForm2.add(lblReferencia, cc.xy(2, 2));

				//---- lblGlosa ----
				lblGlosa.setText("Glosa:");
				panelForm2.add(lblGlosa, cc.xy(2, 3));
			}
			contentPane.add(panelForm2, cc.xy(1, 5));
		}
		add(contentPane, cc.xy(1, 2));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel contentPane;
	private JLabel label2;
	private JComponent goodiesFormsSeparator;
	private JPanel panelForm;
	private JLabel lblAsiento;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblTipoAsiento;
	private JTextField txtTipoAsiento;
	private JLabel lblSubTipoAsiento;
	private JTextField txtSubTipoAsiento;
	private JLabel label1;
	private JTextField txtObservaciones;
	private JButton btnIrAsiento;
	private JPanel panelForm2;
	private JTextField txtReferencia;
	private JTextField txtGlosa;
	private JLabel lblCuenta;
	private JLabel lblReferencia;
	private JLabel lblGlosa;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtGlosa() {
		return txtGlosa;
	}

	public void setTxtGlosa(JTextField txtGlosa) {
		this.txtGlosa = txtGlosa;
	}

	public JTextField getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(JTextField txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextField getTxtSubTipoAsiento() {
		return txtSubTipoAsiento;
	}

	public void setTxtSubTipoAsiento(JTextField txtSubTipoAsiento) {
		this.txtSubTipoAsiento = txtSubTipoAsiento;
	}

	public JTextField getTxtTipoAsiento() {
		return txtTipoAsiento;
	}

	public void setTxtTipoAsiento(JTextField txtTipoAsiento) {
		this.txtTipoAsiento = txtTipoAsiento;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(JTextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public JButton getBtnIrAsiento() {
		return btnIrAsiento;
	}

	public void setBtnIrAsiento(JButton btnIrAsiento) {
		this.btnIrAsiento = btnIrAsiento;
	}
}
