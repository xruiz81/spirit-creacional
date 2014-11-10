package com.spirit.general.gui.panel;

import javax.swing.JButton;
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
import com.spirit.client.model.SpiritResourceManager;

/**
 * @author xruiz
 */
public abstract class JPUsuarioPuntoImpresion extends MantenimientoModelImpl {
	public JPUsuarioPuntoImpresion() {
		initComponents();
		setName("Usuario x Punto de Impresion");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblUsuario = new JLabel();
		txtUsuario = new JTextField();
		btnUsuario = new JButton();
		lblPuntoImpresion = new JLabel();
		cmbPuntoImpresion = new JComboBox();
		spTblUsuarioPuntoImpresion = new JScrollPane();
		tblUsuarioPuntoImpresion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(200)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblUsuario ----
		lblUsuario.setText("Usuario:");
		add(lblUsuario, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtUsuario, cc.xy(5, 3));
		add(btnUsuario, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblPuntoImpresion ----
		lblPuntoImpresion.setText("Punto de Impresi\u00f3n:");
		add(lblPuntoImpresion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPuntoImpresion, cc.xywh(5, 5, 5, 1));

		//======== spTblUsuarioPuntoImpresion ========
		{
			
			//---- tblUsuarioPuntoImpresion ----
			tblUsuarioPuntoImpresion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null},
				},
				new String[] {
					"Usuario", "Punto de Impresi\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblUsuarioPuntoImpresion.setViewportView(tblUsuarioPuntoImpresion);
		}
		add(spTblUsuarioPuntoImpresion, cc.xywh(3, 9, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnUsuario.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnUsuario.setToolTipText("Buscar Usuario");
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JButton btnUsuario;
	private JLabel lblPuntoImpresion;
	private JComboBox cmbPuntoImpresion;
	private JScrollPane spTblUsuarioPuntoImpresion;
	private JTable tblUsuarioPuntoImpresion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnUsuario() {
		return btnUsuario;
	}

	public void setBtnUsuario(JButton btnUsuario) {
		this.btnUsuario = btnUsuario;
	}

	public JComboBox getCmbPuntoImpresion() {
		return cmbPuntoImpresion;
	}

	public void setCmbPuntoImpresion(JComboBox cmbPuntoImpresion) {
		this.cmbPuntoImpresion = cmbPuntoImpresion;
	}

	public JScrollPane getSpTblUsuarioPuntoImpresion() {
		return spTblUsuarioPuntoImpresion;
	}

	public void setSpTblUsuarioPuntoImpresion(JScrollPane spTblUsuarioPuntoImpresion) {
		this.spTblUsuarioPuntoImpresion = spTblUsuarioPuntoImpresion;
	}

	public JTable getTblUsuarioPuntoImpresion() {
		return tblUsuarioPuntoImpresion;
	}

	public void setTblUsuarioPuntoImpresion(JTable tblUsuarioPuntoImpresion) {
		this.tblUsuarioPuntoImpresion = tblUsuarioPuntoImpresion;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
}
