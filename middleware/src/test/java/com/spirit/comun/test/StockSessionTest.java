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

import com.spirit.inventario.entity.StockData;
import com.spirit.inventario.entity.StockIf;
import com.spirit.inventario.session.StockSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class StockSessionTest {


   //id=>NUMBER(10)
   //bodegaId=>NUMBER(10)
   //productoId=>NUMBER(10)
   //loteId=>NUMBER(10)
   //anio=>VARCHAR2(4)
   //mes=>VARCHAR2(2)
   //cantidad=>NUMBER(22)
   //reserva=>NUMBER(22)
   //transito=>NUMBER(22)
 
        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/stock.xml");
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
   public void addStock() throws Exception {
     StockIf value = new StockData();
 
      
       value.setBodegaId(new Long(1)); 
       value.setProductoId(new Long(1));
       value.setLoteId(new Long(1)); 
       value.setAnio("2000"); 
       value.setMes("01"); 
       value.setCantidad(new BigDecimal(1)); 
       value.setReserva(new BigDecimal(1)); 
       value.setTransito(new BigDecimal(1)); 
     
     StockIf resultado = getStockSessionService().addStock(value);
           
       Assert.assertEquals(resultado.getBodegaId(),new Long(1));  
       Assert.assertEquals(resultado.getProductoId(),new Long(1));  
       Assert.assertEquals(resultado.getLoteId(),new Long(1));  
       Assert.assertEquals(resultado.getAnio(),"2000"); 
       Assert.assertEquals(resultado.getMes(),"01"); 
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getReserva(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getTransito(),new BigDecimal(1)); 
  	
   }

  @Test (timeout=2000)
  public void saveStock() throws Exception {
      
    StockIf value = getStockSessionService().getStock(new Long(1));
 
          
    value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2001"); 
    value.setMes("02"); 
    value.setCantidad(new BigDecimal(2)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
    
     getStockSessionService().saveStock(value);

     StockIf resultado = getStockSessionService().getStock(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getBodegaId(),new Long(1));  
       Assert.assertEquals(resultado.getProductoId(),new Long(1));  
       Assert.assertEquals(resultado.getLoteId(),new Long(1));  
       Assert.assertEquals(resultado.getAnio(),"2001"); 
       Assert.assertEquals(resultado.getMes(),"02"); 
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getReserva(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getTransito(),new BigDecimal(2)); 
  		
   }


 @Test (timeout=2000)
 public void deleteStock() throws Exception {
      getStockSessionService().deleteStock(new Long(3));
      StockIf resultado = getStockSessionService().getStock(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findStockById() throws Exception {
       Collection c = getStockSessionService().findStockById(new Long(1)); 
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findStockByBodegaId() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2001"); 
    value.setMes("02"); 
    value.setCantidad(new BigDecimal(2)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
       
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByBodegaId(new Long(1));  
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findStockByProductoId() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2001"); 
    value.setMes("02"); 
    value.setCantidad(new BigDecimal(2)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
       
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByProductoId(new Long(1));
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getProductoId(),new Long(1));

   }

   @Test (timeout=2000)
   public void findStockByLoteId() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2001"); 
    value.setMes("02"); 
    value.setCantidad(new BigDecimal(2)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
       
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByLoteId(new Long(1)); 
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getLoteId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findStockByAnio() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2002"); 
    value.setMes("02"); 
    value.setCantidad(new BigDecimal(2)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
       
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByAnio("2002"); 
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getAnio(),"2002"); 

   }

   @Test (timeout=2000)
   public void findStockByMes() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2002"); 
    value.setMes("03"); 
    value.setCantidad(new BigDecimal(2)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
        
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByMes("03"); 
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getMes(),"03"); 

   }

   @Test (timeout=2000)
   public void findStockByCantidad() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2002"); 
    value.setMes("03"); 
    value.setCantidad(new BigDecimal(3)); 
    value.setReserva(new BigDecimal(2)); 
    value.setTransito(new BigDecimal(2)); 
        
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByCantidad(new BigDecimal(3)); 
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(3));  

   }

   @Test (timeout=2000)
   public void findStockByReserva() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2002"); 
    value.setMes("03"); 
    value.setCantidad(new BigDecimal(3)); 
    value.setReserva(new BigDecimal(3)); 
    value.setTransito(new BigDecimal(3)); 
       
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByReserva(new BigDecimal(3)); 
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getReserva(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findStockByTransito() throws Exception {

 	StockIf value = new StockData();
 
      
 	value.setBodegaId(new Long(1)); 
    value.setProductoId(new Long(1));
    value.setLoteId(new Long(1)); 
    value.setAnio("2002"); 
    value.setMes("03"); 
    value.setCantidad(new BigDecimal(3)); 
    value.setReserva(new BigDecimal(3)); 
    value.setTransito(new BigDecimal(3));
         
       getStockSessionService().addStock(value);

       Collection c = getStockSessionService().findStockByTransito(new BigDecimal(3));
       StockIf resultado = (StockIf)c.iterator().next();
       Assert.assertEquals(resultado.getTransito(),new BigDecimal(3));

   }

    @Test (timeout=2000)
    public void findStockByQuery() throws Exception {
 	
    	StockIf value = new StockData();
 
       
    	value.setBodegaId(new Long(1)); 
    	value.setProductoId(new Long(1));
    	value.setLoteId(new Long(1)); 
    	value.setAnio("2005"); 
    	value.setMes("05"); 
    	value.setCantidad(new BigDecimal(5)); 
    	value.setReserva(new BigDecimal(5)); 
    	value.setTransito(new BigDecimal(5));
    	      
      getStockSessionService().addStock(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("bodegaId",new Long(1));  
       parametros.put("productoId",new Long(1));  
       parametros.put("loteId",new Long(1));  
       parametros.put("anio","2005"); 
       parametros.put("mes","05"); 
       parametros.put("cantidad",new BigDecimal(5)); 
       parametros.put("reserva",new BigDecimal(5)); 
       parametros.put("transito",new BigDecimal(5)); 
       
     Collection c = getStockSessionService().findStockByQuery(parametros); 
     StockIf resultado = (StockIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getBodegaId(),new Long(1));  
      Assert.assertEquals(resultado.getProductoId(),new Long(1));  
      Assert.assertEquals(resultado.getLoteId(),new Long(1));  
      Assert.assertEquals(resultado.getAnio(),"2005"); 
      Assert.assertEquals(resultado.getMes(),"05"); 
      Assert.assertEquals(resultado.getCantidad(),new BigDecimal(5));  
      Assert.assertEquals(resultado.getReserva(),new BigDecimal(5));  
      Assert.assertEquals(resultado.getTransito(),new BigDecimal(5));  
     

    }

public static StockSessionService getStockSessionService() {
		try {
			return (StockSessionService) ServiceLocator
					.getService(ServiceLocator.STOCKSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(StockSessionTest.class);

 }


 


}
