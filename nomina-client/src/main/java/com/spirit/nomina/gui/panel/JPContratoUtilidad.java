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

public abstract class JPContratoUtilidad extends MantenimientoModelImpl {
	public JPContratoUtilidad() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblMontoUtilidad = new JLabel();
		txtMontoUtilidad = new JTextField();
		lblPorcentajeContratos = new JLabel();
		txtPorcentajeContratos = new JTextField();
		lblPorcentajeCargas = new JLabel();
		txtPorcentajeCargas = new JTextField();
		spTblContratoUtilidad = new JScrollPane();
		tblContratoUtilidad = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Utilidades");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xywh(5, 3, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xy(3, 5));

		//---- cmbFechaInicio ----
		cmbFechaInicio.setShowOKButton(false);
		cmbFechaInicio.setShowNoneButton(false);
		cmbFechaInicio.setShowWeekNumbers(true);
		cmbFechaInicio.setShowTodayButton(false);
		add(cmbFechaInicio, cc.xywh(5, 5, 7, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xy(15, 5));
		add(cmbFechaFin, cc.xy(17, 5));

		//---- lblMontoUtilidad ----
		lblMontoUtilidad.setText("Monto Utilidad:");
		add(lblMontoUtilidad, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtMontoUtilidad, cc.xywh(5, 7, 5, 1));

		//---- lblPorcentajeContratos ----
		lblPorcentajeContratos.setText("Porcentaje Contratos: ");
		add(lblPorcentajeContratos, cc.xy(3, 9));
		add(txtPorcentajeContratos, cc.xy(5, 9));

		//---- lblPorcentajeCargas ----
		lblPorcentajeCargas.setText("Porcentaje Cargas: ");
		add(lblPorcentajeCargas, cc.xy(3, 11));
		add(txtPorcentajeCargas, cc.xy(5, 11));

		//======== spTblContratoUtilidad ========
		{
			
			//---- tblContratoUtilidad ----
			tblContratoUtilidad.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Fecha Inicio", "Fecha Fin", "Monto Utilidad", "Porcentaje Contratos", "Porcentaje Cargas"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblContratoUtilidad.setViewportView(tblContratoUtilidad);
		}
		add(spTblContratoUtilidad, cc.xywh(3, 15, 17, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblMontoUtilidad;
	private JTextField txtMontoUtilidad;
	private JLabel lblPorcentajeContratos;
	private JTextField txtPorcentajeContratos;
	private JLabel lblPorcentajeCargas;
	private JTextField txtPorcentajeCargas;
	private JScrollPane spTblContratoUtilidad;
	private JTable tblContratoUtilidad;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
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

	public JLabel getLblMontoUtilidad() {
		return lblMontoUtilidad;
	}

	public JTextField getTxtMontoUtilidad() {
		return txtMontoUtilidad;
	}

	public JLabel getLblPorcentajeContratos() {
		return lblPorcentajeContratos;
	}

	public JTextField getTxtPorcentajeContratos() {
		return txtPorcentajeContratos;
	}

	public JLabel getLblPorcentajeCargas() {
		return lblPorcentajeCargas;
	}

	public JTextField getTxtPorcentajeCargas() {
		return txtPorcentajeCargas;
	}

	public JScrollPane getSpTblContratoUtilidad() {
		return spTblContratoUtilidad;
	}

	public JTable getTblContratoUtilidad() {
		return tblContratoUtilidad;
	}
}
