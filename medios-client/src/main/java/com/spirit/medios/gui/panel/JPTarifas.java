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
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPTarifas extends SpiritModelImpl {
	public JPTarifas() {
		initComponents();
		setName("Tarifas");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spPanelTarifas = new JScrollPane();
		panelTarifas = new JPanel();
		lblCodigoTarifa = new JLabel();
		txtCodigoTarifa = new JTextField();
		lblDiarioTarifa = new JLabel();
		cmbDiarioTarifa = new JComboBox();
		lblColor = new JLabel();
		cmbColor = new JComboBox();
		lblSeccionTarifa = new JLabel();
		cmbSeccionTarifa = new JComboBox();
		lblDia = new JLabel();
		cmbDia = new JComboBox();
		lblUbicacionTarifa = new JLabel();
		cmbUbicacionTarifa = new JComboBox();
		lblTCalculada = new JLabel();
		cmbTCalculada = new JComboBox();
		lblFormatoTarifa = new JLabel();
		lblTarifa = new JLabel();
		cmbFormatoTarifa = new JComboBox();
		lblOperacion = new JLabel();
		cmbOperacion = new JComboBox();
		txtTarifa = new JTextField();
		txtRecargo = new JTextField();
		lblRecargo = new JLabel();
		spTblTarifa = new JScrollPane();
		tblTarifa = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== spPanelTarifas ========
		{
			
			//======== panelTarifas ========
			{
				panelTarifas.setLayout(new FormLayout(
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
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(40)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(50), FormSpec.DEFAULT_GROW),
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
				
				//---- lblCodigoTarifa ----
				lblCodigoTarifa.setText("C\u00f3digo:");
				panelTarifas.add(lblCodigoTarifa, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(txtCodigoTarifa, cc.xy(5, 3));
				
				//---- lblDiarioTarifa ----
				lblDiarioTarifa.setText("Diario:");
				panelTarifas.add(lblDiarioTarifa, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbDiarioTarifa, cc.xywh(5, 5, 9, 1));
				
				//---- lblColor ----
				lblColor.setText("Color:");
				panelTarifas.add(lblColor, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbColor, cc.xywh(19, 5, 3, 1));
				
				//---- lblSeccionTarifa ----
				lblSeccionTarifa.setText("Secci\u00f3n:");
				panelTarifas.add(lblSeccionTarifa, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbSeccionTarifa, cc.xywh(5, 7, 9, 1));
				
				//---- lblDia ----
				lblDia.setText("D\u00eda:");
				panelTarifas.add(lblDia, cc.xywh(17, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbDia, cc.xywh(19, 7, 3, 1));
				
				//---- lblUbicacionTarifa ----
				lblUbicacionTarifa.setText("Ubicaci\u00f3n:");
				panelTarifas.add(lblUbicacionTarifa, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbUbicacionTarifa, cc.xywh(5, 9, 9, 1));
				
				//---- lblTCalculada ----
				lblTCalculada.setText("T. Calculada:");
				panelTarifas.add(lblTCalculada, cc.xywh(17, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbTCalculada, cc.xy(19, 9));
				
				//---- lblFormatoTarifa ----
				lblFormatoTarifa.setText("Formato:");
				panelTarifas.add(lblFormatoTarifa, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblTarifa ----
				lblTarifa.setText("Tarifa($):");
				panelTarifas.add(lblTarifa, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbFormatoTarifa, cc.xywh(5, 11, 9, 1));
				
				//---- lblOperacion ----
				lblOperacion.setText("Operaci\u00f3n:");
				panelTarifas.add(lblOperacion, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelTarifas.add(cmbOperacion, cc.xywh(19, 11, 5, 1));
				panelTarifas.add(txtTarifa, cc.xy(5, 13));
				panelTarifas.add(txtRecargo, cc.xy(11, 13));
				
				//---- lblRecargo ----
				lblRecargo.setText("Recargo(%):");
				panelTarifas.add(lblRecargo, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//======== spTblTarifa ========
				{
					
					//---- tblTarifa ----
					tblTarifa.setPreferredScrollableViewportSize(new Dimension(450, 150));
					tblTarifa.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null, null},
						},
						new String[] {
							"Diario", "Secci\u00f3n", "Ubicaci\u00f3n", "Formato", "Color", "D\u00eda", "Tarifa"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblTarifa.setViewportView(tblTarifa);
				}
				panelTarifas.add(spTblTarifa, cc.xywh(3, 17, 23, 5));
			}
			spPanelTarifas.setViewportView(panelTarifas);
		}
		add(spPanelTarifas, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	//JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spPanelTarifas;
	private JPanel panelTarifas;
	private JLabel lblCodigoTarifa;
	private JTextField txtCodigoTarifa;
	private JLabel lblDiarioTarifa;
	private JComboBox cmbDiarioTarifa;
	private JLabel lblColor;
	private JComboBox cmbColor;
	private JLabel lblSeccionTarifa;
	private JComboBox cmbSeccionTarifa;
	private JLabel lblDia;
	private JComboBox cmbDia;
	private JLabel lblUbicacionTarifa;
	private JComboBox cmbUbicacionTarifa;
	private JLabel lblTCalculada;
	private JComboBox cmbTCalculada;
	private JLabel lblFormatoTarifa;
	private JLabel lblTarifa;
	private JComboBox cmbFormatoTarifa;
	private JLabel lblOperacion;
	private JComboBox cmbOperacion;
	private JTextField txtTarifa;
	private JTextField txtRecargo;
	private JLabel lblRecargo;
	private JScrollPane spTblTarifa;
	private JTable tblTarifa;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbColor() {
		return cmbColor;
	}

	public void setCmbColor(JComboBox cmbColor) {
		this.cmbColor = cmbColor;
	}

	public JComboBox getCmbDia() {
		return cmbDia;
	}

	public void setCmbDia(JComboBox cmbDia) {
		this.cmbDia = cmbDia;
	}

	public JComboBox getCmbDiarioTarifa() {
		return cmbDiarioTarifa;
	}

	public void setCmbDiarioTarifa(JComboBox cmbDiarioTarifa) {
		this.cmbDiarioTarifa = cmbDiarioTarifa;
	}

	public JComboBox getCmbFormatoTarifa() {
		return cmbFormatoTarifa;
	}

	public void setCmbFormatoTarifa(JComboBox cmbFormatoTarifa) {
		this.cmbFormatoTarifa = cmbFormatoTarifa;
	}

	public JComboBox getCmbSeccionTarifa() {
		return cmbSeccionTarifa;
	}

	public void setCmbSeccionTarifa(JComboBox cmbSeccionTarifa) {
		this.cmbSeccionTarifa = cmbSeccionTarifa;
	}

	public JComboBox getCmbTCalculada() {
		return cmbTCalculada;
	}

	public void setCmbTCalculada(JComboBox cmbTCalculada) {
		this.cmbTCalculada = cmbTCalculada;
	}

	public JComboBox getCmbUbicacionTarifa() {
		return cmbUbicacionTarifa;
	}

	public void setCmbUbicacionTarifa(JComboBox cmbUbicacionTarifa) {
		this.cmbUbicacionTarifa = cmbUbicacionTarifa;
	}

	public JPanel getPanelTarifas() {
		return panelTarifas;
	}

	public void setPanelTarifas(JPanel panelTarifas) {
		this.panelTarifas = panelTarifas;
	}

	public JScrollPane getSpPanelTarifas() {
		return spPanelTarifas;
	}

	public void setSpPanelTarifas(JScrollPane spPanelTarifas) {
		this.spPanelTarifas = spPanelTarifas;
	}

	public JScrollPane getSpTblTarifa() {
		return spTblTarifa;
	}

	public void setSpTblTarifa(JScrollPane spTblTarifa) {
		this.spTblTarifa = spTblTarifa;
	}

	public JTable getTblTarifa() {
		return tblTarifa;
	}

	public void setTblTarifa(JTable tblTarifa) {
		this.tblTarifa = tblTarifa;
	}

	public JTextField getTxtCodigoTarifa() {
		return txtCodigoTarifa;
	}

	public void setTxtCodigoTarifa(JTextField txtCodigoTarifa) {
		this.txtCodigoTarifa = txtCodigoTarifa;
	}

	public JTextField getTxtRecargo() {
		return txtRecargo;
	}

	public void setTxtRecargo(JTextField txtRecargo) {
		this.txtRecargo = txtRecargo;
	}

	public JTextField getTxtTarifa() {
		return txtTarifa;
	}

	public void setTxtTarifa(JTextField txtTarifa) {
		this.txtTarifa = txtTarifa;
	}

	public JComboBox getCmbOperacion() {
		return cmbOperacion;
	}

	public void setCmbOperacion(JComboBox cmbOperacion) {
		this.cmbOperacion = cmbOperacion;
	}
}
