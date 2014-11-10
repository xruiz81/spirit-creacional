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

import com.spirit.client.SpiritAlert;
import com.spirit.contabilidad.entity.TipoCuentaData;
import com.spirit.contabilidad.entity.TipoCuentaIf;
import com.spirit.contabilidad.session.TipoCuentaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class TipoCuentaSessionTest {


	
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(2)
   //nombre=>VARCHAR2(50)
   //debehaber=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_cuenta.xml");
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
   public void addTipoCuenta() throws Exception {
     TipoCuentaIf value = new TipoCuentaData();
 
      
       value.setCodigo("C1"); 
       value.setNombre("NOMBRE1"); 
       value.setDebehaber("D"); 
      
     TipoCuentaIf resultado = getTipoCuentaSessionService().addTipoCuenta(value);
           
       Assert.assertEquals(resultado.getCodigo(),"C1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getDebehaber(),"D"); 
		
   }

  @Test (timeout=2000)
  public void saveTipoCuenta() throws Exception {
      
    TipoCuentaIf value = getTipoCuentaSessionService().getTipoCuenta(new Long(1));
 
          
    value.setCodigo("C2"); 
    value.setNombre("NOMBRE2"); 
    value.setDebehaber("H"); 
      
     getTipoCuentaSessionService().saveTipoCuenta(value);

     TipoCuentaIf resultado = getTipoCuentaSessionService().getTipoCuenta(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"C2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getDebehaber(),"H"); 
		
   }


 @Test (timeout=2000)
 public void deleteTipoCuenta() throws Exception {
      getTipoCuentaSessionService().deleteTipoCuenta(new Long(3));
      TipoCuentaIf resultado = getTipoCuentaSessionService().getTipoCuenta(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoCuentaById() throws Exception {
       Collection c = getTipoCuentaSessionService().findTipoCuentaById(new Long(1)); 
       TipoCuentaIf resultado = (TipoCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findTipoCuentaByCodigo() throws Exception {

 	TipoCuentaIf value = new TipoCuentaData();
 
      
 	value.setCodigo("Cx"); 
    value.setNombre("NOMBRE1"); 
    value.setDebehaber("D"); 
      
       getTipoCuentaSessionService().addTipoCuenta(value);

       Collection c = getTipoCuentaSessionService().findTipoCuentaByCodigo("Cx"); 
       TipoCuentaIf resultado = (TipoCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"Cx"); 

   }

   @Test (timeout=2000)
   public void findTipoCuentaByNombre() throws Exception {

 	TipoCuentaIf value = new TipoCuentaData();
 
      
 	value.setCodigo("C1"); 
    value.setNombre("NOMBREx"); 
    value.setDebehaber("D");  
      
       getTipoCuentaSessionService().addTipoCuenta(value);

       Collection c = getTipoCuentaSessionService().findTipoCuentaByNombre("NOMBREx"); 
       TipoCuentaIf resultado = (TipoCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }

   @Test (timeout=2000)
   public void findTipoCuentaByDebehaber() throws Exception {

 	TipoCuentaIf value = new TipoCuentaData();
 
      
 	value.setCodigo("C1"); 
    value.setNombre("NOMBRE1"); 
    value.setDebehaber("x");  
      
       getTipoCuentaSessionService().addTipoCuenta(value);

       Collection c = getTipoCuentaSessionService().findTipoCuentaByDebehaber("x"); 
       TipoCuentaIf resultado = (TipoCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDebehaber(),"x"); 

   }



    
    @Test (timeout=2000)
    public void findTipoCuentaByQuery() throws Exception {
 	
    	TipoCuentaIf value = new TipoCuentaData();
 
       
    	value.setCodigo("Cy"); 
    	value.setNombre("NOMBREy"); 
    	value.setDebehaber("y"); 
      
      getTipoCuentaSessionService().addTipoCuenta(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","Cy"); 
       parametros.put("nombre","NOMBREy"); 
       parametros.put("debehaber","y"); 

     Collection c = getTipoCuentaSessionService().findTipoCuentaByQuery(parametros); 
     TipoCuentaIf resultado = (TipoCuentaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"Cy"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
      Assert.assertEquals(resultado.getDebehaber(),"y"); 


    }

public static TipoCuentaSessionService getTipoCuentaSessionService() {
		try {
			return (TipoCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoCuentaSessionTest.class);

 }


 


}
