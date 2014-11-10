package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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

import com.spirit.crm.entity.ClienteContactoData;
import com.spirit.crm.entity.ClienteContactoIf;
import com.spirit.crm.session.ClienteContactoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class ClienteContactoSessionTest {

	Timestamp now = new Timestamp(System.currentTimeMillis());
   //id=>NUMBER(10)
   //tipocontactoId=>NUMBER(10)
   //clienteoficinaId=>NUMBER(10)
   //nombre=>VARCHAR2(40)
   //direccion=>VARCHAR2(40)
   //telefonoOfic=>VARCHAR2(10)
   //telefonoCasa=>VARCHAR2(10)
   //celular=>VARCHAR2(10)
   //mail=>VARCHAR2(25)
   //cumpleanos=>DATE

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cliente_contacto.xml");
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
   public void addClienteContacto() throws Exception {
     ClienteContactoIf value = new ClienteContactoData();
 
      
       value.setTipocontactoId(new Long(1)); 
       value.setClienteoficinaId(new Long(1)); 
       value.setNombre("NOMBRE1"); 
       value.setDireccion("DIRECCION1"); 
       value.setTelefonoOfic("TELEFONO1"); 
       value.setTelefonoCasa("TELEFONO1"); 
       value.setCelular("CELULAR1"); 
       value.setMail("MAIL1"); 
       value.setCumpleanos(now); 
      
     ClienteContactoIf resultado = getClienteContactoSessionService().addClienteContacto(value);
           
       Assert.assertEquals(resultado.getTipocontactoId(),new Long(1)); 
       Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
       Assert.assertEquals(resultado.getTelefonoOfic(),"TELEFONO1"); 
       Assert.assertEquals(resultado.getTelefonoCasa(),"TELEFONO1"); 
       Assert.assertEquals(resultado.getCelular(),"CELULAR1"); 
       Assert.assertEquals(resultado.getMail(),"MAIL1"); 
       Assert.assertEquals(resultado.getCumpleanos(),now); 
		
   }

  @Test (timeout=2000)
  public void saveClienteContacto() throws Exception {
      
    ClienteContactoIf value = getClienteContactoSessionService().getClienteContacto(new Long(1));
 
          
    value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBRE2"); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefonoOfic("TELEFONO2"); 
    value.setTelefonoCasa("TELEFONO2"); 
    value.setCelular("CELULAR2"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now); 
      
     getClienteContactoSessionService().saveClienteContacto(value);

     ClienteContactoIf resultado = getClienteContactoSessionService().getClienteContacto(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getTipocontactoId(),new Long(1)); 
       Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION2"); 
       Assert.assertEquals(resultado.getTelefonoOfic(),"TELEFONO2"); 
       Assert.assertEquals(resultado.getTelefonoCasa(),"TELEFONO2"); 
       Assert.assertEquals(resultado.getCelular(),"CELULAR2"); 
       Assert.assertEquals(resultado.getMail(),"MAIL2"); 
       Assert.assertEquals(resultado.getCumpleanos().toString(),now.toString()); 
		
   }


 @Test (timeout=2000)
 public void deleteClienteContacto() throws Exception {
      getClienteContactoSessionService().deleteClienteContacto(new Long(3));
      ClienteContactoIf resultado = getClienteContactoSessionService().getClienteContacto(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findClienteContactoById() throws Exception {
       Collection c = getClienteContactoSessionService().findClienteContactoById(new Long(1)); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findClienteContactoByTipocontactoId() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	 value.setTipocontactoId(new Long(1)); 
     value.setClienteoficinaId(new Long(1)); 
     value.setNombre("NOMBRE2"); 
     value.setDireccion("DIRECCION2"); 
     value.setTelefonoOfic("TELEFONO2"); 
     value.setTelefonoCasa("TELEFONO2"); 
     value.setCelular("CELULAR2"); 
     value.setMail("MAIL2"); 
     value.setCumpleanos(now);  
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByTipocontactoId(new Long(1));
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipocontactoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByClienteoficinaId() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBRE2"); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefonoOfic("TELEFONO2"); 
    value.setTelefonoCasa("TELEFONO2"); 
    value.setCelular("CELULAR2"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now);
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByClienteoficinaId(new Long(1));
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1));

   }

   @Test (timeout=2000)
   public void findClienteContactoByNombre() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefonoOfic("TELEFONO2"); 
    value.setTelefonoCasa("TELEFONO2"); 
    value.setCelular("CELULAR2"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now);
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByNombre("NOMBREx"); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByDireccion() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefonoOfic("TELEFONO2"); 
    value.setTelefonoCasa("TELEFONO2"); 
    value.setCelular("CELULAR2"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now);
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByDireccion("DIRECCIONx"); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getDireccion(),"DIRECCIONx"); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByTelefonoOfic() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefonoOfic("TELEFONOx"); 
    value.setTelefonoCasa("TELEFONO2"); 
    value.setCelular("CELULAR2"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now); 
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByTelefonoOfic("TELEFONOx"); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTelefonoOfic(),"TELEFONOx"); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByTelefonoCasa() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefonoOfic("TELEFONOx"); 
    value.setTelefonoCasa("TELEFONOx"); 
    value.setCelular("CELULAR2"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now); 
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByTelefonoCasa("TELEFONOx"); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTelefonoCasa(),"TELEFONOx"); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByCelular() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefonoOfic("TELEFONOx"); 
    value.setTelefonoCasa("TELEFONOx"); 
    value.setCelular("CELULARx"); 
    value.setMail("MAIL2"); 
    value.setCumpleanos(now); 
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByCelular("CELULARx"); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCelular(),"CELULARx"); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByMail() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
      
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefonoOfic("TELEFONOx"); 
    value.setTelefonoCasa("TELEFONOx"); 
    value.setCelular("CELULARx"); 
    value.setMail("MAILx"); 
    value.setCumpleanos(now);  
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByMail("MAILx"); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getMail(),"MAILx"); 

   }

   @Test (timeout=2000)
   public void findClienteContactoByCumpleanos() throws Exception {

 	ClienteContactoIf value = new ClienteContactoData();
 
 	Timestamp date = new Timestamp(System.currentTimeMillis());
 	value.setTipocontactoId(new Long(1)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setNombre("NOMBREx"); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefonoOfic("TELEFONOx"); 
    value.setTelefonoCasa("TELEFONOx"); 
    value.setCelular("CELULARx"); 
    value.setMail("MAILx"); 
    value.setCumpleanos(date); 
      
       getClienteContactoSessionService().addClienteContacto(value);

       Collection c = getClienteContactoSessionService().findClienteContactoByCumpleanos(date); 
       ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCumpleanos().toString(),date.toString()); 

   }



    
    @Test (timeout=2000)
    public void findClienteContactoByQuery() throws Exception {
 	
    	ClienteContactoIf value = new ClienteContactoData();
 
       
    	value.setTipocontactoId(new Long(1)); 
        value.setClienteoficinaId(new Long(1)); 
        value.setNombre("NOMBREy"); 
        value.setDireccion("DIRECCIONy"); 
        value.setTelefonoOfic("TELEFONOy"); 
        value.setTelefonoCasa("TELEFONOy"); 
        value.setCelular("CELULARy"); 
        value.setMail("MAILy"); 
        value.setCumpleanos(now);  
      
      getClienteContactoSessionService().addClienteContacto(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("tipocontactoId",new Long(1)); 
       parametros.put("clienteoficinaId",new Long(1)); 
       parametros.put("nombre","NOMBREy"); 
       parametros.put("direccion","DIRECCIONy"); 
       parametros.put("telefonoOfic","TELEFONOy"); 
       parametros.put("telefonoCasa","TELEFONOy"); 
       parametros.put("celular","CELULARy"); 
       parametros.put("mail","MAILy"); 
       parametros.put("cumpleanos",now); 

     Collection c = getClienteContactoSessionService().findClienteContactoByQuery(parametros); 
     ClienteContactoIf resultado = (ClienteContactoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getTipocontactoId(),new Long(1)); 
      Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
      Assert.assertEquals(resultado.getDireccion(),"DIRECCIONy"); 
      Assert.assertEquals(resultado.getTelefonoOfic(),"TELEFONOy"); 
      Assert.assertEquals(resultado.getTelefonoCasa(),"TELEFONOy"); 
      Assert.assertEquals(resultado.getCelular(),"CELULARy"); 
      Assert.assertEquals(resultado.getMail(),"MAILy"); 
      Assert.assertEquals(resultado.getCumpleanos().toString(),now.toString()); 


    }

public static ClienteContactoSessionService getClienteContactoSessionService() {
		try {
			return (ClienteContactoSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTECONTACTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteContactoSessionTest.class);

 }


 


}
