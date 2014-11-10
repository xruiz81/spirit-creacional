package com.spirit.medios.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Thu Apr 11 17:13:44 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author xruiz
 */
public abstract class JPOrdenesEstado extends SpiritModelImpl {
	public JPOrdenesEstado() {
		initComponents();
		setName("Ordenes Estado");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblPresupuesto = new JLabel();
		txtPresupuesto = new JTextField();
		btnConsultar = new JButton();
		spTblOrdenesEstado = new JScrollPane();
		tblOrdenesEstado = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblPresupuesto ----
		lblPresupuesto.setText("Presupuesto:");
		add(lblPresupuesto, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- txtPresupuesto ----
		txtPresupuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtPresupuesto, cc.xy(5, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(9, 3));

		//======== spTblOrdenesEstado ========
		{

			//---- tblOrdenesEstado ----
			tblOrdenesEstado.setModel(new DefaultTableModel(
				new Object[][] {
					{"", null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Proveedor", "Fecha", "SubTotal", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, true
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblOrdenesEstado.setViewportView(tblOrdenesEstado);
		}
		add(spTblOrdenesEstado, cc.xywh(3, 7, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblPresupuesto;
	private JTextField txtPresupuesto;
	private JButton btnConsultar;
	private JScrollPane spTblOrdenesEstado;
	private JTable tblOrdenesEstado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtPresupuesto() {
		return txtPresupuesto;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblOrdenesEstado() {
		return spTblOrdenesEstado;
	}

	public JTable getTblOrdenesEstado() {
		return tblOrdenesEstado;
	}
}
