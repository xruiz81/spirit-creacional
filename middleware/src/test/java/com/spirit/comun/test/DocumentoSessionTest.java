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

import com.spirit.general.entity.DocumentoData;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.session.DocumentoSessionService;
import com.spirit.server.DBUnitConnection;
import com.spirit.servicelocator.ServiceLocator;


public class DocumentoSessionTest {

   //id=>NUMBER(10)
   //codigo=>VARCHAR2(4)
   //nombre=>VARCHAR2(30)
   //abreviado=>VARCHAR2(20)
   //tipodocumentoId=>NUMBER(10)
   //pideautorizacion=>VARCHAR2(1)
   //estado=>VARCHAR2(1)
   //diferido=>VARCHAR2(1)
   //bonificacion=>VARCHAR2(1)
   //precioespecial=>VARCHAR2(1)
   //descuentoespecial=>VARCHAR2(1)
   //multa=>VARCHAR2(1)
   //interes=>VARCHAR2(1)
   //protesto=>VARCHAR2(1)
   //cheque=>VARCHAR2(1)
   //retencion=>VARCHAR2(1)

        private IDataSet getDataSet() throws IOException, DataSetException {
		File file = new File("src/test/resources/documento.xml");
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
   public void addDocumento() throws Exception {
     DocumentoIf value = new DocumentoData();
 
       value.setCodigo("COD"); 
       value.setNombre("NOMBRE1"); 
       value.setAbreviado("ABREVIADO1"); 
       value.setTipodocumentoId(new Long(1)); 
       value.setPideautorizacion("N"); 
       value.setEstado("A"); 
       value.setDiferido("N"); 
       value.setBonificacion("N"); 
       value.setPrecioespecial("N"); 
       value.setDescuentoespecial("N"); 
       value.setMulta("N"); 
       value.setInteres("N"); 
       value.setProtesto("N"); 
       value.setCheque("N"); 
       value.setRetencion("N"); 
      
     DocumentoIf resultado = getDocumentoSessionService().addDocumento(value);
           
       Assert.assertEquals(resultado.getCodigo(),"COD"); 
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
       Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
       Assert.assertEquals(resultado.getPideautorizacion(),"N"); 
       Assert.assertEquals(resultado.getEstado(),"A"); 
       Assert.assertEquals(resultado.getDiferido(),"N"); 
       Assert.assertEquals(resultado.getBonificacion(),"N"); 
       Assert.assertEquals(resultado.getPrecioespecial(),"N"); 
       Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
       Assert.assertEquals(resultado.getMulta(),"N"); 
       Assert.assertEquals(resultado.getInteres(),"N"); 
       Assert.assertEquals(resultado.getProtesto(),"N"); 
       Assert.assertEquals(resultado.getCheque(),"N"); 
       Assert.assertEquals(resultado.getRetencion(),"N"); 
		
   }

  @Test (timeout=2000)
  public void saveDocumento() throws Exception {
      
    DocumentoIf value = getDocumentoSessionService().getDocumento(new Long(1));
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
     getDocumentoSessionService().saveDocumento(value);

     DocumentoIf resultado = getDocumentoSessionService().getDocumento(new Long(1));

     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
     Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getPideautorizacion(),"N"); 
     Assert.assertEquals(resultado.getEstado(),"A"); 
     Assert.assertEquals(resultado.getDiferido(),"N"); 
     Assert.assertEquals(resultado.getBonificacion(),"N"); 
     Assert.assertEquals(resultado.getPrecioespecial(),"N"); 
     Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
     Assert.assertEquals(resultado.getMulta(),"N"); 
     Assert.assertEquals(resultado.getInteres(),"N"); 
     Assert.assertEquals(resultado.getProtesto(),"N"); 
     Assert.assertEquals(resultado.getCheque(),"N"); 
     Assert.assertEquals(resultado.getRetencion(),"N"); 
		
   }


 @Test (timeout=2000)
 public void deleteDocumento() throws Exception {
      getDocumentoSessionService().deleteDocumento(new Long(3));
      DocumentoIf resultado = getDocumentoSessionService().getDocumento(new Long(3));
     Assert.assertNull(resultado);
 }




