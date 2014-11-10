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

import com.spirit.general.entity.UsuarioDocumentoData;
import com.spirit.general.entity.UsuarioDocumentoIf;
import com.spirit.general.session.UsuarioDocumentoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class UsuarioDocumentoSessionTest {


   //id=>NUMBER(10)
   //usuarioId=>NUMBER(10)
   //documentoId=>NUMBER(10)
   //permisoImpresion=>VARCHAR2(1)
   //permisoRegistro=>VARCHAR2(1)
   //permisoBorrado=>VARCHAR2(1)
   //permisoAutorizar=>VARCHAR2(1)
   //estado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/usuario_documento.xml");
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
   public void addUsuarioDocumento() throws Exception {
     UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
       value.setUsuarioId(new Long(1)); 
       value.setDocumentoId(new Long(1)); 
       value.setPermisoImpresion("P"); 
       value.setPermisoRegistro("P"); 
       value.setPermisoBorrado("P"); 
       value.setPermisoAutorizar("P"); 
       value.setEstado("E"); 
      
     UsuarioDocumentoIf resultado = getUsuarioDocumentoSessionService().addUsuarioDocumento(value);
           
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getPermisoImpresion(),"P"); 
       Assert.assertEquals(resultado.getPermisoRegistro(),"P"); 
       Assert.assertEquals(resultado.getPermisoBorrado(),"P"); 
       Assert.assertEquals(resultado.getPermisoAutorizar(),"P"); 
       Assert.assertEquals(resultado.getEstado(),"E"); 
		
   }

  @Test (timeout=2000)
  public void saveUsuarioDocumento() throws Exception {
      
    UsuarioDocumentoIf value = getUsuarioDocumentoSessionService().getUsuarioDocumento(new Long(1));
 
          
       value.setUsuarioId(new Long(1)); 
       value.setDocumentoId(new Long(1)); 
       value.setPermisoImpresion("A"); 
       value.setPermisoRegistro("A"); 
       value.setPermisoBorrado("A"); 
       value.setPermisoAutorizar("A"); 
       value.setEstado("A"); 
      
     getUsuarioDocumentoSessionService().saveUsuarioDocumento(value);

     UsuarioDocumentoIf resultado = getUsuarioDocumentoSessionService().getUsuarioDocumento(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getPermisoImpresion(),"A"); 
       Assert.assertEquals(resultado.getPermisoRegistro(),"A"); 
       Assert.assertEquals(resultado.getPermisoBorrado(),"A"); 
       Assert.assertEquals(resultado.getPermisoAutorizar(),"A"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }

 @Test (timeout=2000)
 public void deleteUsuarioDocumento() throws Exception {
      getUsuarioDocumentoSessionService().deleteUsuarioDocumento(new Long(3));
      UsuarioDocumentoIf resultado = getUsuarioDocumentoSessionService().getUsuarioDocumento(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findUsuarioDocumentoById() throws Exception {

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoById(new Long(1)); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByUsuarioId() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("A"); 
    value.setPermisoRegistro("A"); 
    value.setPermisoBorrado("A"); 
    value.setPermisoAutorizar("A"); 
    value.setEstado("A");
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByUsuarioId(new Long(1)); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByDocumentoId() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("A"); 
    value.setPermisoRegistro("A"); 
    value.setPermisoBorrado("A"); 
    value.setPermisoAutorizar("A"); 
    value.setEstado("A");
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByDocumentoId(new Long(1)); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByPermisoImpresion() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("x"); 
    value.setPermisoRegistro("A"); 
    value.setPermisoBorrado("A"); 
    value.setPermisoAutorizar("A"); 
    value.setEstado("A"); 
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByPermisoImpresion("x"); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPermisoImpresion(),"x"); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByPermisoRegistro() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("A"); 
    value.setPermisoRegistro("x"); 
    value.setPermisoBorrado("A"); 
    value.setPermisoAutorizar("A"); 
    value.setEstado("A");
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByPermisoRegistro("x"); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPermisoRegistro(),"x"); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByPermisoBorrado() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("A"); 
    value.setPermisoRegistro("A"); 
    value.setPermisoBorrado("x"); 
    value.setPermisoAutorizar("A"); 
    value.setEstado("A"); 
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByPermisoBorrado("x"); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPermisoBorrado(),"x"); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByPermisoAutorizar() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("A"); 
    value.setPermisoRegistro("A"); 
    value.setPermisoBorrado("A"); 
    value.setPermisoAutorizar("x"); 
    value.setEstado("A");
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByPermisoAutorizar("x"); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPermisoAutorizar(),"x"); 

   }

   @Test (timeout=2000)
   public void findUsuarioDocumentoByEstado() throws Exception {

 	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
      
 	value.setUsuarioId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setPermisoImpresion("A"); 
    value.setPermisoRegistro("A"); 
    value.setPermisoBorrado("A"); 
    value.setPermisoAutorizar("A"); 
    value.setEstado("x"); 
      
       getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

       Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByEstado("x"); 
       UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"x"); 
   }
    
    @Test (timeout=2000)
    public void findUsuarioDocumentoByQuery() throws Exception {
 	
    	UsuarioDocumentoIf value = new UsuarioDocumentoData();
 
       
    	value.setUsuarioId(new Long(1)); 
        value.setDocumentoId(new Long(1)); 
        value.setPermisoImpresion("y"); 
        value.setPermisoRegistro("y"); 
        value.setPermisoBorrado("y"); 
        value.setPermisoAutorizar("y"); 
        value.setEstado("y"); 
      
      getUsuarioDocumentoSessionService().addUsuarioDocumento(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("usuarioId",new Long(1)); 
       parametros.put("documentoId",new Long(1)); 
       parametros.put("permisoImpresion","y"); 
       parametros.put("permisoRegistro","y"); 
       parametros.put("permisoBorrado","y"); 
       parametros.put("permisoAutorizar","y"); 
       parametros.put("estado","y"); 

     Collection c = getUsuarioDocumentoSessionService().findUsuarioDocumentoByQuery(parametros); 
     UsuarioDocumentoIf resultado = (UsuarioDocumentoIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
      Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
      Assert.assertEquals(resultado.getPermisoImpresion(),"y"); 
      Assert.assertEquals(resultado.getPermisoRegistro(),"y"); 
      Assert.assertEquals(resultado.getPermisoBorrado(),"y"); 
      Assert.assertEquals(resultado.getPermisoAutorizar(),"y"); 
      Assert.assertEquals(resultado.getEstado(),"y"); 


    }

public static UsuarioDocumentoSessionService getUsuarioDocumentoSessionService() {
		try {
			return (UsuarioDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(UsuarioDocumentoSessionTest.class);

 }


 


}
