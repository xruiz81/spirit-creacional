package com.spirit.general.gui.controller;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.util.FilteringJList;

public class JDQuickSearch extends JDialog {
	public JDQuickSearch(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDQuickSearch(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblIrA = new JLabel();
		txtCodigo = new JTextField();
		spQuickSearch = new JScrollPane();
		listQuickSearch = new FilteringJList();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("B\u00fasqueda r\u00e1pida de pantallas");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(40)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblIrA ----
		lblIrA.setText("Ir a:");
		contentPane.add(lblIrA, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		contentPane.add(txtCodigo, cc.xywh(5, 3, 3, 1));

		//======== spQuickSearch ========
		{
			spQuickSearch.setViewportView(listQuickSearch);
		}
		contentPane.add(spQuickSearch, cc.xywh(5, 4, 3, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblIrA;
	private JTextField txtCodigo;
	private JScrollPane spQuickSearch;
	private FilteringJList listQuickSearch;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public FilteringJList getListQuickSearch() {
		return listQuickSearch;
	}

	public void setListQuickSearch(FilteringJList listQuickSearch) {
		this.listQuickSearch = listQuickSearch;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
}
