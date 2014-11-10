package com.spirit.sri.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public abstract class JDAirPopUp extends JDialog {
	public JDAirPopUp(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAirPopUp(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		cmbCodigo = new JComboBox();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblPorcentaje = new JLabel();
		txtPorcentaje = new JTextField();
		lblValorRetenido = new JLabel();
		txtValorRetenido = new JTextField();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spAirPopUp = new JScrollPane();
		tblAirPopUp = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Air");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(Sizes.dluX(45)),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, 1.0),
				new ColumnSpec(Sizes.DLUX3),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, 1.0),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo");
		contentPane.add(lblCodigo, cc.xy(3, 3));

		//---- cmbCodigo ----
		cmbCodigo.setEditable(false);
		contentPane.add(cmbCodigo, cc.xywh(5, 3, 3, 1));

		//---- lblValor ----
		lblValor.setText("Valor");
		contentPane.add(lblValor, cc.xy(3, 5));
		contentPane.add(txtValor, cc.xy(5, 5));

		//---- lblPorcentaje ----
		lblPorcentaje.setText("Porcentaje");
		contentPane.add(lblPorcentaje, cc.xy(3, 7));
		contentPane.add(txtPorcentaje, cc.xy(5, 7));

		//---- lblValorRetenido ----
		lblValorRetenido.setText("Valor Retenido");
		contentPane.add(lblValorRetenido, cc.xy(3, 9));
		contentPane.add(txtValorRetenido, cc.xy(5, 9));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC,
					new ColumnSpec(Sizes.DLUX3),
					FormFactory.DEFAULT_COLSPEC
				},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- btnAgregarDetalle ----
			btnAgregarDetalle.setText("A");
			panel1.add(btnAgregarDetalle, cc.xy(1, 1));
			
			//---- btnActualizarDetalle ----
			btnActualizarDetalle.setText("U");
			panel1.add(btnActualizarDetalle, cc.xy(3, 1));
			
			//---- btnEliminarDetalle ----
			btnEliminarDetalle.setText("D");
			panel1.add(btnEliminarDetalle, cc.xy(5, 1));
		}
		contentPane.add(panel1, cc.xywh(3, 13, 5, 1));

		//======== spAirPopUp ========
		{
			
			//---- tblAirPopUp ----
			tblAirPopUp.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Valor", "Porcentaje", "Valor Retenido"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblAirPopUp.setPreferredScrollableViewportSize(new Dimension(500, 160));
			spAirPopUp.setViewportView(tblAirPopUp);
		}
		contentPane.add(spAirPopUp, cc.xywh(3, 15, 5, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JComboBox cmbCodigo;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblPorcentaje;
	private JTextField txtPorcentaje;
	private JLabel lblValorRetenido;
	private JTextField txtValorRetenido;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spAirPopUp;
	private JTable tblAirPopUp;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public JComboBox getCmbCodigo() {
		return cmbCodigo;
	}

	public JTable getTblAirPopUp() {
		return tblAirPopUp;
	}

	public JTextField getTxtPorcentaje() {
		return txtPorcentaje;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JTextField getTxtValorRetenido() {
		return txtValorRetenido;
	}
}
