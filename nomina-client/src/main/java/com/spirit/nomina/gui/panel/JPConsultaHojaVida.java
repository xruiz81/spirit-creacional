package com.spirit.nomina.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.ReportModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPConsultaHojaVida extends ReportModelImpl {
	public JPConsultaHojaVida() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoRol = new JLabel();
		cmbTipoRol = new JComboBox();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblContrato = new JLabel();
		btnBuscarContrato = new JButton();
		txtContrato = new JTextField();
		lblFechaInicio = new JLabel();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		cmbFechaInicio = new DateComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		btnConsultar = new JButton();
		panelRubros = new JPanel();
		spRubro = new JScrollPane();
		tblRubro = new JTable();
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
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo Rol: ");
		add(lblTipoRol, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRol, cc.xywh(5, 3, 7, 1));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo Contrato: ");
		add(lblTipoContrato, cc.xy(3, 5));
		add(cmbTipoContrato, cc.xywh(5, 5, 7, 1));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado: ");
		add(lblEmpleado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 7, 9, 1));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xy(15, 7));

		//---- lblContrato ----
		lblContrato.setText("Contrato: ");
		add(lblContrato, cc.xy(3, 9));

		//---- btnBuscarContrato ----
		btnBuscarContrato.setText("B");
		add(btnBuscarContrato, cc.xy(17, 9));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xywh(5, 9, 11, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha  Inicio: ");
		add(lblFechaInicio, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(13, 11));
		add(cmbFechaFin, cc.xywh(15, 11, 7, 1));
		add(cmbFechaInicio, cc.xywh(5, 11, 5, 1));

		//---- lblObservacion ----
		lblObservacion.setText("Observaci\u00f3n: ");
		add(lblObservacion, cc.xy(3, 13));

		//---- txtObservacion ----
		txtObservacion.setEditable(false);
		txtObservacion.setEnabled(true);
		add(txtObservacion, cc.xywh(5, 13, 5, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(5, 17, 3, 1));

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
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return columnEditable[columnIndex];
					}
				});
				spRubro.setViewportView(tblRubro);
			}
			panelRubros.add(spRubro, cc.xy(1, 1));
		}
		add(panelRubros, cc.xywh(3, 21, 23, 1));
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
	private JButton btnBuscarContrato;
	private JTextField txtContrato;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private DateComboBox cmbFechaInicio;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JButton btnConsultar;
	private JPanel panelRubros;
	private JScrollPane spRubro;
	private JTable tblRubro;
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

	public JButton getBtnBuscarContrato() {
		return btnBuscarContrato;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
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

	public JPanel getPanelRubros() {
		return panelRubros;
	}

	public JScrollPane getSpRubro() {
		return spRubro;
	}

	public JTable getTblRubro() {
		return tblRubro;
	}
}
