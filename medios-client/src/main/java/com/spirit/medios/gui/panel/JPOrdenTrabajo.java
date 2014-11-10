package com.spirit.medios.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPOrdenTrabajo extends SpiritModelImpl {
	public JPOrdenTrabajo() {
		initComponents();
		setName("Orden de Trabajo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpOrdenTrabajo = new JideTabbedPane();
		spGeneral = new JScrollPane();
		panelOrdenTrabajo = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblEstadoOrden = new JLabel();
		cmbEstadoOrden = new JComboBox();
		txtDescripcionOrden = new JTextField();
		lblDescripcionOrden = new JLabel();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		txtCliente = new JTextField();
		lblCliente = new JLabel();
		btnBuscarCliente = new JButton();
		lblFechaLimiteOrden = new JLabel();
		cmbFechaLimiteOrden = new DateComboBox();
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		btnBuscarClienteOficina = new JButton();
		lblFechaEntregaOrden = new JLabel();
		cmbFechaEntregaOrden = new DateComboBox();
		lblCampana = new JLabel();
		cmbCampana = new JComboBox();
		lblAsignadoAOrden = new JLabel();
		cmbAsignadoAOrden = new JComboBox();
		lblDirector = new JLabel();
		cmbDirector = new JComboBox();
		lblUrlPropuestaOrden = new JLabel();
		txtUrlPropuestaOrden = new JTextField();
		btnAgregarArchivoPropuestaOrden = new JButton();
		btnVerArchivoPropuestaOrden = new JButton();
		lblObservacion = new JLabel();
		scrollPane2 = new JScrollPane();
		txtObservacion = new JTextArea();
		panelProductoCliente = new JPanel();
		btnSeleccionarTodo = new JButton();
		btnDeseleccionarTodo = new JButton();
		spCbListProductos = new JScrollPane();
		cbListProductos = new CheckBoxList();
		spDetalle = new JScrollPane();
		panelOrdenTrabajoDetalle = new JPanel();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		lblFechaLimiteOrdenDetalle = new JLabel();
		cmbFechaLimiteOrdenDetalle = new DateComboBox();
		lblEquipo = new JLabel();
		cmbEquipo = new JComboBox();
		lblFechaEntregaOrdenDetall = new JLabel();
		cmbFechaEntregaOrdenDetalle = new DateComboBox();
		lblAsignadoAOrdenDetalle = new JLabel();
		cmbAsignadoAOrdenDetalle = new JComboBox();
		ttJefe = compFactory.createTitle("Jefe");
		lblEstadoOrdenDetalle = new JLabel();
		cmbEstadoOrdenDetalle = new JComboBox();
		lblSubTipo = new JLabel();
		cmbSubTipo = new JComboBox();
		lblUrlDescripcionOrdenDetalle = new JLabel();
		btnAgregarArchivoDescripcion = new JButton();
		txtUrlDescripcionOrdenDetalle = new JTextField();
		btnLimpiarArchivoDescripcion = new JButton();
		lblUrlPropuestaDetalle = new JLabel();
		btnAgregarArchivoPropuestaOrdenDetalle = new JButton();
		txtUrlPropuestaOrdenDetalle = new JTextField();
		btnLimpiarArchivoPropuesta = new JButton();
		lblDescripcion = new JLabel();
		scrollPane1 = new JScrollPane();
		txtDescripcionOrdenDetalle = new JTextArea();
		panel2 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		scOrdenTrabajoDetalle = new JScrollPane();
		tblOrdenTrabajoDetalle = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpOrdenTrabajo ========
		{

			//======== spGeneral ========
			{

				//======== panelOrdenTrabajo ========
				{
					panelOrdenTrabajo.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(110)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(125)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							new RowSpec(Sizes.dluY(12)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							new RowSpec(RowSpec.TOP, Sizes.DLUY8, FormSpec.NO_GROW),
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(100), FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));

					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelOrdenTrabajo.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblEstadoOrden ----
					lblEstadoOrden.setText("Estado:");
					panelOrdenTrabajo.add(lblEstadoOrden, cc.xywh(13, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(cmbEstadoOrden, cc.xywh(15, 3, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtDescripcionOrden, cc.xywh(5, 5, 15, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblDescripcionOrden ----
					lblDescripcionOrden.setText("Descripci\u00f3n:");
					panelOrdenTrabajo.add(lblDescripcionOrden, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- lblCorporacion ----
					lblCorporacion.setText("Corporaci\u00f3n:");
					panelOrdenTrabajo.add(lblCorporacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtCorporacion, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajo.add(btnBuscarCorporacion, cc.xywh(9, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblFechaCreacion ----
					lblFechaCreacion.setText("Fecha de creaci\u00f3n:");
					panelOrdenTrabajo.add(lblFechaCreacion, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtFechaCreacion, cc.xywh(15, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtCliente, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblCliente ----
					lblCliente.setText("Cliente:");
					panelOrdenTrabajo.add(lblCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(btnBuscarCliente, cc.xywh(9, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblFechaLimiteOrden ----
					lblFechaLimiteOrden.setText("Fecha l\u00edmite:");
					panelOrdenTrabajo.add(lblFechaLimiteOrden, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(cmbFechaLimiteOrden, cc.xywh(15, 9, 5, 1));

					//---- lblOficina ----
					lblOficina.setText("Oficina:");
					panelOrdenTrabajo.add(lblOficina, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtOficina, cc.xywh(5, 11, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajo.add(btnBuscarClienteOficina, cc.xywh(9, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblFechaEntregaOrden ----
					lblFechaEntregaOrden.setText("Fecha de entrega:");
					panelOrdenTrabajo.add(lblFechaEntregaOrden, cc.xywh(13, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(cmbFechaEntregaOrden, cc.xywh(15, 11, 5, 1));

					//---- lblCampana ----
					lblCampana.setText("Campa\u00f1a:");
					panelOrdenTrabajo.add(lblCampana, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(cmbCampana, cc.xywh(5, 13, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblAsignadoAOrden ----
					lblAsignadoAOrden.setText("Ejecutivo(a):");
					panelOrdenTrabajo.add(lblAsignadoAOrden, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajo.add(cmbAsignadoAOrden, cc.xywh(5, 15, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblDirector ----
					lblDirector.setText("Director(a):");
					panelOrdenTrabajo.add(lblDirector, cc.xywh(9, 15, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelOrdenTrabajo.add(cmbDirector, cc.xywh(13, 15, 7, 1));

					//---- lblUrlPropuestaOrden ----
					lblUrlPropuestaOrden.setText("Archivo Propuesta:");
					panelOrdenTrabajo.add(lblUrlPropuestaOrden, cc.xywh(3, 17, 3, 1, CellConstraints.LEFT, CellConstraints.FILL));
					panelOrdenTrabajo.add(txtUrlPropuestaOrden, cc.xywh(3, 19, 13, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajo.add(btnAgregarArchivoPropuestaOrden, cc.xywh(17, 19, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelOrdenTrabajo.add(btnVerArchivoPropuestaOrden, cc.xywh(19, 19, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblObservacion ----
					lblObservacion.setText("Observaci\u00f3n:");
					panelOrdenTrabajo.add(lblObservacion, cc.xywh(3, 21, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

					//======== scrollPane2 ========
					{

						//---- txtObservacion ----
						txtObservacion.setLineWrap(true);
						scrollPane2.setViewportView(txtObservacion);
					}
					panelOrdenTrabajo.add(scrollPane2, cc.xywh(3, 23, 17, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				spGeneral.setViewportView(panelOrdenTrabajo);
			}
			jtpOrdenTrabajo.addTab("General", spGeneral);


			//======== panelProductoCliente ========
			{
				panelProductoCliente.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						new RowSpec(RowSpec.CENTER, Sizes.dluY(80), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- btnSeleccionarTodo ----
				btnSeleccionarTodo.setText("Seleccionar todo");
				panelProductoCliente.add(btnSeleccionarTodo, cc.xy(7, 3));

				//---- btnDeseleccionarTodo ----
				btnDeseleccionarTodo.setText("Deseleccionar todo");
				panelProductoCliente.add(btnDeseleccionarTodo, cc.xy(7, 5));

				//======== spCbListProductos ========
				{
					spCbListProductos.setViewportView(cbListProductos);
				}
				panelProductoCliente.add(spCbListProductos, cc.xywh(3, 3, 3, 7));
			}
			jtpOrdenTrabajo.addTab("Productos", panelProductoCliente);


			//======== spDetalle ========
			{

				//======== panelOrdenTrabajoDetalle ========
				{
					panelOrdenTrabajoDetalle.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(170)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.CENTER, Sizes.dluX(12), FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12))
						},
						new RowSpec[] {
							new RowSpec(Sizes.dluY(12)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY14),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(15)),
							new RowSpec(RowSpec.TOP, Sizes.DLUY8, FormSpec.NO_GROW),
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(40), FormSpec.DEFAULT_GROW),
							new RowSpec(RowSpec.TOP, Sizes.DLUY3, FormSpec.NO_GROW),
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(90)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));

					//---- lblTipo ----
					lblTipo.setText("Tipo:");
					panelOrdenTrabajoDetalle.add(lblTipo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbTipo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblFechaLimiteOrdenDetalle ----
					lblFechaLimiteOrdenDetalle.setText("Fecha l\u00edmite:");
					panelOrdenTrabajoDetalle.add(lblFechaLimiteOrdenDetalle, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbFechaLimiteOrdenDetalle, cc.xy(13, 3));

					//---- lblEquipo ----
					lblEquipo.setText("Equipo:");
					panelOrdenTrabajoDetalle.add(lblEquipo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbEquipo, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblFechaEntregaOrdenDetall ----
					lblFechaEntregaOrdenDetall.setText("Fecha de entrega:");
					panelOrdenTrabajoDetalle.add(lblFechaEntregaOrdenDetall, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbFechaEntregaOrdenDetalle, cc.xy(13, 5));

					//---- lblAsignadoAOrdenDetalle ----
					lblAsignadoAOrdenDetalle.setText("Asignado a:");
					panelOrdenTrabajoDetalle.add(lblAsignadoAOrdenDetalle, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbAsignadoAOrdenDetalle, cc.xywh(5, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(ttJefe, cc.xy(7, 7));

					//---- lblEstadoOrdenDetalle ----
					lblEstadoOrdenDetalle.setText("Estado:");
					panelOrdenTrabajoDetalle.add(lblEstadoOrdenDetalle, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbEstadoOrdenDetalle, cc.xywh(13, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblSubTipo ----
					lblSubTipo.setText("Subtipo:");
					panelOrdenTrabajoDetalle.add(lblSubTipo, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(cmbSubTipo, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblUrlDescripcionOrdenDetalle ----
					lblUrlDescripcionOrdenDetalle.setText("Archivo Descripci\u00f3n:");
					panelOrdenTrabajoDetalle.add(lblUrlDescripcionOrdenDetalle, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(btnAgregarArchivoDescripcion, cc.xywh(15, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(txtUrlDescripcionOrdenDetalle, cc.xywh(5, 11, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(btnLimpiarArchivoDescripcion, cc.xywh(17, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblUrlPropuestaDetalle ----
					lblUrlPropuestaDetalle.setText("Archivo Propuesta:");
					panelOrdenTrabajoDetalle.add(lblUrlPropuestaDetalle, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(btnAgregarArchivoPropuestaOrdenDetalle, cc.xywh(15, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(txtUrlPropuestaOrdenDetalle, cc.xywh(5, 13, 9, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelOrdenTrabajoDetalle.add(btnLimpiarArchivoPropuesta, cc.xywh(17, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblDescripcion ----
					lblDescripcion.setText("Descripci\u00f3n:");
					panelOrdenTrabajoDetalle.add(lblDescripcion, cc.xywh(3, 15, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

					//======== scrollPane1 ========
					{

						//---- txtDescripcionOrdenDetalle ----
						txtDescripcionOrdenDetalle.setLineWrap(true);
						scrollPane1.setViewportView(txtDescripcionOrdenDetalle);
					}
					panelOrdenTrabajoDetalle.add(scrollPane1, cc.xywh(3, 17, 15, 1));

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

						//---- btnAgregarDetalle ----
						btnAgregarDetalle.setText("A");
						panel2.add(btnAgregarDetalle, cc.xy(1, 1));

						//---- btnActualizarDetalle ----
						btnActualizarDetalle.setText("U");
						panel2.add(btnActualizarDetalle, cc.xy(3, 1));

						//---- btnEliminarDetalle ----
						btnEliminarDetalle.setText("E");
						panel2.add(btnEliminarDetalle, cc.xy(5, 1));
					}
					panelOrdenTrabajoDetalle.add(panel2, cc.xywh(3, 21, 3, 1));

					//======== scOrdenTrabajoDetalle ========
					{

						//---- tblOrdenTrabajoDetalle ----
						tblOrdenTrabajoDetalle.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Subtipo", "Equipo", "Asignado a", "Fecha L\u00edmite", "Estado"
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
						tblOrdenTrabajoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblOrdenTrabajoDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						scOrdenTrabajoDetalle.setViewportView(tblOrdenTrabajoDetalle);
					}
					panelOrdenTrabajoDetalle.add(scOrdenTrabajoDetalle, cc.xywh(3, 23, 15, 3, CellConstraints.FILL, CellConstraints.FILL));
				}
				spDetalle.setViewportView(panelOrdenTrabajoDetalle);
			}
			jtpOrdenTrabajo.addTab("Detalle", spDetalle);

		}
		add(jtpOrdenTrabajo, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpOrdenTrabajo;
	private JScrollPane spGeneral;
	private JPanel panelOrdenTrabajo;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblEstadoOrden;
	private JComboBox cmbEstadoOrden;
	private JTextField txtDescripcionOrden;
	private JLabel lblDescripcionOrden;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JTextField txtCliente;
	private JLabel lblCliente;
	private JButton btnBuscarCliente;
	private JLabel lblFechaLimiteOrden;
	private DateComboBox cmbFechaLimiteOrden;
	private JLabel lblOficina;
	private JTextField txtOficina;
	private JButton btnBuscarClienteOficina;
	private JLabel lblFechaEntregaOrden;
	private DateComboBox cmbFechaEntregaOrden;
	private JLabel lblCampana;
	private JComboBox cmbCampana;
	private JLabel lblAsignadoAOrden;
	private JComboBox cmbAsignadoAOrden;
	private JLabel lblDirector;
	private JComboBox cmbDirector;
	private JLabel lblUrlPropuestaOrden;
	private JTextField txtUrlPropuestaOrden;
	private JButton btnAgregarArchivoPropuestaOrden;
	private JButton btnVerArchivoPropuestaOrden;
	private JLabel lblObservacion;
	private JScrollPane scrollPane2;
	private JTextArea txtObservacion;
	private JPanel panelProductoCliente;
	private JButton btnSeleccionarTodo;
	private JButton btnDeseleccionarTodo;
	private JScrollPane spCbListProductos;
	private CheckBoxList cbListProductos;
	private JScrollPane spDetalle;
	private JPanel panelOrdenTrabajoDetalle;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JLabel lblFechaLimiteOrdenDetalle;
	private DateComboBox cmbFechaLimiteOrdenDetalle;
	private JLabel lblEquipo;
	private JComboBox cmbEquipo;
	private JLabel lblFechaEntregaOrdenDetall;
	private DateComboBox cmbFechaEntregaOrdenDetalle;
	private JLabel lblAsignadoAOrdenDetalle;
	private JComboBox cmbAsignadoAOrdenDetalle;
	private JLabel ttJefe;
	private JLabel lblEstadoOrdenDetalle;
	private JComboBox cmbEstadoOrdenDetalle;
	private JLabel lblSubTipo;
	private JComboBox cmbSubTipo;
	private JLabel lblUrlDescripcionOrdenDetalle;
	private JButton btnAgregarArchivoDescripcion;
	private JTextField txtUrlDescripcionOrdenDetalle;
	private JButton btnLimpiarArchivoDescripcion;
	private JLabel lblUrlPropuestaDetalle;
	private JButton btnAgregarArchivoPropuestaOrdenDetalle;
	private JTextField txtUrlPropuestaOrdenDetalle;
	private JButton btnLimpiarArchivoPropuesta;
	private JLabel lblDescripcion;
	private JScrollPane scrollPane1;
	private JTextArea txtDescripcionOrdenDetalle;
	private JPanel panel2;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane scOrdenTrabajoDetalle;
	private JTable tblOrdenTrabajoDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbDirector() {
		return cmbDirector;
	}

	public JButton getBtnSeleccionarTodo() {
		return btnSeleccionarTodo;
	}

	public void setBtnSeleccionarTodo(JButton btnSeleccionarTodo) {
		this.btnSeleccionarTodo = btnSeleccionarTodo;
	}

	public JButton getBtnDeseleccionarTodo() {
		return btnDeseleccionarTodo;
	}

	public void setBtnDeseleccionarTodo(JButton btnDeseleccionarTodo) {
		this.btnDeseleccionarTodo = btnDeseleccionarTodo;
	}

	public CheckBoxList getCbListProductos() {
		return cbListProductos;
	}

	public void setCbListProductos(CheckBoxList cbListProductos) {
		this.cbListProductos = cbListProductos;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public void setBtnActualizarDetalle(JButton btnActualizarDetalle) {
		this.btnActualizarDetalle = btnActualizarDetalle;
	}

	public JButton getBtnAgregarArchivoDescripcion() {
		return btnAgregarArchivoDescripcion;
	}

	public void setBtnAgregarArchivoDescripcion(JButton btnAgregarArchivoDescripcion) {
		this.btnAgregarArchivoDescripcion = btnAgregarArchivoDescripcion;
	}

	public JButton getBtnAgregarArchivoPropuestaOrden() {
		return btnAgregarArchivoPropuestaOrden;
	}

	public void setBtnAgregarArchivoPropuestaOrden(
			JButton btnAgregarArchivoPropuestaOrden) {
		this.btnAgregarArchivoPropuestaOrden = btnAgregarArchivoPropuestaOrden;
	}

	public JButton getBtnAgregarArchivoPropuestaOrdenDetalle() {
		return btnAgregarArchivoPropuestaOrdenDetalle;
	}

	public void setBtnAgregarArchivoPropuestaOrdenDetalle(
			JButton btnAgregarArchivoPropuestaOrdenDetalle) {
		this.btnAgregarArchivoPropuestaOrdenDetalle = btnAgregarArchivoPropuestaOrdenDetalle;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarClienteOficina() {
		return btnBuscarClienteOficina;
	}

	public void setBtnBuscarClienteOficina(JButton btnBuscarClienteOficina) {
		this.btnBuscarClienteOficina = btnBuscarClienteOficina;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JComboBox getCmbAsignadoAOrden() {
		return cmbAsignadoAOrden;
	}

	public void setCmbAsignadoAOrden(JComboBox cmbAsignadoAOrden) {
		this.cmbAsignadoAOrden = cmbAsignadoAOrden;
	}

	public JComboBox getCmbAsignadoAOrdenDetalle() {
		return cmbAsignadoAOrdenDetalle;
	}

	public void setCmbAsignadoAOrdenDetalle(JComboBox cmbAsignadoAOrdenDetalle) {
		this.cmbAsignadoAOrdenDetalle = cmbAsignadoAOrdenDetalle;
	}

	public JComboBox getCmbCampana() {
		return cmbCampana;
	}

	public void setCmbCampana(JComboBox cmbCampana) {
		this.cmbCampana = cmbCampana;
	}

	public JComboBox getCmbEquipo() {
		return cmbEquipo;
	}

	public void setCmbEquipo(JComboBox cmbEquipo) {
		this.cmbEquipo = cmbEquipo;
	}

	public JComboBox getCmbEstadoOrden() {
		return cmbEstadoOrden;
	}

	public void setCmbEstadoOrden(JComboBox cmbEstadoOrden) {
		this.cmbEstadoOrden = cmbEstadoOrden;
	}

	public JComboBox getCmbEstadoOrdenDetalle() {
		return cmbEstadoOrdenDetalle;
	}

	public void setCmbEstadoOrdenDetalle(JComboBox cmbEstadoOrdenDetalle) {
		this.cmbEstadoOrdenDetalle = cmbEstadoOrdenDetalle;
	}

	public DateComboBox getCmbFechaLimiteOrden() {
		return cmbFechaLimiteOrden;
	}

	public void setCmbFechaLimiteOrden(DateComboBox cmbFechaLimiteOrden) {
		this.cmbFechaLimiteOrden = cmbFechaLimiteOrden;
	}

	public DateComboBox getCmbFechaLimiteOrdenDetalle() {
		return cmbFechaLimiteOrdenDetalle;
	}

	public void setCmbFechaLimiteOrdenDetalle(DateComboBox cmbFechaLimiteOrdenDetalle) {
		this.cmbFechaLimiteOrdenDetalle = cmbFechaLimiteOrdenDetalle;
	}

	public JComboBox getCmbSubTipo() {
		return cmbSubTipo;
	}

	public void setCmbSubTipo(JComboBox cmbSubTipo) {
		this.cmbSubTipo = cmbSubTipo;
	}

	public JComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public JideTabbedPane getJtpOrdenTrabajo() {
		return jtpOrdenTrabajo;
	}

	public void setJtpOrdenTrabajo(JideTabbedPane jtpOrdenTrabajo) {
		this.jtpOrdenTrabajo = jtpOrdenTrabajo;
	}
	
	public JTable getTblOrdenTrabajoDetalle() {
		return tblOrdenTrabajoDetalle;
	}

	public void setTblOrdenTrabajoDetalle(JTable tblOrdenTrabajoDetalle) {
		this.tblOrdenTrabajoDetalle = tblOrdenTrabajoDetalle;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtDescripcionOrden() {
		return txtDescripcionOrden;
	}

	public void setTxtDescripcionOrden(JTextField txtDescripcionOrden) {
		this.txtDescripcionOrden = txtDescripcionOrden;
	}

	public JTextArea getTxtDescripcionOrdenDetalle() {
		return txtDescripcionOrdenDetalle;
	}

	public void setTxtDescripcionOrdenDetalle(JTextArea txtDescripcionOrdenDetalle) {
		this.txtDescripcionOrdenDetalle = txtDescripcionOrdenDetalle;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public void setTxtFechaCreacion(JTextField txtFechaCreacion) {
		this.txtFechaCreacion = txtFechaCreacion;
	}

	public JTextArea getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextArea txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JTextField txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JTextField getTxtUrlDescripcionOrdenDetalle() {
		return txtUrlDescripcionOrdenDetalle;
	}

	public void setTxtUrlDescripcionOrdenDetalle(
			JTextField txtUrlDescripcionOrdenDetalle) {
		this.txtUrlDescripcionOrdenDetalle = txtUrlDescripcionOrdenDetalle;
	}

	public JTextField getTxtUrlPropuestaOrden() {
		return txtUrlPropuestaOrden;
	}

	public void setTxtUrlPropuestaOrden(JTextField txtUrlPropuestaOrden) {
		this.txtUrlPropuestaOrden = txtUrlPropuestaOrden;
	}

	public JTextField getTxtUrlPropuestaOrdenDetalle() {
		return txtUrlPropuestaOrdenDetalle;
	}

	public void setTxtUrlPropuestaOrdenDetalle(
			JTextField txtUrlPropuestaOrdenDetalle) {
		this.txtUrlPropuestaOrdenDetalle = txtUrlPropuestaOrdenDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JButton getBtnVerArchivoPropuestaOrden() {
		return btnVerArchivoPropuestaOrden;
	}

	public void setBtnVerArchivoPropuestaOrden(JButton btnVerArchivoPropuestaOrden) {
		this.btnVerArchivoPropuestaOrden = btnVerArchivoPropuestaOrden;
	}

	public DateComboBox getCmbFechaEntregaOrden() {
		return cmbFechaEntregaOrden;
	}

	public void setCmbFechaEntregaOrden(DateComboBox cmbFechaEntregaOrden) {
		this.cmbFechaEntregaOrden = cmbFechaEntregaOrden;
	}

	public DateComboBox getCmbFechaEntregaOrdenDetalle() {
		return cmbFechaEntregaOrdenDetalle;
	}

	public void setCmbFechaEntregaOrdenDetalle(
			DateComboBox cmbFechaEntregaOrdenDetalle) {
		this.cmbFechaEntregaOrdenDetalle = cmbFechaEntregaOrdenDetalle;
	}

	public JButton getBtnLimpiarArchivoDescripcion() {
		return btnLimpiarArchivoDescripcion;
	}

	public void setBtnLimpiarArchivoDescripcion(JButton btnLimpiarArchivoDescripcion) {
		this.btnLimpiarArchivoDescripcion = btnLimpiarArchivoDescripcion;
	}

	public JButton getBtnLimpiarArchivoPropuesta() {
		return btnLimpiarArchivoPropuesta;
	}

	public void setBtnLimpiarArchivoPropuesta(JButton btnLimpiarArchivoPropuesta) {
		this.btnLimpiarArchivoPropuesta = btnLimpiarArchivoPropuesta;
	}

	public JLabel getTtJefe() {
		return ttJefe;
	}
}
