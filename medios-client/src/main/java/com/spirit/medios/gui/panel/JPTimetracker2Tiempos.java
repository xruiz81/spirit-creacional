package com.spirit.medios.gui.panel;

import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Jan 29 11:33:26 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author xruiz
 */
public abstract class JPTimetracker2Tiempos extends SpiritModelImpl {
	public JPTimetracker2Tiempos() {
		initComponents();
		setName("Timetracker2 Tiempos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblEmpleadoTiempo = new JLabel();
		txtEmpleadoTiempo = new JTextField();
		btnEmpleadoTiempo = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		lblTiempo = new JLabel();
		txtTiempo = new JTextField();
		lblTiempoPorcentaje = new JLabel();
		spTblTiempos = new JScrollPane();
		tblTiempos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(200)),
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

		//---- lblEmpleadoTiempo ----
		lblEmpleadoTiempo.setText("Empleado:");
		add(lblEmpleadoTiempo, cc.xy(3, 3));

		//---- txtEmpleadoTiempo ----
		txtEmpleadoTiempo.setEditable(false);
		add(txtEmpleadoTiempo, cc.xywh(5, 3, 3, 1));
		add(btnEmpleadoTiempo, cc.xywh(9, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 5, 3, 1));
		add(btnCliente, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblTiempo ----
		lblTiempo.setText("Tiempo:");
		add(lblTiempo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		txtTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtTiempo, cc.xy(5, 7));

		//---- lblTiempoPorcentaje ----
		lblTiempoPorcentaje.setText("[%]");
		add(lblTiempoPorcentaje, cc.xywh(7, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== spTblTiempos ========
		{

			//---- tblTiempos ----
			tblTiempos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"EMPLEADO", "CLIENTE", "TIEMPO"
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
			spTblTiempos.setViewportView(tblTiempos);
		}
		add(spTblTiempos, cc.xywh(3, 11, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblEmpleadoTiempo;
	private JTextField txtEmpleadoTiempo;
	private JButton btnEmpleadoTiempo;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JLabel lblTiempo;
	private JTextField txtTiempo;
	private JLabel lblTiempoPorcentaje;
	private JScrollPane spTblTiempos;
	private JTable tblTiempos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtEmpleadoTiempo() {
		return txtEmpleadoTiempo;
	}

	public JButton getBtnEmpleadoTiempo() {
		return btnEmpleadoTiempo;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JTextField getTxtTiempo() {
		return txtTiempo;
	}

	public JScrollPane getSpTblTiempos() {
		return spTblTiempos;
	}

	public JTable getTblTiempos() {
		return tblTiempos;
	}
}
