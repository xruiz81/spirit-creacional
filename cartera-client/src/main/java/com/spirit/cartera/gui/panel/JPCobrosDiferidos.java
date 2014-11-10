package com.spirit.cartera.gui.panel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPCobrosDiferidos extends MantenimientoModelImpl {
	public JPCobrosDiferidos() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbTodos = new JCheckBox();
		btnConsultar = new JButton();
		btnSeleccionarTodos = new JButton();
		spTblCobrosDiferidos = new JScrollPane();
		tblCobrosDiferidos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Cobros Diferidos");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(250)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(8)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, 1.0),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCliente, cc.xy(5, 3));
		add(btnBuscarCliente, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(11, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(15, 3));

		//---- btnSeleccionarTodos ----
		btnSeleccionarTodos.setText("Seleccionar Todos");
		add(btnSeleccionarTodos, cc.xywh(3, 7, 3, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== spTblCobrosDiferidos ========
		{
			
			//---- tblCobrosDiferidos ----
			tblCobrosDiferidos.setModel(new DefaultTableModel(
				new Object[][] {
					{Boolean.FALSE, "", null, null, null},
				},
				new String[] {
					"Actualizar", "Cliente", "F. Cartera", "Valor", "Cuenta Bancaria"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, true
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblCobrosDiferidos.setViewportView(tblCobrosDiferidos);
		}
		add(spTblCobrosDiferidos, cc.xywh(3, 9, 15, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		getBtnBuscarCliente().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnBuscarCliente().setToolTipText("Buscar Proveedor");
		getBtnConsultar().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnConsultar().setToolTipText("Consultar");
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblCobrosDiferidos.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbTodos;
	private JButton btnConsultar;
	private JButton btnSeleccionarTodos;
	private JScrollPane spTblCobrosDiferidos;
	private JTable tblCobrosDiferidos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public void setBtnSeleccionarTodos(JButton btnSeleccionarTodos) {
		this.btnSeleccionarTodos = btnSeleccionarTodos;
	}

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}

	public JScrollPane getSpTblCobrosDiferidos() {
		return spTblCobrosDiferidos;
	}

	public void setSpTblCobrosDiferidos(JScrollPane spTblCobrosDiferidos) {
		this.spTblCobrosDiferidos = spTblCobrosDiferidos;
	}

	public JTable getTblCobrosDiferidos() {
		return tblCobrosDiferidos;
	}

	public void setTblCobrosDiferidos(JTable tblCobrosDiferidos) {
		this.tblCobrosDiferidos = tblCobrosDiferidos;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}
}
