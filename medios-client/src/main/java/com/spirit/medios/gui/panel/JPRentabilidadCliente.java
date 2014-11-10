package com.spirit.medios.gui.panel;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.DateComboBox;
/*
 * Created by JFormDesigner on Tue Oct 15 17:48:49 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;


/**
 * @author xruiz
 */
public abstract class JPRentabilidadCliente extends SpiritModelImpl {
	public JPRentabilidadCliente() {
		initComponents();
		setName("Rentabilidad por Cliente");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnCorporacion = new JButton();
		btnLimpiarDatosCliente = new JButton();
		cbDesglosarClienteOficina = new JCheckBox();
		btnCliente = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		lblPresupuestosOpciones = new JLabel();
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		btnClienteOficina = new JButton();
		lblEstadoPresupuesto = new JLabel();
		cmbEstadoPresupuesto = new JComboBox();
		lblMesInicio = new JLabel();
		cmbMesInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbMesFin = new DateComboBox();
		cbEstadosAprobadosFacturados = new JCheckBox();
		btnConsultar = new JButton();
		spTblRentabilidad = new JScrollPane();
		tblRentabilidad = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCorporacion ----
		lblCorporacion.setText("Corporaci\u00f3n:");
		add(lblCorporacion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCorporacion, cc.xywh(5, 3, 8, 1));
		add(btnCorporacion, cc.xywh(13, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(btnLimpiarDatosCliente, cc.xywh(15, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbDesglosarClienteOficina ----
		cbDesglosarClienteOficina.setText("Desglosar por Cliente Oficina");
		add(cbDesglosarClienteOficina, cc.xywh(19, 3, 5, 1));
		add(btnCliente, cc.xywh(13, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(true);
		add(txtCliente, cc.xywh(5, 5, 8, 1));

		//---- lblPresupuestosOpciones ----
		lblPresupuestosOpciones.setText("Presupuestos:");
		add(lblPresupuestosOpciones, cc.xywh(19, 5, 3, 1));

		//---- lblClienteOficina ----
		lblClienteOficina.setText("Cliente Oficina:");
		add(lblClienteOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtClienteOficina ----
		txtClienteOficina.setEditable(true);
		add(txtClienteOficina, cc.xywh(5, 7, 8, 1));
		add(btnClienteOficina, cc.xywh(13, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblEstadoPresupuesto ----
		lblEstadoPresupuesto.setText("Estado:");
		add(lblEstadoPresupuesto, cc.xy(19, 7));

		//---- cmbEstadoPresupuesto ----
		cmbEstadoPresupuesto.setModel(new DefaultComboBoxModel(new String[] {
			"TODOS",
			"COTIZADO",
			"APROBADO",
			"FACTURADO",
			"PREPAGADO"
		}));
		add(cmbEstadoPresupuesto, cc.xy(21, 7));

		//---- lblMesInicio ----
		lblMesInicio.setText("Mes Inicio:");
		add(lblMesInicio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMesInicio, cc.xy(5, 9));

		//---- lblFechaFin ----
		lblFechaFin.setText("Mes Fin:");
		add(lblFechaFin, cc.xy(9, 9));
		add(cmbMesFin, cc.xy(11, 9));

		//---- cbEstadosAprobadosFacturados ----
		cbEstadosAprobadosFacturados.setText("Aprobados y Facturados");
		add(cbEstadosAprobadosFacturados, cc.xywh(19, 9, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(5, 11));

		//======== spTblRentabilidad ========
		{
			spTblRentabilidad.setPreferredSize(new Dimension(452, 100));

			//---- tblRentabilidad ----
			tblRentabilidad.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null, null, null, ""},
				},
				new String[] {
					" ", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Total"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRentabilidad.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblRentabilidad.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblRentabilidad.setAutoCreateColumnsFromModel(true);
			spTblRentabilidad.setViewportView(tblRentabilidad);
		}
		add(spTblRentabilidad, cc.xywh(3, 15, 21, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnCorporacion;
	private JButton btnLimpiarDatosCliente;
	private JCheckBox cbDesglosarClienteOficina;
	private JButton btnCliente;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JLabel lblPresupuestosOpciones;
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JButton btnClienteOficina;
	private JLabel lblEstadoPresupuesto;
	private JComboBox cmbEstadoPresupuesto;
	private JLabel lblMesInicio;
	private DateComboBox cmbMesInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbMesFin;
	private JCheckBox cbEstadosAprobadosFacturados;
	private JButton btnConsultar;
	private JScrollPane spTblRentabilidad;
	private JTable tblRentabilidad;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public JCheckBox getCbDesglosarClienteOficina() {
		return cbDesglosarClienteOficina;
	}

	public JComboBox getCmbEstadoPresupuesto() {
		return cmbEstadoPresupuesto;
	}

	public JCheckBox getCbEstadosAprobadosFacturados() {
		return cbEstadosAprobadosFacturados;
	}

	public JButton getBtnCorporacion() {
		return btnCorporacion;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public JButton getBtnClienteOficina() {
		return btnClienteOficina;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblRentabilidad() {
		return spTblRentabilidad;
	}

	public JTable getTblRentabilidad() {
		return tblRentabilidad;
	}

	public JButton getBtnLimpiarDatosCliente() {
		return btnLimpiarDatosCliente;
	}

	public DateComboBox getCmbMesInicio() {
		return cmbMesInicio;
	}

	public DateComboBox getCmbMesFin() {
		return cmbMesFin;
	}
}
