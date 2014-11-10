package com.spirit.nomina.gui.panel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPRubro extends MantenimientoModelImpl {
	public JPRubro() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblTipoRol = new JLabel();
		cmbTipoRol = new JComboBox();
		lblTipoRubro = new JLabel();
		lblFrecuencia = new JLabel();
		cmbTipoRubro = new JComboBox();
		lblFecha = new JLabel();
		cmbFrecuencia = new JComboBox();
		cmbFecha = new DateComboBox();
		lblModoOperacion = new JLabel();
		cmbModoOperacion = new JComboBox();
		lblPolitica = new JLabel();
		txtPolitica = new JTextField();
		gfsRubros2 = compFactory.createSeparator("Rubro Eventual");
		lblPagoIndividual = new JLabel();
		cmbPagoIndividual = new JComboBox();
		lblNemonico = new JLabel();
		cmbNemonico = new JComboBox();
		spTblRubro = new JScrollPane();
		tblRubro = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Rubro");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(65)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(75)),
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
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 5, 5, 1));

		//---- lblTipoRol ----
		lblTipoRol.setText("Tipo Rol:");
		add(lblTipoRol, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRol, cc.xywh(5, 7, 5, 1));

		//---- lblTipoRubro ----
		lblTipoRubro.setText("Tipo Rubro:");
		add(lblTipoRubro, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFrecuencia ----
		lblFrecuencia.setText("Frecuencia:");
		add(lblFrecuencia, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoRubro, cc.xywh(5, 9, 5, 1));

		//---- lblFecha ----
		lblFecha.setText("Fecha:");
		add(lblFecha, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFrecuencia, cc.xywh(5, 11, 3, 1));
		add(cmbFecha, cc.xy(11, 11));

		//---- lblModoOperacion ----
		lblModoOperacion.setText("Modo de Operaci\u00f3n:");
		add(lblModoOperacion, cc.xy(3, 13));
		add(cmbModoOperacion, cc.xywh(5, 13, 3, 1));

		//---- lblPolitica ----
		lblPolitica.setText("Pol\u00edtica:");
		add(lblPolitica, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtPolitica, cc.xywh(5, 15, 5, 1));
		add(gfsRubros2, cc.xywh(3, 17, 13, 1));

		//---- lblPagoIndividual ----
		lblPagoIndividual.setText("Pago Individual");
		add(lblPagoIndividual, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPagoIndividual, cc.xy(5, 19));

		//---- lblNemonico ----
		lblNemonico.setText("Nem\u00f3nico");
		lblNemonico.setHorizontalAlignment(SwingConstants.LEADING);
		add(lblNemonico, cc.xywh(9, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbNemonico, cc.xywh(11, 19, 3, 1));

		//======== spTblRubro ========
		{
			
			//---- tblRubro ----
			tblRubro.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"Codigo", "Nombre", "Tipo Rol", "Tipo Rubro"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblRubro.setViewportView(tblRubro);
		}
		add(spTblRubro, cc.xywh(3, 23, 13, 4));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTipoRol;
	private JComboBox cmbTipoRol;
	private JLabel lblTipoRubro;
	private JLabel lblFrecuencia;
	private JComboBox cmbTipoRubro;
	private JLabel lblFecha;
	private JComboBox cmbFrecuencia;
	private DateComboBox cmbFecha;
	private JLabel lblModoOperacion;
	private JComboBox cmbModoOperacion;
	private JLabel lblPolitica;
	private JTextField txtPolitica;
	private JComponent gfsRubros2;
	private JLabel lblPagoIndividual;
	private JComboBox cmbPagoIndividual;
	private JLabel lblNemonico;
	private JComboBox cmbNemonico;
	private JScrollPane spTblRubro;
	private JTable tblRubro;
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

	public JLabel getLblTipoRol() {
		return lblTipoRol;
	}

	public JComboBox getCmbTipoRol() {
		return cmbTipoRol;
	}

	public JLabel getLblTipoRubro() {
		return lblTipoRubro;
	}

	public JLabel getLblFrecuencia() {
		return lblFrecuencia;
	}

	public JComboBox getCmbTipoRubro() {
		return cmbTipoRubro;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JComboBox getCmbFrecuencia() {
		return cmbFrecuencia;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public JLabel getLblModoOperacion() {
		return lblModoOperacion;
	}

	public JComboBox getCmbModoOperacion() {
		return cmbModoOperacion;
	}

	public JLabel getLblPolitica() {
		return lblPolitica;
	}

	public JTextField getTxtPolitica() {
		return txtPolitica;
	}

	public JComponent getGfsRubros2() {
		return gfsRubros2;
	}

	public JLabel getLblPagoIndividual() {
		return lblPagoIndividual;
	}

	public JComboBox getCmbPagoIndividual() {
		return cmbPagoIndividual;
	}

	public JLabel getLblNemonico() {
		return lblNemonico;
	}

	public JComboBox getCmbNemonico() {
		return cmbNemonico;
	}

	public JScrollPane getSpTblRubro() {
		return spTblRubro;
	}

	public JTable getTblRubro() {
		return tblRubro;
	}
}
