package com.spirit.contabilidad.gui.panel;
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
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPEventoContable extends MantenimientoModelImpl {
	public JPEventoContable() {
		initComponents();
		setName("Eventos Contables");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		txtCodigo = new JTextField();
		lblCodigo = new JLabel();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		lblModulo = new JLabel();
		cmbModulo = new JComboBox();
		lblLinea = new JLabel();
		cmbLinea = new JComboBox();
		lblSubtipoAsiento = new JLabel();
		cmbSubtipoAsiento = new JComboBox();
		lblEtapa = new JLabel();
		txtEtapa = new JTextField();
		cbUsarDescripcionDetalle = new JCheckBox();
		cbAutorizacionRequerida = new JCheckBox();
		cbAgruparDetalles = new JCheckBox();
		cbValidoGuardar = new JCheckBox();
		cbValidoActualizar = new JCheckBox();
		spTblEventoContable = new JScrollPane();
		tblEventoContable = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec("max(default;10dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec("max(default;10dlu)"),
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
				new RowSpec(Sizes.dluY(10))
			}));
		add(txtCodigo, cc.xywh(5, 3, 3, 1));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de cuenta:");
		add(lblPlanCuenta, cc.xywh(13, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(15, 3, 3, 1));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));

		//---- lblDocumento ----
		lblDocumento.setText("Documento:");
		add(lblDocumento, cc.xywh(13, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDocumento, cc.xywh(15, 5, 3, 1));

		//---- lblModulo ----
		lblModulo.setText("M\u00f3dulo:");
		add(lblModulo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbModulo, cc.xywh(5, 7, 5, 1));

		//---- lblLinea ----
		lblLinea.setText("L\u00ednea:");
		add(lblLinea, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbLinea, cc.xywh(15, 7, 3, 1));

		//---- lblSubtipoAsiento ----
		lblSubtipoAsiento.setText("Subtipo de asiento:");
		add(lblSubtipoAsiento, cc.xy(3, 9));
		add(cmbSubtipoAsiento, cc.xywh(5, 9, 5, 1));

		//---- lblEtapa ----
		lblEtapa.setText("Etapa:");
		add(lblEtapa, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEtapa ----
		txtEtapa.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtEtapa, cc.xy(15, 9));

		//---- cbUsarDescripcionDetalle ----
		cbUsarDescripcionDetalle.setText("Usar descripci\u00f3n detalle");
		add(cbUsarDescripcionDetalle, cc.xywh(3, 11, 5, 1));

		//---- cbAutorizacionRequerida ----
		cbAutorizacionRequerida.setText("Autorizaci\u00f3n requerida");
		add(cbAutorizacionRequerida, cc.xywh(9, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- cbAgruparDetalles ----
		cbAgruparDetalles.setText("Agrupar detalles");
		add(cbAgruparDetalles, cc.xywh(11, 11, 5, 1));

		//---- cbValidoGuardar ----
		cbValidoGuardar.setText("V\u00e1lido al guardar");
		add(cbValidoGuardar, cc.xywh(3, 13, 5, 1));

		//---- cbValidoActualizar ----
		cbValidoActualizar.setText("V\u00e1lido al actualizar");
		add(cbValidoActualizar, cc.xy(9, 13));

		//======== spTblEventoContable ========
		{

			//---- tblEventoContable ----
			tblEventoContable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "M\u00f3dulo", "Documento", "Linea", "Etapa"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblEventoContable.setViewportView(tblEventoContable);
		}
		add(spTblEventoContable, cc.xywh(3, 17, 15, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JLabel lblModulo;
	private JComboBox cmbModulo;
	private JLabel lblLinea;
	private JComboBox cmbLinea;
	private JLabel lblSubtipoAsiento;
	private JComboBox cmbSubtipoAsiento;
	private JLabel lblEtapa;
	private JTextField txtEtapa;
	private JCheckBox cbUsarDescripcionDetalle;
	private JCheckBox cbAutorizacionRequerida;
	private JCheckBox cbAgruparDetalles;
	private JCheckBox cbValidoGuardar;
	private JCheckBox cbValidoActualizar;
	private JScrollPane spTblEventoContable;
	private JTable tblEventoContable;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbLinea() {
		return cmbLinea;
	}
	
	public void setCmbLinea(JComboBox cmbLinea) {
		this.cmbLinea = cmbLinea;
	}
	
	public JComboBox getCmbModulo() {
		return cmbModulo;
	}
	
	public void setCmbModulo(JComboBox cmbModulo) {
		this.cmbModulo = cmbModulo;
	}
	
	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}
	
	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}
	
	public JScrollPane getSpTblEventoContable() {
		return spTblEventoContable;
	}
	
	public void setSpTblEventoContable(JScrollPane spTblEventoContable) {
		this.spTblEventoContable = spTblEventoContable;
	}
	
	public JTable getTblEventoContable() {
		return tblEventoContable;
	}
	
	public void setTblEventoContable(JTable tblEventoContable) {
		this.tblEventoContable = tblEventoContable;
	}
	
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}
	
	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
	
	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}
	
	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}
	
	public JTextField getTxtEtapa() {
		return txtEtapa;
	}
	
	public void setTxtEtapa(JTextField txtEtapa) {
		this.txtEtapa = txtEtapa;
	}

	public JComboBox getCmbSubtipoAsiento() {
		return cmbSubtipoAsiento;
	}

	public void setCmbSubtipoAsiento(JComboBox cmbSubtipoAsiento) {
		this.cmbSubtipoAsiento = cmbSubtipoAsiento;
	}

	public JCheckBox getCbUsarDescripcionDetalle() {
		return cbUsarDescripcionDetalle;
	}

	public void setCbUsarDescripcionDetalle(JCheckBox cbUsarDescripcionDetalle) {
		this.cbUsarDescripcionDetalle = cbUsarDescripcionDetalle;
	}

	public JCheckBox getCbAutorizacionRequerida() {
		return cbAutorizacionRequerida;
	}

	public void setCbAutorizacionRequerida(JCheckBox cbAutorizacionRequerida) {
		this.cbAutorizacionRequerida = cbAutorizacionRequerida;
	}

	public JCheckBox getCbAgruparDetalles() {
		return cbAgruparDetalles;
	}

	public void setCbAgruparDetalles(JCheckBox cbAgruparDetalles) {
		this.cbAgruparDetalles = cbAgruparDetalles;
	}

	public JCheckBox getCbValidoGuardar() {
		return cbValidoGuardar;
	}

	public void setCbValidoGuardar(JCheckBox cbValidoGuardar) {
		this.cbValidoGuardar = cbValidoGuardar;
	}

	public JCheckBox getCbValidoActualizar() {
		return cbValidoActualizar;
	}

	public void setCbValidoActualizar(JCheckBox cbValidoActualizar) {
		this.cbValidoActualizar = cbValidoActualizar;
	}
}
