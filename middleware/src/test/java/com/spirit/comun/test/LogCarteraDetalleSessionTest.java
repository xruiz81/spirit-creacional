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



public class LogCarteraDetalleSessionTest {
/*

   //id=>NUMBER(10)
   //logCarteraId=>NUMBER(10)
   //documentoId=>NUMBER(10)
   //secuencial=>NUMBER(3)
   //lineaId=>NUMBER(10)
   //tipopagoId=>NUMBER(10)
   //cuentaBancariaId=>NUMBER(10)
   //referencia=>VARCHAR2(30)
   //preimpreso=>VARCHAR2(20)
   //depositoId=>NUMBER(10)
   //fechaCreacion=>DATE
   //fechaCartera=>DATE
   //fechaVencimiento=>DATE
   //fechaActualizacion=>DATE
   //valor=>NUMBER(22)
   //saldo=>NUMBER(22)
   //cotizacion=>NUMBER(22)
   //cartera=>VARCHAR2(1)
   //autorizacion=>VARCHAR2(20)
   //sriSustentoTributarioId=>NUMBER(10)
   //valorRetencionFuente=>NUMBER(22)
   //valorRetencionIva=>NUMBER(22)
   //log=>VARCHAR2(500)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/LogCarteraDetalle.xml");
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
   public void addLogCarteraDetalle() throws Exception {
     LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
     LogCarteraDetalleIf resultado = getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);
           
       Assert.assertEquals(resultado.getLogCarteraId(),"LOG_CARTERA_ID1"); 
       Assert.assertEquals(resultado.getDocumentoId(),"DOCUMENTO_ID1"); 
       Assert.assertEquals(resultado.getSecuencial(),"SECUENCIAL1"); 
       Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 
       Assert.assertEquals(resultado.getTipopagoId(),"TIPOPAGO_ID1"); 
       Assert.assertEquals(resultado.getCuentaBancariaId(),"CUENTA_BANCARIA_ID1"); 
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
       Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
       Assert.assertEquals(resultado.getDepositoId(),"DEPOSITO_ID1"); 
       Assert.assertEquals(resultado.getFechaCreacion(),"FECHA_CREACION1"); 
       Assert.assertEquals(resultado.getFechaCartera(),"FECHA_CARTERA1"); 
       Assert.assertEquals(resultado.getFechaVencimiento(),"FECHA_VENCIMIENTO1"); 
       Assert.assertEquals(resultado.getFechaActualizacion(),"FECHA_ACTUALIZACION1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
       Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 
       Assert.assertEquals(resultado.getCotizacion(),"COTIZACION1"); 
       Assert.assertEquals(resultado.getCartera(),"CARTERA1"); 
       Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 
       Assert.assertEquals(resultado.getSriSustentoTributarioId(),"SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       Assert.assertEquals(resultado.getValorRetencionFuente(),"VALOR_RETENCION_FUENTE1"); 
       Assert.assertEquals(resultado.getValorRetencionIva(),"VALOR_RETENCION_IVA1"); 
       Assert.assertEquals(resultado.getLog(),"LOG1"); 
		
   }

  @Test (timeout=2000)
  public void saveLogCarteraDetalle() throws Exception {
      
    LogCarteraDetalleIf value = getLogCarteraDetalleSessionService().getLogCarteraDetalle(new Long(1));
 
          
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
     getLogCarteraDetalleSessionService().saveLogCarteraDetalle(value);

     LogCarteraDetalleIf resultado = getLogCarteraDetalleSessionService().getLogCarteraDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getLogCarteraId(),"LOG_CARTERA_ID1"); 
       Assert.assertEquals(resultado.getDocumentoId(),"DOCUMENTO_ID1"); 
       Assert.assertEquals(resultado.getSecuencial(),"SECUENCIAL1"); 
       Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 
       Assert.assertEquals(resultado.getTipopagoId(),"TIPOPAGO_ID1"); 
       Assert.assertEquals(resultado.getCuentaBancariaId(),"CUENTA_BANCARIA_ID1"); 
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
       Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
       Assert.assertEquals(resultado.getDepositoId(),"DEPOSITO_ID1"); 
       Assert.assertEquals(resultado.getFechaCreacion(),"FECHA_CREACION1"); 
       Assert.assertEquals(resultado.getFechaCartera(),"FECHA_CARTERA1"); 
       Assert.assertEquals(resultado.getFechaVencimiento(),"FECHA_VENCIMIENTO1"); 
       Assert.assertEquals(resultado.getFechaActualizacion(),"FECHA_ACTUALIZACION1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
       Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 
       Assert.assertEquals(resultado.getCotizacion(),"COTIZACION1"); 
       Assert.assertEquals(resultado.getCartera(),"CARTERA1"); 
       Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 
       Assert.assertEquals(resultado.getSriSustentoTributarioId(),"SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       Assert.assertEquals(resultado.getValorRetencionFuente(),"VALOR_RETENCION_FUENTE1"); 
       Assert.assertEquals(resultado.getValorRetencionIva(),"VALOR_RETENCION_IVA1"); 
       Assert.assertEquals(resultado.getLog(),"LOG1"); 
		
   }


 @Test (timeout=2000)
 public void deleteLogCarteraDetalle() throws Exception {
      getLogCarteraDetalleSessionService().deleteLogCarteraDetalle(new Long(3));
      LogCarteraDetalleIf resultado = getLogCarteraDetalleSessionService().getLogCarteraDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findLogCarteraDetalleById() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleById("ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByLogCarteraId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByLogCarteraId("LOG_CARTERA_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLogCarteraId(),"LOG_CARTERA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByDocumentoId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByDocumentoId("DOCUMENTO_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDocumentoId(),"DOCUMENTO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleBySecuencial() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleBySecuencial("SECUENCIAL1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getSecuencial(),"SECUENCIAL1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByLineaId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByLineaId("LINEA_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByTipopagoId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByTipopagoId("TIPOPAGO_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipopagoId(),"TIPOPAGO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByCuentaBancariaId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCuentaBancariaId(),"CUENTA_BANCARIA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByReferencia() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByReferencia("REFERENCIA1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByPreimpreso() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByPreimpreso("PREIMPRESO1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByDepositoId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByDepositoId("DEPOSITO_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDepositoId(),"DEPOSITO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByFechaCreacion() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByFechaCreacion("FECHA_CREACION1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaCreacion(),"FECHA_CREACION1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByFechaCartera() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByFechaCartera("FECHA_CARTERA1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaCartera(),"FECHA_CARTERA1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByFechaVencimiento() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByFechaVencimiento("FECHA_VENCIMIENTO1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaVencimiento(),"FECHA_VENCIMIENTO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByFechaActualizacion() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByFechaActualizacion("FECHA_ACTUALIZACION1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaActualizacion(),"FECHA_ACTUALIZACION1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByValor() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByValor("VALOR1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleBySaldo() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleBySaldo("SALDO1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByCotizacion() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByCotizacion("COTIZACION1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCotizacion(),"COTIZACION1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByCartera() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByCartera("CARTERA1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCartera(),"CARTERA1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByAutorizacion() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByAutorizacion("AUTORIZACION1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleBySriSustentoTributarioId() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleBySriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getSriSustentoTributarioId(),"SRI_SUSTENTO_TRIBUTARIO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByValorRetencionFuente() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValorRetencionFuente(),"VALOR_RETENCION_FUENTE1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByValorRetencionIva() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByValorRetencionIva("VALOR_RETENCION_IVA1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValorRetencionIva(),"VALOR_RETENCION_IVA1"); 

   }

   @Test (timeout=2000)
   public void findLogCarteraDetalleByLog() throws Exception {

 	LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
      
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
       getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

       Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByLog("LOG1"); 
       LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLog(),"LOG1"); 

   }



    
    @Test (timeout=2000)
    public void findLogCarteraDetalleByQuery() throws Exception {
 	
LogCarteraDetalleIf value = new LogCarteraDetalleData();
 
       
       value.setLogCarteraId("LOG_CARTERA_ID1"); 
       value.setDocumentoId("DOCUMENTO_ID1"); 
       value.setSecuencial("SECUENCIAL1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setTipopagoId("TIPOPAGO_ID1"); 
       value.setCuentaBancariaId("CUENTA_BANCARIA_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setPreimpreso("PREIMPRESO1"); 
       value.setDepositoId("DEPOSITO_ID1"); 
       value.setFechaCreacion("FECHA_CREACION1"); 
       value.setFechaCartera("FECHA_CARTERA1"); 
       value.setFechaVencimiento("FECHA_VENCIMIENTO1"); 
       value.setFechaActualizacion("FECHA_ACTUALIZACION1"); 
       value.setValor("VALOR1"); 
       value.setSaldo("SALDO1"); 
       value.setCotizacion("COTIZACION1"); 
       value.setCartera("CARTERA1"); 
       value.setAutorizacion("AUTORIZACION1"); 
       value.setSriSustentoTributarioId("SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       value.setValorRetencionFuente("VALOR_RETENCION_FUENTE1"); 
       value.setValorRetencionIva("VALOR_RETENCION_IVA1"); 
       value.setLog("LOG1"); 
      
      getLogCarteraDetalleSessionService().addLogCarteraDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("logCarteraId","LOG_CARTERA_ID1"); 
       parametros.put("documentoId","DOCUMENTO_ID1"); 
       parametros.put("secuencial","SECUENCIAL1"); 
       parametros.put("lineaId","LINEA_ID1"); 
       parametros.put("tipopagoId","TIPOPAGO_ID1"); 
       parametros.put("cuentaBancariaId","CUENTA_BANCARIA_ID1"); 
       parametros.put("referencia","REFERENCIA1"); 
       parametros.put("preimpreso","PREIMPRESO1"); 
       parametros.put("depositoId","DEPOSITO_ID1"); 
       parametros.put("fechaCreacion","FECHA_CREACION1"); 
       parametros.put("fechaCartera","FECHA_CARTERA1"); 
       parametros.put("fechaVencimiento","FECHA_VENCIMIENTO1"); 
       parametros.put("fechaActualizacion","FECHA_ACTUALIZACION1"); 
       parametros.put("valor","VALOR1"); 
       parametros.put("saldo","SALDO1"); 
       parametros.put("cotizacion","COTIZACION1"); 
       parametros.put("cartera","CARTERA1"); 
       parametros.put("autorizacion","AUTORIZACION1"); 
       parametros.put("sriSustentoTributarioId","SRI_SUSTENTO_TRIBUTARIO_ID1"); 
       parametros.put("valorRetencionFuente","VALOR_RETENCION_FUENTE1"); 
       parametros.put("valorRetencionIva","VALOR_RETENCION_IVA1"); 
       parametros.put("log","LOG1"); 

     Collection c = getLogCarteraDetalleSessionService().findLogCarteraDetalleByQuery(parametros); 
     LogCarteraDetalleIf resultado = (LogCarteraDetalleIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getLogCarteraId(),"LOG_CARTERA_ID1"); 
      Assert.assertEquals(resultado.getDocumentoId(),"DOCUMENTO_ID1"); 
      Assert.assertEquals(resultado.getSecuencial(),"SECUENCIAL1"); 
      Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 
      Assert.assertEquals(resultado.getTipopagoId(),"TIPOPAGO_ID1"); 
      Assert.assertEquals(resultado.getCuentaBancariaId(),"CUENTA_BANCARIA_ID1"); 
      Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
      Assert.assertEquals(resultado.getPreimpreso(),"PREIMPRESO1"); 
      Assert.assertEquals(resultado.getDepositoId(),"DEPOSITO_ID1"); 
      Assert.assertEquals(resultado.getFechaCreacion(),"FECHA_CREACION1"); 
      Assert.assertEquals(resultado.getFechaCartera(),"FECHA_CARTERA1"); 
      Assert.assertEquals(resultado.getFechaVencimiento(),"FECHA_VENCIMIENTO1"); 
      Assert.assertEquals(resultado.getFechaActualizacion(),"FECHA_ACTUALIZACION1"); 
      Assert.assertEquals(resultado.getValor(),"VALOR1"); 
      Assert.assertEquals(resultado.getSaldo(),"SALDO1"); 
      Assert.assertEquals(resultado.getCotizacion(),"COTIZACION1"); 
      Assert.assertEquals(resultado.getCartera(),"CARTERA1"); 
      Assert.assertEquals(resultado.getAutorizacion(),"AUTORIZACION1"); 
      Assert.assertEquals(resultado.getSriSustentoTributarioId(),"SRI_SUSTENTO_TRIBUTARIO_ID1"); 
      Assert.assertEquals(resultado.getValorRetencionFuente(),"VALOR_RETENCION_FUENTE1"); 
      Assert.assertEquals(resultado.getValorRetencionIva(),"VALOR_RETENCION_IVA1"); 
      Assert.assertEquals(resultado.getLog(),"LOG1"); 


    }

public static LogCarteraDetalleSessionService getLogCarteraDetalleSessionService() {
		try {
			return (LogCarteraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.LogCarteraDetalleSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LogCarteraDetalleSessionTest.class);

 }


 

*/
}
