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

import com.spirit.inventario.entity.TipoProductoData;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.inventario.session.TipoProductoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class TipoProductoSessionTest {


   //id=>NUMBER(10)
   //codigo=>VARCHAR2(3)
   //nombre=>VARCHAR2(30)
   //empresaId=>NUMBER(10)
   //estado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_producto.xml");
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
   public void addTipoProducto() throws Exception {
     TipoProductoIf value = new TipoProductoData();
 
      
       value.setCodigo("CO1"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
       value.setEstado("E");  
      
     TipoProductoIf resultado = getTipoProductoSessionService().addTipoProducto(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CO1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getEstado(),"E");  
		
   }

  @Test (timeout=2000)
  public void saveTipoProducto() throws Exception {
      
    TipoProductoIf value = getTipoProductoSessionService().getTipoProducto(new Long(1));
 
          
    value.setCodigo("CO2"); 
    value.setNombre("NOMBRE2"); 
    value.setEmpresaId(new Long(1)); 
    value.setEstado("A"); 
      
     getTipoProductoSessionService().saveTipoProducto(value);

     TipoProductoIf resultado = getTipoProductoSessionService().getTipoProducto(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CO2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getEstado(),"A");  
		
   }


 @Test (timeout=2000)
 public void deleteTipoProducto() throws Exception {
      getTipoProductoSessionService().deleteTipoProducto(new Long(3));
      TipoProductoIf resultado = getTipoProductoSessionService().getTipoProducto(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoProductoById() throws Exception {
       Collection c = getTipoProductoSessionService().findTipoProductoById(new Long(1)); 
       TipoProductoIf resultado = (TipoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findTipoProductoByCodigo() throws Exception {

 	TipoProductoIf value = new TipoProductoData();
 
      
 	value.setCodigo("COx"); 
    value.setNombre("NOMBRE2"); 
    value.setEmpresaId(new Long(1)); 
    value.setEstado("A"); 
      
       getTipoProductoSessionService().addTipoProducto(value);

       Collection c = getTipoProductoSessionService().findTipoProductoByCodigo("COx"); 
       TipoProductoIf resultado = (TipoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COx"); 

   }

   @Test (timeout=2000)
   public void findTipoProductoByNombre() throws Exception {

 	TipoProductoIf value = new TipoProductoData();
 
      
 	value.setCodigo("COx"); 
    value.setNombre("NOMBREx"); 
    value.setEmpresaId(new Long(1)); 
    value.setEstado("A"); 
      
       getTipoProductoSessionService().addTipoProducto(value);

       Collection c = getTipoProductoSessionService().findTipoProductoByNombre("NOMBREx"); 
       TipoProductoIf resultado = (TipoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }

   @Test (timeout=2000)
   public void findTipoProductoByEmpresaId() throws Exception {

 	TipoProductoIf value = new TipoProductoData();
 
      
 	value.setCodigo("COx"); 
    value.setNombre("NOMBRE2"); 
    value.setEmpresaId(new Long(1)); 
    value.setEstado("A"); 
      
       getTipoProductoSessionService().addTipoProducto(value);

       Collection c = getTipoProductoSessionService().findTipoProductoByEmpresaId(new Long(1)); 
       TipoProductoIf resultado = (TipoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findTipoProductoByEstado() throws Exception {

 	TipoProductoIf value = new TipoProductoData();
 
      
 	value.setCodigo("COx"); 
    value.setNombre("NOMBRE2"); 
    value.setEmpresaId(new Long(1)); 
    value.setEstado("x"); 
      
       getTipoProductoSessionService().addTipoProducto(value);

       Collection c = getTipoProductoSessionService().findTipoProductoByEstado("x"); 
       TipoProductoIf resultado = (TipoProductoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"x"); 

   }

    @Test (timeout=2000)
    public void findTipoProductoByQuery() throws Exception {
 	
    	TipoProductoIf value = new TipoProductoData();
 
       
    	value.setCodigo("COy"); 
        value.setNombre("NOMBREy"); 
        value.setEmpresaId(new Long(1)); 
        value.setEstado("y");  
      
      getTipoProductoSessionService().addTipoProducto(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COy"); 
       parametros.put("nombre","NOMBREy"); 
       parametros.put("empresaId",new Long(1)); 
       parametros.put("estado","y");  

     Collection c = getTipoProductoSessionService().findTipoProductoByQuery(parametros); 
     TipoProductoIf resultado = (TipoProductoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"COy"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
      Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
      Assert.assertEquals(resultado.getEstado(),"y");  

    }

public static TipoProductoSessionService getTipoProductoSessionService() {
		try {
			return (TipoProductoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoProductoSessionTest.class);

 }


 


}
