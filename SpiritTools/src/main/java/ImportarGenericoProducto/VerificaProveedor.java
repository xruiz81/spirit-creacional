package ImportarGenericoProducto;
  
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

 
public class VerificaProveedor {
	
	static Connection conn = null;
	static Statement stmt = null;
	static FileInputStream fis;
	static Map clienteMap = new HashMap(); //si existe el cliente guardar el id, asociado al ruc y luego buscar la oficina)
	
	static List<ProductoGenerico> verificarCuentas(String driverBase,String urlBase,String usuario
			,String clave,String nombreArchivoExcel,String nombreHoja) throws Exception  {
			
		try{
				File file = new File(nombreArchivoExcel);
				fis = new FileInputStream(file);				
				ArrayList<ProductoGenerico> detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
				Class.forName(driverBase);
				Connection conn = DriverManager.getConnection(urlBase,usuario, clave);
				Statement stmt = conn.createStatement();
				
				//Verifica que exista el cliente(por medio del RUC) en la Base de datos
				Map<String,String> noExisten = new HashMap<String,String>();
				clienteMap = new HashMap();
				Long idClienteOficina = null;
				for ( ProductoGenerico detalleAsiento : detalles ){
					idClienteOficina = null;					
					String query ="select CL.id from CLIENTE CL WHERE CL.IDENTIFICACION='"+detalleAsiento.getProveedorIdentificacion()+"'";					
					ResultSet rs = stmt.executeQuery(query);
					if( rs.next() ){
						idClienteOficina = rs.getLong(1);
						clienteMap.put(detalleAsiento.getProveedorIdentificacion(), idClienteOficina);		
					}					 
					if ( idClienteOficina == null ) noExisten.put(detalleAsiento.getId().toString(),detalleAsiento.getProveedorIdentificacion().toString());
				}
				for ( Iterator itMapa = noExisten.keySet().iterator() ;  itMapa.hasNext() ; ){
					System.out.println("No existe en la tabla de clientes el Proveedor del Generico. "+itMapa.next());
				}				
				cerrar();			
				if ( !noExisten.isEmpty() )	throw new Exception("No existe en la tabla de clientes el Proveedor del Generico.");
				 
				
				//chequear q no se repita en el listado el producto y proveedor igual en el archivo XLS
				Map<String,String> repetidos = new HashMap<String,String>();				
				for ( ProductoGenerico detalleAsiento : detalles ){
					idClienteOficina = null;
					if(clienteMap.get(detalleAsiento.getProveedorIdentificacion()+detalleAsiento.getNombre())!=null)
						repetidos.put(detalleAsiento.getId().toString(),detalleAsiento.getId().toString());
					else
						clienteMap.put(detalleAsiento.getProveedorIdentificacion()+detalleAsiento.getNombre(), detalleAsiento.getId());							
				}
				for ( Iterator itMapa = repetidos.keySet().iterator() ;  itMapa.hasNext() ; ){
					System.out.println("Existen productos /proveedores repetidos "+itMapa.next());
				}				
				cerrar();				
				if ( !repetidos.isEmpty() )	throw new Exception("Existen productos /proveedores repetidos");				
				//////////////////////////////////////////////////////////
				
				//Verifica que el tipo de producto y familia_generico pertenezcan a la misma empresa.
				
				for ( ProductoGenerico detalleAsiento : detalles ){
					
					HashMap empresaIDMap = new HashMap();
					Long idEmp = null;
					String queryTipoProd ="select EMPRESA_ID from TIPO_PRODUCTO WHERE ID='"+detalleAsiento.getTipoproductoId()+"'";					
					ResultSet rs = stmt.executeQuery(queryTipoProd);
					if( rs.next() ){
						idEmp = rs.getLong(1);
						empresaIDMap.put(idEmp,detalleAsiento.getTipoproductoId());		
					}
					
					String queryTipoNegocio ="select EMPRESA_ID from FAMILIA_GENERICO WHERE ID='"+detalleAsiento.getFamiliaGenericoId()+"'";					
					ResultSet rs2 = stmt.executeQuery(queryTipoNegocio);
					if( rs2.next() ) idEmp = rs2.getLong(1);
										
					if(empresaIDMap.get(idEmp)==null)	throw new Exception("<<<<El TIPO_PRODUCTO y FAMILIA_GENERICO son de distintas empresas>>>>");
					
				}
				cerrar();		
				
				
				return detalles;
		} catch( Exception e){
			cerrar();
			e.printStackTrace();
			throw new Exception("Error en la verificacion de datos...");
		}
		
	}
	
	static void cerrar(){
		try {
			if ( stmt != null )
				stmt.close();
			if ( conn != null )
				conn.close();
			if ( fis != null )
				fis.close();
		} catch (SQLException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	/*public static void main(String[] a){
		
		try {
			verificarCuentas();
		} catch (Exception e) {
			cerrar();
		}
		
	}*/

}
