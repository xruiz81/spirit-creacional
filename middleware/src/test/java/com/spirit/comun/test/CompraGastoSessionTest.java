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

import com.spirit.compras.entity.CompraGastoData;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.compras.session.CompraGastoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class CompraGastoSessionTest {


   //id=>BIGINT(10)
   //gastoId=>BIGINT(10)
   //compraId=>BIGINT(10)
   //valor=>DECIMAL(22, 2)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/CompraGasto.xml");
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
   public void addCompraGasto() throws Exception {
     CompraGastoIf value = new CompraGastoData();
 
      
       /*value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); */
      
     CompraGastoIf resultado = getCompraGastoSessionService().addCompraGasto(value);
           
       Assert.assertEquals(resultado.getGastoId(),"GASTO_ID1"); 
       Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		
   }

  @Test (timeout=2000)
  public void saveCompraGasto() throws Exception {
      
    CompraGastoIf value = getCompraGastoSessionService().getCompraGasto(new Long(1));
 
          
       /*value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); */
      
     getCompraGastoSessionService().saveCompraGasto(value);

     CompraGastoIf resultado = getCompraGastoSessionService().getCompraGasto(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getGastoId(),"GASTO_ID1"); 
       Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		
   }


 @Test (timeout=2000)
 public void deleteCompraGasto() throws Exception {
      getCompraGastoSessionService().deleteCompraGasto(new Long(3));
      CompraGastoIf resultado = getCompraGastoSessionService().getCompraGasto(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findCompraGastoById() throws Exception {

 	CompraGastoIf value = new CompraGastoData();
 
      
     /*  value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraGastoSessionService().addCompraGasto(value);

       Collection c = getCompraGastoSessionService().findCompraGastoById("ID1"); 
       CompraGastoIf resultado = (CompraGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findCompraGastoByGastoId() throws Exception {

 	CompraGastoIf value = new CompraGastoData();
 
      
       /*value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraGastoSessionService().addCompraGasto(value);

       Collection c = getCompraGastoSessionService().findCompraGastoByGastoId("GASTO_ID1"); 
       CompraGastoIf resultado = (CompraGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getGastoId(),"GASTO_ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findCompraGastoByCompraId() throws Exception {

 	CompraGastoIf value = new CompraGastoData();
 
      /*
       value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraGastoSessionService().addCompraGasto(value);

       Collection c = getCompraGastoSessionService().findCompraGastoByCompraId("COMPRA_ID1"); 
       CompraGastoIf resultado = (CompraGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findCompraGastoByValor() throws Exception {

 	CompraGastoIf value = new CompraGastoData();
 
      /*
       value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraGastoSessionService().addCompraGasto(value);

       Collection c = getCompraGastoSessionService().findCompraGastoByValor("VALOR1"); 
       CompraGastoIf resultado = (CompraGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
*/
   }



    
    @Test (timeout=2000)
    public void findCompraGastoByQuery() throws Exception {
 	
CompraGastoIf value = new CompraGastoData();
 
       /*
       value.setGastoId("GASTO_ID1"); 
       value.setCompraId("COMPRA_ID1"); 
       value.setValor("VALOR1"); 
      */
      getCompraGastoSessionService().addCompraGasto(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("gastoId","GASTO_ID1"); 
       parametros.put("compraId","COMPRA_ID1"); 
       parametros.put("valor","VALOR1"); 

     Collection c = getCompraGastoSessionService().findCompraGastoByQuery(parametros); 
     CompraGastoIf resultado = (CompraGastoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getGastoId(),"GASTO_ID1"); 
      Assert.assertEquals(resultado.getCompraId(),"COMPRA_ID1"); 
      Assert.assertEquals(resultado.getValor(),"VALOR1"); 


    }

public static CompraGastoSessionService getCompraGastoSessionService() {
		try {
			return (CompraGastoSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRA_GASTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CompraGastoSessionTest.class);

 }


 


}
