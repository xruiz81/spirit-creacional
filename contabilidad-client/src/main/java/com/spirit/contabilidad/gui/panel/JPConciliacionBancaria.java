package com.spirit.contabilidad.gui.panel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
import com.spirit.client.model.ReportModelImpl;

public abstract class JPConciliacionBancaria extends ReportModelImpl {
	public JPConciliacionBancaria() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		panel1 = new JPanel();
		btnEliminarCuentaBancaria = new JButton();
		btnAgregarCuentaBancaria = new JButton();
		spTblCuentaBancaria = new JScrollPane();
		tblCuentaBancaria = new JTable();
		lblCuentaBancaria = new JLabel();
		txtCuentaBancaria = new JTextField();
		btnBuscarCuentaBancaria = new JButton();
		label1 = new JLabel();
		panel2 = new JPanel();
		rbSi = new JRadioButton();
		rbNo = new JRadioButton();
		label12 = new JLabel();
		panel22 = new JPanel();
		rbSiAnulados = new JRadioButton();
		rbNo2 = new JRadioButton();
		label2 = new JLabel();
		panel23 = new JPanel();
		rbSiVersionExtendida = new JRadioButton();
		rbNoVersionExtendida = new JRadioButton();
		fsDetalle = compFactory.createSeparator("Detalle:");
		spDetalle = new JScrollPane();
		tblDetalleConciliacion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Conciliacion Bancaria");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(pref;60dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
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
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaInicio, cc.xywh(5, 3, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaFin, cc.xywh(13, 3, 3, 1));

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

			//---- btnEliminarCuentaBancaria ----
			btnEliminarCuentaBancaria.setText("D");
			panel1.add(btnEliminarCuentaBancaria, cc.xy(3, 1));

