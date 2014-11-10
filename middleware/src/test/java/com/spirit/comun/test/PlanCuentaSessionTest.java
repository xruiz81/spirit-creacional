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

import com.spirit.client.SpiritAlert;
import com.spirit.contabilidad.entity.PlanCuentaData;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.session.PlanCuentaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class PlanCuentaSessionTest {

	
	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //codigo=>VARCHAR2(4)
   //nombre=>VARCHAR2(30)
   //empresaId=>NUMBER(10)
   //fecha=>DATE
   //monedaId=>NUMBER(10)
   //estado=>VARCHAR2(1)
   //mascara=>VARCHAR2(50)
   //predeterminado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/plan_cuenta.xml");
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
   public void addPlanCuenta() throws Exception {
     PlanCuentaIf value = new PlanCuentaData();
 
      
       value.setCodigo("COD1"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
       value.setFecha(now); 
       value.setMonedaId(new Long(1)); 
       value.setEstado("E"); 
       value.setMascara("MASCARA1");
       value.setPredeterminado("S");
      
     PlanCuentaIf resultado = getPlanCuentaSessionService().addPlanCuenta(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getFecha(),now); 
       Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
       Assert.assertEquals(resultado.getEstado(),"E"); 
       Assert.assertEquals(resultado.getMascara(),"MASCARA1");
       Assert.assertEquals(resultado.getPredeterminado(),"S");
		
   }

  @Test (timeout=2000)
  public void savePlanCuenta() throws Exception {
      
    PlanCuentaIf value = getPlanCuentaSessionService().getPlanCuenta(new Long(1));
 
          
    value.setCodigo("COD2"); 
    value.setNombre("NOMBRE2"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("A"); 
    value.setMascara("MASCARA2");
    value.setPredeterminado("N");
      
     getPlanCuentaSessionService().savePlanCuenta(value);

     PlanCuentaIf resultado = getPlanCuentaSessionService().getPlanCuenta(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
       Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getMascara(),"MASCARA2");
       Assert.assertEquals(resultado.getPredeterminado(),"N");
		
   }


 @Test (timeout=2000)
 public void deletePlanCuenta() throws Exception {
      getPlanCuentaSessionService().deletePlanCuenta(new Long(3));
      PlanCuentaIf resultado = getPlanCuentaSessionService().getPlanCuenta(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPlanCuentaById() throws Exception {
       Collection c = getPlanCuentaSessionService().findPlanCuentaById(new Long(1)); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findPlanCuentaByCodigo() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
       value.setCodigo("CODx"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
       value.setFecha(now); 
       value.setMonedaId(new Long(1)); 
       value.setEstado("E"); 
       value.setMascara("MASCARA1"); 
       value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByCodigo("CODx"); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODx"); 

   }

   @Test (timeout=2000)
   public void findPlanCuentaByNombre() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBREx"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("E"); 
    value.setMascara("MASCARA1"); 
    value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByNombre("NOMBREx"); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }

   @Test (timeout=2000)
   public void findPlanCuentaByEmpresaId() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("E"); 
    value.setMascara("MASCARA1"); 
    value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByEmpresaId(new Long(1)); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPlanCuentaByFecha() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
 	Date date = new Date(System.currentTimeMillis());     
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(date); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("E"); 
    value.setMascara("MASCARA1"); 
    value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByFecha(date); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFecha().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findPlanCuentaByMonedaId() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("E"); 
    value.setMascara("MASCARA1"); 
    value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByMonedaId(new Long(1)); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPlanCuentaByEstado() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("x"); 
    value.setMascara("MASCARA1"); 
    value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByEstado("x"); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"x"); 

   }

   @Test (timeout=2000)
   public void findPlanCuentaByMascara() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("E"); 
    value.setMascara("MASCARAx"); 
    value.setPredeterminado("N");
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByMascara("MASCARAx"); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getMascara(),"MASCARAx"); 

   }


   @Test (timeout=2000)
   public void findPlanCuentaByPredeterminado() throws Exception {

 	PlanCuentaIf value = new PlanCuentaData();
 
      
 	value.setCodigo("CODx"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setFecha(now); 
    value.setMonedaId(new Long(1)); 
    value.setEstado("E"); 
    value.setMascara("MASCARAx"); 
    value.setPredeterminado("x"); 
      
       getPlanCuentaSessionService().addPlanCuenta(value);

       Collection c = getPlanCuentaSessionService().findPlanCuentaByPredeterminado("x"); 
       PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPredeterminado(),"x"); 

   }

    
    @Test (timeout=2000)
    public void findPlanCuentaByQuery() throws Exception {
 	
    	PlanCuentaIf value = new PlanCuentaData();
 
       
    	value.setCodigo("CODy"); 
        value.setNombre("NOMBREy"); 
        value.setEmpresaId(new Long(1)); 
        value.setFecha(now); 
        value.setMonedaId(new Long(1)); 
        value.setEstado("y"); 
        value.setMascara("MASCARAy");  
        value.setPredeterminado("y");
      
      getPlanCuentaSessionService().addPlanCuenta(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CODy"); 
       parametros.put("nombre","NOMBREy"); 
       parametros.put("empresaId",new Long(1)); 
       parametros.put("fecha",now); 
       parametros.put("monedaId",new Long(1)); 
       parametros.put("estado","y"); 
       parametros.put("mascara","MASCARAy");
       parametros.put("predeterminado","y");

     Collection c = getPlanCuentaSessionService().findPlanCuentaByQuery(parametros); 
     PlanCuentaIf resultado = (PlanCuentaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCodigo(),"CODy"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
      Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
      Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
      Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
      Assert.assertEquals(resultado.getEstado(),"y"); 
      Assert.assertEquals(resultado.getMascara(),"MASCARAy"); 
      Assert.assertEquals(resultado.getPredeterminado(),"y"); 

    }

public static PlanCuentaSessionService getPlanCuentaSessionService() {
		try {
			return (PlanCuentaSessionService) ServiceLocator
					.getService(ServiceLocator.PLANCUENTASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PlanCuentaSessionTest.class);

 }


 


}
