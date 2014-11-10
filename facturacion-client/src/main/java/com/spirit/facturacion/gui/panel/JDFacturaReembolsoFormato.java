package com.spirit.facturacion.gui.panel;
import java.awt.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Mon May 07 10:15:12 COT 2012
 */

/**
 * @author xruiz
 */
public class JDFacturaReembolsoFormato extends JDialog {
	public JDFacturaReembolsoFormato(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDFacturaReembolsoFormato(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		dialogPane = new JPanel();
		contentPane = new JPanel();
		ldescripcion = new JLabel();
		rbFormatoNormal = new JRadioButton();
		rbFormatoIVAenTotal = new JRadioButton();
		rbFormatoIVAenDetalles = new JRadioButton();
		btnAceptar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Formatos de Factura de Reembolso");
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
						new ColumnSpec(Sizes.dluX(160)),
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
						new RowSpec(Sizes.DLUY7),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY3)
					}));

				//---- ldescripcion ----
				ldescripcion.setText("Elija el formato de la Factura de Reembolso:");
				contentPane.add(ldescripcion, cc.xy(3, 3));

				//---- rbFormatoNormal ----
				rbFormatoNormal.setText("Normal");
				contentPane.add(rbFormatoNormal, cc.xy(3, 5));

				//---- rbFormatoIVAenTotal ----
				rbFormatoIVAenTotal.setText("Presentar el IVA en el Total.");
				contentPane.add(rbFormatoIVAenTotal, cc.xy(3, 7));

				//---- rbFormatoIVAenDetalles ----
				rbFormatoIVAenDetalles.setText("Presentar el IVA en los Detalles.");
				contentPane.add(rbFormatoIVAenDetalles, cc.xy(3, 9));

				//---- btnAceptar ----
				btnAceptar.setText("Aceptar");
				contentPane.add(btnAceptar, cc.xywh(3, 13, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			dialogPane.add(contentPane, BorderLayout.NORTH);
		}
		contentPane2.add(dialogPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());

		//---- bgFormatosFacturaReembolso ----
		ButtonGroup bgFormatosFacturaReembolso = new ButtonGroup();
		bgFormatosFacturaReembolso.add(rbFormatoNormal);
		bgFormatosFacturaReembolso.add(rbFormatoIVAenTotal);
		bgFormatosFacturaReembolso.add(rbFormatoIVAenDetalles);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel ldescripcion;
	private JRadioButton rbFormatoNormal;
	private JRadioButton rbFormatoIVAenTotal;
	private JRadioButton rbFormatoIVAenDetalles;
	private JButton btnAceptar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JRadioButton getRbFormatoNormal() {
		return rbFormatoNormal;
	}

	public JRadioButton getRbFormatoIVAenTotal() {
		return rbFormatoIVAenTotal;
	}

	public JRadioButton getRbFormatoIVAenDetalles() {
		return rbFormatoIVAenDetalles;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}
}
