package com.spirit.nomina.gui.panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;
/*
 * Created by JFormDesigner on Wed Sep 02 10:40:20 COT 2009
 */

/**
 * @author conate
 */
public abstract class JPContrato extends SpiritModelImpl {
	public JPContrato() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpContrato = new JideTabbedPane();
		panelGeneral = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnEmpleado = new JButton();
		lblFechaElaboracion = new JLabel();
		cmbFechaElaboracion = new DateComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFondoReservaDiasPrevios = new JLabel();
		txtFondoReservaDiasPrevios = new JTextField();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		panelRubrosContrato = new JPanel();
		gfsRubros = compFactory.createSeparator("Rubros");
		spTblRubro = new JScrollPane();
		tblRubro = new JTable();
		cbTemporal = new JCheckBox();
		lblFechaInicioRubro = new JLabel();
		cmbFechaInicioRubro = new DateComboBox();
		lblFechaFinRubro = new JLabel();
		cmbFechaFinRubro = new DateComboBox();
		gfsRubrosContrato = compFactory.createSeparator("Rubros por Contrato");
		panelBotones = new JPanel();
		btnRemoverRubroContrato = new JButton();
		btnAgregarRubroContrato = new JButton();
		spTblRubroContrato = new JScrollPane();
		tblRubroContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Contrato");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpContrato ========
		{

			//======== panelGeneral ========
			{
				panelGeneral.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(25)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX11),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
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
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblCodigo ----
				lblCodigo.setText("C\u00f3digo:");
				panelGeneral.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtCodigo ----
				txtCodigo.setEditable(true);
				panelGeneral.add(txtCodigo, cc.xywh(5, 3, 3, 1));

				//---- lblTipoContrato ----
				lblTipoContrato.setText("Tipo de Contrato:");
				panelGeneral.add(lblTipoContrato, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelGeneral.add(cmbTipoContrato, cc.xywh(5, 5, 11, 1));

				//---- lblEmpleado ----
				lblEmpleado.setText("Empleado:");
				panelGeneral.add(lblEmpleado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtEmpleado ----
				txtEmpleado.setEditable(false);
				panelGeneral.add(txtEmpleado, cc.xywh(5, 7, 11, 1));
				panelGeneral.add(btnEmpleado, cc.xywh(17, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblFechaElaboracion ----
				lblFechaElaboracion.setText("Fecha de Elaboraci\u00f3n:");
				panelGeneral.add(lblFechaElaboracion, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelGeneral.add(cmbFechaElaboracion, cc.xywh(5, 9, 5, 1));

				//---- lblFechaInicio ----
				lblFechaInicio.setText("Fecha Inicio:");
				panelGeneral.add(lblFechaInicio, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbFechaInicio ----
				cmbFechaInicio.setEditable(false);
				panelGeneral.add(cmbFechaInicio, cc.xywh(5, 11, 5, 1));

				//---- lblFondoReservaDiasPrevios ----
				lblFondoReservaDiasPrevios.setText("F.R. d\u00edas previos:");
				panelGeneral.add(lblFondoReservaDiasPrevios, cc.xy(13, 11));

				//---- txtFondoReservaDiasPrevios ----
				txtFondoReservaDiasPrevios.setHorizontalAlignment(SwingConstants.RIGHT);
				panelGeneral.add(txtFondoReservaDiasPrevios, cc.xy(15, 11));

				//---- lblFechaFin ----
				lblFechaFin.setText("Fecha Fin:");
				panelGeneral.add(lblFechaFin, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbFechaFin ----
				cmbFechaFin.setEditable(false);
				panelGeneral.add(cmbFechaFin, cc.xywh(5, 13, 5, 1));

				//---- lblEstado ----
				lblEstado.setText("Estado:");
				panelGeneral.add(lblEstado, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbEstado ----
				cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
					"ACTIVO",
					"INACTIVO",
					"TERMINADO"
				}));
				panelGeneral.add(cmbEstado, cc.xy(5, 15));

				//---- lblObservacion ----
				lblObservacion.setText("Observaci\u00f3n:");
				panelGeneral.add(lblObservacion, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelGeneral.add(txtObservacion, cc.xywh(5, 17, 15, 1));
			}
			jtpContrato.addTab("General", panelGeneral);


			//======== panelRubrosContrato ========
			{
				panelRubrosContrato.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY7),
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
				panelRubrosContrato.add(gfsRubros, cc.xywh(3, 3, 17, 1));

				//======== spTblRubro ========
				{

					//---- tblRubro ----
					tblRubro.setModel(new DefaultTableModel(
						new Object[][] {
							{false, null, null},
						},
						new String[] {
							" ", "Rubro", "Tipo de Rubro"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false
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
					spTblRubro.setViewportView(tblRubro);
				}
				panelRubrosContrato.add(spTblRubro, cc.xywh(3, 5, 17, 5));

				//---- cbTemporal ----
				cbTemporal.setText("Temporal");
				panelRubrosContrato.add(cbTemporal, cc.xy(3, 11));

				//---- lblFechaInicioRubro ----
				lblFechaInicioRubro.setText("Fecha Inicio:");
				panelRubrosContrato.add(lblFechaInicioRubro, cc.xy(7, 11));
				panelRubrosContrato.add(cmbFechaInicioRubro, cc.xy(9, 11));

				//---- lblFechaFinRubro ----
				lblFechaFinRubro.setText("Fecha Fin:");
				panelRubrosContrato.add(lblFechaFinRubro, cc.xy(13, 11));
				panelRubrosContrato.add(cmbFechaFinRubro, cc.xy(15, 11));
				panelRubrosContrato.add(gfsRubrosContrato, cc.xywh(3, 15, 17, 1));

				//======== panelBotones ========
				{
					panelBotones.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnRemoverRubroContrato ----
					btnRemoverRubroContrato.setText("D");
					panelBotones.add(btnRemoverRubroContrato, cc.xy(3, 1));

					//---- btnAgregarRubroContrato ----
					btnAgregarRubroContrato.setText("A");
					panelBotones.add(btnAgregarRubroContrato, cc.xy(1, 1));
				}
				panelRubrosContrato.add(panelBotones, cc.xywh(3, 17, 18, 1));

				//======== spTblRubroContrato ========
				{

					//---- tblRubroContrato ----
					tblRubroContrato.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, "", null},
						},
						new String[] {
							"Rubro", "Tipo de Rubro", "Valor", "Fecha Inicio", "Fecha Fin"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, true, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubroContrato.setViewportView(tblRubroContrato);
				}
				panelRubrosContrato.add(spTblRubroContrato, cc.xywh(3, 19, 17, 3));
			}
			jtpContrato.addTab("Rubros por Contrato", panelRubrosContrato);

		}
		add(jtpContrato, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpContrato;
	private JPanel panelGeneral;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnEmpleado;
	private JLabel lblFechaElaboracion;
	private DateComboBox cmbFechaElaboracion;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFondoReservaDiasPrevios;
	private JTextField txtFondoReservaDiasPrevios;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JPanel panelRubrosContrato;
	private JComponent gfsRubros;
	private JScrollPane spTblRubro;
	private JTable tblRubro;
	private JCheckBox cbTemporal;
	private JLabel lblFechaInicioRubro;
	private DateComboBox cmbFechaInicioRubro;
	private JLabel lblFechaFinRubro;
	private DateComboBox cmbFechaFinRubro;
	private JComponent gfsRubrosContrato;
	private JPanel panelBotones;
	private JButton btnRemoverRubroContrato;
	private JButton btnAgregarRubroContrato;
	private JScrollPane spTblRubroContrato;
	private JTable tblRubroContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbTemporal() {
		return cbTemporal;
	}

	public DateComboBox getCmbFechaInicioRubro() {
		return cmbFechaInicioRubro;
	}

	public DateComboBox getCmbFechaFinRubro() {
		return cmbFechaFinRubro;
	}

	public JideTabbedPane getJtpContrato() {
		return jtpContrato;
	}

	public JPanel getPanelGeneral() {
		return panelGeneral;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
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

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JLabel getLblFechaElaboracion() {
		return lblFechaElaboracion;
	}

	public DateComboBox getCmbFechaElaboracion() {
		return cmbFechaElaboracion;
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

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JPanel getPanelRubrosContrato() {
		return panelRubrosContrato;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JScrollPane getSpTblRubro() {
		return spTblRubro;
	}

	public JTable getTblRubro() {
		return tblRubro;
	}

	public JComponent getGfsRubrosContrato() {
		return gfsRubrosContrato;
	}

	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public JButton getBtnRemoverRubroContrato() {
		return btnRemoverRubroContrato;
	}

	public JButton getBtnAgregarRubroContrato() {
		return btnAgregarRubroContrato;
	}

	public JScrollPane getSpTblRubroContrato() {
		return spTblRubroContrato;
	}

	public JTable getTblRubroContrato() {
		return tblRubroContrato;
	}

	public JTextField getTxtFondoReservaDiasPrevios() {
		return txtFondoReservaDiasPrevios;
	}

	public void setTxtFondoReservaDiasPrevios(JTextField txtFondoReservaDiasPrevios) {
		this.txtFondoReservaDiasPrevios = txtFondoReservaDiasPrevios;
	}
}
