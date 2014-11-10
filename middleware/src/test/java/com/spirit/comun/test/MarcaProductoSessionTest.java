package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
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

import com.spirit.medios.entity.MarcaProductoData;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.session.MarcaProductoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class MarcaProductoSessionTest {
	
	
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(3)
	//nombre=>VARCHAR2(50)
	//fechaCreacion=>DATE
	//estado=>VARCHAR2(1)
	//clienteId=>NUMBER(10)
	Date now = new Date(System.currentTimeMillis());
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/marca_producto.xml");
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
	public void addMarcaProducto() throws Exception {
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("A");
		
		MarcaProductoIf resultado = getMarcaProductoSessionService().addMarcaProducto(value);
		
		Assert.assertEquals(resultado.getCodigo(),"CO1"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1));
		Assert.assertEquals(resultado.getTipo(),"A");
		
	}
	
	@Test (timeout=2000)
	public void saveMarcaProducto() throws Exception {
		
		MarcaProductoIf value = getMarcaProductoSessionService().getMarcaProducto(new Long(1));
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("B");
		
		getMarcaProductoSessionService().saveMarcaProducto(value);
		
		MarcaProductoIf resultado = getMarcaProductoSessionService().getMarcaProducto(new Long(1));
		
		Assert.assertEquals(resultado.getCodigo(),"CO1"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1));
		Assert.assertEquals(resultado.getTipo(),"B");
	}
	
	
	@Test (timeout=2000)
	public void deleteMarcaProducto() throws Exception {
		getMarcaProductoSessionService().deleteMarcaProducto(new Long(3));
		MarcaProductoIf resultado = getMarcaProductoSessionService().getMarcaProducto(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findMarcaProductoById() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoById(new Long(1)); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByCodigo() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByCodigo("CO1"); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CO1"); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByNombre() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByNombre("NOMBRE1"); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByFechaCreacion() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByFechaCreacion(now); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByEstado() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByEstado("E"); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"E"); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByClienteId() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByClienteId(new Long(1)); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByTipo() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByTipo("C"); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipo(),"C"); 
		
	}
	
	@Test (timeout=2000)
	public void findMarcaProductoByQuery() throws Exception {
		
		MarcaProductoIf value = new MarcaProductoData();
		
		value.setCodigo("CO1"); 
		value.setNombre("NOMBRE1"); 
		value.setFechaCreacion(now); 
		value.setEstado("E"); 
		value.setClienteId(new Long(1)); 
		value.setTipo("C");
		
		getMarcaProductoSessionService().addMarcaProducto(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("codigo","CO1"); 
		parametros.put("nombre","NOMBRE1"); 
		parametros.put("fechaCreacion",now); 
		parametros.put("estado","E"); 
		parametros.put("clienteId",new Long(1));
		parametros.put("tipo", "C");
		
		Collection c = getMarcaProductoSessionService().findMarcaProductoByQuery(parametros); 
		MarcaProductoIf resultado = (MarcaProductoIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getCodigo(),"CO1"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1));
		Assert.assertEquals(resultado.getTipo(),"C"); 
	}
	
	public static MarcaProductoSessionService getMarcaProductoSessionService() {
		try {
			return (MarcaProductoSessionService) ServiceLocator.getService(ServiceLocator.MARCAPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(MarcaProductoSessionTest.class);
		
	}
}
