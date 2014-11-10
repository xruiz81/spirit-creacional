package com.spirit.nomina.gui.panel;
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


public abstract class JPImpuestoRentaEmpleado extends MantenimientoModelImpl {
	public JPImpuestoRentaEmpleado() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblFraccionBasica = new JLabel();
		txtFraccionBasica = new JTextField();
		lblEmpleado = new JLabel();
		txtExcesoHasta = new JTextField();
		lblImpuestoFraccionBasica = new JLabel();
		txtImpuestoFraccionBasica = new JTextField();
		lblPorcentajeImpuesto = new JLabel();
		txtPorcentajeImpuesto = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		scpImpuestoRenta = new JScrollPane();
		tblImpuestoRenta = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Impuesto Renta Empleado");
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
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblFraccionBasica ----
		lblFraccionBasica.setText("Fracci\u00f3n B\u00e1sica: ");
		add(lblFraccionBasica, cc.xy(3, 3));
		add(txtFraccionBasica, cc.xywh(5, 3, 3, 1));

		//---- lblEmpleado ----
		lblEmpleado.setText("Exceso hasta: ");
		add(lblEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtExcesoHasta, cc.xywh(5, 5, 3, 1));

		//---- lblImpuestoFraccionBasica ----
		lblImpuestoFraccionBasica.setText("Impuesto Fracci\u00f3n B\u00e1sica: ");
		add(lblImpuestoFraccionBasica, cc.xy(3, 7));
		add(txtImpuestoFraccionBasica, cc.xywh(5, 7, 3, 1));

		//---- lblPorcentajeImpuesto ----
		lblPorcentajeImpuesto.setText("% Imp. Fracci\u00f3n Excedente: ");
		add(lblPorcentajeImpuesto, cc.xy(3, 9));
		add(txtPorcentajeImpuesto, cc.xywh(5, 9, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha  Inicio: ");
		add(lblFechaInicio, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 11, 5, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(3, 13));
		add(cmbFechaFin, cc.xywh(5, 13, 5, 1));

		//======== scpImpuestoRenta ========
		{
			
			//---- tblImpuestoRenta ----
			tblImpuestoRenta.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"Fracci\u00f3n B\u00e1sica", "Exceso hasta", "Imp. Fracci\u00f3n B\u00e1sica", "% Imp. Fracci\u00f3n Excedente", "Fecha Inicio", "Fecha Fin"
				}
			) {
				Class[] columnTypes = new Class[] {
					Double.class, Double.class, Double.class, Double.class, Object.class, Object.class
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
			scpImpuestoRenta.setViewportView(tblImpuestoRenta);
		}
		add(scpImpuestoRenta, cc.xywh(3, 17, 13, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblFraccionBasica;
	private JTextField txtFraccionBasica;
	private JLabel lblEmpleado;
	private JTextField txtExcesoHasta;
	private JLabel lblImpuestoFraccionBasica;
	private JTextField txtImpuestoFraccionBasica;
	private JLabel lblPorcentajeImpuesto;
	private JTextField txtPorcentajeImpuesto;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JScrollPane scpImpuestoRenta;
	private JTable tblImpuestoRenta;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblFraccionBasica() {
		return lblFraccionBasica;
	}

	public JTextField getTxtFraccionBasica() {
		return txtFraccionBasica;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtExcesoHasta() {
		return txtExcesoHasta;
	}

	public JLabel getLblImpuestoFraccionBasica() {
		return lblImpuestoFraccionBasica;
	}

	public JTextField getTxtImpuestoFraccionBasica() {
		return txtImpuestoFraccionBasica;
	}

	public JLabel getLblPorcentajeImpuesto() {
		return lblPorcentajeImpuesto;
	}

	public JTextField getTxtPorcentajeImpuesto() {
		return txtPorcentajeImpuesto;
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

	public JScrollPane getScpImpuestoRenta() {
		return scpImpuestoRenta;
	}

	public JTable getTblImpuestoRenta() {
		return tblImpuestoRenta;
	}
}
