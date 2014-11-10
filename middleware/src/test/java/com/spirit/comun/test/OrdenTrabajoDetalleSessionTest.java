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

import com.spirit.medios.entity.OrdenTrabajoDetalleData;
import com.spirit.medios.entity.OrdenTrabajoDetalleIf;
import com.spirit.medios.session.OrdenTrabajoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class OrdenTrabajoDetalleSessionTest {

	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//ordenId=>NUMBER(10)
	//subtipoId=>NUMBER(10)
	//equipoId=>NUMBER(10)
	//asignadoaId=>NUMBER(10)
	//fechalimite=>DATE
	//fechaentrega=>DATE
	//urlDescripcion=>VARCHAR2(100)
	//urlPropuesta=>VARCHAR2(100)
	//descripcion=>VARCHAR2(1000)
	//estado=>VARCHAR2(1)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/orden_trabajo_detalle.xml");
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
	public void addOrdenTrabajoDetalle() throws Exception {
		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		OrdenTrabajoDetalleIf resultado = getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Assert.assertEquals(resultado.getOrdenId(),new Long(1)); 
		Assert.assertEquals(resultado.getSubtipoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEquipoId(),new Long(1)); 
		Assert.assertEquals(resultado.getAsignadoaId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechalimite(),now); 
		Assert.assertEquals(resultado.getFechaentrega(),now); 
		Assert.assertEquals(resultado.getUrlDescripcion(),"URL_DESCRIPCION1"); 
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 

	}

	@Test (timeout=2000)
	public void saveOrdenTrabajoDetalle() throws Exception {

		OrdenTrabajoDetalleIf value = getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(new Long(1));

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().saveOrdenTrabajoDetalle(value);

		OrdenTrabajoDetalleIf resultado = getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getOrdenId(),new Long(1)); 
		Assert.assertEquals(resultado.getSubtipoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEquipoId(),new Long(1)); 
		Assert.assertEquals(resultado.getAsignadoaId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechalimite().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaentrega().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUrlDescripcion(),"URL_DESCRIPCION1"); 
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 

	}


	@Test (timeout=2000)
	public void deleteOrdenTrabajoDetalle() throws Exception {
		getOrdenTrabajoDetalleSessionService().deleteOrdenTrabajoDetalle(new Long(3));
		OrdenTrabajoDetalleIf resultado = getOrdenTrabajoDetalleSessionService().getOrdenTrabajoDetalle(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleById() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleById(new Long(1)); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByOrdenId() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByOrdenId(new Long(1)); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getOrdenId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleBySubtipoId() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleBySubtipoId(new Long(1)); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getSubtipoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByEquipoId() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByEquipoId(new Long(1)); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getEquipoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByAsignadoaId() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByAsignadoaId(new Long(1)); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getAsignadoaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByFechalimite() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		Date date = new Date(System.currentTimeMillis());
		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(date); 
		value.setFechaentrega(date); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByFechalimite(date); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechalimite().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByFechaentrega() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		Date date = new Date(System.currentTimeMillis());
		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(date); 
		value.setFechaentrega(date); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByFechaentrega(date); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaentrega().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByUrlDescripcion() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByUrlDescripcion("URL_DESCRIPCION1"); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getUrlDescripcion(),"URL_DESCRIPCION1"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByUrlPropuesta() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByUrlPropuesta("URL_PROPUESTA1"); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByDescripcion() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByDescripcion("DESCRIPCION1"); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByEstado() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByEstado("A"); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"A"); 

	}




	@Test (timeout=2000)
	public void findOrdenTrabajoDetalleByQuery() throws Exception {

		OrdenTrabajoDetalleIf value = new OrdenTrabajoDetalleData();

		value.setOrdenId(new Long(1)); 
		value.setSubtipoId(new Long(1)); 
		value.setEquipoId(new Long(1)); 
		value.setAsignadoaId(new Long(1)); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlDescripcion("URL_DESCRIPCION1"); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setEstado("A"); 

		getOrdenTrabajoDetalleSessionService().addOrdenTrabajoDetalle(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("ordenId",new Long(1)); 
		parametros.put("subtipoId",new Long(1)); 
		parametros.put("equipoId",new Long(1)); 
		parametros.put("asignadoaId",new Long(1)); 
		parametros.put("fechalimite",now); 
		parametros.put("fechaentrega",now); 
		parametros.put("urlDescripcion","URL_DESCRIPCION1"); 
		parametros.put("urlPropuesta","URL_PROPUESTA1"); 
		parametros.put("descripcion","DESCRIPCION1"); 
		parametros.put("estado","A"); 

		Collection c = getOrdenTrabajoDetalleSessionService().findOrdenTrabajoDetalleByQuery(parametros); 
		OrdenTrabajoDetalleIf resultado = (OrdenTrabajoDetalleIf)c.iterator().next();


		Assert.assertEquals(resultado.getOrdenId(),new Long(1)); 
		Assert.assertEquals(resultado.getSubtipoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEquipoId(),new Long(1)); 
		Assert.assertEquals(resultado.getAsignadoaId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechalimite().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaentrega().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUrlDescripcion(),"URL_DESCRIPCION1"); 
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 


	}

	public static OrdenTrabajoDetalleSessionService getOrdenTrabajoDetalleSessionService() {
		try {
			return (OrdenTrabajoDetalleSessionService) ServiceLocator
			.getService(ServiceLocator.ORDENTRABAJODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(OrdenTrabajoDetalleSessionTest.class);

	}

}
