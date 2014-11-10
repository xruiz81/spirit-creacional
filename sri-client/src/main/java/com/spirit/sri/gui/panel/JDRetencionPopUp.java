package com.spirit.sri.gui.panel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public abstract class JDRetencionPopUp extends JDialog {
	public JDRetencionPopUp(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDRetencionPopUp(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblSerieSecuencial = new JLabel();
		txtEstablecimiento = new JTextField();
		txtPuntoEmision = new JTextField();
		txtSecuencial = new JTextField();
		lblAutorizacion = new JLabel();
		txtAutorizacion = new JTextField();
		lblPuntoEmision = new JLabel();
		txtFechaEmision = new JTextField();
		spRetencionPopUp = new JScrollPane();
		tblRetencionPopUp = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Retenciones");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(35)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(35)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(65)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
				new ColumnSpec(Sizes.DLUX3),
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
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(40), 0.0),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblSerieSecuencial ----
		lblSerieSecuencial.setText("No. de Serie y Secuencial");
		contentPane.add(lblSerieSecuencial, cc.xy(3, 3));

		//---- txtEstablecimiento ----
		txtEstablecimiento.setEditable(false);
		txtEstablecimiento.setBackground(Color.white);
		contentPane.add(txtEstablecimiento, cc.xy(5, 3));

		//---- txtPuntoEmision ----
		txtPuntoEmision.setEditable(false);
		txtPuntoEmision.setBackground(Color.white);
		contentPane.add(txtPuntoEmision, cc.xy(7, 3));

		//---- txtSecuencial ----
		txtSecuencial.setEditable(false);
		txtSecuencial.setBackground(Color.white);
		contentPane.add(txtSecuencial, cc.xy(9, 3));

		//---- lblAutorizacion ----
		lblAutorizacion.setText("No. de Autorizaci\u00f3n");
		contentPane.add(lblAutorizacion, cc.xy(3, 5));

		//---- txtAutorizacion ----
		txtAutorizacion.setEditable(false);
		txtAutorizacion.setBackground(Color.white);
		contentPane.add(txtAutorizacion, cc.xywh(5, 5, 3, 1));

		//---- lblPuntoEmision ----
		lblPuntoEmision.setText("Fecha de Emisi\u00f3n");
		contentPane.add(lblPuntoEmision, cc.xy(3, 7));

		//---- txtFechaEmision ----
		txtFechaEmision.setEditable(false);
		txtFechaEmision.setBackground(Color.white);
		contentPane.add(txtFechaEmision, cc.xywh(5, 7, 3, 1));

		//======== spRetencionPopUp ========
		{
			
			//---- tblRetencionPopUp ----
			tblRetencionPopUp.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"No. de Serie", "Secuencial", "No. de Autorizaci\u00f3n", "Fecha de Emisi\u00f3n"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRetencionPopUp.setPreferredScrollableViewportSize(new Dimension(500, 160));
			spRetencionPopUp.setViewportView(tblRetencionPopUp);
		}
		contentPane.add(spRetencionPopUp, cc.xywh(3, 11, 9, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblSerieSecuencial;
	private JTextField txtEstablecimiento;
	private JTextField txtPuntoEmision;
	private JTextField txtSecuencial;
	private JLabel lblAutorizacion;
	private JTextField txtAutorizacion;
	private JLabel lblPuntoEmision;
	private JTextField txtFechaEmision;
	private JScrollPane spRetencionPopUp;
	private JTable tblRetencionPopUp;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblRetencionPopUp() {
		return tblRetencionPopUp;
	}

	public JTextField getTxtAutorizacion() {
		return txtAutorizacion;
	}

	public JTextField getTxtEstablecimiento() {
		return txtEstablecimiento;
	}

	public JTextField getTxtFechaEmision() {
		return txtFechaEmision;
	}

	public JTextField getTxtPuntoEmision() {
		return txtPuntoEmision;
	}

	public JTextField getTxtSecuencial() {
		return txtSecuencial;
	}
}
