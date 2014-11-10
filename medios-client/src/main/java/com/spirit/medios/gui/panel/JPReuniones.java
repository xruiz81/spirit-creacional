package com.spirit.medios.gui.panel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.Reader;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
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

public abstract class JPReuniones extends SpiritModelImpl {

	public JPReuniones() throws PropertyVetoException {
		initComponents();
		setName("Reuniones");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpReuniones = new JideTabbedPane();
		panelReunion = new JPanel();
		cbCliente = new JCheckBox();
		lblFechaCreacion = new JLabel();
		cmbFechaCreacion = new DateComboBox();
		lblEstadoReunion = new JLabel();
		cmbEstadoReunion = new JComboBox();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		lblProspectoCliente = new JLabel();
		txtProspectoCliente = new JTextField();
		lblEjecutivo = new JLabel();
		cmbEjecutivo = new JComboBox();
		lblLugarReunion = new JLabel();
		txtLugarReunion = new JTextField();
		lblConCopia = new JLabel();
		txtConCopia = new JTextField();
		lblDescripcion = new JLabel();
		spInforme = new JScrollPane();
		txtDescripcion = new JTextPane();
		panelProductoCliente = new JPanel();
		btnSeleccionarTodo = new JButton();
		btnDeseleccionarTodo = new JButton();
		spCbListProductos = new JScrollPane();
		cbListProductos = new CheckBoxList();
		panelAsistentes = new JPanel();
		lblAsistenteAgencia = new JLabel();
		txtAsistenteAgencia = new JTextField();
		btnBuscarAsistenteAgencia = new JButton();
		lblAsistenteCliente = new JLabel();
		txtAsistenteCliente = new JTextField();
		btnBuscarAsistenteCliente = new JButton();
		panel2 = new JPanel();
		btnAgregarAsistenteAgencia = new JButton();
		btnEliminarAsistenteAgencia = new JButton();
		panel3 = new JPanel();
		btnAgregarAsistenteCliente = new JButton();
		btnEliminarAsistenteCliente = new JButton();
		spAsistenteAgencia = new JScrollPane();
		listAsistenteAgencia = new JList();
		spAsistenteCliente = new JScrollPane();
		listAsistenteCliente = new JList();
		panelArchivo = new JPanel();
		lblTipoArchivo = new JLabel();
		cmbTipoArchivo = new JComboBox();
		lblFechaArchivo = new JLabel();
		cmbFechaArchivo = new DateComboBox();
		lblEstadoArchivo = new JLabel();
		cmbEstadoArchivo = new JComboBox();
		lblURLArchivo = new JLabel();
		txtURLArchivo = new JTextField();
		btnAgregarURLArchivo = new JButton();
		panel4 = new JPanel();
		btnAgregarArchivo = new JButton();
		btnActualizarArchivo = new JButton();
		btnEliminarArchivo = new JButton();
		spArchivo = new JScrollPane();
		tableArchivo = new JTable();
		panelCompromiso = new JPanel();
		lblFechaCompromiso = new JLabel();
		cmbFechaCompromiso = new DateComboBox();
		lblEstadoCompromiso = new JLabel();
		cmbEstadoCompromiso = new JComboBox();
		lblTemaTratado = new JLabel();
		txtTemaTratado = new JTextField();
		lblDescripcionCompromiso = new JLabel();
		spDescripcionCompromiso = new JScrollPane();
		txtDescripcionCompromiso = new JTextPane();
		panel5 = new JPanel();
		btnAgregarCompromiso = new JButton();
		btnActualizarCompromiso = new JButton();
		btnEliminarCompromiso = new JButton();
		spCompromiso = new JScrollPane();
		tableCompromiso = new JTable();
		CellConstraints cc = new CellConstraints();
		modelAsistenteAgencia = new DefaultListModel();
		listAsistenteAgencia = new JList(modelAsistenteAgencia);
		modelAsistenteCliente = new DefaultListModel();
		listAsistenteCliente = new JList(modelAsistenteCliente);

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpReuniones ========
		{

			//======== panelReunion ========
			{
				panelReunion.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(110)),
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
						new RowSpec(Sizes.dluY(60)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- cbCliente ----
				cbCliente.setText("Cliente");
				cbCliente.setHorizontalTextPosition(SwingConstants.LEFT);
				panelReunion.add(cbCliente, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

				//---- lblFechaCreacion ----
				lblFechaCreacion.setText("Fecha:");
				panelReunion.add(lblFechaCreacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReunion.add(cmbFechaCreacion, cc.xy(5, 5));

				//---- lblEstadoReunion ----
				lblEstadoReunion.setText("Estado:");
				panelReunion.add(lblEstadoReunion, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReunion.add(cmbEstadoReunion, cc.xy(11, 5));

				//---- lblCorporacion ----
				lblCorporacion.setText("Corporaci\u00f3n:");
				panelReunion.add(lblCorporacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtCorporacion ----
				txtCorporacion.setEditable(false);
				panelReunion.add(txtCorporacion, cc.xywh(5, 7, 9, 1));
				panelReunion.add(btnBuscarCorporacion, cc.xywh(15, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				panelReunion.add(lblCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtCliente ----
				txtCliente.setEditable(true);
				panelReunion.add(txtCliente, cc.xywh(5, 9, 9, 1));
				panelReunion.add(btnBuscarCliente, cc.xywh(15, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblProspectoCliente ----
				lblProspectoCliente.setText("Prospecto Cliente:");
				panelReunion.add(lblProspectoCliente, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReunion.add(txtProspectoCliente, cc.xywh(5, 11, 9, 1));

				//---- lblEjecutivo ----
				lblEjecutivo.setText("Ejecutivo(a):");
				panelReunion.add(lblEjecutivo, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReunion.add(cmbEjecutivo, cc.xywh(5, 13, 7, 1));

				//---- lblLugarReunion ----
				lblLugarReunion.setText("Lugar de Reuni\u00f3n:");
				panelReunion.add(lblLugarReunion, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReunion.add(txtLugarReunion, cc.xywh(5, 15, 7, 1));

				//---- lblConCopia ----
				lblConCopia.setText("C.C.:");
				panelReunion.add(lblConCopia, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelReunion.add(txtConCopia, cc.xywh(5, 17, 9, 1));

				//---- lblDescripcion ----
				lblDescripcion.setText("Temas Tratados:");
				panelReunion.add(lblDescripcion, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//======== spInforme ========
				{
					txtDescripcion.setContentType("text/plane");
					//UberHandler uh = new UberHandler();
				    //uh.setOutput(txtDescripcion);
				    //txtDescripcion.setTransferHandler(uh);
					 
					spInforme.setViewportView(txtDescripcion);
				}
				panelReunion.add(spInforme, cc.xywh(5, 19, 9, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
			}
			jtpReuniones.addTab("General", panelReunion);


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
				panelProductoCliente.add(btnSeleccionarTodo, cc.xy(9, 3));

				//---- btnDeseleccionarTodo ----
				btnDeseleccionarTodo.setText("Deseleccionar todo");
				panelProductoCliente.add(btnDeseleccionarTodo, cc.xy(9, 5));

				//======== spCbListProductos ========
				{
					spCbListProductos.setViewportView(cbListProductos);
				}
				panelProductoCliente.add(spCbListProductos, cc.xywh(3, 3, 5, 7));
			}
			jtpReuniones.addTab("Productos", panelProductoCliente);


			//======== panelAsistentes ========
			{
				panelAsistentes.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(20)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
						FormFactory.DEFAULT_ROWSPEC,
						new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.dluY(80), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblAsistenteAgencia ----
				lblAsistenteAgencia.setText("Por la Agencia:");
				panelAsistentes.add(lblAsistenteAgencia, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsistentes.add(txtAsistenteAgencia, cc.xywh(5, 3, 3, 1));
				panelAsistentes.add(btnBuscarAsistenteAgencia, cc.xywh(9, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblAsistenteCliente ----
				lblAsistenteCliente.setText("Por el Cliente:");
				panelAsistentes.add(lblAsistenteCliente, cc.xy(13, 3));
				panelAsistentes.add(txtAsistenteCliente, cc.xywh(15, 3, 3, 1));
				panelAsistentes.add(btnBuscarAsistenteCliente, cc.xywh(19, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//======== panel2 ========
				{
					panel2.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarAsistenteAgencia ----
					btnAgregarAsistenteAgencia.setText("A");
					panel2.add(btnAgregarAsistenteAgencia, cc.xy(1, 1));

					//---- btnEliminarAsistenteAgencia ----
					btnEliminarAsistenteAgencia.setText("D");
					panel2.add(btnEliminarAsistenteAgencia, cc.xy(3, 1));
				}
				panelAsistentes.add(panel2, cc.xywh(3, 5, 7, 1));

				//======== panel3 ========
				{
					panel3.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarAsistenteCliente ----
					btnAgregarAsistenteCliente.setText("A");
					panel3.add(btnAgregarAsistenteCliente, cc.xy(1, 1));

					//---- btnEliminarAsistenteCliente ----
					btnEliminarAsistenteCliente.setText("E");
					panel3.add(btnEliminarAsistenteCliente, cc.xy(3, 1));
				}
				panelAsistentes.add(panel3, cc.xywh(13, 5, 7, 1));

				//======== spAsistenteAgencia ========
				{

					//---- listAsistenteAgencia ----
					listAsistenteAgencia.setToolTipText("Empleados por Agencia");
					spAsistenteAgencia.setViewportView(listAsistenteAgencia);
				}
				panelAsistentes.add(spAsistenteAgencia, cc.xywh(3, 7, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//======== spAsistenteCliente ========
				{

					//---- listAsistenteCliente ----
					listAsistenteCliente.setToolTipText("Empleados por Agencia");
					spAsistenteCliente.setViewportView(listAsistenteCliente);
				}
				panelAsistentes.add(spAsistenteCliente, cc.xywh(13, 7, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			}
			jtpReuniones.addTab("Asistentes", panelAsistentes);


			//======== panelArchivo ========
			{
				panelArchivo.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(80)),
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.dluY(70), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblTipoArchivo ----
				lblTipoArchivo.setText("Tipo de Archivo:");
				panelArchivo.add(lblTipoArchivo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivo.add(cmbTipoArchivo, cc.xywh(5, 3, 5, 1));

				//---- lblFechaArchivo ----
				lblFechaArchivo.setText("Fecha:");
				panelArchivo.add(lblFechaArchivo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivo.add(cmbFechaArchivo, cc.xywh(5, 5, 3, 1));

				//---- lblEstadoArchivo ----
				lblEstadoArchivo.setText("Estado:");
				panelArchivo.add(lblEstadoArchivo, cc.xywh(13, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivo.add(cmbEstadoArchivo, cc.xy(15, 5));

				//---- lblURLArchivo ----
				lblURLArchivo.setText("Archivo:");
				panelArchivo.add(lblURLArchivo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtURLArchivo ----
				txtURLArchivo.setEditable(false);
				panelArchivo.add(txtURLArchivo, cc.xywh(5, 7, 11, 1));
				panelArchivo.add(btnAgregarURLArchivo, cc.xywh(17, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

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
				panelArchivo.add(panel4, cc.xywh(3, 9, 17, 1));

				//======== spArchivo ========
				{

					//---- tableArchivo ----
					tableArchivo.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Tipo", "Fecha", "Estado", "URL Descripcion"
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
					tableArchivo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					spArchivo.setViewportView(tableArchivo);
				}
				panelArchivo.add(spArchivo, cc.xywh(3, 11, 17, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			}
			jtpReuniones.addTab("Archivos", panelArchivo);


			//======== panelCompromiso ========
			{
				panelCompromiso.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu)"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
						new RowSpec(Sizes.dluY(80)),
						new RowSpec(RowSpec.TOP, Sizes.DLUY6, FormSpec.NO_GROW),
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.dluY(70), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- lblFechaCompromiso ----
				lblFechaCompromiso.setText("Fecha:");
				panelCompromiso.add(lblFechaCompromiso, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCompromiso.add(cmbFechaCompromiso, cc.xywh(5, 3, 3, 1));

				//---- lblEstadoCompromiso ----
				lblEstadoCompromiso.setText("Estado:");
				panelCompromiso.add(lblEstadoCompromiso, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCompromiso.add(cmbEstadoCompromiso, cc.xy(11, 3));

				//---- lblTemaTratado ----
				lblTemaTratado.setText("Tema:");
				panelCompromiso.add(lblTemaTratado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCompromiso.add(txtTemaTratado, cc.xywh(5, 5, 5, 1));

				//---- lblDescripcionCompromiso ----
				lblDescripcionCompromiso.setText("Descripci\u00f3n:");
				panelCompromiso.add(lblDescripcionCompromiso, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//======== spDescripcionCompromiso ========
				{

					//---- txtDescripcionCompromiso ----
					spDescripcionCompromiso.setViewportView(txtDescripcionCompromiso);
				}
				panelCompromiso.add(spDescripcionCompromiso, cc.xywh(5, 7, 7, 3, CellConstraints.DEFAULT, CellConstraints.FILL));

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

					//---- btnAgregarCompromiso ----
					btnAgregarCompromiso.setText("A");
					panel5.add(btnAgregarCompromiso, cc.xy(1, 1));

					//---- btnActualizarCompromiso ----
					btnActualizarCompromiso.setText("U");
					panel5.add(btnActualizarCompromiso, cc.xy(3, 1));

					//---- btnEliminarCompromiso ----
					btnEliminarCompromiso.setText("D");
					panel5.add(btnEliminarCompromiso, cc.xy(5, 1));
				}
				panelCompromiso.add(panel5, cc.xywh(3, 11, 9, 1));

				//======== spCompromiso ========
				{

					//---- tableCompromiso ----
					tableCompromiso.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Fecha", "Estado", "Tema"
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
					tableCompromiso.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					spCompromiso.setViewportView(tableCompromiso);
				}
				panelCompromiso.add(spCompromiso, cc.xywh(3, 13, 9, 1));
			}
			jtpReuniones.addTab("Acuerdos", panelCompromiso);

		}
		add(jtpReuniones, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpReuniones;
	private JPanel panelReunion;
	private JCheckBox cbCliente;
	private JLabel lblFechaCreacion;
	private DateComboBox cmbFechaCreacion;
	private JLabel lblEstadoReunion;
	private JComboBox cmbEstadoReunion;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JLabel lblProspectoCliente;
	private JTextField txtProspectoCliente;
	private JLabel lblEjecutivo;
	private JComboBox cmbEjecutivo;
	private JLabel lblLugarReunion;
	private JTextField txtLugarReunion;
	private JLabel lblConCopia;
	private JTextField txtConCopia;
	private JLabel lblDescripcion;
	private JScrollPane spInforme;
	private JTextPane txtDescripcion;
	private JPanel panelProductoCliente;
	private JButton btnSeleccionarTodo;
	private JButton btnDeseleccionarTodo;
	private JScrollPane spCbListProductos;
	private CheckBoxList cbListProductos;
	private JPanel panelAsistentes;
	private JLabel lblAsistenteAgencia;
	private JTextField txtAsistenteAgencia;
	private JButton btnBuscarAsistenteAgencia;
	private JLabel lblAsistenteCliente;
	private JTextField txtAsistenteCliente;
	private JButton btnBuscarAsistenteCliente;
	private JPanel panel2;
	private JButton btnAgregarAsistenteAgencia;
	private JButton btnEliminarAsistenteAgencia;
	private JPanel panel3;
	private JButton btnAgregarAsistenteCliente;
	private JButton btnEliminarAsistenteCliente;
	private JScrollPane spAsistenteAgencia;
	private JList listAsistenteAgencia;
	private JScrollPane spAsistenteCliente;
	private JList listAsistenteCliente;
	private JPanel panelArchivo;
	private JLabel lblTipoArchivo;
	private JComboBox cmbTipoArchivo;
	private JLabel lblFechaArchivo;
	private DateComboBox cmbFechaArchivo;
	private JLabel lblEstadoArchivo;
	private JComboBox cmbEstadoArchivo;
	private JLabel lblURLArchivo;
	private JTextField txtURLArchivo;
	private JButton btnAgregarURLArchivo;
	private JPanel panel4;
	private JButton btnAgregarArchivo;
	private JButton btnActualizarArchivo;
	private JButton btnEliminarArchivo;
	private JScrollPane spArchivo;
	private JTable tableArchivo;
	private JPanel panelCompromiso;
	private JLabel lblFechaCompromiso;
	private DateComboBox cmbFechaCompromiso;
	private JLabel lblEstadoCompromiso;
	private JComboBox cmbEstadoCompromiso;
	private JLabel lblTemaTratado;
	private JTextField txtTemaTratado;
	private JLabel lblDescripcionCompromiso;
	private JScrollPane spDescripcionCompromiso;
	private JTextPane txtDescripcionCompromiso;
	private JPanel panel5;
	private JButton btnAgregarCompromiso;
	private JButton btnActualizarCompromiso;
	private JButton btnEliminarCompromiso;
	private JScrollPane spCompromiso;
	private JTable tableCompromiso;
	private DefaultListModel modelAsistenteAgencia;
	private DefaultListModel modelAsistenteCliente;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextPane getTxtDescripcionCompromiso() {
		return txtDescripcionCompromiso;
	}

	public void setTxtDescripcionCompromiso(JTextPane txtDescripcionCompromiso) {
		this.txtDescripcionCompromiso = txtDescripcionCompromiso;
	}

	public JButton getBtnAgregarArchivo() {
		return btnAgregarArchivo;
	}

	public void setBtnAgregarArchivo(JButton btnAgregarArchivo) {
		this.btnAgregarArchivo = btnAgregarArchivo;
	}

	public JButton getBtnAgregarAsistenteAgencia() {
		return btnAgregarAsistenteAgencia;
	}

	public void setBtnAgregarAsistenteAgencia(JButton btnAgregarAsistenteAgencia) {
		this.btnAgregarAsistenteAgencia = btnAgregarAsistenteAgencia;
	}

	public JButton getBtnAgregarAsistenteCliente() {
		return btnAgregarAsistenteCliente;
	}

	public void setBtnAgregarAsistenteCliente(JButton btnAgregarAsistenteCliente) {
		this.btnAgregarAsistenteCliente = btnAgregarAsistenteCliente;
	}

	public JButton getBtnAgregarCompromiso() {
		return btnAgregarCompromiso;
	}

	public void setBtnAgregarCompromiso(JButton btnAgregarCompromiso) {
		this.btnAgregarCompromiso = btnAgregarCompromiso;
	}

	public JButton getBtnAgregarURLArchivo() {
		return btnAgregarURLArchivo;
	}

	public void setBtnAgregarURLArchivo(
			JButton btnAgregarURLArchivoDescripcion) {
		this.btnAgregarURLArchivo = btnAgregarURLArchivoDescripcion;
	}

	public JButton getBtnBuscarAsistenteAgencia() {
		return btnBuscarAsistenteAgencia;
	}

	public void setBtnBuscarAsistenteAgencia(JButton btnBuscarAsistenteAgencia) {
		this.btnBuscarAsistenteAgencia = btnBuscarAsistenteAgencia;
	}

	public JButton getBtnBuscarAsistenteCliente() {
		return btnBuscarAsistenteCliente;
	}

	public void setBtnBuscarAsistenteCliente(JButton btnBuscarAsistenteCliente) {
		this.btnBuscarAsistenteCliente = btnBuscarAsistenteCliente;
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

	public JCheckBox getCbCliente() {
		return cbCliente;
	}

	public void setCbCliente(JCheckBox cbCliente) {
		this.cbCliente = cbCliente;
	}

	public JComboBox getCmbEstadoArchivo() {
		return cmbEstadoArchivo;
	}

	public void setCmbEstadoArchivo(JComboBox cmbEstadoArchivo) {
		this.cmbEstadoArchivo = cmbEstadoArchivo;
	}

	public JComboBox getCmbEstadoCompromiso() {
		return cmbEstadoCompromiso;
	}

	public void setCmbEstadoCompromiso(JComboBox cmbEstadoCompromiso) {
		this.cmbEstadoCompromiso = cmbEstadoCompromiso;
	}

	public JComboBox getCmbEstadoReunion() {
		return cmbEstadoReunion;
	}

	public void setCmbEstadoReunion(JComboBox cmbEstadoReunion) {
		this.cmbEstadoReunion = cmbEstadoReunion;
	}

	public DateComboBox getCmbFechaArchivo() {
		return cmbFechaArchivo;
	}

	public void setCmbFechaArchivo(DateComboBox cmbFechaArchivo) {
		this.cmbFechaArchivo = cmbFechaArchivo;
	}

	public DateComboBox getCmbFechaCompromiso() {
		return cmbFechaCompromiso;
	}

	public void setCmbFechaCompromiso(DateComboBox cmbFechaCompromiso) {
		this.cmbFechaCompromiso = cmbFechaCompromiso;
	}

	public DateComboBox getCmbFechaCreacion() {
		return cmbFechaCreacion;
	}

	public void setCmbFechaCreacion(DateComboBox cmbFechaCreacion) {
		this.cmbFechaCreacion = cmbFechaCreacion;
	}

	public JComboBox getCmbTipoArchivo() {
		return cmbTipoArchivo;
	}

	public void setCmbTipoArchivo(JComboBox cmbTipoArchivo) {
		this.cmbTipoArchivo = cmbTipoArchivo;
	}

	public JList getListAsistenteAgencia() {
		return listAsistenteAgencia;
	}

	public void setListAsistenteAgencia(JList listAsistenteAgencia) {
		this.listAsistenteAgencia = listAsistenteAgencia;
	}

	public JList getListAsistenteCliente() {
		return listAsistenteCliente;
	}

	public void setListAsistenteCliente(JList listAsistenteCliente) {
		this.listAsistenteCliente = listAsistenteCliente;
	}

	public DefaultListModel getModelAsistenteAgencia() {
		return modelAsistenteAgencia;
	}

	public void setModelAsistenteAgencia(DefaultListModel modelAsistenteAgencia) {
		this.modelAsistenteAgencia = modelAsistenteAgencia;
	}

	public DefaultListModel getModelAsistenteCliente() {
		return modelAsistenteCliente;
	}

	public void setModelAsistenteCliente(DefaultListModel modelAsistenteCliente) {
		this.modelAsistenteCliente = modelAsistenteCliente;
	}

	public JTextField getTxtAsistenteAgencia() {
		return txtAsistenteAgencia;
	}

	public void setTxtAsistenteAgencia(JTextField txtAsistenteAgencia) {
		this.txtAsistenteAgencia = txtAsistenteAgencia;
	}

	public JTextField getTxtAsistenteCliente() {
		return txtAsistenteCliente;
	}

	public void setTxtAsistenteCliente(JTextField txtAsistenteCliente) {
		this.txtAsistenteCliente = txtAsistenteCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtProspectoCliente() {
		return txtProspectoCliente;
	}

	public void setTxtProspectoCliente(JTextField txtProspectoCliente) {
		this.txtProspectoCliente = txtProspectoCliente;
	}

	public JTextField getTxtURLArchivo() {
		return txtURLArchivo;
	}

	public void setTxtURLArchivo(JTextField txtURLArchivoDescripcion) {
		this.txtURLArchivo = txtURLArchivoDescripcion;
	}

	public JTable getTableArchivo() {
		return tableArchivo;
	}

	public void setTableArchivo(JTable tableArchivo) {
		this.tableArchivo = tableArchivo;
	}

	public JTable getTableCompromiso() {
		return tableCompromiso;
	}

	public void setTableCompromiso(JTable tableCompromiso) {
		this.tableCompromiso = tableCompromiso;
	}

	public JideTabbedPane getJtpReuniones() {
		return jtpReuniones;
	}

	public void setJtpReuniones(JideTabbedPane jtpReuniones) {
		this.jtpReuniones = jtpReuniones;
	}

	public JButton getBtnActualizarArchivo() {
		return btnActualizarArchivo;
	}

	public void setBtnActualizarArchivo(JButton btnActualizarArchivo) {
		this.btnActualizarArchivo = btnActualizarArchivo;
	}

	public JButton getBtnActualizarCompromiso() {
		return btnActualizarCompromiso;
	}

	public void setBtnActualizarCompromiso(JButton btnActualizarCompromiso) {
		this.btnActualizarCompromiso = btnActualizarCompromiso;
	}

	public JButton getBtnEliminarArchivo() {
		return btnEliminarArchivo;
	}

	public void setBtnEliminarArchivo(JButton btnEliminarArchivo) {
		this.btnEliminarArchivo = btnEliminarArchivo;
	}

	public JButton getBtnEliminarAsistenteAgencia() {
		return btnEliminarAsistenteAgencia;
	}

	public void setBtnEliminarAsistenteAgencia(JButton btnEliminarAsistenteAgencia) {
		this.btnEliminarAsistenteAgencia = btnEliminarAsistenteAgencia;
	}

	public JButton getBtnEliminarAsistenteCliente() {
		return btnEliminarAsistenteCliente;
	}

	public void setBtnEliminarAsistenteCliente(JButton btnEliminarAsistenteCliente) {
		this.btnEliminarAsistenteCliente = btnEliminarAsistenteCliente;
	}

	public JButton getBtnEliminarCompromiso() {
		return btnEliminarCompromiso;
	}

	public void setBtnEliminarCompromiso(JButton btnEliminarCompromiso) {
		this.btnEliminarCompromiso = btnEliminarCompromiso;
	}

	public JTextField getTxtConCopia() {
		return txtConCopia;
	}

	public void setTxtConCopia(JTextField txtConCopia) {
		this.txtConCopia = txtConCopia;
	}

	public JTextField getTxtLugarReunion() {
		return txtLugarReunion;
	}

	public void setTxtLugarReunion(JTextField txtLugarReunion) {
		this.txtLugarReunion = txtLugarReunion;
	}

	public JTextField getTxtTemaTratado() {
		return txtTemaTratado;
	}

	public void setTxtTemaTratado(JTextField txtTemaTratado) {
		this.txtTemaTratado = txtTemaTratado;
	}

	public JComboBox getCmbEjecutivo() {
		return cmbEjecutivo;
	}

	public void setCmbEjecutivo(JComboBox cmbEjecutivo) {
		this.cmbEjecutivo = cmbEjecutivo;
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

	public JLabel getLblCorporacion() {
		return lblCorporacion;
	}

	public void setLblCorporacion(JLabel lblCorporacion) {
		this.lblCorporacion = lblCorporacion;
	}

	public JTextPane getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextPane txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
}

class UberHandler extends TransferHandler {
	  JTextArea output;

	  public void TransferHandler() {
	  }

	  public boolean canImport(JComponent dest, DataFlavor[] flavors) {
	    // you bet we can!
	    return true;
	  }

	  public boolean importData(JComponent src, Transferable transferable) {
	    // Ok, here's the tricky part...
	    println("Receiving data from " + src);
	    println("Transferable object is: " + transferable);
	    println("Valid data flavors: ");
	    DataFlavor[] flavors = transferable.getTransferDataFlavors();
	    DataFlavor listFlavor = null;
	    DataFlavor objectFlavor = null;
	    DataFlavor readerFlavor = null;
	    int lastFlavor = flavors.length - 1;

	    // Check the flavors and see if we find one we like.
	    // If we do, save it.
	    for (int f = 0; f <= lastFlavor; f++) {
	      println("  " + flavors[f]);
	      if (flavors[f].isFlavorJavaFileListType()) {
	        listFlavor = flavors[f];
	      }
	      if (flavors[f].isFlavorSerializedObjectType()) {
	        objectFlavor = flavors[f];
	      }
	      if (flavors[f].isRepresentationClassReader()) {
	        readerFlavor = flavors[f];
	      }
	    }

	    // Ok, now try to display the content of the drop.
	    try {
	      DataFlavor bestTextFlavor = DataFlavor
	          .selectBestTextFlavor(flavors);
	      BufferedReader br = null;
	      String line = null;
	      if (bestTextFlavor != null) {
	        println("Best text flavor: " + bestTextFlavor.getMimeType());
	        println("Content:");
	        Reader r = bestTextFlavor.getReaderForText(transferable);
	        br = new BufferedReader(r);
	        line = br.readLine();
	        while (line != null) {
	          println(line);
	          line = br.readLine();
	        }
	        br.close();
	      } else if (listFlavor != null) {
	        java.util.List list = (java.util.List) transferable
	            .getTransferData(listFlavor);
	        println(list);
	      } else if (objectFlavor != null) {
	        println("Data is a java object:\n"
	            + transferable.getTransferData(objectFlavor));
	      } else if (readerFlavor != null) {
	        println("Data is an InputStream:");
	        br = new BufferedReader((Reader) transferable
	            .getTransferData(readerFlavor));
	        line = br.readLine();
	        while (line != null) {
	          println(line);
	        }
	        br.close();
	      } else {
	        // Don't know this flavor type yet...
	        println("No text representation to show.");
	      }
	      println("\n\n");
	    } catch (Exception e) {
	      println("Caught exception decoding transfer:");
	      println(e);
	      return false;
	    }
	    return true;
	  }

	  public void exportDone(JComponent source, Transferable data, int action) {
	    // Just let us know when it occurs...
	    System.err.println("Export Done.");
	  }

	  public void setOutput(JTextArea jta) {
	    output = jta;
	  }

	  protected void print(Object o) {
	    print(o.toString());
	  }

	  protected void print(String s) {
	    if (output != null) {
	      output.append(s);
	    } else {
	      System.out.println(s);
	    }
	  }

	  protected void println(Object o) {
	    println(o.toString());
	  }

	  protected void println(String s) {
	    if (output != null) {
	      output.append(s);
	      output.append("\n");
	    } else {
	      System.out.println(s);
	    }
	  }

	  protected void println() {
	    println("");
	  }

	  public static void main(String args[]) {
	    JFrame frame = new JFrame("Debugging Drop Zone");
	    frame.setSize(500, 300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JTextArea jta = new JTextArea();
	    frame.getContentPane().add(new JScrollPane(jta));
	    UberHandler uh = new UberHandler();
	    uh.setOutput(jta);
	    jta.setTransferHandler(uh);

	    frame.setVisible(true);
	  }
	}

