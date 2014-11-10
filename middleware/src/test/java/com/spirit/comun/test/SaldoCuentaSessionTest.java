package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.spirit.client.SpiritAlert;
import com.spirit.contabilidad.entity.SaldoCuentaData;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.session.SaldoCuentaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class SaldoCuentaSessionTest {


	
	//id=>NUMBER(10)
	//cuentaId=>NUMBER(10)
	//periodoId=>NUMBER(10)
	//anio=>VARCHAR2(4)
	//mes=>VARCHAR2(2)
	//valordebe=>NUMBER(22)
	//valorhaber=>NUMBER(22)
	//valor=>NUMBER(19, 2)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/saldo_cuenta.xml");
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
	public void addSaldoCuenta() throws Exception {
		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2000"); 
		value.setMes("01"); 
		value.setValordebe(new BigDecimal(1)); 
		value.setValorhaber(new BigDecimal(1)); 
		value.setValor(new BigDecimal(1)); 

		SaldoCuentaIf resultado = getSaldoCuentaSessionService().addSaldoCuenta(value);

		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
		Assert.assertEquals(resultado.getAnio(),"2000"); 
		Assert.assertEquals(resultado.getMes(),"01"); 
		Assert.assertEquals(resultado.getValordebe(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getValorhaber(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 

	}

	@Test (timeout=2000)
	public void saveSaldoCuenta() throws Exception {

		SaldoCuentaIf value = getSaldoCuentaSessionService().getSaldoCuenta(new Long(1));


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 

		getSaldoCuentaSessionService().saveSaldoCuenta(value);

		SaldoCuentaIf resultado = getSaldoCuentaSessionService().getSaldoCuenta(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
		Assert.assertEquals(resultado.getAnio(),"2005"); 
		Assert.assertEquals(resultado.getMes(),"05"); 
		Assert.assertEquals(resultado.getValordebe(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getValorhaber(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(2)); 

	}


	@Test (timeout=2000)
	public void deleteSaldoCuenta() throws Exception {
		getSaldoCuentaSessionService().deleteSaldoCuenta(new Long(3));
		SaldoCuentaIf resultado = getSaldoCuentaSessionService().getSaldoCuenta(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findSaldoCuentaById() throws Exception {
		Collection c = getSaldoCuentaSessionService().findSaldoCuentaById(new Long(1)); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}

	@Test (timeout=2000)
	public void findSaldoCuentaByCuentaId() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByCuentaId(new Long(1)); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findSaldoCuentaByPeriodoId() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByPeriodoId(new Long(1)); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findSaldoCuentaByAnio() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2003"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByAnio("2003"); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getAnio(),"2003"); 

	}

	@Test (timeout=2000)
	public void findSaldoCuentaByMes() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("03"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByMes("03"); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getMes(),"03"); 

	}

	@Test (timeout=2000)
	public void findSaldoCuentaByValordebe() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(8)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2));

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByValordebe(new BigDecimal(8)); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getValordebe(),new BigDecimal(8)); 

	}

	@Test (timeout=2000)
	public void findSaldoCuentaByValorhaber() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(8)); 
		value.setValor(new BigDecimal(2));

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByValorhaber(new BigDecimal(8)); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getValorhaber(),new BigDecimal(8)); 

	}

	@Test (timeout=2000)
	public void findSaldoCuentaByValor() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2005"); 
		value.setMes("05"); 
		value.setValordebe(new BigDecimal(2)); 
		value.setValorhaber(new BigDecimal(2)); 
		value.setValor(new BigDecimal(8)); 

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByValor(new BigDecimal(8)); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(8)); 

	}




	@Test (timeout=2000)
	public void findSaldoCuentaByQuery() throws Exception {

		SaldoCuentaIf value = new SaldoCuentaData();


		value.setCuentaId(new Long(1)); 
		value.setPeriodoId(new Long(1)); 
		value.setAnio("2009"); 
		value.setMes("09"); 
		value.setValordebe(new BigDecimal(9)); 
		value.setValorhaber(new BigDecimal(9)); 
		value.setValor(new BigDecimal(9));  

		getSaldoCuentaSessionService().addSaldoCuenta(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("cuentaId",new Long(1)); 
		parametros.put("periodoId",new Long(1)); 
		parametros.put("anio","2009"); 
		parametros.put("mes","09"); 
		parametros.put("valordebe",new BigDecimal(9)); 
		parametros.put("valorhaber",new BigDecimal(9)); 
		parametros.put("valor",new BigDecimal(9)); 

		Collection c = getSaldoCuentaSessionService().findSaldoCuentaByQuery(parametros); 
		SaldoCuentaIf resultado = (SaldoCuentaIf)c.iterator().next();


		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
		Assert.assertEquals(resultado.getAnio(),"2009"); 
		Assert.assertEquals(resultado.getMes(),"09"); 
		Assert.assertEquals(resultado.getValordebe(),new BigDecimal(9)); 
		Assert.assertEquals(resultado.getValorhaber(),new BigDecimal(9)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(9)); 


	}

	public static SaldoCuentaSessionService getSaldoCuentaSessionService() {
		try {
			return (SaldoCuentaSessionService) ServiceLocator
			.getService(ServiceLocator.SALDOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SaldoCuentaSessionTest.class);

	}





}
