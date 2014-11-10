package com.spirit.nomina.gui.panel;
import java.awt.Font;

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
import com.spirit.client.model.ReportModelImpl;

public abstract class JPConsultaRubroEventual extends ReportModelImpl {
	public JPConsultaRubroEventual() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblRubro = new JLabel();
		txtRubro = new JTextField();
		btnBuscarRubro = new JButton();
		cbTodosRubros = new JCheckBox();
		lblMes = new JLabel();
		cmbMes = new JComboBox();
		lblAnio = new JLabel();
		cmbAnio = new JComboBox();
		label1 = new JLabel();
		cmbEstadoDetalle = new JComboBox();
		lblTipoRol = new JLabel();
		cmbTipoRolCobro = new JComboBox();
		cbTodoTipoRol = new JCheckBox();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		cbTodosEmpleados = new JCheckBox();
		lblContrato = new JLabel();
		txtContrato = new JTextField();
		btnBuscarContrato = new JButton();
		btnConsultar = new JButton();
		lblTotal = new JLabel();
		lblCantidadTotal = new JLabel();
		scpRolPagoContrato = new JScrollPane();
		tblRolPagoContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Consulta Rubros Eventuales");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.dluX(50), FormSpec.NO_GROW),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblRubro ----
		lblRubro.setText("Rubro:");
		add(lblRubro, cc.xy(3, 3));

		//---- txtRubro ----
		txtRubro.setEditable(false);
		add(txtRubro, cc.xywh(5, 3, 7, 1));

		//---- btnBuscarRubro ----
		btnBuscarRubro.setText("B");
		add(btnBuscarRubro, cc.xy(13, 3));

		//---- cbTodosRubros ----
		cbTodosRubros.setText("Todos");
		add(cbTodosRubros, cc.xy(15, 3));

		//---- lblMes ----
		lblMes.setText("Mes: ");
		add(lblMes, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbMes ----
		cmbMes.setEditable(false);
		cmbMes.setModel(new DefaultComboBoxModel(new String[] {
			"ENERO",
			"FEBRERO",
			"MARZO",
			"ABRIL",
			"MAYO",
			"JUNIO",
			"JULIO",
			"AGOSTO",
			"SEPTIEMBRE",
			"OCTUBRE",
			"NOVIEMBRE",
			"DICIEMBRE"
		}));
		add(cmbMes, cc.xywh(5, 5, 3, 1));

		//---- lblAnio ----
		lblAnio.setText("A\u00f1o:");
		add(lblAnio, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbAnio ----
		cmbAnio.setModel(new DefaultComboBoxModel(new String[] {
			"2009",
			"2010",
			"2011",
			"2012",
			"2013",
			"2014",
			"2015",
			"2016",
			"2017",
			"2018",
			"2019",
			"2020",
			"2021",
			"2022",
			"2023",
			"2024"
		}));
		add(cmbAnio, cc.xy(11, 5));

		//---- label1 ----
		label1.setText("Estado: ");
		add(label1, cc.xy(3, 7));
		add(cmbEstadoDetalle, cc.xywh(5, 7, 5, 1));

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo Rol Cobro:");
		add(lblTipoRol, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRolCobro, cc.xywh(5, 9, 5, 1));

		//---- cbTodoTipoRol ----
		cbTodoTipoRol.setText("Todos");
		add(cbTodoTipoRol, cc.xy(11, 9));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo de Contrato:");
		add(lblTipoContrato, cc.xy(3, 11));
		add(cmbTipoContrato, cc.xywh(5, 11, 7, 1));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 13, 7, 1));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xy(13, 13));

		//---- cbTodosEmpleados ----
		cbTodosEmpleados.setText("Todos");
		add(cbTodosEmpleados, cc.xy(15, 13));

		//---- lblContrato ----
		lblContrato.setText("Contrato:");
		add(lblContrato, cc.xy(3, 15));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xywh(5, 15, 5, 1));

		//---- btnBuscarContrato ----
		btnBuscarContrato.setText("B");
		add(btnBuscarContrato, cc.xywh(11, 15, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(5, 19, 3, 1));

		//---- lblTotal ----
		lblTotal.setText("Total: ");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblTotal, cc.xy(15, 21));

		//---- lblCantidadTotal ----
		lblCantidadTotal.setText("text");
		add(lblCantidadTotal, cc.xy(17, 21));

		//======== scpRolPagoContrato ========
		{

			//---- tblRolPagoContrato ----
			tblRolPagoContrato.setModel(new DefaultTableModel(
				new Object[][] {
					{null, "", null, null, null, null, ""},
				},
				new String[] {
					"Rubro", "Empleado", "Valor", "Estado", "Fecha Cobro", "Tipo Rol Cobro", "Observaci\u00f3n"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Double.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false
				};
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPagoContrato.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scpRolPagoContrato.setViewportView(tblRolPagoContrato);
		}
		add(scpRolPagoContrato, cc.xywh(3, 23, 17, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblRubro;
	private JTextField txtRubro;
	private JButton btnBuscarRubro;
	private JCheckBox cbTodosRubros;
	private JLabel lblMes;
	private JComboBox cmbMes;
	private JLabel lblAnio;
	private JComboBox cmbAnio;
	private JLabel label1;
	private JComboBox cmbEstadoDetalle;
	private JLabel lblTipoRol;
	private JComboBox cmbTipoRolCobro;
	private JCheckBox cbTodoTipoRol;
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JCheckBox cbTodosEmpleados;
	private JLabel lblContrato;
	private JTextField txtContrato;
	private JButton btnBuscarContrato;
	private JButton btnConsultar;
	private JLabel lblTotal;
	private JLabel lblCantidadTotal;
	private JScrollPane scpRolPagoContrato;
	private JTable tblRolPagoContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblRubro() {
		return lblRubro;
	}

	public JTextField getTxtRubro() {
		return txtRubro;
	}

	public JButton getBtnBuscarRubro() {
		return btnBuscarRubro;
	}

	public JLabel getLblMes() {
		return lblMes;
	}

	public JComboBox getCmbMes() {
		return cmbMes;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public JComboBox getCmbAnio() {
		return cmbAnio;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JComboBox getCmbEstadoDetalle() {
		return cmbEstadoDetalle;
	}

	public JLabel getLblTipoRol() {
		return lblTipoRol;
	}

	public JComboBox getCmbTipoRolCobro() {
		return cmbTipoRolCobro;
	}

	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
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

	public JLabel getLblContrato() {
		return lblContrato;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JButton getBtnBuscarContrato() {
		return btnBuscarContrato;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public JLabel getLblCantidadTotal() {
		return lblCantidadTotal;
	}

	public JScrollPane getScpRolPagoContrato() {
		return scpRolPagoContrato;
	}

	public JTable getTblRolPagoContrato() {
		return tblRolPagoContrato;
	}

	public JCheckBox getCbTodosRubros() {
		return cbTodosRubros;
	}

	public JCheckBox getCbTodoTipoRol() {
		return cbTodoTipoRol;
	}

	public JCheckBox getCbTodosEmpleados() {
		return cbTodosEmpleados;
	}
}
