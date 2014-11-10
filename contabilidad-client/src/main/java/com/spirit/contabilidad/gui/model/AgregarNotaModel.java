package com.spirit.contabilidad.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import com.spirit.contabilidad.gui.panel.JDAgregarNota;
import com.spirit.util.TextChecker;

public class AgregarNotaModel extends JDAgregarNota {
	private static final long serialVersionUID = -8289069745143494042L;
	private boolean cancelAction;
	private String notaAsiento = "";
	private String notaAsientoAnterior = "";
	private int maxLongitudNota;

	public AgregarNotaModel(Frame owner, String notaAsiento, int maxLongitudNota) {
		super(owner);
		this.notaAsientoAnterior = notaAsiento;
		this.maxLongitudNota = maxLongitudNota;
		initDialogComponents();
		initObjects();
		initListeners();
		initKeyListeners();
		initJDialogAdapter(this);
		initDialog();
	}

	private void initDialog() {
		this.setSize(this.getWidth(), this.getHeight());
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
		this.setLocation(x, y);
		this.pack();
		this.setModal(true);
		this.setVisible(true);
	}
	
	private void initDialogComponents() {
		getTxtNota().requestFocusInWindow();
		getTxtNota().setText(this.notaAsientoAnterior);
		actualizarTotalCaracteresRestantes(this.notaAsientoAnterior);
	}

	private void actualizarTotalCaracteresRestantes(String nota) {
		int notaLength = 0;
		if (nota != null)
			notaLength = nota.length();
		int totalCaracteresRestantes = maxLongitudNota - notaLength; 
		getLblTotalCaracteresRestantes().setText(String.valueOf(totalCaracteresRestantes));
	}
	
	private void initObjects() {
		setCancelAction(false);
	}
	
	private void initJDialogAdapter(JDialog jDialog) {
		jDialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setCancelAction(true);
				setVisible(false);
				dispose();
			}
		});
	}
	
	private void initListeners() {
		getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				cancel();
			}			
		});
		
		getBtnAceptar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				accept();
			}			
		});
		
		getTxtNota().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				actualizarTotalCaracteresRestantes(getTxtNota().getText());
			}
		});
	}
	
	private void initKeyListeners(){
		getTxtNota().addKeyListener(new TextChecker(maxLongitudNota));
	}
	
	private void accept() {
		this.notaAsiento = getTxtNota().getText();
		setCancelAction(false);
		setVisible(false);
		dispose();
	}
		
	private void cancel() {
		this.notaAsiento = this.notaAsientoAnterior;
		setCancelAction(true);
		setVisible(false);
		dispose();
	}

	public boolean isCancelAction() {
		return cancelAction;
	}

	public void setCancelAction(boolean cancelAction) {
		this.cancelAction = cancelAction;
	}
	
	public String getNotaAsiento() {
		return this.notaAsiento;
	}
}