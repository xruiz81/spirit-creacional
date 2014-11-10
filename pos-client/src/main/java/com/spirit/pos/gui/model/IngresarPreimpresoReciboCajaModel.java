package com.spirit.pos.gui.model;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.WindowConstants;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.gui.panel.JDIngresarPreimpresoReciboCaja;
import com.spirit.util.TextChecker;

public class IngresarPreimpresoReciboCajaModel extends JDIngresarPreimpresoReciboCaja {
	private Pattern patron = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{7}");
	private static final int MAX_LONGITUD_PREIMPRESO = 15;
	CarteraIf cartera;
	private String preimpreso;
	
	public IngresarPreimpresoReciboCajaModel(Frame owner, CarteraIf cartera) {
		super(owner);
		this.cartera = cartera;
		this.setTitle("Preimpreso R E C I B O   D E   C A J A");
		initKeyListeners();
		initListeners();
		int width = 300;
		int height = 250;
		setSize(width, height);
		int x = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int y = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(x, y);
		pack();
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setVisible(true);
	}
	
	private void initKeyListeners() {
		getTxtPreimpreso().addKeyListener(new TextChecker(MAX_LONGITUD_PREIMPRESO));
	}
	
	private void initListeners() {
		getOkButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					aceptar();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		});
		
		getTxtPreimpreso().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyChar() == KeyEvent.VK_ENTER) {
					try {
						aceptar();
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	private void aceptar() throws CloneNotSupportedException {
		if (validateFields()) {
			try {
				String preimpreso = getTxtPreimpreso().getText();
				SessionServiceLocator.getCarteraSessionService().actualizarPreimpreso(cartera, preimpreso);
				SpiritAlert.createAlert("Preimpreso actualizado con éxito", SpiritAlert.INFORMATION);
				this.preimpreso=preimpreso;
				setVisible(false);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error al actualizar el preimpreso", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	private boolean validateFields() {
		String preimpreso = getTxtPreimpreso().getText();
		Matcher matcher = patron.matcher(preimpreso);
		//boolean encontrado = matcher.find();
		boolean encontrado = matcher.matches();
		if (!encontrado) {
			SpiritAlert.createAlert("Formato de preimpreso debe ser: ###-###-#######", SpiritAlert.WARNING);
			getTxtPreimpreso().grabFocus();
			return false;
		}
		
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("tipodocumentoId", this.cartera.getTipodocumentoId());
			parameterMap.put("preimpreso", preimpreso);
			Iterator it = SessionServiceLocator.getCarteraSessionService().findCarteraByQuery(parameterMap).iterator();
			if (it.hasNext()) {
				SpiritAlert.createAlert("Preimpreso duplicado. Ingréselo nuevamente.", SpiritAlert.WARNING);
				getTxtPreimpreso().grabFocus();
				return false;
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al validar preimpreso.", SpiritAlert.ERROR);
		}

		return true;
	}

	public String getPreimpreso() {
		return preimpreso;
	}
}
