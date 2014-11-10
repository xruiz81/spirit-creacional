package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.JUnit4TestAdapter;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.spirit.crm.entity.ClienteData;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class ClienteSessionTest {
	Timestamp now = new Timestamp(System.currentTimeMillis());
	//id=>NUMBER(10)
	//tipoidentificacionId=>NUMBER(10)
	//identificacion=>VARCHAR2(13)
	//nombreLegal=>VARCHAR2(40)
	//razonSocial=>VARCHAR2(40)
	//representante=>VARCHAR2(40)
	//corporacionId=>NUMBER(10)
	//fechaCreacion=>DATE
	//estado=>VARCHAR2(1)
	//tipoclienteId=>NUMBER(10)
	//tipoproveedorId=>NUMBER(10)
	//tiponegocioId=>NUMBER(10)
	//cuentaId=>NUMBER(10)
	//observacion=>VARCHAR2(50)
	//usuariofinal=>VARCHAR2(1)
	//contribuyenteEspecial=>VARCHAR2(1)
	//tipoPersona=>VARCHAR2(1)
	//llevaContabilidad=>VARCHAR2(1)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cliente.xml");
		IDataSet dataSet = new FlatXmlDataSet(file);
		return dataSet;
	}
	
	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(DBUnitConnection.getConnection(), getDataSet());
	}
	
	@After
	public void tearDown() throws Exception {
		DatabaseOperation.DELETE_ALL.execute(DBUnitConnection.getConnection(), getDataSet());
	}
	
	@Test (timeout=2000)
	public void addCliente() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENT1"); 
		value.setNombreLegal("NOMBRE_LEGAL1"); 
		value.setRazonSocial("RAZON_SOCIAL1"); 
		value.setRepresentante("REPRESENTANTE1"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setUsuariofinal("U");
		value.setContribuyenteEspecial("S");
		value.setTipoPersona("N");
		value.setLlevaContabilidad("S");
		
		ClienteIf resultado = getClienteSessionService().addCliente(value);
		
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getIdentificacion(),"IDENT1"); 
		Assert.assertEquals(resultado.getNombreLegal(),"NOMBRE_LEGAL1"); 
		Assert.assertEquals(resultado.getRazonSocial(),"RAZON_SOCIAL1"); 
		Assert.assertEquals(resultado.getRepresentante(),"REPRESENTANTE1"); 
		Assert.assertEquals(resultado.getCorporacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getTipoclienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1));  
		Assert.assertEquals(resultado.getTiponegocioId(),new Long(1));  
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getUsuariofinal(),"U");
		Assert.assertEquals(resultado.getContribuyenteEspecial(),"S"); 
		Assert.assertEquals(resultado.getTipoPersona(),"N"); 
		Assert.assertEquals(resultado.getLlevaContabilidad(),"S"); 
	}
	
	@Test (timeout=2000)
	public void saveCliente() throws Exception {
		ClienteIf value = getClienteSessionService().getCliente(new Long(1));
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENT2"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().saveCliente(value);
		ClienteIf resultado = getClienteSessionService().getCliente(new Long(1));
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getIdentificacion(),"IDENT2"); 
		Assert.assertEquals(resultado.getNombreLegal(),"NOMBRE_LEGAL2"); 
		Assert.assertEquals(resultado.getRazonSocial(),"RAZON_SOCIAL2"); 
		Assert.assertEquals(resultado.getRepresentante(),"REPRESENTANTE2"); 
		Assert.assertEquals(resultado.getCorporacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getTipoclienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1));  
		Assert.assertEquals(resultado.getTiponegocioId(),new Long(1));  
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION2"); 
		Assert.assertEquals(resultado.getUsuariofinal(),"A");  
		Assert.assertEquals(resultado.getContribuyenteEspecial(),"N"); 
		Assert.assertEquals(resultado.getTipoPersona(),"S"); 
		Assert.assertEquals(resultado.getLlevaContabilidad(),"N"); 
	}
	
	@Test (timeout=2000)
	public void deleteCliente() throws Exception {
		getClienteSessionService().deleteCliente(new Long(3));
		ClienteIf resultado = getClienteSessionService().getCliente(new Long(3));
		Assert.assertNull(resultado);
	}
	
	@Test (timeout=2000)
	public void findClienteById() throws Exception {
		Collection c = getClienteSessionService().findClienteById(new Long(1)); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findClienteByTipoidentificacionId() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENT2"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByTipoidentificacionId(new Long(1));  
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1));  
	}
	
	@Test (timeout=2000)
	public void findClienteByIdentificacion() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A"); 
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByIdentificacion("IDENTx"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getIdentificacion(),"IDENTx"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByNombreLegal() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGALx"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A"); 
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByNombreLegal("NOMBRE_LEGALx"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombreLegal(),"NOMBRE_LEGALx"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByRazonSocial() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIALx"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A"); 
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByRazonSocial("RAZON_SOCIALx"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getRazonSocial(),"RAZON_SOCIALx"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByRepresentante() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTEx"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A"); 
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByRepresentante("REPRESENTANTEx"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getRepresentante(),"REPRESENTANTEx"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByCorporacionId() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByCorporacionId(new Long(1));
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getCorporacionId(),new Long(1));
	}
	
	@Test (timeout=2000)
	public void findClienteByFechaCreacion() throws Exception {
		ClienteIf value = new ClienteData();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(date); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A"); 
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByFechaCreacion(date); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCreacion().toString(),date.toString()); 
	}
	
	@Test (timeout=2000)
	public void findClienteByEstado() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("x"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByEstado("x"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"x"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByTipoclienteId() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByTipoclienteId(new Long(1)); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoclienteId(),new Long(1));
	}
	
	@Test (timeout=2000)
	public void findClienteByTipoproveedorId() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByTipoproveedorId(new Long(1)); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1));  
	}
	
	@Test (timeout=2000)
	public void findClienteByTiponegocioId() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByTiponegocioId(new Long(1));  
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getTiponegocioId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findClienteByCuentaId() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByCuentaId(new Long(1)); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getCuentaId(),new Long(1));  
	}
	
	@Test (timeout=2000)
	public void findClienteByObservacion() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACIONx"); 
		value.setUsuariofinal("A");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByObservacion("OBSERVACIONx"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONx"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByUsuariofinal() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("x");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByUsuariofinal("x"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getUsuariofinal(),"x"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByContribuyenteEspecial() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("x");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByContribuyenteEspecial("N"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getContribuyenteEspecial(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByTipoPersona() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("x");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByTipoPersona("S"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoPersona(),"S"); 
	}
	
	@Test (timeout=2000)
	public void findClienteByLlevaContabilidad() throws Exception {
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTx"); 
		value.setNombreLegal("NOMBRE_LEGAL2"); 
		value.setRazonSocial("RAZON_SOCIAL2"); 
		value.setRepresentante("REPRESENTANTE2"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("A"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setUsuariofinal("x");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		
		Collection c = getClienteSessionService().findClienteByLlevaContabilidad("N"); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getLlevaContabilidad(),"N"); 
	}

	@Test (timeout=3000)
	public void findClienteByQuery() throws Exception {		
		ClienteIf value = new ClienteData();
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTy"); 
		value.setNombreLegal("NOMBRE_LEGALy"); 
		value.setRazonSocial("RAZON_SOCIALy"); 
		value.setRepresentante("REPRESENTANTEy"); 
		value.setCorporacionId(new Long(1)); 
		value.setFechaCreacion(now); 
		value.setEstado("y"); 
		value.setTipoclienteId(new Long(1)); 
		value.setTipoproveedorId(new Long(1)); 
		value.setTiponegocioId(new Long(1)); 
		value.setCuentaId(new Long(1)); 
		value.setObservacion("OBSERVACIONy"); 
		value.setUsuariofinal("y");
		value.setContribuyenteEspecial("N");
		value.setTipoPersona("S");
		value.setLlevaContabilidad("N");
		
		getClienteSessionService().addCliente(value);
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("tipoidentificacionId",new Long(1));
		parametros.put("identificacion","IDENTy");  
		parametros.put("nombreLegal","NOMBRE_LEGALy");  
		parametros.put("razonSocial","RAZON_SOCIALy"); 
		parametros.put("representante","REPRESENTANTEy"); 
		parametros.put("corporacionId",new Long(1)); 
		parametros.put("fechaCreacion",now); 
		parametros.put("estado","y"); 
		parametros.put("tipoclienteId",new Long(1));
		parametros.put("tipoproveedorId",new Long(1));  
		parametros.put("tiponegocioId",new Long(1));
		parametros.put("cuentaId",new Long(1));  
		parametros.put("observacion","OBSERVACIONy"); 
		parametros.put("usuariofinal","y");
		parametros.put("contribuyenteEspecial","N");
		parametros.put("tipoPersona","S");
		parametros.put("llevaContabilidad","N");
		
		Collection c = getClienteSessionService().findClienteByQuery(parametros); 
		ClienteIf resultado = (ClienteIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getIdentificacion(),"IDENTy"); 
		Assert.assertEquals(resultado.getNombreLegal(),"NOMBRE_LEGALy"); 
		Assert.assertEquals(resultado.getRazonSocial(),"RAZON_SOCIALy"); 
		Assert.assertEquals(resultado.getRepresentante(),"REPRESENTANTEy");
		Assert.assertEquals(resultado.getCorporacionId(),new Long(1));  
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getEstado(),"y"); 
		Assert.assertEquals(resultado.getTipoclienteId(),new Long(1));  
		Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getTiponegocioId(),new Long(1));  
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONy"); 
		Assert.assertEquals(resultado.getUsuariofinal(),"y"); 
		Assert.assertEquals(resultado.getContribuyenteEspecial(),"N");
		Assert.assertEquals(resultado.getTipoPersona(),"S");
		Assert.assertEquals(resultado.getLlevaContabilidad(),"N");
	}
	
	public static ClienteSessionService getClienteSessionService() {
		try {
			return (ClienteSessionService) ServiceLocator.getService(ServiceLocator.CLIENTESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteSessionTest.class);	
	}
}
