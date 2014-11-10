package com.spirit.medios.gui.panel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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


public abstract class JPControlTrafico extends SpiritModelImpl {
	public JPControlTrafico() {
		initComponents();
		setName("Control de Trafico");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		cbFechaCreacion = new JCheckBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		cbFechaEntrega = new JCheckBox();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblTipoOrden = new JLabel();
		cmbTipoOrden = new JComboBox();
		lblEqCuentas = new JLabel();
		cmbEqCuentas = new JComboBox();
		spTblOrdenes = new JScrollPane();
		tblOrdenes = new JTable();
		cbOrdenTrabajo = new JCheckBox();
		lblEjecutivo = new JLabel();
		cmbEjecutivo = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		btnConsultar = new JButton();
		lblResponsable = new JLabel();
		cmbResponsable = new JComboBox();
		cbVerDetallesCuentas = new JCheckBox();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX4),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX4)
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
				new RowSpec(Sizes.DLUY8),
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
		add(txtCliente, cc.xywh(5, 3, 9, 1));
		add(btnCliente, cc.xywh(15, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(17, 3));

		//---- cbFechaCreacion ----
		cbFechaCreacion.setText("Ordenar por Fecha de Creaci\u00f3n");
		add(cbFechaCreacion, cc.xy(21, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 5, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(11, 5, 3, 1));

		//---- cbFechaEntrega ----
		cbFechaEntrega.setText("Ordenar por Fecha de Entrega");
		add(cbFechaEntrega, cc.xy(21, 5));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xy(5, 7));

		//---- lblTipoOrden ----
		lblTipoOrden.setText("Tipo:");
		add(lblTipoOrden, cc.xywh(7, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoOrden, cc.xywh(9, 7, 3, 1));

		//---- lblEqCuentas ----
		lblEqCuentas.setText("Eq.Cuenta:");
		add(lblEqCuentas, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEqCuentas, cc.xy(15, 7));

		//======== spTblOrdenes ========
		{

			//---- tblOrdenes ----
			tblOrdenes.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Cliente", "Trabajo", "SubTipo", "Responsable", "Eq.Cuent.", "Ejecutivo(a)", "F.Creaci\u00f3n", "F.Limite", "F.Entrega"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblOrdenes.setViewportView(tblOrdenes);
		}
		add(spTblOrdenes, cc.xywh(3, 15, 21, 5));

		//---- cbOrdenTrabajo ----
		cbOrdenTrabajo.setText("Ordenar por Orden de Trabajo");
		add(cbOrdenTrabajo, cc.xy(21, 7));

		//---- lblEjecutivo ----
		lblEjecutivo.setText("Ejecutivo(a):");
		add(lblEjecutivo, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEjecutivo, cc.xywh(5, 9, 7, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEstado, cc.xy(15, 9));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(21, 9));

		//---- lblResponsable ----
		lblResponsable.setText("Responsable:");
		add(lblResponsable, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbResponsable, cc.xywh(5, 11, 7, 1));

		//---- cbVerDetallesCuentas ----
		cbVerDetallesCuentas.setText("Ver detalles de Cuentas");
		add(cbVerDetallesCuentas, cc.xywh(13, 11, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JCheckBox cbFechaCreacion;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JCheckBox cbFechaEntrega;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblTipoOrden;
	private JComboBox cmbTipoOrden;
	private JLabel lblEqCuentas;
	private JComboBox cmbEqCuentas;
	private JScrollPane spTblOrdenes;
	private JTable tblOrdenes;
	private JCheckBox cbOrdenTrabajo;
	private JLabel lblEjecutivo;
	private JComboBox cmbEjecutivo;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JButton btnConsultar;
	private JLabel lblResponsable;
	private JComboBox cmbResponsable;
	private JCheckBox cbVerDetallesCuentas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEqCuentas() {
		return cmbEqCuentas;
	}

	public JCheckBox getCbVerDetallesCuentas() {
		return cbVerDetallesCuentas;
	}

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

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
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

	public JScrollPane getSpTblOrdenes() {
		return spTblOrdenes;
	}

	public void setSpTblOrdenes(JScrollPane spTblOrdenes) {
		this.spTblOrdenes = spTblOrdenes;
	}

	public JTable getTblOrdenes() {
		return tblOrdenes;
	}

	public void setTblOrdenes(JTable tblOrdenes) {
		this.tblOrdenes = tblOrdenes;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JCheckBox getCbFechaCreacion() {
		return cbFechaCreacion;
	}

	public void setCbFechaCreacion(JCheckBox cbFechaCreacion) {
		this.cbFechaCreacion = cbFechaCreacion;
	}

	public JCheckBox getCbFechaEntrega() {
		return cbFechaEntrega;
	}

	public void setCbFechaEntrega(JCheckBox cbFechaEntrega) {
		this.cbFechaEntrega = cbFechaEntrega;
	}

	public JComboBox getCmbEjecutivo() {
		return cmbEjecutivo;
	}

	public void setCmbEjecutivo(JComboBox cmbEjecutivo) {
		this.cmbEjecutivo = cmbEjecutivo;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JComboBox getCmbTipoOrden() {
		return cmbTipoOrden;
	}

	public void setCmbTipoOrden(JComboBox cmbTipoOrden) {
		this.cmbTipoOrden = cmbTipoOrden;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JCheckBox getCbOrdenTrabajo() {
		return cbOrdenTrabajo;
	}

	public void setCbOrdenTrabajo(JCheckBox cbOrdenTrabajo) {
		this.cbOrdenTrabajo = cbOrdenTrabajo;
	}

	public JComboBox getCmbResponsable() {
		return cmbResponsable;
	}

	public void setCmbResponsable(JComboBox cmbResponsable) {
		this.cmbResponsable = cmbResponsable;
	}

	public JLabel getLblResponsable() {
		return lblResponsable;
	}
}
