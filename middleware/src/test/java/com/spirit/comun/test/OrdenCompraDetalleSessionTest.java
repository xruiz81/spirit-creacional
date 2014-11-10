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

import com.spirit.compras.entity.OrdenCompraDetalleData;
import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.compras.session.OrdenCompraDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class OrdenCompraDetalleSessionTest {


   //id=>NUMBER(10)
   //documentoId=>NUMBER(10)
   //productoId=>NUMBER(10)
   //ordencompraId=>NUMBER(10)
   //cantidad=>NUMBER(10)
   //valor=>FLOAT(126)
   //descuento=>FLOAT(126)
   //iva=>FLOAT(126)
   //ice=>FLOAT(126)
   //otroImpuesto=>FLOAT(126)
   //descripcion=>VARCHAR2(300)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/orden_compra_detalle.xml");
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
   public void addOrdenCompraDetalle() throws Exception {
     OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
       value.setDocumentoId(new Long(1)); 
       value.setProductoId(new Long(1)); 
       value.setOrdencompraId(new Long(1)); 
       value.setCantidad(new Long(1)); 
       value.setValor(new BigDecimal(1)); 
       value.setDescuento(new BigDecimal(1)); 
       value.setIva(new BigDecimal(1)); 
       value.setIce(new BigDecimal(1)); 
       value.setOtroImpuesto(new BigDecimal(1));
       value.setDescripcion("DESCRIPCION1"); 
      
     OrdenCompraDetalleIf resultado = getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);
           
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidad(),new Long(1)); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1");
		
   }

  @Test (timeout=2000)
  public void saveOrdenCompraDetalle() throws Exception {
      
    OrdenCompraDetalleIf value = getOrdenCompraDetalleSessionService().getOrdenCompraDetalle(new Long(1));
 
          
       value.setDocumentoId(new Long(1)); 
       value.setProductoId(new Long(1)); 
       value.setOrdencompraId(new Long(1)); 
       value.setCantidad(new Long(1)); 
       value.setValor(new BigDecimal(2)); 
       value.setDescuento(new BigDecimal(2)); 
       value.setIva(new BigDecimal(2)); 
       value.setIce(new BigDecimal(2)); 
       value.setOtroImpuesto(new BigDecimal(2)); 
       value.setDescripcion("DESCRIPCION1");
      
     getOrdenCompraDetalleSessionService().saveOrdenCompraDetalle(value);

     OrdenCompraDetalleIf resultado = getOrdenCompraDetalleSessionService().getOrdenCompraDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidad(),new Long(1)); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getIva(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getIce(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
   }


 @Test (timeout=2000)
 public void deleteOrdenCompraDetalle() throws Exception {
      getOrdenCompraDetalleSessionService().deleteOrdenCompraDetalle(new Long(3));
      OrdenCompraDetalleIf resultado = getOrdenCompraDetalleSessionService().getOrdenCompraDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findOrdenCompraDetalleById() throws Exception {
       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleById(new Long(1)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByDocumentoId() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setDescripcion("DESCRIPCION1");
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByDocumentoId(new Long(1)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByProductoId() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2));  
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByProductoId(new Long(1)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByOrdencompraId() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2));  
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOrdencompraId(new Long(1)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByCantidad() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2));  
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByCantidad(new Long(2)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCantidad(),new Long(2)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByValor() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(3)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2));  
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByValor(new BigDecimal(3)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByDescuento() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(3)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByDescuento(new BigDecimal(3)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByIva() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(3)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByIva(new BigDecimal(3)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIva(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByIce() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(3)); 
    value.setOtroImpuesto(new BigDecimal(2));  
    value.setDescripcion("DESCRIPCION1");
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByIce(new BigDecimal(3)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIce(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByOtroImpuesto() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setOrdencompraId(new Long(1)); 
    value.setCantidad(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(3)); 
    value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByOtroImpuesto(new BigDecimal(3)); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findOrdenCompraDetalleByDescripcion() throws Exception {

 	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
      
 		value.setDocumentoId(new Long(1)); 
 		value.setProductoId(new Long(1)); 
 		value.setOrdencompraId(new Long(1)); 
 		value.setCantidad(new Long(1)); 
 		value.setValor(new BigDecimal(2)); 
 		value.setDescuento(new BigDecimal(2)); 
 		value.setIva(new BigDecimal(2)); 
 		value.setIce(new BigDecimal(2)); 
 		value.setOtroImpuesto(new BigDecimal(3)); 
        value.setDescripcion("DESCRIPCION1"); 
      
       getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

       Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByDescripcion("DESCRIPCION1"); 
       OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

   }
    
    @Test (timeout=2000)
    public void findOrdenCompraDetalleByQuery() throws Exception {
 	
    	OrdenCompraDetalleIf value = new OrdenCompraDetalleData();
 
       
    	value.setDocumentoId(new Long(1)); 
        value.setProductoId(new Long(1)); 
        value.setOrdencompraId(new Long(1)); 
        value.setCantidad(new Long(5)); 
        value.setValor(new BigDecimal(5)); 
        value.setDescuento(new BigDecimal(5)); 
        value.setIva(new BigDecimal(5)); 
        value.setIce(new BigDecimal(5)); 
        value.setOtroImpuesto(new BigDecimal(5)); 
        value.setDescripcion("DESCRIPCION1"); 
      
      getOrdenCompraDetalleSessionService().addOrdenCompraDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("documentoId",new Long(1)); 
       parametros.put("productoId",new Long(1)); 
       parametros.put("ordencompraId",new Long(1)); 
       parametros.put("cantidad",new Long(5)); 
       parametros.put("valor",new BigDecimal(5)); 
       parametros.put("descuento",new BigDecimal(5)); 
       parametros.put("iva",new BigDecimal(5)); 
       parametros.put("ice",new BigDecimal(5)); 
       parametros.put("otroImpuesto",new BigDecimal(5)); 
       parametros.put("descripcion","DESCRIPCION1"); 

     Collection c = getOrdenCompraDetalleSessionService().findOrdenCompraDetalleByQuery(parametros); 
     OrdenCompraDetalleIf resultado = (OrdenCompraDetalleIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
      Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
      Assert.assertEquals(resultado.getOrdencompraId(),new Long(1)); 
      Assert.assertEquals(resultado.getCantidad(),new Long(5)); 
      Assert.assertEquals(resultado.getValor(),new BigDecimal(5)); 
      Assert.assertEquals(resultado.getDescuento(),new BigDecimal(5)); 
      Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
      Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 
      Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 
      Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 


    }

public static OrdenCompraDetalleSessionService getOrdenCompraDetalleSessionService() {
		try {
			return (OrdenCompraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENCOMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(OrdenCompraDetalleSessionTest.class);

 }


 


}
