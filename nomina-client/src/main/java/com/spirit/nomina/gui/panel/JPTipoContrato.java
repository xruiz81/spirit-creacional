package com.spirit.nomina.gui.panel;

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

public abstract class JPTipoContrato extends MantenimientoModelImpl {
	public JPTipoContrato() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		spTblTipoContrato = new JScrollPane();
		tblTipoContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Tipo Contrato");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
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
				new RowSpec(Sizes.dluY(12))
			}));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 13}});

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Descripci\u00f3n:");
		lblNombre.setToolTipText("Nombre del Centro de Gasto");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre del Centro de Gasto");
		add(txtNombre, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblTipoContrato ========
		{
			
			//---- tblTipoContrato ----
			tblTipoContrato.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"C\u00f3digo", "Descripci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoContrato.setViewportView(tblTipoContrato);
		}
		add(spTblTipoContrato, cc.xywh(3, 9, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JScrollPane spTblTipoContrato;
	private JTable tblTipoContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JScrollPane getSpTblTipoContrato() {
		return spTblTipoContrato;
	}

	public JTable getTblTipoContrato() {
		return tblTipoContrato;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}
	
	
}
