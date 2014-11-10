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

import com.spirit.facturacion.entity.FacturaData;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.session.FacturaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class FacturaSessionTest {

	java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
   //id=>NUMBER(10)
   //oficinaId=>NUMBER(10)
   //tipodocumentoId=>NUMBER(10)
   //numero=>NUMBER(22)
   //clienteoficinaId=>NUMBER(10)
   //tipoidentificacionId=>NUMBER(10)
   //identificacion=>VARCHAR2(20)
   //formapagoId=>NUMBER(10)
   //monedaId=>NUMBER(10)
   //puntoImpresionId=>NUMBER(10)
   //origendocumentoId=>NUMBER(10)
   //vendedorId=>NUMBER(10)
   //bodegaId=>NUMBER(10)
   //pedidoId=>NUMBER(10)
   //listaPrecioId=>NUMBER(10)
   //fechaCreacion=>DATE
   //fechaFactura=>DATE
   //fechaVencimiento=>DATE
   //usuarioId=>NUMBER(10)
   //contacto=>VARCHAR2(40)
   //direccion=>VARCHAR2(100)
   //telefono=>VARCHAR2(30)
   //preimpresoNumero=>VARCHAR2(8)
   //valor=>NUMBER(22)
   //descuento=>NUMBER(22)
   //iva=>NUMBER(22)
   //ice=>NUMBER(22)
   //otroImpuesto=>NUMBER(22)
   //costo=>NUMBER(22)
   //observacion=>VARCHAR2(100)
   //estado=>VARCHAR2(1)
   //facturaaplId=>NUMBER(10)
   //descuentoGlobal=>NUMBER(22)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/factura.xml");
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
   public void addFactura() throws Exception {
     FacturaIf value = new FacturaData();
 
       value.setOficinaId(new Long(1)); 
       value.setTipodocumentoId(new Long(1)); 
       value.setNumero(new BigDecimal(22)); 
       value.setClienteoficinaId(new Long(1)); 
       value.setTipoidentificacionId(new Long(1)); 
       value.setIdentificacion("IDENTIFICACION1"); 
       value.setFormapagoId(new Long(1)); 
       value.setMonedaId(new Long(1)); 
       value.setPuntoImpresionId(new Long(1)); 
       value.setOrigendocumentoId(new Long(1)); 
       value.setVendedorId(new Long(1)); 
       value.setBodegaId(new Long(1)); 
       value.setPedidoId(new Long(1)); 
       value.setListaPrecioId(new Long(1)); 
       value.setFechaCreacion(now); 
       value.setFechaFactura(now); 
       value.setFechaVencimiento(now); 
       value.setUsuarioId(new Long(1)); 
       value.setContacto("CONTACTO1"); 
       value.setDireccion("DIRECCION1"); 
       value.setTelefono("TELEFONO1"); 
       value.setPreimpresoNumero("PREIMPR"); 
       value.setValor(new BigDecimal(22)); 
       value.setDescuento(new BigDecimal(22)); 
       value.setIva(new BigDecimal(22)); 
       value.setIce(new BigDecimal(22)); 
       value.setOtroImpuesto(new BigDecimal(22)); 
       value.setCosto(new BigDecimal(22)); 
       value.setObservacion("OBSERVACION1"); 
       value.setEstado("C"); 
       value.setFacturaaplId(new Long(1)); 
       value.setDescuentoGlobal(new BigDecimal(22)); 
      
     FacturaIf resultado = getFacturaSessionService().addFactura(value);
           
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getNumero(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
       Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
       Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 
       Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 
       Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
       Assert.assertEquals(resultado.getPuntoImpresionId(),new Long(1)); 
       Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 
       Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
       Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 
       Assert.assertEquals(resultado.getListaPrecioId(),new Long(1)); 
       Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechaFactura().toString(),now.toString()); 
       Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
       Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
       Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
       Assert.assertEquals(resultado.getPreimpresoNumero(),"PREIMPR"); 
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
       Assert.assertEquals(resultado.getEstado(),"C"); 
       Assert.assertEquals(resultado.getFacturaaplId(),new Long(1)); 
       Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 
		
   }

  @Test (timeout=2000)
  public void saveFactura() throws Exception {
      
    FacturaIf value = getFacturaSessionService().getFactura(new Long(1));
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
     getFacturaSessionService().saveFactura(value);

     FacturaIf resultado = getFacturaSessionService().getFactura(new Long(1));

     Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getNumero(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
     Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 
     Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 
     Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
     Assert.assertEquals(resultado.getPuntoImpresionId(),new Long(1)); 
     Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 
     Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
     Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 
     Assert.assertEquals(resultado.getListaPrecioId(),new Long(1)); 
     Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaFactura().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
     Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
     Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 
     Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
     Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
     Assert.assertEquals(resultado.getPreimpresoNumero(),"PREIMPR"); 
     Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
     Assert.assertEquals(resultado.getEstado(),"C"); 
     Assert.assertEquals(resultado.getFacturaaplId(),new Long(1)); 
     Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22));  
		
   }


 @Test (timeout=2000)
 public void deleteFactura() throws Exception {
      getFacturaSessionService().deleteFactura(new Long(2));
      FacturaIf resultado = getFacturaSessionService().getFactura(new Long(2));
     Assert.assertNull(resultado);
 }




   @Test (timeout=3000)
   public void findFacturaById() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaById(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByOficinaId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22));  
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByOficinaId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByTipodocumentoId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByTipodocumentoId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByNumero() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22));  
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByNumero(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNumero(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByClienteoficinaId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByClienteoficinaId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByTipoidentificacionId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByTipoidentificacionId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByIdentificacion() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByIdentificacion("IDENTIFICACION1"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 

   }

   @Test (timeout=2000)
   public void findFacturaByFormapagoId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByFormapagoId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByMonedaId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByMonedaId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByPuntoImpresionId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByPuntoImpresionId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPuntoImpresionId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByOrigendocumentoId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByOrigendocumentoId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByVendedorId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22));  
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByVendedorId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByBodegaId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByBodegaId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByPedidoId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByPedidoId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByListaPrecioId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByListaPrecioId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getListaPrecioId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByFechaCreacion() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByFechaCreacion(now); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findFacturaByFechaFactura() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByFechaFactura(now); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaFactura().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findFacturaByFechaVencimiento() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByFechaVencimiento(now); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 

   }

   @Test (timeout=2000)
   public void findFacturaByUsuarioId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByUsuarioId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByContacto() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByContacto("CONTACTO1"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 

   }

   @Test (timeout=2000)
   public void findFacturaByDireccion() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByDireccion("DIRECCION1"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 

   }

   @Test (timeout=2000)
   public void findFacturaByTelefono() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByTelefono("TELEFONO1"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 

   }

   @Test (timeout=2000)
   public void findFacturaByPreimpresoNumero() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByPreimpresoNumero("PREIMPR"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPreimpresoNumero(),"PREIMPR"); 

   }

   @Test (timeout=2000)
   public void findFacturaByValor() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22));  
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByValor(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByDescuento() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByDescuento(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByIva() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByIva(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByIce() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22));  
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByIce(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByOtroImpuesto() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByOtroImpuesto(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByCosto() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByCosto(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 

   }

   @Test (timeout=2000)
   public void findFacturaByObservacion() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByObservacion("OBSERVACION1"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

   }

   @Test (timeout=2000)
   public void findFacturaByEstado() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByEstado("C"); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"C"); 

   }

   @Test (timeout=2000)
   public void findFacturaByFacturaaplId() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByFacturaaplId(new Long(1)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getFacturaaplId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findFacturaByDescuentoGlobal() throws Exception {

 	FacturaIf value = new FacturaData();
 
    value.setOficinaId(new Long(1)); 
    value.setTipodocumentoId(new Long(1)); 
    value.setNumero(new BigDecimal(22)); 
    value.setClienteoficinaId(new Long(1)); 
    value.setTipoidentificacionId(new Long(1)); 
    value.setIdentificacion("IDENTIFICACION1"); 
    value.setFormapagoId(new Long(1)); 
    value.setMonedaId(new Long(1)); 
    value.setPuntoImpresionId(new Long(1)); 
    value.setOrigendocumentoId(new Long(1)); 
    value.setVendedorId(new Long(1)); 
    value.setBodegaId(new Long(1)); 
    value.setPedidoId(new Long(1)); 
    value.setListaPrecioId(new Long(1)); 
    value.setFechaCreacion(now); 
    value.setFechaFactura(now); 
    value.setFechaVencimiento(now); 
    value.setUsuarioId(new Long(1)); 
    value.setContacto("CONTACTO1"); 
    value.setDireccion("DIRECCION1"); 
    value.setTelefono("TELEFONO1"); 
    value.setPreimpresoNumero("PREIMPR"); 
    value.setValor(new BigDecimal(22)); 
    value.setDescuento(new BigDecimal(22)); 
    value.setIva(new BigDecimal(22)); 
    value.setIce(new BigDecimal(22)); 
    value.setOtroImpuesto(new BigDecimal(22)); 
    value.setCosto(new BigDecimal(22)); 
    value.setObservacion("OBSERVACION1"); 
    value.setEstado("C"); 
    value.setFacturaaplId(new Long(1)); 
    value.setDescuentoGlobal(new BigDecimal(22)); 
      
       getFacturaSessionService().addFactura(value);

       Collection c = getFacturaSessionService().findFacturaByDescuentoGlobal(new BigDecimal(22)); 
       FacturaIf resultado = (FacturaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 

   }



    
    @Test (timeout=3000)
    public void findFacturaByQuery() throws Exception {
 	
FacturaIf value = new FacturaData();
 
value.setOficinaId(new Long(1)); 
value.setTipodocumentoId(new Long(1)); 
value.setNumero(new BigDecimal(22)); 
value.setClienteoficinaId(new Long(1)); 
value.setTipoidentificacionId(new Long(1)); 
value.setIdentificacion("IDENTIFICACION1"); 
value.setFormapagoId(new Long(1)); 
value.setMonedaId(new Long(1)); 
value.setPuntoImpresionId(new Long(1)); 
value.setOrigendocumentoId(new Long(1)); 
value.setVendedorId(new Long(1)); 
value.setBodegaId(new Long(1)); 
value.setPedidoId(new Long(1)); 
value.setListaPrecioId(new Long(1)); 
value.setFechaCreacion(now); 
value.setFechaFactura(now); 
value.setFechaVencimiento(now); 
value.setUsuarioId(new Long(1)); 
value.setContacto("CONTACTO1"); 
value.setDireccion("DIRECCION1"); 
value.setTelefono("TELEFONO1"); 
value.setPreimpresoNumero("PREIMPR"); 
value.setValor(new BigDecimal(22)); 
value.setDescuento(new BigDecimal(22)); 
value.setIva(new BigDecimal(22)); 
value.setIce(new BigDecimal(22)); 
value.setOtroImpuesto(new BigDecimal(22)); 
value.setCosto(new BigDecimal(22)); 
value.setObservacion("OBSERVACION1"); 
value.setEstado("C"); 
value.setFacturaaplId(new Long(1)); 
value.setDescuentoGlobal(new BigDecimal(22)); 
      
      getFacturaSessionService().addFactura(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("oficinaId",new Long(1)); 
       parametros.put("tipodocumentoId",new Long(1)); 
       parametros.put("numero",new BigDecimal(22)); 
       parametros.put("clienteoficinaId",new Long(1)); 
       parametros.put("tipoidentificacionId",new Long(1)); 
       parametros.put("identificacion","IDENTIFICACION1"); 
       parametros.put("formapagoId",new Long(1)); 
       parametros.put("monedaId",new Long(1)); 
       parametros.put("puntoImpresionId",new Long(1)); 
       parametros.put("origendocumentoId",new Long(1)); 
       parametros.put("vendedorId",new Long(1)); 
       parametros.put("bodegaId",new Long(1)); 
       parametros.put("pedidoId",new Long(1)); 
       parametros.put("listaPrecioId",new Long(1)); 
       parametros.put("fechaCreacion",now); 
       parametros.put("fechaFactura",now); 
       parametros.put("fechaVencimiento",now); 
       parametros.put("usuarioId",new Long(1)); 
       parametros.put("contacto","CONTACTO1"); 
       parametros.put("direccion","DIRECCION1"); 
       parametros.put("telefono","TELEFONO1"); 
       parametros.put("preimpresoNumero","PREIMPR"); 
       parametros.put("valor",new BigDecimal(22)); 
       parametros.put("descuento",new BigDecimal(22)); 
       parametros.put("iva",new BigDecimal(22)); 
       parametros.put("ice",new BigDecimal(22)); 
       parametros.put("otroImpuesto",new BigDecimal(22)); 
       parametros.put("costo",new BigDecimal(22)); 
       parametros.put("observacion","OBSERVACION1"); 
       parametros.put("estado","C"); 
       parametros.put("facturaaplId",new Long(1)); 
       parametros.put("descuentoGlobal",new BigDecimal(22)); 

     Collection c = getFacturaSessionService().findFacturaByQuery(parametros); 
     FacturaIf resultado = (FacturaIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getOficinaId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getNumero(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getClienteoficinaId(),new Long(1)); 
     Assert.assertEquals(resultado.getTipoidentificacionId(),new Long(1)); 
     Assert.assertEquals(resultado.getIdentificacion(),"IDENTIFICACION1"); 
     Assert.assertEquals(resultado.getFormapagoId(),new Long(1)); 
     Assert.assertEquals(resultado.getMonedaId(),new Long(1)); 
     Assert.assertEquals(resultado.getPuntoImpresionId(),new Long(1)); 
     Assert.assertEquals(resultado.getOrigendocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getVendedorId(),new Long(1)); 
     Assert.assertEquals(resultado.getBodegaId(),new Long(1)); 
     Assert.assertEquals(resultado.getPedidoId(),new Long(1)); 
     Assert.assertEquals(resultado.getListaPrecioId(),new Long(1)); 
     Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaFactura().toString(),now.toString()); 
     Assert.assertEquals(resultado.getFechaVencimiento().toString(),now.toString()); 
     Assert.assertEquals(resultado.getUsuarioId(),new Long(1)); 
     Assert.assertEquals(resultado.getContacto(),"CONTACTO1"); 
     Assert.assertEquals(resultado.getDireccion(),"DIRECCION1"); 
     Assert.assertEquals(resultado.getTelefono(),"TELEFONO1"); 
     Assert.assertEquals(resultado.getPreimpresoNumero(),"PREIMPR"); 
     Assert.assertEquals(resultado.getValor(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getDescuento(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIva(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getIce(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getCosto(),new BigDecimal(22)); 
     Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 
     Assert.assertEquals(resultado.getEstado(),"C"); 
     Assert.assertEquals(resultado.getFacturaaplId(),new Long(1)); 
     Assert.assertEquals(resultado.getDescuentoGlobal(),new BigDecimal(22)); 


    }

public static FacturaSessionService getFacturaSessionService() {
		try {
			return (FacturaSessionService) ServiceLocator
					.getService(ServiceLocator.FACTURASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(FacturaSessionTest.class);

 }


 


}
