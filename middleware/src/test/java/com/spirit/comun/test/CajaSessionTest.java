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

import com.spirit.general.entity.CajaData;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.session.CajaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class CajaSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(3)
   //nombre=>VARCHAR2(30)
   //oficinaId=>NUMBER(10)
   //turno=>VARCHAR2(1)
   //estado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/caja.xml");
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
   public void addCaja() throws Exception {
     CajaIf value = new CajaData();
 
       value.setCodigo("001"); 
       value.setNombre("CAJA1"); 
       value.setOficinaId(new Long(1)); 
       value.setTurno("1"); 
       value.setEstado("A"); 
      
     CajaIf resultado = getCajaSessionService().addCaja(value);
           
       Assert.assertEquals(resultado.getCodigo(),"001"); 
       Assert.assertEquals(resultado.getNombre(),"CAJA1"); 
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getTurno(),"1"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }

  @Test (timeout=2000)
  public void saveCaja() throws Exception {
      
    CajaIf value = getCajaSessionService().getCaja(new Long(2));
 
    value.setCodigo("001"); 
    value.setNombre("CAJA1"); 
    value.setOficinaId(new Long(1)); 
    value.setTurno("1"); 
    value.setEstado("A");  
      
     getCajaSessionService().saveCaja(value);

     CajaIf resultado = getCajaSessionService().getCaja(new Long(1));

       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"001"); 
       Assert.assertEquals(resultado.getNombre(),"CAJA1"); 
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getTurno(),"1"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }


 @Test (timeout=2000)
 public void deleteCaja() throws Exception {
      getCajaSessionService().deleteCaja(new Long(3));
      CajaIf resultado = getCajaSessionService().getCaja(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findCajaById() throws Exception {

 	  Collection c = getCajaSessionService().findCajaById(new Long(1)); 
      CajaIf resultado = (CajaIf)c.iterator().next();
      Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCajaByCodigo() throws Exception {

 	CajaIf value = new CajaData();
 
    value.setCodigo("001"); 
    value.setNombre("CAJA1"); 
    value.setOficinaId(new Long(1)); 
    value.setTurno("1"); 
    value.setEstado("A"); 
      
       getCajaSessionService().addCaja(value);

       Collection c = getCajaSessionService().findCajaByCodigo("001"); 
       CajaIf resultado = (CajaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"001"); 

   }

   @Test (timeout=2000)
   public void findCajaByNombre() throws Exception {

 	CajaIf value = new CajaData();
 
    value.setCodigo("001"); 
    value.setNombre("CAJA1"); 
    value.setOficinaId(new Long(1)); 
    value.setTurno("1"); 
    value.setEstado("A"); 
      
       getCajaSessionService().addCaja(value);

       Collection c = getCajaSessionService().findCajaByNombre("CAJA1"); 
       CajaIf resultado = (CajaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"CAJA1"); 

   }

   @Test (timeout=2000)
   public void findCajaByOficinaId() throws Exception {

 	CajaIf value = new CajaData();
 
    value.setCodigo("001"); 
    value.setNombre("CAJA1"); 
    value.setOficinaId(new Long(1)); 
    value.setTurno("1"); 
    value.setEstado("A"); 
      
       getCajaSessionService().addCaja(value);

       Collection c = getCajaSessionService().findCajaByOficinaId(new Long(1)); 
       CajaIf resultado = (CajaIf)c.iterator().next();
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCajaByTurno() throws Exception {

 	CajaIf value = new CajaData();
 
    value.setCodigo("001"); 
    value.setNombre("CAJA1"); 
    value.setOficinaId(new Long(1)); 
    value.setTurno("1"); 
    value.setEstado("A"); 
      
       getCajaSessionService().addCaja(value);

       Collection c = getCajaSessionService().findCajaByTurno("1"); 
       CajaIf resultado = (CajaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTurno(),"1"); 

   }

   @Test (timeout=2000)
   public void findCajaByEstado() throws Exception {

 	CajaIf value = new CajaData();
 
    value.setCodigo("001"); 
    value.setNombre("CAJA1"); 
    value.setOficinaId(new Long(1)); 
    value.setTurno("1"); 
    value.setEstado("A"); 
      
       getCajaSessionService().addCaja(value);

       Collection c = getCajaSessionService().findCajaByEstado("A"); 
       CajaIf resultado = (CajaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }



    
    @Test (timeout=2000)
    public void findCajaByQuery() throws Exception {
 	
    	CajaIf value = new CajaData();
 
    	value.setCodigo("00x"); 
    	value.setNombre("CAJAx"); 
    	value.setOficinaId(new Long(1)); 
    	value.setTurno("x"); 
    	value.setEstado("x"); 
      
    	getCajaSessionService().addCaja(value);

    	Map<String, Object> parametros = new HashMap<String, Object>();

       
    	parametros.put("codigo","00x"); 
    	parametros.put("nombre","CAJAx"); 
    	parametros.put("oficinaId",new Long(1)); 
    	parametros.put("turno","x"); 
    	parametros.put("estado","x"); 

    	Collection c = getCajaSessionService().findCajaByQuery(parametros); 
    	CajaIf resultado = (CajaIf)c.iterator().next();
      
    	Assert.assertEquals(resultado.getCodigo(),"00x"); 
    	Assert.assertEquals(resultado.getNombre(),"CAJAx"); 
    	Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
    	Assert.assertEquals(resultado.getTurno(),"x"); 
    	Assert.assertEquals(resultado.getEstado(),"x"); 


    }

public static CajaSessionService getCajaSessionService() {
		try {
			return (CajaSessionService) ServiceLocator
					.getService(ServiceLocator.CAJASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CajaSessionTest.class);

 }

}
