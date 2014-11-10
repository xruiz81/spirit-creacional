package com.spirit.inventario.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPProductoRetencion extends MantenimientoModelImpl {
	public JPProductoRetencion() {
		initComponents();
		setName("Retenciones por Producto");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblProducto = new JLabel();
		spProducto = new JScrollPane();
		txtProducto = new JTextArea();
		btnBuscarProducto = new JButton();
		lblRetencion = new JLabel();
		txtRetencion = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		spProductoRetencion = new JScrollPane();
		tblProductoRetencion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(60), FormSpec.DEFAULT_GROW),
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
				new RowSpec(Sizes.dluY(30)),
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

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblProducto, cc.xy(3, 3));

		//======== spProducto ========
		{
			spProducto.setViewportView(txtProducto);
		}
		add(spProducto, cc.xywh(5, 3, 7, 5));

		//---- btnBuscarProducto ----
		btnBuscarProducto.setToolTipText("Buscar Producto");
		btnBuscarProducto.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarProducto.setHorizontalAlignment(SwingConstants.LEFT);
		add(btnBuscarProducto, cc.xywh(13, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblRetencion ----
		lblRetencion.setText("Retenci\u00f3n [%]:");
		lblRetencion.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblRetencion, cc.xy(3, 9));
		
		txtRetencion.setHorizontalAlignment(JTextField.RIGHT);
		add(txtRetencion, cc.xy(5, 9));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFechaInicio, cc.xy(3, 11));
		add(cmbFechaInicio, cc.xywh(5, 11, 5, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblFechaFin, cc.xy(3, 13));
		add(cmbFechaFin, cc.xywh(5, 13, 5, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEstado, cc.xy(3, 15));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xywh(5, 15, 3, 1));

		//======== spProductoRetencion ========
		{
			
			//---- tblProductoRetencion ----
			tblProductoRetencion.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Producto", "% Retenci\u00f3n", "Fecha Inicio", "Fecha Fin", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spProductoRetencion.setViewportView(tblProductoRetencion);
		}
		add(spProductoRetencion, cc.xywh(3, 19, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblProducto;
	private JScrollPane spProducto;
	private JTextArea txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblRetencion;
	private JTextField txtRetencion;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JScrollPane spProductoRetencion;
	private JTable tblProductoRetencion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

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

	public JTable getTblProductoRetencion() {
		return tblProductoRetencion;
	}

	public void setTblProductoRetencion(JTable tblProductoRetencion) {
		this.tblProductoRetencion = tblProductoRetencion;
	}

	public JTextArea getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextArea txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JTextField getTxtRetencion() {
		return txtRetencion;
	}

	public void setTxtRetencion(JTextField txtRetencion) {
		this.txtRetencion = txtRetencion;
	}
}
