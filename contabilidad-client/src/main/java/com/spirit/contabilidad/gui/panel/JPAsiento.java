package com.spirit.contabilidad.gui.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
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
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPAsiento extends SpiritModelImpl {
	public JPAsiento() {
		initComponents();
		setName("Asiento");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		spAsiento = new JScrollPane();
		panelAsiento = new JPanel();
		cbEfectivo = new JCheckBox();
		lblTipoAsiento = new JLabel();
		lblSubtipoAsiento = new JLabel();
		cmbSubtipoAsiento = new JComboBox();
		cmbTipoAsiento = new JComboBox();
		scAsiento = new JScrollPane();
		tblAsiento = new JTable();
		lblTotalHaber = new JLabel();
		lblNumero = new JLabel();
		txtNumero = new JFormattedTextField();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblFecha = new JLabel();
		cmbFecha = new DateComboBox();
		lblPeriodo = new JLabel();
		cmbPeriodo = new JComboBox();
		lblStatus = new JLabel();
		cmbEstado = new JComboBox();
		lblConcepto = new JLabel();
		txtConcepto = new JTextField();
		btnAgregarNota = new JButton();
		lblCuenta = new JLabel();
		txtCuenta = new JTextField();
		btnBuscarCuenta = new JButton();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblGlosa = new JLabel();
		txtGlosa = new JTextField();
		lblValorDebe = new JLabel();
		txtValorDebe = new JFormattedTextField();
		lblValorHaber = new JLabel();
		txtValorHaber = new JFormattedTextField();
		bpAsientoPanel = new JPanel();
		btnMostrarPanelCentrosGasto = new JButton();
		jpCentrosGasto = new JPanel();
		lblCentroGasto = new JLabel();
		cmbCentroGasto = new JComboBox();
		lblEmpleado = new JLabel();
		cmbEmpleado = new JComboBox();
		lblDepartamento = new JLabel();
		cmbDepartamento = new JComboBox();
		lblLinea = new JLabel();
		cmbLinea = new JComboBox();
		lblCliente = new JLabel();
		cmbCliente = new JComboBox();
		lblDumbLabel = new JLabel();
		panel1 = new JPanel();
		btnAgregar = new JButton();
		btnActualizar = new JButton();
		btnEliminar = new JButton();
		lblTotalDebe = new JLabel();
		txtTotalDebe = new JTextField();
		txtTotalHaber = new JTextField();
		btnAnadir = new JButton();
		popup = new JPopupMenu();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"max(pref;10dlu):grow",
			"fill:default:grow"));

		//======== spAsiento ========
		{

			//======== panelAsiento ========
			{
				panelAsiento.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX5),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(80)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(130)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX5)
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec("min(default;30dlu)"),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(100)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));
				panelAsiento.add(cbEfectivo, cc.xy(3, 11));

				//---- lblTipoAsiento ----
				lblTipoAsiento.setText("Tipo Asiento:");
				panelAsiento.add(lblTipoAsiento, cc.xywh(5, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(cmbSubtipoAsiento, cc.xywh(15, 11, 5, 1));
				panelAsiento.add(cmbTipoAsiento, cc.xywh(7, 11, 5, 1));

				//======== scAsiento ========
				{

					//---- tblAsiento ----
					tblAsiento.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00f3digo", "Nombre cuenta", "Glosa", "Debe", "Haber"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblAsiento.setPreferredScrollableViewportSize(new Dimension(450, 200));
					scAsiento.setViewportView(tblAsiento);
				}
				panelAsiento.add(scAsiento, cc.xywh(5, 25, 15, 5));

				//---- lblTotalHaber ----
				lblTotalHaber.setText("Total Haber:");
				panelAsiento.add(lblTotalHaber, cc.xywh(17, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblNumero ----
				lblNumero.setText("N\u00famero:");
				panelAsiento.add(lblNumero, cc.xywh(5, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(txtNumero, cc.xy(7, 3));

				//---- lblPlanCuenta ----
				lblPlanCuenta.setText("Plan Cuenta:");
				panelAsiento.add(lblPlanCuenta, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(cmbPlanCuenta, cc.xywh(13, 3, 5, 1));

				//---- lblFecha ----
				lblFecha.setText("Fecha:");
				panelAsiento.add(lblFecha, cc.xywh(5, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(cmbFecha, cc.xy(7, 5));

				//---- lblPeriodo ----
				lblPeriodo.setText("Per\u00edodo:");
				panelAsiento.add(lblPeriodo, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(cmbPeriodo, cc.xywh(13, 5, 5, 1));

				//---- lblStatus ----
				lblStatus.setText("Estado:");
				panelAsiento.add(lblStatus, cc.xywh(5, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- cmbEstado ----
				cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
					"PRE-ASIENTO",
					"ASIENTO"
				}));
				panelAsiento.add(cmbEstado, cc.xy(7, 7));

				//---- lblConcepto ----
				lblConcepto.setText("Concepto:");
				panelAsiento.add(lblConcepto, cc.xywh(5, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(txtConcepto, cc.xywh(7, 9, 12, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- btnAgregarNota ----
				btnAgregarNota.setText("Agregar Nota");
				panelAsiento.add(btnAgregarNota, cc.xy(19, 9));

				//---- lblSubtipoAsiento ----
				lblSubtipoAsiento.setText("Subtipo Asiento:");
				panelAsiento.add(lblSubtipoAsiento, cc.xywh(13, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- lblCuenta ----
				lblCuenta.setText("Cuenta:");
				panelAsiento.add(lblCuenta, cc.xywh(5, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(txtCuenta, cc.xywh(7, 13, 11, 1));
				panelAsiento.add(btnBuscarCuenta, cc.xywh(19, 13, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

				//---- lblReferencia ----
				lblReferencia.setText("Referencia:");
				panelAsiento.add(lblReferencia, cc.xywh(5, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtReferencia ----
				txtReferencia.setToolTipText("Referencia");
				panelAsiento.add(txtReferencia, cc.xywh(7, 15, 13, 1));

				//---- lblGlosa ----
				lblGlosa.setText("Glosa:");
				panelAsiento.add(lblGlosa, cc.xywh(5, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelAsiento.add(txtGlosa, cc.xywh(7, 17, 13, 1));

				//---- lblValorDebe ----
				lblValorDebe.setText("Valor Debe:");
				panelAsiento.add(lblValorDebe, cc.xywh(11, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtValorDebe ----
				txtValorDebe.setHorizontalAlignment(SwingConstants.RIGHT);
				panelAsiento.add(txtValorDebe, cc.xywh(13, 19, 3, 1));

				//---- lblValorHaber ----
				lblValorHaber.setText("Valor Haber:");
				panelAsiento.add(lblValorHaber, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtValorHaber ----
				txtValorHaber.setHorizontalAlignment(SwingConstants.RIGHT);
				panelAsiento.add(txtValorHaber, cc.xy(19, 19));

				//======== bpAsientoPanel ========
				{
					bpAsientoPanel.setBorder(new LineBorder(Color.lightGray));
					bpAsientoPanel.setLayout(new BorderLayout());

					//---- btnMostrarPanelCentrosGasto ----
					btnMostrarPanelCentrosGasto.setText("Centros de Gasto | Ver m\u00e1s >>");
					btnMostrarPanelCentrosGasto.setHorizontalAlignment(SwingConstants.LEFT);
					bpAsientoPanel.add(btnMostrarPanelCentrosGasto, BorderLayout.NORTH);

					//======== jpCentrosGasto ========
					{
						jpCentrosGasto.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec("max(default;50dlu):grow"),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec("max(default;50dlu):grow"),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(10))
							},
							new RowSpec[] {
								new RowSpec(Sizes.DLUY2),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(Sizes.DLUY3)
							}));

						//---- lblCentroGasto ----
						lblCentroGasto.setText("Centro Gasto:");
						jpCentrosGasto.add(lblCentroGasto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						jpCentrosGasto.add(cmbCentroGasto, cc.xywh(5, 3, 3, 1));

						//---- lblEmpleado ----
						lblEmpleado.setText("Empleado:");
						jpCentrosGasto.add(lblEmpleado, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						jpCentrosGasto.add(cmbEmpleado, cc.xywh(11, 3, 3, 1));

						//---- lblDepartamento ----
						lblDepartamento.setText("Departamento:");
						jpCentrosGasto.add(lblDepartamento, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						jpCentrosGasto.add(cmbDepartamento, cc.xywh(5, 5, 3, 1));

						//---- lblLinea ----
						lblLinea.setText("L\u00ednea:");
						jpCentrosGasto.add(lblLinea, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						jpCentrosGasto.add(cmbLinea, cc.xywh(11, 5, 3, 1));

						//---- lblCliente ----
						lblCliente.setText("Cliente:");
						jpCentrosGasto.add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						jpCentrosGasto.add(cmbCliente, cc.xywh(5, 7, 3, 1));
					}
					bpAsientoPanel.add(jpCentrosGasto, BorderLayout.CENTER);
					bpAsientoPanel.add(lblDumbLabel, BorderLayout.SOUTH);
				}
				panelAsiento.add(bpAsientoPanel, cc.xywh(5, 21, 15, 1));

				//======== panel1 ========
				{
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregar ----
					btnAgregar.setToolTipText("Agrega el Asiento a la Tabla");
					panel1.add(btnAgregar, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panel1.add(btnActualizar, cc.xywh(3, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panel1.add(btnEliminar, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				panelAsiento.add(panel1, cc.xywh(5, 23, 15, 1));

				//---- lblTotalDebe ----
				lblTotalDebe.setText("Total Debe:");
				panelAsiento.add(lblTotalDebe, cc.xywh(11, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtTotalDebe ----
				txtTotalDebe.setHorizontalAlignment(SwingConstants.RIGHT);
				panelAsiento.add(txtTotalDebe, cc.xywh(13, 31, 3, 1));

				//---- txtTotalHaber ----
				txtTotalHaber.setHorizontalAlignment(SwingConstants.RIGHT);
				panelAsiento.add(txtTotalHaber, cc.xy(19, 31));
			}
			spAsiento.setViewportView(panelAsiento);
		}
		add(spAsiento, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spAsiento;
	private JPanel panelAsiento;
	private JCheckBox cbEfectivo;
	private JLabel lblTipoAsiento;
	private JLabel lblSubtipoAsiento;
	private JComboBox cmbSubtipoAsiento;
	private JComboBox cmbTipoAsiento;
	private JScrollPane scAsiento;
	private JTable tblAsiento;
	private JLabel lblTotalHaber;
	private JLabel lblNumero;
	private JFormattedTextField txtNumero;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblFecha;
	private DateComboBox cmbFecha;
	private JLabel lblPeriodo;
	private JComboBox cmbPeriodo;
	private JLabel lblStatus;
	private JComboBox cmbEstado;
	private JLabel lblConcepto;
	private JTextField txtConcepto;
	private JButton btnAgregarNota;
	private JLabel lblCuenta;
	private JTextField txtCuenta;
	private JButton btnBuscarCuenta;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblGlosa;
	private JTextField txtGlosa;
	private JLabel lblValorDebe;
	private JFormattedTextField txtValorDebe;
	private JLabel lblValorHaber;
	private JFormattedTextField txtValorHaber;
	private JPanel bpAsientoPanel;
	private JButton btnMostrarPanelCentrosGasto;
	private JPanel jpCentrosGasto;
	private JLabel lblCentroGasto;
	private JComboBox cmbCentroGasto;
	private JLabel lblEmpleado;
	private JComboBox cmbEmpleado;
	private JLabel lblDepartamento;
	private JComboBox cmbDepartamento;
	private JLabel lblLinea;
	private JComboBox cmbLinea;
	private JLabel lblCliente;
	private JComboBox cmbCliente;
	private JLabel lblDumbLabel;
	private JPanel panel1;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblTotalDebe;
	private JTextField txtTotalDebe;
	private JTextField txtTotalHaber;
	private JButton btnAnadir;
	protected JPopupMenu popup;
	protected JMenuItem menuItem;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(JTextField txtCuenta) {
		this.txtCuenta = txtCuenta;
	}

	public JTextField getTxtGlosa() {
		return txtGlosa;
	}

	public void setTxtGlosa(JTextField txtGlosa) {
		this.txtGlosa = txtGlosa;
	}

	public JFormattedTextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(JFormattedTextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextField getTxtTotalDebe() {
		return txtTotalDebe;
	}

	public void setTxtTotalDebe(JTextField txtTotalDebe) {
		this.txtTotalDebe = txtTotalDebe;
	}

	public JTextField getTxtTotalHaber() {
		return txtTotalHaber;
	}

	public void setTxtTotalHaber(JTextField txtTotalHaber) {
		this.txtTotalHaber = txtTotalHaber;
	}

	public JComboBox getCmbCentroGasto() {
		return cmbCentroGasto;
	}

	public void setCmbCentroGasto(JComboBox cmbCentroGasto) {
		this.cmbCentroGasto = cmbCentroGasto;
	}

	public JComboBox getCmbCliente() {
		return cmbCliente;
	}

	public void setCmbCliente(JComboBox cmbCliente) {
		this.cmbCliente = cmbCliente;
	}

	public JComboBox getCmbDepartamento() {
		return cmbDepartamento;
	}

	public void setCmbDepartamento(JComboBox cmbDepartamento) {
		this.cmbDepartamento = cmbDepartamento;
	}

	public JComboBox getCmbEmpleado() {
		return cmbEmpleado;
	}

	public void setCmbEmpleado(JComboBox cmbEmpleado) {
		this.cmbEmpleado = cmbEmpleado;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public JComboBox getCmbLinea() {
		return cmbLinea;
	}

	public void setCmbLinea(JComboBox cmbLinea) {
		this.cmbLinea = cmbLinea;
	}

	public JComboBox getCmbPeriodo() {
		return cmbPeriodo;
	}

	public void setCmbPeriodo(JComboBox cmbPeriodo) {
		this.cmbPeriodo = cmbPeriodo;
	}

	public JComboBox getCmbSubtipoAsiento() {
		return cmbSubtipoAsiento;
	}

	public void setCmbSubtipoAsiento(JComboBox cmbSubtipoAsiento) {
		this.cmbSubtipoAsiento = cmbSubtipoAsiento;
	}

	public JComboBox getCmbTipoAsiento() {
		return cmbTipoAsiento;
	}

	public void setCmbTipoAsiento(JComboBox cmbTipoAsiento) {
		this.cmbTipoAsiento = cmbTipoAsiento;
	}

	public JTable getTblAsiento() {
		return tblAsiento;
	}

	public void setTblAsiento(JTable tblAsiento) {
		this.tblAsiento = tblAsiento;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JFormattedTextField getTxtValorDebe() {
		return txtValorDebe;
	}

	public void setTxtValorDebe(JFormattedTextField txtValorDebe) {
		this.txtValorDebe = txtValorDebe;
	}

	public JFormattedTextField getTxtValorHaber() {
		return txtValorHaber;
	}

	public void setTxtValorHaber(JFormattedTextField txtValorHaber) {
		this.txtValorHaber = txtValorHaber;
	}
	
	public JButton getBtnBuscarCuenta() {
		return btnBuscarCuenta;
	}
	
	public void setBtnBuscarCuenta(JButton btnBuscarCuenta) {
		this.btnBuscarCuenta = btnBuscarCuenta;
	}

	public JTextField getTxtConcepto() {
		return txtConcepto;
	}

	public void setTxtConcepto(JTextField txtConcepto) {
		this.txtConcepto = txtConcepto;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	
	public JButton getBtnAnadir() {
		return btnAnadir;
	}

	public void setBtnAnadir(JButton btnAnadir) {
		this.btnAnadir = btnAnadir;
	}

	public JCheckBox getCbEfectivo() {
		return cbEfectivo;
	}

	public void setCbEfectivo(JCheckBox cbEfectivo) {
		this.cbEfectivo = cbEfectivo;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JButton getBtnMostrarPanelCentrosGasto() {
		return btnMostrarPanelCentrosGasto;
	}

	public void setBtnMostrarPanelCentrosGasto(JButton btnMostrarPanelCentrosGasto) {
		this.btnMostrarPanelCentrosGasto = btnMostrarPanelCentrosGasto;
	}

	public JPanel getBpAsientoPanel() {
		return bpAsientoPanel;
	}

	public void setBpAsientoPanel(JPanel bpAsientoPanel) {
		this.bpAsientoPanel = bpAsientoPanel;
	}

	public JPanel getJpCentrosGasto() {
		return jpCentrosGasto;
	}

	public void setJpCentrosGasto(JPanel jpCentrosGasto) {
		this.jpCentrosGasto = jpCentrosGasto;
	}

	public JButton getBtnAgregarNota() {
		return btnAgregarNota;
	}

	public void setBtnAgregarNota(JButton btnAgregarNota) {
		this.btnAgregarNota = btnAgregarNota;
	}
}
