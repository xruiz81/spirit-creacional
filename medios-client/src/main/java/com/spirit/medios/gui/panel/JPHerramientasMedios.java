package com.spirit.medios.gui.panel;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Oct 15 10:46:54 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPHerramientasMedios extends SpiritModelImpl {
	public JPHerramientasMedios() {
		initComponents();
		setName("Herramientas de Medios");
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
		lblFrecuencia = new JLabel();
		cmbFrecuencia = new JComboBox();
		panelBotonesMarca = new JPanel();
		btnAgregar = new JButton();
		btnActualizar = new JButton();
		btnEliminar = new JButton();
		spTblHerramientas = new JScrollPane();
		tblHerramientas = new JTable();
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
				new ColumnSpec(Sizes.dluX(180)),
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
		add(btnCliente, cc.xywh(9, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(true);
		add(txtCliente, cc.xywh(5, 3, 3, 1));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedor ----
		txtProveedor.setEditable(true);
		add(txtProveedor, cc.xywh(5, 5, 3, 1));
		add(btnProveedor, cc.xywh(9, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblFrecuencia ----
		lblFrecuencia.setText("Frecuencia:");
		add(lblFrecuencia, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbFrecuencia ----
		cmbFrecuencia.setModel(new DefaultComboBoxModel(new String[] {
			"DIARIO",
			"SEMANAL",
			"QUINCENAL",
			"MENSUAL",
			"BIMENSUAL",
			"TRIMESTRAL",
			"SEMESTRAL",
			"ANUAL"
		}));
		add(cmbFrecuencia, cc.xy(5, 7));

		//======== panelBotonesMarca ========
		{
			panelBotonesMarca.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnAgregar ----
			btnAgregar.setText("A");
			panelBotonesMarca.add(btnAgregar, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- btnActualizar ----
			btnActualizar.setText("U");
			panelBotonesMarca.add(btnActualizar, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

			//---- btnEliminar ----
			btnEliminar.setText("E");
			panelBotonesMarca.add(btnEliminar, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		}
		add(panelBotonesMarca, cc.xywh(3, 11, 4, 1));

		//======== spTblHerramientas ========
		{
			spTblHerramientas.setPreferredSize(new Dimension(452, 100));

			//---- tblHerramientas ----
			tblHerramientas.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, ""},
				},
				new String[] {
					"Cliente", "Proveedor", "Frecuencia"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblHerramientas.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblHerramientas.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblHerramientas.setAutoCreateColumnsFromModel(true);
			spTblHerramientas.setViewportView(tblHerramientas);
		}
		add(spTblHerramientas, cc.xywh(3, 13, 9, 5));
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
	private JLabel lblFrecuencia;
	private JComboBox cmbFrecuencia;
	private JPanel panelBotonesMarca;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JScrollPane spTblHerramientas;
	private JTable tblHerramientas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public JComboBox getCmbFrecuencia() {
		return cmbFrecuencia;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JScrollPane getSpTblHerramientas() {
		return spTblHerramientas;
	}

	public JTable getTblHerramientas() {
		return tblHerramientas;
	}
}
