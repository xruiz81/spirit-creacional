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

import com.spirit.compras.entity.CompraData;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.session.CompraSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class CompraSessionTest {
	
	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//oficinaId=>NUMBER(10)
	//tipodocumentoId=>NUMBER(10)
	//codigo=>VARCHAR2(10)
	//proveedorId=>NUMBER(10)
	//monedaId=>NUMBER(10)
	//usuarioId=>NUMBER(10)
	//fecha=>DATE
	//fechaVencimiento=>DATE
	//preimpreso=>VARCHAR2(20)
	//autorizacion=>VARCHAR2(20)
	//referencia=>VARCHAR2(20)
	//localimportada=>VARCHAR2(1)
	//valor=>FLOAT(126)
	//descuento=>FLOAT(126)
	//iva=>FLOAT(126)
	//ice=>FLOAT(126)
	//otroImpuesto=>FLOAT(126)
	//observacion=>VARCHAR2(100)
	//estado=>VARCHAR2(1)
	//estadoBpm=>VARCHAR2(1)
	//retencion=>NUMBER
	//idSriSustentoTributario=>NUMBER(10)
	//fechaCaducidad=>DATE
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/compra.xml");
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
	public void addCompra() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("AUTORIZACION1"); 
		value.setReferencia("REFERENCIA1"); 
		value.setLocalimportada("L"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("E"); 
		value.setEstadoBpm("E");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		CompraIf resultado = getCompraSessionService().addCompra(value);
		
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha(),now); 
		Assert.assertEquals(resultado.getFechaVencimiento(),now); 
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
		Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getLocalimportada(),"L"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getEstadoBpm(),"E");
		Assert.assertEquals(resultado.getRetencion(), new BigDecimal(1));
		Assert.assertEquals(resultado.getIdSriSustentoTributario(),new Long(1));
		Assert.assertEquals(resultado.getFechaCaducidad(),now);
	}
	
	@Test (timeout=2000)
	public void saveCompra() throws Exception {
		CompraIf value = getCompraSessionService().getCompra(new Long(1));
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(2));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().saveCompra(value);
		
		CompraIf resultado = getCompraSessionService().getCompra(new Long(1));
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO2"); 
		Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION2"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA2"); 
		Assert.assertEquals(resultado.getLocalimportada(),"I"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION2"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getEstadoBpm(),"A");
		Assert.assertEquals(resultado.getRetencion(), new BigDecimal(1));
		Assert.assertEquals(resultado.getIdSriSustentoTributario(),new Long(1));
		Assert.assertEquals(resultado.getFechaCaducidad(),now.toString());
	}
	
	@Test (timeout=2000)
	public void deleteCompra() throws Exception {
		getCompraSessionService().deleteCompra(new Long(3));
		CompraIf resultado = getCompraSessionService().getCompra(new Long(3));
		Assert.assertNull(resultado);
	}
	
	@Test (timeout=2000)
	public void findCompraById() throws Exception {
		Collection c = getCompraSessionService().findCompraById(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1));	
	}
	
	@Test (timeout=2000)
	public void findCompraByOficinaId() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByOficinaId(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByTipodocumentoId() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A");
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByTipodocumentoId(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByOrdencompraId() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByOrdencompraId(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByCodigo() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO5"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByCodigo("CODIGO5"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODIGO5"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByProveedorId() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByProveedorId(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByMonedaId() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByMonedaId(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByUsuarioId() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByUsuarioId(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByFecha() throws Exception {
		CompraIf value = new CompraData();
		Date date = new Date(System.currentTimeMillis()); 
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(date); 
		value.setFechaVencimiento(date); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(date);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByFecha(date); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha().toString(),date.toString()); 
	}
	
	@Test (timeout=2000)
	public void findCompraByFechaVencimiento() throws Exception {
		CompraIf value = new CompraData();
		Date date = new Date(System.currentTimeMillis()); 
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(date); 
		value.setFechaVencimiento(date); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByFechaVencimiento(date); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),date.toString()); 
	}
	
	@Test (timeout=2000)
	public void findCompraByPreimpreso() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESOx"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A");  
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByPreimpreso("PREIMPRESOx"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESOx"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByAutorizacion() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACIONx"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByAutorizacion("AUTORIZACIONx"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACIONx"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByReferencia() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIAx"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByReferencia("REFERENCIAx"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIAx"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByLocalimportada() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("x"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A");
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByLocalimportada("x"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getLocalimportada(),"x"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByValor() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(8)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByValor(new BigDecimal(8)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(8)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByDescuento() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(8)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByDescuento(new BigDecimal(8)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(8)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByIva() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(8)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByIva(new BigDecimal(8)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(8)); 		
	}
	
	@Test (timeout=2000)
	public void findCompraByIce() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(8)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByIce(new BigDecimal(8)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getIce(),new BigDecimal(8)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByOtroImpuesto() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(8)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByOtroImpuesto(new BigDecimal(8)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(8)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByObservacion() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACIONx"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByObservacion("OBSERVACIONx"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONx"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByEstado() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("x"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByEstado("x"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"x"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByEstadoBpm() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("x"); 
		value.setEstadoBpm("x");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByEstadoBpm("x"); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstadoBpm(),"x"); 
	}
	
	@Test (timeout=2000)
	public void findCompraByRetencion() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("x"); 
		value.setEstadoBpm("x");
		value.setRetencion(new BigDecimal(3));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByRetencion(new BigDecimal(3)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getRetencion(),new BigDecimal(3)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByIdSriSustentoTributario() throws Exception {
		CompraIf value = new CompraData();
		Date date = new Date(System.currentTimeMillis()); 
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(date); 
		value.setFechaVencimiento(date); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByIdSriSustentoTributario(new Long(1)); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getIdSriSustentoTributario(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCompraByFechaCaducidad() throws Exception {
		CompraIf value = new CompraData();
		Date date = new Date(System.currentTimeMillis()); 
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGO2"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(date); 
		value.setFechaVencimiento(date); 
		value.setPreimpreso("PREIMPRESO2"); 
		value.setAutorizacion("AUTORIZACION2"); 
		value.setReferencia("REFERENCIA2"); 
		value.setLocalimportada("I"); 
		value.setValor(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION2"); 
		value.setEstado("A"); 
		value.setEstadoBpm("A");
		value.setRetencion(new BigDecimal(1));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(date);
		
		getCompraSessionService().addCompra(value);
		
		Collection c = getCompraSessionService().findCompraByFechaCaducidad(date); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCaducidad().toString(),date.toString()); 
	}
	
	@Test (timeout=3000)
	public void findCompraByQuery() throws Exception {
		CompraIf value = new CompraData();
		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setOrdencompraId(new Long(1)); 
		value.setCodigo("CODIGOy"); 
		value.setProveedorId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFecha(now); 
		value.setFechaVencimiento(now); 
		value.setPreimpreso("PREIMPRESOy"); 
		value.setAutorizacion("AUTORIZACIONy"); 
		value.setReferencia("REFERENCIAy"); 
		value.setLocalimportada("y"); 
		value.setValor(new BigDecimal(7)); 
		value.setDescuento(new BigDecimal(7)); 
		value.setIva(new BigDecimal(7)); 
		value.setIce(new BigDecimal(7)); 
		value.setOtroImpuesto(new BigDecimal(7)); 
		value.setObservacion("OBSERVACIONy"); 
		value.setEstado("y"); 
		value.setEstadoBpm("y");
		value.setRetencion(new BigDecimal(4));
		value.setIdSriSustentoTributario(new Long(1));
		value.setFechaCaducidad(now);
		
		getCompraSessionService().addCompra(value);
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("oficinaId",new Long(1)); 
		parametros.put("tipodocumentoId",new Long(1)); 
		parametros.put("ordencompraId",new Long(1)); 
		parametros.put("codigo","CODIGOy"); 
		parametros.put("proveedorId",new Long(1)); 
		parametros.put("monedaId",new Long(1)); 
		parametros.put("usuarioId",new Long(1)); 
		parametros.put("fecha",now); 
		parametros.put("fechaVencimiento",now); 
		parametros.put("preimpreso","PREIMPRESOy"); 
		parametros.put("autorizacion","AUTORIZACIONy"); 
		parametros.put("referencia","REFERENCIAy"); 
		parametros.put("localimportada","y"); 
		parametros.put("valor",new BigDecimal(7)); 
		parametros.put("descuento",new BigDecimal(7)); 
		parametros.put("iva",new BigDecimal(7)); 
		parametros.put("ice",new BigDecimal(7)); 
		parametros.put("otroImpuesto",new BigDecimal(7)); 
		parametros.put("observacion","OBSERVACIONy"); 
		parametros.put("estado","y"); 
		parametros.put("estadoBpm", "y");
		parametros.put("retencion", new BigDecimal(7));
		parametros.put("idSriSustentoTributario",new Long(1));
		parametros.put("fechaCaducidad",now);
		
		Collection c = getCompraSessionService().findCompraByQuery(parametros); 
		CompraIf resultado = (CompraIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESOy"); 
		Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACIONy"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIAy"); 
		Assert.assertEquals(resultado.getLocalimportada(),"y"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONy"); 
		Assert.assertEquals(resultado.getEstado(),"y"); 
		Assert.assertEquals(resultado.getEstadoBpm(),"y");
		Assert.assertEquals(resultado.getRetencion(),new BigDecimal(7));
		Assert.assertEquals(resultado.getIdSriSustentoTributario(),new Long(1));
		Assert.assertEquals(resultado.getFechaCaducidad().toString(),now.toString());
	}
	
	public static CompraSessionService getCompraSessionService() {
		try {
			return (CompraSessionService) ServiceLocator.getService(ServiceLocator.COMPRASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CompraSessionTest.class);	
	}
}
