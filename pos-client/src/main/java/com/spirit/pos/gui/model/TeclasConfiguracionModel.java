package com.spirit.pos.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.KeyStroke;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.client.HotKeyComponentPOS;
import com.spirit.pos.entity.TeclasConfiguracionData;
import com.spirit.pos.entity.TeclasConfiguracionIf;
import com.spirit.pos.gui.panel.JPTeclasConfig;
import com.spirit.util.SpiritComboBoxModel;
 

public class TeclasConfiguracionModel extends JPTeclasConfig{
	
	public String teclaNombre="";
	public String mascara="";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeclasConfiguracionModel(){		 
		//showSaveMode();		
		clean();
		 initKeyListeners();
		 initListeners();		
				

		showSaveMode();
		new HotKeyComponentPOS(this);
	}
	
	
	private void initListeners(){		
		llenar_combo();
		llenar_datos();

	}

	public void initKeyListeners(){		
		
	
		
		 getCmbCodigo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if (!(getCmbCodigo().getSelectedItem()==null))
				{ 
					llenar_datos();
				}
			}
		});
		 
		getTxtDatos().addKeyListener(oKeyAdapterSetearNombreTecla);		
		 
	 
	}
	
	 
	
public void llenar_datos(){
	Long idTeclas=((TeclasConfiguracionIf) this.getCmbCodigo().getSelectedItem()).getId();
	getTxtDatos().setText("");
	try {
		Collection<TeclasConfiguracionIf> teclas = SessionServiceLocator.getTeclasConfiguracionSessionService().findTeclasConfiguracionById(idTeclas);
		 if ( teclas.size() == 1 ){
			 TeclasConfiguracionIf data = teclas.iterator().next();
			 teclaNombre=data.getTeclasNombre();
			 getTxtNombreTecla().setText(data.getTeclasNombre());
			 getTaDescripcion().setText(data.getDescripcion());
			 getTxtMascara().setText(data.getMascara());
			 mascara=data.getMascara();
			 
		} 
	} catch (Exception e) {
		e.printStackTrace();
		SpiritAlert.createAlert("Se ha producido un error al consultar la oficina del cliente", SpiritAlert.ERROR);
	}
	
	
}

public TeclasConfiguracionData registrarConfiguracionTecla(){	
	TeclasConfiguracionData confTeclas = new TeclasConfiguracionData();
	confTeclas.setEstado("A");
	confTeclas.setCodigo(((TeclasConfiguracionIf) this.getCmbCodigo().getSelectedItem()).getCodigo());
	confTeclas.setId(((TeclasConfiguracionIf) this.getCmbCodigo().getSelectedItem()).getId());
	confTeclas.setDescripcion(getTaDescripcion().getText());
	confTeclas.setTeclasNombre(getTxtNombreTecla().getText());
	confTeclas.setMascara(getTxtMascara().getText());
	return confTeclas;
}


public void grabando(){	
	String nombreTeclaNuevo=getTxtNombreTecla().getText();
	boolean grabar=false;
	boolean actualizar=false;	
	HashMap<String, Object> mapa = new HashMap<String, Object>();
	mapa.clear();			
	mapa.put("estado" , "A" );
	mapa.put("teclasNombre" , nombreTeclaNuevo );
	Iterator cajavalorIt2;
	try {
		cajavalorIt2 = SessionServiceLocator.getTeclasConfiguracionSessionService().findTeclasConfiguracionByQuery(mapa).iterator();
		if (cajavalorIt2.hasNext()) {			
			TeclasConfiguracionIf valor_actual = (TeclasConfiguracionIf) cajavalorIt2.next();			
			if(nombreTeclaNuevo.equals(valor_actual.getTeclasNombre()))
			{	
				if(nombreTeclaNuevo.equals(teclaNombre))
				{
					actualizar=true;					 			
				}
				else{					 
					actualizar=false;
					SpiritAlert.createAlert("La tecla que se le esta asignando ya esta asociada a otra función. Presione otra tecla",SpiritAlert.ERROR);
					getTxtDatos().grabFocus();
					getTxtNombreTecla().setText("");
			
				} 
			}			
			}
		else{			
			actualizar=true;
		}
	} catch (GenericBusinessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	 
	String codigo= ((TeclasConfiguracionIf) this.getCmbCodigo().getSelectedItem()).getCodigo().toString();
	if(actualizar){
		Collection<TeclasConfiguracionIf> teclas;	
		try {		
			teclas = SessionServiceLocator.getTeclasConfiguracionSessionService().findTeclasConfiguracionByCodigo(codigo);
			if ( teclas.size() == 1 ){
				TeclasConfiguracionIf tecla_sel = teclas.iterator().next();
				TeclasConfiguracionData configuracion = registrarConfiguracionTecla();
				configuracion.setId(tecla_sel.getId());
				SessionServiceLocator.getTeclasConfiguracionSessionService().saveTeclasConfiguracion(configuracion);	
			}				
			SpiritAlert.createAlert("Configuración de teclas actualizada con éxito",SpiritAlert.INFORMATION);	 		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
	}	
}
	
public void llenar_combo(){
	SpiritComboBoxModel cmbModelTipoCliente;	
	Map aMap2 = new HashMap();
	aMap2.clear();		
	aMap2.put("estado","A");	 
		try {
			List lotes = (List)SessionServiceLocator.getTeclasConfiguracionSessionService().findTeclasConfiguracionByQuery(aMap2);
			refreshCombo(getCmbCodigo(),lotes);
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
		grabando();			 
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
 
	
 KeyListener oKeyAdapterSetearNombreTecla = new KeyAdapter() {


		public void keyPressed(KeyEvent e) {
			 						
			String nombre=KeyStroke.getKeyStrokeForEvent(e).toString();
			
			System.out.println("nombre del jeu"+nombre);
			
			int posicion=nombre.indexOf(" ");
			 
			nombre=nombre.substring(posicion).trim();	
			System.out.println("KeyStroke.getKeyStroke EN ALEX!!-->"+KeyStroke.getKeyStroke(nombre));		
			
			getTxtNombreTecla().setText(nombre);
			
		}	 


	};
 
	
}
