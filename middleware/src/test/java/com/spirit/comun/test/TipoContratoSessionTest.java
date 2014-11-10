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

import com.spirit.nomina.entity.TipoContratoData;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.session.TipoContratoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoContratoSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(4)
   //nombre=>VARCHAR2(20)
   //empresaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_contrato.xml");
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
   public void addTipoContrato() throws Exception {
     TipoContratoIf value = new TipoContratoData();
 
       value.setCodigo("COD"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
      
     TipoContratoIf resultado = getTipoContratoSessionService().addTipoContrato(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveTipoContrato() throws Exception {
      
    TipoContratoIf value = getTipoContratoSessionService().getTipoContrato(new Long(1));
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
     getTipoContratoSessionService().saveTipoContrato(value);

     TipoContratoIf resultado = getTipoContratoSessionService().getTipoContrato(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteTipoContrato() throws Exception {
      getTipoContratoSessionService().deleteTipoContrato(new Long(2));
      TipoContratoIf resultado = getTipoContratoSessionService().getTipoContrato(new Long(2));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoContratoById() throws Exception {

 	TipoContratoIf value = new TipoContratoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoContratoSessionService().addTipoContrato(value);

       Collection c = getTipoContratoSessionService().findTipoContratoById(new Long(1)); 
       TipoContratoIf resultado = (TipoContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findTipoContratoByCodigo() throws Exception {

 	TipoContratoIf value = new TipoContratoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoContratoSessionService().addTipoContrato(value);

       Collection c = getTipoContratoSessionService().findTipoContratoByCodigo("COD"); 
       TipoContratoIf resultado = (TipoContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findTipoContratoByNombre() throws Exception {

 	TipoContratoIf value = new TipoContratoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoContratoSessionService().addTipoContrato(value);

       Collection c = getTipoContratoSessionService().findTipoContratoByNombre("NOMBRE1"); 
       TipoContratoIf resultado = (TipoContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findTipoContratoByEmpresaId() throws Exception {

 	TipoContratoIf value = new TipoContratoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoContratoSessionService().addTipoContrato(value);

       Collection c = getTipoContratoSessionService().findTipoContratoByEmpresaId(new Long(1)); 
       TipoContratoIf resultado = (TipoContratoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findTipoContratoByQuery() throws Exception {
 	
TipoContratoIf value = new TipoContratoData();
 
value.setCodigo("COD"); 
value.setNombre("NOMBRE1"); 
value.setEmpresaId(new Long(1)); 
      
      getTipoContratoSessionService().addTipoContrato(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       parametros.put("codigo","COD"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("empresaId",new Long(1)); 

     Collection c = getTipoContratoSessionService().findTipoContratoByQuery(parametros); 
     TipoContratoIf resultado = (TipoContratoIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
    }

public static TipoContratoSessionService getTipoContratoSessionService() {
		try {
			return (TipoContratoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOCONTRATOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoContratoSessionTest.class);

 }

}
