package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
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

import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.sri.entity.SriTipoComprobanteData;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.sri.session.SriTipoComprobanteSessionService;

public class SriTipoComprobanteSessionTest {
	//id=>NUMBER(10)
	//nombre=>VARCHAR2(150)
	//codigo=>VARCHAR2(3)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/sri_tipo_comprobante.xml");
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
	public void addSriTipoComprobante() throws Exception {
		SriTipoComprobanteIf value = new SriTipoComprobanteData();	
		value.setNombre("A"); 
		value.setCodigo("A"); 
		
		SriTipoComprobanteIf resultado = getSriTipoComprobanteSessionService().addSriTipoComprobante(value);
		
		Assert.assertEquals(resultado.getNombre(),"A"); 
		Assert.assertEquals(resultado.getCodigo(),"A"); 
	}
	
	@Test (timeout=2000)
	public void saveSriTipoComprobante() throws Exception {
		SriTipoComprobanteIf value = getSriTipoComprobanteSessionService().getSriTipoComprobante(new Long(1));
		value.setNombre("A"); 
		value.setCodigo("A"); 
		
		getSriTipoComprobanteSessionService().saveSriTipoComprobante(value);
		SriTipoComprobanteIf resultado = getSriTipoComprobanteSessionService().getSriTipoComprobante(new Long(1));
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getNombre(),"A"); 
		Assert.assertEquals(resultado.getCodigo(),"A"); 
	}
	
	@Test (timeout=2000)
	public void deleteSriTipoComprobante() throws Exception {
		getSriTipoComprobanteSessionService().deleteSriTipoComprobante(new Long(3));
		SriTipoComprobanteIf resultado = getSriTipoComprobanteSessionService().getSriTipoComprobante(new Long(3));
		Assert.assertNull(resultado);
	}

	@Test (timeout=2000)
	public void findSriTipoComprobanteById() throws Exception {		
		SriTipoComprobanteIf value = new SriTipoComprobanteData();
		value.setNombre("A"); 
		value.setCodigo("A"); 
		
		getSriTipoComprobanteSessionService().addSriTipoComprobante(value);
		
		Collection c = getSriTipoComprobanteSessionService().findSriTipoComprobanteById(new Long(1)); 
		SriTipoComprobanteIf resultado = (SriTipoComprobanteIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findSriTipoComprobanteByNombre() throws Exception {	
		SriTipoComprobanteIf value = new SriTipoComprobanteData();
		value.setNombre("B"); 
		value.setCodigo("B"); 
		
		getSriTipoComprobanteSessionService().addSriTipoComprobante(value);
		
		Collection c = getSriTipoComprobanteSessionService().findSriTipoComprobanteByNombre("B"); 
		SriTipoComprobanteIf resultado = (SriTipoComprobanteIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"B"); 
	}
	
	@Test (timeout=2000)
	public void findSriTipoComprobanteByCodigo() throws Exception {	
		SriTipoComprobanteIf value = new SriTipoComprobanteData();
		value.setNombre("B"); 
		value.setCodigo("B"); 
		
		getSriTipoComprobanteSessionService().addSriTipoComprobante(value);
		
		Collection c = getSriTipoComprobanteSessionService().findSriTipoComprobanteByCodigo("B"); 
		SriTipoComprobanteIf resultado = (SriTipoComprobanteIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"B"); 
	}
	
	@Test (timeout=2000)
	public void findSriTipoComprobanteByQuery() throws Exception {	
		SriTipoComprobanteIf value = new SriTipoComprobanteData();
		value.setNombre("C"); 
		value.setCodigo("C"); 
		
		getSriTipoComprobanteSessionService().addSriTipoComprobante(value);
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("nombre","C"); 
		parametros.put("codigo","C"); 
		
		Collection c = getSriTipoComprobanteSessionService().findSriTipoComprobanteByQuery(parametros); 
		SriTipoComprobanteIf resultado = (SriTipoComprobanteIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getNombre(),"C"); 
		Assert.assertEquals(resultado.getCodigo(),"C"); 		
	}
	
	public static SriTipoComprobanteSessionService getSriTipoComprobanteSessionService() {
		try {
			return (SriTipoComprobanteSessionService) ServiceLocator.getService(ServiceLocator.SRITIPOCOMPROBANTESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SriTipoComprobanteSessionTest.class);
		
	}
}
