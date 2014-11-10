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

import com.spirit.compras.entity.CompraDetalleGastoData;
import com.spirit.compras.entity.CompraDetalleGastoIf;
import com.spirit.compras.session.CompraDetalleGastoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class CompraDetalleGastoSessionTest {


   //id=>BIGINT(10)
   //compraGastoId=>BIGINT(10)
   //compraDetalleId=>BIGINT(10)
   //valor=>DECIMAL(22, 2)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/CompraDetalleGasto.xml");
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
   public void addCompraDetalleGasto() throws Exception {
     CompraDetalleGastoIf value = new CompraDetalleGastoData();
 
      
       /*value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); */
      
     CompraDetalleGastoIf resultado = getCompraDetalleGastoSessionService().addCompraDetalleGasto(value);
           
       Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
       Assert.assertEquals(resultado.getCompraDetalleId(),"COMPRA_DETALLE_ID1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		
   }

  @Test (timeout=2000)
  public void saveCompraDetalleGasto() throws Exception {
      
    CompraDetalleGastoIf value = getCompraDetalleGastoSessionService().getCompraDetalleGasto(new Long(1));
 
          
       /*value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); */
      
     getCompraDetalleGastoSessionService().saveCompraDetalleGasto(value);

     CompraDetalleGastoIf resultado = getCompraDetalleGastoSessionService().getCompraDetalleGasto(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
       Assert.assertEquals(resultado.getCompraDetalleId(),"COMPRA_DETALLE_ID1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		
   }


 @Test (timeout=2000)
 public void deleteCompraDetalleGasto() throws Exception {
      getCompraDetalleGastoSessionService().deleteCompraDetalleGasto(new Long(3));
      CompraDetalleGastoIf resultado = getCompraDetalleGastoSessionService().getCompraDetalleGasto(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findCompraDetalleGastoById() throws Exception {

 	CompraDetalleGastoIf value = new CompraDetalleGastoData();
 
      
      /* value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraDetalleGastoSessionService().addCompraDetalleGasto(value);

       Collection c = getCompraDetalleGastoSessionService().findCompraDetalleGastoById("ID1"); 
       CompraDetalleGastoIf resultado = (CompraDetalleGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findCompraDetalleGastoByCompraGastoId() throws Exception {

 	CompraDetalleGastoIf value = new CompraDetalleGastoData();
 
      
       /*value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraDetalleGastoSessionService().addCompraDetalleGasto(value);

       Collection c = getCompraDetalleGastoSessionService().findCompraDetalleGastoByCompraGastoId("COMPRA_GASTO_ID1"); 
       
       CompraDetalleGastoIf resultado = (CompraDetalleGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findCompraDetalleGastoByCompraDetalleId() throws Exception {

 	CompraDetalleGastoIf value = new CompraDetalleGastoData();
 
      
       /*value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraDetalleGastoSessionService().addCompraDetalleGasto(value);

       Collection c = getCompraDetalleGastoSessionService().findCompraDetalleGastoByCompraDetalleId("COMPRA_DETALLE_ID1"); 
       CompraDetalleGastoIf resultado = (CompraDetalleGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCompraDetalleId(),"COMPRA_DETALLE_ID1"); 
*/
   }

   @Test (timeout=2000)
   public void findCompraDetalleGastoByValor() throws Exception {

 	CompraDetalleGastoIf value = new CompraDetalleGastoData();
 
      
      /* value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); 
      
       getCompraDetalleGastoSessionService().addCompraDetalleGasto(value);

       Collection c = getCompraDetalleGastoSessionService().findCompraDetalleGastoByValor("VALOR1"); 
       CompraDetalleGastoIf resultado = (CompraDetalleGastoIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
*/
   }



    
    @Test (timeout=2000)
    public void findCompraDetalleGastoByQuery() throws Exception {
 	
CompraDetalleGastoIf value = new CompraDetalleGastoData();
 
       
      /* value.setCompraGastoId("COMPRA_GASTO_ID1"); 
       value.setCompraDetalleId("COMPRA_DETALLE_ID1"); 
       value.setValor("VALOR1"); 
      */
      getCompraDetalleGastoSessionService().addCompraDetalleGasto(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("compraGastoId","COMPRA_GASTO_ID1"); 
       parametros.put("compraDetalleId","COMPRA_DETALLE_ID1"); 
       parametros.put("valor","VALOR1"); 

     Collection c = getCompraDetalleGastoSessionService().findCompraDetalleGastoByQuery(parametros); 
     CompraDetalleGastoIf resultado = (CompraDetalleGastoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCompraGastoId(),"COMPRA_GASTO_ID1"); 
      Assert.assertEquals(resultado.getCompraDetalleId(),"COMPRA_DETALLE_ID1"); 
      Assert.assertEquals(resultado.getValor(),"VALOR1"); 


    }

public static CompraDetalleGastoSessionService getCompraDetalleGastoSessionService() {
		try {
			return (CompraDetalleGastoSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRA_DETALLE_GASTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CompraDetalleGastoSessionTest.class);

 }


 


}
