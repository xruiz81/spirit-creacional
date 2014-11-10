package com.spirit.timeTracker.gui.main;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class JPSubtareas extends JPanel {
	public JPSubtareas() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		splitPaneContenedor = new JSplitPane();
		spanelInformacion = new JScrollPane();
		splitPaneInferior = new JSplitPane();
		spanelSubTareas = new JScrollPane();
		tblSubTareas = new JTable();
		spanelSubTareaDetalle = new JScrollPane();
		panel1 = new JPanel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== splitPaneContenedor ========
		{
			splitPaneContenedor.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPaneContenedor.setResizeWeight(0.5);
			splitPaneContenedor.setDividerSize(10);
			splitPaneContenedor.setDividerLocation(195);
			
			//======== spanelInformacion ========
			{
				spanelInformacion.setPreferredSize(new Dimension(29, 240));
				spanelInformacion.setMaximumSize(new Dimension(32767, 250));
				spanelInformacion.setMinimumSize(new Dimension(23, 150));
			}
			splitPaneContenedor.setLeftComponent(spanelInformacion);
			
			//======== splitPaneInferior ========
			{
				
				//======== spanelSubTareas ========
				{
					spanelSubTareas.setPreferredSize(new Dimension(452, 600));
					
					//---- tblSubTareas ----
					tblSubTareas.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Descripcion", "Tiempo"
						}
					) {
						Class[] columnTypes = new Class[] {
							String.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spanelSubTareas.setViewportView(tblSubTareas);
				}
				splitPaneInferior.setLeftComponent(spanelSubTareas);
				
				//======== spanelSubTareaDetalle ========
				{
					
					//======== panel1 ========
					{
						panel1.setLayout(null);
						
						{ // compute preferred size
							Dimension preferredSize = new Dimension();
							for(int i = 0; i < panel1.getComponentCount(); i++) {
								Rectangle bounds = panel1.getComponent(i).getBounds();
								preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
								preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
							}
							Insets insets = panel1.getInsets();
							preferredSize.width += insets.right;
							preferredSize.height += insets.bottom;
							panel1.setPreferredSize( preferredSize );
						}
					}
					spanelSubTareaDetalle.setViewportView(panel1);
				}
				splitPaneInferior.setRightComponent(spanelSubTareaDetalle);
			}
			splitPaneContenedor.setRightComponent(splitPaneInferior);
		}
		add(splitPaneContenedor, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSplitPane splitPaneContenedor;
	private JScrollPane spanelInformacion;
	private JSplitPane splitPaneInferior;
	private JScrollPane spanelSubTareas;
	private JTable tblSubTareas;
	private JScrollPane spanelSubTareaDetalle;
	private JPanel panel1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpanelInformacion() {
		return spanelInformacion;
	}

	public JScrollPane getSpanelSubTareas() {
		return spanelSubTareas;
	}

	public JTable getTblSubTareas() {
		return tblSubTareas;
	}

	public JSplitPane getSplitPaneContenedor() {
		return splitPaneContenedor;
	}

	public JScrollPane getSpanelSubTareaDetalle() {
		return spanelSubTareaDetalle;
	}

	public JSplitPane getSplitPaneInferior() {
		return splitPaneInferior;
	}
}
