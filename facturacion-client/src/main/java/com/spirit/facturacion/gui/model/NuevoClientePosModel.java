package com.spirit.facturacion.gui.model;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.entity.ClienteRetencionIf;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.gui.panel.JDNuevoCliente;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.util.DateHelperClient;
import com.spirit.util.NumberTextField;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.ValidarIdentificacion;


public class NuevoClientePosModel extends JDNuevoCliente {
	private static final long serialVersionUID = 1L;
	private TipoIdentificacionIf tipoIdentificacionIf;
	public String identificacion = "";
	boolean aceptar = false;
	private static final int MAX_LONGITUD_CEDULA = 10;
	private static final int MAX_LONGITUD_RUC = 13;
	private static final int MAX_LONGITUD_RAZON_SOCIAL = 80;
	private static final int MAX_LONGITUD_DIRECCION_OFICINA = 150;
	private static final int MAX_LONGITUD_TELEFONO_OFICINA = 10;
	private static final int MAX_LONGITUD_EMAIL_OFICINA = 50;
	private static final int MAX_LONGITUD_EMAIL_CONTACTO = 50;
	private ClienteOficinaIf clienteOficinaTemp ;
	private ClienteIf cliente;

	public NuevoClientePosModel(Frame owner,String cedulaCliente) {
		super(owner);
		iniciarComponentes();
		initKeyListeners();
		setName("Nuevo Cliente");
		String cedula=cedulaCliente;
		if(cedula==null) cedula="";
		getTxtIdentificacionCliente().setText(cedula);
		addWindowListener(new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				getCmbTipoIdentificacionCliente().requestFocus();
				if (getCmbTipoIdentificacionCliente().getItemCount()>0)
					getCmbTipoIdentificacionCliente().setSelectedIndex(0);
			}
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});		
	}

	private void iniciarComponentes(){
		getBtnAceptar().addActionListener(oActionListenerBotonAceptar);		
		getBtnCancelar().addActionListener(oActionListenerBotonCancelar);		
		SpiritComboBoxModel cmbModelTipoIdentificacionCliente;
		try {
			cmbModelTipoIdentificacionCliente = new SpiritComboBoxModel((ArrayList) SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList());
			getCmbTipoIdentificacionCliente().setModel(cmbModelTipoIdentificacionCliente);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}

		getCmbTipoIdentificacionCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoIdentificacionIf = (TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem();
				for (int i=0; i<getTxtIdentificacionCliente().getKeyListeners().length; i++) {
					KeyListener keyListener = getTxtIdentificacionCliente().getKeyListeners()[i];
					getTxtIdentificacionCliente().removeKeyListener(keyListener);
				}	

				if (tipoIdentificacionIf != null) {
					if (tipoIdentificacionIf.getCodigo().equals("CE")) {
						if (getTxtIdentificacionCliente().getText().length() > MAX_LONGITUD_CEDULA)
							getTxtIdentificacionCliente().setText("");
						getTxtIdentificacionCliente().addKeyListener(new TextChecker(MAX_LONGITUD_CEDULA));
					} else if (tipoIdentificacionIf.getCodigo().equals("RU") || tipoIdentificacionIf.getCodigo().equals("PA")) {
						if (getTxtIdentificacionCliente().getText().length() > MAX_LONGITUD_RUC)
							getTxtIdentificacionCliente().setText("");
						getTxtIdentificacionCliente().addKeyListener(new TextChecker(MAX_LONGITUD_RUC));
					}
					getTxtIdentificacionCliente().setEnabled(true);
					getTxtRazonSocialCliente().grabFocus();
				}
			}
		});
	}

	private Long getCorporacionId() {
		Long corporacionId = 0L;		
		CorporacionIf corporacionIf = null;
		Iterator it = null;
		try {
			it = SessionServiceLocator.getCorporacionSessionService().findCorporacionByCodigo("SICO").iterator();
			if(it.hasNext()) {
				corporacionIf = (CorporacionIf) it.next();	
				corporacionId = corporacionIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return corporacionId;
	}

	
	private Long getTipoProveedorId(){
		Long tipoProveedorId = 0L;		
		TipoProveedorIf tipoProveedorIf = null;
		Iterator iterador = null;
		try {
			//iterador = SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByCodigo("CL").iterator();
			Map parameterMap = new HashMap();	            
			parameterMap.put("codigo", "NN");
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			iterador = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByQuery(parameterMap).iterator();
			
			if(iterador.hasNext()) {
				tipoProveedorIf = (TipoProveedorIf) iterador.next();	
				tipoProveedorId = tipoProveedorIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return tipoProveedorId;
	}
	
	
	private Long getTipoClienteId(){
		Long tipoClienteId = 0L;		
		TipoClienteIf tipoClienteIf = null;
		Iterator iterador = null;
		try {
			iterador = SessionServiceLocator.getTipoClienteSessionService().findTipoClienteByCodigo("CL").iterator();
			if(iterador.hasNext()) {
				tipoClienteIf = (TipoClienteIf) iterador.next();	
				tipoClienteId = tipoClienteIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return tipoClienteId;
	}

	private ClienteIf registrarCliente() throws GenericBusinessException {
		ClienteData data = new ClienteData();
		TipoClienteIf tipoCliente = null;
		data.setIdentificacion(getTxtIdentificacionCliente().getText());
		data.setTipoidentificacionId(((TipoIdentificacionIf) getCmbTipoIdentificacionCliente().getSelectedItem()).getId());
		data.setNombreLegal(getTxtRazonSocialCliente().getText());
		data.setRazonSocial(getTxtRazonSocialCliente().getText());
		Iterator it = SessionServiceLocator.getTipoProveedorSessionService().findTipoProveedorByCodigo("NN").iterator();
			
		
		Long vacio=0L;
		Long tipoproveedor=null;				
		tipoproveedor=getTipoProveedorId();
		if(tipoproveedor!=null && tipoproveedor != vacio) data.setTipoproveedorId(tipoproveedor);
		
		if(getCorporacionId() != vacio) data.setCorporacionId(getCorporacionId());
		data.setEstado("A");
		data.setFechaCreacion(DateHelperClient.getTimeStampNow());	
		if (getTipoClienteId() != vacio) 
			data.setTipoclienteId(getTipoClienteId());
		data.setContribuyenteEspecial("N");
		data.setTipoPersona("N");
		data.setLlevaContabilidad("N");
		data.setObservacion("grabado en pedido");
		data.setUsuariofinal("N");
		return data;
	}

	public void initKeyListeners(){
		getTxtRazonSocialCliente().addKeyListener(new TextChecker(MAX_LONGITUD_RAZON_SOCIAL, 1));
		getTxtDireccionClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_DIRECCION_OFICINA, 1));
		getTxtTelefonoClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_TELEFONO_OFICINA));
		getTxtTelefonoClienteOficina().addKeyListener (new NumberTextField());
		getTxtEmailClienteOficina().addKeyListener(new TextChecker(MAX_LONGITUD_EMAIL_OFICINA, 2));
	}

	public boolean validateFields() {	 
		if (getCmbTipoIdentificacionCliente().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar un tipo de identificación para cliente!!!", SpiritAlert.WARNING);
			getCmbTipoIdentificacionCliente().grabFocus();			
			return false;
		}

		if ("".equals(this.getTxtIdentificacionCliente().getText())) {
			SpiritAlert.createAlert("Debe ingresar una identificación para el cliente!!!", SpiritAlert.WARNING);
			getTxtIdentificacionCliente().grabFocus();			
			return false;
		}

		String tipoIdentificacion = ((TipoIdentificacionIf) this.getCmbTipoIdentificacionCliente().getSelectedItem()).getCodigo();		
		if (tipoIdentificacion != null) {
			if (tipoIdentificacion.equals("CE")) {
				if (getTxtIdentificacionCliente().getText().trim().length() < MAX_LONGITUD_CEDULA) {
					SpiritAlert.createAlert("El número de cédula del cliente debe tener " + MAX_LONGITUD_CEDULA + " dígitos!!!", SpiritAlert.WARNING);
					getTxtIdentificacionCliente().grabFocus();				
					return false;
				}
			} else if (tipoIdentificacion.equals("RU")) {
				if (getTxtIdentificacionCliente().getText().trim().length() < MAX_LONGITUD_RUC) {
					SpiritAlert.createAlert("El RUC del cliente debe tener " + MAX_LONGITUD_RUC + " dígitos!!!", SpiritAlert.WARNING);
					getTxtIdentificacionCliente().grabFocus();					
					return false;
				}
			}			
			if (tipoIdentificacion.equals("CE") || tipoIdentificacion.equals("RU")) {
				if (!ValidarIdentificacion.esNumeroIdentificacionValido(tipoIdentificacion, getTxtIdentificacionCliente().getText())) {
					SpiritAlert.createAlert("La identificación ingresada no es válida!!!", SpiritAlert.WARNING);
					getTxtIdentificacionCliente().grabFocus();				
					return false;
				}
			}			
			Collection operadores = null;
			boolean codigoRepetido = false;
			try {				
				Long empresaId=Parametros.getIdEmpresa();
				operadores = SessionServiceLocator.getClienteSessionService().findClienteByEmpresaId(empresaId);			
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			Iterator operadoresIt = operadores.iterator();
			while (operadoresIt.hasNext()) {
				ClienteIf operadorIf = (ClienteIf) operadoresIt.next();		
				Long id2=((TipoIdentificacionIf) this.getCmbTipoIdentificacionCliente().getSelectedItem()).getId();				
				if (id2.compareTo(operadorIf.getTipoidentificacionId()) == 0 && getTxtIdentificacionCliente().getText().equals(operadorIf.getIdentificacion()))
					codigoRepetido = true;				
			}

			if (codigoRepetido) {
				SpiritAlert.createAlert("El número de identificación del cliente está duplicado !!", SpiritAlert.WARNING);
				getTxtIdentificacionCliente().grabFocus();				
				return false;
			}
		}


		if ("".equals(this.getTxtRazonSocialCliente().getText())) {
			SpiritAlert.createAlert("Debe ingresar el nombre del cliente!!!", SpiritAlert.WARNING);
			getTxtRazonSocialCliente().grabFocus();			
			return false;
		}
		
		return true;
	}

	ActionListener oActionListenerBotonAceptar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			guardar();
		}
	};

	ActionListener oActionListenerBotonCancelar= new ActionListener() {
		public void actionPerformed(ActionEvent evento) {
			dispose();
			aceptar=true;
		}
	};

	private Long getciudadCliente_ID(){
		Long referenciaId = 0L;		
		CiudadIf ciudadIf = null;
		Iterator iterador = null;
		try {
			iterador = SessionServiceLocator.getCiudadSessionService().findCiudadByCodigo("GYE").iterator();
			if(iterador.hasNext()) {
				ciudadIf	 = (CiudadIf) iterador.next();	
				referenciaId=ciudadIf.getId();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		return referenciaId;
	}

	public void guardar() {
		if (validateFields()) {
			try {
				ClienteIf cliente = registrarCliente();
				Vector<ClienteOficinaIf> detalleOficinaClienteColeccion = new Vector<ClienteOficinaIf>();
				Vector<ClienteZonaIf> clienteZonaColeccion = new Vector<ClienteZonaIf>();
				Vector<ClienteRetencionIf> clienteRetencionColeccion = new Vector<ClienteRetencionIf>();
				ClienteOficinaData data = new ClienteOficinaData();
				String codigo = "001";
				data.setCodigo(codigo);				 
				data.setFechaCreacion(DateHelperClient.getTimeStampNow());
				data.setEstado("A");
				data.setDescripcion(getTxtRazonSocialCliente().getText());
				String direccion = getTxtDireccionClienteOficina().getText();
				data.setDireccion(!direccion.trim().equals("")?direccion:"S/D");
				data.setCiudadId(getciudadCliente_ID());
				String telefono = getTxtTelefonoClienteOficina().getText();
				data.setTelefono(!telefono.trim().equals("")?telefono:"S/N");
				data.setEmail(getTxtEmailClienteOficina().getText());
				data.setCalificacion("A");
				detalleOficinaClienteColeccion.add(data);
				Map detalleContactoOficinaClienteMap = new HashMap();
				Map detalleIndicadorOficinaClienteMap = new HashMap();
				SessionServiceLocator.getClienteSessionService().procesarCliente(cliente, clienteZonaColeccion, clienteRetencionColeccion, detalleOficinaClienteColeccion, detalleContactoOficinaClienteMap, detalleIndicadorOficinaClienteMap,false);
				Iterator it = SessionServiceLocator.getClienteSessionService().findClienteByIdentificacion(cliente.getIdentificacion()).iterator();
				if (it.hasNext())
					this.cliente = (ClienteIf) it.next();
				identificacion=getTxtIdentificacionCliente().getText();
				SpiritAlert.createAlert("Cliente nuevo guardado con éxito", SpiritAlert.INFORMATION);
				aceptar=true;
				dispose();
			} catch (Exception e) {
				e.printStackTrace();
				identificacion = "";
				SpiritAlert.createAlert("Ocurrió un error al guardar el Operador de Negocio", SpiritAlert.ERROR);
			}
		}
	}

	public boolean isAceptar() {
		return aceptar;
	}

	public void setAceptar(boolean aceptar) {
		this.aceptar = aceptar;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public ClienteIf getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIf cliente) {
		this.cliente = cliente;
	}
}
