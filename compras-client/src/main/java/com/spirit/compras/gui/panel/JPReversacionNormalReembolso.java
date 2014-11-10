package com.spirit.compras.gui.panel;
import javax.swing.JButton;
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
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPReversacionNormalReembolso extends MantenimientoModelImpl {
	public JPReversacionNormalReembolso() {
		initComponents();
		setName("Normal a Reembolso");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spTblReversacionNormalReembolso = new JScrollPane();
		tblReversacionNormalReembolso = new JTable();
		btnProcesar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spTblReversacionNormalReembolso ========
		{
			
			//---- tblReversacionNormalReembolso ----
			tblReversacionNormalReembolso.setModel(new DefaultTableModel(
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
			tblReversacionNormalReembolso.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			spTblReversacionNormalReembolso.setViewportView(tblReversacionNormalReembolso);
		}
		add(spTblReversacionNormalReembolso, cc.xywh(3, 3, 5, 5));

		//---- btnProcesar ----
		btnProcesar.setText("Procesar");
		btnProcesar.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnProcesar, cc.xy(7, 9));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnProcesar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/rollback.png"));
		btnProcesar.setToolTipText("Reversar Compras por Reembolso");
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblReversacionNormalReembolso.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		tblReversacionNormalReembolso.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblReversacionNormalReembolso.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblReversacionNormalReembolso.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spTblReversacionNormalReembolso;
	private JTable tblReversacionNormalReembolso;
	private JButton btnProcesar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public void setBtnProcesar(JButton btnProcesar) {
		this.btnProcesar = btnProcesar;
	}

	public JTable getTblReversacionNormalReembolso() {
		return tblReversacionNormalReembolso;
	}

	public void setTblReversacionNormalReembolso(
			JTable tblReversacionNormalReembolso) {
		this.tblReversacionNormalReembolso = tblReversacionNormalReembolso;
	}
}
