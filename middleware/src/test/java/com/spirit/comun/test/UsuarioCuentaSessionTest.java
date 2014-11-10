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

import com.spirit.seguridad.entity.UsuarioCuentaData;
import com.spirit.seguridad.entity.UsuarioCuentaIf;
import com.spirit.seguridad.session.UsuarioCuentaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class UsuarioCuentaSessionTest {


   //id=>NUMBER(10)
   //usuarioId=>NUMBER(10)
   //cuentaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/usuario_cuenta.xml");
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
   public void addUsuarioCuenta() throws Exception {
     UsuarioCuentaIf value = new UsuarioCuentaData();
 
      
       value.setUsuarioId(new Long(1)); 
       value.setCuentaId(new Long(1)); 
      
     UsuarioCuentaIf resultado = getUsuarioCuentaSessionService().addUsuarioCuenta(value);
           
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveUsuarioCuenta() throws Exception {
      
    UsuarioCuentaIf value = getUsuarioCuentaSessionService().getUsuarioCuenta(new Long(1));
 
          
       value.setUsuarioId(new Long(1)); 
       value.setCuentaId(new Long(1)); 
      
     getUsuarioCuentaSessionService().saveUsuarioCuenta(value);

     UsuarioCuentaIf resultado = getUsuarioCuentaSessionService().getUsuarioCuenta(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteUsuarioCuenta() throws Exception {
      getUsuarioCuentaSessionService().deleteUsuarioCuenta(new Long(3));
      UsuarioCuentaIf resultado = getUsuarioCuentaSessionService().getUsuarioCuenta(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findUsuarioCuentaById() throws Exception {

       Collection c = getUsuarioCuentaSessionService().findUsuarioCuentaById(new Long(1)); 
       UsuarioCuentaIf resultado = (UsuarioCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioCuentaByUsuarioId() throws Exception {

 	UsuarioCuentaIf value = new UsuarioCuentaData();
 
      
       value.setUsuarioId(new Long(1)); 
       value.setCuentaId(new Long(1)); 
      
       getUsuarioCuentaSessionService().addUsuarioCuenta(value);

       Collection c = getUsuarioCuentaSessionService().findUsuarioCuentaByUsuarioId(new Long(1)); 
       UsuarioCuentaIf resultado = (UsuarioCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioCuentaByCuentaId() throws Exception {

 	UsuarioCuentaIf value = new UsuarioCuentaData();
 
      
       value.setUsuarioId(new Long(1)); 
       value.setCuentaId(new Long(1)); 
      
       getUsuarioCuentaSessionService().addUsuarioCuenta(value);

       Collection c = getUsuarioCuentaSessionService().findUsuarioCuentaByCuentaId(new Long(1)); 
       UsuarioCuentaIf resultado = (UsuarioCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findUsuarioCuentaByQuery() throws Exception {
 	
    	UsuarioCuentaIf value = new UsuarioCuentaData();
 
       
       value.setUsuarioId(new Long(1)); 
       value.setCuentaId(new Long(1)); 
      
      getUsuarioCuentaSessionService().addUsuarioCuenta(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("usuarioId",new Long(1)); 
       parametros.put("cuentaId",new Long(1)); 

     Collection c = getUsuarioCuentaSessionService().findUsuarioCuentaByQuery(parametros); 
     UsuarioCuentaIf resultado = (UsuarioCuentaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
      Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 


    }

public static UsuarioCuentaSessionService getUsuarioCuentaSessionService() {
		try {
			return (UsuarioCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(UsuarioCuentaSessionTest.class);

 }


 


}
