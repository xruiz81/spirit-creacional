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

import com.spirit.contabilidad.entity.CuentaData;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.session.CuentaSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;



public class CuentaSessionTest {


   //id=>NUMBER(10)
   //plancuentaId=>NUMBER(10)
   //codigo=>VARCHAR2(50)
   //nombre=>VARCHAR2(100)
   //nombreCorto=>VARCHAR2(20)
   //tipocuentaId=>NUMBER(10)
   //padreId=>NUMBER(10)
   //relacionada=>NUMBER(10)
   //imputable=>VARCHAR2(1)
   //nivel=>NUMBER(3)
   //tiporesultadoId=>NUMBER(10)
   //efectivo=>VARCHAR2(1)
   //activofijo=>VARCHAR2(1)
   //departamento=>VARCHAR2(1)
   //linea=>VARCHAR2(1)
   //empleado=>VARCHAR2(1)
   //centrogasto=>CHAR(1)
   //cliente=>VARCHAR2(1)
   //gasto=>VARCHAR2(1)
   //estado=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/cuenta.xml");
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
   public void addCuenta() throws Exception {
     CuentaIf value = new CuentaData();
 
      
       value.setPlancuentaId(new Long(1)); 
       value.setCodigo("CODIGO1"); 
       value.setNombre("NOMBRE1"); 
       value.setNombreCorto("NOMBRE_CORTO1"); 
       value.setTipocuentaId(new Long(1));  
       value.setPadreId(new Long(1));  
       value.setRelacionada(new Long(1));  
       value.setImputable("I"); 
       value.setNivel(1); 
       value.setTiporesultadoId(new Long(1));  
       value.setEfectivo("E"); 
       value.setActivofijo("A"); 
       value.setDepartamento("D"); 
       value.setLinea("L"); 
       value.setEmpleado("E"); 
       value.setCentrogasto("C"); 
       value.setCliente("C"); 
       value.setGasto("G"); 
       value.setEstado("A");
      
     CuentaIf resultado = getCuentaSessionService().addCuenta(value);
           
       Assert.assertEquals(resultado.getPlancuentaId(),new Long(1));  
       Assert.assertEquals(resultado.getCodigo(),"CODIGO1"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getNombreCorto(),"NOMBRE_CORTO1"); 
       Assert.assertEquals(resultado.getTipocuentaId(),new Long(1));  
       Assert.assertEquals(resultado.getPadreId(),new Long(1));  
       Assert.assertEquals(resultado.getRelacionada(),new Long(1));  
       Assert.assertEquals(resultado.getImputable(),"I"); 
       Assert.assertEquals(resultado.getNivel(),1); 
       Assert.assertEquals(resultado.getTiporesultadoId(),new Long(1));  
       Assert.assertEquals(resultado.getEfectivo(),"E"); 
       Assert.assertEquals(resultado.getActivofijo(),"A"); 
       Assert.assertEquals(resultado.getDepartamento(),"D"); 
       Assert.assertEquals(resultado.getLinea(),"L"); 
       Assert.assertEquals(resultado.getEmpleado(),"E"); 
       Assert.assertEquals(resultado.getCentrogasto(),"C"); 
       Assert.assertEquals(resultado.getCliente(),"C"); 
       Assert.assertEquals(resultado.getGasto(),"G");
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }

