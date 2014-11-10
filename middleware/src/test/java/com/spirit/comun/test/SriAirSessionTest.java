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

import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.sri.entity.SriAirData;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.session.SriAirSessionService;



public class SriAirSessionTest {
	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(5)
	//concepto=>VARCHAR2(150)
	//porcentaje=>NUMBER(22)
	//fechaInicio=>DATE
	//fechaFin=>DATE
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/sri_air.xml");
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
	public void addSriAir() throws Exception {
		SriAirIf value = new SriAirData();
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now); 
		
		SriAirIf resultado = getSriAirSessionService().addSriAir(value);
		
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO1"); 
		Assert.assertEquals(resultado.getPorcentaje(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getFechaInicio(),now); 
		Assert.assertEquals(resultado.getFechaFin(),now); 
		
	}
	
	@Test (timeout=2000)
	public void saveSriAir() throws Exception {
		SriAirIf value = getSriAirSessionService().getSriAir(new Long(1));
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now); 
		
		getSriAirSessionService().saveSriAir(value);
		SriAirIf resultado = getSriAirSessionService().getSriAir(new Long(1));
		
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO1"); 
		Assert.assertEquals(resultado.getPorcentaje(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getFechaInicio(),now.toString()); 
		Assert.assertEquals(resultado.getFechaFin(),now.toString()); 
	}
	
	
	@Test (timeout=2000)
	public void deleteSriAir() throws Exception {
		getSriAirSessionService().deleteSriAir(new Long(3));
		SriAirIf resultado = getSriAirSessionService().getSriAir(new Long(3));
		Assert.assertNull(resultado);
	}
	
	@Test (timeout=2000)
	public void findSriAirById() throws Exception {
		SriAirIf value = new SriAirData();
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now); 
		
		getSriAirSessionService().addSriAir(value);
		
		Collection c = getSriAirSessionService().findSriAirById(new Long(1)); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findSriAirByCodigo() throws Exception {
		SriAirIf value = new SriAirData();
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now); 
		
		getSriAirSessionService().addSriAir(value);
		
		Collection c = getSriAirSessionService().findSriAirByCodigo("CODIGO1"); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
	}
	
	@Test (timeout=2000)
	public void findSriAirByConcepto() throws Exception {
		SriAirIf value = new SriAirData();
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now); 
		
		getSriAirSessionService().addSriAir(value);
		
		Collection c = getSriAirSessionService().findSriAirByConcepto("CONCEPTO1"); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO1"); 
	}
	
	@Test (timeout=2000)
	public void findSriAirByPorcentaje() throws Exception {
		SriAirIf value = new SriAirData();
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now);  
		
		getSriAirSessionService().addSriAir(value);
		
		Collection c = getSriAirSessionService().findSriAirByPorcentaje(new BigDecimal(1)); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		Assert.assertEquals(resultado.getPorcentaje(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findSriAirByFechaInicio() throws Exception {
		SriAirIf value = new SriAirData();
		Date date = new Date(System.currentTimeMillis()); 
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(date); 
		value.setFechaFin(date); 
		
		getSriAirSessionService().addSriAir(value);
		
		Collection c = getSriAirSessionService().findSriAirByFechaInicio(date); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaInicio().toString(),date.toString()); 
	}
	
	@Test (timeout=2000)
	public void findSriAirByFechaFin() throws Exception {
		SriAirIf value = new SriAirData();
		Date date = new Date(System.currentTimeMillis()); 
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(date); 
		value.setFechaFin(date); 
		
		getSriAirSessionService().addSriAir(value);
		
		Collection c = getSriAirSessionService().findSriAirByFechaFin(date); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaFin().toString(),date.toString()); 
	}
	
	@Test (timeout=2000)
	public void findSriAirByQuery() throws Exception {
		SriAirIf value = new SriAirData();
		value.setCodigo("CODIGO1"); 
		value.setConcepto("CONCEPTO1"); 
		value.setPorcentaje(new BigDecimal(1)); 
		value.setFechaInicio(now); 
		value.setFechaFin(now); 
		
		getSriAirSessionService().addSriAir(value);
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("codigo","CODIGO1"); 
		parametros.put("concepto","CONCEPTO1"); 
		parametros.put("porcentaje",new BigDecimal(1)); 
		parametros.put("fechaInicio",now); 
		parametros.put("fechaFin",now); 
		
		Collection c = getSriAirSessionService().findSriAirByQuery(parametros); 
		SriAirIf resultado = (SriAirIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO1"); 
		Assert.assertEquals(resultado.getPorcentaje(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
	}
	
	public static SriAirSessionService getSriAirSessionService() {
		try {
			return (SriAirSessionService) ServiceLocator.getService(ServiceLocator.SRIAIRSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SriAirSessionTest.class);
	}
}
