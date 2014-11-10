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

import com.spirit.facturacion.entity.FacturaDetalleData;
import com.spirit.facturacion.entity.FacturaDetalleIf;
import com.spirit.facturacion.session.FacturaDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class FacturaDetalleSessionTest {

	//id=>NUMBER(10)
	//documentoId=>NUMBER(10)
	//facturaId=>NUMBER(10)
	//productoId=>NUMBER(10)
	//loteId=>NUMBER(10)
	//descripcion=>VARCHAR2(100)
	//motivodocumentoId=>NUMBER(10)
	//cantidad=>NUMBER(22)
	//precio=>NUMBER(22)
	//precioReal=>NUMBER(22)
	//descuento=>NUMBER(22)
	//valor=>NUMBER(22)
	//iva=>NUMBER(22)
	//ice=>NUMBER(22)
	//otroImpuesto=>NUMBER(22)
	//costo=>NUMBER(22)
	//lineaId=>NUMBER(10)
	//cantidadDevuelta=>NUMBER(22)
	//descuentoGlobal=>NUMBER(22)
	//idSriClienteRetencion=>NUMBER(10)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/factura_detalle.xml");
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
	public void addFacturaDetalle() throws Exception {
		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		FacturaDetalleIf resultado = getFacturaDetalleSessionService().addFacturaDetalle(value);

		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getFacturaId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getPrecioReal(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidadDevuelta(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIdSriClienteRetencion(),new Long(1));
	}

	/*
	@Test (timeout=2000)
	public void saveFacturaDetalle() throws Exception {

		FacturaDetalleIf value = getFacturaDetalleSessionService().getFacturaDetalle(new Long(1));

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().saveFacturaDetalle(value);

		FacturaDetalleIf resultado = getFacturaDetalleSessionService().getFacturaDetalle(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getFacturaId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getPrecioReal(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidadDevuelta(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIdSriClienteRetencion(),new Long(1));
	}


	@Test (timeout=2000)
	public void deleteFacturaDetalle() throws Exception {
		getFacturaDetalleSessionService().deleteFacturaDetalle(new Long(1));
		FacturaDetalleIf resultado = getFacturaDetalleSessionService().getFacturaDetalle(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findFacturaDetalleById() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22));
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleById(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByDocumentoId() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByDocumentoId(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByFacturaId() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByFacturaId(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getFacturaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByProductoId() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByProductoId(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByLoteId() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByLoteId(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getLoteId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByDescripcion() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByDescripcion("DESCRIPCION1"); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByMotivodocumentoId() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByMotivodocumentoId(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByCantidad() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22));  
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByCantidad(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByPrecio() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22));
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByPrecio(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByPrecioReal() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByPrecioReal(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getPrecioReal(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByDescuento() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByDescuento(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByValor() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByValor(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByIva() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByIva(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByIce() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByIce(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByOtroImpuesto() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByOtroImpuesto(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByCosto() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByCosto(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByLineaId() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByLineaId(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByCantidadDevuelta() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22));  
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByCantidadDevuelta(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCantidadDevuelta(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByDescuentoGlobal() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22));  
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByDescuentoGlobal(new BigDecimal(22)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 

	}

	@Test (timeout=2000)
	public void findFacturaDetalleByIdSriClienteRetencion() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22));  
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByIdSriClienteRetencion(new Long(1)); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getIdSriClienteRetencion(),new Long(1)); 

	}


	@Test (timeout=2000)
	public void findFacturaDetalleByQuery() throws Exception {

		FacturaDetalleIf value = new FacturaDetalleData();

		value.setDocumentoId(new Long(1)); 
		value.setFacturaId(new Long(1)); 
		value.setProductoId(new Long(1)); 
		value.setLoteId(new Long(1)); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setMotivodocumentoId(new Long(1)); 
		value.setCantidad(new BigDecimal(22)); 
		value.setPrecio(new BigDecimal(22)); 
		value.setPrecioReal(new BigDecimal(22)); 
		value.setDescuento(new BigDecimal(22)); 
		value.setValor(new BigDecimal(22)); 
		value.setIva(new BigDecimal(22)); 
		value.setIce(new BigDecimal(22)); 
		value.setOtroImpuesto(new BigDecimal(22)); 
		value.setCosto(new BigDecimal(22)); 
		value.setLineaId(new Long(1)); 
		value.setCantidadDevuelta(new BigDecimal(22)); 
		value.setDescuentoGlobal(new BigDecimal(22)); 
		value.setIdSriClienteRetencion(new Long(1));

		getFacturaDetalleSessionService().addFacturaDetalle(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("documentoId",new Long(1)); 
		parametros.put("facturaId",new Long(1)); 
		parametros.put("productoId",new Long(1)); 
		parametros.put("loteId",new Long(1)); 
		parametros.put("descripcion","DESCRIPCION1"); 
		parametros.put("motivodocumentoId",new Long(1)); 
		parametros.put("cantidad",new BigDecimal(22)); 
		parametros.put("precio",new BigDecimal(22)); 
		parametros.put("precioReal",new BigDecimal(22)); 
		parametros.put("descuento",new BigDecimal(22)); 
		parametros.put("valor",new BigDecimal(22)); 
		parametros.put("iva",new BigDecimal(22)); 
		parametros.put("ice",new BigDecimal(22)); 
		parametros.put("otroImpuesto",new BigDecimal(22)); 
		parametros.put("costo",new BigDecimal(22)); 
		parametros.put("lineaId",new Long(1)); 
		parametros.put("cantidadDevuelta",new BigDecimal(22)); 
		parametros.put("descuentoGlobal",new BigDecimal(22)); 
		parametros.put("idSriClienteRetencion",new Long(1));

		Collection c = getFacturaDetalleSessionService().findFacturaDetalleByQuery(parametros); 
		FacturaDetalleIf resultado = (FacturaDetalleIf)c.iterator().next();

		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getFacturaId(),new Long(1)); 
		Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getPrecioReal(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCantidadDevuelta(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 
		Assert.assertEquals(resultado.getIdSriClienteRetencion(),new Long(1));
	}
	*/
	
	public static FacturaDetalleSessionService getFacturaDetalleSessionService() {
		try {
			return (FacturaDetalleSessionService) ServiceLocator.getService(ServiceLocator.FACTURADETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(FacturaDetalleSessionTest.class);

	}





}
