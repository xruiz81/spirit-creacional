package com.spirit.sri.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPRetencionProveedor extends MantenimientoModelImpl {
	public JPRetencionProveedor() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoPersona = new JLabel();
		cmbTipoPersona = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblContabilidad = new JLabel();
		lblBienServicio = new JLabel();
		cmbBienServicio = new JComboBox();
		cmbContabilidad = new JComboBox();
		lblRetencionFuente = new JLabel();
		cmbRetencionFuente = new JComboBox();
		lblRetencionIva = new JLabel();
		cmbRetencionIva = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblCuentaRetencionFuente = new JLabel();
		btnCuentaRetencionFuente = new JButton();
		txtCuentaRetencionFuente = new JTextField();
		lblCuentaRetencionIva = new JLabel();
		btnCuentaRetencionIva = new JButton();
		txtCuentaRetencionIva = new JTextField();
		spTblProveedorRetencion = new JScrollPane();
		tblProveedorRetencion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Retenciones de Proveedor");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(45)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(45)),
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

		//---- lblTipoPersona ----
		lblTipoPersona.setText("Tipo de Persona:");
		add(lblTipoPersona, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbTipoPersona ----
		cmbTipoPersona.setModel(new DefaultComboBoxModel(new String[] {
			"NATURAL",
			"JURIDICA"
		}));
		add(cmbTipoPersona, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEstado, cc.xy(11, 3));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xy(13, 3));

		//---- lblContabilidad ----
		lblContabilidad.setText("Lleva Contabilidad:");
		add(lblContabilidad, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblBienServicio ----
		lblBienServicio.setText("Bien/Servicio");
		add(lblBienServicio, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbBienServicio ----
		cmbBienServicio.setModel(new DefaultComboBoxModel(new String[] {
			"BIEN",
			"SERVICIO"
		}));
		add(cmbBienServicio, cc.xywh(13, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cmbContabilidad ----
		cmbContabilidad.setEditable(false);
		cmbContabilidad.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbContabilidad, cc.xy(5, 5));

		//---- lblRetencionFuente ----
		lblRetencionFuente.setText("Retenci\u00f3n Fuente [%]");
		add(lblRetencionFuente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbRetencionFuente, cc.xy(5, 7));

		//---- lblRetencionIva ----
		lblRetencionIva.setText("Retenci\u00f3n IVA [%]");
		add(lblRetencionIva, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbRetencionIva, cc.xy(13, 7));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbFechaInicio ----
		cmbFechaInicio.setEditable(false);
		add(cmbFechaInicio, cc.xywh(5, 9, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFechaFin, cc.xy(11, 9));

		//---- cmbFechaFin ----
		cmbFechaFin.setEditable(false);
		add(cmbFechaFin, cc.xywh(13, 9, 3, 1));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de Cuentas:");
		add(lblPlanCuenta, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(5, 11, 7, 1));

		//---- lblCuentaRetencionFuente ----
		lblCuentaRetencionFuente.setText("Cuenta Retenci\u00f3n Fuente:");
		add(lblCuentaRetencionFuente, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(btnCuentaRetencionFuente, cc.xywh(17, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(txtCuentaRetencionFuente, cc.xywh(5, 13, 11, 1));

		//---- lblCuentaRetencionIva ----
		lblCuentaRetencionIva.setText("Cuenta Retenci\u00f3n IVA:");
		add(lblCuentaRetencionIva, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(btnCuentaRetencionIva, cc.xywh(17, 15, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(txtCuentaRetencionIva, cc.xywh(5, 15, 11, 1));

		//======== spTblProveedorRetencion ========
		{
			
			//---- tblProveedorRetencion ----
			tblProveedorRetencion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", "", null, null, null, null},
				},
				new String[] {
					"Estado", "Persona", "Contabilidad", "Bien/Servicio", "Ret. Fuente", "Ret. IVA", "Fecha Inicio", "Fecha Fin"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, false, true, true
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblProveedorRetencion.setPreferredScrollableViewportSize(new Dimension(450, 150));
			spTblProveedorRetencion.setViewportView(tblProveedorRetencion);
		}
		add(spTblProveedorRetencion, cc.xywh(3, 19, 17, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnCuentaRetencionFuente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnCuentaRetencionFuente.setToolTipText("Buscar Cuenta Retencion Fuente");
		btnCuentaRetencionIva.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnCuentaRetencionIva.setToolTipText("Buscar Cuenta Retención IVA");
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoPersona;
	private JComboBox cmbTipoPersona;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblContabilidad;
	private JLabel lblBienServicio;
	private JComboBox cmbBienServicio;
	private JComboBox cmbContabilidad;
	private JLabel lblRetencionFuente;
	private JComboBox cmbRetencionFuente;
	private JLabel lblRetencionIva;
	private JComboBox cmbRetencionIva;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblCuentaRetencionFuente;
	private JButton btnCuentaRetencionFuente;
	private JTextField txtCuentaRetencionFuente;
	private JLabel lblCuentaRetencionIva;
	private JButton btnCuentaRetencionIva;
	private JTextField txtCuentaRetencionIva;
	private JScrollPane spTblProveedorRetencion;
	private JTable tblProveedorRetencion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbBienServicio() {
		return cmbBienServicio;
	}

	public void setCmbBienServicio(JComboBox cmbBienServicio) {
		this.cmbBienServicio = cmbBienServicio;
	}

	public JComboBox getCmbContabilidad() {
		return cmbContabilidad;
	}

	public void setCmbContabilidad(JComboBox cmbContabilidad) {
		this.cmbContabilidad = cmbContabilidad;
	}

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

	public JComboBox getCmbRetencionFuente() {
		return cmbRetencionFuente;
	}

	public void setCmbRetencionFuente(JComboBox cmbRetencionFuente) {
		this.cmbRetencionFuente = cmbRetencionFuente;
	}

	public JComboBox getCmbRetencionIva() {
		return cmbRetencionIva;
	}

	public void setCmbRetencionIva(JComboBox cmbRetencionIva) {
		this.cmbRetencionIva = cmbRetencionIva;
	}

	public JComboBox getCmbTipoPersona() {
		return cmbTipoPersona;
	}

	public void setCmbTipoPersona(JComboBox cmbTipoPersona) {
		this.cmbTipoPersona = cmbTipoPersona;
	}

	public JScrollPane getSpTblProveedorRetencion() {
		return spTblProveedorRetencion;
	}

	public void setSpTblProveedorRetencion(JScrollPane spTblProveedorRetencion) {
		this.spTblProveedorRetencion = spTblProveedorRetencion;
	}

	public JTable getTblProveedorRetencion() {
		return tblProveedorRetencion;
	}

	public void setTblProveedorRetencion(JTable tblProveedorRetencion) {
		this.tblProveedorRetencion = tblProveedorRetencion;
	}

	public JButton getBtnCuentaRetencionFuente() {
		return btnCuentaRetencionFuente;
	}

	public void setBtnCuentaRetencionFuente(JButton btnCuentaRetencionFuente) {
		this.btnCuentaRetencionFuente = btnCuentaRetencionFuente;
	}

	public JButton getBtnCuentaRetencionIva() {
		return btnCuentaRetencionIva;
	}

	public void setBtnCuentaRetencionIva(JButton btnCuentaRetencionIva) {
		this.btnCuentaRetencionIva = btnCuentaRetencionIva;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JTextField getTxtCuentaRetencionFuente() {
		return txtCuentaRetencionFuente;
	}

	public void setTxtCuentaRetencionFuente(JTextField txtCuentaRetencionFuente) {
		this.txtCuentaRetencionFuente = txtCuentaRetencionFuente;
	}

	public JTextField getTxtCuentaRetencionIva() {
		return txtCuentaRetencionIva;
	}

	public void setTxtCuentaRetencionIva(JTextField txtCuentaRetencionIva) {
		this.txtCuentaRetencionIva = txtCuentaRetencionIva;
	}	
}
