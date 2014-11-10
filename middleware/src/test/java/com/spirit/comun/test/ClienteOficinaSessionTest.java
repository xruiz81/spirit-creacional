package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.spirit.crm.entity.ClienteOficinaData;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.session.ClienteOficinaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class ClienteOficinaSessionTest {

	Timestamp now = new Timestamp(System.currentTimeMillis());
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(10)
   //clienteId=>NUMBER(10)
   //ciudadId=>NUMBER(10)
   //direccion=>VARCHAR2(150)
   //telefono=>VARCHAR2(10)
   //fax=>VARCHAR2(10)
   //email=>VARCHAR2(20)
   //fechaCreacion=>DATE
   //montoCredito=>NUMBER(22)
   //montoGarantia=>NUMBER(22)
   //calificacion=>VARCHAR2(1)
   //observacion=>VARCHAR2(40)
   //estado=>VARCHAR2(1)
   //descripcion=>VARCHAR2(50)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cliente_oficina.xml");
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
   public void addClienteOficina() throws Exception {
     ClienteOficinaIf value = new ClienteOficinaData();
 
      
       value.setCodigo("CODIGO1"); 
       value.setClienteId(new Long(1)); 
       value.setCiudadId(new Long(1)); 
       value.setDireccion("DIRECCION1"); 
       value.setTelefono("TELEFONO1"); 
       value.setFax("FAX1"); 
       value.setEmail("EMAIL1"); 
       value.setFechaCreacion(now); 
       value.setMontoCredito(new BigDecimal(1)); 
       value.setMontoGarantia(new BigDecimal(1)); 
       value.setCalificacion("A"); 
       value.setObservacion("OBSERVACION1"); 
       value.setEstado("E"); 
       value.setDescripcion("DESCRIPCION1"); 
      
     ClienteOficinaIf resultado = getClienteOficinaSessionService().addClienteOficina(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
       Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
       Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
       Assert.assertEquals(resultado.getFax(),"FAX1"); 
       Assert.assertEquals(resultado.getEmail(),"EMAIL1"); 
       Assert.assertEquals(resultado.getFechaCreacion(),now); 
       Assert.assertEquals(resultado.getMontoCredito(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getMontoGarantia(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getCalificacion(),"A"); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
       Assert.assertEquals(resultado.getEstado(),"E"); 
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		
   }

  @Test (timeout=2000)
  public void saveClienteOficina() throws Exception {
      
    ClienteOficinaIf value = getClienteOficinaSessionService().getClienteOficina(new Long(1));
 
          
    value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAIL2"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2");  
      
     getClienteOficinaSessionService().saveClienteOficina(value);

     ClienteOficinaIf resultado = getClienteOficinaSessionService().getClienteOficina(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
       Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
       Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION2"); 
       Assert.assertEquals(resultado.getTelefono(),"TELEFONO2"); 
       Assert.assertEquals(resultado.getFax(),"FAX2"); 
       Assert.assertEquals(resultado.getEmail(),"EMAIL2"); 
       Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
       Assert.assertEquals(resultado.getMontoCredito(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getMontoGarantia(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getCalificacion(),"B"); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION2"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION2"); 
		
   }


 @Test (timeout=2000)
 public void deleteClienteOficina() throws Exception {
      getClienteOficinaSessionService().deleteClienteOficina(new Long(3));
      ClienteOficinaIf resultado = getClienteOficinaSessionService().getClienteOficina(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findClienteOficinaById() throws Exception {
       Collection c = getClienteOficinaSessionService().findClienteOficinaById(new Long(1)); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findClienteOficinaByCodigo() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	 value.setCodigo("CODIGOx"); 
     value.setClienteId(new Long(1)); 
     value.setCiudadId(new Long(1)); 
     value.setDireccion("DIRECCION2"); 
     value.setTelefono("TELEFONO2"); 
     value.setFax("FAX2"); 
     value.setEmail("EMAIL2"); 
     value.setFechaCreacion(now); 
     value.setMontoCredito(new BigDecimal(2)); 
     value.setMontoGarantia(new BigDecimal(2)); 
     value.setCalificacion("B"); 
     value.setObservacion("OBSERVACION2"); 
     value.setEstado("A"); 
     value.setDescripcion("DESCRIPCION2");  
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByCodigo("CODIGOx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByClienteId() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	 value.setCodigo("CODIGO2"); 
     value.setClienteId(new Long(1)); 
     value.setCiudadId(new Long(1)); 
     value.setDireccion("DIRECCION2"); 
     value.setTelefono("TELEFONO2"); 
     value.setFax("FAX2"); 
     value.setEmail("EMAIL2"); 
     value.setFechaCreacion(now); 
     value.setMontoCredito(new BigDecimal(2)); 
     value.setMontoGarantia(new BigDecimal(2)); 
     value.setCalificacion("B"); 
     value.setObservacion("OBSERVACION2"); 
     value.setEstado("A"); 
     value.setDescripcion("DESCRIPCION2");  
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByClienteId(new Long(1));  
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByCiudadId() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAIL2"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2");  
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByCiudadId(new Long(1)); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCiudadId(),new Long(1));

   }

   @Test (timeout=2000)
   public void findClienteOficinaByDireccion() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCIONx"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAIL2"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByDireccion("DIRECCIONx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDireccion(),"DIRECCIONx"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByTelefono() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONOx"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAIL2"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByTelefono("TELEFONOx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTelefono(),"TELEFONOx"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByFax() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAXx"); 
    value.setEmail("EMAIL2"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByFax("FAXx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFax(),"FAXx"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByEmail() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByEmail("EMAILx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmail(),"EMAILx"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByFechaCreacion() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
 	Timestamp date = new Timestamp(System.currentTimeMillis());
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAIL2"); 
    value.setFechaCreacion(date); 
    value.setMontoCredito(new BigDecimal(2)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2");  
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByFechaCreacion(date); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaCreacion().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByMontoCredito() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(3)); 
    value.setMontoGarantia(new BigDecimal(2)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByMontoCredito(new BigDecimal(3)); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getMontoCredito(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByMontoGarantia() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(3)); 
    value.setMontoGarantia(new BigDecimal(3)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByMontoGarantia(new BigDecimal(3));  
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getMontoGarantia(),new BigDecimal(3)); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByCalificacion() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(3)); 
    value.setMontoGarantia(new BigDecimal(3)); 
    value.setCalificacion("x"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByCalificacion("x"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCalificacion(),"x"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByObservacion() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(3)); 
    value.setMontoGarantia(new BigDecimal(3)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACIONx"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCION2"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByObservacion("OBSERVACIONx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONx"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByEstado() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(3)); 
    value.setMontoGarantia(new BigDecimal(3)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("x"); 
    value.setDescripcion("DESCRIPCION2");  
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByEstado("x"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"x"); 

   }

   @Test (timeout=2000)
   public void findClienteOficinaByDescripcion() throws Exception {

 	ClienteOficinaIf value = new ClienteOficinaData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setClienteId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setDireccion("DIRECCION2"); 
    value.setTelefono("TELEFONO2"); 
    value.setFax("FAX2"); 
    value.setEmail("EMAILx"); 
    value.setFechaCreacion(now); 
    value.setMontoCredito(new BigDecimal(3)); 
    value.setMontoGarantia(new BigDecimal(3)); 
    value.setCalificacion("B"); 
    value.setObservacion("OBSERVACION2"); 
    value.setEstado("A"); 
    value.setDescripcion("DESCRIPCIONx"); 
      
       getClienteOficinaSessionService().addClienteOficina(value);

       Collection c = getClienteOficinaSessionService().findClienteOficinaByDescripcion("DESCRIPCIONx"); 
       ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCIONx"); 

   }



    
    @Test (timeout=2000)
    public void findClienteOficinaByQuery() throws Exception {
 	
    	ClienteOficinaIf value = new ClienteOficinaData();
 
       
    	value.setCodigo("CODIGOy"); 
        value.setClienteId(new Long(1)); 
        value.setCiudadId(new Long(1)); 
        value.setDireccion("DIRECCIONy"); 
        value.setTelefono("TELEFONOy"); 
        value.setFax("FAXy"); 
        value.setEmail("EMAILy"); 
        value.setFechaCreacion(now); 
        value.setMontoCredito(new BigDecimal(5)); 
        value.setMontoGarantia(new BigDecimal(5)); 
        value.setCalificacion("y"); 
        value.setObservacion("OBSERVACIONy"); 
        value.setEstado("y"); 
        value.setDescripcion("DESCRIPCIONy"); 
      
      getClienteOficinaSessionService().addClienteOficina(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CODIGOy"); 
       parametros.put("clienteId",new Long(1)); 
       parametros.put("ciudadId",new Long(1));  
       parametros.put("direccion","DIRECCIONy"); 
       parametros.put("telefono","TELEFONOy"); 
       parametros.put("fax","FAXy"); 
       parametros.put("email","EMAILy"); 
       parametros.put("fechaCreacion",now); 
       parametros.put("montoCredito",new BigDecimal(5)); 
       parametros.put("montoGarantia",new BigDecimal(5)); 
       parametros.put("calificacion","y"); 
       parametros.put("observacion","OBSERVACIONy"); 
       parametros.put("estado","y"); 
       parametros.put("descripcion","DESCRIPCIONy"); 

     Collection c = getClienteOficinaSessionService().findClienteOficinaByQuery(parametros); 
     ClienteOficinaIf resultado = (ClienteOficinaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
      Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
      Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 
      Assert.assertEquals(resultado.getDireccion(),"DIRECCIONy"); 
      Assert.assertEquals(resultado.getTelefono(),"TELEFONOy"); 
      Assert.assertEquals(resultado.getFax(),"FAXy"); 
      Assert.assertEquals(resultado.getEmail(),"EMAILy"); 
      Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
      Assert.assertEquals(resultado.getMontoCredito(),new BigDecimal(5)); 
      Assert.assertEquals(resultado.getMontoGarantia(),new BigDecimal(5));
      Assert.assertEquals(resultado.getCalificacion(),"y"); 
      Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONy"); 
      Assert.assertEquals(resultado.getEstado(),"y"); 
      Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCIONy"); 


    }

public static ClienteOficinaSessionService getClienteOficinaSessionService() {
		try {
			return (ClienteOficinaSessionService) ServiceLocator
					.getService(ServiceLocator.CLIENTEOFICINASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ClienteOficinaSessionTest.class);

 }


 


}
