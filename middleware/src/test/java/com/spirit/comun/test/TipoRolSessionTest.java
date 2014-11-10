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

import com.spirit.nomina.entity.TipoRolData;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.session.TipoRolSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoRolSessionTest {


	//id=>NUMBER(10)
	//empresaId=>NUMBER(10)
	//codigo=>VARCHAR2(4)
	//nombre=>VARCHAR2(50)
	//nemonico=>VARCHAR2(10)
	//rubroEventual=>VARCHAR2(1)
	//documentoId=>NUMBER(10)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_rol.xml");
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
	public void addTipoRol() throws Exception {
		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 
		//value.setDocumentoId(new Long(1));

		TipoRolIf resultado = getTipoRolSessionService().addTipoRol(value);

		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 
		Assert.assertEquals(resultado.getRubroEventual(),"S"); 
		//Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
	}

	@Test (timeout=2000)
	public void saveTipoRol() throws Exception {

		TipoRolIf value = getTipoRolSessionService().getTipoRol(new Long(1));

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 
		//value.setDocumentoId(new Long(1));

		getTipoRolSessionService().saveTipoRol(value);

		TipoRolIf resultado = getTipoRolSessionService().getTipoRol(new Long(1));


		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 
		Assert.assertEquals(resultado.getRubroEventual(),"S");  
		//Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

	}


	@Test (timeout=2000)
	public void deleteTipoRol() throws Exception {
		getTipoRolSessionService().deleteTipoRol(new Long(3));
		TipoRolIf resultado = getTipoRolSessionService().getTipoRol(new Long(3));
		Assert.assertNull(resultado);
	}

	@Test (timeout=2000)
	public void findTipoRolById() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 
		//value.setDocumentoId(new Long(1));

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolById(new Long(1)); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findTipoRolByEmpresaId() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolByEmpresaId(new Long(1)); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findTipoRolByCodigo() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolByCodigo("COD"); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD"); 

	}

	@Test (timeout=2000)
	public void findTipoRolByNombre() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S");  

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolByNombre("NOMBRE1"); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

	}

	@Test (timeout=2000)
	public void findTipoRolByNemonico() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S");  

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolByNemonico("NEMONICO1"); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 

	}

	@Test (timeout=2000)
	public void findTipoRolByRubroEventual() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolByRubroEventual("S"); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		Assert.assertEquals(resultado.getRubroEventual(),"S"); 

	}

	public void findTipoRolByDocumentoId() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 
		//value.setDocumentoId(new Long(1));

		getTipoRolSessionService().addTipoRol(value);

		Collection c = getTipoRolSessionService().findTipoRolByDocumentoId(new Long(1)); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();
		//Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findTipoRolByQuery() throws Exception {

		TipoRolIf value = new TipoRolData();

		value.setEmpresaId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1");  
		value.setNemonico("NEMONICO1"); 
		value.setRubroEventual("S"); 
		//value.setDocumentoId(new Long(1));

		getTipoRolSessionService().addTipoRol(value);

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("empresaId",new Long(1)); 
		parametros.put("codigo","COD"); 
		parametros.put("nombre","NOMBRE1");  
		parametros.put("nemonico","NEMONICO1"); 
		parametros.put("rubroEventual","S"); 
		parametros.put("documentoId", new Long(1));

		Collection c = getTipoRolSessionService().findTipoRolByQuery(parametros); 
		TipoRolIf resultado = (TipoRolIf)c.iterator().next();

		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 
		Assert.assertEquals(resultado.getRubroEventual(),"S"); 
		//Assert.assertEquals(resultado.getDocumentoId(),new Long(1));
	}

	public static TipoRolSessionService getTipoRolSessionService() {
		try {
			return (TipoRolSessionService) ServiceLocator
			.getService(ServiceLocator.TIPOROLSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoRolSessionTest.class);

	}





}
