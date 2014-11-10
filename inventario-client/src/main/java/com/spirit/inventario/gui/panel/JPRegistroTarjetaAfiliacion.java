package com.spirit.inventario.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPRegistroTarjetaAfiliacion extends SpiritModelImpl {
	public JPRegistroTarjetaAfiliacion() {
		initComponents();
		setName("Registro de tarjetas afiliacion");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		gfsRango = compFactory.createSeparator("Rango");
		lblCodigoInicial = new JLabel();
		txtCodigoInicial = new JTextField();
		lblCodigoFinal = new JLabel();
		txtCodigoFinal = new JTextField();
		lblProducto2 = new JLabel();
		cmbTipoTarjeta = new JComboBox();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		lblValor = new JLabel();
		txtValor = new JTextField();
		btnRegistrarTarjetasAfiliacion = new JButton();
		gfsTarjetasAfiliacionActivas = compFactory.createSeparator("Tarjetas afiliaci\u00f3n activas");
		spTarjetasAfiliacionActivas = new JScrollPane();
		tblTarjetasAfiliacionActivas = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
			}));
		add(gfsRango, cc.xywh(3, 3, 9, 1));

		//---- lblCodigoInicial ----
		lblCodigoInicial.setText("C\u00f3digo inicial:");
		lblCodigoInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblCodigoInicial, cc.xy(3, 5));

		//---- txtCodigoInicial ----
		txtCodigoInicial.setHorizontalAlignment(JTextField.RIGHT);
		add(txtCodigoInicial, cc.xy(5, 5));

		//---- lblCodigoFinal ----
		lblCodigoFinal.setText("C\u00f3digo final:");
		lblCodigoFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblCodigoFinal, cc.xy(3, 7));

		//---- txtCodigoFinal ----
		txtCodigoFinal.setHorizontalAlignment(JTextField.RIGHT);
		add(txtCodigoFinal, cc.xy(5, 7));

		//---- lblProducto2 ----
		lblProducto2.setText("Tipo tarjeta:");
		lblProducto2.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblProducto2, cc.xy(3, 9));
		add(cmbTipoTarjeta, cc.xy(5, 9));

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblProducto, cc.xy(3, 11));

		//---- txtProducto ----
		txtProducto.setHorizontalAlignment(JTextField.RIGHT);
		add(txtProducto, cc.xywh(5, 11, 3, 1));

		//---- btnBuscarProducto ----
		btnBuscarProducto.setText("B");
		add(btnBuscarProducto, cc.xywh(9, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblValor ----
		lblValor.setText("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblValor, cc.xy(3, 13));

		//---- txtValor ----
		txtValor.setHorizontalAlignment(JTextField.RIGHT);
		add(txtValor, cc.xy(5, 13));

		//---- btnRegistrarTarjetasAfiliacion ----
		btnRegistrarTarjetasAfiliacion.setText("Registrar Tarjetas Afiliaci\u00f3n");
		add(btnRegistrarTarjetasAfiliacion, cc.xy(9, 13));
		add(gfsTarjetasAfiliacionActivas, cc.xywh(3, 17, 9, 1));

		//======== spTarjetasAfiliacionActivas ========
		{
			
			//---- tblTarjetasAfiliacionActivas ----
			tblTarjetasAfiliacionActivas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Tipo", "Acumulado", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTarjetasAfiliacionActivas.setViewportView(tblTarjetasAfiliacionActivas);
		}
		add(spTarjetasAfiliacionActivas, cc.xywh(3, 21, 9, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JComponent gfsRango;
	private JLabel lblCodigoInicial;
	private JTextField txtCodigoInicial;
	private JLabel lblCodigoFinal;
	private JTextField txtCodigoFinal;
	private JLabel lblProducto2;
	private JComboBox cmbTipoTarjeta;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblValor;
	private JTextField txtValor;
	private JButton btnRegistrarTarjetasAfiliacion;
	private JComponent gfsTarjetasAfiliacionActivas;
	private JScrollPane spTarjetasAfiliacionActivas;
	private JTable tblTarjetasAfiliacionActivas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCodigoInicial() {
		return txtCodigoInicial;
	}

	public void setTxtCodigoInicial(JTextField txtCodigoInicial) {
		this.txtCodigoInicial = txtCodigoInicial;
	}

	public JTextField getTxtCodigoFinal() {
		return txtCodigoFinal;
	}

	public void setTxtCodigoFinal(JTextField txtCodigoFinal) {
		this.txtCodigoFinal = txtCodigoFinal;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JButton getBtnRegistrarTarjetasAfiliacion() {
		return btnRegistrarTarjetasAfiliacion;
	}

	public void setBtnRegistrarTarjetasAfiliacion(
			JButton btnRegistrarTarjetasAfiliacion) {
		this.btnRegistrarTarjetasAfiliacion = btnRegistrarTarjetasAfiliacion;
	}

	public JTable getTblTarjetasAfiliacionActivas() {
		return tblTarjetasAfiliacionActivas;
	}

	public void setTblTarjetasAfiliacionActivas(JTable tblTarjetasAfiliacionActivas) {
		this.tblTarjetasAfiliacionActivas = tblTarjetasAfiliacionActivas;
	}

	public JComboBox getCmbTipoTarjeta() {
		return cmbTipoTarjeta;
	}

	public void setCmbTipoTarjeta(JComboBox cmbTipoTarjeta) {
		this.cmbTipoTarjeta = cmbTipoTarjeta;
	}
}
