package com.spirit.sri.gui.panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

/**
 * @author xruiz
 */
public abstract class JPRetencionesCliente extends MantenimientoModelImpl {
	public JPRetencionesCliente() {
		initComponents();
		setName("Retenciones al Cliente");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblTipoRetencion = new JLabel();
		cmbTipoRetencion = new JComboBox();
		lblPorcentaje = new JLabel();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblCuenta = new JLabel();
		txtCuenta = new JTextField();
		btnCuenta = new JButton();
		txtPorcentaje = new JTextField();
		spTblRetencionesCliente = new JScrollPane();
		tblRetencionesCliente = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(43)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xy(5, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xywh(11, 5, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(11, 7, 3, 1));

		//---- lblTipoRetencion ----
		lblTipoRetencion.setText("Tipo de Retenci\u00f3n:");
		add(lblTipoRetencion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbTipoRetencion ----
		cmbTipoRetencion.setModel(new DefaultComboBoxModel(new String[] {
			"RENTA",
			"IVA"
		}));
		add(cmbTipoRetencion, cc.xy(5, 5));

		//---- lblPorcentaje ----
		lblPorcentaje.setText("Porcentaje:");
		add(lblPorcentaje, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de Cuentas:");
		add(lblPlanCuenta, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(5, 9, 7, 1));

		//---- lblCuenta ----
		lblCuenta.setText("Cuenta:");
		add(lblCuenta, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCuenta, cc.xywh(5, 11, 9, 1));
		add(btnCuenta, cc.xywh(15, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		txtPorcentaje.setHorizontalAlignment(JTextField.RIGHT);
		add(txtPorcentaje, cc.xy(5, 7));

		//======== spTblRetencionesCliente ========
		{
			
			//---- tblRetencionesCliente ----
			tblRetencionesCliente.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"Tipo de Retenci\u00f3n", "Porcentaje", "F. Inicio", "F. Fin", "Cuenta", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblRetencionesCliente.setViewportView(tblRetencionesCliente);
		}
		add(spTblRetencionesCliente, cc.xywh(3, 15, 15, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnCuenta.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnCuenta.setToolTipText("Buscar Cuenta Retencion Fuente");
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblRetencionesCliente.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
				
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblTipoRetencion;
	private JComboBox cmbTipoRetencion;
	private JLabel lblPorcentaje;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblCuenta;
	private JTextField txtCuenta;
	private JButton btnCuenta;
	private JTextField txtPorcentaje;
	private JScrollPane spTblRetencionesCliente;
	private JTable tblRetencionesCliente;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JScrollPane getSpTblRetencionesCliente() {
		return spTblRetencionesCliente;
	}

	public void setSpTblRetencionesCliente(JScrollPane spTblRetencionesCliente) {
		this.spTblRetencionesCliente = spTblRetencionesCliente;
	}

	public JTable getTblRetencionesCliente() {
		return tblRetencionesCliente;
	}

	public void setTblRetencionesCliente(JTable tblRetencionesCliente) {
		this.tblRetencionesCliente = tblRetencionesCliente;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JTextField getTxtPorcentaje() {
		return txtPorcentaje;
	}

	public void setTxtPorcentaje(JTextField txtPorcentaje) {
		this.txtPorcentaje = txtPorcentaje;
	}

	public JButton getBtnCuenta() {
		return btnCuenta;
	}

	public void setBtnCuenta(JButton btnCuenta) {
		this.btnCuenta = btnCuenta;
	}

	public JComboBox getCmbTipoRetencion() {
		return cmbTipoRetencion;
	}

	public void setCmbTipoRetencion(JComboBox cmbTipoRetencion) {
		this.cmbTipoRetencion = cmbTipoRetencion;
	}

	public JTextField getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(JTextField txtCuenta) {
		this.txtCuenta = txtCuenta;
	}
}
