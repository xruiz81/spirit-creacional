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

import com.spirit.general.entity.EmpleadoData;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.session.EmpleadoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class EmpleadoSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(10)
   //nombres=>VARCHAR2(20)
   //apellidos=>VARCHAR2(20)
   //tipoidentificacionId=>NUMBER(10)
   //identificacion=>VARCHAR2(13)
   //empresaId=>NUMBER(10)
   //profesion=>VARCHAR2(30)
   //direccionDomicilio=>VARCHAR2(40)
   //telefonoDomicilio=>VARCHAR2(10)
   //celular=>VARCHAR2(10)
   //emailOficina=>VARCHAR2(30)
   //departamentoId=>NUMBER(10)
   //jefeId=>NUMBER(10)
   //tipoempleadoId=>NUMBER(10)
   //extensionOficina=>VARCHAR2(4)
   //nivel=>NUMBER(3)
   //estado=>VARCHAR2(1)
   //oficinaId=>NUMBER(10)
   //tipocontratoId=>NUMBER(10)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/empleado.xml");
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
   public void addEmpleado() throws Exception {
     EmpleadoIf value = new EmpleadoData();
 
       value.setCodigo("COD"); 
       value.setNombres("NOMBRES1"); 
       value.setApellidos("APELLIDOS1"); 
       value.setTipoidentificacionId(new Long(1)); 
       value.setIdentificacion("IDENTIF"); 
       value.setEmpresaId(new Long(1)); 
       value.setProfesion("PROFESION1"); 
       value.setDireccionDomicilio("DIRECCION"); 
       value.setTelefonoDomicilio("555"); 
       value.setCelular("999"); 
       value.setEmailOficina("EMAIL_OFICINA1"); 
       value.setDepartamentoId(new Long(1)); 
       value.setJefeId(new Long(1)); 
       value.setTipoempleadoId(new Long(1)); 
       value.setExtensionOficina("EXT"); 
       value.setNivel(new Integer(3)); 
       value.setEstado("A"); 
       value.setOficinaId(new Long(1)); 
       value.setTipocontratoId(new Long(1)); 
      
     EmpleadoIf resultado = getEmpleadoSessionService().addEmpleado(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombres(),"NOMBRES1"); 
       Assert.assertEquals(resultado.getApellidos(),"APELLIDOS1"); 
       Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
       Assert.assertEquals(resultado.getIdentificacion(),"IDENTIF"); 
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
       Assert.assertEquals(resultado.getProfesion(),"PROFESION1"); 
       Assert.assertEquals(resultado.getDireccionDomicilio(),"DIRECCION"); 
       Assert.assertEquals(resultado.getTelefonoDomicilio(),"555"); 
       Assert.assertEquals(resultado.getCelular(),"999"); 
       Assert.assertEquals(resultado.getEmailOficina(),"EMAIL_OFICINA1"); 
       Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getJefeId(),new Long(1)); 
       Assert.assertEquals(resultado.getTipoempleadoId(),new Long(1)); 
       Assert.assertEquals(resultado.getExtensionOficina(),"EXT"); 
       Assert.assertEquals(resultado.getNivel(),new Integer(3)); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getTipocontratoId(),new Long(1)); 
		
   }

  @Test (timeout=2000)
  public void saveEmpleado() throws Exception {
      
    EmpleadoIf value = getEmpleadoSessionService().getEmpleado(new Long(1));
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
     getEmpleadoSessionService().saveEmpleado(value);

     EmpleadoIf resultado = getEmpleadoSessionService().getEmpleado(new Long(1));

     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombres(),"NOMBRES1"); 
     Assert.assertEquals(resultado.getApellidos(),"APELLIDOS1"); 
     Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
     Assert.assertEquals(resultado.getIdentificacion(),"IDENTIF"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
     Assert.assertEquals(resultado.getProfesion(),"PROFESION1"); 
     Assert.assertEquals(resultado.getDireccionDomicilio(),"DIRECCION"); 
     Assert.assertEquals(resultado.getTelefonoDomicilio(),"555"); 
     Assert.assertEquals(resultado.getCelular(),"999"); 
     Assert.assertEquals(resultado.getEmailOficina(),"EMAIL_OFICINA1"); 
     Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getJefeId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipoempleadoId(),new Long(1)); 
     Assert.assertEquals(resultado.getExtensionOficina(),"EXT"); 
     Assert.assertEquals(resultado.getNivel(),new Integer(3)); 
     Assert.assertEquals(resultado.getEstado(),"A"); 
     Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipocontratoId(),new Long(1));
		
   }


 @Test (timeout=2000)
 public void deleteEmpleado() throws Exception {
      getEmpleadoSessionService().deleteEmpleado(new Long(3));
      EmpleadoIf resultado = getEmpleadoSessionService().getEmpleado(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findEmpleadoById() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoById(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByCodigo() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1));  
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByCodigo("COD"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByNombres() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByNombres("NOMBRES1"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombres(),"NOMBRES1"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByApellidos() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByApellidos("APELLIDOS1"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getApellidos(),"APELLIDOS1"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByTipoidentificacionId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByTipoidentificacionId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByIdentificacion() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByIdentificacion("IDENTIF"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getIdentificacion(),"IDENTIF"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByEmpresaId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByEmpresaId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByProfesion() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByProfesion("PROFESION1"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getProfesion(),"PROFESION1"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByDireccionDomicilio() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByDireccionDomicilio("DIRECCION"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getDireccionDomicilio(),"DIRECCION"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByTelefonoDomicilio() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByTelefonoDomicilio("555"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTelefonoDomicilio(),"555"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByCelular() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByCelular("999"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCelular(),"999"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByEmailOficina() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByEmailOficina("EMAIL_OFICINA1"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmailOficina(),"EMAIL_OFICINA1"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByDepartamentoId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByDepartamentoId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByJefeId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByJefeId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getJefeId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByTipoempleadoId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByTipoempleadoId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoempleadoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByExtensionOficina() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByExtensionOficina("EXT"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getExtensionOficina(),"EXT"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByNivel() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByNivel(new Integer(3)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNivel(),new Integer(3)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByEstado() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByEstado("A"); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByOficinaId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByOficinaId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findEmpleadoByTipocontratoId() throws Exception {

 	EmpleadoIf value = new EmpleadoData();
 
    value.setCodigo("COD"); 
    value.setNombres("NOMBRES1"); 
    value.setApellidos("APELLIDOS1"); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIF"); 
    value.setEmpresaId(new Long(1)); 
    value.setProfesion("PROFESION1"); 
    value.setDireccionDomicilio("DIRECCION"); 
    value.setTelefonoDomicilio("555"); 
    value.setCelular("999"); 
    value.setEmailOficina("EMAIL_OFICINA1"); 
    value.setDepartamentoId(new Long(1)); 
    value.setJefeId(new Long(1)); 
    value.setTipoempleadoId(new Long(1)); 
    value.setExtensionOficina("EXT"); 
    value.setNivel(new Integer(3)); 
    value.setEstado("A"); 
    value.setOficinaId(new Long(1)); 
    value.setTipocontratoId(new Long(1)); 
      
       getEmpleadoSessionService().addEmpleado(value);

       Collection c = getEmpleadoSessionService().findEmpleadoByTipocontratoId(new Long(1)); 
       EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipocontratoId(),new Long(1)); 

   }



    
    @Test (timeout=2000)
    public void findEmpleadoByQuery() throws Exception {
 	
EmpleadoIf value = new EmpleadoData();
 
value.setCodigo("COD"); 
value.setNombres("NOMBRES1"); 
value.setApellidos("APELLIDOS1"); 
value.setTipoidentificacionId(new Long(1)); 
value.setIdentificacion("IDENTIF"); 
value.setEmpresaId(new Long(1)); 
value.setProfesion("PROFESION1"); 
value.setDireccionDomicilio("DIRECCION"); 
value.setTelefonoDomicilio("555"); 
value.setCelular("999"); 
value.setEmailOficina("EMAIL_OFICINA1"); 
value.setDepartamentoId(new Long(1)); 
value.setJefeId(new Long(1)); 
value.setTipoempleadoId(new Long(1)); 
value.setExtensionOficina("EXT"); 
value.setNivel(new Integer(3)); 
value.setEstado("A"); 
value.setOficinaId(new Long(1)); 
value.setTipocontratoId(new Long(1)); 
      
      getEmpleadoSessionService().addEmpleado(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("codigo","COD"); 
       parametros.put("nombres","NOMBRES1"); 
       parametros.put("apellidos","APELLIDOS1"); 
       parametros.put("tipoidentificacionId",new Long(1)); 
       parametros.put("identificacion","IDENTIF"); 
       parametros.put("empresaId",new Long(1)); 
       parametros.put("profesion","PROFESION1"); 
       parametros.put("direccionDomicilio","DIRECCION"); 
       parametros.put("telefonoDomicilio","555"); 
       parametros.put("celular","999"); 
       parametros.put("emailOficina","EMAIL_OFICINA1"); 
       parametros.put("departamentoId",new Long(1)); 
       parametros.put("jefeId",new Long(1)); 
       parametros.put("tipoempleadoId",new Long(1)); 
       parametros.put("extensionOficina","EXT"); 
       parametros.put("nivel",new Integer(3)); 
       parametros.put("estado","A"); 
       parametros.put("oficinaId",new Long(1)); 
       parametros.put("tipocontratoId",new Long(1)); 

     Collection c = getEmpleadoSessionService().findEmpleadoByQuery(parametros); 
     EmpleadoIf resultado = (EmpleadoIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombres(),"NOMBRES1"); 
     Assert.assertEquals(resultado.getApellidos(),"APELLIDOS1"); 
     Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
     Assert.assertEquals(resultado.getIdentificacion(),"IDENTIF"); 
     Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
     Assert.assertEquals(resultado.getProfesion(),"PROFESION1"); 
     Assert.assertEquals(resultado.getDireccionDomicilio(),"DIRECCION"); 
     Assert.assertEquals(resultado.getTelefonoDomicilio(),"555"); 
     Assert.assertEquals(resultado.getCelular(),"999"); 
     Assert.assertEquals(resultado.getEmailOficina(),"EMAIL_OFICINA1"); 
     Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getJefeId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipoempleadoId(),new Long(1)); 
     Assert.assertEquals(resultado.getExtensionOficina(),"EXT"); 
     Assert.assertEquals(resultado.getNivel(),new Integer(3)); 
     Assert.assertEquals(resultado.getEstado(),"A"); 
     Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipocontratoId(),new Long(1));


    }

public static EmpleadoSessionService getEmpleadoSessionService() {
		try {
			return (EmpleadoSessionService) ServiceLocator
					.getService(ServiceLocator.EMPLEADOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(EmpleadoSessionTest.class);

 }

}
