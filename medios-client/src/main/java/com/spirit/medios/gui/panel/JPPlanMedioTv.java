package com.spirit.medios.gui.panel;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.CheckBoxTree;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPPlanMedioTv extends SpiritModelImpl {
	public JPPlanMedioTv() {
		initComponents();
		setName("Plan de Medios");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpPlanMedio = new JideTabbedPane();
		spPlanMedio = new JScrollPane();
		panelPlanMedio = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblRevision = new JLabel();
		txtRevision = new JTextField();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnCorporacion = new JButton();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		lblModificaciones = new JLabel();
		txtModificaciones = new JTextField();
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		btnOficina = new JButton();
		lblAutorizacionSAP = new JLabel();
		txtAutorizacionSAP = new JTextField();
		lblOrdenTrabajo = new JLabel();
		cmbOrdenTrabajo = new JComboBox();
		lblFechaAprobacion = new JLabel();
		cmbFechaAprobacion = new DateComboBox();
		lblOrdenTrabajoDetalle = new JLabel();
		cmbOrdenTrabajoDetalle = new JComboBox();
		cbPrepago = new JCheckBox();
		lblCampana = new JLabel();
		txtCampana = new JTextField();
		lblConcepto = new JLabel();
		txtConcepto = new JTextField();
		cbPlanMedioNuevaVersion = new JCheckBox();
		cbPlanMedioNuevoMes = new JCheckBox();
		lblPlanMedioRelacion = new JLabel();
		textPlanMedioRelacion = new JTextField();
		btnPlanMedioRelacion = new JButton();
		separatorEstrategia = compFactory.createSeparator("Estrategia");
		lblTarget = new JLabel();
		cmbTarget = new JComboBox();
		lblGuayaquil = new JLabel();
		txtGuayaquil = new JTextField();
		btnGuayaquil = new JButton();
		lblPeriodo = new JLabel();
		cmbPeriodoFechaInicio = new DateComboBox();
		lblPeriodoAl = new JLabel();
		cmbPeriodoFechaFin = new DateComboBox();
		lblQuito = new JLabel();
		txtQuito = new JTextField();
		btnQuito = new JButton();
		lblCobertura = new JLabel();
		spCobertura = new JScrollPane();
		txtCobertura = new JTextArea();
		lblTotal = new JLabel();
		txtTotalCiudad = new JTextField();
		btnTotalCiudad = new JButton();
		lblOtrasConsideraciones = new JLabel();
		spOtrasConsideraciones = new JScrollPane();
		txtOtrasConsideraciones = new JTextArea();
		lblConsideraciones = new JLabel();
		separatorTotales = compFactory.createSeparator("Totales");
		lblSumaPlanMedio = new JLabel();
		txtSumaPlanMedio = new JTextField();
		lblBonificacionVentaPlanMedio = new JLabel();
		txtBonificacionVentaPlanMedio = new JTextField();
		lblDescuentoPlanMedio = new JLabel();
		txtDescuentoPlanMedio = new JTextField();
		lblSubtotalBonificacionVentaPlanMedio = new JLabel();
		txtSubtotalBonificacionVentaPlanMedio = new JTextField();
		lblComisionAgencia = new JLabel();
		txtComisionAgenciaPlanMedio = new JTextField();
		lblIvaPlanMedio = new JLabel();
		txtIvaPlanMedio = new JTextField();
		lblSubtotalPlanMedio = new JLabel();
		txtSubtotalPlanMedio = new JTextField();
		lblTotalPlanMedio = new JLabel();
		txtTotalPlanMedio = new JTextField();
		spPlanMedioPeriodo = new JScrollPane();
		panelPlanMedioPeriodo = new JPanel();
		spTblSubPeriodo = new JScrollPane();
		tblSubPeriodo = new JTable();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		lblSubPeriodo = new JLabel();
		cmbSubPeriodoFechaInicio = new DateComboBox();
		lblSubPeriodoAl = new JLabel();
		cmbSubPeriodoFechaFin = new DateComboBox();
		panelAUE = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spProveedorPrograma = new JScrollPane();
		panelProveedorPrograma = new JPanel();
		lblTipoPeriodo = new JLabel();
		cmbTipoPeriodo = new JComboBox();
		lblMedio = new JLabel();
		cmbMedio = new JComboBox();
		tpArbolMedios = new JTabbedPane();
		spTelevision = new JScrollPane();
		panelTelevision = new JPanel();
		lblComercial = new JLabel();
		spTblComercial = new JScrollPane();
		tblComercial = new JTable();
		cbPautaTelevision = new JCheckBox();
		cbPautaRadio = new JCheckBox();
		cbPautaCine = new JCheckBox();
		cbPautaBasica = new JCheckBox();
		cbAgrupaOrdenes = new JCheckBox();
		cbOrdenPorProductoComercial = new JCheckBox();
		cbOrdenPorVersion = new JCheckBox();
		btnImportarMapaPautaTv = new JButton();
		btnImportarMapaPautaTvMultiple = new JButton();
		btnTvData = new JButton();
		spArbolTelevision = new JScrollPane();
		arbolTelevision = new CheckBoxTree();
		lblCanalTv = new JLabel();
		txtCanalTv = new JTextField();
		lblProgramaTv = new JLabel();
		txtProgramaTv = new JTextField();
		lblHoraInicioPrograma = new JLabel();
		txtHoraInicioPrograma = new JTextField();
		lblHoraFinPrograma = new JLabel();
		txtHoraFinPrograma = new JTextField();
		lblDiasPrograma = new JLabel();
		lblRatingTv = new JLabel();
		txtRatingTv = new JTextField();
		txtVCunaTarifa = new JTextField();
		lblVCunaNegocio = new JLabel();
		txtVCunaNegocio = new JTextField();
		txtDiasPrograma = new JTextField();
		lblVCunaTarifa = new JLabel();
		lblComercialTv = new JLabel();
		txtComercialTv = new JTextField();
		lblDerechoPrograma = new JLabel();
		txtDerechoPrograma = new JTextField();
		lblVersionPrograma = new JLabel();
		txtVersionPrograma = new JTextField();
		btnAgregarProgramaTv = new JButton();
		btnCrearMapaPautaTv = new JButton();
		btnEliminarProgramaTv = new JButton();
		spRadio = new JScrollPane();
		panelRadio = new JPanel();
		spArbolRadio = new JScrollPane();
		arbolRadio = new JTree();
		spPrensa = new JScrollPane();
		panelPrensa = new JPanel();
		spArbolPrensa = new JScrollPane();
		arbolPrensa = new CheckBoxTree();
		lblDiario = new JLabel();
		txtDiario = new JTextField();
		lblSeccion = new JLabel();
		txtSeccion = new JTextField();
		lblUbicacion = new JLabel();
		txtUbicacion = new JTextField();
		lblFormato = new JLabel();
		txtFormato = new JTextField();
		lblAnchoColumnas = new JLabel();
		txtAnchoColumnas = new JTextField();
		lblAltoModulos = new JLabel();
		txtAltoModulos = new JTextField();
		txtAnchoCm = new JTextField();
		lblAnchoCm = new JLabel();
		lblAltoCm = new JLabel();
		txtAltoCm = new JTextField();
		lblColor = new JLabel();
		txtColor = new JTextField();
		lblDia = new JLabel();
		lblTarifa = new JLabel();
		txtTarifa = new JTextField();
		btnCrearMapaPautaPrensa = new JButton();
		txtDia = new JTextField();
		panelMapaPauta = new JPanel();
		lblTipoPeriodoMapa = new JLabel();
		cmbTipoPeriodoMapa = new JComboBox();
		tpMapasPauta = new JTabbedPane();
		scrollPane1 = new JScrollPane();
		panelMapaPautaTv = new JPanel();
		lblProductoProveedor = new JLabel();
		btnCrearOrdenes = new JButton();
		txtProductoProveedor = new JTextField();
		scrollPane2 = new JScrollPane();
		panelTGRPtv = new JPanel();
		spTblTGRPtv = new JScrollPane();
		tblTGRPtv = new JTable();
		lblSuman = new JLabel();
		txtSuman = new JTextField();
		lblDescuento3 = new JLabel();
		txtSuman2 = new JTextField();
		lblDescuento = new JLabel();
		txtDescuento = new JTextField();
		lblDescuento4 = new JLabel();
		txtPorcentajeDescuentoVenta = new JTextField();
		lblDescuento6 = new JLabel();
		txtDescuentoVenta = new JTextField();
		lblSubTotal = new JLabel();
		txtSubTotal = new JTextField();
		lblDescuento5 = new JLabel();
		txtPorcentajeComisionAgencia = new JTextField();
		lblDescuento7 = new JLabel();
		txtComisionAgencia = new JTextField();
		lblBonificacionCompra = new JLabel();
		txtBonificacionCompra = new JTextField();
		lblSubtotalVenta = new JLabel();
		txtSubtotalVenta = new JTextField();
		lblSubtotalBonificacionCompra = new JLabel();
		txtSubtotalBonificacionCompra = new JTextField();
		lblPorcentajeBonifiacionVenta = new JLabel();
		txtPorcentajeBonificacionVenta = new JTextField();
		lblBonificacionVenta = new JLabel();
		txtBonificacionVenta = new JTextField();
		lblIVA = new JLabel();
		txtIVA = new JTextField();
		lblSubtotalBonificacionVenta = new JLabel();
		txtSubtotalBonificacionVenta = new JTextField();
		lblTotalPauta = new JLabel();
		txtTotalPauta = new JTextField();
		lblDescuento9 = new JLabel();
		txtIVA2 = new JTextField();
		lblDescuento10 = new JLabel();
		txtTotalPauta2 = new JTextField();
		scrollPane3 = new JScrollPane();
		panelOrdenesMedios = new JPanel();
		panelDescuentoOrdenMedio = new JPanel();
		cbIvaProveedor = new JCheckBox();
		cbIvaCliente = new JCheckBox();
		btnSetPDsctoOrdenMedio = new JButton();
		lblPorcentajeDescuentoOrdenMedio = new JLabel();
		txtPorcentajeDescuentoOrdenMedio = new JTextField();
		btnSetPDsctoOrdenMedioxProv = new JButton();
		lblPorcentajeBonificacionCompra = new JLabel();
		txtPorcentajeBonificacionCompra = new JTextField();
		btnSetPDsctoOrdenMedioTotal = new JButton();
		panelTipoPagoOrdenMedio = new JPanel();
		rbTipoPagoNormal = new JRadioButton();
		btnSetTipoPago = new JButton();
		rbTipoPagoCanje = new JRadioButton();
		txtPorcentajeCanje = new JTextField();
		btnSetTipoPagoxProv = new JButton();
		cbTipoPagoComision = new JCheckBox();
		btnSetTipoPagoTotal = new JButton();
		cbComisionAdicional = new JCheckBox();
		txtPorcentajeComisionAdicional = new JTextField();
		panelActualizarCodigoOrden = new JPanel();
		lblCodigoOrden = new JLabel();
		textCodigoOrden = new JTextField();
		btnLimpiarCodigoOrden = new JButton();
		btnCambiarCodigo = new JButton();
		panelTotalesOrdenMedio = new JPanel();
		lblSumanOrdenMedio = new JLabel();
		txtSumanOrdenMedio = new JTextField();
		lblSubTotalOrdenMedio = new JLabel();
		txtSubtotalOrdenMedio = new JTextField();
		lblDescuentoOrdenMedio = new JLabel();
		txtDescuentoOrdenMedio = new JTextField();
		lblIVAOrdenMedio = new JLabel();
		txtIVAOrdenMedio = new JTextField();
		lblBonificacionCompraOrden = new JLabel();
		txtBonificacionCompraOrden = new JTextField();
		lblTotalOrdenMedio = new JLabel();
		txtTotalOrdenMedio = new JTextField();
		panelObservacionOrdenMedio = new JPanel();
		spTxtObservacionOrdenMedio = new JScrollPane();
		txtObservacionOrdenMedio = new JTextArea();
		btnSetObservacionOrdenMedio = new JButton();
		btnSetObservacionOrdenMedioxProv = new JButton();
		btnSetObservacionOrdenMedioTotal = new JButton();
		spTblOrdenesMedioCmp = new JScrollPane();
		tblOrdenesMediosCmp = new JTable();
		spTblOrdenesMedio = new JScrollPane();
		tblOrdenesMedios = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== jtpPlanMedio ========
		{

			//======== spPlanMedio ========
			{

				//======== panelPlanMedio ========
				{
					panelPlanMedio.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX7),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(75)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(20)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(42)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(20)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(65)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX7)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY7),
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
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(13), FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(15), FormSpec.NO_GROW),
							new RowSpec(RowSpec.TOP, Sizes.DLUY4, FormSpec.NO_GROW),
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
							new RowSpec(Sizes.DLUY7)
						}));

					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelPlanMedio.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtCodigo, cc.xy(5, 3));

					//---- lblRevision ----
					lblRevision.setText("Revisi\u00f3n:");
					panelPlanMedio.add(lblRevision, cc.xy(9, 3));

					//---- txtRevision ----
					txtRevision.setEditable(false);
					txtRevision.setHorizontalAlignment(SwingConstants.CENTER);
					panelPlanMedio.add(txtRevision, cc.xy(11, 3));

					//---- lblFechaCreacion ----
					lblFechaCreacion.setText("F. Creaci\u00f3n:");
					panelPlanMedio.add(lblFechaCreacion, cc.xywh(21, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtFechaCreacion, cc.xywh(23, 3, 3, 1));

					//---- lblCorporacion ----
					lblCorporacion.setText("Corporaci\u00f3n:");
					panelPlanMedio.add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtCorporacion, cc.xywh(5, 5, 13, 1));
					panelPlanMedio.add(btnCorporacion, cc.xywh(19, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblEstado ----
					lblEstado.setText("Estado:");
					panelPlanMedio.add(lblEstado, cc.xywh(21, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbEstado, cc.xywh(23, 5, 3, 1));

					//---- lblCliente ----
					lblCliente.setText("Cliente:");
					panelPlanMedio.add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtCliente, cc.xywh(5, 7, 13, 1));
					panelPlanMedio.add(btnCliente, cc.xywh(19, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblModificaciones ----
					lblModificaciones.setText("Modificaciones:");
					panelPlanMedio.add(lblModificaciones, cc.xywh(23, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtModificaciones ----
					txtModificaciones.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtModificaciones, cc.xy(25, 7));

					//---- lblOficina ----
					lblOficina.setText("Oficina:");
					panelPlanMedio.add(lblOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtOficina, cc.xywh(5, 9, 13, 1));
					panelPlanMedio.add(btnOficina, cc.xywh(19, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblAutorizacionSAP ----
					lblAutorizacionSAP.setText("SAP:");
					panelPlanMedio.add(lblAutorizacionSAP, cc.xywh(21, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtAutorizacionSAP ----
					txtAutorizacionSAP.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtAutorizacionSAP, cc.xywh(23, 9, 3, 1));

					//---- lblOrdenTrabajo ----
					lblOrdenTrabajo.setText("OT:");
					panelPlanMedio.add(lblOrdenTrabajo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbOrdenTrabajo, cc.xywh(5, 11, 13, 1));

					//---- lblFechaAprobacion ----
					lblFechaAprobacion.setText("F. Aprobaci\u00f3n:");
					panelPlanMedio.add(lblFechaAprobacion, cc.xy(21, 11));
					panelPlanMedio.add(cmbFechaAprobacion, cc.xywh(23, 11, 3, 1));

					//---- lblOrdenTrabajoDetalle ----
					lblOrdenTrabajoDetalle.setText("Detalle OT:");
					panelPlanMedio.add(lblOrdenTrabajoDetalle, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbOrdenTrabajoDetalle, cc.xywh(5, 13, 13, 1));

					//---- cbPrepago ----
					cbPrepago.setText("Prepago");
					panelPlanMedio.add(cbPrepago, cc.xy(23, 13));

					//---- lblCampana ----
					lblCampana.setText("Campa\u00f1a:");
					panelPlanMedio.add(lblCampana, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtCampana ----
					txtCampana.setEditable(false);
					panelPlanMedio.add(txtCampana, cc.xywh(5, 15, 13, 1));

					//---- lblConcepto ----
					lblConcepto.setText("Concepto:");
					panelPlanMedio.add(lblConcepto, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtConcepto, cc.xywh(5, 17, 13, 1));

					//---- cbPlanMedioNuevaVersion ----
					cbPlanMedioNuevaVersion.setText("Nueva Versi\u00f3n");
					panelPlanMedio.add(cbPlanMedioNuevaVersion, cc.xy(5, 19));

					//---- cbPlanMedioNuevoMes ----
					cbPlanMedioNuevoMes.setText("Nuevo Mes");
					panelPlanMedio.add(cbPlanMedioNuevoMes, cc.xywh(11, 19, 3, 1));

					//---- lblPlanMedioRelacion ----
					lblPlanMedioRelacion.setText("Relaci\u00f3n con:");
					panelPlanMedio.add(lblPlanMedioRelacion, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(textPlanMedioRelacion, cc.xywh(19, 19, 3, 1));
					panelPlanMedio.add(btnPlanMedioRelacion, cc.xywh(23, 19, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					panelPlanMedio.add(separatorEstrategia, cc.xywh(3, 21, 23, 1));

					//---- lblTarget ----
					lblTarget.setText("Target:");
					panelPlanMedio.add(lblTarget, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbTarget, cc.xywh(5, 23, 13, 1));

					//---- lblGuayaquil ----
					lblGuayaquil.setText("Guayaquil:");
					panelPlanMedio.add(lblGuayaquil, cc.xywh(21, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtGuayaquil ----
					txtGuayaquil.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtGuayaquil, cc.xy(23, 23));
					panelPlanMedio.add(btnGuayaquil, cc.xywh(25, 23, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblPeriodo ----
					lblPeriodo.setText("Per\u00edodo del:");
					panelPlanMedio.add(lblPeriodo, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbPeriodoFechaInicio, cc.xywh(5, 25, 3, 1));

					//---- lblPeriodoAl ----
					lblPeriodoAl.setText("al:");
					panelPlanMedio.add(lblPeriodoAl, cc.xywh(9, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbPeriodoFechaFin, cc.xywh(11, 25, 5, 1));

					//---- lblQuito ----
					lblQuito.setText("Quito:");
					panelPlanMedio.add(lblQuito, cc.xywh(21, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtQuito ----
					txtQuito.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtQuito, cc.xy(23, 25));
					panelPlanMedio.add(btnQuito, cc.xywh(25, 25, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblCobertura ----
					lblCobertura.setText("Cobertura:");
					panelPlanMedio.add(lblCobertura, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//======== spCobertura ========
					{

						//---- txtCobertura ----
						txtCobertura.setLineWrap(false);
						spCobertura.setViewportView(txtCobertura);
					}
					panelPlanMedio.add(spCobertura, cc.xywh(5, 27, 13, 3));

					//---- lblTotal ----
					lblTotal.setText("Total:");
					panelPlanMedio.add(lblTotal, cc.xywh(21, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtTotalCiudad ----
					txtTotalCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtTotalCiudad, cc.xy(23, 27));
					panelPlanMedio.add(btnTotalCiudad, cc.xywh(25, 27, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblOtrasConsideraciones ----
					lblOtrasConsideraciones.setText("Otras");
					panelPlanMedio.add(lblOtrasConsideraciones, cc.xywh(3, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//======== spOtrasConsideraciones ========
					{
						spOtrasConsideraciones.setViewportView(txtOtrasConsideraciones);
					}
					panelPlanMedio.add(spOtrasConsideraciones, cc.xywh(5, 31, 13, 3));

					//---- lblConsideraciones ----
					lblConsideraciones.setText("Consideraciones:");
					panelPlanMedio.add(lblConsideraciones, cc.xywh(3, 33, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(separatorTotales, cc.xywh(3, 35, 23, 1));

					//---- lblSumaPlanMedio ----
					lblSumaPlanMedio.setText("Suma:");
					panelPlanMedio.add(lblSumaPlanMedio, cc.xywh(3, 37, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtSumaPlanMedio ----
					txtSumaPlanMedio.setEditable(false);
					txtSumaPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtSumaPlanMedio, cc.xy(5, 37));

					//---- lblBonificacionVentaPlanMedio ----
					lblBonificacionVentaPlanMedio.setText("Bonificaci\u00f3n:");
					panelPlanMedio.add(lblBonificacionVentaPlanMedio, cc.xywh(7, 37, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtBonificacionVentaPlanMedio ----
					txtBonificacionVentaPlanMedio.setEditable(false);
					txtBonificacionVentaPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtBonificacionVentaPlanMedio, cc.xywh(11, 37, 3, 1));

					//---- lblDescuentoPlanMedio ----
					lblDescuentoPlanMedio.setText("Descuento:");
					panelPlanMedio.add(lblDescuentoPlanMedio, cc.xywh(3, 39, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDescuentoPlanMedio ----
					txtDescuentoPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					txtDescuentoPlanMedio.setEditable(false);
					panelPlanMedio.add(txtDescuentoPlanMedio, cc.xy(5, 39));

					//---- lblSubtotalBonificacionVentaPlanMedio ----
					lblSubtotalBonificacionVentaPlanMedio.setText("SubTotal:");
					panelPlanMedio.add(lblSubtotalBonificacionVentaPlanMedio, cc.xywh(7, 39, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtSubtotalBonificacionVentaPlanMedio ----
					txtSubtotalBonificacionVentaPlanMedio.setEditable(false);
					txtSubtotalBonificacionVentaPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtSubtotalBonificacionVentaPlanMedio, cc.xywh(11, 39, 3, 1));

					//---- lblComisionAgencia ----
					lblComisionAgencia.setText("Comisi\u00f3n Agencia:");
					panelPlanMedio.add(lblComisionAgencia, cc.xywh(3, 41, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtComisionAgenciaPlanMedio ----
					txtComisionAgenciaPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					txtComisionAgenciaPlanMedio.setEditable(false);
					panelPlanMedio.add(txtComisionAgenciaPlanMedio, cc.xy(5, 41));

					//---- lblIvaPlanMedio ----
					lblIvaPlanMedio.setText("IVA:");
					panelPlanMedio.add(lblIvaPlanMedio, cc.xywh(9, 41, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtIvaPlanMedio ----
					txtIvaPlanMedio.setEditable(false);
					txtIvaPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtIvaPlanMedio, cc.xywh(11, 41, 3, 1));

					//---- lblSubtotalPlanMedio ----
					lblSubtotalPlanMedio.setText("SubTotal:");
					panelPlanMedio.add(lblSubtotalPlanMedio, cc.xywh(3, 43, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtSubtotalPlanMedio ----
					txtSubtotalPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					txtSubtotalPlanMedio.setEditable(false);
					panelPlanMedio.add(txtSubtotalPlanMedio, cc.xy(5, 43));

					//---- lblTotalPlanMedio ----
					lblTotalPlanMedio.setText("Total:");
					panelPlanMedio.add(lblTotalPlanMedio, cc.xywh(9, 43, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtTotalPlanMedio ----
					txtTotalPlanMedio.setEditable(false);
					txtTotalPlanMedio.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPlanMedio.add(txtTotalPlanMedio, cc.xywh(11, 43, 3, 1));
				}
				spPlanMedio.setViewportView(panelPlanMedio);
			}
			jtpPlanMedio.addTab("Estrategia", spPlanMedio);


			//======== spPlanMedioPeriodo ========
			{

				//======== panelPlanMedioPeriodo ========
				{
					panelPlanMedioPeriodo.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX7),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX7)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY7),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(100)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY7)
						}));

					//======== spTblSubPeriodo ========
					{

						//---- tblSubPeriodo ----
						tblSubPeriodo.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Tipo", "Del", "Al"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblSubPeriodo.setPreferredScrollableViewportSize(new Dimension(450, 150));
						spTblSubPeriodo.setViewportView(tblSubPeriodo);
					}
					panelPlanMedioPeriodo.add(spTblSubPeriodo, cc.xywh(3, 11, 9, 5));

					//---- lblTipo ----
					lblTipo.setText("Tipo:");
					panelPlanMedioPeriodo.add(lblTipo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedioPeriodo.add(cmbTipo, cc.xy(5, 3));

					//---- lblSubPeriodo ----
					lblSubPeriodo.setText("Periodo del:");
					panelPlanMedioPeriodo.add(lblSubPeriodo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedioPeriodo.add(cmbSubPeriodoFechaInicio, cc.xy(5, 5));

					//---- lblSubPeriodoAl ----
					lblSubPeriodoAl.setText("al:");
					panelPlanMedioPeriodo.add(lblSubPeriodoAl, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedioPeriodo.add(cmbSubPeriodoFechaFin, cc.xy(9, 5));

					//======== panelAUE ========
					{
						panelAUE.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- btnAgregarDetalle ----
						btnAgregarDetalle.setText("A");
						panelAUE.add(btnAgregarDetalle, cc.xy(1, 1));

						//---- btnActualizarDetalle ----
						btnActualizarDetalle.setText("U");
						panelAUE.add(btnActualizarDetalle, cc.xy(3, 1));

						//---- btnEliminarDetalle ----
						btnEliminarDetalle.setText("E");
						panelAUE.add(btnEliminarDetalle, cc.xy(5, 1));
					}
					panelPlanMedioPeriodo.add(panelAUE, cc.xywh(3, 9, 3, 1));
				}
				spPlanMedioPeriodo.setViewportView(panelPlanMedioPeriodo);
			}
			jtpPlanMedio.addTab("Planificaci\u00f3n", spPlanMedioPeriodo);


			//======== spProveedorPrograma ========
			{
				spProveedorPrograma.setEnabled(true);

				//======== panelProveedorPrograma ========
				{
					panelProveedorPrograma.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX7),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(140)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(250), FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX7)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY7),
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
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY7)
						}));

					//---- lblTipoPeriodo ----
					lblTipoPeriodo.setText("Tipo - Periodo:");
					panelProveedorPrograma.add(lblTipoPeriodo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedorPrograma.add(cmbTipoPeriodo, cc.xywh(5, 3, 3, 1));

					//---- lblMedio ----
					lblMedio.setText("Medio:");
					panelProveedorPrograma.add(lblMedio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedorPrograma.add(cmbMedio, cc.xy(5, 5));

					//======== tpArbolMedios ========
					{

						//======== spTelevision ========
						{
							spTelevision.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

							//======== panelTelevision ========
							{
								panelTelevision.setLayout(new FormLayout(
									new ColumnSpec[] {
										new ColumnSpec(Sizes.dluX(10)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(50)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(60)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(60)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(62)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(62)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(10))
									},
									new RowSpec[] {
										new RowSpec(Sizes.dluY(10)),
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										new RowSpec(Sizes.dluY(77)),
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										new RowSpec(Sizes.DLUY4),
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
										new RowSpec(Sizes.dluY(10)),
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										new RowSpec(Sizes.dluY(10))
									}));

								//---- lblComercial ----
								lblComercial.setText("Versiones:");
								panelTelevision.add(lblComercial, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//======== spTblComercial ========
								{

									//---- tblComercial ----
									tblComercial.setModel(new DefaultTableModel(
										new Object[][] {
										},
										new String[] {
											" ", "C\u00f3digo", "Versi\u00f3n", "Der. Programa", "Identificaci\u00f3n", "Duraci\u00f3n"
										}
									) {
										Class[] columnTypes = new Class[] {
											Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class
										};
										boolean[] columnEditable = new boolean[] {
											true, false, false, false, false, false
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
									spTblComercial.setViewportView(tblComercial);
								}
								panelTelevision.add(spTblComercial, cc.xywh(5, 5, 17, 3));

								//---- cbPautaTelevision ----
								cbPautaTelevision.setText("Televisi\u00f3n");
								panelTelevision.add(cbPautaTelevision, cc.xy(5, 9));

								//---- cbPautaRadio ----
								cbPautaRadio.setText("Radio");
								panelTelevision.add(cbPautaRadio, cc.xywh(9, 9, 3, 1));

								//---- cbPautaCine ----
								cbPautaCine.setText("Cine");
								panelTelevision.add(cbPautaCine, cc.xy(13, 9));

								//---- cbPautaBasica ----
								cbPautaBasica.setText("Pauta b\u00e1sica");
								panelTelevision.add(cbPautaBasica, cc.xy(5, 11));

								//---- cbAgrupaOrdenes ----
								cbAgrupaOrdenes.setText("Orden por Medio");
								panelTelevision.add(cbAgrupaOrdenes, cc.xy(5, 13));

								//---- cbOrdenPorProductoComercial ----
								cbOrdenPorProductoComercial.setText("Orden por Producto Comercial");
								panelTelevision.add(cbOrdenPorProductoComercial, cc.xywh(9, 13, 5, 1, CellConstraints.FILL, CellConstraints.FILL));

								//---- cbOrdenPorVersion ----
								cbOrdenPorVersion.setText("Orden por Comercial");
								panelTelevision.add(cbOrdenPorVersion, cc.xywh(15, 13, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

								//---- btnImportarMapaPautaTv ----
								btnImportarMapaPautaTv.setText("Excel B\u00e1");
								panelTelevision.add(btnImportarMapaPautaTv, cc.xy(11, 17));

								//---- btnImportarMapaPautaTvMultiple ----
								btnImportarMapaPautaTvMultiple.setText("Cargar Excel");
								panelTelevision.add(btnImportarMapaPautaTvMultiple, cc.xy(5, 17));

								//---- btnTvData ----
								btnTvData.setText("C. Excel");
								panelTelevision.add(btnTvData, cc.xywh(5, 19, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

								//======== spArbolTelevision ========
								{

									//---- arbolTelevision ----
									arbolTelevision.setVisibleRowCount(20);
									spArbolTelevision.setViewportView(arbolTelevision);
								}
								panelTelevision.add(spArbolTelevision, cc.xywh(3, 21, 9, 27));

								//---- lblCanalTv ----
								lblCanalTv.setText("Canal:");
								panelTelevision.add(lblCanalTv, cc.xywh(13, 21, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtCanalTv, cc.xywh(15, 21, 5, 1));

								//---- lblProgramaTv ----
								lblProgramaTv.setText("Programa:");
								panelTelevision.add(lblProgramaTv, cc.xywh(13, 23, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtProgramaTv, cc.xywh(15, 23, 5, 1));

								//---- lblHoraInicioPrograma ----
								lblHoraInicioPrograma.setText("Hora Inicio:");
								panelTelevision.add(lblHoraInicioPrograma, cc.xywh(13, 25, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtHoraInicioPrograma, cc.xy(15, 25));

								//---- lblHoraFinPrograma ----
								lblHoraFinPrograma.setText("Hora Fin:");
								panelTelevision.add(lblHoraFinPrograma, cc.xywh(17, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtHoraFinPrograma, cc.xy(19, 25));

								//---- lblDiasPrograma ----
								lblDiasPrograma.setText("D\u00edas:");
								panelTelevision.add(lblDiasPrograma, cc.xywh(13, 27, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- lblRatingTv ----
								lblRatingTv.setText("Rating:");
								panelTelevision.add(lblRatingTv, cc.xywh(17, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtRatingTv, cc.xy(19, 27));
								panelTelevision.add(txtVCunaTarifa, cc.xy(15, 29));

								//---- lblVCunaNegocio ----
								lblVCunaNegocio.setText("V. Cu\u00f1a Negocio:");
								panelTelevision.add(lblVCunaNegocio, cc.xywh(17, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtVCunaNegocio, cc.xy(19, 29));
								panelTelevision.add(txtDiasPrograma, cc.xy(15, 27));

								//---- lblVCunaTarifa ----
								lblVCunaTarifa.setText("V. Cu\u00f1a Tarifa:");
								panelTelevision.add(lblVCunaTarifa, cc.xywh(13, 29, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- lblComercialTv ----
								lblComercialTv.setText("Comercial:");
								panelTelevision.add(lblComercialTv, cc.xywh(13, 31, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtComercialTv, cc.xywh(15, 31, 5, 1));

								//---- lblDerechoPrograma ----
								lblDerechoPrograma.setText("Der. Programa:");
								panelTelevision.add(lblDerechoPrograma, cc.xywh(13, 33, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtDerechoPrograma, cc.xywh(15, 33, 3, 1));

								//---- lblVersionPrograma ----
								lblVersionPrograma.setText("Versi\u00f3n:");
								panelTelevision.add(lblVersionPrograma, cc.xywh(13, 35, 2, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtVersionPrograma, cc.xy(15, 35));

								//---- btnAgregarProgramaTv ----
								btnAgregarProgramaTv.setText("Add Programa");
								panelTelevision.add(btnAgregarProgramaTv, cc.xy(15, 37));

								//---- btnCrearMapaPautaTv ----
								btnCrearMapaPautaTv.setText("Crear Mapa Pauta");
								panelTelevision.add(btnCrearMapaPautaTv, cc.xy(19, 37));

								//---- btnEliminarProgramaTv ----
								btnEliminarProgramaTv.setText("Delete Programa");
								panelTelevision.add(btnEliminarProgramaTv, cc.xy(17, 37));
							}
							spTelevision.setViewportView(panelTelevision);
						}
						tpArbolMedios.addTab("Medios", spTelevision);


						//======== spRadio ========
						{

							//======== panelRadio ========
							{
								panelRadio.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(12)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
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
									new RowSpec[] {
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
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC
									}));

								//======== spArbolRadio ========
								{
									spArbolRadio.setViewportView(arbolRadio);
								}
								panelRadio.add(spArbolRadio, cc.xywh(3, 3, 5, 9));
							}
							spRadio.setViewportView(panelRadio);
						}
						tpArbolMedios.addTab("M1", spRadio);


						//======== spPrensa ========
						{

							//======== panelPrensa ========
							{
								panelPrensa.setLayout(new FormLayout(
									new ColumnSpec[] {
										new ColumnSpec(Sizes.dluX(10)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(12)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(50)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(20)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(50)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(30)),
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
										new RowSpec(Sizes.DLUY7),
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

								//======== spArbolPrensa ========
								{

									//---- arbolPrensa ----
									arbolPrensa.setVisibleRowCount(20);
									spArbolPrensa.setViewportView(arbolPrensa);
								}
								panelPrensa.add(spArbolPrensa, cc.xywh(3, 3, 5, 25));

								//---- lblDiario ----
								lblDiario.setText("Diario:");
								panelPrensa.add(lblDiario, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelPrensa.add(txtDiario, cc.xywh(13, 3, 9, 1));

								//---- lblSeccion ----
								lblSeccion.setText("Secci\u00f3n:");
								panelPrensa.add(lblSeccion, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelPrensa.add(txtSeccion, cc.xywh(13, 5, 9, 1));

								//---- lblUbicacion ----
								lblUbicacion.setText("Ubicaci\u00f3n:");
								panelPrensa.add(lblUbicacion, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelPrensa.add(txtUbicacion, cc.xywh(13, 7, 9, 1));

								//---- lblFormato ----
								lblFormato.setText("Formato:");
								panelPrensa.add(lblFormato, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelPrensa.add(txtFormato, cc.xywh(13, 9, 9, 1));

								//---- lblAnchoColumnas ----
								lblAnchoColumnas.setText("Ancho (Columnas):");
								panelPrensa.add(lblAnchoColumnas, cc.xywh(11, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtAnchoColumnas ----
								txtAnchoColumnas.setHorizontalAlignment(SwingConstants.RIGHT);
								panelPrensa.add(txtAnchoColumnas, cc.xy(13, 11));

								//---- lblAltoModulos ----
								lblAltoModulos.setText("Alto (Modulos):");
								panelPrensa.add(lblAltoModulos, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtAltoModulos ----
								txtAltoModulos.setHorizontalAlignment(SwingConstants.RIGHT);
								panelPrensa.add(txtAltoModulos, cc.xy(19, 11));

								//---- txtAnchoCm ----
								txtAnchoCm.setHorizontalAlignment(SwingConstants.RIGHT);
								panelPrensa.add(txtAnchoCm, cc.xy(13, 13));

								//---- lblAnchoCm ----
								lblAnchoCm.setText("Ancho (Cm.):");
								panelPrensa.add(lblAnchoCm, cc.xywh(11, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- lblAltoCm ----
								lblAltoCm.setText("Alto (Cm.):");
								panelPrensa.add(lblAltoCm, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtAltoCm ----
								txtAltoCm.setHorizontalAlignment(SwingConstants.RIGHT);
								panelPrensa.add(txtAltoCm, cc.xy(19, 13));

								//---- lblColor ----
								lblColor.setText("Color:");
								panelPrensa.add(lblColor, cc.xywh(11, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelPrensa.add(txtColor, cc.xy(13, 15));

								//---- lblDia ----
								lblDia.setText("D\u00eda:");
								panelPrensa.add(lblDia, cc.xywh(17, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- lblTarifa ----
								lblTarifa.setText("Tarifa($):");
								panelPrensa.add(lblTarifa, cc.xywh(11, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtTarifa ----
								txtTarifa.setHorizontalAlignment(SwingConstants.RIGHT);
								panelPrensa.add(txtTarifa, cc.xywh(13, 17, 3, 1));

								//---- btnCrearMapaPautaPrensa ----
								btnCrearMapaPautaPrensa.setText("Crear Mapa Pauta");
								panelPrensa.add(btnCrearMapaPautaPrensa, cc.xywh(19, 21, 3, 1));
								panelPrensa.add(txtDia, cc.xy(19, 15));
							}
							spPrensa.setViewportView(panelPrensa);
						}
						tpArbolMedios.addTab("M2", spPrensa);

					}
					panelProveedorPrograma.add(tpArbolMedios, cc.xywh(3, 9, 9, 5));
				}
				spProveedorPrograma.setViewportView(panelProveedorPrograma);
			}
			jtpPlanMedio.addTab("Medios", spProveedorPrograma);


			//======== panelMapaPauta ========
			{
				panelMapaPauta.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(240)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(17))
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblTipoPeriodoMapa ----
				lblTipoPeriodoMapa.setText("Tipo - Periodo:");
				panelMapaPauta.add(lblTipoPeriodoMapa, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelMapaPauta.add(cmbTipoPeriodoMapa, cc.xy(5, 3));

				//======== tpMapasPauta ========
				{

					//======== scrollPane1 ========
					{

						//======== panelMapaPautaTv ========
						{
							panelMapaPautaTv.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec("max(default;150dlu)"),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.DLUX5)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(RowSpec.CENTER, Sizes.dluY(150), FormSpec.DEFAULT_GROW),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.dluY(10))
								}));

							//---- lblProductoProveedor ----
							lblProductoProveedor.setText("Producto del Proveedor:");
							panelMapaPautaTv.add(lblProductoProveedor, cc.xy(3, 13));

							//---- btnCrearOrdenes ----
							btnCrearOrdenes.setText("Imprimir Ordenes");
							panelMapaPautaTv.add(btnCrearOrdenes, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtProductoProveedor ----
							txtProductoProveedor.setEditable(false);
							panelMapaPautaTv.add(txtProductoProveedor, cc.xywh(5, 13, 3, 1));
						}
						scrollPane1.setViewportView(panelMapaPautaTv);
					}
					tpMapasPauta.addTab("Medio", scrollPane1);


					//======== scrollPane2 ========
					{

						//======== panelTGRPtv ========
						{
							panelTGRPtv.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(10)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(90)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(98)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(75), FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(34)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(77)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(100)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(10))
								},
								new RowSpec[] {
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.dluY(180)),
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

							//======== spTblTGRPtv ========
							{

								//---- tblTGRPtv ----
								tblTGRPtv.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"#", "H.INI", "MEDIO", "PROGRAMA", "COMERCIAL", "CU\u00d1AS", "GYE", "UIO", "PON", "TRPS", "TARIFA", "VALOR TOTAL"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false, false, false, false, false, false, false, false, false, false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								{
									TableColumnModel cm = tblTGRPtv.getColumnModel();
									cm.getColumn(0).setPreferredWidth(30);
								}
								spTblTGRPtv.setViewportView(tblTGRPtv);
							}
							panelTGRPtv.add(spTblTGRPtv, cc.xywh(3, 3, 17, 5));

							//---- lblSuman ----
							lblSuman.setText("Suman:");
							panelTGRPtv.add(lblSuman, cc.xywh(7, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtSuman ----
							txtSuman.setEditable(false);
							txtSuman.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtSuman, cc.xy(9, 11));

							//---- lblDescuento3 ----
							lblDescuento3.setText("Suman:");
							panelTGRPtv.add(lblDescuento3, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtSuman2 ----
							txtSuman2.setEditable(false);
							txtSuman2.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtSuman2, cc.xy(19, 11));

							//---- lblDescuento ----
							lblDescuento.setText("Descuento Agencia [$]:");
							panelTGRPtv.add(lblDescuento, cc.xywh(7, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtDescuento ----
							txtDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
							txtDescuento.setEditable(false);
							panelTGRPtv.add(txtDescuento, cc.xy(9, 13));

							//---- lblDescuento4 ----
							lblDescuento4.setText("Descuento Agencia [%]:");
							panelTGRPtv.add(lblDescuento4, cc.xywh(13, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtPorcentajeDescuentoVenta ----
							txtPorcentajeDescuentoVenta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtPorcentajeDescuentoVenta, cc.xy(15, 13));

							//---- lblDescuento6 ----
							lblDescuento6.setText("Descuento Agencia [$]:");
							panelTGRPtv.add(lblDescuento6, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtDescuentoVenta ----
							txtDescuentoVenta.setEditable(false);
							txtDescuentoVenta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtDescuentoVenta, cc.xy(19, 13));

							//---- lblSubTotal ----
							lblSubTotal.setText("SubTotal:");
							panelTGRPtv.add(lblSubTotal, cc.xywh(7, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtSubTotal ----
							txtSubTotal.setEditable(false);
							txtSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtSubTotal, cc.xy(9, 15));

							//---- lblDescuento5 ----
							lblDescuento5.setText("Comisi\u00f3n Agencia [%]:");
							panelTGRPtv.add(lblDescuento5, cc.xywh(13, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtPorcentajeComisionAgencia ----
							txtPorcentajeComisionAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtPorcentajeComisionAgencia, cc.xy(15, 15));

							//---- lblDescuento7 ----
							lblDescuento7.setText("Comisi\u00f3n Agencia [$]:");
							panelTGRPtv.add(lblDescuento7, cc.xywh(17, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtComisionAgencia ----
							txtComisionAgencia.setEditable(false);
							txtComisionAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtComisionAgencia, cc.xy(19, 15));

							//---- lblBonificacionCompra ----
							lblBonificacionCompra.setText("Bonificaci\u00f3n [$]:");
							panelTGRPtv.add(lblBonificacionCompra, cc.xywh(7, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtBonificacionCompra ----
							txtBonificacionCompra.setEditable(false);
							txtBonificacionCompra.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtBonificacionCompra, cc.xy(9, 17));

							//---- lblSubtotalVenta ----
							lblSubtotalVenta.setText("SubTotal:");
							panelTGRPtv.add(lblSubtotalVenta, cc.xywh(17, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtSubtotalVenta ----
							txtSubtotalVenta.setEditable(false);
							txtSubtotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtSubtotalVenta, cc.xy(19, 17));

							//---- lblSubtotalBonificacionCompra ----
							lblSubtotalBonificacionCompra.setText("SubTotal:");
							panelTGRPtv.add(lblSubtotalBonificacionCompra, cc.xywh(7, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtSubtotalBonificacionCompra ----
							txtSubtotalBonificacionCompra.setEditable(false);
							txtSubtotalBonificacionCompra.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtSubtotalBonificacionCompra, cc.xy(9, 19));

							//---- lblPorcentajeBonifiacionVenta ----
							lblPorcentajeBonifiacionVenta.setText("Bonificaci\u00f3n [%]:");
							panelTGRPtv.add(lblPorcentajeBonifiacionVenta, cc.xywh(13, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtPorcentajeBonificacionVenta ----
							txtPorcentajeBonificacionVenta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtPorcentajeBonificacionVenta, cc.xy(15, 19));

							//---- lblBonificacionVenta ----
							lblBonificacionVenta.setText("Bonificaci\u00f3n [$]:");
							panelTGRPtv.add(lblBonificacionVenta, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtBonificacionVenta ----
							txtBonificacionVenta.setEditable(false);
							txtBonificacionVenta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtBonificacionVenta, cc.xy(19, 19));

							//---- lblIVA ----
							lblIVA.setText("IVA:");
							panelTGRPtv.add(lblIVA, cc.xywh(7, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtIVA ----
							txtIVA.setEditable(false);
							txtIVA.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtIVA, cc.xy(9, 21));

							//---- lblSubtotalBonificacionVenta ----
							lblSubtotalBonificacionVenta.setText("SubTotal:");
							panelTGRPtv.add(lblSubtotalBonificacionVenta, cc.xywh(17, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtSubtotalBonificacionVenta ----
							txtSubtotalBonificacionVenta.setEditable(false);
							txtSubtotalBonificacionVenta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtSubtotalBonificacionVenta, cc.xy(19, 21));

							//---- lblTotalPauta ----
							lblTotalPauta.setText("Total:");
							panelTGRPtv.add(lblTotalPauta, cc.xywh(7, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtTotalPauta ----
							txtTotalPauta.setEditable(false);
							txtTotalPauta.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtTotalPauta, cc.xy(9, 23));

							//---- lblDescuento9 ----
							lblDescuento9.setText("IVA:");
							panelTGRPtv.add(lblDescuento9, cc.xywh(17, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtIVA2 ----
							txtIVA2.setEditable(false);
							txtIVA2.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtIVA2, cc.xy(19, 23));

							//---- lblDescuento10 ----
							lblDescuento10.setText("Total:");
							panelTGRPtv.add(lblDescuento10, cc.xywh(17, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtTotalPauta2 ----
							txtTotalPauta2.setEditable(false);
							txtTotalPauta2.setHorizontalAlignment(SwingConstants.RIGHT);
							panelTGRPtv.add(txtTotalPauta2, cc.xy(19, 25));
						}
						scrollPane2.setViewportView(panelTGRPtv);
					}
					tpMapasPauta.addTab("TGRP", scrollPane2);


					//======== scrollPane3 ========
					{

						//======== panelOrdenesMedios ========
						{
							panelOrdenesMedios.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX8),
									new ColumnSpec(Sizes.dluX(130)),
									new ColumnSpec(Sizes.DLUX4),
									new ColumnSpec(Sizes.dluX(115)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(35)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(87)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(47), FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY8),
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
									FormFactory.DEFAULT_ROWSPEC
								}));

							//======== panelDescuentoOrdenMedio ========
							{
								panelDescuentoOrdenMedio.setBorder(new TitledBorder("Descuento y condiciones IVA"));
								panelDescuentoOrdenMedio.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										new ColumnSpec(Sizes.DLUX5),
										new ColumnSpec(Sizes.dluX(50)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									RowSpec.decodeSpecs("default, 19dlu, default")));

								//---- cbIvaProveedor ----
								cbIvaProveedor.setText("IVA Proveedor");
								cbIvaProveedor.setSelected(true);
								panelDescuentoOrdenMedio.add(cbIvaProveedor, cc.xy(1, 1));

								//---- cbIvaCliente ----
								cbIvaCliente.setText("IVA Cliente");
								cbIvaCliente.setSelected(true);
								panelDescuentoOrdenMedio.add(cbIvaCliente, cc.xy(3, 1));

								//---- btnSetPDsctoOrdenMedio ----
								btnSetPDsctoOrdenMedio.setText("Actualizar Orden");
								panelDescuentoOrdenMedio.add(btnSetPDsctoOrdenMedio, cc.xy(5, 1));

								//---- lblPorcentajeDescuentoOrdenMedio ----
								lblPorcentajeDescuentoOrdenMedio.setText("Desc. Agencia [%]:");
								panelDescuentoOrdenMedio.add(lblPorcentajeDescuentoOrdenMedio, cc.xywh(1, 2, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtPorcentajeDescuentoOrdenMedio ----
								txtPorcentajeDescuentoOrdenMedio.setHorizontalAlignment(SwingConstants.RIGHT);
								panelDescuentoOrdenMedio.add(txtPorcentajeDescuentoOrdenMedio, cc.xy(3, 2));

								//---- btnSetPDsctoOrdenMedioxProv ----
								btnSetPDsctoOrdenMedioxProv.setText("Actualizar Medio");
								panelDescuentoOrdenMedio.add(btnSetPDsctoOrdenMedioxProv, cc.xy(5, 2));

								//---- lblPorcentajeBonificacionCompra ----
								lblPorcentajeBonificacionCompra.setText("Bonificaci\u00f3n [%]:");
								panelDescuentoOrdenMedio.add(lblPorcentajeBonificacionCompra, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtPorcentajeBonificacionCompra ----
								txtPorcentajeBonificacionCompra.setHorizontalAlignment(SwingConstants.RIGHT);
								panelDescuentoOrdenMedio.add(txtPorcentajeBonificacionCompra, cc.xy(3, 3));

								//---- btnSetPDsctoOrdenMedioTotal ----
								btnSetPDsctoOrdenMedioTotal.setText("Actualizar Todos");
								panelDescuentoOrdenMedio.add(btnSetPDsctoOrdenMedioTotal, cc.xy(5, 3));
							}
							panelOrdenesMedios.add(panelDescuentoOrdenMedio, cc.xywh(2, 3, 3, 1));

							//======== panelTipoPagoOrdenMedio ========
							{
								panelTipoPagoOrdenMedio.setBorder(new TitledBorder("Forma de Pago"));
								panelTipoPagoOrdenMedio.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(30)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.DLUX3),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(30))
									},
									new RowSpec[] {
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC
									}));

								//---- rbTipoPagoNormal ----
								rbTipoPagoNormal.setText("Normal");
								panelTipoPagoOrdenMedio.add(rbTipoPagoNormal, cc.xy(1, 1));

								//---- btnSetTipoPago ----
								btnSetTipoPago.setText("Actualizar Orden");
								panelTipoPagoOrdenMedio.add(btnSetTipoPago, cc.xy(5, 1));

								//---- rbTipoPagoCanje ----
								rbTipoPagoCanje.setText("Facturaci\u00f3n Directa [%]:");
								panelTipoPagoOrdenMedio.add(rbTipoPagoCanje, cc.xy(1, 3));

								//---- txtPorcentajeCanje ----
								txtPorcentajeCanje.setHorizontalAlignment(SwingConstants.RIGHT);
								panelTipoPagoOrdenMedio.add(txtPorcentajeCanje, cc.xy(3, 3));

								//---- btnSetTipoPagoxProv ----
								btnSetTipoPagoxProv.setText("Actualizar Medio");
								panelTipoPagoOrdenMedio.add(btnSetTipoPagoxProv, cc.xy(5, 3));

								//---- cbTipoPagoComision ----
								cbTipoPagoComision.setText("Comisi\u00f3n Directa");
								panelTipoPagoOrdenMedio.add(cbTipoPagoComision, cc.xy(1, 5));

								//---- btnSetTipoPagoTotal ----
								btnSetTipoPagoTotal.setText("Actualizar Todos");
								panelTipoPagoOrdenMedio.add(btnSetTipoPagoTotal, cc.xy(5, 5));

								//---- cbComisionAdicional ----
								cbComisionAdicional.setText("Comisi\u00f3n Adicional [%]:");
								panelTipoPagoOrdenMedio.add(cbComisionAdicional, cc.xy(9, 5));

								//---- txtPorcentajeComisionAdicional ----
								txtPorcentajeComisionAdicional.setHorizontalAlignment(SwingConstants.RIGHT);
								panelTipoPagoOrdenMedio.add(txtPorcentajeComisionAdicional, cc.xy(11, 5));
							}
							panelOrdenesMedios.add(panelTipoPagoOrdenMedio, cc.xywh(6, 2, 5, 2));

							//======== panelActualizarCodigoOrden ========
							{
								panelActualizarCodigoOrden.setBorder(new TitledBorder("Actualizar C\u00f3digo"));
								panelActualizarCodigoOrden.setLayout(new FormLayout(
									new ColumnSpec[] {
										new ColumnSpec(Sizes.dluX(31)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(47)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC
									},
									new RowSpec[] {
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC
									}));

								//---- lblCodigoOrden ----
								lblCodigoOrden.setText("C\u00f3digo:");
								panelActualizarCodigoOrden.add(lblCodigoOrden, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelActualizarCodigoOrden.add(textCodigoOrden, cc.xy(3, 1));

								//---- btnLimpiarCodigoOrden ----
								btnLimpiarCodigoOrden.setText("Limpiar");
								panelActualizarCodigoOrden.add(btnLimpiarCodigoOrden, cc.xy(5, 1));

								//---- btnCambiarCodigo ----
								btnCambiarCodigo.setText("Cambiar");
								panelActualizarCodigoOrden.add(btnCambiarCodigo, cc.xy(5, 3));
							}
							panelOrdenesMedios.add(panelActualizarCodigoOrden, cc.xy(12, 3));

							//======== panelTotalesOrdenMedio ========
							{
								panelTotalesOrdenMedio.setBorder(new TitledBorder("Totales"));
								panelTotalesOrdenMedio.setLayout(new FormLayout(
									new ColumnSpec[] {
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(70)),
										new ColumnSpec(ColumnSpec.LEFT, Sizes.DLUX5, FormSpec.NO_GROW),
										FormFactory.DEFAULT_COLSPEC,
										new ColumnSpec(Sizes.DLUX3),
										new ColumnSpec(Sizes.dluX(70)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC
									},
									new RowSpec[] {
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC
									}));

								//---- lblSumanOrdenMedio ----
								lblSumanOrdenMedio.setText("Suman:");
								panelTotalesOrdenMedio.add(lblSumanOrdenMedio, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtSumanOrdenMedio ----
								txtSumanOrdenMedio.setEditable(false);
								txtSumanOrdenMedio.setHorizontalAlignment(SwingConstants.RIGHT);
								panelTotalesOrdenMedio.add(txtSumanOrdenMedio, cc.xy(3, 1));

								//---- lblSubTotalOrdenMedio ----
								lblSubTotalOrdenMedio.setText("SubTotal:");
								panelTotalesOrdenMedio.add(lblSubTotalOrdenMedio, cc.xywh(5, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtSubtotalOrdenMedio ----
								txtSubtotalOrdenMedio.setHorizontalAlignment(SwingConstants.RIGHT);
								txtSubtotalOrdenMedio.setEditable(false);
								panelTotalesOrdenMedio.add(txtSubtotalOrdenMedio, cc.xy(7, 1));

								//---- lblDescuentoOrdenMedio ----
								lblDescuentoOrdenMedio.setText("Descuento [$]:");
								panelTotalesOrdenMedio.add(lblDescuentoOrdenMedio, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtDescuentoOrdenMedio ----
								txtDescuentoOrdenMedio.setHorizontalAlignment(SwingConstants.RIGHT);
								txtDescuentoOrdenMedio.setEditable(false);
								panelTotalesOrdenMedio.add(txtDescuentoOrdenMedio, cc.xy(3, 3));

								//---- lblIVAOrdenMedio ----
								lblIVAOrdenMedio.setText("IVA:");
								panelTotalesOrdenMedio.add(lblIVAOrdenMedio, cc.xywh(5, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtIVAOrdenMedio ----
								txtIVAOrdenMedio.setEditable(false);
								txtIVAOrdenMedio.setHorizontalAlignment(SwingConstants.RIGHT);
								panelTotalesOrdenMedio.add(txtIVAOrdenMedio, cc.xy(7, 3));

								//---- lblBonificacionCompraOrden ----
								lblBonificacionCompraOrden.setText("Bonificaci\u00f3n [$]:");
								panelTotalesOrdenMedio.add(lblBonificacionCompraOrden, cc.xy(1, 5));

								//---- txtBonificacionCompraOrden ----
								txtBonificacionCompraOrden.setEditable(false);
								txtBonificacionCompraOrden.setHorizontalAlignment(SwingConstants.RIGHT);
								panelTotalesOrdenMedio.add(txtBonificacionCompraOrden, cc.xy(3, 5));

								//---- lblTotalOrdenMedio ----
								lblTotalOrdenMedio.setText("Total:");
								panelTotalesOrdenMedio.add(lblTotalOrdenMedio, cc.xywh(5, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

								//---- txtTotalOrdenMedio ----
								txtTotalOrdenMedio.setEditable(false);
								txtTotalOrdenMedio.setHorizontalAlignment(SwingConstants.RIGHT);
								panelTotalesOrdenMedio.add(txtTotalOrdenMedio, cc.xy(7, 5));
							}
							panelOrdenesMedios.add(panelTotalesOrdenMedio, cc.xywh(2, 5, 3, 1));

							//======== panelObservacionOrdenMedio ========
							{
								panelObservacionOrdenMedio.setBorder(new TitledBorder("Observaci\u00f3n"));
								panelObservacionOrdenMedio.setLayout(new FormLayout(
									new ColumnSpec[] {
										new ColumnSpec(Sizes.dluX(180)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(63))
									},
									new RowSpec[] {
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC,
										FormFactory.LINE_GAP_ROWSPEC,
										FormFactory.DEFAULT_ROWSPEC
									}));

								//======== spTxtObservacionOrdenMedio ========
								{

									//---- txtObservacionOrdenMedio ----
									txtObservacionOrdenMedio.setRows(3);
									spTxtObservacionOrdenMedio.setViewportView(txtObservacionOrdenMedio);
								}
								panelObservacionOrdenMedio.add(spTxtObservacionOrdenMedio, cc.xywh(1, 1, 1, 5));

								//---- btnSetObservacionOrdenMedio ----
								btnSetObservacionOrdenMedio.setText("Act. Orden");
								panelObservacionOrdenMedio.add(btnSetObservacionOrdenMedio, cc.xy(3, 1));

								//---- btnSetObservacionOrdenMedioxProv ----
								btnSetObservacionOrdenMedioxProv.setText("Act. Medio");
								panelObservacionOrdenMedio.add(btnSetObservacionOrdenMedioxProv, cc.xy(3, 3));

								//---- btnSetObservacionOrdenMedioTotal ----
								btnSetObservacionOrdenMedioTotal.setText("Act. Todos");
								panelObservacionOrdenMedio.add(btnSetObservacionOrdenMedioTotal, cc.xy(3, 5));
							}
							panelOrdenesMedios.add(panelObservacionOrdenMedio, cc.xywh(6, 5, 5, 1));

							//======== spTblOrdenesMedioCmp ========
							{

								//---- tblOrdenesMediosCmp ----
								tblOrdenesMediosCmp.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"#", "Medio", "Producto Comercial", "Versi\u00f3n", "Desc.", "Tipo Pago", "Estado", "Codigo"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false, true, false, false, false, false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								spTblOrdenesMedioCmp.setViewportView(tblOrdenesMediosCmp);
							}
							panelOrdenesMedios.add(spTblOrdenesMedioCmp, cc.xywh(10, 7, 3, 5));

							//======== spTblOrdenesMedio ========
							{

								//---- tblOrdenesMedios ----
								tblOrdenesMedios.setModel(new DefaultTableModel(
									new Object[][] {
									},
									new String[] {
										"#", "Medio", "Producto Comercial", "Versi\u00f3n", "Desc. ", "Tipo Pago", "Codigo"
									}
								) {
									boolean[] columnEditable = new boolean[] {
										false, false, true, false, false, false, false
									};
									@Override
									public boolean isCellEditable(int rowIndex, int columnIndex) {
										return columnEditable[columnIndex];
									}
								});
								spTblOrdenesMedio.setViewportView(tblOrdenesMedios);
							}
							panelOrdenesMedios.add(spTblOrdenesMedio, cc.xywh(2, 9, 7, 5));
						}
						scrollPane3.setViewportView(panelOrdenesMedios);
					}
					tpMapasPauta.addTab("Ordenes de Medio", scrollPane3);

				}
				panelMapaPauta.add(tpMapasPauta, cc.xywh(3, 7, 9, 5));
			}
			jtpPlanMedio.addTab("Mapas de Pauta", panelMapaPauta);

		}
		add(jtpPlanMedio, cc.xywh(3, 3, 7, 8));

		//---- bgFormaPago ----
		ButtonGroup bgFormaPago = new ButtonGroup();
		bgFormaPago.add(rbTipoPagoNormal);
		bgFormaPago.add(rbTipoPagoCanje);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpPlanMedio;
	private JScrollPane spPlanMedio;
	private JPanel panelPlanMedio;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblRevision;
	private JTextField txtRevision;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnCorporacion;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JLabel lblModificaciones;
	private JTextField txtModificaciones;
	private JLabel lblOficina;
	private JTextField txtOficina;
	private JButton btnOficina;
	private JLabel lblAutorizacionSAP;
	private JTextField txtAutorizacionSAP;
	private JLabel lblOrdenTrabajo;
	private JComboBox cmbOrdenTrabajo;
	private JLabel lblFechaAprobacion;
	private DateComboBox cmbFechaAprobacion;
	private JLabel lblOrdenTrabajoDetalle;
	private JComboBox cmbOrdenTrabajoDetalle;
	private JCheckBox cbPrepago;
	private JLabel lblCampana;
	private JTextField txtCampana;
	private JLabel lblConcepto;
	private JTextField txtConcepto;
	private JCheckBox cbPlanMedioNuevaVersion;
	private JCheckBox cbPlanMedioNuevoMes;
	private JLabel lblPlanMedioRelacion;
	private JTextField textPlanMedioRelacion;
	private JButton btnPlanMedioRelacion;
	private JComponent separatorEstrategia;
	private JLabel lblTarget;
	private JComboBox cmbTarget;
	private JLabel lblGuayaquil;
	private JTextField txtGuayaquil;
	private JButton btnGuayaquil;
	private JLabel lblPeriodo;
	private DateComboBox cmbPeriodoFechaInicio;
	private JLabel lblPeriodoAl;
	private DateComboBox cmbPeriodoFechaFin;
	private JLabel lblQuito;
	private JTextField txtQuito;
	private JButton btnQuito;
	private JLabel lblCobertura;
	private JScrollPane spCobertura;
	private JTextArea txtCobertura;
	private JLabel lblTotal;
	private JTextField txtTotalCiudad;
	private JButton btnTotalCiudad;
	private JLabel lblOtrasConsideraciones;
	private JScrollPane spOtrasConsideraciones;
	private JTextArea txtOtrasConsideraciones;
	private JLabel lblConsideraciones;
	private JComponent separatorTotales;
	private JLabel lblSumaPlanMedio;
	private JTextField txtSumaPlanMedio;
	private JLabel lblBonificacionVentaPlanMedio;
	private JTextField txtBonificacionVentaPlanMedio;
	private JLabel lblDescuentoPlanMedio;
	private JTextField txtDescuentoPlanMedio;
	private JLabel lblSubtotalBonificacionVentaPlanMedio;
	private JTextField txtSubtotalBonificacionVentaPlanMedio;
	private JLabel lblComisionAgencia;
	private JTextField txtComisionAgenciaPlanMedio;
	private JLabel lblIvaPlanMedio;
	private JTextField txtIvaPlanMedio;
	private JLabel lblSubtotalPlanMedio;
	private JTextField txtSubtotalPlanMedio;
	private JLabel lblTotalPlanMedio;
	private JTextField txtTotalPlanMedio;
	private JScrollPane spPlanMedioPeriodo;
	private JPanel panelPlanMedioPeriodo;
	private JScrollPane spTblSubPeriodo;
	private JTable tblSubPeriodo;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JLabel lblSubPeriodo;
	private DateComboBox cmbSubPeriodoFechaInicio;
	private JLabel lblSubPeriodoAl;
	private DateComboBox cmbSubPeriodoFechaFin;
	private JPanel panelAUE;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spProveedorPrograma;
	private JPanel panelProveedorPrograma;
	private JLabel lblTipoPeriodo;
	private JComboBox cmbTipoPeriodo;
	private JLabel lblMedio;
	private JComboBox cmbMedio;
	private JTabbedPane tpArbolMedios;
	private JScrollPane spTelevision;
	private JPanel panelTelevision;
	private JLabel lblComercial;
	private JScrollPane spTblComercial;
	private JTable tblComercial;
	private JCheckBox cbPautaTelevision;
	private JCheckBox cbPautaRadio;
	private JCheckBox cbPautaCine;
	private JCheckBox cbPautaBasica;
	private JCheckBox cbAgrupaOrdenes;
	private JCheckBox cbOrdenPorProductoComercial;
	private JCheckBox cbOrdenPorVersion;
	private JButton btnImportarMapaPautaTv;
	private JButton btnImportarMapaPautaTvMultiple;
	private JButton btnTvData;
	private JScrollPane spArbolTelevision;
	private CheckBoxTree arbolTelevision;
	private JLabel lblCanalTv;
	private JTextField txtCanalTv;
	private JLabel lblProgramaTv;
	private JTextField txtProgramaTv;
	private JLabel lblHoraInicioPrograma;
	private JTextField txtHoraInicioPrograma;
	private JLabel lblHoraFinPrograma;
	private JTextField txtHoraFinPrograma;
	private JLabel lblDiasPrograma;
	private JLabel lblRatingTv;
	private JTextField txtRatingTv;
	private JTextField txtVCunaTarifa;
	private JLabel lblVCunaNegocio;
	private JTextField txtVCunaNegocio;
	private JTextField txtDiasPrograma;
	private JLabel lblVCunaTarifa;
	private JLabel lblComercialTv;
	private JTextField txtComercialTv;
	private JLabel lblDerechoPrograma;
	private JTextField txtDerechoPrograma;
	private JLabel lblVersionPrograma;
	private JTextField txtVersionPrograma;
	private JButton btnAgregarProgramaTv;
	private JButton btnCrearMapaPautaTv;
	private JButton btnEliminarProgramaTv;
	private JScrollPane spRadio;
	private JPanel panelRadio;
	private JScrollPane spArbolRadio;
	private JTree arbolRadio;
	private JScrollPane spPrensa;
	private JPanel panelPrensa;
	private JScrollPane spArbolPrensa;
	private CheckBoxTree arbolPrensa;
	private JLabel lblDiario;
	private JTextField txtDiario;
	private JLabel lblSeccion;
	private JTextField txtSeccion;
	private JLabel lblUbicacion;
	private JTextField txtUbicacion;
	private JLabel lblFormato;
	private JTextField txtFormato;
	private JLabel lblAnchoColumnas;
	private JTextField txtAnchoColumnas;
	private JLabel lblAltoModulos;
	private JTextField txtAltoModulos;
	private JTextField txtAnchoCm;
	private JLabel lblAnchoCm;
	private JLabel lblAltoCm;
	private JTextField txtAltoCm;
	private JLabel lblColor;
	private JTextField txtColor;
	private JLabel lblDia;
	private JLabel lblTarifa;
	private JTextField txtTarifa;
	private JButton btnCrearMapaPautaPrensa;
	private JTextField txtDia;
	private JPanel panelMapaPauta;
	private JLabel lblTipoPeriodoMapa;
	private JComboBox cmbTipoPeriodoMapa;
	private JTabbedPane tpMapasPauta;
	private JScrollPane scrollPane1;
	private JPanel panelMapaPautaTv;
	private JLabel lblProductoProveedor;
	private JButton btnCrearOrdenes;
	private JTextField txtProductoProveedor;
	private JScrollPane scrollPane2;
	private JPanel panelTGRPtv;
	private JScrollPane spTblTGRPtv;
	private JTable tblTGRPtv;
	private JLabel lblSuman;
	private JTextField txtSuman;
	private JLabel lblDescuento3;
	private JTextField txtSuman2;
	private JLabel lblDescuento;
	private JTextField txtDescuento;
	private JLabel lblDescuento4;
	private JTextField txtPorcentajeDescuentoVenta;
	private JLabel lblDescuento6;
	private JTextField txtDescuentoVenta;
	private JLabel lblSubTotal;
	private JTextField txtSubTotal;
	private JLabel lblDescuento5;
	private JTextField txtPorcentajeComisionAgencia;
	private JLabel lblDescuento7;
	private JTextField txtComisionAgencia;
	private JLabel lblBonificacionCompra;
	private JTextField txtBonificacionCompra;
	private JLabel lblSubtotalVenta;
	private JTextField txtSubtotalVenta;
	private JLabel lblSubtotalBonificacionCompra;
	private JTextField txtSubtotalBonificacionCompra;
	private JLabel lblPorcentajeBonifiacionVenta;
	private JTextField txtPorcentajeBonificacionVenta;
	private JLabel lblBonificacionVenta;
	private JTextField txtBonificacionVenta;
	private JLabel lblIVA;
	private JTextField txtIVA;
	private JLabel lblSubtotalBonificacionVenta;
	private JTextField txtSubtotalBonificacionVenta;
	private JLabel lblTotalPauta;
	private JTextField txtTotalPauta;
	private JLabel lblDescuento9;
	private JTextField txtIVA2;
	private JLabel lblDescuento10;
	private JTextField txtTotalPauta2;
	private JScrollPane scrollPane3;
	private JPanel panelOrdenesMedios;
	private JPanel panelDescuentoOrdenMedio;
	private JCheckBox cbIvaProveedor;
	private JCheckBox cbIvaCliente;
	private JButton btnSetPDsctoOrdenMedio;
	private JLabel lblPorcentajeDescuentoOrdenMedio;
	private JTextField txtPorcentajeDescuentoOrdenMedio;
	private JButton btnSetPDsctoOrdenMedioxProv;
	private JLabel lblPorcentajeBonificacionCompra;
	private JTextField txtPorcentajeBonificacionCompra;
	private JButton btnSetPDsctoOrdenMedioTotal;
	private JPanel panelTipoPagoOrdenMedio;
	private JRadioButton rbTipoPagoNormal;
	private JButton btnSetTipoPago;
	private JRadioButton rbTipoPagoCanje;
	private JTextField txtPorcentajeCanje;
	private JButton btnSetTipoPagoxProv;
	private JCheckBox cbTipoPagoComision;
	private JButton btnSetTipoPagoTotal;
	private JCheckBox cbComisionAdicional;
	private JTextField txtPorcentajeComisionAdicional;
	private JPanel panelActualizarCodigoOrden;
	private JLabel lblCodigoOrden;
	private JTextField textCodigoOrden;
	private JButton btnLimpiarCodigoOrden;
	private JButton btnCambiarCodigo;
	private JPanel panelTotalesOrdenMedio;
	private JLabel lblSumanOrdenMedio;
	private JTextField txtSumanOrdenMedio;
	private JLabel lblSubTotalOrdenMedio;
	private JTextField txtSubtotalOrdenMedio;
	private JLabel lblDescuentoOrdenMedio;
	private JTextField txtDescuentoOrdenMedio;
	private JLabel lblIVAOrdenMedio;
	private JTextField txtIVAOrdenMedio;
	private JLabel lblBonificacionCompraOrden;
	private JTextField txtBonificacionCompraOrden;
	private JLabel lblTotalOrdenMedio;
	private JTextField txtTotalOrdenMedio;
	private JPanel panelObservacionOrdenMedio;
	private JScrollPane spTxtObservacionOrdenMedio;
	private JTextArea txtObservacionOrdenMedio;
	private JButton btnSetObservacionOrdenMedio;
	private JButton btnSetObservacionOrdenMedioxProv;
	private JButton btnSetObservacionOrdenMedioTotal;
	private JScrollPane spTblOrdenesMedioCmp;
	private JTable tblOrdenesMediosCmp;
	private JScrollPane spTblOrdenesMedio;
	private JTable tblOrdenesMedios;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtRevision() {
		return txtRevision;
	}

	public DateComboBox getCmbFechaAprobacion() {
		return cmbFechaAprobacion;
	}

	public JTextField getTxtAutorizacionSAP() {
		return txtAutorizacionSAP;
	}

	public JCheckBox getCbPautaCine() {
		return cbPautaCine;
	}

	public JLabel getLblBonificacionVentaPlanMedio() {
		return lblBonificacionVentaPlanMedio;
	}

	public JLabel getLblSubtotalBonificacionVentaPlanMedio() {
		return lblSubtotalBonificacionVentaPlanMedio;
	}

	public JLabel getLblSubtotalBonificacionVenta() {
		return lblSubtotalBonificacionVenta;
	}

	public JLabel getLblPorcentajeBonifiacionVenta() {
		return lblPorcentajeBonifiacionVenta;
	}

	public JLabel getLblBonificacionVenta() {
		return lblBonificacionVenta;
	}

	public JTextField getTxtBonificacionCompraOrden() {
		return txtBonificacionCompraOrden;
	}

	public void setTxtBonificacionCompraOrden(JTextField txtBonificacionCompraOrden) {
		this.txtBonificacionCompraOrden = txtBonificacionCompraOrden;
	}
	
	public JTextField getTxtBonificacionCompra() {
		return txtBonificacionCompra;
	}

	public JTextField getTxtSubtotalBonificacionCompra() {
		return txtSubtotalBonificacionCompra;
	}

	public JTextField getTxtSubtotalBonificacionVenta() {
		return txtSubtotalBonificacionVenta;
	}

	public JTextField getTxtPorcentajeBonificacionCompra() {
		return txtPorcentajeBonificacionCompra;
	}

	public void setTxtBonificacionCompra(JTextField txtBonificacionCompra) {
		this.txtBonificacionCompra = txtBonificacionCompra;
	}

	public void setTxtSubtotalBonificacionCompra(
			JTextField txtSubtotalBonificacionCompra) {
		this.txtSubtotalBonificacionCompra = txtSubtotalBonificacionCompra;
	}

	public void setTxtSubtotalBonificacionVenta(
			JTextField txtSubtotalBonificacionVenta) {
		this.txtSubtotalBonificacionVenta = txtSubtotalBonificacionVenta;
	}

	public void setTxtPorcentajeBonificacionCompra(
			JTextField txtPorcentajeBonificacionCompra) {
		this.txtPorcentajeBonificacionCompra = txtPorcentajeBonificacionCompra;
	}

	public JTextField getTxtBonificacionVentaPlanMedio() {
		return txtBonificacionVentaPlanMedio;
	}

	public JTextField getTxtSubtotalBonificacionVentaPlanMedio() {
		return txtSubtotalBonificacionVentaPlanMedio;
	}

	public void setTxtBonificacionVentaPlanMedio(
			JTextField txtBonificacionVentaPlanMedio) {
		this.txtBonificacionVentaPlanMedio = txtBonificacionVentaPlanMedio;
	}

	public void setTxtSubtotalBonificacionVentaPlanMedio(
			JTextField txtSubtotalBonificacionVentaPlanMedio) {
		this.txtSubtotalBonificacionVentaPlanMedio = txtSubtotalBonificacionVentaPlanMedio;
	}

	public JTextField getTxtSubtotalVenta() {
		return txtSubtotalVenta;
	}

	public JTextField getTxtPorcentajeBonificacionVenta() {
		return txtPorcentajeBonificacionVenta;
	}

	public JTextField getTxtBonificacionVenta() {
		return txtBonificacionVenta;
	}

	public void setTxtSubtotalVenta(JTextField txtSubtotalVenta) {
		this.txtSubtotalVenta = txtSubtotalVenta;
	}

	public void setTxtPorcentajeBonificacionVenta(
			JTextField txtPorcentajeBonificacionVenta) {
		this.txtPorcentajeBonificacionVenta = txtPorcentajeBonificacionVenta;
	}

	public void setTxtBonificacionVenta(JTextField txtBonificacionVenta) {
		this.txtBonificacionVenta = txtBonificacionVenta;
	}

	public JCheckBox getCbTipoPagoComision() {
		return cbTipoPagoComision;
	}

	public JCheckBox getCbPautaTelevision() {
		return cbPautaTelevision;
	}

	public JCheckBox getCbPautaRadio() {
		return cbPautaRadio;
	}

	public void setCbPautaTelevision(JCheckBox cbPautaTelevision) {
		this.cbPautaTelevision = cbPautaTelevision;
	}

	public void setCbPautaRadio(JCheckBox cbPautaRadio) {
		this.cbPautaRadio = cbPautaRadio;
	}

	public JTextField getTxtSumaPlanMedio() {
		return txtSumaPlanMedio;
	}

	public void setTxtSumaPlanMedio(JTextField txtSumaPlanMedio) {
		this.txtSumaPlanMedio = txtSumaPlanMedio;
	}

	public void setCbTipoPagoComision(JCheckBox cbTipoPagoComision) {
		this.cbTipoPagoComision = cbTipoPagoComision;
	}
	
	public JLabel getLblCanalTv() {
		return lblCanalTv;
	}

	public JLabel getLblProgramaTv() {
		return lblProgramaTv;
	}

	public JLabel getLblHoraInicioPrograma() {
		return lblHoraInicioPrograma;
	}

	public JLabel getLblHoraFinPrograma() {
		return lblHoraFinPrograma;
	}

	public JLabel getLblDiasPrograma() {
		return lblDiasPrograma;
	}

	public JLabel getLblRatingTv() {
		return lblRatingTv;
	}

	public JLabel getLblVCunaTarifa() {
		return lblVCunaTarifa;
	}

	public JLabel getLblVCunaNegocio() {
		return lblVCunaNegocio;
	}

	public JLabel getLblComercialTv() {
		return lblComercialTv;
	}

	public JLabel getLblDerechoPrograma() {
		return lblDerechoPrograma;
	}

	public JLabel getLblVersionPrograma() {
		return lblVersionPrograma;
	}

	public JTextField getTxtCampana() {
		return txtCampana;
	}

	public void setTxtCampana(JTextField txtCampana) {
		this.txtCampana = txtCampana;
	}

	public JButton getBtnImportarMapaPautaTvMultiple() {
		return btnImportarMapaPautaTvMultiple;
	}

	public void setBtnImportarMapaPautaTvMultiple(
			JButton btnImportarMapaPautaTvMultiple) {
		this.btnImportarMapaPautaTvMultiple = btnImportarMapaPautaTvMultiple;
	}

	public JTextField getTxtIvaPlanMedio() {
		return txtIvaPlanMedio;
	}

	public void setTxtIvaPlanMedio(JTextField txtIvaPlanMedio) {
		this.txtIvaPlanMedio = txtIvaPlanMedio;
	}

	public JTextField getTxtSubtotalPlanMedio() {
		return txtSubtotalPlanMedio;
	}

	public void setTxtSubtotalPlanMedio(JTextField txtSubtotalPlanMedio) {
		this.txtSubtotalPlanMedio = txtSubtotalPlanMedio;
	}

	public JTextField getTxtDescuentoPlanMedio() {
		return txtDescuentoPlanMedio;
	}

	public void setTxtDescuentoPlanMedio(JTextField txtDescuentoPlanMedio) {
		this.txtDescuentoPlanMedio = txtDescuentoPlanMedio;
	}

	public JTextField getTxtTotalPlanMedio() {
		return txtTotalPlanMedio;
	}

	public void setTxtTotalPlanMedio(JTextField txtTotalPlanMedio) {
		this.txtTotalPlanMedio = txtTotalPlanMedio;
	}

	public JButton getBtnCrearMapaPautaTv() {
		return btnCrearMapaPautaTv;
	}

	public void setBtnCrearMapaPautaTv(JButton btnCrearMapaPautaTv) {
		this.btnCrearMapaPautaTv = btnCrearMapaPautaTv;
	}

	public JButton getBtnImportarMapaPautaTv() {
		return btnImportarMapaPautaTv;
	}

	public void setBtnImportarMapaPautaTv(JButton btnImportarMapaPautaTv) {
		this.btnImportarMapaPautaTv = btnImportarMapaPautaTv;
	}

	public JButton getBtnCrearOrdenes() {
		return btnCrearOrdenes;
	}

	public void setBtnCrearOrdenes(JButton btnCrearOrdenes) {
		this.btnCrearOrdenes = btnCrearOrdenes;
	}

	public JTree getArbolRadio() {
		return arbolRadio;
	}

	public void setArbolRadio(JTree arbolRadio) {
		this.arbolRadio = arbolRadio;
	}

	public CheckBoxTree getArbolTelevision() {
		return arbolTelevision;
	}

	public void setArbolTelevision(CheckBoxTree arbolTelevision) {
		this.arbolTelevision = arbolTelevision;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public void setBtnActualizarDetalle(JButton btnActualizarDetalle) {
		this.btnActualizarDetalle = btnActualizarDetalle;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnAgregarProgramaTv() {
		return btnAgregarProgramaTv;
	}

	public void setBtnAgregarProgramaTv(JButton btnAgregarProgramaTv) {
		this.btnAgregarProgramaTv = btnAgregarProgramaTv;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public void setBtnCliente(JButton btnCliente) {
		this.btnCliente = btnCliente;
	}

	public JButton getBtnCorporacion() {
		return btnCorporacion;
	}

	public void setBtnCorporacion(JButton btnCorporacion) {
		this.btnCorporacion = btnCorporacion;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JButton getBtnGuayaquil() {
		return btnGuayaquil;
	}

	public void setBtnGuayaquil(JButton btnGuayaquil) {
		this.btnGuayaquil = btnGuayaquil;
	}

	public JButton getBtnOficina() {
		return btnOficina;
	}

	public void setBtnOficina(JButton btnOficina) {
		this.btnOficina = btnOficina;
	}

	public JButton getBtnQuito() {
		return btnQuito;
	}

	public void setBtnQuito(JButton btnQuito) {
		this.btnQuito = btnQuito;
	}

	public JButton getBtnTvData() {
		return btnTvData;
	}

	public void setBtnTvData(JButton btnTvData) {
		this.btnTvData = btnTvData;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbMedio() {
		return cmbMedio;
	}

	public void setCmbMedio(JComboBox cmbMedio) {
		this.cmbMedio = cmbMedio;
	}

	public JComboBox getCmbOrdenTrabajo() {
		return cmbOrdenTrabajo;
	}

	public void setCmbOrdenTrabajo(JComboBox cmbOrdenTrabajo) {
		this.cmbOrdenTrabajo = cmbOrdenTrabajo;
	}

	public DateComboBox getCmbPeriodoFechaFin() {
		return cmbPeriodoFechaFin;
	}

	public void setCmbPeriodoFechaFin(DateComboBox cmbPeriodoFechaFin) {
		this.cmbPeriodoFechaFin = cmbPeriodoFechaFin;
	}

	public DateComboBox getCmbPeriodoFechaInicio() {
		return cmbPeriodoFechaInicio;
	}

	public void setCmbPeriodoFechaInicio(DateComboBox cmbPeriodoFechaInicio) {
		this.cmbPeriodoFechaInicio = cmbPeriodoFechaInicio;
	}

	public DateComboBox getCmbSubPeriodoFechaFin() {
		return cmbSubPeriodoFechaFin;
	}

	public void setCmbSubPeriodoFechaFin(DateComboBox cmbSubPeriodoFechaFin) {
		this.cmbSubPeriodoFechaFin = cmbSubPeriodoFechaFin;
	}

	public DateComboBox getCmbSubPeriodoFechaInicio() {
		return cmbSubPeriodoFechaInicio;
	}

	public void setCmbSubPeriodoFechaInicio(DateComboBox cmbSubPeriodoFechaInicio) {
		this.cmbSubPeriodoFechaInicio = cmbSubPeriodoFechaInicio;
	}

	public JComboBox getCmbTarget() {
		return cmbTarget;
	}

	public void setCmbTarget(JComboBox cmbTarget) {
		this.cmbTarget = cmbTarget;
	}

	public JComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public JComboBox getCmbTipoPeriodo() {
		return cmbTipoPeriodo;
	}

	public void setCmbTipoPeriodo(JComboBox cmbTipoPeriodo) {
		this.cmbTipoPeriodo = cmbTipoPeriodo;
	}

	public JideTabbedPane getJtpPlanMedio() {
		return jtpPlanMedio;
	}

	public void setJtpPlanMedio(JideTabbedPane jtpPlanMedio) {
		this.jtpPlanMedio = jtpPlanMedio;
	}

	public JPanel getPanelAUE() {
		return panelAUE;
	}

	public void setPanelAUE(JPanel panelAUE) {
		this.panelAUE = panelAUE;
	}

	public JPanel getPanelMapaPauta() {
		return panelMapaPauta;
	}

	public void setPanelMapaPauta(JPanel panelMapaPauta) {
		this.panelMapaPauta = panelMapaPauta;
	}

	public JPanel getPanelPlanMedio() {
		return panelPlanMedio;
	}

	public void setPanelPlanMedio(JPanel panelPlanMedio) {
		this.panelPlanMedio = panelPlanMedio;
	}

	public JPanel getPanelPlanMedioPeriodo() {
		return panelPlanMedioPeriodo;
	}

	public void setPanelPlanMedioPeriodo(JPanel panelPlanMedioPeriodo) {
		this.panelPlanMedioPeriodo = panelPlanMedioPeriodo;
	}

	public JPanel getPanelPrensa() {
		return panelPrensa;
	}

	public void setPanelPrensa(JPanel panelPrensa) {
		this.panelPrensa = panelPrensa;
	}

	public JPanel getPanelProveedorPrograma() {
		return panelProveedorPrograma;
	}

	public void setPanelProveedorPrograma(JPanel panelProveedorPrograma) {
		this.panelProveedorPrograma = panelProveedorPrograma;
	}

	public JPanel getPanelRadio() {
		return panelRadio;
	}

	public void setPanelRadio(JPanel panelRadio) {
		this.panelRadio = panelRadio;
	}

	public JPanel getPanelTelevision() {
		return panelTelevision;
	}

	public void setPanelTelevision(JPanel panelTelevision) {
		this.panelTelevision = panelTelevision;
	}

	public JComponent getSeparatorEstrategia() {
		return separatorEstrategia;
	}

	public void setSeparatorEstrategia(JComponent separatorEstrategia) {
		this.separatorEstrategia = separatorEstrategia;
	}

	public JComponent getSeparatorTotales() {
		return separatorTotales;
	}

	public void setSeparatorTotales(JComponent separatorTotales) {
		this.separatorTotales = separatorTotales;
	}

	public JScrollPane getSpArbolPrensa() {
		return spArbolPrensa;
	}

	public void setSpArbolPrensa(JScrollPane spArbolPrensa) {
		this.spArbolPrensa = spArbolPrensa;
	}

	public JScrollPane getSpArbolRadio() {
		return spArbolRadio;
	}

	public void setSpArbolRadio(JScrollPane spArbolRadio) {
		this.spArbolRadio = spArbolRadio;
	}

	public JScrollPane getSpArbolTelevision() {
		return spArbolTelevision;
	}

	public void setSpArbolTelevision(JScrollPane spArbolTelevision) {
		this.spArbolTelevision = spArbolTelevision;
	}

	public JScrollPane getSpCobertura() {
		return spCobertura;
	}

	public void setSpCobertura(JScrollPane spCobertura) {
		this.spCobertura = spCobertura;
	}

	public JScrollPane getSpOtrasConsideraciones() {
		return spOtrasConsideraciones;
	}

	public void setSpOtrasConsideraciones(JScrollPane spOtrasConsideraciones) {
		this.spOtrasConsideraciones = spOtrasConsideraciones;
	}

	public JScrollPane getSpPlanMedio() {
		return spPlanMedio;
	}

	public void setSpPlanMedio(JScrollPane spPlanMedio) {
		this.spPlanMedio = spPlanMedio;
	}

	public JScrollPane getSpPlanMedioPeriodo() {
		return spPlanMedioPeriodo;
	}

	public void setSpPlanMedioPeriodo(JScrollPane spPlanMedioPeriodo) {
		this.spPlanMedioPeriodo = spPlanMedioPeriodo;
	}

	public JScrollPane getSpPrensa() {
		return spPrensa;
	}

	public void setSpPrensa(JScrollPane spPrensa) {
		this.spPrensa = spPrensa;
	}

	public JScrollPane getSpProveedorPrograma() {
		return spProveedorPrograma;
	}

	public void setSpProveedorPrograma(JScrollPane spProveedorPrograma) {
		this.spProveedorPrograma = spProveedorPrograma;
	}

	public JScrollPane getSpRadio() {
		return spRadio;
	}

	public void setSpRadio(JScrollPane spRadio) {
		this.spRadio = spRadio;
	}

	public JScrollPane getSpTblComercial() {
		return spTblComercial;
	}

	public void setSpTblComercial(JScrollPane spTblComercial) {
		this.spTblComercial = spTblComercial;
	}

	public JScrollPane getSpTblSubPeriodo() {
		return spTblSubPeriodo;
	}

	public void setSpTblSubPeriodo(JScrollPane spTblSubPeriodo) {
		this.spTblSubPeriodo = spTblSubPeriodo;
	}

	public JScrollPane getSpTelevision() {
		return spTelevision;
	}

	public void setSpTelevision(JScrollPane spTelevision) {
		this.spTelevision = spTelevision;
	}

	public JTable getTblComercial() {
		return tblComercial;
	}

	public void setTblComercial(JTable tblComercial) {
		this.tblComercial = tblComercial;
	}

	public JTable getTblSubPeriodo() {
		return tblSubPeriodo;
	}

	public void setTblSubPeriodo(JTable tblSubPeriodo) {
		this.tblSubPeriodo = tblSubPeriodo;
	}

	public JTextField getTxtModificaciones() {
		return txtModificaciones;
	}

	public void setTxtModificaciones(JTextField textField3) {
		this.txtModificaciones = textField3;
	}

	public JTabbedPane getTpArbolMedios() {
		return tpArbolMedios;
	}

	public void setTpArbolMedios(JTabbedPane tpArbolMedios) {
		this.tpArbolMedios = tpArbolMedios;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextArea getTxtCobertura() {
		return txtCobertura;
	}

	public void setTxtCobertura(JTextArea txtCobertura) {
		this.txtCobertura = txtCobertura;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtConcepto() {
		return txtConcepto;
	}

	public void setTxtConcepto(JTextField txtConcepto) {
		this.txtConcepto = txtConcepto;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public void setTxtFechaCreacion(JTextField txtFechaCreacion) {
		this.txtFechaCreacion = txtFechaCreacion;
	}

	public JTextField getTxtGuayaquil() {
		return txtGuayaquil;
	}

	public void setTxtGuayaquil(JTextField txtGuayaquil) {
		this.txtGuayaquil = txtGuayaquil;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JTextField txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JTextArea getTxtOtrasConsideraciones() {
		return txtOtrasConsideraciones;
	}

	public void setTxtOtrasConsideraciones(JTextArea txtOtrasConsideraciones) {
		this.txtOtrasConsideraciones = txtOtrasConsideraciones;
	}

	public JTextField getTxtQuito() {
		return txtQuito;
	}

	public void setTxtQuito(JTextField txtQuito) {
		this.txtQuito = txtQuito;
	}

	public JTextField getTxtRatingTv() {
		return txtRatingTv;
	}

	public void setTxtRatingTv(JTextField txtRatingTv) {
		this.txtRatingTv = txtRatingTv;
	}

	public JButton getBtnTotalCiudad() {
		return btnTotalCiudad;
	}

	public void setBtnTotalCiudad(JButton btnTotalCiudad) {
		this.btnTotalCiudad = btnTotalCiudad;
	}

	public JTextField getTxtTotalCiudad() {
		return txtTotalCiudad;
	}

	public void setTxtTotalCiudad(JTextField txtTotalCiudad) {
		this.txtTotalCiudad = txtTotalCiudad;
	}

	public JTextField getTxtAltoCm() {
		return txtAltoCm;
	}

	public void setTxtAltoCm(JTextField txtAltoCm) {
		this.txtAltoCm = txtAltoCm;
	}

	public JTextField getTxtAltoModulos() {
		return txtAltoModulos;
	}

	public void setTxtAltoModulos(JTextField txtAltoModulos) {
		this.txtAltoModulos = txtAltoModulos;
	}

	public JTextField getTxtAnchoCm() {
		return txtAnchoCm;
	}

	public void setTxtAnchoCm(JTextField txtAnchoCm) {
		this.txtAnchoCm = txtAnchoCm;
	}

	public JTextField getTxtAnchoColumnas() {
		return txtAnchoColumnas;
	}

	public void setTxtAnchoColumnas(JTextField txtAnchoColumnas) {
		this.txtAnchoColumnas = txtAnchoColumnas;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(JTextField txtColor) {
		this.txtColor = txtColor;
	}

	public JTextField getTxtDia() {
		return txtDia;
	}

	public void setTxtDia(JTextField txtDia) {
		this.txtDia = txtDia;
	}

	public JTextField getTxtDiario() {
		return txtDiario;
	}

	public void setTxtDiario(JTextField txtDiario) {
		this.txtDiario = txtDiario;
	}

	public JTextField getTxtFormato() {
		return txtFormato;
	}

	public void setTxtFormato(JTextField txtFormato) {
		this.txtFormato = txtFormato;
	}

	public JTextField getTxtSeccion() {
		return txtSeccion;
	}

	public void setTxtSeccion(JTextField txtSeccion) {
		this.txtSeccion = txtSeccion;
	}

	public JTextField getTxtTarifa() {
		return txtTarifa;
	}

	public void setTxtTarifa(JTextField txtTarifa) {
		this.txtTarifa = txtTarifa;
	}

	public JTextField getTxtUbicacion() {
		return txtUbicacion;
	}

	public void setTxtUbicacion(JTextField txtUbicacion) {
		this.txtUbicacion = txtUbicacion;
	}

	public JTextField getTxtVCunaNegocio() {
		return txtVCunaNegocio;
	}

	public void setTxtVCunaNegocio(JTextField txtVCunaNegocio) {
		this.txtVCunaNegocio = txtVCunaNegocio;
	}

	public JTextField getTxtVCunaTarifa() {
		return txtVCunaTarifa;
	}

	public void setTxtVCunaTarifa(JTextField txtVCunaTarifa) {
		this.txtVCunaTarifa = txtVCunaTarifa;
	}

	public JButton getBtnCrearMapaPautaPrensa() {
		return btnCrearMapaPautaPrensa;
	}

	public void setBtnCrearMapaPautaPrensa(JButton btnCrearMapaPautaPrensa) {
		this.btnCrearMapaPautaPrensa = btnCrearMapaPautaPrensa;
	}

	public CheckBoxTree getArbolPrensa() {
		return arbolPrensa;
	}

	public void setArbolPrensa(CheckBoxTree arbolPrensa) {
		this.arbolPrensa = arbolPrensa;
	}

	public JPanel getPanelMapaPautaTv() {
		return panelMapaPautaTv;
	}

	public void setPanelMapaPautaTv(JPanel panelMapaPautaTv) {
		this.panelMapaPautaTv = panelMapaPautaTv;
	}

	public JTabbedPane getTpMapasPauta() {
		return tpMapasPauta;
	}

	public void setTpMapasPauta(JTabbedPane tpMapasPauta) {
		this.tpMapasPauta = tpMapasPauta;
	}

	public JComboBox getCmbTipoPeriodoMapa() {
		return cmbTipoPeriodoMapa;
	}

	public void setCmbTipoPeriodoMapa(JComboBox cmbTipoPeriodoMapa) {
		this.cmbTipoPeriodoMapa = cmbTipoPeriodoMapa;
	}

	public JTextField getTxtComercialTv() {
		return txtComercialTv;
	}

	public void setTxtComercialTv(JTextField txtComercialTv) {
		this.txtComercialTv = txtComercialTv;
	}

	public JTextField getTxtDerechoPrograma() {
		return txtDerechoPrograma;
	}

	public void setTxtDerechoPrograma(JTextField txtDerechoPrograma) {
		this.txtDerechoPrograma = txtDerechoPrograma;
	}

	public JTextField getTxtDiasPrograma() {
		return txtDiasPrograma;
	}

	public void setTxtDiasPrograma(JTextField txtDiasPrograma) {
		this.txtDiasPrograma = txtDiasPrograma;
	}

	public JTextField getTxtHoraFinPrograma() {
		return txtHoraFinPrograma;
	}

	public void setTxtHoraFinPrograma(JTextField txtFinPrograma) {
		this.txtHoraFinPrograma = txtFinPrograma;
	}

	public JTextField getTxtHoraInicioPrograma() {
		return txtHoraInicioPrograma;
	}

	public void setTxtHoraInicioPrograma(JTextField txtInicioPrograma) {
		this.txtHoraInicioPrograma = txtInicioPrograma;
	}

	public JTextField getTxtVersionPrograma() {
		return txtVersionPrograma;
	}

	public void setTxtVersionPrograma(JTextField txtVersionPrograma) {
		this.txtVersionPrograma = txtVersionPrograma;
	}

	public JTextField getTxtProgramaTv() {
		return txtProgramaTv;
	}

	public void setTxtProgramaTv(JTextField txtProgramaTv) {
		this.txtProgramaTv = txtProgramaTv;
	}

	public JTextField getTxtCanalTv() {
		return txtCanalTv;
	}

	public void setTxtCanalTv(JTextField txtCanalTv) {
		this.txtCanalTv = txtCanalTv;
	}

	public JLabel getLblMedio() {
		return lblMedio;
	}

	public void setLblMedio(JLabel lblMedio) {
		this.lblMedio = lblMedio;
	}

	public JLabel getLblTipoPeriodoMapa() {
		return lblTipoPeriodoMapa;
	}

	public void setLblTipoPeriodoMapa(JLabel lblTipoPeriodoMapa) {
		this.lblTipoPeriodoMapa = lblTipoPeriodoMapa;
	}

	public JButton getBtnEliminarProgramaTv() {
		return btnEliminarProgramaTv;
	}

	public void setBtnEliminarProgramaTv(JButton btnEliminarProgramaTv) {
		this.btnEliminarProgramaTv = btnEliminarProgramaTv;
	}

	public JPanel getPanelTGRPtv() {
		return panelTGRPtv;
	}

	public void setPanelTGRPtv(JPanel panelTGRPtv) {
		this.panelTGRPtv = panelTGRPtv;
	}

	public JTable getTblTGRPtv() {
		return tblTGRPtv;
	}

	public void setTblTGRPtv(JTable tblTGRPtv) {
		this.tblTGRPtv = tblTGRPtv;
	}

	public JComboBox getCmbOrdenTrabajoDetalle() {
		return cmbOrdenTrabajoDetalle;
	}

	public void setCmbOrdenTrabajoDetalle(JComboBox cmbOrdenTrabajoDetalle) {
		this.cmbOrdenTrabajoDetalle = cmbOrdenTrabajoDetalle;
	}

	public JTextField getTxtProductoProveedor() {
		return txtProductoProveedor;
	}

	public void setTxtProductoProveedor(JTextField txtProductoProveedor) {
		this.txtProductoProveedor = txtProductoProveedor;
	}

	public JTextField getTxtSuman() {
		return txtSuman;
	}

	public void setTxtSuman(JTextField txtSuman) {
		this.txtSuman = txtSuman;
	}

	public JTextField getTxtDescuento() {
		return txtDescuento;
	}

	public void setTxtDescuento(JTextField txtDescuento) {
		this.txtDescuento = txtDescuento;
	}

	public JTextField getTxtSubTotal() {
		return txtSubTotal;
	}

	public void setTxtSubTotal(JTextField txtSubTotal) {
		this.txtSubTotal = txtSubTotal;
	}

	public JTextField getTxtIVA() {
		return txtIVA;
	}

	public void setTxtIVA(JTextField txtIVA) {
		this.txtIVA = txtIVA;
	}

	public JTextField getTxtTotalPauta() {
		return txtTotalPauta;
	}

	public void setTxtTotalPauta(JTextField txtTotalPauta) {
		this.txtTotalPauta = txtTotalPauta;
	}

	public JTextField getTxtSuman2() {
		return txtSuman2;
	}

	public void setTxtSuman2(JTextField txtSuman2) {
		this.txtSuman2 = txtSuman2;
	}

	public JTextField getTxtPorcentajeDescuentoVenta() {
		return txtPorcentajeDescuentoVenta;
	}

	public void setTxtPorcentajeDescuentoVenta(
			JTextField txtPorcentajeDescuentoVenta) {
		this.txtPorcentajeDescuentoVenta = txtPorcentajeDescuentoVenta;
	}

	public JTextField getTxtDescuentoVenta() {
		return txtDescuentoVenta;
	}

	public void setTxtDescuentoVenta(JTextField txtDescuentoVenta) {
		this.txtDescuentoVenta = txtDescuentoVenta;
	}

	public JTextField getTxtPorcentajeComisionAgencia() {
		return txtPorcentajeComisionAgencia;
	}

	public void setTxtPorcentajeComisionAgencia(
			JTextField txtPorcentajeComisionAgencia) {
		this.txtPorcentajeComisionAgencia = txtPorcentajeComisionAgencia;
	}

	public JTextField getTxtComisionAgencia() {
		return txtComisionAgencia;
	}

	public void setTxtComisionAgencia(JTextField txtComisionAgencia) {
		this.txtComisionAgencia = txtComisionAgencia;
	}

	public JTextField getTxtIVA2() {
		return txtIVA2;
	}

	public void setTxtIVA2(JTextField txtIVA2) {
		this.txtIVA2 = txtIVA2;
	}

	public JTextField getTxtTotalPauta2() {
		return txtTotalPauta2;
	}

	public void setTxtTotalPauta2(JTextField txtTotalPauta2) {
		this.txtTotalPauta2 = txtTotalPauta2;
	}

	public JTextField getTxtComisionAgenciaPlanMedio() {
		return txtComisionAgenciaPlanMedio;
	}

	public void setTxtComisionAgenciaPlanMedio(
			JTextField txtComisionAgenciaPlanMedio) {
		this.txtComisionAgenciaPlanMedio = txtComisionAgenciaPlanMedio;
	}

	public JPanel getPanelOrdenesMedios() {
		return panelOrdenesMedios;
	}

	public void setPanelOrdenesMedios(JPanel panelOrdenesMedios) {
		this.panelOrdenesMedios = panelOrdenesMedios;
	}

	public JButton getBtnSetPDsctoOrdenMedio() {
		return btnSetPDsctoOrdenMedio;
	}

	public void setBtnSetPDsctoOrdenMedio(JButton btnSetPDsctoOrdenMedio) {
		this.btnSetPDsctoOrdenMedio = btnSetPDsctoOrdenMedio;
	}

	public JLabel getLblPorcentajeDescuentoOrdenMedio() {
		return lblPorcentajeDescuentoOrdenMedio;
	}

	public void setLblPorcentajeDescuentoOrdenMedio(
			JLabel lblPorcentajeDescuentoOrdenMedio) {
		this.lblPorcentajeDescuentoOrdenMedio = lblPorcentajeDescuentoOrdenMedio;
	}

	public JTextField getTxtPorcentajeDescuentoOrdenMedio() {
		return txtPorcentajeDescuentoOrdenMedio;
	}

	public void setTxtPorcentajeDescuentoOrdenMedio(
			JTextField txtPorcentajeDescuentoOrdenMedio) {
		this.txtPorcentajeDescuentoOrdenMedio = txtPorcentajeDescuentoOrdenMedio;
	}

	public JPanel getPanelTipoPagoOrdenMedio() {
		return panelTipoPagoOrdenMedio;
	}

	public void setPanelTipoPagoOrdenMedio(JPanel panelTipoPagoOrdenMedio) {
		this.panelTipoPagoOrdenMedio = panelTipoPagoOrdenMedio;
	}

	public JRadioButton getRbTipoPagoNormal() {
		return rbTipoPagoNormal;
	}

	public void setRbTipoPagoNormal(JRadioButton rbTipoPagoNormal) {
		this.rbTipoPagoNormal = rbTipoPagoNormal;
	}

	public JButton getBtnSetTipoPago() {
		return btnSetTipoPago;
	}

	public void setBtnSetTipoPago(JButton btnSetTipoPago) {
		this.btnSetTipoPago = btnSetTipoPago;
	}

	public JRadioButton getRbTipoPagoCanje() {
		return rbTipoPagoCanje;
	}

	public void setRbTipoPagoCanje(JRadioButton rbTipoPagoCanje) {
		this.rbTipoPagoCanje = rbTipoPagoCanje;
	}

	/*public JLabel getLblPorcentajeCanje() {
		return lblPorcentajeCanje;
	}

	public void setLblPorcentajeCanje(JLabel lblPorcentajeCanje) {
		this.lblPorcentajeCanje = lblPorcentajeCanje;
	}*/

	public JTextField getTxtPorcentajeCanje() {
		return txtPorcentajeCanje;
	}

	public void setTxtPorcentajeCanje(JTextField txtPorcentajeCanje) {
		this.txtPorcentajeCanje = txtPorcentajeCanje;
	}

	public JButton getBtnSetTipoPagoxProv() {
		return btnSetTipoPagoxProv;
	}

	public void setBtnSetTipoPagoxProv(JButton btnSetTipoPagoxProv) {
		this.btnSetTipoPagoxProv = btnSetTipoPagoxProv;
	}

	public JButton getBtnSetTipoPagoTotal() {
		return btnSetTipoPagoTotal;
	}

	public void setBtnSetTipoPagoTotal(JButton btnSetTipoPagoTotal) {
		this.btnSetTipoPagoTotal = btnSetTipoPagoTotal;
	}

	public JPanel getPanelTotalesOrdenMedio() {
		return panelTotalesOrdenMedio;
	}

	public void setPanelTotalesOrdenMedio(JPanel panelTotalesOrdenMedio) {
		this.panelTotalesOrdenMedio = panelTotalesOrdenMedio;
	}

	public JLabel getLblSumanOrdenMedio() {
		return lblSumanOrdenMedio;
	}

	public void setLblSumanOrdenMedio(JLabel lblSumanOrdenMedio) {
		this.lblSumanOrdenMedio = lblSumanOrdenMedio;
	}

	public JTextField getTxtSumanOrdenMedio() {
		return txtSumanOrdenMedio;
	}

	public void setTxtSumanOrdenMedio(JTextField txtSumanOrdenMedio) {
		this.txtSumanOrdenMedio = txtSumanOrdenMedio;
	}

	public JLabel getLblIVAOrdenMedio() {
		return lblIVAOrdenMedio;
	}

	public void setLblIVAOrdenMedio(JLabel lblIVAOrdenMedio) {
		this.lblIVAOrdenMedio = lblIVAOrdenMedio;
	}

	public JTextField getTxtIVAOrdenMedio() {
		return txtIVAOrdenMedio;
	}

	public void setTxtIVAOrdenMedio(JTextField txtIVAOrdenMedio) {
		this.txtIVAOrdenMedio = txtIVAOrdenMedio;
	}

	public JLabel getLblDescuentoOrdenMedio() {
		return lblDescuentoOrdenMedio;
	}

	public void setLblDescuentoOrdenMedio(JLabel lblDescuentoOrdenMedio) {
		this.lblDescuentoOrdenMedio = lblDescuentoOrdenMedio;
	}

	public JTextField getTxtDescuentoOrdenMedio() {
		return txtDescuentoOrdenMedio;
	}

	public void setTxtDescuentoOrdenMedio(JTextField txtDescuentoOrdenMedio) {
		this.txtDescuentoOrdenMedio = txtDescuentoOrdenMedio;
	}

	public JLabel getLblTotalOrdenMedio() {
		return lblTotalOrdenMedio;
	}

	public void setLblTotalOrdenMedio(JLabel lblTotalOrdenMedio) {
		this.lblTotalOrdenMedio = lblTotalOrdenMedio;
	}

	public JTextField getTxtTotalOrdenMedio() {
		return txtTotalOrdenMedio;
	}

	public void setTxtTotalOrdenMedio(JTextField txtTotalOrdenMedio) {
		this.txtTotalOrdenMedio = txtTotalOrdenMedio;
	}

	public JLabel getLblSubTotalOrdenMedio() {
		return lblSubTotalOrdenMedio;
	}

	public void setLblSubTotalOrdenMedio(JLabel lblSubTotalOrdenMedio) {
		this.lblSubTotalOrdenMedio = lblSubTotalOrdenMedio;
	}

	public JTextField getTxtSubtotalOrdenMedio() {
		return txtSubtotalOrdenMedio;
	}

	public void setTxtSubtotalOrdenMedio(JTextField txtSubtotalOrdenMedio) {
		this.txtSubtotalOrdenMedio = txtSubtotalOrdenMedio;
	}

	public JTable getTblOrdenesMedios() {
		return tblOrdenesMedios;
	}

	public void setTblOrdenesMedios(JTable tblOrdenesMedios) {
		this.tblOrdenesMedios = tblOrdenesMedios;
	}

	public JButton getBtnSetPDsctoOrdenMedioxProv() {
		return btnSetPDsctoOrdenMedioxProv;
	}

	public void setBtnSetPDsctoOrdenMedioxProv(JButton btnSetPDsctoOrdenMedioxProv) {
		this.btnSetPDsctoOrdenMedioxProv = btnSetPDsctoOrdenMedioxProv;
	}

	public JButton getBtnSetPDsctoOrdenMedioTotal() {
		return btnSetPDsctoOrdenMedioTotal;
	}

	public void setBtnSetPDsctoOrdenMedioTotal(JButton btnSetPDsctoOrdenMedioTotal) {
		this.btnSetPDsctoOrdenMedioTotal = btnSetPDsctoOrdenMedioTotal;
	}

	public JScrollPane getSpTblOrdenesMedioCmp() {
		return spTblOrdenesMedioCmp;
	}

	public void setSpTblOrdenesMedioCmp(JScrollPane spTblOrdenesMedioCmp) {
		this.spTblOrdenesMedioCmp = spTblOrdenesMedioCmp;
	}

	public JTable getTblOrdenesMediosCmp() {
		return tblOrdenesMediosCmp;
	}

	public void setTblOrdenesMediosCmp(JTable tblOrdenesMediosCmp) {
		this.tblOrdenesMediosCmp = tblOrdenesMediosCmp;
	}

	public JCheckBox getCbAgrupaOrdenes() {
		return cbAgrupaOrdenes;
	}

	public void setCbAgrupaOrdenes(JCheckBox cbAgrupaOrdenes) {
		this.cbAgrupaOrdenes = cbAgrupaOrdenes;
	}

	public JCheckBox getCbPlanMedioNuevaVersion() {
		return cbPlanMedioNuevaVersion;
	}


	public void setCbPlanMedioNuevaVersion(JCheckBox cbPlanMedioNuevaVersion) {
		this.cbPlanMedioNuevaVersion = cbPlanMedioNuevaVersion;
	}


	public JCheckBox getCbPlanMedioNuevoMes() {
		return cbPlanMedioNuevoMes;
	}


	public void setCbPlanMedioNuevoMes(JCheckBox cbPlanMedioNuevoMes) {
		this.cbPlanMedioNuevoMes = cbPlanMedioNuevoMes;
	}

	public JButton getBtnPlanMedioRelacion() {
		return btnPlanMedioRelacion;
	}

	public void setBtnPlanMedioRelacion(JButton btnPlanMedioRelacion) {
		this.btnPlanMedioRelacion = btnPlanMedioRelacion;
	}

	public JTextArea getTxtObservacionOrdenMedio() {
		return txtObservacionOrdenMedio;
	}

	public void setTxtObservacionOrdenMedio(JTextArea txtObservacionOrdenMedio) {
		this.txtObservacionOrdenMedio = txtObservacionOrdenMedio;
	}

	public JButton getBtnSetObservacionOrdenMedio() {
		return btnSetObservacionOrdenMedio;
	}

	public void setBtnSetObservacionOrdenMedio(JButton btnSetObservacionOrdenMedio) {
		this.btnSetObservacionOrdenMedio = btnSetObservacionOrdenMedio;
	}

	public JButton getBtnSetObservacionOrdenMedioxProv() {
		return btnSetObservacionOrdenMedioxProv;
	}

	public void setBtnSetObservacionOrdenMedioxProv(
			JButton btnSetObservacionOrdenMedioxProv) {
		this.btnSetObservacionOrdenMedioxProv = btnSetObservacionOrdenMedioxProv;
	}

	public JButton getBtnSetObservacionOrdenMedioTotal() {
		return btnSetObservacionOrdenMedioTotal;
	}

	public void setBtnSetObservacionOrdenMedioTotal(
			JButton btnSetObservacionOrdenMedioTotal) {
		this.btnSetObservacionOrdenMedioTotal = btnSetObservacionOrdenMedioTotal;
	}

	public JTextField getTextPlanMedioRelacion() {
		return textPlanMedioRelacion;
	}

	public void setTextPlanMedioRelacion(JTextField textPlanMedioRelacion) {
		this.textPlanMedioRelacion = textPlanMedioRelacion;
	}

	public JButton getBtnLimpiarCodigoOrden() {
		return btnLimpiarCodigoOrden;
	}

	public void setBtnLimpiarCodigoOrden(JButton btnLimpiarCodigoOrden) {
		this.btnLimpiarCodigoOrden = btnLimpiarCodigoOrden;
	}

	public JTextField getTextCodigoOrden() {
		return textCodigoOrden;
	}

	public void setTextCodigoOrden(JTextField textCodigoOrden) {
		this.textCodigoOrden = textCodigoOrden;
	}

	public JButton getBtnCambiarCodigo() {
		return btnCambiarCodigo;
	}

	public void setBtnCambiarCodigo(JButton btnCambiarCodigo) {
		this.btnCambiarCodigo = btnCambiarCodigo;
	}

	public JCheckBox getCbPautaBasica() {
		return cbPautaBasica;
	}

	public void setCbPautaBasica(JCheckBox cbPautaBasica) {
		this.cbPautaBasica = cbPautaBasica;
	}

	public JCheckBox getCbOrdenPorProductoComercial() {
		return cbOrdenPorProductoComercial;
	}

	public JCheckBox getCbOrdenPorVersion() {
		return cbOrdenPorVersion;
	}

	public void setCbOrdenPorProductoComercial(JCheckBox cbOrdenPorProductoComercial) {
		this.cbOrdenPorProductoComercial = cbOrdenPorProductoComercial;
	}

	public void setCbOrdenPorVersion(JCheckBox cbOrdenPorVersion) {
		this.cbOrdenPorVersion = cbOrdenPorVersion;
	}

	public JCheckBox getCbIvaProveedor() {
		return cbIvaProveedor;
	}

	public void setCbIvaProveedor(JCheckBox cbIvaProveedor) {
		this.cbIvaProveedor = cbIvaProveedor;
	}

	public JCheckBox getCbIvaCliente() {
		return cbIvaCliente;
	}

	public void setCbIvaCliente(JCheckBox cbIvaCliente) {
		this.cbIvaCliente = cbIvaCliente;
	}

	public void setCmbFechaAprobacion(DateComboBox cmbFechaAprobacion) {
		this.cmbFechaAprobacion = cmbFechaAprobacion;
	}

	public void setCbPautaCine(JCheckBox cbPautaCine) {
		this.cbPautaCine = cbPautaCine;
	}

	public JCheckBox getCbPrepago() {
		return cbPrepago;
	}

	public JCheckBox getCbComisionAdicional() {
		return cbComisionAdicional;
	}

	public JTextField getTxtPorcentajeComisionAdicional() {
		return txtPorcentajeComisionAdicional;
	}
}
