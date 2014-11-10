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

import com.spirit.contabilidad.entity.EventoContableData;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.session.EventoContableSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class EventoContableSessionTest {
	
	
	
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(4)
	//nombre=>VARCHAR2(30)
	//empresaId=>NUMBER(10)
	//moduloId=>NUMBER(10)
	//oficinaId=>NUMBER(10)
	//documentoId=>NUMBER(10)
	//lineaId=>NUMBER(10)
	//planCuentaId=>NUMBER(10)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/evento_contable.xml");
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
	public void addEventoContable() throws Exception {
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD1"); 
		value.setNombre("NOMBRE1"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1)); 
		
		EventoContableIf resultado = getEventoContableSessionService().addEventoContable(value);
		
		Assert.assertEquals(resultado.getCodigo(),"COD1"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPlanCuentaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void saveEventoContable() throws Exception {
		
		EventoContableIf value = getEventoContableSessionService().getEventoContable(new Long(1));
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1));
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().saveEventoContable(value);
		
		EventoContableIf resultado = getEventoContableSessionService().getEventoContable(new Long(1));
		
		
		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"COD2"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPlanCuentaId(),new Long(1)); 
		
	}
	
	
	@Test (timeout=2000)
	public void deleteEventoContable() throws Exception {
		getEventoContableSessionService().deleteEventoContable(new Long(3));
		EventoContableIf resultado = getEventoContableSessionService().getEventoContable(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findEventoContableById() throws Exception {
		Collection c = getEventoContableSessionService().findEventoContableById(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByCodigo() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("CODx"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1));
		value.setPlanCuentaId(new Long(1)); 
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByCodigo("CODx"); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODx"); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByNombre() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBREx"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByNombre("NOMBREx"); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByEmpresaId() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByEmpresaId(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByModuloId() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByModuloId(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByOficinaId() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByOficinaId(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByDocumentoId() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByDocumentoId(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByLineaId() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByLineaId(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		
	}
	
	
	@Test (timeout=2000)
	public void findEventoContableByPlanCuentaId() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("COD2"); 
		value.setNombre("NOMBRE2"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Collection c = getEventoContableSessionService().findEventoContableByPlanCuentaId(new Long(1)); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		Assert.assertEquals(resultado.getPlanCuentaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findEventoContableByQuery() throws Exception {
		
		EventoContableIf value = new EventoContableData();
		
		
		value.setCodigo("CODy"); 
		value.setNombre("NOMBREy"); 
		value.setEmpresaId(new Long(1)); 
		value.setModuloId(new Long(1)); 
		value.setOficinaId(new Long(1)); 
		value.setDocumentoId(new Long(1)); 
		value.setLineaId(new Long(1));
		value.setPlanCuentaId(new Long(1));
		
		getEventoContableSessionService().addEventoContable(value);
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		
		parametros.put("codigo","CODy"); 
		parametros.put("nombre","NOMBREy"); 
		parametros.put("empresaId",new Long(1)); 
		parametros.put("moduloId",new Long(1)); 
		parametros.put("oficinaId",new Long(1)); 
		parametros.put("documentoId",new Long(1)); 
		parametros.put("lineaId",new Long(1));
		parametros.put("planCuentaId",new Long(1));
		
		Collection c = getEventoContableSessionService().findEventoContableByQuery(parametros); 
		EventoContableIf resultado = (EventoContableIf)c.iterator().next();
		
		
		Assert.assertEquals(resultado.getCodigo(),"CODy"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPlanCuentaId(),new Long(1)); 
		
	}
	
	public static EventoContableSessionService getEventoContableSessionService() {
		try {
			return (EventoContableSessionService) ServiceLocator
			.getService(ServiceLocator.EVENTOCONTABLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(EventoContableSessionTest.class);
		
	}
}
