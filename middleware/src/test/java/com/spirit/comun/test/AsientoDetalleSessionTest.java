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

import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class AsientoDetalleSessionTest {

	
	//id=>NUMBER(10)
	//cuentaId=>NUMBER(10)
	//asientoId=>NUMBER(10)
	//referencia=>VARCHAR2(30)
	//glosa=>VARCHAR2(350)
	//centrogastoId=>NUMBER(10)
	//empleadoId=>NUMBER(10)
	//departamentoId=>NUMBER(10)
	//lineaId=>NUMBER(10)
	//clienteId=>NUMBER(10)
	//debe=>NUMBER(22)
	//haber=>NUMBER(22)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/asiento_detalle.xml");
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

	@Test (timeout=4000)
	public void addAsientoDetalle() throws Exception {
		AsientoDetalleIf value = new AsientoDetalleData();

		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA1"); 
		value.setGlosa("GLOSA1"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(1)); 
		value.setHaber(new BigDecimal(1)); 

		AsientoDetalleIf resultado = getAsientoDetalleSessionService().addAsientoDetalle(value);

		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getAsientoId(),new Long(1)); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getGlosa(),"GLOSA1"); 
		Assert.assertEquals(resultado.getCentrogastoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getDebe(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getHaber(),new BigDecimal(1)); 

	}

	@Test (timeout=2000)
	public void saveAsientoDetalle() throws Exception {

		AsientoDetalleIf value = getAsientoDetalleSessionService().getAsientoDetalle(new Long(1));


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2));  

		getAsientoDetalleSessionService().saveAsientoDetalle(value);

		AsientoDetalleIf resultado = getAsientoDetalleSessionService().getAsientoDetalle(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 
		Assert.assertEquals(resultado.getAsientoId(),new Long(1)); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA2"); 
		Assert.assertEquals(resultado.getGlosa(),"GLOSA2"); 
		Assert.assertEquals(resultado.getCentrogastoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getDebe(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getHaber(),new BigDecimal(2)); 

	}


	@Test (timeout=2000)
	public void deleteAsientoDetalle() throws Exception {
		getAsientoDetalleSessionService().deleteAsientoDetalle(new Long(3));
		AsientoDetalleIf resultado = getAsientoDetalleSessionService().getAsientoDetalle(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findAsientoDetalleById() throws Exception {
		Collection c = getAsientoDetalleSessionService().findAsientoDetalleById(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}

	@Test (timeout=2000)
	public void findAsientoDetalleByCuentaId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2));

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByCuentaId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCuentaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByAsientoId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByAsientoId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getAsientoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByReferencia() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIAx"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByReferencia("REFERENCIAx"); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIAx"); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByGlosa() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSAx"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByGlosa("GLOSAx"); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getGlosa(),"GLOSAx"); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByCentrogastoId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByCentrogastoId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getCentrogastoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByEmpleadoId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByEmpleadoId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByDepartamentoId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByDepartamentoId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByLineaId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByLineaId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByClienteId() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByClienteId(new Long(1)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getClienteId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByDebe() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(5)); 
		value.setHaber(new BigDecimal(2)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByDebe(new BigDecimal(5)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getDebe(),new BigDecimal(5)); 

	}

	@Test (timeout=2000)
	public void findAsientoDetalleByHaber() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIA2"); 
		value.setGlosa("GLOSA2"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(2)); 
		value.setHaber(new BigDecimal(5)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByHaber(new BigDecimal(5)); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();
		Assert.assertEquals(resultado.getHaber(),new BigDecimal(5)); 

	}




	@Test (timeout=2000)
	public void findAsientoDetalleByQuery() throws Exception {

		AsientoDetalleIf value = new AsientoDetalleData();


		value.setCuentaId(new Long(1)); 
		value.setAsientoId(new Long(1)); 
		value.setReferencia("REFERENCIAy"); 
		value.setGlosa("GLOSAy"); 
		value.setCentrogastoId(new Long(1)); 
		value.setEmpleadoId(new Long(1)); 
		value.setDepartamentoId(new Long(1)); 
		value.setLineaId(new Long(1)); 
		value.setClienteId(new Long(1)); 
		value.setDebe(new BigDecimal(7)); 
		value.setHaber(new BigDecimal(7)); 

		getAsientoDetalleSessionService().addAsientoDetalle(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("cuentaId",new Long(1)); 
		parametros.put("asientoId",new Long(1)); 
		parametros.put("referencia","REFERENCIAy"); 
		parametros.put("glosa","GLOSAy"); 
		parametros.put("centrogastoId",new Long(1)); 
		parametros.put("empleadoId",new Long(1)); 
		parametros.put("departamentoId",new Long(1)); 
		parametros.put("lineaId",new Long(1)); 
		parametros.put("clienteId",new Long(1)); 
		parametros.put("debe",new BigDecimal(7)); 
		parametros.put("haber",new BigDecimal(7)); 

		Collection c = getAsientoDetalleSessionService().findAsientoDetalleByQuery(parametros); 
		AsientoDetalleIf resultado = (AsientoDetalleIf)c.iterator().next();


		Assert.assertEquals(resultado.getCuentaId(),new Long(1));
		Assert.assertEquals(resultado.getAsientoId(),new Long(1)); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIAy"); 
		Assert.assertEquals(resultado.getGlosa(),"GLOSAy"); 
		Assert.assertEquals(resultado.getCentrogastoId(),new Long(1)); 
		Assert.assertEquals(resultado.getEmpleadoId(),new Long(1)); 
		Assert.assertEquals(resultado.getDepartamentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getClienteId(),new Long(1)); 
		Assert.assertEquals(resultado.getDebe(),new BigDecimal(7)); 
		Assert.assertEquals(resultado.getHaber(),new BigDecimal(7)); 


	}

	public static AsientoDetalleSessionService getAsientoDetalleSessionService() {
		try {
			return (AsientoDetalleSessionService) ServiceLocator
			.getService(ServiceLocator.ASIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AsientoDetalleSessionTest.class);

	}





}
