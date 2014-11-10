package com.spirit.nomina.gui.panel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPRubrosEventualesContrato extends SpiritModelImpl {
	public JPRubrosEventualesContrato() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblEmpleado = new JLabel();
		btnEmpleado = new JButton();
		txtEmpleado = new JTextField();
		lblContrato = new JLabel();
		btnContrato = new JButton();
		txtContrato = new JTextField();
		lblTipoValor = new JLabel();
		panel1 = new JPanel();
		rbTotal = new JRadioButton();
		rbCuota = new JRadioButton();
		lblRubroEventual = new JLabel();
		cmbRubroEventual = new JComboBox();
		txtValor = new JTextField();
		lblValor = new JLabel();
		lblNumeroPagos = new JLabel();
		txtNumeroPagos = new JTextField();
		lblTipoRolCobro = new JLabel();
		cmbTipoRolCobro = new JComboBox();
		lblFechaCobro = new JLabel();
		cmbFechaCobro = new DateComboBox();
		lblObservacionEventual = new JLabel();
		txtObservacionEventual = new JTextField();
		gfsRubros = compFactory.createSeparator("Condiciones de Pago");
		lblEmitirCheque = new JLabel();
		panelEmitirCheque = new JPanel();
		rbEmitirChequeSi = new JRadioButton();
		rbEmitirChequeNo = new JRadioButton();
		lblTipoRolPago = new JLabel();
		cmbTipoRolPago = new JComboBox();
		lblFechaPago = new JLabel();
		cmbFechaPago = new DateComboBox();
		lblPagoFinMes = new JLabel();
		panelEmitirCheque2 = new JPanel();
		rbFinMesSi = new JRadioButton();
		rbFinMesNo = new JRadioButton();
		panelTblRubrosEventuales = new JPanel();
		btnAgregarRubroEventual = new JButton();
		btnActualizarRubroEventual = new JButton();
		btnRemoverRubroEventual = new JButton();
		tpaneDetalles = new JTabbedPane();
		pnlEmitidos = new JPanel();
		lblFechaInicioEmitido = new JLabel();
		cmbFechaInicioEmitido = new DateComboBox();
		lblFechaFinEmitido = new JLabel();
		btnConsultarEmitidos = new JButton();
		cmbFechaFinEmitido = new DateComboBox();
		spTblRubrosEventuales = new JScrollPane();
		tblRubrosEventualesEmitidos = new JTable();
		pnlAutorizados = new JPanel();
		lblFechaInicioAutorizado = new JLabel();
		cmbFechaInicioAutorizado = new DateComboBox();
		lblFechaFinAutorizado = new JLabel();
		cmbFechaFinAutorizado = new DateComboBox();
		btnConsultarAutorizados = new JButton();
		spTblRubrosEventuales2 = new JScrollPane();
		tblRubrosEventualesAutorizados = new JTable();
		pnlPagados = new JPanel();
		lblFechaInicioPagado = new JLabel();
		cmbFechaInicioPagado = new DateComboBox();
		lblFechaFinPagado = new JLabel();
		cmbFechaFinPagado = new DateComboBox();
		btnConsultarPagados = new JButton();
		spTblRubrosEventuales22 = new JScrollPane();
		tblRubrosEventualesPagados = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Rubros Eventuales por Contrato");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xy(3, 3));

		//---- btnEmpleado ----
		btnEmpleado.setText(" ");
		add(btnEmpleado, cc.xy(11, 3));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 3, 5, 1));

		//---- lblContrato ----
		lblContrato.setText("Contrato:");
		add(lblContrato, cc.xy(3, 5));

		//---- btnContrato ----
		btnContrato.setText(" ");
		add(btnContrato, cc.xy(11, 5));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xywh(5, 5, 3, 1));

		//---- lblTipoValor ----
		lblTipoValor.setText("Tipo de Valor:");
		add(lblTipoValor, cc.xy(17, 5));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- rbTotal ----
			rbTotal.setText("Total");
			rbTotal.setSelected(false);
			panel1.add(rbTotal, cc.xy(1, 1));
			
			//---- rbCuota ----
			rbCuota.setText("Cuota");
			rbCuota.setSelected(true);
			panel1.add(rbCuota, cc.xy(3, 1));
		}
		add(panel1, cc.xywh(19, 5, 3, 1));

		//---- lblRubroEventual ----
		lblRubroEventual.setText("Rubro:");
		add(lblRubroEventual, cc.xy(3, 7));
		add(cmbRubroEventual, cc.xywh(5, 7, 9, 1));

		//---- txtValor ----
		txtValor.setHorizontalAlignment(JTextField.RIGHT);
		add(txtValor, cc.xy(19, 7));

		//---- lblValor ----
		lblValor.setText("Valor:");
		add(lblValor, cc.xy(17, 7));

		//---- lblNumeroPagos ----
		lblNumeroPagos.setText("No. Pagos:");
		add(lblNumeroPagos, cc.xy(23, 7));

		//---- txtNumeroPagos ----
		txtNumeroPagos.setHorizontalAlignment(JTextField.RIGHT);
		txtNumeroPagos.setText("1");
		add(txtNumeroPagos, cc.xy(25, 7));

		//---- lblTipoRolCobro ----
		lblTipoRolCobro.setText("Tipo de Rol Cobro:");
		add(lblTipoRolCobro, cc.xy(3, 9));
		add(cmbTipoRolCobro, cc.xywh(5, 9, 5, 1));

		//---- lblFechaCobro ----
		lblFechaCobro.setText("Fecha Cobro:");
		add(lblFechaCobro, cc.xy(17, 9));

		//---- cmbFechaCobro ----
		cmbFechaCobro.setEditable(false);
		add(cmbFechaCobro, cc.xywh(19, 9, 4, 1));

		//---- lblObservacionEventual ----
		lblObservacionEventual.setText("Observaci\u00f3n:");
		add(lblObservacionEventual, cc.xy(3, 11));
		add(txtObservacionEventual, cc.xywh(5, 11, 21, 1));
		add(gfsRubros, cc.xywh(3, 13, 23, 1));

		//---- lblEmitirCheque ----
		lblEmitirCheque.setText("Emitir Cheque:");
		add(lblEmitirCheque, cc.xy(3, 15));

		//======== panelEmitirCheque ========
		{
			panelEmitirCheque.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				},
				RowSpec.decodeSpecs("default")));
			
			//---- rbEmitirChequeSi ----
			rbEmitirChequeSi.setText("Si");
			rbEmitirChequeSi.setSelected(false);
			panelEmitirCheque.add(rbEmitirChequeSi, cc.xy(1, 1));
			
			//---- rbEmitirChequeNo ----
			rbEmitirChequeNo.setText("No");
			rbEmitirChequeNo.setSelected(true);
			panelEmitirCheque.add(rbEmitirChequeNo, cc.xy(3, 1));
		}
		add(panelEmitirCheque, cc.xywh(5, 15, 2, 1));

		//---- lblTipoRolPago ----
		lblTipoRolPago.setText("Tipo de Rol Pago:");
		add(lblTipoRolPago, cc.xy(3, 17));
		add(cmbTipoRolPago, cc.xywh(5, 17, 5, 1));

		//---- lblFechaPago ----
		lblFechaPago.setText("Fecha Pago:");
		add(lblFechaPago, cc.xy(17, 17));

		//---- cmbFechaPago ----
		cmbFechaPago.setEditable(false);
		add(cmbFechaPago, cc.xywh(19, 17, 3, 1));

		//---- lblPagoFinMes ----
		lblPagoFinMes.setText("Pago \u00fanico a fin de mes:");
		add(lblPagoFinMes, cc.xy(3, 19));

		//======== panelEmitirCheque2 ========
		{
			panelEmitirCheque2.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				},
				RowSpec.decodeSpecs("default")));
			
			//---- rbFinMesSi ----
			rbFinMesSi.setText("Si");
			rbFinMesSi.setSelected(false);
			panelEmitirCheque2.add(rbFinMesSi, cc.xy(1, 1));
			
			//---- rbFinMesNo ----
			rbFinMesNo.setText("No");
			rbFinMesNo.setSelected(true);
			panelEmitirCheque2.add(rbFinMesNo, cc.xy(3, 1));
		}
		add(panelEmitirCheque2, cc.xy(5, 19));

		//======== panelTblRubrosEventuales ========
		{
			panelTblRubrosEventuales.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- btnAgregarRubroEventual ----
			btnAgregarRubroEventual.setText("A");
			panelTblRubrosEventuales.add(btnAgregarRubroEventual, cc.xy(1, 1));
			
			//---- btnActualizarRubroEventual ----
			btnActualizarRubroEventual.setText("U");
			btnActualizarRubroEventual.setHorizontalAlignment(SwingConstants.CENTER);
			panelTblRubrosEventuales.add(btnActualizarRubroEventual, cc.xy(3, 1));
			
			//---- btnRemoverRubroEventual ----
			btnRemoverRubroEventual.setText("D");
			panelTblRubrosEventuales.add(btnRemoverRubroEventual, cc.xy(5, 1));
		}
		add(panelTblRubrosEventuales, cc.xywh(3, 23, 5, 1));

		//======== tpaneDetalles ========
		{
			
			//======== pnlEmitidos ========
			{
				pnlEmitidos.setName("Emitidos");
				pnlEmitidos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX11),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX11)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY5),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY5),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				
				//---- lblFechaInicioEmitido ----
				lblFechaInicioEmitido.setText("Fecha Cobro Incio: ");
				pnlEmitidos.add(lblFechaInicioEmitido, cc.xy(3, 3));
				
				//---- cmbFechaInicioEmitido ----
				cmbFechaInicioEmitido.setEditable(false);
				pnlEmitidos.add(cmbFechaInicioEmitido, cc.xy(5, 3));
				
				//---- lblFechaFinEmitido ----
				lblFechaFinEmitido.setText("Fecha Cobro Fin: ");
				pnlEmitidos.add(lblFechaFinEmitido, cc.xy(9, 3));
				
				//---- btnConsultarEmitidos ----
				btnConsultarEmitidos.setText("Consultar");
				pnlEmitidos.add(btnConsultarEmitidos, cc.xy(15, 3));
				
				//---- cmbFechaFinEmitido ----
				cmbFechaFinEmitido.setEditable(false);
				pnlEmitidos.add(cmbFechaFinEmitido, cc.xy(11, 3));
				
				//======== spTblRubrosEventuales ========
				{
					
					//---- tblRubrosEventualesEmitidos ----
					tblRubrosEventualesEmitidos.setModel(new DefaultTableModel(
						new Object[][] {
							{"", null, null, null, null, null},
						},
						new String[] {
							"Rubro Eventual", "Fecha Cobro", "Tipo de Rol Cobro", "Valor", "Estado", "Identificaci\u00f3n"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Object.class, Object.class, Long.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, true
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubrosEventuales.setViewportView(tblRubrosEventualesEmitidos);
				}
				pnlEmitidos.add(spTblRubrosEventuales, cc.xywh(1, 7, 19, 3));
			}
			tpaneDetalles.addTab("Emitidos", pnlEmitidos);
			
			
			//======== pnlAutorizados ========
			{
				pnlAutorizados.setName("Autorizados");
				pnlAutorizados.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX11),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX11)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY5),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY5),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				
				//---- lblFechaInicioAutorizado ----
				lblFechaInicioAutorizado.setText("Fecha Cobro Incio: ");
				pnlAutorizados.add(lblFechaInicioAutorizado, cc.xy(3, 3));
				
				//---- cmbFechaInicioAutorizado ----
				cmbFechaInicioAutorizado.setEditable(false);
				pnlAutorizados.add(cmbFechaInicioAutorizado, cc.xy(5, 3));
				
				//---- lblFechaFinAutorizado ----
				lblFechaFinAutorizado.setText("Fecha Cobro Fin: ");
				pnlAutorizados.add(lblFechaFinAutorizado, cc.xy(9, 3));
				
				//---- cmbFechaFinAutorizado ----
				cmbFechaFinAutorizado.setEditable(false);
				pnlAutorizados.add(cmbFechaFinAutorizado, cc.xy(11, 3));
				
				//---- btnConsultarAutorizados ----
				btnConsultarAutorizados.setText("Consultar");
				pnlAutorizados.add(btnConsultarAutorizados, cc.xy(15, 3));
				
				//======== spTblRubrosEventuales2 ========
				{
					
					//---- tblRubrosEventualesAutorizados ----
					tblRubrosEventualesAutorizados.setModel(new DefaultTableModel(
						new Object[][] {
							{"", null, null, null, null, null},
						},
						new String[] {
							"Rubro Eventual", "Fecha Cobro", "Tipo de Rol Cobro", "Valor", "Estado", "Identificaci\u00f3n"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Object.class, Object.class, Long.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, true
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubrosEventuales2.setViewportView(tblRubrosEventualesAutorizados);
				}
				pnlAutorizados.add(spTblRubrosEventuales2, cc.xywh(1, 7, 19, 3));
			}
			tpaneDetalles.addTab("Autorizados", pnlAutorizados);
			
			
			//======== pnlPagados ========
			{
				pnlPagados.setName("Pagados");
				pnlPagados.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX11),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(90)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX11)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY5),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY5),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				
				//---- lblFechaInicioPagado ----
				lblFechaInicioPagado.setText("Fecha Cobro Incio: ");
				pnlPagados.add(lblFechaInicioPagado, cc.xy(3, 3));
				
				//---- cmbFechaInicioPagado ----
				cmbFechaInicioPagado.setEditable(false);
				pnlPagados.add(cmbFechaInicioPagado, cc.xy(5, 3));
				
				//---- lblFechaFinPagado ----
				lblFechaFinPagado.setText("Fecha Cobro Fin: ");
				pnlPagados.add(lblFechaFinPagado, cc.xy(9, 3));
				
				//---- cmbFechaFinPagado ----
				cmbFechaFinPagado.setEditable(false);
				pnlPagados.add(cmbFechaFinPagado, cc.xy(11, 3));
				
				//---- btnConsultarPagados ----
				btnConsultarPagados.setText("Consultar");
				pnlPagados.add(btnConsultarPagados, cc.xy(15, 3));
				
				//======== spTblRubrosEventuales22 ========
				{
					
					//---- tblRubrosEventualesPagados ----
					tblRubrosEventualesPagados.setModel(new DefaultTableModel(
						new Object[][] {
							{"", null, null, null, null, null},
						},
						new String[] {
							"Rubro Eventual", "Fecha Cobro", "Tipo de Rol Cobro", "Valor", "Estado", "Identificaci\u00f3n"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Object.class, Object.class, Long.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, true
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubrosEventuales22.setViewportView(tblRubrosEventualesPagados);
				}
				pnlPagados.add(spTblRubrosEventuales22, cc.xywh(1, 7, 19, 3));
			}
			tpaneDetalles.addTab("Pagados", pnlPagados);
			
		}
		add(tpaneDetalles, cc.xywh(3, 25, 25, 3));

		//---- buttonGroupTipoValor ----
		ButtonGroup buttonGroupTipoValor = new ButtonGroup();
		buttonGroupTipoValor.add(rbTotal);
		buttonGroupTipoValor.add(rbCuota);

		//---- buttonGroupEmitirCheque ----
		ButtonGroup buttonGroupEmitirCheque = new ButtonGroup();
		buttonGroupEmitirCheque.add(rbEmitirChequeSi);
		buttonGroupEmitirCheque.add(rbEmitirChequeNo);

		//---- buttonGroupPagoFinMes ----
		ButtonGroup buttonGroupPagoFinMes = new ButtonGroup();
		buttonGroupPagoFinMes.add(rbFinMesSi);
		buttonGroupPagoFinMes.add(rbFinMesNo);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEmpleado;
	private JButton btnEmpleado;
	private JTextField txtEmpleado;
	private JLabel lblContrato;
	private JButton btnContrato;
	private JTextField txtContrato;
	private JLabel lblTipoValor;
	private JPanel panel1;
	private JRadioButton rbTotal;
	private JRadioButton rbCuota;
	private JLabel lblRubroEventual;
	private JComboBox cmbRubroEventual;
	private JTextField txtValor;
	private JLabel lblValor;
	private JLabel lblNumeroPagos;
	private JTextField txtNumeroPagos;
	private JLabel lblTipoRolCobro;
	private JComboBox cmbTipoRolCobro;
	private JLabel lblFechaCobro;
	private DateComboBox cmbFechaCobro;
	private JLabel lblObservacionEventual;
	private JTextField txtObservacionEventual;
	private JComponent gfsRubros;
	private JLabel lblEmitirCheque;
	private JPanel panelEmitirCheque;
	private JRadioButton rbEmitirChequeSi;
	private JRadioButton rbEmitirChequeNo;
	private JLabel lblTipoRolPago;
	private JComboBox cmbTipoRolPago;
	private JLabel lblFechaPago;
	private DateComboBox cmbFechaPago;
	private JLabel lblPagoFinMes;
	private JPanel panelEmitirCheque2;
	private JRadioButton rbFinMesSi;
	private JRadioButton rbFinMesNo;
	private JPanel panelTblRubrosEventuales;
	private JButton btnAgregarRubroEventual;
	private JButton btnActualizarRubroEventual;
	private JButton btnRemoverRubroEventual;
	private JTabbedPane tpaneDetalles;
	private JPanel pnlEmitidos;
	private JLabel lblFechaInicioEmitido;
	private DateComboBox cmbFechaInicioEmitido;
	private JLabel lblFechaFinEmitido;
	private JButton btnConsultarEmitidos;
	private DateComboBox cmbFechaFinEmitido;
	private JScrollPane spTblRubrosEventuales;
	private JTable tblRubrosEventualesEmitidos;
	private JPanel pnlAutorizados;
	private JLabel lblFechaInicioAutorizado;
	private DateComboBox cmbFechaInicioAutorizado;
	private JLabel lblFechaFinAutorizado;
	private DateComboBox cmbFechaFinAutorizado;
	private JButton btnConsultarAutorizados;
	private JScrollPane spTblRubrosEventuales2;
	private JTable tblRubrosEventualesAutorizados;
	private JPanel pnlPagados;
	private JLabel lblFechaInicioPagado;
	private DateComboBox cmbFechaInicioPagado;
	private JLabel lblFechaFinPagado;
	private DateComboBox cmbFechaFinPagado;
	private JButton btnConsultarPagados;
	private JScrollPane spTblRubrosEventuales22;
	private JTable tblRubrosEventualesPagados;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JLabel getLblContrato() {
		return lblContrato;
	}

	public JButton getBtnContrato() {
		return btnContrato;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JLabel getLblTipoValor() {
		return lblTipoValor;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JRadioButton getRbTotal() {
		return rbTotal;
	}

	public JRadioButton getRbCuota() {
		return rbCuota;
	}

	public JLabel getLblRubroEventual() {
		return lblRubroEventual;
	}

	public JComboBox getCmbRubroEventual() {
		return cmbRubroEventual;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public JLabel getLblNumeroPagos() {
		return lblNumeroPagos;
	}

	public JTextField getTxtNumeroPagos() {
		return txtNumeroPagos;
	}

	public JLabel getLblTipoRolCobro() {
		return lblTipoRolCobro;
	}

	public JComboBox getCmbTipoRolCobro() {
		return cmbTipoRolCobro;
	}

	public JLabel getLblFechaCobro() {
		return lblFechaCobro;
	}

	public DateComboBox getCmbFechaCobro() {
		return cmbFechaCobro;
	}

	public JLabel getLblObservacionEventual() {
		return lblObservacionEventual;
	}

	public JTextField getTxtObservacionEventual() {
		return txtObservacionEventual;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JLabel getLblEmitirCheque() {
		return lblEmitirCheque;
	}

	public JPanel getPanelEmitirCheque() {
		return panelEmitirCheque;
	}

	public JRadioButton getRbEmitirChequeSi() {
		return rbEmitirChequeSi;
	}

	public JRadioButton getRbEmitirChequeNo() {
		return rbEmitirChequeNo;
	}

	public JLabel getLblTipoRolPago() {
		return lblTipoRolPago;
	}

	public JComboBox getCmbTipoRolPago() {
		return cmbTipoRolPago;
	}

	public JLabel getLblFechaPago() {
		return lblFechaPago;
	}

	public DateComboBox getCmbFechaPago() {
		return cmbFechaPago;
	}

	public JLabel getLblPagoFinMes() {
		return lblPagoFinMes;
	}

	public JPanel getPanelEmitirCheque2() {
		return panelEmitirCheque2;
	}

	public JRadioButton getRbFinMesSi() {
		return rbFinMesSi;
	}

	public JRadioButton getRbFinMesNo() {
		return rbFinMesNo;
	}

	public JPanel getPanelTblRubrosEventuales() {
		return panelTblRubrosEventuales;
	}

	public JButton getBtnAgregarRubroEventual() {
		return btnAgregarRubroEventual;
	}

	public JButton getBtnActualizarRubroEventual() {
		return btnActualizarRubroEventual;
	}

	public JButton getBtnRemoverRubroEventual() {
		return btnRemoverRubroEventual;
	}

	public JTabbedPane getTpaneDetalles() {
		return tpaneDetalles;
	}

	public JPanel getPnlEmitidos() {
		return pnlEmitidos;
	}

	public JLabel getLblFechaInicioEmitido() {
		return lblFechaInicioEmitido;
	}

	public DateComboBox getCmbFechaInicioEmitido() {
		return cmbFechaInicioEmitido;
	}

	public JLabel getLblFechaFinEmitido() {
		return lblFechaFinEmitido;
	}

	public JButton getBtnConsultarEmitidos() {
		return btnConsultarEmitidos;
	}

	public DateComboBox getCmbFechaFinEmitido() {
		return cmbFechaFinEmitido;
	}

	public JScrollPane getSpTblRubrosEventuales() {
		return spTblRubrosEventuales;
	}

	public JTable getTblRubrosEventualesEmitidos() {
		return tblRubrosEventualesEmitidos;
	}

	public JPanel getPnlAutorizados() {
		return pnlAutorizados;
	}

	public JLabel getLblFechaInicioAutorizado() {
		return lblFechaInicioAutorizado;
	}

	public DateComboBox getCmbFechaInicioAutorizado() {
		return cmbFechaInicioAutorizado;
	}

	public JLabel getLblFechaFinAutorizado() {
		return lblFechaFinAutorizado;
	}

	public DateComboBox getCmbFechaFinAutorizado() {
		return cmbFechaFinAutorizado;
	}

	public JButton getBtnConsultarAutorizados() {
		return btnConsultarAutorizados;
	}

	public JScrollPane getSpTblRubrosEventuales2() {
		return spTblRubrosEventuales2;
	}

	public JTable getTblRubrosEventualesAutorizados() {
		return tblRubrosEventualesAutorizados;
	}

	public JPanel getPnlPagados() {
		return pnlPagados;
	}

	public JLabel getLblFechaInicioPagado() {
		return lblFechaInicioPagado;
	}

	public DateComboBox getCmbFechaInicioPagado() {
		return cmbFechaInicioPagado;
	}

	public JLabel getLblFechaFinPagado() {
		return lblFechaFinPagado;
	}

	public DateComboBox getCmbFechaFinPagado() {
		return cmbFechaFinPagado;
	}

	public JButton getBtnConsultarPagados() {
		return btnConsultarPagados;
	}

	public JScrollPane getSpTblRubrosEventuales22() {
		return spTblRubrosEventuales22;
	}

	public JTable getTblRubrosEventualesPagados() {
		return tblRubrosEventualesPagados;
	}
}
