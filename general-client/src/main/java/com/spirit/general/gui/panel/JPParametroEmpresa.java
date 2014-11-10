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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPParametroEmpresa extends MantenimientoModelImpl {
	public JPParametroEmpresa() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblValor = new JLabel();
		txtValor = new JTextField();
		spParametroEmpresaDetalles = new JScrollPane();
		tblParametroEmpresaDetalles = new JTable();
		lblTipoParametro = new JLabel();
		cmbTipoParametro = new JComboBox();
		lblMascara = new JLabel();
		txtMascara = new JTextField();
		lblValor1 = new JLabel();
		cmbFecha = new DateComboBox();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Parametro Empresa");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(180)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n:");
		add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtDescripcion, cc.xywh(5, 5, 7, 1));

		//---- lblValor ----
		lblValor.setText("Valor:");
		add(lblValor, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtValor, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spParametroEmpresaDetalles ========
		{
			
			//---- tblParametroEmpresaDetalles ----
			tblParametroEmpresaDetalles.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Descripci\u00f3n", "Tipo Parametro", "Valor"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblParametroEmpresaDetalles.setPreferredScrollableViewportSize(new Dimension(450, 400));
			tblParametroEmpresaDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			spParametroEmpresaDetalles.setViewportView(tblParametroEmpresaDetalles);
		}
		add(spParametroEmpresaDetalles, cc.xywh(3, 13, 11, 1));

		//---- lblTipoParametro ----
		lblTipoParametro.setText("Tipo Par\u00e1metro:");
		add(lblTipoParametro, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbTipoParametro, cc.xy(5, 7));

		//---- lblMascara ----
		lblMascara.setText("M\u00e1scara:");
		add(lblMascara, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtMascara, cc.xy(11, 7));

		//---- lblValor1 ----
		lblValor1.setText("Valor:");
		add(lblValor1, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFecha, cc.xy(11, 9));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblValor;
	private JTextField txtValor;
	private JScrollPane spParametroEmpresaDetalles;
	private JTable tblParametroEmpresaDetalles;
	private JLabel lblTipoParametro;
	private JComboBox cmbTipoParametro;
	private JLabel lblMascara;
	private JTextField txtMascara;
	private JLabel lblValor1;
	private DateComboBox cmbFecha;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public void setLblValor(JLabel lblValor) {
		this.lblValor = lblValor;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JScrollPane getSpParametroEmpresaDetalles() {
		return spParametroEmpresaDetalles;
	}

	public void setSpParametroEmpresaDetalles(JScrollPane spParametroEmpresaDetalles) {
		this.spParametroEmpresaDetalles = spParametroEmpresaDetalles;
	}

	public JTable getTblParametroEmpresaDetalles() {
		return tblParametroEmpresaDetalles;
	}

	public void setTblParametroEmpresaDetalles(JTable tblParametroEmpresaDetalles) {
		this.tblParametroEmpresaDetalles = tblParametroEmpresaDetalles;
	}

	public JLabel getLblTipoParametro() {
		return lblTipoParametro;
	}

	public void setLblTipoParametro(JLabel lblTipoParametro) {
		this.lblTipoParametro = lblTipoParametro;
	}

	public JComboBox getCmbTipoParametro() {
		return cmbTipoParametro;
	}

	public void setCmbTipoParametro(JComboBox cmbTipoParametro) {
		this.cmbTipoParametro = cmbTipoParametro;
	}

	public JLabel getLblMascara() {
		return lblMascara;
	}

	public void setLblMascara(JLabel lblMascara) {
		this.lblMascara = lblMascara;
	}

	public JTextField getTxtMascara() {
		return txtMascara;
	}

	public void setTxtMascara(JTextField txtMascara) {
		this.txtMascara = txtMascara;
	}

	public JLabel getLblValor1() {
		return lblValor1;
	}

	public void setLblValor1(JLabel lblValor1) {
		this.lblValor1 = lblValor1;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}
}
