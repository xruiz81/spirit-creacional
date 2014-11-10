package com.spirit.contabilidad.gui.panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
public abstract class JPPlanCuenta extends MantenimientoModelImpl {
	public JPPlanCuenta() {
		initComponents();
		setName("Plan de Cuentas");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JFormattedTextField();
		lblNombre = new JLabel();
		txtNombre = new JFormattedTextField();
		lblFecha = new JLabel();
		txtFecha = new JFormattedTextField();
		lblMoneda = new JLabel();
		cmbMoneda = new JComboBox();
		lblMascara = new JLabel();
		txtMascara = new JTextField();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblPredeterminado = new JLabel();
		cmbPredeterminado = new JComboBox();
		spTblPlanCuenta = new JScrollPane();
		tblPlanCuenta = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
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
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 13}});

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));

		//---- lblFecha ----
		lblFecha.setText("Fecha Creaci\u00f3n:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFecha, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtFecha, cc.xywh(5, 7, 3, 1));

		//---- lblMoneda ----
		lblMoneda.setText("Moneda:");
		lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMoneda, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMoneda, cc.xywh(5, 9, 3, 1));

		//---- lblMascara ----
		lblMascara.setText("M\u00e1scara:");
		lblMascara.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMascara, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtMascara, cc.xywh(5, 11, 5, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEstado, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEstado, cc.xy(5, 13));

		//---- lblPredeterminado ----
		lblPredeterminado.setText("Predeterminado:");
		add(lblPredeterminado, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbPredeterminado ----
		cmbPredeterminado.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbPredeterminado, cc.xy(5, 15));

		//======== spTblPlanCuenta ========
		{
			
			//---- tblPlanCuenta ----
			tblPlanCuenta.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Moneda", "M\u00e1scara", "Estado", "Predet."
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblPlanCuenta.getTableHeader().setReorderingAllowed(false);
			spTblPlanCuenta.setViewportView(tblPlanCuenta);
		}
		add(spTblPlanCuenta, cc.xywh(3, 19, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JFormattedTextField txtCodigo;
	private JLabel lblNombre;
	private JFormattedTextField txtNombre;
	private JLabel lblFecha;
	private JFormattedTextField txtFecha;
	private JLabel lblMoneda;
	private JComboBox cmbMoneda;
	private JLabel lblMascara;
	private JTextField txtMascara;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblPredeterminado;
	private JComboBox cmbPredeterminado;
	private JScrollPane spTblPlanCuenta;
	private JTable tblPlanCuenta;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbMoneda() {
		return cmbMoneda;
	}

	public void setCmbMoneda(JComboBox cmbMoneda) {
		this.cmbMoneda = cmbMoneda;
	}

	public JTable getTblPlanCuenta() {
		return tblPlanCuenta;
	}

	public void setTblPlanCuenta(JTable tblPlanCuenta) {
		this.tblPlanCuenta = tblPlanCuenta;
	}

	public JFormattedTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JFormattedTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JFormattedTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JFormattedTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtMascara() {
		return txtMascara;
	}

	public void setTxtMascara(JTextField txtMascara) {
		this.txtMascara = txtMascara;
	}

	public JFormattedTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JFormattedTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JComboBox getCmbPredeterminado() {
		return cmbPredeterminado;
	}

	public void setCmbPredeterminado(JComboBox cmbPredeterminado) {
		this.cmbPredeterminado = cmbPredeterminado;
	}
}
