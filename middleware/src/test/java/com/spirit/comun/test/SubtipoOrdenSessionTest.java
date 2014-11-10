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

import com.spirit.medios.entity.SubtipoOrdenData;
import com.spirit.medios.entity.SubtipoOrdenIf;
import com.spirit.medios.session.SubtipoOrdenSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class SubtipoOrdenSessionTest {

	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(2)
   //nombre=>VARCHAR2(20)
   //tipoordenId=>NUMBER(10)
   //tipoproveedorId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/subtipo_orden.xml");
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
   public void addSubtipoOrden() throws Exception {
     SubtipoOrdenIf value = new SubtipoOrdenData();
 
      
       value.setCodigo("C1"); 
       value.setNombre("NOMBRE1"); 
       value.setTipoordenId(new Long(1)); 
       value.setTipoproveedorId(new Long(1)); 
      
     SubtipoOrdenIf resultado = getSubtipoOrdenSessionService().addSubtipoOrden(value);
           
       Assert.assertEquals(resultado.getCodigo(),"C1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getTipoordenId(),new Long(1)); 
       Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveSubtipoOrden() throws Exception {
      
    SubtipoOrdenIf value = getSubtipoOrdenSessionService().getSubtipoOrden(new Long(1));
 
          
    value.setCodigo("C2"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoordenId(new Long(1)); 
    value.setTipoproveedorId(new Long(1));
      
     getSubtipoOrdenSessionService().saveSubtipoOrden(value);

     SubtipoOrdenIf resultado = getSubtipoOrdenSessionService().getSubtipoOrden(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"C2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getTipoordenId(),new Long(1)); 
       Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteSubtipoOrden() throws Exception {
      getSubtipoOrdenSessionService().deleteSubtipoOrden(new Long(3));
      SubtipoOrdenIf resultado = getSubtipoOrdenSessionService().getSubtipoOrden(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findSubtipoOrdenById() throws Exception {
       Collection c = getSubtipoOrdenSessionService().findSubtipoOrdenById(new Long(1)); 
       SubtipoOrdenIf resultado = (SubtipoOrdenIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findSubtipoOrdenByCodigo() throws Exception {

 	SubtipoOrdenIf value = new SubtipoOrdenData();
 
      
 	value.setCodigo("Cx"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoordenId(new Long(1)); 
    value.setTipoproveedorId(new Long(1)); 
      
       getSubtipoOrdenSessionService().addSubtipoOrden(value);

       Collection c = getSubtipoOrdenSessionService().findSubtipoOrdenByCodigo("Cx"); 
       SubtipoOrdenIf resultado = (SubtipoOrdenIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"Cx"); 

   }

   @Test (timeout=2000)
   public void findSubtipoOrdenByNombre() throws Exception {

 	SubtipoOrdenIf value = new SubtipoOrdenData();
 
      
 	value.setCodigo("Cx"); 
    value.setNombre("NOMBREx"); 
    value.setTipoordenId(new Long(1)); 
    value.setTipoproveedorId(new Long(1)); 
      
       getSubtipoOrdenSessionService().addSubtipoOrden(value);

       Collection c = getSubtipoOrdenSessionService().findSubtipoOrdenByNombre("NOMBREx"); 
       SubtipoOrdenIf resultado = (SubtipoOrdenIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }

   @Test (timeout=2000)
   public void findSubtipoOrdenByTipoordenId() throws Exception {

 	SubtipoOrdenIf value = new SubtipoOrdenData();
 
      
 	value.setCodigo("Cx"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoordenId(new Long(1)); 
    value.setTipoproveedorId(new Long(1)); 
      
       getSubtipoOrdenSessionService().addSubtipoOrden(value);

       Collection c = getSubtipoOrdenSessionService().findSubtipoOrdenByTipoordenId(new Long(1)); 
       SubtipoOrdenIf resultado = (SubtipoOrdenIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoordenId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findSubtipoOrdenByTipoproveedorId() throws Exception {

 	SubtipoOrdenIf value = new SubtipoOrdenData();
 
      
 	value.setCodigo("Cx"); 
    value.setNombre("NOMBRE2"); 
    value.setTipoordenId(new Long(1)); 
    value.setTipoproveedorId(new Long(1)); 
      
       getSubtipoOrdenSessionService().addSubtipoOrden(value);

       Collection c = getSubtipoOrdenSessionService().findSubtipoOrdenByTipoproveedorId(new Long(1)); 
       SubtipoOrdenIf resultado = (SubtipoOrdenIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findSubtipoOrdenByQuery() throws Exception {
 	
    	SubtipoOrdenIf value = new SubtipoOrdenData();
 
       
    	value.setCodigo("Cy"); 
        value.setNombre("NOMBREy"); 
        value.setTipoordenId(new Long(1)); 
        value.setTipoproveedorId(new Long(1));  
      
      getSubtipoOrdenSessionService().addSubtipoOrden(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","Cy"); 
       parametros.put("nombre","NOMBREy"); 
       parametros.put("tipoordenId",new Long(1)); 
       parametros.put("tipoproveedorId",new Long(1)); 

     Collection c = getSubtipoOrdenSessionService().findSubtipoOrdenByQuery(parametros); 
     SubtipoOrdenIf resultado = (SubtipoOrdenIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"Cy"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
      Assert.assertEquals(resultado.getTipoordenId(),new Long(1)); 
      Assert.assertEquals(resultado.getTipoproveedorId(),new Long(1)); 


    }

public static SubtipoOrdenSessionService getSubtipoOrdenSessionService() {
		try {
			return (SubtipoOrdenSessionService) ServiceLocator
					.getService(ServiceLocator.SUBTIPOORDENSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SubtipoOrdenSessionTest.class);

 }


 


}
