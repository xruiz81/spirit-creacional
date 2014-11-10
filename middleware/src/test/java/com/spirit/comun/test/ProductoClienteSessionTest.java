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

import com.spirit.medios.entity.ProductoClienteData;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.ProductoClienteSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class ProductoClienteSessionTest {


	//id=>NUMBER(10)
	//codigo=>VARCHAR2(3)
	//nombre=>VARCHAR2(30)
	//clienteId=>NUMBER(10)
	//creativoId=>NUMBER(10)
	//ejecutivoId=>NUMBER(10)
	//estado=>VARCHAR2(1)
	//marcaProductoId=>NUMBER(10)
	//marcaProductoNombre=>VARCHAR2(50)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/producto_cliente.xml");
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
	public void addProductoCliente() throws Exception {
		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("E");
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		ProductoClienteIf resultado = getProductoClienteSessionService().addProductoCliente(value);

		Assert.assertEquals(resultado.getCodigo(),"CO1"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1));
		Assert.assertEquals(resultado.getCreativoId(),new Long(1));
		Assert.assertEquals(resultado.getEjecutivoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getMarcaProductoId(),new Long(1));
		Assert.assertEquals(resultado.getMarcaProductoNombre(),"MARCA_PRODUCTO_NOMBRE1");

	}

	@Test (timeout=2000)
	public void saveProductoCliente() throws Exception {

		ProductoClienteIf value = getProductoClienteSessionService().getProductoCliente(new Long(1));


		value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("A");
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().saveProductoCliente(value);

		ProductoClienteIf resultado = getProductoClienteSessionService().getProductoCliente(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"CO2"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1));
		Assert.assertEquals(resultado.getCreativoId(),new Long(1));
		Assert.assertEquals(resultado.getEjecutivoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getMarcaProductoId(),new Long(1));
		Assert.assertEquals(resultado.getMarcaProductoNombre(),"MARCA_PRODUCTO_NOMBRE1");

	}


	@Test (timeout=2000)
	public void deleteProductoCliente() throws Exception {
		getProductoClienteSessionService().deleteProductoCliente(new Long(3));
		ProductoClienteIf resultado = getProductoClienteSessionService().getProductoCliente(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findProductoClienteById() throws Exception {
		Collection c = getProductoClienteSessionService().findProductoClienteById(new Long(1)); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}

	@Test (timeout=2000)
	public void findProductoClienteByCodigo() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("COx"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("A"); 
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByCodigo("COx"); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COx"); 

	}

	@Test (timeout=2000)
	public void findProductoClienteByNombre() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("CO2"); 
		value.setNombre("NOMBREx"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("A");  
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByNombre("NOMBREx"); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

	}

	@Test (timeout=2000)
	public void findProductoClienteByClienteId() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("A");  
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByClienteId(new Long(1));
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getClienteId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findProductoClienteByCreativoId() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("A");  
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByCreativoId(new Long(1));
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getCreativoId(),new Long(1));

	}

	@Test (timeout=2000)
	public void findProductoClienteByEjecutivoId() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("A");  
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByEjecutivoId(new Long(1)); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getEjecutivoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findProductoClienteByEstado() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("x");  
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByEstado("x"); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"x"); 

	}

	@Test (timeout=2000)
	public void findProductoClienteByMarcaProductoId() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();
	 
		value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("x");  
		value.setMarcaProductoId(new Long(1)); 
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 
	      
		getProductoClienteSessionService().addProductoCliente(value);

		Collection c = getProductoClienteSessionService().findProductoClienteByMarcaProductoId(new Long(1)); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
		Assert.assertEquals(resultado.getMarcaProductoId(),new Long(1)); 

	}
	
	@Test (timeout=2000)
	public void findProductoClienteByMarcaProductoNombre() throws Exception {

	 	ProductoClienteIf value = new ProductoClienteData();
	 
	 	value.setCodigo("CO2"); 
		value.setNombre("NOMBRE2"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("x");  
		value.setMarcaProductoId(new Long(1)); 
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1");
	      
	    getProductoClienteSessionService().addProductoCliente(value);

	    Collection c = getProductoClienteSessionService().findProductoClienteByMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 
	    ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();
	    Assert.assertEquals(resultado.getMarcaProductoNombre(),"MARCA_PRODUCTO_NOMBRE1"); 
	}


	@Test (timeout=2000)
	public void findProductoClienteByQuery() throws Exception {

		ProductoClienteIf value = new ProductoClienteData();


		value.setCodigo("COy"); 
		value.setNombre("NOMBREy"); 
		value.setClienteId(new Long(1)); 
		value.setCreativoId(new Long(1)); 
		value.setEjecutivoId(new Long(1)); 
		value.setEstado("y");   
		value.setMarcaProductoId(new Long(1));
		value.setMarcaProductoNombre("MARCA_PRODUCTO_NOMBRE1"); 

		getProductoClienteSessionService().addProductoCliente(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("codigo","COy"); 
		parametros.put("nombre","NOMBREy"); 
		parametros.put("clienteId",new Long(1));
		parametros.put("creativoId",new Long(1)); 
		parametros.put("ejecutivoId",new Long(1)); 
		parametros.put("estado","y"); 
		parametros.put("marcaProductoId",new Long(1)); 
		parametros.put("marcaProductoNombre","MARCA_PRODUCTO_NOMBRE1"); 

		Collection c = getProductoClienteSessionService().findProductoClienteByQuery(parametros); 
		ProductoClienteIf resultado = (ProductoClienteIf)c.iterator().next();


		Assert.assertEquals(resultado.getCodigo(),"COy"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1));
		Assert.assertEquals(resultado.getCreativoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEjecutivoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEstado(),"y"); 
		Assert.assertEquals(resultado.getMarcaProductoId(),new Long(1));
		Assert.assertEquals(resultado.getMarcaProductoNombre(),"MARCA_PRODUCTO_NOMBRE1");


	}

	public static ProductoClienteSessionService getProductoClienteSessionService() {
		try {
			return (ProductoClienteSessionService) ServiceLocator
			.getService(ServiceLocator.PRODUCTOCLIENTESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ProductoClienteSessionTest.class);

	}





}
