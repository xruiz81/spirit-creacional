package com.spirit.general.gui.panel;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public abstract class JPDepartamento extends MantenimientoModelImpl {

	public JPDepartamento() {
		setName("Departamento");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		txtCodigo = new JFormattedTextField();
		lblCodigo = new JLabel();
		txtNombre = new JFormattedTextField();
		lblNombre = new JLabel();
		chkDepartamento = new JCheckBox();
		cmbPadre = new JComboBox();
		lblPadre = new JLabel();
		spTblDepartamento = new JScrollPane();
		tblDepartamento = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
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
				new RowSpec(Sizes.dluY(10))
			}));
		add(txtCodigo, cc.xywh(7, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtNombre, cc.xywh(7, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(5, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(chkDepartamento, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPadre, cc.xywh(7, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblPadre ----
		lblPadre.setText("Padre:");
		lblPadre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblPadre, cc.xywh(5, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//======== spTblDepartamento ========
		{
			
			//---- tblDepartamento ----
			tblDepartamento.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Padre"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblDepartamento.setViewportView(tblDepartamento);
		}
		add(spTblDepartamento, cc.xywh(3, 11, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JFormattedTextField txtCodigo;
	private JLabel lblCodigo;
	private JFormattedTextField txtNombre;
	private JLabel lblNombre;
	private JCheckBox chkDepartamento;
	private JComboBox cmbPadre;
	private JLabel lblPadre;
	private JScrollPane spTblDepartamento;
	private JTable tblDepartamento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getChkDepartamento() {
		return chkDepartamento;
	}

	public void setChkDepartamento(JCheckBox chkDepartamento) {
		this.chkDepartamento = chkDepartamento;
	}

	public JComboBox getCmbPadre() {
		return cmbPadre;
	}

	public void setCmbPadre(JComboBox cmbPadre) {
		this.cmbPadre = cmbPadre;
	}

	public JTable getTblDepartamento() {
		return tblDepartamento;
	}

	public void setTblDepartamento(JTable tblDepartamento) {
		this.tblDepartamento = tblDepartamento;
	}

	public JFormattedTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JFormattedTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JFormattedTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JFormattedTextField txtNombre) {
		this.txtNombre = txtNombre;
	}	
}
