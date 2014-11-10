package com.spirit.general.gui.panel;

import javax.swing.DefaultComboBoxModel;
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

public abstract class JPCaja extends MantenimientoModelImpl {
	public JPCaja() {
		initComponents();
		setName("Caja");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblnombre = new JLabel();
		txtNombre = new JTextField();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		lblTurno = new JLabel();
		cmbTurno = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		spCaja = new JScrollPane();
		tblCaja = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;50dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(50), FormSpec.DEFAULT_GROW),
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
				new RowSpec(Sizes.dluY(12)),
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
		add(lblCodigo, cc.xy(3, 3));
		add(txtCodigo, cc.xy(5, 3));

		//---- lblnombre ----
		lblnombre.setText("Nombre:");
		add(lblnombre, cc.xy(3, 5));
		add(txtNombre, cc.xywh(5, 5, 3, 1));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xy(3, 7));
		add(cmbOficina, cc.xywh(5, 7, 3, 1));

		//---- lblTurno ----
		lblTurno.setText("Turno:");
		add(lblTurno, cc.xy(3, 9));
		
		//---- cmbTurno ----
		cmbTurno.setModel(new DefaultComboBoxModel(new String[] {
			"A TURNO",
			"B TURNO",
			"C TURNO"
		}));
		add(cmbTurno, cc.xywh(5, 9, 3, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xy(3, 11));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVA",
			"INACTIVA"
		}));
		add(cmbEstado, cc.xy(5, 11));

		//======== spCaja ========
		{
			
			//---- tblCaja ----
			tblCaja.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Nombre", "Oficina", "Turno", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblCaja.getTableHeader().setReorderingAllowed(false);
			spCaja.setViewportView(tblCaja);
		}
		add(spCaja, cc.xywh(3, 15, 8, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblnombre;
	private JTextField txtNombre;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JLabel lblTurno;
	private JComboBox cmbTurno;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JScrollPane spCaja;
	private JTable tblCaja;
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

	public JComboBox getCmbTurno() {
		return cmbTurno;
	}

	public void setCmbTurno(JComboBox cmbTurno) {
		this.cmbTurno = cmbTurno;
	}

	public JTable getTblCaja() {
		return tblCaja;
	}

	public void setTblCaja(JTable tblCaja) {
		this.tblCaja = tblCaja;
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
}
