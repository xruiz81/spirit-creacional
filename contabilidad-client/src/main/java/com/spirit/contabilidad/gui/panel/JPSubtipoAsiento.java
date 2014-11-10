package com.spirit.contabilidad.gui.panel;

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

/*
 * Created by JFormDesigner on Wed Feb 14 12:16:12 COT 2007
 */

/**
 * @author Antonio Seiler
 */
public abstract class JPSubtipoAsiento extends MantenimientoModelImpl {
	public JPSubtipoAsiento() {
		initComponents();
		setName("Subtipo de Asiento");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblTipoAsiento = new JLabel();
		cmbTipoAsiento = new JComboBox();
		lblOrden = new JLabel();
		txtOrden = new JTextField();
		lblStatus = new JLabel();
		cmbStatus = new JComboBox();
		spTblSubtipoAsiento = new JScrollPane();
		tblSubtipoAsiento = new JTable();
		CellConstraints cc = new CellConstraints();

		// ======== this ========
		setLayout(new FormLayout(new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)) }, new RowSpec[] {
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT,
						FormSpec.DEFAULT_GROW), FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)) }));
		((FormLayout) getLayout()).setColumnGroups(new int[][] { { 5, 7 } });

		// ---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT,
				CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		// ---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT,
				CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));

		// ---- lblTipoAsiento ----
		lblTipoAsiento.setText("Tipo Asiento:");
		lblTipoAsiento.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoAsiento, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT,
				CellConstraints.DEFAULT));
		add(cmbTipoAsiento, cc.xywh(5, 7, 5, 1));

		// ---- lblOrden ----
		lblOrden.setText("Orden:");
		lblOrden.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblOrden, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT,
				CellConstraints.DEFAULT));
		add(txtOrden, cc.xy(5, 9));

		// ---- lblStatus ----
		lblStatus.setText("Estado:");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblStatus, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT,
				CellConstraints.DEFAULT));
		add(cmbStatus, cc.xy(5, 11));

		// ======== spTblSubtipoAsiento ========
		{

			// ---- tblSubtipoAsiento ----
			tblSubtipoAsiento.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "C\u00f3digo", "Nombre", "Tipo Asiento",
							"Orden", "Estado" }) {
				boolean[] columnEditable = new boolean[] { false, false, false,
						false, false };

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblSubtipoAsiento.getTableHeader().setReorderingAllowed(false);
			spTblSubtipoAsiento.setViewportView(tblSubtipoAsiento);
		}
		add(spTblSubtipoAsiento, cc.xywh(3, 15, 9, 5));
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JLabel lblCodigo;

	private JTextField txtCodigo;

	private JLabel lblNombre;

	private JTextField txtNombre;

	private JLabel lblTipoAsiento;

	private JComboBox cmbTipoAsiento;

	private JLabel lblOrden;

	private JTextField txtOrden;

	private JLabel lblStatus;

	private JComboBox cmbStatus;

	private JScrollPane spTblSubtipoAsiento;

	private JTable tblSubtipoAsiento;

	// JFormDesigner - End of variables declaration //GEN-END:variables

	public JComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(JComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public JComboBox getCmbTipoAsiento() {
		return cmbTipoAsiento;
	}

	public void setCmbTipoAsiento(JComboBox cmbTipoAsiento) {
		this.cmbTipoAsiento = cmbTipoAsiento;
	}

	public JTable getTblSubtipoAsiento() {
		return tblSubtipoAsiento;
	}

	public void setTblSubtipoAsiento(JTable tblSubtipoAsiento) {
		this.tblSubtipoAsiento = tblSubtipoAsiento;
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

	public JTextField getTxtOrden() {
		return txtOrden;
	}

	public void setTxtOrden(JTextField txtOrden) {
		this.txtOrden = txtOrden;
	}

}
