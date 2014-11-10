package com.spirit.general.gui.panel;
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

public abstract class JPTipoIdentificacion extends MantenimientoModelImpl {
	public JPTipoIdentificacion() {
		initComponents();
		setName("Tipo de Identificacion");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblCodigoSri = new JLabel();
		txtCodigoSri = new JTextField();
		spTblTipoIdentificacion = new JScrollPane();
		tblTipoIdentificacion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;100dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
		lblCodigo.setToolTipText("C\u00f3digo descriptivo para el Tipo de Identificaci\u00f3n");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo para el Tipo de Identificaci\u00f3n");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Descripci\u00f3n:");
		lblNombre.setToolTipText("Descripci\u00f3n del Tipo de Identificaci\u00f3n");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Descripci\u00f3n del Tipo de Identificaci\u00f3n");
		add(txtNombre, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCodigoSri ----
		lblCodigoSri.setText("C\u00f3digo SRI:");
		add(lblCodigoSri, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigoSri, cc.xy(5, 7));

		//======== spTblTipoIdentificacion ========
		{
			
			//---- tblTipoIdentificacion ----
			tblTipoIdentificacion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Descripci\u00f3n", "C\u00f3digo SRI"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoIdentificacion.setViewportView(tblTipoIdentificacion);
		}
		add(spTblTipoIdentificacion, cc.xywh(3, 11, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblCodigoSri;
	private JTextField txtCodigoSri;
	private JScrollPane spTblTipoIdentificacion;
	private JTable tblTipoIdentificacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblTipoIdentificacion() {
		return tblTipoIdentificacion;
	}

	public void setTblTipoIdentificacion(JTable tblTipoIdentificacion) {
		this.tblTipoIdentificacion = tblTipoIdentificacion;
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

	public JTextField getTxtCodigoSri() {
		return txtCodigoSri;
	}

	public void setTxtCodigoSri(JTextField txtCodigoSri) {
		this.txtCodigoSri = txtCodigoSri;
	}
}
