package com.spirit.inventario.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPRegistroGiftcards extends SpiritModelImpl {
	public JPRegistroGiftcards() {
		initComponents();
		setName("Activar giftcards");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		gfsRango = compFactory.createSeparator("Rango");
		lblCodigoInicial = new JLabel();
		txtCodigoInicial = new JTextField();
		lblCodigoFinal = new JLabel();
		txtCodigoFinal = new JTextField();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		lblValor = new JLabel();
		txtValor = new JTextField();
		btnRegistrarGiftcards = new JButton();
		gfsGiftcardsActivos = compFactory.createSeparator("Giftcards activos");
		spGiftcardsActivos = new JScrollPane();
		tblGiftcardsActivos = new JTable();
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

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblProducto, cc.xy(3, 9));

		//---- txtProducto ----
		txtProducto.setHorizontalAlignment(JTextField.LEFT);
		add(txtProducto, cc.xywh(5, 9, 3, 1));

		//---- btnBuscarProducto ----
		btnBuscarProducto.setText("B");
		add(btnBuscarProducto, cc.xywh(9, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblValor ----
		lblValor.setText("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblValor, cc.xy(3, 11));

		//---- txtValor ----
		txtValor.setHorizontalAlignment(JTextField.RIGHT);
		add(txtValor, cc.xy(5, 11));

		//---- btnRegistrarGiftcards ----
		btnRegistrarGiftcards.setText("Registrar Giftcards");
		add(btnRegistrarGiftcards, cc.xy(9, 11));
		add(gfsGiftcardsActivos, cc.xywh(3, 15, 9, 1));

		//======== spGiftcardsActivos ========
		{
			
			//---- tblGiftcardsActivos ----
			tblGiftcardsActivos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Valor", "Saldo", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spGiftcardsActivos.setViewportView(tblGiftcardsActivos);
		}
		add(spGiftcardsActivos, cc.xywh(3, 19, 9, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		getBtnBuscarProducto().setText("");
		getBtnBuscarProducto().setToolTipText("Buscar");
		getBtnBuscarProducto().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JComponent gfsRango;
	private JLabel lblCodigoInicial;
	private JTextField txtCodigoInicial;
	private JLabel lblCodigoFinal;
	private JTextField txtCodigoFinal;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblValor;
	private JTextField txtValor;
	private JButton btnRegistrarGiftcards;
	private JComponent gfsGiftcardsActivos;
	private JScrollPane spGiftcardsActivos;
	private JTable tblGiftcardsActivos;
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

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JTable getTblGiftcardsActivos() {
		return tblGiftcardsActivos;
	}

	public void setTblGiftcardsActivos(JTable tblGiftcardsActivos) {
		this.tblGiftcardsActivos = tblGiftcardsActivos;
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

	public JButton getBtnRegistrarGiftcards() {
		return btnRegistrarGiftcards;
	}

	public void setBtnRegistrarGiftcards(JButton btnRegistrarGiftcards) {
		this.btnRegistrarGiftcards = btnRegistrarGiftcards;
	}
}
