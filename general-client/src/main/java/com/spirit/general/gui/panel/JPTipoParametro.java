package com.spirit.general.gui.panel;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
public abstract class JPTipoParametro extends MantenimientoModelImpl {
	public JPTipoParametro() {
		initComponents();
		setName("Tipo Parametro");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		txtCodigo = new JTextField();
		lblCodigo = new JLabel();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblTipoParametro = new JLabel();
		cmbTipoParametro = new JComboBox();
		lblMascara = new JLabel();
		txtMascara = new JTextField();
		spTipoParametroDetalles = new JScrollPane();
		tblTipoParametroDetalles = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(140)),
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
		add(txtCodigo, cc.xy(5, 3));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n:");
		add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtDescripcion, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTipoParametro ----
		lblTipoParametro.setText("Tipo Par\u00e1metro:");
		add(lblTipoParametro, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbTipoParametro, cc.xywh(5, 7, 3, 1));

		//---- lblMascara ----
		lblMascara.setText("M\u00e1scara:");
		add(lblMascara, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtMascara, cc.xywh(5, 9, 3, 1));

		//======== spTipoParametroDetalles ========
		{
			
			//---- tblTipoParametroDetalles ----
			tblTipoParametroDetalles.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Descripci\u00f3n", "Tipo", "M\u00e1scara"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblTipoParametroDetalles.setPreferredScrollableViewportSize(new Dimension(450, 400));
			tblTipoParametroDetalles.getTableHeader().setReorderingAllowed(false);
			tblTipoParametroDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			spTipoParametroDetalles.setViewportView(tblTipoParametroDetalles);
		}
		add(spTipoParametroDetalles, cc.xywh(3, 13, 9, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblTipoParametro;
	private JComboBox cmbTipoParametro;
	private JLabel lblMascara;
	private JTextField txtMascara;
	private JScrollPane spTipoParametroDetalles;
	private JTable tblTipoParametroDetalles;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbTipoParametro() {
		return cmbTipoParametro;
	}

	public void setCmbTipoParametro(JComboBox cmbTipoParametro) {
		this.cmbTipoParametro = cmbTipoParametro;
	}

	public JScrollPane getSpTipoParametroDetalles() {
		return spTipoParametroDetalles;
	}

	public void setSpTipoParametroDetalles(JScrollPane spTipoParametroDetalles) {
		this.spTipoParametroDetalles = spTipoParametroDetalles;
	}

	public JTable getTblTipoParametroDetalles() {
		return tblTipoParametroDetalles;
	}

	public void setTblTipoParametroDetalles(JTable tblTipoParametroDetalles) {
		this.tblTipoParametroDetalles = tblTipoParametroDetalles;
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

	public JTextField getTxtMascara() {
		return txtMascara;
	}

	public void setTxtMascara(JTextField txtMascara) {
		this.txtMascara = txtMascara;
	}

	
}
