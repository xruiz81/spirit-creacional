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

import com.spirit.general.entity.ModuloData;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.session.ModuloSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class ModuloSessionTest {


   //id=>NUMBER(10)
   //codigo=>VARCHAR2(4)
   //nombre=>VARCHAR2(40)
   //status=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/modulo.xml");
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
   public void addModulo() throws Exception {
     ModuloIf value = new ModuloData();
 
       value.setCodigo("COD"); 
       value.setNombre("NOMBRE1"); 
       value.setStatus("A"); 
      
     ModuloIf resultado = getModuloSessionService().addModulo(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getStatus(),"A"); 
		
   }

  @Test (timeout=2000)
  public void saveModulo() throws Exception {
      
    ModuloIf value = getModuloSessionService().getModulo(new Long(1));
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setStatus("A"); 
      
     getModuloSessionService().saveModulo(value);

     ModuloIf resultado = getModuloSessionService().getModulo(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getStatus(),"A"); 
		
   }


 @Test (timeout=2000)
 public void deleteModulo() throws Exception {
      getModuloSessionService().deleteModulo(new Long(3));
      ModuloIf resultado = getModuloSessionService().getModulo(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findModuloById() throws Exception {

 	ModuloIf value = new ModuloData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setStatus("A"); 
      
       getModuloSessionService().addModulo(value);

       Collection c = getModuloSessionService().findModuloById(new Long(1)); 
       ModuloIf resultado = (ModuloIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findModuloByCodigo() throws Exception {

 	ModuloIf value = new ModuloData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setStatus("A"); 
      
       getModuloSessionService().addModulo(value);

       Collection c = getModuloSessionService().findModuloByCodigo("COD"); 
       ModuloIf resultado = (ModuloIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findModuloByNombre() throws Exception {

 	ModuloIf value = new ModuloData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setStatus("A"); 
      
       getModuloSessionService().addModulo(value);

       Collection c = getModuloSessionService().findModuloByNombre("NOMBRE1"); 
       ModuloIf resultado = (ModuloIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findModuloByStatus() throws Exception {

 	ModuloIf value = new ModuloData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setStatus("A"); 
      
       getModuloSessionService().addModulo(value);

       Collection c = getModuloSessionService().findModuloByStatus("A"); 
       ModuloIf resultado = (ModuloIf)c.iterator().next();
       Assert.assertEquals(resultado.getStatus(),"A"); 

   }



    
    @Test (timeout=2000)
    public void findModuloByQuery() throws Exception {
 	
ModuloIf value = new ModuloData();
 
value.setCodigo("COD"); 
value.setNombre("NOMBRE1"); 
value.setStatus("A"); 
      
      getModuloSessionService().addModulo(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COD"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("status","A"); 

     Collection c = getModuloSessionService().findModuloByQuery(parametros); 
     ModuloIf resultado = (ModuloIf)c.iterator().next();
      
     
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getStatus(),"A"); 

    }

public static ModuloSessionService getModuloSessionService() {
		try {
			return (ModuloSessionService) ServiceLocator
					.getService(ServiceLocator.MODULOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ModuloSessionTest.class);

 }

}
