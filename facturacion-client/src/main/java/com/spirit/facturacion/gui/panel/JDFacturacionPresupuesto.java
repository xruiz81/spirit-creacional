package com.spirit.facturacion.gui.panel;
import java.awt.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Tue Aug 23 11:00:18 COT 2011
 */

/**
 * @author xruiz
 */
public class JDFacturacionPresupuesto extends JDialog {
	public JDFacturacionPresupuesto(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDFacturacionPresupuesto(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		dialogPane = new JPanel();
		contentPane = new JPanel();
		ldescripcion = new JLabel();
		rbFacturarCliente = new JRadioButton();
		rbFacturacionParcial = new JRadioButton();
		rbFacturarNegociacionDirecta = new JRadioButton();
		cmbMedioNegociacionDirecta = new JComboBox();
		rbFacturarComisionPura = new JRadioButton();
		cmbMedioComisionPura = new JComboBox();
		btnAceptar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Forma de Facturaci\u00f3n");
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX3),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(95), FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX3)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY3),
						FormFactory.LINE_GAP_ROWSPEC,
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
						new RowSpec(Sizes.DLUY3)
					}));

				//---- ldescripcion ----
				ldescripcion.setText("Elija la forma de Facturaci\u00f3n:");
				contentPane.add(ldescripcion, cc.xywh(3, 3, 7, 1));

				//---- rbFacturarCliente ----
				rbFacturarCliente.setText("Completo");
				contentPane.add(rbFacturarCliente, cc.xy(3, 5));

				//---- rbFacturacionParcial ----
				rbFacturacionParcial.setText("Parcial");
				contentPane.add(rbFacturacionParcial, cc.xy(3, 7));

				//---- rbFacturarNegociacionDirecta ----
				rbFacturarNegociacionDirecta.setText("Negociaci\u00f3n Directa");
				contentPane.add(rbFacturarNegociacionDirecta, cc.xy(3, 9));
				contentPane.add(cmbMedioNegociacionDirecta, cc.xywh(5, 9, 5, 1));

				//---- rbFacturarComisionPura ----
				rbFacturarComisionPura.setText("Comisi\u00f3n Pura");
				contentPane.add(rbFacturarComisionPura, cc.xy(3, 11));
				contentPane.add(cmbMedioComisionPura, cc.xywh(5, 11, 5, 1));

				//---- btnAceptar ----
				btnAceptar.setText("Aceptar");
				contentPane.add(btnAceptar, cc.xy(7, 15));
			}
			dialogPane.add(contentPane, BorderLayout.NORTH);
		}
		contentPane2.add(dialogPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());

		//---- bgFormasFacturacion ----
		ButtonGroup bgFormasFacturacion = new ButtonGroup();
		bgFormasFacturacion.add(rbFacturarCliente);
		bgFormasFacturacion.add(rbFacturacionParcial);
		bgFormasFacturacion.add(rbFacturarNegociacionDirecta);
		bgFormasFacturacion.add(rbFacturarComisionPura);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel ldescripcion;
	private JRadioButton rbFacturarCliente;
	private JRadioButton rbFacturacionParcial;
	private JRadioButton rbFacturarNegociacionDirecta;
	private JComboBox cmbMedioNegociacionDirecta;
	private JRadioButton rbFacturarComisionPura;
	private JComboBox cmbMedioComisionPura;
	private JButton btnAceptar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JRadioButton getRbFacturacionParcial() {
		return rbFacturacionParcial;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JRadioButton getRbFacturarCliente() {
		return rbFacturarCliente;
	}

	public JRadioButton getRbFacturarNegociacionDirecta() {
		return rbFacturarNegociacionDirecta;
	}

	public JComboBox getCmbMedioNegociacionDirecta() {
		return cmbMedioNegociacionDirecta;
	}

	public JRadioButton getRbFacturarComisionPura() {
		return rbFacturarComisionPura;
	}

	public JComboBox getCmbMedioComisionPura() {
		return cmbMedioComisionPura;
	}
}
