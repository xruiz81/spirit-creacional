package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritConstants;
import com.spirit.client.SpiritCursor;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.gui.panel.JPDemoServicioWebConsultaStock;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;


public class DemoServicioWebConsultaStockModel extends JPDemoServicioWebConsultaStock {
	private static final long serialVersionUID = 8609059864863649129L;
	private static final int MAX_LONGITUD_INTERVALO_MINUTOS = 5;
	
	public DemoServicioWebConsultaStockModel() {
		initListeners();
		initKeyListeners();
		showSaveMode();
	}
	
	private void initListeners() {
		getBtnEjecutar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ejecutar();
			}
		});
	}
	
	private void initKeyListeners() {
		getTxtIntervaloMinutos().addKeyListener(new TextChecker(MAX_LONGITUD_INTERVALO_MINUTOS));
		getTxtIntervaloMinutos().addKeyListener(new NumberTextField());
	}

	public void clean() {
		getTxtIntervaloMinutos().setText(SpiritConstants.getEmptyCharacter());
		getRbSiRecuperarTodos().setSelected(true);
		getTxtResultados().setText(SpiritConstants.getEmptyCharacter());
		getLblNumeroItems().setText(SpiritConstants.getEmptyCharacter());
	}

	public void showSaveMode() {
		clean();
	}

	public void report() {
		// TODO Auto-generated method stub
		
	}
		
	@SuppressWarnings("unchecked")
	private void ejecutar() {
		setCursor(SpiritCursor.hourglassCursor);
		try {
			int count = 0;
			String data = SpiritConstants.getEmptyCharacter();
			int intervaloMinutos = getTxtIntervaloMinutos().getText().trim().equals(SpiritConstants.getEmptyCharacter())?0:Integer.parseInt(getTxtIntervaloMinutos().getText());
			Iterator<Object[][]> it = SessionServiceLocator.getStockSessionService().getStockModificadoWebService(Parametros.getIdEmpresa(), intervaloMinutos, getRbSiRecuperarTodos().isSelected()?true:false).iterator();
			while (it.hasNext()) {
				Object[][] stock = it.next();
				for (int i=0; i<stock.length; i++) {
					data += stock[i][0] + "\t" + stock[i][1] + "\n";
					count++;
				}
			}
			getTxtResultados().setText(data);
			getLblNumeroItems().setText(String.valueOf(count));
			getTxtIntervaloMinutos().setText(SpiritConstants.getEmptyCharacter());
			getTxtIntervaloMinutos().requestFocusInWindow();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		setCursor(SpiritCursor.normalCursor);
	}
}
