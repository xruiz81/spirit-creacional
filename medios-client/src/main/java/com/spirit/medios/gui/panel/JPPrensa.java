package com.spirit.medios.gui.panel;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPPrensa extends SpiritModelImpl {
	public JPPrensa() {
		initComponents();
		setName("Prensa");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpPrensa = new JideTabbedPane();
		spSeccion = new JScrollPane();
		panelSeccion = new JPanel();
		lblCodigoSeccion = new JLabel();
		txtCodigoSeccion = new JTextField();
		lblDiarioSeccion = new JLabel();
		cmbDiarioSeccion = new JComboBox();
		lblSeccion = new JLabel();
		txtSeccion = new JTextField();
		spTblSeccion = new JScrollPane();
		tblSeccion = new JTable();
		spUbicacion = new JScrollPane();
		panelUbicacion = new JPanel();
		lblCodigoUbicacion = new JLabel();
		txtCodigoUbicacion = new JTextField();
		lblDiarioUbicacion = new JLabel();
		cmbDiarioUbicacion = new JComboBox();
		lblUbicacion = new JLabel();
		txtUbicacion = new JTextField();
		spTblUbicacion = new JScrollPane();
		tblUbicacion = new JTable();
		spFormato = new JScrollPane();
		panelFormato = new JPanel();
		lblCodigoFormato = new JLabel();
		txtCodigoFormato = new JTextField();
		lblDiarioFormato = new JLabel();
		cmbDiarioFormato = new JComboBox();
		lblFormato = new JLabel();
		txtFormato = new JTextField();
		lblAnchoColumnas = new JLabel();
		txtAnchoColumnas = new JTextField();
		lblAltoModulos = new JLabel();
		txtAltoModulos = new JTextField();
		lblAnchoCm = new JLabel();
		txtAnchoCm = new JTextField();
		lblAltoCm = new JLabel();
		txtAltoCm = new JTextField();
		spTblFormato = new JScrollPane();
		tblFormato = new JTable();
		spInsertos = new JScrollPane();
		panelInsertos = new JPanel();
		lblCodigoInserto = new JLabel();
		txtCodigoInserto = new JTextField();
		lblDiarioInserto = new JLabel();
		cmbDiarioInserto = new JComboBox();
		lblPaginas = new JLabel();
		txtPaginas = new JTextField();
		lblTarifaInserto = new JLabel();
		txtTarifaInserto = new JTextField();
		spTblInserto = new JScrollPane();
		tblInserto = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== jtpPrensa ========
		{
			
			//======== spSeccion ========
			{
				
				//======== panelSeccion ========
				{
					panelSeccion.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(130)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					
					//---- lblCodigoSeccion ----
					lblCodigoSeccion.setText("C\u00f3digo:");
					panelSeccion.add(lblCodigoSeccion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelSeccion.add(txtCodigoSeccion, cc.xy(5, 3));
					
					//---- lblDiarioSeccion ----
					lblDiarioSeccion.setText("Diario:");
					panelSeccion.add(lblDiarioSeccion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelSeccion.add(cmbDiarioSeccion, cc.xywh(5, 5, 3, 1));
					
					//---- lblSeccion ----
					lblSeccion.setText("Secci\u00f3n:");
					panelSeccion.add(lblSeccion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelSeccion.add(txtSeccion, cc.xywh(5, 7, 3, 1));
					
					//======== spTblSeccion ========
					{
						
						//---- tblSeccion ----
						tblSeccion.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null},
							},
							new String[] {
								"C\u00f3digo", "Diario", "Secci\u00f3n"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblSeccion.setPreferredScrollableViewportSize(new Dimension(450, 150));
						spTblSeccion.setViewportView(tblSeccion);
					}
					panelSeccion.add(spTblSeccion, cc.xywh(3, 11, 7, 5));
				}
				spSeccion.setViewportView(panelSeccion);
			}
			jtpPrensa.addTab("Secci\u00f3n", spSeccion);
			
			
			//======== spUbicacion ========
			{
				
				//======== panelUbicacion ========
				{
					panelUbicacion.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(130)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					
					//---- lblCodigoUbicacion ----
					lblCodigoUbicacion.setText("C\u00f3digo:");
					panelUbicacion.add(lblCodigoUbicacion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelUbicacion.add(txtCodigoUbicacion, cc.xy(5, 3));
					
					//---- lblDiarioUbicacion ----
					lblDiarioUbicacion.setText("Diario:");
					panelUbicacion.add(lblDiarioUbicacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelUbicacion.add(cmbDiarioUbicacion, cc.xywh(5, 5, 3, 1));
					
					//---- lblUbicacion ----
					lblUbicacion.setText("Ubicaci\u00f3n:");
					panelUbicacion.add(lblUbicacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelUbicacion.add(txtUbicacion, cc.xywh(5, 7, 3, 1));
					
					//======== spTblUbicacion ========
					{
						
						//---- tblUbicacion ----
						tblUbicacion.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null},
							},
							new String[] {
								"C\u00f3digo", "Diario", "Secci\u00f3n"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblUbicacion.setPreferredScrollableViewportSize(new Dimension(450, 150));
						spTblUbicacion.setViewportView(tblUbicacion);
					}
					panelUbicacion.add(spTblUbicacion, cc.xywh(3, 11, 7, 5));
				}
				spUbicacion.setViewportView(panelUbicacion);
			}
			jtpPrensa.addTab("Ubicaci\u00f3n", spUbicacion);
			
			
			//======== spFormato ========
			{
				
				//======== panelFormato ========
				{
					panelFormato.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					
					//---- lblCodigoFormato ----
					lblCodigoFormato.setText("C\u00f3digo:");
					panelFormato.add(lblCodigoFormato, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelFormato.add(txtCodigoFormato, cc.xy(5, 3));
					
					//---- lblDiarioFormato ----
					lblDiarioFormato.setText("Diario:");
					panelFormato.add(lblDiarioFormato, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelFormato.add(cmbDiarioFormato, cc.xywh(5, 5, 9, 1));
					
					//---- lblFormato ----
					lblFormato.setText("Formato:");
					panelFormato.add(lblFormato, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelFormato.add(txtFormato, cc.xywh(5, 7, 9, 1));
					
					//---- lblAnchoColumnas ----
					lblAnchoColumnas.setText("Ancho (Columnas):");
					panelFormato.add(lblAnchoColumnas, cc.xy(3, 9));
					panelFormato.add(txtAnchoColumnas, cc.xy(5, 9));
					
					//---- lblAltoModulos ----
					lblAltoModulos.setText("Alto (Modulos):");
					panelFormato.add(lblAltoModulos, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelFormato.add(txtAltoModulos, cc.xy(11, 9));
					
					//---- lblAnchoCm ----
					lblAnchoCm.setText("Ancho (Cm.):");
					panelFormato.add(lblAnchoCm, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelFormato.add(txtAnchoCm, cc.xy(5, 11));
					
					//---- lblAltoCm ----
					lblAltoCm.setText("Alto (Cm.):");
					panelFormato.add(lblAltoCm, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelFormato.add(txtAltoCm, cc.xy(11, 11));
					
					//======== spTblFormato ========
					{
						
						//---- tblFormato ----
						tblFormato.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblFormato.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null},
							},
							new String[] {
								"Diario", "Formato", "Ancho (Col.)", "Alto (Mod.)", "Ancho (Cm.)", "Alto (Cm.)"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblFormato.setViewportView(tblFormato);
					}
					panelFormato.add(spTblFormato, cc.xywh(3, 15, 13, 5));
				}
				spFormato.setViewportView(panelFormato);
			}
			jtpPrensa.addTab("Formato", spFormato);
			
			
			//======== spInsertos ========
			{
				
				//======== panelInsertos ========
				{
					panelInsertos.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(130)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					
					//---- lblCodigoInserto ----
					lblCodigoInserto.setText("C\u00f3digo:");
					panelInsertos.add(lblCodigoInserto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelInsertos.add(txtCodigoInserto, cc.xy(5, 3));
					
					//---- lblDiarioInserto ----
					lblDiarioInserto.setText("Diario:");
					panelInsertos.add(lblDiarioInserto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelInsertos.add(cmbDiarioInserto, cc.xywh(5, 5, 3, 1));
					
					//---- lblPaginas ----
					lblPaginas.setText("Max. P\u00e1ginas(#):");
					panelInsertos.add(lblPaginas, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelInsertos.add(txtPaginas, cc.xy(5, 7));
					
					//---- lblTarifaInserto ----
					lblTarifaInserto.setText("Tarifa($):");
					panelInsertos.add(lblTarifaInserto, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelInsertos.add(txtTarifaInserto, cc.xy(5, 9));
					
					//======== spTblInserto ========
					{
						
						//---- tblInserto ----
						tblInserto.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblInserto.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null},
							},
							new String[] {
								"C\u00f3digo", "Diario", "P\u00e1ginas", "Tarifa"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblInserto.setViewportView(tblInserto);
					}
					panelInsertos.add(spTblInserto, cc.xywh(3, 13, 7, 5));
				}
				spInsertos.setViewportView(panelInsertos);
			}
			jtpPrensa.addTab("Inserto/Suplemento", spInsertos);
			
		}
		add(jtpPrensa, cc.xywh(3, 3, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
	}

	//JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpPrensa;
	private JScrollPane spSeccion;
	private JPanel panelSeccion;
	private JLabel lblCodigoSeccion;
	private JTextField txtCodigoSeccion;
	private JLabel lblDiarioSeccion;
	private JComboBox cmbDiarioSeccion;
	private JLabel lblSeccion;
	private JTextField txtSeccion;
	private JScrollPane spTblSeccion;
	private JTable tblSeccion;
	private JScrollPane spUbicacion;
	private JPanel panelUbicacion;
	private JLabel lblCodigoUbicacion;
	private JTextField txtCodigoUbicacion;
	private JLabel lblDiarioUbicacion;
	private JComboBox cmbDiarioUbicacion;
	private JLabel lblUbicacion;
	private JTextField txtUbicacion;
	private JScrollPane spTblUbicacion;
	private JTable tblUbicacion;
	private JScrollPane spFormato;
	private JPanel panelFormato;
	private JLabel lblCodigoFormato;
	private JTextField txtCodigoFormato;
	private JLabel lblDiarioFormato;
	private JComboBox cmbDiarioFormato;
	private JLabel lblFormato;
	private JTextField txtFormato;
	private JLabel lblAnchoColumnas;
	private JTextField txtAnchoColumnas;
	private JLabel lblAltoModulos;
	private JTextField txtAltoModulos;
	private JLabel lblAnchoCm;
	private JTextField txtAnchoCm;
	private JLabel lblAltoCm;
	private JTextField txtAltoCm;
	private JScrollPane spTblFormato;
	private JTable tblFormato;
	private JScrollPane spInsertos;
	private JPanel panelInsertos;
	private JLabel lblCodigoInserto;
	private JTextField txtCodigoInserto;
	private JLabel lblDiarioInserto;
	private JComboBox cmbDiarioInserto;
	private JLabel lblPaginas;
	private JTextField txtPaginas;
	private JLabel lblTarifaInserto;
	private JTextField txtTarifaInserto;
	private JScrollPane spTblInserto;
	private JTable tblInserto;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbDiarioFormato() {
		return cmbDiarioFormato;
	}

	public void setCmbDiarioFormato(JComboBox cmbDiarioFormato) {
		this.cmbDiarioFormato = cmbDiarioFormato;
	}

	public JComboBox getCmbDiarioInserto() {
		return cmbDiarioInserto;
	}

	public void setCmbDiarioInserto(JComboBox cmbDiarioInserto) {
		this.cmbDiarioInserto = cmbDiarioInserto;
	}

	public JComboBox getCmbDiarioSeccion() {
		return cmbDiarioSeccion;
	}

	public void setCmbDiarioSeccion(JComboBox cmbDiarioSeccion) {
		this.cmbDiarioSeccion = cmbDiarioSeccion;
	}

	public JComboBox getCmbDiarioUbicacion() {
		return cmbDiarioUbicacion;
	}

	public void setCmbDiarioUbicacion(JComboBox cmbDiarioUbicacion) {
		this.cmbDiarioUbicacion = cmbDiarioUbicacion;
	}

	public JideTabbedPane getJtpPrensa() {
		return jtpPrensa;
	}

	public void setJtpPrensa(JideTabbedPane jtpPrensa) {
		this.jtpPrensa = jtpPrensa;
	}

	public JPanel getPanelFormato() {
		return panelFormato;
	}

	public void setPanelFormato(JPanel panelFormato) {
		this.panelFormato = panelFormato;
	}

	public JPanel getPanelInsertos() {
		return panelInsertos;
	}

	public void setPanelInsertos(JPanel panelInsertos) {
		this.panelInsertos = panelInsertos;
	}

	public JPanel getPanelSeccion() {
		return panelSeccion;
	}

	public void setPanelSeccion(JPanel panelSeccion) {
		this.panelSeccion = panelSeccion;
	}

	public JPanel getPanelUbicacion() {
		return panelUbicacion;
	}

	public void setPanelUbicacion(JPanel panelUbicacion) {
		this.panelUbicacion = panelUbicacion;
	}

	public JScrollPane getSpFormato() {
		return spFormato;
	}

	public void setSpFormato(JScrollPane spFormato) {
		this.spFormato = spFormato;
	}

	public JScrollPane getSpInsertos() {
		return spInsertos;
	}

	public void setSpInsertos(JScrollPane spInsertos) {
		this.spInsertos = spInsertos;
	}

	public JScrollPane getSpSeccion() {
		return spSeccion;
	}

	public void setSpSeccion(JScrollPane spSeccion) {
		this.spSeccion = spSeccion;
	}

	public JScrollPane getSpTblFormato() {
		return spTblFormato;
	}

	public void setSpTblFormato(JScrollPane spTblFormato) {
		this.spTblFormato = spTblFormato;
	}

	public JScrollPane getSpTblInserto() {
		return spTblInserto;
	}

	public void setSpTblInserto(JScrollPane spTblInserto) {
		this.spTblInserto = spTblInserto;
	}

	public JScrollPane getSpTblSeccion() {
		return spTblSeccion;
	}

	public void setSpTblSeccion(JScrollPane spTblSeccion) {
		this.spTblSeccion = spTblSeccion;
	}

	public JScrollPane getSpTblUbicacion() {
		return spTblUbicacion;
	}

	public void setSpTblUbicacion(JScrollPane spTblUbicacion) {
		this.spTblUbicacion = spTblUbicacion;
	}

	public JScrollPane getSpUbicacion() {
		return spUbicacion;
	}

	public void setSpUbicacion(JScrollPane spUbicacion) {
		this.spUbicacion = spUbicacion;
	}

	public JTable getTblFormato() {
		return tblFormato;
	}

	public void setTblFormato(JTable tblFormato) {
		this.tblFormato = tblFormato;
	}

	public JTable getTblInserto() {
		return tblInserto;
	}

	public void setTblInserto(JTable tblInserto) {
		this.tblInserto = tblInserto;
	}

	public JTable getTblSeccion() {
		return tblSeccion;
	}

	public void setTblSeccion(JTable tblSeccion) {
		this.tblSeccion = tblSeccion;
	}

	public JTable getTblUbicacion() {
		return tblUbicacion;
	}

	public void setTblUbicacion(JTable tblUbicacion) {
		this.tblUbicacion = tblUbicacion;
	}

	public JTextField getTxtAltoCm() {
		return txtAltoCm;
	}

	public void setTxtAltoCm(JTextField txtAltoCm) {
		this.txtAltoCm = txtAltoCm;
	}

	public JTextField getTxtAltoModulos() {
		return txtAltoModulos;
	}

	public void setTxtAltoModulos(JTextField txtAltoModulos) {
		this.txtAltoModulos = txtAltoModulos;
	}

	public JTextField getTxtAnchoCm() {
		return txtAnchoCm;
	}

	public void setTxtAnchoCm(JTextField txtAnchoCm) {
		this.txtAnchoCm = txtAnchoCm;
	}

	public JTextField getTxtAnchoColumnas() {
		return txtAnchoColumnas;
	}

	public void setTxtAnchoColumnas(JTextField txtAnchoColumnas) {
		this.txtAnchoColumnas = txtAnchoColumnas;
	}

	public JTextField getTxtCodigoFormato() {
		return txtCodigoFormato;
	}

	public void setTxtCodigoFormato(JTextField txtCodigoFormato) {
		this.txtCodigoFormato = txtCodigoFormato;
	}

	public JTextField getTxtCodigoInserto() {
		return txtCodigoInserto;
	}

	public void setTxtCodigoInserto(JTextField txtCodigoInserto) {
		this.txtCodigoInserto = txtCodigoInserto;
	}

	public JTextField getTxtCodigoSeccion() {
		return txtCodigoSeccion;
	}

	public void setTxtCodigoSeccion(JTextField txtCodigoSeccion) {
		this.txtCodigoSeccion = txtCodigoSeccion;
	}

	public JTextField getTxtCodigoUbicacion() {
		return txtCodigoUbicacion;
	}

	public void setTxtCodigoUbicacion(JTextField txtCodigoUbicacion) {
		this.txtCodigoUbicacion = txtCodigoUbicacion;
	}

	public JTextField getTxtFormato() {
		return txtFormato;
	}

	public void setTxtFormato(JTextField txtFormato) {
		this.txtFormato = txtFormato;
	}

	public JTextField getTxtPaginas() {
		return txtPaginas;
	}

	public void setTxtPaginas(JTextField txtPaginas) {
		this.txtPaginas = txtPaginas;
	}

	public JTextField getTxtSeccion() {
		return txtSeccion;
	}

	public void setTxtSeccion(JTextField txtSeccion) {
		this.txtSeccion = txtSeccion;
	}

	public JTextField getTxtTarifaInserto() {
		return txtTarifaInserto;
	}

	public void setTxtTarifaInserto(JTextField txtTarifaInserto) {
		this.txtTarifaInserto = txtTarifaInserto;
	}

	public JTextField getTxtUbicacion() {
		return txtUbicacion;
	}

	public void setTxtUbicacion(JTextField txtUbicacion) {
		this.txtUbicacion = txtUbicacion;
	}
}
