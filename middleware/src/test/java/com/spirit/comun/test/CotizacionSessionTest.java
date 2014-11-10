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

import com.spirit.general.entity.CotizacionData;
import com.spirit.general.entity.CotizacionIf;
import com.spirit.general.session.CotizacionSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class CotizacionSessionTest {

	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//monedaId=>NUMBER(10)
	//monedaequivId=>NUMBER(10)
	//fecha=>DATE
	//cotizacion=>NUMBER(22)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cotizacion.xml");
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
	public void addCotizacion() throws Exception {
		CotizacionIf value = new CotizacionData();

		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		CotizacionIf resultado = getCotizacionSessionService().addCotizacion(value);

		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaequivId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString());
		Assert.assertEquals(resultado.getCotizacion(),new BigDecimal(1).setScale(2)); 

	}

	@Test (timeout=2000)
	public void saveCotizacion() throws Exception {

		CotizacionIf value = getCotizacionSessionService().getCotizacion(new Long(1));

		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		getCotizacionSessionService().saveCotizacion(value);

		CotizacionIf resultado = getCotizacionSessionService().getCotizacion(new Long(1));

		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaequivId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getCotizacion(),new BigDecimal(1)); 

	}


	@Test (timeout=2000)
	public void deleteCotizacion() throws Exception {
		getCotizacionSessionService().deleteCotizacion(new Long(3));
		CotizacionIf resultado = getCotizacionSessionService().getCotizacion(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findCotizacionById() throws Exception {
		Collection c = getCotizacionSessionService().findCotizacionById(new Long(1)); 
		CotizacionIf resultado = (CotizacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findCotizacionByMonedaId() throws Exception {

		CotizacionIf value = new CotizacionData();

		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		getCotizacionSessionService().addCotizacion(value);

		Collection c = getCotizacionSessionService().findCotizacionByMonedaId(new Long(1)); 
		CotizacionIf resultado = (CotizacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findCotizacionByMonedaequivId() throws Exception {

		CotizacionIf value = new CotizacionData();

		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		getCotizacionSessionService().addCotizacion(value);

		Collection c = getCotizacionSessionService().findCotizacionByMonedaequivId(new Long(1)); 
		CotizacionIf resultado = (CotizacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getMonedaequivId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findCotizacionByFecha() throws Exception {

		CotizacionIf value = new CotizacionData();

		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		getCotizacionSessionService().addCotizacion(value);

		Collection c = getCotizacionSessionService().findCotizacionByFecha(now); 
		CotizacionIf resultado = (CotizacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 

	}

	@Test (timeout=2000)
	public void findCotizacionByCotizacion() throws Exception {

		CotizacionIf value = new CotizacionData();

		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		getCotizacionSessionService().addCotizacion(value);

		Collection c = getCotizacionSessionService().findCotizacionByCotizacion(new BigDecimal(1)); 
		CotizacionIf resultado = (CotizacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getCotizacion(),new BigDecimal(1)); 

	}




	@Test (timeout=2000)
	public void findCotizacionByQuery() throws Exception {

		CotizacionIf value = new CotizacionData();

		Date date = new Date(System.currentTimeMillis());
		value.setMonedaId(new Long(1)); 
		value.setMonedaequivId(new Long(1)); 
		value.setFecha(now); 
		value.setCotizacion(new BigDecimal(1)); 

		getCotizacionSessionService().addCotizacion(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("monedaId",new Long(1)); 
		parametros.put("monedaequivId",new Long(1)); 
		parametros.put("fecha",date); 
		parametros.put("cotizacion",new BigDecimal(1)); 

		Collection c = getCotizacionSessionService().findCotizacionByQuery(parametros); 
		CotizacionIf resultado = (CotizacionIf)c.iterator().next();

		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaequivId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getCotizacion(),new BigDecimal(1)); 


	}

	public static CotizacionSessionService getCotizacionSessionService() {
		try {
			return (CotizacionSessionService) ServiceLocator
			.getService(ServiceLocator.COTIZACIONSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CotizacionSessionTest.class);

	}

}
