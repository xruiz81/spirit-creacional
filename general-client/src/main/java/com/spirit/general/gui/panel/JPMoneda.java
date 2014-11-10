package com.spirit.general.gui.panel;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.util.MyTableCellEditorNumber;



/**
 * @author Antonio Seiler
 */
public abstract class JPMoneda extends MantenimientoModelImpl {
	public JPMoneda() {
		initComponents();
		setName("Moneda");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblDescripcion = new JLabel();
		scMoneda = new JScrollPane();
		tblMoneda = new JTable();
		txtDescripcion = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xy(3, 3));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDescripcion, cc.xy(3, 5));

		//======== scMoneda ========
		{
			
			//---- tblMoneda ----
			tblMoneda.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"C\u00f3digo", "Descripci\u00f3n"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblMoneda.getTableHeader().setReorderingAllowed(false);
			scMoneda.setViewportView(tblMoneda);
		}
		add(scMoneda, cc.xywh(3, 9, 7, 1));
		add(txtDescripcion, cc.xywh(5, 5, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.LEFT);
		tblMoneda.getColumnModel().getColumn(0).setCellEditor(new MyTableCellEditorNumber());
		tblMoneda.getColumnModel().getColumn(0).setCellRenderer(dtcrColumn);
		tblMoneda.getColumnModel().getColumn(1).setCellEditor(new MyTableCellEditorNumber());
		tblMoneda.getColumnModel().getColumn(1).setCellRenderer(dtcrColumn);
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblDescripcion;
	private JScrollPane scMoneda;
	private JTable tblMoneda;
	private JTextField txtDescripcion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	
	public JTable getTblMoneda() {
		return tblMoneda;
	}

	public void setTblMoneda(JTable tblMoneda) {
		this.tblMoneda = tblMoneda;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
}
