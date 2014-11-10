package com.spirit.facturacion.gui.panel;
import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Wed May 28 17:12:38 COT 2008
 */
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPFixFechasFacturas extends MantenimientoModelImpl {
	public JPFixFechasFacturas() {
		initComponents();
		setName("Fix Fechas Facturas");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblMensaje1 = new JLabel();
		btnContinuar = new JButton();
		lblMensaje2 = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblMensaje1 ----
		lblMensaje1.setText("El siguiente proceso corregir\u00e1 las fechas a las facturas con estado 'X'");
		lblMensaje1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblMensaje1, cc.xywh(3, 3, 3, 1));

		//---- btnContinuar ----
		btnContinuar.setText("Continuar");
		btnContinuar.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnContinuar, cc.xy(7, 3));

		//---- lblMensaje2 ----
		lblMensaje2.setText("que se replicaron con fecha err\u00f3neo debido a bug en el c\u00f3digo >");
		lblMensaje2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblMensaje2, cc.xywh(3, 5, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblMensaje1;
	private JButton btnContinuar;
	private JLabel lblMensaje2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JButton getBtnContinuar() {
		return btnContinuar;
	}

	public void setBtnContinuar(JButton btnContinuar) {
		this.btnContinuar = btnContinuar;
	}
}
