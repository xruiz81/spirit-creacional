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



public class PresupuestoProductoSessionTest {


   //id=>NUMBER(10)
   //productoClienteId=>NUMBER(10)
   //presupuestoId=>NUMBER(10)
/*
        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/PresupuestoProducto.xml");
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
   public void addPresupuestoProducto() throws Exception {
     PresupuestoProductoIf value = new PresupuestoProductoData();
 
      
       value.setProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
      
     PresupuestoProductoIf resultado = getPresupuestoProductoSessionService().addPresupuestoProducto(value);
           
       Assert.assertEquals(resultado.getProductoClienteId(),"PRODUCTO_CLIENTE_ID1"); 
       Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 
		
   }

  @Test (timeout=2000)
  public void savePresupuestoProducto() throws Exception {
      
    PresupuestoProductoIf value = getPresupuestoProductoSessionService().getPresupuestoProducto(new Long(1));
 
          
       value.setProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
      
     getPresupuestoProductoSessionService().savePresupuestoProducto(value);

     PresupuestoProductoIf resultado = getPresupuestoProductoSessionService().getPresupuestoProducto(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getProductoClienteId(),"PRODUCTO_CLIENTE_ID1"); 
       Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 
		
   }


 @Test (timeout=2000)
 public void deletePresupuestoProducto() throws Exception {
      getPresupuestoProductoSessionService().deletePresupuestoProducto(new Long(3));
      PresupuestoProductoIf resultado = getPresupuestoProductoSessionService().getPresupuestoProducto(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPresupuestoProductoById() throws Exception {

 	PresupuestoProductoIf value = new PresupuestoProductoData();
 
      
       value.setProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
      
       getPresupuestoProductoSessionService().addPresupuestoProducto(value);

       Collection c = getPresupuestoProductoSessionService().findPresupuestoProductoById("ID1"); 
       PresupuestoProductoIf resultado = (PresupuestoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findPresupuestoProductoByProductoClienteId() throws Exception {

 	PresupuestoProductoIf value = new PresupuestoProductoData();
 
      
       value.setProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
      
       getPresupuestoProductoSessionService().addPresupuestoProducto(value);

       Collection c = getPresupuestoProductoSessionService().findPresupuestoProductoByProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       PresupuestoProductoIf resultado = (PresupuestoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getProductoClienteId(),"PRODUCTO_CLIENTE_ID1"); 

   }

   @Test (timeout=2000)
   public void findPresupuestoProductoByPresupuestoId() throws Exception {

 	PresupuestoProductoIf value = new PresupuestoProductoData();
 
      
       value.setProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
      
       getPresupuestoProductoSessionService().addPresupuestoProducto(value);

       Collection c = getPresupuestoProductoSessionService().findPresupuestoProductoByPresupuestoId("PRESUPUESTO_ID1"); 
       PresupuestoProductoIf resultado = (PresupuestoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 

   }



    
    @Test (timeout=2000)
    public void findPresupuestoProductoByQuery() throws Exception {
 	
PresupuestoProductoIf value = new PresupuestoProductoData();
 
       
       value.setProductoClienteId("PRODUCTO_CLIENTE_ID1"); 
       value.setPresupuestoId("PRESUPUESTO_ID1"); 
      
      getPresupuestoProductoSessionService().addPresupuestoProducto(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("productoClienteId","PRODUCTO_CLIENTE_ID1"); 
       parametros.put("presupuestoId","PRESUPUESTO_ID1"); 

     Collection c = getPresupuestoProductoSessionService().findPresupuestoProductoByQuery(parametros); 
     PresupuestoProductoIf resultado = (PresupuestoProductoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getProductoClienteId(),"PRODUCTO_CLIENTE_ID1"); 
      Assert.assertEquals(resultado.getPresupuestoId(),"PRESUPUESTO_ID1"); 


    }

public static PresupuestoProductoSessionService getPresupuestoProductoSessionService() {
		try {
			return (PresupuestoProductoSessionService) ServiceLocator
					.getService(ServiceLocator.PresupuestoProductoSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PresupuestoProductoSessionTest.class);

 }


 
*/

}
