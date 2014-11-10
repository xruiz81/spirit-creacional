package com.spirit.sri.gui.panel;

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
 * @author xruiz
 */
public abstract class JPIdentificacionTransacciones extends MantenimientoModelImpl {
	public JPIdentificacionTransacciones() {
		initComponents();
		setName("Identificacion por Transacciones");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblTipoTransaccion = new JLabel();
		cmbTipoTransaccion = new JComboBox();
		lblTipoIdentificacion = new JLabel();
		cmbTipoIdentificacion = new JComboBox();
		spTblIdentificacionTransaccion = new JScrollPane();
		tblIdentificacionTransaccion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Tipo de Sustento Tributario");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(180)),
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

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTipoTransaccion ----
		lblTipoTransaccion.setText("Tipo de Transacci\u00f3n:");
		lblTipoTransaccion.setToolTipText("Nombre del Centro de Gasto");
		lblTipoTransaccion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoTransaccion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbTipoTransaccion, cc.xywh(5, 5, 3, 1));

		//---- lblTipoIdentificacion ----
		lblTipoIdentificacion.setText("Tipo de Identificaci\u00f3n:");
		add(lblTipoIdentificacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoIdentificacion, cc.xywh(5, 7, 3, 1));

		//======== spTblIdentificacionTransaccion ========
		{
			
			//---- tblIdentificacionTransaccion ----
			tblIdentificacionTransaccion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Tipo de Transacci\u00f3n", "Tipo de Identificaci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblIdentificacionTransaccion.setViewportView(tblIdentificacionTransaccion);
		}
		add(spTblIdentificacionTransaccion, cc.xywh(3, 11, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblTipoTransaccion;
	private JComboBox cmbTipoTransaccion;
	private JLabel lblTipoIdentificacion;
	private JComboBox cmbTipoIdentificacion;
	private JScrollPane spTblIdentificacionTransaccion;
	private JTable tblIdentificacionTransaccion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbTipoIdentificacion() {
		return cmbTipoIdentificacion;
	}

	public void setCmbTipoIdentificacion(JComboBox cmbTipoIdentificacion) {
		this.cmbTipoIdentificacion = cmbTipoIdentificacion;
	}

	public JComboBox getCmbTipoTransaccion() {
		return cmbTipoTransaccion;
	}

	public void setCmbTipoTransaccion(JComboBox cmbTipoTransaccion) {
		this.cmbTipoTransaccion = cmbTipoTransaccion;
	}

	public JScrollPane getSpTblIdentificacionTransaccion() {
		return spTblIdentificacionTransaccion;
	}

	public void setSpTblIdentificacionTransaccion(
			JScrollPane spTblIdentificacionTransaccion) {
		this.spTblIdentificacionTransaccion = spTblIdentificacionTransaccion;
	}

	public JTable getTblIdentificacionTransaccion() {
		return tblIdentificacionTransaccion;
	}

	public void setTblIdentificacionTransaccion(JTable tblIdentificacionTransaccion) {
		this.tblIdentificacionTransaccion = tblIdentificacionTransaccion;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
}
