package com.spirit.medios.gui.panel;

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
 * @author Antonio Seiler
 */
public abstract class JPGrupoObjetivo extends MantenimientoModelImpl {
	public JPGrupoObjetivo() {
		initComponents();
		setName("Grupo Objetivo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblNivel = new JLabel();
		cmbNivel = new JComboBox();
		lblGuayaquil = new JLabel();
		txtGuayaquil = new JTextField();
		lblQuito = new JLabel();
		txtQuito = new JTextField();
		lblOtra = new JLabel();
		txtOtra = new JTextField();
		spTblGrupoObjetivo = new JScrollPane();
		tblGrupoObjetivo = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));

		//---- lblNivel ----
		lblNivel.setText("Nivel:");
		add(lblNivel, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbNivel, cc.xywh(5, 7, 3, 1));

		//---- lblGuayaquil ----
		lblGuayaquil.setText("Guayaquil:");
		add(lblGuayaquil, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtGuayaquil, cc.xywh(5, 9, 3, 1));

		//---- lblQuito ----
		lblQuito.setText("Quito:");
		add(lblQuito, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtQuito, cc.xywh(5, 11, 3, 1));

		//---- lblOtra ----
		lblOtra.setText("Otra:");
		add(lblOtra, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtOtra, cc.xywh(5, 13, 3, 1));

		//======== spTblGrupoObjetivo ========
		{
			
			//---- tblGrupoObjetivo ----
			tblGrupoObjetivo.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"Código", "Nombre", "Nivel", "Guayaquil", "Quito", "Otra"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblGrupoObjetivo.setViewportView(tblGrupoObjetivo);
		}
		add(spTblGrupoObjetivo, cc.xywh(3, 17, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblNivel;
	private JComboBox cmbNivel;
	private JLabel lblGuayaquil;
	private JTextField txtGuayaquil;
	private JLabel lblQuito;
	private JTextField txtQuito;
	private JLabel lblOtra;
	private JTextField txtOtra;
	private JScrollPane spTblGrupoObjetivo;
	private JTable tblGrupoObjetivo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbNivel() {
		return cmbNivel;
	}

	public void setCmbNivel(JComboBox cmbNivel) {
		this.cmbNivel = cmbNivel;
	}

	public JScrollPane getSpTblGrupoObjetivo() {
		return spTblGrupoObjetivo;
	}

	public void setSpTblGrupoObjetivo(JScrollPane spTblGrupoObjetivo) {
		this.spTblGrupoObjetivo = spTblGrupoObjetivo;
	}

	public JTable getTblGrupoObjetivo() {
		return tblGrupoObjetivo;
	}

	public void setTblGrupoObjetivo(JTable tblGrupoObjetivo) {
		this.tblGrupoObjetivo = tblGrupoObjetivo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtGuayaquil() {
		return txtGuayaquil;
	}

	public void setTxtGuayaquil(JTextField txtGuayaquil) {
		this.txtGuayaquil = txtGuayaquil;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtOtra() {
		return txtOtra;
	}

	public void setTxtOtra(JTextField txtOtra) {
		this.txtOtra = txtOtra;
	}

	public JTextField getTxtQuito() {
		return txtQuito;
	}

	public void setTxtQuito(JTextField txtQuito) {
		this.txtQuito = txtQuito;
	}
}
