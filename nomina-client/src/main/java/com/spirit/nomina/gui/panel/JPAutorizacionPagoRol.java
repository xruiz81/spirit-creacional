package com.spirit.nomina.gui.panel;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPAutorizacionPagoRol extends SpiritModelImpl {
	public JPAutorizacionPagoRol() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblTipoRubro = new JLabel();
		panelTipoRubros = new JPanel();
		rbNormal = new JRadioButton();
		rbAnticipos = new JRadioButton();
		rbBeneficiosSociales = new JRadioButton();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		btnFiltrar = new JButton();
		splPnAutorizacion = new JSplitPane();
		panelRolPago = new JPanel();
		gfsRubros = compFactory.createSeparator("Rol de Pago");
		spTblRolPago = new JScrollPane();
		tblRolPago = new JTable();
		panelDetalleRolPago = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		btnSeleccionarTodos = new JButton();
		cmbTipoPagoTodos = new JComboBox();
		btnTipoPagoTodos = new JButton();
		cmbCuentaBancariaTodos = new JComboBox();
		btnCuentaBancariaTodos = new JButton();
		gfsRubros2 = compFactory.createSeparator("Detalle Rol de Pago");
		spTblRolPagoDetalleQyM = new JScrollPane();
		tblRolPagoDetalleQyM = new JTable();
		btnGeneraTotal = new JButton();
		lblTotalDebitos = new JLabel();
		txtTotalDebitos = new JTextField();
		lblTotalCheques = new JLabel();
		txtTotalCheques = new JTextField();
		spTblRolPagoDetalleAyD = new JScrollPane();
		tblRolPagoDetalleAportesDecimos = new JTable();
		spTblRolPagoDetalleAnticipos = new JScrollPane();
		tblRolPagoDetalleAnticipos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Autorizacion Pago de Rol");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(82)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(42)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(53)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
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
				new RowSpec("fill:min(default;250dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRubro ----
		lblTipoRubro.setText("Tipo de Rubros:");
		add(lblTipoRubro, cc.xy(3, 3));

		//======== panelTipoRubros ========
		{
			panelTipoRubros.setLayout(new FormLayout(
				"default",
				"default, default, default"));

			//---- rbNormal ----
			rbNormal.setText("Normales");
			panelTipoRubros.add(rbNormal, cc.xy(1, 1));

			//---- rbAnticipos ----
			rbAnticipos.setText("Anticipos/Eventuales");
			panelTipoRubros.add(rbAnticipos, cc.xy(1, 2));

			//---- rbBeneficiosSociales ----
			rbBeneficiosSociales.setText("Beneficios Sociales");
			panelTipoRubros.add(rbBeneficiosSociales, cc.xy(1, 3));
		}
		add(panelTipoRubros, cc.xywh(5, 3, 2, 5));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio: ");
		add(lblFechaInicio, cc.xy(9, 3));
		add(cmbFechaInicio, cc.xywh(11, 3, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(9, 5));
		add(cmbFechaFin, cc.xywh(11, 5, 3, 1));

		//---- btnFiltrar ----
		btnFiltrar.setText("Filtrar");
		add(btnFiltrar, cc.xy(13, 7));

		//======== splPnAutorizacion ========
		{
			splPnAutorizacion.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splPnAutorizacion.setBorder(null);

			//======== panelRolPago ========
			{
				panelRolPago.setMinimumSize(new Dimension(70, 120));
				panelRolPago.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				panelRolPago.add(gfsRubros, cc.xy(1, 1));

				//======== spTblRolPago ========
				{

					//---- tblRolPago ----
					tblRolPago.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, "", null, null},
						},
						new String[] {
							"Tipo de Rol", "Tipo de Contrato", "Mes", "A\u00f1o", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblRolPago.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					spTblRolPago.setViewportView(tblRolPago);
				}
				panelRolPago.add(spTblRolPago, cc.xywh(1, 3, 1, 3));
			}
			splPnAutorizacion.setTopComponent(panelRolPago);

			//======== panelDetalleRolPago ========
			{
				panelDetalleRolPago.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY4),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));

				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(170)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));

					//======== panel2 ========
					{
						panel2.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- btnSeleccionarTodos ----
						btnSeleccionarTodos.setText("Seleccionar Todo");
						panel2.add(btnSeleccionarTodos, cc.xy(1, 1));
					}
					panel1.add(panel2, cc.xywh(1, 1, 11, 1));
					panel1.add(cmbTipoPagoTodos, cc.xy(1, 3));

					//---- btnTipoPagoTodos ----
					btnTipoPagoTodos.setText("Aplicar a todos");
					panel1.add(btnTipoPagoTodos, cc.xy(3, 3));
					panel1.add(cmbCuentaBancariaTodos, cc.xy(7, 3));

					//---- btnCuentaBancariaTodos ----
					btnCuentaBancariaTodos.setText("Aplicar a Todos");
					panel1.add(btnCuentaBancariaTodos, cc.xy(9, 3));
				}
				panelDetalleRolPago.add(panel1, cc.xywh(1, 3, 3, 1));
				panelDetalleRolPago.add(gfsRubros2, cc.xywh(1, 5, 3, 1));

				//======== spTblRolPagoDetalleQyM ========
				{

					//---- tblRolPagoDetalleQyM ----
					tblRolPagoDetalleQyM.setModel(new DefaultTableModel(
						new Object[][] {
							{false, null, null, null, null, null, null, null},
						},
						new String[] {
							" ", "Nombre", "Total Ingresos", "Total Egresos", "Total", "Forma Pago", "Cuenta Bancaria", "# Cheque"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Double.class, Double.class, Double.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, false, false, true, true, true
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
					tblRolPagoDetalleQyM.setColumnSelectionAllowed(false);
					tblRolPagoDetalleQyM.setCellSelectionEnabled(true);
					tblRolPagoDetalleQyM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblRolPagoDetalleQyM.setViewportView(tblRolPagoDetalleQyM);
				}
				panelDetalleRolPago.add(spTblRolPagoDetalleQyM, cc.xywh(1, 7, 3, 3));
			}
			splPnAutorizacion.setBottomComponent(panelDetalleRolPago);
		}
		add(splPnAutorizacion, cc.xywh(3, 9, 17, 1));

		//---- btnGeneraTotal ----
		btnGeneraTotal.setText("Generar Total");
		add(btnGeneraTotal, cc.xy(5, 11));

		//---- lblTotalDebitos ----
		lblTotalDebitos.setText("Total Debitos");
		add(lblTotalDebitos, cc.xywh(9, 11, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
		add(txtTotalDebitos, cc.xy(11, 11));

		//---- lblTotalCheques ----
		lblTotalCheques.setText("Total Cheques");
		add(lblTotalCheques, cc.xywh(15, 11, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
		add(txtTotalCheques, cc.xy(17, 11));

		//======== spTblRolPagoDetalleAyD ========
		{

			//---- tblRolPagoDetalleAportesDecimos ----
			tblRolPagoDetalleAportesDecimos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, ""},
				},
				new String[] {
					" ", "Nombre", "Valor", "Anticipos", "Total", "Forma Pago", "Cuenta Bancaria", "# Cheque"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Double.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, true, true, true
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
			tblRolPagoDetalleAportesDecimos.setCellSelectionEnabled(true);
			tblRolPagoDetalleAportesDecimos.setColumnSelectionAllowed(false);
			tblRolPagoDetalleAportesDecimos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			spTblRolPagoDetalleAyD.setViewportView(tblRolPagoDetalleAportesDecimos);
		}

		//======== spTblRolPagoDetalleAnticipos ========
		{

			//---- tblRolPagoDetalleAnticipos ----
			tblRolPagoDetalleAnticipos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					" ", "Nombre", "Total", "Forma Pago", "Cuenta Bancaria", "# Cheque"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Double.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, true, true, true
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
			tblRolPagoDetalleAnticipos.setCellSelectionEnabled(true);
			tblRolPagoDetalleAnticipos.setColumnSelectionAllowed(false);
			tblRolPagoDetalleAnticipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			spTblRolPagoDetalleAnticipos.setViewportView(tblRolPagoDetalleAnticipos);
		}

		//---- buttonGroupTipoRubro ----
		ButtonGroup buttonGroupTipoRubro = new ButtonGroup();
		buttonGroupTipoRubro.add(rbNormal);
		buttonGroupTipoRubro.add(rbAnticipos);
		buttonGroupTipoRubro.add(rbBeneficiosSociales);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblTipoRubro;
	private JPanel panelTipoRubros;
	private JRadioButton rbNormal;
	private JRadioButton rbAnticipos;
	private JRadioButton rbBeneficiosSociales;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JButton btnFiltrar;
	private JSplitPane splPnAutorizacion;
	private JPanel panelRolPago;
	private JComponent gfsRubros;
	private JScrollPane spTblRolPago;
	private JTable tblRolPago;
	private JPanel panelDetalleRolPago;
	private JPanel panel1;
	private JPanel panel2;
	private JButton btnSeleccionarTodos;
	private JComboBox cmbTipoPagoTodos;
	private JButton btnTipoPagoTodos;
	private JComboBox cmbCuentaBancariaTodos;
	private JButton btnCuentaBancariaTodos;
	private JComponent gfsRubros2;
	private JScrollPane spTblRolPagoDetalleQyM;
	private JTable tblRolPagoDetalleQyM;
	private JButton btnGeneraTotal;
	private JLabel lblTotalDebitos;
	private JTextField txtTotalDebitos;
	private JLabel lblTotalCheques;
	private JTextField txtTotalCheques;
	private JScrollPane spTblRolPagoDetalleAyD;
	private JTable tblRolPagoDetalleAportesDecimos;
	private JScrollPane spTblRolPagoDetalleAnticipos;
	private JTable tblRolPagoDetalleAnticipos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	
	public JLabel getLblTipoRubro() {
		return lblTipoRubro;
	}

	public JPanel getPanelTipoRubros() {
		return panelTipoRubros;
	}

	public JRadioButton getRbNormal() {
		return rbNormal;
	}

	public JRadioButton getRbAnticipos() {
		return rbAnticipos;
	}

	public JRadioButton getRbBeneficiosSociales() {
		return rbBeneficiosSociales;
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

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public JSplitPane getSplPnAutorizacion() {
		return splPnAutorizacion;
	}

	public JPanel getPanelRolPago() {
		return panelRolPago;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JScrollPane getSpTblRolPago() {
		return spTblRolPago;
	}

	public JTable getTblRolPago() {
		return tblRolPago;
	}

	public JPanel getPanelDetalleRolPago() {
		return panelDetalleRolPago;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JComboBox getCmbTipoPagoTodos() {
		return cmbTipoPagoTodos;
	}

	public JButton getBtnTipoPagoTodos() {
		return btnTipoPagoTodos;
	}

	public JComboBox getCmbCuentaBancariaTodos() {
		return cmbCuentaBancariaTodos;
	}

	public JButton getBtnCuentaBancariaTodos() {
		return btnCuentaBancariaTodos;
	}

	public JComponent getGfsRubros2() {
		return gfsRubros2;
	}

	public JScrollPane getSpTblRolPagoDetalleQyM() {
		return spTblRolPagoDetalleQyM;
	}

	public JTable getTblRolPagoDetalleQyM() {
		return tblRolPagoDetalleQyM;
	}

	public JScrollPane getSpTblRolPagoDetalleAyD() {
		return spTblRolPagoDetalleAyD;
	}

	public JTable getTblRolPagoDetalleAportesDecimos() {
		return tblRolPagoDetalleAportesDecimos;
	}

	public JScrollPane getSpTblRolPagoDetalleAnticipos() {
		return spTblRolPagoDetalleAnticipos;
	}

	public JTable getTblRolPagoDetalleAnticipos() {
		return tblRolPagoDetalleAnticipos;
	}

	public JButton getBtnGeneraTotal() {
		return btnGeneraTotal;
	}

	public JLabel getLblTotalDebitos() {
		return lblTotalDebitos;
	}

	public JTextField getTxtTotalDebitos() {
		return txtTotalDebitos;
	}

	public JLabel getLblTotalCheques() {
		return lblTotalCheques;
	}

	public JTextField getTxtTotalCheques() {
		return txtTotalCheques;
	}
		
}
