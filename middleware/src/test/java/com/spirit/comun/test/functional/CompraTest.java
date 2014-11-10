package com.spirit.comun.test.functional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.spirit.client.SpiritAlert;
import com.spirit.compras.entity.CompraData;
import com.spirit.compras.entity.CompraDetalleData;
import com.spirit.compras.entity.CompraDetalleIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.CompraRetencionIf;
import com.spirit.compras.gastos.CompraGastoClase;
import com.spirit.compras.session.CompraDetalleSessionService;
import com.spirit.compras.session.CompraSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.server.StandAloneServer;
import com.spirit.servicelocator.ServiceLocator;

public class CompraTest {
	
	Date now = new Date(System.currentTimeMillis());

	@BeforeClass
	public static void startup() {

		StandAloneServer.start(null);

	}

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/functional/compra.xml");
		IDataSet dataSet = new FlatXmlDataSet(file);
		return dataSet;
	}

	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(
				DBUnitConnection.getConnection(), getDataSet());
	}

	@After
	public void tearDown() throws Exception {
		DatabaseOperation.DELETE_ALL.execute(DBUnitConnection.getConnection(),
				getDataSet());
	}
	
	@Test
	// (timeout=5000)
	public void procesarCompra() throws Exception {
		
		CompraIf sc = new CompraData();
			
		//sc.setId(100L);
		sc.setAutorizacion("AUT100");
		sc.setCodigo("0001");
		sc.setEstado("E");
		//sc.setEstadoBpm(estadoBpm);
		sc.setFecha(now);
		sc.setFechaCaducidad(now);
		sc.setFechaVencimiento(now);
		sc.setValor(new BigDecimal(100));
		sc.setIva(new BigDecimal(12));
		sc.setIce(new BigDecimal(0));
		sc.setRetencion(new BigDecimal(10));
		sc.setDescuento(new BigDecimal(0));
		sc.setIdSriSustentoTributario(1L);
		sc.setLocalimportada("L");
		sc.setMonedaId(1L);
		sc.setObservacion("COMPRA PARA INVENTARIO ");
		sc.setOficinaId(1L);
		sc.setOrdencompraId(1L);
		sc.setOtroImpuesto(new BigDecimal(0));
		sc.setPreimpreso("PRE-1");
		sc.setProveedorId(1L);
		sc.setReferencia("REF-0001");
		sc.setTipodocumentoId(1L);
		sc.setUsuarioId(1L);
		
	

		CompraDetalleIf cd1 = new CompraDetalleData();
		//cd1.setId(id);
		cd1.setCantidad(10L);
		cd1.setCompraId(1L);
		cd1.setDescripcion("COMPRA DE 10 ITEMS");
		cd1.setDescuento(new BigDecimal(0));
		cd1.setDocumentoId(1L);
		cd1.setIce(new BigDecimal(0));
	
		cd1.setIdSriAir(1L);
		cd1.setIva(new BigDecimal(0));
		cd1.setOtroImpuesto(new BigDecimal(0));
		cd1.setProductoId(1L);
		cd1.setValor(new BigDecimal(50));
			
		cd1.setDocumentoId(1L);
		cd1.setProductoId(1L);
		cd1.setCantidad(10L);
		
/*
		CompraDetalleIf scd2 = new CompraDetalleData();

		// scd2.setSolicitudcompraId(1L);
		scd2.setDocumentoId(1L);
		scd2.setProductoId(2L);
		scd2.setCantidad(10L);
*/
		List<CompraDetalleIf> scdList = new ArrayList<CompraDetalleIf>();
		scdList.add(cd1);
		//scdList.add(scd2);
		
		Vector<CompraRetencionIf> retencionList = new Vector<CompraRetencionIf>();

		getCompraSessionService().procesarCompra(sc, scdList, 1L, 1L, 1L, retencionList, 1L, null,new CompraGastoClase());
		
		CompraIf resultado = (CompraIf) getCompraSessionService().getCompraList().iterator().next();
				//.find SolicitudCompraByCodigo("0001").iterator().next();

		Collection c = getCompraDetalleSessionService()
				.findCompraDetalleByCompraId(
						resultado.getId());
		
		CompraDetalleIf resultadoDetalle1 = (CompraDetalleIf)c.iterator().next();
		CompraDetalleIf resultadoDetalle2 = (CompraDetalleIf)c.iterator().next();

		Assert.assertEquals(resultado.getCodigo(), "0001");
		Assert.assertEquals(resultadoDetalle1.getProductoId(), 1L);
		Assert.assertEquals(resultadoDetalle1.getProductoId(), 2L);
		
		
	}
	
	
	public static CompraSessionService getCompraSessionService() {
		try {
			return (CompraSessionService) ServiceLocator.getService(ServiceLocator.COMPRASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static CompraDetalleSessionService getCompraDetalleSessionService() {
		try {
			return (CompraDetalleSessionService) ServiceLocator.getService(ServiceLocator.COMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	

}
