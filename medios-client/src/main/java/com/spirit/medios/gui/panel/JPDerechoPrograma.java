package com.spirit.medios.gui.panel;

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

public abstract class JPDerechoPrograma extends MantenimientoModelImpl {
	public JPDerechoPrograma() {
		initComponents();
		setName("Derecho de Programa");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		txtCodigo = new JTextField();
		spTblDerechoPrograma = new JScrollPane();
		tblDerechoPrograma = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(65)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(65)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
			}));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 13}});

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblNombre ----
		lblNombre.setText("Descripci\u00f3n:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));
		add(txtCodigo, cc.xy(5, 3));

		//======== spTblDerechoPrograma ========
		{
			
			//---- tblDerechoPrograma ----
			tblDerechoPrograma.setModel(new DefaultTableModel(
				new Object[][] {
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
			spTblDerechoPrograma.setViewportView(tblDerechoPrograma);
		}
		add(spTblDerechoPrograma, cc.xywh(3, 9, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JScrollPane spTblDerechoPrograma;
	private JTable tblDerechoPrograma;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblDerechoPrograma() {
		return tblDerechoPrograma;
	}

	public void setTblDerechoPrograma(JTable tblDerechoPrograma) {
		this.tblDerechoPrograma = tblDerechoPrograma;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JScrollPane getSpTblDerechoPrograma() {
		return spTblDerechoPrograma;
	}

	public void setSpTblDerechoPrograma(JScrollPane spTblDerechoPrograma) {
		this.spTblDerechoPrograma = spTblDerechoPrograma;
	}
}
