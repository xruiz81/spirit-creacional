package com.spirit.comun.test;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.SaldoCuentaIf;
import com.spirit.contabilidad.session.AsientoDetalleSessionService;
import com.spirit.contabilidad.session.AsientoSessionService;
import com.spirit.contabilidad.session.SaldoCuentaSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;

public class ProcesarAsientoTest {
	Date now = new Date(System.currentTimeMillis());

	private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/procesar_asiento.xml");
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

	@Test (timeout=60*1000)
	public void procesarAsiento() throws Exception {
		AsientoIf asiento = new AsientoData();
	    asiento.setEmpresaId(new Long(1));
	    asiento.setPeriodoId(new Long(1));
	    asiento.setPlancuentaId(new Long(1)); 
	    asiento.setFecha(now); 
	    asiento.setStatus("A"); 
	    asiento.setEfectivo("N"); 
	    asiento.setOficinaId(new Long(1)); 
	     
	    AsientoDetalleIf ad1 = new AsientoDetalleData();
	    ad1.setCuentaId(new Long(2)); 
	    ad1.setDebe(new BigDecimal(5000)); 
	    ad1.setHaber(new BigDecimal(0)); 
	       
	    AsientoDetalleIf ad2 = new AsientoDetalleData();
	    ad2.setCuentaId(new Long(4));  
	    ad2.setDebe(new BigDecimal(0)); 
	    ad2.setHaber(new BigDecimal(5000));
	    
	    List<AsientoDetalleIf> asientoDetalleList = new LinkedList<AsientoDetalleIf>();
	    
	    asientoDetalleList.add(ad1);
	    asientoDetalleList.add(ad2);
	      
	    //BORRADO DE REGISTROS CREADOS
	    String asientoNumero = getAsientoSessionService().procesarAsiento(asiento, asientoDetalleList); 
	    AsientoIf asientoIf = verificarAsientoGuardado(asientoNumero);
        verificarSaldos(asientoIf);
        
        Collection<AsientoDetalleIf> asientoDetalleCollection = getAsientoDetalleSessionService().getAsientoDetalleList();
        for ( AsientoDetalleIf ad : asientoDetalleCollection )
        	getAsientoDetalleSessionService().deleteAsientoDetalle(ad.getId());
        //getAsientoDetalleSessionService().deleteAsientoDetalle(ad1.getId());
        //getAsientoDetalleSessionService().deleteAsientoDetalle(ad2.getId());
        
        Collection<AsientoIf> asientoCollection = getAsientoSessionService().getAsientoList();
        for ( AsientoIf a : asientoCollection )
        	getAsientoSessionService().deleteAsiento(a.getId());
        //getAsientoSessionService().deleteAsiento(asiento.getId());
        //getAsientoSessionService().deleteAsiento(asientoIf.getId());
        
        Collection<SaldoCuentaIf> saldoCuentaCollection = getSaldoCuentaSessionService().getSaldoCuentaList();
        for ( SaldoCuentaIf sc : saldoCuentaCollection )
        	getSaldoCuentaSessionService().deleteSaldoCuenta(sc.getId());
        
	}

	private AsientoIf verificarAsientoGuardado(String asientoNumero) throws GenericBusinessException {
		Map parameterMap = new HashMap();
	    
	    /* Verificamos si se guardó el asiento correctamente */
	    parameterMap.put("numero", asientoNumero);
	    parameterMap.put("empresaId", new Long(1));
	    Iterator asientoIterator = getAsientoSessionService().findAsientoByQuery(parameterMap).iterator();
	    AsientoIf asientoIf = (AsientoIf) asientoIterator.next();
	    Assert.assertEquals(asientoIf.getNumero(), asientoNumero); 
	    Assert.assertEquals(asientoIf.getEmpresaId(), new Long(1)); 
	    Assert.assertEquals(asientoIf.getPeriodoId(), new Long(1)); 
	    Assert.assertEquals(asientoIf.getPlancuentaId(), new Long(1)); 
	    Assert.assertEquals(asientoIf.getFecha(), now); 
	    Assert.assertEquals(asientoIf.getStatus(), "A"); 
	    Assert.assertEquals(asientoIf.getEfectivo(),"N");   
	    Assert.assertEquals(asientoIf.getOficinaId(), new Long(1));
	    
	    /* Verificamos si los detalles del asiento se guardaron correctamente */
	    parameterMap.clear();
	    parameterMap.put("cuentaId", new Long(2));
	    parameterMap.put("asientoId", asientoIf.getId());
	    Iterator asientoDetalleIterator = getAsientoDetalleSessionService().findAsientoDetalleByQuery(parameterMap).iterator();
	    AsientoDetalleIf asientoDetalleIf = (AsientoDetalleIf) asientoDetalleIterator.next();   	
    	Assert.assertEquals(asientoDetalleIf.getCuentaId(), new Long(2)); 
        Assert.assertEquals(asientoDetalleIf.getAsientoId(), asientoIf.getId());  
        Assert.assertEquals(asientoDetalleIf.getDebe(), new BigDecimal(5000)); 
        Assert.assertEquals(asientoDetalleIf.getHaber(),new BigDecimal(0));
        
        parameterMap.clear();
	    parameterMap.put("cuentaId", new Long(4));
	    parameterMap.put("asientoId", asientoIf.getId());
	    asientoDetalleIterator = getAsientoDetalleSessionService().findAsientoDetalleByQuery(parameterMap).iterator();
	    asientoDetalleIf = (AsientoDetalleIf) asientoDetalleIterator.next();   	
    	Assert.assertEquals(asientoDetalleIf.getCuentaId(), new Long(4)); 
        Assert.assertEquals(asientoDetalleIf.getAsientoId(), asientoIf.getId());  
        Assert.assertEquals(asientoDetalleIf.getDebe(), new BigDecimal(0)); 
        Assert.assertEquals(asientoDetalleIf.getHaber(),new BigDecimal(5000));
        
        return asientoIf;
	}
	
