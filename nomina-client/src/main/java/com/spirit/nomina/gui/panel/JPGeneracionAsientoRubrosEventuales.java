package com.spirit.nomina.gui.panel;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
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
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPGeneracionAsientoRubrosEventuales extends SpiritModelImpl {
	public JPGeneracionAsientoRubrosEventuales() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		splitPnPagoRol = new JSplitPane();
		panelRolPago = new JPanel();
		gfsRubros = compFactory.createSeparator("Rol de Pago");
		spTblRolPago = new JScrollPane();
		tblRolPago = new JTable();
		panelDetalleRolPago = new JPanel();
		btnSeleccionarTodos = new JButton();
		gfsRubros2 = compFactory.createSeparator("Detalle Rol de Pago");
		spTblRolPagoDetalleAnticipos = new JScrollPane();
		tblRolPagoDetalleAnticipos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Generacion de Asientos de Rubros Eventuales");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(130)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

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
				
				//======== spTblRolPagoDetalleAnticipos ========
				{
					
					//---- tblRolPagoDetalleAnticipos ----
					tblRolPagoDetalleAnticipos.setModel(new DefaultTableModel(
						new Object[][] {
							{Boolean.FALSE, null, null, null, null, null, null},
						},
						new String[] {
							" ", "Nombre Rubro", "Total", "Beneficiario", "Forma Pago", "Cuenta Bancaria", "# Cheque"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Double.class, Object.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, true, false, true, true, true, true
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
				panelDetalleRolPago.add(spTblRolPagoDetalleAnticipos, cc.xywh(1, 7, 3, 3));
			}
			splitPnPagoRol.setRightComponent(panelDetalleRolPago);
		}
		add(splitPnPagoRol, cc.xywh(3, 3, 5, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSplitPane splitPnPagoRol;
	private JPanel panelRolPago;
	private JComponent gfsRubros;
	private JScrollPane spTblRolPago;
	private JTable tblRolPago;
	private JPanel panelDetalleRolPago;
	private JButton btnSeleccionarTodos;
	private JComponent gfsRubros2;
	private JScrollPane spTblRolPagoDetalleAnticipos;
	private JTable tblRolPagoDetalleAnticipos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JComponent getGfsRubros2() {
		return gfsRubros2;
	}

	public JPanel getPanelDetalleRolPago() {
		return panelDetalleRolPago;
	}

	public JPanel getPanelRolPago() {
		return panelRolPago;
	}

	public JSplitPane getSplitPnPagoRol() {
		return splitPnPagoRol;
	}

	public JScrollPane getSpTblRolPago() {
		return spTblRolPago;
	}

	public JScrollPane getSpTblRolPagoDetalleAnticipos() {
		return spTblRolPagoDetalleAnticipos;
	}

	public JTable getTblRolPago() {
		return tblRolPago;
	}

	public JTable getTblRolPagoDetalleAnticipos() {
		return tblRolPagoDetalleAnticipos;
	}
}
