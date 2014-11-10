package com.spirit.general.gui.panel;

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
import com.spirit.client.model.MantenimientoModelImpl;


/**
 * @author xruiz
 */
public abstract class JPBanco extends MantenimientoModelImpl {
	public JPBanco() {
		initComponents();
		setName("Bancos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblEstado = new JLabel();
		cmbStatus = new JComboBox();
		lblCodigoMulticash = new JLabel();
		txtCodigoMulticash = new JTextField();
		spTblBanco = new JScrollPane();
		tblBanco = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(35)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
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
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo descriptivo del M\u00f3dulo");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo del M\u00f3dulo");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setToolTipText("Nombre del M\u00f3dulo");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre del M\u00f3dulo");
		add(txtNombre, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		lblEstado.setToolTipText("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEstado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- cmbStatus ----
		cmbStatus.setToolTipText("Estado");
		add(cmbStatus, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCodigoMulticash ----
		lblCodigoMulticash.setText("C\u00f3digo Multicash:");
		add(lblCodigoMulticash, cc.xy(3, 9));
		add(txtCodigoMulticash, cc.xy(5, 9));

		//======== spTblBanco ========
		{

			//---- tblBanco ----
			tblBanco.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Estado", "C\u00f3digo Multicash"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblBanco.setViewportView(tblBanco);
		}
		add(spTblBanco, cc.xywh(3, 13, 9, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblEstado;
	private JComboBox cmbStatus;
	private JLabel lblCodigoMulticash;
	private JTextField txtCodigoMulticash;
	private JScrollPane spTblBanco;
	private JTable tblBanco;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(JComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public JScrollPane getSpTblBanco() {
		return spTblBanco;
	}

	public void setSpTblBanco(JScrollPane spTblBanco) {
		this.spTblBanco = spTblBanco;
	}

	public JTable getTblBanco() {
		return tblBanco;
	}

	public void setTblBanco(JTable tblBanco) {
		this.tblBanco = tblBanco;
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

	public JTextField getTxtCodigoMulticash() {
		return txtCodigoMulticash;
	}

	public void setTxtCodigoMulticash(JTextField txtCodigoMulticash) {
		this.txtCodigoMulticash = txtCodigoMulticash;
	}
}
