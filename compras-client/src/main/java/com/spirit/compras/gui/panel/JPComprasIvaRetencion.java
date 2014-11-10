package com.spirit.compras.gui.panel;

import java.awt.Font;
import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
import com.spirit.client.model.SpiritModelImpl;


public abstract class JPComprasIvaRetencion extends SpiritModelImpl {
	public JPComprasIvaRetencion() {
		initComponents();
		setName("Compras por IVA y Retenciones");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		cbTodos = new JCheckBox();
		btnConsultar = new JButton();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		ckbNotasCredito = new JCheckBox();
		btnConsultarConsolidado = new JButton();
		lblRetencionNumero2 = new JLabel();
		cmbEstado = new JComboBox();
		lblRetencionNumero = new JLabel();
		txtRetencionNumero = new JTextField();
		btnRetencionNumero = new JButton();
		cbOrdenarPorProveedor = new JCheckBox();
		lblCodRenta = new JLabel();
		cbCodigoRenta = new JComboBox();
		lblCodIva = new JLabel();
		cbCodigoIVA = new JComboBox();
		cbOrdenarPorSecuencialRetencion = new JCheckBox();
		spTblCompras = new JScrollPane();
		tblCompras = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX8),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX8),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX7),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX8)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY8),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY8)
			}));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtProveedor, cc.xywh(5, 3, 7, 1));
		add(btnProveedor, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(17, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(19, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("F. Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("F. Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 5));

		//---- btnConsultarConsolidado ----
		btnConsultarConsolidado.setText("Consultar Consolidado");
		add(btnConsultarConsolidado, cc.xy(19, 5));

		//---- lblRetencionNumero2 ----
		lblRetencionNumero2.setText("Ret.:");
		add(lblRetencionNumero2, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODAS",
			"(A) Anuladas",
			"Retencion Especifica......"
		}));
		add(cmbEstado, cc.xy(5, 7));

		//---- lblRetencionNumero ----
		lblRetencionNumero.setText("# Ret.:");
		add(lblRetencionNumero, cc.xy(9, 7));
		add(txtRetencionNumero, cc.xy(11, 7));

		//---- btnRetencionNumero ----
		btnRetencionNumero.setToolTipText("B\u00fasqueda de # Retenci\u00f3n");
		add(btnRetencionNumero, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- ckbNotasCredito ----
		ckbNotasCredito.setText("Mostrar Notas de Cr\u00e9dito");
		add(ckbNotasCredito, cc.xy(17, 7));

		//---- lblCodRenta ----
		lblCodRenta.setText("C\u00f3d. RENTA:");
		add(lblCodRenta, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cbCodigoRenta, cc.xywh(5, 9, 7, 1));

		//---- cbOrdenarPorProveedor ----
		cbOrdenarPorProveedor.setText("Ordenar por Proveedor");
		add(cbOrdenarPorProveedor, cc.xywh(17, 9, 3, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblCodIva ----
		lblCodIva.setText("C\u00f3d. IVA:");
		add(lblCodIva, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cbCodigoIVA, cc.xywh(5, 11, 7, 1));

		//---- cbOrdenarPorSecuencialRetencion ----
		cbOrdenarPorSecuencialRetencion.setText("Ordenar por Secuencial Retenci\u00f3n");
		add(cbOrdenarPorSecuencialRetencion, cc.xywh(17, 11, 4, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== spTblCompras ========
		{

			//---- tblCompras ----
			tblCompras.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null, null, null, null, "", null, null, null, null, null},
				},
				new String[] {
					"# Factura", "Fecha Emisi\u00f3n", "Proveedor", "A", "Exterior", "Reembolso", "Reposici\u00f3n", "Normal", "Base IVA 12%", "Base IVA 0%", "IVA", "TOTAL US$", "Imp.", "C\u00f3digo", "%", "Valor Ret.", "# Retenci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblCompras.setViewportView(tblCompras);
		}
		add(spTblCompras, cc.xywh(3, 13, 19, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JCheckBox cbTodos;
	private JButton btnConsultar;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JCheckBox ckbNotasCredito;
	private JButton btnConsultarConsolidado;
	private JLabel lblRetencionNumero2;
	private JComboBox cmbEstado;
	private JLabel lblRetencionNumero;
	private JTextField txtRetencionNumero;
	private JButton btnRetencionNumero;
	private JCheckBox cbOrdenarPorProveedor;
	private JLabel lblCodRenta;
	private JComboBox cbCodigoRenta;
	private JLabel lblCodIva;
	private JComboBox cbCodigoIVA;
	private JCheckBox cbOrdenarPorSecuencialRetencion;
	private JScrollPane spTblCompras;
	private JTable tblCompras;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(JLabel lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public void setBtnProveedor(JButton btnProveedor) {
		this.btnProveedor = btnProveedor;
	}

	public JCheckBox getCbOrdenarPorProveedor() {
		return cbOrdenarPorProveedor;
	}

	public void setCbOrdenarPorProveedor(JCheckBox cbOrdenarPorProveedor) {
		this.cbOrdenarPorProveedor = cbOrdenarPorProveedor;
	}

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}

	public JCheckBox getCbOrdenarPorSecuencialRetencion() {
		return cbOrdenarPorSecuencialRetencion;
	}

	public void setCbOrdenarPorSecuencialRetencion(
			JCheckBox cbOrdenarPorSecuencialRetencion) {
		this.cbOrdenarPorSecuencialRetencion = cbOrdenarPorSecuencialRetencion;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public JScrollPane getSpTblCompras() {
		return spTblCompras;
	}

	public void setSpTblCompras(JScrollPane spTblCompras) {
		this.spTblCompras = spTblCompras;
	}

	public JTable getTblCompras() {
		return tblCompras;
	}

	public void setTblCompras(JTable tblCompras) {
		this.tblCompras = tblCompras;
	}

	public JComboBox getCbCodigoRenta() {
		return cbCodigoRenta;
	}

	public void setCbCodigoRenta(JComboBox cbCodigoRenta) {
		this.cbCodigoRenta = cbCodigoRenta;
	}

	public JLabel getLblCodRenta() {
		return lblCodRenta;
	}

	public void setLblCodRenta(JLabel lblCodRenta) {
		this.lblCodRenta = lblCodRenta;
	}

	public JComboBox getCbCodigoIVA() {
		return cbCodigoIVA;
	}

	public void setCbCodigoIVA(JComboBox cbCodigoIVA) {
		this.cbCodigoIVA = cbCodigoIVA;
	}

	public JLabel getLblCodIva() {
		return lblCodIva;
	}

	public void setLblCodIva(JLabel lblCodIva) {
		this.lblCodIva = lblCodIva;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JLabel getLblRetencionNumero2() {
		return lblRetencionNumero2;
	}

	public void setLblRetencionNumero2(JLabel lblRetencionNumero2) {
		this.lblRetencionNumero2 = lblRetencionNumero2;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JCheckBox getCkbNotasCredito() {
		return ckbNotasCredito;
	}

	public void setCkbNotasCredito(JCheckBox ckbNotasCredito) {
		this.ckbNotasCredito = ckbNotasCredito;
	}

	public JLabel getLblRetencionNumero() {
		return lblRetencionNumero;
	}

	public void setLblRetencionNumero(JLabel lblRetencionNumero) {
		this.lblRetencionNumero = lblRetencionNumero;
	}

	public JButton getBtnConsultarConsolidado() {
		return btnConsultarConsolidado;
	}

	public void setBtnConsultarConsolidado(JButton btnConsultarConsolidado) {
		this.btnConsultarConsolidado = btnConsultarConsolidado;
	}

	public JTextField getTxtRetencionNumero() {
		return txtRetencionNumero;
	}

	public void setTxtRetencionNumero(JTextField txtRetencionNumero) {
		this.txtRetencionNumero = txtRetencionNumero;
	}

	public JButton getBtnRetencionNumero() {
		return btnRetencionNumero;
	}

	public void setBtnRetencionNumero(JButton btnRetencionNumero) {
		this.btnRetencionNumero = btnRetencionNumero;
	}
	
}
