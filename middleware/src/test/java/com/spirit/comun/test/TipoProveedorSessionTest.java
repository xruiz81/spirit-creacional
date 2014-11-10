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

import com.spirit.general.entity.TipoProveedorData;
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.general.session.TipoProveedorSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoProveedorSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(2)
   //nombre=>VARCHAR2(40)
   //tipo=>VARCHAR2(1)
   //empresaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_proveedor.xml");
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
   public void addTipoProveedor() throws Exception {
     TipoProveedorIf value = new TipoProveedorData();
 
       value.setCodigo("CO"); 
       value.setNombre("NOMBRE1"); 
       value.setTipo("M"); 
       value.setEmpresaId(new Long(1)); 
      
     TipoProveedorIf resultado = getTipoProveedorSessionService().addTipoProveedor(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getTipo(),"M"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveTipoProveedor() throws Exception {
      
    TipoProveedorIf value = getTipoProveedorSessionService().getTipoProveedor(new Long(1));
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("M"); 
    value.setEmpresaId(new Long(1));  
      
     getTipoProveedorSessionService().saveTipoProveedor(value);

     TipoProveedorIf resultado = getTipoProveedorSessionService().getTipoProveedor(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getTipo(),"M"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteTipoProveedor() throws Exception {
      getTipoProveedorSessionService().deleteTipoProveedor(new Long(3));
      TipoProveedorIf resultado = getTipoProveedorSessionService().getTipoProveedor(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoProveedorById() throws Exception {

 	TipoProveedorIf value = new TipoProveedorData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("M"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoProveedorSessionService().addTipoProveedor(value);

       Collection c = getTipoProveedorSessionService().findTipoProveedorById(new Long(1)); 
       TipoProveedorIf resultado = (TipoProveedorIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findTipoProveedorByCodigo() throws Exception {

 	TipoProveedorIf value = new TipoProveedorData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("M"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoProveedorSessionService().addTipoProveedor(value);

       Collection c = getTipoProveedorSessionService().findTipoProveedorByCodigo("CO"); 
       TipoProveedorIf resultado = (TipoProveedorIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CO"); 

   }

   @Test (timeout=2000)
   public void findTipoProveedorByNombre() throws Exception {

 	TipoProveedorIf value = new TipoProveedorData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("M"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoProveedorSessionService().addTipoProveedor(value);

       Collection c = getTipoProveedorSessionService().findTipoProveedorByNombre("NOMBRE1"); 
       TipoProveedorIf resultado = (TipoProveedorIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findTipoProveedorByTipo() throws Exception {

 	TipoProveedorIf value = new TipoProveedorData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("M"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoProveedorSessionService().addTipoProveedor(value);

       Collection c = getTipoProveedorSessionService().findTipoProveedorByTipo("M"); 
       TipoProveedorIf resultado = (TipoProveedorIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipo(),"M"); 

   }

   @Test (timeout=2000)
   public void findTipoProveedorByEmpresaId() throws Exception {

 	TipoProveedorIf value = new TipoProveedorData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("M"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoProveedorSessionService().addTipoProveedor(value);

       Collection c = getTipoProveedorSessionService().findTipoProveedorByEmpresaId(new Long(1)); 
       TipoProveedorIf resultado = (TipoProveedorIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

    
    @Test (timeout=2000)
    public void findTipoProveedorByQuery() throws Exception {
 	
TipoProveedorIf value = new TipoProveedorData();
 
value.setCodigo("CO"); 
value.setNombre("NOMBRE1"); 
value.setTipo("M"); 
value.setEmpresaId(new Long(1)); 
      
      getTipoProveedorSessionService().addTipoProveedor(value);

      Map<String, Object> parametros = new HashMap<String, Object>();
       
       parametros.put("codigo","CO"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("tipo","M"); 
       parametros.put("empresaId",new Long(1)); 

     Collection c = getTipoProveedorSessionService().findTipoProveedorByQuery(parametros); 
     TipoProveedorIf resultado = (TipoProveedorIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"CO"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getTipo(),"M"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
    }

public static TipoProveedorSessionService getTipoProveedorSessionService() {
		try {
			return (TipoProveedorSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPROVEEDORSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoProveedorSessionTest.class);

 }

}
