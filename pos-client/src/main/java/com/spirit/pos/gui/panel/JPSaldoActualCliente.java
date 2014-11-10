package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
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
import com.spirit.client.model.ReportModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPSaldoActualCliente extends ReportModelImpl {
	public JPSaldoActualCliente() {
		initComponents();
		setName("Saldo Actual Cliente");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spTblSaldoActualCliente = new JScrollPane();
		tblSaldoActualCliente = new JTable();
		lblOperadorNegocio = new JLabel();
		txtOperadorNegocio = new JTextField();
		btnConsultar = new JButton();
		btnBuscarOperadorNegocio = new JButton();
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
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spTblSaldoActualCliente ========
		{
			
			//---- tblSaldoActualCliente ----
			tblSaldoActualCliente.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"#", "F. Emisi\u00f3n", "Transacci\u00f3n", "Detalle", "D\u00e9bitos", "Cr\u00e9ditos"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblSaldoActualCliente.setViewportView(tblSaldoActualCliente);
		}
		add(spTblSaldoActualCliente, cc.xywh(3, 7, 13, 5));

		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Operador negocio:");
		add(lblOperadorNegocio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtOperadorNegocio ----
		txtOperadorNegocio.setEditable(false);
		add(txtOperadorNegocio, cc.xywh(5, 3, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- btnBuscarOperadorNegocio ----
		btnBuscarOperadorNegocio.setText("B");
		add(btnBuscarOperadorNegocio, cc.xywh(9, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblTotalDebitos ----
		lblTotalDebitos.setText("Total D\u00e9bitos:");
		lblTotalDebitos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalDebitos.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalDebitos, cc.xy(13, 15));

		//---- txtTotalDebitos ----
		txtTotalDebitos.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalDebitos.setEditable(false);
		add(txtTotalDebitos, cc.xy(15, 15));

		//---- lblTotalCreditos ----
		lblTotalCreditos.setText("Total Cr\u00e9ditos:");
		lblTotalCreditos.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalCreditos.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalCreditos, cc.xy(13, 17));

		//---- txtTotalCreditos ----
		txtTotalCreditos.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalCreditos.setEditable(false);
		add(txtTotalCreditos, cc.xy(15, 17));

		//---- lblSaldo ----
		lblSaldo.setText("S A L D O :");
		lblSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSaldo.setBackground(new Color(236, 233, 216));
		add(lblSaldo, cc.xy(13, 19));

		//---- txtSaldo ----
		txtSaldo.setHorizontalAlignment(JTextField.RIGHT);
		txtSaldo.setEditable(false);
		txtSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtSaldo, cc.xy(15, 19));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spTblSaldoActualCliente;
	private JTable tblSaldoActualCliente;
	private JLabel lblOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JButton btnConsultar;
	private JButton btnBuscarOperadorNegocio;
	private JLabel lblTotalDebitos;
	private JTextField txtTotalDebitos;
	private JLabel lblTotalCreditos;
	private JTextField txtTotalCreditos;
	private JLabel lblSaldo;
	private JTextField txtSaldo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JScrollPane getSpTblSaldoActualCliente() {
		return spTblSaldoActualCliente;
	}

	public void setSpTblSaldoActualCliente(JScrollPane spTblSaldoActualCliente) {
		this.spTblSaldoActualCliente = spTblSaldoActualCliente;
	}

	public JTable getTblSaldoActualCliente() {
		return tblSaldoActualCliente;
	}

	public void setTblSaldoActualCliente(JTable tblSaldoActualCliente) {
		this.tblSaldoActualCliente = tblSaldoActualCliente;
	}

	public JLabel getLblOperadorNegocio() {
		return lblOperadorNegocio;
	}

	public void setLblOperadorNegocio(JLabel lblOperadorNegocio) {
		this.lblOperadorNegocio = lblOperadorNegocio;
	}

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}

	public JLabel getLblTotalDebitos() {
		return lblTotalDebitos;
	}

	public void setLblTotalDebitos(JLabel lblTotalDebitos) {
		this.lblTotalDebitos = lblTotalDebitos;
	}

	public JTextField getTxtTotalDebitos() {
		return txtTotalDebitos;
	}

	public void setTxtTotalDebitos(JTextField txtTotalDebitos) {
		this.txtTotalDebitos = txtTotalDebitos;
	}

	public JLabel getLblTotalCreditos() {
		return lblTotalCreditos;
	}

	public void setLblTotalCreditos(JLabel lblTotalCreditos) {
		this.lblTotalCreditos = lblTotalCreditos;
	}

	public JTextField getTxtTotalCreditos() {
		return txtTotalCreditos;
	}

	public void setTxtTotalCreditos(JTextField txtTotalCreditos) {
		this.txtTotalCreditos = txtTotalCreditos;
	}

	public JLabel getLblSaldo() {
		return lblSaldo;
	}

	public void setLblSaldo(JLabel lblSaldo) {
		this.lblSaldo = lblSaldo;
	}

	public JTextField getTxtSaldo() {
		return txtSaldo;
	}

	public void setTxtSaldo(JTextField txtSaldo) {
		this.txtSaldo = txtSaldo;
	}
	
	
	
}
