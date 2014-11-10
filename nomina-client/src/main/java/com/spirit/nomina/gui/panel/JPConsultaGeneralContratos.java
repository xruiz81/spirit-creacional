package com.spirit.nomina.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public abstract class JPConsultaGeneralContratos extends ReportModelImpl {
	public JPConsultaGeneralContratos() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		btnConsultar = new JButton();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		panelSeleccionarTodos = new JPanel();
		btnSeleccionarTodos = new JButton();
		scrollPane1 = new JScrollPane();
		tblConsulta = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Consulta General de Contratos");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo de Contrato: ");
		add(lblTipoContrato, cc.xy(3, 3));
		add(cmbTipoContrato, cc.xywh(5, 3, 5, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio: ");
		add(lblFechaInicio, cc.xy(3, 5));
		add(cmbFechaInicio, cc.xywh(5, 5, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("fecha Fin: ");
		add(lblFechaFin, cc.xy(3, 7));
		add(cmbFechaFin, cc.xywh(5, 7, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(13, 7));

		//======== panelSeleccionarTodos ========
		{
			panelSeleccionarTodos.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnSeleccionarTodos ----
			btnSeleccionarTodos.setText("Seleccionar Todos");
			panelSeleccionarTodos.add(btnSeleccionarTodos, cc.xy(1, 1));
		}
		add(panelSeleccionarTodos, cc.xywh(3, 11, 13, 1));

		//======== scrollPane1 ========
		{

			//---- tblConsulta ----
			tblConsulta.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null},
				},
				new String[] {
					" ", "Empleado", "Tipo Contrato", "Fecha Ingreso"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false
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
			scrollPane1.setViewportView(tblConsulta);
		}
		add(scrollPane1, cc.xywh(3, 13, 13, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JButton btnConsultar;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JPanel panelSeleccionarTodos;
	private JButton btnSeleccionarTodos;
	private JScrollPane scrollPane1;
	private JTable tblConsulta;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JPanel getPanelSeleccionarTodos() {
		return panelSeleccionarTodos;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public JTable getTblConsulta() {
		return tblConsulta;
	}
}
