package com.spirit.cartera.gui.panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public abstract class JPAprobacionPagos extends MantenimientoModelImpl {
	public JPAprobacionPagos() {
		initComponents();
		setName("Aprobacion de Pagos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		cbTodos = new JCheckBox();
		cmbEstado = new JComboBox();
		cbProveedor = new JCheckBox();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		lblEstado = new JLabel();
		btnConsultar = new JButton();
		cbFiltrarAprobados = new JCheckBox();
		lblTotalAprobado = new JLabel();
		spTblAprobacionPagos = new JScrollPane();
		tblAprobacionPagos = new JTable();
		txtTotalAprobado = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(250)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY6)
			}));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(3, 3));

		//---- cmbEstado ----
		cmbEstado.setEditable(false);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODO",
			"PRE-APROBADO",
			"APROBADO"
		}));
		add(cmbEstado, cc.xy(13, 5));

		//---- cbProveedor ----
		cbProveedor.setText("Proveedor");
		add(cbProveedor, cc.xy(3, 5));
		add(txtProveedor, cc.xy(5, 5));
		add(btnProveedor, cc.xywh(7, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(17, 5));

		//---- cbFiltrarAprobados ----
		cbFiltrarAprobados.setText("Filtrar Aprobados");
		add(cbFiltrarAprobados, cc.xy(17, 7));

		//---- lblTotalAprobado ----
		lblTotalAprobado.setText("Total Aprobado:");
		add(lblTotalAprobado, cc.xywh(13, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//======== spTblAprobacionPagos ========
		{

			//---- tblAprobacionPagos ----
			tblAprobacionPagos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Aprobar", "Proveedor", "# Factura", "F. Inicio", "F. Venc.", "Valor", "Saldo", "Abono", "Pre-Abono", "Observaci\u00f3n", "Desaprobar"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, String.class, Boolean.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, false, false, true, false, true, true
				};
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblAprobacionPagos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			spTblAprobacionPagos.setViewportView(tblAprobacionPagos);
		}
		add(spTblAprobacionPagos, cc.xywh(3, 11, 19, 5));

		//---- txtTotalAprobado ----
		txtTotalAprobado.setEditable(false);
		txtTotalAprobado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtTotalAprobado, cc.xywh(15, 17, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JCheckBox cbTodos;
	private JComboBox cmbEstado;
	private JCheckBox cbProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JLabel lblEstado;
	private JButton btnConsultar;
	private JCheckBox cbFiltrarAprobados;
	private JLabel lblTotalAprobado;
	private JScrollPane spTblAprobacionPagos;
	private JTable tblAprobacionPagos;
	private JTextField txtTotalAprobado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JScrollPane getSpTblAprobacionPagos() {
		return spTblAprobacionPagos;
	}

	public void setSpTblAprobacionPagos(JScrollPane spTblAprobacionPagos) {
		this.spTblAprobacionPagos = spTblAprobacionPagos;
	}

	public JTable getTblAprobacionPagos() {
		return tblAprobacionPagos;
	}

	public void setTblAprobacionPagos(JTable tblAprobacionPagos) {
		this.tblAprobacionPagos = tblAprobacionPagos;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public void setBtnProveedor(JButton btnProveedor) {
		this.btnProveedor = btnProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JCheckBox getCbProveedor() {
		return cbProveedor;
	}

	public void setCbProveedor(JCheckBox cbProveedor) {
		this.cbProveedor = cbProveedor;
	}

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}

	public JTextField getTxtTotalAprobado() {
		return txtTotalAprobado;
	}

	public void setTxtTotalAprobado(JTextField txtTotalAprobado) {
		this.txtTotalAprobado = txtTotalAprobado;
	}

	public JCheckBox getCbFiltrarAprobados() {
		return cbFiltrarAprobados;
	}
}
