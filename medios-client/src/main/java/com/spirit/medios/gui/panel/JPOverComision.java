package com.spirit.medios.gui.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Thu Oct 30 16:07:30 COT 2014
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPOverComision extends SpiritModelImpl {
	public JPOverComision() {
		initComponents();
		setName("Over Comision");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		cbVerReporteProveedor = new JCheckBox();
		lblAnio = new JLabel();
		cmbAnio = new DateComboBox();
		cbVerReporteAnio = new JCheckBox();
		lblInversionDe = new JLabel();
		txtInversionDe = new JTextField();
		lblInversionA = new JLabel();
		txtInversionA = new JTextField();
		lblPorcentajeOver = new JLabel();
		txtPorcentajeOver = new JTextField();
		lblPorcentaje = new JLabel();
		lblObjetivo = new JLabel();
		cmbObjetivo = new JComboBox();
		panelBotonesOver = new JPanel();
		btnAgregarOver = new JButton();
		btnActualizarOver = new JButton();
		btnEliminarOver = new JButton();
		spTblOver = new JScrollPane();
		tblOver = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
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
				new RowSpec(Sizes.DLUY8),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedor ----
		txtProveedor.setEditable(true);
		add(txtProveedor, cc.xywh(5, 3, 19, 1));
		add(btnProveedor, cc.xywh(25, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- cbVerReporteProveedor ----
		cbVerReporteProveedor.setText("Ver en Reporte");
		add(cbVerReporteProveedor, cc.xy(27, 3));

		//---- lblAnio ----
		lblAnio.setText("A\u00f1o:");
		add(lblAnio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbAnio, cc.xy(5, 5));

		//---- cbVerReporteAnio ----
		cbVerReporteAnio.setText("Ver en Reporte");
		add(cbVerReporteAnio, cc.xywh(7, 5, 5, 1));

		//---- lblInversionDe ----
		lblInversionDe.setText("Inversi\u00f3n De:");
		add(lblInversionDe, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtInversionDe ----
		txtInversionDe.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtInversionDe, cc.xy(5, 7));

		//---- lblInversionA ----
		lblInversionA.setText("A:");
		add(lblInversionA, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtInversionA ----
		txtInversionA.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtInversionA, cc.xy(11, 7));

		//---- lblPorcentajeOver ----
		lblPorcentajeOver.setText("% Over:");
		add(lblPorcentajeOver, cc.xywh(15, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtPorcentajeOver ----
		txtPorcentajeOver.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtPorcentajeOver, cc.xy(17, 7));

		//---- lblPorcentaje ----
		lblPorcentaje.setText("%");
		add(lblPorcentaje, cc.xy(19, 7));

		//---- lblObjetivo ----
		lblObjetivo.setText("Objetivo:");
		add(lblObjetivo, cc.xy(23, 7));

		//---- cmbObjetivo ----
		cmbObjetivo.setModel(new DefaultComboBoxModel(new String[] {
			"NO",
			"SI"
		}));
		add(cmbObjetivo, cc.xy(25, 7));

		//======== panelBotonesOver ========
		{
			panelBotonesOver.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnAgregarOver ----
			btnAgregarOver.setText("A");
			panelBotonesOver.add(btnAgregarOver, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- btnActualizarOver ----
			btnActualizarOver.setText("U");
			panelBotonesOver.add(btnActualizarOver, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- btnEliminarOver ----
			btnEliminarOver.setText("E");
			panelBotonesOver.add(btnEliminarOver, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		}
		add(panelBotonesOver, cc.xywh(3, 11, 5, 1));

		//======== spTblOver ========
		{
			spTblOver.setPreferredSize(new Dimension(452, 100));

			//---- tblOver ----
			tblOver.setModel(new DefaultTableModel(
				new Object[][] {
					{null, "", null, null, null, null},
				},
				new String[] {
					"Proveedor", "A\u00f1o", "De:", "A:", "% Over", "Objetivo"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblOver.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblOver.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblOver.setAutoCreateColumnsFromModel(true);
			spTblOver.setViewportView(tblOver);
		}
		add(spTblOver, cc.xywh(3, 13, 27, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JCheckBox cbVerReporteProveedor;
	private JLabel lblAnio;
	private DateComboBox cmbAnio;
	private JCheckBox cbVerReporteAnio;
	private JLabel lblInversionDe;
	private JTextField txtInversionDe;
	private JLabel lblInversionA;
	private JTextField txtInversionA;
	private JLabel lblPorcentajeOver;
	private JTextField txtPorcentajeOver;
	private JLabel lblPorcentaje;
	private JLabel lblObjetivo;
	private JComboBox cmbObjetivo;
	private JPanel panelBotonesOver;
	private JButton btnAgregarOver;
	private JButton btnActualizarOver;
	private JButton btnEliminarOver;
	private JScrollPane spTblOver;
	private JTable tblOver;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JCheckBox getCbVerReporteProveedor() {
		return cbVerReporteProveedor;
	}

	public JCheckBox getCbVerReporteAnio() {
		return cbVerReporteAnio;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public DateComboBox getCmbAnio() {
		return cmbAnio;
	}

	public JLabel getLblInversionDe() {
		return lblInversionDe;
	}

	public JTextField getTxtInversionDe() {
		return txtInversionDe;
	}

	public JLabel getLblInversionA() {
		return lblInversionA;
	}

	public JTextField getTxtInversionA() {
		return txtInversionA;
	}

	public JLabel getLblPorcentajeOver() {
		return lblPorcentajeOver;
	}

	public JTextField getTxtPorcentajeOver() {
		return txtPorcentajeOver;
	}

	public JLabel getLblPorcentaje() {
		return lblPorcentaje;
	}

	public JLabel getLblObjetivo() {
		return lblObjetivo;
	}

	public JComboBox getCmbObjetivo() {
		return cmbObjetivo;
	}

	public JPanel getPanelBotonesOver() {
		return panelBotonesOver;
	}

	public JButton getBtnAgregarOver() {
		return btnAgregarOver;
	}

	public JButton getBtnActualizarOver() {
		return btnActualizarOver;
	}

	public JButton getBtnEliminarOver() {
		return btnEliminarOver;
	}

	public JScrollPane getSpTblOver() {
		return spTblOver;
	}

	public JTable getTblOver() {
		return tblOver;
	}
}
