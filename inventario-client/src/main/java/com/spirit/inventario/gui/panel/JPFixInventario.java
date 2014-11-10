package com.spirit.inventario.gui.panel;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.spirit.client.model.ReportModelImpl;
/*
 * Created by JFormDesigner on Mon Oct 19 17:47:00 COT 2009
 */



/**
 * @author Antonio Seiler
 */
public abstract class JPFixInventario extends ReportModelImpl {
	public JPFixInventario() {
		initComponents();
		setName("Fix Inventario");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		btnRegenerarCodigos = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("KARDEX");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(200)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX5),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
			}));

		//---- label1 ----
		label1.setText("Regenera los codigos de Barras de Todos los productos:");
		add(label1, cc.xywh(3, 3, 2, 1));

		//---- btnRegenerarCodigos ----
		btnRegenerarCodigos.setText("Regenerar Codigo de Barras");
		add(btnRegenerarCodigos, cc.xy(7, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JButton btnRegenerarCodigos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JButton getBtnRegenerarCodigos() {
		return btnRegenerarCodigos;
	}
}
