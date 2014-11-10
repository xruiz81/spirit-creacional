package com.spirit.nomina.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.ReportModelImpl;

public abstract class JPConsultaRubro extends ReportModelImpl {
	public JPConsultaRubro() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblTipoRol = new JLabel();
		cmbTipoRol = new JComboBox();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblRubro = new JLabel();
		txtRubro = new JTextField();
		btnBuscarRubro = new JButton();
		lblModo = new JLabel();
		chbEstricto = new JCheckBox();
		lblReporteFondoReserva = new JLabel();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		chbFondoReservaGeneral = new JCheckBox();
		gfsRubros = compFactory.createSeparator("Datos Espec\u00edficos: ");
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblContrato = new JLabel();
		txtContrato = new JTextField();
		btnBuscarContrato = new JButton();
		lblEstadoDetalle = new JLabel();
		cmbEstadoDetalle = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		btnConsultar = new JButton();
		lblTotal = new JLabel();
		lblValorTotal = new JLabel();
		panelRubros = new JPanel();
		spRubro = new JScrollPane();
		tblRubro = new JTable();
		spRubroImpuestoRenta = new JScrollPane();
		tblImpuestoRenta = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Consulta de Rubros");
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
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(45)),
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
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo Rol: ");
		add(lblTipoRol, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRol, cc.xywh(5, 3, 9, 1));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo Contrato: ");
		add(lblTipoContrato, cc.xy(3, 5));
		add(cmbTipoContrato, cc.xywh(5, 5, 9, 1));

		//---- lblRubro ----
		lblRubro.setText("Rubro: ");
		add(lblRubro, cc.xy(3, 7));

		//---- txtRubro ----
		txtRubro.setEditable(false);
		add(txtRubro, cc.xywh(5, 7, 11, 1));

		//---- btnBuscarRubro ----
		btnBuscarRubro.setText("B");
		add(btnBuscarRubro, cc.xy(17, 7));

		//---- lblModo ----
		lblModo.setText("Modo:");
		add(lblModo, cc.xywh(21, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- chbEstricto ----
		chbEstricto.setText("Estricto");
		add(chbEstricto, cc.xy(23, 7));

		//---- lblReporteFondoReserva ----
		lblReporteFondoReserva.setText("Reporte: ");
		add(lblReporteFondoReserva, cc.xywh(21, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha  Inicio: ");
		add(lblFechaInicio, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 11, 5, 1));
		add(cmbFechaFin, cc.xywh(15, 11, 7, 1));

		//---- chbFondoReservaGeneral ----
		chbFondoReservaGeneral.setText("General");
		add(chbFondoReservaGeneral, cc.xy(23, 9));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(13, 11));
		add(gfsRubros, cc.xywh(3, 13, 25, 1));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado: ");
		add(lblEmpleado, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 15, 11, 1));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xy(17, 15));

		//---- lblContrato ----
		lblContrato.setText("Contrato: ");
		add(lblContrato, cc.xy(3, 17));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xywh(5, 17, 11, 1));

		//---- btnBuscarContrato ----
		btnBuscarContrato.setText("B");
		add(btnBuscarContrato, cc.xy(17, 17));

		//---- lblEstadoDetalle ----
		lblEstadoDetalle.setText("Estado: ");
		add(lblEstadoDetalle, cc.xy(3, 19));
		add(cmbEstadoDetalle, cc.xywh(5, 19, 5, 1));

		//---- lblObservacion ----
		lblObservacion.setText("Observaci\u00f3n: ");
		add(lblObservacion, cc.xy(3, 21));

		//---- txtObservacion ----
		txtObservacion.setEditable(false);
		txtObservacion.setEnabled(true);
		add(txtObservacion, cc.xywh(5, 21, 5, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(5, 23, 3, 1));

		//---- lblTotal ----
		lblTotal.setText("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblTotal, cc.xywh(21, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblValorTotal ----
		lblValorTotal.setText("0.00");
		add(lblValorTotal, cc.xywh(23, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== panelRubros ========
		{
			panelRubros.setLayout(new FormLayout(
				"default:grow",
				"default:grow"));

			//======== spRubro ========
			{

				//---- tblRubro ----
				tblRubro.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null},
					},
					new String[] {
						"A\u00f1o", "Mes", "Empleado", "Valor", "Tipo Rubro", "Estado"
					}
				) {
					Class[] columnTypes = new Class[] {
						Integer.class, Object.class, Object.class, Double.class, Object.class, Object.class
					};
					boolean[] columnEditable = new boolean[] {
						false, false, false, false, false, false
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
				spRubro.setViewportView(tblRubro);
			}
			panelRubros.add(spRubro, cc.xy(1, 1));
		}
		add(panelRubros, cc.xywh(3, 27, 25, 1));

		//======== spRubroImpuestoRenta ========
		{

			//---- tblImpuestoRenta ----
			tblImpuestoRenta.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"A\u00f1o", "Mes", "Empleado", "Sueldo", "IESS", "Gastos", "Base Imponible", "Imp. Fracci\u00f3n B\u00e1sica", "Porcentaje", "Total"
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
			spRubroImpuestoRenta.setViewportView(tblImpuestoRenta);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoRol;
	private JComboBox cmbTipoRol;
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblRubro;
	private JTextField txtRubro;
	private JButton btnBuscarRubro;
	private JLabel lblModo;
	private JCheckBox chbEstricto;
	private JLabel lblReporteFondoReserva;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JCheckBox chbFondoReservaGeneral;
	private JComponent gfsRubros;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblContrato;
	private JTextField txtContrato;
	private JButton btnBuscarContrato;
	private JLabel lblEstadoDetalle;
	private JComboBox cmbEstadoDetalle;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JButton btnConsultar;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	private JPanel panelRubros;
	private JScrollPane spRubro;
	private JTable tblRubro;
	private JScrollPane spRubroImpuestoRenta;
	private JTable tblImpuestoRenta;
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

	public JLabel getLblRubro() {
		return lblRubro;
	}

	public JTextField getTxtRubro() {
		return txtRubro;
	}

	public JButton getBtnBuscarRubro() {
		return btnBuscarRubro;
	}

	public JLabel getLblModo() {
		return lblModo;
	}

	public JCheckBox getChbEstricto() {
		return chbEstricto;
	}

	public JLabel getLblReporteFondoReserva() {
		return lblReporteFondoReserva;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JCheckBox getChbFondoReservaGeneral() {
		return chbFondoReservaGeneral;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
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

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public JLabel getLblValorTotal() {
		return lblValorTotal;
	}

	public JPanel getPanelRubros() {
		return panelRubros;
	}

	public JScrollPane getSpRubro() {
		return spRubro;
	}

	public JTable getTblRubro() {
		return tblRubro;
	}

	public JScrollPane getSpRubroImpuestoRenta() {
		return spRubroImpuestoRenta;
	}

	public JTable getTblImpuestoRenta() {
		return tblImpuestoRenta;
	}
}
