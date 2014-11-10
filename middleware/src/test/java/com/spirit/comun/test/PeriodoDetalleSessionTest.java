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
import com.spirit.contabilidad.entity.PeriodoDetalleData;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.session.PeriodoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class PeriodoDetalleSessionTest {


   //id=>NUMBER(10)
   //status=>VARCHAR2(1)
   //periodoId=>NUMBER(10)
   //mes=>VARCHAR2(10)
   //anio=>VARCHAR2(4)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/periodo_detalle.xml");
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
   public void addPeriodoDetalle() throws Exception {
     PeriodoDetalleIf value = new PeriodoDetalleData();
 
      
       value.setStatus("S"); 
       value.setPeriodoId(new Long(1)); 
       value.setMes("ENERO"); 
       value.setAnio("2007"); 
      
     PeriodoDetalleIf resultado = getPeriodoDetalleSessionService().addPeriodoDetalle(value);
           
       Assert.assertEquals(resultado.getStatus(),"S"); 
       Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
       Assert.assertEquals(resultado.getMes(),"ENERO"); 
       Assert.assertEquals(resultado.getAnio(),"2007"); 
		
   }

  @Test (timeout=2000)
  public void savePeriodoDetalle() throws Exception {
      
    PeriodoDetalleIf value = getPeriodoDetalleSessionService().getPeriodoDetalle(new Long(1));
 
          
    value.setStatus("A"); 
    value.setPeriodoId(new Long(1)); 
    value.setMes("FEBRERO"); 
    value.setAnio("2008");
      
     getPeriodoDetalleSessionService().savePeriodoDetalle(value);

     PeriodoDetalleIf resultado = getPeriodoDetalleSessionService().getPeriodoDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getStatus(),"A"); 
       Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
       Assert.assertEquals(resultado.getMes(),"FEBRERO"); 
       Assert.assertEquals(resultado.getAnio(),"2008"); 
		
   }


 @Test (timeout=2000)
 public void deletePeriodoDetalle() throws Exception {
      getPeriodoDetalleSessionService().deletePeriodoDetalle(new Long(3));
      PeriodoDetalleIf resultado = getPeriodoDetalleSessionService().getPeriodoDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPeriodoDetalleById() throws Exception {
       Collection c = getPeriodoDetalleSessionService().findPeriodoDetalleById(new Long(1)); 
       PeriodoDetalleIf resultado = (PeriodoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findPeriodoDetalleByStatus() throws Exception {

 	PeriodoDetalleIf value = new PeriodoDetalleData();
 
      
 	value.setStatus("R"); 
    value.setPeriodoId(new Long(1)); 
    value.setMes("FEBRERO"); 
    value.setAnio("2008"); 
      
       getPeriodoDetalleSessionService().addPeriodoDetalle(value);

       Collection c = getPeriodoDetalleSessionService().findPeriodoDetalleByStatus("R"); 
       PeriodoDetalleIf resultado = (PeriodoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getStatus(),"R"); 

   }

   @Test (timeout=2000)
   public void findPeriodoDetalleByPeriodoId() throws Exception {

 	PeriodoDetalleIf value = new PeriodoDetalleData();
 
      
 	value.setStatus("A"); 
    value.setPeriodoId(new Long(1)); 
    value.setMes("FEBRERO"); 
    value.setAnio("2008");
      
       getPeriodoDetalleSessionService().addPeriodoDetalle(value);

       Collection c = getPeriodoDetalleSessionService().findPeriodoDetalleByPeriodoId(new Long(1)); 
       PeriodoDetalleIf resultado = (PeriodoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPeriodoDetalleByMes() throws Exception {

 	PeriodoDetalleIf value = new PeriodoDetalleData();
 
      
 	value.setStatus("A"); 
    value.setPeriodoId(new Long(1)); 
    value.setMes("ABRIL"); 
    value.setAnio("2008"); 
      
       getPeriodoDetalleSessionService().addPeriodoDetalle(value);

       Collection c = getPeriodoDetalleSessionService().findPeriodoDetalleByMes("ABRIL"); 
       PeriodoDetalleIf resultado = (PeriodoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getMes(),"ABRIL"); 

   }

   @Test (timeout=2000)
   public void findPeriodoDetalleByAnio() throws Exception {

 	PeriodoDetalleIf value = new PeriodoDetalleData();
 
      
 	value.setStatus("A"); 
    value.setPeriodoId(new Long(1)); 
    value.setMes("FEBRERO"); 
    value.setAnio("2000"); 
      
       getPeriodoDetalleSessionService().addPeriodoDetalle(value);

       Collection c = getPeriodoDetalleSessionService().findPeriodoDetalleByAnio("2000"); 
       PeriodoDetalleIf resultado = (PeriodoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getAnio(),"2000"); 
   }



    
    @Test (timeout=2000)
    public void findPeriodoDetalleByQuery() throws Exception {
 	
    	PeriodoDetalleIf value = new PeriodoDetalleData();
 
       
    	value.setStatus("X"); 
        value.setPeriodoId(new Long(1)); 
        value.setMes("MARZO"); 
        value.setAnio("2005"); 
      
      getPeriodoDetalleSessionService().addPeriodoDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("status","X"); 
       parametros.put("periodoId",new Long(1)); 
       parametros.put("mes","MARZO"); 
       parametros.put("anio","2005"); 

     Collection c = getPeriodoDetalleSessionService().findPeriodoDetalleByQuery(parametros); 
     PeriodoDetalleIf resultado = (PeriodoDetalleIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getStatus(),"X"); 
      Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
      Assert.assertEquals(resultado.getMes(),"MARZO"); 
      Assert.assertEquals(resultado.getAnio(),"2005"); 


    }

public static PeriodoDetalleSessionService getPeriodoDetalleSessionService() {
		try {
			return (PeriodoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.PERIODODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PeriodoDetalleSessionTest.class);

 }


 


}
