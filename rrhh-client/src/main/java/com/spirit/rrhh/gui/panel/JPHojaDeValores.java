package com.spirit.rrhh.gui.panel;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.ReportModelImpl;

public abstract class JPHojaDeValores extends ReportModelImpl {
	public JPHojaDeValores() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpHojaValores = new JideTabbedPane();
		spHistorialSueldos = new JScrollPane();
		panelDatosPersonales = new JPanel();
		btnImprimirSueldos = new JButton();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblEmpleado2 = new JLabel();
		txtContrato = new JTextField();
		btnBuscarContrato = new JButton();
		cbUnirContratos = new JCheckBox();
		spTblSueldos = new JScrollPane();
		tblSueldos = new JTable();
		spDeudas = new JScrollPane();
		panelDeudas = new JPanel();
		btnImprimirDeudas = new JButton();
		cbDeudaDetallada = new JCheckBox();
		spTblDeudas = new JScrollPane();
		tblDeudas = new JTable();
		spVacaciones = new JScrollPane();
		panelVacaciones = new JPanel();
		btnImprimirVac = new JButton();
		panelBotonesFormacion = new JPanel();
		spTblVacaciones = new JScrollPane();
		tblVacaciones = new JTable();
		spBeneficios = new JScrollPane();
		panelBeneficios = new JPanel();
		cbFechaTipoRol = new JCheckBox();
		btnImprimirBeneficios = new JButton();
		spTblBeneficios = new JScrollPane();
		tblBeneficios = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Hoja de Valores");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpHojaValores ========
		{
			
			//======== spHistorialSueldos ========
			{
				
				//======== panelDatosPersonales ========
				{
					panelDatosPersonales.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(160)),
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
							new RowSpec(Sizes.dluY(12)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- btnImprimirSueldos ----
					btnImprimirSueldos.setText("IS");
					panelDatosPersonales.add(btnImprimirSueldos, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblEmpleado ----
					lblEmpleado.setText("Empleado: ");
					panelDatosPersonales.add(lblEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtEmpleado ----
					txtEmpleado.setEditable(false);
					panelDatosPersonales.add(txtEmpleado, cc.xy(5, 5));
					
					//---- btnBuscarEmpleado ----
					btnBuscarEmpleado.setText("BE");
					panelDatosPersonales.add(btnBuscarEmpleado, cc.xywh(7, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblEmpleado2 ----
					lblEmpleado2.setText("Contrato: ");
					panelDatosPersonales.add(lblEmpleado2, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtContrato ----
					txtContrato.setEditable(false);
					panelDatosPersonales.add(txtContrato, cc.xy(5, 7));
					
					//---- btnBuscarContrato ----
					btnBuscarContrato.setText("BC");
					panelDatosPersonales.add(btnBuscarContrato, cc.xywh(7, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
					
					//---- cbUnirContratos ----
					cbUnirContratos.setText("Unir contratos");
					panelDatosPersonales.add(cbUnirContratos, cc.xywh(3, 9, 3, 1));
					
					//======== spTblSueldos ========
					{
						
						//---- tblSueldos ----
						tblSueldos.setPreferredScrollableViewportSize(new Dimension(450, 140));
						tblSueldos.setModel(new DefaultTableModel(
							new Object[][] {
								{"", null, null},
							},
							new String[] {
								"Incremento", "Fecha cambio", "Sueldo ($)"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblSueldos.setViewportView(tblSueldos);
					}
					panelDatosPersonales.add(spTblSueldos, cc.xywh(3, 13, 7, 3));
				}
				spHistorialSueldos.setViewportView(panelDatosPersonales);
			}
			jtpHojaValores.addTab("Historial de Sueldos", spHistorialSueldos);
			
			
			//======== spDeudas ========
			{
				
				//======== panelDeudas ========
				{
					panelDeudas.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(90)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- btnImprimirDeudas ----
					btnImprimirDeudas.setText("ID");
					panelDeudas.add(btnImprimirDeudas, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- cbDeudaDetallada ----
					cbDeudaDetallada.setText("Detallado");
					panelDeudas.add(cbDeudaDetallada, cc.xywh(3, 5, 3, 1));
					
					//======== spTblDeudas ========
					{
						
						//---- tblDeudas ----
						tblDeudas.setPreferredScrollableViewportSize(new Dimension(450, 140));
						tblDeudas.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null},
							},
							new String[] {
								"Rubro", "Descripci\u00f3n", "Tipo Rol", "Fecha Cobro", "Estado", "Valor"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblDeudas.setViewportView(tblDeudas);
					}
					panelDeudas.add(spTblDeudas, cc.xywh(3, 7, 7, 3));
				}
				spDeudas.setViewportView(panelDeudas);
			}
			jtpHojaValores.addTab("Deudas", spDeudas);
			
			
			//======== spVacaciones ========
			{
				
				//======== panelVacaciones ========
				{
					panelVacaciones.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- btnImprimirVac ----
					btnImprimirVac.setText("IV");
					panelVacaciones.add(btnImprimirVac, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== panelBotonesFormacion ========
					{
						panelBotonesFormacion.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));
					}
					panelVacaciones.add(panelBotonesFormacion, cc.xywh(3, 5, 5, 1));
					
					//======== spTblVacaciones ========
					{
						
						//---- tblVacaciones ----
						tblVacaciones.setPreferredScrollableViewportSize(new Dimension(450, 160));
						tblVacaciones.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null},
							},
							new String[] {
								"Periodo(Fecha Ini - Fecha Fin)", "Dias Tomados", "Fecha Inicio", "Fecha Fin"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblVacaciones.setViewportView(tblVacaciones);
					}
					panelVacaciones.add(spTblVacaciones, cc.xywh(3, 7, 5, 3));
				}
				spVacaciones.setViewportView(panelVacaciones);
			}
			jtpHojaValores.addTab("Vacaciones", spVacaciones);
			
			
			//======== spBeneficios ========
			{
				
				//======== panelBeneficios ========
				{
					panelBeneficios.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(90)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- cbFechaTipoRol ----
					cbFechaTipoRol.setText("Fecha(Mes/A\u00f1o) de Tipo de Rol");
					panelBeneficios.add(cbFechaTipoRol, cc.xywh(3, 5, 3, 1));
					
					//---- btnImprimirBeneficios ----
					btnImprimirBeneficios.setText("IBS");
					panelBeneficios.add(btnImprimirBeneficios, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== spTblBeneficios ========
					{
						
						//---- tblBeneficios ----
						tblBeneficios.setPreferredScrollableViewportSize(new Dimension(450, 160));
						tblBeneficios.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null},
							},
							new String[] {
								"Tipo", "Fecha Inicio", "Fecha Fin", "Estado", "Valor "
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblBeneficios.setViewportView(tblBeneficios);
					}
					panelBeneficios.add(spTblBeneficios, cc.xywh(3, 7, 9, 3));
				}
				spBeneficios.setViewportView(panelBeneficios);
			}
			jtpHojaValores.addTab("Beneficios", spBeneficios);
			
		}
		add(jtpHojaValores, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpHojaValores;
	private JScrollPane spHistorialSueldos;
	private JPanel panelDatosPersonales;
	private JButton btnImprimirSueldos;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblEmpleado2;
	private JTextField txtContrato;
	private JButton btnBuscarContrato;
	private JCheckBox cbUnirContratos;
	private JScrollPane spTblSueldos;
	private JTable tblSueldos;
	private JScrollPane spDeudas;
	private JPanel panelDeudas;
	private JButton btnImprimirDeudas;
	private JCheckBox cbDeudaDetallada;
	private JScrollPane spTblDeudas;
	private JTable tblDeudas;
	private JScrollPane spVacaciones;
	private JPanel panelVacaciones;
	private JButton btnImprimirVac;
	private JPanel panelBotonesFormacion;
	private JScrollPane spTblVacaciones;
	private JTable tblVacaciones;
	private JScrollPane spBeneficios;
	private JPanel panelBeneficios;
	private JCheckBox cbFechaTipoRol;
	private JButton btnImprimirBeneficios;
	private JScrollPane spTblBeneficios;
	private JTable tblBeneficios;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideTabbedPane getJtpHojaValores() {
		return jtpHojaValores;
	}

	public JScrollPane getSpHistorialSueldos() {
		return spHistorialSueldos;
	}

	public JPanel getPanelDatosPersonales() {
		return panelDatosPersonales;
	}

	public JButton getBtnImprimirSueldos() {
		return btnImprimirSueldos;
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

	public JLabel getLblEmpleado2() {
		return lblEmpleado2;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JButton getBtnBuscarContrato() {
		return btnBuscarContrato;
	}

	public JCheckBox getCbUnirContratos() {
		return cbUnirContratos;
	}

	public JScrollPane getSpTblSueldos() {
		return spTblSueldos;
	}

	public JTable getTblSueldos() {
		return tblSueldos;
	}

	public JScrollPane getSpDeudas() {
		return spDeudas;
	}

	public JPanel getPanelDeudas() {
		return panelDeudas;
	}

	public JButton getBtnImprimirDeudas() {
		return btnImprimirDeudas;
	}

	public JCheckBox getCbDeudaDetallada() {
		return cbDeudaDetallada;
	}

	public JScrollPane getSpTblDeudas() {
		return spTblDeudas;
	}

	public JTable getTblDeudas() {
		return tblDeudas;
	}

	public JScrollPane getSpVacaciones() {
		return spVacaciones;
	}

	public JPanel getPanelVacaciones() {
		return panelVacaciones;
	}

	public JButton getBtnImprimirVac() {
		return btnImprimirVac;
	}

	public JPanel getPanelBotonesFormacion() {
		return panelBotonesFormacion;
	}

	public JScrollPane getSpTblVacaciones() {
		return spTblVacaciones;
	}

	public JTable getTblVacaciones() {
		return tblVacaciones;
	}

	public JScrollPane getSpBeneficios() {
		return spBeneficios;
	}

	public JPanel getPanelBeneficios() {
		return panelBeneficios;
	}

	public JCheckBox getCbFechaTipoRol() {
		return cbFechaTipoRol;
	}

	public JButton getBtnImprimirBeneficios() {
		return btnImprimirBeneficios;
	}

	public JScrollPane getSpTblBeneficios() {
		return spTblBeneficios;
	}

	public JTable getTblBeneficios() {
		return tblBeneficios;
	}
}
