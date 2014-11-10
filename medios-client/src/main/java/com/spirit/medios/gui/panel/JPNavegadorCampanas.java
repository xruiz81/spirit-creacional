package com.spirit.medios.gui.panel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
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
import com.spirit.util.tree.TreeBrowser;
import com.spirit.util.tree.TreeNode;

public abstract class JPNavegadorCampanas extends ReportModelImpl {

	public JPNavegadorCampanas() {
		initComponents();
		setName("Navegador de Campañas");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		sppNavegadorCampana = new JSplitPane();
		panelResultadosBusqueda = new JPanel();
		lblResultadoBusqueda = compFactory.createTitle("Resultados de la B\u00fasqueda");
		scrollBarVertical = new JScrollBar();
		scrollBarHorizontal = new JScrollBar();
		panelCriteriosBusqueda = new JPanel();
		lblCriteriosBusqueda = compFactory.createTitle("Criterios de B\u00fasqueda");
		panelCriterioBusqueda = new JPanel();
		cbTodasCampanas = new JCheckBox();
		cbPorCliente = new JCheckBox();
		txtPorCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		txtPorCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbPorEstado = new JCheckBox();
		cbPorResponsableOrdenTrabajo = new JCheckBox();
		txtPorResponsableOrdenTrabajo = new JTextField();
		btnBuscarResponsableOrdenTrabajo = new JButton();
		cmbPorEstado = new JComboBox();
		cbPorFechas = new JCheckBox();
		cmbFechaInicio = new DateComboBox();
		cmbFechaFin = new DateComboBox();
		btnBuscar = new JButton();
		jideTabbedPane1 = new JideTabbedPane();
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
		txtTipoNegocioCliente = new JTextField();
		lblTipoNegocioCliente = new JLabel();
		txtObservacionesCliente = new JTextField();
		lblCuentaContableCliente = new JLabel();
		txtCtaContableCliente = new JTextField();
		spCampana = new JScrollPane();
		panelCampana = new JPanel();
		lblCodigoCampana = new JLabel();
		txtCodigoCampana = new JTextField();
		lblEstadoCampana = new JLabel();
		txtEstadoCampana = new JTextField();
		lblNombreCampana = new JLabel();
		txtNombreCampana = new JTextField();
		lblCorporacionCampana = new JLabel();
		txtCorporacionCampana = new JTextField();
		lblClienteCampana = new JLabel();
		txtClienteCampana = new JTextField();
		lblFechaInicioCampana = new JLabel();
		txtFechaInicioCampana = new JTextField();
		lblPublicoObjetivoCampana = new JLabel();
		txtPublicoObjetivoCampana = new JTextField();
		lblObservacionCampana = new JLabel();
		txtObservacionCampana = new JTextField();
		spOrdenTrabajo = new JScrollPane();
		panelOrdenTrabajo = new JPanel();
		lblCodigoOrdenTrabajo = new JLabel();
		lblFechaCreacionOrdenTrabajo = new JLabel();
		txtFechaCreacionOrdenTrabajo = new JTextField();
		txtCodigoOrdenTrabajo = new JTextField();
		txtDescripcionOrdenTrabajo = new JTextField();
		lblCorporacionOrdenTrabajo = new JLabel();
		lblDescripcionOrdenTrabajo = new JLabel();
		txtCorporacionOrdenTrabajo = new JTextField();
		lblClienteOrdenTrabajo = new JLabel();
		txtClienteOrdenTrabajo = new JTextField();
		lblOficinaOrdenTrabajo = new JLabel();
		txtOficinaOrdenTrabajo = new JTextField();
		lblCampanaOrdenTrabajo = new JLabel();
		txtCampanaOrdenTrabajo = new JTextField();
		lblAsignadoAOrdenTrabajo = new JLabel();
		txtAsignadoAOrdenTrabajo = new JTextField();
		lblEstadoOrdenTrabajo = new JLabel();
		txtEstadoOrdenTrabajo = new JTextField();
		lblFechaLimiteOrdenTrabajo = new JLabel();
		txtFechaLimiteOrdenTrabajo = new JTextField();
		lblFechaEntregaOrdenTrabajo = new JLabel();
		txtFechaEntregaOrdenTrabajo = new JTextField();
		txtUrlPropuestaOrdenTrabajo = new JTextField();
		lblObservacionOrdenTrabajo = new JLabel();
		lblUrlPropuestaOrdenTrabajo = new JLabel();
		scrollPaneObservacionOrdenTrabajo = new JScrollPane();
		txtObservacionOrdenTrabajo = new JTextArea();
		spOrdenTrabajoDetalle = new JScrollPane();
		panelOrdenTrabajoDetalle = new JPanel();
		lblEstadoOrdenTrabajoDetalle = new JLabel();
		txtEstadoOrdenTrabajoDetalle = new JTextField();
		lblTipoOrdenTrabajoDetalle = new JLabel();
		txtTipoOrdenTrabajoDetalle = new JTextField();
		lblSubTipoOrdenTrabajoDetalle = new JLabel();
		txtSubTipoOrdenTrabajoDetalle = new JTextField();
		lblEquipoOrdenTrabajoDetalle = new JLabel();
		txtEquipoOrdenTrabajoDetalle = new JTextField();
		lblAsignadoAOrdenTrabajoDetalle = new JLabel();
		txtAsignadoOrdenTrabajoDetalle = new JTextField();
		lblFechaLimiteOrdenTrabajoDetalle = new JLabel();
		txtFechaLimiteOrdenTrabajoDetalle = new JTextField();
		lblFechaEntregaOrdenTrabajoDetalle = new JLabel();
		txtFechaEntregaOrdenTrabajoDetalle = new JTextField();
		lblUrlDescripcionOrdenTrabajoDetalle = new JLabel();
		txtUrlDescripcionOrdenTrabajoDetalle = new JTextField();
		lblUrlPropuestaOrdenTrabajoDetalle = new JLabel();
		txtUrlPropuestaOrdenTrabajoDetalle = new JTextField();
		lblDescripcionOrdenTrabajoDetalle = new JLabel();
		scrollPaneOrdenTrabajoDetalle = new JScrollPane();
		txtDescripcionOrdenTrabajoDetalle = new JTextArea();
		spPresupuesto = new JScrollPane();
		panelPresupuesto = new JPanel();
		lblCodigoPresupuesto = new JLabel();
		txtCodigoPresupuesto = new JTextField();
		lblEstadoPresupuesto = new JLabel();
		txtEstadoPresupuesto = new JTextField();
		lblConceptoPresupuesto = new JLabel();
		txtConceptoPresupuesto = new JTextField();
		lblCorporacionPresupuesto = new JLabel();
		txtCorporacionPresupuesto = new JTextField();
		lblClientePresupuesto = new JLabel();
		txtClientePresupuesto = new JTextField();
		lblClienteOficinaPresupuesto = new JLabel();
		txtOficinaPresupuesto = new JTextField();
		lblTipoOrdenPresupuesto = new JLabel();
		txtTipoOrdenPresupuesto = new JTextField();
		lblOrdenTrabajoPresupuesto = new JLabel();
		txtOrdenTrabajoPresupuesto = new JTextField();
		lblOrdenTrabajoDetallePresupuesto = new JLabel();
		txtOrdenTrabajoDetallePresupuesto = new JTextField();
		lblFechaInicialPresupuesto = new JLabel();
		lblFechaFinalPresupuesto = new JLabel();
		txtFechaFinalPresupuesto = new JTextField();
		txtFechaInicialPresupuesto = new JTextField();
		lblFechaValidezPresupuesto = new JLabel();
		txtFechaValidezPresupuesto = new JTextField();
		lblModificacionPresupuesto = new JLabel();
		txtModificacionPresupuesto = new JTextField();
		lblCabeceraPresupuesto = new JLabel();
		scrollPaneCabeceraPresupuesto = new JScrollPane();
		txtCabeceraPresupuesto = new JTextArea();
		spPlanMedio = new JScrollPane();
		panelPlanMedios = new JPanel();
		lblcodigoPlanMedios = new JLabel();
		txtCodigoPlanMedios = new JTextField();
		lblEstadoPlanMedios = new JLabel();
		txtEstadoPlanMedios = new JTextField();
		lblConceptoPlanMedios = new JLabel();
		txtConceptoPlanMedios = new JTextField();
		lblTipoProveedorPlanMedios = new JLabel();
		txtTipoProveedorPlanMedios = new JTextField();
		lblCorporacionPlanMedios = new JLabel();
		txtCorporacionPlanMedios = new JTextField();
		lblClientePlanMedios = new JLabel();
		txtClientePlanMedios = new JTextField();
		lblOficinaPlanMedios = new JLabel();
		txtOficinaPlanMedios = new JTextField();
		lblTipoOrdenPlanMedios = new JLabel();
		txtTipoOrdenPlanMedios = new JTextField();
		lblValorPlanMedios = new JLabel();
		txtValorPlanMedios = new JTextField();
		lblOrdenTrabajoPlanMedios = new JLabel();
		txtOrdenTrabajoPlanMedios = new JTextField();
		lblOrdenTrabajoDetallePlanMedios = new JLabel();
		lblFechaInicialPlanMedios = new JLabel();
		txtFechaInicialPlanMedios = new JTextField();
		lblFechaFinalPlanMedios = new JLabel();
		txtFechaFinalPlanMedios = new JTextField();
		txtOrdenTrabajoDetallePlanMedios = new JTextField();
		CellConstraints cc = new CellConstraints();
		rootNodeTreeCampana = new TreeNode(null, "", null, "images/icons/database.png", "images/icons/database.png", 2, 0);
		treeCampana = new TreeBrowser(getRootNodeTreeCampana(),"",0);

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
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
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

		//======== sppNavegadorCampana ========
		{
			sppNavegadorCampana.setDividerLocation(400);
			sppNavegadorCampana.setDividerSize(5);
			sppNavegadorCampana.setLastDividerLocation(628);
			sppNavegadorCampana.setPreferredSize(new Dimension(407, 300));			
			
			//======== panelResultadosBusqueda ========
			{
				panelResultadosBusqueda.setPreferredSize(new Dimension(200, 111));
				panelResultadosBusqueda.setMinimumSize(new Dimension(200, 111));
				panelResultadosBusqueda.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				panelResultadosBusqueda.add(lblResultadoBusqueda, cc.xywh(1, 1, 3, 1));
				
				//---- treeCampana ----
				treeCampana.setPreferredSize(new Dimension(100, 64));
				treeCampana.setMaximumSize(new Dimension(100, 64));
				panelResultadosBusqueda.add(treeCampana, cc.xywh(1, 3, 5, 5));
				panelResultadosBusqueda.add(scrollBarVertical, cc.xywh(7, 3, 1, 5));
				
				//---- scrollBarHorizontal ----
				scrollBarHorizontal.setOrientation(JScrollBar.HORIZONTAL);
				panelResultadosBusqueda.add(scrollBarHorizontal, cc.xywh(1, 9, 5, 1));
			}
			sppNavegadorCampana.setLeftComponent(panelResultadosBusqueda);
			
			//======== panelCriteriosBusqueda ========
			{
				panelCriteriosBusqueda.setMinimumSize(new Dimension(100, 272));
				panelCriteriosBusqueda.setPreferredSize(new Dimension(200, 600));
				panelCriteriosBusqueda.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(120)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				panelCriteriosBusqueda.add(lblCriteriosBusqueda, cc.xy(1, 1));
				
				//======== panelCriterioBusqueda ========
				{
					panelCriterioBusqueda.setBorder(new TitledBorder(new EtchedBorder(), ""));
					panelCriterioBusqueda.setLayout(new FormLayout(
						ColumnSpec.decodeSpecs("default, default:grow, 3dlu, default:grow, default"),
						new RowSpec[] {
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW)
						}));
					
					//---- cbTodasCampanas ----
					cbTodasCampanas.setText("Todas las Campa\u00f1as");
					panelCriterioBusqueda.add(cbTodasCampanas, cc.xy(1, 1));
					
					//---- cbPorCliente ----
					cbPorCliente.setText("Por Cliente");
					panelCriterioBusqueda.add(cbPorCliente, cc.xy(1, 3));
					
					//---- txtPorCorporacion ----
					txtPorCorporacion.setEditable(false);
					panelCriterioBusqueda.add(txtPorCorporacion, cc.xywh(2, 3, 3, 1));
					panelCriterioBusqueda.add(btnBuscarCorporacion, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- txtPorCliente ----
					txtPorCliente.setEditable(false);
					panelCriterioBusqueda.add(txtPorCliente, cc.xywh(2, 5, 3, 1));
					panelCriterioBusqueda.add(btnBuscarCliente, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- cbPorEstado ----
					cbPorEstado.setText("Por Estado");
					panelCriterioBusqueda.add(cbPorEstado, cc.xy(1, 7));
					
					//---- cbPorResponsableOrdenTrabajo ----
					cbPorResponsableOrdenTrabajo.setText("Por Responsable de Orden Trabajo");
					panelCriterioBusqueda.add(cbPorResponsableOrdenTrabajo, cc.xy(1, 9));
					
					//---- txtPorResponsableOrdenTrabajo ----
					txtPorResponsableOrdenTrabajo.setEditable(false);
					panelCriterioBusqueda.add(txtPorResponsableOrdenTrabajo, cc.xywh(2, 9, 3, 1));
					panelCriterioBusqueda.add(btnBuscarResponsableOrdenTrabajo, cc.xywh(5, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelCriterioBusqueda.add(cmbPorEstado, cc.xywh(2, 7, 3, 1));
					
					//---- cbPorFechas ----
					cbPorFechas.setText("Por Fechas");
					panelCriterioBusqueda.add(cbPorFechas, cc.xy(1, 11));
					panelCriterioBusqueda.add(cmbFechaInicio, cc.xy(2, 11));
					panelCriterioBusqueda.add(cmbFechaFin, cc.xy(4, 11));
				}
				panelCriteriosBusqueda.add(panelCriterioBusqueda, cc.xywh(1, 3, 5, 1));
				
				//---- btnBuscar ----
				btnBuscar.setText("Buscar");
				panelCriteriosBusqueda.add(btnBuscar, cc.xy(1, 5));
				
				//======== jideTabbedPane1 ========
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
									new ColumnSpec(Sizes.dluX(120)),
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
									new RowSpec(Sizes.DLUY3)
								}));
							
							//---- lblTipoIdentificacionCliente ----
							lblTipoIdentificacionCliente.setText("Tipo Identificaci\u00f3n:");
							panelCliente.add(lblTipoIdentificacionCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtTipoIdentificacionCliente ----
							txtTipoIdentificacionCliente.setEditable(false);
							panelCliente.add(txtTipoIdentificacionCliente, cc.xy(5, 3));
							
							//---- lblIdentificacionCliente ----
							lblIdentificacionCliente.setText("Identificaci\u00f3n:");
							panelCliente.add(lblIdentificacionCliente, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblNombreLegalCliente ----
							lblNombreLegalCliente.setText("Nombre Legal:");
							panelCliente.add(lblNombreLegalCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtIdentificacionCliente ----
							txtIdentificacionCliente.setEditable(false);
							panelCliente.add(txtIdentificacionCliente, cc.xy(9, 3));
							
							//---- txtNombreLegalCliente ----
							txtNombreLegalCliente.setEditable(false);
							panelCliente.add(txtNombreLegalCliente, cc.xywh(5, 5, 5, 1));
							
							//---- lblRazonSocialCliente ----
							lblRazonSocialCliente.setText("Raz\u00f3n Social:");
							panelCliente.add(lblRazonSocialCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtRazonSocialCliente ----
							txtRazonSocialCliente.setEditable(false);
							panelCliente.add(txtRazonSocialCliente, cc.xywh(5, 7, 5, 1));
							
							//---- txtRepresentanteCliente ----
							txtRepresentanteCliente.setEditable(false);
							panelCliente.add(txtRepresentanteCliente, cc.xywh(5, 9, 5, 1));
							
							//---- lblCorporacionCliente ----
							lblCorporacionCliente.setText("Corporaci\u00f3n");
							panelCliente.add(lblCorporacionCliente, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCorporacionCliente ----
							txtCorporacionCliente.setEditable(false);
							panelCliente.add(txtCorporacionCliente, cc.xywh(5, 11, 5, 1));
							
							//---- lblRepresentanteCliente ----
							lblRepresentanteCliente.setText("Representante:");
							panelCliente.add(lblRepresentanteCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblFechaCreacionCliente ----
							lblFechaCreacionCliente.setText("Fecha de Creaci\u00f3n: ");
							panelCliente.add(lblFechaCreacionCliente, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblEstadoCliente ----
							lblEstadoCliente.setText("Estado:");
							panelCliente.add(lblEstadoCliente, cc.xywh(7, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblTipoCliente ----
							lblTipoCliente.setText("Tipo de Cliente:");
							panelCliente.add(lblTipoCliente, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaCreacionCliente ----
							txtFechaCreacionCliente.setEditable(false);
							panelCliente.add(txtFechaCreacionCliente, cc.xy(5, 13));
							
							//---- txtEstadoCliente ----
							txtEstadoCliente.setEditable(false);
							panelCliente.add(txtEstadoCliente, cc.xy(9, 13));
							
							//---- txtTipoCliente ----
							txtTipoCliente.setEditable(false);
							panelCliente.add(txtTipoCliente, cc.xywh(5, 15, 5, 1));
							
							//---- lblObservacionesCliente ----
							lblObservacionesCliente.setText("Observaciones:");
							panelCliente.add(lblObservacionesCliente, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtTipoNegocioCliente ----
							txtTipoNegocioCliente.setEditable(false);
							panelCliente.add(txtTipoNegocioCliente, cc.xywh(5, 17, 5, 1));
							
							//---- lblTipoNegocioCliente ----
							lblTipoNegocioCliente.setText("Tipo de Negocio:");
							panelCliente.add(lblTipoNegocioCliente, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtObservacionesCliente ----
							txtObservacionesCliente.setEditable(false);
							panelCliente.add(txtObservacionesCliente, cc.xywh(5, 19, 5, 1));
							
							//---- lblCuentaContableCliente ----
							lblCuentaContableCliente.setText("Cta. Contable:");
							panelCliente.add(lblCuentaContableCliente, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCtaContableCliente ----
							txtCtaContableCliente.setEditable(false);
							panelCliente.add(txtCtaContableCliente, cc.xywh(5, 21, 5, 1));
						}
						spCliente.setViewportView(panelCliente);
					}
					jideTabbedPane1.addTab("Cliente", spCliente);
					
					
					//======== spCampana ========
					{
						
						//======== panelCampana ========
						{
							panelCampana.setBorder(new EtchedBorder());
							panelCampana.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX3),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(140)),
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
									FormFactory.PREF_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY3)
								}));
							
							//---- lblCodigoCampana ----
							lblCodigoCampana.setText("C\u00f3digo:");
							panelCampana.add(lblCodigoCampana, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCodigoCampana ----
							txtCodigoCampana.setEditable(false);
							panelCampana.add(txtCodigoCampana, cc.xy(5, 3));
							
							//---- lblEstadoCampana ----
							lblEstadoCampana.setText("Estado:");
							panelCampana.add(lblEstadoCampana, cc.xy(9, 3));
							
							//---- txtEstadoCampana ----
							txtEstadoCampana.setEditable(false);
							panelCampana.add(txtEstadoCampana, cc.xy(11, 3));
							
							//---- lblNombreCampana ----
							lblNombreCampana.setText("Nombre:");
							panelCampana.add(lblNombreCampana, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtNombreCampana ----
							txtNombreCampana.setEditable(false);
							panelCampana.add(txtNombreCampana, cc.xywh(5, 5, 7, 1));
							
							//---- lblCorporacionCampana ----
							lblCorporacionCampana.setText("Corporaci\u00f3n:");
							panelCampana.add(lblCorporacionCampana, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCorporacionCampana ----
							txtCorporacionCampana.setEditable(false);
							panelCampana.add(txtCorporacionCampana, cc.xywh(5, 7, 7, 1));
							
							//---- lblClienteCampana ----
							lblClienteCampana.setText("Cliente:");
							panelCampana.add(lblClienteCampana, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtClienteCampana ----
							txtClienteCampana.setEditable(false);
							panelCampana.add(txtClienteCampana, cc.xywh(5, 9, 7, 1));
							
							//---- lblFechaInicioCampana ----
							lblFechaInicioCampana.setText("Fecha Inicio:");
							panelCampana.add(lblFechaInicioCampana, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaInicioCampana ----
							txtFechaInicioCampana.setEditable(false);
							panelCampana.add(txtFechaInicioCampana, cc.xy(5, 11));
							
							//---- lblPublicoObjetivoCampana ----
							lblPublicoObjetivoCampana.setText("P\u00fablico Objetivo:");
							panelCampana.add(lblPublicoObjetivoCampana, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtPublicoObjetivoCampana ----
							txtPublicoObjetivoCampana.setEditable(false);
							panelCampana.add(txtPublicoObjetivoCampana, cc.xywh(5, 13, 7, 1));
							
							//---- lblObservacionCampana ----
							lblObservacionCampana.setText("Observaci\u00f3n");
							panelCampana.add(lblObservacionCampana, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtObservacionCampana ----
							txtObservacionCampana.setEditable(false);
							panelCampana.add(txtObservacionCampana, cc.xywh(5, 15, 7, 1));
						}
						spCampana.setViewportView(panelCampana);
					}
					jideTabbedPane1.addTab("Campa\u00f1a", spCampana);
					
					
					//======== spOrdenTrabajo ========
					{
						
						//======== panelOrdenTrabajo ========
						{
							panelOrdenTrabajo.setBorder(new EtchedBorder());
							panelOrdenTrabajo.setLayout(new FormLayout(
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
									new ColumnSpec(Sizes.dluX(95)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.DLUX3)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY3),
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
									new RowSpec(Sizes.dluY(12)),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.dluY(24)),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY3)
								}));
							
							//---- lblCodigoOrdenTrabajo ----
							lblCodigoOrdenTrabajo.setText("C\u00f3digo:");
							panelOrdenTrabajo.add(lblCodigoOrdenTrabajo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblFechaCreacionOrdenTrabajo ----
							lblFechaCreacionOrdenTrabajo.setText("Fecha Creaci\u00f3n:");
							panelOrdenTrabajo.add(lblFechaCreacionOrdenTrabajo, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaCreacionOrdenTrabajo ----
							txtFechaCreacionOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtFechaCreacionOrdenTrabajo, cc.xy(11, 3));
							
							//---- txtCodigoOrdenTrabajo ----
							txtCodigoOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtCodigoOrdenTrabajo, cc.xy(5, 3));
							
							//---- txtDescripcionOrdenTrabajo ----
							txtDescripcionOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtDescripcionOrdenTrabajo, cc.xywh(5, 5, 9, 1));
							
							//---- lblCorporacionOrdenTrabajo ----
							lblCorporacionOrdenTrabajo.setText("Corporaci\u00f3n:");
							panelOrdenTrabajo.add(lblCorporacionOrdenTrabajo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblDescripcionOrdenTrabajo ----
							lblDescripcionOrdenTrabajo.setText("Descripci\u00f3n:");
							panelOrdenTrabajo.add(lblDescripcionOrdenTrabajo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCorporacionOrdenTrabajo ----
							txtCorporacionOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtCorporacionOrdenTrabajo, cc.xywh(5, 7, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblClienteOrdenTrabajo ----
							lblClienteOrdenTrabajo.setText("Cliente:");
							panelOrdenTrabajo.add(lblClienteOrdenTrabajo, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtClienteOrdenTrabajo ----
							txtClienteOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtClienteOrdenTrabajo, cc.xywh(5, 9, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblOficinaOrdenTrabajo ----
							lblOficinaOrdenTrabajo.setText("Oficina:");
							panelOrdenTrabajo.add(lblOficinaOrdenTrabajo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtOficinaOrdenTrabajo ----
							txtOficinaOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtOficinaOrdenTrabajo, cc.xywh(5, 11, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblCampanaOrdenTrabajo ----
							lblCampanaOrdenTrabajo.setText("Campa\u00f1a:");
							panelOrdenTrabajo.add(lblCampanaOrdenTrabajo, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtCampanaOrdenTrabajo ----
							txtCampanaOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtCampanaOrdenTrabajo, cc.xywh(5, 13, 7, 1));
							
							//---- lblAsignadoAOrdenTrabajo ----
							lblAsignadoAOrdenTrabajo.setText("Ejecutivo(a):");
							panelOrdenTrabajo.add(lblAsignadoAOrdenTrabajo, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtAsignadoAOrdenTrabajo ----
							txtAsignadoAOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtAsignadoAOrdenTrabajo, cc.xywh(5, 15, 7, 1));
							
							//---- lblEstadoOrdenTrabajo ----
							lblEstadoOrdenTrabajo.setText("Estado:");
							panelOrdenTrabajo.add(lblEstadoOrdenTrabajo, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtEstadoOrdenTrabajo ----
							txtEstadoOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtEstadoOrdenTrabajo, cc.xy(5, 17));
							
							//---- lblFechaLimiteOrdenTrabajo ----
							lblFechaLimiteOrdenTrabajo.setText("Fecha L\u00edmite:");
							panelOrdenTrabajo.add(lblFechaLimiteOrdenTrabajo, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaLimiteOrdenTrabajo ----
							txtFechaLimiteOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtFechaLimiteOrdenTrabajo, cc.xy(5, 19));
							
							//---- lblFechaEntregaOrdenTrabajo ----
							lblFechaEntregaOrdenTrabajo.setText("Fecha de Entrega:");
							panelOrdenTrabajo.add(lblFechaEntregaOrdenTrabajo, cc.xywh(9, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaEntregaOrdenTrabajo ----
							txtFechaEntregaOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtFechaEntregaOrdenTrabajo, cc.xy(11, 19));
							
							//---- txtUrlPropuestaOrdenTrabajo ----
							txtUrlPropuestaOrdenTrabajo.setEditable(false);
							panelOrdenTrabajo.add(txtUrlPropuestaOrdenTrabajo, cc.xywh(5, 21, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblObservacionOrdenTrabajo ----
							lblObservacionOrdenTrabajo.setText("Observaci\u00f3n:");
							panelOrdenTrabajo.add(lblObservacionOrdenTrabajo, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblUrlPropuestaOrdenTrabajo ----
							lblUrlPropuestaOrdenTrabajo.setText("URL Propuesta:");
							panelOrdenTrabajo.add(lblUrlPropuestaOrdenTrabajo, cc.xywh(3, 21, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
							
							//======== scrollPaneObservacionOrdenTrabajo ========
							{
								
								//---- txtObservacionOrdenTrabajo ----
								txtObservacionOrdenTrabajo.setLineWrap(true);
								txtObservacionOrdenTrabajo.setEditable(false);
								scrollPaneObservacionOrdenTrabajo.setViewportView(txtObservacionOrdenTrabajo);
							}
							panelOrdenTrabajo.add(scrollPaneObservacionOrdenTrabajo, cc.xywh(5, 23, 9, 3, CellConstraints.FILL, CellConstraints.FILL));
						}
						spOrdenTrabajo.setViewportView(panelOrdenTrabajo);
					}
					jideTabbedPane1.addTab("Orden Trabajo", spOrdenTrabajo);
					
					
					//======== spOrdenTrabajoDetalle ========
					{
						
						//======== panelOrdenTrabajoDetalle ========
						{
							panelOrdenTrabajoDetalle.setBorder(new EtchedBorder());
							panelOrdenTrabajoDetalle.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX3),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(100)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(10)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(50)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(90), FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.DLUX3)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY3),
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
									new RowSpec(Sizes.dluY(12)),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.dluY(24)),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY3)
								}));
							
							//---- lblEstadoOrdenTrabajoDetalle ----
							lblEstadoOrdenTrabajoDetalle.setText("Estado:");
							panelOrdenTrabajoDetalle.add(lblEstadoOrdenTrabajoDetalle, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtEstadoOrdenTrabajoDetalle ----
							txtEstadoOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtEstadoOrdenTrabajoDetalle, cc.xy(5, 3));
							
							//---- lblTipoOrdenTrabajoDetalle ----
							lblTipoOrdenTrabajoDetalle.setText("Tipo:");
							panelOrdenTrabajoDetalle.add(lblTipoOrdenTrabajoDetalle, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtTipoOrdenTrabajoDetalle ----
							txtTipoOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtTipoOrdenTrabajoDetalle, cc.xywh(5, 5, 5, 1));
							
							//---- lblSubTipoOrdenTrabajoDetalle ----
							lblSubTipoOrdenTrabajoDetalle.setText("Sub Tipo:");
							panelOrdenTrabajoDetalle.add(lblSubTipoOrdenTrabajoDetalle, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtSubTipoOrdenTrabajoDetalle ----
							txtSubTipoOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtSubTipoOrdenTrabajoDetalle, cc.xywh(5, 7, 5, 1));
							
							//---- lblEquipoOrdenTrabajoDetalle ----
							lblEquipoOrdenTrabajoDetalle.setText("Equipo:");
							panelOrdenTrabajoDetalle.add(lblEquipoOrdenTrabajoDetalle, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtEquipoOrdenTrabajoDetalle ----
							txtEquipoOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtEquipoOrdenTrabajoDetalle, cc.xywh(5, 9, 5, 1));
							
							//---- lblAsignadoAOrdenTrabajoDetalle ----
							lblAsignadoAOrdenTrabajoDetalle.setText("Asignado a:");
							panelOrdenTrabajoDetalle.add(lblAsignadoAOrdenTrabajoDetalle, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtAsignadoOrdenTrabajoDetalle ----
							txtAsignadoOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtAsignadoOrdenTrabajoDetalle, cc.xywh(5, 11, 5, 1));
							
							//---- lblFechaLimiteOrdenTrabajoDetalle ----
							lblFechaLimiteOrdenTrabajoDetalle.setText("Fecha L\u00edmite:");
							panelOrdenTrabajoDetalle.add(lblFechaLimiteOrdenTrabajoDetalle, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaLimiteOrdenTrabajoDetalle ----
							txtFechaLimiteOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtFechaLimiteOrdenTrabajoDetalle, cc.xy(5, 13));
							
							//---- lblFechaEntregaOrdenTrabajoDetalle ----
							lblFechaEntregaOrdenTrabajoDetalle.setText("Fecha de Entrega:");
							panelOrdenTrabajoDetalle.add(lblFechaEntregaOrdenTrabajoDetalle, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaEntregaOrdenTrabajoDetalle ----
							txtFechaEntregaOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtFechaEntregaOrdenTrabajoDetalle, cc.xy(5, 15));
							
							//---- lblUrlDescripcionOrdenTrabajoDetalle ----
							lblUrlDescripcionOrdenTrabajoDetalle.setText("URL Descripci\u00f3n:");
							panelOrdenTrabajoDetalle.add(lblUrlDescripcionOrdenTrabajoDetalle, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtUrlDescripcionOrdenTrabajoDetalle ----
							txtUrlDescripcionOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtUrlDescripcionOrdenTrabajoDetalle, cc.xywh(5, 17, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblUrlPropuestaOrdenTrabajoDetalle ----
							lblUrlPropuestaOrdenTrabajoDetalle.setText("URL Propuesta:");
							panelOrdenTrabajoDetalle.add(lblUrlPropuestaOrdenTrabajoDetalle, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtUrlPropuestaOrdenTrabajoDetalle ----
							txtUrlPropuestaOrdenTrabajoDetalle.setEditable(false);
							panelOrdenTrabajoDetalle.add(txtUrlPropuestaOrdenTrabajoDetalle, cc.xywh(5, 19, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
							
							//---- lblDescripcionOrdenTrabajoDetalle ----
							lblDescripcionOrdenTrabajoDetalle.setText("Descripcion:");
							panelOrdenTrabajoDetalle.add(lblDescripcionOrdenTrabajoDetalle, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//======== scrollPaneOrdenTrabajoDetalle ========
							{
								
								//---- txtDescripcionOrdenTrabajoDetalle ----
								txtDescripcionOrdenTrabajoDetalle.setLineWrap(true);
								txtDescripcionOrdenTrabajoDetalle.setEditable(false);
								scrollPaneOrdenTrabajoDetalle.setViewportView(txtDescripcionOrdenTrabajoDetalle);
							}
							panelOrdenTrabajoDetalle.add(scrollPaneOrdenTrabajoDetalle, cc.xywh(5, 21, 7, 3));
						}
						spOrdenTrabajoDetalle.setViewportView(panelOrdenTrabajoDetalle);
					}
					jideTabbedPane1.addTab("Orden Trabajo Detalle", spOrdenTrabajoDetalle);
					
					
					//======== spPresupuesto ========
					{
						
						//======== panelPresupuesto ========
						{
							panelPresupuesto.setBorder(new EtchedBorder());
							panelPresupuesto.setLayout(new FormLayout(
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
									new ColumnSpec(Sizes.dluX(95)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.DLUX3)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY3),
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
									new RowSpec(Sizes.dluY(12)),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.dluY(24)),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY3)
								}));
							
							//---- lblCodigoPresupuesto ----
							lblCodigoPresupuesto.setText("C\u00f3digo:");
							panelPresupuesto.add(lblCodigoPresupuesto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCodigoPresupuesto ----
							txtCodigoPresupuesto.setEditable(false);
							panelPresupuesto.add(txtCodigoPresupuesto, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
							
							//---- lblEstadoPresupuesto ----
							lblEstadoPresupuesto.setText("Estado:");
							panelPresupuesto.add(lblEstadoPresupuesto, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtEstadoPresupuesto ----
							txtEstadoPresupuesto.setEditable(false);
							panelPresupuesto.add(txtEstadoPresupuesto, cc.xy(11, 3));
							
							//---- lblConceptoPresupuesto ----
							lblConceptoPresupuesto.setText("Concepto:");
							panelPresupuesto.add(lblConceptoPresupuesto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtConceptoPresupuesto ----
							txtConceptoPresupuesto.setEditable(false);
							panelPresupuesto.add(txtConceptoPresupuesto, cc.xywh(5, 5, 9, 1));
							
							//---- lblCorporacionPresupuesto ----
							lblCorporacionPresupuesto.setText("Corporaci\u00f3n:");
							panelPresupuesto.add(lblCorporacionPresupuesto, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtCorporacionPresupuesto ----
							txtCorporacionPresupuesto.setEditable(false);
							panelPresupuesto.add(txtCorporacionPresupuesto, cc.xywh(5, 7, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblClientePresupuesto ----
							lblClientePresupuesto.setText("Cliente:");
							panelPresupuesto.add(lblClientePresupuesto, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtClientePresupuesto ----
							txtClientePresupuesto.setEditable(false);
							panelPresupuesto.add(txtClientePresupuesto, cc.xywh(5, 9, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
							
							//---- lblClienteOficinaPresupuesto ----
							lblClienteOficinaPresupuesto.setText("Oficina:");
							panelPresupuesto.add(lblClienteOficinaPresupuesto, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtOficinaPresupuesto ----
							txtOficinaPresupuesto.setEditable(false);
							panelPresupuesto.add(txtOficinaPresupuesto, cc.xywh(5, 11, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblTipoOrdenPresupuesto ----
							lblTipoOrdenPresupuesto.setText("Tipo de Orden:");
							panelPresupuesto.add(lblTipoOrdenPresupuesto, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtTipoOrdenPresupuesto ----
							txtTipoOrdenPresupuesto.setEditable(false);
							panelPresupuesto.add(txtTipoOrdenPresupuesto, cc.xywh(5, 13, 7, 1));
							
							//---- lblOrdenTrabajoPresupuesto ----
							lblOrdenTrabajoPresupuesto.setText("Orden Trabajo:");
							panelPresupuesto.add(lblOrdenTrabajoPresupuesto, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtOrdenTrabajoPresupuesto ----
							txtOrdenTrabajoPresupuesto.setEditable(false);
							panelPresupuesto.add(txtOrdenTrabajoPresupuesto, cc.xywh(5, 15, 7, 1));
							
							//---- lblOrdenTrabajoDetallePresupuesto ----
							lblOrdenTrabajoDetallePresupuesto.setText("Orden Trabajo Detalle:");
							panelPresupuesto.add(lblOrdenTrabajoDetallePresupuesto, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtOrdenTrabajoDetallePresupuesto ----
							txtOrdenTrabajoDetallePresupuesto.setEditable(false);
							panelPresupuesto.add(txtOrdenTrabajoDetallePresupuesto, cc.xywh(5, 17, 7, 1));
							
							//---- lblFechaInicialPresupuesto ----
							lblFechaInicialPresupuesto.setText("Fecha Inicial:");
							panelPresupuesto.add(lblFechaInicialPresupuesto, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblFechaFinalPresupuesto ----
							lblFechaFinalPresupuesto.setText("Fecha Final:");
							panelPresupuesto.add(lblFechaFinalPresupuesto, cc.xywh(9, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaFinalPresupuesto ----
							txtFechaFinalPresupuesto.setEditable(false);
							panelPresupuesto.add(txtFechaFinalPresupuesto, cc.xy(11, 19));
							
							//---- txtFechaInicialPresupuesto ----
							txtFechaInicialPresupuesto.setEditable(false);
							panelPresupuesto.add(txtFechaInicialPresupuesto, cc.xy(5, 19));
							
							//---- lblFechaValidezPresupuesto ----
							lblFechaValidezPresupuesto.setText("Fecha Validez:");
							panelPresupuesto.add(lblFechaValidezPresupuesto, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtFechaValidezPresupuesto ----
							txtFechaValidezPresupuesto.setEditable(false);
							panelPresupuesto.add(txtFechaValidezPresupuesto, cc.xy(5, 21));
							
							//---- lblModificacionPresupuesto ----
							lblModificacionPresupuesto.setText("Modificado:");
							panelPresupuesto.add(lblModificacionPresupuesto, cc.xywh(9, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtModificacionPresupuesto ----
							txtModificacionPresupuesto.setEditable(false);
							panelPresupuesto.add(txtModificacionPresupuesto, cc.xywh(11, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblCabeceraPresupuesto ----
							lblCabeceraPresupuesto.setText("Observaci\u00f3n:");
							panelPresupuesto.add(lblCabeceraPresupuesto, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//======== scrollPaneCabeceraPresupuesto ========
							{
								
								//---- txtCabeceraPresupuesto ----
								txtCabeceraPresupuesto.setLineWrap(true);
								scrollPaneCabeceraPresupuesto.setViewportView(txtCabeceraPresupuesto);
							}
							panelPresupuesto.add(scrollPaneCabeceraPresupuesto, cc.xywh(5, 23, 9, 3));
						}
						spPresupuesto.setViewportView(panelPresupuesto);
					}
					jideTabbedPane1.addTab("Presupuesto", spPresupuesto);
					
					
					//======== spPlanMedio ========
					{
						
						//======== panelPlanMedios ========
						{
							panelPlanMedios.setBorder(new EtchedBorder());
							panelPlanMedios.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX3),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(110)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(10)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(65)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.DLUX3)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY3),
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
									new RowSpec(RowSpec.FILL, Sizes.DLUY3, FormSpec.NO_GROW)
								}));
							
							//---- lblcodigoPlanMedios ----
							lblcodigoPlanMedios.setText("C\u00f3digo:");
							panelPlanMedios.add(lblcodigoPlanMedios, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtCodigoPlanMedios ----
							txtCodigoPlanMedios.setEditable(false);
							panelPlanMedios.add(txtCodigoPlanMedios, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
							
							//---- lblEstadoPlanMedios ----
							lblEstadoPlanMedios.setText("Estado:");
							panelPlanMedios.add(lblEstadoPlanMedios, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtEstadoPlanMedios ----
							txtEstadoPlanMedios.setEditable(false);
							panelPlanMedios.add(txtEstadoPlanMedios, cc.xy(11, 3));
							
							//---- lblConceptoPlanMedios ----
							lblConceptoPlanMedios.setText("Concepto:");
							panelPlanMedios.add(lblConceptoPlanMedios, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtConceptoPlanMedios ----
							txtConceptoPlanMedios.setEditable(false);
							panelPlanMedios.add(txtConceptoPlanMedios, cc.xywh(5, 5, 7, 1));
							
							//---- lblTipoProveedorPlanMedios ----
							lblTipoProveedorPlanMedios.setText("Tipo Proveedor:");
							panelPlanMedios.add(lblTipoProveedorPlanMedios, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtTipoProveedorPlanMedios ----
							txtTipoProveedorPlanMedios.setEditable(false);
							panelPlanMedios.add(txtTipoProveedorPlanMedios, cc.xywh(5, 7, 7, 1));
							
							//---- lblCorporacionPlanMedios ----
							lblCorporacionPlanMedios.setText("Corporaci\u00f3n:");
							panelPlanMedios.add(lblCorporacionPlanMedios, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtCorporacionPlanMedios ----
							txtCorporacionPlanMedios.setEditable(false);
							panelPlanMedios.add(txtCorporacionPlanMedios, cc.xywh(5, 9, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblClientePlanMedios ----
							lblClientePlanMedios.setText("Cliente:");
							panelPlanMedios.add(lblClientePlanMedios, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtClientePlanMedios ----
							txtClientePlanMedios.setEditable(false);
							panelPlanMedios.add(txtClientePlanMedios, cc.xywh(5, 11, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
							
							//---- lblOficinaPlanMedios ----
							lblOficinaPlanMedios.setText("Oficina:");
							panelPlanMedios.add(lblOficinaPlanMedios, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtOficinaPlanMedios ----
							txtOficinaPlanMedios.setEditable(false);
							panelPlanMedios.add(txtOficinaPlanMedios, cc.xywh(5, 13, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblTipoOrdenPlanMedios ----
							lblTipoOrdenPlanMedios.setText("Tipo de Orden:");
							panelPlanMedios.add(lblTipoOrdenPlanMedios, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtTipoOrdenPlanMedios ----
							txtTipoOrdenPlanMedios.setEditable(false);
							panelPlanMedios.add(txtTipoOrdenPlanMedios, cc.xy(5, 15));
							
							//---- lblValorPlanMedios ----
							lblValorPlanMedios.setText("Valor:");
							panelPlanMedios.add(lblValorPlanMedios, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
							
							//---- txtValorPlanMedios ----
							txtValorPlanMedios.setEditable(false);
							panelPlanMedios.add(txtValorPlanMedios, cc.xywh(11, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
							
							//---- lblOrdenTrabajoPlanMedios ----
							lblOrdenTrabajoPlanMedios.setText("Orden Trabajo:");
							panelPlanMedios.add(lblOrdenTrabajoPlanMedios, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtOrdenTrabajoPlanMedios ----
							txtOrdenTrabajoPlanMedios.setEditable(false);
							panelPlanMedios.add(txtOrdenTrabajoPlanMedios, cc.xywh(5, 17, 7, 1));
							
							//---- lblOrdenTrabajoDetallePlanMedios ----
							lblOrdenTrabajoDetallePlanMedios.setText("Orden Trabajo Detalle:");
							panelPlanMedios.add(lblOrdenTrabajoDetallePlanMedios, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- lblFechaInicialPlanMedios ----
							lblFechaInicialPlanMedios.setText("Fecha Inicial:");
							panelPlanMedios.add(lblFechaInicialPlanMedios, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaInicialPlanMedios ----
							txtFechaInicialPlanMedios.setEditable(false);
							panelPlanMedios.add(txtFechaInicialPlanMedios, cc.xy(5, 21));
							
							//---- lblFechaFinalPlanMedios ----
							lblFechaFinalPlanMedios.setText("Fecha Final:");
							panelPlanMedios.add(lblFechaFinalPlanMedios, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
							
							//---- txtFechaFinalPlanMedios ----
							txtFechaFinalPlanMedios.setEditable(false);
							panelPlanMedios.add(txtFechaFinalPlanMedios, cc.xy(5, 23));
							
							//---- txtOrdenTrabajoDetallePlanMedios ----
							txtOrdenTrabajoDetallePlanMedios.setEditable(false);
							panelPlanMedios.add(txtOrdenTrabajoDetallePlanMedios, cc.xywh(5, 19, 7, 1));
						}
						spPlanMedio.setViewportView(panelPlanMedios);
					}
					jideTabbedPane1.addTab("Plan de Medios", spPlanMedio);
					
				}
				panelCriteriosBusqueda.add(jideTabbedPane1, cc.xywh(1, 7, 5, 5));
			}
			sppNavegadorCampana.setRightComponent(panelCriteriosBusqueda);
		}
		add(sppNavegadorCampana, cc.xywh(3, 3, 5, 9));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		//Seteo los scrollbar para el arbol
		treeCampana.setVerticalScrollbar(scrollBarVertical);
		treeCampana.setHorizontalScrollbar(scrollBarHorizontal);
		
		jideTabbedPane1.addTab("Cliente", SpiritResourceManager.getImageIcon("images/icons/entidad/Cliente.png"), spCliente);
		jideTabbedPane1.addTab("Campa\u00f1a", SpiritResourceManager.getImageIcon("images/icons/entidad/Campana.png"), spCampana);
		jideTabbedPane1.addTab("Orden Trabajo", SpiritResourceManager.getImageIcon("images/icons/entidad/OrdenTrabajo.png"), spOrdenTrabajo);
		jideTabbedPane1.addTab("Orden Trabajo Detalle", SpiritResourceManager.getImageIcon("images/icons/entidad/OrdenTrabajoDetalle.png"), spOrdenTrabajoDetalle);
		jideTabbedPane1.addTab("Presupuesto", SpiritResourceManager.getImageIcon("images/icons/entidad/Presupuesto.png"), spPresupuesto);
		jideTabbedPane1.addTab("Plan de Medios", SpiritResourceManager.getImageIcon("images/icons/entidad/Plan de Medios.png"), spPlanMedio);
				
		//---- btnBuscar ----
		btnBuscar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscar.setToolTipText("Realizar la Búsqueda Ahora");

		//---- btnBuscarCorporacion ----
		btnBuscarCorporacion.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCorporacion.setToolTipText("Buscar Corporaciones");
		
		//---- btnBuscarCliente ----
		btnBuscarCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCliente.setToolTipText("Buscar Clientes de Corporacion");
		
		//---- btnBuscarResponsableOrdenTrabajo ----
		btnBuscarResponsableOrdenTrabajo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarResponsableOrdenTrabajo.setToolTipText("Buscar Responsable de OrdenTrabajo");

		jideTabbedPane1.setShowCloseButton(false);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSplitPane sppNavegadorCampana;
	private JPanel panelResultadosBusqueda;
	private JLabel lblResultadoBusqueda;
	private TreeBrowser treeCampana;
	private JScrollBar scrollBarVertical;
	private JScrollBar scrollBarHorizontal;
	private JPanel panelCriteriosBusqueda;
	private JLabel lblCriteriosBusqueda;
	private JPanel panelCriterioBusqueda;
	private JCheckBox cbTodasCampanas;
	private JCheckBox cbPorCliente;
	private JTextField txtPorCorporacion;
	private JButton btnBuscarCorporacion;
	private JTextField txtPorCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbPorEstado;
	private JCheckBox cbPorResponsableOrdenTrabajo;
	private JTextField txtPorResponsableOrdenTrabajo;
	private JButton btnBuscarResponsableOrdenTrabajo;
	private JComboBox cmbPorEstado;
	private JCheckBox cbPorFechas;
	private DateComboBox cmbFechaInicio;
	private DateComboBox cmbFechaFin;
	private JButton btnBuscar;
	private JideTabbedPane jideTabbedPane1;
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
	private JTextField txtTipoNegocioCliente;
	private JLabel lblTipoNegocioCliente;
	private JTextField txtObservacionesCliente;
	private JLabel lblCuentaContableCliente;
	private JTextField txtCtaContableCliente;
	private JScrollPane spCampana;
	private JPanel panelCampana;
	private JLabel lblCodigoCampana;
	private JTextField txtCodigoCampana;
	private JLabel lblEstadoCampana;
	private JTextField txtEstadoCampana;
	private JLabel lblNombreCampana;
	private JTextField txtNombreCampana;
	private JLabel lblCorporacionCampana;
	private JTextField txtCorporacionCampana;
	private JLabel lblClienteCampana;
	private JTextField txtClienteCampana;
	private JLabel lblFechaInicioCampana;
	private JTextField txtFechaInicioCampana;
	private JLabel lblPublicoObjetivoCampana;
	private JTextField txtPublicoObjetivoCampana;
	private JLabel lblObservacionCampana;
	private JTextField txtObservacionCampana;
	private JScrollPane spOrdenTrabajo;
	private JPanel panelOrdenTrabajo;
	private JLabel lblCodigoOrdenTrabajo;
	private JLabel lblFechaCreacionOrdenTrabajo;
	private JTextField txtFechaCreacionOrdenTrabajo;
	private JTextField txtCodigoOrdenTrabajo;
	private JTextField txtDescripcionOrdenTrabajo;
	private JLabel lblCorporacionOrdenTrabajo;
	private JLabel lblDescripcionOrdenTrabajo;
	private JTextField txtCorporacionOrdenTrabajo;
	private JLabel lblClienteOrdenTrabajo;
	private JTextField txtClienteOrdenTrabajo;
	private JLabel lblOficinaOrdenTrabajo;
	private JTextField txtOficinaOrdenTrabajo;
	private JLabel lblCampanaOrdenTrabajo;
	private JTextField txtCampanaOrdenTrabajo;
	private JLabel lblAsignadoAOrdenTrabajo;
	private JTextField txtAsignadoAOrdenTrabajo;
	private JLabel lblEstadoOrdenTrabajo;
	private JTextField txtEstadoOrdenTrabajo;
	private JLabel lblFechaLimiteOrdenTrabajo;
	private JTextField txtFechaLimiteOrdenTrabajo;
	private JLabel lblFechaEntregaOrdenTrabajo;
	private JTextField txtFechaEntregaOrdenTrabajo;
	private JTextField txtUrlPropuestaOrdenTrabajo;
	private JLabel lblObservacionOrdenTrabajo;
	private JLabel lblUrlPropuestaOrdenTrabajo;
	private JScrollPane scrollPaneObservacionOrdenTrabajo;
	private JTextArea txtObservacionOrdenTrabajo;
	private JScrollPane spOrdenTrabajoDetalle;
	private JPanel panelOrdenTrabajoDetalle;
	private JLabel lblEstadoOrdenTrabajoDetalle;
	private JTextField txtEstadoOrdenTrabajoDetalle;
	private JLabel lblTipoOrdenTrabajoDetalle;
	private JTextField txtTipoOrdenTrabajoDetalle;
	private JLabel lblSubTipoOrdenTrabajoDetalle;
	private JTextField txtSubTipoOrdenTrabajoDetalle;
	private JLabel lblEquipoOrdenTrabajoDetalle;
	private JTextField txtEquipoOrdenTrabajoDetalle;
	private JLabel lblAsignadoAOrdenTrabajoDetalle;
	private JTextField txtAsignadoOrdenTrabajoDetalle;
	private JLabel lblFechaLimiteOrdenTrabajoDetalle;
	private JTextField txtFechaLimiteOrdenTrabajoDetalle;
	private JLabel lblFechaEntregaOrdenTrabajoDetalle;
	private JTextField txtFechaEntregaOrdenTrabajoDetalle;
	private JLabel lblUrlDescripcionOrdenTrabajoDetalle;
	private JTextField txtUrlDescripcionOrdenTrabajoDetalle;
	private JLabel lblUrlPropuestaOrdenTrabajoDetalle;
	private JTextField txtUrlPropuestaOrdenTrabajoDetalle;
	private JLabel lblDescripcionOrdenTrabajoDetalle;
	private JScrollPane scrollPaneOrdenTrabajoDetalle;
	private JTextArea txtDescripcionOrdenTrabajoDetalle;
	private JScrollPane spPresupuesto;
	private JPanel panelPresupuesto;
	private JLabel lblCodigoPresupuesto;
	private JTextField txtCodigoPresupuesto;
	private JLabel lblEstadoPresupuesto;
	private JTextField txtEstadoPresupuesto;
	private JLabel lblConceptoPresupuesto;
	private JTextField txtConceptoPresupuesto;
	private JLabel lblCorporacionPresupuesto;
	private JTextField txtCorporacionPresupuesto;
	private JLabel lblClientePresupuesto;
	private JTextField txtClientePresupuesto;
	private JLabel lblClienteOficinaPresupuesto;
	private JTextField txtOficinaPresupuesto;
	private JLabel lblTipoOrdenPresupuesto;
	private JTextField txtTipoOrdenPresupuesto;
	private JLabel lblOrdenTrabajoPresupuesto;
	private JTextField txtOrdenTrabajoPresupuesto;
	private JLabel lblOrdenTrabajoDetallePresupuesto;
	private JTextField txtOrdenTrabajoDetallePresupuesto;
	private JLabel lblFechaInicialPresupuesto;
	private JLabel lblFechaFinalPresupuesto;
	private JTextField txtFechaFinalPresupuesto;
	private JTextField txtFechaInicialPresupuesto;
	private JLabel lblFechaValidezPresupuesto;
	private JTextField txtFechaValidezPresupuesto;
	private JLabel lblModificacionPresupuesto;
	private JTextField txtModificacionPresupuesto;
	private JLabel lblCabeceraPresupuesto;
	private JScrollPane scrollPaneCabeceraPresupuesto;
	private JTextArea txtCabeceraPresupuesto;
	private JScrollPane spPlanMedio;
	private JPanel panelPlanMedios;
	private JLabel lblcodigoPlanMedios;
	private JTextField txtCodigoPlanMedios;
	private JLabel lblEstadoPlanMedios;
	private JTextField txtEstadoPlanMedios;
	private JLabel lblConceptoPlanMedios;
	private JTextField txtConceptoPlanMedios;
	private JLabel lblTipoProveedorPlanMedios;
	private JTextField txtTipoProveedorPlanMedios;
	private JLabel lblCorporacionPlanMedios;
	private JTextField txtCorporacionPlanMedios;
	private JLabel lblClientePlanMedios;
	private JTextField txtClientePlanMedios;
	private JLabel lblOficinaPlanMedios;
	private JTextField txtOficinaPlanMedios;
	private JLabel lblTipoOrdenPlanMedios;
	private JTextField txtTipoOrdenPlanMedios;
	private JLabel lblValorPlanMedios;
	private JTextField txtValorPlanMedios;
	private JLabel lblOrdenTrabajoPlanMedios;
	private JTextField txtOrdenTrabajoPlanMedios;
	private JLabel lblOrdenTrabajoDetallePlanMedios;
	private JLabel lblFechaInicialPlanMedios;
	private JTextField txtFechaInicialPlanMedios;
	private JLabel lblFechaFinalPlanMedios;
	private JTextField txtFechaFinalPlanMedios;
	private JTextField txtOrdenTrabajoDetallePlanMedios;
	private TreeNode rootNodeTreeCampana;
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

	public JButton getBtnBuscarResponsableOrdenTrabajo() {
		return btnBuscarResponsableOrdenTrabajo;
	}

	public void setBtnBuscarResponsableOrdenTrabajo(
			JButton btnBuscarResponsableOrdenTrabajo) {
		this.btnBuscarResponsableOrdenTrabajo = btnBuscarResponsableOrdenTrabajo;
	}

	public JCheckBox getCbPorCliente() {
		return cbPorCliente;
	}

	public void setCbPorCliente(JCheckBox cbPorCliente) {
		this.cbPorCliente = cbPorCliente;
	}

	public JCheckBox getCbPorEstado() {
		return cbPorEstado;
	}

	public void setCbPorEstado(JCheckBox cbPorEstado) {
		this.cbPorEstado = cbPorEstado;
	}

	public JCheckBox getCbPorResponsableOrdenTrabajo() {
		return cbPorResponsableOrdenTrabajo;
	}

	public void setCbPorResponsableOrdenTrabajo(
			JCheckBox cbPorResponsableOrdenTrabajo) {
		this.cbPorResponsableOrdenTrabajo = cbPorResponsableOrdenTrabajo;
	}

	public JCheckBox getCbTodasCampanas() {
		return cbTodasCampanas;
	}

	public void setCbTodasCampanas(JCheckBox cbTodasCampanas) {
		this.cbTodasCampanas = cbTodasCampanas;
	}

	public JComboBox getCmbPorEstado() {
		return cmbPorEstado;
	}

	public void setCmbPorEstado(JComboBox cmbPorEstado) {
		this.cmbPorEstado = cmbPorEstado;
	}

	public TreeNode getRootNodeTreeCampana() {
		return rootNodeTreeCampana;
	}

	public void setRootNodeTreeCampana(TreeNode rootNodeTreeCampana) {
		this.rootNodeTreeCampana = rootNodeTreeCampana;
	}

	public TreeBrowser getTreeCampana() {
		return treeCampana;
	}

	public void setTreeCampana(TreeBrowser treeCampana) {
		this.treeCampana = treeCampana;
	}

	public JTextField getTxtAsignadoAOrdenTrabajo() {
		return txtAsignadoAOrdenTrabajo;
	}

	public void setTxtAsignadoAOrdenTrabajo(JTextField txtAsignadoAOrdenTrabajo) {
		this.txtAsignadoAOrdenTrabajo = txtAsignadoAOrdenTrabajo;
	}

	public JTextField getTxtAsignadoOrdenTrabajoDetalle() {
		return txtAsignadoOrdenTrabajoDetalle;
	}

	public void setTxtAsignadoOrdenTrabajoDetalle(
			JTextField txtAsignadoOrdenTrabajoDetalle) {
		this.txtAsignadoOrdenTrabajoDetalle = txtAsignadoOrdenTrabajoDetalle;
	}

	public JTextArea getTxtCabeceraPresupuesto() {
		return txtCabeceraPresupuesto;
	}

	public void setTxtCabeceraPresupuesto(JTextArea txtCabeceraPresupuesto) {
		this.txtCabeceraPresupuesto = txtCabeceraPresupuesto;
	}

	public JTextField getTxtCampanaOrdenTrabajo() {
		return txtCampanaOrdenTrabajo;
	}

	public void setTxtCampanaOrdenTrabajo(JTextField txtCampanaOrdenTrabajo) {
		this.txtCampanaOrdenTrabajo = txtCampanaOrdenTrabajo;
	}

	public JTextField getTxtClienteCampana() {
		return txtClienteCampana;
	}

	public void setTxtClienteCampana(JTextField txtClienteCampana) {
		this.txtClienteCampana = txtClienteCampana;
	}

	public JTextField getTxtClienteOrdenTrabajo() {
		return txtClienteOrdenTrabajo;
	}

	public void setTxtClienteOrdenTrabajo(JTextField txtClienteOrdenTrabajo) {
		this.txtClienteOrdenTrabajo = txtClienteOrdenTrabajo;
	}

	public JTextField getTxtClientePlanMedios() {
		return txtClientePlanMedios;
	}

	public void setTxtClientePlanMedios(JTextField txtClientePlanMedios) {
		this.txtClientePlanMedios = txtClientePlanMedios;
	}

	public JTextField getTxtClientePresupuesto() {
		return txtClientePresupuesto;
	}

	public void setTxtClientePresupuesto(JTextField txtClientePresupuesto) {
		this.txtClientePresupuesto = txtClientePresupuesto;
	}

	public JTextField getTxtCodigoCampana() {
		return txtCodigoCampana;
	}

	public void setTxtCodigoCampana(JTextField txtCodigoCampana) {
		this.txtCodigoCampana = txtCodigoCampana;
	}

	public JTextField getTxtCodigoOrdenTrabajo() {
		return txtCodigoOrdenTrabajo;
	}

	public void setTxtCodigoOrdenTrabajo(JTextField txtCodigoOrdenTrabajo) {
		this.txtCodigoOrdenTrabajo = txtCodigoOrdenTrabajo;
	}

	public JTextField getTxtCodigoPlanMedios() {
		return txtCodigoPlanMedios;
	}

	public void setTxtCodigoPlanMedios(JTextField txtCodigoPlanMedios) {
		this.txtCodigoPlanMedios = txtCodigoPlanMedios;
	}

	public JTextField getTxtCodigoPresupuesto() {
		return txtCodigoPresupuesto;
	}

	public void setTxtCodigoPresupuesto(JTextField txtCodigoPresupuesto) {
		this.txtCodigoPresupuesto = txtCodigoPresupuesto;
	}

	public JTextField getTxtConceptoPlanMedios() {
		return txtConceptoPlanMedios;
	}

	public void setTxtConceptoPlanMedios(JTextField txtConceptoPlanMedios) {
		this.txtConceptoPlanMedios = txtConceptoPlanMedios;
	}

	public JTextField getTxtConceptoPresupuesto() {
		return txtConceptoPresupuesto;
	}

	public void setTxtConceptoPresupuesto(JTextField txtConceptoPresupuesto) {
		this.txtConceptoPresupuesto = txtConceptoPresupuesto;
	}

	public JTextField getTxtCorporacionCampana() {
		return txtCorporacionCampana;
	}

	public void setTxtCorporacionCampana(JTextField txtCorporacionCampana) {
		this.txtCorporacionCampana = txtCorporacionCampana;
	}

	public JTextField getTxtCorporacionCliente() {
		return txtCorporacionCliente;
	}

	public void setTxtCorporacionCliente(JTextField txtCorporacionCliente) {
		this.txtCorporacionCliente = txtCorporacionCliente;
	}

	public JTextField getTxtCorporacionOrdenTrabajo() {
		return txtCorporacionOrdenTrabajo;
	}

	public void setTxtCorporacionOrdenTrabajo(JTextField txtCorporacionOrdenTrabajo) {
		this.txtCorporacionOrdenTrabajo = txtCorporacionOrdenTrabajo;
	}

	public JTextField getTxtCorporacionPlanMedios() {
		return txtCorporacionPlanMedios;
	}

	public void setTxtCorporacionPlanMedios(JTextField txtCorporacionPlanMedios) {
		this.txtCorporacionPlanMedios = txtCorporacionPlanMedios;
	}

	public JTextField getTxtCorporacionPresupuesto() {
		return txtCorporacionPresupuesto;
	}

	public void setTxtCorporacionPresupuesto(JTextField txtCorporacionPresupuesto) {
		this.txtCorporacionPresupuesto = txtCorporacionPresupuesto;
	}

	public JTextField getTxtCtaContableCliente() {
		return txtCtaContableCliente;
	}

	public void setTxtCtaContableCliente(JTextField txtCtaContableCliente) {
		this.txtCtaContableCliente = txtCtaContableCliente;
	}

	public JTextField getTxtDescripcionOrdenTrabajo() {
		return txtDescripcionOrdenTrabajo;
	}

	public void setTxtDescripcionOrdenTrabajo(JTextField txtDescripcionOrdenTrabajo) {
		this.txtDescripcionOrdenTrabajo = txtDescripcionOrdenTrabajo;
	}

	public JTextArea getTxtDescripcionOrdenTrabajoDetalle() {
		return txtDescripcionOrdenTrabajoDetalle;
	}

	public void setTxtDescripcionOrdenTrabajoDetalle(
			JTextArea txtDescripcionOrdenTrabajoDetalle) {
		this.txtDescripcionOrdenTrabajoDetalle = txtDescripcionOrdenTrabajoDetalle;
	}

	public JTextField getTxtEquipoOrdenTrabajoDetalle() {
		return txtEquipoOrdenTrabajoDetalle;
	}

	public void setTxtEquipoOrdenTrabajoDetalle(
			JTextField txtEquipoOrdenTrabajoDetalle) {
		this.txtEquipoOrdenTrabajoDetalle = txtEquipoOrdenTrabajoDetalle;
	}

	public JTextField getTxtEstadoCampana() {
		return txtEstadoCampana;
	}

	public void setTxtEstadoCampana(JTextField txtEstadoCampana) {
		this.txtEstadoCampana = txtEstadoCampana;
	}

	public JTextField getTxtEstadoCliente() {
		return txtEstadoCliente;
	}

	public void setTxtEstadoCliente(JTextField txtEstadoCliente) {
		this.txtEstadoCliente = txtEstadoCliente;
	}

	public JTextField getTxtEstadoOrdenTrabajo() {
		return txtEstadoOrdenTrabajo;
	}

	public void setTxtEstadoOrdenTrabajo(JTextField txtEstadoOrdenTrabajo) {
		this.txtEstadoOrdenTrabajo = txtEstadoOrdenTrabajo;
	}

	public JTextField getTxtEstadoOrdenTrabajoDetalle() {
		return txtEstadoOrdenTrabajoDetalle;
	}

	public void setTxtEstadoOrdenTrabajoDetalle(
			JTextField txtEstadoOrdenTrabajoDetalle) {
		this.txtEstadoOrdenTrabajoDetalle = txtEstadoOrdenTrabajoDetalle;
	}

	public JTextField getTxtEstadoPlanMedios() {
		return txtEstadoPlanMedios;
	}

	public void setTxtEstadoPlanMedios(JTextField txtEstadoPlanMedios) {
		this.txtEstadoPlanMedios = txtEstadoPlanMedios;
	}

	public JTextField getTxtEstadoPresupuesto() {
		return txtEstadoPresupuesto;
	}

	public void setTxtEstadoPresupuesto(JTextField txtEstadoPresupuesto) {
		this.txtEstadoPresupuesto = txtEstadoPresupuesto;
	}

	public JTextField getTxtFechaCreacionCliente() {
		return txtFechaCreacionCliente;
	}

	public void setTxtFechaCreacionCliente(JTextField txtFechaCreacionCliente) {
		this.txtFechaCreacionCliente = txtFechaCreacionCliente;
	}

	public JTextField getTxtFechaCreacionOrdenTrabajo() {
		return txtFechaCreacionOrdenTrabajo;
	}

	public void setTxtFechaCreacionOrdenTrabajo(
			JTextField txtFechaCreacionOrdenTrabajo) {
		this.txtFechaCreacionOrdenTrabajo = txtFechaCreacionOrdenTrabajo;
	}

	public JTextField getTxtFechaEntregaOrdenTrabajo() {
		return txtFechaEntregaOrdenTrabajo;
	}

	public void setTxtFechaEntregaOrdenTrabajo(
			JTextField txtFechaEntregaOrdenTrabajo) {
		this.txtFechaEntregaOrdenTrabajo = txtFechaEntregaOrdenTrabajo;
	}

	public JTextField getTxtFechaEntregaOrdenTrabajoDetalle() {
		return txtFechaEntregaOrdenTrabajoDetalle;
	}

	public void setTxtFechaEntregaOrdenTrabajoDetalle(
			JTextField txtFechaEntregaOrdenTrabajoDetalle) {
		this.txtFechaEntregaOrdenTrabajoDetalle = txtFechaEntregaOrdenTrabajoDetalle;
	}

	public JTextField getTxtFechaFinalPlanMedios() {
		return txtFechaFinalPlanMedios;
	}

	public void setTxtFechaFinalPlanMedios(JTextField txtFechaFinalPlanMedios) {
		this.txtFechaFinalPlanMedios = txtFechaFinalPlanMedios;
	}

	public JTextField getTxtFechaFinalPresupuesto() {
		return txtFechaFinalPresupuesto;
	}

	public void setTxtFechaFinalPresupuesto(JTextField txtFechaFinalPresupuesto) {
		this.txtFechaFinalPresupuesto = txtFechaFinalPresupuesto;
	}

	public JTextField getTxtFechaInicialPlanMedios() {
		return txtFechaInicialPlanMedios;
	}

	public void setTxtFechaInicialPlanMedios(JTextField txtFechaInicialPlanMedios) {
		this.txtFechaInicialPlanMedios = txtFechaInicialPlanMedios;
	}

	public JTextField getTxtFechaInicialPresupuesto() {
		return txtFechaInicialPresupuesto;
	}

	public void setTxtFechaInicialPresupuesto(JTextField txtFechaInicialPresupuesto) {
		this.txtFechaInicialPresupuesto = txtFechaInicialPresupuesto;
	}

	public JTextField getTxtFechaInicioCampana() {
		return txtFechaInicioCampana;
	}

	public void setTxtFechaInicioCampana(JTextField txtFechaInicioCampana) {
		this.txtFechaInicioCampana = txtFechaInicioCampana;
	}

	public JTextField getTxtFechaLimiteOrdenTrabajo() {
		return txtFechaLimiteOrdenTrabajo;
	}

	public void setTxtFechaLimiteOrdenTrabajo(JTextField txtFechaLimiteOrdenTrabajo) {
		this.txtFechaLimiteOrdenTrabajo = txtFechaLimiteOrdenTrabajo;
	}

	public JTextField getTxtFechaLimiteOrdenTrabajoDetalle() {
		return txtFechaLimiteOrdenTrabajoDetalle;
	}

	public void setTxtFechaLimiteOrdenTrabajoDetalle(
			JTextField txtFechaLimiteOrdenTrabajoDetalle) {
		this.txtFechaLimiteOrdenTrabajoDetalle = txtFechaLimiteOrdenTrabajoDetalle;
	}

	public JTextField getTxtFechaValidezPresupuesto() {
		return txtFechaValidezPresupuesto;
	}

	public void setTxtFechaValidezPresupuesto(JTextField txtFechaValidezPresupuesto) {
		this.txtFechaValidezPresupuesto = txtFechaValidezPresupuesto;
	}

	public JTextField getTxtIdentificacionCliente() {
		return txtIdentificacionCliente;
	}

	public void setTxtIdentificacionCliente(JTextField txtIdentificacionCliente) {
		this.txtIdentificacionCliente = txtIdentificacionCliente;
	}

	public JTextField getTxtModificacionPresupuesto() {
		return txtModificacionPresupuesto;
	}

	public void setTxtModificacionPresupuesto(JTextField txtModificacionPresupuesto) {
		this.txtModificacionPresupuesto = txtModificacionPresupuesto;
	}

	public JTextField getTxtNombreCampana() {
		return txtNombreCampana;
	}

	public void setTxtNombreCampana(JTextField txtNombreCampana) {
		this.txtNombreCampana = txtNombreCampana;
	}

	public JTextField getTxtNombreLegalCliente() {
		return txtNombreLegalCliente;
	}

	public void setTxtNombreLegalCliente(JTextField txtNombreLegalCliente) {
		this.txtNombreLegalCliente = txtNombreLegalCliente;
	}

	public JTextField getTxtObservacionCampana() {
		return txtObservacionCampana;
	}

	public void setTxtObservacionCampana(JTextField txtObservacionCampana) {
		this.txtObservacionCampana = txtObservacionCampana;
	}

	public JTextField getTxtObservacionesCliente() {
		return txtObservacionesCliente;
	}

	public void setTxtObservacionesCliente(JTextField txtObservacionesCliente) {
		this.txtObservacionesCliente = txtObservacionesCliente;
	}

	public JTextArea getTxtObservacionOrdenTrabajo() {
		return txtObservacionOrdenTrabajo;
	}

	public void setTxtObservacionOrdenTrabajo(JTextArea txtObservacionOrdenTrabajo) {
		this.txtObservacionOrdenTrabajo = txtObservacionOrdenTrabajo;
	}

	public JTextField getTxtOficinaOrdenTrabajo() {
		return txtOficinaOrdenTrabajo;
	}

	public void setTxtOficinaOrdenTrabajo(JTextField txtOficinaOrdenTrabajo) {
		this.txtOficinaOrdenTrabajo = txtOficinaOrdenTrabajo;
	}

	public JTextField getTxtOficinaPlanMedios() {
		return txtOficinaPlanMedios;
	}

	public void setTxtOficinaPlanMedios(JTextField txtOficinaPlanMedios) {
		this.txtOficinaPlanMedios = txtOficinaPlanMedios;
	}

	public JTextField getTxtOficinaPresupuesto() {
		return txtOficinaPresupuesto;
	}

	public void setTxtOficinaPresupuesto(JTextField txtOficinaPresupuesto) {
		this.txtOficinaPresupuesto = txtOficinaPresupuesto;
	}

	public JTextField getTxtOrdenTrabajoDetallePlanMedios() {
		return txtOrdenTrabajoDetallePlanMedios;
	}

	public void setTxtOrdenTrabajoDetallePlanMedios(
			JTextField txtOrdenTrabajoDetallePlanMedios) {
		this.txtOrdenTrabajoDetallePlanMedios = txtOrdenTrabajoDetallePlanMedios;
	}

	public JTextField getTxtOrdenTrabajoDetallePresupuesto() {
		return txtOrdenTrabajoDetallePresupuesto;
	}

	public void setTxtOrdenTrabajoDetallePresupuesto(
			JTextField txtOrdenTrabajoDetallePresupuesto) {
		this.txtOrdenTrabajoDetallePresupuesto = txtOrdenTrabajoDetallePresupuesto;
	}

	public JTextField getTxtOrdenTrabajoPlanMedios() {
		return txtOrdenTrabajoPlanMedios;
	}

	public void setTxtOrdenTrabajoPlanMedios(JTextField txtOrdenTrabajoPlanMedios) {
		this.txtOrdenTrabajoPlanMedios = txtOrdenTrabajoPlanMedios;
	}

	public JTextField getTxtOrdenTrabajoPresupuesto() {
		return txtOrdenTrabajoPresupuesto;
	}

	public void setTxtOrdenTrabajoPresupuesto(JTextField txtOrdenTrabajoPresupuesto) {
		this.txtOrdenTrabajoPresupuesto = txtOrdenTrabajoPresupuesto;
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

	public JTextField getTxtPorResponsableOrdenTrabajo() {
		return txtPorResponsableOrdenTrabajo;
	}

	public void setTxtPorResponsableOrdenTrabajo(
			JTextField txtPorResponsableOrdenTrabajo) {
		this.txtPorResponsableOrdenTrabajo = txtPorResponsableOrdenTrabajo;
	}

	public JTextField getTxtPublicoObjetivoCampana() {
		return txtPublicoObjetivoCampana;
	}

	public void setTxtPublicoObjetivoCampana(JTextField txtPublicoObjetivoCampana) {
		this.txtPublicoObjetivoCampana = txtPublicoObjetivoCampana;
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

	public JTextField getTxtSubTipoOrdenTrabajoDetalle() {
		return txtSubTipoOrdenTrabajoDetalle;
	}

	public void setTxtSubTipoOrdenTrabajoDetalle(
			JTextField txtSubTipoOrdenTrabajoDetalle) {
		this.txtSubTipoOrdenTrabajoDetalle = txtSubTipoOrdenTrabajoDetalle;
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

	public JTextField getTxtTipoOrdenPlanMedios() {
		return txtTipoOrdenPlanMedios;
	}

	public void setTxtTipoOrdenPlanMedios(JTextField txtTipoOrdenPlanMedios) {
		this.txtTipoOrdenPlanMedios = txtTipoOrdenPlanMedios;
	}

	public JTextField getTxtTipoOrdenPresupuesto() {
		return txtTipoOrdenPresupuesto;
	}

	public void setTxtTipoOrdenPresupuesto(JTextField txtTipoOrdenPresupuesto) {
		this.txtTipoOrdenPresupuesto = txtTipoOrdenPresupuesto;
	}

	public JTextField getTxtTipoOrdenTrabajoDetalle() {
		return txtTipoOrdenTrabajoDetalle;
	}

	public void setTxtTipoOrdenTrabajoDetalle(JTextField txtTipoOrdenTrabajoDetalle) {
		this.txtTipoOrdenTrabajoDetalle = txtTipoOrdenTrabajoDetalle;
	}

	public JTextField getTxtTipoProveedorPlanMedios() {
		return txtTipoProveedorPlanMedios;
	}

	public void setTxtTipoProveedorPlanMedios(JTextField txtTipoProveedorPlanMedios) {
		this.txtTipoProveedorPlanMedios = txtTipoProveedorPlanMedios;
	}

	public JTextField getTxtUrlDescripcionOrdenTrabajoDetalle() {
		return txtUrlDescripcionOrdenTrabajoDetalle;
	}

	public void setTxtUrlDescripcionOrdenTrabajoDetalle(
			JTextField txtUrlDescripcionOrdenTrabajoDetalle) {
		this.txtUrlDescripcionOrdenTrabajoDetalle = txtUrlDescripcionOrdenTrabajoDetalle;
	}

	public JTextField getTxtUrlPropuestaOrdenTrabajo() {
		return txtUrlPropuestaOrdenTrabajo;
	}

	public void setTxtUrlPropuestaOrdenTrabajo(
			JTextField txtUrlPropuestaOrdenTrabajo) {
		this.txtUrlPropuestaOrdenTrabajo = txtUrlPropuestaOrdenTrabajo;
	}

	public JTextField getTxtUrlPropuestaOrdenTrabajoDetalle() {
		return txtUrlPropuestaOrdenTrabajoDetalle;
	}

	public void setTxtUrlPropuestaOrdenTrabajoDetalle(
			JTextField txtUrlPropuestaOrdenTrabajoDetalle) {
		this.txtUrlPropuestaOrdenTrabajoDetalle = txtUrlPropuestaOrdenTrabajoDetalle;
	}

	public JTextField getTxtValorPlanMedios() {
		return txtValorPlanMedios;
	}

	public void setTxtValorPlanMedios(JTextField txtValorPlanMedios) {
		this.txtValorPlanMedios = txtValorPlanMedios;
	}

	public JideTabbedPane getJideTabbedPane1() {
		return jideTabbedPane1;
	}

	public void setJideTabbedPane1(JideTabbedPane jideTabbedPane1) {
		this.jideTabbedPane1 = jideTabbedPane1;
	}

	public JCheckBox getCbPorFechas() {
		return cbPorFechas;
	}

	public void setCbPorFechas(JCheckBox cbPorFechas) {
		this.cbPorFechas = cbPorFechas;
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
}
