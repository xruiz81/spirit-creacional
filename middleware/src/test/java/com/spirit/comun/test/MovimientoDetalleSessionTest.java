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

import com.spirit.inventario.entity.MovimientoDetalleData;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.session.MovimientoDetalleSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class MovimientoDetalleSessionTest {


   //id=>NUMBER(10)
   //movimientoId=>NUMBER(10)
   //documentoId=>NUMBER(10)
   //loteId=>NUMBER(10)
   //cantidad=>NUMBER(22)
   //costo=>NUMBER(22)
   //precio=>NUMBER(22)
   //costoLifo=>NUMBER(22)
   //costoFifo=>NUMBER(22)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/movimiento_detalle.xml");
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
   public void addMovimientoDetalle() throws Exception {
     MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
       value.setMovimientoId(new Long(1)); 
       value.setDocumentoId(new Long(1)); 
       value.setLoteId(new Long(1)); 
       value.setCantidad(new BigDecimal(1)); 
       value.setCosto(new BigDecimal(1)); 
       value.setPrecio(new BigDecimal(1));
       value.setCostoLifo(new BigDecimal(1)); 
       value.setCostoFifo(new BigDecimal(1)); 
      
     MovimientoDetalleIf resultado = getMovimientoDetalleSessionService().addMovimientoDetalle(value);
           
       Assert.assertEquals(resultado.getMovimientoId(),new Long(1)); 
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getCosto(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getPrecio(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1)); 
		
   }

  @Test (timeout=2000)
  public void saveMovimientoDetalle() throws Exception {
      
    MovimientoDetalleIf value = getMovimientoDetalleSessionService().getMovimientoDetalle(new Long(1));
 
          
    value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(2)); 
    value.setCosto(new BigDecimal(2)); 
    value.setPrecio(new BigDecimal(2));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
     getMovimientoDetalleSessionService().saveMovimientoDetalle(value);

     MovimientoDetalleIf resultado = getMovimientoDetalleSessionService().getMovimientoDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getMovimientoId(),new Long(1)); 
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getLoteId(),new Long(1)); 
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getCosto(),new BigDecimal(2)); 
       Assert.assertEquals(resultado.getPrecio(),new BigDecimal(2));
       Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 
       Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1));
		
   }


 @Test (timeout=2000)
 public void deleteMovimientoDetalle() throws Exception {
      getMovimientoDetalleSessionService().deleteMovimientoDetalle(new Long(3));
      MovimientoDetalleIf resultado = getMovimientoDetalleSessionService().getMovimientoDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findMovimientoDetalleById() throws Exception {
       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleById(new Long(1)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByMovimientoId() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(2)); 
    value.setCosto(new BigDecimal(2)); 
    value.setPrecio(new BigDecimal(2));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByMovimientoId(new Long(1)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getMovimientoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByDocumentoId() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(2)); 
    value.setCosto(new BigDecimal(2)); 
    value.setPrecio(new BigDecimal(2));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByDocumentoId(new Long(1)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByLoteId() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(2)); 
    value.setCosto(new BigDecimal(2)); 
    value.setPrecio(new BigDecimal(2));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByLoteId(new Long(1)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLoteId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByCantidad() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(5)); 
    value.setCosto(new BigDecimal(2)); 
    value.setPrecio(new BigDecimal(2));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByCantidad(new BigDecimal(5)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCantidad(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByCosto() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(5)); 
    value.setCosto(new BigDecimal(5)); 
    value.setPrecio(new BigDecimal(2));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByCosto(new BigDecimal(5)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCosto(),new BigDecimal(5)); 

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByPrecio() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(5)); 
    value.setCosto(new BigDecimal(5)); 
    value.setPrecio(new BigDecimal(5));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByPrecio(new BigDecimal(5));
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getPrecio(),new BigDecimal(5));

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByCostoLifo() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(5)); 
    value.setCosto(new BigDecimal(5)); 
    value.setPrecio(new BigDecimal(5));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByCostoLifo(new BigDecimal(1)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 

   }

   @Test (timeout=2000)
   public void findMovimientoDetalleByCostoFifo() throws Exception {

 	MovimientoDetalleIf value = new MovimientoDetalleData();
 
      
 	value.setMovimientoId(new Long(1)); 
    value.setDocumentoId(new Long(1)); 
    value.setLoteId(new Long(1)); 
    value.setCantidad(new BigDecimal(5)); 
    value.setCosto(new BigDecimal(5)); 
    value.setPrecio(new BigDecimal(5));
    value.setCostoLifo(new BigDecimal(1)); 
    value.setCostoFifo(new BigDecimal(1));
      
       getMovimientoDetalleSessionService().addMovimientoDetalle(value);

       Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByCostoFifo(new BigDecimal(1)); 
       MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1)); 

   }

    
    @Test (timeout=2000)
    public void findMovimientoDetalleByQuery() throws Exception {
 	
    	MovimientoDetalleIf value = new MovimientoDetalleData();
 
       
    	value.setMovimientoId(new Long(1)); 
    	value.setDocumentoId(new Long(1)); 
    	value.setLoteId(new Long(1)); 
    	value.setCantidad(new BigDecimal(7)); 
    	value.setCosto(new BigDecimal(7)); 
    	value.setPrecio(new BigDecimal(7));
    	value.setCostoLifo(new BigDecimal(1)); 
        value.setCostoFifo(new BigDecimal(1));
      
      getMovimientoDetalleSessionService().addMovimientoDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("movimientoId",new Long(1)); 
       parametros.put("documentoId",new Long(1)); 
       parametros.put("loteId",new Long(1)); 
       parametros.put("cantidad",new BigDecimal(7));  
       parametros.put("costo",new BigDecimal(7)); 
       parametros.put("precio",new BigDecimal(7));
       parametros.put("costoLifo",new BigDecimal(1)); 
       parametros.put("costoFifo",new BigDecimal(1)); 

     Collection c = getMovimientoDetalleSessionService().findMovimientoDetalleByQuery(parametros); 
     MovimientoDetalleIf resultado = (MovimientoDetalleIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getMovimientoId(),new Long(1)); 
      Assert.assertEquals(resultado.getDocumentoId(),new Long(1)); 
      Assert.assertEquals(resultado.getLoteId(),new Long(1));  
      Assert.assertEquals(resultado.getCantidad(),new BigDecimal(7)); 
      Assert.assertEquals(resultado.getCosto(),new BigDecimal(7)); 
      Assert.assertEquals(resultado.getPrecio(),new BigDecimal(7));
      Assert.assertEquals(resultado.getCostoLifo(),new BigDecimal(1)); 
      Assert.assertEquals(resultado.getCostoFifo(),new BigDecimal(1));


    }

public static MovimientoDetalleSessionService getMovimientoDetalleSessionService() {
		try {
			return (MovimientoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(MovimientoDetalleSessionTest.class);

 }


 


}
