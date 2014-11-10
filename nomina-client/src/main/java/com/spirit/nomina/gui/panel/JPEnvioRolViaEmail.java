package com.spirit.nomina.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
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

public abstract class JPEnvioRolViaEmail extends SpiritModelImpl {
	/**
	 * 
	 */
	 
	public JPEnvioRolViaEmail() {
		setName("Rol Envio Mail");		
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblTipoRol = new JLabel();
		cmbTipoRol = new JComboBox();
		lblMonth = new JLabel();
		cmbMonth = new JComboBox();
		lblYear = new JLabel();
		cmbYear = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		btnConsultar = new JButton();
		splitPnPagoRol = new JSplitPane();
		panelRolPago = new JPanel();
		gfsRubros = compFactory.createSeparator("Rol de Pago");
		spTblRolPago = new JScrollPane();
		tblRolPago = new JTable();
		panelDetalleRolPago = new JPanel();
		btnSeleccionarTodos = new JButton();
		btnEnviarRolMail = new JButton();
		gfsRubros2 = compFactory.createSeparator("Detalle Rol de Pago");
		spDetalleRolPago = new JScrollPane();
		tblDetalleRolPago = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setMinimumSize(new Dimension(620, 350));
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
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
				new RowSpec("fill:min(default;250dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo de rol:");
		add(lblTipoRol, cc.xy(3, 3));
		add(cmbTipoRol, cc.xy(5, 3));

		//---- lblMonth ----
		lblMonth.setText("Mes:");
		add(lblMonth, cc.xy(7, 3));

		//---- cmbMonth ----
		cmbMonth.setModel(new DefaultComboBoxModel(new String[] {
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
		add(cmbMonth, cc.xy(9, 3));

		//---- lblYear ----
		lblYear.setText("A\u00f1o:");
		add(lblYear, cc.xy(11, 3));
		add(cmbYear, cc.xy(13, 3));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xy(15, 3));
		add(cmbEstado, cc.xy(17, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(19, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== splitPnPagoRol ========
		{
			splitPnPagoRol.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPnPagoRol.setBorder(null);

			//======== panelRolPago ========
			{
				panelRolPago.setMinimumSize(new Dimension(70, 120));
				panelRolPago.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				panelRolPago.add(gfsRubros, cc.xy(1, 1));

				//======== spTblRolPago ========
				{

					//---- tblRolPago ----
					tblRolPago.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Tipo de Rol", "Tipo de Contrato", "Mes", "A\u00f1o", "Estado"
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
					tblRolPago.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					spTblRolPago.setViewportView(tblRolPago);
				}
				panelRolPago.add(spTblRolPago, cc.xywh(1, 3, 1, 3));
			}
			splitPnPagoRol.setTopComponent(panelRolPago);

			//======== panelDetalleRolPago ========
			{
				panelDetalleRolPago.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
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
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));

				//---- btnSeleccionarTodos ----
				btnSeleccionarTodos.setText("Seleccionar Todos");
				panelDetalleRolPago.add(btnSeleccionarTodos, cc.xy(1, 3));

				//---- btnEnviarRolMail ----
				btnEnviarRolMail.setText("Enviar por e-mail");
				panelDetalleRolPago.add(btnEnviarRolMail, cc.xywh(5, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				panelDetalleRolPago.add(gfsRubros2, cc.xywh(1, 5, 5, 1));

				//======== spDetalleRolPago ========
				{

					//---- tblDetalleRolPago ----
					tblDetalleRolPago.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							" ", "Empleado", "E-mail", "Ingresos", "Egresos", "Neto"
						}
					) {
						Class[] columnTypes = new Class[] {
							Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, false, false, false
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
						TableColumnModel cm = tblDetalleRolPago.getColumnModel();
						cm.getColumn(0).setPreferredWidth(10);
						cm.getColumn(1).setPreferredWidth(200);
						cm.getColumn(2).setPreferredWidth(100);
					}
					spDetalleRolPago.setViewportView(tblDetalleRolPago);
				}
				panelDetalleRolPago.add(spDetalleRolPago, cc.xywh(1, 7, 5, 5));
			}
			splitPnPagoRol.setBottomComponent(panelDetalleRolPago);
		}
		add(splitPnPagoRol, cc.xywh(3, 7, 19, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblTipoRol;
	private JComboBox cmbTipoRol;
	private JLabel lblMonth;
	private JComboBox cmbMonth;
	private JLabel lblYear;
	private JComboBox cmbYear;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JButton btnConsultar;
	private JSplitPane splitPnPagoRol;
	private JPanel panelRolPago;
	private JComponent gfsRubros;
	private JScrollPane spTblRolPago;
	private JTable tblRolPago;
	private JPanel panelDetalleRolPago;
	private JButton btnSeleccionarTodos;
	private JButton btnEnviarRolMail;
	private JComponent gfsRubros2;
	private JScrollPane spDetalleRolPago;
	private JTable tblDetalleRolPago;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JComboBox getCmbTipoRol() {
		return cmbTipoRol;
	}

	public void setCmbTipoRol(JComboBox cmbTipoRol) {
		this.cmbTipoRol = cmbTipoRol;
	}

	public JComboBox getCmbMonth() {
		return cmbMonth;
	}

	public void setCmbMonth(JComboBox cmbMonth) {
		this.cmbMonth = cmbMonth;
	}

	public JComboBox getCmbYear() {
		return cmbYear;
	}

	public void setCmbYear(JComboBox cmbYear) {
		this.cmbYear = cmbYear;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JTable getTblRolPago() {
		return tblRolPago;
	}

	public void setTblRolPago(JTable tblRolPago) {
		this.tblRolPago = tblRolPago;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public void setBtnSeleccionarTodos(JButton btnSeleccionarTodos) {
		this.btnSeleccionarTodos = btnSeleccionarTodos;
	}

	public JButton getBtnEnviarRolMail() {
		return btnEnviarRolMail;
	}

	public void setBtnEnviarRolMail(JButton btnEnviarRolMail) {
		this.btnEnviarRolMail = btnEnviarRolMail;
	}

	public JTable getTblDetalleRolPago() {
		return tblDetalleRolPago;
	}

	public void setTblDetalleRolPago(JTable tblDetalleRolPago) {
		this.tblDetalleRolPago = tblDetalleRolPago;
	}
}
