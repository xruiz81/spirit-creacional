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

import com.spirit.cartera.entity.NotaCreditoDetalleData;
import com.spirit.cartera.entity.NotaCreditoDetalleIf;
import com.spirit.cartera.session.NotaCreditoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class NotaCreditoDetalleSessionTest {
	//id=>NUMBER(10)
	//documentoId=>NUMBER(10)
	//notaCreditoId=>NUMBER(10)
	//productoId=>NUMBER(10)
	//cantidad=>NUMBER(22)
	//valor=>NUMBER(22)
	//iva=>NUMBER(22)
	//ice=>NUMBER(22)
	//otroImpuesto=>NUMBER(22)
	//descripcion=>VARCHAR2(300)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/nota_credito_detalle.xml");
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
	public void addNotaCreditoDetalle() throws Exception {
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setCantidad(new BigDecimal(1)); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		NotaCreditoDetalleIf resultado = getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getNotaCreditoId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
	}
	
	@Test (timeout=2000)
	public void saveNotaCreditoDetalle() throws Exception {
		
		NotaCreditoDetalleIf value = getNotaCreditoDetalleSessionService().getNotaCreditoDetalle(new Long(1));
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().saveNotaCreditoDetalle(value);
		
		NotaCreditoDetalleIf resultado = getNotaCreditoDetalleSessionService().getNotaCreditoDetalle(new Long(1));
		
		
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getNotaCreditoId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
	}
	
	
	@Test (timeout=2000)
	public void deleteNotaCreditoDetalle() throws Exception {
		getNotaCreditoDetalleSessionService().deleteNotaCreditoDetalle(new Long(3));
		NotaCreditoDetalleIf resultado = getNotaCreditoDetalleSessionService().getNotaCreditoDetalle(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleById() throws Exception {
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleById(new Long(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByDocumentoId() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByDocumentoId(new Long(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByNotaCreditoId() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByNotaCreditoId(new Long(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getNotaCreditoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByProductoId() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByProductoId(new Long(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByCantidad() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByCantidad(new BigDecimal(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByValor() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByValor(new BigDecimal(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByIva() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByIva(new BigDecimal(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByIce() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByIce(new BigDecimal(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByOtroImpuesto() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByOtroImpuesto(new BigDecimal(1)); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByDescripcion() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByDescripcion("DESCRIPCION1"); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
	}
	
	
	
	
	@Test (timeout=2000)
	public void findNotaCreditoDetalleByQuery() throws Exception {
		
		NotaCreditoDetalleIf value = new NotaCreditoDetalleData();
		
		
		value.setDocumentoId(new Long(1)); 
		value.setNotaCreditoId(new Long(1)); 
		value.setProductoId(new Long(1));  
		value.setCantidad(new BigDecimal(1));
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setIce(new BigDecimal(1)); 
		value.setOtroImpuesto(new BigDecimal(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		
		getNotaCreditoDetalleSessionService().addNotaCreditoDetalle(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		parametros.put("documentoId",new Long(1)); 
		parametros.put("notaCreditoId",new Long(1)); 
		parametros.put("productoId",new Long(1)); 
		parametros.put("cantidad",new BigDecimal(1)); 
		parametros.put("valor",new BigDecimal(1)); 
		parametros.put("iva",new BigDecimal(1)); 
		parametros.put("ice",new BigDecimal(1)); 
		parametros.put("otroImpuesto",new BigDecimal(1)); 
		parametros.put("descripcion","DESCRIPCION1"); 
		
		Collection c = getNotaCreditoDetalleSessionService().findNotaCreditoDetalleByQuery(parametros); 
		NotaCreditoDetalleIf resultado = (NotaCreditoDetalleIf)c.iterator().next();
		
		
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getNotaCreditoId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
		
	}
	
	public static NotaCreditoDetalleSessionService getNotaCreditoDetalleSessionService() {
		try {
			return (NotaCreditoDetalleSessionService) ServiceLocator.getService(ServiceLocator.NOTACREDITODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(NotaCreditoDetalleSessionTest.class);
		
	}
}
