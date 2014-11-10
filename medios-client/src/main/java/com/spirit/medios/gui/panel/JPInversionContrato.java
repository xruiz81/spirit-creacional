package com.spirit.medios.gui.panel;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Thu Oct 30 16:07:19 COT 2014
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPInversionContrato extends SpiritModelImpl {
	public JPInversionContrato() {
		initComponents();
		setName("Inversion por Contrato");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		btnCliente = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblInversionContrato = new JLabel();
		txtInversionContrato = new JTextField();
		lblAplicaDevolucion = new JLabel();
		cmbAplicaDevolucion = new JComboBox();
		txtAplicaDevolucion = new JTextField();
		lblPorcentajeAplicaDevolucion = new JLabel();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		panelBotonesInversionContrato = new JPanel();
		btnAgregarInversionContrato = new JButton();
		btnActualizarInversionContrato = new JButton();
		btnEliminarInversionContrato = new JButton();
		spTblInversionContrato = new JScrollPane();
		tblInversionContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(36)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(67)),
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
				new RowSpec(Sizes.dluY(12))
			}));
		add(btnCliente, cc.xywh(19, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(true);
		add(txtCliente, cc.xywh(5, 3, 13, 1));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedor ----
		txtProveedor.setEditable(true);
		add(txtProveedor, cc.xywh(5, 5, 13, 1));
		add(btnProveedor, cc.xywh(19, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicial:");
		add(lblFechaInicio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(5, 7, 5, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Final:");
		add(lblFechaFin, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(15, 7, 3, 1));

		//---- lblInversionContrato ----
		lblInversionContrato.setText("Inversi\u00f3n por Contrato:");
		add(lblInversionContrato, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtInversionContrato ----
		txtInversionContrato.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtInversionContrato, cc.xywh(5, 9, 5, 1));

		//---- lblAplicaDevolucion ----
		lblAplicaDevolucion.setText("Aplica Devoluci\u00f3n:");
		add(lblAplicaDevolucion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbAplicaDevolucion ----
		cmbAplicaDevolucion.setModel(new DefaultComboBoxModel(new String[] {
			"NO",
			"SI"
		}));
		add(cmbAplicaDevolucion, cc.xy(5, 11));

		//---- txtAplicaDevolucion ----
		txtAplicaDevolucion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtAplicaDevolucion, cc.xy(7, 11));

		//---- lblPorcentajeAplicaDevolucion ----
		lblPorcentajeAplicaDevolucion.setText("%");
		add(lblPorcentajeAplicaDevolucion, cc.xy(9, 11));

		//---- lblObservacion ----
		lblObservacion.setText("Observaci\u00f3n:");
		add(lblObservacion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtObservacion, cc.xywh(5, 13, 15, 1));

		//======== panelBotonesInversionContrato ========
		{
			panelBotonesInversionContrato.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnAgregarInversionContrato ----
			btnAgregarInversionContrato.setText("A");
			panelBotonesInversionContrato.add(btnAgregarInversionContrato, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- btnActualizarInversionContrato ----
			btnActualizarInversionContrato.setText("U");
			panelBotonesInversionContrato.add(btnActualizarInversionContrato, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- btnEliminarInversionContrato ----
			btnEliminarInversionContrato.setText("E");
			panelBotonesInversionContrato.add(btnEliminarInversionContrato, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		}
		add(panelBotonesInversionContrato, cc.xywh(3, 17, 9, 1));

		//======== spTblInversionContrato ========
		{
			spTblInversionContrato.setPreferredSize(new Dimension(452, 100));

			//---- tblInversionContrato ----
			tblInversionContrato.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, "", null, null, null},
				},
				new String[] {
					"Cliente", "Proveedor", "Fecha Inicial", "Fecha Final", "Inv. Contrato", "Aplica Devoluci\u00f3n", "Observaci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblInversionContrato.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblInversionContrato.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblInversionContrato.setAutoCreateColumnsFromModel(true);
			spTblInversionContrato.setViewportView(tblInversionContrato);
		}
		add(spTblInversionContrato, cc.xywh(3, 19, 19, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JButton btnCliente;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblInversionContrato;
	private JTextField txtInversionContrato;
	private JLabel lblAplicaDevolucion;
	private JComboBox cmbAplicaDevolucion;
	private JTextField txtAplicaDevolucion;
	private JLabel lblPorcentajeAplicaDevolucion;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JPanel panelBotonesInversionContrato;
	private JButton btnAgregarInversionContrato;
	private JButton btnActualizarInversionContrato;
	private JButton btnEliminarInversionContrato;
	private JScrollPane spTblInversionContrato;
	private JTable tblInversionContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JLabel getLblInversionContrato() {
		return lblInversionContrato;
	}

	public JTextField getTxtInversionContrato() {
		return txtInversionContrato;
	}

	public JLabel getLblAplicaDevolucion() {
		return lblAplicaDevolucion;
	}

	public JComboBox getCmbAplicaDevolucion() {
		return cmbAplicaDevolucion;
	}

	public JTextField getTxtAplicaDevolucion() {
		return txtAplicaDevolucion;
	}

	public JLabel getLblPorcentajeAplicaDevolucion() {
		return lblPorcentajeAplicaDevolucion;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JPanel getPanelBotonesInversionContrato() {
		return panelBotonesInversionContrato;
	}

	public JButton getBtnAgregarInversionContrato() {
		return btnAgregarInversionContrato;
	}

	public JButton getBtnActualizarInversionContrato() {
		return btnActualizarInversionContrato;
	}

	public JButton getBtnEliminarInversionContrato() {
		return btnEliminarInversionContrato;
	}

	public JScrollPane getSpTblInversionContrato() {
		return spTblInversionContrato;
	}

	public JTable getTblInversionContrato() {
		return tblInversionContrato;
	}
}
