package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
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

import com.spirit.general.entity.MonedaData;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.session.MonedaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class MonedaSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(3)
   //nombre=>VARCHAR2(30)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/moneda.xml");
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
   public void addMoneda() throws Exception {
     MonedaIf value = new MonedaData();
 
     
       value.setCodigo("COD"); 
       value.setNombre("NOMBRE1"); 
      
     MonedaIf resultado = getMonedaSessionService().addMoneda(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		
   }

  @Test (timeout=2000)
  public void saveMoneda() throws Exception {
      
    MonedaIf value = getMonedaSessionService().getMoneda(new Long(1));
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
      
     getMonedaSessionService().saveMoneda(value);

     MonedaIf resultado = getMonedaSessionService().getMoneda(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		
   }


 @Test (timeout=2000)
 public void deleteMoneda() throws Exception {
      getMonedaSessionService().deleteMoneda(new Long(3));
      MonedaIf resultado = getMonedaSessionService().getMoneda(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findMonedaById() throws Exception {

 	MonedaIf value = new MonedaData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
      
       getMonedaSessionService().addMoneda(value);

       Collection c = getMonedaSessionService().findMonedaById(new Long(1)); 
       MonedaIf resultado = (MonedaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findMonedaByCodigo() throws Exception {

 	MonedaIf value = new MonedaData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
      
       getMonedaSessionService().addMoneda(value);

       Collection c = getMonedaSessionService().findMonedaByCodigo("COD"); 
       MonedaIf resultado = (MonedaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findMonedaByNombre() throws Exception {

 	MonedaIf value = new MonedaData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
      
       getMonedaSessionService().addMoneda(value);

       Collection c = getMonedaSessionService().findMonedaByNombre("NOMBRE1"); 
       MonedaIf resultado = (MonedaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }



    
    @Test (timeout=2000)
    public void findMonedaByQuery() throws Exception {
 	
MonedaIf value = new MonedaData();
 
value.setCodigo("COD"); 
value.setNombre("NOMBRE1"); 
      
      getMonedaSessionService().addMoneda(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COD"); 
       parametros.put("nombre","NOMBRE1"); 

     Collection c = getMonedaSessionService().findMonedaByQuery(parametros); 
     MonedaIf resultado = (MonedaIf)c.iterator().next();
      
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 


    }

public static MonedaSessionService getMonedaSessionService() {
		try {
			return (MonedaSessionService) ServiceLocator
					.getService(ServiceLocator.MONEDASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(MonedaSessionTest.class);

 }

}
