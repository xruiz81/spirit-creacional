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

import com.spirit.facturacion.entity.PedidoDetalleData;
import com.spirit.facturacion.entity.PedidoDetalleIf;
import com.spirit.facturacion.session.PedidoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class PedidoDetalleSessionTest {

   //id=>NUMBER(10)
   //documentoId=>NUMBER(10)
   //pedidoId=>NUMBER(10)
   //productoId=>NUMBER(10)
   //loteId=>NUMBER(10)
   //descripcion=>VARCHAR2(100)
   //motivodocumentoId=>NUMBER(10)
   //cantidadpedida=>NUMBER(22)
   //cantidad=>NUMBER(22)
   //precio=>NUMBER(22)
   //precioreal=>NUMBER(22)
   //descuento=>NUMBER(22)
   //valor=>NUMBER(22)
   //iva=>NUMBER(22)
   //ice=>NUMBER(22)
   //otroimpuesto=>NUMBER(22)
   //descuentoGlobal=>NUMBER(22)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/pedido_detalle.xml");
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
   public void addPedidoDetalle() throws Exception {
     PedidoDetalleIf value = new PedidoDetalleData();
 
       value.setDocumentoId(new Long(1)); 
       value.setPedidoId(new Long(1)); 
       value.setProductoId(new Long(1)); 
       value.setLoteId(new Long(1)); 
       value.setDescripcion("DESCRIPCION1"); 
       value.setMotivodocumentoId(new Long(1)); 
       value.setCantidadpedida(new BigDecimal(22)); 
       value.setCantidad(new BigDecimal(22)); 
       value.setPrecio(new BigDecimal(22)); 
       value.setPrecioreal(new BigDecimal(22)); 
       value.setDescuento(new BigDecimal(22)); 
       value.setValor(new BigDecimal(22)); 
       value.setIva(new BigDecimal(22)); 
       value.setIce(new BigDecimal(22)); 
       value.setOtroimpuesto(new BigDecimal(22)); 
       value.setDescuentoGlobal(new BigDecimal(22)); 
      
     PedidoDetalleIf resultado = getPedidoDetalleSessionService().addPedidoDetalle(value);
           
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
       Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidadpedida(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getPrecioreal(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getOtroimpuesto(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 
		
   }

  @Test (timeout=2000)
  public void savePedidoDetalle() throws Exception {
      
    PedidoDetalleIf value = getPedidoDetalleSessionService().getPedidoDetalle(new Long(1));
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
     getPedidoDetalleSessionService().savePedidoDetalle(value);

     PedidoDetalleIf resultado = getPedidoDetalleSessionService().getPedidoDetalle(new Long(1));

     Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 
     Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
     Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
     Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
     Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getCantidadpedida(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getPrecioreal(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getOtroimpuesto(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 
		
   }


 @Test (timeout=2000)
 public void deletePedidoDetalle() throws Exception {
      getPedidoDetalleSessionService().deletePedidoDetalle(new Long(2));
      PedidoDetalleIf resultado = getPedidoDetalleSessionService().getPedidoDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPedidoDetalleById() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleById(new Long(1)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByDocumentoId() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22));  
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByDocumentoId(new Long(1)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByPedidoId() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByPedidoId(new Long(1)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByProductoId() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByProductoId(new Long(1)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByLoteId() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByLoteId(new Long(1)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLoteId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByDescripcion() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByDescripcion("DESCRIPCION1"); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByMotivodocumentoId() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByMotivodocumentoId(new Long(1)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByCantidadpedida() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByCantidadpedida(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCantidadpedida(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByCantidad() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByCantidad(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByPrecio() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByPrecio(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByPrecioreal() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByPrecioreal(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getPrecioreal(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByDescuento() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByDescuento(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByValor() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByValor(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByIva() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByIva(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByIce() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByIce(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findPedidoDetalleByOtroimpuesto() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByOtroimpuesto(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getOtroimpuesto(),new BigDecimal(22)); 

   }

   @Test (timeout=3000)
   public void findPedidoDetalleByDescuentoGlobal() throws Exception {

 	PedidoDetalleIf value = new PedidoDetalleData();
 
    value.setDocumentoId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setDescripcion("DESCRIPCION1"); 
    value.setMotivodocumentoId(new Long(1)); 
    value.setCantidadpedida(new BigDecimal(22)); 
    value.setCantidad(new BigDecimal(22)); 
    value.setPrecio(new BigDecimal(22)); 
    value.setPrecioreal(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setValor(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroimpuesto(new BigDecimal(22)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getPedidoDetalleSessionService().addPedidoDetalle(value);

       Collection c = getPedidoDetalleSessionService().findPedidoDetalleByDescuentoGlobal(new BigDecimal(22)); 
       PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 

   }



    
    @Test (timeout=2000)
    public void findPedidoDetalleByQuery() throws Exception {
 	
PedidoDetalleIf value = new PedidoDetalleData();
 
value.setDocumentoId(new Long(1)); 
value.setPedidoId(new Long(1)); 
value.setProductoId(new Long(1)); 
value.setLoteId(new Long(1)); 
value.setDescripcion("DESCRIPCION1"); 
value.setMotivodocumentoId(new Long(1)); 
value.setCantidadpedida(new BigDecimal(22)); 
value.setCantidad(new BigDecimal(22)); 
value.setPrecio(new BigDecimal(22)); 
value.setPrecioreal(new BigDecimal(22)); 
value.setDescuento(new BigDecimal(22)); 
value.setValor(new BigDecimal(22)); 
value.setIva(new BigDecimal(22)); 
value.setIce(new BigDecimal(22)); 
value.setOtroimpuesto(new BigDecimal(22)); 
value.setDescuentoGlobal(new BigDecimal(22)); 
      
      getPedidoDetalleSessionService().addPedidoDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("documentoId",new Long(1)); 
       parametros.put("pedidoId",new Long(1)); 
       parametros.put("productoId",new Long(1)); 
       parametros.put("loteId",new Long(1)); 
       parametros.put("descripcion","DESCRIPCION1"); 
       parametros.put("motivodocumentoId",new Long(1)); 
       parametros.put("cantidadpedida",new BigDecimal(22)); 
       parametros.put("cantidad",new BigDecimal(22)); 
       parametros.put("precio",new BigDecimal(22)); 
       parametros.put("precioreal",new BigDecimal(22)); 
       parametros.put("descuento",new BigDecimal(22)); 
       parametros.put("valor",new BigDecimal(22)); 
       parametros.put("iva",new BigDecimal(22)); 
       parametros.put("ice",new BigDecimal(22)); 
       parametros.put("otroimpuesto",new BigDecimal(22)); 
       parametros.put("descuentoGlobal",new BigDecimal(22)); 

     Collection c = getPedidoDetalleSessionService().findPedidoDetalleByQuery(parametros); 
     PedidoDetalleIf resultado = (PedidoDetalleIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 
     Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
     Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
     Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
     Assert.assertEquals(resultado.getMotivodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getCantidadpedida(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getCantidad(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getPrecio(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getPrecioreal(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getOtroimpuesto(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 


    }

public static PedidoDetalleSessionService getPedidoDetalleSessionService() {
		try {
			return (PedidoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PEDIDODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PedidoDetalleSessionTest.class);

 }

}