			//---- btnAgregarCuentaBancaria ----
			btnAgregarCuentaBancaria.setText("A");
			btnAgregarCuentaBancaria.setToolTipText("Agrega el Asiento a la Tabla");
			panel1.add(btnAgregarCuentaBancaria, cc.xy(1, 1));
		}
		add(panel1, cc.xywh(3, 13, 17, 1));

		//======== spTblCuentaBancaria ========
		{

			//---- tblCuentaBancaria ----
			tblCuentaBancaria.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Banco", "Cuenta Bancaria", "Cuenta", "Tipo Cuenta"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblCuentaBancaria.setViewportView(tblCuentaBancaria);
		}
		add(spTblCuentaBancaria, cc.xywh(3, 15, 17, 3));

		//---- lblCuentaBancaria ----
		lblCuentaBancaria.setText("Cuenta Bancaria: ");
		add(lblCuentaBancaria, cc.xy(3, 5));

		//---- txtCuentaBancaria ----
		txtCuentaBancaria.setEnabled(true);
		txtCuentaBancaria.setEditable(false);
		add(txtCuentaBancaria, cc.xywh(5, 5, 5, 1));

		//---- btnBuscarCuentaBancaria ----
		btnBuscarCuentaBancaria.setText("B");
		add(btnBuscarCuentaBancaria, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- label1 ----
		label1.setText("Detallado: ");
		add(label1, cc.xy(3, 7));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- rbSi ----
			rbSi.setText("Si");
			rbSi.setSelected(true);
			panel2.add(rbSi, cc.xy(1, 1));

			//---- rbNo ----
			rbNo.setText("No");
			panel2.add(rbNo, cc.xy(3, 1));
		}
		add(panel2, cc.xywh(5, 7, 3, 1));

		//---- label12 ----
		label12.setText("Cheques Anulados: ");
		add(label12, cc.xy(3, 9));

		//======== panel22 ========
		{
			panel22.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- rbSiAnulados ----
			rbSiAnulados.setText("Si");
			rbSiAnulados.setSelected(true);
			panel22.add(rbSiAnulados, cc.xy(1, 1));

			//---- rbNo2 ----
			rbNo2.setText("No");
			panel22.add(rbNo2, cc.xy(3, 1));
		}
		add(panel22, cc.xy(5, 9));

		//---- label2 ----
		label2.setText("Versi\u00f3n Extendida:");
		add(label2, cc.xy(3, 11));

		//======== panel23 ========
		{
			panel23.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- rbSiVersionExtendida ----
			rbSiVersionExtendida.setText("Si");
			rbSiVersionExtendida.setSelected(true);
			panel23.add(rbSiVersionExtendida, cc.xy(1, 1));

			//---- rbNoVersionExtendida ----
			rbNoVersionExtendida.setText("No");
			panel23.add(rbNoVersionExtendida, cc.xy(3, 1));
		}
		add(panel23, cc.xy(5, 11));
		add(fsDetalle, cc.xywh(3, 19, 3, 1));

		//======== spDetalle ========
		{

			//---- tblDetalleConciliacion ----
			tblDetalleConciliacion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"   ", " ", " ", " "
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spDetalle.setViewportView(tblDetalleConciliacion);
		}
		add(spDetalle, cc.xywh(3, 21, 17, 3));

		//---- buttonGroupDetallarConciliacion ----
		ButtonGroup buttonGroupDetallarConciliacion = new ButtonGroup();
		buttonGroupDetallarConciliacion.add(rbSi);
		buttonGroupDetallarConciliacion.add(rbNo);

		//---- buttonGroupChequesEmitidos ----
		ButtonGroup buttonGroupChequesEmitidos = new ButtonGroup();
		buttonGroupChequesEmitidos.add(rbSiAnulados);
		buttonGroupChequesEmitidos.add(rbNo2);
		
		//---- buttonGroupVersionExtendida ----
		ButtonGroup buttonGroupVersionExtendida = new ButtonGroup();
		buttonGroupVersionExtendida.add(rbSiVersionExtendida);
		buttonGroupVersionExtendida.add(rbNoVersionExtendida);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JPanel panel1;
	private JButton btnEliminarCuentaBancaria;
	private JButton btnAgregarCuentaBancaria;
	private JScrollPane spTblCuentaBancaria;
	private JTable tblCuentaBancaria;
	private JLabel lblCuentaBancaria;
	private JTextField txtCuentaBancaria;
	private JButton btnBuscarCuentaBancaria;
	private JLabel label1;
	private JPanel panel2;
	private JRadioButton rbSi;
	private JRadioButton rbNo;
	private JLabel label12;
	private JPanel panel22;
	private JRadioButton rbSiAnulados;
	private JRadioButton rbNo2;
	private JLabel label2;
	private JPanel panel23;
	private JRadioButton rbSiVersionExtendida;
	private JRadioButton rbNoVersionExtendida;
	private JComponent fsDetalle;
	private JScrollPane spDetalle;
	private JTable tblDetalleConciliacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}
	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}
	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}
	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}
	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}
	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}
	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}
	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}
	public JPanel getPanel1() {
		return panel1;
	}
	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}
	public JButton getBtnEliminarCuentaBancaria() {
		return btnEliminarCuentaBancaria;
	}
	public void setBtnEliminarCuentaBancaria(JButton btnEliminarCuentaBancaria) {
		this.btnEliminarCuentaBancaria = btnEliminarCuentaBancaria;
	}
	public JButton getBtnAgregarCuentaBancaria() {
		return btnAgregarCuentaBancaria;
	}
	public void setBtnAgregarCuentaBancaria(JButton btnAgregarCuentaBancaria) {
		this.btnAgregarCuentaBancaria = btnAgregarCuentaBancaria;
	}
	public JScrollPane getSpTblCuentaBancaria() {
		return spTblCuentaBancaria;
	}
	public void setSpTblCuentaBancaria(JScrollPane spTblCuentaBancaria) {
		this.spTblCuentaBancaria = spTblCuentaBancaria;
	}
	public JTable getTblCuentaBancaria() {
		return tblCuentaBancaria;
	}
	public void setTblCuentaBancaria(JTable tblCuentaBancaria) {
		this.tblCuentaBancaria = tblCuentaBancaria;
	}
	public JLabel getLblCuentaBancaria() {
		return lblCuentaBancaria;
	}
	public void setLblCuentaBancaria(JLabel lblCuentaBancaria) {
		this.lblCuentaBancaria = lblCuentaBancaria;
	}
	public JTextField getTxtCuentaBancaria() {
		return txtCuentaBancaria;
	}
	public void setTxtCuentaBancaria(JTextField txtCuentaBancaria) {
		this.txtCuentaBancaria = txtCuentaBancaria;
	}
	public JButton getBtnBuscarCuentaBancaria() {
		return btnBuscarCuentaBancaria;
	}
	public void setBtnBuscarCuentaBancaria(JButton btnBuscarCuentaBancaria) {
		this.btnBuscarCuentaBancaria = btnBuscarCuentaBancaria;
	}
	public JLabel getLabel1() {
		return label1;
	}
	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}
	public JPanel getPanel2() {
		return panel2;
	}
	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}
	public JRadioButton getRbSi() {
		return rbSi;
	}
	public void setRbSi(JRadioButton rbSi) {
		this.rbSi = rbSi;
	}
	public JRadioButton getRbNo() {
		return rbNo;
	}
	public void setRbNo(JRadioButton rbNo) {
		this.rbNo = rbNo;
	}
	public JLabel getLabel12() {
		return label12;
	}
	public void setLabel12(JLabel label12) {
		this.label12 = label12;
	}
	public JPanel getPanel22() {
		return panel22;
	}
	public void setPanel22(JPanel panel22) {
		this.panel22 = panel22;
	}
	public JRadioButton getRbSiAnulados() {
		return rbSiAnulados;
	}
	public void setRbSiAnulados(JRadioButton rbSiAnulados) {
		this.rbSiAnulados = rbSiAnulados;
	}
	public JRadioButton getRbNo2() {
		return rbNo2;
	}
	public void setRbNo2(JRadioButton rbNo2) {
		this.rbNo2 = rbNo2;
	}
	public JRadioButton getRbSiVersionExtendida() {
		return rbSiVersionExtendida;
	}

	public void setRbSiVersionExtendida(JRadioButton rbSiVersionExtendida) {
		this.rbSiVersionExtendida = rbSiVersionExtendida;
	}

	public JRadioButton getRbNoVersionExtendida() {
		return rbNoVersionExtendida;
	}

	public void setRbNoVersionExtendida(JRadioButton rbNoVersionExtendida) {
		this.rbNoVersionExtendida = rbNoVersionExtendida;
	}

	public JComponent getFsDetalle() {
		return fsDetalle;
	}
	public void setFsDetalle(JComponent fsDetalle) {
		this.fsDetalle = fsDetalle;
	}
	public JScrollPane getSpDetalle() {
		return spDetalle;
	}
	public void setSpDetalle(JScrollPane spDetalle) {
		this.spDetalle = spDetalle;
	}
	public JTable getTblDetalleConciliacion() {
		return tblDetalleConciliacion;
	}
	public void setTblDetalleConciliacion(JTable tblDetalleConciliacion) {
		this.tblDetalleConciliacion = tblDetalleConciliacion;
	}
}
