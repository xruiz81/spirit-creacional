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

import com.spirit.general.entity.BancoData;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.session.BancoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class BancoSessionTest {


	//id=>NUMBER(10)
	//codigo=>VARCHAR2(4)
	//nombre=>VARCHAR2(30)
	//estado=>VARCHAR2(1)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/banco.xml");
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
	public void addBanco() throws Exception {
		BancoIf value = new BancoData();


		value.setCodigo("COD1"); 
		value.setNombre("NOMBRE1"); 
		value.setEstado("E"); 

		BancoIf resultado = getBancoSessionService().addBanco(value);

		Assert.assertEquals(resultado.getCodigo(),"COD1"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getEstado(),"E"); 

	}

	@Test (timeout=2000)
	public void saveBanco() throws Exception {

		BancoIf value = getBancoSessionService().getBanco(new Long(1));


		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEstado("E"); 

		getBancoSessionService().saveBanco(value);

		BancoIf resultado = getBancoSessionService().getBanco(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"COD2"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
		Assert.assertEquals(resultado.getEstado(),"E"); 

	}


	//@Test (timeout=2000)
	public void deleteBanco() throws Exception {
		getBancoSessionService().deleteBanco(new Long(3));
		BancoIf resultado = getBancoSessionService().getBanco(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findBancoById() throws Exception {

		Collection c = getBancoSessionService().findBancoById(new Long(1)); 
		BancoIf resultado = (BancoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findBancoByCodigo() throws Exception {

		BancoIf value = new BancoData();


		value.setCodigo("COD3"); 
		value.setNombre("NOMBRE3"); 
		value.setEstado("E"); 

		getBancoSessionService().addBanco(value);

		Collection c = getBancoSessionService().findBancoByCodigo("COD3"); 
		BancoIf resultado = (BancoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD3"); 

	}

	@Test (timeout=2000)
	public void findBancoByNombre() throws Exception {

		BancoIf value = new BancoData();


		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setEstado("E"); 

		getBancoSessionService().addBanco(value);

		Collection c = getBancoSessionService().findBancoByNombre("NOMBRE4"); 
		BancoIf resultado = (BancoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE4"); 

	}

	@Test (timeout=2000)
	public void findBancoByEstado() throws Exception {

		BancoIf value = new BancoData();


		value.setCodigo("COD5"); 
		value.setNombre("NOMBRE5"); 
		value.setEstado("X"); 

		getBancoSessionService().addBanco(value);

		Collection c = getBancoSessionService().findBancoByEstado("X"); 
		BancoIf resultado = (BancoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"X"); 

	}




	@Test (timeout=2000)
	public void findBancoByQuery() throws Exception {

		BancoIf value = new BancoData();


		value.setCodigo("CODx"); 
		value.setNombre("NOMBREx"); 
		value.setEstado("x"); 

		getBancoSessionService().addBanco(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("codigo","CODx"); 
		parametros.put("nombre","NOMBREx"); 
		parametros.put("estado","x"); 

		Collection c = getBancoSessionService().findBancoByQuery(parametros); 
		BancoIf resultado = (BancoIf)c.iterator().next();


		Assert.assertEquals(resultado.getCodigo(),"CODx"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 
		Assert.assertEquals(resultado.getEstado(),"x"); 


	}

	public static BancoSessionService getBancoSessionService() {
		try {
			return (BancoSessionService) ServiceLocator
			.getService(ServiceLocator.BANCOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(BancoSessionTest.class);

	}





}
