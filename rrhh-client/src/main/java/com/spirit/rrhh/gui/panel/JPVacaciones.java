package com.spirit.rrhh.gui.panel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPVacaciones extends SpiritModelImpl {
	public JPVacaciones() {
		initComponents();
		setName("Vacaciones");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpVacaciones = new JideTabbedPane();
		panelVacaciones = new JPanel();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnEmpleado = new JButton();
		lblFechaIngreso = new JLabel();
		txtFechaIngreso = new JTextField();
		lblTotalDiasGenerados = new JLabel();
		txtTotalDiasGenerados = new JTextField();
		lblTotalDiasDisfrutados = new JLabel();
		txtTotalDiasDisfrutados = new JTextField();
		lblDiasDisponibles = new JLabel();
		txtDiasDisponibles = new JTextField();
		lblPeriodoVacaciones = new JLabel();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblDiasDisfrutados = new JLabel();
		txtDiasDisfrutados = new JTextField();
		lblObservacion = new JLabel();
		spTxtObservacion = new JScrollPane();
		txtObservacion = new JTextArea();
		lblArchivo = new JLabel();
		txtArchivo = new JTextField();
		btnArchivo = new JButton();
		btnVisualizarArchivo = new JButton();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spTblVacaciones = new JScrollPane();
		tblVacaciones = new JTable();
		panelReporte = new JPanel();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		btnConsultar = new JButton();
		cbPorFechas = new JCheckBox();
		cmbFechaInicioReporte = new DateComboBox();
		cmbFechaFinReporte = new DateComboBox();
		spTblVacacionesReporte = new JScrollPane();
		tblVacacionesReporte = new JTable();
		tblReporteFechas = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Hoja de Valores");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpVacaciones ========
		{

			//======== panelVacaciones ========
			{
				panelVacaciones.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(20)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW),
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(22)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY6),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblEmpleado ----
				lblEmpleado.setText("Empleado: ");
				panelVacaciones.add(lblEmpleado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtEmpleado ----
				txtEmpleado.setEditable(false);
				panelVacaciones.add(txtEmpleado, cc.xywh(5, 3, 5, 1));
				panelVacaciones.add(btnEmpleado, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

				//---- lblFechaIngreso ----
				lblFechaIngreso.setText("Fecha de Ingreso:");
				panelVacaciones.add(lblFechaIngreso, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtFechaIngreso ----
				txtFechaIngreso.setEditable(false);
				txtFechaIngreso.setHorizontalAlignment(SwingConstants.CENTER);
				panelVacaciones.add(txtFechaIngreso, cc.xy(5, 5));

				//---- lblTotalDiasGenerados ----
				lblTotalDiasGenerados.setText("Total de D\u00edas generados:");
				panelVacaciones.add(lblTotalDiasGenerados, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtTotalDiasGenerados ----
				txtTotalDiasGenerados.setEditable(false);
				txtTotalDiasGenerados.setHorizontalAlignment(SwingConstants.CENTER);
				panelVacaciones.add(txtTotalDiasGenerados, cc.xywh(11, 5, 3, 1));

				//---- lblTotalDiasDisfrutados ----
				lblTotalDiasDisfrutados.setText("Total de D\u00edas disfrutados:");
				panelVacaciones.add(lblTotalDiasDisfrutados, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtTotalDiasDisfrutados ----
				txtTotalDiasDisfrutados.setEditable(false);
				txtTotalDiasDisfrutados.setHorizontalAlignment(SwingConstants.CENTER);
				panelVacaciones.add(txtTotalDiasDisfrutados, cc.xywh(11, 7, 3, 1));

				//---- lblDiasDisponibles ----
				lblDiasDisponibles.setText("D\u00edas disponibles:");
				panelVacaciones.add(lblDiasDisponibles, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtDiasDisponibles ----
				txtDiasDisponibles.setEditable(false);
				txtDiasDisponibles.setHorizontalAlignment(SwingConstants.CENTER);
				panelVacaciones.add(txtDiasDisponibles, cc.xywh(11, 9, 3, 1));

				//---- lblPeriodoVacaciones ----
				lblPeriodoVacaciones.setText("Per\u00edodo de Vacaciones:");
				panelVacaciones.add(lblPeriodoVacaciones, cc.xywh(3, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//---- lblFechaInicio ----
				lblFechaInicio.setText("Fecha Inicio:");
				panelVacaciones.add(lblFechaInicio, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelVacaciones.add(cmbFechaInicio, cc.xy(5, 13));

				//---- lblFechaFin ----
				lblFechaFin.setText("Fecha Fin:");
				panelVacaciones.add(lblFechaFin, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelVacaciones.add(cmbFechaFin, cc.xywh(11, 13, 5, 1));

				//---- lblDiasDisfrutados ----
				lblDiasDisfrutados.setText("D\u00edas disfrutados:");
				panelVacaciones.add(lblDiasDisfrutados, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtDiasDisfrutados ----
				txtDiasDisfrutados.setEditable(false);
				txtDiasDisfrutados.setHorizontalAlignment(SwingConstants.CENTER);
				panelVacaciones.add(txtDiasDisfrutados, cc.xy(5, 15));

				//---- lblObservacion ----
				lblObservacion.setText("Observaci\u00f3n:");
				panelVacaciones.add(lblObservacion, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//======== spTxtObservacion ========
				{
					spTxtObservacion.setViewportView(txtObservacion);
				}
				panelVacaciones.add(spTxtObservacion, cc.xywh(5, 17, 11, 3));

				//---- lblArchivo ----
				lblArchivo.setText("Archivo:");
				panelVacaciones.add(lblArchivo, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtArchivo ----
				txtArchivo.setEditable(false);
				panelVacaciones.add(txtArchivo, cc.xywh(5, 21, 11, 1));
				panelVacaciones.add(btnArchivo, cc.xywh(17, 21, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				panelVacaciones.add(btnVisualizarArchivo, cc.xywh(19, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

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

					//---- btnAgregarDetalle ----
					btnAgregarDetalle.setText("A");
					panel1.add(btnAgregarDetalle, cc.xy(1, 1));

					//---- btnActualizarDetalle ----
					btnActualizarDetalle.setText("U");
					panel1.add(btnActualizarDetalle, cc.xy(3, 1));

					//---- btnEliminarDetalle ----
					btnEliminarDetalle.setText("D");
					panel1.add(btnEliminarDetalle, cc.xy(5, 1));
				}
				panelVacaciones.add(panel1, cc.xywh(3, 25, 3, 1));

				//======== spTblVacaciones ========
				{

					//---- tblVacaciones ----
					tblVacaciones.setPreferredScrollableViewportSize(new Dimension(450, 140));
					tblVacaciones.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, "", null, null, null, "", null},
							},
							new String[] {
								"D\u00edas disponibles", "Fechas disfrutadas", "D\u00edas disfrutados", "Saldo de d\u00edas", "L-V", "S-D", "Observaci\u00f3n", "Archivo"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false, false, false
							};
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
					spTblVacaciones.setViewportView(tblVacaciones);
				}
				panelVacaciones.add(spTblVacaciones, cc.xywh(3, 27, 19, 3));
			}
			jtpVacaciones.addTab("Vacaciones", panelVacaciones);


			//======== panelReporte ========
			{
				panelReporte.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(130)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(130)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW),
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
						new RowSpec(Sizes.DLUY6),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblOficina ----
				lblOficina.setText("Oficina:");
				panelReporte.add(lblOficina, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReporte.add(cmbOficina, cc.xy(5, 3));

				//---- lblDepartamento ----
				lblDepartamento.setText("Departamento:");
				panelReporte.add(lblDepartamento, cc.xy(3, 5));
				panelReporte.add(cmbDepartamento, cc.xy(5, 5));

				//---- btnConsultar ----
				btnConsultar.setText("Consultar");
				panelReporte.add(btnConsultar, cc.xy(11, 5));

				//---- cbPorFechas ----
				cbPorFechas.setText("Por Fechas:");
				panelReporte.add(cbPorFechas, cc.xy(3, 7));
				panelReporte.add(cmbFechaInicioReporte, cc.xy(5, 7));
				panelReporte.add(cmbFechaFinReporte, cc.xy(9, 7));

				//======== spTblVacacionesReporte ========
				{

					//---- tblVacacionesReporte ----
					tblVacacionesReporte.setPreferredScrollableViewportSize(new Dimension(450, 140));
					tblVacacionesReporte.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null, null},
						},
						new String[] {
							"Empleado", "Fecha Ingreso", "Oficina", "Departamento", "Total de d\u00edas", "D\u00edas disfrutados", "Saldo de d\u00edas"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, true, false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblVacacionesReporte.setViewportView(tblVacacionesReporte);
				}
				panelReporte.add(spTblVacacionesReporte, cc.xywh(3, 11, 13, 3));
			}
			jtpVacaciones.addTab("Reportes", panelReporte);

		}
		add(jtpVacaciones, cc.xy(1, 1));

		//---- tblReporteFechas ----
		tblReporteFechas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, ""},
			},
			new String[] {
				"Empleado", "Oficina", "Departamento", "Fecha Inicio", "Fecha Fin", "D\u00edas disfrutados"
			}
		) {
			boolean[] columnEditable = new boolean[] {
				false, false, false, false, false, false
			};
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
		});
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpVacaciones;
	private JPanel panelVacaciones;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnEmpleado;
	private JLabel lblFechaIngreso;
	private JTextField txtFechaIngreso;
	private JLabel lblTotalDiasGenerados;
	private JTextField txtTotalDiasGenerados;
	private JLabel lblTotalDiasDisfrutados;
	private JTextField txtTotalDiasDisfrutados;
	private JLabel lblDiasDisponibles;
	private JTextField txtDiasDisponibles;
	private JLabel lblPeriodoVacaciones;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblDiasDisfrutados;
	private JTextField txtDiasDisfrutados;
	private JLabel lblObservacion;
	private JScrollPane spTxtObservacion;
	private JTextArea txtObservacion;
	private JLabel lblArchivo;
	private JTextField txtArchivo;
	private JButton btnArchivo;
	private JButton btnVisualizarArchivo;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spTblVacaciones;
	private JTable tblVacaciones;
	private JPanel panelReporte;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JButton btnConsultar;
	private JCheckBox cbPorFechas;
	private DateComboBox cmbFechaInicioReporte;
	private DateComboBox cmbFechaFinReporte;
	private JScrollPane spTblVacacionesReporte;
	private JTable tblVacacionesReporte;
	private JTable tblReporteFechas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtTotalDiasDisfrutados() {
		return txtTotalDiasDisfrutados;
	}

	public JTextField getTxtTotalDiasGenerados() {
		return txtTotalDiasGenerados;
	}

	public JCheckBox getCbPorFechas() {
		return cbPorFechas;
	}

	public DateComboBox getCmbFechaInicioReporte() {
		return cmbFechaInicioReporte;
	}

	public DateComboBox getCmbFechaFinReporte() {
		return cmbFechaFinReporte;
	}

	public JTable getTblReporteFechas() {
		return tblReporteFechas;
	}

	public JButton getBtnVisualizarArchivo() {
		return btnVisualizarArchivo;
	}

	public JTextField getTxtDiasDisponibles() {
		return txtDiasDisponibles;
	}

	public JTextField getTxtDiasDisfrutados() {
		return txtDiasDisfrutados;
	}

	public JideTabbedPane getJtpVacaciones() {
		return jtpVacaciones;
	}

	public JPanel getPanelVacaciones() {
		return panelVacaciones;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JTextField getTxtFechaIngreso() {
		return txtFechaIngreso;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JScrollPane getSpTxtObservacion() {
		return spTxtObservacion;
	}

	public JTextArea getTxtObservacion() {
		return txtObservacion;
	}

	public JTextField getTxtArchivo() {
		return txtArchivo;
	}

	public JButton getBtnArchivo() {
		return btnArchivo;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public JScrollPane getSpTblVacaciones() {
		return spTblVacaciones;
	}

	public JTable getTblVacaciones() {
		return tblVacaciones;
	}

	public JPanel getPanelReporte() {
		return panelReporte;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblVacacionesReporte() {
		return spTblVacacionesReporte;
	}

	public JTable getTblVacacionesReporte() {
		return tblVacacionesReporte;
	}
}
