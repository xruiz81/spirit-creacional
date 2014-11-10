package com.spirit.medios.gui.panel;
import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Wed Jul 20 17:12:30 COT 2011
 */
import com.spirit.client.model.ReportModelImpl;



/**
 * @author SHOCKIE
 */
public abstract class JPInversionPlanMedios extends ReportModelImpl {
	public JPInversionPlanMedios() {
		setName("Inversiones Plan Medio");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		label1 = new JLabel();
		rbAgruparClienteProductoMedios = new JRadioButton();
		rbAgruparClienteMedioProducto = new JRadioButton();
		rbAgruparMedioClienteProducto = new JRadioButton();
		separator1 = new JSeparator();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		label6 = new JLabel();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbClientesTodos = new JCheckBox();
		cbMostrarCliente = new JCheckBox();
		rbValorBruto = new JRadioButton();
		label7 = new JLabel();
		txtClienteOficina = new JTextField();
		btnClienteOficina = new JButton();
		cbClienteOficinaTodos = new JCheckBox();
		cbMostrarClienteOficina = new JCheckBox();
		rbValorNeto = new JRadioButton();
		label8 = new JLabel();
		cmbMarcas = new JComboBox();
		cbMostrarMarca = new JCheckBox();
		btnConsultar = new JButton();
		label4 = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		cbProductosTodos = new JCheckBox();
		cbMostrarProducto = new JCheckBox();
		label3 = new JLabel();
		cmbTipoMedio = new JComboBox();
		cbMostrarCampana = new JCheckBox();
		lblTipoProducto = new JLabel();
		cmbTipoProducto = new JComboBox();
		cbMostrarTipoMedio = new JCheckBox();
		label2 = new JLabel();
		txtMedio = new JTextField();
		btnMedio = new JButton();
		cbMediosTodos = new JCheckBox();
		cbMostrarMedio = new JCheckBox();
		label9 = new JLabel();
		txtMedioOficina = new JTextField();
		btnMedioOficina = new JButton();
		cbMedioOficinaTodos = new JCheckBox();
		cbMostrarMedioOficina = new JCheckBox();
		label10 = new JLabel();
		cbPautaRegular = new JCheckBox();
		cbAuspicio = new JCheckBox();
		cbMostrarTipoPauta = new JCheckBox();
		lblOficinaEmpresa = new JLabel();
		cmbOficinaEmpresa = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		cbVerAprobadosFacturados = new JCheckBox();
		cbMostrarDerechoPrograma = new JCheckBox();
		lblDerechoPrograma = new JLabel();
		cmbDerechoPrograma = new JComboBox();
		spTblInversiones = new JScrollPane();
		tblInversionesPlanMedios = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX4),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(43)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX4)
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(16)),
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
				new RowSpec(Sizes.DLUY8),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- label1 ----
		label1.setText("Agrupar por:");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label1, cc.xy(3, 3));

		//---- rbAgruparClienteProductoMedios ----
		rbAgruparClienteProductoMedios.setText("Cliente/Producto/T-Medios/Medios");
		rbAgruparClienteProductoMedios.setSelected(true);
		add(rbAgruparClienteProductoMedios, cc.xywh(5, 3, 5, 1));

		//---- rbAgruparClienteMedioProducto ----
		rbAgruparClienteMedioProducto.setText("Cliente/T-Medios/Medios/Producto");
		add(rbAgruparClienteMedioProducto, cc.xywh(11, 3, 9, 1));

		//---- rbAgruparMedioClienteProducto ----
		rbAgruparMedioClienteProducto.setText("T-Medios/Medios/Cliente/Producto");
		add(rbAgruparMedioClienteProducto, cc.xywh(23, 3, 5, 1));
		add(separator1, cc.xywh(3, 5, 25, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 7, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(13, 7, 7, 1));

		//---- label6 ----
		label6.setText("Datos a presentar:");
		label6.setHorizontalAlignment(SwingConstants.LEFT);
		add(label6, cc.xy(23, 7));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCliente, cc.xywh(5, 9, 7, 1));
		add(btnCliente, cc.xywh(13, 9, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbClientesTodos ----
		cbClientesTodos.setText("Todos");
		add(cbClientesTodos, cc.xywh(17, 9, 3, 1));

		//---- cbMostrarCliente ----
		cbMostrarCliente.setText("Cliente");
		add(cbMostrarCliente, cc.xy(23, 9));

		//---- rbValorBruto ----
		rbValorBruto.setText("Valor Bruto");
		add(rbValorBruto, cc.xy(27, 9));

		//---- label7 ----
		label7.setText("Cliente Oficina:");
		label7.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label7, cc.xy(3, 11));
		add(txtClienteOficina, cc.xywh(5, 11, 7, 1));
		add(btnClienteOficina, cc.xywh(13, 11, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbClienteOficinaTodos ----
		cbClienteOficinaTodos.setText("Todos");
		add(cbClienteOficinaTodos, cc.xywh(17, 11, 3, 1));

		//---- cbMostrarClienteOficina ----
		cbMostrarClienteOficina.setText("Cliente Oficina");
		add(cbMostrarClienteOficina, cc.xy(23, 11));

		//---- rbValorNeto ----
		rbValorNeto.setText("Valor Neto");
		add(rbValorNeto, cc.xy(27, 11));

		//---- label8 ----
		label8.setText("Marca:");
		label8.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label8, cc.xy(3, 13));
		add(cmbMarcas, cc.xywh(5, 13, 7, 1));

		//---- cbMostrarMarca ----
		cbMostrarMarca.setText("Marca");
		add(cbMostrarMarca, cc.xy(23, 13));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(27, 13, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- label4 ----
		label4.setText("Producto:");
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label4, cc.xy(3, 15));
		add(txtProducto, cc.xywh(5, 15, 7, 1));
		add(btnProducto, cc.xywh(13, 15, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbProductosTodos ----
		cbProductosTodos.setText("Todos");
		add(cbProductosTodos, cc.xywh(17, 15, 3, 1));

		//---- cbMostrarProducto ----
		cbMostrarProducto.setText("Producto");
		add(cbMostrarProducto, cc.xy(23, 15));

		//---- label3 ----
		label3.setText("Tipo de Medio:");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label3, cc.xy(3, 17));
		add(cmbTipoMedio, cc.xywh(5, 17, 7, 1));

		//---- cbMostrarCampana ----
		cbMostrarCampana.setText("Campa\u00f1a");
		add(cbMostrarCampana, cc.xy(23, 17));

		//---- lblTipoProducto ----
		lblTipoProducto.setText("Tipo de Producto:");
		add(lblTipoProducto, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoProducto, cc.xywh(5, 19, 7, 1));

		//---- cbMostrarTipoMedio ----
		cbMostrarTipoMedio.setText("Tipo de Medio");
		add(cbMostrarTipoMedio, cc.xy(23, 19));

		//---- label2 ----
		label2.setText("Medio:");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label2, cc.xy(3, 21));
		add(txtMedio, cc.xywh(5, 21, 7, 1));
		add(btnMedio, cc.xywh(13, 21, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbMediosTodos ----
		cbMediosTodos.setText("Todos");
		cbMediosTodos.setHorizontalAlignment(SwingConstants.LEFT);
		add(cbMediosTodos, cc.xywh(17, 21, 3, 1));

		//---- cbMostrarMedio ----
		cbMostrarMedio.setText("Medio");
		add(cbMostrarMedio, cc.xy(23, 21));

		//---- label9 ----
		label9.setText("Medio Oficina:");
		label9.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label9, cc.xy(3, 23));
		add(txtMedioOficina, cc.xywh(5, 23, 7, 1));
		add(btnMedioOficina, cc.xywh(13, 23, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbMedioOficinaTodos ----
		cbMedioOficinaTodos.setText("Todos");
		add(cbMedioOficinaTodos, cc.xywh(17, 23, 3, 1));

		//---- cbMostrarMedioOficina ----
		cbMostrarMedioOficina.setText("Medio Oficina");
		add(cbMostrarMedioOficina, cc.xy(23, 23));

		//---- label10 ----
		label10.setText("Tipo de Pauta:");
		label10.setHorizontalAlignment(SwingConstants.RIGHT);
		add(label10, cc.xy(3, 25));

		//---- cbPautaRegular ----
		cbPautaRegular.setText("Pauta Regular");
		add(cbPautaRegular, cc.xy(5, 25));

		//---- cbAuspicio ----
		cbAuspicio.setText("Auspicio");
		add(cbAuspicio, cc.xywh(9, 25, 3, 1));

		//---- cbMostrarTipoPauta ----
		cbMostrarTipoPauta.setText("Tipo de Pauta");
		add(cbMostrarTipoPauta, cc.xy(23, 25));

		//---- lblOficinaEmpresa ----
		lblOficinaEmpresa.setText("Oficina:");
		add(lblOficinaEmpresa, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficinaEmpresa, cc.xywh(5, 27, 7, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODOS",
			"COTIZADO",
			"APROBADO",
			"FACTURADO",
			"PREPAGADO"
		}));
		add(cmbEstado, cc.xy(5, 29));

		//---- cbVerAprobadosFacturados ----
		cbVerAprobadosFacturados.setText("Ver solo Aprobados y Facturados");
		add(cbVerAprobadosFacturados, cc.xywh(7, 29, 7, 1));

		//---- cbMostrarDerechoPrograma ----
		cbMostrarDerechoPrograma.setText("Derecho Programa");
		add(cbMostrarDerechoPrograma, cc.xy(23, 29));

		//---- lblDerechoPrograma ----
		lblDerechoPrograma.setText("Derecho de Programa:");
		lblDerechoPrograma.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDerechoPrograma, cc.xy(3, 31));
		add(cmbDerechoPrograma, cc.xywh(5, 31, 7, 1));

		//======== spTblInversiones ========
		{

			//---- tblInversionesPlanMedios ----
			tblInversionesPlanMedios.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Informaci\u00f3n", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Total"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, true, true, false, true, true, true
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblInversiones.setViewportView(tblInversionesPlanMedios);
		}
		add(spTblInversiones, cc.xywh(3, 35, 25, 11));

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbAgruparClienteProductoMedios);
		buttonGroup1.add(rbAgruparClienteMedioProducto);
		buttonGroup1.add(rbAgruparMedioClienteProducto);

		//---- buttonGroup2 ----
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rbValorBruto);
		buttonGroup2.add(rbValorNeto);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel label1;
	private JRadioButton rbAgruparClienteProductoMedios;
	private JRadioButton rbAgruparClienteMedioProducto;
	private JRadioButton rbAgruparMedioClienteProducto;
	private JSeparator separator1;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel label6;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbClientesTodos;
	private JCheckBox cbMostrarCliente;
	private JRadioButton rbValorBruto;
	private JLabel label7;
	private JTextField txtClienteOficina;
	private JButton btnClienteOficina;
	private JCheckBox cbClienteOficinaTodos;
	private JCheckBox cbMostrarClienteOficina;
	private JRadioButton rbValorNeto;
	private JLabel label8;
	private JComboBox cmbMarcas;
	private JCheckBox cbMostrarMarca;
	private JButton btnConsultar;
	private JLabel label4;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JCheckBox cbProductosTodos;
	private JCheckBox cbMostrarProducto;
	private JLabel label3;
	private JComboBox cmbTipoMedio;
	private JCheckBox cbMostrarCampana;
	private JLabel lblTipoProducto;
	private JComboBox cmbTipoProducto;
	private JCheckBox cbMostrarTipoMedio;
	private JLabel label2;
	private JTextField txtMedio;
	private JButton btnMedio;
	private JCheckBox cbMediosTodos;
	private JCheckBox cbMostrarMedio;
	private JLabel label9;
	private JTextField txtMedioOficina;
	private JButton btnMedioOficina;
	private JCheckBox cbMedioOficinaTodos;
	private JCheckBox cbMostrarMedioOficina;
	private JLabel label10;
	private JCheckBox cbPautaRegular;
	private JCheckBox cbAuspicio;
	private JCheckBox cbMostrarTipoPauta;
	private JLabel lblOficinaEmpresa;
	private JComboBox cmbOficinaEmpresa;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JCheckBox cbVerAprobadosFacturados;
	private JCheckBox cbMostrarDerechoPrograma;
	private JLabel lblDerechoPrograma;
	private JComboBox cmbDerechoPrograma;
	private JScrollPane spTblInversiones;
	private JTable tblInversionesPlanMedios;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}

	public JCheckBox getCbVerAprobadosFacturados() {
		return cbVerAprobadosFacturados;
	}

	public JScrollPane getSpTblInversiones() {
		return spTblInversiones;
	}

	public JRadioButton getRbValorBruto() {
		return rbValorBruto;
	}

	public JRadioButton getRbValorNeto() {
		return rbValorNeto;
	}

	public JLabel getLblOficinaEmpresa() {
		return lblOficinaEmpresa;
	}

	public JComboBox getCmbOficinaEmpresa() {
		return cmbOficinaEmpresa;
	}

	public JLabel getLblDerechoPrograma() {
		return lblDerechoPrograma;
	}

	public JRadioButton getRbAgruparClienteProductoMedios() {
		return rbAgruparClienteProductoMedios;
	}

	public void setRbAgruparClienteProductoMedios(
			JRadioButton rbAgruparClienteProductoMedios) {
		this.rbAgruparClienteProductoMedios = rbAgruparClienteProductoMedios;
	}

	public JRadioButton getRbAgruparClienteMedioProducto() {
		return rbAgruparClienteMedioProducto;
	}

	public void setRbAgruparClienteMedioProducto(
			JRadioButton rbAgruparClienteMedioProducto) {
		this.rbAgruparClienteMedioProducto = rbAgruparClienteMedioProducto;
	}

	public JRadioButton getRbAgruparMedioClienteProducto() {
		return rbAgruparMedioClienteProducto;
	}

	public void setRbAgruparMedioClienteProducto(
			JRadioButton rbAgruparMedioClienteProducto) {
		this.rbAgruparMedioClienteProducto = rbAgruparMedioClienteProducto;
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

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public void setBtnCliente(JButton btnCliente) {
		this.btnCliente = btnCliente;
	}

	public JCheckBox getCbClientesTodos() {
		return cbClientesTodos;
	}

	public void setCbClientesTodos(JCheckBox cbClientesTodos) {
		this.cbClientesTodos = cbClientesTodos;
	}

	public JTextField getTxtMedio() {
		return txtMedio;
	}

	public void setTxtMedio(JTextField txtMedio) {
		this.txtMedio = txtMedio;
	}

	public JButton getBtnMedio() {
		return btnMedio;
	}

	public void setBtnMedio(JButton btnMedio) {
		this.btnMedio = btnMedio;
	}

	public JCheckBox getCbMediosTodos() {
		return cbMediosTodos;
	}

	public void setCbMediosTodos(JCheckBox cbMediosTodos) {
		this.cbMediosTodos = cbMediosTodos;
	}

	public JCheckBox getCbMostrarCliente() {
		return cbMostrarCliente;
	}

	public void setCbMostrarCliente(JCheckBox cbMostrarCliente) {
		this.cbMostrarCliente = cbMostrarCliente;
	}

	public JComboBox getCmbTipoMedio() {
		return cmbTipoMedio;
	}

	public void setCmbTipoMedio(JComboBox cmbTipoMedio) {
		this.cmbTipoMedio = cmbTipoMedio;
	}

	public JCheckBox getCbMostrarMedio() {
		return cbMostrarMedio;
	}

	public void setCbMostrarMedio(JCheckBox cbMostrarMedio) {
		this.cbMostrarMedio = cbMostrarMedio;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}

	public JCheckBox getCbProductosTodos() {
		return cbProductosTodos;
	}

	public void setCbProductosTodos(JCheckBox cbProductosTodos) {
		this.cbProductosTodos = cbProductosTodos;
	}

	public JCheckBox getCbMostrarTipoMedio() {
		return cbMostrarTipoMedio;
	}

	public void setCbMostrarTipoMedio(JCheckBox cbMostrarTipoMedio) {
		this.cbMostrarTipoMedio = cbMostrarTipoMedio;
	}

	public JComboBox getCmbDerechoPrograma() {
		return cmbDerechoPrograma;
	}

	public void setCmbDerechoPrograma(JComboBox cmbDerechoPrograma) {
		this.cmbDerechoPrograma = cmbDerechoPrograma;
	}

	public JCheckBox getCbMostrarProducto() {
		return cbMostrarProducto;
	}

	public void setCbMostrarProducto(JCheckBox cbMostrarProducto) {
		this.cbMostrarProducto = cbMostrarProducto;
	}

	public JCheckBox getCbPautaRegular() {
		return cbPautaRegular;
	}

	public void setCbPautaRegular(JCheckBox cbPautaRegular) {
		this.cbPautaRegular = cbPautaRegular;
	}

	public JCheckBox getCbAuspicio() {
		return cbAuspicio;
	}

	public void setCbAuspicio(JCheckBox cbAuspicio) {
		this.cbAuspicio = cbAuspicio;
	}

	public JCheckBox getCbMostrarDerechoPrograma() {
		return cbMostrarDerechoPrograma;
	}

	public void setCbMostrarDerechoPrograma(JCheckBox cbMostrarDerechoPrograma) {
		this.cbMostrarDerechoPrograma = cbMostrarDerechoPrograma;
	}

	public JTable getTblInversionesPlanMedios() {
		return tblInversionesPlanMedios;
	}

	public void setTblInversionesPlanMedios(JTable tblInversionesPlanMedios) {
		this.tblInversionesPlanMedios = tblInversionesPlanMedios;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public void setTxtClienteOficina(JTextField txtClienteOficina) {
		this.txtClienteOficina = txtClienteOficina;
	}

	public JButton getBtnClienteOficina() {
		return btnClienteOficina;
	}

	public void setBtnClienteOficina(JButton btnClienteOficina) {
		this.btnClienteOficina = btnClienteOficina;
	}

	public JCheckBox getCbClienteOficinaTodos() {
		return cbClienteOficinaTodos;
	}

	public void setCbClienteOficinaTodos(JCheckBox cbClienteOficinaTodos) {
		this.cbClienteOficinaTodos = cbClienteOficinaTodos;
	}
	
	public JButton getBtnMedioOficina() {
		return btnMedioOficina;
	}

	public void setBtnMedioOficina(JButton btnMedioOficina) {
		this.btnMedioOficina = btnMedioOficina;
	}

	public JCheckBox getCbMedioOficinaTodos() {
		return cbMedioOficinaTodos;
	}

	public void setCbMedioOficinaTodos(JCheckBox cbMedioOficinaTodos) {
		this.cbMedioOficinaTodos = cbMedioOficinaTodos;
	}
	
	public JTextField getTxtMedioOficina() {
		return txtMedioOficina;
	}

	public void setTxtMedioOficina(JTextField txtMedioOficina) {
		this.txtMedioOficina = txtMedioOficina;
	}

	public JComboBox getCmbMarcas() {
		return cmbMarcas;
	}

	public void setCmbMarcas(JComboBox cmbMarcas) {
		this.cmbMarcas = cmbMarcas;
	}

	public JCheckBox getCbMostrarClienteOficina() {
		return cbMostrarClienteOficina;
	}

	public void setCbMostrarClienteOficina(JCheckBox cbMostrarClienteOficina) {
		this.cbMostrarClienteOficina = cbMostrarClienteOficina;
	}

	public JCheckBox getCbMostrarMarca() {
		return cbMostrarMarca;
	}

	public void setCbMostrarMarca(JCheckBox cbMostrarMarca) {
		this.cbMostrarMarca = cbMostrarMarca;
	}

	public JCheckBox getCbMostrarMedioOficina() {
		return cbMostrarMedioOficina;
	}

	public void setCbMostrarMedioOficina(JCheckBox cbMostrarMedioOficina) {
		this.cbMostrarMedioOficina = cbMostrarMedioOficina;
	}

	public JCheckBox getCbMostrarTipoPauta() {
		return cbMostrarTipoPauta;
	}

	public void setCbMostrarTipoPauta(JCheckBox cbMostrarTipoPauta) {
		this.cbMostrarTipoPauta = cbMostrarTipoPauta;
	}

	public JCheckBox getCbMostrarCampana() {
		return cbMostrarCampana;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}
	
}
