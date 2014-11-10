package com.spirit.timeTracker.gui.main;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class JPPanelPrincipal extends JPanel {
	public JPPanelPrincipal() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		splitPaneGeneral = new JSplitPane();
		splitPaneListas = new JSplitPane();
		panelProyectos = new JPanel();
		panelTareas = new JPanel();
		panelSubtareas = new JPanel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== splitPaneGeneral ========
		{
			splitPaneGeneral.setOrientation(JSplitPane.VERTICAL_SPLIT);

			//======== splitPaneListas ========
			{

				//======== panelProyectos ========
				{
					panelProyectos.setLayout(new FlowLayout());
				}
				splitPaneListas.setLeftComponent(panelProyectos);

				//======== panelTareas ========
				{
					panelTareas.setLayout(new FlowLayout());
				}
				splitPaneListas.setRightComponent(panelTareas);
			}
			splitPaneGeneral.setTopComponent(splitPaneListas);

			//======== panelSubtareas ========
			{
				panelSubtareas.setLayout(new FlowLayout());
			}
			splitPaneGeneral.setBottomComponent(panelSubtareas);
		}
		add(splitPaneGeneral, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSplitPane splitPaneGeneral;
	private JSplitPane splitPaneListas;
	private JPanel panelProyectos;
	private JPanel panelTareas;
	private JPanel panelSubtareas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JSplitPane getSplitPaneGeneral() {
		return splitPaneGeneral;
	}

	public JSplitPane getSplitPaneListas() {
		return splitPaneListas;
	}
}
