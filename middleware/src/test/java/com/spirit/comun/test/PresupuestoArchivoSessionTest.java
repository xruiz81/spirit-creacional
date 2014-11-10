package com.spirit.comun.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import junit.framework.JUnit4TestAdapter;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.spirit.medios.entity.*;



public class PresupuestoArchivoSessionTest {
/*

   //id=>NUMBER(10)
   //presupuestoId=>NUMBER(10)
   //tipoArchivoId=>NUMBER(10)
   //url=>VARCHAR2(250)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/PresupuestoArchivo.xml");
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
   public void addPresupuestoArchivo() throws Exception {
     PresupuestoArchivoIf value = new PresupuestoArchivoData();
 
      
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
     PresupuestoArchivoIf resultado = getPresupuestoArchivoSessionService().addPresupuestoArchivo(value);
           
       Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 
       Assert.assertEquals(resultado.getTipoArchivoId(),"TIPO_ARCHIVO_ID1"); 
       Assert.assertEquals(resultado.getUrl(),"URL1"); 
		
   }

  @Test (timeout=2000)
  public void savePresupuestoArchivo() throws Exception {
      
    PresupuestoArchivoIf value = getPresupuestoArchivoSessionService().getPresupuestoArchivo(new Long(1));
 
          
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
     getPresupuestoArchivoSessionService().savePresupuestoArchivo(value);

     PresupuestoArchivoIf resultado = getPresupuestoArchivoSessionService().getPresupuestoArchivo(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 
       Assert.assertEquals(resultado.getTipoArchivoId(),"TIPO_ARCHIVO_ID1"); 
       Assert.assertEquals(resultado.getUrl(),"URL1"); 
		
   }


 @Test (timeout=2000)
 public void deletePresupuestoArchivo() throws Exception {
      getPresupuestoArchivoSessionService().deletePresupuestoArchivo(new Long(3));
      PresupuestoArchivoIf resultado = getPresupuestoArchivoSessionService().getPresupuestoArchivo(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPresupuestoArchivoById() throws Exception {

 	PresupuestoArchivoIf value = new PresupuestoArchivoData();
 
      
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
       getPresupuestoArchivoSessionService().addPresupuestoArchivo(value);

       Collection c = getPresupuestoArchivoSessionService().findPresupuestoArchivoById("ID1"); 
       PresupuestoArchivoIf resultado = (PresupuestoArchivoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findPresupuestoArchivoByPresupuestoId() throws Exception {

 	PresupuestoArchivoIf value = new PresupuestoArchivoData();
 
      
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
       getPresupuestoArchivoSessionService().addPresupuestoArchivo(value);

       Collection c = getPresupuestoArchivoSessionService().findPresupuestoArchivoByPresupuestoId("PRESUPUESTO_ID1"); 
       PresupuestoArchivoIf resultado = (PresupuestoArchivoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 

   }

   @Test (timeout=2000)
   public void findPresupuestoArchivoByTipoArchivoId() throws Exception {

 	PresupuestoArchivoIf value = new PresupuestoArchivoData();
 
      
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
       getPresupuestoArchivoSessionService().addPresupuestoArchivo(value);

       Collection c = getPresupuestoArchivoSessionService().findPresupuestoArchivoByTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       PresupuestoArchivoIf resultado = (PresupuestoArchivoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoArchivoId(),"TIPO_ARCHIVO_ID1"); 

   }

   @Test (timeout=2000)
   public void findPresupuestoArchivoByUrl() throws Exception {

 	PresupuestoArchivoIf value = new PresupuestoArchivoData();
 
      
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
       getPresupuestoArchivoSessionService().addPresupuestoArchivo(value);

       Collection c = getPresupuestoArchivoSessionService().findPresupuestoArchivoByUrl("URL1"); 
       PresupuestoArchivoIf resultado = (PresupuestoArchivoIf)c.iterator().next();
       Assert.assertEquals(resultado.getUrl(),"URL1"); 

   }



    
    @Test (timeout=2000)
    public void findPresupuestoArchivoByQuery() throws Exception {
 	
PresupuestoArchivoIf value = new PresupuestoArchivoData();
 
       
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
       value.setTipoArchivoId("TIPO_ARCHIVO_ID1"); 
       value.setUrl("URL1"); 
      
      getPresupuestoArchivoSessionService().addPresupuestoArchivo(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("presupuestoId","PRESUPUESTO_ID1"); 
       parametros.put("tipoArchivoId","TIPO_ARCHIVO_ID1"); 
       parametros.put("url","URL1"); 

     Collection c = getPresupuestoArchivoSessionService().findPresupuestoArchivoByQuery(parametros); 
     PresupuestoArchivoIf resultado = (PresupuestoArchivoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 
      Assert.assertEquals(resultado.getTipoArchivoId(),"TIPO_ARCHIVO_ID1"); 
      Assert.assertEquals(resultado.getUrl(),"URL1"); 


    }

public static PresupuestoArchivoSessionService getPresupuestoArchivoSessionService() {
		try {
			return (PresupuestoArchivoSessionService) ServiceLocator
					.getService(ServiceLocator.PresupuestoArchivoSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PresupuestoArchivoSessionTest.class);

 }


 

*/
}
