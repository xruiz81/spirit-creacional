package com.spirit.general.gui.main;
import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Fri Jun 24 11:38:29 COT 2011
 */



/**
 * @author SHOCKIE
 */
public class SpiritSpeedDial extends JPanel {
	public SpiritSpeedDial() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		button14 = new JButton();
		button10 = new JButton();
		button1 = new JButton();
		button15 = new JButton();
		button11 = new JButton();
		button2 = new JButton();
		button16 = new JButton();
		button12 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button18 = new JButton();
		button5 = new JButton();
		button17 = new JButton();
		button13 = new JButton();
		button6 = new JButton();
		button7 = new JButton();
		button8 = new JButton();
		button9 = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setBackground(Color.white);
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.dluX(100), Sizes.dluX(100)), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.dluX(100), Sizes.dluX(100)), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;100dlu):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec("max(default;100dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec("max(default;100dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec("max(default;100dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//---- button14 ----
		button14.setText("Nueva oficina");
		button14.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button14, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button10 ----
		button10.setText("text");
		add(button10, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button1 ----
		button1.setText("text");
		add(button1, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button15 ----
		button15.setText("Nuevo usuario");
		button15.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button15, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button11 ----
		button11.setText("text");
		add(button11, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button2 ----
		button2.setText("text");
		add(button2, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button16 ----
		button16.setText("Nuevo producto");
		button16.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button16, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button12 ----
		button12.setText("text");
		add(button12, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button3 ----
		button3.setText("text");
		add(button3, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button4 ----
		button4.setText("text");
		button4.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button4, cc.xywh(3, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button18 ----
		button18.setText("text");
		button18.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button18, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button5 ----
		button5.setText("text");
		add(button5, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button17 ----
		button17.setText("text");
		button17.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button17, cc.xywh(7, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button13 ----
		button13.setText("text");
		add(button13, cc.xywh(7, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button6 ----
		button6.setText("text");
		add(button6, cc.xywh(7, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button7 ----
		button7.setText("text");
		button7.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button7, cc.xywh(3, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button8 ----
		button8.setText("text");
		button8.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button8, cc.xywh(5, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- button9 ----
		button9.setText("text");
		button9.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(button9, cc.xywh(7, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JButton button14;
	private JButton button10;
	private JButton button1;
	private JButton button15;
	private JButton button11;
	private JButton button2;
	private JButton button16;
	private JButton button12;
	private JButton button3;
	private JButton button4;
	private JButton button18;
	private JButton button5;
	private JButton button17;
	private JButton button13;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
