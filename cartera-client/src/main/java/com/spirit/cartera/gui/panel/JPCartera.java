package com.spirit.cartera.gui.panel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPCartera extends SpiritModelImpl {
	
	public JPCartera() {
		setName("Cartera");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpCartera = new JideTabbedPane();
		spPanelCartera = new JScrollPane();
		panelCartera = new JPanel();
		goodiesFormsSeparator1 = compFactory.createSeparator("Datos de la Cartera");
		lblTipoCartera = new JLabel();
		cmbTipoCartera = new JComboBox();
		txtCodigoCartera = new JTextField();
		lblCodigoCartera = new JLabel();
		lblTipoDocumentoCartera = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblFechaEmisionCartera = new JLabel();
		cmbFechaEmisionCartera = new DateComboBox();
		lblReferenciaCartera = new JLabel();
		txtReferenciaCartera = new JTextField();
		lblPreimpreso = new JLabel();
		txtPreimpreso = new JTextField();
		lblCorporacionCartera = new JLabel();
		txtCorporacionCartera = new JTextField();
		btnBuscarCorporacionCartera = new JButton();
		lbMonedaCartera = new JLabel();
		cmbMonedaCartera = new JComboBox();
		lblClienteCartera = new JLabel();
		txtClienteCartera = new JTextField();
		btnBuscarClienteCartera = new JButton();
		lblEstadoCartera = new JLabel();
		cmbEstadoCartera = new JComboBox();
		lblClienteOficinaCartera = new JLabel();
		txtClienteOficinaCartera = new JTextField();
		btnBuscarClienteOficinaCartera = new JButton();
		lblFechaCambioEstadoCartera = new JLabel();
		txtFechaCambioEstadoCartera = new JTextField();
		lblOficinaCartera = new JLabel();
		txtOficinaCartera = new JTextField();
		lblValorCartera = new JLabel();
		txtValorCartera = new JTextField();
		lblVendedorCartera = new JLabel();
		cmbVendedorCartera = new JComboBox();
		lblSaldoCartera = new JLabel();
		lblComentariosCartera = new JLabel();
		txtComentariosCartera = new JTextField();
		txtSaldoCartera = new JTextField();
		spPanelCarteraDetalle = new JScrollPane();
		panelCarteraDetalle = new JPanel();
		lblDocumentoDetalle = new JLabel();
		cmbDocumentoDetalle = new JComboBox();
		lblSecuencialDetalle = new JLabel();
		txtSecuencialDetalle = new JTextField();
		lblPorcentaje = new JLabel();
		cmbPorcentaje = new JComboBox();
		lblReferenciaDetalle = new JLabel();
		txtReferenciaDetalle = new JTextField();
		lblBancoDetalle = new JLabel();
		cmbBancoDetalle = new JComboBox();
		lblPreimpresoDetalle = new JLabel();
		txtPreimpresoDetalle = new JTextField();
		lblCuentaBancaria = new JLabel();
		cmbCuentaBancaria = new JComboBox();
		lblAutorizacionDetalle = new JLabel();
		txtAutorizacionDetalle = new JTextField();
		lblDepositoDetalle = new JLabel();
		txtDepositoDetalle = new JTextField();
		lblCarteraDetalle = new JLabel();
		txtCarteraDetalle = new JTextField();
		lblLineaDetalle = new JLabel();
		cmbLineaDetalle = new JComboBox();
		lblFechaCreacionDetalle = new JLabel();
		txtFechaCreacionDetalle = new JTextField();
		lblValorDetalle = new JLabel();
		txtValorDetalle = new JTextField();
		lblFechaVencimientoDetalle = new JLabel();
		txtFechaVencimientoDetalle = new JTextField();
		lblSaldoDetalle = new JLabel();
		txtSaldoDetalle = new JTextField();
		lblFechaActualizacionDetalle = new JLabel();
		txtFechaActualizacionDetalle = new JTextField();
		lblFechaCarteraDetalle = new JLabel();
		cmbFechaCarteraDetalle = new DateComboBox();
		lblSustentoTributario = new JLabel();
		cmbSustentoTributario = new JComboBox();
		lblCotizacionDetalle = new JLabel();
		txtCotizacionDetalle = new JTextField();
		panel1 = new JPanel();
		btnActualizarDetalle = new JButton();
		btnAgregarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spDetalle = new JScrollPane();
		tblDetalle = new JTable();
		CellConstraints cc = new CellConstraints();
		popup = new JPopupMenu();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpCartera ========
		{
			
			//======== spPanelCartera ========
			{
				
				//======== panelCartera ========
				{
					panelCartera.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX5),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(90)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX5),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(42)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX5)
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
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					panelCartera.add(goodiesFormsSeparator1, cc.xywh(3, 3, 15, 1));
					
					//---- lblTipoCartera ----
					lblTipoCartera.setText("Tipo de cartera:");
					panelCartera.add(lblTipoCartera, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(cmbTipoCartera, cc.xywh(5, 5, 3, 1));
					panelCartera.add(txtCodigoCartera, cc.xywh(15, 5, 3, 1));
					
					//---- lblCodigoCartera ----
					lblCodigoCartera.setText("C\u00f3digo:");
					panelCartera.add(lblCodigoCartera, cc.xywh(13, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblTipoDocumentoCartera ----
					lblTipoDocumentoCartera.setText("Tipo documento:");
					panelCartera.add(lblTipoDocumentoCartera, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(cmbTipoDocumento, cc.xywh(5, 7, 3, 1));
					
					//---- lblFechaEmisionCartera ----
					lblFechaEmisionCartera.setText("Fecha de emisi\u00f3n: ");
					panelCartera.add(lblFechaEmisionCartera, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(cmbFechaEmisionCartera, cc.xywh(15, 7, 3, 1));
					
					//---- lblReferenciaCartera ----
					lblReferenciaCartera.setText("Referencia:");
					panelCartera.add(lblReferenciaCartera, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtReferenciaCartera, cc.xy(5, 9));
					
					//---- lblPreimpreso ----
					lblPreimpreso.setText("Preimpreso:");
					lblPreimpreso.setHorizontalAlignment(SwingConstants.RIGHT);
					panelCartera.add(lblPreimpreso, cc.xy(13, 9));
					panelCartera.add(txtPreimpreso, cc.xywh(15, 9, 3, 1));
					
					//---- lblCorporacionCartera ----
					lblCorporacionCartera.setText("Corporaci\u00f3n:");
					panelCartera.add(lblCorporacionCartera, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtCorporacionCartera, cc.xywh(5, 11, 3, 1));
					panelCartera.add(btnBuscarCorporacionCartera, cc.xywh(9, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lbMonedaCartera ----
					lbMonedaCartera.setText("Moneda:");
					panelCartera.add(lbMonedaCartera, cc.xywh(13, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(cmbMonedaCartera, cc.xywh(15, 11, 3, 1));
					
					//---- lblClienteCartera ----
					lblClienteCartera.setText("Cliente:");
					panelCartera.add(lblClienteCartera, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtClienteCartera, cc.xywh(5, 13, 3, 1));
					panelCartera.add(btnBuscarClienteCartera, cc.xywh(9, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblEstadoCartera ----
					lblEstadoCartera.setText("Estado:");
					panelCartera.add(lblEstadoCartera, cc.xywh(13, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(cmbEstadoCartera, cc.xywh(15, 13, 3, 1));
					
					//---- lblClienteOficinaCartera ----
					lblClienteOficinaCartera.setText("Oficina cliente:");
					panelCartera.add(lblClienteOficinaCartera, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtClienteOficinaCartera, cc.xywh(5, 15, 3, 1));
					panelCartera.add(btnBuscarClienteOficinaCartera, cc.xywh(9, 15, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblFechaCambioEstadoCartera ----
					lblFechaCambioEstadoCartera.setText("Fecha cambio estado:");
					panelCartera.add(lblFechaCambioEstadoCartera, cc.xywh(13, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtFechaCambioEstadoCartera, cc.xywh(15, 15, 3, 1));
					
					//---- lblOficinaCartera ----
					lblOficinaCartera.setText("Oficina:");
					panelCartera.add(lblOficinaCartera, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtOficinaCartera, cc.xywh(5, 17, 3, 1));
					
					//---- lblValorCartera ----
					lblValorCartera.setText("Valor:");
					panelCartera.add(lblValorCartera, cc.xy(13, 17));
					
					//---- txtValorCartera ----
					txtValorCartera.setHorizontalAlignment(JTextField.RIGHT);
					panelCartera.add(txtValorCartera, cc.xywh(15, 17, 3, 1));
					
					//---- lblVendedorCartera ----
					lblVendedorCartera.setText("Vendedor:");
					panelCartera.add(lblVendedorCartera, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(cmbVendedorCartera, cc.xywh(5, 19, 3, 1));
					
					//---- lblSaldoCartera ----
					lblSaldoCartera.setText("Saldo:");
					panelCartera.add(lblSaldoCartera, cc.xy(13, 19));
					
					//---- lblComentariosCartera ----
					lblComentariosCartera.setText("Comentarios:");
					panelCartera.add(lblComentariosCartera, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCartera.add(txtComentariosCartera, cc.xywh(5, 21, 13, 1));
					
					//---- txtSaldoCartera ----
					txtSaldoCartera.setHorizontalAlignment(JTextField.RIGHT);
					panelCartera.add(txtSaldoCartera, cc.xywh(15, 19, 3, 1));
				}
				spPanelCartera.setViewportView(panelCartera);
			}
			jtpCartera.addTab("General", spPanelCartera);
			
			
			//======== spPanelCarteraDetalle ========
			{
				
				//======== panelCarteraDetalle ========
				{
					panelCarteraDetalle.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec("max(default;50dlu):grow"),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DLUY5, FormSpec.NO_GROW)
						}));
					
					//---- lblDocumentoDetalle ----
					lblDocumentoDetalle.setText("Documento:");
					panelCarteraDetalle.add(lblDocumentoDetalle, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(cmbDocumentoDetalle, cc.xywh(5, 3, 9, 1));
					
					//---- lblSecuencialDetalle ----
					lblSecuencialDetalle.setText("Secuencial:");
					panelCarteraDetalle.add(lblSecuencialDetalle, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtSecuencialDetalle, cc.xy(19, 3));
					
					//---- lblBancoDetalle ----
					lblBancoDetalle.setText("Banco:");
					panelCarteraDetalle.add(lblBancoDetalle, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(cmbBancoDetalle, cc.xywh(5, 5, 9, 1));
					
					//---- lblReferenciaDetalle ----
					lblReferenciaDetalle.setText("Referencia:");
					panelCarteraDetalle.add(lblReferenciaDetalle, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtReferenciaDetalle, cc.xy(19, 5));
					
					//---- lblCuentaBancaria ----
					lblCuentaBancaria.setText("Cuenta bancaria:");
					panelCarteraDetalle.add(lblCuentaBancaria, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(cmbCuentaBancaria, cc.xywh(5, 7, 9, 1));
					
					//---- lblPreimpresoDetalle ----
					lblPreimpresoDetalle.setText("Preimpreso:");
					panelCarteraDetalle.add(lblPreimpresoDetalle, cc.xywh(17, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtPreimpresoDetalle, cc.xy(19, 7));
					
					//---- lblDepositoDetalle ----
					lblDepositoDetalle.setText("Dep\u00f3sito:");
					panelCarteraDetalle.add(lblDepositoDetalle, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtDepositoDetalle, cc.xywh(5, 9, 9, 1));
					
					//---- lblAutorizacionDetalle ----
					lblAutorizacionDetalle.setText("Autorizaci\u00f3n:");
					panelCarteraDetalle.add(lblAutorizacionDetalle, cc.xywh(17, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtAutorizacionDetalle, cc.xy(19, 9));
					
					//---- lblLineaDetalle ----
					lblLineaDetalle.setText("L\u00ednea:");
					panelCarteraDetalle.add(lblLineaDetalle, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(cmbLineaDetalle, cc.xywh(5, 11, 9, 1));
					
					//---- lblCarteraDetalle ----
					lblCarteraDetalle.setText("Cartera:");
					panelCarteraDetalle.add(lblCarteraDetalle, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtCarteraDetalle, cc.xy(19, 11));
					
					//---- lblValorDetalle ----
					lblValorDetalle.setText("Valor:");
					panelCarteraDetalle.add(lblValorDetalle, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtValorDetalle ----
					txtValorDetalle.setHorizontalAlignment(JTextField.RIGHT);
					panelCarteraDetalle.add(txtValorDetalle, cc.xy(5, 13));
					
					//---- lblPorcentaje ----
					lblPorcentaje.setText("Porcentaje:");
					panelCarteraDetalle.add(lblPorcentaje, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(cmbPorcentaje, cc.xy(11, 13));
					
					//---- lblFechaCreacionDetalle ----
					lblFechaCreacionDetalle.setText("Fecha de creaci\u00f3n: ");
					panelCarteraDetalle.add(lblFechaCreacionDetalle, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtFechaCreacionDetalle, cc.xy(19, 13));
					
					//---- lblSaldoDetalle ----
					lblSaldoDetalle.setText("Saldo:");
					panelCarteraDetalle.add(lblSaldoDetalle, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtSaldoDetalle ----
					txtSaldoDetalle.setHorizontalAlignment(JTextField.RIGHT);
					panelCarteraDetalle.add(txtSaldoDetalle, cc.xy(5, 15));
					
					//---- lblFechaVencimientoDetalle ----
					lblFechaVencimientoDetalle.setText("Fecha de vencimiento: ");
					panelCarteraDetalle.add(lblFechaVencimientoDetalle, cc.xywh(17, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtFechaVencimientoDetalle, cc.xy(19, 15));
					
					//---- lblCotizacionDetalle ----
					lblCotizacionDetalle.setText("Cotizaci\u00f3n:");
					panelCarteraDetalle.add(lblCotizacionDetalle, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtCotizacionDetalle ----
					txtCotizacionDetalle.setHorizontalAlignment(JTextField.RIGHT);
					panelCarteraDetalle.add(txtCotizacionDetalle, cc.xy(5, 17));
					
					//---- lblFechaActualizacionDetalle ----
					lblFechaActualizacionDetalle.setText("Fecha de actualizaci\u00f3n: ");
					panelCarteraDetalle.add(lblFechaActualizacionDetalle, cc.xywh(17, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(txtFechaActualizacionDetalle, cc.xy(19, 17));
					
					//---- lblFechaCarteraDetalle ----
					lblFechaCarteraDetalle.setText("Fecha de cartera: ");
					panelCarteraDetalle.add(lblFechaCarteraDetalle, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCarteraDetalle.add(cmbFechaCarteraDetalle, cc.xy(19, 19));
					
					//---- lblSustentoTributario ----
					lblSustentoTributario.setText("Sustento Tributario:");
					panelCarteraDetalle.add(lblSustentoTributario, cc.xy(3, 21));
					panelCarteraDetalle.add(cmbSustentoTributario, cc.xywh(5, 21, 15, 1));
					
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
						
						//---- btnActualizarDetalle ----
						btnActualizarDetalle.setText("U");
						panel1.add(btnActualizarDetalle, cc.xy(3, 1));
						
						//---- btnAgregarDetalle ----
						btnAgregarDetalle.setText("A");
						panel1.add(btnAgregarDetalle, cc.xy(1, 1));
						
						//---- btnEliminarDetalle ----
						btnEliminarDetalle.setText("D");
						panel1.add(btnEliminarDetalle, cc.xy(5, 1));
					}
					panelCarteraDetalle.add(panel1, cc.xywh(3, 25, 17, 1));
					
					//======== spDetalle ========
					{
						spDetalle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						
						//---- tblDetalle ----
						tblDetalle.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null, null, null, null, null},
							},
							new String[] {
								"Sec.", "Documento", "T. Pago", "L\u00ednea", "F. Creaci\u00f3n", "F. Vencimiento", "Cartera", "Valor", "Saldo", "Diferido"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
						tblDetalle.setAutoCreateColumnsFromModel(true);
						spDetalle.setViewportView(tblDetalle);
					}
					panelCarteraDetalle.add(spDetalle, cc.xywh(3, 27, 17, 3));
				}
				spPanelCarteraDetalle.setViewportView(panelCarteraDetalle);
			}
			jtpCartera.addTab("Detalle", spPanelCarteraDetalle);
			
		}
		add(jtpCartera, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarCorporacionCartera.setToolTipText("Buscar Corporaci\u00f3n");
		btnBuscarCorporacionCartera.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarClienteCartera.setToolTipText("Buscar Operador de Negocio");
		btnBuscarClienteCartera.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarClienteOficinaCartera.setToolTipText("Buscar Oficina del Operador de Negocio");
		btnBuscarClienteOficinaCartera.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnAgregarDetalle.setToolTipText("Agregar Detalle");
		btnAgregarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		btnActualizarDetalle.setToolTipText("Actualizar Detalle");
		btnActualizarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		btnEliminarDetalle.setToolTipText("Eliminar Detalle");
		btnEliminarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		btnActualizarDetalle.setText("");
		btnAgregarDetalle.setText("");
		btnEliminarDetalle.setText("");
			
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblDetalle.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		tblDetalle.getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		tblDetalle.getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpCartera;
	private JScrollPane spPanelCartera;
	private JPanel panelCartera;
	private JComponent goodiesFormsSeparator1;
	private JLabel lblTipoCartera;
	private JComboBox cmbTipoCartera;
	private JTextField txtCodigoCartera;
	private JLabel lblCodigoCartera;
	private JLabel lblTipoDocumentoCartera;
	private JComboBox cmbTipoDocumento;
	private JLabel lblFechaEmisionCartera;
	private DateComboBox cmbFechaEmisionCartera;
	private JLabel lblReferenciaCartera;
	private JTextField txtReferenciaCartera;
	private JLabel lblPreimpreso;
	private JTextField txtPreimpreso;
	private JLabel lblCorporacionCartera;
	private JTextField txtCorporacionCartera;
	private JButton btnBuscarCorporacionCartera;
	private JLabel lbMonedaCartera;
	private JComboBox cmbMonedaCartera;
	private JLabel lblClienteCartera;
	private JTextField txtClienteCartera;
	private JButton btnBuscarClienteCartera;
	private JLabel lblEstadoCartera;
	private JComboBox cmbEstadoCartera;
	private JLabel lblClienteOficinaCartera;
	private JTextField txtClienteOficinaCartera;
	private JButton btnBuscarClienteOficinaCartera;
	private JLabel lblFechaCambioEstadoCartera;
	private JTextField txtFechaCambioEstadoCartera;
	private JLabel lblOficinaCartera;
	private JTextField txtOficinaCartera;
	private JLabel lblValorCartera;
	private JTextField txtValorCartera;
	private JLabel lblVendedorCartera;
	private JComboBox cmbVendedorCartera;
	private JLabel lblSaldoCartera;
	private JLabel lblComentariosCartera;
	private JTextField txtComentariosCartera;
	private JTextField txtSaldoCartera;
	private JScrollPane spPanelCarteraDetalle;
	private JPanel panelCarteraDetalle;
	private JLabel lblDocumentoDetalle;
	private JComboBox cmbDocumentoDetalle;
	private JLabel lblSecuencialDetalle;
	private JTextField txtSecuencialDetalle;
	private JLabel lblPorcentaje;
	private JComboBox cmbPorcentaje;
	private JLabel lblReferenciaDetalle;
	private JTextField txtReferenciaDetalle;
	private JLabel lblBancoDetalle;
	private JComboBox cmbBancoDetalle;
	private JLabel lblPreimpresoDetalle;
	private JTextField txtPreimpresoDetalle;
	private JLabel lblCuentaBancaria;
	private JComboBox cmbCuentaBancaria;
	private JLabel lblAutorizacionDetalle;
	private JTextField txtAutorizacionDetalle;
	private JLabel lblDepositoDetalle;
	private JTextField txtDepositoDetalle;
	private JLabel lblCarteraDetalle;
	private JTextField txtCarteraDetalle;
	private JLabel lblLineaDetalle;
	private JComboBox cmbLineaDetalle;
	private JLabel lblFechaCreacionDetalle;
	private JTextField txtFechaCreacionDetalle;
	private JLabel lblValorDetalle;
	private JTextField txtValorDetalle;
	private JLabel lblFechaVencimientoDetalle;
	private JTextField txtFechaVencimientoDetalle;
	private JLabel lblSaldoDetalle;
	private JTextField txtSaldoDetalle;
	private JLabel lblFechaActualizacionDetalle;
	private JTextField txtFechaActualizacionDetalle;
	private JLabel lblFechaCarteraDetalle;
	private DateComboBox cmbFechaCarteraDetalle;
	private JLabel lblSustentoTributario;
	private JComboBox cmbSustentoTributario;
	private JLabel lblCotizacionDetalle;
	private JTextField txtCotizacionDetalle;
	private JPanel panel1;
	private JButton btnActualizarDetalle;
	private JButton btnAgregarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spDetalle;
	private JTable tblDetalle;
	protected JMenuItem itemMapaAfectaCartera;
	protected JMenuItem itemEliminarDetalleCartera;
	protected JPopupMenu popup;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JMenuItem getMenuItemMapaAfectaCartera() {
		return itemMapaAfectaCartera;
	}
	
	public void setMenuItemMapaAfectaCartera(JMenuItem menuItem) {
		this.itemMapaAfectaCartera = menuItem;
	}
	
	public JMenuItem getMenuItemEliminarDetalleCartera() {
		return itemEliminarDetalleCartera;
	}
	
	public void setMenuItemEliminarDetalleCartera(JMenuItem menuItem) {
		this.itemEliminarDetalleCartera = menuItem;
	}
	
	public JPopupMenu getPopup() {
		return popup;
	}
	
	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
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
	
	public JButton getBtnBuscarClienteCartera() {
		return btnBuscarClienteCartera;
	}
	
	public void setBtnBuscarClienteCartera(JButton btnBuscarClienteCartera) {
		this.btnBuscarClienteCartera = btnBuscarClienteCartera;
	}
	
	public JButton getBtnBuscarClienteOficinaCartera() {
		return btnBuscarClienteOficinaCartera;
	}
	
	public void setBtnBuscarClienteOficinaCartera(
			JButton btnBuscarClienteOficinaCartera) {
		this.btnBuscarClienteOficinaCartera = btnBuscarClienteOficinaCartera;
	}
	
	public JButton getBtnBuscarCorporacionCartera() {
		return btnBuscarCorporacionCartera;
	}
	
	public void setBtnBuscarCorporacionCartera(JButton btnBuscarCorporacionCartera) {
		this.btnBuscarCorporacionCartera = btnBuscarCorporacionCartera;
	}
	
	public JComboBox getCmbBancoDetalle() {
		return cmbBancoDetalle;
	}
	
	public void setCmbBancoDetalle(JComboBox cmbCuentaBancariaDetalle) {
		this.cmbBancoDetalle = cmbCuentaBancariaDetalle;
	}
	
	public JComboBox getCmbDocumentoDetalle() {
		return cmbDocumentoDetalle;
	}
	
	public void setCmbDocumentoDetalle(JComboBox cmbDocumentoDetalle) {
		this.cmbDocumentoDetalle = cmbDocumentoDetalle;
	}
	
	public JComboBox getCmbEstadoCartera() {
		return cmbEstadoCartera;
	}
	
	public void setCmbEstadoCartera(JComboBox cmbEstadoCartera) {
		this.cmbEstadoCartera = cmbEstadoCartera;
	}
	
	public DateComboBox getCmbFechaCarteraDetalle() {
		return cmbFechaCarteraDetalle;
	}
	
	public void setCmbFechaCarteraDetalle(DateComboBox cmbFechaCarteraDetalle) {
		this.cmbFechaCarteraDetalle = cmbFechaCarteraDetalle;
	}
	
	public DateComboBox getCmbFechaEmisionCartera() {
		return cmbFechaEmisionCartera;
	}
	
	public void setCmbFechaEmisionCartera(DateComboBox cmbFechaEmisionCartera) {
		this.cmbFechaEmisionCartera = cmbFechaEmisionCartera;
	}
	
	public JComboBox getCmbLineaDetalle() {
		return cmbLineaDetalle;
	}
	
	public void setCmbLineaDetalle(JComboBox cmbLineaDetalle) {
		this.cmbLineaDetalle = cmbLineaDetalle;
	}
	
	public JComboBox getCmbMonedaCartera() {
		return cmbMonedaCartera;
	}
	
	public void setCmbMonedaCartera(JComboBox cmbMonedaCartera) {
		this.cmbMonedaCartera = cmbMonedaCartera;
	}
	
	public JComboBox getCmbTipoCartera() {
		return cmbTipoCartera;
	}
	
	public void setCmbTipoCartera(JComboBox cmbTipoCartera) {
		this.cmbTipoCartera = cmbTipoCartera;
	}
	
	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}
	
	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}
	
	public JComboBox getCmbVendedorCartera() {
		return cmbVendedorCartera;
	}
	
	public void setCmbVendedorCartera(JComboBox cmbVendedorCartera) {
		this.cmbVendedorCartera = cmbVendedorCartera;
	}
	
	public JideTabbedPane getJtpCartera() {
		return jtpCartera;
	}
	
	public void setJtpCartera(JideTabbedPane jtpCartera) {
		this.jtpCartera = jtpCartera;
	}
	
	public JTable getTblDetalle() {
		return tblDetalle;
	}
	
	public void setTblDetalle(JTable tblDetalle) {
		this.tblDetalle = tblDetalle;
	}
	
	public JTextField getTxtAutorizacionDetalle() {
		return txtAutorizacionDetalle;
	}
	
	public void setTxtAutorizacionDetalle(JTextField txtAutorizacionDetalle) {
		this.txtAutorizacionDetalle = txtAutorizacionDetalle;
	}
	
	public JTextField getTxtCarteraDetalle() {
		return txtCarteraDetalle;
	}
	
	public void setTxtCarteraDetalle(JTextField txtCarteraDetalle) {
		this.txtCarteraDetalle = txtCarteraDetalle;
	}
	
	public JTextField getTxtClienteCartera() {
		return txtClienteCartera;
	}
	
	public void setTxtClienteCartera(JTextField txtClienteCartera) {
		this.txtClienteCartera = txtClienteCartera;
	}
	
	public JTextField getTxtClienteOficinaCartera() {
		return txtClienteOficinaCartera;
	}
	
	public void setTxtClienteOficinaCartera(JTextField txtClienteOficinaCartera) {
		this.txtClienteOficinaCartera = txtClienteOficinaCartera;
	}
	
	public JTextField getTxtCodigoCartera() {
		return txtCodigoCartera;
	}
	
	public void setTxtCodigoCartera(JTextField txtCodigoCartera) {
		this.txtCodigoCartera = txtCodigoCartera;
	}
	
	public JTextField getTxtComentariosCartera() {
		return txtComentariosCartera;
	}
	
	public void setTxtComentariosCartera(JTextField txtComentariosCartera) {
		this.txtComentariosCartera = txtComentariosCartera;
	}
	
	public JTextField getTxtCorporacionCartera() {
		return txtCorporacionCartera;
	}
	
	public void setTxtCorporacionCartera(JTextField txtCorporacionCartera) {
		this.txtCorporacionCartera = txtCorporacionCartera;
	}
	
	public JTextField getTxtCotizacionDetalle() {
		return txtCotizacionDetalle;
	}
	
	public void setTxtCotizacionDetalle(JTextField txtCotizacionDetalle) {
		this.txtCotizacionDetalle = txtCotizacionDetalle;
	}
	
	public JTextField getTxtDepositoDetalle() {
		return txtDepositoDetalle;
	}
	
	public void setTxtDepositoDetalle(JTextField txtDepositoDetalle) {
		this.txtDepositoDetalle = txtDepositoDetalle;
	}
	
	public JTextField getTxtFechaActualizacionDetalle() {
		return txtFechaActualizacionDetalle;
	}
	
	public void setTxtFechaActualizacionDetalle(
			JTextField txtFechaActualizacionDetalle) {
		this.txtFechaActualizacionDetalle = txtFechaActualizacionDetalle;
	}
	
	public JTextField getTxtFechaCambioEstadoCartera() {
		return txtFechaCambioEstadoCartera;
	}
	
	public void setTxtFechaCambioEstadoCartera(
			JTextField txtFechaCambioEstadoCartera) {
		this.txtFechaCambioEstadoCartera = txtFechaCambioEstadoCartera;
	}
	
	public JTextField getTxtFechaCreacionDetalle() {
		return txtFechaCreacionDetalle;
	}
	
	public void setTxtFechaCreacionDetalle(JTextField txtFechaCreacionDetalle) {
		this.txtFechaCreacionDetalle = txtFechaCreacionDetalle;
	}
	
	public JTextField getTxtFechaVencimientoDetalle() {
		return txtFechaVencimientoDetalle;
	}
	
	public void setTxtFechaVencimientoDetalle(JTextField txtFechaVencimientoDetalle) {
		this.txtFechaVencimientoDetalle = txtFechaVencimientoDetalle;
	}
	
	public JTextField getTxtOficinaCartera() {
		return txtOficinaCartera;
	}
	
	public void setTxtOficinaCartera(JTextField txtOficinaCartera) {
		this.txtOficinaCartera = txtOficinaCartera;
	}
	
	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}
	
	public void setTxtPreimpreso(JTextField txtPreimpreso) {
		this.txtPreimpreso = txtPreimpreso;
	}
	
	public JTextField getTxtPreimpresoDetalle() {
		return txtPreimpresoDetalle;
	}
	
	public void setTxtPreimpresoDetalle(JTextField txtPreimpresoDetalle) {
		this.txtPreimpresoDetalle = txtPreimpresoDetalle;
	}
	
	public JTextField getTxtReferenciaCartera() {
		return txtReferenciaCartera;
	}
	
	public void setTxtReferenciaCartera(JTextField txtReferenciaCartera) {
		this.txtReferenciaCartera = txtReferenciaCartera;
	}
	
	public JTextField getTxtReferenciaDetalle() {
		return txtReferenciaDetalle;
	}
	
	public void setTxtReferenciaDetalle(JTextField txtReferenciaDetalle) {
		this.txtReferenciaDetalle = txtReferenciaDetalle;
	}
	
	public JTextField getTxtSaldoCartera() {
		return txtSaldoCartera;
	}
	
	public void setTxtSaldoCartera(JTextField txtSaldoCartera) {
		this.txtSaldoCartera = txtSaldoCartera;
	}
	
	public JTextField getTxtSaldoDetalle() {
		return txtSaldoDetalle;
	}
	
	public void setTxtSaldoDetalle(JTextField txtSaldoDetalle) {
		this.txtSaldoDetalle = txtSaldoDetalle;
	}
	
	public JTextField getTxtSecuencialDetalle() {
		return txtSecuencialDetalle;
	}
	
	public void setTxtSecuencialDetalle(JTextField txtSecuencialDetalle) {
		this.txtSecuencialDetalle = txtSecuencialDetalle;
	}
	
	public JTextField getTxtValorCartera() {
		return txtValorCartera;
	}
	
	public void setTxtValorCartera(JTextField txtValorCartera) {
		this.txtValorCartera = txtValorCartera;
	}
	
	public JTextField getTxtValorDetalle() {
		return txtValorDetalle;
	}
	
	public void setTxtValorDetalle(JTextField txtValorDetalle) {
		this.txtValorDetalle = txtValorDetalle;
	}
	
	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}
	
	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}
	
	public JComboBox getCmbCuentaBancaria() {
		return cmbCuentaBancaria;
	}
	
	public void setCmbCuentaBancaria(JComboBox cmbCuentaBancaria) {
		this.cmbCuentaBancaria = cmbCuentaBancaria;
	}
	
	public JComboBox getCmbSustentoTributario() {
		return cmbSustentoTributario;
	}
	
	public void setCmbSustentoTributario(JComboBox cmbSustentoTributario) {
		this.cmbSustentoTributario = cmbSustentoTributario;
	}

	public JMenuItem getItemEliminarDetalleCartera() {
		return itemEliminarDetalleCartera;
	}
	
	public void setItemEliminarDetalleCartera(JMenuItem itemEliminarDetalleCartera) {
		this.itemEliminarDetalleCartera = itemEliminarDetalleCartera;
	}
	
	public JMenuItem getItemMapaAfectaCartera() {
		return itemMapaAfectaCartera;
	}
	
	public void setItemMapaAfectaCartera(JMenuItem itemMapaAfectaCartera) {
		this.itemMapaAfectaCartera = itemMapaAfectaCartera;
	}

	public JLabel getLblDepositoDetalle() {
		return lblDepositoDetalle;
	}

	public void setLblDepositoDetalle(JLabel lblDepositoDetalle) {
		this.lblDepositoDetalle = lblDepositoDetalle;
	}

	public JComboBox getCmbPorcentaje() {
		return cmbPorcentaje;
	}

	public void setCmbPorcentaje(JComboBox cmbPorcentaje) {
		this.cmbPorcentaje = cmbPorcentaje;
	}
}