  @Test (timeout=2000)
  public void saveCuenta() throws Exception {
      
    CuentaIf value = getCuentaSessionService().getCuenta(new Long(1));
 
          
    value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A");
    value.setEstado("A");
      
     getCuentaSessionService().saveCuenta(value);

     CuentaIf resultado = getCuentaSessionService().getCuenta(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 
       Assert.assertEquals(resultado.getCodigo(),"CODIGO2"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE2"); 
       Assert.assertEquals(resultado.getNombreCorto(),"NOMBRE_CORTO2"); 
       Assert.assertEquals(resultado.getTipocuentaId(),new Long(1)); 
       Assert.assertEquals(resultado.getPadreId(),new Long(1)); 
       Assert.assertEquals(resultado.getRelacionada(),new Long(1)); 
       Assert.assertEquals(resultado.getImputable(),"A"); 
       Assert.assertEquals(resultado.getNivel(),1); 
       Assert.assertEquals(resultado.getTiporesultadoId(),new Long(1)); 
       Assert.assertEquals(resultado.getEfectivo(),"A"); 
       Assert.assertEquals(resultado.getActivofijo(),"A"); 
       Assert.assertEquals(resultado.getDepartamento(),"A"); 
       Assert.assertEquals(resultado.getLinea(),"A"); 
       Assert.assertEquals(resultado.getEmpleado(),"A"); 
       Assert.assertEquals(resultado.getCentrogasto(),"A"); 
       Assert.assertEquals(resultado.getCliente(),"A"); 
       Assert.assertEquals(resultado.getGasto(),"A");
       Assert.assertEquals(resultado.getEstado(),"A"); 
		
   }


 @Test (timeout=2000)
 public void deleteCuenta() throws Exception {
      getCuentaSessionService().deleteCuenta(new Long(3));
      CuentaIf resultado = getCuentaSessionService().getCuenta(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findCuentaById() throws Exception {
       Collection c = getCuentaSessionService().findCuentaById(new Long(1)); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 
   }

   @Test (timeout=2000)
   public void findCuentaByPlancuentaId() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A");
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByPlancuentaId(new Long(1)); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCuentaByCodigo() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGOx"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByCodigo("CODIGOx"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"CODIGOx"); 

   }

   @Test (timeout=2000)
   public void findCuentaByNombre() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBREx"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByNombre("NOMBREx"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBREx"); 

   }

   @Test (timeout=2000)
   public void findCuentaByNombreCorto() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTOx"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByNombreCorto("NOMBRE_CORTOx"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombreCorto(),"NOMBRE_CORTOx"); 

   }

