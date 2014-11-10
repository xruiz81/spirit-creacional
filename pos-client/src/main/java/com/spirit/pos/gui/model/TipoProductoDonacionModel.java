package com.spirit.pos.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.pos.entity.DonacionTipoproductoData;
import com.spirit.pos.entity.DonacionTipoproductoIf;
import com.spirit.pos.gui.panel.JPTipoProductoDonacion;
import com.spirit.util.NumberTextFieldDecimal;
 
 

public class TipoProductoDonacionModel extends JPTipoProductoDonacion {

	DonacionTipoproductoIf donacionTipoproductoIf;

	public TipoProductoDonacionModel() {
		 
		showSaveMode();
		initListeners();
	 
	}
	
	private void initListeners(){ 
		cargarComboTipoProducto(); 
		
		getCmbTipoProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(getCmbTipoProducto().getSelectedItem()==null)) 
					cargarValor();
			}
		});
		
		getTxtValorItem().addKeyListener(new NumberTextFieldDecimal());		
		
	}
	
	
	public void cargarValor(){
		TipoProductoIf tempo=(TipoProductoIf) this.getCmbTipoProducto().getSelectedItem();	
		if(tempo!=null){			  
			try {				
				Iterator tiposProductoDonacionIterator;				
				ArrayList tiposProducto = (ArrayList)SessionServiceLocator.getDonacionTipoproductoSessionService().findDonacionTipoproductoByTipoproductoId(tempo.getId());
				if(tiposProducto.size()>0){
					tiposProductoDonacionIterator = tiposProducto.iterator();
					int numero = 1;
					if (tiposProductoDonacionIterator.hasNext()) {
						donacionTipoproductoIf = (DonacionTipoproductoIf) tiposProductoDonacionIterator.next();					 	 
						BigDecimal valor=donacionTipoproductoIf.getValor();
						getTxtValorItem().setText(valor.toString());
					}
					else{
						getTxtValorItem().setText("0.00");
					}
				}
				else{
					donacionTipoproductoIf=null;
					getTxtValorItem().setText("0.00");
				}			
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	private void cargarComboTipoProducto(){
		try {
			//List tiposProducto = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoByEmpresaId(Parametros.getIdEmpresa());
			List tiposProducto = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoDonacion();			
			List tiposProducto2 = (List)SessionServiceLocator.getTipoProductoSessionService().findTipoProductoDonacionResto();			
			tiposProducto.addAll(tiposProducto2);			
			/*TipoProductoData tipoTodos = new TipoProductoData();
			tipoTodos.setNombre("TODOS");
			tipoTodos.setCodigo("  ");
			tipoTodos.setId(null);
			tiposProducto.add(0,tipoTodos);*/
			refreshCombo(getCmbTipoProducto(), tiposProducto);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
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
		
		if(donacionTipoproductoIf!=null){
			donacionTipoproductoIf.setValor(new BigDecimal(getTxtValorItem().getText()));		
		}
		else{		
			donacionTipoproductoIf=registrarDonacionTipoProducto();
		}

		try {
			SessionServiceLocator.getDonacionTipoproductoSessionService().saveDonacionTipoproducto(donacionTipoproductoIf);
			SpiritAlert.createAlert("Valor actualizado con éxito!!!", SpiritAlert.INFORMATION);
			 
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DonacionTipoproductoIf registrarDonacionTipoProducto(){
		DonacionTipoproductoData donaDatos = new DonacionTipoproductoData();
		
		Long tipoProductoId=((TipoProductoIf) this.getCmbTipoProducto().getSelectedItem()).getId();		
		System.out.println("NUEBVO TIPO PRDUC ID");
		
		donaDatos.setTipoproductoId(tipoProductoId);
		donaDatos.setValor(new BigDecimal(getTxtValorItem().getText()));
		
		return donaDatos;
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
