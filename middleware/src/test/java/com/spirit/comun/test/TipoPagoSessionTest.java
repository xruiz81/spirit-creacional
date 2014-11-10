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

import com.spirit.general.entity.TipoPagoData;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.session.TipoPagoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoPagoSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(2)
   //nombre=>VARCHAR2(40)
   //empresaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_pago.xml");
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
   public void addTipoPago() throws Exception {
     TipoPagoIf value = new TipoPagoData();
 
     
       value.setCodigo("CO"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
      
     TipoPagoIf resultado = getTipoPagoSessionService().addTipoPago(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveTipoPago() throws Exception {
      
    TipoPagoIf value = getTipoPagoSessionService().getTipoPago(new Long(1));
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
     getTipoPagoSessionService().saveTipoPago(value);

     TipoPagoIf resultado = getTipoPagoSessionService().getTipoPago(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1));  
		
   }


 @Test (timeout=2000)
 public void deleteTipoPago() throws Exception {
      getTipoPagoSessionService().deleteTipoPago(new Long(3));
      TipoPagoIf resultado = getTipoPagoSessionService().getTipoPago(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoPagoById() throws Exception {

 	TipoPagoIf value = new TipoPagoData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoPagoSessionService().addTipoPago(value);

       Collection c = getTipoPagoSessionService().findTipoPagoById(new Long(1)); 
       TipoPagoIf resultado = (TipoPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findTipoPagoByCodigo() throws Exception {

 	TipoPagoIf value = new TipoPagoData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoPagoSessionService().addTipoPago(value);

       Collection c = getTipoPagoSessionService().findTipoPagoByCodigo("CO"); 
       TipoPagoIf resultado = (TipoPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CO"); 

   }

   @Test (timeout=2000)
   public void findTipoPagoByNombre() throws Exception {

 	TipoPagoIf value = new TipoPagoData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoPagoSessionService().addTipoPago(value);

       Collection c = getTipoPagoSessionService().findTipoPagoByNombre("NOMBRE1"); 
       TipoPagoIf resultado = (TipoPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findTipoPagoByEmpresaId() throws Exception {

 	TipoPagoIf value = new TipoPagoData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoPagoSessionService().addTipoPago(value);

       Collection c = getTipoPagoSessionService().findTipoPagoByEmpresaId(new Long(1)); 
       TipoPagoIf resultado = (TipoPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findTipoPagoByQuery() throws Exception {
 	
TipoPagoIf value = new TipoPagoData();
 
value.setCodigo("CO"); 
value.setNombre("NOMBRE1"); 
value.setEmpresaId(new Long(1));  
      
      getTipoPagoSessionService().addTipoPago(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CO"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("empresaId",new Long(1)); 

     Collection c = getTipoPagoSessionService().findTipoPagoByQuery(parametros); 
     TipoPagoIf resultado = (TipoPagoIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"CO"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

    }

public static TipoPagoSessionService getTipoPagoSessionService() {
		try {
			return (TipoPagoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPAGOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoPagoSessionTest.class);

 }

}
