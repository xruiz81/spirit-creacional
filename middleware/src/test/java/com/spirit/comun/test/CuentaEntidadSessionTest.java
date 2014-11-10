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

import com.spirit.contabilidad.entity.CuentaEntidadData;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.session.CuentaEntidadSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class CuentaEntidadSessionTest {
	//id=>NUMBER(10)
	//entidadId=>NUMBER(10)
	//tipoEntidad=>VARCHAR2(1)
	//nemonico=>VARCHAR2(10)
	//cuentaId=>NUMBER(10)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cuenta_entidad.xml");
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
	public void addCuentaEntidad() throws Exception {
		CuentaEntidadIf value = new CuentaEntidadData();	
		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("A"); 
		value.setNemonico("A"); 
		value.setCuentaId(new Long(1)); 
		
		CuentaEntidadIf resultado = getCuentaEntidadSessionService().addCuentaEntidad(value);
		
		Assert.assertEquals(resultado.getEntidadId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoEntidad(),"A"); 
		Assert.assertEquals(resultado.getNemonico(),"A"); 
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void saveCuentaEntidad() throws Exception {
		CuentaEntidadIf value = getCuentaEntidadSessionService().getCuentaEntidad(new Long(1));
		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("B"); 
		value.setNemonico("B"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().saveCuentaEntidad(value);
		CuentaEntidadIf resultado = getCuentaEntidadSessionService().getCuentaEntidad(new Long(1));
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getEntidadId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoEntidad(),"B"); 
		Assert.assertEquals(resultado.getNemonico(),"B"); 
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void deleteCuentaEntidad() throws Exception {
		getCuentaEntidadSessionService().deleteCuentaEntidad(new Long(3));
		CuentaEntidadIf resultado = getCuentaEntidadSessionService().getCuentaEntidad(new Long(3));
		Assert.assertNull(resultado);
	}
	
	@Test (timeout=2000)
	public void findCuentaEntidadById() throws Exception {	
		CuentaEntidadIf value = new CuentaEntidadData();

		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("A"); 
		value.setNemonico("A"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().addCuentaEntidad(value);
		
		Collection c = getCuentaEntidadSessionService().findCuentaEntidadById(new Long(1)); 
		CuentaEntidadIf resultado = (CuentaEntidadIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findCuentaEntidadByEntidadId() throws Exception {
		CuentaEntidadIf value = new CuentaEntidadData();

		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("A"); 
		value.setNemonico("A"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().addCuentaEntidad(value);
		
		Collection c = getCuentaEntidadSessionService().findCuentaEntidadByEntidadId(new Long(1)); 
		CuentaEntidadIf resultado = (CuentaEntidadIf)c.iterator().next();
		Assert.assertEquals(resultado.getEntidadId(),new Long(1)); 		
	}
	
	@Test (timeout=2000)
	public void findCuentaEntidadByTipoEntidad() throws Exception {
		CuentaEntidadIf value = new CuentaEntidadData();
		
		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("C"); 
		value.setNemonico("C"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().addCuentaEntidad(value);
		
		Collection c = getCuentaEntidadSessionService().findCuentaEntidadByTipoEntidad("C"); 
		CuentaEntidadIf resultado = (CuentaEntidadIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoEntidad(),"C"); 
	}
	
	@Test (timeout=2000)
	public void findCuentaEntidadByNemonico() throws Exception {
		CuentaEntidadIf value = new CuentaEntidadData();

		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("C"); 
		value.setNemonico("C"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().addCuentaEntidad(value);
		
		Collection c = getCuentaEntidadSessionService().findCuentaEntidadByNemonico("C"); 
		CuentaEntidadIf resultado = (CuentaEntidadIf)c.iterator().next();
		Assert.assertEquals(resultado.getNemonico(),"C"); 		
	}
	
	@Test (timeout=2000)
	public void findCuentaEntidadByCuentaId() throws Exception {
		CuentaEntidadIf value = new CuentaEntidadData();

		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("C"); 
		value.setNemonico("C"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().addCuentaEntidad(value);
		
		Collection c = getCuentaEntidadSessionService().findCuentaEntidadByCuentaId(new Long(1)); 
		CuentaEntidadIf resultado = (CuentaEntidadIf)c.iterator().next();
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 		
	}

	@Test (timeout=2000)
	public void findCuentaEntidadByQuery() throws Exception {		
		CuentaEntidadIf value = new CuentaEntidadData();

		value.setEntidadId(new Long(1)); 
		value.setTipoEntidad("D"); 
		value.setNemonico("D"); 
		value.setCuentaId(new Long(1)); 
		
		getCuentaEntidadSessionService().addCuentaEntidad(value);		
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("entidadId",new Long(1)); 
		parametros.put("tipoEntidad","D"); 
		parametros.put("nemonico","D"); 
		parametros.put("cuentaId",new Long(1)); 
		
		Collection c = getCuentaEntidadSessionService().findCuentaEntidadByQuery(parametros); 
		CuentaEntidadIf resultado = (CuentaEntidadIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getEntidadId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoEntidad(),"D"); 
		Assert.assertEquals(resultado.getNemonico(),"D"); 
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 		
	}
	
	public static CuentaEntidadSessionService getCuentaEntidadSessionService() {
		try {
			return (CuentaEntidadSessionService) ServiceLocator.getService(ServiceLocator.CUENTAENTIDADSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CuentaEntidadSessionTest.class);
		
	}
}
