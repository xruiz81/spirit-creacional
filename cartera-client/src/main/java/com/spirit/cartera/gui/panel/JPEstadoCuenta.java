package com.spirit.cartera.gui.panel;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
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
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPEstadoCuenta extends ReportModelImpl {
	public JPEstadoCuenta() {
		setName("Estado de Cuenta");
		initComponents();
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpEstadoCuenta = new JideTabbedPane();
		panelGeneral = new JPanel();
		panelEstadoCuenta = new JPanel();
		lblTipoCartera = new JLabel();
		cmbTipoCartera = new JComboBox();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		lblCorporacion = new JLabel();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		lblclienteOficina = new JLabel();
		btnBuscarOficina = new JButton();
		txtOficina = new JTextField();
		lblFechaCorte = new JLabel();
		cmbFechaCorte = new DateComboBox();
		btnConsultar = new JButton();
		panelSaldos = new JPanel();
		lblSaldoTotalDiferidos = new JLabel();
		txtSaldoTotalDiferidos = new JTextField();
		lblPDP = new JLabel();
		txtPDP = new JTextField();
		lblPDC = new JLabel();
		lblSaldo0 = new JLabel();
		lblSaldo31 = new JLabel();
		lblSaldo61 = new JLabel();
		lblSaldo91 = new JLabel();
		lblSaldo120 = new JLabel();
		lblSaldoTotal = new JLabel();
		txtSaldoTotal = new JTextField();
		txtPDC = new JTextField();
		txtSaldo0 = new JTextField();
		txtSaldo31 = new JTextField();
		txtSaldo61 = new JTextField();
		txtSaldo91 = new JTextField();
		txtSaldo120 = new JTextField();
		panelDebitos = new JPanel();
		lblSaldoDebito = new JLabel();
		txtSaldoDebito = new JTextField();
		spDebitos = new JScrollPane();
		tblDebitos = new JTable();
		panelCreditos = new JPanel();
		lblSaldoCredito = new JLabel();
		txtSaldoCredito = new JTextField();
		spCreditos = new JScrollPane();
		tblCreditos = new JTable();
		panelDiferidos = new JPanel();
		lblSaldoDiferido = new JLabel();
		txtSaldoDiferido = new JTextField();
		spDiferidos = new JScrollPane();
		tblDiferidos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"default"));

		//======== jtpEstadoCuenta ========
		{
			
			//======== panelGeneral ========
			{
				panelGeneral.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//======== panelEstadoCuenta ========
				{
					panelEstadoCuenta.setBorder(new TitledBorder("Datos del estado de cuenta"));
					panelEstadoCuenta.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(90)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(25)),
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
							new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
						}));
					
					//---- lblTipoCartera ----
					lblTipoCartera.setText("Tipo de cartera:");
					panelEstadoCuenta.add(lblTipoCartera, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- cmbTipoCartera ----
					cmbTipoCartera.setModel(new DefaultComboBoxModel(new String[] {
						"CLIENTE",
						"PROVEEDOR"
					}));
					panelEstadoCuenta.add(cmbTipoCartera, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtCorporacion ----
					txtCorporacion.setEditable(false);
					panelEstadoCuenta.add(txtCorporacion, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- btnBuscarCorporacion ----
					panelEstadoCuenta.add(btnBuscarCorporacion, cc.xy(9, 5));
					
					//---- lblCorporacion ----
					lblCorporacion.setText("Corporaci\u00f3n:");
					panelEstadoCuenta.add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblCliente ----
					lblCliente.setText("Operador:");
					panelEstadoCuenta.add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- txtCliente ----
					txtCliente.setEditable(false);
					panelEstadoCuenta.add(txtCliente, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- btnBuscarCliente ----
					panelEstadoCuenta.add(btnBuscarCliente, cc.xy(9, 7));
					
					//---- lblclienteOficina ----
					lblclienteOficina.setText("Oficina operador:");
					panelEstadoCuenta.add(lblclienteOficina, cc.xywh(3, 9, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- btnBuscarOficina ----
					panelEstadoCuenta.add(btnBuscarOficina, cc.xy(9, 9));
					
					//---- txtOficina ----
					txtOficina.setEditable(false);
					panelEstadoCuenta.add(txtOficina, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblFechaCorte ----
					lblFechaCorte.setText("Fecha de corte:");
					panelEstadoCuenta.add(lblFechaCorte, cc.xy(3, 11));
					
					//---- cmbFechaCorte ----
					cmbFechaCorte.setEditable(false);
					cmbFechaCorte.setShowNoneButton(false);
					panelEstadoCuenta.add(cmbFechaCorte, cc.xy(5, 11));
					
					//---- btnConsultar ----
					btnConsultar.setText("Consultar");
					panelEstadoCuenta.add(btnConsultar, cc.xywh(7, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				}
				panelGeneral.add(panelEstadoCuenta, cc.xy(1, 1));
				
				//======== panelSaldos ========
				{
					panelSaldos.setBorder(new TitledBorder("Saldos"));
					panelSaldos.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec("max(default;10dlu)"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;50dlu):grow"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;50dlu):grow"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;50dlu):grow"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;10dlu)")
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
							new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.DEFAULT_GROW)
						}));
					
					//---- lblSaldoTotalDiferidos ----
					lblSaldoTotalDiferidos.setText("Saldo Diferido:");
					panelSaldos.add(lblSaldoTotalDiferidos, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- txtSaldoTotalDiferidos ----
					txtSaldoTotalDiferidos.setEditable(false);
					txtSaldoTotalDiferidos.setHorizontalAlignment(JTextField.RIGHT);
					txtSaldoTotalDiferidos.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
					panelSaldos.add(txtSaldoTotalDiferidos, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblPDP ----
					lblPDP.setText("P.D.P:");
					panelSaldos.add(lblPDP, cc.xywh(9, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- txtPDP ----
					txtPDP.setEditable(false);
					txtPDP.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
					txtPDP.setHorizontalAlignment(JTextField.RIGHT);
					panelSaldos.add(txtPDP, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblPDC ----
					lblPDC.setText("P.D.C:");
					panelSaldos.add(lblPDC, cc.xywh(15, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSaldo0 ----
					lblSaldo0.setText("Saldo 0-30:");
					panelSaldos.add(lblSaldo0, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSaldo31 ----
					lblSaldo31.setText("Saldo 31-60:");
					panelSaldos.add(lblSaldo31, cc.xywh(9, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSaldo61 ----
					lblSaldo61.setText("Saldo 61-90:");
					panelSaldos.add(lblSaldo61, cc.xywh(15, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSaldo91 ----
					lblSaldo91.setText("Saldo 91-120:");
					panelSaldos.add(lblSaldo91, cc.xywh(3, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSaldo120 ----
					lblSaldo120.setText("Saldo +120:");
					panelSaldos.add(lblSaldo120, cc.xywh(9, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSaldoTotal ----
					lblSaldoTotal.setText("Saldo Total:");
					lblSaldoTotal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
					panelSaldos.add(lblSaldoTotal, cc.xywh(15, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- txtSaldoTotal ----
					txtSaldoTotal.setEditable(false);
					txtSaldoTotal.setHorizontalAlignment(JTextField.RIGHT);
					txtSaldoTotal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
					panelSaldos.add(txtSaldoTotal, cc.xywh(17, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtPDC ----
					txtPDC.setEditable(false);
					txtPDC.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
					txtPDC.setHorizontalAlignment(JTextField.RIGHT);
					panelSaldos.add(txtPDC, cc.xywh(17, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtSaldo0 ----
					txtSaldo0.setEditable(false);
					txtSaldo0.setHorizontalAlignment(JTextField.RIGHT);
					panelSaldos.add(txtSaldo0, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtSaldo31 ----
					txtSaldo31.setEditable(false);
					txtSaldo31.setHorizontalAlignment(JTextField.RIGHT);
					panelSaldos.add(txtSaldo31, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtSaldo61 ----
					txtSaldo61.setEditable(false);
					txtSaldo61.setHorizontalAlignment(JTextField.RIGHT);
					panelSaldos.add(txtSaldo61, cc.xywh(17, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtSaldo91 ----
					txtSaldo91.setEditable(false);
					txtSaldo91.setHorizontalAlignment(JTextField.RIGHT);
					panelSaldos.add(txtSaldo91, cc.xywh(5, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtSaldo120 ----
					txtSaldo120.setHorizontalAlignment(JTextField.RIGHT);
					txtSaldo120.setEditable(false);
					panelSaldos.add(txtSaldo120, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				panelGeneral.add(panelSaldos, cc.xy(1, 3));
			}
			jtpEstadoCuenta.addTab("General", panelGeneral);
			
			
			//======== panelDebitos ========
			{
				panelDebitos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
					}));
				
				//---- lblSaldoDebito ----
				lblSaldoDebito.setText("Saldo:");
				lblSaldoDebito.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelDebitos.add(lblSaldoDebito, cc.xywh(11, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
				
				//---- txtSaldoDebito ----
				txtSaldoDebito.setEditable(false);
				txtSaldoDebito.setHorizontalAlignment(JTextField.RIGHT);
				txtSaldoDebito.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelDebitos.add(txtSaldoDebito, cc.xywh(13, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//======== spDebitos ========
				{
					spDebitos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					//---- tblDebitos ----
					tblDebitos.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00f3digo", "Fecha Emisi\u00f3n", "Preimpreso", "Autorizaci\u00f3n", "Concepto", "Valor", "Saldo", "Fecha Cartera"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblDebitos.setPreferredScrollableViewportSize(new Dimension(450, 500));
					tblDebitos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblDebitos.setAutoCreateColumnsFromModel(true);
					spDebitos.setViewportView(tblDebitos);
				}
				panelDebitos.add(spDebitos, cc.xywh(3, 3, 11, 3));
			}
			jtpEstadoCuenta.addTab("D\u00e9bitos", panelDebitos);
			
			
			//======== panelCreditos ========
			{
				panelCreditos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
					}));
				
				//---- lblSaldoCredito ----
				lblSaldoCredito.setText("Saldo:");
				lblSaldoCredito.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelCreditos.add(lblSaldoCredito, cc.xywh(11, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
				
				//---- txtSaldoCredito ----
				txtSaldoCredito.setEditable(false);
				txtSaldoCredito.setHorizontalAlignment(JTextField.RIGHT);
				txtSaldoCredito.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelCreditos.add(txtSaldoCredito, cc.xywh(13, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//======== spCreditos ========
				{
					spCreditos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					//---- tblCreditos ----
					tblCreditos.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00f3digo", "Fecha Emisi\u00f3n", "Preimpreso", "Autorizaci\u00f3n", "Concepto", "Valor", "Saldo", "Fecha Cartera"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblCreditos.setPreferredScrollableViewportSize(new Dimension(450, 75));
					tblCreditos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblCreditos.setAutoCreateColumnsFromModel(true);
					spCreditos.setViewportView(tblCreditos);
				}
				panelCreditos.add(spCreditos, cc.xywh(3, 3, 11, 3));
			}
			jtpEstadoCuenta.addTab("Cr\u00e9ditos", panelCreditos);
			
			
			//======== panelDiferidos ========
			{
				panelDiferidos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
					}));
				
				//---- lblSaldoDiferido ----
				lblSaldoDiferido.setText("Saldo:");
				lblSaldoDiferido.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelDiferidos.add(lblSaldoDiferido, cc.xywh(11, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
				
				//---- txtSaldoDiferido ----
				txtSaldoDiferido.setEditable(false);
				txtSaldoDiferido.setHorizontalAlignment(JTextField.RIGHT);
				txtSaldoDiferido.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
				panelDiferidos.add(txtSaldoDiferido, cc.xywh(13, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//======== spDiferidos ========
				{
					spDiferidos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					//---- tblDiferidos ----
					tblDiferidos.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00f3digo", "Fecha Emisi\u00f3n", "Preimpreso", "Autorizaci\u00f3n", "Concepto", "Valor", "Saldo", "Fecha Cartera"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblDiferidos.setPreferredScrollableViewportSize(new Dimension(450, 75));
					tblDiferidos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblDiferidos.setAutoCreateColumnsFromModel(true);
					spDiferidos.setViewportView(tblDiferidos);
				}
				panelDiferidos.add(spDiferidos, cc.xywh(3, 3, 11, 3));
			}
			jtpEstadoCuenta.addTab("Diferidos", panelDiferidos);
			
		}
		add(jtpEstadoCuenta, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarCorporacion.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarOficina.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblDebitos.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblDebitos.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblCreditos.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblCreditos.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblDiferidos.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblDiferidos.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpEstadoCuenta;
	private JPanel panelGeneral;
	private JPanel panelEstadoCuenta;
	private JLabel lblTipoCartera;
	private JComboBox cmbTipoCartera;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JLabel lblCorporacion;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JLabel lblclienteOficina;
	private JButton btnBuscarOficina;
	private JTextField txtOficina;
	private JLabel lblFechaCorte;
	private DateComboBox cmbFechaCorte;
	private JButton btnConsultar;
	private JPanel panelSaldos;
	private JLabel lblSaldoTotalDiferidos;
	private JTextField txtSaldoTotalDiferidos;
	private JLabel lblPDP;
	private JTextField txtPDP;
	private JLabel lblPDC;
	private JLabel lblSaldo0;
	private JLabel lblSaldo31;
	private JLabel lblSaldo61;
	private JLabel lblSaldo91;
	private JLabel lblSaldo120;
	private JLabel lblSaldoTotal;
	private JTextField txtSaldoTotal;
	private JTextField txtPDC;
	private JTextField txtSaldo0;
	private JTextField txtSaldo31;
	private JTextField txtSaldo61;
	private JTextField txtSaldo91;
	private JTextField txtSaldo120;
	private JPanel panelDebitos;
	private JLabel lblSaldoDebito;
	private JTextField txtSaldoDebito;
	private JScrollPane spDebitos;
	private JTable tblDebitos;
	private JPanel panelCreditos;
	private JLabel lblSaldoCredito;
	private JTextField txtSaldoCredito;
	private JScrollPane spCreditos;
	private JTable tblCreditos;
	private JPanel panelDiferidos;
	private JLabel lblSaldoDiferido;
	private JTextField txtSaldoDiferido;
	private JScrollPane spDiferidos;
	private JTable tblDiferidos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
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

	public JButton getBtnBuscarOficina() {
		return btnBuscarOficina;
	}

	public void setBtnBuscarOficina(JButton btnBuscarOficina) {
		this.btnBuscarOficina = btnBuscarOficina;
	}

	public JComboBox getCmbTipoCartera() {
		return cmbTipoCartera;
	}

	public void setCmbTipoCartera(JComboBox cmbTipoCartera) {
		this.cmbTipoCartera = cmbTipoCartera;
	}

	public JideTabbedPane getJtpEstadoCuenta() {
		return jtpEstadoCuenta;
	}

	public void setJtpEstadoCuenta(JideTabbedPane jtpEstadoCuenta) {
		this.jtpEstadoCuenta = jtpEstadoCuenta;
	}

	public JTable getTblCreditos() {
		return tblCreditos;
	}

	public void setTblCreditos(JTable tblCreditos) {
		this.tblCreditos = tblCreditos;
	}

	public JTable getTblDebitos() {
		return tblDebitos;
	}

	public void setTblDebitos(JTable tblDebitos) {
		this.tblDebitos = tblDebitos;
	}

	public JTable getTblDiferidos() {
		return tblDiferidos;
	}

	public void setTblDiferidos(JTable tblDiferidos) {
		this.tblDiferidos = tblDiferidos;
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

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JTextField txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JTextField getTxtPDC() {
		return txtPDC;
	}

	public void setTxtPDC(JTextField txtPDC) {
		this.txtPDC = txtPDC;
	}

	public JTextField getTxtPDP() {
		return txtPDP;
	}

	public void setTxtPDP(JTextField txtPDP) {
		this.txtPDP = txtPDP;
	}

	public JTextField getTxtSaldo0() {
		return txtSaldo0;
	}

	public void setTxtSaldo0(JTextField txtSaldo0) {
		this.txtSaldo0 = txtSaldo0;
	}

	public JTextField getTxtSaldo120() {
		return txtSaldo120;
	}

	public void setTxtSaldo120(JTextField txtSaldo120) {
		this.txtSaldo120 = txtSaldo120;
	}

	public JTextField getTxtSaldo31() {
		return txtSaldo31;
	}

	public void setTxtSaldo31(JTextField txtSaldo31) {
		this.txtSaldo31 = txtSaldo31;
	}

	public JTextField getTxtSaldo61() {
		return txtSaldo61;
	}

	public void setTxtSaldo61(JTextField txtSaldo61) {
		this.txtSaldo61 = txtSaldo61;
	}

	public JTextField getTxtSaldo91() {
		return txtSaldo91;
	}

	public void setTxtSaldo91(JTextField txtSaldo91) {
		this.txtSaldo91 = txtSaldo91;
	}

	public JTextField getTxtSaldoCredito() {
		return txtSaldoCredito;
	}

	public void setTxtSaldoCredito(JTextField txtSaldoCredito) {
		this.txtSaldoCredito = txtSaldoCredito;
	}

	public JTextField getTxtSaldoDebito() {
		return txtSaldoDebito;
	}

	public void setTxtSaldoDebito(JTextField txtSaldoDebito) {
		this.txtSaldoDebito = txtSaldoDebito;
	}

	public JTextField getTxtSaldoDiferido() {
		return txtSaldoDiferido;
	}

	public void setTxtSaldoDiferido(JTextField txtSaldoDiferido) {
		this.txtSaldoDiferido = txtSaldoDiferido;
	}

	public JTextField getTxtSaldoTotal() {
		return txtSaldoTotal;
	}

	public void setTxtSaldoTotal(JTextField txtSaldoTotal) {
		this.txtSaldoTotal = txtSaldoTotal;
	}

	public JTextField getTxtSaldoTotalDiferidos() {
		return txtSaldoTotalDiferidos;
	}

	public void setTxtSaldoTotalDiferidos(JTextField txtSaldoTotalDiferidos) {
		this.txtSaldoTotalDiferidos = txtSaldoTotalDiferidos;
	}

	public DateComboBox getCmbFechaCorte() {
		return cmbFechaCorte;
	}

	public void setCmbFechaCorte(DateComboBox cmbFechaCorte) {
		this.cmbFechaCorte = cmbFechaCorte;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
}
