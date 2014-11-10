package com.spirit.comun.test;


import junit.framework.JUnit4TestAdapter;

import com.spirit.nomina.session.RolPagoDetalleSessionService;
import com.spirit.servicelocator.ServiceLocator;



public class RolPagoDetalleSessionTest {

	/*
   //id=>NUMBER(10)
   //rolpagoId=>NUMBER(10)
   //contratoId=>NUMBER(10)
   //rubroId=>NUMBER(10)
   //valor=>NUMBER(22)
   //observacion=>VARCHAR2(100)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/general/test-data/RolPagoDetalle.xml");
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
   public void addRolPagoDetalle() throws Exception {
     RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

     RolPagoDetalleIf resultado = getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Assert.assertEquals(resultado.getRolpagoId(),"ROLPAGO_ID1"); 
       Assert.assertEquals(resultado.getContradoId(),"CONTRADO_ID1"); 
       Assert.assertEquals(resultado.getRubroId(),"RUBRO_ID1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

   }

  @Test (timeout=2000)
  public void saveRolPagoDetalle() throws Exception {

    RolPagoDetalleIf value = getRolPagoDetalleSessionService().getRolPagoDetalle(new Long(1));


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

     getRolPagoDetalleSessionService().saveRolPagoDetalle(value);

     RolPagoDetalleIf resultado = getRolPagoDetalleSessionService().getRolPagoDetalle(new Long(1));


       Assert.assertEquals(resultado.getId(),new Long(1));       
       Assert.assertEquals(resultado.getRolpagoId(),"ROLPAGO_ID1"); 
       Assert.assertEquals(resultado.getContradoId(),"CONTRADO_ID1"); 
       Assert.assertEquals(resultado.getRubroId(),"RUBRO_ID1"); 
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

   }


 @Test (timeout=2000)
 public void deleteRolPagoDetalle() throws Exception {
      getRolPagoDetalleSessionService().deleteRolPagoDetalle(new Long(3));
      RolPagoDetalleIf resultado = getRolPagoDetalleSessionService().getRolPagoDetalle(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findRolPagoDetalleById() throws Exception {

 	RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

       getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleById("ID1"); 
       RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),"ID1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoDetalleByRolpagoId() throws Exception {

 	RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

       getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleByRolpagoId("ROLPAGO_ID1"); 
       RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getRolpagoId(),"ROLPAGO_ID1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoDetalleByContradoId() throws Exception {

 	RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

       getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleByContradoId("CONTRADO_ID1"); 
       RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getContradoId(),"CONTRADO_ID1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoDetalleByRubroId() throws Exception {

 	RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

       getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleByRubroId("RUBRO_ID1"); 
       RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getRubroId(),"RUBRO_ID1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoDetalleByValor() throws Exception {

 	RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

       getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleByValor("VALOR1"); 
       RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getValor(),"VALOR1"); 

   }

   @Test (timeout=2000)
   public void findRolPagoDetalleByObservacion() throws Exception {

 	RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

       getRolPagoDetalleSessionService().addRolPagoDetalle(value);

       Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleByObservacion("OBSERVACION1"); 
       RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();
       Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 

   }




    @Test (timeout=2000)
    public void findRolPagoDetalleByQuery() throws Exception {

RolPagoDetalleIf value = new RolPagoDetalleData();


       value.setRolpagoId("ROLPAGO_ID1"); 
       value.setContradoId("CONTRADO_ID1"); 
       value.setRubroId("RUBRO_ID1"); 
       value.setValor("VALOR1"); 
       value.setObservacion("OBSERVACION1"); 

      getRolPagoDetalleSessionService().addRolPagoDetalle(value);

      Map<String, Object> parametros = new HashMap<String, Object>();


       parametros.put("rolpagoId","ROLPAGO_ID1"); 
       parametros.put("contradoId","CONTRADO_ID1"); 
       parametros.put("rubroId","RUBRO_ID1"); 
       parametros.put("valor","VALOR1"); 
       parametros.put("observacion","OBSERVACION1"); 

     Collection c = getRolPagoDetalleSessionService().findRolPagoDetalleByQuery(parametros); 
     RolPagoDetalleIf resultado = (RolPagoDetalleIf)c.iterator().next();


      Assert.assertEquals(resultado.getRolpagoId(),"ROLPAGO_ID1"); 
      Assert.assertEquals(resultado.getContradoId(),"CONTRADO_ID1"); 
      Assert.assertEquals(resultado.getRubroId(),"RUBRO_ID1"); 
      Assert.assertEquals(resultado.getValor(),"VALOR1"); 
      Assert.assertEquals(resultado.getObservacion(),"OBSERVACION1"); 



    }
	 */
	public static RolPagoDetalleSessionService getRolPagoDetalleSessionService() {
		try {
			return (RolPagoDetalleSessionService) ServiceLocator
			.getService(ServiceLocator.ROLPAGODETALLESESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RolPagoDetalleSessionTest.class);

	}





}
