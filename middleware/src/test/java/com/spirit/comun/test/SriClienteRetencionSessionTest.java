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

import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.sri.entity.SriClienteRetencionData;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.session.SriClienteRetencionSessionService;


public class SriClienteRetencionSessionTest {

	Date now = new Date(System.currentTimeMillis());
   //id=>NUMBER(10)
   //tipoRetencion=>VARCHAR2(1)
   //porcentajeRetencion=>NUMBER(22)
   //fechaInicio=>DATE
   //fechaFin=>DATE
   //estado=>VARCHAR2(1)
   //idCuenta=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/SriClienteRetencion.xml");
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
   public void addSriClienteRetencion() throws Exception {
     SriClienteRetencionIf value = new SriClienteRetencionData();
       
       value.setTipoRetencion("R"); 
       value.setPorcentajeRetencion(new BigDecimal(1)); 
       value.setFechaInicio(now); 
       value.setFechaFin(now); 
       value.setEstado("A"); 
       value.setCuentaId(new Long(1)); 
      
     SriClienteRetencionIf resultado = getSriClienteRetencionSessionService().addSriClienteRetencion(value);
           
       Assert.assertEquals(resultado.getTipoRetencion(),"R"); 
       Assert.assertEquals(resultado.getPorcentajeRetencion(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getFechaInicio(),now); 
       Assert.assertEquals(resultado.getFechaFin(),now); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveSriClienteRetencion() throws Exception {
      
    SriClienteRetencionIf value = getSriClienteRetencionSessionService().getSriClienteRetencion(new Long(1));
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
     getSriClienteRetencionSessionService().saveSriClienteRetencion(value);

     SriClienteRetencionIf resultado = getSriClienteRetencionSessionService().getSriClienteRetencion(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getTipoRetencion(),"R"); 
       Assert.assertEquals(resultado.getPorcentajeRetencion(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getFechaInicio(),now); 
       Assert.assertEquals(resultado.getFechaFin(),now); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getCuentaId(),new Long(1));
		
   }


 @Test (timeout=2000)
 public void deleteSriClienteRetencion() throws Exception {
      getSriClienteRetencionSessionService().deleteSriClienteRetencion(new Long(3));
      SriClienteRetencionIf resultado = getSriClienteRetencionSessionService().getSriClienteRetencion(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findSriClienteRetencionById() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionById(new Long(1)); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findSriClienteRetencionByTipoRetencion() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByTipoRetencion("R"); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoRetencion(),"R"); 

   }

   @Test (timeout=2000)
   public void findSriClienteRetencionByPorcentajeRetencion() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByPorcentajeRetencion(new BigDecimal(1)); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getPorcentajeRetencion(),"PORCENTAJE_RETENCION1"); 

   }

   @Test (timeout=2000)
   public void findSriClienteRetencionByFechaInicio() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByFechaInicio(now); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaInicio(),"FECHA_INICIO1"); 

   }

   @Test (timeout=2000)
   public void findSriClienteRetencionByFechaFin() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByFechaFin(now); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaFin(),"FECHA_FIN1"); 

   }

   @Test (timeout=2000)
   public void findSriClienteRetencionByEstado() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByEstado("A"); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }

   @Test (timeout=2000)
   public void findSriClienteRetencionByIdCuenta() throws Exception {

 	SriClienteRetencionIf value = new SriClienteRetencionData();
 
    value.setTipoRetencion("R"); 
    value.setPorcentajeRetencion(new BigDecimal(1)); 
    value.setFechaInicio(now); 
    value.setFechaFin(now); 
    value.setEstado("A"); 
    value.setCuentaId(new Long(1)); 
      
       getSriClienteRetencionSessionService().addSriClienteRetencion(value);

       Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByCuentaId(new Long(1)); 
       SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
       Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findSriClienteRetencionByQuery() throws Exception {
 	
SriClienteRetencionIf value = new SriClienteRetencionData();
 
value.setTipoRetencion("R"); 
value.setPorcentajeRetencion(new BigDecimal(1)); 
value.setFechaInicio(now); 
value.setFechaFin(now); 
value.setEstado("A"); 
value.setCuentaId(new Long(1));        
      
      getSriClienteRetencionSessionService().addSriClienteRetencion(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("tipoRetencion","TIPO_RETENCION1"); 
       parametros.put("porcentajeRetencion","PORCENTAJE_RETENCION1"); 
       parametros.put("fechaInicio","FECHA_INICIO1"); 
       parametros.put("fechaFin","FECHA_FIN1"); 
       parametros.put("estado","ESTADO1"); 
       parametros.put("cuentaId","ID_CUENTA1"); 

     Collection c = getSriClienteRetencionSessionService().findSriClienteRetencionByQuery(parametros); 
     SriClienteRetencionIf resultado = (SriClienteRetencionIf)c.iterator().next();
      
      
     Assert.assertEquals(resultado.getTipoRetencion(),"R"); 
     Assert.assertEquals(resultado.getPorcentajeRetencion(),new BigDecimal(1)); 
     Assert.assertEquals(resultado.getFechaInicio(),now); 
     Assert.assertEquals(resultado.getFechaFin(),now); 
     Assert.assertEquals(resultado.getEstado(),"A"); 
     Assert.assertEquals(resultado.getCuentaId(),new Long(1));


    }

public static SriClienteRetencionSessionService getSriClienteRetencionSessionService() {
		try {
			return (SriClienteRetencionSessionService) ServiceLocator.getService(ServiceLocator.SRICLIENTERETENCIONSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(SriClienteRetencionSessionTest.class);

 }


 


}
