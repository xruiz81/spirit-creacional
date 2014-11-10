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
import com.spirit.contabilidad.entity.PlantillaData;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.session.PlantillaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class PlantillaSessionTest {

	

   //id=>NUMBER(10)
   //eventocontableId=>NUMBER(10)
   //cuentaId=>NUMBER(10)
   //debehaber=>VARCHAR2(1)
   //referencia=>VARCHAR2(30)
   //glosa=>VARCHAR2(100)
   //nemonico=>VARCHAR2(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/plantilla.xml");
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
   public void addPlantilla() throws Exception {
     PlantillaIf value = new PlantillaData();
 
      
       value.setEventocontableId(new Long(1)); 
       value.setCuentaId(new Long(1)); 
       value.setDebehaber("D"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1");  
       value.setNemonico("NEMONICO1"); 
      
     PlantillaIf resultado = getPlantillaSessionService().addPlantilla(value);
           
       Assert.assertEquals(resultado.getEventocontableId(),new Long(1)); 
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
       Assert.assertEquals(resultado.getDebehaber(),"D"); 
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
       Assert.assertEquals(resultado.getGlosa(),"GLOSA1");  
       Assert.assertEquals(resultado.getNemonico(),"NEMONICO1");
		
   }

  @Test (timeout=2000)
  public void savePlantilla() throws Exception {
      
    PlantillaIf value = getPlantillaSessionService().getPlantilla(new Long(1));
 
          
    value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("H"); 
    value.setReferencia("REFERENCIA2"); 
    value.setGlosa("GLOSA2"); 
    value.setNemonico("NEMONICO2");
      
     getPlantillaSessionService().savePlantilla(value);

     PlantillaIf resultado = getPlantillaSessionService().getPlantilla(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getEventocontableId(),new Long(1)); 
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
       Assert.assertEquals(resultado.getDebehaber(),"H"); 
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA2"); 
       Assert.assertEquals(resultado.getGlosa(),"GLOSA2");  
       Assert.assertEquals(resultado.getNemonico(),"NEMONICO2");
		
   }


 @Test (timeout=2000)
 public void deletePlantilla() throws Exception {
      getPlantillaSessionService().deletePlantilla(new Long(3));
      PlantillaIf resultado = getPlantillaSessionService().getPlantilla(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPlantillaById() throws Exception {
       Collection c = getPlantillaSessionService().findPlantillaById(new Long(1)); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findPlantillaByEventocontableId() throws Exception {

 	PlantillaIf value = new PlantillaData();
 
      
 	value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("D"); 
    value.setReferencia("REFERENCIA1"); 
    value.setGlosa("GLOSA1");
    value.setNemonico("NEMONICO1"); 
    
       getPlantillaSessionService().addPlantilla(value);

       Collection c = getPlantillaSessionService().findPlantillaByEventocontableId(new Long(1)); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEventocontableId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPlantillaByCuentaId() throws Exception {

 	PlantillaIf value = new PlantillaData();
 
      
 	value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("D"); 
    value.setReferencia("REFERENCIA1"); 
    value.setGlosa("GLOSA1"); 
    value.setNemonico("NEMONICO1"); 
      
       getPlantillaSessionService().addPlantilla(value);

       Collection c = getPlantillaSessionService().findPlantillaByCuentaId(new Long(1)); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPlantillaByDebehaber() throws Exception {

 	PlantillaIf value = new PlantillaData();
 
      
 	value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("x"); 
    value.setReferencia("REFERENCIA1"); 
    value.setGlosa("GLOSA1"); 
    value.setNemonico("NEMONICO1"); 
      
       getPlantillaSessionService().addPlantilla(value);

       Collection c = getPlantillaSessionService().findPlantillaByDebehaber("x"); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDebehaber(),"x"); 

   }

   @Test (timeout=2000)
   public void findPlantillaByReferencia() throws Exception {

 	PlantillaIf value = new PlantillaData();
 
      
 	value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("D"); 
    value.setReferencia("REFERENCIAx"); 
    value.setGlosa("GLOSA1"); 
    value.setNemonico("NEMONICO1"); 
      
       getPlantillaSessionService().addPlantilla(value);

       Collection c = getPlantillaSessionService().findPlantillaByReferencia("REFERENCIAx"); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIAx"); 

   }

   @Test (timeout=2000)
   public void findPlantillaByGlosa() throws Exception {

 	PlantillaIf value = new PlantillaData();
 
      
 	value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("D"); 
    value.setReferencia("REFERENCIA1"); 
    value.setGlosa("GLOSAx");
    value.setNemonico("NEMONICO1"); 
      
       getPlantillaSessionService().addPlantilla(value);

       Collection c = getPlantillaSessionService().findPlantillaByGlosa("GLOSAx"); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getGlosa(),"GLOSAx"); 

   }

   @Test (timeout=2000)
   public void findPlantillaByNemonico() throws Exception {

 	PlantillaIf value = new PlantillaData();
 
      
 	value.setEventocontableId(new Long(1)); 
    value.setCuentaId(new Long(1)); 
    value.setDebehaber("D"); 
    value.setReferencia("REFERENCIA1"); 
    value.setGlosa("GLOSAx"); 
    value.setNemonico("NEMONICOx"); 
      
       getPlantillaSessionService().addPlantilla(value);

       Collection c = getPlantillaSessionService().findPlantillaByNemonico("NEMONICOx"); 
       PlantillaIf resultado = (PlantillaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNemonico(),"NEMONICOx"); 

   }

    
    @Test (timeout=2000)
    public void findPlantillaByQuery() throws Exception {
 	
    	PlantillaIf value = new PlantillaData();
 
       
    	value.setEventocontableId(new Long(1)); 
    	value.setCuentaId(new Long(1)); 
    	value.setDebehaber("y"); 
    	value.setReferencia("REFERENCIAy"); 
    	value.setGlosa("GLOSAy");  
        value.setNemonico("NEMONICOy");
      
      getPlantillaSessionService().addPlantilla(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("eventocontableId",new Long(1)); 
       parametros.put("cuentaId",new Long(1)); 
       parametros.put("debehaber","y"); 
       parametros.put("referencia","REFERENCIAy"); 
       parametros.put("glosa","GLOSAy");  
       parametros.put("nemonico","NEMONICOy"); 

     Collection c = getPlantillaSessionService().findPlantillaByQuery(parametros); 
     PlantillaIf resultado = (PlantillaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getEventocontableId(),new Long(1)); 
      Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
      Assert.assertEquals(resultado.getDebehaber(),"y"); 
      Assert.assertEquals(resultado.getReferencia(),"REFERENCIAy"); 
      Assert.assertEquals(resultado.getGlosa(),"GLOSAy"); 
      Assert.assertEquals(resultado.getNemonico(),"NEMONICOy"); 


    }

public static PlantillaSessionService getPlantillaSessionService() {
		try {
			return (PlantillaSessionService) ServiceLocator
					.getService(ServiceLocator.PLANTILLASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PlantillaSessionTest.class);

 }


 


}
