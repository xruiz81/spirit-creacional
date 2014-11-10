package com.spirit.comun.test;


public class LogAsientoDetalleSessionTest {
/*

   //id=>NUMBER(10)
   //cuentaId=>NUMBER(10)
   //logAsientoId=>NUMBER(10)
   //referencia=>VARCHAR2(30)
   //glosa=>VARCHAR2(350)
   //centrogastoId=>NUMBER(10)
   //empleadoId=>NUMBER(10)
   //departamentoId=>NUMBER(10)
   //lineaId=>NUMBER(10)
   //clienteId=>NUMBER(10)
   //debe=>NUMBER(22)
   //haber=>NUMBER(22)
   //log=>VARCHAR2(500)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/LogAsientoDetalle.xml");
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
   public void addLogAsientoDetalle() throws Exception {
     LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
     LogAsientoDetalleIf resultado = getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);
           
       Assert.assertEquals(resultado.getCuentaId(),"CUENTA_ID1"); 
       Assert.assertEquals(resultado.getLogAsientoId(),"LOG_ASIENTO_ID1"); 
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
       Assert.assertEquals(resultado.getGlosa(),"GLOSA1"); 
       Assert.assertEquals(resultado.getCentrogastoId(),"CENTROGASTO_ID1"); 
       Assert.assertEquals(resultado.getEmpleadoId(),"EMPLEADO_ID1"); 
       Assert.assertEquals(resultado.getDepartamentoId(),"DEPARTAMENTO_ID1"); 
       Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 
       Assert.assertEquals(resultado.getClienteId(),"CLIENTE_ID1"); 
       Assert.assertEquals(resultado.getDebe(),"DEBE1"); 
       Assert.assertEquals(resultado.getHaber(),"HABER1"); 
       Assert.assertEquals(resultado.getLog(),"LOG1"); 
		
   }

  @Test (timeout=2000)
  public void saveLogAsientoDetalle() throws Exception {
      
    LogAsientoDetalleIf value = getLogAsientoDetalleSessionService().getLogAsientoDetalle(new Long(1));
 
          
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
     getLogAsientoDetalleSessionService().saveLogAsientoDetalle(value);

     LogAsientoDetalleIf resultado = getLogAsientoDetalleSessionService().getLogAsientoDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getCuentaId(),"CUENTA_ID1"); 
       Assert.assertEquals(resultado.getLogAsientoId(),"LOG_ASIENTO_ID1"); 
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
       Assert.assertEquals(resultado.getGlosa(),"GLOSA1"); 
       Assert.assertEquals(resultado.getCentrogastoId(),"CENTROGASTO_ID1"); 
       Assert.assertEquals(resultado.getEmpleadoId(),"EMPLEADO_ID1"); 
       Assert.assertEquals(resultado.getDepartamentoId(),"DEPARTAMENTO_ID1"); 
       Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 
       Assert.assertEquals(resultado.getClienteId(),"CLIENTE_ID1"); 
       Assert.assertEquals(resultado.getDebe(),"DEBE1"); 
       Assert.assertEquals(resultado.getHaber(),"HABER1"); 
       Assert.assertEquals(resultado.getLog(),"LOG1"); 
		
   }


 @Test (timeout=2000)
 public void deleteLogAsientoDetalle() throws Exception {
      getLogAsientoDetalleSessionService().deleteLogAsientoDetalle(new Long(3));
      LogAsientoDetalleIf resultado = getLogAsientoDetalleSessionService().getLogAsientoDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findLogAsientoDetalleById() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleById("ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByCuentaId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByCuentaId("CUENTA_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCuentaId(),"CUENTA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByLogAsientoId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByLogAsientoId("LOG_ASIENTO_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLogAsientoId(),"LOG_ASIENTO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByReferencia() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByReferencia("REFERENCIA1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByGlosa() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByGlosa("GLOSA1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getGlosa(),"GLOSA1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByCentrogastoId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByCentrogastoId("CENTROGASTO_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getCentrogastoId(),"CENTROGASTO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByEmpleadoId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByEmpleadoId("EMPLEADO_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getEmpleadoId(),"EMPLEADO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByDepartamentoId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByDepartamentoId("DEPARTAMENTO_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDepartamentoId(),"DEPARTAMENTO_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByLineaId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByLineaId("LINEA_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByClienteId() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByClienteId("CLIENTE_ID1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getClienteId(),"CLIENTE_ID1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByDebe() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByDebe("DEBE1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getDebe(),"DEBE1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByHaber() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByHaber("HABER1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getHaber(),"HABER1"); 

   }

   @Test (timeout=2000)
   public void findLogAsientoDetalleByLog() throws Exception {

 	LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
      
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
       getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

       Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByLog("LOG1"); 
       LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getLog(),"LOG1"); 

   }



    
    @Test (timeout=2000)
    public void findLogAsientoDetalleByQuery() throws Exception {
 	
LogAsientoDetalleIf value = new LogAsientoDetalleData();
 
       
       value.setCuentaId("CUENTA_ID1"); 
       value.setLogAsientoId("LOG_ASIENTO_ID1"); 
       value.setReferencia("REFERENCIA1"); 
       value.setGlosa("GLOSA1"); 
       value.setCentrogastoId("CENTROGASTO_ID1"); 
       value.setEmpleadoId("EMPLEADO_ID1"); 
       value.setDepartamentoId("DEPARTAMENTO_ID1"); 
       value.setLineaId("LINEA_ID1"); 
       value.setClienteId("CLIENTE_ID1"); 
       value.setDebe("DEBE1"); 
       value.setHaber("HABER1"); 
       value.setLog("LOG1"); 
      
      getLogAsientoDetalleSessionService().addLogAsientoDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       
       parametros.put("cuentaId","CUENTA_ID1"); 
       parametros.put("logAsientoId","LOG_ASIENTO_ID1"); 
       parametros.put("referencia","REFERENCIA1"); 
       parametros.put("glosa","GLOSA1"); 
       parametros.put("centrogastoId","CENTROGASTO_ID1"); 
       parametros.put("empleadoId","EMPLEADO_ID1"); 
       parametros.put("departamentoId","DEPARTAMENTO_ID1"); 
       parametros.put("lineaId","LINEA_ID1"); 
       parametros.put("clienteId","CLIENTE_ID1"); 
       parametros.put("debe","DEBE1"); 
       parametros.put("haber","HABER1"); 
       parametros.put("log","LOG1"); 

     Collection c = getLogAsientoDetalleSessionService().findLogAsientoDetalleByQuery(parametros); 
     LogAsientoDetalleIf resultado = (LogAsientoDetalleIf)c.iterator().next();
      
      
      Assert.assertEquals(resultado.getCuentaId(),"CUENTA_ID1"); 
      Assert.assertEquals(resultado.getLogAsientoId(),"LOG_ASIENTO_ID1"); 
      Assert.assertEquals(resultado.getReferencia(),"REFERENCIA1"); 
      Assert.assertEquals(resultado.getGlosa(),"GLOSA1"); 
      Assert.assertEquals(resultado.getCentrogastoId(),"CENTROGASTO_ID1"); 
      Assert.assertEquals(resultado.getEmpleadoId(),"EMPLEADO_ID1"); 
      Assert.assertEquals(resultado.getDepartamentoId(),"DEPARTAMENTO_ID1"); 
      Assert.assertEquals(resultado.getLineaId(),"LINEA_ID1"); 
      Assert.assertEquals(resultado.getClienteId(),"CLIENTE_ID1"); 
      Assert.assertEquals(resultado.getDebe(),"DEBE1"); 
      Assert.assertEquals(resultado.getHaber(),"HABER1"); 
      Assert.assertEquals(resultado.getLog(),"LOG1"); 


    }

public static LogAsientoDetalleSessionService getLogAsientoDetalleSessionService() {
		try {
			return (LogAsientoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.LogAsientoDetalleSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(LogAsientoDetalleSessionTest.class);

 }


 

*/
}
