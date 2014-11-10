package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
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

import com.spirit.nomina.entity.RubroData;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.RubroSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class RubroSessionTest {

	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(4)
	//empresaId=>NUMBER(10)
	//frecuencia=>VARCHAR2(1)
	//tipoRubro=>VARCHAR2(1)
	//nombre=>VARCHAR2(40)
	//tiporolId=>NUMBER(10)
	//fecha=>DATE
	//politica=>VARCHAR2(50)
	//modoOperacion=>VARCHAR2(1)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/rubro.xml");
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
	public void addRubro() throws Exception {
		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		RubroIf resultado = getRubroSessionService().addRubro(value);

		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getFrecuencia(),"M"); 
		Assert.assertEquals(resultado.getTipoRubro(),"S"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getTiporolId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getPolitica(),"POLITICA1"); 
		Assert.assertEquals(resultado.getModoOperacion(),"R"); 
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 

	}

	@Test (timeout=2000)
	public void saveRubro() throws Exception {

		RubroIf value = getRubroSessionService().getRubro(new Long(1));

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().saveRubro(value);

		RubroIf resultado = getRubroSessionService().getRubro(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getFrecuencia(),"M"); 
		Assert.assertEquals(resultado.getTipoRubro(),"S"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getTiporolId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getPolitica(),"POLITICA1"); 
		Assert.assertEquals(resultado.getModoOperacion(),"R"); 
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 

	}


	@Test (timeout=2000)
	public void deleteRubro() throws Exception {
		getRubroSessionService().deleteRubro(new Long(2));
		RubroIf resultado = getRubroSessionService().getRubro(new Long(2));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findRubroById() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroById(new Long(1)); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroByCodigo() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByCodigo("COD"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD"); 

	}

	@Test (timeout=2000)
	public void findRubroByEmpresaId() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByEmpresaId(new Long(1)); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroByFrecuencia() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByFrecuencia("M"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getFrecuencia(),"M"); 

	}

	@Test (timeout=2000)
	public void findRubroByTipoRubro() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByTipoRubro("S"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoRubro(),"S"); 

	}

	@Test (timeout=2000)
	public void findRubroByNombre() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByNombre("NOMBRE1"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

	}

	@Test (timeout=2000)
	public void findRubroByTiporolId() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByTiporolId(new Long(1)); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getTiporolId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findRubroByFecha() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByFecha(now); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 

	}

	@Test (timeout=2000)
	public void findRubroByPolitica() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByPolitica("POLITICA1"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getPolitica(),"POLITICA1"); 

	}

	@Test (timeout=2000)
	public void findRubroByModoOperacion() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByModoOperacion("R"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getModoOperacion(),"R"); 

	}

	@Test (timeout=2000)
	public void findRubroByNemonico() throws Exception {

		RubroIf value = new RubroData();


		value.setCodigo("CODIGO1"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("FRECUENCIA1"); 
		value.setTipoRubro("TIPO_RUBRO1"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("MODO_OPERACION1"); 
		value.setPagoIndividual("PAGO_INDIVIDUAL1"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Collection c = getRubroSessionService().findRubroByNemonico("NEMONICO1"); 
		RubroIf resultado = (RubroIf)c.iterator().next();
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 

	}


	@Test (timeout=2000)
	public void findRubroByQuery() throws Exception {

		RubroIf value = new RubroData();

		value.setCodigo("COD"); 
		value.setEmpresaId(new Long(1)); 
		value.setFrecuencia("M"); 
		value.setTipoRubro("S"); 
		value.setNombre("NOMBRE1"); 
		value.setTiporolId(new Long(1)); 
		value.setFecha(now); 
		value.setPolitica("POLITICA1"); 
		value.setModoOperacion("R"); 
		value.setNemonico("NEMONICO1"); 

		getRubroSessionService().addRubro(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("codigo","COD"); 
		parametros.put("empresaId",new Long(1)); 
		parametros.put("frecuencia","M"); 
		parametros.put("tipoRubro","S"); 
		parametros.put("nombre","NOMBRE1"); 
		parametros.put("tiporolId",new Long(1)); 
		parametros.put("fecha",now); 
		parametros.put("politica","POLITICA1"); 
		parametros.put("modoOperacion","R"); 
		parametros.put("nemonico","NEMONICO1"); 

		Collection c = getRubroSessionService().findRubroByQuery(parametros); 
		RubroIf resultado = (RubroIf)c.iterator().next();

		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getFrecuencia(),"M"); 
		Assert.assertEquals(resultado.getTipoRubro(),"S"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getTiporolId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getPolitica(),"POLITICA1"); 
		Assert.assertEquals(resultado.getModoOperacion(),"R"); 
		Assert.assertEquals(resultado.getNemonico(),"NEMONICO1"); 
	}

	public static RubroSessionService getRubroSessionService() {
		try {
			return (RubroSessionService) ServiceLocator
			.getService(ServiceLocator.RUBROSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RubroSessionTest.class);

	}

}
