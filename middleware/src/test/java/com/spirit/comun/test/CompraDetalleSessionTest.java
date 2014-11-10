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

import com.spirit.compras.entity.CompraDetalleData;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.session.CompraDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class CompraDetalleSessionTest {

   //id=>NUMBER(10)
   //documentoId=>NUMBER(10)
   //compraId=>NUMBER(10)
   //productoId=>NUMBER(10)
   //cantidad=>NUMBER(10)
   //valor=>FLOAT(126)
   //descuento=>FLOAT(126)
   //iva=>FLOAT(126)
   //ice=>FLOAT(126)
   //otroImpuesto=>FLOAT(126)
   //idSriProveedorRetencion=>NUMBER(10)
   //descripcion=>VARCHAR2(300)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/compra_detalle.xml");
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
   public void addCompraDetalle() throws Exception {
     CompraDetalleIf value = new CompraDetalleData();
 
      
       value.setDocumentoId(new Long(1)); 
       value.setCompraId(new Long(1)); 
       value.setProductoId(new Long(1)); 
       value.setCantidad(new Long(1)); 
       value.setValor(new BigDecimal(1)); 
       value.setDescuento(new BigDecimal(1)); 
       value.setIva(new BigDecimal(1)); 
       value.setIce(new BigDecimal(1)); 
       value.setOtroImpuesto(new BigDecimal(1)); 
       value.setIdSriAir(new Long(1));
       value.setDescripcion("DESCRIPCION1");
      
     CompraDetalleIf resultado = getCompraDetalleSessionService().addCompraDetalle(value);
           
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCompraId(),new Long(1)); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidad(),new Long(1)); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getIce(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(1));
       Assert.assertEquals(resultado.getIdSriAir(),new Long(1));
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
   }

  @Test (timeout=2000)
  public void saveCompraDetalle() throws Exception {
      
    CompraDetalleIf value = getCompraDetalleSessionService().getCompraDetalle(new Long(1));
 
          
    value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
     getCompraDetalleSessionService().saveCompraDetalle(value);

     CompraDetalleIf resultado = getCompraDetalleSessionService().getCompraDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCompraId(),new Long(1)); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidad(),new Long(2)); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getIva(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getIce(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(2));
       Assert.assertEquals(resultado.getIdSriAir(),new Long(1)); 
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
   }


 @Test (timeout=2000)
 public void deleteCompraDetalle() throws Exception {
      getCompraDetalleSessionService().deleteCompraDetalle(new Long(3));
      CompraDetalleIf resultado = getCompraDetalleSessionService().getCompraDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findCompraDetalleById() throws Exception {
       Collection c = getCompraDetalleSessionService().findCompraDetalleById(new Long(1)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByDocumentoId() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2));
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByDocumentoId(new Long(1)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByCompraId() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByCompraId(new Long(1)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCompraId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByProductoId() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByProductoId(new Long(1)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByCantidad() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(5)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2));
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByCantidad(new Long(5)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCantidad(),new Long(5)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByValor() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(5)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByValor(new BigDecimal(5)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByDescuento() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(5)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByDescuento(new BigDecimal(5)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByIva() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(5)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1");
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByIva(new BigDecimal(5)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByIce() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(5)); 
    value.setOtroImpuesto(new BigDecimal(2)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1");
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByIce(new BigDecimal(5)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByOtroImpuesto() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(5)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByOtroImpuesto(new BigDecimal(5)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByIdSriAir() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(5)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByIdSriAir(new Long(1)); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getIdSriAir(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCompraDetalleByDescripcion() throws Exception {

 	CompraDetalleIf value = new CompraDetalleData();
 
      
 	value.setDocumentoId(new Long(1)); 
    value.setCompraId(new Long(1)); 
    value.setProductoId(new Long(1)); 
    value.setCantidad(new Long(2)); 
    value.setValor(new BigDecimal(2)); 
    value.setDescuento(new BigDecimal(2)); 
    value.setIva(new BigDecimal(2)); 
    value.setIce(new BigDecimal(2)); 
    value.setOtroImpuesto(new BigDecimal(5)); 
    value.setIdSriAir(new Long(1));
    value.setDescripcion("DESCRIPCION1"); 
      
       getCompraDetalleSessionService().addCompraDetalle(value);

       Collection c = getCompraDetalleSessionService().findCompraDetalleByDescripcion("DESCRIPCION1"); 
       CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

   }

    
    @Test (timeout=2000)
    public void findCompraDetalleByQuery() throws Exception {
 	
    	CompraDetalleIf value = new CompraDetalleData();
 
       
    	value.setDocumentoId(new Long(1)); 
    	value.setCompraId(new Long(1)); 
    	value.setProductoId(new Long(1)); 
    	value.setCantidad(new Long(6)); 
    	value.setValor(new BigDecimal(6)); 
    	value.setDescuento(new BigDecimal(6)); 
    	value.setIva(new BigDecimal(6)); 
    	value.setIce(new BigDecimal(6)); 
    	value.setOtroImpuesto(new BigDecimal(6)); 
    	value.setIdSriAir(new Long(1));
    	value.setDescripcion("DESCRIPCION1"); 
      
      getCompraDetalleSessionService().addCompraDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("documentoId",new Long(1)); 
       parametros.put("compraId",new Long(1)); 
       parametros.put("productoId",new Long(1)); 
       parametros.put("cantidad",new Long(6)); 
       parametros.put("valor",new BigDecimal(6)); 
       parametros.put("descuento",new BigDecimal(6)); 
       parametros.put("iva",new BigDecimal(6)); 
       parametros.put("ice",new BigDecimal(6)); 
       parametros.put("otroImpuesto",new BigDecimal(6));
       parametros.put("idSriAir",new Long(1));
       parametros.put("descripcion","DESCRIPCION1");

     Collection c = getCompraDetalleSessionService().findCompraDetalleByQuery(parametros); 
     CompraDetalleIf resultado = (CompraDetalleIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
      Assert.assertEquals(resultado.getCompraId(),new Long(1)); 
      Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
      Assert.assertEquals(resultado.getCantidad(),new Long(6)); 
      Assert.assertEquals(resultado.getValor(),new BigDecimal(6)); 
      Assert.assertEquals(resultado.getDescuento(),new BigDecimal(6)); 
      Assert.assertEquals(resultado.getIva(),new BigDecimal(6)); 
      Assert.assertEquals(resultado.getIce(),new BigDecimal(6)); 
      Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(6));
      Assert.assertEquals(resultado.getIdSriAir(),new Long(1)); 
      Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 


    }

public static CompraDetalleSessionService getCompraDetalleSessionService() {
		try {
			return (CompraDetalleSessionService) ServiceLocator.getService(ServiceLocator.COMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CompraDetalleSessionTest.class);

 }


 


}
