package com.spirit.inventario.gui.panel;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPMarcaProducto extends MantenimientoModelImpl {
	public JPMarcaProducto() {
		initComponents();
		setName("Marca");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		cmbEstado = new JComboBox();
		lblEstado = new JLabel();
		btnBuscarCorporacion = new JButton();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnEncerarOperadorNegocio = new JButton();
		lblOperadorNegocio = new JLabel();
		txtOperadorNegocio = new JTextField();
		btnBuscarOperadorNegocio = new JButton();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		spTblMarcaProducto = new JScrollPane();
		tblMarcaProducto = new JTable();
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
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigo, cc.xy(5, 3));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xy(11, 3));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(btnBuscarCorporacion, cc.xywh(13, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblCorporacion ----
		lblCorporacion.setText("Corporaci\u00f3n:");
		add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCorporacion ----
		txtCorporacion.setEditable(false);
		add(txtCorporacion, cc.xywh(5, 5, 7, 1));
		add(btnEncerarOperadorNegocio, cc.xywh(15, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Operador de negocio:");
		add(lblOperadorNegocio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtOperadorNegocio ----
		txtOperadorNegocio.setEditable(false);
		add(txtOperadorNegocio, cc.xywh(5, 7, 7, 1));
		add(btnBuscarOperadorNegocio, cc.xywh(13, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtNombre, cc.xywh(5, 9, 5, 1));

		//======== spTblMarcaProducto ========
		{
			spTblMarcaProducto.setPreferredSize(new Dimension(452, 100));
			
			//---- tblMarcaProducto ----
			tblMarcaProducto.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblMarcaProducto.setPreferredScrollableViewportSize(new Dimension(450, 300));
			tblMarcaProducto.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblMarcaProducto.setAutoCreateColumnsFromModel(true);
			spTblMarcaProducto.setViewportView(tblMarcaProducto);
		}
		add(spTblMarcaProducto, cc.xywh(3, 13, 13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnEncerarOperadorNegocio.setToolTipText("Limpiar datos operador de negocio");
		btnEncerarOperadorNegocio.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/cancelar.png"));
		btnBuscarCorporacion.setToolTipText("Buscar Corporación");
		btnBuscarCorporacion.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarOperadorNegocio.setToolTipText("Buscar operador de negocio");
		btnBuscarOperadorNegocio.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JComboBox cmbEstado;
	private JLabel lblEstado;
	private JButton btnBuscarCorporacion;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnEncerarOperadorNegocio;
	private JLabel lblOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JButton btnBuscarOperadorNegocio;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JScrollPane spTblMarcaProducto;
	private JTable tblMarcaProducto;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}

	public JButton getBtnEncerarOperadorNegocio() {
		return btnEncerarOperadorNegocio;
	}

	public void setBtnEncerarOperadorNegocio(JButton btnEncerarOperadorNegocio) {
		this.btnEncerarOperadorNegocio = btnEncerarOperadorNegocio;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JTable getTblMarcaProducto() {
		return tblMarcaProducto;
	}

	public void setTblMarcaProducto(JTable tblMarcaProducto) {
		this.tblMarcaProducto = tblMarcaProducto;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}
}
