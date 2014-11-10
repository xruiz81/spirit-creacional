package com.spirit.cartera.gui.panel;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPClienteCondicion extends MantenimientoModelImpl {
	public JPClienteCondicion() {
		setName("Condiciones de Cliente");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		lblFormaPago = new JLabel();
		cmbFormaPago = new JComboBox();
		lblTipoOrden = new JLabel();
		cmbTipoOrden = new JComboBox();
		lblSubtipoOrden = new JLabel();
		cmbSubtipoOrden = new JComboBox();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		lblFechaFinal = new JLabel();
		cmbFechaFinal = new DateComboBox();
		lblObservaciones = new JLabel();
		txtObservaciones = new JTextField();
		spClienteCondicion = new JScrollPane();
		tblClienteCondicion = new JTable();
		CellConstraints cc = new CellConstraints();
		popup = new JPopupMenu();
		
		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(110)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
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

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCliente, cc.xywh(5, 3, 5, 1));
		add(btnBuscarCliente, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFormaPago ----
		lblFormaPago.setText("Forma de pago:");
		add(lblFormaPago, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFormaPago, cc.xywh(5, 5, 3, 1));

		//---- lblTipoOrden ----
		lblTipoOrden.setText("Tipo de orden:");
		add(lblTipoOrden, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoOrden, cc.xywh(5, 7, 3, 1));

		//---- lblSubtipoOrden ----
		lblSubtipoOrden.setText("Subtipo de orden:");
		add(lblSubtipoOrden, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbSubtipoOrden, cc.xywh(5, 9, 3, 1));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicial, cc.xywh(5, 11, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaFinal, cc.xy(5, 13));

		//---- lblObservaciones ----
		lblObservaciones.setText("Observaciones:");
		add(lblObservaciones, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtObservaciones, cc.xywh(5, 15, 9, 1));

		//======== spClienteCondicion ========
		{
			
			//---- tblClienteCondicion ----
			tblClienteCondicion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Forma de Pago", "Cliente", "Tipo de Orden", "Subtipo de Orden"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblClienteCondicion.setPreferredScrollableViewportSize(new Dimension(450, 400));
			tblClienteCondicion.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblClienteCondicion.setAutoCreateColumnsFromModel(true);
			spClienteCondicion.setViewportView(tblClienteCondicion);
		}
		add(spClienteCondicion, cc.xywh(3, 19, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarCliente.setToolTipText("Buscar cliente");
		btnBuscarCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCliente;
	private JButton btnBuscarCliente;
	private JTextField txtCliente;
	private JLabel lblFormaPago;
	private JComboBox cmbFormaPago;
	private JLabel lblTipoOrden;
	private JComboBox cmbTipoOrden;
	private JLabel lblSubtipoOrden;
	private JComboBox cmbSubtipoOrden;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JLabel lblFechaFinal;
	private DateComboBox cmbFechaFinal;
	private JLabel lblObservaciones;
	private JTextField txtObservaciones;
	private JScrollPane spClienteCondicion;
	private JTable tblClienteCondicion;
	protected JPopupMenu popup;
	protected JMenuItem menuItem;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public void setCmbFechaFinal(DateComboBox cmbFechaFinal) {
		this.cmbFechaFinal = cmbFechaFinal;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public void setCmbFechaInicial(DateComboBox cmbFechaInicial) {
		this.cmbFechaInicial = cmbFechaInicial;
	}

	public JComboBox getCmbFormaPago() {
		return cmbFormaPago;
	}

	public void setCmbFormaPago(JComboBox cmbFormaPago) {
		this.cmbFormaPago = cmbFormaPago;
	}

	public JComboBox getCmbSubtipoOrden() {
		return cmbSubtipoOrden;
	}

	public void setCmbSubtipoOrden(JComboBox cmbSubtipoOrden) {
		this.cmbSubtipoOrden = cmbSubtipoOrden;
	}

	public JComboBox getCmbTipoOrden() {
		return cmbTipoOrden;
	}

	public void setCmbTipoOrden(JComboBox cmbTipoOrden) {
		this.cmbTipoOrden = cmbTipoOrden;
	}

	public JMenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(JMenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void setPopup(JPopupMenu popup) {
		this.popup = popup;
	}

	public JTable getTblClienteCondicion() {
		return tblClienteCondicion;
	}

	public void setTblClienteCondicion(JTable tblClienteCondicion) {
		this.tblClienteCondicion = tblClienteCondicion;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(JTextField txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}
}
