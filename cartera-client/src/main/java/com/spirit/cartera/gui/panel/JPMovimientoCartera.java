package com.spirit.cartera.gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.ReportModelImpl;

public abstract class JPMovimientoCartera extends ReportModelImpl {
	public JPMovimientoCartera() {
		setName("Movimiento de Cartera");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblTipoCartera = new JLabel();
		cmbTipoCartera = new JComboBox();
		spTblMovimientoCartera = new JScrollPane();
		tblMovimientoCartera = new JTable();
		lblOperadorNegocio = new JLabel();
		txtOperadorNegocio = new JTextField();
		btnConsultar = new JButton();
		btnBuscarOperadorNegocio = new JButton();
		cbTodosOperadores = new JCheckBox();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodosClientes = new JCheckBox();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnResetearFechas = new JButton();
		lblFechaFinal = new JLabel();
		cmbFechaFinal = new DateComboBox();
		cbMostrarTodos = new JCheckBox();
		lblTotalDebitos = new JLabel();
		txtTotalDebitos = new JTextField();
		lblTotalCreditos = new JLabel();
		txtTotalCreditos = new JTextField();
		lblSaldo = new JLabel();
		txtSaldo = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX6),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY6),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY6)
			}));

		//---- lblTipoCartera ----
		lblTipoCartera.setText("Tipo cartera:");
		add(lblTipoCartera, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbTipoCartera ----
		cmbTipoCartera.setModel(new DefaultComboBoxModel(new String[] {
			"CLIENTE",
			"PROVEEDOR"
		}));
		add(cmbTipoCartera, cc.xy(5, 3));

		//======== spTblMovimientoCartera ========
		{

			//---- tblMovimientoCartera ----
			tblMovimientoCartera.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"#", "F. Emisi\u00f3n", "Diario", "Transacci\u00f3n", "Usuario", "Detalle", "D\u00e9bitos", "Cr\u00e9ditos"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblMovimientoCartera.setViewportView(tblMovimientoCartera);
		}
		add(spTblMovimientoCartera, cc.xywh(3, 15, 15, 5));

		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Operador negocio:");
		add(lblOperadorNegocio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtOperadorNegocio ----
		txtOperadorNegocio.setEditable(false);
		add(txtOperadorNegocio, cc.xywh(5, 5, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(15, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		add(btnBuscarOperadorNegocio, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosOperadores ----
		cbTodosOperadores.setText("Todos");
		add(cbTodosOperadores, cc.xy(11, 5));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 7, 3, 1));
		add(btnCliente, cc.xywh(9, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosClientes ----
		cbTodosClientes.setText("Todos");
		add(cbTodosClientes, cc.xy(11, 7));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicial, cc.xy(5, 9));

		//---- btnResetearFechas ----
		btnResetearFechas.setText("Resetear fechas");
		btnResetearFechas.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnResetearFechas, cc.xywh(7, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFinal, cc.xy(5, 11));

		//---- cbMostrarTodos ----
		cbMostrarTodos.setText("Mostrar movimientos con asientos no autorizados");
		cbMostrarTodos.setSelected(true);
		add(cbMostrarTodos, cc.xywh(7, 11, 9, 1));

		//---- lblTotalDebitos ----
		lblTotalDebitos.setText("Total D\u00e9bitos:");
		lblTotalDebitos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalDebitos.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalDebitos, cc.xy(15, 23));

		//---- txtTotalDebitos ----
		txtTotalDebitos.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalDebitos.setEditable(false);
		add(txtTotalDebitos, cc.xy(17, 23));

		//---- lblTotalCreditos ----
		lblTotalCreditos.setText("Total Cr\u00e9ditos:");
		lblTotalCreditos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalCreditos, cc.xy(15, 25));

		//---- txtTotalCreditos ----
		txtTotalCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalCreditos.setEditable(false);
		add(txtTotalCreditos, cc.xy(17, 25));

		//---- lblSaldo ----
		lblSaldo.setText("S A L D O :");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xy(15, 27));

		//---- txtSaldo ----
		txtSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSaldo.setEditable(false);
		txtSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtSaldo, cc.xy(17, 27));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblTipoCartera;
	private JComboBox cmbTipoCartera;
	private JScrollPane spTblMovimientoCartera;
	private JTable tblMovimientoCartera;
	private JLabel lblOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JButton btnConsultar;
	private JButton btnBuscarOperadorNegocio;
	private JCheckBox cbTodosOperadores;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodosClientes;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JButton btnResetearFechas;
	private JLabel lblFechaFinal;
	private DateComboBox cmbFechaFinal;
	private JCheckBox cbMostrarTodos;
	private JLabel lblTotalDebitos;
	private JTextField txtTotalDebitos;
	private JLabel lblTotalCreditos;
	private JTextField txtTotalCreditos;
	private JLabel lblSaldo;
	private JTextField txtSaldo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbTodosOperadores() {
		return cbTodosOperadores;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public void setCmbFechaFinal(DateComboBox cmbFechaFinal) {
		this.cmbFechaFinal = cmbFechaFinal;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public void setCmbFechaInicial(DateComboBox cmbFechaInicial) {
		this.cmbFechaInicial = cmbFechaInicial;
	}

	public JComboBox getCmbTipoCartera() {
		return cmbTipoCartera;
	}

	public void setCmbTipoCartera(JComboBox cmbTipoCartera) {
		this.cmbTipoCartera = cmbTipoCartera;
	}

	public JTable getTblMovimientoCartera() {
		return tblMovimientoCartera;
	}

	public void setTblMovimientoCartera(JTable tblMovimientoCartera) {
		this.tblMovimientoCartera = tblMovimientoCartera;
	}

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}

	public JTextField getTxtSaldo() {
		return txtSaldo;
	}

	public void setTxtSaldo(JTextField txtSaldo) {
		this.txtSaldo = txtSaldo;
	}

	public JTextField getTxtTotalCreditos() {
		return txtTotalCreditos;
	}

	public void setTxtTotalCreditos(JTextField txtTotalCreditos) {
		this.txtTotalCreditos = txtTotalCreditos;
	}

	public JTextField getTxtTotalDebitos() {
		return txtTotalDebitos;
	}

	public void setTxtTotalDebitos(JTextField txtTotalDebitos) {
		this.txtTotalDebitos = txtTotalDebitos;
	}

	public JButton getBtnResetearFechas() {
		return btnResetearFechas;
	}

	public void setBtnResetearFechas(JButton btnResetearFechas) {
		this.btnResetearFechas = btnResetearFechas;
	}

	public JCheckBox getCbMostrarTodos() {
		return cbMostrarTodos;
	}

	public void setCbMostrarTodos(JCheckBox cbMostrarTodos) {
		this.cbMostrarTodos = cbMostrarTodos;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JCheckBox getCbTodosClientes() {
		return cbTodosClientes;
	}
}
