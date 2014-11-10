package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.GregorianCalendar;
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

import com.spirit.inventario.entity.ProductoData;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class ProductoSessionTest {
	Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
	//id=>NUMBER(10)
	//genericoId=>NUMBER(10)
	//presentacionId=>NUMBER(10)
	//proveedorId=>NUMBER(10)
	//origenProducto=>VARCHAR2(1)
	//codigoBarras=>VARCHAR2(30)
	//costo=>NUMBER(22)
	//fechaCreacion=>DATE
	//rebate=>NUMBER(22)
	//aceptapromocion=>VARCHAR2(1)
	//permiteventa=>VARCHAR2(1)
	//aceptadevolucion=>VARCHAR2(1)
	//cambioprecio=>VARCHAR2(1)
	//estado=>VARCHAR2(1)
	//codigo=>VARCHAR2(10)
	//margenminimo=>NUMBER(22)
	//subproveedor=>VARCHAR2(300)
	//costoLifo=>NUMBER(22)
	//costoFifo=>NUMBER(22)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/producto.xml");
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
	public void addProducto() throws Exception {
		ProductoIf value = new ProductoData();		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("O"); 
		value.setCodigoBarras("CODIGO_BARRAS1"); 
		value.setCosto(new BigDecimal(1)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(1)); 
		value.setAceptapromocion("A"); 
		value.setPermiteventa("P"); 
		value.setAceptadevolucion("A"); 
		value.setCambioprecio("C"); 
		value.setEstado("E"); 
		value.setCodigo("CODIGO1"); 
		value.setMargenminimo(new BigDecimal(1)); 
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		ProductoIf resultado = getProductoSessionService().addProducto(value);
		
		Assert.assertEquals(resultado.getGenericoId(),new Long(1)); 
		Assert.assertEquals(resultado.getPresentacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrigenProducto(),"O"); 
		Assert.assertEquals(resultado.getCodigoBarras(),"CODIGO_BARRAS1"); 
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(1));
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		Assert.assertEquals(resultado.getRebate(),new BigDecimal(1));
		Assert.assertEquals(resultado.getAceptapromocion(),"A"); 
		Assert.assertEquals(resultado.getPermiteventa(),"P"); 
		Assert.assertEquals(resultado.getAceptadevolucion(),"A"); 
		Assert.assertEquals(resultado.getCambioprecio(),"C"); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getMargenminimo(),new BigDecimal(1));
		Assert.assertEquals(resultado.getSubproveedor(),"A");
		Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 
	    Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1));
	}
	
	@Test (timeout=2000)
	public void saveProducto() throws Exception {
		
		ProductoIf value = getProductoSessionService().getProducto(new Long(1));
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("R"); 
		value.setCodigoBarras("CODIGO_BARRAS2"); 
		value.setCosto(new BigDecimal(2)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2)); 
		value.setSubproveedor("B");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().saveProducto(value);
		
		ProductoIf resultado = getProductoSessionService().getProducto(new Long(1));
		
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getGenericoId(),new Long(1)); 
		Assert.assertEquals(resultado.getPresentacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrigenProducto(),"R"); 
		Assert.assertEquals(resultado.getCodigoBarras(),"CODIGO_BARRAS2"); 
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(2));
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getRebate(),new BigDecimal(2));
		Assert.assertEquals(resultado.getAceptapromocion(),"B"); 
		Assert.assertEquals(resultado.getPermiteventa(),"B"); 
		Assert.assertEquals(resultado.getAceptadevolucion(),"B"); 
		Assert.assertEquals(resultado.getCambioprecio(),"B"); 
		Assert.assertEquals(resultado.getEstado(),"B"); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
		Assert.assertEquals(resultado.getMargenminimo(),new BigDecimal(2));
		Assert.assertEquals(resultado.getSubproveedor(),"B");
		Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 
	    Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1));
		
	}
	
	
	@Test (timeout=2000)
	public void deleteProducto() throws Exception {
		getProductoSessionService().deleteProducto(new Long(3));
		ProductoIf resultado = getProductoSessionService().getProducto(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findProductoById() throws Exception {
		Collection c = getProductoSessionService().findProductoById(new Long(1)); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findProductoByGenericoId() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("R"); 
		value.setCodigoBarras("CODIGO_BARRAS2"); 
		value.setCosto(new BigDecimal(2)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2)); 
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
	    
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByGenericoId(new Long(1));
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getGenericoId(),new Long(1));
		
	}
	
	@Test (timeout=2000)
	public void findProductoByPresentacionId() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("R"); 
		value.setCodigoBarras("CODIGO_BARRAS2"); 
		value.setCosto(new BigDecimal(2)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2)); 
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByPresentacionId(new Long(1)); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPresentacionId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByProveedorId() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("R"); 
		value.setCodigoBarras("CODIGO_BARRAS2"); 
		value.setCosto(new BigDecimal(2)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
	    
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByProveedorId(new Long(1)); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByOrigenProducto() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRAS2"); 
		value.setCosto(new BigDecimal(2)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByOrigenProducto("x"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOrigenProducto(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByCodigoBarras() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(2)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByCodigoBarras("CODIGO_BARRASx"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigoBarras(),"CODIGO_BARRASx"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByCosto() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByCosto(new BigDecimal(3)); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(3));  
		
	}
	
	@Test (timeout=2000)
	public void findProductoByFechaCreacion() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		Date date = new Date(System.currentTimeMillis());
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(2)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
	    
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByFechaCreacion(new Timestamp(date.getTime())); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCreacion().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByRebate() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("B"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByRebate(new BigDecimal(3)); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getRebate(),new BigDecimal(3)); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByAceptapromocion() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("B"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByAceptapromocion("x"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAceptapromocion(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByPermiteventa() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("B"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByPermiteventa("x"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPermiteventa(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByAceptadevolucion() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("B"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByAceptadevolucion("x"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAceptadevolucion(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByCambioprecio() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("B"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByCambioprecio("x"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCambioprecio(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByEstado() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("x"); 
		value.setCodigo("CODIGO2"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByEstado("x"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByCodigo() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("x"); 
		value.setCodigo("CODIGOx"); 
		value.setMargenminimo(new BigDecimal(2));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
	    
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByCodigo("CODIGOx"); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 
		
	}
	
	@Test (timeout=2000)
	public void findProductoByMargenminimo() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("x"); 
		value.setCodigo("CODIGOx"); 
		value.setMargenminimo(new BigDecimal(3));
		value.setSubproveedor("A");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoByMargenminimo(new BigDecimal(3));
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getMargenminimo(),new BigDecimal(3));
		
	}
	
	@Test (timeout=2000)
	public void findProductoBySubproveedor() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("x"); 
		value.setCodigo("CODIGOx"); 
		value.setMargenminimo(new BigDecimal(3));
		value.setSubproveedor("x");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Collection c = getProductoSessionService().findProductoBySubproveedor("x");
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		Assert.assertEquals(resultado.getSubproveedor(),"x");
		
	}
	
	@Test (timeout=2000)
	   public void findProductoByCostoLifo() throws Exception {

	 	ProductoIf value = new ProductoData();
	 
	      
	 	value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("x"); 
		value.setCodigo("CODIGOx"); 
		value.setMargenminimo(new BigDecimal(3));
		value.setSubproveedor("x");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
	      
	       getProductoSessionService().addProducto(value);

	       Collection c = getProductoSessionService().findProductoByCostoLifo(new BigDecimal(1)); 
	       ProductoIf resultado = (ProductoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 

	   }

	   @Test (timeout=2000)
	   public void findProductoByCostoFifo() throws Exception {

	 	ProductoIf value = new ProductoData();
	 
	      
	 	value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("x"); 
		value.setCodigoBarras("CODIGO_BARRASx"); 
		value.setCosto(new BigDecimal(3)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(3)); 
		value.setAceptapromocion("x"); 
		value.setPermiteventa("x"); 
		value.setAceptadevolucion("x"); 
		value.setCambioprecio("x"); 
		value.setEstado("x"); 
		value.setCodigo("CODIGOx"); 
		value.setMargenminimo(new BigDecimal(3));
		value.setSubproveedor("x");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
	      
	       getProductoSessionService().addProducto(value);

	       Collection c = getProductoSessionService().findProductoByCostoFifo(new BigDecimal(1)); 
	       ProductoIf resultado = (ProductoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1)); 

	   }
	
	@Test (timeout=2000)
	public void findProductoByQuery() throws Exception {
		
		ProductoIf value = new ProductoData();
		
		
		value.setGenericoId(new Long(1)); 
		value.setPresentacionId(new Long(1)); 
		value.setProveedorId(new Long(1)); 
		value.setOrigenProducto("y"); 
		value.setCodigoBarras("CODIGO_BARRASy"); 
		value.setCosto(new BigDecimal(5)); 
		value.setFechaCreacion(now); 
		value.setRebate(new BigDecimal(5)); 
		value.setAceptapromocion("y"); 
		value.setPermiteventa("y"); 
		value.setAceptadevolucion("y"); 
		value.setCambioprecio("y"); 
		value.setEstado("y"); 
		value.setCodigo("CODIGOy"); 
		value.setMargenminimo(new BigDecimal(5)); 
		value.setSubproveedor("y");
		value.setCostoLifo(new BigDecimal(1)); 
	    value.setCostoFifo(new BigDecimal(1)); 
		
		getProductoSessionService().addProducto(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		parametros.put("genericoId",new Long(1)); 
		parametros.put("presentacionId",new Long(1)); 
		parametros.put("proveedorId",new Long(1)); 
		parametros.put("origenProducto","y"); 
		parametros.put("codigoBarras","CODIGO_BARRASy"); 
		parametros.put("costo",new BigDecimal(5));  
		parametros.put("fechaCreacion",now); 
		parametros.put("rebate",new BigDecimal(5));  
		parametros.put("aceptapromocion","y"); 
		parametros.put("permiteventa","y"); 
		parametros.put("aceptadevolucion","y"); 
		parametros.put("cambioprecio","y"); 
		parametros.put("estado","y"); 
		parametros.put("codigo","CODIGOy"); 
		parametros.put("margenminimo",new BigDecimal(5));
		parametros.put("subproveedor","y");
		parametros.put("costoLifo",new BigDecimal(1)); 
	    parametros.put("costoFifo",new BigDecimal(1)); 
		
		Collection c = getProductoSessionService().findProductoByQuery(parametros); 
		ProductoIf resultado = (ProductoIf)c.iterator().next();
		
		
		Assert.assertEquals(resultado.getGenericoId(),new Long(1));  
		Assert.assertEquals(resultado.getPresentacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1));  
		Assert.assertEquals(resultado.getOrigenProducto(),"y"); 
		Assert.assertEquals(resultado.getCodigoBarras(),"CODIGO_BARRASy"); 
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(5));  
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getRebate(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getAceptapromocion(),"y"); 
		Assert.assertEquals(resultado.getPermiteventa(),"y"); 
		Assert.assertEquals(resultado.getAceptadevolucion(),"y"); 
		Assert.assertEquals(resultado.getCambioprecio(),"y"); 
		Assert.assertEquals(resultado.getEstado(),"y"); 
		Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
		Assert.assertEquals(resultado.getMargenminimo(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getSubproveedor(),"y");
		Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 
	    Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1));
		
	}
	
	public static ProductoSessionService getProductoSessionService() {
		try {
			return (ProductoSessionService) ServiceLocator.getService(ServiceLocator.PRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ProductoSessionTest.class);
		
	}
}
