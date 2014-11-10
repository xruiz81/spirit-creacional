package com.spirit.facturacion.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;

public abstract class JPPreimpresoFacturas extends MantenimientoModelImpl {
	public JPPreimpresoFacturas() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		lblFecha = new JLabel();
		cmbFecha = new DateComboBox();
		btnBuscar = new JButton();
		spTblPreimpresos = new JScrollPane();
		tblPreimpresos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Preimpresos");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblDocumento ----
		lblDocumento.setText("Documento");
		add(lblDocumento, cc.xy(3, 3));
		add(cmbDocumento, cc.xywh(5, 3, 3, 1));

		//---- lblFecha ----
		lblFecha.setText("Fecha");
		add(lblFecha, cc.xy(3, 5));
		add(cmbFecha, cc.xy(5, 5));

		//---- btnBuscar ----
		btnBuscar.setText("");
		add(btnBuscar, cc.xy(11, 5));

		//======== spTblPreimpresos ========
		{
			
			//---- tblPreimpresos ----
			tblPreimpresos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"#", "Cliente", "F. Factura", "Observaci\u00f3n", "Total", "Preimpreso"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, true
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblPreimpresos.setViewportView(tblPreimpresos);
		}
		add(spTblPreimpresos, cc.xywh(3, 7, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		//TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		//tblPreimpresos.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendederHorizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		tblPreimpresos.getColumnModel().getColumn(0).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblPreimpresos.getColumnModel().getColumn(2).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblPreimpresos.getColumnModel().getColumn(4).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblPreimpresos.getColumnModel().getColumn(5).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		btnBuscar.setToolTipText("Consultar facturas");
		btnBuscar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JLabel lblFecha;
	private DateComboBox cmbFecha;
	private JButton btnBuscar;
	private JScrollPane spTblPreimpresos;
	private JTable tblPreimpresos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public JTable getTblPreimpresos() {
		return tblPreimpresos;
	}

	public void setTblPreimpresos(JTable tblPreimpresos) {
		this.tblPreimpresos = tblPreimpresos;
	}
}
