package com.spirit.pos.gui.model;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.entity.CajasesionMovimientosData;
import com.spirit.pos.gui.panel.JPMovimientosCaja;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.Utilitarios;
 

public class MovimientosCajaModel extends JPMovimientosCaja {
	private DefaultTableModel tableModel;
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	Long cajaAbiertaID;
	Long oficinaCajaRegistro; 
	private boolean cajaAbierta = false;
	private AutorizacionModel jdAutorizacionModel;
	protected EmpleadoIf empleado;

	public MovimientosCajaModel(){		
		initKeyListeners();			 
		clean(); 	 	
		new HotKeyComponent(this);	 
	}

	public void initKeyListeners(){ 
		getPnBanco().setEnabled(false);		
		getPnBanco().setVisible(false);
		getPnCajas().setVisible(false);
		getPnCajas().setEnabled(false);
		getCmbCajaRegistro().setEditable(false);
		getCmbCajaRegistro().setEnabled(false);
		getTxtValor().addKeyListener(new NumberTextFieldDecimal()); 
		getTxtFecha().setText(Utilitarios.fechaAhora());			
		getTxtCajero().setText(Parametros.getUsuario());		
		getCmbMotivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (!(getCmbMotivo().getSelectedItem()==null))
					activarPaneles();
			}
		});

		getCmbBanco().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(getCmbBanco().getSelectedItem()==null)) 
					cargarCuentas();
			}
		});

		getCmbTipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if(getCmbTipo().getSelectedItem() != null){
					getTxtValor().setText("");
					getTxtValor().grabFocus();
				}
			}
		});
	}


	WindowListener wl = new WindowAdapter() {
		public void windowClosed(WindowEvent e) {
			jdAutorizacionModel = null;
			System.gc();
		}		
	};

	public void activarPaneles(){
		if(getCmbMotivo().getSelectedItem().equals("Transferencia Bancaria")){
			getPnBanco().setEnabled(true);
			getPnBanco().setVisible(true);
			getPnCajas().setVisible(false);
			getPnCajas().setEnabled(false);
		}
		if(getCmbMotivo().getSelectedItem().equals("Transferencia Caja")){
			getPnBanco().setEnabled(false);		
			getPnBanco().setVisible(false);
			getPnCajas().setVisible(true);
			getPnCajas().setEnabled(true);
		}
	}

	private void cargarComboCaja(){
		try {
			List cajasesion = (List) SessionServiceLocator.getCajaSessionService().findCajaByOficinaId(Parametros.getIdOficina());
			refreshCombo(getCmbCajaRegistro(),cajasesion);
		} catch (GenericBusinessException e1) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e1.printStackTrace();
		}
	}

	public void cargarBancos() {
		SpiritComboBoxModel cmbModelBancos;		 
		try {
			cmbModelBancos = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getBancoSessionService().findBancoByEstado("A"));			
			getCmbBanco().setModel(cmbModelBancos);		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}

	}	

	private void cargarCajasDestino() {
		Vector<String> oficinasIdsVector = new Vector<String>();
		Iterator cajaValorIt;
		try {
			cajaValorIt = SessionServiceLocator.getOficinaSessionService().findOficinaByCodigo("MATRIZ").iterator();
			while (cajaValorIt.hasNext()) {
				OficinaIf valorActual = (OficinaIf) cajaValorIt.next();	
				oficinasIdsVector.add(valorActual.getId().toString());				
			}			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}	
		oficinasIdsVector.add(new Long(Parametros.getIdOficina()).toString());		
		//En un vector guardo el id de la oficina matriz, mas el id de la oficina sucursal.
		Map aMap = new HashMap();
		aMap.clear();		
		aMap.put("oficinaId",oficinasIdsVector);
		aMap.put("estado","A");		
		SpiritComboBoxModel cmbModelCajaDestino = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getCajaSessionService().findCajaByQueryVariosOficinaId(aMap));
		getCmbCajaDestino().setModel(cmbModelCajaDestino);		
	}

	public void cargarCuentas(){
		BancoIf tempo=(BancoIf) this.getCmbBanco().getSelectedItem();	
		if(tempo!=null){
			SpiritComboBoxModel cmbModelCuentas;		 
			try {
				cmbModelCuentas = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByBancoId(tempo.getId()));				
				getCmbCuenta().setModel(cmbModelCuentas);		
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}

	public void getCajaAbiertaId(){
		Long cajaSessionId = new Long("0");
		Long oficinaCajaRegistroId = new Long("0");
		Long usuarioId = ((UsuarioIf)Parametros.getUsuarioIf()).getId();		
		Map aMap = new HashMap();
		aMap.clear();
		aMap.put("usuarioId", usuarioId);
		aMap.put("estado", "A");
		aMap.put("fechafin", null);
		Iterator cajaValorIt;
		try {
			cajaValorIt = SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).iterator();
			if (cajaValorIt.hasNext()) {
				CajaSesionIf valorActual = (CajaSesionIf) cajaValorIt.next();
				BigDecimal valor = valorActual.getValorInicial();				 	 
				cajaSessionId = valorActual.getId();
				oficinaCajaRegistroId = valorActual.getCajaId();
				getCmbCajaRegistro().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCajaRegistro(), oficinaCajaRegistroId));
				getCmbCajaRegistro().setEnabled(false);
			} else
				SpiritAlert.createAlert("Debe tener una caja abierta",SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	
		this.cajaAbiertaID = cajaSessionId;
		this.oficinaCajaRegistro = oficinaCajaRegistroId;
	}

	public void clean() {		
		cargarComboCaja();
		activarPaneles();		
		cargarBancos();
		
		if(getCmbBanco().getSelectedObjects().length>0)			getCmbBanco().setSelectedIndex(0);
		
		cargarCuentas();		
		cargarCajasDestino();
		
		getTxtAviso().setVisible(false);		
		getTxtValor().setText("");
		getTxtObservacion().setText("");
		//Tomamos el valor de la cajasessionId
		getCajaAbiertaId();		
		UsuarioIf usuario = ((UsuarioIf) Parametros.getUsuarioIf());
		Long usuarioId = usuario.getId();				 	
		try {
			//No tiene caja ACTIVA
			if(this.cajaAbiertaID.equals(new Long("0"))) {   			
				//Revisamos si el usuario no tiene session de bloqueada la caja
				getTxtAviso().setVisible(true);
				Map aMap = new HashMap();
				aMap.clear();
				aMap.clear();
				aMap.put("usuarioId", usuarioId);
				aMap.put("estado", "B");		
				getTxtAviso().setVisible(true);
				getTxtValor().setEnabled(false);
				getTxtAviso().setText("El usuario NO tiene una CAJA ABIERTA ");				
				if(!SessionServiceLocator.getCajaSesionSessionService().findCajaSesionByQuery(aMap).isEmpty()){
					getTxtAviso().setText("El usuario tiene una Caja con session BLOQUEADA, debe primero DESBLOQUEAR la CAJA para poder efectuar movimientos.");								
				}
				//SpiritAlert.createAlert("Debe existir una CAJA ABIERTA y ACTIVA!!", SpiritAlert.INFORMATION);
			}
			else{				
				cajaAbierta=true;//el usuario tiene una caja ABIERTA entonces si puede hacer MOVIMIENTOS
				//UsuarioIf usergeneral = (UsuarioIf) getUsuarioSessionService().findUsuarioByUsuario(Parametros.getUsuario().toLowerCase()).iterator().next();
				Long empleadoId= usuario.getEmpleadoId();	
				HashMap<String, Object> mapa = new HashMap<String, Object>();
				mapa.clear();
				mapa.put("id" , empleadoId );			
				Iterator vendedoresIt = SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(mapa).iterator();
				empleado = (EmpleadoIf) vendedoresIt.next();	
				getTxtCajero().setText(empleado.getApellidos()+" "+empleado.getNombres());
				showSaveMode();
				getTxtAviso().setVisible(false);
			}	
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete() {
		// TODO Auto-generated method stub
	}
	
	public CajasesionMovimientosData registrarMovimiento(){		
		CajasesionMovimientosData cajasesionMovdata= new CajasesionMovimientosData();		
		cajasesionMovdata.setCajasesionId(cajaAbiertaID);
		cajasesionMovdata.setValor(new BigDecimal(Utilitarios.removeDecimalFormat(getTxtValor().getText())));
		if(getCmbMotivo().getSelectedItem().equals("Transferencia Bancaria")){
			CuentaBancariaIf tempo=(CuentaBancariaIf) this.getCmbCuenta().getSelectedItem();		
			if(tempo!=null)	cajasesionMovdata.setCuentaId(tempo.getId());
			else cajasesionMovdata.setCuentaId(null);
		}		
		if(getCmbMotivo().getSelectedItem().equals("Transferencia Caja")){
			CajaIf tempo2=(CajaIf) this.getCmbCajaRegistro().getSelectedItem();		
			if(tempo2!=null) cajasesionMovdata.setCajadestinoId(tempo2.getId());
			else cajasesionMovdata.setCajadestinoId(null);
		}
		String tipo_mov="";
		if(getCmbTipo().getSelectedIndex()==0) 			tipo_mov="I";
		if(getCmbTipo().getSelectedIndex()==1) 			tipo_mov="E";		
		cajasesionMovdata.setTipomovimiento(tipo_mov);
		cajasesionMovdata.setObservacion(getTxtObservacion().getText()); 
		
		//cajasesionMovdata.setFecha(new java.sql.Timestamp(new Timestamp().getDateTime()));
		cajasesionMovdata.setFecha(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		
		return cajasesionMovdata;
	}

	public void save() {
		try {			
			if (validateFields()){				
				//GRABAMOS EL MOVIMIENTO
				jdAutorizacionModel = new AutorizacionModel(Parametros.getMainFrame());
				jdAutorizacionModel.addWindowListener(wl);
				int x = (Toolkit.getDefaultToolkit().getScreenSize().width - 730) / 2;
				int y = (Toolkit.getDefaultToolkit().getScreenSize().height - 650) / 2;
				jdAutorizacionModel.setLocation(x, y);
				jdAutorizacionModel.pack();
				jdAutorizacionModel.setModal(true);
				jdAutorizacionModel.setVisible(true);
				if(jdAutorizacionModel.correctousr) {
					CajasesionMovimientosData movimientos = registrarMovimiento();	
					SessionServiceLocator.getCajasesionMovimientosSessionService().saveCajasesionMovimientos(movimientos);					
					HashMap<String, BigDecimal> mapaAsientos=new HashMap<String, BigDecimal>();					
					BigDecimal valor=BigDecimal.ZERO;			
					//ingresos: ya sea por TX BANCARIA: IBM ó TX CAJA: ITM
					if(movimientos.getTipomovimiento().equals("I")) {
						//IBM: SALE BANCO ENTRA AL MALL
						if(movimientos.getCuentaId()!=null){
							valor=movimientos.getValor();
							mapaAsientos.put("IBM", valor);
						}
						//ITM: SALE DE LA CAJA MATRIZ ENTRA AL MALL
						if(movimientos.getCajadestinoId()!=null){
							valor=movimientos.getValor();							
							mapaAsientos.put("ITM", valor);
						}
					}					
					//Egresos: ya sea por TX BANCARIA: EMB ó TX CAJA: EMT
					if(movimientos.getTipomovimiento().equals("E")) {
						//IBM: SALE DEL MALL ENTRA AL BANCO
						if(movimientos.getCuentaId()!=null){
							valor=movimientos.getValor();
							mapaAsientos.put("EMB", valor);
						}
						//ITM: SALE DE LA CAJA MALL ENTRA AL MATRIZ
						if(movimientos.getCajadestinoId()!=null){
							valor=movimientos.getValor();							
							mapaAsientos.put("EMT", valor);
						}
					}
					SessionServiceLocator.getCajaSesionSessionService().generarAsientos(mapaAsientos, Parametros.getIdEmpresa(), Parametros.getIdOficina(),empleado.toString());
					SpiritAlert.createAlert("Movimiento guardado \u00e9xito",SpiritAlert.INFORMATION);
					cajaAbierta=true;	
					clean();
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}		
	}

	public void showSaveMode() {
		setSaveMode();
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public boolean validateFields() {
		boolean bandera=true;
		if (getTxtValor().getText().equals("")){
			SpiritAlert.createAlert("Ingrese el valor del movimiento", SpiritAlert.INFORMATION);			 
			getTxtValor().grabFocus();
			bandera=false;
		}
		if(getCmbMotivo().getSelectedItem().equals("Transferencia Bancaria")){			
			CuentaBancariaIf tempo=(CuentaBancariaIf) this.getCmbCuenta().getSelectedItem();	
			System.out.println("tempo!!!"+tempo);
			if(tempo==null) {
				bandera=false;	
				SpiritAlert.createAlert("Debe seleccionar el Banco y la Cuenta Bancaria", SpiritAlert.INFORMATION);		
			}
		}
		if(getCmbMotivo().getSelectedItem().equals("Transferencia Caja")){
			CajaIf tempo2=(CajaIf) this.getCmbCajaDestino().getSelectedItem();	
			System.out.println("tempo2!!!"+tempo2);
			if(tempo2==null){
				bandera=false;
				SpiritAlert.createAlert("Debe seleccionar la caja con la que desea hacer el movimiento.", SpiritAlert.INFORMATION);		
			}else{
				Long idpcdestino=tempo2.getId();
				if(idpcdestino.equals(oficinaCajaRegistro)) {
					bandera=false;
					SpiritAlert.createAlert("Debe seleccionar una Caja destino diferente a la Caja Origen.", SpiritAlert.INFORMATION);	
				}
			}

		}

		return bandera;
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
		
	}

	public void find() {
		// TODO Auto-generated method stub
		
	}

	public void report() {
		// TODO Auto-generated method stub
		
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
		
		clean();
		
	}

	public void showFindMode() {
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
