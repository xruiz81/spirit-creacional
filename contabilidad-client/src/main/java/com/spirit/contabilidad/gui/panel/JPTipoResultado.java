package com.spirit.contabilidad.gui.panel;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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



/**
 * @author Antonio Seiler
 */
public abstract class JPTipoResultado extends MantenimientoModelImpl {
	public JPTipoResultado() {
		initComponents();
		setName("Tipos de Resultado");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JFormattedTextField();
		lblNombre = new JLabel();
		txtNombre = new JFormattedTextField();
		lblOrden = new JLabel();
		txtOrden = new JFormattedTextField();
		spTblTipoResultado = new JScrollPane();
		tblTipoResultado = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(85)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(85)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
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
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));

		//---- lblOrden ----
		lblOrden.setText("Orden:");
		lblOrden.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblOrden, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtOrden, cc.xy(5, 7));

		//======== spTblTipoResultado ========
		{
			
			//---- tblTipoResultado ----
			tblTipoResultado.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Orden"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblTipoResultado.getTableHeader().setReorderingAllowed(false);
			spTblTipoResultado.setViewportView(tblTipoResultado);
		}
		add(spTblTipoResultado, cc.xywh(3, 11, 10, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JFormattedTextField txtCodigo;
	private JLabel lblNombre;
	private JFormattedTextField txtNombre;
	private JLabel lblOrden;
	private JFormattedTextField txtOrden;
	private JScrollPane spTblTipoResultado;
	private JTable tblTipoResultado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblTipoResultado() {
		return tblTipoResultado;
	}

	public void setTblTipoResultado(JTable tblTipoResultado) {
		this.tblTipoResultado = tblTipoResultado;
	}

	public JFormattedTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JFormattedTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JFormattedTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JFormattedTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JFormattedTextField getTxtOrden() {
		return txtOrden;
	}

	public void setTxtOrden(JFormattedTextField txtOrden) {
		this.txtOrden = txtOrden;
	}
	
}
