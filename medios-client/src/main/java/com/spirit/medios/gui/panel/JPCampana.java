package com.spirit.medios.gui.panel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

public abstract class JPCampana extends SpiritModelImpl {

	public JPCampana() {
		initComponents();
		setName("Campaña");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpCampana = new JideTabbedPane();
		panelCampana = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		txtNombre = new JTextField();
		lblCliente = new JLabel();
		btnBuscarCliente = new JButton();
		txtCliente = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblPublicoObjetivo = new JLabel();
		txtPublicoObjetivo = new JTextField();
		txtObservacion = new JTextField();
		lblObservacion = new JLabel();
		panelProductoCliente = new JPanel();
		spCbListProductos = new JScrollPane();
		cbListProductos = new CheckBoxList();
		btnSeleccionarTodo = new JButton();
		btnDeseleccionarTodo = new JButton();
		panelVersion = new JPanel();
		lblCodigoProducto2 = new JLabel();
		txtCodigoVersion = new JTextField();
		lblEstadoProducto2 = new JLabel();
		cmbEstadoVersion = new JComboBox();
		lblMarca2 = new JLabel();
		cmbProducto = new JComboBox();
		btnActualizarProductos = new JButton();
		lblComercial = new JLabel();
		txtNombreVersion = new JTextField();
		panelBotonesVersion = new JPanel();
		btnAgregarVersion = new JButton();
		btnActualizarVersion = new JButton();
		btnEliminarVersion = new JButton();
		spTblVersion = new JScrollPane();
		tblVersion = new JTable();
		panelDetalle = new JPanel();
		cmbZonaCliente = new JComboBox();
		lblParticipacion = new JLabel();
		txtParticipacion = new JTextField();
		lblRestante = new JLabel();
		txtRestante = new JTextField();
		txtDescripcionDetalle = new JTextField();
		spDetalleCampana = new JScrollPane();
		tableDetalleCampana = new JTable();
		lblZonaCliente = new JLabel();
		lblDescripcionDetalle = new JLabel();
		panel2 = new JPanel();
		btnEliminarDetalle = new JButton();
		btnAgregarDetalle = new JButton();
		panelBrief = new JPanel();
		lblTipoBrief = new JLabel();
		cmbTipoBrief = new JComboBox();
		lblURLDescripcion = new JLabel();
		txtURLBrief = new JTextField();
		btnAgregarArchivoBrief = new JButton();
		lblDescripcionBrief = new JLabel();
		spDescripcionBrief = new JScrollPane();
		txtDescripcionBrief = new JTextArea();
		panel3 = new JPanel();
		btnAgregarBrief = new JButton();
		btnActualizarBrief = new JButton();
		btnEliminarBrief = new JButton();
		spBrief = new JScrollPane();
		tableBrief = new JTable();
		panelArchivo = new JPanel();
		lblFechaArchivo = new JLabel();
		cmbTipoArchivo = new JComboBox();
		lblURLArchivo = new JLabel();
		cmbFechaArchivo = new DateComboBox();
		txtURLArchivo = new JTextField();
		btnAgregarURLArchivo = new JButton();
		spArchivo = new JScrollPane();
		tableArchivo = new JTable();
		lblTipoArchivo = new JLabel();
		panel4 = new JPanel();
		btnAgregarArchivo = new JButton();
		btnActualizarArchivo = new JButton();
		btnEliminarArchivo = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpCampana ========
		{

			//======== panelCampana ========
			{
				panelCampana.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
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
						FormFactory.PREF_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));
				((FormLayout)panelCampana.getLayout()).setColumnGroups(new int[][] {{1, 17}, {3, 9}, {5, 13}});

				//---- lblCodigo ----
				lblCodigo.setText("C\u00f3digo:");
				panelCampana.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCampana.add(txtCodigo, cc.xy(5, 3));

				//---- lblNombre ----
				lblNombre.setText("Nombre:");
				panelCampana.add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblCorporacion ----
				lblCorporacion.setText("Corporaci\u00f3n:");
				panelCampana.add(lblCorporacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCampana.add(txtCorporacion, cc.xywh(5, 7, 5, 1));
				panelCampana.add(btnBuscarCorporacion, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				panelCampana.add(txtNombre, cc.xywh(5, 5, 5, 1));

				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				panelCampana.add(lblCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCampana.add(btnBuscarCliente, cc.xywh(11, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				panelCampana.add(txtCliente, cc.xywh(5, 9, 5, 1));

				//---- lblFechaInicio ----
				lblFechaInicio.setText("Fecha Inicio:");
				panelCampana.add(lblFechaInicio, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCampana.add(cmbFechaInicio, cc.xy(5, 11));

				//---- lblEstado ----
				lblEstado.setText("Estado:");
				panelCampana.add(lblEstado, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCampana.add(cmbEstado, cc.xy(5, 13));

				//---- lblPublicoObjetivo ----
				lblPublicoObjetivo.setText("Grupo Objetivo:");
				panelCampana.add(lblPublicoObjetivo, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCampana.add(txtPublicoObjetivo, cc.xywh(5, 15, 9, 1));
				panelCampana.add(txtObservacion, cc.xywh(5, 17, 9, 1));

				//---- lblObservacion ----
				lblObservacion.setText("Observaci\u00f3n:");
				panelCampana.add(lblObservacion, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			jtpCampana.addTab("General", panelCampana);


			//======== panelProductoCliente ========
			{
				panelProductoCliente.setLayout(new FormLayout(
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
						new RowSpec(Sizes.dluY(12)),
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

				//======== spCbListProductos ========
				{
					spCbListProductos.setViewportView(cbListProductos);
				}
				panelProductoCliente.add(spCbListProductos, cc.xywh(3, 3, 5, 7));

				//---- btnSeleccionarTodo ----
				btnSeleccionarTodo.setText("Seleccionar todo");
				panelProductoCliente.add(btnSeleccionarTodo, cc.xy(9, 3));

				//---- btnDeseleccionarTodo ----
				btnDeseleccionarTodo.setText("Deseleccionar todo");
				panelProductoCliente.add(btnDeseleccionarTodo, cc.xy(9, 5));
			}
			jtpCampana.addTab("Productos", panelProductoCliente);


			//======== panelVersion ========
			{
				panelVersion.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(170)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(49)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
						new RowSpec(Sizes.DLUY8),
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

				//---- lblCodigoProducto2 ----
				lblCodigoProducto2.setText("C\u00f3digo:");
				panelVersion.add(lblCodigoProducto2, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelVersion.add(txtCodigoVersion, cc.xy(5, 3));

				//---- lblEstadoProducto2 ----
				lblEstadoProducto2.setText("Estado:");
				panelVersion.add(lblEstadoProducto2, cc.xywh(7, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbEstadoVersion ----
				cmbEstadoVersion.setModel(new DefaultComboBoxModel(new String[] {
					"ACTIVO",
					"INACTIVO"
				}));
				panelVersion.add(cmbEstadoVersion, cc.xywh(9, 3, 2, 1));

				//---- lblMarca2 ----
				lblMarca2.setText("Producto:");
				panelVersion.add(lblMarca2, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelVersion.add(cmbProducto, cc.xywh(5, 5, 3, 1));
				panelVersion.add(btnActualizarProductos, cc.xywh(9, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

				//---- lblComercial ----
				lblComercial.setText("Comercial:");
				panelVersion.add(lblComercial, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelVersion.add(txtNombreVersion, cc.xywh(5, 7, 3, 1));

				//======== panelBotonesVersion ========
				{
					panelBotonesVersion.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					panelBotonesVersion.add(btnAgregarVersion, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelBotonesVersion.add(btnActualizarVersion, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelBotonesVersion.add(btnEliminarVersion, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				panelVersion.add(panelBotonesVersion, cc.xywh(3, 11, 5, 1));

				//======== spTblVersion ========
				{
					spTblVersion.setPreferredSize(new Dimension(452, 100));

					//---- tblVersion ----
					tblVersion.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00f3digo", "Producto", "Comercial", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, true, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblVersion.setPreferredScrollableViewportSize(new Dimension(450, 300));
					tblVersion.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblVersion.setAutoCreateColumnsFromModel(true);
					spTblVersion.setViewportView(tblVersion);
				}
				panelVersion.add(spTblVersion, cc.xywh(3, 13, 9, 5));
			}
			jtpCampana.addTab("Comerciales", panelVersion);


			//======== panelDetalle ========
			{
				panelDetalle.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(25)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(25)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.PREFERRED, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));
				panelDetalle.add(cmbZonaCliente, cc.xywh(5, 3, 3, 1));

				//---- lblParticipacion ----
				lblParticipacion.setText("Participaci\u00f3n (%):");
				panelDetalle.add(lblParticipacion, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtParticipacion ----
				txtParticipacion.setEditable(true);
				panelDetalle.add(txtParticipacion, cc.xy(13, 3));

				//---- lblRestante ----
				lblRestante.setText("Restante (%):");
				panelDetalle.add(lblRestante, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtRestante ----
				txtRestante.setEditable(true);
				panelDetalle.add(txtRestante, cc.xy(17, 3));
				panelDetalle.add(txtDescripcionDetalle, cc.xywh(5, 5, 13, 1));

				//======== spDetalleCampana ========
				{

					//---- tableDetalleCampana ----
					tableDetalleCampana.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Zona", "Participacion %", "Descripcion"
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
					tableDetalleCampana.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tableDetalleCampana.setPreferredScrollableViewportSize(new Dimension(450, 160));
					spDetalleCampana.setViewportView(tableDetalleCampana);
				}
				panelDetalle.add(spDetalleCampana, cc.xywh(3, 11, 15, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblZonaCliente ----
				lblZonaCliente.setText("Zona:");
				panelDetalle.add(lblZonaCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblDescripcionDetalle ----
				lblDescripcionDetalle.setText("Observaci\u00f3n:");
				panelDetalle.add(lblDescripcionDetalle, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

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

					//---- btnEliminarDetalle ----
					btnEliminarDetalle.setText("E");
					panel2.add(btnEliminarDetalle, cc.xy(3, 1));

					//---- btnAgregarDetalle ----
					btnAgregarDetalle.setText("A");
					panel2.add(btnAgregarDetalle, cc.xy(1, 1));
				}
				panelDetalle.add(panel2, cc.xywh(3, 9, 15, 1));
			}
			jtpCampana.addTab("Zonas", panelDetalle);


			//======== panelBrief ========
			{
				panelBrief.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
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
						new RowSpec(Sizes.dluY(40)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.dluY(70), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblTipoBrief ----
				lblTipoBrief.setText("Tipo de Brief:");
				panelBrief.add(lblTipoBrief, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelBrief.add(cmbTipoBrief, cc.xy(5, 3));

				//---- lblURLDescripcion ----
				lblURLDescripcion.setText("Brief:");
				panelBrief.add(lblURLDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtURLBrief ----
				txtURLBrief.setEditable(false);
				panelBrief.add(txtURLBrief, cc.xywh(5, 5, 3, 1));
				panelBrief.add(btnAgregarArchivoBrief, cc.xywh(9, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblDescripcionBrief ----
				lblDescripcionBrief.setText("Observaci\u00f3n:");
				panelBrief.add(lblDescripcionBrief, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//======== spDescripcionBrief ========
				{

					//---- txtDescripcionBrief ----
					txtDescripcionBrief.setLineWrap(true);
					txtDescripcionBrief.setWrapStyleWord(false);
					spDescripcionBrief.setViewportView(txtDescripcionBrief);
				}
				panelBrief.add(spDescripcionBrief, cc.xywh(5, 7, 5, 3, CellConstraints.DEFAULT, CellConstraints.FILL));

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

					//---- btnAgregarBrief ----
					btnAgregarBrief.setText("A");
					panel3.add(btnAgregarBrief, cc.xy(1, 1));

					//---- btnActualizarBrief ----
					btnActualizarBrief.setText("U");
					panel3.add(btnActualizarBrief, cc.xy(3, 1));

					//---- btnEliminarBrief ----
					btnEliminarBrief.setText("D");
					panel3.add(btnEliminarBrief, cc.xy(5, 1));
				}
				panelBrief.add(panel3, cc.xywh(3, 13, 9, 1));

				//======== spBrief ========
				{

					//---- tableBrief ----
					tableBrief.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Tipo", "Descripcion", "URL"
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
					tableBrief.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					spBrief.setViewportView(tableBrief);
				}
				panelBrief.add(spBrief, cc.xywh(3, 15, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			}
			jtpCampana.addTab("Briefs", panelBrief);


			//======== panelArchivo ========
			{
				panelArchivo.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
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
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.dluY(70), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblFechaArchivo ----
				lblFechaArchivo.setText("Fecha:");
				panelArchivo.add(lblFechaArchivo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivo.add(cmbTipoArchivo, cc.xywh(5, 3, 3, 1));

				//---- lblURLArchivo ----
				lblURLArchivo.setText("Archivo:");
				panelArchivo.add(lblURLArchivo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivo.add(cmbFechaArchivo, cc.xywh(5, 5, 3, 1));

				//---- txtURLArchivo ----
				txtURLArchivo.setEditable(false);
				panelArchivo.add(txtURLArchivo, cc.xywh(5, 7, 5, 1));
				panelArchivo.add(btnAgregarURLArchivo, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//======== spArchivo ========
				{

					//---- tableArchivo ----
					tableArchivo.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Tipo", "Fecha", "URL"
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
					tableArchivo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					spArchivo.setViewportView(tableArchivo);
				}
				panelArchivo.add(spArchivo, cc.xywh(3, 13, 11, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblTipoArchivo ----
				lblTipoArchivo.setText("Tipo de Archivo:");
				panelArchivo.add(lblTipoArchivo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

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

					//---- btnAgregarArchivo ----
					btnAgregarArchivo.setText("A");
					panel4.add(btnAgregarArchivo, cc.xy(1, 1));

					//---- btnActualizarArchivo ----
					btnActualizarArchivo.setText("U");
					panel4.add(btnActualizarArchivo, cc.xy(3, 1));

					//---- btnEliminarArchivo ----
					btnEliminarArchivo.setText("D");
					panel4.add(btnEliminarArchivo, cc.xy(5, 1));
				}
				panelArchivo.add(panel4, cc.xywh(3, 11, 11, 1));
			}
			jtpCampana.addTab("Archivos", panelArchivo);

		}
		add(jtpCampana, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpCampana;
	private JPanel panelCampana;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JTextField txtNombre;
	private JLabel lblCliente;
	private JButton btnBuscarCliente;
	private JTextField txtCliente;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblPublicoObjetivo;
	private JTextField txtPublicoObjetivo;
	private JTextField txtObservacion;
	private JLabel lblObservacion;
	private JPanel panelProductoCliente;
	private JScrollPane spCbListProductos;
	private CheckBoxList cbListProductos;
	private JButton btnSeleccionarTodo;
	private JButton btnDeseleccionarTodo;
	private JPanel panelVersion;
	private JLabel lblCodigoProducto2;
	private JTextField txtCodigoVersion;
	private JLabel lblEstadoProducto2;
	private JComboBox cmbEstadoVersion;
	private JLabel lblMarca2;
	private JComboBox cmbProducto;
	private JButton btnActualizarProductos;
	private JLabel lblComercial;
	private JTextField txtNombreVersion;
	private JPanel panelBotonesVersion;
	private JButton btnAgregarVersion;
	private JButton btnActualizarVersion;
	private JButton btnEliminarVersion;
	private JScrollPane spTblVersion;
	private JTable tblVersion;
	private JPanel panelDetalle;
	private JComboBox cmbZonaCliente;
	private JLabel lblParticipacion;
	private JTextField txtParticipacion;
	private JLabel lblRestante;
	private JTextField txtRestante;
	private JTextField txtDescripcionDetalle;
	private JScrollPane spDetalleCampana;
	private JTable tableDetalleCampana;
	private JLabel lblZonaCliente;
	private JLabel lblDescripcionDetalle;
	private JPanel panel2;
	private JButton btnEliminarDetalle;
	private JButton btnAgregarDetalle;
	private JPanel panelBrief;
	private JLabel lblTipoBrief;
	private JComboBox cmbTipoBrief;
	private JLabel lblURLDescripcion;
	private JTextField txtURLBrief;
	private JButton btnAgregarArchivoBrief;
	private JLabel lblDescripcionBrief;
	private JScrollPane spDescripcionBrief;
	private JTextArea txtDescripcionBrief;
	private JPanel panel3;
	private JButton btnAgregarBrief;
	private JButton btnActualizarBrief;
	private JButton btnEliminarBrief;
	private JScrollPane spBrief;
	private JTable tableBrief;
	private JPanel panelArchivo;
	private JLabel lblFechaArchivo;
	private JComboBox cmbTipoArchivo;
	private JLabel lblURLArchivo;
	private DateComboBox cmbFechaArchivo;
	private JTextField txtURLArchivo;
	private JButton btnAgregarURLArchivo;
	private JScrollPane spArchivo;
	private JTable tableArchivo;
	private JLabel lblTipoArchivo;
	private JPanel panel4;
	private JButton btnAgregarArchivo;
	private JButton btnActualizarArchivo;
	private JButton btnEliminarArchivo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnAgregarArchivo() {
		return btnAgregarArchivo;
	}

	public void setBtnAgregarArchivo(JButton btnAgregarArchivo) {
		this.btnAgregarArchivo = btnAgregarArchivo;
	}

	public JButton getBtnAgregarArchivoBrief() {
		return btnAgregarArchivoBrief;
	}

	public void setBtnAgregarArchivoBrief(JButton btnAgregarArchivoBrief) {
		this.btnAgregarArchivoBrief = btnAgregarArchivoBrief;
	}

	public JButton getBtnAgregarBrief() {
		return btnAgregarBrief;
	}

	public void setBtnAgregarBrief(JButton btnAgregarBrief) {
		this.btnAgregarBrief = btnAgregarBrief;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnAgregarURLArchivo() {
		return btnAgregarURLArchivo;
	}

	public void setBtnAgregarURLArchivo(
			JButton btnAgregarURLArchivoDescripcion) {
		this.btnAgregarURLArchivo = btnAgregarURLArchivoDescripcion;
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

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFechaArchivo() {
		return cmbFechaArchivo;
	}

	public void setCmbFechaArchivo(DateComboBox cmbFechaArchivo) {
		this.cmbFechaArchivo = cmbFechaArchivo;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JComboBox getCmbTipoArchivo() {
		return cmbTipoArchivo;
	}

	public void setCmbTipoArchivo(JComboBox cmbTipoArchivo) {
		this.cmbTipoArchivo = cmbTipoArchivo;
	}

	public JComboBox getCmbTipoBrief() {
		return cmbTipoBrief;
	}

	public void setCmbTipoBrief(JComboBox cmbTipoBrief) {
		this.cmbTipoBrief = cmbTipoBrief;
	}

	public JComboBox getCmbZonaCliente() {
		return cmbZonaCliente;
	}

	public void setCmbZonaCliente(JComboBox cmbZonaCliente) {
		this.cmbZonaCliente = cmbZonaCliente;
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

	public JTextArea getTxtDescripcionBrief() {
		return txtDescripcionBrief;
	}

	public void setTxtDescripcionBrief(JTextArea txtDescripcionBrief) {
		this.txtDescripcionBrief = txtDescripcionBrief;
	}

	public JTextField getTxtDescripcionDetalle() {
		return txtDescripcionDetalle;
	}

	public void setTxtDescripcionDetalle(JTextField txtDescripcionDetalle) {
		this.txtDescripcionDetalle = txtDescripcionDetalle;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JTextField getTxtParticipacion() {
		return txtParticipacion;
	}

	public void setTxtParticipacion(JTextField txtParticipacion) {
		this.txtParticipacion = txtParticipacion;
	}

	public JTextField getTxtPublicoObjetivo() {
		return txtPublicoObjetivo;
	}

	public void setTxtPublicoObjetivo(JTextField txtPublicoObjetivo) {
		this.txtPublicoObjetivo = txtPublicoObjetivo;
	}

	public JTextField getTxtURLArchivo() {
		return txtURLArchivo;
	}

	public void setTxtURLArchivo(JTextField txtURLArchivoDescripcion) {
		this.txtURLArchivo = txtURLArchivoDescripcion;
	}

	public JTextField getTxtURLBrief() {
		return txtURLBrief;
	}

	public void setTxtURLBrief(JTextField txtURLBrief) {
		this.txtURLBrief = txtURLBrief;
	}

	public JTable getTableArchivo() {
		return tableArchivo;
	}

	public void setTableArchivo(JTable tableArchivo) {
		this.tableArchivo = tableArchivo;
	}

	public JTable getTableBrief() {
		return tableBrief;
	}

	public void setTableBrief(JTable tableBrief) {
		this.tableBrief = tableBrief;
	}

	public JTable getTableDetalleCampana() {
		return tableDetalleCampana;
	}

	public void setTableDetalleCampana(JTable tableDetalleCampana) {
		this.tableDetalleCampana = tableDetalleCampana;
	}

	public JTextField getTxtRestante() {
		return txtRestante;
	}

	public void setTxtRestante(JTextField txtRestante) {
		this.txtRestante = txtRestante;
	}

	public JideTabbedPane getJtpCampana() {
		return jtpCampana;
	}

	public void setJtpCampana(JideTabbedPane jtpCampana) {
		this.jtpCampana = jtpCampana;
	}

	public JButton getBtnActualizarArchivo() {
		return btnActualizarArchivo;
	}

	public void setBtnActualizarArchivo(JButton btnActualizarArchivo) {
		this.btnActualizarArchivo = btnActualizarArchivo;
	}

	public JButton getBtnActualizarBrief() {
		return btnActualizarBrief;
	}

	public void setBtnActualizarBrief(JButton btnActualizarBrief) {
		this.btnActualizarBrief = btnActualizarBrief;
	}

	public JButton getBtnEliminarArchivo() {
		return btnEliminarArchivo;
	}

	public void setBtnEliminarArchivo(JButton btnEliminarArchivo) {
		this.btnEliminarArchivo = btnEliminarArchivo;
	}

	public JButton getBtnEliminarBrief() {
		return btnEliminarBrief;
	}

	public void setBtnEliminarBrief(JButton btnEliminarBrief) {
		this.btnEliminarBrief = btnEliminarBrief;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JButton getBtnDeseleccionarTodo() {
		return btnDeseleccionarTodo;
	}

	public void setBtnDeseleccionarTodo(JButton btnDeseleccionarTodo) {
		this.btnDeseleccionarTodo = btnDeseleccionarTodo;
	}

	public JButton getBtnSeleccionarTodo() {
		return btnSeleccionarTodo;
	}

	public void setBtnSeleccionarTodo(JButton btnSeleccionarTodo) {
		this.btnSeleccionarTodo = btnSeleccionarTodo;
	}

	public CheckBoxList getCbListProductos() {
		return cbListProductos;
	}

	public void setCbListProductos(CheckBoxList cbListProductos) {
		this.cbListProductos = cbListProductos;
	}

	public JTextField getTxtCodigoVersion() {
		return txtCodigoVersion;
	}

	public JComboBox getCmbEstadoVersion() {
		return cmbEstadoVersion;
	}

	public JComboBox getCmbProducto() {
		return cmbProducto;
	}

	public JTextField getTxtNombreVersion() {
		return txtNombreVersion;
	}

	public JButton getBtnAgregarVersion() {
		return btnAgregarVersion;
	}

	public JButton getBtnActualizarVersion() {
		return btnActualizarVersion;
	}

	public JButton getBtnEliminarVersion() {
		return btnEliminarVersion;
	}

	public JTable getTblVersion() {
		return tblVersion;
	}

	public void setTxtCodigoVersion(JTextField txtCodigoVersion) {
		this.txtCodigoVersion = txtCodigoVersion;
	}

	public void setCmbEstadoVersion(JComboBox cmbEstadoVersion) {
		this.cmbEstadoVersion = cmbEstadoVersion;
	}

	public void setCmbProducto(JComboBox cmbProducto) {
		this.cmbProducto = cmbProducto;
	}

	public void setTxtNombreVersion(JTextField txtNombreVersion) {
		this.txtNombreVersion = txtNombreVersion;
	}

	public void setBtnAgregarVersion(JButton btnAgregarVersion) {
		this.btnAgregarVersion = btnAgregarVersion;
	}

	public void setBtnActualizarVersion(JButton btnActualizarVersion) {
		this.btnActualizarVersion = btnActualizarVersion;
	}

	public void setBtnEliminarVersion(JButton btnEliminarVersion) {
		this.btnEliminarVersion = btnEliminarVersion;
	}

	public void setTblVersion(JTable tblVersion) {
		this.tblVersion = tblVersion;
	}

	public JButton getBtnActualizarProductos() {
		return btnActualizarProductos;
	}

	public void setBtnActualizarProductos(JButton btnActualizarProductos) {
		this.btnActualizarProductos = btnActualizarProductos;
	}
}
