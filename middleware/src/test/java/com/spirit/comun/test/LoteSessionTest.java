package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
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

import com.spirit.inventario.entity.LoteData;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.session.LoteSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class LoteSessionTest {

	Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(20)
   //productoId=>NUMBER(10)
   //fechaCreacion=>DATE
   //fechaElaboracion=>DATE
   //fechaVencimiento=>DATE
   //estado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/lote.xml");
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
   public void addLote() throws Exception {
     LoteIf value = new LoteData();
 
       value.setCodigo("COD"); 
       value.setProductoId(new Long(1)); 
       value.setFechaCreacion(now); 
       value.setFechaElaboracion(now); 
       value.setFechaVencimiento(now); 
       value.setEstado("A"); 
      
     LoteIf resultado = getLoteSessionService().addLote(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaCreacion(),now); 
       Assert.assertEquals(resultado.getFechaElaboracion(),now); 
       Assert.assertEquals(resultado.getFechaVencimiento(),now); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }

  @Test (timeout=2000)
  public void saveLote() throws Exception {
      
    LoteIf value = getLoteSessionService().getLote(new Long(1));
 
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
     getLoteSessionService().saveLote(value);

     LoteIf resultado = getLoteSessionService().getLote(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechaElaboracion().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }


 @Test (timeout=2000)
 public void deleteLote() throws Exception {
      getLoteSessionService().deleteLote(new Long(3));
      LoteIf resultado = getLoteSessionService().getLote(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findLoteById() throws Exception {

 	LoteIf value = new LoteData();
 
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteById(new Long(1)); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findLoteByCodigo() throws Exception {

 	LoteIf value = new LoteData();
 
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteByCodigo("COD"); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findLoteByProductoId() throws Exception {

 	LoteIf value = new LoteData();
 
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteByProductoId(new Long(1)); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getProductoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findLoteByFechaCreacion() throws Exception {

 	LoteIf value = new LoteData();
 
 	Date date = new Date(System.currentTimeMillis());
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteByFechaCreacion(new Timestamp(date.getTime())); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaCreacion().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findLoteByFechaElaboracion() throws Exception {

 	LoteIf value = new LoteData();
 
 	Date date = new Date(System.currentTimeMillis());
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteByFechaElaboracion(new Timestamp(date.getTime())); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaElaboracion().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findLoteByFechaVencimiento() throws Exception {

 	LoteIf value = new LoteData();
 
 	Date date = new Date(System.currentTimeMillis());
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteByFechaVencimiento(new Timestamp(date.getTime())); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaVencimiento().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findLoteByEstado() throws Exception {

 	LoteIf value = new LoteData();
 
    value.setCodigo("COD"); 
    value.setProductoId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaElaboracion(now); 
    value.setFechaVencimiento(now); 
    value.setEstado("A"); 
      
       getLoteSessionService().addLote(value);

       Collection c = getLoteSessionService().findLoteByEstado("A"); 
       LoteIf resultado = (LoteIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }



    
    @Test (timeout=2000)
    public void findLoteByQuery() throws Exception {
 	
LoteIf value = new LoteData();
 
value.setCodigo("COD"); 
value.setProductoId(new Long(1)); 
value.setFechaCreacion(now); 
value.setFechaElaboracion(now); 
value.setFechaVencimiento(now); 
value.setEstado("A"); 
      
      getLoteSessionService().addLote(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COD"); 
       parametros.put("productoId",new Long(1)); 
       parametros.put("fechaCreacion",now); 
       parametros.put("fechaElaboracion",now); 
       parametros.put("fechaVencimiento",now); 
       parametros.put("estado","A"); 

     Collection c = getLoteSessionService().findLoteByQuery(parametros); 
     LoteIf resultado = (LoteIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getProductoId(),new Long(1)); 
     Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaElaboracion().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
     Assert.assertEquals(resultado.getEstado(),"A");  


    }

public static LoteSessionService getLoteSessionService() {
		try {
			return (LoteSessionService) ServiceLocator
					.getService(ServiceLocator.LOTESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LoteSessionTest.class);

 }

}
