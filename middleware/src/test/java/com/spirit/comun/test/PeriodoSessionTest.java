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

import com.spirit.client.SpiritAlert;
import com.spirit.contabilidad.entity.PeriodoData;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.session.PeriodoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class PeriodoSessionTest {

	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(8)
   //empresaId=>NUMBER(10)
   //fechaini=>DATE
   //fechafin=>DATE
   //status=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/periodo.xml");
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
   public void addPeriodo() throws Exception {
     PeriodoIf value = new PeriodoData();
 
      
       value.setCodigo("CODIGO1"); 
       value.setEmpresaId(new Long(1)); 
       value.setFechaini(now); 
       value.setFechafin(now); 
       value.setStatus("S"); 
      
     PeriodoIf resultado = getPeriodoSessionService().addPeriodo(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaini(),now); 
       Assert.assertEquals(resultado.getFechafin(),now); 
       Assert.assertEquals(resultado.getStatus(),"S"); 
		
   }

  @Test (timeout=2000)
  public void savePeriodo() throws Exception {
      
    PeriodoIf value = getPeriodoSessionService().getPeriodo(new Long(1));
 
          
    value.setCodigo("CODIGO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setFechaini(now); 
    value.setFechafin(now); 
    value.setStatus("X"); 
      
     getPeriodoSessionService().savePeriodo(value);

     PeriodoIf resultado = getPeriodoSessionService().getPeriodo(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaini().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechafin().toString(),now.toString()); 
       Assert.assertEquals(resultado.getStatus(),"X"); 
		
   }


 @Test (timeout=2000)
 public void deletePeriodo() throws Exception {
      getPeriodoSessionService().deletePeriodo(new Long(3));
      PeriodoIf resultado = getPeriodoSessionService().getPeriodo(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPeriodoById() throws Exception {
       Collection c = getPeriodoSessionService().findPeriodoById(new Long(1)); 
       PeriodoIf resultado = (PeriodoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findPeriodoByCodigo() throws Exception {

 	PeriodoIf value = new PeriodoData();
 
      
 	value.setCodigo("CODIGOx"); 
    value.setEmpresaId(new Long(1)); 
    value.setFechaini(now); 
    value.setFechafin(now); 
    value.setStatus("X"); 
      
       getPeriodoSessionService().addPeriodo(value);

       Collection c = getPeriodoSessionService().findPeriodoByCodigo("CODIGOx"); 
       PeriodoIf resultado = (PeriodoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 

   }

   @Test (timeout=2000)
   public void findPeriodoByEmpresaId() throws Exception {

 	PeriodoIf value = new PeriodoData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setFechaini(now); 
    value.setFechafin(now); 
    value.setStatus("X");  
      
       getPeriodoSessionService().addPeriodo(value);

       Collection c = getPeriodoSessionService().findPeriodoByEmpresaId(new Long(1)); 
       PeriodoIf resultado = (PeriodoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPeriodoByFechaini() throws Exception {

 	PeriodoIf value = new PeriodoData();
 
 	Date date = new Date(System.currentTimeMillis());
 	value.setCodigo("CODIGO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setFechaini(date); 
    value.setFechafin(date); 
    value.setStatus("X");  
      
       getPeriodoSessionService().addPeriodo(value);

       Collection c = getPeriodoSessionService().findPeriodoByFechaini(date); 
       PeriodoIf resultado = (PeriodoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaini().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findPeriodoByFechafin() throws Exception {

 	PeriodoIf value = new PeriodoData();
 
      
 	Date date = new Date(System.currentTimeMillis());
 	value.setCodigo("CODIGO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setFechaini(date); 
    value.setFechafin(date); 
    value.setStatus("X");  
      
       getPeriodoSessionService().addPeriodo(value);

       Collection c = getPeriodoSessionService().findPeriodoByFechafin(date); 
       PeriodoIf resultado = (PeriodoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechafin().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findPeriodoByStatus() throws Exception {

 	PeriodoIf value = new PeriodoData();
 
      
 	value.setCodigo("CODIGO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setFechaini(now); 
    value.setFechafin(now); 
    value.setStatus("W");   
      
       getPeriodoSessionService().addPeriodo(value);

       Collection c = getPeriodoSessionService().findPeriodoByStatus("W"); 
       PeriodoIf resultado = (PeriodoIf)c.iterator().next();
       Assert.assertEquals(resultado.getStatus(),"W"); 

   }



    
    @Test (timeout=2000)
    public void findPeriodoByQuery() throws Exception {
 	
    	PeriodoIf value = new PeriodoData();
 
       
    	value.setCodigo("CODIGOy"); 
        value.setEmpresaId(new Long(1)); 
        value.setFechaini(now); 
        value.setFechafin(now); 
        value.setStatus("y");   
      
      getPeriodoSessionService().addPeriodo(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CODIGOy"); 
       parametros.put("empresaId",new Long(1)); 
       parametros.put("fechaini",now); 
       parametros.put("fechafin",now); 
       parametros.put("status","y"); 

     Collection c = getPeriodoSessionService().findPeriodoByQuery(parametros); 
     PeriodoIf resultado = (PeriodoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
      Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
      Assert.assertEquals(resultado.getFechaini().toString(),now.toString()); 
      Assert.assertEquals(resultado.getFechafin().toString(),now.toString()); 
      Assert.assertEquals(resultado.getStatus(),"y"); 


    }

public static PeriodoSessionService getPeriodoSessionService() {
		try {
			return (PeriodoSessionService) ServiceLocator
					.getService(ServiceLocator.PERIODOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PeriodoSessionTest.class);

 }


 


}
