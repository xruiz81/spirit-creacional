package com.spirit.contabilidad.gui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
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


public abstract class JPConciliacionFondoRotativo extends ReportModelImpl {
	public JPConciliacionFondoRotativo() {
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
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		btnBuscarClienteOficina = new JButton();
		lblDetallado = new JLabel();
		panel2 = new JPanel();
		rbSi = new JRadioButton();
		rbNo = new JRadioButton();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblChequesAnulados = new JLabel();
		panel22 = new JPanel();
		rbSiAnulados = new JRadioButton();
		rbNo2 = new JRadioButton();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		lblVersionExtendida = new JLabel();
		panel23 = new JPanel();
		rbSiVersionExtendida = new JRadioButton();
		rbNoVersionExtendida = new JRadioButton();
		lblTipoProducto = new JLabel();
		cmbTipoProducto = new JComboBox();
		fsDetalle = compFactory.createSeparator("Detalle:");
		spDetalle = new JScrollPane();
		tblDetalleConciliacion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Conciliacion Fondo Rotativo");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(pref;65dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
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
		add(cmbFechaFin, cc.xy(13, 3));

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
		add(panel1, cc.xywh(3, 15, 19, 1));

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
		add(spTblCuentaBancaria, cc.xywh(3, 17, 19, 3));

		//---- lblCuentaBancaria ----
		lblCuentaBancaria.setText("Cuenta Bancaria: ");
		add(lblCuentaBancaria, cc.xy(3, 5));

		//---- txtCuentaBancaria ----
		txtCuentaBancaria.setEnabled(true);
		txtCuentaBancaria.setEditable(false);
		add(txtCuentaBancaria, cc.xywh(5, 5, 11, 1));
		add(btnBuscarCuentaBancaria, cc.xywh(17, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblClienteOficina ----
		lblClienteOficina.setText("Cliente Oficina:");
		add(lblClienteOficina, cc.xy(3, 7));

		//---- txtClienteOficina ----
		txtClienteOficina.setEditable(false);
		add(txtClienteOficina, cc.xywh(5, 7, 11, 1));
		add(btnBuscarClienteOficina, cc.xywh(17, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblDetallado ----
		lblDetallado.setText("Detallado: ");
		add(lblDetallado, cc.xy(3, 9));

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
		add(panel2, cc.xywh(5, 9, 3, 1));

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo Documento:");
		add(lblTipoDocumento, cc.xywh(9, 9, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoDocumento, cc.xywh(13, 9, 5, 1));

		//---- lblChequesAnulados ----
		lblChequesAnulados.setText("Cheques Anulados: ");
		add(lblChequesAnulados, cc.xy(3, 11));

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
		add(panel22, cc.xy(5, 11));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo de Proveedor:");
		add(lblTipoProveedor, cc.xywh(9, 11, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoProveedor, cc.xywh(13, 11, 5, 1));

		//---- lblVersionExtendida ----
		lblVersionExtendida.setText("Versi\u00f3n Extendida:");
		add(lblVersionExtendida, cc.xy(3, 13));

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
		add(panel23, cc.xy(5, 13));

		//---- lblTipoProducto ----
		lblTipoProducto.setText("Tipo de Producto:");
		add(lblTipoProducto, cc.xywh(9, 13, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoProducto, cc.xywh(13, 13, 5, 1));
		add(fsDetalle, cc.xywh(3, 21, 3, 1));

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
		add(spDetalle, cc.xywh(3, 23, 19, 3));

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
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JButton btnBuscarClienteOficina;
	private JLabel lblDetallado;
	private JPanel panel2;
	private JRadioButton rbSi;
	private JRadioButton rbNo;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblChequesAnulados;
	private JPanel panel22;
	private JRadioButton rbSiAnulados;
	private JRadioButton rbNo2;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JLabel lblVersionExtendida;
	private JPanel panel23;
	private JRadioButton rbSiVersionExtendida;
	private JRadioButton rbNoVersionExtendida;
	private JLabel lblTipoProducto;
	private JComboBox cmbTipoProducto;
	private JComponent fsDetalle;
	private JScrollPane spDetalle;
	private JTable tblDetalleConciliacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}

	public void setCmbTipoProducto(JComboBox cmbTipoProducto) {
		this.cmbTipoProducto = cmbTipoProducto;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public void setCmbTipoProveedor(JComboBox cmbTipoProveedor) {
		this.cmbTipoProveedor = cmbTipoProveedor;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public void setTxtClienteOficina(JTextField txtClienteOficina) {
		this.txtClienteOficina = txtClienteOficina;
	}

	public JButton getBtnBuscarClienteOficina() {
		return btnBuscarClienteOficina;
	}

	public void setBtnBuscarClienteOficina(JButton btnBuscarClienteOficina) {
		this.btnBuscarClienteOficina = btnBuscarClienteOficina;
	}

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
