package com.spirit.cartera.gui.panel;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.factories.DefaultComponentFactory;
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

public abstract class JPRetencionProveedor extends SpiritModelImpl {
	public JPRetencionProveedor() {
		setName("Retenciones Proveedor");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		spRetencionesProveedor = new JScrollPane();
		panelRetencionesProveedor = new JPanel();
		fsListadoCompras = compFactory.createSeparator("Compras:");
		spCompras = new JScrollPane();
		tblCompras = new JTable();
		fsRetenciones = compFactory.createSeparator("Retenciones:");
		lblPreimpreso = new JLabel();
		txtEstablecimiento = new JTextField();
		txtPuntoEmision = new JTextField();
		lblAutorizacion = new JLabel();
		txtAutorizacion = new JTextField();
		txtSecuencial = new JTextField();
		cmbOficina = new JComboBox();
		lblFechaEmision = new JLabel();
		cmbFechaEmision = new DateComboBox();
		panel1 = new JPanel();
		btnActualizarTodos = new JButton();
		btnActualizar = new JButton();
		spRetenciones = new JScrollPane();
		tblRetenciones = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== spRetencionesProveedor ========
		{

			//======== panelRetencionesProveedor ========
			{
				panelRetencionesProveedor.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(10))
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY6),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY4),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY4),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY4),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(60)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));
				panelRetencionesProveedor.add(fsListadoCompras, cc.xywh(3, 3, 13, 1));

				//======== spCompras ========
				{

					//---- tblCompras ----
					tblCompras.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00f3digo", "Fecha", "Proveedor", "# Factura", "Autorizaci\u00f3n"
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
					tblCompras.setPreferredScrollableViewportSize(new Dimension(450, 150));
					spCompras.setViewportView(tblCompras);
				}
				panelRetencionesProveedor.add(spCompras, cc.xywh(3, 7, 13, 5));
				panelRetencionesProveedor.add(fsRetenciones, cc.xywh(3, 15, 13, 1));

				//---- lblPreimpreso ----
				lblPreimpreso.setText("No. de Serie y Secuencial:");
				panelRetencionesProveedor.add(lblPreimpreso, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelRetencionesProveedor.add(txtEstablecimiento, cc.xy(5, 19));
				panelRetencionesProveedor.add(txtPuntoEmision, cc.xy(7, 19));

				//---- lblAutorizacion ----
				lblAutorizacion.setText("No. de Autorizaci\u00f3n:");
				panelRetencionesProveedor.add(lblAutorizacion, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelRetencionesProveedor.add(txtAutorizacion, cc.xywh(5, 21, 3, 1));
				panelRetencionesProveedor.add(txtSecuencial, cc.xy(9, 19));
				panelRetencionesProveedor.add(cmbOficina, cc.xy(13, 19));

				//---- lblFechaEmision ----
				lblFechaEmision.setText("Fecha de Emisi\u00f3n:");
				panelRetencionesProveedor.add(lblFechaEmision, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelRetencionesProveedor.add(cmbFechaEmision, cc.xywh(5, 23, 5, 1));

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

					//---- btnActualizarTodos ----
					btnActualizarTodos.setText("UA");
					panel1.add(btnActualizarTodos, cc.xy(3, 1));

					//---- btnActualizar ----
					btnActualizar.setText("U");
					panel1.add(btnActualizar, cc.xy(1, 1));
				}
				panelRetencionesProveedor.add(panel1, cc.xywh(3, 27, 13, 1));

				//======== spRetenciones ========
				{

					//---- tblRetenciones ----
					tblRetenciones.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Ejercicio Fiscal", "Preimpreso", "Autorizaci\u00f3n", "Base Imponible", "Impuesto", "Cod. Impuesto", "% Retenci\u00f3n", "Valor Retenido", "ID Cuenta"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblRetenciones.setPreferredScrollableViewportSize(new Dimension(450, 180));
					spRetenciones.setViewportView(tblRetenciones);
				}
				panelRetencionesProveedor.add(spRetenciones, cc.xywh(3, 29, 13, 5));
			}
			spRetencionesProveedor.setViewportView(panelRetencionesProveedor);
		}
		add(spRetencionesProveedor, cc.xywh(3, 3, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spRetencionesProveedor;
	private JPanel panelRetencionesProveedor;
	private JComponent fsListadoCompras;
	private JScrollPane spCompras;
	private JTable tblCompras;
	private JComponent fsRetenciones;
	private JLabel lblPreimpreso;
	private JTextField txtEstablecimiento;
	private JTextField txtPuntoEmision;
	private JLabel lblAutorizacion;
	private JTextField txtAutorizacion;
	private JTextField txtSecuencial;
	private JComboBox cmbOficina;
	private JLabel lblFechaEmision;
	private DateComboBox cmbFechaEmision;
	private JPanel panel1;
	private JButton btnActualizarTodos;
	private JButton btnActualizar;
	private JScrollPane spRetenciones;
	private JTable tblRetenciones;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JTable getTblCompras() {
		return tblCompras;
	}

	public void setTblCompras(JTable tblCompras) {
		this.tblCompras = tblCompras;
	}

	public JTable getTblRetenciones() {
		return tblRetenciones;
	}

	public void setTblRetenciones(JTable tblRetenciones) {
		this.tblRetenciones = tblRetenciones;
	}

	public JTextField getTxtAutorizacion() {
		return txtAutorizacion;
	}

	public void setTxtAutorizacion(JTextField txtAutorizacion) {
		this.txtAutorizacion = txtAutorizacion;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public JButton getBtnActualizarTodos() {
		return btnActualizarTodos;
	}

	public void setBtnActualizarTodos(JButton btnActualizarTodos) {
		this.btnActualizarTodos = btnActualizarTodos;
	}

	public JTextField getTxtEstablecimiento() {
		return txtEstablecimiento;
	}

	public void setTxtEstablecimiento(JTextField txtEstablecimiento) {
		this.txtEstablecimiento = txtEstablecimiento;
	}

	public JTextField getTxtPuntoEmision() {
		return txtPuntoEmision;
	}

	public void setTxtPuntoEmision(JTextField txtPuntoEmision) {
		this.txtPuntoEmision = txtPuntoEmision;
	}

	public JTextField getTxtSecuencial() {
		return txtSecuencial;
	}

	public void setTxtSecuencial(JTextField txtSecuencial) {
		this.txtSecuencial = txtSecuencial;
	}

	public DateComboBox getCmbFechaEmision() {
		return cmbFechaEmision;
	}

	public void setCmbFechaEmision(DateComboBox cmbFechaEmision) {
		this.cmbFechaEmision = cmbFechaEmision;
	}
}
