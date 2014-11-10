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

import com.spirit.general.entity.TipoParametroData;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.TipoParametroSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoParametroSessionTest {


   //id=>NUMBER(10)
   //codigo=>VARCHAR2(10)
   //nombre=>VARCHAR2(40)
   //tipo=>VARCHAR2(1)
   //mascara=>VARCHAR2(20)
   //empresaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_parametro.xml");
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
   public void addTipoParametro() throws Exception {
     TipoParametroIf value = new TipoParametroData();
       
       value.setCodigo("COD"); 
       value.setNombre("NOMBRE1"); 
       value.setTipo("S"); 
       value.setMascara("DIRECCION"); 
       value.setEmpresaId(new Long(1)); 
      
     TipoParametroIf resultado = getTipoParametroSessionService().addTipoParametro(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getTipo(),"S"); 
       Assert.assertEquals(resultado.getMascara(),"DIRECCION"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveTipoParametro() throws Exception {
      
    TipoParametroIf value = getTipoParametroSessionService().getTipoParametro(new Long(1));
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
     getTipoParametroSessionService().saveTipoParametro(value);

     TipoParametroIf resultado = getTipoParametroSessionService().getTipoParametro(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getTipo(),"S"); 
       Assert.assertEquals(resultado.getMascara(),"DIRECCION"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1));  
		
   }


 @Test (timeout=2000)
 public void deleteTipoParametro() throws Exception {
      getTipoParametroSessionService().deleteTipoParametro(new Long(3));
      TipoParametroIf resultado = getTipoParametroSessionService().getTipoParametro(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findTipoParametroById() throws Exception {

 	TipoParametroIf value = new TipoParametroData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoParametroSessionService().addTipoParametro(value);

       Collection c = getTipoParametroSessionService().findTipoParametroById(new Long(1)); 
       TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findTipoParametroByCodigo() throws Exception {

 	TipoParametroIf value = new TipoParametroData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoParametroSessionService().addTipoParametro(value);

       Collection c = getTipoParametroSessionService().findTipoParametroByCodigo("COD"); 
       TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findTipoParametroByNombre() throws Exception {

 	TipoParametroIf value = new TipoParametroData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoParametroSessionService().addTipoParametro(value);

       Collection c = getTipoParametroSessionService().findTipoParametroByNombre("NOMBRE1"); 
       TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findTipoParametroByTipo() throws Exception {

 	TipoParametroIf value = new TipoParametroData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoParametroSessionService().addTipoParametro(value);

       Collection c = getTipoParametroSessionService().findTipoParametroByTipo("S"); 
       TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipo(),"S"); 

   }

   @Test (timeout=2000)
   public void findTipoParametroByMascara() throws Exception {

 	TipoParametroIf value = new TipoParametroData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoParametroSessionService().addTipoParametro(value);

       Collection c = getTipoParametroSessionService().findTipoParametroByMascara("DIRECCION"); 
       TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
       Assert.assertEquals(resultado.getMascara(),"DIRECCION"); 

   }

   @Test (timeout=2000)
   public void findTipoParametroByEmpresaId() throws Exception {

 	TipoParametroIf value = new TipoParametroData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setTipo("S"); 
    value.setMascara("DIRECCION"); 
    value.setEmpresaId(new Long(1)); 
      
       getTipoParametroSessionService().addTipoParametro(value);

       Collection c = getTipoParametroSessionService().findTipoParametroByEmpresaId(new Long(1)); 
       TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findTipoParametroByQuery() throws Exception {
 	
TipoParametroIf value = new TipoParametroData();
 
value.setCodigo("COD"); 
value.setNombre("NOMBRE1"); 
value.setTipo("S"); 
value.setMascara("DIRECCION"); 
value.setEmpresaId(new Long(1)); 
      
      getTipoParametroSessionService().addTipoParametro(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COD"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("tipo","S"); 
       parametros.put("mascara","DIRECCION"); 
       parametros.put("empresaId",new Long(1)); 

     Collection c = getTipoParametroSessionService().findTipoParametroByQuery(parametros); 
     TipoParametroIf resultado = (TipoParametroIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getTipo(),"S"); 
     Assert.assertEquals(resultado.getMascara(),"DIRECCION"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 


    }

public static TipoParametroSessionService getTipoParametroSessionService() {
		try {
			return (TipoParametroSessionService) ServiceLocator
					.getService(ServiceLocator.TIPOPARAMETROSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoParametroSessionTest.class);

 }

}
