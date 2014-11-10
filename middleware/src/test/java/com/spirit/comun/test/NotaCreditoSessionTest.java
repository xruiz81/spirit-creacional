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

import com.spirit.cartera.entity.NotaCreditoData;
import com.spirit.cartera.entity.NotaCreditoIf;
import com.spirit.cartera.session.NotaCreditoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class NotaCreditoSessionTest {
	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//oficinaId=>NUMBER(10)
	//tipoDocumentoId=>NUMBER(10)
	//codigo=>VARCHAR2(10)
	//operadorNegocioId=>NUMBER(10)
	//monedaId=>NUMBER(10)
	//usuarioId=>NUMBER(10)
	//fechaEmision=>DATE
	//fechaVencimiento=>DATE
	//fechaCaducidad=>DATE
	//preimpreso=>VARCHAR2(20)
	//autorizacion=>VARCHAR2(12)
	//referencia=>VARCHAR2(20)
	//valor=>NUMBER(22)
	//iva=>NUMBER(22)
	//ice=>NUMBER(22)
	//otroImpuesto=>NUMBER(22)
	//observacion=>VARCHAR2(100)
	//estado=>VARCHAR2(1)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/nota_credito.xml");
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
	public void addNotaCredito() throws Exception {
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		NotaCreditoIf resultado = getNotaCreditoSessionService().addNotaCredito(value);
		
		Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
		Assert.assertEquals(resultado.getTipoDocumentoId(),"TIPO_DOCUMENTO_ID1"); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getOperadorNegocioId(),"OPERADOR_NEGOCIO_ID1"); 
		Assert.assertEquals(resultado.getMonedaId(),"MONEDA_ID1"); 
		Assert.assertEquals(resultado.getUsuarioId(),"USUARIO_ID1"); 
		Assert.assertEquals(resultado.getFechaEmision(),"FECHA_EMISION1"); 
		Assert.assertEquals(resultado.getFechaVencimiento(),"FECHA_VENCIMIENTO1"); 
		Assert.assertEquals(resultado.getFechaCaducidad(),"FECHA_CADUCIDAD1"); 
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
		Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		Assert.assertEquals(resultado.getIva(),"IVA1"); 
		Assert.assertEquals(resultado.getIce(),"ICE1"); 
		Assert.assertEquals(resultado.getOtroImpuesto(),"OTRO_IMPUESTO1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
		
	}
	
	@Test (timeout=2000)
	public void saveNotaCredito() throws Exception {
		
		NotaCreditoIf value = getNotaCreditoSessionService().getNotaCredito(new Long(1));
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().saveNotaCredito(value);
		
		NotaCreditoIf resultado = getNotaCreditoSessionService().getNotaCredito(new Long(1));
		
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
		Assert.assertEquals(resultado.getTipoDocumentoId(),"TIPO_DOCUMENTO_ID1"); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getOperadorNegocioId(),"OPERADOR_NEGOCIO_ID1"); 
		Assert.assertEquals(resultado.getMonedaId(),"MONEDA_ID1"); 
		Assert.assertEquals(resultado.getUsuarioId(),"USUARIO_ID1"); 
		Assert.assertEquals(resultado.getFechaEmision(),"FECHA_EMISION1"); 
		Assert.assertEquals(resultado.getFechaVencimiento(),"FECHA_VENCIMIENTO1"); 
		Assert.assertEquals(resultado.getFechaCaducidad(),"FECHA_CADUCIDAD1"); 
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
		Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		Assert.assertEquals(resultado.getIva(),"IVA1"); 
		Assert.assertEquals(resultado.getIce(),"ICE1"); 
		Assert.assertEquals(resultado.getOtroImpuesto(),"OTRO_IMPUESTO1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
		
	}
	
	
	@Test (timeout=2000)
	public void deleteNotaCredito() throws Exception {
		getNotaCreditoSessionService().deleteNotaCredito(new Long(3));
		NotaCreditoIf resultado = getNotaCreditoSessionService().getNotaCredito(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findNotaCreditoById() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoById(new Long(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByOficinaId() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByOficinaId(new Long(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByTipoDocumentoId() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByTipoDocumentoId(new Long(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoDocumentoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByCodigo() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByCodigo("CODIGO1"); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByOperadorNegocioId() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByOperadorNegocioId(new Long(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOperadorNegocioId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByMonedaId() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByMonedaId(new Long(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByUsuarioId() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByUsuarioId(new Long(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByFechaEmision() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		Date date = new Date(System.currentTimeMillis());
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(date); 
		value.setFechaVencimiento(date); 
		value.setFechaCaducidad(date); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByFechaEmision(date); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaEmision().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByFechaVencimiento() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		Date date = new Date(System.currentTimeMillis());
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(date); 
		value.setFechaVencimiento(date); 
		value.setFechaCaducidad(date); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByFechaVencimiento(date); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByFechaCaducidad() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		Date date = new Date(System.currentTimeMillis());
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(date); 
		value.setFechaVencimiento(date); 
		value.setFechaCaducidad(date); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByFechaCaducidad(date); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCaducidad().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByPreimpreso() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByPreimpreso("PREIMPRESO1"); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByAutorizacion() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByAutorizacion("A"); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAutorizacion(),"A"); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByReferencia() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByReferencia("REFERENCIA1"); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByValor() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByValor(new BigDecimal(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByIva() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByIva(new BigDecimal(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByIce() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByIce(new BigDecimal(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1));
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByOtroImpuesto() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByOtroImpuesto(new BigDecimal(1)); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByObservacion() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A"); 
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByObservacion("OBSERVACION1"); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoByEstado() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByEstado("A"); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"A"); 
		
	}
	
	
	
	
	@Test (timeout=2000)
	public void findNotaCreditoByQuery() throws Exception {
		
		NotaCreditoIf value = new NotaCreditoData();
		
		
		value.setOficinaId(new Long(1)); 
		value.setTipoDocumentoId(new Long(1)); 
		value.setCodigo("CODIGO1"); 
		value.setOperadorNegocioId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setUsuarioId(new Long(1)); 
		value.setFechaEmision(now); 
		value.setFechaVencimiento(now); 
		value.setFechaCaducidad(now); 
		value.setPreimpreso("PREIMPRESO1"); 
		value.setAutorizacion("A"); 
		value.setReferencia("REFERENCIA1"); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("A");  
		
		getNotaCreditoSessionService().addNotaCredito(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		parametros.put("oficinaId",new Long(1)); 
		parametros.put("tipoDocumentoId",new Long(1));  
		parametros.put("codigo","CODIGO1"); 
		parametros.put("operadorNegocioId",new Long(1));  
		parametros.put("monedaId",new Long(1));  
		parametros.put("usuarioId",new Long(1));  
		parametros.put("fechaEmision",now); 
		parametros.put("fechaVencimiento",now); 
		parametros.put("fechaCaducidad",now); 
		parametros.put("preimpreso","PREIMPRESO1"); 
		parametros.put("autorizacion","A"); 
		parametros.put("referencia","REFERENCIA1"); 
		parametros.put("valor",new BigDecimal(1)); 
		parametros.put("iva",new BigDecimal(1)); 
		parametros.put("ice",new BigDecimal(1)); 
		parametros.put("otroImpuesto",new BigDecimal(1)); 
		parametros.put("observacion","OBSERVACION1"); 
		parametros.put("estado","A"); 
		
		Collection c = getNotaCreditoSessionService().findNotaCreditoByQuery(parametros); 
		NotaCreditoIf resultado = (NotaCreditoIf)c.iterator().next();
		
		
		Assert.assertEquals(resultado.getOficinaId(),new Long(1));  
		Assert.assertEquals(resultado.getTipoDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getOperadorNegocioId(),new Long(1));  
		Assert.assertEquals(resultado.getMonedaId(),new Long(1));  
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1));  
		Assert.assertEquals(resultado.getFechaEmision().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaCaducidad().toString(),now.toString()); 
		Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
		Assert.assertEquals(resultado.getAutorizacion(),"A"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1));  
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1));  
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1));  
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1));  
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		
		
	}
	
	public static NotaCreditoSessionService getNotaCreditoSessionService() {
		try {
			return (NotaCreditoSessionService) ServiceLocator.getService(ServiceLocator.NOTACREDITOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(NotaCreditoSessionTest.class);
		
	}	
}
