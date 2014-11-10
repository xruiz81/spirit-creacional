package com.spirit.cartera.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPCruceNotaCredito extends SpiritModelImpl {
	public JPCruceNotaCredito() {
		initComponents();
		setName("Cruce Nota de Crédito");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoCartera = new JLabel();
		cmbTipoCartera = new JComboBox();
		panel1 = new JPanel();
		btnCruzarNotasCredito = new JButton();
		btnConsultarNotasCredito = new JButton();
		lblOperadorNegocio = new JLabel();
		txtOperadorNegocio = new JTextField();
		btnBuscarOperadorNegocio = new JButton();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		spNotasCredito = new JScrollPane();
		tblNotasCredito = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec("max(default;50dlu):grow"),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblTipoCartera ----
		lblTipoCartera.setText("Tipo cartera:");
		add(lblTipoCartera, cc.xy(3, 3));

		//---- cmbTipoCartera ----
		cmbTipoCartera.setModel(new DefaultComboBoxModel(new String[] {
			"CLIENTE",
			"PROVEEDOR"
		}));
		add(cmbTipoCartera, cc.xy(5, 3));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- btnCruzarNotasCredito ----
			btnCruzarNotasCredito.setText("C");
			panel1.add(btnCruzarNotasCredito, cc.xy(5, 1));
			
			//---- btnConsultarNotasCredito ----
			btnConsultarNotasCredito.setText("B");
			panel1.add(btnConsultarNotasCredito, cc.xy(3, 1));
		}
		add(panel1, cc.xy(13, 3));

		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Operador Negocio:");
		add(lblOperadorNegocio, cc.xy(3, 5));

		//---- txtOperadorNegocio ----
		txtOperadorNegocio.setEditable(false);
		add(txtOperadorNegocio, cc.xywh(5, 5, 5, 1));

		//---- btnBuscarOperadorNegocio ----
		btnBuscarOperadorNegocio.setText("B");
		add(btnBuscarOperadorNegocio, cc.xywh(11, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblDocumento ----
		lblDocumento.setText("Documento:");
		add(lblDocumento, cc.xy(3, 7));
		add(cmbDocumento, cc.xywh(5, 7, 3, 1));

		//======== spNotasCredito ========
		{
			
			//---- tblNotasCredito ----
			tblNotasCredito.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					" ", "Fecha", "Preimpreso", "Referencia", "Observacion", "Valor", "Saldo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, true, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spNotasCredito.setViewportView(tblNotasCredito);
		}
		add(spNotasCredito, cc.xywh(3, 11, 11, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnBuscarOperadorNegocio.setText("");
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblNotasCredito.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblNotasCredito.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		TableCellRendererHorizontalCenterAlignment tableCellRendederHorizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		tblNotasCredito.getColumnModel().getColumn(1).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblNotasCredito.getColumnModel().getColumn(2).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
		tblNotasCredito.getColumnModel().getColumn(3).setCellRenderer(tableCellRendederHorizontalCenterAlignment);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoCartera;
	private JComboBox cmbTipoCartera;
	private JPanel panel1;
	private JButton btnCruzarNotasCredito;
	private JButton btnConsultarNotasCredito;
	private JLabel lblOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JButton btnBuscarOperadorNegocio;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JScrollPane spNotasCredito;
	private JTable tblNotasCredito;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}

	public JTable getTblNotasCredito() {
		return tblNotasCredito;
	}

	public void setTblNotasCredito(JTable tblNotasCredito) {
		this.tblNotasCredito = tblNotasCredito;
	}

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}


	public JButton getBtnCruzarNotasCredito() {
		return btnCruzarNotasCredito;
	}


	public void setBtnCruzarNotasCredito(JButton btnCruzarNotasCredito) {
		this.btnCruzarNotasCredito = btnCruzarNotasCredito;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JButton getBtnConsultarNotasCredito() {
		return btnConsultarNotasCredito;
	}

	public void setBtnConsultarNotasCredito(JButton btnConsultarNotasCredito) {
		this.btnConsultarNotasCredito = btnConsultarNotasCredito;
	}

	public JComboBox getCmbTipoCartera() {
		return cmbTipoCartera;
	}

	public void setCmbTipoCartera(JComboBox cmbTipoCartera) {
		this.cmbTipoCartera = cmbTipoCartera;
	}
}
