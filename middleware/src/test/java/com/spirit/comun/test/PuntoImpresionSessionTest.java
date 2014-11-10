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

import com.spirit.general.entity.PuntoImpresionData;
import com.spirit.general.entity.PuntoImpresionIf;
import com.spirit.general.session.PuntoImpresionSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class PuntoImpresionSessionTest {

   //id=>NUMBER(10)
   //tipodocumentoId=>NUMBER(10)
   //cajaId=>NUMBER(10)
   //serie=>VARCHAR2(3)
   //impresora=>VARCHAR2(100)
   //estado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/punto_impresion.xml");
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
   public void addPuntoImpresion() throws Exception {
     PuntoImpresionIf value = new PuntoImpresionData();
 
       value.setTipodocumentoId(new Long(1)); 
       value.setCajaId(new Long(1)); 
       value.setSerie("SER"); 
       value.setImpresora("IMPRESORA1"); 
       value.setEstado("A"); 
      
     PuntoImpresionIf resultado = getPuntoImpresionSessionService().addPuntoImpresion(value);
           
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCajaId(),new Long(1)); 
       Assert.assertEquals(resultado.getSerie(),"SER"); 
       Assert.assertEquals(resultado.getImpresora(),"IMPRESORA1"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }

  @Test (timeout=2000)
  public void savePuntoImpresion() throws Exception {
      
    PuntoImpresionIf value = getPuntoImpresionSessionService().getPuntoImpresion(new Long(1));
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A"); 
      
     getPuntoImpresionSessionService().savePuntoImpresion(value);

     PuntoImpresionIf resultado = getPuntoImpresionSessionService().getPuntoImpresion(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getCajaId(),new Long(1)); 
       Assert.assertEquals(resultado.getSerie(),"SER"); 
       Assert.assertEquals(resultado.getImpresora(),"IMPRESORA1"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }


 @Test (timeout=2000)
 public void deletePuntoImpresion() throws Exception {
      getPuntoImpresionSessionService().deletePuntoImpresion(new Long(2));
      PuntoImpresionIf resultado = getPuntoImpresionSessionService().getPuntoImpresion(new Long(2));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findPuntoImpresionById() throws Exception {

 	PuntoImpresionIf value = new PuntoImpresionData();
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A");  
      
       getPuntoImpresionSessionService().addPuntoImpresion(value);

       Collection c = getPuntoImpresionSessionService().findPuntoImpresionById(new Long(1)); 
       PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPuntoImpresionByTipodocumentoId() throws Exception {

 	PuntoImpresionIf value = new PuntoImpresionData();
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A"); 
      
       getPuntoImpresionSessionService().addPuntoImpresion(value);

       Collection c = getPuntoImpresionSessionService().findPuntoImpresionByTipodocumentoId(new Long(1)); 
       PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPuntoImpresionByCajaId() throws Exception {

 	PuntoImpresionIf value = new PuntoImpresionData();
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A"); 
      
       getPuntoImpresionSessionService().addPuntoImpresion(value);

       Collection c = getPuntoImpresionSessionService().findPuntoImpresionByCajaId(new Long(1)); 
       PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
       Assert.assertEquals(resultado.getCajaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findPuntoImpresionBySerie() throws Exception {

 	PuntoImpresionIf value = new PuntoImpresionData();
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A"); 
      
       getPuntoImpresionSessionService().addPuntoImpresion(value);

       Collection c = getPuntoImpresionSessionService().findPuntoImpresionBySerie("SER"); 
       PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
       Assert.assertEquals(resultado.getSerie(),"SER"); 

   }

   @Test (timeout=2000)
   public void findPuntoImpresionByImpresora() throws Exception {

 	PuntoImpresionIf value = new PuntoImpresionData();
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A"); 
      
       getPuntoImpresionSessionService().addPuntoImpresion(value);

       Collection c = getPuntoImpresionSessionService().findPuntoImpresionByImpresora("IMPRESORA1"); 
       PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
       Assert.assertEquals(resultado.getImpresora(),"IMPRESORA1"); 

   }

   @Test (timeout=2000)
   public void findPuntoImpresionByEstado() throws Exception {

 	PuntoImpresionIf value = new PuntoImpresionData();
 
    value.setTipodocumentoId(new Long(1)); 
    value.setCajaId(new Long(1)); 
    value.setSerie("SER"); 
    value.setImpresora("IMPRESORA1"); 
    value.setEstado("A"); 
      
       getPuntoImpresionSessionService().addPuntoImpresion(value);

       Collection c = getPuntoImpresionSessionService().findPuntoImpresionByEstado("A"); 
       PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }



    
    @Test (timeout=2000)
    public void findPuntoImpresionByQuery() throws Exception {
 	
PuntoImpresionIf value = new PuntoImpresionData();
 
value.setTipodocumentoId(new Long(1)); 
value.setCajaId(new Long(1)); 
value.setSerie("SER"); 
value.setImpresora("IMPRESORA1"); 
value.setEstado("A"); 
      
      getPuntoImpresionSessionService().addPuntoImpresion(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("tipodocumentoId",new Long(1)); 
       parametros.put("cajaId",new Long(1)); 
       parametros.put("serie","SER"); 
       parametros.put("impresora","IMPRESORA1"); 
       parametros.put("estado","A"); 

     Collection c = getPuntoImpresionSessionService().findPuntoImpresionByQuery(parametros); 
     PuntoImpresionIf resultado = (PuntoImpresionIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getCajaId(),new Long(1)); 
     Assert.assertEquals(resultado.getSerie(),"SER"); 
     Assert.assertEquals(resultado.getImpresora(),"IMPRESORA1"); 
     Assert.assertEquals(resultado.getEstado(),"A"); 


    }

public static PuntoImpresionSessionService getPuntoImpresionSessionService() {
		try {
			return (PuntoImpresionSessionService) ServiceLocator
					.getService(ServiceLocator.PUNTOIMPRESIONSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PuntoImpresionSessionTest.class);

 }

}
