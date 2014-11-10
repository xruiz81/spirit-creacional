package com.spirit.facturacion.gui.panel;

import javax.swing.DefaultComboBoxModel;
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

public abstract class JPFacturacionTipoProducto extends SpiritModelImpl {
	public JPFacturacionTipoProducto() {
		initComponents();
		setName("Facturacion por Tipo de Producto");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		lblIVA = new JLabel();
		cmbIVA = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		cbSinNotasCredito = new JCheckBox();
		cbUsarNombreComercial = new JCheckBox();
		lblTipoDocumento = new JLabel();
		spTblFacturacion = new JScrollPane();
		tblFacturacion = new JTable();
		cmbTipoDocumento = new JComboBox();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		cbComisiones = new JCheckBox();
		btnConsultar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX6),
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
				new ColumnSpec(Sizes.DLUX8),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6)
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
				new RowSpec(Sizes.dluY(10)),
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

		//---- lblIVA ----
		lblIVA.setText("IVA:");
		add(lblIVA, cc.xywh(21, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbIVA ----
		cmbIVA.setModel(new DefaultComboBoxModel(new String[] {
			"CON IVA",
			"SIN IVA"
		}));
		add(cmbIVA, cc.xy(23, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 5));

		//---- cbSinNotasCredito ----
		cbSinNotasCredito.setText("Sin Notas de Cr\u00e9dito");
		add(cbSinNotasCredito, cc.xy(17, 5));

		//---- cbUsarNombreComercial ----
		cbUsarNombreComercial.setText("Usar Nombre Comercial");
		add(cbUsarNombreComercial, cc.xywh(21, 5, 5, 1));

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo Documento:");
		add(lblTipoDocumento, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== spTblFacturacion ========
		{

			//---- tblFacturacion ----
			tblFacturacion.setModel(new DefaultTableModel(
				new Object[][] {
					{"", null, null, null, null, null, "", "", null, null, null, null},
				},
				new String[] {
					"Cliente", "Televisi\u00f3n", "Prensa", "Radio", "Revista", "P. Interna", "P. Externa", "Vallas", "FEE", "Comision D.", "Otros", "TOTAL US$"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblFacturacion.setViewportView(tblFacturacion);
		}
		add(spTblFacturacion, cc.xywh(3, 11, 25, 5));
		add(cmbTipoDocumento, cc.xywh(5, 7, 3, 1));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xywh(11, 7, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

		//---- cbComisiones ----
		cbComisiones.setText("Comisiones");
		add(cbComisiones, cc.xy(17, 7));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(21, 7, 3, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JLabel lblIVA;
	private JComboBox cmbIVA;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JCheckBox cbSinNotasCredito;
	private JCheckBox cbUsarNombreComercial;
	private JLabel lblTipoDocumento;
	private JScrollPane spTblFacturacion;
	private JTable tblFacturacion;
	private JComboBox cmbTipoDocumento;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JCheckBox cbComisiones;
	private JButton btnConsultar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbComisiones() {
		return cbComisiones;
	}

	public void setCbComisiones(JCheckBox cbComisiones) {
		this.cbComisiones = cbComisiones;
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

	public JComboBox getCmbIVA() {
		return cmbIVA;
	}

	public void setCmbIVA(JComboBox cmbIVA) {
		this.cmbIVA = cmbIVA;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
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

	public JCheckBox getCbSinNotasCredito() {
		return cbSinNotasCredito;
	}

	public void setCbSinNotasCredito(JCheckBox cbSinNotasCredito) {
		this.cbSinNotasCredito = cbSinNotasCredito;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JCheckBox getCbUsarNombreComercial() {
		return cbUsarNombreComercial;
	}
}
