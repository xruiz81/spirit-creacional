package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.AsientoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class AsientoSessionTest {

	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //numero=>VARCHAR2(20)
   //empresaId=>NUMBER(10)
   //periodoId=>NUMBER(10)
   //plancuentaId=>NUMBER(10)
   //fecha=>DATE
   //status=>VARCHAR2(1)
   //efectivo=>VARCHAR2(1)
   //subtipoasientoId=>NUMBER(10)
   //observacion=>VARCHAR2(350)
   //oficinaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/asiento.xml");
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
	  public void procesarAsiento() throws Exception {
	     AsientoIf asiento = new AsientoData();
	 
	      
	       asiento.setNumero("NUMERO1"); 
	       asiento.setEmpresaId(new Long(1)); 
	       asiento.setPeriodoId(new Long(1)); 
	       asiento.setPlancuentaId(new Long(1)); 
	       asiento.setFecha(now); 
	       asiento.setStatus("S"); 
	       asiento.setEfectivo("E"); 
	       asiento.setSubtipoasientoId(new Long(1)); 
	       asiento.setObservacion("OBSERVACION1"); 
	       asiento.setOficinaId(new Long(1)); 
	     
	       AsientoDetalleIf ad1 = new AsientoDetalleData();
	       ad1.setCuentaId(new Long(1)); 
	       ad1.setAsientoId(new Long(1)); 
	       ad1.setReferencia("REFERENCIA1"); 
	       ad1.setGlosa("GLOSA1"); 
	       ad1.setCentrogastoId(new Long(1)); 
	       ad1.setEmpleadoId(new Long(1)); 
	       ad1.setDepartamentoId(new Long(1)); 
	       ad1.setLineaId(new Long(1)); 
	       ad1.setClienteId(new Long(1)); 
	       ad1.setDebe(new BigDecimal(1)); 
	       ad1.setHaber(new BigDecimal(1)); 
	       
	       AsientoDetalleIf ad2 = new AsientoDetalleData();
	       ad2.setCuentaId(new Long(1)); 
	       ad2.setAsientoId(new Long(1)); 
	       ad2.setReferencia("REFERENCIA1"); 
	       ad2.setGlosa("GLOSA1"); 
	       ad2.setCentrogastoId(new Long(1)); 
	       ad2.setEmpleadoId(new Long(1)); 
	       ad2.setDepartamentoId(new Long(1)); 
	       ad2.setLineaId(new Long(1)); 
	       ad2.setClienteId(new Long(1)); 
	       ad2.setDebe(new BigDecimal(1)); 
	       ad2.setHaber(new BigDecimal(1)); 
	      
	    // AsientoIf resultado = getAsientoSessionService().procesarAsiento(value);
	//AsientoIf model,List<AsientoDetalleIf> modelDetalleList
	
	 }
   @Test (timeout=2000)
   public void addAsiento() throws Exception {
     AsientoIf value = new AsientoData();
 
      
       value.setNumero("NUMERO1"); 
       value.setEmpresaId(new Long(1)); 
       value.setPeriodoId(new Long(1)); 
       value.setPlancuentaId(new Long(1)); 
       value.setFecha(now); 
       value.setStatus("S"); 
       value.setEfectivo("E"); 
       value.setSubtipoasientoId(new Long(1)); 
       value.setObservacion("OBSERVACION1"); 
       value.setOficinaId(new Long(1)); 
      
     AsientoIf resultado = getAsientoSessionService().addAsiento(value);
           
       Assert.assertEquals(resultado.getNumero(),"NUMERO1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
       Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 
       Assert.assertEquals(resultado.getFecha(),now); 
       Assert.assertEquals(resultado.getStatus(),"S"); 
       Assert.assertEquals(resultado.getEfectivo(),"E"); 
       Assert.assertEquals(resultado.getSubtipoasientoId(),new Long(1)); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveAsiento() throws Exception {
      
    AsientoIf value = getAsientoSessionService().getAsiento(new Long(1));
 
          
    value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1)); 
      
     getAsientoSessionService().saveAsiento(value);

     AsientoIf resultado = getAsientoSessionService().getAsiento(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getNumero(),"NUMERO2"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
       Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 
       Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
       Assert.assertEquals(resultado.getStatus(),"A"); 
       Assert.assertEquals(resultado.getEfectivo(),"A"); 
       Assert.assertEquals(resultado.getSubtipoasientoId(),new Long(1)); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION2"); 
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteAsiento() throws Exception {
      getAsientoSessionService().deleteAsiento(new Long(3));
      AsientoIf resultado = getAsientoSessionService().getAsiento(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findAsientoById() throws Exception {
       Collection c = getAsientoSessionService().findAsientoById(new Long(1)); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findAsientoByNumero() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMEROx"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1)); 
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByNumero("NUMEROx"); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNumero(),"NUMEROx"); 

   }

   @Test (timeout=2000)
   public void findAsientoByEmpresaId() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1));  
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByEmpresaId(new Long(1)); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findAsientoByPeriodoId() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1));  
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByPeriodoId(new Long(1)); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findAsientoByPlancuentaId() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1));  
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByPlancuentaId(new Long(1)); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findAsientoByFecha() throws Exception {

 	AsientoIf value = new AsientoData();
 
 	Date date = new Date(System.currentTimeMillis());
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(date); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1));  
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByFecha(date); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getFecha().toString(),date.toString()); 

   }

   @Test (timeout=2000)
   public void findAsientoByStatus() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("x"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1)); 
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByStatus("x"); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getStatus(),"x"); 

   }

   @Test (timeout=2000)
   public void findAsientoByEfectivo() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("x"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1)); 
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByEfectivo("x"); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEfectivo(),"x"); 

   }

   @Test (timeout=2000)
   public void findAsientoBySubtipoasientoId() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1)); 
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoBySubtipoasientoId(new Long(1)); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getSubtipoasientoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findAsientoByObservacion() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACIONx"); 
    value.setOficinaId(new Long(1)); 
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByObservacion("OBSERVACIONx"); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONx"); 

   }

   @Test (timeout=2000)
   public void findAsientoByOficinaId() throws Exception {

 	AsientoIf value = new AsientoData();
 
      
 	value.setNumero("NUMERO2"); 
    value.setEmpresaId(new Long(1)); 
    value.setPeriodoId(new Long(1)); 
    value.setPlancuentaId(new Long(1)); 
    value.setFecha(now); 
    value.setStatus("A"); 
    value.setEfectivo("A"); 
    value.setSubtipoasientoId(new Long(1)); 
    value.setObservacion("OBSERVACION2"); 
    value.setOficinaId(new Long(1)); 
      
       getAsientoSessionService().addAsiento(value);

       Collection c = getAsientoSessionService().findAsientoByOficinaId(new Long(1)); 
       AsientoIf resultado = (AsientoIf)c.iterator().next();
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findAsientoByQuery() throws Exception {
 	
    	AsientoIf value = new AsientoData();
 
       
    	value.setNumero("NUMEROy"); 
        value.setEmpresaId(new Long(1)); 
        value.setPeriodoId(new Long(1)); 
        value.setPlancuentaId(new Long(1)); 
        value.setFecha(now); 
        value.setStatus("y"); 
        value.setEfectivo("y"); 
        value.setSubtipoasientoId(new Long(1)); 
        value.setObservacion("OBSERVACIONy"); 
        value.setOficinaId(new Long(1)); 
      
      getAsientoSessionService().addAsiento(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("numero","NUMEROy"); 
       parametros.put("empresaId",new Long(1)); 
       parametros.put("periodoId",new Long(1)); 
       parametros.put("plancuentaId",new Long(1)); 
       parametros.put("fecha",now); 
       parametros.put("status","y"); 
       parametros.put("efectivo","y"); 
       parametros.put("subtipoasientoId",new Long(1)); 
       parametros.put("observacion","OBSERVACIONy"); 
       parametros.put("oficinaId",new Long(1)); 

     Collection c = getAsientoSessionService().findAsientoByQuery(parametros); 
     AsientoIf resultado = (AsientoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getNumero(),"NUMEROy"); 
      Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
      Assert.assertEquals(resultado.getPeriodoId(),new Long(1)); 
      Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 
      Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
      Assert.assertEquals(resultado.getStatus(),"y"); 
      Assert.assertEquals(resultado.getEfectivo(),"y"); 
      Assert.assertEquals(resultado.getSubtipoasientoId(),new Long(1)); 
      Assert.assertEquals(resultado.getObservacion(),"OBSERVACIONy"); 
      Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 


    }

public static AsientoSessionService getAsientoSessionService() {
		try {
			return (AsientoSessionService) ServiceLocator
					.getService(ServiceLocator.ASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AsientoSessionTest.class);

 }


 


}
