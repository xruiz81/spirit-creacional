package com.spirit.inventario.gui.panel;
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
import com.jidesoft.swing.JideButton;
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPBodega extends MantenimientoModelImpl {
	public JPBodega() {
		initComponents();
		setName("Bodega");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		txtCodigo = new JTextField();
		lblCodigo = new JLabel();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblTipoBodega = new JLabel();
		txtTipoBodega = new JTextField();
		btnBuscarTipoBodega = new JButton();
		lblFuncion = new JLabel();
		txtFuncionBodega = new JTextField();
		btnBuscarFuncionBodega = new JButton();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		spTblBodega = new JScrollPane();
		tblBodega = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
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
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblFechaCreacion ----
		lblFechaCreacion.setText("Fecha Creaci\u00f3n:");
		add(lblFechaCreacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtFechaCreacion, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		add(lblNombre, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtNombre, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbOficina, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblTipoBodega ----
		lblTipoBodega.setText("Tipo Bodega:");
		add(lblTipoBodega, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtTipoBodega, cc.xywh(5, 11, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- btnBuscarTipoBodega ----
		btnBuscarTipoBodega.setToolTipText("Buscar Tipo de Bodega");
		btnBuscarTipoBodega.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		add(btnBuscarTipoBodega, cc.xywh(11, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblFuncion ----
		lblFuncion.setText("Funci\u00f3n Bodega:");
		add(lblFuncion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtFuncionBodega, cc.xywh(5, 13, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- btnBuscarFuncionBodega ----
		btnBuscarFuncionBodega.setToolTipText("Buscar Función de Bodega");
		btnBuscarFuncionBodega.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		add(btnBuscarFuncionBodega, cc.xywh(11, 13, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"INACTIVO"
		}));
		add(cmbEstado, cc.xywh(5, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//======== spTblBodega ========
		{
			
			//---- tblBodega ----
			tblBodega.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Oficina", "Tipo Bodega", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblBodega.getTableHeader().setReorderingAllowed(false);
			spTblBodega.setViewportView(tblBodega);
		}
		add(spTblBodega, cc.xywh(3, 19, 10, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JTextField txtCodigo;
	private JLabel lblCodigo;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblTipoBodega;
	private JTextField txtTipoBodega;
	private JButton btnBuscarTipoBodega;
	private JLabel lblFuncion;
	private JTextField txtFuncionBodega;
	private JButton btnBuscarFuncionBodega;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JScrollPane spTblBodega;
	private JTable tblBodega;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public void setTxtFechaCreacion(JTextField txtFechaCreacion) {
		this.txtFechaCreacion = txtFechaCreacion;
	}

	public JButton getBtnBuscarFuncionBodega() {
		return btnBuscarFuncionBodega;
	}

	public JButton getBtnBuscarTipoBodega() {
		return btnBuscarTipoBodega;
	}

	public JTextField getTxtFuncionBodega() {
		return txtFuncionBodega;
	}

	public void setTxtFuncionBodega(JTextField txtFuncionBodega) {
		this.txtFuncionBodega = txtFuncionBodega;
	}

	public JTextField getTxtTipoBodega() {
		return txtTipoBodega;
	}

	public void setTxtTipoBodega(JTextField txtTipoBodega) {
		this.txtTipoBodega = txtTipoBodega;
	}

	public void setBtnBuscarFuncionBodega(JideButton btnBuscarFuncionBodega) {
		this.btnBuscarFuncionBodega = btnBuscarFuncionBodega;
	}

	public void setBtnBuscarTipoBodega(JideButton btnBuscarTipoBodega) {
		this.btnBuscarTipoBodega = btnBuscarTipoBodega;
	}

	public JScrollPane getSpTblBodega() {
		return spTblBodega;
	}

	public void setSpTblBodega(JScrollPane spTblBodega) {
		this.spTblBodega = spTblBodega;
	}

	public JTable getTblBodega() {
		return tblBodega;
	}

	public void setTblBodega(JTable tblBodega) {
		this.tblBodega = tblBodega;
	}
}
