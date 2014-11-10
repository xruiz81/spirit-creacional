package com.spirit.pos.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.pos.entity.PuntosTipoProductoData;
import com.spirit.pos.entity.PuntosTipoProductoIf;
import com.spirit.pos.entity.TarjetaTipoIf;
import com.spirit.pos.gui.panel.JPTipoProductoTarjetaAfiliacionPuntos;
 

public class TipoProductoTarjetaAfiliacionPuntosModel extends JPTipoProductoTarjetaAfiliacionPuntos {

	TarjetaTipoIf tarjetaTipoIf;
	PuntosTipoProductoIf puntosTipoProductoIf;
	public TipoProductoTarjetaAfiliacionPuntosModel() {
		 
		showSaveMode();
		initListeners();
	 
	}
	
	private void initListeners(){ 
		cargarComboTipoTarjetaAfiliacion();
		cargarComboTipoProducto();
		
		getCmbTipoTarjetaAfiliacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(getCmbTipoTarjetaAfiliacion().getSelectedItem()==null)) 
					cargarRelacionDscto();
					cargarComboTipoProducto();
			}
		});
		
		getCmbTipoProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(getCmbTipoProducto().getSelectedItem()==null)) 
					cargarPuntos();
			}
		});			
	}
	
	private void cargarComboTipoTarjetaAfiliacion(){
		try {
			Iterator padreIterator = (Iterator)SessionServiceLocator.getTarjetaTipoSessionService().findTarjetaTipoByCodigo("TA").iterator();
			if (padreIterator.hasNext()) {				
				List tarjetasAfiliacion = (List)SessionServiceLocator.getTarjetaTipoSessionService().findTarjetaTipoByPadreId(((TarjetaTipoIf) padreIterator.next()).getId());			
				refreshCombo(getCmbTipoTarjetaAfiliacion(), tarjetasAfiliacion);
			}
			cargarRelacionDscto();
			cargarComboTipoProducto();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	 
	private void cargarComboTipoProducto(){
		try {
			TarjetaTipoIf tarjetatipo=(TarjetaTipoIf) this.getCmbTipoTarjetaAfiliacion().getSelectedItem();	
			if(tarjetatipo!=null)
			{
				System.out.println("AKI LLENANDO EL SEGUNDO COMBO"+tarjetatipo.getId());
				List tiposProducto = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoTarjetaAfiliacion(tarjetatipo.getId().toString());				
				List tiposProducto2 = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoTarjetaAfiliacionResto(tarjetatipo.getId().toString());
				
				System.out.println("TIPOSPRODUC"+tiposProducto);
				System.out.println("TIPOSPRODUC-->"+tiposProducto2);
				tiposProducto.addAll(tiposProducto2);
				
				refreshCombo(getCmbTipoProducto(), tiposProducto);	
				cargarPuntos();
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void cargarRelacionDscto(){
		TarjetaTipoIf tempo=(TarjetaTipoIf) this.getCmbTipoTarjetaAfiliacion().getSelectedItem();	
		if(tempo!=null){			  
			try {				
				Iterator tarjetatipoIterator;				
				ArrayList tarjetaTipoArray = (ArrayList)SessionServiceLocator.getTarjetaTipoSessionService().findTarjetaTipoById(tempo.getId());
				
				if(tarjetaTipoArray.size()>0){
					tarjetatipoIterator = tarjetaTipoArray.iterator();
					int numero = 1;
					if (tarjetatipoIterator.hasNext()) {
						tarjetaTipoIf = (TarjetaTipoIf) tarjetatipoIterator.next();					 	 
						String relacionPuntosDinero=tarjetaTipoIf.getPuntosDinero();
						getTxtPuntosDinero().setText(relacionPuntosDinero);
						String dsctoReferido=tarjetaTipoIf.getDsctoReferido().toString();
						getTxtDsctoReferido().setText(dsctoReferido);
						String dsctoPropie=tarjetaTipoIf.getDsctoPropietario().toString();
						getTxtDsctoPropietario().setText(dsctoPropie);
					}
					else{
						tarjetaTipoIf=null;
						getTxtPuntosDinero().setText("0");
						getTxtDsctoReferido().setText("0");
						getTxtDsctoPropietario().setText("0");
					}
				}
				else{
					tarjetaTipoIf=null;
					getTxtPuntosDinero().setText("0");
					getTxtDsctoReferido().setText("0");
					getTxtDsctoPropietario().setText("0");
				}			
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void cargarPuntos(){
		TarjetaTipoIf tarjetatipo=(TarjetaTipoIf) this.getCmbTipoTarjetaAfiliacion().getSelectedItem();	
		TipoProductoIf tipoproducto=(TipoProductoIf) this.getCmbTipoProducto().getSelectedItem();
		
		System.out.println("AKI LLENANDO EL SEGUNDO COMBO LOS PUNTOS"+tarjetatipo.getId());
		System.out.println("AKI LLENANDO EL SEGUNDO COMBO LOS PUNTOS"+tipoproducto.getId());
		
		
		if(tarjetatipo!=null){			  
			try {				
				Iterator puntosIterator;			
				Map aMap = new HashMap();
				aMap.clear();
				aMap.put("tarjetaTipoId", tarjetatipo.getId());
				aMap.put("tipoProductoId", tipoproducto.getId());			
				ArrayList puntosTipoProductoArray = (ArrayList)SessionServiceLocator.getPuntosTipoProductoSessionService().findPuntosTipoProductoByQuery(aMap);
				
				if(puntosTipoProductoArray.size()>0){
					System.out.println("encontrado DATOS EN PUNTOSTIPO");
					puntosIterator = puntosTipoProductoArray.iterator();
					int numero = 1;
					if (puntosIterator.hasNext()) {
						puntosTipoProductoIf = (PuntosTipoProductoIf) puntosIterator.next();				
						getTxtPuntosReferido().setText(puntosTipoProductoIf.getPuntosReferido().toString());						
						getTxtPuntosPropietario().setText(puntosTipoProductoIf.getPuntosCompras().toString());
					}
					else{
						getTxtPuntosReferido().setText("0");
						getTxtPuntosPropietario().setText("0");					
					}
				}
				else{
					puntosTipoProductoIf=null;
					getTxtPuntosReferido().setText("0");
					getTxtPuntosPropietario().setText("0");	
				}			
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	 
	
	
	@Override
	public void clean() {
		// TODO Auto-generated method stub
		initListeners();
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		if(tarjetaTipoIf!=null){
			tarjetaTipoIf.setPuntosDinero(getTxtPuntosDinero().getText());
			tarjetaTipoIf.setDsctoReferido(new BigDecimal(getTxtDsctoReferido().getText()));		
			tarjetaTipoIf.setDsctoPropietario(new BigDecimal(getTxtDsctoPropietario().getText()));
			try {
				SessionServiceLocator.getTarjetaTipoSessionService().saveTarjetaTipo(tarjetaTipoIf);
				SpiritAlert.createAlert("Valor de Relacion tipo/dinero y Descuentos actualizados con éxito!!!", SpiritAlert.INFORMATION);
				 
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("puntosTipoproductoIf----------SAVE AGAIN>"+puntosTipoProductoIf);
		
		if(puntosTipoProductoIf!=null){
			puntosTipoProductoIf.setPuntosReferido(new BigDecimal(getTxtPuntosReferido().getText()));
			puntosTipoProductoIf.setPuntosCompras(new BigDecimal(getTxtPuntosPropietario().getText()));
			try {
				SessionServiceLocator.getPuntosTipoProductoSessionService().savePuntosTipoProducto(puntosTipoProductoIf);
				SpiritAlert.createAlert("Valor de Puntos por Tipo de producto grabado con éxito!!!", SpiritAlert.INFORMATION);
				 
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			puntosTipoProductoIf=registrarPuntosTipoProducto();
			try {
				SessionServiceLocator.getPuntosTipoProductoSessionService().savePuntosTipoProducto(puntosTipoProductoIf);
				SpiritAlert.createAlert("Valor de Puntos por Tipo de producto grabado con éxito!!!", SpiritAlert.INFORMATION);
				 
			} catch (GenericBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			clean();
	}
	
	
	public PuntosTipoProductoIf registrarPuntosTipoProducto(){
		PuntosTipoProductoData puntosDatos = new PuntosTipoProductoData();		
		Long tipoProductoId=((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem()).getId();
		TarjetaTipoIf tarjetatipo=(TarjetaTipoIf) this.getCmbTipoTarjetaAfiliacion().getSelectedItem();		
		puntosDatos.setTipoProductoId(tipoProductoId);
		puntosDatos.setTarjetaTipoId(tarjetatipo.getId());
		puntosDatos.setPuntosCompras(new BigDecimal(getTxtPuntosPropietario().getText()));
		puntosDatos.setPuntosReferido(new BigDecimal(getTxtPuntosReferido().getText()));		
		return puntosDatos;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

 
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

 
	public void showFindMode() {
		// TODO Auto-generated method stub
		
	}

	 
	public void showSaveMode() {
		// TODO Auto-generated method stub
		
	}
 	 
	public void showUpdateMode() {
		// TODO Auto-generated method stub
		
	}

	 
	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

}