	private void verificarSaldos(AsientoIf asientoIf) throws GenericBusinessException {
		/* Si el estado del asiento no es PRE-ASIENTO, verificamos si los saldos de las cuentas se afectaron correctamente */
	    if (!asientoIf.getStatus().equals("P")) {
	    	Map parameterMap = new HashMap();
	    	parameterMap.put("periodoId", new Long(1));
	    	parameterMap.put("cuentaId", new Long(2));
	    	parameterMap.put("anio", now.getYear());
	    	parameterMap.put("mes", now.getMonth());
	    	Iterator saldoCuentaIterator = getSaldoCuentaSessionService().findSaldoCuentaByQuery(parameterMap).iterator();
	    	SaldoCuentaIf saldoCuentaIf = (SaldoCuentaIf) saldoCuentaIterator.next();
	    	Assert.assertEquals(saldoCuentaIf.getCuentaId(), new Long(2)); 
	    	Assert.assertEquals(saldoCuentaIf.getPeriodoId(), new Long(1)); 
	    	Assert.assertEquals(saldoCuentaIf.getAnio(), now.getYear()); 
	    	Assert.assertEquals(saldoCuentaIf.getMes(), now.getMonth()); 
	    	Assert.assertEquals(saldoCuentaIf.getValordebe(), new BigDecimal(5000)); 
	    	Assert.assertEquals(saldoCuentaIf.getValorhaber(),new BigDecimal(0)); 
	    	
	    	parameterMap.clear();
	    	parameterMap.put("periodoId", new Long(1));
	    	parameterMap.put("cuentaId", new Long(4));
	    	parameterMap.put("anio", now.getYear());
	    	parameterMap.put("mes", now.getMonth());
	    	saldoCuentaIterator = getSaldoCuentaSessionService().findSaldoCuentaByQuery(parameterMap).iterator();
	    	saldoCuentaIf = (SaldoCuentaIf) saldoCuentaIterator.next();
	    	Assert.assertEquals(saldoCuentaIf.getCuentaId(), new Long(4)); 
	    	Assert.assertEquals(saldoCuentaIf.getPeriodoId(), new Long(1)); 
	    	Assert.assertEquals(saldoCuentaIf.getAnio(), now.getYear()); 
	    	Assert.assertEquals(saldoCuentaIf.getMes(), now.getMonth()); 
	    	Assert.assertEquals(saldoCuentaIf.getValordebe(), new BigDecimal(0)); 
	    	Assert.assertEquals(saldoCuentaIf.getValorhaber(),new BigDecimal(5000));
	    }
	}

	public static AsientoSessionService getAsientoSessionService() {
		try {
			return (AsientoSessionService) ServiceLocator.getService(ServiceLocator.ASIENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static AsientoDetalleSessionService getAsientoDetalleSessionService() {
		try {
			return (AsientoDetalleSessionService) ServiceLocator.getService(ServiceLocator.ASIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static SaldoCuentaSessionService getSaldoCuentaSessionService() {
		try {
			return (SaldoCuentaSessionService) ServiceLocator.getService(ServiceLocator.SALDOCUENTASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AsientoSessionTest.class);
	}
}
