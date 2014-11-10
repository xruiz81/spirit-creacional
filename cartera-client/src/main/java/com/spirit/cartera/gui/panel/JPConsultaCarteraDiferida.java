package com.spirit.cartera.gui.panel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.JideButton;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPConsultaCarteraDiferida extends ReportModelImpl {
	
	public JPConsultaCarteraDiferida() {
		setName("Consulta Cartera Diferida");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		spDetalle = new JScrollPane();
		tblDetalle = new JTable();
		cmbFechaFin = new DateComboBox();
		jbtnConsultar = new JideButton();
		jbtnActualizarEstado = new JideButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(96)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(96)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(60), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha inicial:");
		add(lblFechaInicio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaInicio, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha final:");
		add(lblFechaFin, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//======== spDetalle ========
		{
			spDetalle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			spDetalle.setPreferredSize(new Dimension(452, 300));
			
			 //Setea que los valores de estas columnas se ubiquen en el lado derecho de las celdas de la tabla
			DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
			dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
			
			//---- tblDetalle ----
			tblDetalle.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha de Cartera", "C\u00f3digo", "Tipo de Documento", "Documento", "Cliente", "Moneda", "Valor", "Saldo"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblDetalle.setPreferredScrollableViewportSize(new Dimension(450, 75));
			tblDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblDetalle.getColumnModel().getColumn(6).setCellRenderer(dtcrColumn);
			tblDetalle.getColumnModel().getColumn(7).setCellRenderer(dtcrColumn);
		    tblDetalle.setFocusable(false);
		    tblDetalle.setCellSelectionEnabled(false);
			tblDetalle.setAutoCreateColumnsFromModel(true);
			spDetalle.setViewportView(tblDetalle);
		}
		add(spDetalle, cc.xywh(3, 7, 14, 1));
		cmbFechaFin.setShowNoneButton(false);
		add(cmbFechaFin, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- jbtnConsultar ----
		jbtnConsultar.setText("Consultar");
		jbtnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		jbtnConsultar.setToolTipText("Consultar Carteras que tienen valores diferidos");
		add(jbtnConsultar, cc.xy(15, 3));

		//---- jbtnActualizarEstado ----
		jbtnActualizarEstado.setText("Actualizar estado");
		jbtnActualizarEstado.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/checkElement.png"));
		jbtnActualizarEstado.setToolTipText("Actualizar saldo de esta(s) Cartera(s)");
		add(jbtnActualizarEstado, cc.xy(15, 11));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private JScrollPane spDetalle;
	private JTable tblDetalle;
	private DateComboBox cmbFechaFin;
	private JideButton jbtnActualizarEstado;
	private JideButton jbtnConsultar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
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

	public JideButton getJbtnActualizarEstado() {
		return jbtnActualizarEstado;
	}

	public void setJbtnActualizarEstado(JideButton jbtnActualizarEstado) {
		this.jbtnActualizarEstado = jbtnActualizarEstado;
	}

	public JideButton getJbtnConsultar() {
		return jbtnConsultar;
	}

	public void setJbtnConsultar(JideButton jbtnConsultar) {
		this.jbtnConsultar = jbtnConsultar;
	}

	public JTable getTblDetalle() {
		return tblDetalle;
	}

	public void setTblDetalle(JTable tblDetalle) {
		this.tblDetalle = tblDetalle;
	}
	
}
