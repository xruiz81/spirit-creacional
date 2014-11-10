package com.spirit.general.gui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

/**
 * @author Antonio Seiler
 */
public class JPCheque extends JPanel {
	
	int chequeActual = 0;
	Vector<String[]> cheques = new Vector<String[]>();
	
	public JPCheque(Vector<String[]> cheques) {
		initComponents();
		initListeners();
		this.cheques = cheques;
		fillCheque();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblPagueseA = new JLabel();
		txtPagueseA = new JTextField();
		lblCantidadLetras = new JLabel();
		txtCantidadLetrasPrimeraLinea = new JTextField();
		txtCantidadLetrasSegundaLinea = new JTextField();
		lblLugarFecha = new JLabel();
		txtLugarFecha = new JTextField();
		btnAnterior = new JButton();
		btnSiguiente = new JButton();
		btnImprimirCheque = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(min;150dlu):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblValor ----
		lblValor.setText("Valor:");
		add(lblValor, cc.xy(7, 3));
		add(txtValor, cc.xywh(9, 3, 3, 1));

		//---- lblPagueseA ----
		lblPagueseA.setText("P\u00e1guese a la orden de:");
		add(lblPagueseA, cc.xy(3, 5));
		add(txtPagueseA, cc.xywh(5, 5, 7, 1));

		//---- lblCantidadLetras ----
		lblCantidadLetras.setText("La cantidad de:");
		add(lblCantidadLetras, cc.xy(3, 7));
		add(txtCantidadLetrasPrimeraLinea, cc.xywh(5, 7, 7, 1));
		add(txtCantidadLetrasSegundaLinea, cc.xywh(3, 9, 9, 1));

		//---- lblLugarFecha ----
		lblLugarFecha.setText("Lugar y Fecha:");
		add(lblLugarFecha, cc.xy(3, 11));
		add(txtLugarFecha, cc.xy(5, 11));

		//---- btnAnterior ----
		btnAnterior.setText("<");
		add(btnAnterior, cc.xy(7, 13));

		//---- btnSiguiente ----
		btnSiguiente.setText(">");
		add(btnSiguiente, cc.xy(11, 13));

		//---- btnImprimirCheque ----
		btnImprimirCheque.setText("Imprimir Cheque");
		add(btnImprimirCheque, cc.xy(9, 13));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblPagueseA;
	private JTextField txtPagueseA;
	private JLabel lblCantidadLetras;
	private JTextField txtCantidadLetrasPrimeraLinea;
	private JTextField txtCantidadLetrasSegundaLinea;
	private JLabel lblLugarFecha;
	private JTextField txtLugarFecha;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnImprimirCheque;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public void initListeners() {
		getBtnAnterior().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (chequeActual > 0) {
					chequeActual--;
					fillCheque();
				}
			}
		});
		
		getBtnSiguiente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (chequeActual < cheques.size()) {
					chequeActual++;
					fillCheque();
				}
			}
		});
	}
	
	public void fillCheque() {
		String[] datosCheque = new String[5];
		datosCheque = cheques.get(chequeActual);
		
		cleanForm();
		
		getTxtValor().setText(datosCheque[0]);
		getTxtPagueseA().setText(datosCheque[1]);
		getTxtCantidadLetrasPrimeraLinea().setText(datosCheque[2]);
		getTxtCantidadLetrasSegundaLinea().setText(datosCheque[3]);
		getTxtLugarFecha().setText(datosCheque[4]);
	}
	
	public void cleanForm() {
		getTxtValor().setText("");
		getTxtPagueseA().setText("");
		getTxtCantidadLetrasPrimeraLinea().setText("");
		getTxtCantidadLetrasSegundaLinea().setText("");
		getTxtLugarFecha().setText("");
	}	
	
	public JButton getBtnAnterior() {
		return btnAnterior;
	}

	public void setBtnAnterior(JButton btnAnterior) {
		this.btnAnterior = btnAnterior;
	}

	public JButton getBtnImprimirCheque() {
		return btnImprimirCheque;
	}

	public void setBtnImprimirCheque(JButton btnImprimirCheque) {
		this.btnImprimirCheque = btnImprimirCheque;
	}

	public JButton getBtnSiguiente() {
		return btnSiguiente;
	}

	public void setBtnSiguiente(JButton btnSiguiente) {
		this.btnSiguiente = btnSiguiente;
	}

	public JTextField getTxtCantidadLetrasPrimeraLinea() {
		return txtCantidadLetrasPrimeraLinea;
	}

	public void setTxtCantidadLetrasPrimeraLinea(
			JTextField txtCantidadLetrasPrimeraLinea) {
		this.txtCantidadLetrasPrimeraLinea = txtCantidadLetrasPrimeraLinea;
	}

	public JTextField getTxtCantidadLetrasSegundaLinea() {
		return txtCantidadLetrasSegundaLinea;
	}

	public void setTxtCantidadLetrasSegundaLinea(
			JTextField txtCantidadLetrasSegundaLinea) {
		this.txtCantidadLetrasSegundaLinea = txtCantidadLetrasSegundaLinea;
	}

	public JTextField getTxtLugarFecha() {
		return txtLugarFecha;
	}

	public void setTxtLugarFecha(JTextField txtLugarFecha) {
		this.txtLugarFecha = txtLugarFecha;
	}

	public JTextField getTxtPagueseA() {
		return txtPagueseA;
	}

	public void setTxtPagueseA(JTextField txtPagueseA) {
		this.txtPagueseA = txtPagueseA;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}
	
}
