package com.spirit.nomina.gui.panel;
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

public abstract class JPRolPagoDocumento extends MantenimientoModelImpl {
	public JPRolPagoDocumento() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoContrato = new JLabel();
		cmbTipoContrato = new JComboBox();
		label1 = new JLabel();
		cmbTipoRol = new JComboBox();
		lblNombre = new JLabel();
		cmbDocumento = new JComboBox();
		lblUsuarioCreador = new JLabel();
		txtUsuarioCreador = new JTextField();
		lblFechaCreacion = new JLabel();
		lblUsuarioActual = new JLabel();
		txtFechaCreacion = new JTextField();
		txtUsuarioActualizador = new JTextField();
		txtFechaActualizacion = new JTextField();
		lblFechaActualizacion = new JLabel();
		spTblTipoContrato = new JScrollPane();
		tblTipoContrato = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Documento por Rol de Pago");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(130)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(90)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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
				new RowSpec(Sizes.dluY(12))
			}));
		((FormLayout)getLayout()).setColumnGroups(new int[][] {{1, 17}});

		//---- lblTipoContrato ----
		lblTipoContrato.setText("Tipo de Contrato: ");
		lblTipoContrato.setToolTipText("C\u00f3digo Descriptivo para el Centro de Gasto");
		lblTipoContrato.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoContrato, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbTipoContrato, cc.xy(5, 3));

		//---- label1 ----
		label1.setText("Tipo de Rol: ");
		add(label1, cc.xy(3, 5));
		add(cmbTipoRol, cc.xy(5, 5));

		//---- lblNombre ----
		lblNombre.setText("Documento: ");
		lblNombre.setToolTipText("Nombre del Centro de Gasto");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbDocumento, cc.xywh(5, 7, 3, 1));

		//---- lblUsuarioCreador ----
		lblUsuarioCreador.setText("Usuario Creador: ");
		add(lblUsuarioCreador, cc.xy(3, 9));

		//---- txtUsuarioCreador ----
		txtUsuarioCreador.setEditable(false);
		txtUsuarioCreador.setEnabled(false);
		add(txtUsuarioCreador, cc.xywh(5, 9, 3, 1));

		//---- lblFechaCreacion ----
		lblFechaCreacion.setText("Fecha Creaci\u00f3n: ");
		add(lblFechaCreacion, cc.xy(11, 9));

		//---- lblUsuarioActual ----
		lblUsuarioActual.setText("Usuario Actual.: ");
		add(lblUsuarioActual, cc.xy(3, 11));

		//---- txtFechaCreacion ----
		txtFechaCreacion.setEditable(false);
		txtFechaCreacion.setEnabled(false);
		add(txtFechaCreacion, cc.xy(13, 9));

		//---- txtUsuarioActualizador ----
		txtUsuarioActualizador.setEnabled(false);
		txtUsuarioActualizador.setEditable(false);
		add(txtUsuarioActualizador, cc.xywh(5, 11, 3, 1));

		//---- txtFechaActualizacion ----
		txtFechaActualizacion.setEditable(false);
		txtFechaActualizacion.setEnabled(false);
		add(txtFechaActualizacion, cc.xy(13, 11));

		//---- lblFechaActualizacion ----
		lblFechaActualizacion.setText("Fecha Actualizaci\u00f3n: ");
		add(lblFechaActualizacion, cc.xy(11, 11));

		//======== spTblTipoContrato ========
		{
			
			//---- tblTipoContrato ----
			tblTipoContrato.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"Tipo Contrato", "Tipo de Rol", "Documento"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, true
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoContrato.setViewportView(tblTipoContrato);
		}
		add(spTblTipoContrato, cc.xywh(3, 15, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoContrato;
	private JComboBox cmbTipoContrato;
	private JLabel label1;
	private JComboBox cmbTipoRol;
	private JLabel lblNombre;
	private JComboBox cmbDocumento;
	private JLabel lblUsuarioCreador;
	private JTextField txtUsuarioCreador;
	private JLabel lblFechaCreacion;
	private JLabel lblUsuarioActual;
	private JTextField txtFechaCreacion;
	private JTextField txtUsuarioActualizador;
	private JTextField txtFechaActualizacion;
	private JLabel lblFechaActualizacion;
	private JScrollPane spTblTipoContrato;
	private JTable tblTipoContrato;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public JComboBox getCmbTipoContrato() {
		return cmbTipoContrato;
	}

	public JComboBox getCmbTipoRol() {
		return cmbTipoRol;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JLabel getLblFechaActualizacion() {
		return lblFechaActualizacion;
	}

	public JLabel getLblFechaCreacion() {
		return lblFechaCreacion;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public JLabel getLblTipoContrato() {
		return lblTipoContrato;
	}

	public JLabel getLblUsuarioActual() {
		return lblUsuarioActual;
	}

	public JLabel getLblUsuarioCreador() {
		return lblUsuarioCreador;
	}

	public JScrollPane getSpTblTipoContrato() {
		return spTblTipoContrato;
	}

	public JTable getTblTipoContrato() {
		return tblTipoContrato;
	}

	public JTextField getTxtFechaActualizacion() {
		return txtFechaActualizacion;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public JTextField getTxtUsuarioActualizador() {
		return txtUsuarioActualizador;
	}

	public JTextField getTxtUsuarioCreador() {
		return txtUsuarioCreador;
	}
}
