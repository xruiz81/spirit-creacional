package com.spirit.nomina.gui.panel;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
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

/**
 * @author Antonio Seiler
 */
public abstract class JPRolEnvioMail extends SpiritModelImpl {
	/**
	 * 
	 */
	 
	public JPRolEnvioMail() {
		setName("Rol Envio Mail");		
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblTipoRubro = new JLabel();
		panelTipoRubros = new JPanel();
		rbNormal = new JRadioButton();
		rbAnticipos = new JRadioButton();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblTipoContrato = new JLabel();
		lblFechaFin = new JLabel();
		cmbTipoContrato = new JComboBox();
		cmbFechaFin = new DateComboBox();
		btnFiltrar = new JButton();
		splitPnPagoRol = new JSplitPane();
		panelRolPago = new JPanel();
		gfsRubros = compFactory.createSeparator("Rol de Pago");
		spTblRolPago = new JScrollPane();
		tblRolPago = new JTable();
		panelDetalleRolPago = new JPanel();
		btnSeleccionarTodos = new JButton();
		btnEnviarRolMail = new JButton();
		gfsRubros2 = compFactory.createSeparator("Detalle Rol de Pago");
		spTblRolPagoDetalleQyM = new JScrollPane();
		tblRolPagoDetalleQyM = new JTable();
		spTblRolPagoDetalleAyD = new JScrollPane();
		tblRolPagoDetalleAportesDecimos = new JTable();
		spTblRolPagoDetalleAnticipos = new JScrollPane();
		tblRolPagoDetalleAnticipos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Pago de Rol");
		setMinimumSize(new Dimension(620, 350));
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(130)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
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
				new RowSpec("fill:min(default;250dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRubro ----
		lblTipoRubro.setText("Tipo de Rubros:");
		add(lblTipoRubro, cc.xy(3, 3));

		//======== panelTipoRubros ========
		{
			panelTipoRubros.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- rbNormal ----
			rbNormal.setText("Normales");
			panelTipoRubros.add(rbNormal, cc.xy(1, 1));
			
			//---- rbAnticipos ----
			rbAnticipos.setText("Anticipos/Eventuales");
			panelTipoRubros.add(rbAnticipos, cc.xy(3, 1));
		}
		add(panelTipoRubros, cc.xy(5, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio: ");
		add(lblFechaInicio, cc.xy(9, 3));
		add(cmbFechaInicio, cc.xywh(11, 3, 3, 1));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo de Contrato:");
		add(lblTipoContrato, cc.xy(3, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin: ");
		add(lblFechaFin, cc.xy(9, 5));
		add(cmbTipoContrato, cc.xy(5, 5));
		add(cmbFechaFin, cc.xywh(11, 5, 3, 1));

		//---- btnFiltrar ----
		btnFiltrar.setText("Filtrar");
		add(btnFiltrar, cc.xy(13, 7));

		//======== splitPnPagoRol ========
		{
			splitPnPagoRol.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPnPagoRol.setBorder(null);
			
			//======== panelRolPago ========
			{
				panelRolPago.setMinimumSize(new Dimension(70, 120));
				panelRolPago.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				panelRolPago.add(gfsRubros, cc.xy(1, 1));
				
				//======== spTblRolPago ========
				{
					
					//---- tblRolPago ----
					tblRolPago.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, "", null, null},
						},
						new String[] {
							"Tipo de Rol", "Tipo de Contrato", "Mes", "A\u00f1o", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblRolPago.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					spTblRolPago.setViewportView(tblRolPago);
				}
				panelRolPago.add(spTblRolPago, cc.xywh(1, 3, 1, 3));
			}
			splitPnPagoRol.setLeftComponent(panelRolPago);
			
			//======== panelDetalleRolPago ========
			{
				panelDetalleRolPago.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				
				//---- btnSeleccionarTodos ----
				btnSeleccionarTodos.setText("Seleccionar / Deseleccionar");
				panelDetalleRolPago.add(btnSeleccionarTodos, cc.xy(1, 3));
				
				//---- btnEnviarRolMail ----
				btnEnviarRolMail.setText("Enviar Rol por Correo");
				panelDetalleRolPago.add(btnEnviarRolMail, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				panelDetalleRolPago.add(gfsRubros2, cc.xywh(1, 5, 3, 1));
				
				//======== spTblRolPagoDetalleQyM ========
				{
					
					//---- tblRolPagoDetalleQyM ----
					tblRolPagoDetalleQyM.setModel(new DefaultTableModel(
						new Object[][] {
							{Boolean.FALSE, null, null, null, null, null},
						},
						new String[] {
							" ", "Nombre", "Total Ingresos", "Total Egresos", "Total", "Email"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Double.class, Double.class, Double.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, false, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblRolPagoDetalleQyM.setColumnSelectionAllowed(false);
					tblRolPagoDetalleQyM.setCellSelectionEnabled(true);
					tblRolPagoDetalleQyM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblRolPagoDetalleQyM.setViewportView(tblRolPagoDetalleQyM);
				}
				panelDetalleRolPago.add(spTblRolPagoDetalleQyM, cc.xywh(1, 7, 3, 3));
			}
			splitPnPagoRol.setRightComponent(panelDetalleRolPago);
		}
		add(splitPnPagoRol, cc.xywh(3, 9, 13, 1));

		//======== spTblRolPagoDetalleAyD ========
		{
			
			//---- tblRolPagoDetalleAportesDecimos ----
			tblRolPagoDetalleAportesDecimos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					" ", "Nombre", "Total", "Email"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Double.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPagoDetalleAportesDecimos.setCellSelectionEnabled(true);
			tblRolPagoDetalleAportesDecimos.setColumnSelectionAllowed(false);
			tblRolPagoDetalleAportesDecimos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			spTblRolPagoDetalleAyD.setViewportView(tblRolPagoDetalleAportesDecimos);
		}

		//======== spTblRolPagoDetalleAnticipos ========
		{
			
			//---- tblRolPagoDetalleAnticipos ----
			tblRolPagoDetalleAnticipos.setModel(new DefaultTableModel(
				new Object[][] {
					{Boolean.FALSE, null, null, null, null},
				},
				new String[] {
					" ", "Nombre Rubro", "Total", "Beneficiario", "Email"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Double.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, true, false, true, true
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPagoDetalleAnticipos.setCellSelectionEnabled(true);
			tblRolPagoDetalleAnticipos.setColumnSelectionAllowed(false);
			tblRolPagoDetalleAnticipos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			spTblRolPagoDetalleAnticipos.setViewportView(tblRolPagoDetalleAnticipos);
		}

		//---- buttonGroupTipoRubro ----
		ButtonGroup buttonGroupTipoRubro = new ButtonGroup();
		buttonGroupTipoRubro.add(rbNormal);
		buttonGroupTipoRubro.add(rbAnticipos);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoRubro;
	private JPanel panelTipoRubros;
	private JRadioButton rbNormal;
	private JRadioButton rbAnticipos;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblTipoContrato;
	private JLabel lblFechaFin;
	private JComboBox cmbTipoContrato;
	private DateComboBox cmbFechaFin;
	private JButton btnFiltrar;
	private JSplitPane splitPnPagoRol;
	private JPanel panelRolPago;
	private JComponent gfsRubros;
	private JScrollPane spTblRolPago;
	private JTable tblRolPago;
	private JPanel panelDetalleRolPago;
	private JButton btnSeleccionarTodos;
	private JButton btnEnviarRolMail;
	private JComponent gfsRubros2;
	private JScrollPane spTblRolPagoDetalleQyM;
	private JTable tblRolPagoDetalleQyM;
	private JScrollPane spTblRolPagoDetalleAyD;
	private JTable tblRolPagoDetalleAportesDecimos;
	private JScrollPane spTblRolPagoDetalleAnticipos;
	private JTable tblRolPagoDetalleAnticipos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblTipoRubro() {
		return lblTipoRubro;
	}

	public void setLblTipoRubro(JLabel lblTipoRubro) {
		this.lblTipoRubro = lblTipoRubro;
	}

	public JPanel getPanelTipoRubros() {
		return panelTipoRubros;
	}

	public void setPanelTipoRubros(JPanel panelTipoRubros) {
		this.panelTipoRubros = panelTipoRubros;
	}

	public JRadioButton getRbNormal() {
		return rbNormal;
	}

	public void setRbNormal(JRadioButton rbNormal) {
		this.rbNormal = rbNormal;
	}

	public JRadioButton getRbAnticipos() {
		return rbAnticipos;
	}

	public void setRbAnticipos(JRadioButton rbAnticipos) {
		this.rbAnticipos = rbAnticipos;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public void setLblTipoContrato(JLabel lblTipoContrato) {
		this.lblTipoContrato = lblTipoContrato;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
	}

	public void setCmbTipoContrato(JComboBox cmbTipoContrato) {
		this.cmbTipoContrato = cmbTipoContrato;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public void setBtnFiltrar(JButton btnFiltrar) {
		this.btnFiltrar = btnFiltrar;
	}

	public JSplitPane getSplitPnPagoRol() {
		return splitPnPagoRol;
	}

	public void setSplitPnPagoRol(JSplitPane splitPnPagoRol) {
		this.splitPnPagoRol = splitPnPagoRol;
	}

	public JPanel getPanelRolPago() {
		return panelRolPago;
	}

	public void setPanelRolPago(JPanel panelRolPago) {
		this.panelRolPago = panelRolPago;
	}

	public JComponent getGfsRubros() {
		return gfsRubros;
	}

	public void setGfsRubros(JComponent gfsRubros) {
		this.gfsRubros = gfsRubros;
	}

	public JScrollPane getSpTblRolPago() {
		return spTblRolPago;
	}

	public void setSpTblRolPago(JScrollPane spTblRolPago) {
		this.spTblRolPago = spTblRolPago;
	}

	public JTable getTblRolPago() {
		return tblRolPago;
	}

	public void setTblRolPago(JTable tblRolPago) {
		this.tblRolPago = tblRolPago;
	}

	public JPanel getPanelDetalleRolPago() {
		return panelDetalleRolPago;
	}

	public void setPanelDetalleRolPago(JPanel panelDetalleRolPago) {
		this.panelDetalleRolPago = panelDetalleRolPago;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public void setBtnSeleccionarTodos(JButton btnSeleccionarTodos) {
		this.btnSeleccionarTodos = btnSeleccionarTodos;
	}

	public JButton getBtnEnviarRolMail() {
		return btnEnviarRolMail;
	}

	public void setBtnEnviarRolMail(JButton btnEnviarRolMail) {
		this.btnEnviarRolMail = btnEnviarRolMail;
	}

	public JComponent getGfsRubros2() {
		return gfsRubros2;
	}

	public void setGfsRubros2(JComponent gfsRubros2) {
		this.gfsRubros2 = gfsRubros2;
	}

	public JScrollPane getSpTblRolPagoDetalleQyM() {
		return spTblRolPagoDetalleQyM;
	}

	public void setSpTblRolPagoDetalleQyM(JScrollPane spTblRolPagoDetalleQyM) {
		this.spTblRolPagoDetalleQyM = spTblRolPagoDetalleQyM;
	}

	public JTable getTblRolPagoDetalleQyM() {
		return tblRolPagoDetalleQyM;
	}

	public void setTblRolPagoDetalleQyM(JTable tblRolPagoDetalleQyM) {
		this.tblRolPagoDetalleQyM = tblRolPagoDetalleQyM;
	}

	public JScrollPane getSpTblRolPagoDetalleAyD() {
		return spTblRolPagoDetalleAyD;
	}

	public void setSpTblRolPagoDetalleAyD(JScrollPane spTblRolPagoDetalleAyD) {
		this.spTblRolPagoDetalleAyD = spTblRolPagoDetalleAyD;
	}

	public JTable getTblRolPagoDetalleAportesDecimos() {
		return tblRolPagoDetalleAportesDecimos;
	}

	public void setTblRolPagoDetalleAportesDecimos(
			JTable tblRolPagoDetalleAportesDecimos) {
		this.tblRolPagoDetalleAportesDecimos = tblRolPagoDetalleAportesDecimos;
	}

	public JScrollPane getSpTblRolPagoDetalleAnticipos() {
		return spTblRolPagoDetalleAnticipos;
	}

	public void setSpTblRolPagoDetalleAnticipos(
			JScrollPane spTblRolPagoDetalleAnticipos) {
		this.spTblRolPagoDetalleAnticipos = spTblRolPagoDetalleAnticipos;
	}

	public JTable getTblRolPagoDetalleAnticipos() {
		return tblRolPagoDetalleAnticipos;
	}

	public void setTblRolPagoDetalleAnticipos(JTable tblRolPagoDetalleAnticipos) {
		this.tblRolPagoDetalleAnticipos = tblRolPagoDetalleAnticipos;
	}
	
	
	
}
