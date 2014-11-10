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

import com.spirit.nomina.entity.RubroEventualData;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.session.RubroEventualSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class RubroEventualSessionTest {

	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//contratoId=>NUMBER(10)
	//rubroId=>NUMBER(10)
	//tipoRolId=>NUMBER(10)
	//valor=>NUMBER(22)
	//fecha=>DATE
	//estado=>VARCHAR2(1)
	//observacion=>VARCHAR2(100)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/RubroEventual.xml");
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
	public void addRubroEventual() throws Exception {
		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		RubroEventualIf resultado = getRubroEventualSessionService().addRubroEventual(value);

		Assert.assertEquals(resultado.getContratoId(),new Long(1)); 
		Assert.assertEquals(resultado.getRubroId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoRolIdCobro(),new Long(1)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(100)); 
		Assert.assertEquals(resultado.getFechaCobro(),now); 
		Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

	}

	@Test (timeout=2000)
	public void saveRubroEventual() throws Exception {

		RubroEventualIf value = getRubroEventualSessionService().getRubroEventual(new Long(1));

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().saveRubroEventual(value);

		RubroEventualIf resultado = getRubroEventualSessionService().getRubroEventual(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getContratoId(),new Long(1)); 
		Assert.assertEquals(resultado.getRubroId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoRolIdCobro(),new Long(1)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(100)); 
		Assert.assertEquals(resultado.getFechaCobro(),now); 
		Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

	}


	@Test (timeout=2000)
	public void deleteRubroEventual() throws Exception {
		getRubroEventualSessionService().deleteRubroEventual(new Long(3));
		RubroEventualIf resultado = getRubroEventualSessionService().getRubroEventual(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findRubroEventualById() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualById(new Long(1)); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByContratoId() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");  

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByContratoId(new Long(1)); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getContratoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByRubroId() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByRubroId(new Long(1)); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getRubroId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByTipoRolId() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByTipoRolIdPago(new Long(1)); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoRolIdCobro(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByValor() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByValor(new BigDecimal(100)); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(100)); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByFecha() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByFechaPago(now); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCobro(),now); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByEstado() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByEstado("A"); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"A"); 

	}

	@Test (timeout=2000)
	public void findRubroEventualByObservacion() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Collection c = getRubroEventualSessionService().findRubroEventualByObservacion("OBSERVACION1"); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

	}




	@Test (timeout=2000)
	public void findRubroEventualByQuery() throws Exception {

		RubroEventualIf value = new RubroEventualData();

		value.setContratoId(new Long(1)); 
		value.setRubroId(new Long(1)); 
		value.setTipoRolIdCobro(new Long(1)); 
		value.setValor(new BigDecimal(100)); 
		value.setFechaCobro(now); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1"); 

		getRubroEventualSessionService().addRubroEventual(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("contratoId",new Long(1)); 
		parametros.put("rubroId",new Long(1)); 
		parametros.put("tipoRolId",new Long(1)); 
		parametros.put("valor",new BigDecimal(100)); 
		parametros.put("fecha",now); 
		parametros.put("estado","A"); 
		parametros.put("observacion","OBSERVACION1"); 

		Collection c = getRubroEventualSessionService().findRubroEventualByQuery(parametros); 
		RubroEventualIf resultado = (RubroEventualIf)c.iterator().next();


		Assert.assertEquals(resultado.getContratoId(),new Long(1)); 
		Assert.assertEquals(resultado.getRubroId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoRolIdCobro(),new Long(1)); 
		Assert.assertEquals(resultado.getValor(),new BigDecimal(100)); 
		Assert.assertEquals(resultado.getFechaCobro(),now); 
		Assert.assertEquals(resultado.getEstado(),"ESTADO1"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1");  


	}

	public static RubroEventualSessionService getRubroEventualSessionService() {
		try {
			return (RubroEventualSessionService) ServiceLocator
			.getService(ServiceLocator.RUBROEVENTUALSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RubroEventualSessionTest.class);

	}





}
