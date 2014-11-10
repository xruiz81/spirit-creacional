package ImportarBasicosYdatClientes;


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

 
public class VerificarClientes {
	
	
	static Connection conn = null;
	static Statement stmt = null;
	static FileInputStream fis;
	 static Map clienteMap = new HashMap(); //si existe el cliente guardar el id, asociado al ruc y luego buscar la oficina)
	 static Map clienteRepetidosXLSMap = new HashMap(); //para verificar si se repiten los clientes en el archivo de Excel
	 static Map clienteIdentificacionMap = new HashMap();
	
	/*static Comparator<ClientesCOficina> comparadorNumeroAsiento = new Comparator<ClientesCOficina>(){
		public int compare(ClientesCOficina o1, ClientesCOficina o2) {
			return Integer.valueOf( o1.getCodigo() ).compareTo(o2.getCodigo());
		}
	};*/
	
	static List<ClientesCOficina> verificarCliente(String driverBase,String urlBase,String usuario
			,String clave,String nombreArchivoExcel,String nombreHoja) throws Exception  {
			
		try{
				File file = new File(nombreArchivoExcel);
					fis = new FileInputStream(file);
				
				ArrayList<ClientesCOficina> detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
				//Collections.sort(detalles, comparadorNumeroAsiento);
				
				//		INSERCION EN LA TABLA
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName(driverBase);
		
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				
				Connection conn = DriverManager.getConnection(urlBase,usuario, clave);
		 
				Statement stmt = conn.createStatement();
				Map<String,String> noExisten = new HashMap<String,String>();
				clienteMap = new HashMap();
				clienteRepetidosXLSMap = new HashMap();
				
				Long idClienteOficina = null;
				for ( ClientesCOficina detalleAsiento : detalles ){
					if(!detalleAsiento.getRazonSocial().equals("") || detalleAsiento.getCiudadId().compareTo(new Long(-1)) != 0){
						idClienteOficina = null;
						//String query = "select id from CLIENTE where IDENTIFICACION = '"+detalleAsiento.getClienteruc()+"'";
						String query ="select CL.id from CLIENTE CL WHERE CL.IDENTIFICACION='"+detalleAsiento.getIdentificacion()+"'";
						
						ResultSet rs = stmt.executeQuery(query);
						if( rs.next() ){
							idClienteOficina = rs.getLong(1);
							clienteMap.put(detalleAsiento.getIdentificacion(), idClienteOficina);		
						}
						
						
						if(clienteIdentificacionMap.get(detalleAsiento.getIdentificacion())!=null)
							clienteRepetidosXLSMap.put(detalleAsiento.getIdentificacion(), detalleAsiento.getNombreLegal());
						else
							clienteIdentificacionMap.put(detalleAsiento.getIdentificacion(), detalleAsiento.getNombreLegal());	
							
					}					
				}				
					
				cerrar();
			
				if ( !clienteMap.isEmpty() )	
					throw new Exception("Existen clientes que ya existen en la BD.");
				
				for ( Iterator itMapa = clienteRepetidosXLSMap.keySet().iterator() ;  itMapa.hasNext() ; ){
					System.out.println("Cliente Repetido en archivo Excel (Identificacion)--->: "+itMapa.next());
				}
				
				if ( !clienteRepetidosXLSMap.isEmpty() )	
					throw new Exception("Existen clientes repetidos en el archivo de Excel.");
				
				////////// TIPO_PROVEEDOR 
				
				
				Long idEmpProvee = null;
				for ( ClientesCOficina detalleAsiento : detalles ){
					if(detalleAsiento.getId() != -1){
						idEmpProvee = null;
						
						HashMap empresaIDMap = new HashMap();
						
						String queryProveedor ="select EMPRESA_ID from TIPO_PROVEEDOR WHERE ID='"+detalleAsiento.getTipoproveedorId()+"'";					
						ResultSet rs = stmt.executeQuery(queryProveedor);
						if( rs.next() ){
							idEmpProvee = rs.getLong(1);
							empresaIDMap.put(idEmpProvee,detalleAsiento.getTipoproveedorId());		
						}
						 					
						String queryTipoNegocio ="select EMPRESA_ID from TIPO_NEGOCIO WHERE ID='"+detalleAsiento.getTiponegocioId()+"'";					
						ResultSet rs2 = stmt.executeQuery(queryTipoNegocio);
						
						if( rs2.next() ) 
							idEmpProvee = rs2.getLong(1);
						
						if(empresaIDMap.get(idEmpProvee)==null)
							throw new Exception("<<<<El TIPO_NEGOCIO es de diferente empresa que el TIPO_PROVEEDOR.>>>>");
						else{
							
							String queryCorporacion ="select EMPRESA_ID from CORPORACION WHERE ID='"+detalleAsiento.getCorporacionId()+"'";					
							ResultSet rs3 = stmt.executeQuery(queryCorporacion);
							if( rs3.next() ) idEmpProvee = rs3.getLong(1);
							
							if(empresaIDMap.get(idEmpProvee)==null)
								throw new Exception("<<<<El TIPO_PROVEEDOR y TIPO_NEGOCIO es de diferente empresa que la CORPORACION.>>>>");
							else
							{
							
								String queryTipoCliente ="select EMPRESA_ID from TIPO_CLIENTE WHERE ID='"+detalleAsiento.getTipoclienteId()+"'";					
								ResultSet rs4 = stmt.executeQuery(queryTipoCliente);
								if( rs4.next() ) idEmpProvee = rs4.getLong(1);
								
								if(empresaIDMap.get(idEmpProvee)==null)
									throw new Exception("<<<<El TIPO_PROVEEDOR y TIPO_NEGOCIO y CORPORACION es de diferente empresa que TIPO_CLIENTE.>>>>");
								else
									System.out.println("Datos correctos!");
								
							}							
						}
					}						
				}
				cerrar();
				
			
				//////////////////////////////////////////////////////////
				
				
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
