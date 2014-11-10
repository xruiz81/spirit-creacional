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

import com.spirit.medios.entity.OrdenTrabajoData;
import com.spirit.medios.entity.OrdenTrabajoIf;
import com.spirit.medios.session.OrdenTrabajoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class OrdenTrabajoSessionTest {

	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(20)
	//descripcion=>VARCHAR2(100)
	//oficinaId=>NUMBER(10)
	//clienteoficinaId=>NUMBER(10)
	//campanaId=>NUMBER(10)
	//empleadoId=>NUMBER(10)
	//fecha=>DATE
	//fechalimite=>DATE
	//fechaentrega=>DATE
	//urlPropuesta=>VARCHAR2(100)
	//estado=>VARCHAR2(1)
	//observacion=>VARCHAR2(255)
	//usuarioCreacionId=>NUMBER(10)
	//fechaCreacion=>DATE
	//usuarioActualizacionId=>NUMBER(10)
	//fechaActualizacion=>DATE

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/orden_trabajo.xml");
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
	public void addOrdenTrabajo() throws Exception {
		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		OrdenTrabajoSessionService otss = getOrdenTrabajoSessionService(); 
		OrdenTrabajoIf resultado = otss.addOrdenTrabajo(value);

		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCampanaId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha(),now); 
		Assert.assertEquals(resultado.getFechalimite(),now); 
		Assert.assertEquals(resultado.getFechaentrega(),now); 
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1");
	    Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(1)); 
	    Assert.assertEquals(resultado.getFechaCreacion(),now); 
	    Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(1)); 
	    Assert.assertEquals(resultado.getFechaActualizacion(),now); 

	}

	@Test (timeout=2000)
	public void saveOrdenTrabajo() throws Exception {

		OrdenTrabajoIf value = getOrdenTrabajoSessionService().getOrdenTrabajo(new Long(1));

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().saveOrdenTrabajo(value);

		OrdenTrabajoIf resultado = getOrdenTrabajoSessionService().getOrdenTrabajo(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCampanaId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechalimite().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaentrega().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1");
	    Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(1)); 
	    Assert.assertEquals(resultado.getFechaCreacion(),now); 
	    Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(1)); 
	    Assert.assertEquals(resultado.getFechaActualizacion(),now); 

	}


	@Test (timeout=2000)
	public void deleteOrdenTrabajo() throws Exception {
		getOrdenTrabajoSessionService().deleteOrdenTrabajo(new Long(3));
		OrdenTrabajoIf resultado = getOrdenTrabajoSessionService().getOrdenTrabajo(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findOrdenTrabajoById() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoById(new Long(1)); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByCodigo() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByCodigo("COD"); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByDescripcion() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByDescripcion("DESCRIPCION1"); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByOficinaId() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByOficinaId(new Long(1)); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByClienteoficinaId() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByClienteoficinaId(new Long(1)); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByCampanaId() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByCampanaId(new Long(1)); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCampanaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByEmpleadoId() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByEmpleadoId(new Long(1)); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByFecha() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(date); 
		value.setFechalimite(date); 
		value.setFechaentrega(date); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByFecha(date); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByFechalimite() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(date); 
		value.setFechalimite(date); 
		value.setFechaentrega(date); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByFechalimite(date); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechalimite().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByFechaentrega() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(date); 
		value.setFechalimite(date); 
		value.setFechaentrega(date); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByFechaentrega(date); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaentrega().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByUrlPropuesta() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByUrlPropuesta("URL_PROPUESTA1"); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByEstado() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByEstado("A"); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"A"); 

	}

	@Test (timeout=2000)
	public void findOrdenTrabajoByObservacion() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByObservacion("OBSERVACION1"); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

	}

	   @Test (timeout=2000)
	   public void findOrdenTrabajoByUsuarioCreacionId() throws Exception {

	 	OrdenTrabajoIf value = new OrdenTrabajoData();
	 
	      
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);
	      
	       getOrdenTrabajoSessionService().addOrdenTrabajo(value);

	       Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByUsuarioCreacionId(new Long(1)); 
	       OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(1)); 

	   }

	   @Test (timeout=2000)
	   public void findOrdenTrabajoByFechaCreacion() throws Exception {

	 	OrdenTrabajoIf value = new OrdenTrabajoData();
	 
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);
	      
	       getOrdenTrabajoSessionService().addOrdenTrabajo(value);

	       Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByFechaCreacion(now); 
	       OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getFechaCreacion(),now); 

	   }

	   @Test (timeout=2000)
	   public void findOrdenTrabajoByUsuarioActualizacionId() throws Exception {

	 	OrdenTrabajoIf value = new OrdenTrabajoData();
	 
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);
	      
	       getOrdenTrabajoSessionService().addOrdenTrabajo(value);

	       Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByUsuarioActualizacionId(new Long(1)); 
	       OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(1)); 

	   }

	   @Test (timeout=2000)
	   public void findOrdenTrabajoByFechaActualizacion() throws Exception {

	 	OrdenTrabajoIf value = new OrdenTrabajoData();
	 
		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);
	      
	       getOrdenTrabajoSessionService().addOrdenTrabajo(value);

	       Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByFechaActualizacion(now); 
	       OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getFechaActualizacion(),now); 

	   }


	@Test (timeout=2000)
	public void findOrdenTrabajoByQuery() throws Exception {

		OrdenTrabajoIf value = new OrdenTrabajoData();

		value.setCodigo("COD"); 
		value.setDescripcion("DESCRIPCION1"); 
		value.setOficinaId(new Long(1)); 
		value.setClienteoficinaId(new Long(1)); 
		value.setCampanaId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setFecha(now); 
		value.setFechalimite(now); 
		value.setFechaentrega(now); 
		value.setUrlPropuesta("URL_PROPUESTA1"); 
		value.setEstado("A"); 
		value.setObservacion("OBSERVACION1");
	    value.setUsuarioCreacionId(new Long(1)); 
	    value.setFechaCreacion(now); 
	    value.setUsuarioActualizacionId(new Long(1)); 
	    value.setFechaActualizacion(now);

		getOrdenTrabajoSessionService().addOrdenTrabajo(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("codigo","COD"); 
		parametros.put("descripcion","DESCRIPCION1"); 
		parametros.put("oficinaId",new Long(1)); 
		parametros.put("clienteoficinaId",new Long(1)); 
		parametros.put("campanaId",new Long(1)); 
		parametros.put("empleadoId",new Long(1)); 
		parametros.put("fecha",now); 
		parametros.put("fechalimite",now); 
		parametros.put("fechaentrega",now); 
		parametros.put("urlPropuesta","URL_PROPUESTA1"); 
		parametros.put("estado","A"); 
		parametros.put("observacion","OBSERVACION1");
	    parametros.put("usuarioCreacionId",new Long(1)); 
	    parametros.put("fechaCreacion",now); 
	    parametros.put("usuarioActualizacionId",new Long(1)); 
	    parametros.put("fechaActualizacion",now); 

		Collection c = getOrdenTrabajoSessionService().findOrdenTrabajoByQuery(parametros); 
		OrdenTrabajoIf resultado = (OrdenTrabajoIf)c.iterator().next();


		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getDescripcion(),"DESCRIPCION1"); 
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getCampanaId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechalimite().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaentrega().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUrlPropuesta(),"URL_PROPUESTA1"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1");
	    Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(1)); 
	    Assert.assertEquals(resultado.getFechaCreacion(),now); 
	    Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(1)); 
	    Assert.assertEquals(resultado.getFechaActualizacion(),now); 


	}

	public static OrdenTrabajoSessionService getOrdenTrabajoSessionService() {
		try {
			return (OrdenTrabajoSessionService) ServiceLocator
			.getService(ServiceLocator.ORDENTRABAJOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(OrdenTrabajoSessionTest.class);

	}

}
