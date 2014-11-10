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

import com.spirit.general.entity.EmpresaData;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class EmpresaSessionTest {
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(4)
	//nombre=>VARCHAR2(30)
	//logo=>VARCHAR2(100)
	//ruc=>VARCHAR2(15)
	//web=>VARCHAR2(30)
	//emailContador=>VARCHAR2(60)
	//tipoIdRepresentante=>VARCHAR2(1)
	//numeroIdentificacion=>VARCHAR2(13)
	//rucContador=>VARCHAR2(13)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/empresa.xml");
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
	public void addEmpresa() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		EmpresaIf resultado = getEmpresaSessionService().addEmpresa(value);
		//Assert.assertEquals(resultado.getId(),new Long(4));       
		Assert.assertEquals(resultado.getCodigo(),"COD4"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE4"); 
		Assert.assertEquals(resultado.getLogo(),"LOGO4"); 
		Assert.assertEquals(resultado.getRuc(),"RUC4"); 
		Assert.assertEquals(resultado.getWeb(),"WEB4"); 
		Assert.assertEquals(resultado.getEmailContador(),"A");
		Assert.assertEquals(resultado.getTipoIdRepresentante(),new Long(1));
		Assert.assertEquals(resultado.getNumeroIdentificacion(),"A");
		Assert.assertEquals(resultado.getRucContador(),"A");
	}
	
	@Test (timeout=2000)
	public void saveEmpresa() throws Exception {
		EmpresaIf value = getEmpresaSessionService().getEmpresa(new Long(2));
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().saveEmpresa(value);
		EmpresaIf resultado = getEmpresaSessionService().getEmpresa(new Long(2));
		
		Assert.assertEquals(resultado.getId(),new Long(2));       
		Assert.assertEquals(resultado.getCodigo(),"COD4"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE4"); 
		Assert.assertEquals(resultado.getLogo(),"LOGO4"); 
		Assert.assertEquals(resultado.getRuc(),"RUC4"); 
		Assert.assertEquals(resultado.getWeb(),"WEB4"); 
		Assert.assertEquals(resultado.getEmailContador(),"A");
		Assert.assertEquals(resultado.getTipoIdRepresentante(),new Long(1));
		Assert.assertEquals(resultado.getNumeroIdentificacion(),"A");
		Assert.assertEquals(resultado.getRucContador(),"A");
	}
	
	//@Test (timeout=2000)
	public void deleteEmpresa() throws Exception {	
		EmpresaIf resultado = getEmpresaSessionService().getEmpresa(new Long(3));
		getEmpresaSessionService().deleteEmpresa(resultado.getId());
		Assert.assertNull(getEmpresaSessionService().getEmpresa(new Long(3)));
	}

	@Test (timeout=2000)
	public void findEmpresaById() throws Exception {		
		Collection c = getEmpresaSessionService().findEmpresaById(new Long(1)); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1));
	}
	
	@Test (timeout=2000)
	public void findEmpresaByCodigo() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByCodigo("COD4"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD4"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByNombre() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByNombre("NOMBRE4"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE4"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByLogo() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByLogo("LOGO4"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getLogo(),"LOGO4"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByRuc() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByRuc("RUC4"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getRuc(),"RUC4"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByWeb() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByWeb("WEB4"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getWeb(),"WEB4"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByEmailContador() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByEmailContador("A"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmailContador(),"A"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByTipoIdRepresentante() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByTipoIdRepresentante(new Long(1)); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoIdRepresentante(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByNumeroIdentificacion() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByNumeroIdentificacion("A"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getNumeroIdentificacion(),"A"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByRucContador() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Collection c = getEmpresaSessionService().findEmpresaByRucContador("A"); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		Assert.assertEquals(resultado.getRucContador(),"A"); 
	}
	
	@Test (timeout=2000)
	public void findEmpresaByQuery() throws Exception {
		EmpresaIf value = new EmpresaData();
		value.setCodigo("COD4"); 
		value.setNombre("NOMBRE4"); 
		value.setLogo("LOGO4"); 
		value.setRuc("RUC4"); 
		value.setWeb("WEB4"); 
		value.setEmailContador("A");
		value.setTipoIdRepresentante(new Long(1));
		value.setNumeroIdentificacion("A");
		value.setRucContador("A");
		
		getEmpresaSessionService().addEmpresa(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		//parametros.put("Id",new Long(20));
		parametros.put("codigo","COD4"); 
		parametros.put("nombre","NOMBRE4"); 
		parametros.put("logo","LOGO4"); 
		parametros.put("ruc","RUC4"); 
		parametros.put("web","WEB4");
		parametros.put("emailContador","A");
		parametros.put("tipoIdRepresentante",new Long(1));
		parametros.put("numeroIdentificacion","A");
		parametros.put("rucContador","A");
		
		Collection c = getEmpresaSessionService().findEmpresaByQuery(parametros); 
		EmpresaIf resultado = (EmpresaIf)c.iterator().next();
		
		// Assert.assertEquals(resultado.getId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD4"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE4"); 
		Assert.assertEquals(resultado.getLogo(),"LOGO4"); 
		Assert.assertEquals(resultado.getRuc(),"RUC4"); 
		Assert.assertEquals(resultado.getWeb(),"WEB4"); 
		Assert.assertEquals(resultado.getEmailContador(),"A");
		Assert.assertEquals(resultado.getTipoIdRepresentante(),new Long(1));
		Assert.assertEquals(resultado.getNumeroIdentificacion(),"A");
		Assert.assertEquals(resultado.getRucContador(),"A");	
	}
	
	public static EmpresaSessionService getEmpresaSessionService() {
		try {
			return (EmpresaSessionService) ServiceLocator.getService(ServiceLocator.EMPRESASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(EmpresaSessionTest.class);
	}
}
