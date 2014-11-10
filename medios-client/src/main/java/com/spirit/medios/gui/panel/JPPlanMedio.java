package com.spirit.medios.gui.panel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
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
import com.jidesoft.swing.CheckBoxTree;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPPlanMedio extends SpiritModelImpl {
	public JPPlanMedio() {
		initComponents();
		setName("Plan de Medios");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpPlanMedio = new JideTabbedPane();
		spPlanMedio = new JScrollPane();
		panelPlanMedio = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
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
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		btnOficina = new JButton();
		lblOrdenTrabajo = new JLabel();
		cmbOrdenTrabajo = new JComboBox();
		lblOrdenTrabajoDetalle = new JLabel();
		txtOrdenTrabajoDetalle = new JTextField();
		lblConcepto = new JLabel();
		txtConcepto = new JTextField();
		separatorEstrategia = compFactory.createSeparator("Estrategia");
		lblTarget = new JLabel();
		cmbTarget = new JComboBox();
		lblGuayaquil = new JLabel();
		txtGuayaquil = new JTextField();
		btnGuayaquil = new JButton();
		lblQuito = new JLabel();
		txtQuito = new JTextField();
		btnQuito = new JButton();
		lblPeriodo = new JLabel();
		cmbPeriodoFechaInicio = new DateComboBox();
		lblPeriodoAl = new JLabel();
		cmbPeriodoFechaFin = new DateComboBox();
		lblTotal = new JLabel();
		txtTotalCiudad = new JTextField();
		btnTotalCiudad = new JButton();
		lblCobertura = new JLabel();
		spCobertura = new JScrollPane();
		txtCobertura = new JTextArea();
		lblOtrasConsideraciones = new JLabel();
		spOtrasConsideraciones = new JScrollPane();
		txtOtrasConsideraciones = new JTextArea();
		lblConsideraciones = new JLabel();
		separatorTotales = compFactory.createSeparator("Totales");
		lblValorTarifa = new JLabel();
		txtValorTarifa = new JTextField();
		lblValorDescuento = new JLabel();
		txtValorDescuento = new JTextField();
		lblModificaciones = new JLabel();
		txtModificaciones = new JTextField();
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
		btnTvData = new JButton();
		lblCanalTv = new JLabel();
		lblProgramaTv = new JLabel();
		spArbolTelevision = new JScrollPane();
		arbolTelevision = new CheckBoxTree();
		txtCanalTv = new JTextField();
		txtProgramaTv = new JTextField();
		lblHoraInicioPrograma = new JLabel();
		txtHoraInicioPrograma = new JTextField();
		lblHoraFinPrograma = new JLabel();
		txtHoraFinPrograma = new JTextField();
		lblDiasPrograma = new JLabel();
		lblRatingTv = new JLabel();
		txtRatingTv = new JTextField();
		lblVCunaTarifa = new JLabel();
		txtVCunaTarifa = new JTextField();
		lblVCunaNegocio = new JLabel();
		txtVCunaNegocio = new JTextField();
		lblComercialTv = new JLabel();
		txtDiasPrograma = new JTextField();
		txtComercialTv = new JTextField();
		lblDerechoPrograma = new JLabel();
		txtDerechoPrograma = new JTextField();
		lblVersionPrograma = new JLabel();
		btnAgregarProgramaTv = new JButton();
		btnCrearMapaPautaTv = new JButton();
		txtVersionPrograma = new JTextField();
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
		panelMapaPautaTv = new JPanel();
		panelMapaPautaRadio = new JPanel();
		spTblMapaPautaRadio = new JScrollPane();
		tblMapaPautaRadio = new JTable();
		panelMapaPautaPrensa = new JPanel();
		spTblMapaPautaPrensa = new JScrollPane();
		tblMapaPautaPrensa = new JTable();
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
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
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
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(25)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(55)),
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
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
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
							new RowSpec(Sizes.DLUY7)
						}));
					
					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelPlanMedio.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtCodigo, cc.xy(5, 3));
					
					//---- lblFechaCreacion ----
					lblFechaCreacion.setText("Fecha de Creaci\u00f3n:");
					panelPlanMedio.add(lblFechaCreacion, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtFechaCreacion, cc.xywh(19, 3, 3, 1));
					
					//---- lblCorporacion ----
					lblCorporacion.setText("Corporaci\u00f3n:");
					panelPlanMedio.add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtCorporacion, cc.xywh(5, 5, 7, 1));
					panelPlanMedio.add(btnCorporacion, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblEstado ----
					lblEstado.setText("Estado:");
					panelPlanMedio.add(lblEstado, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbEstado, cc.xywh(19, 5, 3, 1));
					
					//---- lblCliente ----
					lblCliente.setText("Cliente:");
					panelPlanMedio.add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtCliente, cc.xywh(5, 7, 7, 1));
					panelPlanMedio.add(btnCliente, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblOficina ----
					lblOficina.setText("Oficina:");
					panelPlanMedio.add(lblOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtOficina, cc.xywh(5, 9, 7, 1));
					panelPlanMedio.add(btnOficina, cc.xywh(13, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblOrdenTrabajo ----
					lblOrdenTrabajo.setText("Orden de Trabajo:");
					panelPlanMedio.add(lblOrdenTrabajo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbOrdenTrabajo, cc.xywh(5, 11, 7, 1));
					
					//---- lblOrdenTrabajoDetalle ----
					lblOrdenTrabajoDetalle.setText("Orden Trabajo Detalle:");
					panelPlanMedio.add(lblOrdenTrabajoDetalle, cc.xywh(15, 11, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtOrdenTrabajoDetalle, cc.xywh(19, 11, 3, 1));
					
					//---- lblConcepto ----
					lblConcepto.setText("Concepto:");
					panelPlanMedio.add(lblConcepto, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(txtConcepto, cc.xywh(5, 13, 17, 1));
					panelPlanMedio.add(separatorEstrategia, cc.xywh(3, 17, 19, 1));
					
					//---- lblTarget ----
					lblTarget.setText("Target:");
					panelPlanMedio.add(lblTarget, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbTarget, cc.xywh(5, 19, 7, 1));
					
					//---- lblGuayaquil ----
					lblGuayaquil.setText("Guayaquil:");
					panelPlanMedio.add(lblGuayaquil, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtGuayaquil ----
					txtGuayaquil.setHorizontalAlignment(JTextField.RIGHT);
					panelPlanMedio.add(txtGuayaquil, cc.xy(19, 19));
					panelPlanMedio.add(btnGuayaquil, cc.xywh(21, 19, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblQuito ----
					lblQuito.setText("Quito:");
					panelPlanMedio.add(lblQuito, cc.xywh(17, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtQuito ----
					txtQuito.setHorizontalAlignment(JTextField.RIGHT);
					panelPlanMedio.add(txtQuito, cc.xy(19, 21));
					panelPlanMedio.add(btnQuito, cc.xywh(21, 21, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblPeriodo ----
					lblPeriodo.setText("Per\u00edodo del:");
					panelPlanMedio.add(lblPeriodo, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbPeriodoFechaInicio, cc.xy(5, 23));
					
					//---- lblPeriodoAl ----
					lblPeriodoAl.setText("al:");
					panelPlanMedio.add(lblPeriodoAl, cc.xywh(7, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(cmbPeriodoFechaFin, cc.xy(9, 23));
					
					//---- lblTotal ----
					lblTotal.setText("Total:");
					panelPlanMedio.add(lblTotal, cc.xywh(17, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtTotalCiudad ----
					txtTotalCiudad.setHorizontalAlignment(JTextField.RIGHT);
					panelPlanMedio.add(txtTotalCiudad, cc.xy(19, 23));
					panelPlanMedio.add(btnTotalCiudad, cc.xywh(21, 23, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblCobertura ----
					lblCobertura.setText("Cobertura:");
					panelPlanMedio.add(lblCobertura, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== spCobertura ========
					{
						
						//---- txtCobertura ----
						txtCobertura.setLineWrap(false);
						spCobertura.setViewportView(txtCobertura);
					}
					panelPlanMedio.add(spCobertura, cc.xywh(5, 25, 15, 3));
					
					//---- lblOtrasConsideraciones ----
					lblOtrasConsideraciones.setText("Otras");
					panelPlanMedio.add(lblOtrasConsideraciones, cc.xywh(3, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== spOtrasConsideraciones ========
					{
						spOtrasConsideraciones.setViewportView(txtOtrasConsideraciones);
					}
					panelPlanMedio.add(spOtrasConsideraciones, cc.xywh(5, 29, 15, 3));
					
					//---- lblConsideraciones ----
					lblConsideraciones.setText("Consideraciones:");
					panelPlanMedio.add(lblConsideraciones, cc.xywh(3, 30, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPlanMedio.add(separatorTotales, cc.xywh(3, 35, 19, 1));
					
					//---- lblValorTarifa ----
					lblValorTarifa.setText("Valor Tarifa:");
					panelPlanMedio.add(lblValorTarifa, cc.xywh(3, 37, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtValorTarifa ----
					txtValorTarifa.setHorizontalAlignment(JTextField.RIGHT);
					panelPlanMedio.add(txtValorTarifa, cc.xy(5, 37));
					
					//---- lblValorDescuento ----
					lblValorDescuento.setText("Valor Descuento:");
					panelPlanMedio.add(lblValorDescuento, cc.xywh(3, 39, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtValorDescuento ----
					txtValorDescuento.setHorizontalAlignment(JTextField.RIGHT);
					panelPlanMedio.add(txtValorDescuento, cc.xy(5, 39));
					
					//---- lblModificaciones ----
					lblModificaciones.setText("Modificaciones:");
					panelPlanMedio.add(lblModificaciones, cc.xywh(3, 41, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtModificaciones ----
					txtModificaciones.setHorizontalAlignment(JTextField.RIGHT);
					panelPlanMedio.add(txtModificaciones, cc.xy(5, 41));
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
								{"", null, null},
							},
							new String[] {
								"Tipo", "Del", "Al"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
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
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
							spTelevision.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
							
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
										new ColumnSpec(Sizes.dluX(60)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(12)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(55)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(20)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										FormFactory.DEFAULT_COLSPEC,
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(55)),
										FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
										new ColumnSpec(Sizes.dluX(30)),
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
										new RowSpec(Sizes.dluY(50)),
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
								lblComercial.setText("Comercial:");
								panelTelevision.add(lblComercial, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//======== spTblComercial ========
								{
									
									//---- tblComercial ----
									tblComercial.setModel(new DefaultTableModel(
										new Object[][] {
											{Boolean.FALSE, null, null, null, null, null},
										},
										new String[] {
											" ", "C\u00f3digo", "Nombre", "Der. Programa", "Versi\u00f3n", "Duraci\u00f3n"
										}
									) {
										Class[] columnTypes = new Class[] {
											Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class
										};
										boolean[] columnEditable = new boolean[] {
											true, false, false, false, false, false
										};
										public Class getColumnClass(int columnIndex) {
											return columnTypes[columnIndex];
										}
										public boolean isCellEditable(int rowIndex, int columnIndex) {
											return columnEditable[columnIndex];
										}
									});
									spTblComercial.setViewportView(tblComercial);
								}
								panelTelevision.add(spTblComercial, cc.xywh(5, 3, 21, 5));
								
								//---- btnTvData ----
								btnTvData.setText("Cargar Archivo Excel");
								panelTelevision.add(btnTvData, cc.xywh(5, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
								
								//---- lblCanalTv ----
								lblCanalTv.setText("Canal:");
								panelTelevision.add(lblCanalTv, cc.xywh(13, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//---- lblProgramaTv ----
								lblProgramaTv.setText("Programa:");
								panelTelevision.add(lblProgramaTv, cc.xywh(13, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//======== spArbolTelevision ========
								{
									
									//---- arbolTelevision ----
									arbolTelevision.setVisibleRowCount(20);
									spArbolTelevision.setViewportView(arbolTelevision);
								}
								panelTelevision.add(spArbolTelevision, cc.xywh(3, 13, 7, 27));
								panelTelevision.add(txtCanalTv, cc.xywh(15, 13, 9, 1));
								panelTelevision.add(txtProgramaTv, cc.xywh(15, 15, 9, 1));
								
								//---- lblHoraInicioPrograma ----
								lblHoraInicioPrograma.setText("Hora Inicio:");
								panelTelevision.add(lblHoraInicioPrograma, cc.xywh(13, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtHoraInicioPrograma, cc.xy(15, 17));
								
								//---- lblHoraFinPrograma ----
								lblHoraFinPrograma.setText("Hora Fin:");
								panelTelevision.add(lblHoraFinPrograma, cc.xywh(19, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtHoraFinPrograma, cc.xy(21, 17));
								
								//---- lblDiasPrograma ----
								lblDiasPrograma.setText("D\u00edas:");
								panelTelevision.add(lblDiasPrograma, cc.xywh(13, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//---- lblRatingTv ----
								lblRatingTv.setText("Rating:");
								panelTelevision.add(lblRatingTv, cc.xywh(19, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtRatingTv, cc.xy(21, 19));
								
								//---- lblVCunaTarifa ----
								lblVCunaTarifa.setText("V. Cu\u00f1a Tarifa:");
								panelTelevision.add(lblVCunaTarifa, cc.xywh(13, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtVCunaTarifa, cc.xy(15, 21));
								
								//---- lblVCunaNegocio ----
								lblVCunaNegocio.setText("V. Cu\u00f1a Negocio:");
								panelTelevision.add(lblVCunaNegocio, cc.xywh(19, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtVCunaNegocio, cc.xy(21, 21));
								
								//---- lblComercialTv ----
								lblComercialTv.setText("Comercial:");
								panelTelevision.add(lblComercialTv, cc.xywh(13, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtDiasPrograma, cc.xy(15, 19));
								panelTelevision.add(txtComercialTv, cc.xywh(15, 23, 9, 1));
								
								//---- lblDerechoPrograma ----
								lblDerechoPrograma.setText("Der. Programa:");
								panelTelevision.add(lblDerechoPrograma, cc.xywh(13, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								panelTelevision.add(txtDerechoPrograma, cc.xywh(15, 25, 5, 1));
								
								//---- lblVersionPrograma ----
								lblVersionPrograma.setText("Versi\u00f3n:");
								panelTelevision.add(lblVersionPrograma, cc.xywh(21, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//---- btnAgregarProgramaTv ----
								btnAgregarProgramaTv.setText("Agregar Programa");
								panelTelevision.add(btnAgregarProgramaTv, cc.xywh(15, 29, 3, 1));
								
								//---- btnCrearMapaPautaTv ----
								btnCrearMapaPautaTv.setText("Crear Mapa de Pauta");
								panelTelevision.add(btnCrearMapaPautaTv, cc.xywh(21, 29, 3, 1));
								panelTelevision.add(txtVersionPrograma, cc.xy(23, 25));
								
								//---- btnEliminarProgramaTv ----
								btnEliminarProgramaTv.setText("Eliminar Programa");
								panelTelevision.add(btnEliminarProgramaTv, cc.xy(19, 29));
							}
							spTelevision.setViewportView(panelTelevision);
						}
						tpArbolMedios.addTab("Televisi\u00f3n", spTelevision);
						
						
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
						tpArbolMedios.addTab("Radio", spRadio);
						
						
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
								txtAnchoColumnas.setHorizontalAlignment(JTextField.RIGHT);
								panelPrensa.add(txtAnchoColumnas, cc.xy(13, 11));
								
								//---- lblAltoModulos ----
								lblAltoModulos.setText("Alto (Modulos):");
								panelPrensa.add(lblAltoModulos, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//---- txtAltoModulos ----
								txtAltoModulos.setHorizontalAlignment(JTextField.RIGHT);
								panelPrensa.add(txtAltoModulos, cc.xy(19, 11));
								
								//---- txtAnchoCm ----
								txtAnchoCm.setHorizontalAlignment(JTextField.RIGHT);
								panelPrensa.add(txtAnchoCm, cc.xy(13, 13));
								
								//---- lblAnchoCm ----
								lblAnchoCm.setText("Ancho (Cm.):");
								panelPrensa.add(lblAnchoCm, cc.xywh(11, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//---- lblAltoCm ----
								lblAltoCm.setText("Alto (Cm.):");
								panelPrensa.add(lblAltoCm, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
								
								//---- txtAltoCm ----
								txtAltoCm.setHorizontalAlignment(JTextField.RIGHT);
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
								txtTarifa.setHorizontalAlignment(JTextField.RIGHT);
								panelPrensa.add(txtTarifa, cc.xywh(13, 17, 3, 1));
								
								//---- btnCrearMapaPautaPrensa ----
								btnCrearMapaPautaPrensa.setText("Crear Mapa Pauta");
								panelPrensa.add(btnCrearMapaPautaPrensa, cc.xywh(19, 21, 3, 1));
								panelPrensa.add(txtDia, cc.xy(19, 15));
							}
							spPrensa.setViewportView(panelPrensa);
						}
						tpArbolMedios.addTab("Prensa", spPrensa);
						
					}
					panelProveedorPrograma.add(tpArbolMedios, cc.xywh(3, 9, 7, 5));
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
					
					//======== panelMapaPautaTv ========
					{
						panelMapaPautaTv.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.DLUX5),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
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
					}
					tpMapasPauta.addTab("Televisi\u00f3n", panelMapaPautaTv);
					
					
					//======== panelMapaPautaRadio ========
					{
						panelMapaPautaRadio.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(10)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
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
								new RowSpec(RowSpec.FILL, Sizes.dluY(150), FormSpec.NO_GROW),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(Sizes.dluY(10))
							}));
						
						//======== spTblMapaPautaRadio ========
						{
							
							//---- tblMapaPautaRadio ----
							tblMapaPautaRadio.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null},
								},
								new String[] {
									null, null
								}
							));
							tblMapaPautaRadio.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblMapaPautaRadio.setRowSelectionAllowed(false);
							spTblMapaPautaRadio.setViewportView(tblMapaPautaRadio);
						}
						panelMapaPautaRadio.add(spTblMapaPautaRadio, cc.xywh(3, 3, 5, 5));
					}
					tpMapasPauta.addTab("Radio", panelMapaPautaRadio);
					
					
					//======== panelMapaPautaPrensa ========
					{
						panelMapaPautaPrensa.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.DLUX5),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
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
								new RowSpec(RowSpec.FILL, Sizes.dluY(150), FormSpec.NO_GROW),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(Sizes.DLUY5),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(Sizes.dluY(10))
							}));
						
						//======== spTblMapaPautaPrensa ========
						{
							
							//---- tblMapaPautaPrensa ----
							tblMapaPautaPrensa.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null, null, null},
								},
								new String[] {
									"Diario", "Secci\u00f3n", "Ubicaci\u00f3n", "Formato"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false
								};
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							spTblMapaPautaPrensa.setViewportView(tblMapaPautaPrensa);
						}
						panelMapaPautaPrensa.add(spTblMapaPautaPrensa, cc.xywh(3, 3, 5, 5));
					}
					tpMapasPauta.addTab("Prensa", panelMapaPautaPrensa);
					
				}
				panelMapaPauta.add(tpMapasPauta, cc.xywh(3, 7, 7, 5));
			}
			jtpPlanMedio.addTab("Mapas de Pauta", panelMapaPauta);
			
		}
		add(jtpPlanMedio, cc.xywh(3, 3, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpPlanMedio;
	private JScrollPane spPlanMedio;
	private JPanel panelPlanMedio;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
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
	private JLabel lblOficina;
	private JTextField txtOficina;
	private JButton btnOficina;
	private JLabel lblOrdenTrabajo;
	private JComboBox cmbOrdenTrabajo;
	private JLabel lblOrdenTrabajoDetalle;
	private JTextField txtOrdenTrabajoDetalle;
	private JLabel lblConcepto;
	private JTextField txtConcepto;
	private JComponent separatorEstrategia;
	private JLabel lblTarget;
	private JComboBox cmbTarget;
	private JLabel lblGuayaquil;
	private JTextField txtGuayaquil;
	private JButton btnGuayaquil;
	private JLabel lblQuito;
	private JTextField txtQuito;
	private JButton btnQuito;
	private JLabel lblPeriodo;
	private DateComboBox cmbPeriodoFechaInicio;
	private JLabel lblPeriodoAl;
	private DateComboBox cmbPeriodoFechaFin;
	private JLabel lblTotal;
	private JTextField txtTotalCiudad;
	private JButton btnTotalCiudad;
	private JLabel lblCobertura;
	private JScrollPane spCobertura;
	private JTextArea txtCobertura;
	private JLabel lblOtrasConsideraciones;
	private JScrollPane spOtrasConsideraciones;
	private JTextArea txtOtrasConsideraciones;
	private JLabel lblConsideraciones;
	private JComponent separatorTotales;
	private JLabel lblValorTarifa;
	private JTextField txtValorTarifa;
	private JLabel lblValorDescuento;
	private JTextField txtValorDescuento;
	private JLabel lblModificaciones;
	private JTextField txtModificaciones;
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
	private JButton btnTvData;
	private JLabel lblCanalTv;
	private JLabel lblProgramaTv;
	private JScrollPane spArbolTelevision;
	private CheckBoxTree arbolTelevision;
	private JTextField txtCanalTv;
	private JTextField txtProgramaTv;
	private JLabel lblHoraInicioPrograma;
	private JTextField txtHoraInicioPrograma;
	private JLabel lblHoraFinPrograma;
	private JTextField txtHoraFinPrograma;
	private JLabel lblDiasPrograma;
	private JLabel lblRatingTv;
	private JTextField txtRatingTv;
	private JLabel lblVCunaTarifa;
	private JTextField txtVCunaTarifa;
	private JLabel lblVCunaNegocio;
	private JTextField txtVCunaNegocio;
	private JLabel lblComercialTv;
	private JTextField txtDiasPrograma;
	private JTextField txtComercialTv;
	private JLabel lblDerechoPrograma;
	private JTextField txtDerechoPrograma;
	private JLabel lblVersionPrograma;
	private JButton btnAgregarProgramaTv;
	private JButton btnCrearMapaPautaTv;
	private JTextField txtVersionPrograma;
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
	private JPanel panelMapaPautaTv;
	private JPanel panelMapaPautaRadio;
	private JScrollPane spTblMapaPautaRadio;
	private JTable tblMapaPautaRadio;
	private JPanel panelMapaPautaPrensa;
	private JScrollPane spTblMapaPautaPrensa;
	private JTable tblMapaPautaPrensa;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
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

	public JTextField getTxtOrdenTrabajoDetalle() {
		return txtOrdenTrabajoDetalle;
	}

	public void setTxtOrdenTrabajoDetalle(JTextField txtOrdenTrabajoDetalle) {
		this.txtOrdenTrabajoDetalle = txtOrdenTrabajoDetalle;
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

	public JTextField getTxtValorDescuento() {
		return txtValorDescuento;
	}

	public void setTxtValorDescuento(JTextField txtValorDescuento) {
		this.txtValorDescuento = txtValorDescuento;
	}

	public JTextField getTxtValorTarifa() {
		return txtValorTarifa;
	}

	public void setTxtValorTarifa(JTextField txtValorTarifa) {
		this.txtValorTarifa = txtValorTarifa;
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

	public JButton getBtnCrearMapaPautaTv() {
		return btnCrearMapaPautaTv;
	}

	public void setBtnCrearMapaPautaTv(JButton btnCrearMapaPautaTv) {
		this.btnCrearMapaPautaTv = btnCrearMapaPautaTv;
	}

	public CheckBoxTree getArbolPrensa() {
		return arbolPrensa;
	}

	public void setArbolPrensa(CheckBoxTree arbolPrensa) {
		this.arbolPrensa = arbolPrensa;
	}

	public JPanel getPanelMapaPautaPrensa() {
		return panelMapaPautaPrensa;
	}

	public void setPanelMapaPautaPrensa(JPanel panelMapaPautaPrensa) {
		this.panelMapaPautaPrensa = panelMapaPautaPrensa;
	}

	public JPanel getPanelMapaPautaRadio() {
		return panelMapaPautaRadio;
	}

	public void setPanelMapaPautaRadio(JPanel panelMapaPautaRadio) {
		this.panelMapaPautaRadio = panelMapaPautaRadio;
	}

	public JPanel getPanelMapaPautaTv() {
		return panelMapaPautaTv;
	}

	public void setPanelMapaPautaTv(JPanel panelMapaPautaTv) {
		this.panelMapaPautaTv = panelMapaPautaTv;
	}

	public JScrollPane getSpTblMapaPautaPrensa() {
		return spTblMapaPautaPrensa;
	}

	public void setSpTblMapaPautaPrensa(JScrollPane spTblMapaPautaPrensa) {
		this.spTblMapaPautaPrensa = spTblMapaPautaPrensa;
	}

	public JScrollPane getSpTblMapaPautaRadio() {
		return spTblMapaPautaRadio;
	}

	public void setSpTblMapaPautaRadio(JScrollPane spTblMapaPautaRadio) {
		this.spTblMapaPautaRadio = spTblMapaPautaRadio;
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
}
