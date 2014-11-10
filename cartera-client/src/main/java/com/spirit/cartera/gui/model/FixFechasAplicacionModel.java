package com.spirit.cartera.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.gui.panel.JPFixFechasAplicacion;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;

public class FixFechasAplicacionModel extends JPFixFechasAplicacion {
	public FixFechasAplicacionModel(){
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnContinuar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				procesarFixFechasAplicacion();
			}
		});
	}
	
	public void save() {
		showSaveMode();
	}

	public void delete() {
		// TODO Auto-generated method stub		
	}

	public void update() {
		// TODO Auto-generated method stub		
	}

	public void showSaveMode() {
		setSaveMode();
	}
		
	private void procesarFixFechasAplicacion() {
		try {
			Iterator carteraDetalleNegativosIterator = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleNegativos().iterator();
			while (carteraDetalleNegativosIterator.hasNext()) {
				CarteraDetalleIf carteraDetalle = (CarteraDetalleIf) carteraDetalleNegativosIterator.next();
				Iterator carteraAfectaIterator = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(carteraDetalle.getId()).iterator();
				while (carteraAfectaIterator.hasNext()) {
					CarteraAfectaIf carteraAfecta = (CarteraAfectaIf) carteraAfectaIterator.next();
					carteraAfecta.setFechaAplicacion(carteraDetalle.getFechaCartera());
					SessionServiceLocator.getCarteraAfectaSessionService().saveCarteraAfecta(carteraAfecta);
				}
			}
			
			SpiritAlert.createAlert("Proceso realizado con éxito!", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void refresh() {
		clean();
	}
	
	public void clean() {
		// TODO Auto-generated method stub		
	}
}
