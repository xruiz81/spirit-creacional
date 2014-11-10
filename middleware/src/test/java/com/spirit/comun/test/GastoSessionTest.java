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

import com.spirit.compras.entity.GastoData;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.session.GastoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class GastoSessionTest {


   //id=>BIGINT(10)
   //codigo=>VARCHAR(4)
   //nombre=>VARCHAR(50)
   //empresaId=>BIGINT(10)
   //tipo=>VARCHAR(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/Gasto.xml");
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
   public void addGasto() throws Exception {
     GastoIf value = new GastoData();
 
      
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       //value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
     GastoIf resultado = getGastoSessionService().addGasto(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
       Assert.assertEquals(resultado.getTipo(),"TIPO1"); 
		
   }

  @Test (timeout=2000)
  public void saveGasto() throws Exception {
      
    GastoIf value = getGastoSessionService().getGasto(new Long(1));
 
          
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       //value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
     getGastoSessionService().saveGasto(value);

     GastoIf resultado = getGastoSessionService().getGasto(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
       Assert.assertEquals(resultado.getTipo(),"TIPO1"); 
		
   }


 @Test (timeout=2000)
 public void deleteGasto() throws Exception {
      getGastoSessionService().deleteGasto(new Long(3));
      GastoIf resultado = getGastoSessionService().getGasto(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findGastoById() throws Exception {

 	GastoIf value = new GastoData();
 
      
      /* value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
       getGastoSessionService().addGasto(value);

       Collection c = getGastoSessionService().findGastoById("ID1"); 
       GastoIf resultado = (GastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findGastoByCodigo() throws Exception {

 	GastoIf value = new GastoData();
 
      
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       //value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
       getGastoSessionService().addGasto(value);

       Collection c = getGastoSessionService().findGastoByCodigo("CODIGO1"); 
       GastoIf resultado = (GastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 

   }

   @Test (timeout=2000)
   public void findGastoByNombre() throws Exception {

 	GastoIf value = new GastoData();
 
      
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       //value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
       getGastoSessionService().addGasto(value);

       Collection c = getGastoSessionService().findGastoByNombre("NOMBRE1"); 
       GastoIf resultado = (GastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findGastoByEmpresaId() throws Exception {

 	GastoIf value = new GastoData();
 
      
       /*value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
       getGastoSessionService().addGasto(value);

       Collection c = getGastoSessionService().findGastoByEmpresaId("EMPRESA_ID1"); 
       GastoIf resultado = (GastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findGastoByTipo() throws Exception {

 	GastoIf value = new GastoData();
 
      
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       //value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
       getGastoSessionService().addGasto(value);

       Collection c = getGastoSessionService().findGastoByTipo("TIPO1"); 
       GastoIf resultado = (GastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipo(),"TIPO1"); 

   }



    
    @Test (timeout=2000)
    public void findGastoByQuery() throws Exception {
 	
GastoIf value = new GastoData();
 
       
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       //value.setEmpresaId("EMPRESA_ID1"); 
       value.setTipo("TIPO1"); 
      
      getGastoSessionService().addGasto(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CODIGO1"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("empresaId","EMPRESA_ID1"); 
       parametros.put("tipo","TIPO1"); 

     Collection c = getGastoSessionService().findGastoByQuery(parametros); 
     GastoIf resultado = (GastoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
      Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
      Assert.assertEquals(resultado.getTipo(),"TIPO1"); 


    }

public static GastoSessionService getGastoSessionService() {
		try {
			return (GastoSessionService) ServiceLocator
					.getService(ServiceLocator.GASTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(GastoSessionTest.class);

 }


 


}
