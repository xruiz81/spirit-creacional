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
import com.spirit.contabilidad.entity.SubtipoAsientoData;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.session.SubTipoAsientoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class SubtipoAsientoSessionTest {


	
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(4)
   //nombre=>VARCHAR2(40)
   //tipoId=>NUMBER(10)
   //orden=>NUMBER(2)
   //status=>VARCHAR2(1)
   //tipo=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/subtipo_asiento.xml");
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
   public void addSubtipoAsiento() throws Exception {
     SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
       value.setCodigo("COD1"); 
       value.setNombre("NOMBRE1"); 
       value.setTipoId(new Long(1)); 
       value.setOrden(1L); 
       value.setStatus("S"); 
       value.setTipo(new Long(1)); 
      
     SubtipoAsientoIf resultado = getSubtipoAsientoSessionService().addSubtipoAsiento(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getTipoId(),new Long(1)); 
       Assert.assertEquals(resultado.getOrden(),1); 
       Assert.assertEquals(resultado.getStatus(),"S"); 
       Assert.assertEquals(resultado.getTipo(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveSubtipoAsiento() throws Exception {
      
    SubtipoAsientoIf value = getSubtipoAsientoSessionService().getSubtipoAsiento(new Long(1));
 
          
    value.setCodigo("COD2"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(2L); 
    value.setStatus("A"); 
    value.setTipo(new Long(1));
      
     getSubtipoAsientoSessionService().saveSubtipoAsiento(value);

     SubtipoAsientoIf resultado = getSubtipoAsientoSessionService().getSubtipoAsiento(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getTipoId(),new Long(1)); 
       Assert.assertEquals(resultado.getOrden(),2); 
       Assert.assertEquals(resultado.getStatus(),"A"); 
       Assert.assertEquals(resultado.getTipo(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteSubtipoAsiento() throws Exception {
      getSubtipoAsientoSessionService().deleteSubtipoAsiento(new Long(3));
      SubtipoAsientoIf resultado = getSubtipoAsientoSessionService().getSubtipoAsiento(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findSubtipoAsientoById() throws Exception {
       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoById(new Long(1)); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findSubtipoAsientoByCodigo() throws Exception {

 	SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
 	value.setCodigo("COD3"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(2L); 
    value.setStatus("A"); 
    value.setTipo(new Long(1));
      
       getSubtipoAsientoSessionService().addSubtipoAsiento(value);

       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByCodigo("COD3"); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD3"); 

   }

   @Test (timeout=2000)
   public void findSubtipoAsientoByNombre() throws Exception {

 	SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
 	value.setCodigo("COD2"); 
    value.setNombre("NOMBRE3"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(2L); 
    value.setStatus("A"); 
    value.setTipo(new Long(1));
      
       getSubtipoAsientoSessionService().addSubtipoAsiento(value);

       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByNombre("NOMBRE3"); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE3"); 

   }

   @Test (timeout=2000)
   public void findSubtipoAsientoByTipoId() throws Exception {

 	SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
 	value.setCodigo("COD2"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(2L); 
    value.setStatus("A"); 
    value.setTipo(new Long(1));
      
       getSubtipoAsientoSessionService().addSubtipoAsiento(value);

       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByTipoId(new Long(1)); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findSubtipoAsientoByOrden() throws Exception {

 	SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
 	value.setCodigo("COD2"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(5L); 
    value.setStatus("A"); 
    value.setTipo(new Long(1)); 
      
       getSubtipoAsientoSessionService().addSubtipoAsiento(value);

       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByOrden(5L); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getOrden(),5); 

   }

   @Test (timeout=2000)
   public void findSubtipoAsientoByStatus() throws Exception {

 	SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
 	value.setCodigo("COD2"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(2L); 
    value.setStatus("x"); 
    value.setTipo(new Long(1)); 
      
       getSubtipoAsientoSessionService().addSubtipoAsiento(value);

       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByStatus("x"); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getStatus(),"x"); 

   }

   @Test (timeout=2000)
   public void findSubtipoAsientoByTipo() throws Exception {

 	SubtipoAsientoIf value = new SubtipoAsientoData();
 
      
 	value.setCodigo("COD2"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoId(new Long(1)); 
    value.setOrden(2L); 
    value.setStatus("A"); 
    value.setTipo(new Long(1)); 
      
       getSubtipoAsientoSessionService().addSubtipoAsiento(value);

       Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByTipo(new Long(1)); 
       SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipo(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findSubtipoAsientoByQuery() throws Exception {
 	
    	SubtipoAsientoIf value = new SubtipoAsientoData();
 
       
    	value.setCodigo("CODx"); 
    	value.setNombre("NOMBREx"); 
    	value.setTipoId(new Long(1)); 
    	value.setOrden(7L); 
    	value.setStatus("x"); 
    	value.setTipo(new Long(1));
      
      getSubtipoAsientoSessionService().addSubtipoAsiento(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CODx"); 
       parametros.put("nombre","NOMBREx"); 
       parametros.put("tipoId",new Long(1)); 
       parametros.put("orden",7); 
       parametros.put("status","x"); 
       parametros.put("tipo",new Long(1)); 

     Collection c = getSubtipoAsientoSessionService().findSubtipoAsientoByQuery(parametros); 
     SubtipoAsientoIf resultado = (SubtipoAsientoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"CODx"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 
      Assert.assertEquals(resultado.getTipoId(),new Long(1)); 
      Assert.assertEquals(resultado.getOrden(),7); 
      Assert.assertEquals(resultado.getStatus(),"x"); 
      Assert.assertEquals(resultado.getTipo(),new Long(1)); 


    }

public static SubTipoAsientoSessionService getSubtipoAsientoSessionService() {
		try {
			return (SubTipoAsientoSessionService) ServiceLocator
					.getService(ServiceLocator.SUBTIPOASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SubtipoAsientoSessionTest.class);

 }


 


}
