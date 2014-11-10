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

import com.spirit.nomina.entity.RolPagoData;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.session.RolPagoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class RolPagoSessionTest {


	//id=>NUMBER(10)
	//tiporolId=>NUMBER(10)
	//estado=>VARCHAR2(1)
	//mes=>VARCHAR2(2)
	//anio=>VARCHAR2(4)
	//fecha=>DATE

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/RolPago.xml");
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


	/*

   @Test (timeout=2000)
   public void addRolPago() throws Exception {
     RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

     RolPagoIf resultado = getRolPagoSessionService().addRolPago(value);

       Assert.assertEquals(resultado.getTiporolId(),"TIPOROL_ID1"); 
       Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
       Assert.assertEquals(resultado.getMes(),"MES1"); 
       Assert.assertEquals(resultado.getAnio(),"ANIO1"); 
       Assert.assertEquals(resultado.getFecha(),"FECHA1"); 

   }

  @Test (timeout=2000)
  public void saveRolPago() throws Exception {

    RolPagoIf value = getRolPagoSessionService().getRolPago(new Long(1));


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

     getRolPagoSessionService().saveRolPago(value);

     RolPagoIf resultado = getRolPagoSessionService().getRolPago(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getTiporolId(),"TIPOROL_ID1"); 
       Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
       Assert.assertEquals(resultado.getMes(),"MES1"); 
       Assert.assertEquals(resultado.getAnio(),"ANIO1"); 
       Assert.assertEquals(resultado.getFecha(),"FECHA1"); 

   }


 @Test (timeout=2000)
 public void deleteRolPago() throws Exception {
      getRolPagoSessionService().deleteRolPago(new Long(3));
      RolPagoIf resultado = getRolPagoSessionService().getRolPago(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findRolPagoById() throws Exception {

 	RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

       getRolPagoSessionService().addRolPago(value);

       Collection c = getRolPagoSessionService().findRolPagoById("ID1"); 
       RolPagoIf resultado = (RolPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoByTiporolId() throws Exception {

 	RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

       getRolPagoSessionService().addRolPago(value);

       Collection c = getRolPagoSessionService().findRolPagoByTiporolId("TIPOROL_ID1"); 
       RolPagoIf resultado = (RolPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTiporolId(),"TIPOROL_ID1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoByEstado() throws Exception {

 	RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

       getRolPagoSessionService().addRolPago(value);

       Collection c = getRolPagoSessionService().findRolPagoByEstado("ESTADO1"); 
       RolPagoIf resultado = (RolPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoByMes() throws Exception {

 	RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

       getRolPagoSessionService().addRolPago(value);

       Collection c = getRolPagoSessionService().findRolPagoByMes("MES1"); 
       RolPagoIf resultado = (RolPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getMes(),"MES1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoByAnio() throws Exception {

 	RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

       getRolPagoSessionService().addRolPago(value);

       Collection c = getRolPagoSessionService().findRolPagoByAnio("ANIO1"); 
       RolPagoIf resultado = (RolPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getAnio(),"ANIO1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoByFecha() throws Exception {

 	RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

       getRolPagoSessionService().addRolPago(value);

       Collection c = getRolPagoSessionService().findRolPagoByFecha("FECHA1"); 
       RolPagoIf resultado = (RolPagoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFecha(),"FECHA1"); 

   }




    @Test (timeout=2000)
    public void findRolPagoByQuery() throws Exception {

RolPagoIf value = new RolPagoData();


       value.setTiporolId("TIPOROL_ID1"); 
       value.setEstado("ESTADO1"); 
       value.setMes("MES1"); 
       value.setAnio("ANIO1"); 
       value.setFecha("FECHA1"); 

      getRolPagoSessionService().addRolPago(value);

      Map<String, Object> parametros = new HashMap<String, Object>();


       parametros.put("tiporolId","TIPOROL_ID1"); 
       parametros.put("estado","ESTADO1"); 
       parametros.put("mes","MES1"); 
       parametros.put("anio","ANIO1"); 
       parametros.put("fecha","FECHA1"); 

     Collection c = getRolPagoSessionService().findRolPagoByQuery(parametros); 
     RolPagoIf resultado = (RolPagoIf)c.iterator().next();


      Assert.assertEquals(resultado.getTiporolId(),"TIPOROL_ID1"); 
      Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
      Assert.assertEquals(resultado.getMes(),"MES1"); 
      Assert.assertEquals(resultado.getAnio(),"ANIO1"); 
      Assert.assertEquals(resultado.getFecha(),"FECHA1"); 


    }
	 */

	public static RolPagoSessionService getRolPagoSessionService() {
		try {
			return (RolPagoSessionService) ServiceLocator
			.getService(ServiceLocator.ROLPAGOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RolPagoSessionTest.class);

	}
}
