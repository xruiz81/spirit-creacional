package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.spirit.general.entity.NumeradoresData;
import com.spirit.general.entity.NumeradoresIf;
import com.spirit.general.session.NumeradoresSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class NumeradoresSessionTest {

	//id=>NUMBER(10)
	//ultimo_valor=>NUMBER
	//nombre_tabla=>VARCHAR2(30)
	//empresa_id ==> NUMBER(10)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/numeradores.xml");
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
	public void addNumeradores() throws Exception {
		NumeradoresIf value = new NumeradoresData();

		value.setUltimoValor(new BigDecimal(40)); 
		value.setNombreTabla("TABLA 1");
		value.setEmpresaId(new Long(1));

		NumeradoresIf resultado = getNumeradoresSessionService().addNumeradores(value);

		Assert.assertEquals(resultado.getUltimoValor(),new BigDecimal(40)); 
		Assert.assertEquals(resultado.getNombreTabla(),"TABLA 1");
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1));

	}

	@Test (timeout=2000)
	public void saveMoneda() throws Exception {

		NumeradoresIf value = getNumeradoresSessionService().getNumeradores(new Long(1));

		value.setUltimoValor(new BigDecimal(50)); 
		value.setNombreTabla("TABLA 0");
		value.setEmpresaId(new Long(1));

		getNumeradoresSessionService().saveNumeradores(value);

		NumeradoresIf resultado = getNumeradoresSessionService().getNumeradores(new Long(1));

		Assert.assertEquals(resultado.getUltimoValor(),new BigDecimal(50)); 
		Assert.assertEquals(resultado.getNombreTabla(),"TABLA 0");
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 

	}


	@Test (timeout=2000)
	public void deleteMoneda() throws Exception {
		getNumeradoresSessionService().deleteNumeradores(new Long(3));
		NumeradoresIf resultado = getNumeradoresSessionService().getNumeradores(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findNumeradoresById() throws Exception {

		Collection c = getNumeradoresSessionService().findNumeradoresById(new Long(1)); 
		NumeradoresIf resultado = (NumeradoresIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getId(),new Long(1));

	}

	@Test (timeout=2000)
	public void findNumeradoresByUltimoValor() throws Exception {

		NumeradoresIf value =  new NumeradoresData();
		value.setUltimoValor(new BigDecimal(60)); 
		value.setNombreTabla("TABLA 4");
		value.setEmpresaId(new Long(1));

		NumeradoresIf numerador = getNumeradoresSessionService().addNumeradores(value);

		Collection c = getNumeradoresSessionService().findNumeradoresByUltimoValor(new BigDecimal(60)); 
		NumeradoresIf resultado = (NumeradoresIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getUltimoValor(),new BigDecimal(60));
		Assert.assertEquals(resultado.getNombreTabla(),"TABLA 4");
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1));

	}

	@Test (timeout=2000)
	public void findNumeradoresByNombreTabla() throws Exception {

		NumeradoresIf value =  new NumeradoresData();
		value.setUltimoValor(new BigDecimal(60)); 
		value.setNombreTabla("TABLA 5");
		value.setEmpresaId(new Long(1));

		NumeradoresIf numerador = getNumeradoresSessionService().addNumeradores(value);

		Collection c = getNumeradoresSessionService().findNumeradoresByNombreTabla("TABLA 5"); 
		NumeradoresIf resultado = (NumeradoresIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getUltimoValor(),new BigDecimal(60));
		Assert.assertEquals(resultado.getNombreTabla(),"TABLA 5");
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1));

	}

	@Test (timeout=2000)
	public void findNumeradoresByEmpresaId() throws Exception {

		Collection c = getNumeradoresSessionService().findNumeradoresByEmpresaId(new Long(1)); 
		NumeradoresIf resultado = (NumeradoresIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1));

	}


	@Test (timeout=2000)
	public void findNumeradoresByQuery() throws Exception {

		NumeradoresIf value =  new NumeradoresData();
		value.setUltimoValor(new BigDecimal(70)); 
		value.setNombreTabla("TABLA 6");
		value.setEmpresaId(new Long(1));

		NumeradoresIf numerador = getNumeradoresSessionService().addNumeradores(value);
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		mapa.put("ultimoValor", new BigDecimal(70));
		mapa.put("nombreTabla", "TABLA 6");
		mapa.put("empresaId", new Long(1));

		Collection c = getNumeradoresSessionService().findNumeradoresByQuery(mapa); 
		NumeradoresIf resultado = (NumeradoresIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getUltimoValor(),new BigDecimal(70));
		Assert.assertEquals(resultado.getNombreTabla(),"TABLA 6");
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1));


	}

	public static NumeradoresSessionService getNumeradoresSessionService() {
		try {
			return (NumeradoresSessionService) ServiceLocator
			.getService(ServiceLocator.NUMERADORESSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(NumeradoresSessionTest.class);

	}

}
