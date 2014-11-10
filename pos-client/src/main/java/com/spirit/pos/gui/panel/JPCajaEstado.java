package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPCajaEstado extends SpiritModelImpl {
	public JPCajaEstado() {
		initComponents();
		setName("CAJA ESTADO");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		lblCajero22 = new JLabel();
		lblCajero2 = new JLabel();
		cmbOficina = new JComboBox();
		cmbCaja = new JComboBox();
		spTblCajaEstado = new JScrollPane();
		tblCajaEstado = new JTable();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		btnResetearFechas = new JButton();
		btnConsultar = new JButton();
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

		//---- label1 ----
		label1.setText("Khomo S.A");
		label1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label1.setForeground(new Color(102, 51, 0));
		add(label1, cc.xy(3, 3));

		//---- lblCajero22 ----
		lblCajero22.setText("Oficina:");
		lblCajero22.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero22, cc.xy(3, 5));

		//---- lblCajero2 ----
		lblCajero2.setText("Caja-PC:");
		lblCajero2.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero2, cc.xy(3, 7));
		add(cmbOficina, cc.xy(5, 5));
		add(cmbCaja, cc.xy(5, 7));

		//======== spTblCajaEstado ========
		{
			
			//---- tblCajaEstado ----
			tblCajaEstado.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"#", "Cajero", "Fecha Apertura", "Fecha Cierre", "ESTADO"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, true, false, true, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblCajaEstado.setViewportView(tblCajaEstado);
		}
		add(spTblCajaEstado, cc.xywh(3, 17, 7, 5));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha desde:");
		lblFechaInicial.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblFechaInicial, cc.xy(3, 9));
		add(cmbFechaInicial, cc.xy(5, 9));

		//---- btnResetearFechas ----
		btnResetearFechas.setText("Resetear fechas");
		btnResetearFechas.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnResetearFechas, cc.xy(7, 9));

		//---- btnConsultar ----
		btnConsultar.setText("C O N S U L T A R");
		btnConsultar.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(btnConsultar, cc.xy(3, 13));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel lblCajero22;
	private JLabel lblCajero2;
	private JComboBox cmbOficina;
	private JComboBox cmbCaja;
	private JScrollPane spTblCajaEstado;
	private JTable tblCajaEstado;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JButton btnResetearFechas;
	private JButton btnConsultar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLblCajero22() {
		return lblCajero22;
	}

	public void setLblCajero22(JLabel lblCajero22) {
		this.lblCajero22 = lblCajero22;
	}

	public JLabel getLblCajero2() {
		return lblCajero2;
	}

	public void setLblCajero2(JLabel lblCajero2) {
		this.lblCajero2 = lblCajero2;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JComboBox getCmbCaja() {
		return cmbCaja;
	}

	public void setCmbCaja(JComboBox cmbCaja) {
		this.cmbCaja = cmbCaja;
	}

	public JScrollPane getSpTblCajaEstado() {
		return spTblCajaEstado;
	}

	public void setSpTblCajaEstado(JScrollPane spTblCajaEstado) {
		this.spTblCajaEstado = spTblCajaEstado;
	}

	public JTable getTblCajaEstado() {
		return tblCajaEstado;
	}

	public void setTblCajaEstado(JTable tblCajaEstado) {
		this.tblCajaEstado = tblCajaEstado;
	}

	public JLabel getLblFechaInicial() {
		return lblFechaInicial;
	}

	public void setLblFechaInicial(JLabel lblFechaInicial) {
		this.lblFechaInicial = lblFechaInicial;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public void setCmbFechaInicial(DateComboBox cmbFechaInicial) {
		this.cmbFechaInicial = cmbFechaInicial;
	}

	public JButton getBtnResetearFechas() {
		return btnResetearFechas;
	}

	public void setBtnResetearFechas(JButton btnResetearFechas) {
		this.btnResetearFechas = btnResetearFechas;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
	
	
	
}
