package com.spirit.facturacion.gui.panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.SpiritModelImpl;


public abstract class JPFacturacionIvaRetencion extends SpiritModelImpl {
	public JPFacturacionIvaRetencion() {
		initComponents();
		setName("Facturacion por IVA y Retenciones");
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		spTblFacturacion = new JScrollPane();
		tblFacturacion = new JTable();
		btnConsultar = new JButton();
		cbPreimpreso = new JCheckBox();
		lblRetRenta = new JLabel();
		cmbRetencionesRenta = new JComboBox();
		lblRentIva = new JLabel();
		cmbRetencionesIVA = new JComboBox();
		btnConsultarConsolidado = new JButton();
		cbCliente = new JCheckBox();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
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
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCliente, cc.xywh(5, 3, 7, 1));
		add(btnCliente, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(17, 3));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(23, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODAS",
			"(A) Anuladas"
		}));
		add(cmbEstado, cc.xywh(25, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 5));

		//======== spTblFacturacion ========
		{

			//---- tblFacturacion ----
			tblFacturacion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null, "", null, null, null, null, null, null, null},
				},
				new String[] {
					"# Factura", "Fecha Emisi\u00f3n", "Cliente", "A", "Exterior", "Reembolso", "Normal", "IVA", "TOTAL US$", "# Ret. Renta", "Renta 1%", "Renta 2%", "# Ret. IVA", "IVA 30%", "IVA 70%", "IVA 100%"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblFacturacion.setViewportView(tblFacturacion);
		}
		add(spTblFacturacion, cc.xywh(3, 11, 25, 5));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(17, 5));

		//---- cbPreimpreso ----
		cbPreimpreso.setText("Ordenar por # Factura");
		add(cbPreimpreso, cc.xywh(23, 5, 3, 1));

		//---- lblRetRenta ----
		lblRetRenta.setText("# Ret. Renta:");
		add(lblRetRenta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbRetencionesRenta ----
		cmbRetencionesRenta.setModel(new DefaultComboBoxModel(new String[] {
			"TODAS",
			"Recibidas",
			"Pendientes"
		}));
		add(cmbRetencionesRenta, cc.xywh(5, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblRentIva ----
		lblRentIva.setText("# Ret. IVA:");
		add(lblRentIva, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbRetencionesIVA ----
		cmbRetencionesIVA.setModel(new DefaultComboBoxModel(new String[] {
			"TODAS",
			"Recibidas",
			"Pendientes"
		}));
		add(cmbRetencionesIVA, cc.xywh(11, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- btnConsultarConsolidado ----
		btnConsultarConsolidado.setText("Consultar Consolidado");
		add(btnConsultarConsolidado, cc.xywh(17, 7, 3, 1));

		//---- cbCliente ----
		cbCliente.setText("Ordenar por Cliente");
		add(cbCliente, cc.xywh(23, 7, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JScrollPane spTblFacturacion;
	private JTable tblFacturacion;
	private JButton btnConsultar;
	private JCheckBox cbPreimpreso;
	private JLabel lblRetRenta;
	private JComboBox cmbRetencionesRenta;
	private JLabel lblRentIva;
	private JComboBox cmbRetencionesIVA;
	private JButton btnConsultarConsolidado;
	private JCheckBox cbCliente;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnCliente() {
		return btnCliente;
	}


	public void setBtnCliente(JButton btnCliente) {
		this.btnCliente = btnCliente;
	}


	public JButton getBtnConsultar() {
		return btnConsultar;
	}


	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}


	public JButton getBtnConsultarConsolidado() {
		return btnConsultarConsolidado;
	}


	public void setBtnConsultarConsolidado(JButton btnConsultarConsolidado) {
		this.btnConsultarConsolidado = btnConsultarConsolidado;
	}


	public JCheckBox getCbCliente() {
		return cbCliente;
	}


	public void setCbCliente(JCheckBox cbCliente) {
		this.cbCliente = cbCliente;
	}


	public JCheckBox getCbPreimpreso() {
		return cbPreimpreso;
	}


	public void setCbPreimpreso(JCheckBox cbPreimpreso) {
		this.cbPreimpreso = cbPreimpreso;
	}


	public JCheckBox getCbTodos() {
		return cbTodos;
	}


	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}


	public JComboBox getCmbEstado() {
		return cmbEstado;
	}


	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}


	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}


	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}


	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}


	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}


	public JComboBox getCmbRetencionesIVA() {
		return cmbRetencionesIVA;
	}


	public void setCmbRetencionesIVA(JComboBox cmbRetencionesIVA) {
		this.cmbRetencionesIVA = cmbRetencionesIVA;
	}


	public JComboBox getCmbRetencionesRenta() {
		return cmbRetencionesRenta;
	}


	public void setCmbRetencionesRenta(JComboBox cmbRetencionesRenta) {
		this.cmbRetencionesRenta = cmbRetencionesRenta;
	}


	public JLabel getLblCliente() {
		return lblCliente;
	}


	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}


	public JLabel getLblEstado() {
		return lblEstado;
	}


	public void setLblEstado(JLabel lblEstado) {
		this.lblEstado = lblEstado;
	}


	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}


	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}


	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}


	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}


	public JLabel getLblRentIva() {
		return lblRentIva;
	}


	public void setLblRentIva(JLabel lblRentIva) {
		this.lblRentIva = lblRentIva;
	}


	public JLabel getLblRetRenta() {
		return lblRetRenta;
	}


	public void setLblRetRenta(JLabel lblRetRenta) {
		this.lblRetRenta = lblRetRenta;
	}


	public JScrollPane getSpTblFacturacion() {
		return spTblFacturacion;
	}


	public void setSpTblFacturacion(JScrollPane spTblFacturacion) {
		this.spTblFacturacion = spTblFacturacion;
	}


	public JTable getTblFacturacion() {
		return tblFacturacion;
	}


	public void setTblFacturacion(JTable tblFacturacion) {
		this.tblFacturacion = tblFacturacion;
	}


	public JTextField getTxtCliente() {
		return txtCliente;
	}


	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}
	
	
}
