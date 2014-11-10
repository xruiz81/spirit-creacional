package com.spirit.crm.gui.panel;

import javax.swing.JComboBox;
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



/**
 * @author Antonio Seiler
 */
public abstract class JPCorporacion extends MantenimientoModelImpl {
	public JPCorporacion() {
		setName("Corporacion");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		txtCodigo = new JTextField();
		lblCodigo = new JLabel();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblRepresentante = new JLabel();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		txtRepresentante = new JTextField();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		spTblCorporacion = new JScrollPane();
		tblCorporacion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(60), FormSpec.DEFAULT_GROW),
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
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtNombre, cc.xywh(5, 5, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblRepresentante ----
		lblRepresentante.setText("Representante:");
		lblRepresentante.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblRepresentante, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblFechaCreacion ----
		lblFechaCreacion.setText("Fecha de Creaci\u00f3n:");
		lblFechaCreacion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFechaCreacion, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtFechaCreacion ----
		txtFechaCreacion.setEditable(false);
		add(txtFechaCreacion, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(txtRepresentante, cc.xywh(5, 7, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEstado, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbEstado, cc.xywh(13, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblCorporacion ========
		{
			
			//---- tblCorporacion ----
			tblCorporacion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Representante", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblCorporacion.getTableHeader().setReorderingAllowed(false);
			spTblCorporacion.setViewportView(tblCorporacion);
		}
		add(spTblCorporacion, cc.xywh(3, 13, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblRepresentante;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JTextField txtRepresentante;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JScrollPane spTblCorporacion;
	private JTable tblCorporacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JTable getTblCorporacion() {
		return tblCorporacion;
	}

	public void setTblCorporacion(JTable tblCorporacion) {
		this.tblCorporacion = tblCorporacion;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public void setTxtFechaCreacion(JTextField txtFechaCreacion) {
		this.txtFechaCreacion = txtFechaCreacion;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtRepresentante() {
		return txtRepresentante;
	}

	public void setTxtRepresentante(JTextField txtRepresentante) {
		this.txtRepresentante = txtRepresentante;
	}
	
}
