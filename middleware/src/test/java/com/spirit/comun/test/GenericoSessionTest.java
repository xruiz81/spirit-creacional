package com.spirit.comun.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.GregorianCalendar;
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

import com.spirit.inventario.entity.GenericoData;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class GenericoSessionTest {
	Timestamp now = new Timestamp(new GregorianCalendar().getTimeInMillis());
	//id=>NUMBER(10)
	//codigo=>VARCHAR2(8)
	//nombre=>VARCHAR2(30)
	//abreviado=>VARCHAR2(20)
	//nombreGenerico=>VARCHAR2(30)
	//cambioDescripcion=>VARCHAR2(1)
	//empresaId=>NUMBER(10)
	//tipoproductoId=>NUMBER(10)
	//medidaId=>NUMBER(10)
	//partidaArancelaria=>VARCHAR2(30)
	//fechaCreacion=>DATE
	//referencia=>VARCHAR2(20)
	//usaLote=>VARCHAR2(1)
	//lineaId=>NUMBER(10)
	//iva=>NUMBER(5, 2)
	//ice=>NUMBER(5, 2)
	//otroImpuesto=>NUMBER(5, 2)
	//servicio=>VARCHAR2(1)
	//familiaGenericoId=>NUMBER(10)
	//serie=>VARCHAR2(1)
	//afectastock=>VARCHAR2(1)
	//estado=>VARCHAR2(1)
	//cobraIva=>VARCHAR2(255)
	//cobraIce=>VARCHAR2(255)
	//mediosProduccion=>VARCHAR2(1)
	
	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/generico.xml");
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
	public void addGenerico() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		GenericoIf resultado = getGenericoSessionService().addGenerico(value);
		
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
		Assert.assertEquals(resultado.getNombreGenerico(),"NOMBRE_GENERICO1"); 
		Assert.assertEquals(resultado.getCambioDescripcion(),"N"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoproductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getMedidaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPartidaArancelaria(),"PARTIDA_ARANCELARIA1"); 
		Assert.assertEquals(resultado.getFechaCreacion(),now); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getUsaLote(),"N"); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getServicio(),"N"); 
		Assert.assertEquals(resultado.getFamiliaGenericoId(),new Long(1)); 
		Assert.assertEquals(resultado.getSerie(),"N"); 
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getCobraIva(),"N"); 
		Assert.assertEquals(resultado.getCobraIce(),"N");
		Assert.assertEquals(resultado.getMediosProduccion(), "M");
		
	}
	
	@Test (timeout=2000)
	public void saveGenerico() throws Exception {
		GenericoIf value = getGenericoSessionService().getGenerico(new Long(1));
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("P");
		
		getGenericoSessionService().saveGenerico(value);
		
		GenericoIf resultado = getGenericoSessionService().getGenerico(new Long(1));
		
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
		Assert.assertEquals(resultado.getNombreGenerico(),"NOMBRE_GENERICO1"); 
		Assert.assertEquals(resultado.getCambioDescripcion(),"N"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoproductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getMedidaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPartidaArancelaria(),"PARTIDA_ARANCELARIA1"); 
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getUsaLote(),"N"); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getServicio(),"N"); 
		Assert.assertEquals(resultado.getFamiliaGenericoId(),new Long(1)); 
		Assert.assertEquals(resultado.getSerie(),"N"); 
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getCobraIva(),"N"); 
		Assert.assertEquals(resultado.getCobraIce(),"N"); 
		Assert.assertEquals(resultado.getMediosProduccion(), "P");
	}
	
	
	@Test (timeout=2000)
	public void deleteGenerico() throws Exception {
		getGenericoSessionService().deleteGenerico(new Long(3));
		GenericoIf resultado = getGenericoSessionService().getGenerico(new Long(3));
		Assert.assertNull(resultado);
	}
	
	
	
	
	@Test (timeout=2000)
	public void findGenericoById() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoById(new Long(1)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByCodigo() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByCodigo("COD"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByNombre() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByNombre("NOMBRE1"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByAbreviado() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByAbreviado("ABREVIADO1"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByNombreGenerico() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByNombreGenerico("NOMBRE_GENERICO1"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getNombreGenerico(),"NOMBRE_GENERICO1"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByCambioDescripcion() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByCambioDescripcion("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCambioDescripcion(),"N"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByEmpresaId() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByEmpresaId(new Long(1)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByTipoproductoId() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByTipoproductoId(new Long(1)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getTipoproductoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByMedidaId() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByMedidaId(new Long(1)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getMedidaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByPartidaArancelaria() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getPartidaArancelaria(),"PARTIDA_ARANCELARIA1"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByFechaCreacion() throws Exception {
		GenericoIf value = new GenericoData();
		
		Date date = new Date(System.currentTimeMillis());
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByFechaCreacion(new Timestamp(date.getTime())); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFechaCreacion().toString(),date.toString()); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByReferencia() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByReferencia("REFERENCIA1"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByUsaLote() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByUsaLote("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getUsaLote(),"N"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByLineaId() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByLineaId(new Long(1)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByIva() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByIva(new BigDecimal(5)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByIce() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByIce(new BigDecimal(5)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByOtroImpuesto() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByOtroImpuesto(new BigDecimal(5)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByServicio() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByServicio("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getServicio(),"N"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByFamiliaGenericoId() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByFamiliaGenericoId(new Long(1)); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getFamiliaGenericoId(),new Long(1)); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoBySerie() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoBySerie("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getSerie(),"N"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByAfectastock() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByAfectastock("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByEstado() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByEstado("A"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getEstado(),"A"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByCobraIva() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByCobraIva("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCobraIva(),"N"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByCobraIce() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("M");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByCobraIce("N"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getCobraIce(),"N"); 
	}
	
	@Test (timeout=2000)
	public void findGenericoByMediosProduccion() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("x");
		
		getGenericoSessionService().addGenerico(value);
		
		Collection c = getGenericoSessionService().findGenericoByMediosProduccion("x"); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		Assert.assertEquals(resultado.getMediosProduccion(),"x"); 
		
	}
	
	@Test (timeout=2000)
	public void findGenericoByQuery() throws Exception {
		GenericoIf value = new GenericoData();
		
		value.setCodigo("COD"); 
		value.setNombre("NOMBRE1"); 
		value.setAbreviado("ABREVIADO1"); 
		value.setNombreGenerico("NOMBRE_GENERICO1"); 
		value.setCambioDescripcion("N"); 
		value.setEmpresaId(new Long(1)); 
		value.setTipoproductoId(new Long(1)); 
		value.setMedidaId(new Long(1)); 
		value.setPartidaArancelaria("PARTIDA_ARANCELARIA1"); 
		value.setFechaCreacion(now); 
		value.setReferencia("REFERENCIA1"); 
		value.setUsaLote("N"); 
		value.setLineaId(new Long(1)); 
		value.setIva(new BigDecimal(5)); 
		value.setIce(new BigDecimal(5)); 
		value.setOtroImpuesto(new BigDecimal(5)); 
		value.setServicio("N"); 
		value.setFamiliaGenericoId(new Long(1)); 
		value.setSerie("N"); 
		value.setAfectastock("N"); 
		value.setEstado("A"); 
		value.setCobraIva("N"); 
		value.setCobraIce("N"); 
		value.setMediosProduccion("y");
		
		getGenericoSessionService().addGenerico(value);
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("codigo","COD"); 
		parametros.put("nombre","NOMBRE1"); 
		parametros.put("abreviado","ABREVIADO1"); 
		parametros.put("nombreGenerico","NOMBRE_GENERICO1"); 
		parametros.put("cambioDescripcion","N"); 
		parametros.put("empresaId",new Long(1)); 
		parametros.put("tipoproductoId",new Long(1)); 
		parametros.put("medidaId",new Long(1)); 
		parametros.put("partidaArancelaria","PARTIDA_ARANCELARIA1"); 
		parametros.put("fechaCreacion",now); 
		parametros.put("referencia","REFERENCIA1"); 
		parametros.put("usaLote","N"); 
		parametros.put("lineaId",new Long(1)); 
		parametros.put("iva",new BigDecimal(5)); 
		parametros.put("ice",new BigDecimal(5)); 
		parametros.put("otroImpuesto",new BigDecimal(5)); 
		parametros.put("servicio","N"); 
		parametros.put("familiaGenericoId",new Long(1)); 
		parametros.put("serie","N"); 
		parametros.put("afectastock","N"); 
		parametros.put("estado","A"); 
		parametros.put("cobraIva","N"); 
		parametros.put("cobraIce","N");
		parametros.put("mediosProduccion", "y");
		
		Collection c = getGenericoSessionService().findGenericoByQuery(parametros); 
		GenericoIf resultado = (GenericoIf)c.iterator().next();
		
		Assert.assertEquals(resultado.getCodigo(),"COD"); 
		Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
		Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
		Assert.assertEquals(resultado.getNombreGenerico(),"NOMBRE_GENERICO1"); 
		Assert.assertEquals(resultado.getCambioDescripcion(),"N"); 
		Assert.assertEquals(resultado.getEmpresaId(),new Long(1)); 
		Assert.assertEquals(resultado.getTipoproductoId(),new Long(1)); 
		Assert.assertEquals(resultado.getMedidaId(),new Long(1)); 
		Assert.assertEquals(resultado.getPartidaArancelaria(),"PARTIDA_ARANCELARIA1"); 
		Assert.assertEquals(resultado.getFechaCreacion().toString(),now.toString()); 
		Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
		Assert.assertEquals(resultado.getUsaLote(),"N"); 
		Assert.assertEquals(resultado.getLineaId(),new Long(1)); 
		Assert.assertEquals(resultado.getIva(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getIce(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getOtroImpuesto(),new BigDecimal(5)); 
		Assert.assertEquals(resultado.getServicio(),"N"); 
		Assert.assertEquals(resultado.getFamiliaGenericoId(),new Long(1)); 
		Assert.assertEquals(resultado.getSerie(),"N"); 
		Assert.assertEquals(resultado.getAfectastock(),"N"); 
		Assert.assertEquals(resultado.getEstado(),"A"); 
		Assert.assertEquals(resultado.getCobraIva(),"N"); 
		Assert.assertEquals(resultado.getCobraIce(),"N");  
		Assert.assertEquals(resultado.getMediosProduccion(), "y");
		
	}
	
	public static GenericoSessionService getGenericoSessionService() {
		try {
			return (GenericoSessionService) ServiceLocator.getService(ServiceLocator.GENERICOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(GenericoSessionTest.class);
		
	}
}
