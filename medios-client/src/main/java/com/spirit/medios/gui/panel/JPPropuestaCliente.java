package com.spirit.medios.gui.panel;

import java.awt.Dimension;
import java.awt.Font;

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
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;


/**
 * @author Tatiana Vásconez
 */
public abstract class JPPropuestaCliente extends SpiritModelImpl {
	
	private static final long serialVersionUID = 7288040350798541061L;
	
	public JPPropuestaCliente() {
		initComponents();
		setName("Propuesta Cliente");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		txtCodigo = new JTextField();
		lblcodigo = new JLabel();
		btnLLenarTablaPresupuestos = new JButton();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		lblFecha = new JLabel();
		txtFecha = new JTextField();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cmbEstado = new JComboBox();
		lblEstado = new JLabel();
		lblClienteOficina = new JLabel();
		txtOficina = new JTextField();
		btnBuscarOficina = new JButton();
		txtValor = new JTextField();
		lblValor = new JLabel();
		lblOrdenTrabajo = new JLabel();
		cmbOrdenTrabajo = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		spPropuestaDetalle = new JScrollPane();
		tblPropuestaDetalle = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(160)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
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
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
			}));
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblcodigo ----
		lblcodigo.setText("C\u00f3digo:");
		add(lblcodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- btnLLenarTablaPresupuestos ----
		btnLLenarTablaPresupuestos.setText("LLenar Tabla Presupuestos");
		add(btnLLenarTablaPresupuestos, cc.xy(19, 3));

		//---- lblCorporacion ----
		lblCorporacion.setText("Corporaci\u00f3n:");
		add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtCorporacion, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(btnBuscarCorporacion, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFecha ----
		lblFecha.setText("Fecha:");
		add(lblFecha, cc.xywh(13, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtFecha, cc.xywh(15, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtCliente, cc.xywh(5, 7, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(btnBuscarCliente, cc.xywh(9, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbEstado, cc.xywh(15, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblClienteOficina ----
		lblClienteOficina.setText("Oficina:");
		add(lblClienteOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtOficina, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(btnBuscarOficina, cc.xywh(9, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- txtValor ----
		txtValor.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtValor, cc.xywh(15, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblValor ----
		lblValor.setText("Valor:");
		lblValor.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblValor, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblOrdenTrabajo ----
		lblOrdenTrabajo.setText("Orden Trabajo:");
		add(lblOrdenTrabajo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbOrdenTrabajo, cc.xywh(5, 11, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblObservacion ----
		lblObservacion.setText("Observaci\u00f3n:");
		add(lblObservacion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtObservacion, cc.xywh(5, 13, 11, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spPropuestaDetalle ========
		{

			//---- tblPropuestaDetalle ----
			tblPropuestaDetalle.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Presupuesto/Plan Medio", "Concepto", "Valor"
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
			tblPropuestaDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
			tblPropuestaDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			spPropuestaDetalle.setViewportView(tblPropuestaDetalle);
		}
		add(spPropuestaDetalle, cc.xywh(3, 17, 19, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JTextField txtCodigo;
	private JLabel lblcodigo;
	private JButton btnLLenarTablaPresupuestos;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JComboBox cmbEstado;
	private JLabel lblEstado;
	private JLabel lblClienteOficina;
	private JTextField txtOficina;
	private JButton btnBuscarOficina;
	private JTextField txtValor;
	private JLabel lblValor;
	private JLabel lblOrdenTrabajo;
	private JComboBox cmbOrdenTrabajo;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JScrollPane spPropuestaDetalle;
	private JTable tblPropuestaDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JButton getBtnBuscarOficina() {
		return btnBuscarOficina;
	}

	public void setBtnBuscarOficina(JButton btnBuscarOficina) {
		this.btnBuscarOficina = btnBuscarOficina;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbOrdenTrabajo() {
		return cmbOrdenTrabajo;
	}

	public void setCmbOrdenTrabajo(JComboBox cmbOrdenTrabajo) {
		this.cmbOrdenTrabajo = cmbOrdenTrabajo;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JTextField txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JTable getTblPropuestaDetalle() {
		return tblPropuestaDetalle;
	}

	public void setTblPropuestaDetalle(JTable tblPropuestaDetalle) {
		this.tblPropuestaDetalle = tblPropuestaDetalle;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JButton getBtnLLenarTablaPresupuestos() {
		return btnLLenarTablaPresupuestos;
	}

	public void setBtnLLenarTablaPresupuestos(JButton btnLLenarTablaPresupuestos) {
		this.btnLLenarTablaPresupuestos = btnLLenarTablaPresupuestos;
	}
}

