package com.spirit.contabilidad.gui.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.popup.JidePopup;
/*
 * Created by JFormDesigner on Wed Mar 28 15:48:00 COT 2007
 */
import com.spirit.client.MainFrameModel;
import com.spirit.client.model.SpiritModel;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.gui.model.AsientoModel;
import com.spirit.general.gui.controller.PanelHandler;

/**
 * @author xruiz
 */

public class PopupInfoAsientoDetalle extends JidePopup {
	
	private AsientoIf asientoIf;
	private static Map panels;
		
	public PopupInfoAsientoDetalle(AsientoIf asiento, String referencia, String glosa, String centroGasto, String empleado, String departamento, String linea, String cliente, String debe, String haber) {
		initComponents();
		panels = MainFrameModel.get_panels();
		
		getTxtNumeroAsiento().setText(asiento.getNumero());
		getTxtFecha().setText(asiento.getFecha().toString());
		getTxtReferencia().setText(referencia);
		getTxtGlosario().setText(glosa);
		getTxtCentroGasto().setText(centroGasto);
		getTxtEmpleado().setText(empleado);
		getTxtDepartamento().setText(departamento);
		getTxtLinea().setText(linea);
		getTxtCliente().setText(cliente);
		getTxtDebe().setText(debe);
		getTxtHaber().setText(haber);
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
		contentPane = new JPanel();
		lblDetalleDelAsiento = new JLabel();
		panelPopupInfoAsientoDetalle = new JPanel();
		lblNumeroAsiento = new JLabel();
		lblFecha = new JLabel();
		txtFecha = new JTextField();
		label1 = new JLabel();
		txtNumeroAsiento = new JTextField();
		lblGlosario = new JLabel();
		txtGlosario = new JTextField();
		lblCentroGasto = new JLabel();
		txtCentroGasto = new JTextField();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		lblDepartamento = new JLabel();
		txtDepartamento = new JTextField();
		lblLinea = new JLabel();
		txtLinea = new JTextField();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		lblDebe = new JLabel();
		txtReferencia = new JTextField();
		txtDebe = new JTextField();
		lblHaber = new JLabel();
		txtHaber = new JTextField();
		btnIrAsiento = new JButton();
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
				"fill:pref, 10dlu, default, 10dlu"));

			//---- lblDetalleDelAsiento ----
			lblDetalleDelAsiento.setText("Detalle del Asiento");
			lblDetalleDelAsiento.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
			contentPane.add(lblDetalleDelAsiento, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

			//======== panelPopupInfoAsientoDetalle ========
			{
				panelPopupInfoAsientoDetalle.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
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

				//---- lblNumeroAsiento ----
				lblNumeroAsiento.setText("# Asiento:");
				panelPopupInfoAsientoDetalle.add(lblNumeroAsiento, cc.xy(3, 1));

				//---- lblFecha ----
				lblFecha.setText("Fecha:");
				panelPopupInfoAsientoDetalle.add(lblFecha, cc.xy(11, 1));
				panelPopupInfoAsientoDetalle.add(txtFecha, cc.xy(13, 1));

				//---- label1 ----
				label1.setText("Referencia:");
				panelPopupInfoAsientoDetalle.add(label1, cc.xy(3, 3));
				panelPopupInfoAsientoDetalle.add(txtNumeroAsiento, cc.xywh(5, 1, 3, 1));

				//---- lblGlosario ----
				lblGlosario.setText("Glosario:");
				panelPopupInfoAsientoDetalle.add(lblGlosario, cc.xy(3, 5));
				panelPopupInfoAsientoDetalle.add(txtGlosario, cc.xywh(5, 5, 9, 1));

				//---- lblCentroGasto ----
				lblCentroGasto.setText("Centro de Gasto:");
				panelPopupInfoAsientoDetalle.add(lblCentroGasto, cc.xy(3, 7));
				panelPopupInfoAsientoDetalle.add(txtCentroGasto, cc.xywh(5, 7, 9, 1));

				//---- lblEmpleado ----
				lblEmpleado.setText("Empleado:");
				panelPopupInfoAsientoDetalle.add(lblEmpleado, cc.xy(3, 9));
				panelPopupInfoAsientoDetalle.add(txtEmpleado, cc.xywh(5, 9, 9, 1));

				//---- lblDepartamento ----
				lblDepartamento.setText("Departamento:");
				panelPopupInfoAsientoDetalle.add(lblDepartamento, cc.xy(3, 11));
				panelPopupInfoAsientoDetalle.add(txtDepartamento, cc.xywh(5, 11, 9, 1));

				//---- lblLinea ----
				lblLinea.setText("L\u00ednea:");
				panelPopupInfoAsientoDetalle.add(lblLinea, cc.xy(3, 13));
				panelPopupInfoAsientoDetalle.add(txtLinea, cc.xywh(5, 13, 9, 1));

				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				panelPopupInfoAsientoDetalle.add(lblCliente, cc.xy(3, 15));
				panelPopupInfoAsientoDetalle.add(txtCliente, cc.xywh(5, 15, 9, 1));

				//---- lblDebe ----
				lblDebe.setText("Debe:");
				panelPopupInfoAsientoDetalle.add(lblDebe, cc.xy(3, 17));
				panelPopupInfoAsientoDetalle.add(txtReferencia, cc.xywh(5, 3, 9, 1));
				panelPopupInfoAsientoDetalle.add(txtDebe, cc.xy(5, 17));

				//---- lblHaber ----
				lblHaber.setText("Haber:");
				panelPopupInfoAsientoDetalle.add(lblHaber, cc.xy(3, 19));
				panelPopupInfoAsientoDetalle.add(txtHaber, cc.xy(5, 19));

				//---- btnIrAsiento ----
				btnIrAsiento.setText("Ir");
				panelPopupInfoAsientoDetalle.add(btnIrAsiento, cc.xywh(7, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			contentPane.add(panelPopupInfoAsientoDetalle, cc.xy(1, 3));
		}
		add(contentPane, cc.xy(1, 2));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel contentPane;
	private JLabel lblDetalleDelAsiento;
	private JPanel panelPopupInfoAsientoDetalle;
	private JLabel lblNumeroAsiento;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel label1;
	private JTextField txtNumeroAsiento;
	private JLabel lblGlosario;
	private JTextField txtGlosario;
	private JLabel lblCentroGasto;
	private JTextField txtCentroGasto;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JLabel lblDepartamento;
	private JTextField txtDepartamento;
	private JLabel lblLinea;
	private JTextField txtLinea;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JLabel lblDebe;
	private JTextField txtReferencia;
	private JTextField txtDebe;
	private JLabel lblHaber;
	private JTextField txtHaber;
	private JButton btnIrAsiento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCentroGasto() {
		return txtCentroGasto;
	}

	public void setTxtCentroGasto(JTextField txtCentroGasto) {
		this.txtCentroGasto = txtCentroGasto;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtDebe() {
		return txtDebe;
	}

	public void setTxtDebe(JTextField txtDebe) {
		this.txtDebe = txtDebe;
	}

	public JTextField getTxtDepartamento() {
		return txtDepartamento;
	}

	public void setTxtDepartamento(JTextField txtDepartamento) {
		this.txtDepartamento = txtDepartamento;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public void setTxtEmpleado(JTextField txtEmpleado) {
		this.txtEmpleado = txtEmpleado;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtGlosario() {
		return txtGlosario;
	}

	public void setTxtGlosario(JTextField txtGlosario) {
		this.txtGlosario = txtGlosario;
	}

	public JTextField getTxtHaber() {
		return txtHaber;
	}

	public void setTxtHaber(JTextField txtHaber) {
		this.txtHaber = txtHaber;
	}

	public JTextField getTxtLinea() {
		return txtLinea;
	}

	public void setTxtLinea(JTextField txtLinea) {
		this.txtLinea = txtLinea;
	}

	public JTextField getTxtNumeroAsiento() {
		return txtNumeroAsiento;
	}

	public void setTxtNumeroAsiento(JTextField txtNumeroAsiento) {
		this.txtNumeroAsiento = txtNumeroAsiento;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JButton getBtnIrAsiento() {
		return btnIrAsiento;
	}

	public void setBtnIrAsiento(JButton btnIrAsiento) {
		this.btnIrAsiento = btnIrAsiento;
	}
}
