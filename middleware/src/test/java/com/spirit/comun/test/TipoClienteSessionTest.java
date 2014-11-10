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

import com.spirit.general.entity.TipoClienteData;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.session.TipoClienteSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoClienteSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(2)
   //nombre=>VARCHAR2(20)
   //empresaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_cliente.xml");
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
   public void addTipoCliente() throws Exception {
     TipoClienteIf value = new TipoClienteData();
 
       value.setCodigo("CO"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
      
     TipoClienteIf resultado = getTipoClienteSessionService().addTipoCliente(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveTipoCliente() throws Exception {
      
    TipoClienteIf value = getTipoClienteSessionService().getTipoCliente(new Long(1));
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
     getTipoClienteSessionService().saveTipoCliente(value);

     TipoClienteIf resultado = getTipoClienteSessionService().getTipoCliente(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteTipoCliente() throws Exception {
      getTipoClienteSessionService().deleteTipoCliente(new Long(3));
      TipoClienteIf resultado = getTipoClienteSessionService().getTipoCliente(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoClienteById() throws Exception {

 	TipoClienteIf value = new TipoClienteData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoClienteSessionService().addTipoCliente(value);

       Collection c = getTipoClienteSessionService().findTipoClienteById(new Long(1)); 
       TipoClienteIf resultado = (TipoClienteIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findTipoClienteByCodigo() throws Exception {

 	TipoClienteIf value = new TipoClienteData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1));  
      
       getTipoClienteSessionService().addTipoCliente(value);

       Collection c = getTipoClienteSessionService().findTipoClienteByCodigo("CO"); 
       TipoClienteIf resultado = (TipoClienteIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CO"); 

   }

   @Test (timeout=2000)
   public void findTipoClienteByNombre() throws Exception {

 	TipoClienteIf value = new TipoClienteData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoClienteSessionService().addTipoCliente(value);

       Collection c = getTipoClienteSessionService().findTipoClienteByNombre("NOMBRE1"); 
       TipoClienteIf resultado = (TipoClienteIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findTipoClienteByEmpresaId() throws Exception {

 	TipoClienteIf value = new TipoClienteData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoClienteSessionService().addTipoCliente(value);

       Collection c = getTipoClienteSessionService().findTipoClienteByEmpresaId(new Long(1)); 
       TipoClienteIf resultado = (TipoClienteIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findTipoClienteByQuery() throws Exception {
 	
TipoClienteIf value = new TipoClienteData();
 
value.setCodigo("CO"); 
value.setNombre("NOMBRE1"); 
value.setEmpresaId(new Long(1)); 
      
      getTipoClienteSessionService().addTipoCliente(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CO"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("empresaId",new Long(1)); 

     Collection c = getTipoClienteSessionService().findTipoClienteByQuery(parametros); 
     TipoClienteIf resultado = (TipoClienteIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"CO"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 


    }

public static TipoClienteSessionService getTipoClienteSessionService() {
		try {
			return (TipoClienteSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCLIENTESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoClienteSessionTest.class);

 }
 
}
