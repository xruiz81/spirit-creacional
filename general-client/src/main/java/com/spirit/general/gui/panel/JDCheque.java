package com.spirit.general.gui.panel;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JREmptyDataSource;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;

public class JDCheque extends JDialog {
	
	private static final long serialVersionUID = -2776232962920377465L;
	int chequeActual = 0;
	ArrayList<String[]> cheques = new ArrayList<String[]>();
	
	public JDCheque(Frame owner, ArrayList<String[]> cheques) {
		super(owner);
		initComponents();
		initListeners();
		this.cheques = cheques;
		if (this.cheques.size() > 1)
			btnSiguiente.setEnabled(true);
		fillCheque();
		initDialog();
	}
	
	private void initDialog() {
		this.setSize(SpiritConstants.getJdialogCheckWidth(), SpiritConstants.getJdialogCheckWidth());
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - SpiritConstants.getJdialogCheckWidth()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - SpiritConstants.getJdialogCheckHeight()) / 2;
		this.setLocation(x, y);
		this.pack();
		this.setVisible(true);
	}
	
	public JDCheque(Dialog owner, ArrayList<String[]> cheques) {
		super(owner);
		initComponents();
		initListeners();
		this.cheques = cheques;
		if (this.cheques.size() > 1)
			btnSiguiente.setEnabled(true);
		fillCheque();
	}
	
	/*public JDCheque(Frame owner, Vector<String[]> cheques) {
		super(owner);
		initComponents();
		initListeners();
		this.cheques = cheques;
		if (this.cheques.size() > 1)
			btnSiguiente.setEnabled(true);
		fillCheque();
	}*/

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		JPCheque = new JPanel();
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
		btnEditar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Previsualizaci\u00f3n de Cheque");
		setResizable(false);
		setModal(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default",
			"default"));

		//======== JPCheque ========
		{
			JPCheque.setLayout(new FormLayout(
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
			JPCheque.add(lblValor, cc.xy(7, 3));
			
			//---- txtValor ----
			txtValor.setEditable(false);
			JPCheque.add(txtValor, cc.xywh(9, 3, 3, 1));
			
			//---- lblPagueseA ----
			lblPagueseA.setText("P\u00e1guese a la orden de:");
			JPCheque.add(lblPagueseA, cc.xy(3, 5));
			
			//---- txtPagueseA ----
			txtPagueseA.setEditable(false);
			JPCheque.add(txtPagueseA, cc.xywh(5, 5, 5, 1));
			
			//---- btnEditar ----
			btnEditar.setText("Editar");
			JPCheque.add(btnEditar, cc.xy(11, 5));
			
			//---- lblCantidadLetras ----
			lblCantidadLetras.setText("La cantidad de:");
			JPCheque.add(lblCantidadLetras, cc.xy(3, 7));
			
			//---- txtCantidadLetrasPrimeraLinea ----
			txtCantidadLetrasPrimeraLinea.setEditable(false);
			JPCheque.add(txtCantidadLetrasPrimeraLinea, cc.xywh(5, 7, 7, 1));
			
			//---- txtCantidadLetrasSegundaLinea ----
			txtCantidadLetrasSegundaLinea.setEditable(false);
			JPCheque.add(txtCantidadLetrasSegundaLinea, cc.xywh(3, 9, 9, 1));
			
			//---- lblLugarFecha ----
			lblLugarFecha.setText("Lugar y Fecha:");
			JPCheque.add(lblLugarFecha, cc.xy(3, 11));
			
			//---- txtLugarFecha ----
			txtLugarFecha.setEditable(false);
			JPCheque.add(txtLugarFecha, cc.xy(5, 11));
			
			//---- btnAnterior ----
			btnAnterior.setText("<");
			JPCheque.add(btnAnterior, cc.xy(7, 13));
			
			//---- btnSiguiente ----
			btnSiguiente.setText(">");
			JPCheque.add(btnSiguiente, cc.xywh(11, 13, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
			
			//---- btnImprimirCheque ----
			btnImprimirCheque.setText("Imprimir Cheque");
			JPCheque.add(btnImprimirCheque, cc.xy(9, 13));
		}
		contentPane.add(JPCheque, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnAnterior.setEnabled(false);
		btnSiguiente.setEnabled(false);
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel JPCheque;
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
	private JButton btnEditar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public void initListeners() {
		getBtnEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getTxtPagueseA().isEditable()){
					getTxtPagueseA().setEditable(false);
				}else{
					getTxtPagueseA().setEditable(true);
				}
			}
		});
		
		getBtnAnterior().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (chequeActual > 0) {
					chequeActual--;
					btnAnterior.setEnabled(true);
					btnSiguiente.setEnabled(true);
					if (chequeActual == 0)
						btnAnterior.setEnabled(false);
					fillCheque();
				}
			}
		});
		
		getBtnSiguiente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (chequeActual < cheques.size() - 1) {
					chequeActual++;
					btnAnterior.setEnabled(true);
					btnSiguiente.setEnabled(true);
					if (chequeActual == cheques.size() - 1)
						btnSiguiente.setEnabled(false);
					fillCheque();
				}
			}
		});
		
		getBtnImprimirCheque().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				report();
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

	public void report() {
		String fileName = "jaspers/cartera/RPCheque.jasper";
		HashMap<String,Object> parametrosMap = new HashMap<String,Object>();
		parametrosMap.put("valorCheque", getTxtValor().getText());
		parametrosMap.put("pagueseA", getTxtPagueseA().getText());
		parametrosMap.put("cantidadLetrasPrimeraLinea", getTxtCantidadLetrasPrimeraLinea().getText());
		parametrosMap.put("cantidadLetrasSegundaLinea", getTxtCantidadLetrasSegundaLinea().getText());
		parametrosMap.put("lugarFecha", getTxtLugarFecha().getText());
		//this.setVisible(false);
		//this.dispose();
		ReportModelImpl.processReport(fileName, parametrosMap, new JREmptyDataSource(), true);
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
	
	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	
	
}
