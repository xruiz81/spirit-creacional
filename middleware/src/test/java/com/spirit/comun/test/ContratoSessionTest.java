package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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

import com.spirit.nomina.entity.ContratoData;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.session.ContratoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class ContratoSessionTest {

	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(18)
   //fechaElaboracion=>DATE
   //tipocontratoId=>NUMBER(10)
   //empleadoId=>NUMBER(10)
   //fechaInicio=>DATE
   //fechaFin=>DATE
   //estado=>VARCHAR2(1)
   //observacion=>VARCHAR2(100)
   //url=>VARCHAR2(50)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/contrato.xml");
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
   public void addContrato() throws Exception {
     ContratoIf value = new ContratoData();
       
       value.setCodigo("COD"); 
       value.setFechaElaboracion(now); 
       value.setTipocontratoId(new Long(1)); 
       value.setEmpleadoId(new Long(1)); 
       value.setFechaInicio(now); 
       value.setFechaFin(now); 
       value.setEstado("A"); 
       value.setObservacion("OBS"); 
       value.setUrl("URL1"); 
      
     ContratoIf resultado = getContratoSessionService().addContrato(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getFechaElaboracion().toString(),now.toString()); 
       Assert.assertEquals(resultado.getTipocontratoId(),new Long(1)); 
       Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getObservacion(),"OBS"); 
       Assert.assertEquals(resultado.getUrl(),"URL1"); 
		
   }

  @Test (timeout=2000)
  public void saveContrato() throws Exception {
      
    ContratoIf value = getContratoSessionService().getContrato(new Long(1));
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
     getContratoSessionService().saveContrato(value);

     ContratoIf resultado = getContratoSessionService().getContrato(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getFechaElaboracion().toString(),now.toString()); 
       Assert.assertEquals(resultado.getTipocontratoId(),new Long(1)); 
       Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getObservacion(),"OBS"); 
       Assert.assertEquals(resultado.getUrl(),"URL1"); 
		
   }


 @Test (timeout=2000)
 public void deleteContrato() throws Exception {
      getContratoSessionService().deleteContrato(new Long(2));
      ContratoIf resultado = getContratoSessionService().getContrato(new Long(2));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findContratoById() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoById(new Long(1)); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findContratoByCodigo() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByCodigo("COD"); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findContratoByFechaElaboracion() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByFechaElaboracion(now); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaElaboracion().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findContratoByTipocontratoId() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByTipocontratoId(new Long(1)); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipocontratoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findContratoByEmpleadoId() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByEmpleadoId(new Long(1)); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findContratoByFechaInicio() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByFechaInicio(now); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findContratoByFechaFin() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByFechaFin(now); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findContratoByEstado() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByEstado("A"); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }

   @Test (timeout=2000)
   public void findContratoByObservacion() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByObservacion("OBS"); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getObservacion(),"OBS"); 

   }

   @Test (timeout=2000)
   public void findContratoByUrl() throws Exception {

 	ContratoIf value = new ContratoData();
 
    value.setCodigo("COD"); 
    value.setFechaElaboracion(now); 
    value.setTipocontratoId(new Long(1)); 
    value.setEmpleadoId(new Long(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setObservacion("OBS"); 
    value.setUrl("URL1"); 
      
       getContratoSessionService().addContrato(value);

       Collection c = getContratoSessionService().findContratoByUrl("URL1"); 
       ContratoIf resultado = (ContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getUrl(),"URL1"); 

   }



    
    @Test (timeout=2000)
    public void findContratoByQuery() throws Exception {
 	
ContratoIf value = new ContratoData();
 
value.setCodigo("COD"); 
value.setFechaElaboracion(now); 
value.setTipocontratoId(new Long(1)); 
value.setEmpleadoId(new Long(1)); 
value.setFechaInicio(now); 
value.setFechaFin(now); 
value.setEstado("A"); 
value.setObservacion("OBS"); 
value.setUrl("URL1"); 
      
      getContratoSessionService().addContrato(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COD"); 
       parametros.put("fechaElaboracion",now); 
       parametros.put("tipocontratoId",new Long(1)); 
       parametros.put("empleadoId",new Long(1)); 
       parametros.put("fechaInicio",now); 
       parametros.put("fechaFin",now); 
       parametros.put("estado","A"); 
       parametros.put("observacion","OBS"); 
       parametros.put("url","URL1"); 

     Collection c = getContratoSessionService().findContratoByQuery(parametros); 
     ContratoIf resultado = (ContratoIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getFechaElaboracion().toString(),now.toString()); 
     Assert.assertEquals(resultado.getTipocontratoId(),new Long(1)); 
     Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
     Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
     Assert.assertEquals(resultado.getEstado(),"A"); 
     Assert.assertEquals(resultado.getObservacion(),"OBS"); 
     Assert.assertEquals(resultado.getUrl(),"URL1"); 
    }

public static ContratoSessionService getContratoSessionService() {
		try {
			return (ContratoSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ContratoSessionTest.class);

 }

}
