package com.spirit.nomina.gui.panel;
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
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPRolPago extends MantenimientoModelImpl {
	public JPRolPago() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblTipoRol = new JLabel();
		cmbTipoRol = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblMes = new JLabel();
		cmbMes = new JComboBox();
		btnFiltrar = new JButton();
		lblAnio = new JLabel();
		cmbAnio = new JComboBox();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		cbReporteCostes = new JCheckBox();
		lblRubroEventual = new JLabel();
		txtEstado = new JTextField();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		btnGenerarReporte = new JButton();
		btnCerrarRol = new JButton();
		scpTablaRolPago = new JScrollPane();
		tblRolPago = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Generacion de Rol Pago");
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
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.CENTER, Sizes.dluX(25), FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo Rol:");
		add(lblTipoRol, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRol, cc.xywh(5, 3, 7, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio: ");
		add(lblFechaInicio, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(17, 3, 3, 1));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo de Contrato:");
		add(lblTipoContrato, cc.xy(3, 5));
		add(cmbTipoContrato, cc.xywh(5, 5, 7, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(17, 5, 3, 1));

		//---- lblMes ----
		lblMes.setText("Mes: ");
		add(lblMes, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

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
		add(cmbMes, cc.xywh(5, 7, 3, 1));

		//---- btnFiltrar ----
		btnFiltrar.setText("Filtrar");
		add(btnFiltrar, cc.xy(19, 7));

		//---- lblAnio ----
		lblAnio.setText("A\u00f1o:");
		add(lblAnio, cc.xy(3, 9));

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
		add(cmbAnio, cc.xy(5, 9));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(15, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xywh(17, 9, 3, 1));

		//---- cbReporteCostes ----
		cbReporteCostes.setText("Reporte de Costes");
		add(cbReporteCostes, cc.xy(21, 9));

		//---- lblRubroEventual ----
		lblRubroEventual.setText("Estado:");
		add(lblRubroEventual, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEstado ----
		txtEstado.setEditable(false);
		add(txtEstado, cc.xywh(5, 11, 3, 1));

		//---- lblDepartamento ----
		lblDepartamento.setText("Departamento:");
		add(lblDepartamento, cc.xywh(15, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDepartamento, cc.xywh(17, 11, 3, 1));

		//---- btnGenerarReporte ----
		btnGenerarReporte.setText("GENERAR ROL");
		add(btnGenerarReporte, cc.xywh(5, 15, 3, 1));

		//---- btnCerrarRol ----
		btnCerrarRol.setText("CERRAR ROL");
		add(btnCerrarRol, cc.xywh(11, 15, 3, 1));

		//======== scpTablaRolPago ========
		{

			//---- tblRolPago ----
			tblRolPago.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"Tipo de Rol", "Tipo de Contrato", "Mes", "A\u00f1o", "Estado"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Integer.class, String.class
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
			scpTablaRolPago.setViewportView(tblRolPago);
		}
		add(scpTablaRolPago, cc.xywh(3, 19, 21, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblTipoRol;
	private JComboBox cmbTipoRol;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblMes;
	private JComboBox cmbMes;
	private JButton btnFiltrar;
	private JLabel lblAnio;
	private JComboBox cmbAnio;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JCheckBox cbReporteCostes;
	private JLabel lblRubroEventual;
	private JTextField txtEstado;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JButton btnGenerarReporte;
	private JButton btnCerrarRol;
	private JScrollPane scpTablaRolPago;
	private JTable tblRolPago;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblTipoRol() {
		return lblTipoRol;
	}

	public JComboBox getCmbTipoRol() {
		return cmbTipoRol;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JLabel getLblMes() {
		return lblMes;
	}

	public JComboBox getCmbMes() {
		return cmbMes;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public JComboBox getCmbAnio() {
		return cmbAnio;
	}

	public JLabel getLblRubroEventual() {
		return lblRubroEventual;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public JButton getBtnCerrarRol() {
		return btnCerrarRol;
	}

	public JScrollPane getScpTablaRolPago() {
		return scpTablaRolPago;
	}

	public JTable getTblRolPago() {
		return tblRolPago;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JCheckBox getCbReporteCostes() {
		return cbReporteCostes;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}
}
