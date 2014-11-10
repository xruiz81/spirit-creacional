package com.spirit.inventario.gui.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.JDialogModelImpl;

/**
 * @author Antonio Seiler
 */
public abstract class JDParametrosGuiaRemision extends JDialogModelImpl {
	public JDParametrosGuiaRemision(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDParametrosGuiaRemision(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label9 = new JLabel();
		label1 = new JLabel();
		cmbFechaInicial = new DateComboBox();
		label2 = new JLabel();
		cmbFechaFinal2 = new DateComboBox();
		label3 = new JLabel();
		txtComprobanteVenta = new JTextField();
		label4 = new JLabel();
		label5 = new JLabel();
		txtTransportistaRucCI = new JTextField();
		label6 = new JLabel();
		txtTransportistaRazonSocial = new JTextField();
		label7 = new JLabel();
		txtTransportistaTelefono = new JTextField();
		label8 = new JLabel();
		txtTransportistaPlacaVehiculo = new JTextField();
		buttonBar = new JPanel();
		btnOk = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setName("Compras Asociadas");
		setTitle("Gu\u00eda de Remisi\u00f3n");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(min;70dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(min;50dlu)"),
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
				FormFactory.DEFAULT_ROWSPEC,
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

		//---- label9 ----
		label9.setText("Datos Generales");
		label9.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		contentPane.add(label9, cc.xy(3, 3));

		//---- label1 ----
		label1.setText("Fecha Inicial");
		contentPane.add(label1, cc.xy(3, 5));
		contentPane.add(cmbFechaInicial, cc.xy(5, 5));

		//---- label2 ----
		label2.setText("Fecha Final");
		contentPane.add(label2, cc.xy(3, 7));
		contentPane.add(cmbFechaFinal2, cc.xy(5, 7));

		//---- label3 ----
		label3.setText("Comprobante de Venta");
		contentPane.add(label3, cc.xy(3, 9));
		contentPane.add(txtComprobanteVenta, cc.xy(5, 9));

		//---- label4 ----
		label4.setText("Datos del Transportista");
		label4.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		contentPane.add(label4, cc.xy(3, 11));

		//---- label5 ----
		label5.setText("RUC/CI");
		contentPane.add(label5, cc.xy(3, 13));
		contentPane.add(txtTransportistaRucCI, cc.xy(5, 13));

		//---- label6 ----
		label6.setText("Razon Social/Nombre");
		contentPane.add(label6, cc.xy(3, 15));
		contentPane.add(txtTransportistaRazonSocial, cc.xywh(5, 15, 3, 1));

		//---- label7 ----
		label7.setText("Telef.");
		contentPane.add(label7, cc.xy(3, 17));
		contentPane.add(txtTransportistaTelefono, cc.xy(5, 17));

		//---- label8 ----
		label8.setText("Placa del Vehiculo");
		contentPane.add(label8, cc.xy(3, 19));
		contentPane.add(txtTransportistaPlacaVehiculo, cc.xy(5, 19));

		//======== buttonBar ========
		{
			buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
			buttonBar.setLayout(new FormLayout(
				"default, pref:grow, default, default:grow, default",
				"default:grow"));
			
			//---- btnOk ----
			btnOk.setText("OK");
			buttonBar.add(btnOk, cc.xy(2, 1));
			
			//---- btnCancel ----
			btnCancel.setText("Cancel");
			buttonBar.add(btnCancel, cc.xy(4, 1));
		}
		contentPane.add(buttonBar, cc.xywh(3, 21, 7, 2));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label9;
	private JLabel label1;
	private DateComboBox cmbFechaInicial;
	private JLabel label2;
	private DateComboBox cmbFechaFinal2;
	private JLabel label3;
	private JTextField txtComprobanteVenta;
	private JLabel label4;
	private JLabel label5;
	private JTextField txtTransportistaRucCI;
	private JLabel label6;
	private JTextField txtTransportistaRazonSocial;
	private JLabel label7;
	private JTextField txtTransportistaTelefono;
	private JLabel label8;
	private JTextField txtTransportistaPlacaVehiculo;
	private JPanel buttonBar;
	private JButton btnOk;
	private JButton btnCancel;


	// JFormDesigner - End of variables declaration //GEN-END:variables

	public DateComboBox getCmbFechaFinal2() {
		return cmbFechaFinal2;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public JTextField getTxtComprobanteVenta() {
		return txtComprobanteVenta;
	}

	public JTextField getTxtTransportistaRucCI() {
		return txtTransportistaRucCI;
	}

	public JTextField getTxtTransportistaRazonSocial() {
		return txtTransportistaRazonSocial;
	}

	public JTextField getTxtTransportistaTelefono() {
		return txtTransportistaTelefono;
	}

	public JTextField getTxtTransportistaPlacaVehiculo() {
		return txtTransportistaPlacaVehiculo;
	}

	public JPanel getButtonBar() {
		return buttonBar;
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}
}
