package com.spirit.contabilidad.gui.panel;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPAutorizarAsiento extends SpiritModelImpl {
	public JPAutorizarAsiento() {
		initComponents();
		setName("Autorización de Asientos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		fsListadoPreasientos = compFactory.createSeparator("Listado Preasientos:");
		spPreasientos = new JScrollPane();
		tblPreasientos = new JTable();
		fsDetallePreasiento = compFactory.createSeparator("Detalle Preasiento:");
		spAutorizarAsiento = new JScrollPane();
		tblAutorizarAsiento = new JTable();
		popup = new JPopupMenu();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec("max(default;50dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(90)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));
		add(fsListadoPreasientos, cc.xywh(3, 3, 7, 1));

		//======== spPreasientos ========
		{
			
			//---- tblPreasientos ----
			tblPreasientos.setModel(new DefaultTableModel(
				new Object[][] {
					{Boolean.FALSE, null, null, null, null},
				},
				new String[] {
					"Autorizar", "N\u00famero", "Oficina", "Tipo", "Fecha", "Concepto"
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
			spPreasientos.setViewportView(tblPreasientos);
		}
		add(spPreasientos, cc.xywh(3, 5, 7, 5));
		add(fsDetallePreasiento, cc.xywh(3, 13, 7, 1));

		//======== spAutorizarAsiento ========
		{
			
			//---- tblAutorizarAsiento ----
			tblAutorizarAsiento.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Cuenta", "Nombre Cuenta", "Glosa", "Debe", "Haber"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spAutorizarAsiento.setViewportView(tblAutorizarAsiento);
		}
		add(spAutorizarAsiento, cc.xywh(3, 15, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		tblAutorizarAsiento.getTableHeader().setReorderingAllowed(false);
		tblPreasientos.getTableHeader().setReorderingAllowed(false);
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblAutorizarAsiento.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		tblAutorizarAsiento.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendererCenter = new TableCellRendererHorizontalCenterAlignment();
		tblAutorizarAsiento.getColumnModel().getColumn(0).setCellRenderer(tableCellRendererCenter);
		tblPreasientos.getColumnModel().getColumn(1).setCellRenderer(tableCellRendererCenter);
		tblPreasientos.getColumnModel().getColumn(2).setCellRenderer(tableCellRendererCenter);
		tblPreasientos.getColumnModel().getColumn(3).setCellRenderer(tableCellRendererCenter);
		tblPreasientos.getColumnModel().getColumn(4).setCellRenderer(tableCellRendererCenter);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JComponent fsListadoPreasientos;
	private JScrollPane spPreasientos;
	private JTable tblPreasientos;
	private JComponent fsDetallePreasiento;
	private JScrollPane spAutorizarAsiento;
	private JTable tblAutorizarAsiento;
	protected JPopupMenu popup;
	protected JMenuItem menuItem;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblAutorizarAsiento() {
		return tblAutorizarAsiento;
	}

	public void setTblAutorizarAsiento(JTable tblAutorizarAsiento) {
		this.tblAutorizarAsiento = tblAutorizarAsiento;
	}

	public JTable getTblPreasientos() {
		return tblPreasientos;
	}

	public void setTblPreasientos(JTable tblPreasientos) {
		this.tblPreasientos = tblPreasientos;
	}
}
