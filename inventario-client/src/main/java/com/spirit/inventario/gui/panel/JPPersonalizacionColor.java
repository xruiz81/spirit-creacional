package com.spirit.inventario.gui.panel;

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
 * @author Antonio Seiler
 */
public abstract class JPPersonalizacionColor extends MantenimientoModelImpl {
	public JPPersonalizacionColor() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		spTblColor = new JScrollPane();
		tblColor = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Personalizacion Color");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
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

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo descriptivo del M\u00f3dulo");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo del M\u00f3dulo");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setToolTipText("Nombre del M\u00f3dulo");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre del M\u00f3dulo");
		add(txtNombre, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblColor ========
		{
			
			//---- tblColor ----
			tblColor.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"C\u00f3digo", "Nombre"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblColor.setViewportView(tblColor);
		}
		add(spTblColor, cc.xywh(3, 9, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JScrollPane spTblColor;
	private JTable tblColor;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JScrollPane getSpTblColor() {
		return spTblColor;
	}

	public JTable getTblColor() {
		return tblColor;
	}
}
