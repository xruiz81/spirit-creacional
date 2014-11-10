package com.spirit.crm.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPOperadoresNegocio extends SpiritModelImpl {
	public JPOperadoresNegocio() {
		setName("Operadores de Negocio");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpTabsAdministracionCliente = new JideTabbedPane();
		spPanelSubTabCliente = new JScrollPane();
		panelSubTabCliente = new JPanel();
		tpTabsCliente = new JTabbedPane();
		panelRetencion = new JPanel();
		spTblRetenciones = new JScrollPane();
		tblRetenciones = new JTable();
		lblRetencionRenta = new JLabel();
		cmbRetencionRenta = new JComboBox();
		lblRetencionIVA = new JLabel();
		cmbRetencionIVA = new JComboBox();
		panel6 = new JPanel();
		btnAgregarRetencion = new JButton();
		btnActualizarRetencion = new JButton();
		btnEliminarRetencion = new JButton();
		btnProgramador = new JButton();
		panelZona = new JPanel();
		txtCodigoZona = new JTextField();
		txtNombreZona = new JTextField();
		spDetalleZona = new JScrollPane();
		tblDetalleZona = new JTable();
		lblCodigoZona = new JLabel();
		lblNombreZona = new JLabel();
		panel2 = new JPanel();
		btnAgregarDetalleZona = new JButton();
		btnActualizarDetalleZona = new JButton();
		btnEliminarDetalleZona = new JButton();
		panelCliente = new JPanel();
		lblTipoCliente = new JLabel();
		cmbTipoCliente = new JComboBox();
		lblFechaCreacionCliente = new JLabel();
		txtFechaCreacionCliente = new JTextField();
		lblTipoIdentificacionCliente = new JLabel();
		cmbTipoIdentificacionCliente = new JComboBox();
		lblEstadoCliente = new JLabel();
		cmbEstadoCliente = new JComboBox();
		lblNombreLegalCliente = new JLabel();
		lblIdentificacionCliente = new JLabel();
		txtIdentificacionCliente = new JTextField();
		txtNombreLegalCliente = new JTextField();
		lblRazonSocialCliente = new JLabel();
		txtRazonSocialCliente = new JTextField();
		txtRepresentanteCliente = new JTextField();
		lblCorporacionCliente = new JLabel();
		txtCorporacionCliente = new JTextField();
		lblRepresentanteCliente = new JLabel();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		btnBuscarCorporacionCliente = new JButton();
		lblTipoNegocioCliente = new JLabel();
		lblUsuarioFinal = new JLabel();
		cmbTipoNegocioCliente = new JComboBox();
		cmbUsuarioFinal = new JComboBox();
		lblBanco = new JLabel();
		cmbBanco = new JComboBox();
		lblContribuyenteEspecial = new JLabel();
		cmbContribuyenteEspecial = new JComboBox();
		lblTipoCuenta = new JLabel();
		cmbTipoCuenta = new JComboBox();
		lblTipoPersona = new JLabel();
		cmbTipoPersona = new JComboBox();
		lblNumeroCuenta = new JLabel();
		txtNumeroCuenta = new JTextField();
		lblLlevaContabilidad = new JLabel();
		cmbLlevaContabilidad = new JComboBox();
		lblRequiereSap = new JLabel();
		cmbRequiereSap = new JComboBox();
		lblObservacionesCliente = new JLabel();
		lblinformacionAdc = new JLabel();
		txtinformacionAdc = new JTextField();
		txtObservacionesCliente = new JTextField();
		spPanelSubTabOficina = new JScrollPane();
		panelSubTabOficina = new JPanel();
		tpTabsOficina = new JTabbedPane();
		panelOficina = new JPanel();
		txtCodigoClienteOficina = new JTextField();
		txtDescripcionClienteOficina = new JTextField();
		txtCiudadClienteOficina = new JTextField();
		txtTelefonoClienteOficina = new JTextField();
		spOficinasCliente = new JScrollPane();
		tblOficinasCliente = new JTable();
		lblFechaFechaCreacionClienteOficina = new JLabel();
		txtFechaCreacionClienteOficina = new JTextField();
		lblCodigoClienteOficina = new JLabel();
		lblDescripcionClienteOficina = new JLabel();
		lblEstadoClienteOficina = new JLabel();
		cmbEstadoClienteOficina = new JComboBox();
		btnCiudadesClienteOficina = new JButton();
		lblCiudadClienteOficina = new JLabel();
		lblDireccionClienteOficina = new JLabel();
		txtDireccionClienteOficina = new JTextField();
		lblTelefonoClienteOficina = new JLabel();
		lblFaxClienteOficina = new JLabel();
		txtFaxClienteOficina = new JTextField();
		txtEmailClienteOficina = new JTextField();
		lblEmailClienteOficina = new JLabel();
		lblObservacionClienteOficina = new JLabel();
		lblCalificacionClienteOficina = new JLabel();
		cmbCalificacionClienteOficina = new JComboBox();
		lblMontoCreditoClienteOficina = new JLabel();
		txtObservacionClienteOficina = new JTextField();
		lblMontoGarantiaClienteOficina = new JLabel();
		txtMontoGarantiaClienteOficina = new JTextField();
		txtMontoCreditoClienteOficina = new JTextField();
		panel3 = new JPanel();
		btnAgregarClienteOficina = new JButton();
		btnActualizarClienteOficina = new JButton();
		btnEliminarClienteOficina = new JButton();
		panelContacto = new JPanel();
		cmbTipoContacto = new JComboBox();
		txtDireccionContacto = new JTextField();
		spDetalleContacto = new JScrollPane();
		tblDetalleContacto = new JTable();
		lblTipoContacto = new JLabel();
		lblNombreContacto = new JLabel();
		txtNombreContacto = new JTextField();
		lblDireccionContacto = new JLabel();
		lblTelefonoCasaContacto = new JLabel();
		lblTelefonoOficContacto = new JLabel();
		txtTelefonoOficContacto = new JTextField();
		lblCelularContacto = new JLabel();
		txtCelularContacto = new JTextField();
		lblMailContacto = new JLabel();
		txtMailContacto = new JTextField();
		lblCumpleanosContacto = new JLabel();
		cmbCumpleanosContacto = new DateComboBox();
		txtTelefonoCasaContacto = new JTextField();
		panel4 = new JPanel();
		btnAgregarDetalleContacto = new JButton();
		btnActualizarDetalleContacto = new JButton();
		btnEliminarDetalleContacto = new JButton();
		panelIndicador = new JPanel();
		cmbTipoIndicador = new JComboBox();
		txtValorIndicador = new JTextField();
		spDetalleIndicador = new JScrollPane();
		tblDetalleIndicador = new JTable();
		lblTipoIndicador = new JLabel();
		lblValorIndicador = new JLabel();
		panel5 = new JPanel();
		btnAgregarDetalleIndicador = new JButton();
		btnActualizarDetalleIndicador = new JButton();
		btnEliminarDetalleIndicador = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpTabsAdministracionCliente ========
		{

			//======== spPanelSubTabCliente ========
			{

				//======== panelSubTabCliente ========
				{
					panelSubTabCliente.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX6),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX6)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY6),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));

					//======== tpTabsCliente ========
					{

						//======== panelCliente ========
						{
							panelCliente.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX6),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(100)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(80)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(45)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.DLUX6)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY6),
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
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.dluY(10))
								}));

							//---- lblTipoCliente ----
							lblTipoCliente.setText("Tipo Operador:");
							panelCliente.add(lblTipoCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbTipoCliente, cc.xy(5, 3));

							//---- lblFechaCreacionCliente ----
							lblFechaCreacionCliente.setText("Fecha Creaci\u00f3n: ");
							panelCliente.add(lblFechaCreacionCliente, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(txtFechaCreacionCliente, cc.xywh(13, 3, 3, 1));

							//---- lblTipoIdentificacionCliente ----
							lblTipoIdentificacionCliente.setText("Tipo Identificaci\u00f3n:");
							panelCliente.add(lblTipoIdentificacionCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbTipoIdentificacionCliente, cc.xy(5, 5));

							//---- lblEstadoCliente ----
							lblEstadoCliente.setText("Estado:");
							panelCliente.add(lblEstadoCliente, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbEstadoCliente, cc.xy(13, 5));

							//---- lblNombreLegalCliente ----
							lblNombreLegalCliente.setText("Nombre Legal:");
							panelCliente.add(lblNombreLegalCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblIdentificacionCliente ----
							lblIdentificacionCliente.setText("Identificaci\u00f3n:");
							panelCliente.add(lblIdentificacionCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(txtIdentificacionCliente, cc.xy(5, 7));
							panelCliente.add(txtNombreLegalCliente, cc.xywh(5, 9, 3, 1));

							//---- lblRazonSocialCliente ----
							lblRazonSocialCliente.setText("Raz\u00f3n Social:");
							panelCliente.add(lblRazonSocialCliente, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(txtRazonSocialCliente, cc.xywh(5, 11, 3, 1));
							panelCliente.add(txtRepresentanteCliente, cc.xywh(5, 13, 3, 1));

							//---- lblCorporacionCliente ----
							lblCorporacionCliente.setText("Corporaci\u00f3n");
							panelCliente.add(lblCorporacionCliente, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(txtCorporacionCliente, cc.xywh(5, 15, 3, 1));

							//---- lblRepresentanteCliente ----
							lblRepresentanteCliente.setText("Representante:");
							panelCliente.add(lblRepresentanteCliente, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblTipoProveedor ----
							lblTipoProveedor.setText("Tipo Proveedor:");
							panelCliente.add(lblTipoProveedor, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbTipoProveedor, cc.xywh(5, 17, 3, 1));

							//---- btnBuscarCorporacionCliente ----
							btnBuscarCorporacionCliente.setToolTipText("Buscar Corporacion");
							panelCliente.add(btnBuscarCorporacionCliente, cc.xywh(9, 15, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

							//---- lblTipoNegocioCliente ----
							lblTipoNegocioCliente.setText("Tipo Negocio:");
							panelCliente.add(lblTipoNegocioCliente, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblUsuarioFinal ----
							lblUsuarioFinal.setText("Usuario Final:");
							panelCliente.add(lblUsuarioFinal, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbTipoNegocioCliente, cc.xywh(5, 19, 3, 1));

							//---- cmbUsuarioFinal ----
							cmbUsuarioFinal.setModel(new DefaultComboBoxModel(new String[] {
								"NO",
								"SI"
							}));
							panelCliente.add(cmbUsuarioFinal, cc.xy(5, 21));

							//---- lblBanco ----
							lblBanco.setText("Banco:");
							panelCliente.add(lblBanco, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbBanco, cc.xywh(13, 21, 5, 1));

							//---- lblContribuyenteEspecial ----
							lblContribuyenteEspecial.setText("Contribuyente Especial:");
							panelCliente.add(lblContribuyenteEspecial, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- cmbContribuyenteEspecial ----
							cmbContribuyenteEspecial.setModel(new DefaultComboBoxModel(new String[] {
								"SI",
								"NO"
							}));
							panelCliente.add(cmbContribuyenteEspecial, cc.xy(5, 23));

							//---- lblTipoCuenta ----
							lblTipoCuenta.setText("Tipo de Cuenta:");
							panelCliente.add(lblTipoCuenta, cc.xywh(11, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(cmbTipoCuenta, cc.xywh(13, 23, 3, 1));

							//---- lblTipoPersona ----
							lblTipoPersona.setText("Tipo Persona:");
							panelCliente.add(lblTipoPersona, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- cmbTipoPersona ----
							cmbTipoPersona.setModel(new DefaultComboBoxModel(new String[] {
								"NATURAL",
								"JURIDICA"
							}));
							panelCliente.add(cmbTipoPersona, cc.xy(5, 25));

							//---- lblNumeroCuenta ----
							lblNumeroCuenta.setText("# Cuenta:");
							panelCliente.add(lblNumeroCuenta, cc.xywh(11, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelCliente.add(txtNumeroCuenta, cc.xywh(13, 25, 3, 1));

							//---- lblLlevaContabilidad ----
							lblLlevaContabilidad.setText("Lleva Contabilidad:");
							panelCliente.add(lblLlevaContabilidad, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- cmbLlevaContabilidad ----
							cmbLlevaContabilidad.setModel(new DefaultComboBoxModel(new String[] {
								"SI",
								"NO"
							}));
							panelCliente.add(cmbLlevaContabilidad, cc.xy(5, 27));

							//---- lblRequiereSap ----
							lblRequiereSap.setText("Requiere # SAP para Facturar:");
							panelCliente.add(lblRequiereSap, cc.xywh(3, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- cmbRequiereSap ----
							cmbRequiereSap.setModel(new DefaultComboBoxModel(new String[] {
								"SI",
								"NO"
							}));
							panelCliente.add(cmbRequiereSap, cc.xy(5, 29));

							//---- lblObservacionesCliente ----
							lblObservacionesCliente.setText("Observaciones:");
							panelCliente.add(lblObservacionesCliente, cc.xywh(3, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblinformacionAdc ----
							lblinformacionAdc.setText("Informaci\u00f3n adicional (cobros/pagos):");
							panelCliente.add(lblinformacionAdc, cc.xy(3, 33));
							panelCliente.add(txtinformacionAdc, cc.xywh(5, 33, 7, 1));
							panelCliente.add(txtObservacionesCliente, cc.xywh(5, 31, 7, 1));
						}
						tpTabsCliente.addTab("General", panelCliente);


						//======== panelRetencion ========
						{
							panelRetencion.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(12)),
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
									new RowSpec(Sizes.DLUY6),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5),
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

							//======== spTblRetenciones ========
							{

								//---- tblRetenciones ----
								tblRetenciones.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"Retenci\u00f3n Renta", "Retenci\u00f3n IVA"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								tblRetenciones.setPreferredScrollableViewportSize(new Dimension(450, 150));
								tblRetenciones.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
								tblRetenciones.setAutoCreateColumnsFromModel(true);
								spTblRetenciones.setViewportView(tblRetenciones);
							}
							panelRetencion.add(spTblRetenciones, cc.xywh(3, 11, 5, 5));

							//---- lblRetencionRenta ----
							lblRetencionRenta.setText("Retenci\u00f3n Renta [%]:");
							panelRetencion.add(lblRetencionRenta, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
							panelRetencion.add(cmbRetencionRenta, cc.xy(5, 3));

							//---- lblRetencionIVA ----
							lblRetencionIVA.setText("Retenci\u00f3n IVA [%]:");
							panelRetencion.add(lblRetencionIVA, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
							panelRetencion.add(cmbRetencionIVA, cc.xy(5, 5));

							//======== panel6 ========
							{
								panel6.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									RowSpec.decodeSpecs("default")));

								//---- btnAgregarRetencion ----
								btnAgregarRetencion.setText("A");
								panel6.add(btnAgregarRetencion, cc.xy(1, 1));

								//---- btnActualizarRetencion ----
								btnActualizarRetencion.setText("U");
								panel6.add(btnActualizarRetencion, cc.xy(3, 1));

								//---- btnEliminarRetencion ----
								btnEliminarRetencion.setText("D");
								panel6.add(btnEliminarRetencion, cc.xy(5, 1));

								//---- btnProgramador ----
								btnProgramador.setText("Programador");
								panel6.add(btnProgramador, cc.xy(7, 1));
							}
							panelRetencion.add(panel6, cc.xywh(3, 9, 5, 1));
						}
						tpTabsCliente.addTab("Retenci\u00f3n", panelRetencion);


						//======== panelZona ========
						{
							panelZona.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(12)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(30), FormSpec.NO_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(120)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(12))
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY6),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5),
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
							panelZona.add(txtCodigoZona, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
							panelZona.add(txtNombreZona, cc.xywh(5, 5, 3, 1));

							//======== spDetalleZona ========
							{

								//---- tblDetalleZona ----
								tblDetalleZona.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"C\u00f3digo", "Nombre"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								tblDetalleZona.setPreferredScrollableViewportSize(new Dimension(450, 150));
								tblDetalleZona.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
								tblDetalleZona.setAutoCreateColumnsFromModel(true);
								spDetalleZona.setViewportView(tblDetalleZona);
							}
							panelZona.add(spDetalleZona, cc.xywh(3, 11, 7, 5));

							//---- lblCodigoZona ----
							lblCodigoZona.setText("C\u00f3digo:");
							panelZona.add(lblCodigoZona, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblNombreZona ----
							lblNombreZona.setText("Nombre:");
							panelZona.add(lblNombreZona, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//======== panel2 ========
							{
								panel2.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									RowSpec.decodeSpecs("default")));

								//---- btnAgregarDetalleZona ----
								btnAgregarDetalleZona.setText("A");
								panel2.add(btnAgregarDetalleZona, cc.xy(1, 1));

								//---- btnActualizarDetalleZona ----
								btnActualizarDetalleZona.setText("U");
								panel2.add(btnActualizarDetalleZona, cc.xy(3, 1));

								//---- btnEliminarDetalleZona ----
								btnEliminarDetalleZona.setText("D");
								panel2.add(btnEliminarDetalleZona, cc.xy(5, 1));
							}
							panelZona.add(panel2, cc.xywh(3, 9, 7, 1));
						}
						tpTabsCliente.addTab("Zona", panelZona);

					}
					panelSubTabCliente.add(tpTabsCliente, cc.xy(3, 3));
				}
				spPanelSubTabCliente.setViewportView(panelSubTabCliente);
			}
			jtpTabsAdministracionCliente.addTab("Operador de Negocio", spPanelSubTabCliente);


			//======== spPanelSubTabOficina ========
			{

				//======== panelSubTabOficina ========
				{
					panelSubTabOficina.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY5),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY5)
						}));

					//======== tpTabsOficina ========
					{

						//======== panelOficina ========
						{
							panelOficina.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(12)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(55)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(55)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(12))
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
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
									new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY3)
								}));
							panelOficina.add(txtCodigoClienteOficina, cc.xy(5, 3));
							panelOficina.add(txtDescripcionClienteOficina, cc.xywh(5, 5, 7, 1));
							panelOficina.add(txtCiudadClienteOficina, cc.xywh(5, 7, 3, 1));
							panelOficina.add(txtTelefonoClienteOficina, cc.xy(5, 11));

							//======== spOficinasCliente ========
							{

								//---- tblOficinasCliente ----
								tblOficinasCliente.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"C\u00f3digo", "Fecha Creaci\u00f3n", "Estado", "Descripci\u00f3n", "Ciudad"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false, false, false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								tblOficinasCliente.setPreferredScrollableViewportSize(new Dimension(450, 150));
								tblOficinasCliente.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
								tblOficinasCliente.setAutoCreateColumnsFromModel(true);
								spOficinasCliente.setViewportView(tblOficinasCliente);
							}
							panelOficina.add(spOficinasCliente, cc.xywh(3, 20, 19, 5));

							//---- lblFechaFechaCreacionClienteOficina ----
							lblFechaFechaCreacionClienteOficina.setText("Fecha Creaci\u00f3n: ");
							panelOficina.add(lblFechaFechaCreacionClienteOficina, cc.xywh(13, 3, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelOficina.add(txtFechaCreacionClienteOficina, cc.xywh(17, 3, 3, 1));

							//---- lblCodigoClienteOficina ----
							lblCodigoClienteOficina.setText("C\u00f3digo:");
							panelOficina.add(lblCodigoClienteOficina, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblDescripcionClienteOficina ----
							lblDescripcionClienteOficina.setText("Descripcion:");
							panelOficina.add(lblDescripcionClienteOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblEstadoClienteOficina ----
							lblEstadoClienteOficina.setText("Estado:");
							panelOficina.add(lblEstadoClienteOficina, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelOficina.add(cmbEstadoClienteOficina, cc.xy(17, 5));
							panelOficina.add(btnCiudadesClienteOficina, cc.xywh(9, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

							//---- lblCiudadClienteOficina ----
							lblCiudadClienteOficina.setText("Ciudad:");
							panelOficina.add(lblCiudadClienteOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblDireccionClienteOficina ----
							lblDireccionClienteOficina.setText("Direcci\u00f3n:");
							panelOficina.add(lblDireccionClienteOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelOficina.add(txtDireccionClienteOficina, cc.xywh(5, 9, 15, 1));

							//---- lblTelefonoClienteOficina ----
							lblTelefonoClienteOficina.setText("Tel\u00e9fono:");
							panelOficina.add(lblTelefonoClienteOficina, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblFaxClienteOficina ----
							lblFaxClienteOficina.setText("Fax:");
							panelOficina.add(lblFaxClienteOficina, cc.xywh(7, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelOficina.add(txtFaxClienteOficina, cc.xy(9, 11));
							panelOficina.add(txtEmailClienteOficina, cc.xywh(13, 11, 7, 1));

							//---- lblEmailClienteOficina ----
							lblEmailClienteOficina.setText("E-mail:");
							panelOficina.add(lblEmailClienteOficina, cc.xywh(11, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblObservacionClienteOficina ----
							lblObservacionClienteOficina.setText("Observaci\u00f3n:");
							panelOficina.add(lblObservacionClienteOficina, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblCalificacionClienteOficina ----
							lblCalificacionClienteOficina.setText("Calificaci\u00f3n:");
							panelOficina.add(lblCalificacionClienteOficina, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelOficina.add(cmbCalificacionClienteOficina, cc.xy(5, 15));

							//---- lblMontoCreditoClienteOficina ----
							lblMontoCreditoClienteOficina.setText("Monto Cr\u00e9dito:");
							panelOficina.add(lblMontoCreditoClienteOficina, cc.xywh(7, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelOficina.add(txtObservacionClienteOficina, cc.xywh(5, 13, 7, 1));

							//---- lblMontoGarantiaClienteOficina ----
							lblMontoGarantiaClienteOficina.setText("Monto Garant\u00eda:");
							panelOficina.add(lblMontoGarantiaClienteOficina, cc.xywh(11, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtMontoGarantiaClienteOficina ----
							txtMontoGarantiaClienteOficina.setHorizontalAlignment(SwingConstants.RIGHT);
							panelOficina.add(txtMontoGarantiaClienteOficina, cc.xy(13, 15));

							//---- txtMontoCreditoClienteOficina ----
							txtMontoCreditoClienteOficina.setHorizontalAlignment(SwingConstants.RIGHT);
							panelOficina.add(txtMontoCreditoClienteOficina, cc.xy(9, 15));

							//======== panel3 ========
							{
								panel3.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									RowSpec.decodeSpecs("default")));

								//---- btnAgregarClienteOficina ----
								btnAgregarClienteOficina.setText("A");
								panel3.add(btnAgregarClienteOficina, cc.xy(1, 1));

								//---- btnActualizarClienteOficina ----
								btnActualizarClienteOficina.setText("U");
								panel3.add(btnActualizarClienteOficina, cc.xy(3, 1));

								//---- btnEliminarClienteOficina ----
								btnEliminarClienteOficina.setText("D");
								panel3.add(btnEliminarClienteOficina, cc.xy(5, 1));
							}
							panelOficina.add(panel3, cc.xywh(3, 18, 18, 1));
						}
						tpTabsOficina.addTab("General", panelOficina);


						//======== panelContacto ========
						{
							panelContacto.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(12)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(60)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(12)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(60)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(150)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(12))
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY6),
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
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY6)
								}));
							panelContacto.add(cmbTipoContacto, cc.xywh(5, 3, 7, 1));
							panelContacto.add(txtDireccionContacto, cc.xywh(5, 7, 9, 1));

							//======== spDetalleContacto ========
							{

								//---- tblDetalleContacto ----
								tblDetalleContacto.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"Tipo Contacto", "Nombre", "Email", "Direcci\u00f3n"
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
								tblDetalleContacto.setPreferredScrollableViewportSize(new Dimension(450, 150));
								tblDetalleContacto.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
								tblDetalleContacto.setAutoCreateColumnsFromModel(true);
								spDetalleContacto.setViewportView(tblDetalleContacto);
							}
							panelContacto.add(spDetalleContacto, cc.xywh(3, 21, 13, 5));

							//---- lblTipoContacto ----
							lblTipoContacto.setText("Tipo Contacto:");
							panelContacto.add(lblTipoContacto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblNombreContacto ----
							lblNombreContacto.setText("Nombre:");
							panelContacto.add(lblNombreContacto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelContacto.add(txtNombreContacto, cc.xywh(5, 5, 7, 1));

							//---- lblDireccionContacto ----
							lblDireccionContacto.setText("Direcci\u00f3n:");
							panelContacto.add(lblDireccionContacto, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblTelefonoCasaContacto ----
							lblTelefonoCasaContacto.setText("Tel\u00e9fono Casa:");
							panelContacto.add(lblTelefonoCasaContacto, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblTelefonoOficContacto ----
							lblTelefonoOficContacto.setText("Tel\u00e9fono Oficina:");
							panelContacto.add(lblTelefonoOficContacto, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelContacto.add(txtTelefonoOficContacto, cc.xy(11, 9));

							//---- lblCelularContacto ----
							lblCelularContacto.setText("Celular:");
							panelContacto.add(lblCelularContacto, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelContacto.add(txtCelularContacto, cc.xy(5, 11));

							//---- lblMailContacto ----
							lblMailContacto.setText("E-mail:");
							panelContacto.add(lblMailContacto, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelContacto.add(txtMailContacto, cc.xywh(5, 13, 5, 1));

							//---- lblCumpleanosContacto ----
							lblCumpleanosContacto.setText("Fecha de Nacimiento:");
							panelContacto.add(lblCumpleanosContacto, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							panelContacto.add(cmbCumpleanosContacto, cc.xywh(5, 15, 5, 1));
							panelContacto.add(txtTelefonoCasaContacto, cc.xy(5, 9));

							//======== panel4 ========
							{
								panel4.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									RowSpec.decodeSpecs("default")));

								//---- btnAgregarDetalleContacto ----
								btnAgregarDetalleContacto.setText("A");
								panel4.add(btnAgregarDetalleContacto, cc.xy(1, 1));

								//---- btnActualizarDetalleContacto ----
								btnActualizarDetalleContacto.setText("U");
								panel4.add(btnActualizarDetalleContacto, cc.xy(3, 1));

								//---- btnEliminarDetalleContacto ----
								btnEliminarDetalleContacto.setText("D");
								panel4.add(btnEliminarDetalleContacto, cc.xy(5, 1));
							}
							panelContacto.add(panel4, cc.xywh(3, 19, 14, 1));
						}
						tpTabsOficina.addTab("Contacto", panelContacto);


						//======== panelIndicador ========
						{
							panelIndicador.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(12)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(50), FormSpec.NO_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(120)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(12))
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY6),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5),
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
							panelIndicador.add(cmbTipoIndicador, cc.xywh(5, 3, 3, 1));

							//---- txtValorIndicador ----
							txtValorIndicador.setHorizontalAlignment(SwingConstants.RIGHT);
							panelIndicador.add(txtValorIndicador, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

							//======== spDetalleIndicador ========
							{

								//---- tblDetalleIndicador ----
								tblDetalleIndicador.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"Tipo Indicador", "Valor"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								tblDetalleIndicador.setPreferredScrollableViewportSize(new Dimension(450, 150));
								tblDetalleIndicador.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
								tblDetalleIndicador.setAutoCreateColumnsFromModel(true);
								spDetalleIndicador.setViewportView(tblDetalleIndicador);
							}
							panelIndicador.add(spDetalleIndicador, cc.xywh(3, 11, 7, 5));

							//---- lblTipoIndicador ----
							lblTipoIndicador.setText("Tipo Indicador:");
							panelIndicador.add(lblTipoIndicador, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblValorIndicador ----
							lblValorIndicador.setText("Valor:");
							panelIndicador.add(lblValorIndicador, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//======== panel5 ========
							{
								panel5.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									RowSpec.decodeSpecs("default")));

								//---- btnAgregarDetalleIndicador ----
								btnAgregarDetalleIndicador.setText("A");
								panel5.add(btnAgregarDetalleIndicador, cc.xy(1, 1));

								//---- btnActualizarDetalleIndicador ----
								btnActualizarDetalleIndicador.setText("U");
								panel5.add(btnActualizarDetalleIndicador, cc.xy(3, 1));

								//---- btnEliminarDetalleIndicador ----
								btnEliminarDetalleIndicador.setText("D");
								panel5.add(btnEliminarDetalleIndicador, cc.xy(5, 1));
							}
							panelIndicador.add(panel5, cc.xywh(3, 9, 7, 1));
						}
						tpTabsOficina.addTab("Indicador", panelIndicador);

					}
					panelSubTabOficina.add(tpTabsOficina, cc.xy(3, 3));
				}
				spPanelSubTabOficina.setViewportView(panelSubTabOficina);
			}
			jtpTabsAdministracionCliente.addTab("Oficina", spPanelSubTabOficina);

		}
		add(jtpTabsAdministracionCliente, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpTabsAdministracionCliente;
	private JScrollPane spPanelSubTabCliente;
	private JPanel panelSubTabCliente;
	private JTabbedPane tpTabsCliente;
	private JPanel panelRetencion;
	private JScrollPane spTblRetenciones;
	private JTable tblRetenciones;
	private JLabel lblRetencionRenta;
	private JComboBox cmbRetencionRenta;
	private JLabel lblRetencionIVA;
	private JComboBox cmbRetencionIVA;
	private JPanel panel6;
	private JButton btnAgregarRetencion;
	private JButton btnActualizarRetencion;
	private JButton btnEliminarRetencion;
	private JButton btnProgramador;
	private JPanel panelZona;
	private JTextField txtCodigoZona;
	private JTextField txtNombreZona;
	private JScrollPane spDetalleZona;
	private JTable tblDetalleZona;
	private JLabel lblCodigoZona;
	private JLabel lblNombreZona;
	private JPanel panel2;
	private JButton btnAgregarDetalleZona;
	private JButton btnActualizarDetalleZona;
	private JButton btnEliminarDetalleZona;
	private JPanel panelCliente;
	private JLabel lblTipoCliente;
	private JComboBox cmbTipoCliente;
	private JLabel lblFechaCreacionCliente;
	private JTextField txtFechaCreacionCliente;
	private JLabel lblTipoIdentificacionCliente;
	private JComboBox cmbTipoIdentificacionCliente;
	private JLabel lblEstadoCliente;
	private JComboBox cmbEstadoCliente;
	private JLabel lblNombreLegalCliente;
	private JLabel lblIdentificacionCliente;
	private JTextField txtIdentificacionCliente;
	private JTextField txtNombreLegalCliente;
	private JLabel lblRazonSocialCliente;
	private JTextField txtRazonSocialCliente;
	private JTextField txtRepresentanteCliente;
	private JLabel lblCorporacionCliente;
	private JTextField txtCorporacionCliente;
	private JLabel lblRepresentanteCliente;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JButton btnBuscarCorporacionCliente;
	private JLabel lblTipoNegocioCliente;
	private JLabel lblUsuarioFinal;
	private JComboBox cmbTipoNegocioCliente;
	private JComboBox cmbUsuarioFinal;
	private JLabel lblBanco;
	private JComboBox cmbBanco;
	private JLabel lblContribuyenteEspecial;
	private JComboBox cmbContribuyenteEspecial;
	private JLabel lblTipoCuenta;
	private JComboBox cmbTipoCuenta;
	private JLabel lblTipoPersona;
	private JComboBox cmbTipoPersona;
	private JLabel lblNumeroCuenta;
	private JTextField txtNumeroCuenta;
	private JLabel lblLlevaContabilidad;
	private JComboBox cmbLlevaContabilidad;
	private JLabel lblRequiereSap;
	private JComboBox cmbRequiereSap;
	private JLabel lblObservacionesCliente;
	private JLabel lblinformacionAdc;
	private JTextField txtinformacionAdc;
	private JTextField txtObservacionesCliente;
	private JScrollPane spPanelSubTabOficina;
	private JPanel panelSubTabOficina;
	private JTabbedPane tpTabsOficina;
	private JPanel panelOficina;
	private JTextField txtCodigoClienteOficina;
	private JTextField txtDescripcionClienteOficina;
	private JTextField txtCiudadClienteOficina;
	private JTextField txtTelefonoClienteOficina;
	private JScrollPane spOficinasCliente;
	private JTable tblOficinasCliente;
	private JLabel lblFechaFechaCreacionClienteOficina;
	private JTextField txtFechaCreacionClienteOficina;
	private JLabel lblCodigoClienteOficina;
	private JLabel lblDescripcionClienteOficina;
	private JLabel lblEstadoClienteOficina;
	private JComboBox cmbEstadoClienteOficina;
	private JButton btnCiudadesClienteOficina;
	private JLabel lblCiudadClienteOficina;
	private JLabel lblDireccionClienteOficina;
	private JTextField txtDireccionClienteOficina;
	private JLabel lblTelefonoClienteOficina;
	private JLabel lblFaxClienteOficina;
	private JTextField txtFaxClienteOficina;
	private JTextField txtEmailClienteOficina;
	private JLabel lblEmailClienteOficina;
	private JLabel lblObservacionClienteOficina;
	private JLabel lblCalificacionClienteOficina;
	private JComboBox cmbCalificacionClienteOficina;
	private JLabel lblMontoCreditoClienteOficina;
	private JTextField txtObservacionClienteOficina;
	private JLabel lblMontoGarantiaClienteOficina;
	private JTextField txtMontoGarantiaClienteOficina;
	private JTextField txtMontoCreditoClienteOficina;
	private JPanel panel3;
	private JButton btnAgregarClienteOficina;
	private JButton btnActualizarClienteOficina;
	private JButton btnEliminarClienteOficina;
	private JPanel panelContacto;
	private JComboBox cmbTipoContacto;
	private JTextField txtDireccionContacto;
	private JScrollPane spDetalleContacto;
	private JTable tblDetalleContacto;
	private JLabel lblTipoContacto;
	private JLabel lblNombreContacto;
	private JTextField txtNombreContacto;
	private JLabel lblDireccionContacto;
	private JLabel lblTelefonoCasaContacto;
	private JLabel lblTelefonoOficContacto;
	private JTextField txtTelefonoOficContacto;
	private JLabel lblCelularContacto;
	private JTextField txtCelularContacto;
	private JLabel lblMailContacto;
	private JTextField txtMailContacto;
	private JLabel lblCumpleanosContacto;
	private DateComboBox cmbCumpleanosContacto;
	private JTextField txtTelefonoCasaContacto;
	private JPanel panel4;
	private JButton btnAgregarDetalleContacto;
	private JButton btnActualizarDetalleContacto;
	private JButton btnEliminarDetalleContacto;
	private JPanel panelIndicador;
	private JComboBox cmbTipoIndicador;
	private JTextField txtValorIndicador;
	private JScrollPane spDetalleIndicador;
	private JTable tblDetalleIndicador;
	private JLabel lblTipoIndicador;
	private JLabel lblValorIndicador;
	private JPanel panel5;
	private JButton btnAgregarDetalleIndicador;
	private JButton btnActualizarDetalleIndicador;
	private JButton btnEliminarDetalleIndicador;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblRetenciones() {
		return tblRetenciones;
	}

	public JButton getBtnAgregarRetencion() {
		return btnAgregarRetencion;
	}

	public JButton getBtnActualizarRetencion() {
		return btnActualizarRetencion;
	}

	public JButton getBtnEliminarRetencion() {
		return btnEliminarRetencion;
	}

	public JComboBox getCmbRetencionRenta() {
		return cmbRetencionRenta;
	}

	public JComboBox getCmbRetencionIVA() {
		return cmbRetencionIVA;
	}

	public JComboBox getCmbRequiereSap() {
		return cmbRequiereSap;
	}

	public void setCmbRequiereSap(JComboBox cmbRequiereSap) {
		this.cmbRequiereSap = cmbRequiereSap;
	}

	public JideTabbedPane getJtpTabsAdministracionCliente() {
		return jtpTabsAdministracionCliente;
	}

	public void setJtpTabsAdministracionCliente(
			JideTabbedPane jtpTabsAdministracionCliente) {
		this.jtpTabsAdministracionCliente = jtpTabsAdministracionCliente;
	}

	public JScrollPane getSpPanelSubTabCliente() {
		return spPanelSubTabCliente;
	}

	public void setSpPanelSubTabCliente(JScrollPane spPanelSubTabCliente) {
		this.spPanelSubTabCliente = spPanelSubTabCliente;
	}

	public JPanel getPanelSubTabCliente() {
		return panelSubTabCliente;
	}

	public void setPanelSubTabCliente(JPanel panelSubTabCliente) {
		this.panelSubTabCliente = panelSubTabCliente;
	}

	public JTabbedPane getTpTabsCliente() {
		return tpTabsCliente;
	}

	public void setTpTabsCliente(JTabbedPane tpTabsCliente) {
		this.tpTabsCliente = tpTabsCliente;
	}

	public JPanel getPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(JPanel panelCliente) {
		this.panelCliente = panelCliente;
	}

	public JLabel getLblTipoCliente() {
		return lblTipoCliente;
	}

	public void setLblTipoCliente(JLabel lblTipoCliente) {
		this.lblTipoCliente = lblTipoCliente;
	}

	public JComboBox getCmbTipoCliente() {
		return cmbTipoCliente;
	}

	public void setCmbTipoCliente(JComboBox cmbTipoCliente) {
		this.cmbTipoCliente = cmbTipoCliente;
	}

	public JLabel getLblFechaCreacionCliente() {
		return lblFechaCreacionCliente;
	}

	public void setLblFechaCreacionCliente(JLabel lblFechaCreacionCliente) {
		this.lblFechaCreacionCliente = lblFechaCreacionCliente;
	}

	public JTextField getTxtFechaCreacionCliente() {
		return txtFechaCreacionCliente;
	}

	public void setTxtFechaCreacionCliente(JTextField txtFechaCreacionCliente) {
		this.txtFechaCreacionCliente = txtFechaCreacionCliente;
	}

	public JLabel getLblTipoIdentificacionCliente() {
		return lblTipoIdentificacionCliente;
	}

	public void setLblTipoIdentificacionCliente(JLabel lblTipoIdentificacionCliente) {
		this.lblTipoIdentificacionCliente = lblTipoIdentificacionCliente;
	}

	public JComboBox getCmbTipoIdentificacionCliente() {
		return cmbTipoIdentificacionCliente;
	}

	public void setCmbTipoIdentificacionCliente(
			JComboBox cmbTipoIdentificacionCliente) {
		this.cmbTipoIdentificacionCliente = cmbTipoIdentificacionCliente;
	}

	public JLabel getLblEstadoCliente() {
		return lblEstadoCliente;
	}

	public void setLblEstadoCliente(JLabel lblEstadoCliente) {
		this.lblEstadoCliente = lblEstadoCliente;
	}

	public JComboBox getCmbEstadoCliente() {
		return cmbEstadoCliente;
	}

	public void setCmbEstadoCliente(JComboBox cmbEstadoCliente) {
		this.cmbEstadoCliente = cmbEstadoCliente;
	}

	public JLabel getLblNombreLegalCliente() {
		return lblNombreLegalCliente;
	}

	public void setLblNombreLegalCliente(JLabel lblNombreLegalCliente) {
		this.lblNombreLegalCliente = lblNombreLegalCliente;
	}

	public JLabel getLblIdentificacionCliente() {
		return lblIdentificacionCliente;
	}

	public void setLblIdentificacionCliente(JLabel lblIdentificacionCliente) {
		this.lblIdentificacionCliente = lblIdentificacionCliente;
	}

	public JTextField getTxtIdentificacionCliente() {
		return txtIdentificacionCliente;
	}

	public void setTxtIdentificacionCliente(JTextField txtIdentificacionCliente) {
		this.txtIdentificacionCliente = txtIdentificacionCliente;
	}

	public JTextField getTxtNombreLegalCliente() {
		return txtNombreLegalCliente;
	}

	public void setTxtNombreLegalCliente(JTextField txtNombreLegalCliente) {
		this.txtNombreLegalCliente = txtNombreLegalCliente;
	}

	public JLabel getLblRazonSocialCliente() {
		return lblRazonSocialCliente;
	}

	public void setLblRazonSocialCliente(JLabel lblRazonSocialCliente) {
		this.lblRazonSocialCliente = lblRazonSocialCliente;
	}

	public JTextField getTxtRazonSocialCliente() {
		return txtRazonSocialCliente;
	}

	public void setTxtRazonSocialCliente(JTextField txtRazonSocialCliente) {
		this.txtRazonSocialCliente = txtRazonSocialCliente;
	}

	public JTextField getTxtRepresentanteCliente() {
		return txtRepresentanteCliente;
	}

	public void setTxtRepresentanteCliente(JTextField txtRepresentanteCliente) {
		this.txtRepresentanteCliente = txtRepresentanteCliente;
	}

	public JLabel getLblCorporacionCliente() {
		return lblCorporacionCliente;
	}

	public void setLblCorporacionCliente(JLabel lblCorporacionCliente) {
		this.lblCorporacionCliente = lblCorporacionCliente;
	}

	public JTextField getTxtCorporacionCliente() {
		return txtCorporacionCliente;
	}

	public void setTxtCorporacionCliente(JTextField txtCorporacionCliente) {
		this.txtCorporacionCliente = txtCorporacionCliente;
	}

	public JLabel getLblRepresentanteCliente() {
		return lblRepresentanteCliente;
	}

	public void setLblRepresentanteCliente(JLabel lblRepresentanteCliente) {
		this.lblRepresentanteCliente = lblRepresentanteCliente;
	}

	public JLabel getLblTipoProveedor() {
		return lblTipoProveedor;
	}

	public void setLblTipoProveedor(JLabel lblTipoProveedor) {
		this.lblTipoProveedor = lblTipoProveedor;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public void setCmbTipoProveedor(JComboBox cmbTipoProveedor) {
		this.cmbTipoProveedor = cmbTipoProveedor;
	}

	public JButton getBtnBuscarCorporacionCliente() {
		return btnBuscarCorporacionCliente;
	}

	public void setBtnBuscarCorporacionCliente(JButton btnBuscarCorporacionCliente) {
		this.btnBuscarCorporacionCliente = btnBuscarCorporacionCliente;
	}

	public JLabel getLblTipoNegocioCliente() {
		return lblTipoNegocioCliente;
	}

	public void setLblTipoNegocioCliente(JLabel lblTipoNegocioCliente) {
		this.lblTipoNegocioCliente = lblTipoNegocioCliente;
	}

	public JLabel getLblUsuarioFinal() {
		return lblUsuarioFinal;
	}

	public void setLblUsuarioFinal(JLabel lblUsuarioFinal) {
		this.lblUsuarioFinal = lblUsuarioFinal;
	}

	public JComboBox getCmbTipoNegocioCliente() {
		return cmbTipoNegocioCliente;
	}

	public void setCmbTipoNegocioCliente(JComboBox cmbTipoNegocioCliente) {
		this.cmbTipoNegocioCliente = cmbTipoNegocioCliente;
	}

	public JComboBox getCmbUsuarioFinal() {
		return cmbUsuarioFinal;
	}

	public void setCmbUsuarioFinal(JComboBox cmbUsuarioFinal) {
		this.cmbUsuarioFinal = cmbUsuarioFinal;
	}

	public JLabel getLblContribuyenteEspecial() {
		return lblContribuyenteEspecial;
	}

	public void setLblContribuyenteEspecial(JLabel lblContribuyenteEspecial) {
		this.lblContribuyenteEspecial = lblContribuyenteEspecial;
	}

	public JComboBox getCmbContribuyenteEspecial() {
		return cmbContribuyenteEspecial;
	}

	public void setCmbContribuyenteEspecial(JComboBox cmbContribuyenteEspecial) {
		this.cmbContribuyenteEspecial = cmbContribuyenteEspecial;
	}

	public JLabel getLblTipoPersona() {
		return lblTipoPersona;
	}

	public void setLblTipoPersona(JLabel lblTipoPersona) {
		this.lblTipoPersona = lblTipoPersona;
	}

	public JComboBox getCmbTipoPersona() {
		return cmbTipoPersona;
	}

	public void setCmbTipoPersona(JComboBox cmbTipoPersona) {
		this.cmbTipoPersona = cmbTipoPersona;
	}

	public JLabel getLblLlevaContabilidad() {
		return lblLlevaContabilidad;
	}

	public void setLblLlevaContabilidad(JLabel lblLlevaContabilidad) {
		this.lblLlevaContabilidad = lblLlevaContabilidad;
	}

	public JComboBox getCmbLlevaContabilidad() {
		return cmbLlevaContabilidad;
	}

	public void setCmbLlevaContabilidad(JComboBox cmbLlevaContabilidad) {
		this.cmbLlevaContabilidad = cmbLlevaContabilidad;
	}

	public JLabel getLblObservacionesCliente() {
		return lblObservacionesCliente;
	}

	public void setLblObservacionesCliente(JLabel lblObservacionesCliente) {
		this.lblObservacionesCliente = lblObservacionesCliente;
	}

	public JLabel getLblinformacionAdc() {
		return lblinformacionAdc;
	}

	public void setLblinformacionAdc(JLabel lblinformacionAdc) {
		this.lblinformacionAdc = lblinformacionAdc;
	}

	public JTextField getTxtinformacionAdc() {
		return txtinformacionAdc;
	}

	public void setTxtinformacionAdc(JTextField txtinformacionAdc) {
		this.txtinformacionAdc = txtinformacionAdc;
	}

	public JTextField getTxtObservacionesCliente() {
		return txtObservacionesCliente;
	}

	public void setTxtObservacionesCliente(JTextField txtObservacionesCliente) {
		this.txtObservacionesCliente = txtObservacionesCliente;
	}

	public JPanel getPanelZona() {
		return panelZona;
	}

	public void setPanelZona(JPanel panelZona) {
		this.panelZona = panelZona;
	}

	public JTextField getTxtCodigoZona() {
		return txtCodigoZona;
	}

	public void setTxtCodigoZona(JTextField txtCodigoZona) {
		this.txtCodigoZona = txtCodigoZona;
	}

	public JTextField getTxtNombreZona() {
		return txtNombreZona;
	}

	public void setTxtNombreZona(JTextField txtNombreZona) {
		this.txtNombreZona = txtNombreZona;
	}

	public JScrollPane getSpDetalleZona() {
		return spDetalleZona;
	}

	public void setSpDetalleZona(JScrollPane spDetalleZona) {
		this.spDetalleZona = spDetalleZona;
	}

	public JTable getTblDetalleZona() {
		return tblDetalleZona;
	}

	public void setTblDetalleZona(JTable tblDetalleZona) {
		this.tblDetalleZona = tblDetalleZona;
	}

	public JLabel getLblCodigoZona() {
		return lblCodigoZona;
	}

	public void setLblCodigoZona(JLabel lblCodigoZona) {
		this.lblCodigoZona = lblCodigoZona;
	}

	public JLabel getLblNombreZona() {
		return lblNombreZona;
	}

	public void setLblNombreZona(JLabel lblNombreZona) {
		this.lblNombreZona = lblNombreZona;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JButton getBtnAgregarDetalleZona() {
		return btnAgregarDetalleZona;
	}

	public void setBtnAgregarDetalleZona(JButton btnAgregarDetalleZona) {
		this.btnAgregarDetalleZona = btnAgregarDetalleZona;
	}

	public JButton getBtnActualizarDetalleZona() {
		return btnActualizarDetalleZona;
	}

	public void setBtnActualizarDetalleZona(JButton btnActualizarDetalleZona) {
		this.btnActualizarDetalleZona = btnActualizarDetalleZona;
	}

	public JButton getBtnEliminarDetalleZona() {
		return btnEliminarDetalleZona;
	}

	public void setBtnEliminarDetalleZona(JButton btnEliminarDetalleZona) {
		this.btnEliminarDetalleZona = btnEliminarDetalleZona;
	}

	public JScrollPane getSpPanelSubTabOficina() {
		return spPanelSubTabOficina;
	}

	public void setSpPanelSubTabOficina(JScrollPane spPanelSubTabOficina) {
		this.spPanelSubTabOficina = spPanelSubTabOficina;
	}

	public JPanel getPanelSubTabOficina() {
		return panelSubTabOficina;
	}

	public void setPanelSubTabOficina(JPanel panelSubTabOficina) {
		this.panelSubTabOficina = panelSubTabOficina;
	}

	public JTabbedPane getTpTabsOficina() {
		return tpTabsOficina;
	}

	public void setTpTabsOficina(JTabbedPane tpTabsOficina) {
		this.tpTabsOficina = tpTabsOficina;
	}

	public JPanel getPanelOficina() {
		return panelOficina;
	}

	public void setPanelOficina(JPanel panelOficina) {
		this.panelOficina = panelOficina;
	}

	public JTextField getTxtCodigoClienteOficina() {
		return txtCodigoClienteOficina;
	}

	public void setTxtCodigoClienteOficina(JTextField txtCodigoClienteOficina) {
		this.txtCodigoClienteOficina = txtCodigoClienteOficina;
	}

	public JTextField getTxtDescripcionClienteOficina() {
		return txtDescripcionClienteOficina;
	}

	public void setTxtDescripcionClienteOficina(
			JTextField txtDescripcionClienteOficina) {
		this.txtDescripcionClienteOficina = txtDescripcionClienteOficina;
	}

	public JTextField getTxtCiudadClienteOficina() {
		return txtCiudadClienteOficina;
	}

	public void setTxtCiudadClienteOficina(JTextField txtCiudadClienteOficina) {
		this.txtCiudadClienteOficina = txtCiudadClienteOficina;
	}

	public JTextField getTxtTelefonoClienteOficina() {
		return txtTelefonoClienteOficina;
	}

	public void setTxtTelefonoClienteOficina(JTextField txtTelefonoClienteOficina) {
		this.txtTelefonoClienteOficina = txtTelefonoClienteOficina;
	}

	public JScrollPane getSpOficinasCliente() {
		return spOficinasCliente;
	}

	public void setSpOficinasCliente(JScrollPane spOficinasCliente) {
		this.spOficinasCliente = spOficinasCliente;
	}

	public JTable getTblOficinasCliente() {
		return tblOficinasCliente;
	}

	public void setTblOficinasCliente(JTable tblOficinasCliente) {
		this.tblOficinasCliente = tblOficinasCliente;
	}

	public JLabel getLblFechaFechaCreacionClienteOficina() {
		return lblFechaFechaCreacionClienteOficina;
	}

	public void setLblFechaFechaCreacionClienteOficina(
			JLabel lblFechaFechaCreacionClienteOficina) {
		this.lblFechaFechaCreacionClienteOficina = lblFechaFechaCreacionClienteOficina;
	}

	public JTextField getTxtFechaCreacionClienteOficina() {
		return txtFechaCreacionClienteOficina;
	}

	public void setTxtFechaCreacionClienteOficina(
			JTextField txtFechaCreacionClienteOficina) {
		this.txtFechaCreacionClienteOficina = txtFechaCreacionClienteOficina;
	}

	public JLabel getLblCodigoClienteOficina() {
		return lblCodigoClienteOficina;
	}

	public void setLblCodigoClienteOficina(JLabel lblCodigoClienteOficina) {
		this.lblCodigoClienteOficina = lblCodigoClienteOficina;
	}

	public JLabel getLblDescripcionClienteOficina() {
		return lblDescripcionClienteOficina;
	}

	public void setLblDescripcionClienteOficina(JLabel lblDescripcionClienteOficina) {
		this.lblDescripcionClienteOficina = lblDescripcionClienteOficina;
	}

	public JLabel getLblEstadoClienteOficina() {
		return lblEstadoClienteOficina;
	}

	public void setLblEstadoClienteOficina(JLabel lblEstadoClienteOficina) {
		this.lblEstadoClienteOficina = lblEstadoClienteOficina;
	}

	public JComboBox getCmbEstadoClienteOficina() {
		return cmbEstadoClienteOficina;
	}

	public void setCmbEstadoClienteOficina(JComboBox cmbEstadoClienteOficina) {
		this.cmbEstadoClienteOficina = cmbEstadoClienteOficina;
	}

	public JButton getBtnCiudadesClienteOficina() {
		return btnCiudadesClienteOficina;
	}

	public void setBtnCiudadesClienteOficina(JButton btnCiudadesClienteOficina) {
		this.btnCiudadesClienteOficina = btnCiudadesClienteOficina;
	}

	public JLabel getLblCiudadClienteOficina() {
		return lblCiudadClienteOficina;
	}

	public void setLblCiudadClienteOficina(JLabel lblCiudadClienteOficina) {
		this.lblCiudadClienteOficina = lblCiudadClienteOficina;
	}

	public JLabel getLblDireccionClienteOficina() {
		return lblDireccionClienteOficina;
	}

	public void setLblDireccionClienteOficina(JLabel lblDireccionClienteOficina) {
		this.lblDireccionClienteOficina = lblDireccionClienteOficina;
	}

	public JTextField getTxtDireccionClienteOficina() {
		return txtDireccionClienteOficina;
	}

	public void setTxtDireccionClienteOficina(JTextField txtDireccionClienteOficina) {
		this.txtDireccionClienteOficina = txtDireccionClienteOficina;
	}

	public JLabel getLblTelefonoClienteOficina() {
		return lblTelefonoClienteOficina;
	}

	public void setLblTelefonoClienteOficina(JLabel lblTelefonoClienteOficina) {
		this.lblTelefonoClienteOficina = lblTelefonoClienteOficina;
	}

	public JLabel getLblFaxClienteOficina() {
		return lblFaxClienteOficina;
	}

	public void setLblFaxClienteOficina(JLabel lblFaxClienteOficina) {
		this.lblFaxClienteOficina = lblFaxClienteOficina;
	}

	public JTextField getTxtFaxClienteOficina() {
		return txtFaxClienteOficina;
	}

	public void setTxtFaxClienteOficina(JTextField txtFaxClienteOficina) {
		this.txtFaxClienteOficina = txtFaxClienteOficina;
	}

	public JTextField getTxtEmailClienteOficina() {
		return txtEmailClienteOficina;
	}

	public void setTxtEmailClienteOficina(JTextField txtEmailClienteOficina) {
		this.txtEmailClienteOficina = txtEmailClienteOficina;
	}

	public JLabel getLblEmailClienteOficina() {
		return lblEmailClienteOficina;
	}

	public void setLblEmailClienteOficina(JLabel lblEmailClienteOficina) {
		this.lblEmailClienteOficina = lblEmailClienteOficina;
	}

	public JLabel getLblObservacionClienteOficina() {
		return lblObservacionClienteOficina;
	}

	public void setLblObservacionClienteOficina(JLabel lblObservacionClienteOficina) {
		this.lblObservacionClienteOficina = lblObservacionClienteOficina;
	}

	public JLabel getLblCalificacionClienteOficina() {
		return lblCalificacionClienteOficina;
	}

	public void setLblCalificacionClienteOficina(
			JLabel lblCalificacionClienteOficina) {
		this.lblCalificacionClienteOficina = lblCalificacionClienteOficina;
	}

	public JComboBox getCmbCalificacionClienteOficina() {
		return cmbCalificacionClienteOficina;
	}

	public void setCmbCalificacionClienteOficina(
			JComboBox cmbCalificacionClienteOficina) {
		this.cmbCalificacionClienteOficina = cmbCalificacionClienteOficina;
	}

	public JLabel getLblMontoCreditoClienteOficina() {
		return lblMontoCreditoClienteOficina;
	}

	public void setLblMontoCreditoClienteOficina(
			JLabel lblMontoCreditoClienteOficina) {
		this.lblMontoCreditoClienteOficina = lblMontoCreditoClienteOficina;
	}

	public JTextField getTxtObservacionClienteOficina() {
		return txtObservacionClienteOficina;
	}

	public void setTxtObservacionClienteOficina(
			JTextField txtObservacionClienteOficina) {
		this.txtObservacionClienteOficina = txtObservacionClienteOficina;
	}

	public JLabel getLblMontoGarantiaClienteOficina() {
		return lblMontoGarantiaClienteOficina;
	}

	public void setLblMontoGarantiaClienteOficina(
			JLabel lblMontoGarantiaClienteOficina) {
		this.lblMontoGarantiaClienteOficina = lblMontoGarantiaClienteOficina;
	}

	public JTextField getTxtMontoGarantiaClienteOficina() {
		return txtMontoGarantiaClienteOficina;
	}

	public void setTxtMontoGarantiaClienteOficina(
			JTextField txtMontoGarantiaClienteOficina) {
		this.txtMontoGarantiaClienteOficina = txtMontoGarantiaClienteOficina;
	}

	public JTextField getTxtMontoCreditoClienteOficina() {
		return txtMontoCreditoClienteOficina;
	}

	public void setTxtMontoCreditoClienteOficina(
			JTextField txtMontoCreditoClienteOficina) {
		this.txtMontoCreditoClienteOficina = txtMontoCreditoClienteOficina;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JButton getBtnAgregarClienteOficina() {
		return btnAgregarClienteOficina;
	}

	public void setBtnAgregarClienteOficina(JButton btnAgregarClienteOficina) {
		this.btnAgregarClienteOficina = btnAgregarClienteOficina;
	}

	public JButton getBtnActualizarClienteOficina() {
		return btnActualizarClienteOficina;
	}

	public void setBtnActualizarClienteOficina(JButton btnActualizarClienteOficina) {
		this.btnActualizarClienteOficina = btnActualizarClienteOficina;
	}

	public JButton getBtnEliminarClienteOficina() {
		return btnEliminarClienteOficina;
	}

	public void setBtnEliminarClienteOficina(JButton btnEliminarClienteOficina) {
		this.btnEliminarClienteOficina = btnEliminarClienteOficina;
	}

	public JPanel getPanelContacto() {
		return panelContacto;
	}

	public void setPanelContacto(JPanel panelContacto) {
		this.panelContacto = panelContacto;
	}

	public JComboBox getCmbTipoContacto() {
		return cmbTipoContacto;
	}

	public void setCmbTipoContacto(JComboBox cmbTipoContacto) {
		this.cmbTipoContacto = cmbTipoContacto;
	}

	public JTextField getTxtDireccionContacto() {
		return txtDireccionContacto;
	}

	public void setTxtDireccionContacto(JTextField txtDireccionContacto) {
		this.txtDireccionContacto = txtDireccionContacto;
	}

	public JScrollPane getSpDetalleContacto() {
		return spDetalleContacto;
	}

	public void setSpDetalleContacto(JScrollPane spDetalleContacto) {
		this.spDetalleContacto = spDetalleContacto;
	}

	public JTable getTblDetalleContacto() {
		return tblDetalleContacto;
	}

	public void setTblDetalleContacto(JTable tblDetalleContacto) {
		this.tblDetalleContacto = tblDetalleContacto;
	}

	public JLabel getLblTipoContacto() {
		return lblTipoContacto;
	}

	public void setLblTipoContacto(JLabel lblTipoContacto) {
		this.lblTipoContacto = lblTipoContacto;
	}

	public JLabel getLblNombreContacto() {
		return lblNombreContacto;
	}

	public void setLblNombreContacto(JLabel lblNombreContacto) {
		this.lblNombreContacto = lblNombreContacto;
	}

	public JTextField getTxtNombreContacto() {
		return txtNombreContacto;
	}

	public void setTxtNombreContacto(JTextField txtNombreContacto) {
		this.txtNombreContacto = txtNombreContacto;
	}

	public JLabel getLblDireccionContacto() {
		return lblDireccionContacto;
	}

	public void setLblDireccionContacto(JLabel lblDireccionContacto) {
		this.lblDireccionContacto = lblDireccionContacto;
	}

	public JLabel getLblTelefonoCasaContacto() {
		return lblTelefonoCasaContacto;
	}

	public void setLblTelefonoCasaContacto(JLabel lblTelefonoCasaContacto) {
		this.lblTelefonoCasaContacto = lblTelefonoCasaContacto;
	}

	public JLabel getLblTelefonoOficContacto() {
		return lblTelefonoOficContacto;
	}

	public void setLblTelefonoOficContacto(JLabel lblTelefonoOficContacto) {
		this.lblTelefonoOficContacto = lblTelefonoOficContacto;
	}

	public JTextField getTxtTelefonoOficContacto() {
		return txtTelefonoOficContacto;
	}

	public void setTxtTelefonoOficContacto(JTextField txtTelefonoOficContacto) {
		this.txtTelefonoOficContacto = txtTelefonoOficContacto;
	}

	public JLabel getLblCelularContacto() {
		return lblCelularContacto;
	}

	public void setLblCelularContacto(JLabel lblCelularContacto) {
		this.lblCelularContacto = lblCelularContacto;
	}

	public JTextField getTxtCelularContacto() {
		return txtCelularContacto;
	}

	public void setTxtCelularContacto(JTextField txtCelularContacto) {
		this.txtCelularContacto = txtCelularContacto;
	}

	public JLabel getLblMailContacto() {
		return lblMailContacto;
	}

	public void setLblMailContacto(JLabel lblMailContacto) {
		this.lblMailContacto = lblMailContacto;
	}

	public JTextField getTxtMailContacto() {
		return txtMailContacto;
	}

	public void setTxtMailContacto(JTextField txtMailContacto) {
		this.txtMailContacto = txtMailContacto;
	}

	public JLabel getLblCumpleanosContacto() {
		return lblCumpleanosContacto;
	}

	public void setLblCumpleanosContacto(JLabel lblCumpleanosContacto) {
		this.lblCumpleanosContacto = lblCumpleanosContacto;
	}

	public DateComboBox getCmbCumpleanosContacto() {
		return cmbCumpleanosContacto;
	}

	public void setCmbCumpleanosContacto(DateComboBox cmbCumpleanosContacto) {
		this.cmbCumpleanosContacto = cmbCumpleanosContacto;
	}

	public JTextField getTxtTelefonoCasaContacto() {
		return txtTelefonoCasaContacto;
	}

	public void setTxtTelefonoCasaContacto(JTextField txtTelefonoCasaContacto) {
		this.txtTelefonoCasaContacto = txtTelefonoCasaContacto;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JButton getBtnAgregarDetalleContacto() {
		return btnAgregarDetalleContacto;
	}

	public void setBtnAgregarDetalleContacto(JButton btnAgregarDetalleContacto) {
		this.btnAgregarDetalleContacto = btnAgregarDetalleContacto;
	}

	public JButton getBtnActualizarDetalleContacto() {
		return btnActualizarDetalleContacto;
	}

	public void setBtnActualizarDetalleContacto(JButton btnActualizarDetalleContacto) {
		this.btnActualizarDetalleContacto = btnActualizarDetalleContacto;
	}

	public JButton getBtnEliminarDetalleContacto() {
		return btnEliminarDetalleContacto;
	}

	public void setBtnEliminarDetalleContacto(JButton btnEliminarDetalleContacto) {
		this.btnEliminarDetalleContacto = btnEliminarDetalleContacto;
	}

	public JPanel getPanelIndicador() {
		return panelIndicador;
	}

	public void setPanelIndicador(JPanel panelIndicador) {
		this.panelIndicador = panelIndicador;
	}

	public JComboBox getCmbTipoIndicador() {
		return cmbTipoIndicador;
	}

	public void setCmbTipoIndicador(JComboBox cmbTipoIndicador) {
		this.cmbTipoIndicador = cmbTipoIndicador;
	}

	public JTextField getTxtValorIndicador() {
		return txtValorIndicador;
	}

	public void setTxtValorIndicador(JTextField txtValorIndicador) {
		this.txtValorIndicador = txtValorIndicador;
	}

	public JScrollPane getSpDetalleIndicador() {
		return spDetalleIndicador;
	}

	public void setSpDetalleIndicador(JScrollPane spDetalleIndicador) {
		this.spDetalleIndicador = spDetalleIndicador;
	}

	public JTable getTblDetalleIndicador() {
		return tblDetalleIndicador;
	}

	public void setTblDetalleIndicador(JTable tblDetalleIndicador) {
		this.tblDetalleIndicador = tblDetalleIndicador;
	}

	public JLabel getLblTipoIndicador() {
		return lblTipoIndicador;
	}

	public void setLblTipoIndicador(JLabel lblTipoIndicador) {
		this.lblTipoIndicador = lblTipoIndicador;
	}

	public JLabel getLblValorIndicador() {
		return lblValorIndicador;
	}

	public void setLblValorIndicador(JLabel lblValorIndicador) {
		this.lblValorIndicador = lblValorIndicador;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public void setPanel5(JPanel panel5) {
		this.panel5 = panel5;
	}

	public JButton getBtnAgregarDetalleIndicador() {
		return btnAgregarDetalleIndicador;
	}

	public void setBtnAgregarDetalleIndicador(JButton btnAgregarDetalleIndicador) {
		this.btnAgregarDetalleIndicador = btnAgregarDetalleIndicador;
	}

	public JButton getBtnActualizarDetalleIndicador() {
		return btnActualizarDetalleIndicador;
	}

	public void setBtnActualizarDetalleIndicador(
			JButton btnActualizarDetalleIndicador) {
		this.btnActualizarDetalleIndicador = btnActualizarDetalleIndicador;
	}

	public JButton getBtnEliminarDetalleIndicador() {
		return btnEliminarDetalleIndicador;
	}

	public void setBtnEliminarDetalleIndicador(JButton btnEliminarDetalleIndicador) {
		this.btnEliminarDetalleIndicador = btnEliminarDetalleIndicador;
	}

	public JButton getBtnProgramador() {
		return btnProgramador;
	}

	public JComboBox getCmbBanco() {
		return cmbBanco;
	}

	public JComboBox getCmbTipoCuenta() {
		return cmbTipoCuenta;
	}

	public JTextField getTxtNumeroCuenta() {
		return txtNumeroCuenta;
	}

	public JLabel getLblBanco() {
		return lblBanco;
	}

	public JLabel getLblTipoCuenta() {
		return lblTipoCuenta;
	}

	public JLabel getLblNumeroCuenta() {
		return lblNumeroCuenta;
	}
	
	
	
	
}
