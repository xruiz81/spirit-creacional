package com.spirit.medios.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Fri Nov 23 12:31:23 COT 2012
 */
import com.spirit.client.model.SpiritModelImpl;


/**
 * @author xruiz
 */
public abstract class JPTimetracker2 extends SpiritModelImpl {
	public JPTimetracker2() {
		initComponents();
		setName("Timetracker2");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		cbTodosEmpleados = new JCheckBox();
		cbReportePorCliente = new JCheckBox();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbTodosClientes = new JCheckBox();
		cbReportePorEmpleado = new JCheckBox();
		lblTiempoDesignado = new JLabel();
		txtTiempoDesignado = new JTextField();
		lblPorcentaje = new JLabel();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblMesAnio = new JLabel();
		cmbMesAnio = new DateComboBox();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		btnConsultar = new JButton();
		cbTransformarHorasDecimal = new JCheckBox();
		spRangoAprobacion = compFactory.createSeparator("Rango de Aprobaci\u00f3n");
		cbVerFechasAprobacion = new JCheckBox();
		lblDiaInicio = new JLabel();
		cmbDiaInicio = new DateComboBox();
		lblDiaFin = new JLabel();
		cmbDiaFin = new DateComboBox();
		spTblTiempos = new JScrollPane();
		tblTiempos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
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
				new RowSpec(Sizes.DLUY3),
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
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 3, 9, 1));
		add(btnBuscarEmpleado, cc.xywh(15, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosEmpleados ----
		cbTodosEmpleados.setText("Todos");
		add(cbTodosEmpleados, cc.xy(19, 3));

		//---- cbReportePorCliente ----
		cbReportePorCliente.setText("Reporte por Cliente");
		add(cbReportePorCliente, cc.xy(23, 3));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 5, 9, 1));
		add(btnBuscarCliente, cc.xywh(15, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosClientes ----
		cbTodosClientes.setText("Todos");
		add(cbTodosClientes, cc.xy(19, 5));

		//---- cbReportePorEmpleado ----
		cbReportePorEmpleado.setText("Reporte por Empleado");
		add(cbReportePorEmpleado, cc.xy(23, 5));

		//---- lblTiempoDesignado ----
		lblTiempoDesignado.setText("Tiempo designado:");
		add(lblTiempoDesignado, cc.xy(5, 7));

		//---- txtTiempoDesignado ----
		txtTiempoDesignado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtTiempoDesignado, cc.xy(7, 7));

		//---- lblPorcentaje ----
		lblPorcentaje.setText("%");
		add(lblPorcentaje, cc.xy(9, 7));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xywh(5, 9, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblMesAnio ----
		lblMesAnio.setText("Mes/A\u00f1o:");
		add(lblMesAnio, cc.xywh(21, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMesAnio, cc.xy(23, 9));

		//---- lblDepartamento ----
		lblDepartamento.setText("Departamento:");
		add(lblDepartamento, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDepartamento, cc.xywh(5, 11, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(23, 11));

		//---- cbTransformarHorasDecimal ----
		cbTransformarHorasDecimal.setText("Transformar horas a decimal");
		add(cbTransformarHorasDecimal, cc.xy(25, 11));
		add(spRangoAprobacion, cc.xywh(3, 15, 13, 1));

		//---- cbVerFechasAprobacion ----
		cbVerFechasAprobacion.setText("Ver");
		add(cbVerFechasAprobacion, cc.xy(19, 15));

		//---- lblDiaInicio ----
		lblDiaInicio.setText("Inicio:");
		add(lblDiaInicio, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDiaInicio, cc.xywh(5, 17, 3, 1));

		//---- lblDiaFin ----
		lblDiaFin.setText("Fin:");
		add(lblDiaFin, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDiaFin, cc.xywh(11, 17, 3, 1));

		//======== spTblTiempos ========
		{

			//---- tblTiempos ----
			tblTiempos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null, null, null, "", null, null, null, null, null, "", null, null, null, null, null, null, null, null, null, null, null, "", null, null, null, null},
				},
				new String[] {
					"ACTIVIDADES / CLIENTES", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "TOTAL"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTiempos.setViewportView(tblTiempos);
		}
		add(spTblTiempos, cc.xywh(3, 21, 23, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JCheckBox cbTodosEmpleados;
	private JCheckBox cbReportePorCliente;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbTodosClientes;
	private JCheckBox cbReportePorEmpleado;
	private JLabel lblTiempoDesignado;
	private JTextField txtTiempoDesignado;
	private JLabel lblPorcentaje;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblMesAnio;
	private DateComboBox cmbMesAnio;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JButton btnConsultar;
	private JCheckBox cbTransformarHorasDecimal;
	private JComponent spRangoAprobacion;
	private JCheckBox cbVerFechasAprobacion;
	private JLabel lblDiaInicio;
	private DateComboBox cmbDiaInicio;
	private JLabel lblDiaFin;
	private DateComboBox cmbDiaFin;
	private JScrollPane spTblTiempos;
	private JTable tblTiempos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtTiempoDesignado() {
		return txtTiempoDesignado;
	}

	public JCheckBox getCbTransformarHorasDecimal() {
		return cbTransformarHorasDecimal;
	}

	public DateComboBox getCmbDiaInicio() {
		return cmbDiaInicio;
	}

	public DateComboBox getCmbDiaFin() {
		return cmbDiaFin;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public JLabel getLblMesAnio() {
		return lblMesAnio;
	}

	public DateComboBox getCmbMesAnio() {
		return cmbMesAnio;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblTiempos() {
		return spTblTiempos;
	}

	public JTable getTblTiempos() {
		return tblTiempos;
	}

	public JCheckBox getCbReportePorCliente() {
		return cbReportePorCliente;
	}

	public JLabel getLblDiaInicio() {
		return lblDiaInicio;
	}

	public JLabel getLblDiaFin() {
		return lblDiaFin;
	}

	public JCheckBox getCbReportePorEmpleado() {
		return cbReportePorEmpleado;
	}

	public JCheckBox getCbTodosEmpleados() {
		return cbTodosEmpleados;
	}

	public JCheckBox getCbTodosClientes() {
		return cbTodosClientes;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JLabel getLblDepartamento() {
		return lblDepartamento;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public JComponent getSpRangoAprobacion() {
		return spRangoAprobacion;
	}

	public JCheckBox getCbVerFechasAprobacion() {
		return cbVerFechasAprobacion;
	}
}
