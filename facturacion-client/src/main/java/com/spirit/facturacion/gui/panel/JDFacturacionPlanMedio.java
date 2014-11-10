package com.spirit.facturacion.gui.panel;

import java.awt.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Nov 23 15:25:14 COT 2010
 */
import com.jidesoft.combobox.DateComboBox;

/**
 * @author xruiz
 */
public class JDFacturacionPlanMedio extends JDialog {
	public JDFacturacionPlanMedio(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDFacturacionPlanMedio(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		dialogPane = new JPanel();
		contentPane = new JPanel();
		ldescripcion = new JLabel();
		rbCompleto = new JRadioButton();
		rbParcial = new JRadioButton();
		rbPorProveedor = new JRadioButton();
		cmbPorProveedor = new JComboBox();
		rbPorProductoComercial = new JRadioButton();
		cmbPorProductoComercial = new JComboBox();
		rbPorVersion = new JRadioButton();
		cmbPorVersion = new JComboBox();
		rbPorComisionMedio = new JRadioButton();
		cmbPorComisionMedio = new JComboBox();
		cbPeriodo = new JCheckBox();
		lbPeriodoFehaInicio = new JLabel();
		cmbPeridoInicio = new DateComboBox();
		lbPeriodoFechaFin = new JLabel();
		cmbPeriodoFin = new DateComboBox();
		btnAceptar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Forma de Facturaci\u00f3n");
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX3),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX3)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY3),
						new RowSpec(Sizes.dluY(10)),
						new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
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
						new RowSpec(Sizes.DLUY6),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));

				//---- ldescripcion ----
				ldescripcion.setText("Elija la forma de Facturaci\u00f3n:");
				contentPane.add(ldescripcion, cc.xywh(3, 2, 3, 1));

				//---- rbCompleto ----
				rbCompleto.setText("Completo");
				contentPane.add(rbCompleto, cc.xy(3, 4));

				//---- rbParcial ----
				rbParcial.setText("Parcial");
				contentPane.add(rbParcial, cc.xy(3, 6));

				//---- rbPorProveedor ----
				rbPorProveedor.setText("Por Proveedor");
				contentPane.add(rbPorProveedor, cc.xy(3, 8));
				contentPane.add(cmbPorProveedor, cc.xywh(5, 8, 7, 1));

				//---- rbPorProductoComercial ----
				rbPorProductoComercial.setText("Por Producto Cliente");
				contentPane.add(rbPorProductoComercial, cc.xy(3, 10));
				contentPane.add(cmbPorProductoComercial, cc.xywh(5, 10, 7, 1));

				//---- rbPorVersion ----
				rbPorVersion.setText("Por Comercial");
				contentPane.add(rbPorVersion, cc.xy(3, 12));
				contentPane.add(cmbPorVersion, cc.xywh(5, 12, 7, 1));

				//---- rbPorComisionMedio ----
				rbPorComisionMedio.setText("Comisi\u00f3n del Medio");
				contentPane.add(rbPorComisionMedio, cc.xy(3, 14));
				contentPane.add(cmbPorComisionMedio, cc.xywh(5, 14, 7, 1));

				//---- cbPeriodo ----
				cbPeriodo.setText("Periodo");
				contentPane.add(cbPeriodo, cc.xywh(3, 16, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//---- lbPeriodoFehaInicio ----
				lbPeriodoFehaInicio.setText("Fecha inicio:");
				contentPane.add(lbPeriodoFehaInicio, cc.xy(5, 16));
				contentPane.add(cmbPeridoInicio, cc.xy(7, 16));

				//---- lbPeriodoFechaFin ----
				lbPeriodoFechaFin.setText("Fecha fin:");
				contentPane.add(lbPeriodoFechaFin, cc.xy(9, 16));
				contentPane.add(cmbPeriodoFin, cc.xy(11, 16));

				//---- btnAceptar ----
				btnAceptar.setText("Aceptar");
				contentPane.add(btnAceptar, cc.xy(7, 20));
			}
			dialogPane.add(contentPane, BorderLayout.NORTH);
		}
		contentPane2.add(dialogPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());

		//---- bgFormasFacturacion ----
		ButtonGroup bgFormasFacturacion = new ButtonGroup();
		bgFormasFacturacion.add(rbCompleto);
		bgFormasFacturacion.add(rbParcial);
		bgFormasFacturacion.add(rbPorProveedor);
		bgFormasFacturacion.add(rbPorProductoComercial);
		bgFormasFacturacion.add(rbPorVersion);
		bgFormasFacturacion.add(rbPorComisionMedio);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel ldescripcion;
	private JRadioButton rbCompleto;
	private JRadioButton rbParcial;
	private JRadioButton rbPorProveedor;
	private JComboBox cmbPorProveedor;
	private JRadioButton rbPorProductoComercial;
	private JComboBox cmbPorProductoComercial;
	private JRadioButton rbPorVersion;
	private JComboBox cmbPorVersion;
	private JRadioButton rbPorComisionMedio;
	private JComboBox cmbPorComisionMedio;
	private JCheckBox cbPeriodo;
	private JLabel lbPeriodoFehaInicio;
	private DateComboBox cmbPeridoInicio;
	private JLabel lbPeriodoFechaFin;
	private DateComboBox cmbPeriodoFin;
	private JButton btnAceptar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JRadioButton getRbParcial() {
		return rbParcial;
	}

	public JPanel getDialogPane() {
		return dialogPane;
	}

	public JRadioButton getRbPorProveedor() {
		return rbPorProveedor;
	}

	public JRadioButton getRbPorProductoComercial() {
		return rbPorProductoComercial;
	}

	public JRadioButton getRbPorVersion() {
		return rbPorVersion;
	}

	public void setRbPorProveedor(JRadioButton rbPorProveedor) {
		this.rbPorProveedor = rbPorProveedor;
	}

	public void setRbPorProductoComercial(JRadioButton rbPorProductoComercial) {
		this.rbPorProductoComercial = rbPorProductoComercial;
	}

	public void setRbPorVersion(JRadioButton rbPorVersion) {
		this.rbPorVersion = rbPorVersion;
	}

	public JComboBox getCmbPorProveedor() {
		return cmbPorProveedor;
	}

	public JComboBox getCmbPorProductoComercial() {
		return cmbPorProductoComercial;
	}

	public JComboBox getCmbPorVersion() {
		return cmbPorVersion;
	}

	public JComboBox getCmbPorComisionMedio() {
		return cmbPorComisionMedio;
	}

	public void setCmbPorProveedor(JComboBox cmbPorProveedor) {
		this.cmbPorProveedor = cmbPorProveedor;
	}

	public void setCmbPorProductoComercial(JComboBox cmbPorProductoComercial) {
		this.cmbPorProductoComercial = cmbPorProductoComercial;
	}

	public void setCmbPorVersion(JComboBox cmbPorVersion) {
		this.cmbPorVersion = cmbPorVersion;
	}

	public void setCmbPorComisionMedio(JComboBox cmbPorComisionMedio) {
		this.cmbPorComisionMedio = cmbPorComisionMedio;
	}

	public void setDialogPane(JPanel dialogPane) {
		this.dialogPane = dialogPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JLabel getLdescripcion() {
		return ldescripcion;
	}

	public void setLdescripcion(JLabel ldescripcion) {
		this.ldescripcion = ldescripcion;
	}

	public JRadioButton getRbPorComisionMedio() {
		return rbPorComisionMedio;
	}

	public void setRbPorComisionMedio(JRadioButton rbPorComisionMedio) {
		this.rbPorComisionMedio = rbPorComisionMedio;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JRadioButton getRbCompleto() {
		return rbCompleto;
	}

	public void setRbCompleto(JRadioButton rbCompleto) {
		this.rbCompleto = rbCompleto;
	}

	public JCheckBox getCbPeriodo() {
		return cbPeriodo;
	}

	public void setCbPeriodo(JCheckBox cbPeriodo) {
		this.cbPeriodo = cbPeriodo;
	}

	public DateComboBox getCmbPeridoInicio() {
		return cmbPeridoInicio;
	}

	public void setCmbPeridoInicio(DateComboBox cmbPeridoInicio) {
		this.cmbPeridoInicio = cmbPeridoInicio;
	}

	public DateComboBox getCmbPeriodoFin() {
		return cmbPeriodoFin;
	}

	public void setCmbPeriodoFin(DateComboBox cmbPeriodoFin) {
		this.cmbPeriodoFin = cmbPeriodoFin;
	}	
}
