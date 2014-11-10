package com.spirit.sri.gui.panel;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;

/**
 * @author xruiz
 */
public abstract class JPTipoTransacciones extends MantenimientoModelImpl {
	public JPTipoTransacciones() {
		initComponents();
		setName("Tipos de Transacciones");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblTipoTransaccion = new JLabel();
		txtTipoTransaccion = new JTextField();
		spTblTipoTransaccion = new JScrollPane();
		tblTipoTransaccion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Tipo de Sustento Tributario");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(180)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 11}});

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTipoTransaccion ----
		lblTipoTransaccion.setText("Tipo de Transacci\u00f3n:");
		lblTipoTransaccion.setToolTipText("Nombre del Centro de Gasto");
		lblTipoTransaccion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoTransaccion, cc.xywh(3, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtTipoTransaccion ----
		txtTipoTransaccion.setToolTipText("Nombre del Centro de Gasto");
		add(txtTipoTransaccion, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblTipoTransaccion ========
		{
			
			//---- tblTipoTransaccion ----
			tblTipoTransaccion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"C\u00f3digo", "Tipo de Transacci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoTransaccion.setViewportView(tblTipoTransaccion);
		}
		add(spTblTipoTransaccion, cc.xywh(3, 9, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblTipoTransaccion;
	private JTextField txtTipoTransaccion;
	private JScrollPane spTblTipoTransaccion;
	private JTable tblTipoTransaccion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpTblTipoTransaccion() {
		return spTblTipoTransaccion;
	}

	public void setSpTblTipoTransaccion(JScrollPane spTblTipoTransaccion) {
		this.spTblTipoTransaccion = spTblTipoTransaccion;
	}

	public JTable getTblTipoTransaccion() {
		return tblTipoTransaccion;
	}

	public void setTblTipoTransaccion(JTable tblTipoTransaccion) {
		this.tblTipoTransaccion = tblTipoTransaccion;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtTipoTransaccion() {
		return txtTipoTransaccion;
	}

	public void setTxtTipoTransaccion(JTextField txtTipoTransaccion) {
		this.txtTipoTransaccion = txtTipoTransaccion;
	}
}