   @Test (timeout=2000)
   public void findDocumentoById() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoById(new Long(1)); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findDocumentoByCodigo() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByCodigo("COD"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCodigo(),"COD"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByNombre() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByNombre("NOMBRE1"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByAbreviado() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByAbreviado("ABREVIADO1"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByTipodocumentoId() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByTipodocumentoId(new Long(1)); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 

   }

   @Test (timeout=2000)
   public void findDocumentoByPideautorizacion() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByPideautorizacion("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPideautorizacion(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByEstado() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByEstado("A"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getEstado(),"A"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByDiferido() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByDiferido("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getDiferido(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByBonificacion() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByBonificacion("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getBonificacion(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByPrecioespecial() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByPrecioespecial("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getPrecioespecial(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByDescuentoespecial() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByDescuentoespecial("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByMulta() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByMulta("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getMulta(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByInteres() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByInteres("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getInteres(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByProtesto() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByProtesto("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getProtesto(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByCheque() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByCheque("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getCheque(),"N"); 

   }

   @Test (timeout=2000)
   public void findDocumentoByRetencion() throws Exception {

 	DocumentoIf value = new DocumentoData();
 
    value.setCodigo("COD"); 
    value.setNombre("NOMBRE1"); 
    value.setAbreviado("ABREVIADO1"); 
    value.setTipodocumentoId(new Long(1)); 
    value.setPideautorizacion("N"); 
    value.setEstado("A"); 
    value.setDiferido("N"); 
    value.setBonificacion("N"); 
    value.setPrecioespecial("N"); 
    value.setDescuentoespecial("N"); 
    value.setMulta("N"); 
    value.setInteres("N"); 
    value.setProtesto("N"); 
    value.setCheque("N"); 
    value.setRetencion("N"); 
      
       getDocumentoSessionService().addDocumento(value);

       Collection c = getDocumentoSessionService().findDocumentoByRetencion("N"); 
       DocumentoIf resultado = (DocumentoIf)c.iterator().next();
       Assert.assertEquals(resultado.getRetencion(),"N"); 

   }



    
    @Test (timeout=2000)
    public void findDocumentoByQuery() throws Exception {
 	
DocumentoIf value = new DocumentoData();
 
value.setCodigo("COD"); 
value.setNombre("NOMBRE1"); 
value.setAbreviado("ABREVIADO1"); 
value.setTipodocumentoId(new Long(1)); 
value.setPideautorizacion("N"); 
value.setEstado("A"); 
value.setDiferido("N"); 
value.setBonificacion("N"); 
value.setPrecioespecial("N"); 
value.setDescuentoespecial("N"); 
value.setMulta("N"); 
value.setInteres("N"); 
value.setProtesto("N"); 
value.setCheque("N"); 
value.setRetencion("N"); 
      
      getDocumentoSessionService().addDocumento(value);

      Map<String, Object> parametros = new HashMap<String, Object>();

       parametros.put("codigo","COD"); 
       parametros.put("nombre","NOMBRE1"); 
       parametros.put("abreviado","ABREVIADO1"); 
       parametros.put("tipodocumentoId",new Long(1)); 
       parametros.put("pideautorizacion","N"); 
       parametros.put("estado","A"); 
       parametros.put("diferido","N"); 
       parametros.put("bonificacion","N"); 
       parametros.put("precioespecial","N"); 
       parametros.put("descuentoespecial","N"); 
       parametros.put("multa","N"); 
       parametros.put("interes","N"); 
       parametros.put("protesto","N"); 
       parametros.put("cheque","N"); 
       parametros.put("retencion","N"); 

     Collection c = getDocumentoSessionService().findDocumentoByQuery(parametros); 
     DocumentoIf resultado = (DocumentoIf)c.iterator().next();
      
     Assert.assertEquals(resultado.getCodigo(),"COD"); 
     Assert.assertEquals(resultado.getNombre(),"NOMBRE1"); 
     Assert.assertEquals(resultado.getAbreviado(),"ABREVIADO1"); 
     Assert.assertEquals(resultado.getTipodocumentoId(),new Long(1)); 
     Assert.assertEquals(resultado.getPideautorizacion(),"N"); 
     Assert.assertEquals(resultado.getEstado(),"A"); 
     Assert.assertEquals(resultado.getDiferido(),"N"); 
     Assert.assertEquals(resultado.getBonificacion(),"N"); 
     Assert.assertEquals(resultado.getPrecioespecial(),"N"); 
     Assert.assertEquals(resultado.getDescuentoespecial(),"N"); 
     Assert.assertEquals(resultado.getMulta(),"N"); 
     Assert.assertEquals(resultado.getInteres(),"N"); 
     Assert.assertEquals(resultado.getProtesto(),"N"); 
     Assert.assertEquals(resultado.getCheque(),"N"); 
     Assert.assertEquals(resultado.getRetencion(),"N"); 


    }

public static DocumentoSessionService getDocumentoSessionService() {
		try {
			return (DocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.DOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }

 public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DocumentoSessionTest.class);

 }

}
