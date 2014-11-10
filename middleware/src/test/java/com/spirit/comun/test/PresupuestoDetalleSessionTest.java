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

import com.spirit.medios.entity.PresupuestoDetalleData;
import com.spirit.medios.entity.PresupuestoDetalleIf;
import com.spirit.medios.session.PresupuestoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class PresupuestoDetalleSessionTest {


	//id=>NUMBER(10)
	//presupuestoId=>NUMBER(10)
	//productoId=>NUMBER(10)
	//concepto=>VARCHAR2(100)
	//cantidad=>NUMBER(22)
	//precioagencia=>NUMBER(22)
	//precioventa=>NUMBER(22)
	//descuento=>NUMBER(22)
	//iva=>NUMBER(22)
	//proveedorId=>NUMBER(10)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/presupuesto_detalle.xml");
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
	public void addPresupuestoDetalle() throws Exception {
		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO1"); 
		value.setCantidad(new BigDecimal(1)); 
		value.setPrecioagencia(new BigDecimal(1)); 
		value.setPrecioventa(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setProveedorId(new Long(1));

		PresupuestoDetalleIf resultado = getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Assert.assertEquals(resultado.getPresupuestoId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO1"); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getPrecioagencia(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getPrecioventa(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1).setScale(2)); 
		Assert.assertEquals(resultado.getProveedorId(),new Long(1));

	}

	@Test (timeout=2000)
	public void savePresupuestoDetalle() throws Exception {

		PresupuestoDetalleIf value = getPresupuestoDetalleSessionService().getPresupuestoDetalle(new Long(1));


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(2)); 
		value.setPrecioagencia(new BigDecimal(2)); 
		value.setPrecioventa(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2));
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().savePresupuestoDetalle(value);

		PresupuestoDetalleIf resultado = getPresupuestoDetalleSessionService().getPresupuestoDetalle(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getPresupuestoId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO2"); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getPrecioagencia(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getPrecioventa(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(2));
		Assert.assertEquals(resultado.getProveedorId(),new Long(1));

	}


	@Test (timeout=2000)
	public void deletePresupuestoDetalle() throws Exception {
		getPresupuestoDetalleSessionService().deletePresupuestoDetalle(new Long(3));
		PresupuestoDetalleIf resultado = getPresupuestoDetalleSessionService().getPresupuestoDetalle(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findPresupuestoDetalleById() throws Exception {
		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleById(new Long(1)); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByPresupuestoId() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(2)); 
		value.setPrecioagencia(new BigDecimal(2)); 
		value.setPrecioventa(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByPresupuestoId(new Long(1)); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getPresupuestoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByProductoId() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(2)); 
		value.setPrecioagencia(new BigDecimal(2)); 
		value.setPrecioventa(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByProductoId(new Long(1)); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByConcepto() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setCantidad(new BigDecimal(2)); 
		value.setPrecioagencia(new BigDecimal(2)); 
		value.setPrecioventa(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByConcepto("CONCEPTOx"); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTOx"); 

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByCantidad() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(3)); 
		value.setPrecioagencia(new BigDecimal(2)); 
		value.setPrecioventa(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2));
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByCantidad(new BigDecimal(3)); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(3));

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByPrecioagencia() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(3)); 
		value.setPrecioagencia(new BigDecimal(3)); 
		value.setPrecioventa(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2));
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByPrecioagencia(new BigDecimal(3)); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getPrecioagencia(),new BigDecimal(3)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByPrecioventa() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(3)); 
		value.setPrecioagencia(new BigDecimal(3)); 
		value.setPrecioventa(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2));
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByPrecioventa(new BigDecimal(3));  
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getPrecioventa(),new BigDecimal(3));  

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByDescuento() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(3)); 
		value.setPrecioagencia(new BigDecimal(3)); 
		value.setPrecioventa(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setIva(new BigDecimal(2));
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByDescuento(new BigDecimal(3)); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(3));  

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByIva() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(3)); 
		value.setPrecioagencia(new BigDecimal(3)); 
		value.setPrecioventa(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByIva(new BigDecimal(3));  
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(3)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByProveedorId() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setCantidad(new BigDecimal(3)); 
		value.setPrecioagencia(new BigDecimal(3)); 
		value.setPrecioventa(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByProveedorId(new Long(1));  
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getProveedorId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoDetalleByQuery() throws Exception {

		PresupuestoDetalleIf value = new PresupuestoDetalleData();


		value.setPresupuestoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setCantidad(new BigDecimal(5)); 
		value.setPrecioagencia(new BigDecimal(5)); 
		value.setPrecioventa(new BigDecimal(5)); 
		value.setDescuento(new BigDecimal(5)); 
		value.setIva(new BigDecimal(5)); 
		value.setProveedorId(new Long(1));

		getPresupuestoDetalleSessionService().addPresupuestoDetalle(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("presupuestoId",new Long(1));  
		parametros.put("productoId",new Long(1));  
		parametros.put("concepto","CONCEPTOx"); 
		parametros.put("cantidad",new BigDecimal(5)); 
		parametros.put("precioagencia",new BigDecimal(5));  
		parametros.put("precioventa",new BigDecimal(5));  
		parametros.put("descuento",new BigDecimal(5)); 
		parametros.put("iva",new BigDecimal(5));
		parametros.put("proveedorId",new Long(1));

		Collection c = getPresupuestoDetalleSessionService().findPresupuestoDetalleByQuery(parametros); 
		PresupuestoDetalleIf resultado = (PresupuestoDetalleIf)c.iterator().next();


		Assert.assertEquals(resultado.getPresupuestoId(),new Long(1));  
		Assert.assertEquals(resultado.getProductoId(),new Long(1));  
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTOx"); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getPrecioagencia(),new BigDecimal(5));  
		Assert.assertEquals(resultado.getPrecioventa(),new BigDecimal(5));  
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(5));  
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5));  
		Assert.assertEquals(resultado.getProveedorId(),new Long(1));


	}

	public static PresupuestoDetalleSessionService getPresupuestoDetalleSessionService() {
		try {
			return (PresupuestoDetalleSessionService) ServiceLocator.getService(ServiceLocator.PRESUPUESTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PresupuestoDetalleSessionTest.class);

	}
}
