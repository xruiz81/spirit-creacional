package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
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

import com.spirit.compras.entity.OrdenCompraData;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.compras.session.OrdenCompraSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class OrdenCompraSessionTest {
	
	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//oficinaId=>NUMBER(10)
	//tipodocumentoId=>NUMBER(10)
	//codigo=>VARCHAR2(10)
	//proveedorId=>NUMBER(10)
	//monedaId=>NUMBER(10)
	//empleadoId=>NUMBER(10)
	//usuarioId=>NUMBER(10)
	//bodegaId=>NUMBER(10)
	//localimportada=>VARCHAR2(1)
	//fecha=>DATE
	//fechaRecepcion=>DATE
	//fechaVencimiento=>DATE
	//estado=>VARCHAR2(1)
	//tiporeferencia=>VARCHAR2(1)
	//referencia=>VARCHAR2(20)
	//observacion=>VARCHAR2(100)
	//valor=>FLOAT(126)
	//descuento=>FLOAT(126)
	//iva=>FLOAT(126)
	//ice=>FLOAT(126)
	//otroImpuesto=>FLOAT(126)
	//solicitudcompraId=>NUMBER(10)
	//estadoBpm=>VARCHAR2(10)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/orden_compra.xml");
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
	public void addOrdenCompra() throws Exception {
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("L"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("E"); 
		value.setObservacion("OBSERVACION1"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1));
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		OrdenCompraIf resultado = getOrdenCompraSessionService().addOrdenCompra(value);
		
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		Assert.assertEquals(resultado.getLocalimportada(),"L"); 
		Assert.assertEquals(resultado.getFecha(),now); 
		Assert.assertEquals(resultado.getFechaRecepcion(),now); 
		Assert.assertEquals(resultado.getFechaVencimiento(),now); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getSolicitudcompraId(),new Long(1));
		Assert.assertEquals(resultado.getEstadoBpm(),"A");
		
	}
	
	@Test (timeout=2000)
	public void saveOrdenCompra() throws Exception {
		
		OrdenCompraIf value = getOrdenCompraSessionService().getOrdenCompra(new Long(1));
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("E");
		
		getOrdenCompraSessionService().saveOrdenCompra(value);
		
		OrdenCompraIf resultado = getOrdenCompraSessionService().getOrdenCompra(new Long(1));
		
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		Assert.assertEquals(resultado.getLocalimportada(),"I"); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaRecepcion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION2"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getSolicitudcompraId(),new Long(1));
		Assert.assertEquals(resultado.getEstadoBpm(),"E");
		
	}
	
	
	@Test (timeout=2000)
	public void deleteOrdenCompra() throws Exception {
		getOrdenCompraSessionService().deleteOrdenCompra(new Long(3));
		OrdenCompraIf resultado = getOrdenCompraSessionService().getOrdenCompra(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findOrdenCompraById() throws Exception {
		Collection c = getOrdenCompraSessionService().findOrdenCompraById(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByOficinaId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByOficinaId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByTipodocumentoId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByTipodocumentoId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByCodigo() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGOx"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByCodigo("CODIGOx"); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByProveedorId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByProveedorId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByMonedaId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByMonedaId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByEmpleadoId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByEmpleadoId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByUsuarioId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByUsuarioId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByBodegaId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2)); 
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByBodegaId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByLocalimportada() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByLocalimportada("x"); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getLocalimportada(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByFecha() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		Date date = new Date(System.currentTimeMillis());
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(date); 
		value.setFechaRecepcion(date); 
		value.setFechaVencimiento(date); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2)); 
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByFecha(date); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByFechaRecepcion() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		Date date = new Date(System.currentTimeMillis());
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(date); 
		value.setFechaRecepcion(date); 
		value.setFechaVencimiento(date); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByFechaRecepcion(date); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaRecepcion().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByFechaVencimiento() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		Date date = new Date(System.currentTimeMillis());
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("I"); 
		value.setFecha(date); 
		value.setFechaRecepcion(date); 
		value.setFechaVencimiento(date); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2)); 
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByFechaVencimiento(date); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByEstado() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("x"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByEstado("x"); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByObservacion() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACIONx"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1));
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByObservacion("OBSERVACIONx"); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONx"); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByValor() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(5)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByValor(new BigDecimal(5)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByDescuento() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(5)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByDescuento(new BigDecimal(5)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByIva() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(2));   
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByIva(new BigDecimal(5)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByIce() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(2));   
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByIce(new BigDecimal(5)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByOtroImpuesto() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(5));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByOtroImpuesto(new BigDecimal(5)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraBySolicitudcompraId() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(5));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("A");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraBySolicitudcompraId(new Long(1)); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getSolicitudcompraId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findOrdenCompraByEstadoBpm() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("x"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION2"); 
		value.setValor(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setIce(new BigDecimal(2)); 
		value.setOtroImpuesto(new BigDecimal(5));  
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("x");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByEstadoBpm("x"); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstadoBpm(),"x"); 
		
	}
	
	@Test (timeout=3000)
	public void findOrdenCompraByQuery() throws Exception {
		
		OrdenCompraIf value = new OrdenCompraData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("CODIGOy"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setLocalimportada("y"); 
		value.setFecha(now); 
		value.setFechaRecepcion(now); 
		value.setFechaVencimiento(now); 
		value.setEstado("y"); 
		value.setObservacion("OBSERVACIONy"); 
		value.setValor(new BigDecimal(7)); 
		value.setDescuento(new BigDecimal(7)); 
		value.setIva(new BigDecimal(7)); 
		value.setIce(new BigDecimal(7)); 
		value.setOtroImpuesto(new BigDecimal(7)); 
		value.setSolicitudcompraId(new Long(1)); 
		value.setEstadoBpm("y");
		
		getOrdenCompraSessionService().addOrdenCompra(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		parametros.put("oficinaId",new Long(1)); 
		parametros.put("tipodocumentoId",new Long(1)); 
		parametros.put("codigo","CODIGOy"); 
		parametros.put("proveedorId",new Long(1)); 
		parametros.put("monedaId",new Long(1)); 
		parametros.put("empleadoId",new Long(1)); 
		parametros.put("usuarioId",new Long(1)); 
		parametros.put("bodegaId",new Long(1)); 
		parametros.put("localimportada","y"); 
		parametros.put("fecha",now); 
		parametros.put("fechaRecepcion",now); 
		parametros.put("fechaVencimiento",now); 
		parametros.put("estado","y"); 
		parametros.put("tiporeferencia","y"); 
		parametros.put("referencia","REFERENCIAy"); 
		parametros.put("observacion","OBSERVACIONy"); 
		parametros.put("valor",new BigDecimal(7)); 
		parametros.put("descuento",new BigDecimal(7)); 
		parametros.put("iva",new BigDecimal(7)); 
		parametros.put("ice",new BigDecimal(7)); 
		parametros.put("otroImpuesto",new BigDecimal(7)); 
		parametros.put("solicitudcompraId",new Long(1)); 
		parametros.put("estadoBpm", "y");
		
		Collection c = getOrdenCompraSessionService().findOrdenCompraByQuery(parametros); 
		OrdenCompraIf resultado = (OrdenCompraIf)c.iterator().next();
		
		
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		Assert.assertEquals(resultado.getLocalimportada(),"y"); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaRecepcion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
		Assert.assertEquals(resultado.getEstado(),"y"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONy"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getSolicitudcompraId(),new Long(1)); 
		Assert.assertEquals(resultado.getEstadoBpm(),"y"); 
		
	}
	
	public static OrdenCompraSessionService getOrdenCompraSessionService() {
		try {
			return (OrdenCompraSessionService) ServiceLocator.getService(ServiceLocator.ORDENCOMPRASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(OrdenCompraSessionTest.class);
		
	}
}
