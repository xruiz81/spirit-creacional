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

import com.spirit.general.entity.UsuarioData;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.UsuarioSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class UsuarioSessionTest {


   //id=>NUMBER(10)
   //usuario=>VARCHAR2(10)
   //clave=>VARCHAR2(200)
   //tipousuario=>VARCHAR2(5)
   //empleadoId=>NUMBER(10)
   //empresaId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/usuario.xml");
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
   public void addUsuario() throws Exception {
     UsuarioIf value = new UsuarioData();
 
      
       value.setUsuario("USUARIO1"); 
       value.setClave("CLAVE1"); 
       value.setTipousuario("TIPO1"); 
       value.setEmpleadoId(new Long(1)); 
       value.setEmpresaId(new Long(1)); 
      
     UsuarioIf resultado = getUsuarioSessionService().addUsuario(value);
           
       Assert.assertEquals(resultado.getUsuario(),"USUARIO1"); 
       Assert.assertEquals(resultado.getClave(),"CLAVE1"); 
       Assert.assertEquals(resultado.getTipousuario(),"TIPO1"); 
       Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveUsuario() throws Exception {
      
    UsuarioIf value = getUsuarioSessionService().getUsuario(new Long(1));
 
          
       value.setUsuario("USUARIO1"); 
       value.setClave("CLAVE1"); 
       value.setTipousuario("TIPO1"); 
       value.setEmpleadoId(new Long(1)); 
       value.setEmpresaId(new Long(1)); 
      
     getUsuarioSessionService().saveUsuario(value);

     UsuarioIf resultado = getUsuarioSessionService().getUsuario(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getUsuario(),"USUARIO1"); 
       Assert.assertEquals(resultado.getClave(),"CLAVE1"); 
       Assert.assertEquals(resultado.getTipousuario(),"TIPO1"); 
       Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
   }


 @Test (timeout=2000)
 public void deleteUsuario() throws Exception {
      getUsuarioSessionService().deleteUsuario(new Long(3));
      UsuarioIf resultado = getUsuarioSessionService().getUsuario(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findUsuarioById() throws Exception {

       Collection c = getUsuarioSessionService().findUsuarioById(new Long(1)); 
       UsuarioIf resultado = (UsuarioIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioByUsuario() throws Exception {

 	UsuarioIf value = new UsuarioData();
 
      
 	value.setUsuario("USUARIOx"); 
    value.setClave("CLAVE1"); 
    value.setTipousuario("TIPO1"); 
    value.setEmpleadoId(new Long(1)); 
    value.setEmpresaId(new Long(1)); 
      
       getUsuarioSessionService().addUsuario(value);

       Collection c = getUsuarioSessionService().findUsuarioByUsuario("USUARIOx"); 
       UsuarioIf resultado = (UsuarioIf)c.iterator().next();
       Assert.assertEquals(resultado.getUsuario(),"USUARIOx"); 

   }

   @Test (timeout=2000)
   public void findUsuarioByClave() throws Exception {

 	UsuarioIf value = new UsuarioData();
 
      
 	value.setUsuario("USUARIO1"); 
    value.setClave("CLAVEx"); 
    value.setTipousuario("TIPO1"); 
    value.setEmpleadoId(new Long(1)); 
    value.setEmpresaId(new Long(1)); 
      
       getUsuarioSessionService().addUsuario(value);

       Collection c = getUsuarioSessionService().findUsuarioByClave("CLAVEx"); 
       UsuarioIf resultado = (UsuarioIf)c.iterator().next();
       Assert.assertEquals(resultado.getClave(),"CLAVEx"); 

   }

   @Test (timeout=2000)
   public void findUsuarioByTipousuario() throws Exception {

 	UsuarioIf value = new UsuarioData();
 
      
 	value.setUsuario("USUARIO1"); 
    value.setClave("CLAVE1"); 
    value.setTipousuario("TIPOx"); 
    value.setEmpleadoId(new Long(1)); 
    value.setEmpresaId(new Long(1)); 
      
       getUsuarioSessionService().addUsuario(value);

       Collection c = getUsuarioSessionService().findUsuarioByTipousuario("TIPOx"); 
       UsuarioIf resultado = (UsuarioIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipousuario(),"TIPOx"); 

   }

   @Test (timeout=2000)
   public void findUsuarioByEmpleadoId() throws Exception {

 	UsuarioIf value = new UsuarioData();
 
      
 	value.setUsuario("USUARIO1"); 
    value.setClave("CLAVE1"); 
    value.setTipousuario("TIPO1"); 
    value.setEmpleadoId(new Long(1)); 
    value.setEmpresaId(new Long(1)); 
      
       getUsuarioSessionService().addUsuario(value);

       Collection c = getUsuarioSessionService().findUsuarioByEmpleadoId(new Long(1)); 
       UsuarioIf resultado = (UsuarioIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findUsuarioByEmpresaId() throws Exception {

 	UsuarioIf value = new UsuarioData();
 
      
 	value.setUsuario("USUARIO1"); 
    value.setClave("CLAVE1"); 
    value.setTipousuario("TIPO1"); 
    value.setEmpleadoId(new Long(1)); 
    value.setEmpresaId(new Long(1));  
      
       getUsuarioSessionService().addUsuario(value);

       Collection c = getUsuarioSessionService().findUsuarioByEmpresaId(new Long(1)); 
       UsuarioIf resultado = (UsuarioIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findUsuarioByQuery() throws Exception {
 	
    	UsuarioIf value = new UsuarioData();
 
       
    	value.setUsuario("USUARIOy"); 
        value.setClave("CLAVEy"); 
        value.setTipousuario("TIPOy"); 
        value.setEmpleadoId(new Long(1)); 
        value.setEmpresaId(new Long(1));  
      
      getUsuarioSessionService().addUsuario(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("usuario","USUARIOy"); 
       parametros.put("clave","CLAVEy"); 
       parametros.put("tipousuario","TIPOy"); 
       parametros.put("empleadoId",new Long(1)); 
       parametros.put("empresaId",new Long(1)); 

     Collection c = getUsuarioSessionService().findUsuarioByQuery(parametros); 
     UsuarioIf resultado = (UsuarioIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getUsuario(),"USUARIOy"); 
      Assert.assertEquals(resultado.getClave(),"CLAVEy"); 
      Assert.assertEquals(resultado.getTipousuario(),"TIPOy"); 
      Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
      Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 


    }

public static UsuarioSessionService getUsuarioSessionService() {
		try {
			return (UsuarioSessionService) ServiceLocator
					.getService(ServiceLocator.USUARIOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(UsuarioSessionTest.class);

 }


 


}
