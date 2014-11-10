package com.spirit.nomina.gui.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.ReportModelImpl;

public abstract class JPReporteContrato extends ReportModelImpl {
	public JPReporteContrato() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		lblEmpleado = new JLabel();
		btnBuscarEmpleado = new JButton();
		txtEmpleado = new JTextField();
		lblContrato = new JLabel();
		btnBuscarContrato = new JButton();
		txtContrato = new JTextField();
		label1 = new JLabel();
		cmbFechaInicio = new DateComboBox();
		label2 = new JLabel();
		cmbFechaFin = new DateComboBox();
		btnConsultar = new JButton();
		lblCantidadTotal = new JLabel();
		panelFiltro = new JPanel();
		panelTable = new JScrollPane();
		lblTotal = new JLabel();
		lblValorTotal = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Reporte por Contrato");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(85)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.dluX(35), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo Contrato: ");
		add(lblTipoContrato, cc.xy(3, 3));
		add(cmbTipoContrato, cc.xywh(5, 3, 5, 1));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- btnBuscarEmpleado ----
		btnBuscarEmpleado.setText("B");
		add(btnBuscarEmpleado, cc.xy(11, 5));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 5, 5, 1));

		//---- lblContrato ----
		lblContrato.setText("Contrato:");
		add(lblContrato, cc.xy(3, 7));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xy(5, 7));

		//---- btnBuscarContrato ----
		btnBuscarContrato.setText("B");
		add(btnBuscarContrato, cc.xy(7, 7));

		//---- label1 ----
		label1.setText("Fecha Inicio: ");
		add(label1, cc.xy(3, 9));
		add(cmbFechaInicio, cc.xywh(5, 9, 3, 1));

		//---- label2 ----
		label2.setText("Fecha Fin: ");
		add(label2, cc.xy(3, 11));
		add(cmbFechaFin, cc.xywh(5, 11, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(5, 15));

		//---- lblCantidadTotal ----
		lblCantidadTotal.setText(" ");
		add(lblCantidadTotal, cc.xy(17, 17));

		//======== panelFiltro ========
		{
			panelFiltro.setLayout(new FormLayout(
				"default",
				"fill:default"));
		}
		add(panelFiltro, cc.xywh(3, 19, 17, 1));
		add(panelTable, cc.xywh(3, 21, 17, 3));

		//---- lblTotal ----
		lblTotal.setText("Total: ");
		lblTotal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		add(lblTotal, cc.xy(17, 25));

		//---- lblValorTotal ----
		lblValorTotal.setText(" ");
		lblValorTotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		add(lblValorTotal, cc.xy(19, 25));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel lblEmpleado;
	private JButton btnBuscarEmpleado;
	private JTextField txtEmpleado;
	private JLabel lblContrato;
	private JButton btnBuscarContrato;
	private JTextField txtContrato;
	private JLabel label1;
	private DateComboBox cmbFechaInicio;
	private JLabel label2;
	private DateComboBox cmbFechaFin;
	private JButton btnConsultar;
	private JLabel lblCantidadTotal;
	private JPanel panelFiltro;
	private JScrollPane panelTable;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarContrato() {
		return btnBuscarContrato;
	}

	public JButton getBtnBuscarEmpleado() {
		return btnBuscarEmpleado;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public JLabel getLblCantidadTotal() {
		return lblCantidadTotal;
	}

	public JLabel getLblContrato() {
		return lblContrato;
	}

	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public JLabel getLblValorTotal() {
		return lblValorTotal;
	}

	public JPanel getPanelFiltro() {
		return panelFiltro;
	}

	public JScrollPane getPanelTable() {
		return panelTable;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}
}
