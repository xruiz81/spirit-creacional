package com.spirit.comun.test;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import junit.framework.JUnit4TestAdapter;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.spirit.cartera.entity.*;



public class LogCarteraSessionTest {
/*

   //id=>NUMBER(10)
   //tipo=>VARCHAR2(1)
   //oficinaId=>NUMBER(10)
   //tipodocumentoId=>NUMBER(10)
   //codigo=>VARCHAR2(20)
   //referenciaId=>NUMBER(10)
   //clienteoficinaId=>NUMBER(10)
   //preimpreso=>VARCHAR2(20)
   //usuarioId=>NUMBER(10)
   //vendedorId=>NUMBER(10)
   //monedaId=>NUMBER(10)
   //fechaEmision=>DATE
   //valor=>NUMBER(22)
   //saldo=>NUMBER(22)
   //fechacambioestado=>DATE
   //estado=>VARCHAR2(1)
   //comentario=>VARCHAR2(300)
   //aprobado=>VARCHAR2(1)
   //log=>VARCHAR2(500)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/LogCartera.xml");
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
   public void addLogCartera() throws Exception {
     LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
     LogCarteraIf resultado = getLogCarteraSessionService().addLogCartera(value);
           
       Assert.assertEquals(resultado.getTipo(),"TIPO1"); 
       Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
       Assert.assertEquals(resultado.getTipodocumentoId(),"TIPODOCUMENTO_ID1"); 
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getReferenciaId(),"REFERENCIA_ID1"); 
       Assert.assertEquals(resultado.getClienteoficinaId(),"CLIENTEOFICINA_ID1"); 
       Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
       Assert.assertEquals(resultado.getUsuarioId(),"USUARIO_ID1"); 
       Assert.assertEquals(resultado.getVendedorId(),"VENDEDOR_ID1"); 
       Assert.assertEquals(resultado.getMonedaId(),"MONEDA_ID1"); 
       Assert.assertEquals(resultado.getFechaEmision(),"FECHA_EMISION1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
       Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 
       Assert.assertEquals(resultado.getFechacambioestado(),"FECHACAMBIOESTADO1"); 
       Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
       Assert.assertEquals(resultado.getComentario(),"COMENTARIO1"); 
       Assert.assertEquals(resultado.getAprobado(),"APROBADO1"); 
       Assert.assertEquals(resultado.getLog(),"LOG1"); 
		
   }

  @Test (timeout=2000)
  public void saveLogCartera() throws Exception {
      
    LogCarteraIf value = getLogCarteraSessionService().getLogCartera(new Long(1));
 
          
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
     getLogCarteraSessionService().saveLogCartera(value);

     LogCarteraIf resultado = getLogCarteraSessionService().getLogCartera(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getTipo(),"TIPO1"); 
       Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
       Assert.assertEquals(resultado.getTipodocumentoId(),"TIPODOCUMENTO_ID1"); 
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getReferenciaId(),"REFERENCIA_ID1"); 
       Assert.assertEquals(resultado.getClienteoficinaId(),"CLIENTEOFICINA_ID1"); 
       Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
       Assert.assertEquals(resultado.getUsuarioId(),"USUARIO_ID1"); 
       Assert.assertEquals(resultado.getVendedorId(),"VENDEDOR_ID1"); 
       Assert.assertEquals(resultado.getMonedaId(),"MONEDA_ID1"); 
       Assert.assertEquals(resultado.getFechaEmision(),"FECHA_EMISION1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
       Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 
       Assert.assertEquals(resultado.getFechacambioestado(),"FECHACAMBIOESTADO1"); 
       Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
       Assert.assertEquals(resultado.getComentario(),"COMENTARIO1"); 
       Assert.assertEquals(resultado.getAprobado(),"APROBADO1"); 
       Assert.assertEquals(resultado.getLog(),"LOG1"); 
		
   }


 @Test (timeout=2000)
 public void deleteLogCartera() throws Exception {
      getLogCarteraSessionService().deleteLogCartera(new Long(3));
      LogCarteraIf resultado = getLogCarteraSessionService().getLogCartera(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findLogCarteraById() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraById("ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByTipo() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByTipo("TIPO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipo(),"TIPO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByOficinaId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByOficinaId("OFICINA_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByTipodocumentoId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByTipodocumentoId("TIPODOCUMENTO_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipodocumentoId(),"TIPODOCUMENTO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByCodigo() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByCodigo("CODIGO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByReferenciaId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByReferenciaId("REFERENCIA_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getReferenciaId(),"REFERENCIA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByClienteoficinaId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByClienteoficinaId("CLIENTEOFICINA_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteoficinaId(),"CLIENTEOFICINA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByPreimpreso() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByPreimpreso("PREIMPRESO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByUsuarioId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByUsuarioId("USUARIO_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getUsuarioId(),"USUARIO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByVendedorId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByVendedorId("VENDEDOR_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getVendedorId(),"VENDEDOR_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByMonedaId() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByMonedaId("MONEDA_ID1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getMonedaId(),"MONEDA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByFechaEmision() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByFechaEmision("FECHA_EMISION1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaEmision(),"FECHA_EMISION1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByValor() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByValor("VALOR1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraBySaldo() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraBySaldo("SALDO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByFechacambioestado() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByFechacambioestado("FECHACAMBIOESTADO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechacambioestado(),"FECHACAMBIOESTADO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByEstado() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByEstado("ESTADO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByComentario() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByComentario("COMENTARIO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getComentario(),"COMENTARIO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByAprobado() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByAprobado("APROBADO1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getAprobado(),"APROBADO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraByLog() throws Exception {

 	LogCarteraIf value = new LogCarteraData();
 
      
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraSessionService().addLogCartera(value);

       Collection c = getLogCarteraSessionService().findLogCarteraByLog("LOG1"); 
       LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
       Assert.assertEquals(resultado.getLog(),"LOG1"); 

   }



    
    @Test (timeout=2000)
    public void findLogCarteraByQuery() throws Exception {
 	
LogCarteraIf value = new LogCarteraData();
 
       
       value.setTipo("TIPO1"); 
       value.setOficinaId("OFICINA_ID1"); 
       value.setTipodocumentoId("TIPODOCUMENTO_ID1"); 
       value.setCodigo("CODIGO1"); 
       value.setReferenciaId("REFERENCIA_ID1"); 
       value.setClienteoficinaId("CLIENTEOFICINA_ID1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setUsuarioId("USUARIO_ID1"); 
       value.setVendedorId("VENDEDOR_ID1"); 
       value.setMonedaId("MONEDA_ID1"); 
       value.setFechaEmision("FECHA_EMISION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setFechacambioestado("FECHACAMBIOESTADO1"); 
       value.setEstado("ESTADO1"); 
       value.setComentario("COMENTARIO1"); 
       value.setAprobado("APROBADO1"); 
       value.setLog("LOG1"); 
      
      getLogCarteraSessionService().addLogCartera(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("tipo","TIPO1"); 
       parametros.put("oficinaId","OFICINA_ID1"); 
       parametros.put("tipodocumentoId","TIPODOCUMENTO_ID1"); 
       parametros.put("codigo","CODIGO1"); 
       parametros.put("referenciaId","REFERENCIA_ID1"); 
       parametros.put("clienteoficinaId","CLIENTEOFICINA_ID1"); 
       parametros.put("preimpreso","PREIMPRESO1"); 
       parametros.put("usuarioId","USUARIO_ID1"); 
       parametros.put("vendedorId","VENDEDOR_ID1"); 
       parametros.put("monedaId","MONEDA_ID1"); 
       parametros.put("fechaEmision","FECHA_EMISION1"); 
       parametros.put("valor","VALOR1"); 
       parametros.put("saldo","SALDO1"); 
       parametros.put("fechacambioestado","FECHACAMBIOESTADO1"); 
       parametros.put("estado","ESTADO1"); 
       parametros.put("comentario","COMENTARIO1"); 
       parametros.put("aprobado","APROBADO1"); 
       parametros.put("log","LOG1"); 

     Collection c = getLogCarteraSessionService().findLogCarteraByQuery(parametros); 
     LogCarteraIf resultado = (LogCarteraIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getTipo(),"TIPO1"); 
      Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
      Assert.assertEquals(resultado.getTipodocumentoId(),"TIPODOCUMENTO_ID1"); 
      Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
      Assert.assertEquals(resultado.getReferenciaId(),"REFERENCIA_ID1"); 
      Assert.assertEquals(resultado.getClienteoficinaId(),"CLIENTEOFICINA_ID1"); 
      Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
      Assert.assertEquals(resultado.getUsuarioId(),"USUARIO_ID1"); 
      Assert.assertEquals(resultado.getVendedorId(),"VENDEDOR_ID1"); 
      Assert.assertEquals(resultado.getMonedaId(),"MONEDA_ID1"); 
      Assert.assertEquals(resultado.getFechaEmision(),"FECHA_EMISION1"); 
      Assert.assertEquals(resultado.getValor(),"VALOR1"); 
      Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 
      Assert.assertEquals(resultado.getFechacambioestado(),"FECHACAMBIOESTADO1"); 
      Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
      Assert.assertEquals(resultado.getComentario(),"COMENTARIO1"); 
      Assert.assertEquals(resultado.getAprobado(),"APROBADO1"); 
      Assert.assertEquals(resultado.getLog(),"LOG1"); 


    }

public static LogCarteraSessionService getLogCarteraSessionService() {
		try {
			return (LogCarteraSessionService) ServiceLocator
					.getService(ServiceLocator.LogCarteraSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LogCarteraSessionTest.class);

 }


 

*/
}
