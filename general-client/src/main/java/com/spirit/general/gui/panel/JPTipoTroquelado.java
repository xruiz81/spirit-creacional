package com.spirit.general.gui.panel;
import javax.swing.DefaultComboBoxModel;
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
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPTipoTroquelado extends MantenimientoModelImpl {
	public JPTipoTroquelado() {
		initComponents();
		setName("Tipo Troquelado");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		txtCodigo = new JTextField();
		lblSeccionesHoja = new JLabel();
		cmbSeccionesHoja = new JComboBox();
		spTblTipoTroquelado = new JScrollPane();
		tblTipoTroquelado = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;50dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(200)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
			}));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 11}});

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo descriptivo del pa\u00eds");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n:");
		lblDescripcion.setToolTipText("Nombre del pa\u00eds");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- txtDescripcion ----
		txtDescripcion.setToolTipText("Nombre del pa\u00eds");
		add(txtDescripcion, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo del pa\u00eds");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblSeccionesHoja ----
		lblSeccionesHoja.setText("Secciones por hoja:");
		add(lblSeccionesHoja, cc.xy(3, 7));

		//---- cmbSeccionesHoja ----
		cmbSeccionesHoja.setModel(new DefaultComboBoxModel(new String[] {
			"1",
			"2",
			"4"
		}));
		cmbSeccionesHoja.setEditable(false);
		add(cmbSeccionesHoja, cc.xy(5, 7));

		//======== spTblTipoTroquelado ========
		{
			
			//---- tblTipoTroquelado ----
			tblTipoTroquelado.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Descripci\u00f3n", "# Secciones"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoTroquelado.setViewportView(tblTipoTroquelado);
		}
		add(spTblTipoTroquelado, cc.xywh(3, 11, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		getTblTipoTroquelado().getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JTextField txtCodigo;
	private JLabel lblSeccionesHoja;
	private JComboBox cmbSeccionesHoja;
	private JScrollPane spTblTipoTroquelado;
	private JTable tblTipoTroquelado;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblTipoTroquelado() {
		return tblTipoTroquelado;
	}

	public void setTblTipoTroquelado(JTable tblTipoTroquelado) {
		this.tblTipoTroquelado = tblTipoTroquelado;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JComboBox getCmbSeccionesHoja() {
		return cmbSeccionesHoja;
	}

	public void setCmbSeccionesHoja(JComboBox cmbSeccionesHoja) {
		this.cmbSeccionesHoja = cmbSeccionesHoja;
	}
}
