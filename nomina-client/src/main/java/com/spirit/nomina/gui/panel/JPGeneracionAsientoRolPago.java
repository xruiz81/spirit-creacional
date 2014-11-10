package com.spirit.nomina.gui.panel;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
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

public abstract class JPGeneracionAsientoRolPago extends SpiritModelImpl {
	public JPGeneracionAsientoRolPago() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		cmbFechaInicio = new DateComboBox();
		lblFechaInicio = new JLabel();
		lblFechaFin = new JLabel();
		btnFiltrar = new JButton();
		cmbFechaFin = new DateComboBox();
		splitPnPagoRol = new JSplitPane();
		panelRolPago = new JPanel();
		gfsRubros = compFactory.createSeparator("Rol de Pago");
		spTblRolPago = new JScrollPane();
		tblRolPago = new JTable();
		panelDetalleRolPago = new JPanel();
		btnSeleccionarTodos = new JButton();
		gfsRubros2 = compFactory.createSeparator("Detalle Rol de Pago");
		spTblRolPagoDetalleQyM = new JScrollPane();
		tblRolPagoDetalleQyM = new JTable();
		spTblRolPagoDetalleAyD = new JScrollPane();
		tblRolPagoDetalleAportesDecimos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Generacion de Asientos de Rol de Pago");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));
		add(cmbFechaInicio, cc.xywh(5, 3, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio: ");
		add(lblFechaInicio, cc.xy(3, 3));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(3, 5));

		//---- btnFiltrar ----
		btnFiltrar.setText("Filtrar");
		add(btnFiltrar, cc.xy(9, 5));
		add(cmbFechaFin, cc.xywh(5, 5, 3, 1));

		//======== splitPnPagoRol ========
		{
			splitPnPagoRol.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPnPagoRol.setBorder(null);
			
			//======== panelRolPago ========
			{
				panelRolPago.setMinimumSize(new Dimension(70, 120));
				panelRolPago.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				panelRolPago.add(gfsRubros, cc.xy(1, 1));
				
				//======== spTblRolPago ========
				{
					
					//---- tblRolPago ----
					tblRolPago.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, "", null, null},
						},
						new String[] {
							"Tipo de Rol", "Tipo de Contrato", "Mes", "A\u00f1o", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblRolPago.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					spTblRolPago.setViewportView(tblRolPago);
				}
				panelRolPago.add(spTblRolPago, cc.xywh(1, 3, 1, 3));
			}
			splitPnPagoRol.setLeftComponent(panelRolPago);
			
			//======== panelDetalleRolPago ========
			{
				panelDetalleRolPago.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				
				//---- btnSeleccionarTodos ----
				btnSeleccionarTodos.setText("Seleccionar Todo");
				panelDetalleRolPago.add(btnSeleccionarTodos, cc.xy(1, 3));
				panelDetalleRolPago.add(gfsRubros2, cc.xywh(1, 5, 3, 1));
				
				//======== spTblRolPagoDetalleQyM ========
				{
					
					//---- tblRolPagoDetalleQyM ----
					tblRolPagoDetalleQyM.setModel(new DefaultTableModel(
						new Object[][] {
							{Boolean.FALSE, null, null, null, null, null, null, null},
						},
						new String[] {
							" ", "Nombre", "Total Ingresos", "Total Egresos", "Total", "Forma Pago", "Cuenta Bancaria", "# Cheque"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Double.class, Double.class, Double.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, false, false, false, false, true
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblRolPagoDetalleQyM.setColumnSelectionAllowed(false);
					tblRolPagoDetalleQyM.setCellSelectionEnabled(true);
					tblRolPagoDetalleQyM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblRolPagoDetalleQyM.setViewportView(tblRolPagoDetalleQyM);
				}
				panelDetalleRolPago.add(spTblRolPagoDetalleQyM, cc.xywh(1, 7, 3, 3));
			}
			splitPnPagoRol.setRightComponent(panelDetalleRolPago);
		}
		add(splitPnPagoRol, cc.xywh(3, 9, 9, 1));

		//======== spTblRolPagoDetalleAyD ========
		{
			
			//---- tblRolPagoDetalleAportesDecimos ----
			tblRolPagoDetalleAportesDecimos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, ""},
				},
				new String[] {
					" ", "Nombre", "Total", "Forma Pago", "Cuenta Bancaria", "# Cheque"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Double.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, true
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPagoDetalleAportesDecimos.setCellSelectionEnabled(true);
			tblRolPagoDetalleAportesDecimos.setColumnSelectionAllowed(false);
			tblRolPagoDetalleAportesDecimos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			spTblRolPagoDetalleAyD.setViewportView(tblRolPagoDetalleAportesDecimos);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JButton btnFiltrar;
	private DateComboBox cmbFechaFin;
	private JSplitPane splitPnPagoRol;
	private JPanel panelRolPago;
	private JComponent gfsRubros;
	private JScrollPane spTblRolPago;
	private JTable tblRolPago;
	private JPanel panelDetalleRolPago;
	private JButton btnSeleccionarTodos;
	private JComponent gfsRubros2;
	private JScrollPane spTblRolPagoDetalleQyM;
	private JTable tblRolPagoDetalleQyM;
	private JScrollPane spTblRolPagoDetalleAyD;
	private JTable tblRolPagoDetalleAportesDecimos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JSplitPane getSplitPnPagoRol() {
		return splitPnPagoRol;
	}

	public JPanel getPanelRolPago() {
		return panelRolPago;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JScrollPane getSpTblRolPago() {
		return spTblRolPago;
	}

	public JTable getTblRolPago() {
		return tblRolPago;
	}

	public JPanel getPanelDetalleRolPago() {
		return panelDetalleRolPago;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JComponent getGfsRubros2() {
		return gfsRubros2;
	}

	public JScrollPane getSpTblRolPagoDetalleQyM() {
		return spTblRolPagoDetalleQyM;
	}

	public JTable getTblRolPagoDetalleQyM() {
		return tblRolPagoDetalleQyM;
	}

	public JScrollPane getSpTblRolPagoDetalleAyD() {
		return spTblRolPagoDetalleAyD;
	}

	public JTable getTblRolPagoDetalleAportesDecimos() {
		return tblRolPagoDetalleAportesDecimos;
	}
}
