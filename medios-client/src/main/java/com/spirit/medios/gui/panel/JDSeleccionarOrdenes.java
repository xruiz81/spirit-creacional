package com.spirit.medios.gui.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Sep 25 15:28:32 COT 2012
 */

/**
 * @author xruiz
 */
public class JDSeleccionarOrdenes extends JDialog {
	public JDSeleccionarOrdenes(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDSeleccionarOrdenes(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		dialogPane = new JPanel();
		panel1 = new JPanel();
		ldescripcion = new JLabel();
		spTblPresupuestoDetalles = new JScrollPane();
		tblPresupuestoDetalles = new JTable();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Facturaci\u00f3n Parcial");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());
		}
		contentPane.add(dialogPane, BorderLayout.SOUTH);

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(120)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(120)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.DLUX3)
				},
				new RowSpec[] {
					new RowSpec(Sizes.DLUY3),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));

			//---- ldescripcion ----
			ldescripcion.setText("Elija los detalles que desea facturar:");
			panel1.add(ldescripcion, cc.xywh(3, 3, 3, 1));

			//======== spTblPresupuestoDetalles ========
			{

				//---- tblPresupuestoDetalles ----
				tblPresupuestoDetalles.setModel(new DefaultTableModel(
					new Object[][] {
						{false, null, null, null, null},
					},
					new String[] {
						"", "Orden", "Proveedor", "Producto", "Valor"
					}
				) {
					Class[] columnTypes = new Class[] {
						Boolean.class, Object.class, Object.class, Object.class, Object.class
					};
					boolean[] columnEditable = new boolean[] {
						true, false, true, true, false
					};
					@Override
					public Class<?> getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					@Override
					public boolean isCellEditable(int rowIndex, int columnIndex) {
						return columnEditable[columnIndex];
					}
				});
				spTblPresupuestoDetalles.setViewportView(tblPresupuestoDetalles);
			}
			panel1.add(spTblPresupuestoDetalles, cc.xywh(3, 5, 9, 5));

			//---- btnAceptar ----
			btnAceptar.setText("Aceptar");
			panel1.add(btnAceptar, cc.xy(5, 11));

			//---- btnCancelar ----
			btnCancelar.setText("Cancelar");
			panel1.add(btnCancelar, cc.xy(9, 11));
		}
		contentPane.add(panel1, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel dialogPane;
	private JPanel panel1;
	private JLabel ldescripcion;
	private JScrollPane spTblPresupuestoDetalles;
	private JTable tblPresupuestoDetalles;
	private JButton btnAceptar;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getDialogPane() {
		return dialogPane;
	}

	public JScrollPane getSpTblPresupuestoDetalles() {
		return spTblPresupuestoDetalles;
	}

	public JTable getTblPresupuestoDetalles() {
		return tblPresupuestoDetalles;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
}
