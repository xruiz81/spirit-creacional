package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.spirit.medios.entity.PresupuestoData;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.session.PresupuestoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class PresupuestoSessionTest {

	Date now = new Date(System.currentTimeMillis());
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(10)
	//ordentrabajodetId=>NUMBER(10)
	//clienteCondicionId=>NUMBER(10)
	//planmedioId=>NUMBER(10)
	//concepto=>VARCHAR2(100)
	//modificacion=>NUMBER(3)
	//fecha=>DATE
	//fechaValidez=>DATE
	//fechaEnvio=>DATE
	//fechaCancelacion=>DATE
	//fechaAceptacion=>DATE
	//valorbruto=>NUMBER(22)
	//descuento=>NUMBER(22)
	//valor=>NUMBER(22)
	//iva=>NUMBER(22)
	//cabecera=>VARCHAR2(1000)
	//estado=>VARCHAR2(1)
	//usuarioCreacionId=>NUMBER(10)
	//fechaCreacion=>DATE
	//usuarioActualizacionId=>NUMBER(10)
	//fechaActualizacion=>DATE
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/presupuesto.xml");
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
	public void addPresupuesto() throws Exception {
		PresupuestoIf value = new PresupuestoData();

		value.setCodigo("CODIGO1"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO1"); 
		value.setModificacion(1); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(1)); 
		value.setDescuento(new BigDecimal(1)); 
		value.setValor(new BigDecimal(1)); 
		value.setIva(new BigDecimal(1)); 
		value.setCabecera("CABECERA1"); 
		value.setEstado("E"); 
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now); 

		PresupuestoIf resultado = getPresupuestoSessionService().addPresupuesto(value);

		Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
		Assert.assertEquals(resultado.getOrdentrabajodetId(),new Long(1));
		Assert.assertEquals(resultado.getClienteCondicionId(),new Long(1));
		Assert.assertEquals(resultado.getPlanmedioId(),new Long(1)); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO1"); 
		Assert.assertEquals(resultado.getModificacion(),1); 
		Assert.assertEquals(resultado.getFecha(),now); 
		Assert.assertEquals(resultado.getFechaValidez(),now); 
		Assert.assertEquals(resultado.getFechaEnvio(),now); 
		Assert.assertEquals(resultado.getFechaCancelacion(),now); 
		Assert.assertEquals(resultado.getFechaAceptacion(),now); 
		Assert.assertEquals(resultado.getValorbruto(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(1));  
		Assert.assertEquals(resultado.getValor(),new BigDecimal(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(1)); ; 
		Assert.assertEquals(resultado.getCabecera(),"CABECERA1"); 
		Assert.assertEquals(resultado.getEstado(),"E"); 
		Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(41));
		Assert.assertEquals(resultado.getFechaCreacion(),now);
		Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(80));
		Assert.assertEquals(resultado.getFechaActualizacion(),now); 
	}

	@Test (timeout=2000)
	public void savePresupuesto() throws Exception {

		PresupuestoIf value = getPresupuestoSessionService().getPresupuesto(new Long(1));


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A"); 
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().savePresupuesto(value);

		PresupuestoIf resultado = getPresupuestoSessionService().getPresupuesto(new Long(1));


		Assert.assertEquals(resultado.getId(),new Long(1));       
		Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
		Assert.assertEquals(resultado.getOrdentrabajodetId(),new Long(1));
		Assert.assertEquals(resultado.getClienteCondicionId(),new Long(1));
		Assert.assertEquals(resultado.getPlanmedioId(),new Long(1)); 
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTO2"); 
		Assert.assertEquals(resultado.getModificacion(),2); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaValidez().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaEnvio().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaCancelacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaAceptacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getValorbruto(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(2));  
		Assert.assertEquals(resultado.getValor(),new BigDecimal(2)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(2)); ; 
		Assert.assertEquals(resultado.getCabecera(),"CABECERA2"); 
		Assert.assertEquals(resultado.getEstado(),"A");  
		Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(41));
		Assert.assertEquals(resultado.getFechaCreacion(),now);
		Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(80));
		Assert.assertEquals(resultado.getFechaActualizacion(),now); 
	}


	@Test (timeout=2000)
	public void deletePresupuesto() throws Exception {
		getPresupuestoSessionService().deletePresupuesto(new Long(3));
		PresupuestoIf resultado = getPresupuestoSessionService().getPresupuesto(new Long(3));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findPresupuestoById() throws Exception {
		Collection c = getPresupuestoSessionService().findPresupuestoById(new Long(1)); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}

	@Test (timeout=2000)
	public void findPresupuestoByCodigo() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGOx"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByCodigo("CODIGOx"); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByOrdentrabajodetId() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByOrdentrabajodetId(new Long(1)); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOrdentrabajodetId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByClienteCondicionId() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByClienteCondicionId(new Long(1)); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getClienteCondicionId(),new Long(1));

	}

	@Test (timeout=2000)
	public void findPresupuestoByPlanmedioId() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByPlanmedioId(new Long(1));  
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPlanmedioId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByConcepto() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1));  
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByConcepto("CONCEPTOx"); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTOx"); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByModificacion() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1));  
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(3); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByModificacion(3); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getModificacion(),3); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByFecha() throws Exception {

		PresupuestoIf value = new PresupuestoData();

		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(date); 
		value.setFechaValidez(date); 
		value.setFechaEnvio(date); 
		value.setFechaCancelacion(date); 
		value.setFechaAceptacion(date); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByFecha(date); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFecha().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByFechaValidez() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(date); 
		value.setFechaValidez(date); 
		value.setFechaEnvio(date); 
		value.setFechaCancelacion(date); 
		value.setFechaAceptacion(date); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByFechaValidez(date); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaValidez().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByFechaEnvio() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(date); 
		value.setFechaValidez(date); 
		value.setFechaEnvio(date); 
		value.setFechaCancelacion(date); 
		value.setFechaAceptacion(date); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByFechaEnvio(date); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaEnvio().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByFechaCancelacion() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(date); 
		value.setFechaValidez(date); 
		value.setFechaEnvio(date); 
		value.setFechaCancelacion(date); 
		value.setFechaAceptacion(date); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByFechaCancelacion(date); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCancelacion().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByFechaAceptacion() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTO2"); 
		value.setModificacion(2); 
		value.setFecha(date); 
		value.setFechaValidez(date); 
		value.setFechaEnvio(date); 
		value.setFechaCancelacion(date); 
		value.setFechaAceptacion(date); 
		value.setValorbruto(new BigDecimal(2)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByFechaAceptacion(date); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaAceptacion().toString(),date.toString()); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByValorbruto() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(2)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByValorbruto(new BigDecimal(3));
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getValorbruto(),new BigDecimal(3)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByDescuento() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(2)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByDescuento(new BigDecimal(3));  
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(3));  

	}

	@Test (timeout=2000)
	public void findPresupuestoByValor() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(2)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByValor(new BigDecimal(3)); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getValor(),new BigDecimal(3)); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByIva() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByIva(new BigDecimal(3));
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(3));

	}

	@Test (timeout=2000)
	public void findPresupuestoByCabecera() throws Exception {

		PresupuestoIf value = new PresupuestoData();


		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERAx"); 
		value.setEstado("A");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByCabecera("CABECERAx"); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCabecera(),"CABECERAx"); 

	}

	@Test (timeout=2000)
	public void findPresupuestoByEstado() throws Exception {		
		PresupuestoIf value = new PresupuestoData();

		value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("x");   
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Collection c = getPresupuestoSessionService().findPresupuestoByEstado("x"); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"x"); 		
	}
	
	   @Test (timeout=2000)
	   public void findPresupuestoByUsuarioCreacionId() throws Exception {

	 	PresupuestoIf value = new PresupuestoData();
	 
	      
	 	value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("x");   
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
	      
	       getPresupuestoSessionService().addPresupuesto(value);

	       Collection c = getPresupuestoSessionService().findPresupuestoByUsuarioCreacionId(new Long(41)); 
	       PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(41)); 

	   }

	   @Test (timeout=2000)
	   public void findPresupuestoByFechaCreacion() throws Exception {

	 	PresupuestoIf value = new PresupuestoData();
	 
	      
	 	value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("x");   
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
	      
	       getPresupuestoSessionService().addPresupuesto(value);

	       Collection c = getPresupuestoSessionService().findPresupuestoByFechaCreacion(now); 
	       PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getFechaCreacion(),now); 

	   }

	   @Test (timeout=2000)
	   public void findPresupuestoByUsuarioActualizacionId() throws Exception {

	 	PresupuestoIf value = new PresupuestoData();
	 
	      
	 	value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("x");   
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
	      
	       getPresupuestoSessionService().addPresupuesto(value);

	       Collection c = getPresupuestoSessionService().findPresupuestoByUsuarioActualizacionId(new Long(80)); 
	       PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(80)); 

	   }

	   @Test (timeout=2000)
	   public void findPresupuestoByFechaActualizacion() throws Exception {

	 	PresupuestoIf value = new PresupuestoData();
	 
	      
	 	value.setCodigo("CODIGO2"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOx"); 
		value.setModificacion(2); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(3)); 
		value.setDescuento(new BigDecimal(3)); 
		value.setValor(new BigDecimal(3)); 
		value.setIva(new BigDecimal(3)); 
		value.setCabecera("CABECERA2"); 
		value.setEstado("x");   
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
	      
	       getPresupuestoSessionService().addPresupuesto(value);

	       Collection c = getPresupuestoSessionService().findPresupuestoByFechaActualizacion(now); 
	       PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();
	       Assert.assertEquals(resultado.getFechaActualizacion(),now); 

	   }

	@Test (timeout=2000)
	public void findPresupuestoByQuery() throws Exception {
		PresupuestoIf value = new PresupuestoData();

		value.setCodigo("CODIGOy"); 
		value.setOrdentrabajodetId(new Long(1)); 
		value.setClienteCondicionId(new Long(1)); 
		value.setPlanmedioId(new Long(1)); 
		value.setConcepto("CONCEPTOy"); 
		value.setModificacion(5); 
		value.setFecha(now); 
		value.setFechaValidez(now); 
		value.setFechaEnvio(now); 
		value.setFechaCancelacion(now); 
		value.setFechaAceptacion(now); 
		value.setValorbruto(new BigDecimal(5)); 
		value.setDescuento(new BigDecimal(5)); 
		value.setValor(new BigDecimal(5)); 
		value.setIva(new BigDecimal(5)); 
		value.setCabecera("CABECERAy"); 
		value.setEstado("y");  
		value.setUsuarioCreacionId(new Long(41));
		value.setFechaCreacion(now);
		value.setUsuarioActualizacionId(new Long(80));
		value.setFechaActualizacion(now);
		
		getPresupuestoSessionService().addPresupuesto(value);

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("codigo","CODIGOy"); 
		parametros.put("ordentrabajodetId",new Long(1));
		parametros.put("clienteCondicionId",new Long(1)); 
		parametros.put("planmedioId",new Long(1)); 
		parametros.put("concepto","CONCEPTOy"); 
		parametros.put("modificacion",5); 
		parametros.put("fecha",now); 
		parametros.put("fechaValidez",now); 
		parametros.put("fechaEnvio",now); 
		parametros.put("fechaCancelacion",now); 
		parametros.put("fechaAceptacion",now); 
		parametros.put("valorbruto",new BigDecimal(5)); 
		parametros.put("descuento",new BigDecimal(5)); 
		parametros.put("valor",new BigDecimal(5));
		parametros.put("iva",new BigDecimal(5)); 
		parametros.put("cabecera","CABECERAy"); 
		parametros.put("estado","y"); 
		parametros.put("usuarioCreacionId",new Long(41));
		parametros.put("fechaCreacion",now);
		parametros.put("usuarioActualizacionId",new Long(80));
		parametros.put("fechaActualizacion",now); 

		Collection c = getPresupuestoSessionService().findPresupuestoByQuery(parametros); 
		PresupuestoIf resultado = (PresupuestoIf)c.iterator().next();

		Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
		Assert.assertEquals(resultado.getOrdentrabajodetId(),new Long(1));
		Assert.assertEquals(resultado.getClienteCondicionId(),new Long(1));
		Assert.assertEquals(resultado.getPlanmedioId(),new Long(1));
		Assert.assertEquals(resultado.getConcepto(),"CONCEPTOy"); 
		Assert.assertEquals(resultado.getModificacion(),5); 
		Assert.assertEquals(resultado.getFecha().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaValidez().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaEnvio().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaCancelacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaAceptacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getValorbruto(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getDescuento(),new BigDecimal(5));
		Assert.assertEquals(resultado.getValor(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getCabecera(),"CABECERAy"); 
		Assert.assertEquals(resultado.getEstado(),"y"); 
		Assert.assertEquals(resultado.getUsuarioCreacionId(),new Long(41));
		Assert.assertEquals(resultado.getFechaCreacion(),now);
		Assert.assertEquals(resultado.getUsuarioActualizacionId(),new Long(80));
		Assert.assertEquals(resultado.getFechaActualizacion(),now); 

	}

	public static PresupuestoSessionService getPresupuestoSessionService() {
		try {
			return (PresupuestoSessionService) ServiceLocator.getService(ServiceLocator.PRESUPUESTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PresupuestoSessionTest.class);

	}
}
