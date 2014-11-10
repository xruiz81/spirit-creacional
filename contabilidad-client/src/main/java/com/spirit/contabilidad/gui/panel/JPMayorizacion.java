package com.spirit.contabilidad.gui.panel;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;



public abstract class JPMayorizacion extends MantenimientoModelImpl {
	public JPMayorizacion() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblPeriodo = new JLabel();
		cmbPeriodo = new JComboBox();
		lblMesInicial = new JLabel();
		cmbMesInicial = new JComboBox();
		lblEventos = new JLabel();
		spEventos = new JScrollPane();
		txtEventos = new JTextArea();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Mayorizacion");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(150)),
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
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblPeriodo ----
		lblPeriodo.setText("Periodo: ");
		lblPeriodo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblPeriodo, cc.xy(3, 3));
		add(cmbPeriodo, cc.xy(5, 3));

		//---- lblMesInicial ----
		lblMesInicial.setText("Mes inicial:");
		lblMesInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblMesInicial, cc.xy(3, 5));
		add(cmbMesInicial, cc.xy(5, 5));

		//---- lblEventos ----
		lblEventos.setText("Eventos: ");
		lblEventos.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblEventos, cc.xy(3, 7));

		//======== spEventos ========
		{
			spEventos.setViewportView(txtEventos);
		}
		add(spEventos, cc.xywh(3, 9, 5, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblPeriodo;
	private JComboBox cmbPeriodo;
	private JLabel lblMesInicial;
	private JComboBox cmbMesInicial;
	private JLabel lblEventos;
	private JScrollPane spEventos;
	private JTextArea txtEventos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JComboBox getCmbPeriodo() {
		return cmbPeriodo;
	}

	public void setCmbPeriodo(JComboBox cmbPeriodo) {
		this.cmbPeriodo = cmbPeriodo;
	}

	public JComboBox getCmbMesInicial() {
		return cmbMesInicial;
	}

	public void setCmbMesInicial(JComboBox cmbMesInicial) {
		this.cmbMesInicial = cmbMesInicial;
	}

	public JScrollPane getSpEventos() {
		return spEventos;
	}

	public void setSpEventos(JScrollPane spEventos) {
		this.spEventos = spEventos;
	}

	public JTextArea getTxtEventos() {
		return txtEventos;
	}

	public void setTxtEventos(JTextArea txtEventos) {
		this.txtEventos = txtEventos;
	}
}
