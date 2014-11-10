package com.spirit.nomina.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPTipoRol extends MantenimientoModelImpl {
	public JPTipoRol() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblNemonico = new JLabel();
		txtNemonico = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblRubroEventual = new JLabel();
		cmbRubroEventual = new JComboBox();
		lblFormaPago = new JLabel();
		cmbFormaPago = new JComboBox();
		lblRubroProvisionado = new JLabel();
		cmbRubroProvisionado = new JComboBox();
		spTblTipoRol = new JScrollPane();
		tblTipoRol = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Tipo Rol");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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
		add(txtNombre, cc.xywh(5, 5, 7, 1));

		//---- lblNemonico ----
		lblNemonico.setText("Nem\u00f3nico:");
		add(lblNemonico, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNemonico, cc.xywh(5, 7, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xy(3, 9));

		//---- cmbFechaInicio ----
		cmbFechaInicio.setShowOKButton(false);
		cmbFechaInicio.setShowNoneButton(false);
		cmbFechaInicio.setShowWeekNumbers(true);
		cmbFechaInicio.setShowTodayButton(false);
		add(cmbFechaInicio, cc.xywh(5, 9, 5, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xy(11, 9));
		add(cmbFechaFin, cc.xy(13, 9));

		//---- lblRubroEventual ----
		lblRubroEventual.setText("Rubro Eventual:");
		add(lblRubroEventual, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbRubroEventual ----
		cmbRubroEventual.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbRubroEventual, cc.xy(5, 11));

		//---- lblFormaPago ----
		lblFormaPago.setText("Forma de Pago: ");
		add(lblFormaPago, cc.xy(11, 11));
		add(cmbFormaPago, cc.xy(13, 11));

		//---- lblRubroProvisionado ----
		lblRubroProvisionado.setText("Provisionado: ");
		add(lblRubroProvisionado, cc.xy(3, 13));

		//---- cmbRubroProvisionado ----
		cmbRubroProvisionado.setModel(new DefaultComboBoxModel(new String[] {
			"SI",
			"NO"
		}));
		add(cmbRubroProvisionado, cc.xy(5, 13));

		//======== spTblTipoRol ========
		{
			
			//---- tblTipoRol ----
			tblTipoRol.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Nem\u00f3nico", "Rubro Eventual", "Provisionado", "Forma Pago"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoRol.setViewportView(tblTipoRol);
		}
		add(spTblTipoRol, cc.xywh(3, 17, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblNemonico;
	private JTextField txtNemonico;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblRubroEventual;
	private JComboBox cmbRubroEventual;
	private JLabel lblFormaPago;
	private JComboBox cmbFormaPago;
	private JLabel lblRubroProvisionado;
	private JComboBox cmbRubroProvisionado;
	private JScrollPane spTblTipoRol;
	private JTable tblTipoRol;
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

	public JLabel getLblNemonico() {
		return lblNemonico;
	}

	public JTextField getTxtNemonico() {
		return txtNemonico;
	}

	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JLabel getLblRubroEventual() {
		return lblRubroEventual;
	}

	public JComboBox getCmbRubroEventual() {
		return cmbRubroEventual;
	}

	public JLabel getLblFormaPago() {
		return lblFormaPago;
	}

	public JComboBox getCmbFormaPago() {
		return cmbFormaPago;
	}

	public JLabel getLblRubroProvisionado() {
		return lblRubroProvisionado;
	}

	public JComboBox getCmbRubroProvisionado() {
		return cmbRubroProvisionado;
	}

	public JScrollPane getSpTblTipoRol() {
		return spTblTipoRol;
	}

	public JTable getTblTipoRol() {
		return tblTipoRol;
	}
}
