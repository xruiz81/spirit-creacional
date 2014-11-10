package com.spirit.cartera.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPCruceAnticipoCliente extends SpiritModelImpl {
	public JPCruceAnticipoCliente() {
		initComponents();
		setName("Cruce de Anticipos Cliente");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblOperadorNegocio = new JLabel();
		txtOperadorNegocio = new JTextField();
		btnBuscarOperadorNegocio = new JButton();
		btnConsultar = new JButton();
		lblFechaCruce = new JLabel();
		cmbFechaCruce = new DateComboBox();
		fsCuentasPorCobrar = compFactory.createSeparator("Cuentas por Cobrar:");
		lblValorAfecta = new JLabel();
		txtValorAfecta = new JTextField();
		spCuentasPorCobrar = new JScrollPane();
		tblCuentasPorCobrar = new JTable();
		btnActualizarValorAfecta = new JButton();
		spAnticipos = new JScrollPane();
		tblAnticipos = new JTable();
		fsAnticipos = compFactory.createSeparator("Anticipos:");
		CellConstraints cc = new CellConstraints();
		
		//======== this ========
		setLayout(new FormLayout(
				new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(120)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec("max(default;100dlu):grow"),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec("max(default;100dlu):grow"),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
				}));
		
		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Operador de Negocio:");
		add(lblOperadorNegocio, cc.xy(3, 3));
		
		//---- txtOperadorNegocio ----
		txtOperadorNegocio.setEditable(false);
		add(txtOperadorNegocio, cc.xywh(5, 3, 5, 1));
		
		//---- btnBuscarOperadorNegocio ----
		btnBuscarOperadorNegocio.setText("B");
		add(btnBuscarOperadorNegocio, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		
		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(15, 3, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		
		//---- lblFechaCruce ----
		lblFechaCruce.setText("Fecha de Cruce:");
		add(lblFechaCruce, cc.xy(3, 5));
		add(cmbFechaCruce, cc.xywh(5, 5, 3, 1));
		add(fsCuentasPorCobrar, cc.xywh(3, 9, 15, 1));
		
		//---- lblValorAfecta ----
		lblValorAfecta.setText("Valor afecta:");
		lblValorAfecta.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblValorAfecta, cc.xywh(11, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		
		//---- txtValorAfecta ----
		txtValorAfecta.setEditable(false);
		txtValorAfecta.setHorizontalAlignment(JTextField.RIGHT);
		add(txtValorAfecta, cc.xywh(13, 17, 3, 1));
		
		//======== spCuentasPorCobrar ========
		{
			spCuentasPorCobrar.setFocusTraversalPolicyProvider(false);
			
			//---- tblCuentasPorCobrar ----
			tblCuentasPorCobrar.setModel(new DefaultTableModel(
					new Object[][] {
							{Boolean.FALSE, null, null, null, null},
					},
					new String[] {
							"Seleccionar", "# Fact.", "Fecha", "Detalle", "Saldo"
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
			spCuentasPorCobrar.setViewportView(tblCuentasPorCobrar);
		}
		add(spCuentasPorCobrar, cc.xywh(3, 11, 15, 5));
		
		//---- btnActualizarValorAfecta ----
		btnActualizarValorAfecta.setText("U");
		add(btnActualizarValorAfecta, cc.xywh(17, 17, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		
		//======== spAnticipos ========
		{
			
			//---- tblAnticipos ----
			tblAnticipos.setModel(new DefaultTableModel(
					new Object[][] {
							{null, null, null, null},
					},
					new String[] {
							"C\u00f3digo", "Fecha", "Detalle", "Saldo"
					}
			) {
				boolean[] columnEditable = new boolean[] {
						false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spAnticipos.setViewportView(tblAnticipos);
		}
		add(spAnticipos, cc.xywh(3, 23, 15, 5));
		add(fsAnticipos, cc.xywh(3, 21, 15, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarOperadorNegocio.setText("");
		btnBuscarOperadorNegocio.setToolTipText("Buscar Operador de Negocio");
		btnBuscarOperadorNegocio.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnActualizarValorAfecta.setText("");
		btnActualizarValorAfecta.setToolTipText("Actualizar valor a aplicar");
		btnActualizarValorAfecta.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblCuentasPorCobrar.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		tblAnticipos.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendederHorizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		tblCuentasPorCobrar.getColumnModel().getColumn(1).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblCuentasPorCobrar.getColumnModel().getColumn(2).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblAnticipos.getColumnModel().getColumn(0).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblAnticipos.getColumnModel().getColumn(1).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
	}
	
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JButton btnBuscarOperadorNegocio;
	private JButton btnConsultar;
	private JLabel lblFechaCruce;
	private DateComboBox cmbFechaCruce;
	private JComponent fsCuentasPorCobrar;
	private JLabel lblValorAfecta;
	private JTextField txtValorAfecta;
	private JScrollPane spCuentasPorCobrar;
	private JTable tblCuentasPorCobrar;
	private JButton btnActualizarValorAfecta;
	private JScrollPane spAnticipos;
	private JTable tblAnticipos;
	private JComponent fsAnticipos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}
	
	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}
	
	public JTable getTblCuentasPorCobrar() {
		return tblCuentasPorCobrar;
	}
	
	public void setTblCuentasPorCobrar(JTable tblCuentasPorCobrar) {
		this.tblCuentasPorCobrar = tblCuentasPorCobrar;
	}
	
	public JTable getTblAnticipos() {
		return tblAnticipos;
	}
	
	public void setTblAnticipos(JTable tblAnticipos) {
		this.tblAnticipos = tblAnticipos;
	}
	
	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}
	
	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}
	
	public JTextField getTxtValorAfecta() {
		return txtValorAfecta;
	}
	
	public void setTxtValorAfecta(JTextField txtValorAfecta) {
		this.txtValorAfecta = txtValorAfecta;
	}
	
	public JButton getBtnConsultar() {
		return btnConsultar;
	}
	
	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
	
	public DateComboBox getCmbFechaCruce() {
		return cmbFechaCruce;
	}
	
	public void setCmbFechaCruce(DateComboBox cmbFechaCruce) {
		this.cmbFechaCruce = cmbFechaCruce;
	}
	
	public JButton getBtnActualizarValorAfecta() {
		return btnActualizarValorAfecta;
	}
	
	public void setBtnActualizarValorAfecta(JButton btnActualizarValorAfecta) {
		this.btnActualizarValorAfecta = btnActualizarValorAfecta;
	}
}
