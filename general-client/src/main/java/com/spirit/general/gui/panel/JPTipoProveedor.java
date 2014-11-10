package com.spirit.general.gui.panel;

import javax.swing.JComboBox;
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
public abstract class JPTipoProveedor extends MantenimientoModelImpl {

	public JPTipoProveedor() {
		initComponents();
		setName("Tipo Proveedor");
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		spTblTipoProveedor = new JScrollPane();
		tblTipoProveedor = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(90)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
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
				new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo para el Tipo de Proveedor");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre del Tipo de Proveedor");
		add(txtNombre, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTipo ----
		lblTipo.setText("Clasificaci\u00f3n");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbTipo ----
		cmbTipo.setToolTipText("Clasificaci\u00f3n del Tipo de Proveedor");
		add(cmbTipo, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblTipoProveedor ========
		{
			
			//---- tblTipoProveedor ----
			tblTipoProveedor.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Clasificaci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblTipoProveedor.getTableHeader().setReorderingAllowed(false);
			spTblTipoProveedor.setViewportView(tblTipoProveedor);
		}
		add(spTblTipoProveedor, cc.xywh(3, 11, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JScrollPane spTblTipoProveedor;
	private JTable tblTipoProveedor;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbTipo() {
		return cmbTipo;
	}


	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}


	public JTable getTblTipoProveedor() {
		return tblTipoProveedor;
	}


	public void setTblTipoProveedor(JTable tblTipoProveedor) {
		this.tblTipoProveedor = tblTipoProveedor;
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
	
}
