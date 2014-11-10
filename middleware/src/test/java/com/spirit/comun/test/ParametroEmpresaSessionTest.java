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

import com.spirit.general.entity.ParametroEmpresaData;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.session.ParametroEmpresaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class ParametroEmpresaSessionTest {


	//id=>NUMBER(10)
	//tipoparametroId=>NUMBER(10)
	//empresaId=>NUMBER(10)
	//valor=>VARCHAR2(50)
	//codigo=>VARCHAR2(30)
	//descripcion=>VARCHAR2(100)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/parametro_empresa.xml");
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
	public void addParametroEmpresa() throws Exception {
		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		ParametroEmpresaIf resultado = getParametroEmpresaSessionService().addParametroEmpresa(value);

		Assert.assertEquals(resultado.getTipoparametroId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		Assert.assertEquals(resultado.getCodigo(),"COD1"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}

	@Test (timeout=2000)
	public void saveParametroEmpresa() throws Exception {

		ParametroEmpresaIf value = getParametroEmpresaSessionService().getParametroEmpresa(new Long(1));

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		getParametroEmpresaSessionService().saveParametroEmpresa(value);

		ParametroEmpresaIf resultado = getParametroEmpresaSessionService().getParametroEmpresa(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getTipoparametroId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		Assert.assertEquals(resultado.getCodigo(),"COD1"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}


	@Test (timeout=2000)
	public void deleteParametroEmpresa() throws Exception {
		getParametroEmpresaSessionService().deleteParametroEmpresa(new Long(2));
		ParametroEmpresaIf resultado = getParametroEmpresaSessionService().getParametroEmpresa(new Long(2));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findParametroEmpresaById() throws Exception {

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaById(new Long(1)); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findParametroEmpresaByTipoparametroId() throws Exception {

		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		getParametroEmpresaSessionService().addParametroEmpresa(value);

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaByTipoparametroId(new Long(1)); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoparametroId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findParametroEmpresaByEmpresaId() throws Exception {

		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		getParametroEmpresaSessionService().addParametroEmpresa(value);

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaByEmpresaId(new Long(1)); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findParametroEmpresaByValor() throws Exception {

		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		getParametroEmpresaSessionService().addParametroEmpresa(value);

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaByValor("VALOR1"); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),"VALOR1"); 

	}

	@Test (timeout=2000)
	public void findParametroEmpresaByCodigo() throws Exception {

		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		getParametroEmpresaSessionService().addParametroEmpresa(value);

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaByCodigo("COD1"); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD1"); 

	}

	@Test (timeout=2000)
	public void findParametroEmpresaByDescripcion() throws Exception {

		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		getParametroEmpresaSessionService().addParametroEmpresa(value);

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaByDescripcion("DESCRIPCION1"); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}




	@Test (timeout=2000)
	public void findParametroEmpresaByQuery() throws Exception {

		ParametroEmpresaIf value = new ParametroEmpresaData();

		value.setTipoparametroId(new Long(1)); 
		value.setEmpresaId(new Long(1)); 
		value.setValor("VALOR1"); 
		value.setCodigo("COD1"); 
		value.setDescripcion("DESCRIPCION1"); 

		ParametroEmpresaIf parametroEmpresaIf = getParametroEmpresaSessionService().addParametroEmpresa(value);

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("tipoparametroId",new Long(1)); 
		parametros.put("empresaId",new Long(1)); 
		parametros.put("valor","VALOR1"); 
		parametros.put("codigo","COD1"); 
		parametros.put("descripcion","DESCRIPCION1"); 

		Collection c = getParametroEmpresaSessionService().findParametroEmpresaByQuery(parametros); 
		ParametroEmpresaIf resultado = (ParametroEmpresaIf)c.iterator().next();


		Assert.assertEquals(resultado.getTipoparametroId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getValor(),"VALOR1"); 
		Assert.assertEquals(resultado.getCodigo(),"COD1"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}

	public static ParametroEmpresaSessionService getParametroEmpresaSessionService() {
		try {
			return (ParametroEmpresaSessionService) ServiceLocator
			.getService(ServiceLocator.PARAMETROEMPRESASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ParametroEmpresaSessionTest.class);

	}

}
