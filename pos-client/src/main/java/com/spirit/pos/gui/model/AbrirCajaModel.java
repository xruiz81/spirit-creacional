package com.spirit.pos.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionData;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.gui.panel.JPAbrirCaja;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.Utilitarios;
 

public class AbrirCajaModel extends JPAbrirCaja{

	private boolean cajaActivar=false;
	 

	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
 
	Long cajaAbiertaID; 
	Vector<CajaSesionIf> cajaPosVector = new Vector<CajaSesionIf>();
	CajaSesionIf cajasesionif;	
	public Long idprincipal=new Long("0");
	 
	public AbrirCajaModel(){
		initKeyListeners();		 
		initListeners(); 
		clean();
		mostrarDatos();
		new HotKeyComponent(this);	 
	}

	private void initListeners(){
		getBtnActivarCaja().addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent evento) { 	activar();}	}
		);		
	}

	public void initKeyListeners(){	
		
		getLblFecha().setText(Utilitarios.fechaAhora());			
		getLblCajero().setText(Parametros.getUsuario());
		cargarComboCaja();
		if(getCmbPC().getSelectedObjects().length>0)	getCmbPC().setSelectedIndex(0);		
		
		
		
		getCmbPC().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
		
				if (!(getCmbPC().getSelectedItem()==null))
				{ 
					mostrarDatos();			
				}
			}
		});
	}
	
	
	private void cargarComboCaja(){
		try {
			System.out.println("En carga combo caja de solo x oficina"+Parametros.getIdOficina());
			List listaPrecios = (List) SessionServiceLocator.getCajaSessionService().findCajaByOficinaId(Parametros.getIdOficina());
			refreshCombo(getCmbPC(),listaPrecios);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}


	
	
	public void mostrarDatos() {
		// TODO Auto-generated method stub
		getTxtImporte().setEnabled(false);
		getBtnActivarCaja().setEnabled(false);
		Long caja_id=new Long("0");
		Map aMap = new HashMap();
		aMap.clear();
		Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();
		
		System.out.println("USUARIO ID>"+usuario_id);
 		try{
		getTextPane1().setVisible(false);		
		aMap.put("usuarioId", usuario_id);		
		aMap.put("fechafin",null);
		
		Iterator cajavalorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
		boolean abierta=false;
		boolean bloqueada=false;
	
		if (cajavalorIt.hasNext()) {		
		while (cajavalorIt.hasNext()) {					
			CajaSesionIf cajasesionif_existe = (CajaSesionIf) cajavalorIt.next();
			String estadoCaja=cajasesionif_existe.getEstado();
			
			if(estadoCaja.equals("A")){
				abierta=true;
				cajaActivar=false;//el usuario tiene una caja ABIERTA
				getTextPane1().setVisible(false);
				getTextPane1().setText("El usuario tiene una Caja Abierta debe primero CERRARLA para poder abrir una nueva");
				BigDecimal valor=cajasesionif_existe.getValorInicial();
				String fecha_ini=cajasesionif_existe.getFechaini().toString();
				getLblFecha().setText(fecha_ini);					 
				getTxtImporte().setText(formatoDecimal.format(valor));					
				caja_id=cajasesionif_existe.getId();
				Long cajaPc_id=cajasesionif_existe.getCajaId();
				getCmbPC().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPC(), cajaPc_id));
				break;			
			}
			if(estadoCaja.equals("B")){
				bloqueada=true;
				getBtnActivarCaja().setEnabled(false);
				System.out.println("6****");
				getTextPane1().setVisible(true);
				getTextPane1().setText("El usuario tiene una Caja con session BLOQUEADA, debe primero CERRAR la CAJA para poder abrir una nueva ");							
				getCmbTurno().setEnabled(false);					
				BigDecimal valor=cajasesionif_existe.getValorInicial();
				String fecha_ini=cajasesionif_existe.getFechaini().toString();
				getLblFecha().setText(fecha_ini);
				getTxtImporte().setText(valor.toString());
				caja_id=cajasesionif_existe.getId();	
				Long cajaPc_id=cajasesionif_existe.getCajaId();
				getCmbPC().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPC(), cajaPc_id));				
				cajaActivar=false;	
				break;
			}
		}		
		}
		else{
			//es vacio con ese usuario y fecha null no existe
			
			getBtnActivarCaja().setEnabled(true);
			showSaveMode();		//no hay caja abierta para dicho usuario
			cajaActivar=true;//ese usuario tiene NO TIENE CAJA CON SESSION ABIERTA	entonces SI PUEDE ACTIVARLA Y ACTUALIZAR REG. DATOS DEL USUARIO				
			getTextPane1().setText("");
			getTextPane1().setVisible(false);		
		 	
			Long cajaPC_id=(this.getCmbPC().getSelectedItem() != null)?((CajaIf) this.getCmbPC().getSelectedItem()).getId():null;
			
			aMap.clear();
			aMap.put("usuarioId", null);
			aMap.put("cajaId", cajaPC_id);
			aMap.put("estado", "I");
			aMap.put("fechafin",null);
			
			Iterator cajalistaActivar = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajalistaActivar.hasNext()) {
				//aqui la deja lista para que la active 
				cajasesionif = (CajaSesionIf) cajalistaActivar.next();
				BigDecimal valor=cajasesionif.getValorInicial();
				getTxtImporte().setText(formatoDecimal.format(valor));					
				caja_id=cajasesionif.getId();						 
			}
			else{											
				//PUEDE QUE SI HAYAN DATOS PERO DE OTRO USUARIO Y YA ESTEN ACTIVOS
				aMap.clear();					
				aMap.put("cajaId", cajaPC_id);	
				aMap.put("estado", "A");
				Iterator cajavalorIt8 = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
				if (cajavalorIt8.hasNext()) {
					
					getTxtImporte().setText("");
					getTextPane1().setText("La caja ya ha sido abierta por otro usuario, dos usuarios no pueden asociarse a una caja.");
					getTextPane1().setVisible(true);	
					cajaActivar=false;
					getBtnActivarCaja().setEnabled(false);
				}
				else{					
				aMap.clear();					
				aMap.put("cajaId", cajaPC_id);						
				Iterator cajavalorIt2 = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
				if (!cajavalorIt2.hasNext()) {
					//no existe ningun dato anterior de esa caja... es la primera vez que se la usará
					cajaActivar=true;
					getTxtImporte().setEnabled(true);
					getTxtImporte().setEditable(true); 
				}
				else{		
					getTxtImporte().setText("");
					getTextPane1().setText("Asegurese que la caja anterior se cerró exitosamente");
					getTextPane1().setVisible(true);	
					cajaActivar=false;
					getBtnActivarCaja().setEnabled(false);
					}
				}
			}	
			
			
		}
		 
					
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.cajaAbiertaID=caja_id;
		idprincipal=new Long("0");
	}
	
	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}
	

	
	public void activar(){
		save();		
	}

	public void save() {
		boolean bandera=false;
		
		Crono cr4=new Crono();
		cr4.inicializa();
		Long usuarioId=((UsuarioIf)Parametros.getUsuarioIf()).getId();	
	
		
		if(cajaActivar){
	
	
			
			if (getTxtImporte().getText().equals("")){
				SpiritAlert.createAlert("No tiene valor inicial la caja. Deberia tener al menos valor 0.00 !!!", SpiritAlert.INFORMATION);
				bandera=true;
				getTxtImporte().grabFocus();
			}		
			if(!bandera)
			{
				if(getTxtImporte().isEnabled())
				{
					//aqui tomamos todos datos Usuario,Valor, fecha etc.etc
					cajasesionif= registrarCajaRegistradora(usuarioId);					
					try {
						//getCajaSesionSessionService().saveCajaSesion(cajasesionif);	
						SessionServiceLocator.getCajaSesionSessionService().crearCaja(cajasesionif);
						SpiritAlert.createAlert("Caja ACTIVADA con éxito",SpiritAlert.INFORMATION);
						getBtnActivarCaja().setEnabled(false);
						cajaActivar=false;
						getTxtImporte().setEnabled(false);				
					} catch (GenericBusinessException e) {
						e.printStackTrace();
					}
				}
				else{

					cajasesionif.setUsuarioId(usuarioId);
					//cajasesionif.setFechaini(new java.sql.Timestamp(new Timestamp().getDateTime()));
					cajasesionif.setFechaini(new java.sql.Timestamp(new java.util.Date().getTime()));
					
					cajasesionif.setTurno("A");
					cajasesionif.setEstado("A");
					
					
					try {	
						SessionServiceLocator.getCajaSesionSessionService().activarCaja(cajasesionif);
						SpiritAlert.createAlert("Caja ACTIVADA con éxito",SpiritAlert.INFORMATION);
						getBtnActivarCaja().setEnabled(false);
						cajaActivar=false;
						getTxtImporte().setEnabled(false);
						
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						}
					}	
				}
		}
		else
			SpiritAlert.createAlert("No se puede guardar la Caja, porque ya existe una Caja Abierta o Activa !!!", SpiritAlert.INFORMATION);	
	 
	 
	}
	
	 
	
	public CajaSesionIf registrarCajaRegistradora(Long usuarioId){
		CajaSesionData cajaPosn = new CajaSesionData();	
		
		//Long usuario_id=((UsuarioIf)Parametros.getUsuarioIf()).getId();		
		Long cajaPC_id=((CajaIf) this.getCmbPC().getSelectedItem()).getId();		
		String valor_inicial=getTxtImporte().getText();
		cajaPosn.setCajaId(cajaPC_id);	
		cajaPosn.setUsuarioId(usuarioId);
		//cajaPosn.setFechaini(new java.sql.Timestamp(new Timestamp().getDateTime()));
		cajaPosn.setFechaini(new java.sql.Timestamp(new java.util.Date().getTime()));
		cajaPosn.setEstado("A");	 
		cajaPosn.setValorInicial(new BigDecimal(Utilitarios.removeDecimalFormat(valor_inicial)));
		cajaPosn.setTurno(getCmbTurno().getSelectedItem().toString());
		cajaPosn.setTurno("A");		
		return cajaPosn;
	}
	
	
	
	
	@Override
	public void showSaveMode() {
		// TODO Auto-generated method stub
		setSaveMode();
	}

	
	
	
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		mostrarDatos();
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

	
}
