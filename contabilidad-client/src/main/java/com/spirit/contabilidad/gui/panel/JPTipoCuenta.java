package com.spirit.contabilidad.gui.panel;

import javax.swing.DefaultComboBoxModel;
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
public abstract class JPTipoCuenta extends MantenimientoModelImpl {
	public JPTipoCuenta() {
		initComponents();
		setName("Tipos de Cuenta");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JFormattedTextField();
		lblNombre = new JLabel();
		txtNombre = new JFormattedTextField();
		lblDebeHaber = new JLabel();
		cmbDebeHaber = new JComboBox();
		spTblTipoCuenta = new JScrollPane();
		tblTipoCuenta = new JTable();
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
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;50dlu):grow"),
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
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 9, 1));

		//---- lblDebeHaber ----
		lblDebeHaber.setText("Debe/Haber:");
		lblDebeHaber.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDebeHaber, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbDebeHaber ----
		cmbDebeHaber.setModel(new DefaultComboBoxModel(new String[] {
			"DEBE",
			"HABER"
		}));
		add(cmbDebeHaber, cc.xy(5, 7));

		//======== spTblTipoCuenta ========
		{
			
			//---- tblTipoCuenta ----
			tblTipoCuenta.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Debe/Haber"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblTipoCuenta.getTableHeader().setReorderingAllowed(false);
			spTblTipoCuenta.setViewportView(tblTipoCuenta);
		}
		add(spTblTipoCuenta, cc.xywh(3, 11, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JFormattedTextField txtCodigo;
	private JLabel lblNombre;
	private JFormattedTextField txtNombre;
	private JLabel lblDebeHaber;
	private JComboBox cmbDebeHaber;
	private JScrollPane spTblTipoCuenta;
	private JTable tblTipoCuenta;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbDebeHaber() {
		return cmbDebeHaber;
	}

	public void setCmbDebeHaber(JComboBox cmbDebeHaber) {
		this.cmbDebeHaber = cmbDebeHaber;
	}

	public JScrollPane getSpTblTipoCuenta() {
		return spTblTipoCuenta;
	}

	public void setSpTblTipoCuenta(JScrollPane spTblTipoCuenta) {
		this.spTblTipoCuenta = spTblTipoCuenta;
	}

	public JTable getTblTipoCuenta() {
		return tblTipoCuenta;
	}

	public void setTblTipoCuenta(JTable tblTipoCuenta) {
		this.tblTipoCuenta = tblTipoCuenta;
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
