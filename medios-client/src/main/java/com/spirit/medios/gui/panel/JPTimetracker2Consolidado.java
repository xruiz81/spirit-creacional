package com.spirit.medios.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Mon Jan 21 15:27:51 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPTimetracker2Consolidado extends SpiritModelImpl {
	public JPTimetracker2Consolidado() {
		initComponents();
		setName("Timetracker2 Consolidado");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbTodosClientes = new JCheckBox();
		cbReporteEmpleado = new JCheckBox();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		cbTodosEmpleados = new JCheckBox();
		cbReporteCliente = new JCheckBox();
		lblFehaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		btnConsultar = new JButton();
		spTblTiempos = new JScrollPane();
		tblTiempos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY3),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY3),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY3)
			}));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 3, 7, 1));
		add(btnBuscarCliente, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosClientes ----
		cbTodosClientes.setText("Todos");
		add(cbTodosClientes, cc.xywh(17, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- cbReporteEmpleado ----
		cbReporteEmpleado.setText("Reporte por Empleado");
		add(cbReporteEmpleado, cc.xy(21, 3));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 5, 7, 1));
		add(btnBuscarEmpleado, cc.xywh(13, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosEmpleados ----
		cbTodosEmpleados.setText("Todos");
		add(cbTodosEmpleados, cc.xywh(17, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- cbReporteCliente ----
		cbReporteCliente.setText("Reporte por Cliente");
		add(cbReporteCliente, cc.xy(21, 5));

		//---- lblFehaInicio ----
		lblFehaInicio.setText("Fecha Inicio:");
		add(lblFehaInicio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 7));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xywh(11, 7, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(5, 9));

		//---- lblDepartamento ----
		lblDepartamento.setText("Departamento:");
		add(lblDepartamento, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDepartamento, cc.xywh(11, 9, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(21, 9));

		//======== spTblTiempos ========
		{

			//---- tblTiempos ----
			tblTiempos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null, null, null, "", null, null, null, null},
				},
				new String[] {
					"EMPLEADO / CLIENTE", "TIEMPO [%]", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE", "TOTAL"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTiempos.setViewportView(tblTiempos);
		}
		add(spTblTiempos, cc.xywh(3, 13, 21, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbTodosClientes;
	private JCheckBox cbReporteEmpleado;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JCheckBox cbTodosEmpleados;
	private JCheckBox cbReporteCliente;
	private JLabel lblFehaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JButton btnConsultar;
	private JScrollPane spTblTiempos;
	private JTable tblTiempos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public JCheckBox getCbTodosEmpleados() {
		return cbTodosEmpleados;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JScrollPane getSpTblTiempos() {
		return spTblTiempos;
	}

	public JTable getTblTiempos() {
		return tblTiempos;
	}

	public JCheckBox getCbTodosClientes() {
		return cbTodosClientes;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public JCheckBox getCbReporteCliente() {
		return cbReporteCliente;
	}

	public JCheckBox getCbReporteEmpleado() {
		return cbReporteEmpleado;
	}
}