   @Test (timeout=2000)
   public void findCuentaByTipocuentaId() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByTipocuentaId(new Long(1)); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipocuentaId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCuentaByPadreId() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByPadreId(new Long(1)); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getPadreId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCuentaByRelacionada() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByRelacionada(new Long(1)); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getRelacionada(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCuentaByImputable() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("x"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByImputable("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getImputable(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByNivel() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(3); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByNivel(3); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getNivel(),3); 

   }

   @Test (timeout=2000)
   public void findCuentaByTiporesultadoId() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByTiporesultadoId(new Long(1)); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getTiporesultadoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findCuentaByEfectivo() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("x"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByEfectivo("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEfectivo(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByActivofijo() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("x"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByActivofijo("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getActivofijo(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByDepartamento() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("x"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByDepartamento("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getDepartamento(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByLinea() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("x"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByLinea("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getLinea(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByEmpleado() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("x"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByEmpleado("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpleado(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByCentrogasto() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("x"); 
    value.setCliente("A"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByCentrogasto("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCentrogasto(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByCliente() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("x"); 
    value.setGasto("A"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByCliente("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getCliente(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByGasto() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("x"); 
    value.setEstado("A");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByGasto("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getGasto(),"x"); 

   }

   @Test (timeout=2000)
   public void findCuentaByEstado() throws Exception {

 	CuentaIf value = new CuentaData();
 
      
 	value.setPlancuentaId(new Long(1)); 
    value.setCodigo("CODIGO2"); 
    value.setNombre("NOMBRE2"); 
    value.setNombreCorto("NOMBRE_CORTO2"); 
    value.setTipocuentaId(new Long(1));  
    value.setPadreId(new Long(1));  
    value.setRelacionada(new Long(1));  
    value.setImputable("A"); 
    value.setNivel(1); 
    value.setTiporesultadoId(new Long(1));  
    value.setEfectivo("A"); 
    value.setActivofijo("A"); 
    value.setDepartamento("A"); 
    value.setLinea("A"); 
    value.setEmpleado("A"); 
    value.setCentrogasto("A"); 
    value.setCliente("A"); 
    value.setGasto("x"); 
    value.setEstado("x");
      
       getCuentaSessionService().addCuenta(value);

       Collection c = getCuentaSessionService().findCuentaByEstado("x"); 
       CuentaIf resultado = (CuentaIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"x"); 

   }

    
    @Test (timeout=2000)
    public void findCuentaByQuery() throws Exception {
 	
    	CuentaIf value = new CuentaData();
 
       
    	value.setPlancuentaId(new Long(1)); 
    	value.setCodigo("CODIGOy"); 
    	value.setNombre("NOMBREy"); 
    	value.setNombreCorto("NOMBRE_CORTOy"); 
    	value.setTipocuentaId(new Long(1));  
    	value.setPadreId(new Long(1));  
    	value.setRelacionada(new Long(1));  
    	value.setImputable("y"); 
    	value.setNivel(5); 
    	value.setTiporesultadoId(new Long(1));  
    	value.setEfectivo("y"); 
    	value.setActivofijo("y"); 
    	value.setDepartamento("y"); 
    	value.setLinea("y"); 
    	value.setEmpleado("y"); 
    	value.setCentrogasto("y"); 
    	value.setCliente("y"); 
    	value.setGasto("y");
    	value.setEstado("y");
      
      getCuentaSessionService().addCuenta(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("plancuentaId",new Long(1)); 
       parametros.put("codigo","CODIGOy"); 
       parametros.put("nombre","NOMBREy"); 
       parametros.put("nombreCorto","NOMBRE_CORTOy"); 
       parametros.put("tipocuentaId",new Long(1)); 
       parametros.put("padreId",new Long(1)); 
       parametros.put("relacionada",new Long(1)); 
       parametros.put("imputable","y"); 
       parametros.put("nivel",5); 
       parametros.put("tiporesultadoId",new Long(1)); 
       parametros.put("efectivo","y"); 
       parametros.put("activofijo","y"); 
       parametros.put("departamento","y"); 
       parametros.put("linea","y"); 
       parametros.put("empleado","y"); 
       parametros.put("centrogasto","y"); 
       parametros.put("cliente","y"); 
       parametros.put("gasto","y"); 
       parametros.put("estado", "y");

     Collection c = getCuentaSessionService().findCuentaByQuery(parametros); 
     CuentaIf resultado = (CuentaIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getPlancuentaId(),new Long(1)); 
      Assert.assertEquals(resultado.getCodigo(),"CODIGOy"); 
      Assert.assertEquals(resultado.getNombre(),"NOMBREy"); 
      Assert.assertEquals(resultado.getNombreCorto(),"NOMBRE_CORTOy"); 
      Assert.assertEquals(resultado.getTipocuentaId(),new Long(1)); 
      Assert.assertEquals(resultado.getPadreId(),new Long(1)); 
      Assert.assertEquals(resultado.getRelacionada(),new Long(1)); 
      Assert.assertEquals(resultado.getImputable(),"y"); 
      Assert.assertEquals(resultado.getNivel(),5); 
      Assert.assertEquals(resultado.getTiporesultadoId(),new Long(1)); 
      Assert.assertEquals(resultado.getEfectivo(),"y"); 
      Assert.assertEquals(resultado.getActivofijo(),"y"); 
      Assert.assertEquals(resultado.getDepartamento(),"y"); 
      Assert.assertEquals(resultado.getLinea(),"y"); 
      Assert.assertEquals(resultado.getEmpleado(),"y"); 
      Assert.assertEquals(resultado.getCentrogasto(),"y"); 
      Assert.assertEquals(resultado.getCliente(),"y"); 
      Assert.assertEquals(resultado.getGasto(),"y");
      Assert.assertEquals(resultado.getEstado(),"y"); 


    }

public static CuentaSessionService getCuentaSessionService() {
		try {
			return (CuentaSessionService) ServiceLocator
					.getService(ServiceLocator.CUENTASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(CuentaSessionTest.class);

 }


 


}
