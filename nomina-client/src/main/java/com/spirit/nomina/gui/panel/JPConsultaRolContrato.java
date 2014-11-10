package com.spirit.nomina.gui.panel;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPConsultaRolContrato extends MantenimientoModelImpl {
	public JPConsultaRolContrato() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblTipoRol = new JLabel();
		cmbTipoRol = new JComboBox();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblContrato = new JLabel();
		txtContrato = new JTextField();
		btnBuscarContrato = new JButton();
		lblMes = new JLabel();
		cmbMes = new JComboBox();
		lblAnio = new JLabel();
		cmbAnio = new JComboBox();
		lblEstadoDetalle = new JLabel();
		cmbEstadoDetalle = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		btnConsultar = new JButton();
		gfsOpcionesImpresion = compFactory.createSeparator("Opciones de Impresi\u00f3n");
		panel1 = new JPanel();
		lblImpresionContrato = new JLabel();
		rbImpresionContinua = new JRadioButton();
		rbImpresionPorPagina = new JRadioButton();
		scpRolPagoContrato = new JScrollPane();
		tblRolPagoContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Consulta Rol Por Contrato");
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
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo Rol:");
		add(lblTipoRol, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRol, cc.xywh(5, 3, 5, 1));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo de Contrato:");
		add(lblTipoContrato, cc.xy(3, 5));
		add(cmbTipoContrato, cc.xywh(5, 5, 5, 1));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 7, 7, 1));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xy(13, 7));

		//---- lblContrato ----
		lblContrato.setText("Contrato:");
		add(lblContrato, cc.xy(3, 9));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xywh(5, 9, 3, 1));

		//---- btnBuscarContrato ----
		btnBuscarContrato.setText("B");
		add(btnBuscarContrato, cc.xywh(9, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblMes ----
		lblMes.setText("Mes: ");
		add(lblMes, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

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
		add(cmbMes, cc.xywh(5, 11, 3, 1));

		//---- lblAnio ----
		lblAnio.setText("A\u00f1o:");
		add(lblAnio, cc.xy(3, 13));

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
			"2019",
			"2020",
			"2021",
			"2022",
			"2023",
			"2024"
		}));
		add(cmbAnio, cc.xy(5, 13));

		//---- lblEstadoDetalle ----
		lblEstadoDetalle.setText("Estado:");
		add(lblEstadoDetalle, cc.xy(3, 15));
		add(cmbEstadoDetalle, cc.xywh(5, 15, 3, 1));

		//---- lblObservacion ----
		lblObservacion.setText("Observaci\u00f3n:");
		add(lblObservacion, cc.xy(3, 17));

		//---- txtObservacion ----
		txtObservacion.setEditable(false);
		txtObservacion.setEnabled(true);
		add(txtObservacion, cc.xywh(5, 17, 5, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(5, 19, 3, 1));
		add(gfsOpcionesImpresion, cc.xywh(3, 21, 17, 1));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- lblImpresionContrato ----
			lblImpresionContrato.setText("Contratos: ");
			panel1.add(lblImpresionContrato, cc.xy(1, 1));

			//---- rbImpresionContinua ----
			rbImpresionContinua.setText("Continuos");
			rbImpresionContinua.setSelected(true);
			panel1.add(rbImpresionContinua, cc.xy(3, 1));

			//---- rbImpresionPorPagina ----
			rbImpresionPorPagina.setText("Por p\u00e1gina");
			panel1.add(rbImpresionPorPagina, cc.xy(5, 1));
		}
		add(panel1, cc.xywh(3, 23, 17, 1));

		//======== scpRolPagoContrato ========
		{

			//---- tblRolPagoContrato ----
			tblRolPagoContrato.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, ""},
				},
				new String[] {
					"Rubro", "Valor", "Eventual", "Estado", "Observaci\u00f3n"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Double.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
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
			scpRolPagoContrato.setViewportView(tblRolPagoContrato);
		}
		add(scpRolPagoContrato, cc.xywh(3, 29, 17, 3));

		//---- buttonGroupImpresion ----
		ButtonGroup buttonGroupImpresion = new ButtonGroup();
		buttonGroupImpresion.add(rbImpresionContinua);
		buttonGroupImpresion.add(rbImpresionPorPagina);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoRol;
	private JComboBox cmbTipoRol;
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblContrato;
	private JTextField txtContrato;
	private JButton btnBuscarContrato;
	private JLabel lblMes;
	private JComboBox cmbMes;
	private JLabel lblAnio;
	private JComboBox cmbAnio;
	private JLabel lblEstadoDetalle;
	private JComboBox cmbEstadoDetalle;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JButton btnConsultar;
	private JComponent gfsOpcionesImpresion;
	private JPanel panel1;
	private JLabel lblImpresionContrato;
	private JRadioButton rbImpresionContinua;
	private JRadioButton rbImpresionPorPagina;
	private JScrollPane scpRolPagoContrato;
	private JTable tblRolPagoContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblTipoRol() {
		return lblTipoRol;
	}

	public JComboBox getCmbTipoRol() {
		return cmbTipoRol;
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

	public JLabel getLblEstadoDetalle() {
		return lblEstadoDetalle;
	}

	public JComboBox getCmbEstadoDetalle() {
		return cmbEstadoDetalle;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JComponent getGfsOpcionesImpresion() {
		return gfsOpcionesImpresion;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JLabel getLblImpresionContrato() {
		return lblImpresionContrato;
	}

	public JRadioButton getRbImpresionContinua() {
		return rbImpresionContinua;
	}

	public JRadioButton getRbImpresionPorPagina() {
		return rbImpresionPorPagina;
	}

	public JScrollPane getScpRolPagoContrato() {
		return scpRolPagoContrato;
	}

	public JTable getTblRolPagoContrato() {
		return tblRolPagoContrato;
	}
}
