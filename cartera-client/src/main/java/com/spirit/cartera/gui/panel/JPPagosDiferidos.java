package com.spirit.cartera.gui.panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

/**
 * @author xruiz
 */
public abstract class JPPagosDiferidos extends MantenimientoModelImpl {
	public JPPagosDiferidos() {
		initComponents();
		setName("Pagos Diferidos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		btnSeleccionarTodos = new JButton();
		spTblPagosDiferidos = new JScrollPane();
		tblPagosDiferidos = new JTable();
		cbTodos = new JCheckBox();
		btnConsultar = new JButton();
		cbProveedor = new JCheckBox();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(230)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.DLUY7, FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- btnSeleccionarTodos ----
		btnSeleccionarTodos.setText("Seleccionar Todo");
		add(btnSeleccionarTodos, cc.xywh(3, 7, 3, 1));

		//======== spTblPagosDiferidos ========
		{
			
			//---- tblPagosDiferidos ----
			tblPagosDiferidos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Actualizar", "Proveedor", "# Factura", "F. Venc.", "Valor", "Forma de Pago", "# Cheque"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblPagosDiferidos.setViewportView(tblPagosDiferidos);
		}
		add(spTblPagosDiferidos, cc.xywh(3, 9, 15, 5));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(3, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(13, 3));

		//---- cbProveedor ----
		cbProveedor.setText("Proveedor");
		add(cbProveedor, cc.xy(3, 5));
		add(txtProveedor, cc.xywh(5, 5, 3, 1));
		add(btnProveedor, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnProveedor.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnProveedor.setToolTipText("Buscar Proveedor");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnConsultar.setToolTipText("Consultar");
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblPagosDiferidos.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
	}

	//JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JButton btnSeleccionarTodos;
	private JScrollPane spTblPagosDiferidos;
	private JTable tblPagosDiferidos;
	private JCheckBox cbTodos;
	private JButton btnConsultar;
	private JCheckBox cbProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblPagosDiferidos() {
		return tblPagosDiferidos;
	}

	public void setTblPagosDiferidos(JTable tblPagosDiferidos) {
		this.tblPagosDiferidos = tblPagosDiferidos;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public void setBtnSeleccionarTodos(JButton btnSeleccionarTodos) {
		this.btnSeleccionarTodos = btnSeleccionarTodos;
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

	public JScrollPane getSpTblPagosDiferidos() {
		return spTblPagosDiferidos;
	}

	public void setSpTblPagosDiferidos(JScrollPane spTblPagosDiferidos) {
		this.spTblPagosDiferidos = spTblPagosDiferidos;
	}
}
