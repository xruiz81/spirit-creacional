package com.spirit.general.gui.panel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
 * @author Antonio Seiler
 */
public abstract class JPCruceDocumento extends MantenimientoModelImpl {
	public JPCruceDocumento() {
		initComponents();
		setName("Cruce Documento");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		lblTipoDocumentoAplica = new JLabel();
		cmbTipoDocumentoAplica = new JComboBox();
		lblDocumentoAplica = new JLabel();
		cmbDocumentoAplica = new JComboBox();
		spPlantilla = new JScrollPane();
		tblCruceDocumento = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(200)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
			}));

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo Documento:");
		add(lblTipoDocumento, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoDocumento, cc.xy(5, 3));

		//---- lblDocumento ----
		lblDocumento.setText("Documento:");
		add(lblDocumento, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDocumento, cc.xy(5, 5));

		//---- lblTipoDocumentoAplica ----
		lblTipoDocumentoAplica.setText("Tipo Documento Aplica:");
		add(lblTipoDocumentoAplica, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoDocumentoAplica, cc.xy(5, 7));

		//---- lblDocumentoAplica ----
		lblDocumentoAplica.setText("Documento Aplica:");
		add(lblDocumentoAplica, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDocumentoAplica, cc.xy(5, 9));

		//======== spPlantilla ========
		{
			
			//---- tblCruceDocumento ----
			tblCruceDocumento.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Documento", "Documento que Aplica"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spPlantilla.setViewportView(tblCruceDocumento);
		}
		add(spPlantilla, cc.xywh(3, 13, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JLabel lblTipoDocumentoAplica;
	private JComboBox cmbTipoDocumentoAplica;
	private JLabel lblDocumentoAplica;
	private JComboBox cmbDocumentoAplica;
	private JScrollPane spPlantilla;
	private JTable tblCruceDocumento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JComboBox getCmbDocumentoAplica() {
		return cmbDocumentoAplica;
	}

	public void setCmbDocumentoAplica(JComboBox cmbDocumentoAplica) {
		this.cmbDocumentoAplica = cmbDocumentoAplica;
	}

	public JTable getTblCruceDocumento() {
		return tblCruceDocumento;
	}

	public void setTblCruceDocumento(JTable tblCruceDocumento) {
		this.tblCruceDocumento = tblCruceDocumento;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JComboBox getCmbTipoDocumentoAplica() {
		return cmbTipoDocumentoAplica;
	}

	public void setCmbTipoDocumentoAplica(JComboBox cmbTipoDocumentoAplica) {
		this.cmbTipoDocumentoAplica = cmbTipoDocumentoAplica;
	}
	
}
