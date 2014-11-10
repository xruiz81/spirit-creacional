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

import com.spirit.general.entity.TipoDocumentoData;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class TipoDocumentoSessionTest {
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(3)
	//nombre=>VARCHAR2(40)
	//moduloId=>NUMBER(10)
	//mascara=>VARCHAR2(20)
	//empresaId=>NUMBER(10)
	//numerolineas=>NUMBER(3)
	//afectacartera=>VARCHAR2(1)
	//afectastock=>VARCHAR2(1)
	//afectaventa=>VARCHAR2(1)
	//exigemotivo=>VARCHAR2(1)
	//estado=>VARCHAR2(1)
	//formapago=>VARCHAR2(1)
	//cliente=>VARCHAR2(1)
	//caja=>VARCHAR2(1)
	//permiteeliminacion=>VARCHAR2(1)
	//reembolso=>VARCHAR2(1)
	//signocartera=>VARCHAR2(1)
	//signostock=>VARCHAR2(1)
	//signoventa=>VARCHAR2(1)
	//descuentoespecial=>VARCHAR2(1)
	//tipocartera=>VARCHAR2(1)
	//idSriTipoComprobante=>NUMBER(10)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/tipo_documento.xml");
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
	public void addTipoDocumento() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N");
		value.setIdSriTipoComprobante(new Long(1));
		
		TipoDocumentoIf resultado = getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		Assert.assertEquals(resultado.getMascara(),"MASCARA1"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getNumerolineas(),new Integer(3)); 
		Assert.assertEquals(resultado.getAfectacartera(),"N"); 
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		Assert.assertEquals(resultado.getAfectaventa(),"N"); 
		Assert.assertEquals(resultado.getExigemotivo(),"N"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getFormapago(),"N"); 
		Assert.assertEquals(resultado.getCliente(),"N"); 
		Assert.assertEquals(resultado.getCaja(),"N"); 
		Assert.assertEquals(resultado.getPermiteeliminacion(),"N"); 
		Assert.assertEquals(resultado.getReembolso(),"N"); 
		Assert.assertEquals(resultado.getSignocartera(),"+"); 
		Assert.assertEquals(resultado.getSignostock(),"+"); 
		Assert.assertEquals(resultado.getSignoventa(),"+"); 
		Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
		Assert.assertEquals(resultado.getTipocartera(),"N"); 
		Assert.assertEquals(resultado.getIdSriTipoComprobante(),new Long(1));
	}
	
	@Test (timeout=2000)
	public void saveTipoDocumento() throws Exception {
		TipoDocumentoIf value = getTipoDocumentoSessionService().getTipoDocumento(new Long(1));
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().saveTipoDocumento(value);
		TipoDocumentoIf resultado = getTipoDocumentoSessionService().getTipoDocumento(new Long(1));
		
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		Assert.assertEquals(resultado.getMascara(),"MASCARA1"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getNumerolineas(),new Integer(3)); 
		Assert.assertEquals(resultado.getAfectacartera(),"N"); 
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		Assert.assertEquals(resultado.getAfectaventa(),"N"); 
		Assert.assertEquals(resultado.getExigemotivo(),"N"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getFormapago(),"N"); 
		Assert.assertEquals(resultado.getCliente(),"N"); 
		Assert.assertEquals(resultado.getCaja(),"N"); 
		Assert.assertEquals(resultado.getPermiteeliminacion(),"N"); 
		Assert.assertEquals(resultado.getReembolso(),"N"); 
		Assert.assertEquals(resultado.getSignocartera(),"+"); 
		Assert.assertEquals(resultado.getSignostock(),"+"); 
		Assert.assertEquals(resultado.getSignoventa(),"+"); 
		Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
		Assert.assertEquals(resultado.getTipocartera(),"N"); 
		Assert.assertEquals(resultado.getIdSriTipoComprobante(),new Long(1));
	}
	
	@Test (timeout=2000)
	public void deleteTipoDocumento() throws Exception {
		getTipoDocumentoSessionService().deleteTipoDocumento(new Long(3));
		TipoDocumentoIf resultado = getTipoDocumentoSessionService().getTipoDocumento(new Long(3));
		Assert.assertNull(resultado);
	}

	@Test (timeout=2000)
	public void findTipoDocumentoById() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N");
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoById(new Long(1)); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByCodigo() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByCodigo("COD"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD");
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByNombre() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByNombre("NOMBRE1"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByModuloId() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByModuloId(new Long(1)); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByMascara() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByMascara("MASCARA1"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getMascara(),"MASCARA1"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByEmpresaId() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(new Long(1)); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByNumerolineas() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByNumerolineas(new Integer(3)); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNumerolineas(),new Integer(3)); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByAfectacartera() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByAfectacartera("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAfectacartera(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByAfectastock() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByAfectastock("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByAfectaventa() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByAfectaventa("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAfectaventa(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByExigemotivo() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N");  
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByExigemotivo("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getExigemotivo(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByEstado() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByEstado("A"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"A"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByFormapago() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByFormapago("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFormapago(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByCliente() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByCliente("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCliente(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByCaja() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByCaja("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCaja(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByPermiteeliminacion() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByPermiteeliminacion("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPermiteeliminacion(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByReembolso() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByReembolso("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getReembolso(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoBySignocartera() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoBySignocartera("+"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getSignocartera(),"+"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoBySignostock() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoBySignostock("+"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getSignostock(),"+"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoBySignoventa() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoBySignoventa("+"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getSignoventa(),"+"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByDescuentoespecial() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByDescuentoespecial("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByTipocartera() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByTipocartera("N"); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipocartera(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByIdSriTipoComprobante() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByIdSriTipoComprobante(new Long(1)); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIdSriTipoComprobante(),new Long(1)); 
	}
	
	@Test (timeout=2000)
	public void findTipoDocumentoByQuery() throws Exception {
		TipoDocumentoIf value = new TipoDocumentoData();
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setModuloId(new Long(1)); 
		value.setMascara("MASCARA1"); 
		value.setEmpresaId(new Long(1)); 
		value.setNumerolineas(new Integer(3)); 
		value.setAfectacartera("N"); 
		value.setAfectastock("N"); 
		value.setAfectaventa("N"); 
		value.setExigemotivo("N"); 
		value.setEstado("A"); 
		value.setFormapago("N"); 
		value.setCliente("N"); 
		value.setCaja("N"); 
		value.setPermiteeliminacion("N"); 
		value.setReembolso("N"); 
		value.setSignocartera("+"); 
		value.setSignostock("+"); 
		value.setSignoventa("+"); 
		value.setDescuentoespecial("N"); 
		value.setTipocartera("N"); 
		value.setIdSriTipoComprobante(new Long(1));
		
		getTipoDocumentoSessionService().addTipoDocumento(value);
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("codigo","COD"); 
		parametros.put("nombre","NOMBRE1"); 
		parametros.put("moduloId",new Long(1)); 
		parametros.put("mascara","MASCARA1"); 
		parametros.put("empresaId",new Long(1)); 
		parametros.put("numerolineas",new Integer(3)); 
		parametros.put("afectacartera","N"); 
		parametros.put("afectastock","N"); 
		parametros.put("afectaventa","N"); 
		parametros.put("exigemotivo","N"); 
		parametros.put("estado","A"); 
		parametros.put("formapago","N"); 
		parametros.put("cliente","N"); 
		parametros.put("caja","N"); 
		parametros.put("permiteeliminacion","N"); 
		parametros.put("reembolso","N"); 
		parametros.put("signocartera","+"); 
		parametros.put("signostock","+"); 
		parametros.put("signoventa","+"); 
		parametros.put("descuentoespecial","N"); 
		parametros.put("tipocartera","N"); 
		parametros.put("idSriTipoComprobante",new Long(1));
		
		Collection c = getTipoDocumentoSessionService().findTipoDocumentoByQuery(parametros); 
		TipoDocumentoIf resultado = (TipoDocumentoIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getModuloId(),new Long(1)); 
		Assert.assertEquals(resultado.getMascara(),"MASCARA1"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getNumerolineas(),new Integer(3)); 
		Assert.assertEquals(resultado.getAfectacartera(),"N"); 
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		Assert.assertEquals(resultado.getAfectaventa(),"N"); 
		Assert.assertEquals(resultado.getExigemotivo(),"N"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getFormapago(),"N"); 
		Assert.assertEquals(resultado.getCliente(),"N"); 
		Assert.assertEquals(resultado.getCaja(),"N"); 
		Assert.assertEquals(resultado.getPermiteeliminacion(),"N"); 
		Assert.assertEquals(resultado.getReembolso(),"N"); 
		Assert.assertEquals(resultado.getSignocartera(),"+"); 
		Assert.assertEquals(resultado.getSignostock(),"+"); 
		Assert.assertEquals(resultado.getSignoventa(),"+"); 
		Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
		Assert.assertEquals(resultado.getTipocartera(),"N"); 
		Assert.assertEquals(resultado.getIdSriTipoComprobante(),new Long(1));
	}
	
	public static TipoDocumentoSessionService getTipoDocumentoSessionService() {
		try {
			return (TipoDocumentoSessionService) ServiceLocator.getService(ServiceLocator.TIPODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(TipoDocumentoSessionTest.class);
	}
}
