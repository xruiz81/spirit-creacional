package com.spirit.nomina.gui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPRolPagado extends SpiritModelImpl {
	public JPRolPagado() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblTipoRubro = new JLabel();
		panelTipoRubros = new JPanel();
		rbNormal = new JRadioButton();
		rbAnticipos = new JRadioButton();
		gfsRubros = compFactory.createSeparator("Rol de Pago");
		spTblRolPago = new JScrollPane();
		tblRolPago = new JTable();
		btnSeleccionarTodos = new JButton();
		gfsRubros2 = compFactory.createSeparator("Detalle Rol de Pago");
		panelRolPagoDetalle = new JPanel();
		spTblRolPagoDetalleQyM = new JScrollPane();
		tblRolPagoDetalleQyM = new JTable();
		spTblRolPagoDetalleAyD = new JScrollPane();
		tblRolPagoDetalleAportesDecimos = new JTable();
		spTblRolPagoDetalleAnticipos = new JScrollPane();
		tblRolPagoDetalleAnticipos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Rol Pagado");
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
				new RowSpec(Sizes.dluY(40)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRubro ----
		lblTipoRubro.setText("Tipo de Rubros:");
		add(lblTipoRubro, cc.xy(3, 3));

		//======== panelTipoRubros ========
		{
			panelTipoRubros.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				},
				RowSpec.decodeSpecs("default")));
			
			//---- rbNormal ----
			rbNormal.setText("Normales");
			panelTipoRubros.add(rbNormal, cc.xy(1, 1));
			
			//---- rbAnticipos ----
			rbAnticipos.setText("Anticipos");
			panelTipoRubros.add(rbAnticipos, cc.xy(3, 1));
		}
		add(panelTipoRubros, cc.xywh(5, 3, 3, 1));
		add(gfsRubros, cc.xywh(3, 5, 8, 1));

		//======== spTblRolPago ========
		{
			
			//---- tblRolPago ----
			tblRolPago.setModel(new DefaultTableModel(
				new Object[][] {
					{null, "", null, null},
				},
				new String[] {
					"Tipo de Rol", "Mes", "A\u00f1o", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPago.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			spTblRolPago.setViewportView(tblRolPago);
		}
		add(spTblRolPago, cc.xywh(3, 7, 8, 3));

		//---- btnSeleccionarTodos ----
		btnSeleccionarTodos.setText("Seleccionar Todo");
		add(btnSeleccionarTodos, cc.xy(3, 13));
		add(gfsRubros2, cc.xywh(3, 15, 8, 1));

		//======== panelRolPagoDetalle ========
		{
			panelRolPagoDetalle.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default:grow"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				}));
			
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
			panelRolPagoDetalle.add(spTblRolPagoDetalleQyM, cc.xywh(1, 1, 1, 3));
		}
		add(panelRolPagoDetalle, cc.xywh(3, 17, 7, 1));

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

		//======== spTblRolPagoDetalleAnticipos ========
		{
			
			//---- tblRolPagoDetalleAnticipos ----
			tblRolPagoDetalleAnticipos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
				},
				new String[] {
					" ", "Nombre", "Descripci\u00f3n", "Total", "Forma Pago", "Cuenta Bancaria", "# Cheque"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Double.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, true, false, false, false, true
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPagoDetalleAnticipos.setCellSelectionEnabled(true);
			tblRolPagoDetalleAnticipos.setColumnSelectionAllowed(false);
			tblRolPagoDetalleAnticipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			spTblRolPagoDetalleAnticipos.setViewportView(tblRolPagoDetalleAnticipos);
		}

		//---- buttonGroupTipoRubro ----
		ButtonGroup buttonGroupTipoRubro = new ButtonGroup();
		buttonGroupTipoRubro.add(rbNormal);
		buttonGroupTipoRubro.add(rbAnticipos);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoRubro;
	private JPanel panelTipoRubros;
	private JRadioButton rbNormal;
	private JRadioButton rbAnticipos;
	private JComponent gfsRubros;
	private JScrollPane spTblRolPago;
	private JTable tblRolPago;
	private JButton btnSeleccionarTodos;
	private JComponent gfsRubros2;
	private JPanel panelRolPagoDetalle;
	private JScrollPane spTblRolPagoDetalleQyM;
	private JTable tblRolPagoDetalleQyM;
	private JScrollPane spTblRolPagoDetalleAyD;
	private JTable tblRolPagoDetalleAportesDecimos;
	private JScrollPane spTblRolPagoDetalleAnticipos;
	private JTable tblRolPagoDetalleAnticipos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblTipoRubro() {
		return lblTipoRubro;
	}

	public JPanel getPanelTipoRubros() {
		return panelTipoRubros;
	}

	public JRadioButton getRbNormal() {
		return rbNormal;
	}

	public JRadioButton getRbAnticipos() {
		return rbAnticipos;
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

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JComponent getGfsRubros2() {
		return gfsRubros2;
	}

	public JPanel getPanelRolPagoDetalle() {
		return panelRolPagoDetalle;
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

	public JScrollPane getSpTblRolPagoDetalleAnticipos() {
		return spTblRolPagoDetalleAnticipos;
	}

	public JTable getTblRolPagoDetalleAnticipos() {
		return tblRolPagoDetalleAnticipos;
	}
}