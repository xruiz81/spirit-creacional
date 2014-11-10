package com.spirit.nomina.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPContratoPlantilla extends SpiritModelImpl {
	public JPContratoPlantilla() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpContrato = new JideTabbedPane();
		panelGeneral = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		gfsRubros = compFactory.createSeparator("Rubros");
		spTblRubro = new JScrollPane();
		tblRubro = new JTable();
		gfsRubrosContrato = compFactory.createSeparator("Rubros por Contrato");
		panelBotones = new JPanel();
		btnRemoverRubroContrato = new JButton();
		btnAgregarRubroContrato = new JButton();
		spTblRubroContrato = new JScrollPane();
		tblRubroContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Plantilla de Contrato");
		setFocusCycleRoot(true);
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpContrato ========
		{
			
			//======== panelGeneral ========
			{
				panelGeneral.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(25)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX11),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
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
				
				//---- lblCodigo ----
				lblCodigo.setText("C\u00f3digo:");
				panelGeneral.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCodigo ----
				txtCodigo.setEditable(true);
				panelGeneral.add(txtCodigo, cc.xywh(5, 3, 3, 1));
				
				//---- lblTipoContrato ----
				lblTipoContrato.setText("Tipo de Contrato:");
				panelGeneral.add(lblTipoContrato, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelGeneral.add(cmbTipoContrato, cc.xywh(5, 5, 7, 1));
				
				//---- lblObservacion ----
				lblObservacion.setText("Observaci\u00f3n:");
				panelGeneral.add(lblObservacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelGeneral.add(txtObservacion, cc.xywh(5, 7, 11, 1));
				panelGeneral.add(gfsRubros, cc.xywh(3, 11, 15, 1));
				
				//======== spTblRubro ========
				{
					
					//---- tblRubro ----
					tblRubro.setModel(new DefaultTableModel(
						new Object[][] {
							{Boolean.FALSE, null, null},
						},
						new String[] {
							" ", "Rubro", "Tipo de Rubro"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubro.setViewportView(tblRubro);
				}
				panelGeneral.add(spTblRubro, cc.xywh(3, 13, 15, 3));
				panelGeneral.add(gfsRubrosContrato, cc.xywh(3, 19, 15, 1));
				
				//======== panelBotones ========
				{
					panelBotones.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnRemoverRubroContrato ----
					btnRemoverRubroContrato.setText("D");
					panelBotones.add(btnRemoverRubroContrato, cc.xy(3, 1));
					
					//---- btnAgregarRubroContrato ----
					btnAgregarRubroContrato.setText("A");
					panelBotones.add(btnAgregarRubroContrato, cc.xy(1, 1));
				}
				panelGeneral.add(panelBotones, cc.xywh(3, 21, 3, 1));
				
				//======== spTblRubroContrato ========
				{
					
					//---- tblRubroContrato ----
					tblRubroContrato.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null},
						},
						new String[] {
							"Rubro", "Tipo de Rubro", "Valor"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, true
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblRubroContrato.setViewportView(tblRubroContrato);
				}
				panelGeneral.add(spTblRubroContrato, cc.xywh(3, 23, 15, 3));
			}
			jtpContrato.addTab("General", panelGeneral);
			
		}
		add(jtpContrato, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpContrato;
	private JPanel panelGeneral;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JComponent gfsRubros;
	private JScrollPane spTblRubro;
	private JTable tblRubro;
	private JComponent gfsRubrosContrato;
	private JPanel panelBotones;
	private JButton btnRemoverRubroContrato;
	private JButton btnAgregarRubroContrato;
	private JScrollPane spTblRubroContrato;
	private JTable tblRubroContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideTabbedPane getJtpContrato() {
		return jtpContrato;
	}

	public JPanel getPanelGeneral() {
		return panelGeneral;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public JScrollPane getSpTblRubro() {
		return spTblRubro;
	}

	public JTable getTblRubro() {
		return tblRubro;
	}

	public JComponent getGfsRubrosContrato() {
		return gfsRubrosContrato;
	}

	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public JButton getBtnRemoverRubroContrato() {
		return btnRemoverRubroContrato;
	}

	public JButton getBtnAgregarRubroContrato() {
		return btnAgregarRubroContrato;
	}

	public JScrollPane getSpTblRubroContrato() {
		return spTblRubroContrato;
	}

	public JTable getTblRubroContrato() {
		return tblRubroContrato;
	}
}
