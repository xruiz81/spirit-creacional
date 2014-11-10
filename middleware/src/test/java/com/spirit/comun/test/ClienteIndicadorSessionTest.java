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

import com.spirit.crm.entity.ClienteIndicadorData;
import com.spirit.crm.entity.ClienteIndicadorIf;
import com.spirit.crm.session.ClienteIndicadorSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class ClienteIndicadorSessionTest {


   //id=>NUMBER(10)
   //clienteOficinaId=>NUMBER(10)
   //tipoindicadorId=>NUMBER(10)
   //valor=>NUMBER(22)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cliente_indicador.xml");
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
   public void addClienteIndicador() throws Exception {
     ClienteIndicadorIf value = new ClienteIndicadorData();
 
      
       value.setClienteOficinaId(new Long(1)); 
       value.setTipoindicadorId(new Long(1)); 
       value.setValor(new BigDecimal(1)); 
      
     ClienteIndicadorIf resultado = getClienteIndicadorSessionService().addClienteIndicador(value);
           
       Assert.assertEquals(resultado.getClienteOficinaId(),new Long(1));
       Assert.assertEquals(resultado.getTipoindicadorId(),new Long(1));
       Assert.assertEquals(resultado.getValor(),new BigDecimal(1));  
		
   }

  @Test (timeout=2000)
  public void saveClienteIndicador() throws Exception {
      
    ClienteIndicadorIf value = getClienteIndicadorSessionService().getClienteIndicador(new Long(1));
 
          
    value.setClienteOficinaId(new Long(1)); 
    value.setTipoindicadorId(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
      
     getClienteIndicadorSessionService().saveClienteIndicador(value);

     ClienteIndicadorIf resultado = getClienteIndicadorSessionService().getClienteIndicador(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getClienteOficinaId(),new Long(1));
       Assert.assertEquals(resultado.getTipoindicadorId(),new Long(1));
       Assert.assertEquals(resultado.getValor(),new BigDecimal(2));
		
   }


 @Test (timeout=2000)
 public void deleteClienteIndicador() throws Exception {
      getClienteIndicadorSessionService().deleteClienteIndicador(new Long(3));
      ClienteIndicadorIf resultado = getClienteIndicadorSessionService().getClienteIndicador(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findClienteIndicadorById() throws Exception {
       Collection c = getClienteIndicadorSessionService().findClienteIndicadorById(new Long(1)); 
       ClienteIndicadorIf resultado = (ClienteIndicadorIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findClienteIndicadorByClienteOficinaId() throws Exception {

 	ClienteIndicadorIf value = new ClienteIndicadorData();
 
      
 	value.setClienteOficinaId(new Long(1)); 
    value.setTipoindicadorId(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
      
       getClienteIndicadorSessionService().addClienteIndicador(value);

       Collection c = getClienteIndicadorSessionService().findClienteIndicadorByClienteOficinaId(new Long(1)); 
       ClienteIndicadorIf resultado = (ClienteIndicadorIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteOficinaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findClienteIndicadorByTipoindicadorId() throws Exception {

 	ClienteIndicadorIf value = new ClienteIndicadorData();
 
      
 	value.setClienteOficinaId(new Long(1)); 
    value.setTipoindicadorId(new Long(1)); 
    value.setValor(new BigDecimal(2)); 
      
       getClienteIndicadorSessionService().addClienteIndicador(value);

       Collection c = getClienteIndicadorSessionService().findClienteIndicadorByTipoindicadorId(new Long(1)); 
       ClienteIndicadorIf resultado = (ClienteIndicadorIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoindicadorId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findClienteIndicadorByValor() throws Exception {

 	ClienteIndicadorIf value = new ClienteIndicadorData();
 
      
 	value.setClienteOficinaId(new Long(1)); 
    value.setTipoindicadorId(new Long(1)); 
    value.setValor(new BigDecimal(3));  
      
       getClienteIndicadorSessionService().addClienteIndicador(value);

       Collection c = getClienteIndicadorSessionService().findClienteIndicadorByValor(new BigDecimal(3)); 
       ClienteIndicadorIf resultado = (ClienteIndicadorIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),new BigDecimal(3));

   }



    
    @Test (timeout=2000)
    public void findClienteIndicadorByQuery() throws Exception {
 	
    	ClienteIndicadorIf value = new ClienteIndicadorData();
 
       
    	value.setClienteOficinaId(new Long(1)); 
        value.setTipoindicadorId(new Long(1)); 
        value.setValor(new BigDecimal(5));   
      
      getClienteIndicadorSessionService().addClienteIndicador(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("clienteOficinaId",new Long(1)); 
       parametros.put("tipoindicadorId",new Long(1)); 
       parametros.put("valor",new BigDecimal(5));  

     Collection c = getClienteIndicadorSessionService().findClienteIndicadorByQuery(parametros); 
     ClienteIndicadorIf resultado = (ClienteIndicadorIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getClienteOficinaId(),new Long(1)); 
      Assert.assertEquals(resultado.getTipoindicadorId(),new Long(1));
      Assert.assertEquals(resultado.getValor(),new BigDecimal(5)); 


    }

public static ClienteIndicadorSessionService getClienteIndicadorSessionService() {
		try {
			return (ClienteIndicadorSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTEINDICADORSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteIndicadorSessionTest.class);

 }


 


}
