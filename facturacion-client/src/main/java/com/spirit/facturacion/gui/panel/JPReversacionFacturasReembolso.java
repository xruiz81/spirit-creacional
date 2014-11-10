package com.spirit.facturacion.gui.panel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPReversacionFacturasReembolso extends MantenimientoModelImpl {
	public JPReversacionFacturasReembolso() {
		initComponents();
		setName("Reversación de Facturas de Reembolso");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblDesde = new JLabel();
		cmbDesde = new DateComboBox();
		lblHasta = new JLabel();
		cmbHasta = new DateComboBox();
		btnConsultar = new JButton();
		spTblReversacionFacturasReembolso = new JScrollPane();
		tblReversacionFacturasReembolso = new JTable();
		btnProcesar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblDesde ----
		lblDesde.setText("Desde:");
		add(lblDesde, cc.xy(3, 3));

		//---- cmbDesde ----
		cmbDesde.setEditable(false);
		add(cmbDesde, cc.xy(5, 3));

		//---- lblHasta ----
		lblHasta.setText("Hasta:");
		add(lblHasta, cc.xy(7, 3));

		//---- cmbHasta ----
		cmbHasta.setEditable(false);
		add(cmbHasta, cc.xy(9, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		btnConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnConsultar, cc.xywh(11, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== spTblReversacionFacturasReembolso ========
		{
			
			//---- tblReversacionFacturasReembolso ----
			tblReversacionFacturasReembolso.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"No.", "# Factura", "Fecha", "Descripci\u00f3n", "Subtotal", "IVA", "Total", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblReversacionFacturasReembolso.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			spTblReversacionFacturasReembolso.setViewportView(tblReversacionFacturasReembolso);
		}
		add(spTblReversacionFacturasReembolso, cc.xywh(3, 7, 11, 5));

		//---- btnProcesar ----
		btnProcesar.setText("Procesar");
		btnProcesar.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnProcesar, cc.xy(13, 13));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		btnConsultar.setToolTipText("Consultar");
		btnProcesar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/rollback.png"));
		btnProcesar.setToolTipText("Reversar Compras por Reembolso");
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblReversacionFacturasReembolso.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		tblReversacionFacturasReembolso.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblReversacionFacturasReembolso.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblReversacionFacturasReembolso.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblDesde;
	private DateComboBox cmbDesde;
	private JLabel lblHasta;
	private DateComboBox cmbHasta;
	private JButton btnConsultar;
	private JScrollPane spTblReversacionFacturasReembolso;
	private JTable tblReversacionFacturasReembolso;
	private JButton btnProcesar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public void setBtnProcesar(JButton btnProcesar) {
		this.btnProcesar = btnProcesar;
	}

	public DateComboBox getCmbDesde() {
		return cmbDesde;
	}

	public void setCmbDesde(DateComboBox cmbDesde) {
		this.cmbDesde = cmbDesde;
	}

	public DateComboBox getCmbHasta() {
		return cmbHasta;
	}

	public void setCmbHasta(DateComboBox cmbHasta) {
		this.cmbHasta = cmbHasta;
	}

	public JTable getTblReversacionFacturasReembolso() {
		return tblReversacionFacturasReembolso;
	}

	public void setTblReversacionFacturasReembolso(
			JTable tblReversacionFacturasReembolso) {
		this.tblReversacionFacturasReembolso = tblReversacionFacturasReembolso;
	}
}
