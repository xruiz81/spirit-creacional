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

import com.spirit.compras.entity.CompraAsociadaGastoData;
import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.session.CompraAsociadaGastoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class CompraAsociadaGastoSessionTest {


	//id=>BIGINT(10)
	//compraGastoId=>BIGINT(10)
	//compraId=>BIGINT(10)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/CompraAsociadaGasto.xml");
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
	public void addCompraAsociadaGasto() throws Exception {
		CompraAsociadaGastoIf value = new CompraAsociadaGastoData();


		//value.setCompraGastoId("COMPRA_GASTO_ID1"); 
		// value.setCompraId("COMPRA_ID1"); 

		CompraAsociadaGastoIf resultado = getCompraAsociadaGastoSessionService().addCompraAsociadaGasto(value);

		Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
		Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 

	}

	@Test (timeout=2000)
	public void saveCompraAsociadaGasto() throws Exception {

		CompraAsociadaGastoIf value = getCompraAsociadaGastoSessionService().getCompraAsociadaGasto(new Long(1));


		// value.setCompraGastoId("COMPRA_GASTO_ID1"); 
		// value.setCompraId("COMPRA_ID1"); 

		getCompraAsociadaGastoSessionService().saveCompraAsociadaGasto(value);

		CompraAsociadaGastoIf resultado = getCompraAsociadaGastoSessionService().getCompraAsociadaGasto(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
		Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 

	}


	@Test (timeout=2000)
	public void deleteCompraAsociadaGasto() throws Exception {
		getCompraAsociadaGastoSessionService().deleteCompraAsociadaGasto(new Long(3));
		CompraAsociadaGastoIf resultado = getCompraAsociadaGastoSessionService().getCompraAsociadaGasto(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findCompraAsociadaGastoById() throws Exception {

		CompraAsociadaGastoIf value = new CompraAsociadaGastoData();


		/*value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 

       getCompraAsociadaGastoSessionService().addCompraAsociadaGasto(value);

       Collection c = getCompraAsociadaGastoSessionService().findCompraAsociadaGastoById("ID1"); 
       CompraAsociadaGastoIf resultado = (CompraAsociadaGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); */

	}

	@Test (timeout=2000)
	public void findCompraAsociadaGastoByCompraGastoId() throws Exception {

		CompraAsociadaGastoIf value = new CompraAsociadaGastoData();


		/*  value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 

       getCompraAsociadaGastoSessionService().addCompraAsociadaGasto(value);

       Collection c = getCompraAsociadaGastoSessionService().findCompraAsociadaGastoByCompraGastoId("COMPRA_GASTO_ID1"); 
       CompraAsociadaGastoIf resultado = (CompraAsociadaGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); */

	}

	@Test (timeout=2000)
	public void findCompraAsociadaGastoByCompraId() throws Exception {

		CompraAsociadaGastoIf value = new CompraAsociadaGastoData();


		/* value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 

       getCompraAsociadaGastoSessionService().addCompraAsociadaGasto(value);

       Collection c = getCompraAsociadaGastoSessionService().findCompraAsociadaGastoByCompraId("COMPRA_ID1"); 
       CompraAsociadaGastoIf resultado = (CompraAsociadaGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); */

	}




	@Test (timeout=2000)
	public void findCompraAsociadaGastoByQuery() throws Exception {

		CompraAsociadaGastoIf value = new CompraAsociadaGastoData();


		// value.setCompraGastoId("COMPRA_GASTO_ID1"); 
		// value.setCompraId("COMPRA_ID1"); 

		getCompraAsociadaGastoSessionService().addCompraAsociadaGasto(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("compraGastoId","COMPRA_GASTO_ID1"); 
		parametros.put("compraId","COMPRA_ID1"); 

		Collection c = getCompraAsociadaGastoSessionService().findCompraAsociadaGastoByQuery(parametros); 
		CompraAsociadaGastoIf resultado = (CompraAsociadaGastoIf)c.iterator().next();


		Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
		Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 


	}

	public static CompraAsociadaGastoSessionService getCompraAsociadaGastoSessionService() {
		try {
			//return (CompraAsociadaGastoSessionService) ServiceLocator
			//.getService(ServiceLocator.CompraAsociadaGastoSESSION_SERVICE);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CompraAsociadaGastoSessionTest.class);

	}





}
