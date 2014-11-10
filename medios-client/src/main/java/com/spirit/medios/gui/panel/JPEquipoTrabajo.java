package com.spirit.medios.gui.panel;
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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPEquipoTrabajo extends MantenimientoModelImpl {
	public JPEquipoTrabajo() {
		initComponents();
		setName("Equipo de Trabajo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblTipoOrden = new JLabel();
		cmbTipoOrden = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		spTblEquipoTrabajo = new JScrollPane();
		tblEquipoTrabajo = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xy(3, 3));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblTipoOrden ----
		lblTipoOrden.setText("Tipo Orden:");
		lblTipoOrden.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTipoOrden, cc.xy(3, 5));
		add(cmbTipoOrden, cc.xywh(5, 5, 7, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFechaInicio, cc.xy(3, 7));
		add(cmbFechaInicio, cc.xywh(5, 7, 5, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFechaFin, cc.xy(3, 9));
		add(cmbFechaFin, cc.xywh(5, 9, 5, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEstado, cc.xy(3, 11));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xy(5, 11));

		//======== spTblEquipoTrabajo ========
		{
			
			//---- tblEquipoTrabajo ----
			tblEquipoTrabajo.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Tipo de Orden", "Fecha Inicio", "Fecha Fin", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblEquipoTrabajo.setViewportView(tblEquipoTrabajo);
		}
		add(spTblEquipoTrabajo, cc.xywh(3, 15, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblTipoOrden;
	private JComboBox cmbTipoOrden;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JScrollPane spTblEquipoTrabajo;
	private JTable tblEquipoTrabajo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JComboBox getCmbTipoOrden() {
		return cmbTipoOrden;
	}

	public void setCmbTipoOrden(JComboBox cmbTipoOrden) {
		this.cmbTipoOrden = cmbTipoOrden;
	}

	public JTable getTblEquipoTrabajo() {
		return tblEquipoTrabajo;
	}

	public void setTblEquipoTrabajo(JTable tblEquipoTrabajo) {
		this.tblEquipoTrabajo = tblEquipoTrabajo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
}
