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

import com.spirit.contabilidad.entity.LogAsientoData;
import com.spirit.contabilidad.entity.LogAsientoIf;
import com.spirit.contabilidad.session.LogAsientoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class LogAsientoSessionTest {

	/*

	//id=>NUMBER(10)
	//numero=>VARCHAR2(30)
	//empresaId=>NUMBER(10)
	//periodoId=>NUMBER(10)
	//plancuentaId=>NUMBER(10)
	//fecha=>DATE
	//status=>VARCHAR2(1)
	//efectivo=>VARCHAR2(1)
	//subtipoasientoId=>NUMBER(10)
	//observacion=>VARCHAR2(350)
	//oficinaId=>NUMBER(10)
	//tipoDocumentoId=>NUMBER(10)
	//transaccionId=>NUMBER(10)
	//log=>VARCHAR2(500)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/LogAsiento.xml");
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
	public void addLogAsiento() throws Exception {
		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		LogAsientoIf resultado = getLogAsientoSessionService().addLogAsiento(value);

		Assert.assertEquals(resultado.getNumero(),"NUMERO1"); 
		Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
		Assert.assertEquals(resultado.getPeriodoId(),"PERIODO_ID1"); 
		Assert.assertEquals(resultado.getPlancuentaId(),"PLANCUENTA_ID1"); 
		Assert.assertEquals(resultado.getFecha(),"FECHA1"); 
		Assert.assertEquals(resultado.getStatus(),"STATUS1"); 
		Assert.assertEquals(resultado.getEfectivo(),"EFECTIVO1"); 
		Assert.assertEquals(resultado.getSubtipoasientoId(),"SUBTIPOASIENTO_ID1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
		Assert.assertEquals(resultado.getTipoDocumentoId(),"TIPO_DOCUMENTO_ID1"); 
		Assert.assertEquals(resultado.getTransaccionId(),"TRANSACCION_ID1"); 
		Assert.assertEquals(resultado.getLog(),"LOG1"); 

	}

	@Test (timeout=2000)
	public void saveLogAsiento() throws Exception {

		LogAsientoIf value = getLogAsientoSessionService().getLogAsiento(new Long(1));


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().saveLogAsiento(value);

		LogAsientoIf resultado = getLogAsientoSessionService().getLogAsiento(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getNumero(),"NUMERO1"); 
		Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
		Assert.assertEquals(resultado.getPeriodoId(),"PERIODO_ID1"); 
		Assert.assertEquals(resultado.getPlancuentaId(),"PLANCUENTA_ID1"); 
		Assert.assertEquals(resultado.getFecha(),"FECHA1"); 
		Assert.assertEquals(resultado.getStatus(),"STATUS1"); 
		Assert.assertEquals(resultado.getEfectivo(),"EFECTIVO1"); 
		Assert.assertEquals(resultado.getSubtipoasientoId(),"SUBTIPOASIENTO_ID1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
		Assert.assertEquals(resultado.getTipoDocumentoId(),"TIPO_DOCUMENTO_ID1"); 
		Assert.assertEquals(resultado.getTransaccionId(),"TRANSACCION_ID1"); 
		Assert.assertEquals(resultado.getLog(),"LOG1"); 

	}


	@Test (timeout=2000)
	public void deleteLogAsiento() throws Exception {
		getLogAsientoSessionService().deleteLogAsiento(new Long(3));
		LogAsientoIf resultado = getLogAsientoSessionService().getLogAsiento(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findLogAsientoById() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoById("ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),"ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByNumero() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByNumero("NUMERO1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNumero(),"NUMERO1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByEmpresaId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByEmpresaId("EMPRESA_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByPeriodoId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByPeriodoId("PERIODO_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPeriodoId(),"PERIODO_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByPlancuentaId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByPlancuentaId("PLANCUENTA_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPlancuentaId(),"PLANCUENTA_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByFecha() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByFecha("FECHA1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha(),"FECHA1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByStatus() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByStatus("STATUS1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getStatus(),"STATUS1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByEfectivo() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByEfectivo("EFECTIVO1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEfectivo(),"EFECTIVO1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoBySubtipoasientoId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoBySubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getSubtipoasientoId(),"SUBTIPOASIENTO_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByObservacion() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByObservacion("OBSERVACION1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByOficinaId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByOficinaId("OFICINA_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByTipoDocumentoId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoDocumentoId(),"TIPO_DOCUMENTO_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByTransaccionId() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByTransaccionId("TRANSACCION_ID1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTransaccionId(),"TRANSACCION_ID1"); 

	}

	@Test (timeout=2000)
	public void findLogAsientoByLog() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Collection c = getLogAsientoSessionService().findLogAsientoByLog("LOG1"); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();
		Assert.assertEquals(resultado.getLog(),"LOG1"); 

	}




	@Test (timeout=2000)
	public void findLogAsientoByQuery() throws Exception {

		LogAsientoIf value = new LogAsientoData();


		value.setNumero("NUMERO1"); 
		value.setEmpresaId("EMPRESA_ID1"); 
		value.setPeriodoId("PERIODO_ID1"); 
		value.setPlancuentaId("PLANCUENTA_ID1"); 
		value.setFecha("FECHA1"); 
		value.setStatus("STATUS1"); 
		value.setEfectivo("EFECTIVO1"); 
		value.setSubtipoasientoId("SUBTIPOASIENTO_ID1"); 
		value.setObservacion("OBSERVACION1"); 
		value.setOficinaId("OFICINA_ID1"); 
		value.setTipoDocumentoId("TIPO_DOCUMENTO_ID1"); 
		value.setTransaccionId("TRANSACCION_ID1"); 
		value.setLog("LOG1"); 

		getLogAsientoSessionService().addLogAsiento(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("numero","NUMERO1"); 
		parametros.put("empresaId","EMPRESA_ID1"); 
		parametros.put("periodoId","PERIODO_ID1"); 
		parametros.put("plancuentaId","PLANCUENTA_ID1"); 
		parametros.put("fecha","FECHA1"); 
		parametros.put("status","STATUS1"); 
		parametros.put("efectivo","EFECTIVO1"); 
		parametros.put("subtipoasientoId","SUBTIPOASIENTO_ID1"); 
		parametros.put("observacion","OBSERVACION1"); 
		parametros.put("oficinaId","OFICINA_ID1"); 
		parametros.put("tipoDocumentoId","TIPO_DOCUMENTO_ID1"); 
		parametros.put("transaccionId","TRANSACCION_ID1"); 
		parametros.put("log","LOG1"); 

		Collection c = getLogAsientoSessionService().findLogAsientoByQuery(parametros); 
		LogAsientoIf resultado = (LogAsientoIf)c.iterator().next();


		Assert.assertEquals(resultado.getNumero(),"NUMERO1"); 
		Assert.assertEquals(resultado.getEmpresaId(),"EMPRESA_ID1"); 
		Assert.assertEquals(resultado.getPeriodoId(),"PERIODO_ID1"); 
		Assert.assertEquals(resultado.getPlancuentaId(),"PLANCUENTA_ID1"); 
		Assert.assertEquals(resultado.getFecha(),"FECHA1"); 
		Assert.assertEquals(resultado.getStatus(),"STATUS1"); 
		Assert.assertEquals(resultado.getEfectivo(),"EFECTIVO1"); 
		Assert.assertEquals(resultado.getSubtipoasientoId(),"SUBTIPOASIENTO_ID1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getOficinaId(),"OFICINA_ID1"); 
		Assert.assertEquals(resultado.getTipoDocumentoId(),"TIPO_DOCUMENTO_ID1"); 
		Assert.assertEquals(resultado.getTransaccionId(),"TRANSACCION_ID1"); 
		Assert.assertEquals(resultado.getLog(),"LOG1"); 


	}

	public static LogAsientoSessionService getLogAsientoSessionService() {
		try {
			return (LogAsientoSessionService) ServiceLocator
			.getService(ServiceLocator.LogAsientoSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LogAsientoSessionTest.class);

	}

*/
}
