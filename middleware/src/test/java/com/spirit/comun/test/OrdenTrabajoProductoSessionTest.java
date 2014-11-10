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

import com.spirit.medios.entity.OrdenTrabajoProductoData;
import com.spirit.medios.entity.OrdenTrabajoProductoIf;
import com.spirit.medios.session.OrdenTrabajoProductoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class OrdenTrabajoProductoSessionTest {

	//id=>NUMBER(10)
	//productoClienteId=>NUMBER(10)
	//ordenTrabajoId=>NUMBER(10)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/orden_trabajo_producto.xml");
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
	public void addOrdenTrabajoProducto() throws Exception {
		OrdenTrabajoProductoIf value = new OrdenTrabajoProductoData();

		value.setProductoClienteId(new Long(1)); 
		value.setOrdenTrabajoId(new Long(1)); 

		OrdenTrabajoProductoIf resultado = getOrdenTrabajoProductoSessionService().addOrdenTrabajoProducto(value);

		Assert.assertEquals(resultado.getProductoClienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrdenTrabajoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void saveOrdenTrabajoProducto() throws Exception {

		OrdenTrabajoProductoIf value = getOrdenTrabajoProductoSessionService().getOrdenTrabajoProducto(new Long(1));

		value.setProductoClienteId(new Long(1)); 
		value.setOrdenTrabajoId(new Long(1)); 

		getOrdenTrabajoProductoSessionService().saveOrdenTrabajoProducto(value);

		OrdenTrabajoProductoIf resultado = getOrdenTrabajoProductoSessionService().getOrdenTrabajoProducto(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getProductoClienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrdenTrabajoId(),new Long(1));  

	}


	@Test (timeout=2000)
	public void deleteOrdenTrabajoProducto() throws Exception {
		getOrdenTrabajoProductoSessionService().deleteOrdenTrabajoProducto(new Long(3));
		OrdenTrabajoProductoIf resultado = getOrdenTrabajoProductoSessionService().getOrdenTrabajoProducto(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findOrdenTrabajoProductoById() throws Exception {

		OrdenTrabajoProductoIf value = new OrdenTrabajoProductoData();

		value.setProductoClienteId(new Long(1)); 
		value.setOrdenTrabajoId(new Long(1)); 

		getOrdenTrabajoProductoSessionService().addOrdenTrabajoProducto(value);

		Collection c = getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoById(new Long(1)); 
		OrdenTrabajoProductoIf resultado = (OrdenTrabajoProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoProductoByProductoClienteId() throws Exception {

		OrdenTrabajoProductoIf value = new OrdenTrabajoProductoData();

		value.setProductoClienteId(new Long(1)); 
		value.setOrdenTrabajoId(new Long(1)); 

		getOrdenTrabajoProductoSessionService().addOrdenTrabajoProducto(value);

		Collection c = getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByProductoClienteId(new Long(1)); 
		OrdenTrabajoProductoIf resultado = (OrdenTrabajoProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getProductoClienteId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoProductoByOrdenTrabajoId() throws Exception {

		OrdenTrabajoProductoIf value = new OrdenTrabajoProductoData();

		value.setProductoClienteId(new Long(1)); 
		value.setOrdenTrabajoId(new Long(1)); 

		getOrdenTrabajoProductoSessionService().addOrdenTrabajoProducto(value);

		Collection c = getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByOrdenTrabajoId(new Long(1)); 
		OrdenTrabajoProductoIf resultado = (OrdenTrabajoProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOrdenTrabajoId(),new Long(1)); 

	}




	@Test (timeout=2000)
	public void findOrdenTrabajoProductoByQuery() throws Exception {

		OrdenTrabajoProductoIf value = new OrdenTrabajoProductoData();

		value.setProductoClienteId(new Long(1)); 
		value.setOrdenTrabajoId(new Long(1)); 

		getOrdenTrabajoProductoSessionService().addOrdenTrabajoProducto(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("productoClienteId",new Long(1)); 
		parametros.put("ordenTrabajoId",new Long(1)); 

		Collection c = getOrdenTrabajoProductoSessionService().findOrdenTrabajoProductoByQuery(parametros); 
		OrdenTrabajoProductoIf resultado = (OrdenTrabajoProductoIf)c.iterator().next();

		Assert.assertEquals(resultado.getProductoClienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrdenTrabajoId(),new Long(1)); 


	}

	public static OrdenTrabajoProductoSessionService getOrdenTrabajoProductoSessionService() {
		try {
			return (OrdenTrabajoProductoSessionService) ServiceLocator
			.getService(ServiceLocator.ORDENTRABAJOPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(OrdenTrabajoProductoSessionTest.class);

	}

}
