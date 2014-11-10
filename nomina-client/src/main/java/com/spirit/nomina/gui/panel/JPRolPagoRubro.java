package com.spirit.nomina.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPRolPagoRubro extends SpiritModelImpl {
	public JPRolPagoRubro() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblRolPago = new JLabel();
		txtRolPago = new JTextField();
		btnBuscarRolPago = new JButton();
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnBuscarEmpleado = new JButton();
		lblContrato = new JLabel();
		txtContrato = new JTextField();
		btnBuscarContrato = new JButton();
		gfsRubros = compFactory.createSeparator("Rubros");
		jtpRubros = new JideTabbedPane();
		pnRubroNormal = new JPanel();
		lblRubroNormal = new JLabel();
		txtRubroNormal = new JTextField();
		btnBuscarRubro = new JButton();
		lblValorNormal = new JLabel();
		txtValorNormal = new JTextField();
		panelBtnRubros = new JPanel();
		btnActualizarRubroNormal = new JButton();
		btnRemoverRubroNormal = new JButton();
		btnAgregarRubroNormal = new JButton();
		spTblRubroNormal = new JScrollPane();
		tblRubroNormal = new JTable();
		pnRubroEventual = new JPanel();
		lblRubroEventual = new JLabel();
		cmbRubroEventual = new JComboBox();
		lblFecha = new JLabel();
		cmbFechaRubroEventual = new DateComboBox();
		lblTipoRubroEventual = new JLabel();
		cmbTipoRolEventual = new JComboBox();
		lblValorRubroEventual = new JLabel();
		txtValorRubroEventual = new JTextField();
		lblObservacionRubroEventual = new JLabel();
		txtObservacionRubroEventual = new JTextField();
		panelBtnRubroEventual = new JPanel();
		btnActualizarRubroEventual = new JButton();
		btnRemoverRubroEventual = new JButton();
		btnAgregarRubroEventual = new JButton();
		scrollPane1 = new JScrollPane();
		tblRubroEventual = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Rol de Pago por Rubro");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblRolPago ----
		lblRolPago.setText("Rol de Pago:");
		add(lblRolPago, cc.xy(3, 3));
		add(txtRolPago, cc.xywh(5, 3, 3, 1));

		//---- btnBuscarRolPago ----
		btnBuscarRolPago.setText("B");
		add(btnBuscarRolPago, cc.xy(9, 3));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xy(3, 5));
		add(txtEmpleado, cc.xywh(5, 5, 7, 1));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xy(13, 5));

		//---- lblContrato ----
		lblContrato.setText("Contrato:");
		add(lblContrato, cc.xy(3, 7));
		add(txtContrato, cc.xy(5, 7));

		//---- btnBuscarContrato ----
		btnBuscarContrato.setText("B");
		add(btnBuscarContrato, cc.xy(7, 7));
		add(gfsRubros, cc.xywh(3, 11, 17, 1));

		//======== jtpRubros ========
		{
			
			//======== pnRubroNormal ========
			{
				pnRubroNormal.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					new RowSpec[] {
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblRubroNormal ----
				lblRubroNormal.setText("Rubro:");
				pnRubroNormal.add(lblRubroNormal, cc.xy(3, 3));
				pnRubroNormal.add(txtRubroNormal, cc.xy(5, 3));
				
				//---- btnBuscarRubro ----
				btnBuscarRubro.setText("B");
				pnRubroNormal.add(btnBuscarRubro, cc.xy(7, 3));
				
				//---- lblValorNormal ----
				lblValorNormal.setText("Valor:");
				pnRubroNormal.add(lblValorNormal, cc.xy(3, 5));
				pnRubroNormal.add(txtValorNormal, cc.xy(5, 5));
				
				//======== panelBtnRubros ========
				{
					panelBtnRubros.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnActualizarRubroNormal ----
					btnActualizarRubroNormal.setText("U");
					btnActualizarRubroNormal.setHorizontalAlignment(SwingConstants.CENTER);
					panelBtnRubros.add(btnActualizarRubroNormal, cc.xy(3, 1));
					
					//---- btnRemoverRubroNormal ----
					btnRemoverRubroNormal.setText("D");
					panelBtnRubros.add(btnRemoverRubroNormal, cc.xy(5, 1));
					
					//---- btnAgregarRubroNormal ----
					btnAgregarRubroNormal.setText("A");
					panelBtnRubros.add(btnAgregarRubroNormal, cc.xy(1, 1));
				}
				pnRubroNormal.add(panelBtnRubros, cc.xywh(3, 9, 3, 1));
				
				//======== spTblRubroNormal ========
				{
					
					//---- tblRubroNormal ----
					tblRubroNormal.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null},
						},
						new String[] {
							"Rubro", "Tipo de Rubro", "Valor"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Double.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubroNormal.setViewportView(tblRubroNormal);
				}
				pnRubroNormal.add(spTblRubroNormal, cc.xywh(3, 11, 7, 3));
			}
			jtpRubros.addTab("Normales", pnRubroNormal);
			
			
			//======== pnRubroEventual ========
			{
				pnRubroEventual.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(150)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(55)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(25)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					new RowSpec[] {
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblRubroEventual ----
				lblRubroEventual.setText("Rubro Eventual:");
				pnRubroEventual.add(lblRubroEventual, cc.xy(3, 3));
				pnRubroEventual.add(cmbRubroEventual, cc.xy(5, 3));
				
				//---- lblFecha ----
				lblFecha.setText("Fecha:");
				pnRubroEventual.add(lblFecha, cc.xy(7, 3));
				pnRubroEventual.add(cmbFechaRubroEventual, cc.xywh(9, 3, 3, 1));
				
				//---- lblTipoRubroEventual ----
				lblTipoRubroEventual.setText("Tipo Rol:");
				pnRubroEventual.add(lblTipoRubroEventual, cc.xy(3, 5));
				pnRubroEventual.add(cmbTipoRolEventual, cc.xy(5, 5));
				
				//---- lblValorRubroEventual ----
				lblValorRubroEventual.setText("Valor:");
				pnRubroEventual.add(lblValorRubroEventual, cc.xy(7, 5));
				pnRubroEventual.add(txtValorRubroEventual, cc.xy(9, 5));
				
				//---- lblObservacionRubroEventual ----
				lblObservacionRubroEventual.setText("Observaci\u00f3n:");
				pnRubroEventual.add(lblObservacionRubroEventual, cc.xy(3, 7));
				pnRubroEventual.add(txtObservacionRubroEventual, cc.xywh(5, 7, 5, 1));
				
				//======== panelBtnRubroEventual ========
				{
					panelBtnRubroEventual.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnActualizarRubroEventual ----
					btnActualizarRubroEventual.setText("U");
					btnActualizarRubroEventual.setHorizontalAlignment(SwingConstants.CENTER);
					panelBtnRubroEventual.add(btnActualizarRubroEventual, cc.xy(3, 1));
					
					//---- btnRemoverRubroEventual ----
					btnRemoverRubroEventual.setText("D");
					panelBtnRubroEventual.add(btnRemoverRubroEventual, cc.xy(5, 1));
					
					//---- btnAgregarRubroEventual ----
					btnAgregarRubroEventual.setText("A");
					panelBtnRubroEventual.add(btnAgregarRubroEventual, cc.xy(1, 1));
				}
				pnRubroEventual.add(panelBtnRubroEventual, cc.xywh(3, 11, 3, 1));
				
				//======== scrollPane1 ========
				{
					
					//---- tblRubroEventual ----
					tblRubroEventual.setModel(new DefaultTableModel(
						new Object[][] {
							{null, "", null, null},
						},
						new String[] {
							"Rubro", "Fecha", "Tipo de Rol", "Valor"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Double.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					scrollPane1.setViewportView(tblRubroEventual);
				}
				pnRubroEventual.add(scrollPane1, cc.xywh(3, 13, 11, 3));
			}
			jtpRubros.addTab("Eventuales", pnRubroEventual);
			
		}
		add(jtpRubros, cc.xywh(3, 13, 17, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblRolPago;
	private JTextField txtRolPago;
	private JButton btnBuscarRolPago;
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnBuscarEmpleado;
	private JLabel lblContrato;
	private JTextField txtContrato;
	private JButton btnBuscarContrato;
	private JComponent gfsRubros;
	private JideTabbedPane jtpRubros;
	private JPanel pnRubroNormal;
	private JLabel lblRubroNormal;
	private JTextField txtRubroNormal;
	private JButton btnBuscarRubro;
	private JLabel lblValorNormal;
	private JTextField txtValorNormal;
	private JPanel panelBtnRubros;
	private JButton btnActualizarRubroNormal;
	private JButton btnRemoverRubroNormal;
	private JButton btnAgregarRubroNormal;
	private JScrollPane spTblRubroNormal;
	private JTable tblRubroNormal;
	private JPanel pnRubroEventual;
	private JLabel lblRubroEventual;
	private JComboBox cmbRubroEventual;
	private JLabel lblFecha;
	private DateComboBox cmbFechaRubroEventual;
	private JLabel lblTipoRubroEventual;
	private JComboBox cmbTipoRolEventual;
	private JLabel lblValorRubroEventual;
	private JTextField txtValorRubroEventual;
	private JLabel lblObservacionRubroEventual;
	private JTextField txtObservacionRubroEventual;
	private JPanel panelBtnRubroEventual;
	private JButton btnActualizarRubroEventual;
	private JButton btnRemoverRubroEventual;
	private JButton btnAgregarRubroEventual;
	private JScrollPane scrollPane1;
	private JTable tblRubroEventual;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblRolPago() {
		return lblRolPago;
	}

	public JTextField getTxtRolPago() {
		return txtRolPago;
	}

	public JButton getBtnBuscarRolPago() {
		return btnBuscarRolPago;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public JLabel getLblContrato() {
		return lblContrato;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JButton getBtnBuscarContrato() {
		return btnBuscarContrato;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JideTabbedPane getJtpRubros() {
		return jtpRubros;
	}

	public JPanel getPnRubroNormal() {
		return pnRubroNormal;
	}

	public JLabel getLblRubroNormal() {
		return lblRubroNormal;
	}

	public JTextField getTxtRubroNormal() {
		return txtRubroNormal;
	}

	public JButton getBtnBuscarRubro() {
		return btnBuscarRubro;
	}

	public JLabel getLblValorNormal() {
		return lblValorNormal;
	}

	public JTextField getTxtValorNormal() {
		return txtValorNormal;
	}

	public JPanel getPanelBtnRubros() {
		return panelBtnRubros;
	}

	public JButton getBtnActualizarRubroNormal() {
		return btnActualizarRubroNormal;
	}

	public JButton getBtnRemoverRubroNormal() {
		return btnRemoverRubroNormal;
	}

	public JButton getBtnAgregarRubroNormal() {
		return btnAgregarRubroNormal;
	}

	public JScrollPane getSpTblRubroNormal() {
		return spTblRubroNormal;
	}

	public JTable getTblRubroNormal() {
		return tblRubroNormal;
	}

	public JPanel getPnRubroEventual() {
		return pnRubroEventual;
	}

	public JLabel getLblRubroEventual() {
		return lblRubroEventual;
	}

	public JComboBox getCmbRubroEventual() {
		return cmbRubroEventual;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public DateComboBox getCmbFechaRubroEventual() {
		return cmbFechaRubroEventual;
	}

	public JLabel getLblTipoRubroEventual() {
		return lblTipoRubroEventual;
	}

	public JComboBox getCmbTipoRolEventual() {
		return cmbTipoRolEventual;
	}

	public JLabel getLblValorRubroEventual() {
		return lblValorRubroEventual;
	}

	public JTextField getTxtValorRubroEventual() {
		return txtValorRubroEventual;
	}

	public JLabel getLblObservacionRubroEventual() {
		return lblObservacionRubroEventual;
	}

	public JTextField getTxtObservacionRubroEventual() {
		return txtObservacionRubroEventual;
	}

	public JPanel getPanelBtnRubroEventual() {
		return panelBtnRubroEventual;
	}

	public JButton getBtnActualizarRubroEventual() {
		return btnActualizarRubroEventual;
	}

	public JButton getBtnRemoverRubroEventual() {
		return btnRemoverRubroEventual;
	}

	public JButton getBtnAgregarRubroEventual() {
		return btnAgregarRubroEventual;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public JTable getTblRubroEventual() {
		return tblRubroEventual;
	}
}
