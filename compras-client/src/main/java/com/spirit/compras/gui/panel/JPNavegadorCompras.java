package com.spirit.compras.gui.panel;

import javax.swing.*;
import javax.swing.border.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
import com.jidesoft.swing.*;
import com.spirit.client.model.ReportModelImpl;
/*
 * Created by JFormDesigner on Thu Nov 22 11:36:46 COT 2007
 */
import com.spirit.client.model.SpiritResourceManager;

/**
 * @author xruiz
 */
public abstract class JPNavegadorCompras extends ReportModelImpl {
	public JPNavegadorCompras() {
		initComponents();
		setName("Navegador de Compras");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		spArbolNavegadorCompras = new JScrollPane();
		arbolNavegadorCompras = new JTree();
		titleCriteriosBusqueda = compFactory.createTitle("Criterios de B\u00fasqueda");
		panelCriteriosBusqueda = new JPanel();
		cbTodasCompras = new JCheckBox();
		cbProveedor = new JCheckBox();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		cbRangoFechas = new JCheckBox();
		cmbFechaInicio = new DateComboBox();
		lblAl = new JLabel();
		cmbFechaFin = new DateComboBox();
		cbEstado = new JCheckBox();
		cmbEstado = new JComboBox();
		btnConsultar = new JButton();
		jtpNavegadorCompras = new JideTabbedPane();
		spProveedor = new JScrollPane();
		panelProveedor = new JPanel();
		lblTipoIdentificacion = new JLabel();
		txtTipoIdentificacion = new JTextField();
		lblIdentificacion = new JLabel();
		txtIdentificacion = new JTextField();
		lblNombreLegal = new JLabel();
		txtNombreLegal = new JTextField();
		lblRazonSocial = new JLabel();
		txtRazonSocial = new JTextField();
		lblRepresentante = new JLabel();
		txtRepresentante = new JTextField();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		lblTipoProveedor = new JLabel();
		txtTipoProveedor = new JTextField();
		lblTipoNegocio = new JLabel();
		txtTipoNegocio = new JTextField();
		lblUsuarioFinal = new JLabel();
		txtUsuarioFinal = new JTextField();
		lblContribuyenteEspecial = new JLabel();
		txtContribuyenteEspecial = new JTextField();
		lblTipoPersona = new JLabel();
		txtTipoPersona = new JTextField();
		lblLlevaContabilidad = new JLabel();
		txtLlevaContabilidad = new JTextField();
		lblObservaciones = new JLabel();
		txtObservaciones = new JTextField();
		spCompra = new JScrollPane();
		panelCompra = new JPanel();
		lblCodigoCompra = new JLabel();
		txtCodigoCompra = new JTextField();
		lblFechaCreacionCompra = new JLabel();
		cmbFechaCreacionCompra = new DateComboBox();
		lblFechaVencimientoCompra = new JLabel();
		cmbFechaVencimientoCompra = new DateComboBox();
		lblOficinaCompra = new JLabel();
		txtOficinaCompra = new JTextField();
		lblProveedorCompra = new JLabel();
		txtProveedorCompra = new JTextField();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblMonedaCompra = new JLabel();
		txtMonedaCompra = new JTextField();
		lblTipoDocumentoCompra = new JLabel();
		txtTipoDocumentoCompra = new JTextField();
		lblTipoCompraCompra = new JLabel();
		txtTipoCompraCompra = new JTextField();
		lblEstadoCompra = new JLabel();
		txtEstadoCompra = new JTextField();
		lblTotal = new JLabel();
		txtTotal = new JTextField();
		lblObservacionCompra = new JLabel();
		txtObservacionCompra = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(370)),
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
				new RowSpec(Sizes.dluY(94)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//======== spArbolNavegadorCompras ========
		{
			spArbolNavegadorCompras.setViewportView(arbolNavegadorCompras);
		}
		add(spArbolNavegadorCompras, cc.xywh(3, 3, 5, 13));
		add(titleCriteriosBusqueda, cc.xywh(11, 3, 6, 1));

		//======== panelCriteriosBusqueda ========
		{
			panelCriteriosBusqueda.setBorder(new EtchedBorder());
			panelCriteriosBusqueda.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(95)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(95)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(40)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
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
					FormFactory.DEFAULT_ROWSPEC
				}));

			//---- cbTodasCompras ----
			cbTodasCompras.setText("Todas las Compras");
			panelCriteriosBusqueda.add(cbTodasCompras, cc.xy(3, 3));

			//---- cbProveedor ----
			cbProveedor.setText("Por Proveedor:");
			panelCriteriosBusqueda.add(cbProveedor, cc.xy(3, 5));
			panelCriteriosBusqueda.add(txtProveedor, cc.xywh(5, 5, 7, 1));
			panelCriteriosBusqueda.add(btnProveedor, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- cbRangoFechas ----
			cbRangoFechas.setText("Rango de Fechas:");
			panelCriteriosBusqueda.add(cbRangoFechas, cc.xy(3, 7));
			panelCriteriosBusqueda.add(cmbFechaInicio, cc.xy(5, 7));

			//---- lblAl ----
			lblAl.setText("al:");
			panelCriteriosBusqueda.add(lblAl, cc.xy(7, 7));
			panelCriteriosBusqueda.add(cmbFechaFin, cc.xy(9, 7));

			//---- cbEstado ----
			cbEstado.setText("Estado:");
			panelCriteriosBusqueda.add(cbEstado, cc.xy(3, 9));
			panelCriteriosBusqueda.add(cmbEstado, cc.xy(5, 9));

			//---- btnConsultar ----
			btnConsultar.setText("Consultar");
			panelCriteriosBusqueda.add(btnConsultar, cc.xy(3, 11));
		}
		add(panelCriteriosBusqueda, cc.xywh(11, 5, 5, 5));

		//======== jtpNavegadorCompras ========
		{

			//======== spProveedor ========
			{

				//======== panelProveedor ========
				{
					panelProveedor.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						new RowSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC
						}));

					//---- lblTipoIdentificacion ----
					lblTipoIdentificacion.setText("Tipo Identificaci\u00f3n:");
					panelProveedor.add(lblTipoIdentificacion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtTipoIdentificacion, cc.xy(5, 3));

					//---- lblIdentificacion ----
					lblIdentificacion.setText("Identificaci\u00f3n:");
					panelProveedor.add(lblIdentificacion, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtIdentificacion, cc.xy(11, 3));

					//---- lblNombreLegal ----
					lblNombreLegal.setText("Nombre Legal:");
					panelProveedor.add(lblNombreLegal, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtNombreLegal, cc.xywh(5, 5, 7, 1));

					//---- lblRazonSocial ----
					lblRazonSocial.setText("Raz\u00f3n Social:");
					panelProveedor.add(lblRazonSocial, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtRazonSocial, cc.xywh(5, 7, 7, 1));

					//---- lblRepresentante ----
					lblRepresentante.setText("Representante:");
					panelProveedor.add(lblRepresentante, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtRepresentante, cc.xywh(5, 9, 7, 1));

					//---- lblCorporacion ----
					lblCorporacion.setText("Corporaci\u00f3n:");
					panelProveedor.add(lblCorporacion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtCorporacion, cc.xywh(5, 11, 7, 1));

					//---- lblTipoProveedor ----
					lblTipoProveedor.setText("Tipo Proveedor:");
					panelProveedor.add(lblTipoProveedor, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtTipoProveedor, cc.xy(5, 13));

					//---- lblTipoNegocio ----
					lblTipoNegocio.setText("Tipo Negocio:");
					panelProveedor.add(lblTipoNegocio, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtTipoNegocio, cc.xy(11, 13));

					//---- lblUsuarioFinal ----
					lblUsuarioFinal.setText("Usuario Final:");
					panelProveedor.add(lblUsuarioFinal, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtUsuarioFinal, cc.xy(5, 15));

					//---- lblContribuyenteEspecial ----
					lblContribuyenteEspecial.setText("Contribuyente Especial:");
					panelProveedor.add(lblContribuyenteEspecial, cc.xy(9, 15));
					panelProveedor.add(txtContribuyenteEspecial, cc.xy(11, 15));

					//---- lblTipoPersona ----
					lblTipoPersona.setText("Tipo Persona:");
					panelProveedor.add(lblTipoPersona, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtTipoPersona, cc.xy(5, 17));

					//---- lblLlevaContabilidad ----
					lblLlevaContabilidad.setText("Lleva Contabilidad:");
					panelProveedor.add(lblLlevaContabilidad, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtLlevaContabilidad, cc.xy(11, 17));

					//---- lblObservaciones ----
					lblObservaciones.setText("Observaciones:");
					panelProveedor.add(lblObservaciones, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtObservaciones, cc.xywh(5, 19, 7, 1));
				}
				spProveedor.setViewportView(panelProveedor);
			}
			jtpNavegadorCompras.addTab("Proveedor", spProveedor);


			//======== spCompra ========
			{

				//======== panelCompra ========
				{
					panelCompra.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						new RowSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC
						}));

					//---- lblCodigoCompra ----
					lblCodigoCompra.setText("C\u00f3digo:");
					panelCompra.add(lblCodigoCompra, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtCodigoCompra, cc.xy(5, 3));

					//---- lblFechaCreacionCompra ----
					lblFechaCreacionCompra.setText("Fecha Creaci\u00f3n:");
					panelCompra.add(lblFechaCreacionCompra, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(cmbFechaCreacionCompra, cc.xy(5, 5));

					//---- lblFechaVencimientoCompra ----
					lblFechaVencimientoCompra.setText("Fecha Vencimiento:");
					panelCompra.add(lblFechaVencimientoCompra, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(cmbFechaVencimientoCompra, cc.xy(11, 5));

					//---- lblOficinaCompra ----
					lblOficinaCompra.setText("Oficina:");
					panelCompra.add(lblOficinaCompra, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtOficinaCompra, cc.xywh(5, 7, 7, 1));

					//---- lblProveedorCompra ----
					lblProveedorCompra.setText("Proveedor:");
					panelCompra.add(lblProveedorCompra, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtProveedorCompra, cc.xywh(5, 9, 7, 1));

					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panelCompra.add(lblReferencia, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtReferencia, cc.xy(5, 11));

					//---- lblMonedaCompra ----
					lblMonedaCompra.setText("Moneda:");
					panelCompra.add(lblMonedaCompra, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtMonedaCompra, cc.xy(11, 11));

					//---- lblTipoDocumentoCompra ----
					lblTipoDocumentoCompra.setText("Tipo Documento:");
					panelCompra.add(lblTipoDocumentoCompra, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtTipoDocumentoCompra, cc.xy(5, 13));

					//---- lblTipoCompraCompra ----
					lblTipoCompraCompra.setText("Tipo de Compra:");
					panelCompra.add(lblTipoCompraCompra, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtTipoCompraCompra, cc.xy(11, 13));

					//---- lblEstadoCompra ----
					lblEstadoCompra.setText("Estado:");
					panelCompra.add(lblEstadoCompra, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtEstadoCompra, cc.xy(5, 15));

					//---- lblTotal ----
					lblTotal.setText("Total:");
					panelCompra.add(lblTotal, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtTotal, cc.xy(11, 15));

					//---- lblObservacionCompra ----
					lblObservacionCompra.setText("Observaci\u00f3n:");
					panelCompra.add(lblObservacionCompra, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCompra.add(txtObservacionCompra, cc.xywh(5, 17, 7, 1));
				}
				spCompra.setViewportView(panelCompra);
			}
			jtpNavegadorCompras.addTab("Compra", spCompra);

		}
		add(jtpNavegadorCompras, cc.xywh(11, 11, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents

	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spArbolNavegadorCompras;
	private JTree arbolNavegadorCompras;
	private JLabel titleCriteriosBusqueda;
	private JPanel panelCriteriosBusqueda;
	private JCheckBox cbTodasCompras;
	private JCheckBox cbProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JCheckBox cbRangoFechas;
	private DateComboBox cmbFechaInicio;
	private JLabel lblAl;
	private DateComboBox cmbFechaFin;
	private JCheckBox cbEstado;
	private JComboBox cmbEstado;
	private JButton btnConsultar;
	private JideTabbedPane jtpNavegadorCompras;
	private JScrollPane spProveedor;
	private JPanel panelProveedor;
	private JLabel lblTipoIdentificacion;
	private JTextField txtTipoIdentificacion;
	private JLabel lblIdentificacion;
	private JTextField txtIdentificacion;
	private JLabel lblNombreLegal;
	private JTextField txtNombreLegal;
	private JLabel lblRazonSocial;
	private JTextField txtRazonSocial;
	private JLabel lblRepresentante;
	private JTextField txtRepresentante;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JLabel lblTipoProveedor;
	private JTextField txtTipoProveedor;
	private JLabel lblTipoNegocio;
	private JTextField txtTipoNegocio;
	private JLabel lblUsuarioFinal;
	private JTextField txtUsuarioFinal;
	private JLabel lblContribuyenteEspecial;
	private JTextField txtContribuyenteEspecial;
	private JLabel lblTipoPersona;
	private JTextField txtTipoPersona;
	private JLabel lblLlevaContabilidad;
	private JTextField txtLlevaContabilidad;
	private JLabel lblObservaciones;
	private JTextField txtObservaciones;
	private JScrollPane spCompra;
	private JPanel panelCompra;
	private JLabel lblCodigoCompra;
	private JTextField txtCodigoCompra;
	private JLabel lblFechaCreacionCompra;
	private DateComboBox cmbFechaCreacionCompra;
	private JLabel lblFechaVencimientoCompra;
	private DateComboBox cmbFechaVencimientoCompra;
	private JLabel lblOficinaCompra;
	private JTextField txtOficinaCompra;
	private JLabel lblProveedorCompra;
	private JTextField txtProveedorCompra;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblMonedaCompra;
	private JTextField txtMonedaCompra;
	private JLabel lblTipoDocumentoCompra;
	private JTextField txtTipoDocumentoCompra;
	private JLabel lblTipoCompraCompra;
	private JTextField txtTipoCompraCompra;
	private JLabel lblEstadoCompra;
	private JTextField txtEstadoCompra;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JLabel lblObservacionCompra;
	private JTextField txtObservacionCompra;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JTree getArbolNavegadorCompras() {
		return arbolNavegadorCompras;
	}

	public void setArbolNavegadorCompras(JTree arbolNavegadorCompras) {
		this.arbolNavegadorCompras = arbolNavegadorCompras;
	}

	public JCheckBox getCbEstado() {
		return cbEstado;
	}

	public void setCbEstado(JCheckBox cbEstado) {
		this.cbEstado = cbEstado;
	}

	public JCheckBox getCbProveedor() {
		return cbProveedor;
	}

	public void setCbProveedor(JCheckBox cbProveedor) {
		this.cbProveedor = cbProveedor;
	}

	public JCheckBox getCbRangoFechas() {
		return cbRangoFechas;
	}

	public void setCbRangoFechas(JCheckBox cbRangoFechas) {
		this.cbRangoFechas = cbRangoFechas;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFechaCreacionCompra() {
		return cmbFechaCreacionCompra;
	}

	public void setCmbFechaCreacionCompra(DateComboBox cmbFechaCreacionCompra) {
		this.cmbFechaCreacionCompra = cmbFechaCreacionCompra;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public DateComboBox getCmbFechaVencimientoCompra() {
		return cmbFechaVencimientoCompra;
	}

	public void setCmbFechaVencimientoCompra(DateComboBox cmbFechaVencimientoCompra) {
		this.cmbFechaVencimientoCompra = cmbFechaVencimientoCompra;
	}

	public JideTabbedPane getJtpNavegadorCompras() {
		return jtpNavegadorCompras;
	}

	public void setJtpNavegadorCompras(JideTabbedPane jtpNavegadorCompras) {
		this.jtpNavegadorCompras = jtpNavegadorCompras;
	}

	public JPanel getPanelCompra() {
		return panelCompra;
	}

	public void setPanelCompra(JPanel panelCompra) {
		this.panelCompra = panelCompra;
	}

	public JPanel getPanelCriteriosBusqueda() {
		return panelCriteriosBusqueda;
	}

	public void setPanelCriteriosBusqueda(JPanel panelCriteriosBusqueda) {
		this.panelCriteriosBusqueda = panelCriteriosBusqueda;
	}

	public JPanel getPanelProveedor() {
		return panelProveedor;
	}

	public void setPanelProveedor(JPanel panelProveedor) {
		this.panelProveedor = panelProveedor;
	}

	public JScrollPane getSpArbolNavegadorCompras() {
		return spArbolNavegadorCompras;
	}

	public void setSpArbolNavegadorCompras(JScrollPane spArbolNavegadorCompras) {
		this.spArbolNavegadorCompras = spArbolNavegadorCompras;
	}

	public JScrollPane getSpCompra() {
		return spCompra;
	}

	public void setSpCompra(JScrollPane spCompra) {
		this.spCompra = spCompra;
	}

	public JScrollPane getSpProveedor() {
		return spProveedor;
	}

	public void setSpProveedor(JScrollPane spProveedor) {
		this.spProveedor = spProveedor;
	}

	public JLabel getTitleCriteriosBusqueda() {
		return titleCriteriosBusqueda;
	}

	public void setTitleCriteriosBusqueda(JLabel titleCriteriosBusqueda) {
		this.titleCriteriosBusqueda = titleCriteriosBusqueda;
	}

	public JTextField getTxtCodigoCompra() {
		return txtCodigoCompra;
	}

	public void setTxtCodigoCompra(JTextField txtCodigoCompra) {
		this.txtCodigoCompra = txtCodigoCompra;
	}

	public JTextField getTxtContribuyenteEspecial() {
		return txtContribuyenteEspecial;
	}

	public void setTxtContribuyenteEspecial(JTextField txtContribuyenteEspecial) {
		this.txtContribuyenteEspecial = txtContribuyenteEspecial;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtEstadoCompra() {
		return txtEstadoCompra;
	}

	public void setTxtEstadoCompra(JTextField txtEstadoCompra) {
		this.txtEstadoCompra = txtEstadoCompra;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public JTextField getTxtLlevaContabilidad() {
		return txtLlevaContabilidad;
	}

	public void setTxtLlevaContabilidad(JTextField txtLlevaContabilidad) {
		this.txtLlevaContabilidad = txtLlevaContabilidad;
	}

	public JTextField getTxtMonedaCompra() {
		return txtMonedaCompra;
	}

	public void setTxtMonedaCompra(JTextField txtMonedaCompra) {
		this.txtMonedaCompra = txtMonedaCompra;
	}

	public JTextField getTxtNombreLegal() {
		return txtNombreLegal;
	}

	public void setTxtNombreLegal(JTextField txtNombreLegal) {
		this.txtNombreLegal = txtNombreLegal;
	}

	public JTextField getTxtObservacionCompra() {
		return txtObservacionCompra;
	}

	public void setTxtObservacionCompra(JTextField txtObservacionCompra) {
		this.txtObservacionCompra = txtObservacionCompra;
	}

	public JTextField getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(JTextField txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}

	public JTextField getTxtOficinaCompra() {
		return txtOficinaCompra;
	}

	public void setTxtOficinaCompra(JTextField txtOficinaCompra) {
		this.txtOficinaCompra = txtOficinaCompra;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JTextField getTxtProveedorCompra() {
		return txtProveedorCompra;
	}

	public void setTxtProveedorCompra(JTextField txtProveedorCompra) {
		this.txtProveedorCompra = txtProveedorCompra;
	}

	public JTextField getTxtRazonSocial() {
		return txtRazonSocial;
	}

	public void setTxtRazonSocial(JTextField txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}

	public JTextField getTxtRepresentante() {
		return txtRepresentante;
	}

	public void setTxtRepresentante(JTextField txtRepresentante) {
		this.txtRepresentante = txtRepresentante;
	}

	public JTextField getTxtTipoCompraCompra() {
		return txtTipoCompraCompra;
	}

	public void setTxtTipoCompraCompra(JTextField txtTipoCompraCompra) {
		this.txtTipoCompraCompra = txtTipoCompraCompra;
	}

	public JTextField getTxtTipoDocumentoCompra() {
		return txtTipoDocumentoCompra;
	}

	public void setTxtTipoDocumentoCompra(JTextField txtTipoDocumentoCompra) {
		this.txtTipoDocumentoCompra = txtTipoDocumentoCompra;
	}

	public JTextField getTxtTipoIdentificacion() {
		return txtTipoIdentificacion;
	}

	public void setTxtTipoIdentificacion(JTextField txtTipoIdentificacion) {
		this.txtTipoIdentificacion = txtTipoIdentificacion;
	}

	public JTextField getTxtTipoNegocio() {
		return txtTipoNegocio;
	}

	public void setTxtTipoNegocio(JTextField txtTipoNegocio) {
		this.txtTipoNegocio = txtTipoNegocio;
	}

	public JTextField getTxtTipoPersona() {
		return txtTipoPersona;
	}

	public void setTxtTipoPersona(JTextField txtTipoPersona) {
		this.txtTipoPersona = txtTipoPersona;
	}

	public JTextField getTxtTipoProveedor() {
		return txtTipoProveedor;
	}

	public void setTxtTipoProveedor(JTextField txtTipoProveedor) {
		this.txtTipoProveedor = txtTipoProveedor;
	}

	public JTextField getTxtUsuarioFinal() {
		return txtUsuarioFinal;
	}

	public void setTxtUsuarioFinal(JTextField txtUsuarioFinal) {
		this.txtUsuarioFinal = txtUsuarioFinal;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public void setBtnProveedor(JButton btnProveedor) {
		this.btnProveedor = btnProveedor;
	}

	public JCheckBox getCbTodasCompras() {
		return cbTodasCompras;
	}

	public void setCbTodasCompras(JCheckBox cbTodasCompras) {
		this.cbTodasCompras = cbTodasCompras;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}
}
