package com.spirit.general.gui.panel;

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

/**
 * @author xruiz
 */
public abstract class JPPuntoImpresion extends MantenimientoModelImpl {
	public JPPuntoImpresion() {
		initComponents();
		setName("Punto de Impresion");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblCaja = new JLabel();
		cmbCaja = new JComboBox();
		lblImpresora = new JLabel();
		txtImpresora = new JTextField();
		lblSerie = new JLabel();
		txtSerie = new JTextField();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		spTblPuntoImpresion = new JScrollPane();
		tblPuntoImpresion = new JTable();
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
				new ColumnSpec(Sizes.dluX(140)),
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

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo de Documento:");
		add(lblTipoDocumento, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoDocumento, cc.xywh(5, 3, 3, 1));

		//---- lblCaja ----
		lblCaja.setText("Caja:");
		add(lblCaja, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCaja, cc.xywh(5, 5, 3, 1));

		//---- lblImpresora ----
		lblImpresora.setText("Impresora:");
		add(lblImpresora, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtImpresora, cc.xywh(5, 7, 3, 1));

		//---- lblSerie ----
		lblSerie.setText("Serie:");
		add(lblSerie, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtSerie, cc.xy(5, 9));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEstado, cc.xy(5, 11));

		//======== spTblPuntoImpresion ========
		{
			
			//---- tblPuntoImpresion ----
			tblPuntoImpresion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"Tipo de Documento", "Caja", "Impresora", "Serie", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblPuntoImpresion.setViewportView(tblPuntoImpresion);
		}
		add(spTblPuntoImpresion, cc.xywh(3, 15, 7, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblCaja;
	private JComboBox cmbCaja;
	private JLabel lblImpresora;
	private JTextField txtImpresora;
	private JLabel lblSerie;
	private JTextField txtSerie;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JScrollPane spTblPuntoImpresion;
	private JTable tblPuntoImpresion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbCaja() {
		return cmbCaja;
	}

	public void setCmbCaja(JComboBox cmbCaja) {
		this.cmbCaja = cmbCaja;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JScrollPane getSpTblPuntoImpresion() {
		return spTblPuntoImpresion;
	}

	public void setSpTblPuntoImpresion(JScrollPane spTblPuntoImpresion) {
		this.spTblPuntoImpresion = spTblPuntoImpresion;
	}

	public JTable getTblPuntoImpresion() {
		return tblPuntoImpresion;
	}

	public void setTblPuntoImpresion(JTable tblPuntoImpresion) {
		this.tblPuntoImpresion = tblPuntoImpresion;
	}

	public JTextField getTxtImpresora() {
		return txtImpresora;
	}

	public void setTxtImpresora(JTextField txtImpresora) {
		this.txtImpresora = txtImpresora;
	}

	public JTextField getTxtSerie() {
		return txtSerie;
	}

	public void setTxtSerie(JTextField txtSerie) {
		this.txtSerie = txtSerie;
	}
}
