package com.spirit.comun.test.functional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.spirit.general.entity.UsuarioData;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.inventario.entity.MovimientoData;
import com.spirit.inventario.entity.MovimientoDetalleData;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.session.MovimientoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.server.StandAloneServer;
import com.spirit.servicelocator.ServiceLocator;

public class MovimientoTest {
	
	Timestamp now = new Timestamp(System.currentTimeMillis());

	@BeforeClass
	public static void startup() {

		StandAloneServer.start(null);

	}

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/functional/movimiento.xml");
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
	public void procesarMovimiento() throws Exception {
		UsuarioIf u = new UsuarioData();
		u.setId(new Long(1));
		u.setUsuario("usuario");
		u.setTipousuario("U");
		u.setEmpresaId(new Long(1));
		u.setEmpleadoId(new Long(1));
		
		MovimientoIf m = new MovimientoData();
		  m.setTipodocumentoId(new Long(1)); 
	       m.setCodigo("1"); 
	       m.setBodegaId(new Long(1)); 
	       m.setBodegarefId(new Long(1));
	       m.setOrdencompraId(new Long(1));
	       m.setCompraId(new Long(1));
	       //m.setReferencia("REFERENCIA1"); 
	       m.setFechaCreacion(now); 
	       m.setFechaDocumento(now); 
	       m.setCosto(new BigDecimal(1));
	       m.setPrecio(new BigDecimal(1));
	       m.setPreimpreso("PREIMPRESO1"); 
	       m.setUsuarioId(new Long(1));
	       m.setUsuarioautId(new Long(1)); 
	       m.setObservacion("OBSERVACION1"); 
	       m.setEstado("E"); 
	       
	       MovimientoDetalleIf md1 = new MovimientoDetalleData();
	       md1.setMovimientoId(new Long(1)); 
	       md1.setDocumentoId(new Long(1)); 
	       md1.setLoteId(new Long(1)); 
	       md1.setCantidad(new BigDecimal(1)); 
	       md1.setCosto(new BigDecimal(1)); 
	       md1.setPrecio(new BigDecimal(1));
	       md1.setCostoLifo(new BigDecimal(1)); 
	       md1.setCostoFifo(new BigDecimal(1)); 
	       
	       MovimientoDetalleIf md2 = new MovimientoDetalleData();
	       md2.setMovimientoId(new Long(1)); 
	       md2.setDocumentoId(new Long(1)); 
	       md2.setLoteId(new Long(1)); 
	       md2.setCantidad(new BigDecimal(1)); 
	       md2.setCosto(new BigDecimal(1)); 
	       md2.setPrecio(new BigDecimal(1));
	       md2.setCostoLifo(new BigDecimal(1)); 
	       md2.setCostoFifo(new BigDecimal(1)); 
	       
	       List<MovimientoDetalleIf> mdList = new ArrayList<MovimientoDetalleIf>();
	       mdList.add(md1);
	       mdList.add(md2);
	       getMovimientoSessionService().procesarMovimiento(m, mdList, u);
		
		
	}
	
	public static MovimientoSessionService getMovimientoSessionService() {
		try {
			return (MovimientoSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

}
