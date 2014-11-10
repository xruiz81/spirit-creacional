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

import com.spirit.general.entity.OficinaData;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class OficinaSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(2)
   //nombre=>VARCHAR2(30)
   //empresaId=>NUMBER(10)
   //ciudadId=>NUMBER(10)
   //administradorId=>NUMBER(10)
   //direccion=>VARCHAR2(30)
   //telefono=>VARCHAR2(10)
   //fax=>VARCHAR2(10)
   //preimpresoSerie=>VARCHAR2(3)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/oficina.xml");
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
   public void addOficina() throws Exception {
     OficinaIf value = new OficinaData();
 
       value.setCodigo("CO"); 
       value.setNombre("NOMBRE1"); 
       value.setEmpresaId(new Long(1)); 
       value.setCiudadId(new Long(1)); 
       value.setAdministradorId(new Long(1)); 
       value.setDireccion("DIRECCION1"); 
       value.setTelefono("555"); 
       value.setFax("444"); 
       value.setPreimpresoSerie("PRE"); 
      
     OficinaIf resultado = getOficinaSessionService().addOficina(value);
           
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 
       Assert.assertEquals(resultado.getAdministradorId(),new Long(1)); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
       Assert.assertEquals(resultado.getTelefono(),"555"); 
       Assert.assertEquals(resultado.getFax(),"444"); 
       Assert.assertEquals(resultado.getPreimpresoSerie(),"PRE"); 
		
   }

  @Test (timeout=2000)
  public void saveOficina() throws Exception {
      
    OficinaIf value = getOficinaSessionService().getOficina(new Long(1));
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
     getOficinaSessionService().saveOficina(value);

     OficinaIf resultado = getOficinaSessionService().getOficina(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCodigo(),"CO"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 
       Assert.assertEquals(resultado.getAdministradorId(),new Long(1)); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
       Assert.assertEquals(resultado.getTelefono(),"555"); 
       Assert.assertEquals(resultado.getFax(),"444"); 
       Assert.assertEquals(resultado.getPreimpresoSerie(),"PRE"); 
		
   }


 @Test (timeout=2000)
 public void deleteOficina() throws Exception {
      getOficinaSessionService().deleteOficina(new Long(3));
      OficinaIf resultado = getOficinaSessionService().getOficina(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findOficinaById() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaById(new Long(1)); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOficinaByCodigo() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE");  
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByCodigo("CO"); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CO"); 

   }

   @Test (timeout=2000)
   public void findOficinaByNombre() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByNombre("NOMBRE1"); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findOficinaByEmpresaId() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByEmpresaId(new Long(1)); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOficinaByCiudadId() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByCiudadId(new Long(1)); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOficinaByAdministradorId() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByAdministradorId(new Long(1)); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getAdministradorId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findOficinaByDireccion() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByDireccion("DIRECCION1"); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 

   }

   @Test (timeout=2000)
   public void findOficinaByTelefono() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByTelefono("555"); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTelefono(),"555"); 

   }

   @Test (timeout=2000)
   public void findOficinaByFax() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByFax("444"); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFax(),"444"); 

   }

   @Test (timeout=2000)
   public void findOficinaByPreimpresoSerie() throws Exception {

 	OficinaIf value = new OficinaData();
 
    value.setCodigo("CO"); 
    value.setNombre("NOMBRE1"); 
    value.setEmpresaId(new Long(1)); 
    value.setCiudadId(new Long(1)); 
    value.setAdministradorId(new Long(1)); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("555"); 
    value.setFax("444"); 
    value.setPreimpresoSerie("PRE"); 
      
       getOficinaSessionService().addOficina(value);

       Collection c = getOficinaSessionService().findOficinaByPreimpresoSerie("PRE"); 
       OficinaIf resultado = (OficinaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPreimpresoSerie(),"PRE"); 

   }


    @Test (timeout=2000)
    public void findOficinaByQuery() throws Exception {
 	
OficinaIf value = new OficinaData();
 
value.setCodigo("CO"); 
value.setNombre("NOMBRE1"); 
value.setEmpresaId(new Long(1)); 
value.setCiudadId(new Long(1)); 
value.setAdministradorId(new Long(1)); 
value.setDireccion("DIRECCION1"); 
value.setTelefono("555"); 
value.setFax("444"); 
value.setPreimpresoSerie("PRE"); 
      
      getOficinaSessionService().addOficina(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","CO"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("empresaId",new Long(1)); 
       parametros.put("ciudadId",new Long(1)); 
       parametros.put("administradorId",new Long(1)); 
       parametros.put("direccion","DIRECCION1"); 
       parametros.put("telefono","555"); 
       parametros.put("fax","444"); 
       parametros.put("preimpresoSerie","PRE"); 

     Collection c = getOficinaSessionService().findOficinaByQuery(parametros); 
     OficinaIf resultado = (OficinaIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"CO"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
     Assert.assertEquals(resultado.getCiudadId(),new Long(1)); 
     Assert.assertEquals(resultado.getAdministradorId(),new Long(1)); 
     Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
     Assert.assertEquals(resultado.getTelefono(),"555"); 
     Assert.assertEquals(resultado.getFax(),"444"); 
     Assert.assertEquals(resultado.getPreimpresoSerie(),"PRE"); 


    }

public static OficinaSessionService getOficinaSessionService() {
		try {
			return (OficinaSessionService) ServiceLocator
					.getService(ServiceLocator.OFICINASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(OficinaSessionTest.class);

 }

}
