package ImportarBasicosYdatClientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
 

public class automateImportTxtMySQL
{
    public static void main(String[] args) 
    {
        DBase db = 
        	new DBase();
        Connection conn = db.connect(
        		"jdbc:mysql://localhost:3306/SPIRIT_UMA","root","master");
        		//"jdbc:mysql://192.168.100.9:3306/SPIRIT_UMA","desarrollo","desarrollo");
       //******************LINUX***********************//        
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/oficina.txt","OFICINA",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/departamento.txt","DEPARTAMENTO",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_empleado.txt","TIPO_EMPLEADO",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_contrato.txt","TIPO_CONTRATO",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/empleado.txt","EMPLEADO",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/linea.txt","LINEA",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/cuenta_bancaria.txt","CUENTA_BANCARIA",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_proveedor.txt","TIPO_PROVEEDOR",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_negocio.txt","TIPO_NEGOCIO",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/corporacion.txt","CORPORACION",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/cliente.txt","CLIENTE",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/cliente_oficina.txt","CLIENTE_OFICINA",false);        
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_cuenta.txt","TIPO_CUENTA",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_resultado.txt","TIPO_RESULTADO",false);
       //db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/cuenta.txt","CUENTA",false);
       // db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/familia_generico.txt","FAMILIA_GENERICO",false);
       // db.importData(conn,"/tmp/datosbase/archivosACME_EMCATXT/tipo_producto.txt","TIPO_PRODUCTO",false);
       //db.importData(conn,"/usr/java/ejemploImportacionEmpresa.txt","EMPRESA",false);

       //******************WINDOWS***********************//        
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//oficina.txt","OFICINA",false);        
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//departamento.txt","DEPARTAMENTO",false);
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_empleado.txt","TIPO_EMPLEADO",false);        
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_contrato.txt","TIPO_CONTRATO",false);        
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//empleado.txt","EMPLEADO",false);      
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//linea.txt","LINEA",false);        
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//cuenta_bancaria.txt","CUENTA_BANCARIA",false);
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_proveedor.txt","TIPO_PROVEEDOR",false);
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_negocio.txt","TIPO_NEGOCIO",false);
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//corporacion.txt","CORPORACION",false);
       //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//cliente.txt","CLIENTE",false);
       // db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//cliente_oficina.txt","CLIENTE_OFICINA",false);
        db.importData(conn,"C://SQL_UMA//cuentas3.txt","CUENTA",false);
        
    }
 
}
 
class DBase
{
    public DBase()
    {
    }
 
    public Connection connect(String db_connect_str, 
  String db_userid, String db_password)
    {
        Connection conn;
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            conn = DriverManager.getConnection(db_connect_str,db_userid, db_password);            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            conn = null;
        }
 
        return conn;    
    }
    
    public void importData(Connection conn,String filename,String tableName,boolean validar)
    {
        Statement stmt;
        String query;
 
        try
        {
        	
        	
        	if(tableName.equals("CLIENTE"))
        	{
        		String[] args=null;
        		ImportarClientes.main(args);
        	}
        	
        	if(!validar){
        	
        	
            stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_SENSITIVE,
    ResultSet.CONCUR_UPDATABLE);
 
    query = "LOAD DATA INFILE '"+filename+

    //empresa
    //"' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,RUC,EMAIL_CONTADOR,RUC_CONTADOR);";
    
    
    //oficina
     //   "' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,EMPRESA_ID,CIUDAD_ID,DIRECCION,TELEFONO,FAX,PREIMPRESO_SERIE);";
    //departamento
         // "' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,EMPRESA_ID);";
    //tipo_empleado:
     //       "' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,VENDEDOR,EMPRESA_ID);";            
    //tipo_contrato
          // "' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,EMPRESA_ID);";
    //empleado
       /* 
            "' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRES,APELLIDOS,TIPOIDENTIFICACION_ID ,IDENTIFICACION ,EMPRESA_ID ,PROFESION" +
    		  	" ,DIRECCION_DOMICILIO ,TELEFONO_DOMICILIO ,CELULAR ,EMAIL_OFICINA,DEPARTAMENTO_ID,JEFE_ID,TIPOEMPLEADO_ID ," +
    		 	"EXTENSION_OFICINA,NIVEL,ESTADO,OFICINA_ID,TIPOCONTRATO_ID );";*/
    			
    //linea  
    	// "' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,EMPRESA_ID,NIVEL,ACEPTAITEM);";
    
    //cuenta_bancaria//  
    //"' INTO TABLE "+tableName+";";
    
    //tipo_proveedor//
    //"' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,TIPO,EMPRESA_ID);";
    
    //tipo_negocio
    //"' INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,NOMBRE,EMPRESA_ID);";
    
    //corporacion//     
    	//"' INTO TABLE "+tableName+";";
    
    //cliente//
    /*
     "'INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,TIPOIDENTIFICACION_ID,IDENTIFICACION,NOMBRE_LEGAL,RAZON_SOCIAL," +
    "REPRESENTANTE,CORPORACION_ID,FECHA_CREACION,ESTADO,TIPOCLIENTE_ID,TIPOPROVEEDOR_ID," +
    "TIPONEGOCIO_ID,OBSERVACION,USUARIOFINAL,CONTRIBUYENTE_ESPECIAL,TIPO_PERSONA,LLEVA_CONTABILIDAD);";
    */
   
    //cliente_oficina//
    /*"'INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,CODIGO,CLIENTE_ID,CIUDAD_ID,DIRECCION," +
    "TELEFONO,FAX,EMAIL,FECHA_CREACION,MONTO_CREDITO,MONTO_GARANTIA,CALIFICACION," +
    "OBSERVACION,ESTADO,DESCRIPCION);";*/
    
    //CUENTA//
    "'INTO TABLE "+tableName+" CHARACTER SET latin1 (ID,PLANCUENTA_ID,CODIGO,NOMBRE,NOMBRE_CORTO,TIPOCUENTA_ID," +
    "PADRE_ID,RELACIONADA,IMPUTABLE,NIVEL,TIPORESULTADO_ID,EFECTIVO,ACTIVOFIJO,DEPARTAMENTO,LINEA," +
    "EMPLEADO,CENTROGASTO,CLIENTE,GASTO,ESTADO,CUENTA_BANCO);";
    
  //FAMILIA GENERICO, TIPO PRODUCTO, 
    
    /*"' INTO TABLE "+tableName+";";*/    
            stmt.executeUpdate(query);
        	} 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    }
};

