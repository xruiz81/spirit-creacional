package com.spirit.sri.gui.panel;
import javax.swing.JButton;
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
/*
 * Created by JFormDesigner on Tue Nov 10 12:43:48 COT 2009
 */



/**
 * @author Antonio Seiler
 */
public abstract class JPTipoComprobante extends MantenimientoModelImpl {
	public JPTipoComprobante() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblAnulacion = new JLabel();
		txtAnulacion = new JTextField();
		btnBuscarAnulacion = new JButton();
		spTblTipoComprobante = new JScrollPane();
		tblTipoComprobante = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Tipo de Comprobante");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setToolTipText("Nombre del Centro de Gasto");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre del Centro de Gasto");
		add(txtNombre, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblAnulacion ----
		lblAnulacion.setText("Anulacion: ");
		add(lblAnulacion, cc.xy(3, 7));

		//---- txtAnulacion ----
		txtAnulacion.setEnabled(false);
		add(txtAnulacion, cc.xywh(5, 7, 3, 1));

		//---- btnBuscarAnulacion ----
		btnBuscarAnulacion.setText("B");
		add(btnBuscarAnulacion, cc.xy(9, 7));

		//======== spTblTipoComprobante ========
		{
			
			//---- tblTipoComprobante ----
			tblTipoComprobante.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Comprobante de Anulaci\u00f3n"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoComprobante.setViewportView(tblTipoComprobante);
		}
		add(spTblTipoComprobante, cc.xywh(3, 11, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblAnulacion;
	private JTextField txtAnulacion;
	private JButton btnBuscarAnulacion;
	private JScrollPane spTblTipoComprobante;
	private JTable tblTipoComprobante;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JLabel getLblAnulacion() {
		return lblAnulacion;
	}

	public JTextField getTxtAnulacion() {
		return txtAnulacion;
	}

	public JButton getBtnBuscarAnulacion() {
		return btnBuscarAnulacion;
	}

	public JScrollPane getSpTblTipoComprobante() {
		return spTblTipoComprobante;
	}

	public JTable getTblTipoComprobante() {
		return tblTipoComprobante;
	}
}
