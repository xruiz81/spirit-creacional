package com.spirit.cartera.gui.panel;

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
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPFormaPago extends MantenimientoModelImpl {
	public JPFormaPago() {
		initComponents();
		setName("Forma de Pago");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblDiasInicio = new JLabel();
		txtDiasInicio = new JTextField();
		lblDiasFin = new JLabel();
		txtDiasFin = new JTextField();
		lblDescuentoVenta = new JLabel();
		txtDescuentoVenta = new JTextField();
		lblDescuentoCartera = new JLabel();
		txtDescuentoCartera = new JTextField();
		lblInteres = new JLabel();
		txtInteres = new JTextField();
		spTblFormaPago = new JScrollPane();
		tblFormaPago = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 11, 1));

		//---- lblDiasInicio ----
		lblDiasInicio.setText("D\u00edas Inicio:");
		add(lblDiasInicio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtDiasInicio ----
		txtDiasInicio.setHorizontalAlignment(JTextField.RIGHT);
		add(txtDiasInicio, cc.xy(5, 7));

		//---- lblDiasFin ----
		lblDiasFin.setText("D\u00edas Fin:");
		add(lblDiasFin, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtDiasFin ----
		txtDiasFin.setHorizontalAlignment(JTextField.RIGHT);
		add(txtDiasFin, cc.xy(13, 7));

		//---- lblDescuentoVenta ----
		lblDescuentoVenta.setText("Descuento Venta [%]:");
		add(lblDescuentoVenta, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtDescuentoVenta ----
		txtDescuentoVenta.setHorizontalAlignment(JTextField.RIGHT);
		add(txtDescuentoVenta, cc.xy(5, 9));

		//---- lblDescuentoCartera ----
		lblDescuentoCartera.setText("Descuento Cartera [%]:");
		add(lblDescuentoCartera, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtDescuentoCartera ----
		txtDescuentoCartera.setHorizontalAlignment(JTextField.RIGHT);
		add(txtDescuentoCartera, cc.xy(13, 9));

		//---- lblInteres ----
		lblInteres.setText("Inter\u00e9s[%]:");
		add(lblInteres, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtInteres ----
		txtInteres.setHorizontalAlignment(JTextField.RIGHT);
		add(txtInteres, cc.xy(5, 11));

		//======== spTblFormaPago ========
		{
			
			//---- tblFormaPago ----
			tblFormaPago.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "D\u00edas Inicio", "D\u00edas Fin", "Dsct. Venta", "Dsct. Cartera", "Inter\u00e9s"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblFormaPago.setViewportView(tblFormaPago);
		}
		add(spTblFormaPago, cc.xywh(3, 15, 15, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblFormaPago.getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
		tblFormaPago.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
		tblFormaPago.getColumnModel().getColumn(4).setCellRenderer(tableCellRenderer);
		tblFormaPago.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblFormaPago.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblDiasInicio;
	private JTextField txtDiasInicio;
	private JLabel lblDiasFin;
	private JTextField txtDiasFin;
	private JLabel lblDescuentoVenta;
	private JTextField txtDescuentoVenta;
	private JLabel lblDescuentoCartera;
	private JTextField txtDescuentoCartera;
	private JLabel lblInteres;
	private JTextField txtInteres;
	private JScrollPane spTblFormaPago;
	private JTable tblFormaPago;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JScrollPane getSpTblFormaPago() {
		return spTblFormaPago;
	}

	public void setSpTblFormaPago(JScrollPane spTblFormaPago) {
		this.spTblFormaPago = spTblFormaPago;
	}

	public JTable getTblFormaPago() {
		return tblFormaPago;
	}

	public void setTblFormaPago(JTable tblFormaPago) {
		this.tblFormaPago = tblFormaPago;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtDescuentoCartera() {
		return txtDescuentoCartera;
	}

	public void setTxtDescuentoCartera(JTextField txtDescuentoCartera) {
		this.txtDescuentoCartera = txtDescuentoCartera;
	}

	public JTextField getTxtDescuentoVenta() {
		return txtDescuentoVenta;
	}

	public void setTxtDescuentoVenta(JTextField txtDescuentoVenta) {
		this.txtDescuentoVenta = txtDescuentoVenta;
	}

	public JTextField getTxtDiasFin() {
		return txtDiasFin;
	}

	public void setTxtDiasFin(JTextField txtDiasFin) {
		this.txtDiasFin = txtDiasFin;
	}

	public JTextField getTxtDiasInicio() {
		return txtDiasInicio;
	}

	public void setTxtDiasInicio(JTextField txtDiasInicio) {
		this.txtDiasInicio = txtDiasInicio;
	}

	public JTextField getTxtInteres() {
		return txtInteres;
	}

	public void setTxtInteres(JTextField txtInteres) {
		this.txtInteres = txtInteres;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
}
