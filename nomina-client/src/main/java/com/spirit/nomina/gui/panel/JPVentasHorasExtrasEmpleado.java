package com.spirit.nomina.gui.panel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPVentasHorasExtrasEmpleado extends SpiritModelImpl {
	private static final long serialVersionUID = 2446936202043855322L;
	
	public JPVentasHorasExtrasEmpleado() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		sTitulo = compFactory.createSeparator("Tabla de datos para c\u00e1lculo de comisiones y horas extras");
		lblImportarDatosVentasDe = new JLabel();
		lblMes = new JLabel();
		cmbMes = new JComboBox();
		lblAnio = new JLabel();
		txtAnio = new JTextField();
		btnImportar = new JButton();
		btnBorrarTodo = new JButton();
		btnBorrarSeleccionados = new JButton();
		spDatos = new JScrollPane();
		tblDatos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;90dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;60dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;30dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;70dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;50dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;50dlu)"),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));
		add(sTitulo, cc.xywh(3, 3, 17, 1));

		//---- lblImportarDatosVentasDe ----
		lblImportarDatosVentasDe.setText("IMPORTAR VENTAS DE ->");
		lblImportarDatosVentasDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblImportarDatosVentasDe, cc.xy(3, 7));

		//---- lblMes ----
		lblMes.setText("MES:");
		lblMes.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblMes, cc.xy(5, 7));

		//---- cmbMes ----
		cmbMes.setModel(new DefaultComboBoxModel(new String[] {
			"ENERO",
			"FEBRERO",
			"MARZO",
			"ABRIL",
			"MAYO",
			"JUNIO",
			"JULIO",
			"AGOSTO",
			"SEPTIEMBRE",
			"OCTUBRE",
			"NOVIEMBRE",
			"DICIEMBRE"
		}));
		add(cmbMes, cc.xy(7, 7));

		//---- lblAnio ----
		lblAnio.setText("A\u00d1O:");
		lblAnio.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblAnio, cc.xy(9, 7));

		//---- txtAnio ----
		txtAnio.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtAnio, cc.xy(11, 7));

		//---- btnImportar ----
		btnImportar.setText("Importar");
		add(btnImportar, cc.xy(13, 7));

		//---- btnBorrarTodo ----
		btnBorrarTodo.setText("Borrar todo");
		add(btnBorrarTodo, cc.xy(15, 7));

		//---- btnBorrarSeleccionados ----
		btnBorrarSeleccionados.setText("Borrar seleccionados");
		add(btnBorrarSeleccionados, cc.xy(17, 7));

		//======== spDatos ========
		{

			//---- tblDatos ----
			tblDatos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					" ", "Empleado", "Total Ventas", "H.E. 50% Recargo", "H.E. 100% Recargo", "CONTRATO_ID"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, true, true, true, false
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
			{
				TableColumnModel cm = tblDatos.getColumnModel();
				cm.getColumn(1).setMinWidth(300);
				cm.getColumn(2).setMinWidth(70);
				cm.getColumn(3).setMinWidth(50);
				cm.getColumn(4).setMinWidth(50);
				cm.getColumn(5).setResizable(false);
			}
			spDatos.setViewportView(tblDatos);
		}
		add(spDatos, cc.xywh(3, 11, 17, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JComponent sTitulo;
	private JLabel lblImportarDatosVentasDe;
	private JLabel lblMes;
	private JComboBox cmbMes;
	private JLabel lblAnio;
	private JTextField txtAnio;
	private JButton btnImportar;
	private JButton btnBorrarTodo;
	private JButton btnBorrarSeleccionados;
	private JScrollPane spDatos;
	private JTable tblDatos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComponent getsTitulo() {
		return sTitulo;
	}

	public void setsTitulo(JComponent sTitulo) {
		this.sTitulo = sTitulo;
	}

	public JLabel getLblImportarDatosVentasDe() {
		return lblImportarDatosVentasDe;
	}

	public void setLblImportarDatosVentasDe(JLabel lblImportarDatosVentasDe) {
		this.lblImportarDatosVentasDe = lblImportarDatosVentasDe;
	}

	public JLabel getLblMes() {
		return lblMes;
	}

	public void setLblMes(JLabel lblMes) {
		this.lblMes = lblMes;
	}

	public JComboBox getCmbMes() {
		return cmbMes;
	}

	public void setCmbMes(JComboBox cmbMes) {
		this.cmbMes = cmbMes;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public void setLblAnio(JLabel lblAnio) {
		this.lblAnio = lblAnio;
	}

	public JTextField getTxtAnio() {
		return txtAnio;
	}

	public void setTxtAnio(JTextField txtAnio) {
		this.txtAnio = txtAnio;
	}

	public JButton getBtnImportar() {
		return btnImportar;
	}

	public void setBtnImportar(JButton btnImportar) {
		this.btnImportar = btnImportar;
	}

	public JButton getBtnBorrarTodo() {
		return btnBorrarTodo;
	}

	public void setBtnBorrarTodo(JButton btnBorrarTodo) {
		this.btnBorrarTodo = btnBorrarTodo;
	}

	public JButton getBtnBorrarSeleccionados() {
		return btnBorrarSeleccionados;
	}

	public void setBtnBorrarSeleccionados(JButton btnBorrarSeleccionados) {
		this.btnBorrarSeleccionados = btnBorrarSeleccionados;
	}

	public JScrollPane getSpDatos() {
		return spDatos;
	}

	public void setSpDatos(JScrollPane spDatos) {
		this.spDatos = spDatos;
	}

	public JTable getTblDatos() {
		return tblDatos;
	}

	public void setTblDatos(JTable tblDatos) {
		this.tblDatos = tblDatos;
	}
}
