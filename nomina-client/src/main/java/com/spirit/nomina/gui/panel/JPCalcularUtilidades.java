package com.spirit.nomina.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Wed Apr 23 13:23:00 COT 2014
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPCalcularUtilidades extends SpiritModelImpl {
	public JPCalcularUtilidades() {
		initComponents();
		setName("Calcular Utilidades");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblIngresarUtilidad = new JLabel();
		txtIngresarUtilidad = new JTextField();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lbl75Porciento = new JLabel();
		txt75Porciento = new JTextField();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		lbl25Porciento = new JLabel();
		txt25Porciento = new JTextField();
		btnConsultar = new JButton();
		lblAnio = new JLabel();
		cmbAnio = new DateComboBox();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		btnCalcularUtilidades = new JButton();
		spCalcularUtilidades = new JScrollPane();
		tblCalcularUtilidades = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Consulta General de Contratos");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(130)),
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
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblIngresarUtilidad ----
		lblIngresarUtilidad.setText("Ingresar Total de Utilidad:");
		add(lblIngresarUtilidad, cc.xy(3, 3));

		//---- txtIngresarUtilidad ----
		txtIngresarUtilidad.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtIngresarUtilidad, cc.xy(5, 3));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xy(17, 3));

		//---- lbl75Porciento ----
		lbl75Porciento.setText("75%:");
		add(lbl75Porciento, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txt75Porciento ----
		txt75Porciento.setHorizontalAlignment(SwingConstants.RIGHT);
		txt75Porciento.setEditable(false);
		add(txt75Porciento, cc.xy(5, 5));

		//---- lblDepartamento ----
		lblDepartamento.setText("Departamento:");
		add(lblDepartamento, cc.xy(15, 5));
		add(cmbDepartamento, cc.xy(17, 5));

		//---- lbl25Porciento ----
		lbl25Porciento.setText("25%:");
		add(lbl25Porciento, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txt25Porciento ----
		txt25Porciento.setHorizontalAlignment(SwingConstants.RIGHT);
		txt25Porciento.setEditable(false);
		add(txt25Porciento, cc.xy(5, 7));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(17, 7));

		//---- lblAnio ----
		lblAnio.setText("A\u00f1o: ");
		add(lblAnio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbAnio, cc.xy(5, 9));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xy(9, 9));

		//---- txtEstado ----
		txtEstado.setEditable(false);
		txtEstado.setHorizontalAlignment(SwingConstants.CENTER);
		add(txtEstado, cc.xy(11, 9));

		//---- btnCalcularUtilidades ----
		btnCalcularUtilidades.setText("Calcular distribuci\u00f3n de Utilidades");
		add(btnCalcularUtilidades, cc.xywh(5, 11, 7, 1));

		//======== spCalcularUtilidades ========
		{

			//---- tblCalcularUtilidades ----
			tblCalcularUtilidades.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, "", null},
				},
				new String[] {
					" Oficina", "Departamento", "Empleado", "Fecha de Ingreso", "# D\u00edas", "Carga", "Relaci\u00f3n", "Fecha de Nacimiento", "$ Empleado", "$ Carga", "$ Total"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false
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
			spCalcularUtilidades.setViewportView(tblCalcularUtilidades);
		}
		add(spCalcularUtilidades, cc.xywh(3, 15, 17, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblIngresarUtilidad;
	private JTextField txtIngresarUtilidad;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lbl75Porciento;
	private JTextField txt75Porciento;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JLabel lbl25Porciento;
	private JTextField txt25Porciento;
	private JButton btnConsultar;
	private JLabel lblAnio;
	private DateComboBox cmbAnio;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JButton btnCalcularUtilidades;
	private JScrollPane spCalcularUtilidades;
	private JTable tblCalcularUtilidades;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtIngresarUtilidad() {
		return txtIngresarUtilidad;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JTextField getTxt75Porciento() {
		return txt75Porciento;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public JTextField getTxt25Porciento() {
		return txt25Porciento;
	}

	public DateComboBox getCmbAnio() {
		return cmbAnio;
	}

	public JButton getBtnCalcularUtilidades() {
		return btnCalcularUtilidades;
	}

	public JScrollPane getSpCalcularUtilidades() {
		return spCalcularUtilidades;
	}

	public JTable getTblCalcularUtilidades() {
		return tblCalcularUtilidades;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}
}
