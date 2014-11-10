package com.spirit.general.gui.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class automateImportTxtMySQL
{
    public static void main(String[] args) 
    {
        DBase db = new DBase();
        Connection conn = db.connect(
    "jdbc:mysql://192.168.100.7:3306/SPIRIT_AXIS","root","root");
              
        //******************LINUX***********************//
        
        //db.importData(conn,"/tmp/datosbase/oficina.txt","OFICINA");
        //db.importData(conn,"/tmp/datosbase/departamento.txt","DEPARTAMENTO");
        //db.importData(conn,"/tmp/datosbase/tipo_empleado.txt","TIPO_EMPLEADO");
        //db.importData(conn,"/tmp/datosbase/tipo_contrato.txt","TIPO_CONTRATO");
        //db.importData(conn,"/tmp/datosbase/empleado.txt","EMPLEADO");
        //db.importData(conn,"/tmp/datosbase/linea.txt","LINEA");
        //db.importData(conn,"/tmp/datosbase/cuenta_bancaria.txt","CUENTA_BANCARIA");
        //db.importData(conn,"/tmp/datosbase/tipo_proveedor.txt","TIPO_PROVEEDOR");
        //db.importData(conn,"/tmp/datosbase/tipo_negocio.txt","TIPO_NEGOCIO");
        //db.importData(conn,"/tmp/datosbase/corporacion.txt","CORPORACION");
        //db.importData(conn,"/tmp/datosbase/cliente.txt","CLIENTE");
        //db.importData(conn,"/tmp/datosbase/cliente_oficina.txt","CLIENTE_OFICINA");
        
        db.importData(conn,"/tmp/datosbase/cuenta.txt","CUENTA");
        
        //******************WINDOWS***********************//
        
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//oficina.txt","OFICINA");        
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//departamento.txt","DEPARTAMENTO");
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_empleado.txt","TIPO_EMPLEADO");        
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_contrato.txt","TIPO_CONTRATO");        
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//empleado.txt","EMPLEADO");      
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//linea.txt","LINEA");
        
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//cuenta_bancaria.txt","CUENTA_BANCARIA");
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_proveedor.txt","TIPO_PROVEEDOR");
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//tipo_negocio.txt","TIPO_NEGOCIO");
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//corporacion.txt","CORPORACION");
        //db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//cliente.txt","CLIENTE");
       // db.importData(conn,"C://Documents and Settings//Winxp//Escritorio//Axis//cliente_oficina.txt","CLIENTE_OFICINA");
  
        
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
 
            conn = DriverManager.getConnection(db_connect_str, 
    db_userid, db_password);
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
            conn = null;
        }
 
        return conn;    
    }
    
    public void importData(Connection conn,String filename,String tableName)
    {
        Statement stmt;
        String query;
 
        try
        {
            stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_SENSITIVE,
    ResultSet.CONCUR_UPDATABLE);
 
    query = "LOAD DATA INFILE '"+filename+

    //oficina
        // "' INTO TABLE "+tableName+"(ID,CODIGO,NOMBRE,EMPRESA_ID,CIUDAD_ID,DIRECCION,TELEFONO,FAX,PREIMPRESO_SERIE,SERVIDOR_ID);";
    //departamento
       //    "' INTO TABLE "+tableName+"(ID,CODIGO,NOMBRE,EMPRESA_ID);";
    //tipo_empleado:
       //     "' INTO TABLE "+tableName+"(ID,CODIGO,NOMBRE,EMPRESA_ID,VENDEDOR);";            
    //tipo_contrato
       //    "' INTO TABLE "+tableName+"(ID,CODIGO,NOMBRE,EMPRESA_ID);";
    //empleado
        /*
            "' INTO TABLE "+tableName+"(ID,CODIGO,NOMBRES,APELLIDOS,TIPOIDENTIFICACION_ID ,IDENTIFICACION ,EMPRESA_ID ,PROFESION" +
    		  	" ,DIRECCION_DOMICILIO ,TELEFONO_DOMICILIO ,CELULAR ,EMAIL_OFICINA,DEPARTAMENTO_ID,TIPOEMPLEADO_ID ," +
    		 	"EXTENSION_OFICINA,NIVEL,ESTADO,OFICINA_ID,TIPOCONTRATO_ID );";
    			*/
    //linea  
    	//"' INTO TABLE "+tableName+"(ID,CODIGO,NOMBRE,EMPRESA_ID);";
    
    //cuenta_bancaria//  
    	//    "' INTO TABLE "+tableName+";";
    
    //tipo_proveedor//
    	//
    //"' INTO TABLE "+tableName+";";
    
    //tipo_negocio
    	//"' INTO TABLE "+tableName+";";
    
    //corporacion//     
    	//"' INTO TABLE "+tableName+";";
    
    //cliente//
   
  /*  "'INTO TABLE "+tableName+"(ID,TIPOIDENTIFICACION_ID,IDENTIFICACION,NOMBRE_LEGAL,RAZON_SOCIAL," +
    "REPRESENTANTE,CORPORACION_ID,FECHA_CREACION,ESTADO,TIPOCLIENTE_ID,TIPOPROVEEDOR_ID," +
    "TIPONEGOCIO_ID,OBSERVACION,USUARIOFINAL,CONTRIBUYENTE_ESPECIAL,TIPO_PERSONA,LLEVA_CONTABILIDAD);";
    */
    
    //cliente_oficina
   "' INTO TABLE "+tableName+";";
    
    

            stmt.executeUpdate(query);
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    }
};

