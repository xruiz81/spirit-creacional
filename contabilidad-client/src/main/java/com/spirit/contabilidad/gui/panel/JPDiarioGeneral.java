package com.spirit.contabilidad.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPDiarioGeneral extends ReportModelImpl {
	public JPDiarioGeneral() {
		initComponents();
		setName("Diario General");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaIni = new DateComboBox();
		btnConsultar = new JButton();
		lblEjercicioContable = new JLabel();
		lblFechaFin = new JLabel();
		cmbEjercicioContable = new JComboBox();
		cmbFechaFin = new DateComboBox();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		cmbStatus = new JComboBox();
		lblEstado = new JLabel();
		spTablaDiario = new JScrollPane();
		tblDiario = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(110)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan Cuenta:");
		add(lblPlanCuenta, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(5, 3, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaIni, cc.xy(13, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(17, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblEjercicioContable ----
		lblEjercicioContable.setText("Ejercicio Contable:");
		add(lblEjercicioContable, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEjercicioContable, cc.xywh(5, 5, 3, 1));
		add(cmbFechaFin, cc.xy(13, 5));

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo Documento:");
		add(lblTipoDocumento, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoDocumento, cc.xywh(5, 7, 3, 1));
		add(cmbStatus, cc.xy(5, 9));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== spTablaDiario ========
		{

			//---- tblDiario ----
			tblDiario.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha", "# Asiento", "Comprobante", "Cuenta", "Nombre de Cuenta", "Debe", "Haber"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTablaDiario.setViewportView(tblDiario);
		}
		add(spTablaDiario, cc.xywh(3, 13, 17, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaIni;
	private JButton btnConsultar;
	private JLabel lblEjercicioContable;
	private JLabel lblFechaFin;
	private JComboBox cmbEjercicioContable;
	private DateComboBox cmbFechaFin;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JComboBox cmbStatus;
	private JLabel lblEstado;
	private JScrollPane spTablaDiario;
	private JTable tblDiario;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaIni() {
		return cmbFechaIni;
	}

	public void setCmbFechaIni(DateComboBox cmbFechaIni) {
		this.cmbFechaIni = cmbFechaIni;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(JComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public JScrollPane getSpTablaDiario() {
		return spTablaDiario;
	}

	public void setSpTablaDiario(JScrollPane spTablaDiario) {
		this.spTablaDiario = spTablaDiario;
	}

	public JTable getTblDiario() {
		return tblDiario;
	}

	public void setTblDiario(JTable tblDiario) {
		this.tblDiario = tblDiario;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JComboBox getCmbEjercicioContable() {
		return cmbEjercicioContable;
	}

	public void setCmbEjercicioContable(JComboBox cmbEjercicioContable) {
		this.cmbEjercicioContable = cmbEjercicioContable;
	}
}
