package com.spirit.medios.gui.panel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.MySheetTableScrollPane;
import com.spirit.util.tree.TreeBrowser;
import com.spirit.util.tree.TreeNode;

/*
 * Created by JFormDesigner on Wed Sep 14 15:56:26 COT 2005
 */



/**
 * @author Tatiana Vásconez
 */
public abstract class JPNavegadorMedios extends ReportModelImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4568109443888866687L;
	public JPNavegadorMedios() {
		initComponents();
		setName("Navegador de Medios");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblResultadoBusqueda = compFactory.createTitle("Resultados de la B\u00fasqueda");
		lblCriteriosBusqueda = compFactory.createTitle("Criterios de B\u00fasqueda");
		scrollBarVertical = new JScrollBar();
		panelCriterioBusqueda = new JPanel();
		cbTodasOrdenes = new JCheckBox();
		lblPorFechaInicio = new JLabel();
		cmbPorFechaInicio = new DateComboBox();
		lblPorFechaFin = new JLabel();
		cbPorTipoProveedor = new JCheckBox();
		cmbPorTipoProveedor = new JComboBox();
		cbPorProveedor = new JCheckBox();
		cmbPorProveedor = new JComboBox();
		cbPorCliente = new JCheckBox();
		txtPorCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		txtPorCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cmbPorFechaFin = new DateComboBox();
		btnBuscar = new JButton();
		jideTabbedPane = new JideTabbedPane();
		spCliente = new JScrollPane();
		panelCliente = new JPanel();
		lblTipoIdentificacionCliente = new JLabel();
		txtTipoIdentificacionCliente = new JTextField();
		lblIdentificacionCliente = new JLabel();
		lblNombreLegalCliente = new JLabel();
		txtIdentificacionCliente = new JTextField();
		txtNombreLegalCliente = new JTextField();
		lblRazonSocialCliente = new JLabel();
		txtRazonSocialCliente = new JTextField();
		txtRepresentanteCliente = new JTextField();
		lblCorporacionCliente = new JLabel();
		txtCorporacionCliente = new JTextField();
		lblRepresentanteCliente = new JLabel();
		lblFechaCreacionCliente = new JLabel();
		lblEstadoCliente = new JLabel();
		lblTipoCliente = new JLabel();
		txtFechaCreacionCliente = new JTextField();
		txtEstadoCliente = new JTextField();
		txtTipoCliente = new JTextField();
		lblObservacionesCliente = new JLabel();
		lblTipoNegocioCliente = new JLabel();
		txtTipoNegocioCliente = new JTextField();
		txtObservacionesCliente = new JTextField();
		lblCuentaContableCliente = new JLabel();
		txtCtaContableCliente = new JTextField();
		spProveedor = new JScrollPane();
		panelProveedor = new JPanel();
		lblCodigoClienteOficina = new JLabel();
		txtCodigoClienteOficina = new JTextField();
		lblEstadoClienteOficina = new JLabel();
		txtEstadoClienteOficina = new JTextField();
		lblDireccionClienteOficina = new JLabel();
		txtDireccionClienteOficina = new JTextField();
		lblTelefonoClienteOficina = new JLabel();
		txtTelefonoClienteOficina = new JTextField();
		lblEmailClienteOficina = new JLabel();
		txtEmailClienteOficina = new JTextField();
		lblFaxClienteOficina = new JLabel();
		txtFaxClienteOficina = new JTextField();
		lblFechaCreacionClienteOficina = new JLabel();
		txtFechaCreacionClienteOficina = new JTextField();
		lblMontoCreditoClienteOficina = new JLabel();
		txtMontoCreditoClienteOficina = new JTextField();
		lblCalificacionClienteOficina = new JLabel();
		txtCalificacionClienteOficina = new JTextField();
		lblMontoGarantiaClienteOficina = new JLabel();
		lblObservacionClienteOficina = new JLabel();
		txtMontoGarantiaClienteOficina = new JTextField();
		lblDescripcionClienteOficina = new JLabel();
		txtDescripcionClienteOficina = new JTextField();
		txtObservacionClienteOficina = new JTextField();
		spOrdenMedios = new JScrollPane();
		panelOrdenMedios = new JPanel();
		lblCodigoOrden = new JLabel();
		txtCodigoOrden = new JTextField();
		lblEstadoOrden = new JLabel();
		txtEstadoOrden = new JTextField();
		lblFechaInicioOrden = new JLabel();
		txtFechaInicioOrden = new JTextField();
		lblProveedorOrden = new JLabel();
		txtProveedorOrden = new JTextField();
		lblClienteOrden = new JLabel();
		txtClienteOrden = new JTextField();
		lblPlanMedioOrden = new JLabel();
		txtPlanMedioOrden = new JTextField();
		lblFechaFinOrden = new JLabel();
		txtFechaFinOrden = new JTextField();
		lblCorporacionOrden = new JLabel();
		txtCorporacionOrden = new JTextField();
		lblOficinaOrden = new JLabel();
		txtOficinaOrden = new JTextField();
		lblValorOrden = new JLabel();
		txtValorOrden = new JTextField();
		scrollBarHorizontal = new JScrollBar();
		CellConstraints cc = new CellConstraints();
		rootNodeTreeOrdenMedios = new TreeNode(null, "", null, "images/icons/database.png", "images/icons/database.png", 2, 0);
		treeOrdenMedios = new TreeBrowser(getRootNodeTreeOrdenMedios(),"",2);

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX8),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));
		add(lblResultadoBusqueda, cc.xywh(3, 3, 5, 1));
		add(lblCriteriosBusqueda, cc.xywh(13, 3, 5, 1));
		add(treeOrdenMedios, cc.xywh(3, 5, 5, 13));
		add(scrollBarVertical, cc.xywh(9, 5, 1, 13));

		//======== panelCriterioBusqueda ========
		{
			panelCriterioBusqueda.setBorder(new TitledBorder(new EtchedBorder(), ""));
			panelCriterioBusqueda.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default, default:grow, default"),
				new RowSpec[] {
					new RowSpec(Sizes.DLUY14),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY14),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY14),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY14),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY14),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY14),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY14)
				}));
			
			//---- cbTodasOrdenes ----
			cbTodasOrdenes.setText("Todas las Ordenes");
			panelCriterioBusqueda.add(cbTodasOrdenes, cc.xy(1, 1));
			
			//---- lblPorFechaInicio ----
			lblPorFechaInicio.setText("Fecha Inicio Orden:");
			panelCriterioBusqueda.add(lblPorFechaInicio, cc.xywh(1, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
			panelCriterioBusqueda.add(cmbPorFechaInicio, cc.xywh(2, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblPorFechaFin ----
			lblPorFechaFin.setText("Fecha Fin Orden:");
			panelCriterioBusqueda.add(lblPorFechaFin, cc.xywh(1, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
			
			//---- cbPorTipoProveedor ----
			cbPorTipoProveedor.setText("Por Tipo Proveedor");
			panelCriterioBusqueda.add(cbPorTipoProveedor, cc.xy(1, 7));
			panelCriterioBusqueda.add(cmbPorTipoProveedor, cc.xywh(2, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- cbPorProveedor ----
			cbPorProveedor.setText("Por Proveedor");
			panelCriterioBusqueda.add(cbPorProveedor, cc.xy(1, 9));
			panelCriterioBusqueda.add(cmbPorProveedor, cc.xywh(2, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- cbPorCliente ----
			cbPorCliente.setText("Por Cliente");
			panelCriterioBusqueda.add(cbPorCliente, cc.xywh(1, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
			
			//---- txtPorCorporacion ----
			txtPorCorporacion.setEditable(false);
			panelCriterioBusqueda.add(txtPorCorporacion, cc.xywh(2, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			panelCriterioBusqueda.add(btnBuscarCorporacion, cc.xywh(3, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			
			//---- txtPorCliente ----
			txtPorCliente.setEditable(false);
			panelCriterioBusqueda.add(txtPorCliente, cc.xywh(2, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			panelCriterioBusqueda.add(btnBuscarCliente, cc.xywh(3, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			panelCriterioBusqueda.add(cmbPorFechaFin, cc.xywh(2, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		}
		add(panelCriterioBusqueda, cc.xywh(13, 5, 5, 1));

		//---- btnBuscar ----
		btnBuscar.setText("Buscar");
		add(btnBuscar, cc.xy(13, 7));

		//======== spJideTabbedPane ========
		{
			
			//======== spCliente ========
			{
				
				//======== panelCliente ========
				{
					panelCliente.setBorder(new EtchedBorder());
					panelCliente.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY3)
						}));
					
					//---- lblTipoIdentificacionCliente ----
					lblTipoIdentificacionCliente.setText("Tipo Identificaci\u00f3n:");
					panelCliente.add(lblTipoIdentificacionCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtTipoIdentificacionCliente ----
					txtTipoIdentificacionCliente.setEditable(false);
					panelCliente.add(txtTipoIdentificacionCliente, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblIdentificacionCliente ----
					lblIdentificacionCliente.setText("Identificaci\u00f3n:");
					panelCliente.add(lblIdentificacionCliente, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblNombreLegalCliente ----
					lblNombreLegalCliente.setText("Nombre Legal:");
					panelCliente.add(lblNombreLegalCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtIdentificacionCliente ----
					txtIdentificacionCliente.setEditable(false);
					panelCliente.add(txtIdentificacionCliente, cc.xywh(9, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtNombreLegalCliente ----
					txtNombreLegalCliente.setEditable(false);
					panelCliente.add(txtNombreLegalCliente, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblRazonSocialCliente ----
					lblRazonSocialCliente.setText("Raz\u00f3n Social:");
					panelCliente.add(lblRazonSocialCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtRazonSocialCliente ----
					txtRazonSocialCliente.setEditable(false);
					panelCliente.add(txtRazonSocialCliente, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtRepresentanteCliente ----
					txtRepresentanteCliente.setEditable(false);
					panelCliente.add(txtRepresentanteCliente, cc.xywh(5, 9, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCorporacionCliente ----
					lblCorporacionCliente.setText("Corporaci\u00f3n");
					panelCliente.add(lblCorporacionCliente, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtCorporacionCliente ----
					txtCorporacionCliente.setEditable(false);
					panelCliente.add(txtCorporacionCliente, cc.xywh(5, 11, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblRepresentanteCliente ----
					lblRepresentanteCliente.setText("Representante:");
					panelCliente.add(lblRepresentanteCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblFechaCreacionCliente ----
					lblFechaCreacionCliente.setText("Fecha de Creaci\u00f3n: ");
					panelCliente.add(lblFechaCreacionCliente, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblEstadoCliente ----
					lblEstadoCliente.setText("Estado:");
					panelCliente.add(lblEstadoCliente, cc.xywh(7, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblTipoCliente ----
					lblTipoCliente.setText("TipoCliente:");
					panelCliente.add(lblTipoCliente, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtFechaCreacionCliente ----
					txtFechaCreacionCliente.setEditable(false);
					panelCliente.add(txtFechaCreacionCliente, cc.xywh(5, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtEstadoCliente ----
					txtEstadoCliente.setEditable(false);
					panelCliente.add(txtEstadoCliente, cc.xywh(9, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtTipoCliente ----
					txtTipoCliente.setEditable(false);
					panelCliente.add(txtTipoCliente, cc.xywh(5, 15, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblObservacionesCliente ----
					lblObservacionesCliente.setText("Observaciones:");
					panelCliente.add(lblObservacionesCliente, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblTipoNegocioCliente ----
					lblTipoNegocioCliente.setText("Tipo Negocio:");
					panelCliente.add(lblTipoNegocioCliente, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtTipoNegocioCliente ----
					txtTipoNegocioCliente.setEditable(false);
					panelCliente.add(txtTipoNegocioCliente, cc.xywh(5, 17, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtObservacionesCliente ----
					txtObservacionesCliente.setEditable(false);
					panelCliente.add(txtObservacionesCliente, cc.xywh(5, 19, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCuentaContableCliente ----
					lblCuentaContableCliente.setText("Cta Contable:");
					panelCliente.add(lblCuentaContableCliente, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtCtaContableCliente ----
					txtCtaContableCliente.setEditable(false);
					panelCliente.add(txtCtaContableCliente, cc.xywh(5, 21, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				spCliente.setViewportView(panelCliente);
			}
			jideTabbedPane.addTab("Cliente", spCliente);
			
			
			//======== spProveedor ========
			{
				
				//======== panelProveedor ========
				{
					panelProveedor.setBorder(new EtchedBorder());
					panelProveedor.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY3)
						}));
					
					//---- lblCodigoClienteOficina ----
					lblCodigoClienteOficina.setText("C\u00f3digo:");
					txtCodigoClienteOficina.setEditable(false);
					panelProveedor.add(lblCodigoClienteOficina, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtCodigoClienteOficina, cc.xy(5, 3));
					
					//---- lblEstadoClienteOficina ----
					lblEstadoClienteOficina.setText("Estado:");
					txtEstadoClienteOficina.setEditable(false);
					panelProveedor.add(lblEstadoClienteOficina, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtEstadoClienteOficina, cc.xy(9, 3));
					
					//---- lblDireccionClienteOficina ----
					lblDireccionClienteOficina.setText("Direcci\u00f3n:");
					txtDireccionClienteOficina.setEditable(false);
					panelProveedor.add(lblDireccionClienteOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtDireccionClienteOficina, cc.xy(5, 5));
					
					//---- lblTelefonoClienteOficina ----
					lblTelefonoClienteOficina.setText("Tel\u00e9fono:");
					txtTelefonoClienteOficina.setEditable(false);
					panelProveedor.add(lblTelefonoClienteOficina, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtTelefonoClienteOficina, cc.xy(9, 5));
					
					//---- lblEmailClienteOficina ----
					lblEmailClienteOficina.setText("Email:");
					txtEmailClienteOficina.setEditable(false);
					panelProveedor.add(lblEmailClienteOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtEmailClienteOficina, cc.xy(5, 7));
					
					//---- lblFaxClienteOficina ----
					lblFaxClienteOficina.setText("Fax:");
					txtFaxClienteOficina.setEditable(false);
					panelProveedor.add(lblFaxClienteOficina, cc.xywh(7, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtFaxClienteOficina, cc.xy(9, 7));
					
					//---- lblFechaCreacionClienteOficina ----
					lblFechaCreacionClienteOficina.setText("Fecha de Creaci\u00f3n:");
					txtFechaCreacionClienteOficina.setEditable(false);
					panelProveedor.add(lblFechaCreacionClienteOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtFechaCreacionClienteOficina, cc.xy(5, 9));
					
					//---- lblMontoCreditoClienteOficina ----
					lblMontoCreditoClienteOficina.setText("Monto Credito:");
					txtMontoCreditoClienteOficina.setEditable(false);
					panelProveedor.add(lblMontoCreditoClienteOficina, cc.xywh(7, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtMontoCreditoClienteOficina, cc.xy(9, 9));
					
					//---- lblCalificacionClienteOficina ----
					lblCalificacionClienteOficina.setText("Calificaci\u00f3n:");
					txtCalificacionClienteOficina.setEditable(false);
					panelProveedor.add(lblCalificacionClienteOficina, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtCalificacionClienteOficina, cc.xy(5, 11));
					
					//---- lblMontoGarantiaClienteOficina ----
					lblMontoGarantiaClienteOficina.setText("Monto Garant\u00eda:");
					panelProveedor.add(lblMontoGarantiaClienteOficina, cc.xywh(7, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblObservacionClienteOficina ----
					lblObservacionClienteOficina.setText("Observaci\u00f3n:");
					txtMontoGarantiaClienteOficina.setEditable(false);
					panelProveedor.add(lblObservacionClienteOficina, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProveedor.add(txtMontoGarantiaClienteOficina, cc.xy(9, 11));
					
					//---- lblDescripcionClienteOficina ----
					lblDescripcionClienteOficina.setText("Descripci\u00f3n:");
					panelProveedor.add(lblDescripcionClienteOficina, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					txtDescripcionClienteOficina.setEditable(false);
					txtObservacionClienteOficina.setEditable(false);
					panelProveedor.add(txtDescripcionClienteOficina, cc.xywh(5, 15, 5, 1));
					panelProveedor.add(txtObservacionClienteOficina, cc.xywh(5, 13, 5, 1));
				}
				spProveedor.setViewportView(panelProveedor);
			}
			jideTabbedPane.addTab("Proveedor", spProveedor);
			
			
			//======== spOrdenMedios ========
			{
				
				//======== panelOrdenMedios ========
				{
					panelOrdenMedios.setBorder(new EtchedBorder());
					panelOrdenMedios.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DLUY3, FormSpec.NO_GROW)
						}));
					
					//---- lblCodigoOrden ----
					lblCodigoOrden.setText("C\u00f3digo:");
					panelOrdenMedios.add(lblCodigoOrden, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtCodigoOrden ----
					txtCodigoOrden.setEditable(false);
					panelOrdenMedios.add(txtCodigoOrden, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblEstadoOrden ----
					lblEstadoOrden.setText("Estado:");
					panelOrdenMedios.add(lblEstadoOrden, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtEstadoOrden ----
					txtEstadoOrden.setEditable(false);
					panelOrdenMedios.add(txtEstadoOrden, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblFechaInicioOrden ----
					lblFechaInicioOrden.setText("Fecha Inicio:");
					panelOrdenMedios.add(lblFechaInicioOrden, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtFechaInicioOrden ----
					txtFechaInicioOrden.setEditable(false);
					panelOrdenMedios.add(txtFechaInicioOrden, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblProveedorOrden ----
					lblProveedorOrden.setText("Proveedor:");
					panelOrdenMedios.add(lblProveedorOrden, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtProveedorOrden ----
					txtProveedorOrden.setEditable(false);
					panelOrdenMedios.add(txtProveedorOrden, cc.xywh(5, 9, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblClienteOrden ----
					lblClienteOrden.setText("Cliente:");
					panelOrdenMedios.add(lblClienteOrden, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtClienteOrden ----
					txtClienteOrden.setEditable(false);
					panelOrdenMedios.add(txtClienteOrden, cc.xywh(5, 13, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblPlanMedioOrden ----
					lblPlanMedioOrden.setText("Plan de Medios:");
					panelOrdenMedios.add(lblPlanMedioOrden, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtPlanMedioOrden ----
					txtPlanMedioOrden.setEditable(false);
					panelOrdenMedios.add(txtPlanMedioOrden, cc.xywh(5, 17, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblFechaFinOrden ----
					lblFechaFinOrden.setText("Fecha Fin:");
					panelOrdenMedios.add(lblFechaFinOrden, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtFechaFinOrden ----
					txtFechaFinOrden.setEditable(false);
					panelOrdenMedios.add(txtFechaFinOrden, cc.xywh(5, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCorporacionOrden ----
					lblCorporacionOrden.setText("Corporacion:");
					panelOrdenMedios.add(lblCorporacionOrden, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtCorporacionOrden ----
					txtCorporacionOrden.setEditable(false);
					panelOrdenMedios.add(txtCorporacionOrden, cc.xywh(5, 11, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblOficinaOrden ----
					lblOficinaOrden.setText("Oficina:");
					panelOrdenMedios.add(lblOficinaOrden, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtOficinaOrden ----
					txtOficinaOrden.setEditable(false);
					panelOrdenMedios.add(txtOficinaOrden, cc.xywh(5, 15, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblValorOrden ----
					lblValorOrden.setText("Valor:");
					panelOrdenMedios.add(lblValorOrden, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtValorOrden ----
					txtValorOrden.setEditable(false);
					panelOrdenMedios.add(txtValorOrden, cc.xywh(5, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				spOrdenMedios.setViewportView(panelOrdenMedios);
			}
			jideTabbedPane.addTab("Orden de Medios", spOrdenMedios);
			
		}
		add(jideTabbedPane, cc.xywh(13, 9, 5, 11));

		//---- scrollBarHorizontal ----
		scrollBarHorizontal.setOrientation(JScrollBar.HORIZONTAL);
		add(scrollBarHorizontal, cc.xywh(3, 19, 5, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarCorporacion.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCorporacion.setToolTipText("Buscar Corporaciones");
		
		btnBuscarCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCliente.setToolTipText("Buscar Clientes de Corporacion");
				
		//Seteo los scrollbar para el arbol
		treeOrdenMedios.setVerticalScrollbar(scrollBarVertical);
		treeOrdenMedios.setHorizontalScrollbar(scrollBarHorizontal);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblResultadoBusqueda;
	private JLabel lblCriteriosBusqueda;
	private JScrollBar scrollBarVertical;
	private JPanel panelCriterioBusqueda;
	private JCheckBox cbTodasOrdenes;
	private JLabel lblPorFechaInicio;
	private DateComboBox cmbPorFechaInicio;
	private JLabel lblPorFechaFin;
	private JCheckBox cbPorTipoProveedor;
	private JComboBox cmbPorTipoProveedor;
	private JCheckBox cbPorProveedor;
	private JComboBox cmbPorProveedor;
	private JCheckBox cbPorCliente;
	private JTextField txtPorCorporacion;
	private JButton btnBuscarCorporacion;
	private JTextField txtPorCliente;
	private JButton btnBuscarCliente;
	private DateComboBox cmbPorFechaFin;
	private JButton btnBuscar;
	private JideTabbedPane jideTabbedPane;
	private JScrollPane spCliente;
	private JPanel panelCliente;
	private JLabel lblTipoIdentificacionCliente;
	private JTextField txtTipoIdentificacionCliente;
	private JLabel lblIdentificacionCliente;
	private JLabel lblNombreLegalCliente;
	private JTextField txtIdentificacionCliente;
	private JTextField txtNombreLegalCliente;
	private JLabel lblRazonSocialCliente;
	private JTextField txtRazonSocialCliente;
	private JTextField txtRepresentanteCliente;
	private JLabel lblCorporacionCliente;
	private JTextField txtCorporacionCliente;
	private JLabel lblRepresentanteCliente;
	private JLabel lblFechaCreacionCliente;
	private JLabel lblEstadoCliente;
	private JLabel lblTipoCliente;
	private JTextField txtFechaCreacionCliente;
	private JTextField txtEstadoCliente;
	private JTextField txtTipoCliente;
	private JLabel lblObservacionesCliente;
	private JLabel lblTipoNegocioCliente;
	private JTextField txtTipoNegocioCliente;
	private JTextField txtObservacionesCliente;
	private JLabel lblCuentaContableCliente;
	private JTextField txtCtaContableCliente;
	private JScrollPane spProveedor;
	private JPanel panelProveedor;
	private JLabel lblCodigoClienteOficina;
	private JTextField txtCodigoClienteOficina;
	private JLabel lblEstadoClienteOficina;
	private JTextField txtEstadoClienteOficina;
	private JLabel lblDireccionClienteOficina;
	private JTextField txtDireccionClienteOficina;
	private JLabel lblTelefonoClienteOficina;
	private JTextField txtTelefonoClienteOficina;
	private JLabel lblEmailClienteOficina;
	private JTextField txtEmailClienteOficina;
	private JLabel lblFaxClienteOficina;
	private JTextField txtFaxClienteOficina;
	private JLabel lblFechaCreacionClienteOficina;
	private JTextField txtFechaCreacionClienteOficina;
	private JLabel lblMontoCreditoClienteOficina;
	private JTextField txtMontoCreditoClienteOficina;
	private JLabel lblCalificacionClienteOficina;
	private JTextField txtCalificacionClienteOficina;
	private JLabel lblMontoGarantiaClienteOficina;
	private JLabel lblObservacionClienteOficina;
	private JTextField txtMontoGarantiaClienteOficina;
	private JLabel lblDescripcionClienteOficina;
	private JTextField txtDescripcionClienteOficina;
	private JTextField txtObservacionClienteOficina;
	private JScrollPane spOrdenMedios;
	private JPanel panelOrdenMedios;
	private JLabel lblCodigoOrden;
	private JTextField txtCodigoOrden;
	private JLabel lblEstadoOrden;
	private JTextField txtEstadoOrden;
	private JLabel lblFechaInicioOrden;
	private JTextField txtFechaInicioOrden;
	private JLabel lblProveedorOrden;
	private JTextField txtProveedorOrden;
	private JLabel lblClienteOrden;
	private JTextField txtClienteOrden;
	private JLabel lblPlanMedioOrden;
	private JTextField txtPlanMedioOrden;
	private JLabel lblFechaFinOrden;
	private JTextField txtFechaFinOrden;
	private JLabel lblCorporacionOrden;
	private JTextField txtCorporacionOrden;
	private JLabel lblOficinaOrden;
	private JTextField txtOficinaOrden;
	private JLabel lblValorOrden;
	private JTextField txtValorOrden;
	private JScrollBar scrollBarHorizontal;
	private TreeNode rootNodeTreeOrdenMedios;
	private TreeBrowser treeOrdenMedios;
	private MySheetTableScrollPane tblOrdenMedioDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JCheckBox getCbPorCliente() {
		return cbPorCliente;
	}

	public void setCbPorCliente(JCheckBox cbPorCliente) {
		this.cbPorCliente = cbPorCliente;
	}

	public JCheckBox getCbPorProveedor() {
		return cbPorProveedor;
	}

	public void setCbPorProveedor(JCheckBox cbPorProveedor) {
		this.cbPorProveedor = cbPorProveedor;
	}

	public JCheckBox getCbPorTipoProveedor() {
		return cbPorTipoProveedor;
	}

	public void setCbPorTipoProveedor(JCheckBox cbPorTipoProveedor) {
		this.cbPorTipoProveedor = cbPorTipoProveedor;
	}

	public JCheckBox getCbTodasOrdenes() {
		return cbTodasOrdenes;
	}

	public void setCbTodasOrdenes(JCheckBox cbTodasOrdenes) {
		this.cbTodasOrdenes = cbTodasOrdenes;
	}

	public DateComboBox getCmbPorFechaFin() {
		return cmbPorFechaFin;
	}

	public void setCmbPorFechaFin(DateComboBox cmbPorFechaFin) {
		this.cmbPorFechaFin = cmbPorFechaFin;
	}

	public DateComboBox getCmbPorFechaInicio() {
		return cmbPorFechaInicio;
	}

	public void setCmbPorFechaInicio(DateComboBox cmbPorFechaInicio) {
		this.cmbPorFechaInicio = cmbPorFechaInicio;
	}

	public JComboBox getCmbPorProveedor() {
		return cmbPorProveedor;
	}

	public void setCmbPorProveedor(JComboBox cmbPorProveedor) {
		this.cmbPorProveedor = cmbPorProveedor;
	}

	public JComboBox getCmbPorTipoProveedor() {
		return cmbPorTipoProveedor;
	}

	public void setCmbPorTipoProveedor(JComboBox cmbPorTipoProveedor) {
		this.cmbPorTipoProveedor = cmbPorTipoProveedor;
	}

	public JTextField getTxtClienteOrden() {
		return txtClienteOrden;
	}

	public void setTxtClienteOrden(JTextField txtClienteOrden) {
		this.txtClienteOrden = txtClienteOrden;
	}

	public JTextField getTxtCodigoOrden() {
		return txtCodigoOrden;
	}

	public void setTxtCodigoOrden(JTextField txtCodigoOrden) {
		this.txtCodigoOrden = txtCodigoOrden;
	}

	public JTextField getTxtCorporacionCliente() {
		return txtCorporacionCliente;
	}

	public void setTxtCorporacionCliente(JTextField txtCorporacionCliente) {
		this.txtCorporacionCliente = txtCorporacionCliente;
	}

	public JTextField getTxtCorporacionOrden() {
		return txtCorporacionOrden;
	}

	public void setTxtCorporacionOrden(JTextField txtCorporacionOrden) {
		this.txtCorporacionOrden = txtCorporacionOrden;
	}

	public JTextField getTxtCtaContableCliente() {
		return txtCtaContableCliente;
	}

	public void setTxtCtaContableCliente(JTextField txtCtaContableCliente) {
		this.txtCtaContableCliente = txtCtaContableCliente;
	}

	public JTextField getTxtEstadoCliente() {
		return txtEstadoCliente;
	}

	public void setTxtEstadoCliente(JTextField txtEstadoCliente) {
		this.txtEstadoCliente = txtEstadoCliente;
	}

	public JTextField getTxtEstadoOrden() {
		return txtEstadoOrden;
	}

	public void setTxtEstadoOrden(JTextField txtEstadoOrden) {
		this.txtEstadoOrden = txtEstadoOrden;
	}

	public JTextField getTxtFechaCreacionCliente() {
		return txtFechaCreacionCliente;
	}

	public void setTxtFechaCreacionCliente(JTextField txtFechaCreacionCliente) {
		this.txtFechaCreacionCliente = txtFechaCreacionCliente;
	}

	public JTextField getTxtFechaFinOrden() {
		return txtFechaFinOrden;
	}

	public void setTxtFechaFinOrden(JTextField txtFechaFinOrden) {
		this.txtFechaFinOrden = txtFechaFinOrden;
	}

	public JTextField getTxtFechaInicioOrden() {
		return txtFechaInicioOrden;
	}

	public void setTxtFechaInicioOrden(JTextField txtFechaInicioOrden) {
		this.txtFechaInicioOrden = txtFechaInicioOrden;
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

	public JTextField getTxtObservacionesCliente() {
		return txtObservacionesCliente;
	}

	public void setTxtObservacionesCliente(JTextField txtObservacionesCliente) {
		this.txtObservacionesCliente = txtObservacionesCliente;
	}

	public JTextField getTxtOficinaOrden() {
		return txtOficinaOrden;
	}

	public void setTxtOficinaOrden(JTextField txtOficinaOrden) {
		this.txtOficinaOrden = txtOficinaOrden;
	}

	public JTextField getTxtPlanMedioOrden() {
		return txtPlanMedioOrden;
	}

	public void setTxtPlanMedioOrden(JTextField txtPlanMedioOrden) {
		this.txtPlanMedioOrden = txtPlanMedioOrden;
	}

	public JTextField getTxtPorCliente() {
		return txtPorCliente;
	}

	public void setTxtPorCliente(JTextField txtPorCliente) {
		this.txtPorCliente = txtPorCliente;
	}

	public JTextField getTxtPorCorporacion() {
		return txtPorCorporacion;
	}

	public void setTxtPorCorporacion(JTextField txtPorCorporacion) {
		this.txtPorCorporacion = txtPorCorporacion;
	}

	public JTextField getTxtProveedorOrden() {
		return txtProveedorOrden;
	}

	public void setTxtProveedorOrden(JTextField txtProveedorOrden) {
		this.txtProveedorOrden = txtProveedorOrden;
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

	public JTextField getTxtTipoCliente() {
		return txtTipoCliente;
	}

	public void setTxtTipoCliente(JTextField txtTipoCliente) {
		this.txtTipoCliente = txtTipoCliente;
	}

	public JTextField getTxtTipoIdentificacionCliente() {
		return txtTipoIdentificacionCliente;
	}

	public void setTxtTipoIdentificacionCliente(
			JTextField txtTipoIdentificacionCliente) {
		this.txtTipoIdentificacionCliente = txtTipoIdentificacionCliente;
	}

	public JTextField getTxtTipoNegocioCliente() {
		return txtTipoNegocioCliente;
	}

	public void setTxtTipoNegocioCliente(JTextField txtTipoNegocioCliente) {
		this.txtTipoNegocioCliente = txtTipoNegocioCliente;
	}

	public JTextField getTxtValorOrden() {
		return txtValorOrden;
	}

	public void setTxtValorOrden(JTextField txtValorOrden) {
		this.txtValorOrden = txtValorOrden;
	}

	public TreeNode getRootNodeTreeOrdenMedios() {
		return rootNodeTreeOrdenMedios;
	}

	public void setRootNodeTreeOrdenMedios(TreeNode rootNodeTreeOrdenMedios) {
		this.rootNodeTreeOrdenMedios = rootNodeTreeOrdenMedios;
	}

	public TreeBrowser getTreeOrdenMedios() {
		return treeOrdenMedios;
	}

	public void setTreeOrdenMedios(TreeBrowser treeOrdenMedios) {
		this.treeOrdenMedios = treeOrdenMedios;
	}

	public JideTabbedPane getJideTabbedPane() {
		return jideTabbedPane;
	}

	public void setJideTabbedPane(JideTabbedPane jideTabbedPane) {
		this.jideTabbedPane = jideTabbedPane;
	}

	public MySheetTableScrollPane getTblOrdenMedioDetalle() {
		return tblOrdenMedioDetalle;
	}

	public void setTblOrdenMedioDetalle(MySheetTableScrollPane tblOrdenMedioDetalle) {
		this.tblOrdenMedioDetalle = tblOrdenMedioDetalle;
	}

	public JPanel getPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(JPanel panelCliente) {
		this.panelCliente = panelCliente;
	}

	public JPanel getPanelCriterioBusqueda() {
		return panelCriterioBusqueda;
	}

	public void setPanelCriterioBusqueda(JPanel panelCriterioBusqueda) {
		this.panelCriterioBusqueda = panelCriterioBusqueda;
	}

	public JPanel getPanelOrdenMedios() {
		return panelOrdenMedios;
	}

	public void setPanelOrdenMedios(JPanel panelOrdenMedios) {
		this.panelOrdenMedios = panelOrdenMedios;
	}

	public JPanel getPanelProveedor() {
		return panelProveedor;
	}

	public void setPanelProveedor(JPanel panelProveedor) {
		this.panelProveedor = panelProveedor;
	}

	public JTextField getTxtCalificacionClienteOficina() {
		return txtCalificacionClienteOficina;
	}

	public void setTxtCalificacionClienteOficina(JTextField txtCalificacionClienteOficina) {
		this.txtCalificacionClienteOficina = txtCalificacionClienteOficina;
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

	public JTextField getTxtDireccionClienteOficina() {
		return txtDireccionClienteOficina;
	}

	public void setTxtDireccionClienteOficina(JTextField txtDireccionClienteOficina) {
		this.txtDireccionClienteOficina = txtDireccionClienteOficina;
	}

	public JTextField getTxtEmailClienteOficina() {
		return txtEmailClienteOficina;
	}

	public void setTxtEmailClienteOficina(JTextField txtEmailClienteOficina) {
		this.txtEmailClienteOficina = txtEmailClienteOficina;
	}

	public JTextField getTxtEstadoClienteOficina() {
		return txtEstadoClienteOficina;
	}

	public void setTxtEstadoClienteOficina(JTextField txtEstadoClienteOficina) {
		this.txtEstadoClienteOficina = txtEstadoClienteOficina;
	}

	public JTextField getTxtFaxClienteOficina() {
		return txtFaxClienteOficina;
	}

	public void setTxtFaxClienteOficina(JTextField txtFaxClienteOficina) {
		this.txtFaxClienteOficina = txtFaxClienteOficina;
	}

	public JTextField getTxtFechaCreacionClienteOficina() {
		return txtFechaCreacionClienteOficina;
	}

	public void setTxtFechaCreacionClienteOficina(
			JTextField txtFechaCreacionClienteOficina) {
		this.txtFechaCreacionClienteOficina = txtFechaCreacionClienteOficina;
	}

	public JTextField getTxtMontoCreditoClienteOficina() {
		return txtMontoCreditoClienteOficina;
	}

	public void setTxtMontoCreditoClienteOficina(
			JTextField txtMontoCreditoClienteOficina) {
		this.txtMontoCreditoClienteOficina = txtMontoCreditoClienteOficina;
	}

	public JTextField getTxtMontoGarantiaClienteOficina() {
		return txtMontoGarantiaClienteOficina;
	}

	public void setTxtMontoGarantiaClienteOficina(
			JTextField txtMontoGarantiaClienteOficina) {
		this.txtMontoGarantiaClienteOficina = txtMontoGarantiaClienteOficina;
	}

	public JTextField getTxtObservacionClienteOficina() {
		return txtObservacionClienteOficina;
	}

	public void setTxtObservacionClienteOficina(
			JTextField txtObservacionClienteOficina) {
		this.txtObservacionClienteOficina = txtObservacionClienteOficina;
	}

	public JTextField getTxtTelefonoClienteOficina() {
		return txtTelefonoClienteOficina;
	}

	public void setTxtTelefonoClienteOficina(JTextField txtTelefonoClienteOficina) {
		this.txtTelefonoClienteOficina = txtTelefonoClienteOficina;
	}	
}
