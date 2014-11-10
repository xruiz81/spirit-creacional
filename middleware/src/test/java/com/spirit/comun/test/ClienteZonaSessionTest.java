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

import com.spirit.crm.entity.ClienteZonaData;
import com.spirit.crm.entity.ClienteZonaIf;
import com.spirit.crm.session.ClienteZonaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class ClienteZonaSessionTest {


   //id=>NUMBER(10)
   //clienteId=>NUMBER(10)
   //codigo=>VARCHAR2(10)
   //nombre=>VARCHAR2(30)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cliente_zona.xml");
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
   public void addClienteZona() throws Exception {
     ClienteZonaIf value = new ClienteZonaData();
 
      
       value.setClienteId(new Long(1)); 
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
      
     ClienteZonaIf resultado = getClienteZonaSessionService().addClienteZona(value);
           
       Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		
   }

  @Test (timeout=2000)
  public void saveClienteZona() throws Exception {
      
    ClienteZonaIf value = getClienteZonaSessionService().getClienteZona(new Long(1));
 
          
    value.setClienteId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
      
     getClienteZonaSessionService().saveClienteZona(value);

     ClienteZonaIf resultado = getClienteZonaSessionService().getClienteZona(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
       Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
		
   }


 @Test (timeout=2000)
 public void deleteClienteZona() throws Exception {
      getClienteZonaSessionService().deleteClienteZona(new Long(3));
      ClienteZonaIf resultado = getClienteZonaSessionService().getClienteZona(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findClienteZonaById() throws Exception {
       Collection c = getClienteZonaSessionService().findClienteZonaById(new Long(1)); 
       ClienteZonaIf resultado = (ClienteZonaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findClienteZonaByClienteId() throws Exception {

 	ClienteZonaIf value = new ClienteZonaData();
 
      
 	value.setClienteId(new Long(1)); 
    value.setCodigo("CODIGO1"); 
    value.setNombre("NOMBRE1");  
      
       getClienteZonaSessionService().addClienteZona(value);

       Collection c = getClienteZonaSessionService().findClienteZonaByClienteId(new Long(1)); 
       ClienteZonaIf resultado = (ClienteZonaIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findClienteZonaByCodigo() throws Exception {

 	ClienteZonaIf value = new ClienteZonaData();
 
      
 	value.setClienteId(new Long(1)); 
    value.setCodigo("CODIGOx"); 
    value.setNombre("NOMBRE1"); 
      
       getClienteZonaSessionService().addClienteZona(value);

       Collection c = getClienteZonaSessionService().findClienteZonaByCodigo("CODIGOx"); 
       ClienteZonaIf resultado = (ClienteZonaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 

   }

   @Test (timeout=2000)
   public void findClienteZonaByNombre() throws Exception {

 	ClienteZonaIf value = new ClienteZonaData();
 
      
 	value.setClienteId(new Long(1)); 
    value.setCodigo("CODIGO1"); 
    value.setNombre("NOMBREx"); 
      
       getClienteZonaSessionService().addClienteZona(value);

       Collection c = getClienteZonaSessionService().findClienteZonaByNombre("NOMBREx"); 
       ClienteZonaIf resultado = (ClienteZonaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }



    
    @Test (timeout=2000)
    public void findClienteZonaByQuery() throws Exception {
 	
    	ClienteZonaIf value = new ClienteZonaData();
 
       
    	value.setClienteId(new Long(1)); 
        value.setCodigo("CODIGOy"); 
        value.setNombre("NOMBREy");  
      
      getClienteZonaSessionService().addClienteZona(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("clienteId",new Long(1)); 
       parametros.put("codigo","CODIGOy"); 
       parametros.put("nombre","NOMBREy"); 

     Collection c = getClienteZonaSessionService().findClienteZonaByQuery(parametros); 
     ClienteZonaIf resultado = (ClienteZonaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
      Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 


    }

public static ClienteZonaSessionService getClienteZonaSessionService() {
		try {
			return (ClienteZonaSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTEZONASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteZonaSessionTest.class);

 }


 


}
