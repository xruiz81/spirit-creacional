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

import com.spirit.nomina.entity.ContratoRubroData;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.session.ContratoRubroSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class ContratoRubroSessionTest {

	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //fechaFin=>DATE
   //estado=>VARCHAR2(1)
   //fechaInicio=>DATE
   //valor=>NUMBER(22)
   //contratoId=>NUMBER(10)
   //rubroId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/contrato_rubro.xml");
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
   public void addContratoRubro() throws Exception {
     ContratoRubroIf value = new ContratoRubroData();
       
       value.setFechaFin(now); 
       value.setEstado("E"); 
       value.setFechaInicio(now); 
       value.setValor(new BigDecimal(22)); 
       value.setContratoId(new Long(1)); 
       value.setRubroId(new Long(1)); 
      
     ContratoRubroIf resultado = getContratoRubroSessionService().addContratoRubro(value);
           
       Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
       Assert.assertEquals(resultado.getEstado(),"E"); 
       Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getContratoId(),new Long(1)); 
       Assert.assertEquals(resultado.getRubroId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveContratoRubro() throws Exception {
      
    ContratoRubroIf value = getContratoRubroSessionService().getContratoRubro(new Long(1));
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
     getContratoRubroSessionService().saveContratoRubro(value);

     ContratoRubroIf resultado = getContratoRubroSessionService().getContratoRubro(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
       Assert.assertEquals(resultado.getEstado(),"E"); 
       Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getContratoId(),new Long(1)); 
       Assert.assertEquals(resultado.getRubroId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteContratoRubro() throws Exception {
      getContratoRubroSessionService().deleteContratoRubro(new Long(2));
      ContratoRubroIf resultado = getContratoRubroSessionService().getContratoRubro(new Long(2));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findContratoRubroById() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroById(new Long(1)); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findContratoRubroByFechaFin() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroByFechaFin(now); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findContratoRubroByEstado() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroByEstado("E"); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"E"); 

   }

   @Test (timeout=2000)
   public void findContratoRubroByFechaInicio() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroByFechaInicio(now); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findContratoRubroByValor() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroByValor(new BigDecimal(22)); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findContratoRubroByContratoId() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroByContratoId(new Long(1)); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getContratoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findContratoRubroByRubroId() throws Exception {

 	ContratoRubroIf value = new ContratoRubroData();
 
    value.setFechaFin(now); 
    value.setEstado("E"); 
    value.setFechaInicio(now); 
    value.setValor(new BigDecimal(22)); 
    value.setContratoId(new Long(1)); 
    value.setRubroId(new Long(1)); 
      
       getContratoRubroSessionService().addContratoRubro(value);

       Collection c = getContratoRubroSessionService().findContratoRubroByRubroId(new Long(1)); 
       ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
       Assert.assertEquals(resultado.getRubroId(),new Long(1)); 

   }

    
    @Test (timeout=2000)
    public void findContratoRubroByQuery() throws Exception {
 	
ContratoRubroIf value = new ContratoRubroData();
 
value.setFechaFin(now); 
value.setEstado("E"); 
value.setFechaInicio(now); 
value.setValor(new BigDecimal(22)); 
value.setContratoId(new Long(1)); 
value.setRubroId(new Long(1)); 
      
      getContratoRubroSessionService().addContratoRubro(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("fechaFin",now); 
       parametros.put("estado","E"); 
       parametros.put("fechaInicio",now); 
       parametros.put("valor",new BigDecimal(22)); 
       parametros.put("contratoId",new Long(1)); 
       parametros.put("rubroId",new Long(1)); 

     Collection c = getContratoRubroSessionService().findContratoRubroByQuery(parametros); 
     ContratoRubroIf resultado = (ContratoRubroIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getFechaFin().toString(),now.toString()); 
     Assert.assertEquals(resultado.getEstado(),"E"); 
     Assert.assertEquals(resultado.getFechaInicio().toString(),now.toString()); 
     Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getContratoId(),new Long(1)); 
     Assert.assertEquals(resultado.getRubroId(),new Long(1)); 
    }

public static ContratoRubroSessionService getContratoRubroSessionService() {
		try {
			return (ContratoRubroSessionService) ServiceLocator
					.getService(ServiceLocator.CONTRATORUBROSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ContratoRubroSessionTest.class);

 }

}
