package ImportarCartera;
  
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

 
public class VerificaCuentas {
	
	
	static Connection conn = null;
	static Statement stmt = null;
	static FileInputStream fis;
	 static Map clienteMap = new HashMap(); //si existe el cliente guardar el id, asociado al ruc y luego buscar la oficina)
	
	static Comparator<CarteraDetalleImportadaData> comparadorNumeroAsiento = new Comparator<CarteraDetalleImportadaData>(){
		public int compare(CarteraDetalleImportadaData o1, CarteraDetalleImportadaData o2) {
			return Integer.valueOf( o1.getSecuencial() ).compareTo(o2.getSecuencial());
		}
	};
	
	static List<CarteraDetalleImportadaData> verificarCuentas(String driverBase,String urlBase,String usuario
			,String clave,String nombreArchivoExcel,String nombreHoja) throws Exception  {
			
		try{
				File file = new File(nombreArchivoExcel);
					fis = new FileInputStream(file);
				
				//ArrayList<CarteraCDetalle> detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
				ArrayList detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
				
				//Collections.sort(detalles, comparadorNumeroAsiento);
				
				//		INSERCION EN LA TABLA
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName(driverBase);
		
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				
				Connection conn = DriverManager.getConnection(urlBase,usuario, clave);
				
				Statement stmt = conn.createStatement();
				Map<String,String> noExisten = new HashMap<String,String>();
				clienteMap = new HashMap();
				Long idClienteOficina = null;
				//for ( CarteraCDetalle detalleAsiento : detalles ){
				for(int i=0; i<detalles.size(); i++){					
					CarteraDetalleImportadaData detalleAsiento = (CarteraDetalleImportadaData)detalles.get(i);
					idClienteOficina = null;
					String query ="select CO.id from CLIENTE CL, CLIENTE_OFICINA CO WHERE CO.CLIENTE_ID=CL.ID AND CL.NOMBRE_LEGAL='"+detalleAsiento.getClienteruc()+"'";
					
					ResultSet rs = stmt.executeQuery(query);
					if( rs.next() ){
						idClienteOficina = rs.getLong(1);
						clienteMap.put(detalleAsiento.getClienteruc(), idClienteOficina);		
					}
									
					
					if ( idClienteOficina == null )						
						noExisten.put(detalleAsiento.getClienteruc(),detalleAsiento.getClienteruc());
					
						
				}				
				
				for ( Iterator itMapa = noExisten.keySet().iterator() ;  itMapa.hasNext() ; ){
					System.out.println("NO EXISTEN EL CLIENTE O LA OFICINA DEL CLIENTE Cliente / Cliente-Oficina: "+itMapa.next());
				}
				
				
	
				cerrar();
			
				if ( !noExisten.isEmpty() )	throw new Exception("Hay clientes que no existen");
	
				System.out.println("Todas las cuentas registradas....");
				
				
				//////////////////////////BUSCA LOS TIPO DE DOCUMENTOS y los pone en un Map
				
				Map<String,Long> tipodocMap = new HashMap<String,Long>();				
				 
				//for ( CarteraCDetalle detalleAsiento : detalles ){
				for(int i=0; i<detalles.size(); i++){					
					CarteraDetalleImportadaData detalleAsiento = (CarteraDetalleImportadaData)detalles.get(i);
					String codigoTipodoc="";
					Long idTipoDoc=null;
					//f o nv
					if(detalleAsiento.getTipodoc()!=null)
					{						
						if(tipodocMap.get(codigoTipodoc)!=null)
							{ 
								idTipoDoc=tipodocMap.get(codigoTipodoc);
							}
						else{
								String query = "select id from TIPO_DOCUMENTO where NOMBRE='"+detalleAsiento.getTipodoc()+"'";
								ResultSet rs = stmt.executeQuery(query);
								if( rs.next() ){
									idTipoDoc = rs.getLong(1);
									tipodocMap.put(detalleAsiento.getTipodoc(), idTipoDoc);
								}
							}						
					}
				}
				
				//////////////////////////////////////////////////////////
				
				//se revisa que no este ingresado ese preimpreso y tipodoc en la tabla Cartera
				// y ademas que en el listado de excel no este repetido el preimpreso. 
				
				String preimpreso = null;
				
				Vector<String> preimpresoCollection = new Vector<String>();
				Map<String,Long> preimpresoMap = new HashMap<String,Long>();
				
				Map<String,String> preimpresoRepetido = new HashMap<String,String>();
				
				
				
				//for ( CarteraCDetalle detalleAsiento : detalles ){
				for(int i=0; i<detalles.size(); i++){					
					CarteraDetalleImportadaData detalleAsiento = (CarteraDetalleImportadaData)detalles.get(i);
					preimpreso = null;					
					Long idTipoDoc=null;					
					idTipoDoc=tipodocMap.get(detalleAsiento.getTipodoc());					
					String query = "select id from CARTERA where PREIMPRESO = '"+detalleAsiento.getPreimpreso()+"' AND TIPODOCUMENTO_ID='"+idTipoDoc+"'";
					
					System.out.println("queryyyy>"+query);
					ResultSet rs = stmt.executeQuery(query);
					Long idCartera=null;
					if ( rs.next() ){
						idCartera = rs.getLong(1);
						
						System.out.println("icartera*-------->"+idCartera);
					}
					
					if (idCartera!= null) // existe en la tabla
					{
						preimpresoRepetido.put(idCartera.toString(), idCartera.toString());
					}
					else{						
						//busca en el listado de excel.
						
					}						
				}
				
				for ( Iterator itMapa = preimpresoRepetido.keySet().iterator() ;  itMapa.hasNext() ; ){
					System.out.println("preimpresoRepetido--->: "+itMapa.next());
				}
	
				cerrar();
				
				if ( !preimpresoRepetido.isEmpty() )
					throw new Exception("Hay preimpresos con el mismo tipo de documento que ya existe en la base de datos");
				
				
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
