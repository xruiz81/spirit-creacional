package com.spirit.cartera.gui.panel;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author Antonio Seiler
 */
public abstract class JPEmpleadoDocumento extends SpiritModelImpl {
	public JPEmpleadoDocumento() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPEmpleadoDocumento = new JPanel();
		lblDocumentoId = new JLabel();
		cmbDocumentoId = new JComboBox();
		lblEmpleadoId = new JLabel();
		cmbEmpleadoId = new JComboBox();
		lblPermisoImpresion = new JLabel();
		lblPermisoRegistro = new JLabel();
		rbPermisoImpresionSi = new JRadioButton();
		rbPermisoRegistroSi = new JRadioButton();
		rbPermisoRegistroNo = new JRadioButton();
		rbPermisoImpresionNo = new JRadioButton();
		CellConstraints cc = new CellConstraints();

		//======== JPEmpleadoDocumento ========
		{
			JPEmpleadoDocumento.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;10dlu):grow")
				},
				new RowSpec[] {
					new RowSpec("max(default;10dlu)"),
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
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//---- lblDocumentoId ----
			lblDocumentoId.setText("Documento:");
			JPEmpleadoDocumento.add(lblDocumentoId, cc.xy(3, 3));
			JPEmpleadoDocumento.add(cmbDocumentoId, cc.xy(5, 3));
			
			//---- lblEmpleadoId ----
			lblEmpleadoId.setText("Empleado:");
			JPEmpleadoDocumento.add(lblEmpleadoId, cc.xy(3, 5));
			JPEmpleadoDocumento.add(cmbEmpleadoId, cc.xy(5, 5));
			
			//---- lblPermisoImpresion ----
			lblPermisoImpresion.setText("Permiso Impresi\u00f3n:");
			JPEmpleadoDocumento.add(lblPermisoImpresion, cc.xy(3, 7));
			
			//---- lblPermisoRegistro ----
			lblPermisoRegistro.setText("Permiso Registro:");
			JPEmpleadoDocumento.add(lblPermisoRegistro, cc.xy(7, 7));
			
			//---- rbPermisoImpresionSi ----
			rbPermisoImpresionSi.setText("S\u00ed");
			JPEmpleadoDocumento.add(rbPermisoImpresionSi, cc.xy(5, 7));
			
			//---- rbPermisoRegistroSi ----
			rbPermisoRegistroSi.setText("S\u00ed");
			JPEmpleadoDocumento.add(rbPermisoRegistroSi, cc.xy(9, 7));
			
			//---- rbPermisoRegistroNo ----
			rbPermisoRegistroNo.setText("No");
			JPEmpleadoDocumento.add(rbPermisoRegistroNo, cc.xy(9, 9));
			
			//---- rbPermisoImpresionNo ----
			rbPermisoImpresionNo.setText("No");
			JPEmpleadoDocumento.add(rbPermisoImpresionNo, cc.xy(5, 9));
		}

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbPermisoImpresionSi);
		buttonGroup1.add(rbPermisoImpresionNo);

		//---- buttonGroup2 ----
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rbPermisoRegistroSi);
		buttonGroup2.add(rbPermisoRegistroNo);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPEmpleadoDocumento;
	private JLabel lblDocumentoId;
	private JComboBox cmbDocumentoId;
	private JLabel lblEmpleadoId;
	private JComboBox cmbEmpleadoId;
	private JLabel lblPermisoImpresion;
	private JLabel lblPermisoRegistro;
	private JRadioButton rbPermisoImpresionSi;
	private JRadioButton rbPermisoRegistroSi;
	private JRadioButton rbPermisoRegistroNo;
	private JRadioButton rbPermisoImpresionNo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
