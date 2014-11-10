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

import com.spirit.facturacion.entity.PedidoData;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.PedidoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class PedidoSessionTest {

	java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
	//id=>NUMBER(10)
	//oficinaId=>NUMBER(10)
	//tipodocumentoId=>NUMBER(10)
	//codigo=>VARCHAR2(10)
	//clienteoficinaId=>NUMBER(10)
	//tipoidentificacionId=>NUMBER(10)
	//identificacion=>VARCHAR2(20)
	//formapagoId=>NUMBER(10)
	//monedaId=>NUMBER(10)
	//puntoimpresionId=>NUMBER(10)
	//origendocumentoId=>NUMBER(10)
	//vendedorId=>NUMBER(10)
	//bodegaId=>NUMBER(10)
	//listaprecioId=>NUMBER(10)
	//fechaPedido=>DATE
	//fechaCreacion=>DATE
	//usuarioId=>NUMBER(10)
	//contacto=>VARCHAR2(40)
	//direccion=>VARCHAR2(100)
	//telefono=>VARCHAR2(30)
	//tiporeferencia=>VARCHAR2(1)
	//referencia=>VARCHAR2(20)
	//diasvalidez=>NUMBER(3)
	//observacion=>VARCHAR2(100)
	//estado=>VARCHAR2(1)
	//pedidoaplId=>NUMBER(10)
	//descuentoGlobal=>NUMBER(22)

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/pedido.xml");
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
	public void addPedido() throws Exception {
		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		PedidoIf resultado = getPedidoSessionService().addPedido(value);

		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 
		Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPuntoimpresionId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		Assert.assertEquals(resultado.getListaprecioId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechaPedido().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 
		Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
		Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
		Assert.assertEquals(resultado.getTiporeferencia(),"N"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getDiasvalidez(),new Integer(3)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"P"); 
		Assert.assertEquals(resultado.getPedidoaplId(),new Long(1)); 

	}

	@Test (timeout=3000)
	public void savePedido() throws Exception {

		PedidoIf value = getPedidoSessionService().getPedido(new Long(1));

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().savePedido(value);

		PedidoIf resultado = getPedidoSessionService().getPedido(new Long(1));

		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 
		Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPuntoimpresionId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		Assert.assertEquals(resultado.getListaprecioId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechaPedido().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 
		Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
		Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
		Assert.assertEquals(resultado.getTiporeferencia(),"N"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getDiasvalidez(),new Integer(3)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"P"); 
		Assert.assertEquals(resultado.getPedidoaplId(),new Long(1));  

	}


	@Test (timeout=2000)
	public void deletePedido() throws Exception {
		getPedidoSessionService().deletePedido(new Long(2));
		PedidoIf resultado = getPedidoSessionService().getPedido(new Long(2));
		Assert.assertNull(resultado);
	}




	@Test (timeout=2000)
	public void findPedidoById() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoById(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByOficinaId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByOficinaId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByTipodocumentoId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByTipodocumentoId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByCodigo() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByCodigo("COD"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD"); 

	}

	@Test (timeout=2000)
	public void findPedidoByClienteoficinaId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByClienteoficinaId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByTipoidentificacionId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByTipoidentificacionId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByIdentificacion() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByIdentificacion("IDENTIFICACION1"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 

	}

	@Test (timeout=2000)
	public void findPedidoByFormapagoId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByFormapagoId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByMonedaId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByMonedaId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByPuntoimpresionId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByPuntoimpresionId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPuntoimpresionId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByOrigendocumentoId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByOrigendocumentoId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByVendedorId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByVendedorId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByBodegaId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByBodegaId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByListaprecioId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByListaprecioId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getListaprecioId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByFechaPedido() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByFechaPedido(now); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaPedido().toString(),now.toString()); 

	}

	@Test (timeout=2000)
	public void findPedidoByFechaCreacion() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByFechaCreacion(now); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 

	}

	@Test (timeout=2000)
	public void findPedidoByUsuarioId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByUsuarioId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 

	}

	@Test (timeout=2000)
	public void findPedidoByContacto() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByContacto("CONTACTO1"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 

	}

	@Test (timeout=2000)
	public void findPedidoByDireccion() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByDireccion("DIRECCION1"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 

	}

	@Test (timeout=2000)
	public void findPedidoByTelefono() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByTelefono("TELEFONO1"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 

	}

	@Test (timeout=2000)
	public void findPedidoByTiporeferencia() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByTiporeferencia("N"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTiporeferencia(),"N"); 

	}

	@Test (timeout=2000)
	public void findPedidoByReferencia() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByReferencia("REFERENCIA1"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 

	}

	@Test (timeout=2000)
	public void findPedidoByDiasvalidez() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByDiasvalidez(new Integer(3)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getDiasvalidez(),new Integer(3)); 

	}

	@Test (timeout=2000)
	public void findPedidoByObservacion() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByObservacion("OBSERVACION1"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

	}

	@Test (timeout=2000)
	public void findPedidoByEstado() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByEstado("P"); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"P"); 

	}

	@Test (timeout=2000)
	public void findPedidoByPedidoaplId() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Collection c = getPedidoSessionService().findPedidoByPedidoaplId(new Long(1)); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPedidoaplId(),new Long(1)); 

	}



	@Test (timeout=3000)
	public void findPedidoByQuery() throws Exception {

		PedidoIf value = new PedidoData();

		value.setOficinaId(new Long(1)); 
		value.setTipodocumentoId(new Long(1)); 
		value.setCodigo("COD"); 
		value.setClienteoficinaId(new Long(1)); 
		value.setTipoidentificacionId(new Long(1)); 
		value.setIdentificacion("IDENTIFICACION1"); 
		value.setFormapagoId(new Long(1)); 
		value.setMonedaId(new Long(1)); 
		value.setPuntoimpresionId(new Long(1)); 
		value.setOrigendocumentoId(new Long(1)); 
		value.setVendedorId(new Long(1)); 
		value.setBodegaId(new Long(1)); 
		value.setListaprecioId(new Long(1)); 
		value.setFechaPedido(now); 
		value.setFechaCreacion(now); 
		value.setUsuarioId(new Long(1)); 
		value.setContacto("CONTACTO1"); 
		value.setDireccion("DIRECCION1"); 
		value.setTelefono("TELEFONO1"); 
		value.setTiporeferencia("N"); 
		value.setReferencia("REFERENCIA1"); 
		value.setDiasvalidez(new Integer(3)); 
		value.setObservacion("OBSERVACION1"); 
		value.setEstado("P"); 
		value.setPedidoaplId(new Long(1)); 

		getPedidoSessionService().addPedido(value);

		Map<String, Object> parametros = new HashMap<String, Object>();


		parametros.put("oficinaId",new Long(1)); 
		parametros.put("tipodocumentoId",new Long(1)); 
		parametros.put("codigo","COD"); 
		parametros.put("clienteoficinaId",new Long(1)); 
		parametros.put("tipoidentificacionId",new Long(1)); 
		parametros.put("identificacion","IDENTIFICACION1"); 
		parametros.put("formapagoId",new Long(1)); 
		parametros.put("monedaId",new Long(1)); 
		parametros.put("puntoimpresionId",new Long(1)); 
		parametros.put("origendocumentoId",new Long(1)); 
		parametros.put("vendedorId",new Long(1)); 
		parametros.put("bodegaId",new Long(1)); 
		parametros.put("listaprecioId",new Long(1)); 
		parametros.put("fechaPedido",now); 
		parametros.put("fechaCreacion",now); 
		parametros.put("usuarioId",new Long(1)); 
		parametros.put("contacto","CONTACTO1"); 
		parametros.put("direccion","DIRECCION1"); 
		parametros.put("telefono","TELEFONO1"); 
		parametros.put("tiporeferencia","N"); 
		parametros.put("referencia","REFERENCIA1"); 
		parametros.put("diasvalidez",new Integer(3)); 
		parametros.put("observacion","OBSERVACION1"); 
		parametros.put("estado","P"); 
		parametros.put("pedidoaplId",new Long(1)); 


		Collection c = getPedidoSessionService().findPedidoByQuery(parametros); 
		PedidoIf resultado = (PedidoIf)c.iterator().next();

		Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
		Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 
		Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 
		Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPuntoimpresionId(),new Long(1)); 
		Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 
		Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 
		Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
		Assert.assertEquals(resultado.getListaprecioId(),new Long(1)); 
		Assert.assertEquals(resultado.getFechaPedido().toString(),now.toString()); 
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
		Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 
		Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
		Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
		Assert.assertEquals(resultado.getTiporeferencia(),"N"); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getDiasvalidez(),new Integer(3)); 
		Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
		Assert.assertEquals(resultado.getEstado(),"P"); 
		Assert.assertEquals(resultado.getPedidoaplId(),new Long(1)); 


	}

	public static PedidoSessionService getPedidoSessionService() {
		try {
			return (PedidoSessionService) ServiceLocator
			.getService(ServiceLocator.PEDIDOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(PedidoSessionTest.class);

	}

}
