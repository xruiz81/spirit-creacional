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

import com.spirit.general.entity.TipoIdentificacionData;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.session.TipoIdentificacionSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class TipoIdentificacionSessionTest {
	
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(2)
	//nombre=>VARCHAR2(20)
	//codigoSri=>VARCHAR2(3)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_identificacion.xml");
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
	public void addTipoIdentificacion() throws Exception {
		TipoIdentificacionIf value = new TipoIdentificacionData();
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1"); 
		value.setCodigoSri("A");
		TipoIdentificacionIf resultado = getTipoIdentificacionSessionService().addTipoIdentificacion(value);
		Assert.assertEquals(resultado.getCodigo(),"CO"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1");
		Assert.assertEquals(resultado.getCodigoSri(),"A");
	}
	
	@Test (timeout=2000)
	public void saveTipoIdentificacion() throws Exception {
		TipoIdentificacionIf value = getTipoIdentificacionSessionService().getTipoIdentificacion(new Long(1));
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1"); 
		value.setCodigoSri("A");
		getTipoIdentificacionSessionService().saveTipoIdentificacion(value);
		TipoIdentificacionIf resultado = getTipoIdentificacionSessionService().getTipoIdentificacion(new Long(1));
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"CO"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1");
		Assert.assertEquals(resultado.getCodigoSri(),"A");
	}
	
	
	@Test (timeout=2000)
	public void deleteTipoIdentificacion() throws Exception {
		getTipoIdentificacionSessionService().deleteTipoIdentificacion(new Long(3));
		TipoIdentificacionIf resultado = getTipoIdentificacionSessionService().getTipoIdentificacion(new Long(3));
		Assert.assertNull(resultado);
	}
	
	@Test (timeout=2000)
	public void findTipoIdentificacionById() throws Exception {	
		TipoIdentificacionIf value = new TipoIdentificacionData();
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1");
		value.setCodigoSri("B");
		getTipoIdentificacionSessionService().addTipoIdentificacion(value);
		Collection c = getTipoIdentificacionSessionService().findTipoIdentificacionById(new Long(1)); 
		TipoIdentificacionIf resultado = (TipoIdentificacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findTipoIdentificacionByCodigo() throws Exception {
		TipoIdentificacionIf value = new TipoIdentificacionData();
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1"); 
		value.setCodigoSri("B");
		getTipoIdentificacionSessionService().addTipoIdentificacion(value);
		Collection c = getTipoIdentificacionSessionService().findTipoIdentificacionByCodigo("CO"); 
		TipoIdentificacionIf resultado = (TipoIdentificacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CO"); 
	}
	
	@Test (timeout=2000)
	public void findTipoIdentificacionByNombre() throws Exception {
		TipoIdentificacionIf value = new TipoIdentificacionData();
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1");
		value.setCodigoSri("B");
		getTipoIdentificacionSessionService().addTipoIdentificacion(value);
		Collection c = getTipoIdentificacionSessionService().findTipoIdentificacionByNombre("NOMBRE1"); 
		TipoIdentificacionIf resultado = (TipoIdentificacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
	}
	
	@Test (timeout=2000)
	public void findTipoIdentificacionByCodigoSri() throws Exception {
		TipoIdentificacionIf value = new TipoIdentificacionData();
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1");
		value.setCodigoSri("B");
		getTipoIdentificacionSessionService().addTipoIdentificacion(value);
		Collection c = getTipoIdentificacionSessionService().findTipoIdentificacionByCodigoSri("B"); 
		TipoIdentificacionIf resultado = (TipoIdentificacionIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigoSri(),"B"); 
	}
	
	@Test (timeout=2000)
	public void findTipoIdentificacionByQuery() throws Exception {	
		TipoIdentificacionIf value = new TipoIdentificacionData();
		value.setCodigo("CO"); 
		value.setNombre("NOMBRE1"); 
		value.setCodigoSri("C");
		
		getTipoIdentificacionSessionService().addTipoIdentificacion(value);
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo","CO"); 
		parametros.put("nombre","NOMBRE1"); 
		parametros.put("codigoSri","C");
		Collection c = getTipoIdentificacionSessionService().findTipoIdentificacionByQuery(parametros); 
		TipoIdentificacionIf resultado = (TipoIdentificacionIf)c.iterator().next();

		Assert.assertEquals(resultado.getCodigo(),"CO"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getCodigoSri(),"C");
	}
	
	public static TipoIdentificacionSessionService getTipoIdentificacionSessionService() {
		try {
			return (TipoIdentificacionSessionService) ServiceLocator.getService(ServiceLocator.TIPOIDENTIFICACIONSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoIdentificacionSessionTest.class);
	}
}
