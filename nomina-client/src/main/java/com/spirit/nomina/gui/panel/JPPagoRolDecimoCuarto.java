package com.spirit.nomina.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Mon Mar 24 11:04:16 COT 2014
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPPagoRolDecimoCuarto extends SpiritModelImpl {
	public JPPagoRolDecimoCuarto() {
		initComponents();
		setName("Pago Rol Decimo Cuarto");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblMesInicio = new JLabel();
		cmbMesInicio = new DateComboBox();
		lblMesFin = new JLabel();
		cmbMesFin = new DateComboBox();
		btnGenerar = new JButton();
		gfsDetallesRolPago = compFactory.createSeparator("Detalle Rol de Pago");
		btnSeleccionarTodos = new JButton();
		cmbTipoPagoTodos = new JComboBox();
		btnCuentaBancariaTodos = new JButton();
		cmbCuentaBancariaTodos = new JComboBox();
		btnTipoPagoTodos = new JButton();
		spTblRolPagoDetalleR14 = new JScrollPane();
		tblRolPagoDetalleR14 = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Autorizacion Pago de Rol");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
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
				new RowSpec(Sizes.DLUY9),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY3),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec("fill:min(default;250dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xy(5, 3));

		//---- lblMesInicio ----
		lblMesInicio.setText("Mes Inicio: ");
		add(lblMesInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMesInicio, cc.xy(5, 5));

		//---- lblMesFin ----
		lblMesFin.setText("Mes Fin: ");
		add(lblMesFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMesFin, cc.xywh(11, 5, 3, 1));

		//---- btnGenerar ----
		btnGenerar.setText("Generar");
		add(btnGenerar, cc.xy(17, 5));
		add(gfsDetallesRolPago, cc.xywh(3, 9, 21, 1));

		//---- btnSeleccionarTodos ----
		btnSeleccionarTodos.setText("Seleccionar Todo");
		add(btnSeleccionarTodos, cc.xy(3, 11));
		add(cmbTipoPagoTodos, cc.xywh(5, 11, 3, 1));

		//---- btnCuentaBancariaTodos ----
		btnCuentaBancariaTodos.setText("Aplicar a Todos");
		add(btnCuentaBancariaTodos, cc.xywh(9, 11, 3, 1));
		add(cmbCuentaBancariaTodos, cc.xywh(13, 11, 7, 1));

		//---- btnTipoPagoTodos ----
		btnTipoPagoTodos.setText("Aplicar a todos");
		add(btnTipoPagoTodos, cc.xy(21, 11));

		//======== spTblRolPagoDetalleR14 ========
		{

			//---- tblRolPagoDetalleR14 ----
			tblRolPagoDetalleR14.setModel(new DefaultTableModel(
				new Object[][] {
					{false, null, null, null, null, null},
				},
				new String[] {
					" ", "Apellidos", "Nombres", "Inicio Contrato", "D\u00edas Laborados", "D\u00e9cimo Cuarto"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Double.class, Double.class, Double.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, true
				};
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblRolPagoDetalleR14.setColumnSelectionAllowed(false);
			tblRolPagoDetalleR14.setCellSelectionEnabled(true);
			tblRolPagoDetalleR14.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			spTblRolPagoDetalleR14.setViewportView(tblRolPagoDetalleR14);
		}
		add(spTblRolPagoDetalleR14, cc.xywh(3, 15, 21, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblMesInicio;
	private DateComboBox cmbMesInicio;
	private JLabel lblMesFin;
	private DateComboBox cmbMesFin;
	private JButton btnGenerar;
	private JComponent gfsDetallesRolPago;
	private JButton btnSeleccionarTodos;
	private JComboBox cmbTipoPagoTodos;
	private JButton btnCuentaBancariaTodos;
	private JComboBox cmbCuentaBancariaTodos;
	private JButton btnTipoPagoTodos;
	private JScrollPane spTblRolPagoDetalleR14;
	private JTable tblRolPagoDetalleR14;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public DateComboBox getCmbMesInicio() {
		return cmbMesInicio;
	}

	public DateComboBox getCmbMesFin() {
		return cmbMesFin;
	}

	public JButton getBtnGenerar() {
		return btnGenerar;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JComboBox getCmbTipoPagoTodos() {
		return cmbTipoPagoTodos;
	}

	public JButton getBtnCuentaBancariaTodos() {
		return btnCuentaBancariaTodos;
	}

	public JComboBox getCmbCuentaBancariaTodos() {
		return cmbCuentaBancariaTodos;
	}

	public JButton getBtnTipoPagoTodos() {
		return btnTipoPagoTodos;
	}

	public JTable getTblRolPagoDetalleR14() {
		return tblRolPagoDetalleR14;
	}
}
