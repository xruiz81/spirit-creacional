package ImportarBasicosYdatClientes;
 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

 

import ImportarCartera.VerificaCuentas;

import com.spirit.util.Utilitarios;

public class ImportarClientes {

	//PARA CARTERA
	static Connection conn = null;
	static List<ClientesCOficina> detalles = null;
	static Statement stmt = null;
	static FileInputStream fis;
	
	public static void main(String[] a ){

		try {
		
			String usuario = "root";
			String clave = "master";
			String urlBase = "jdbc:mysql://localhost:3306/SPIRIT_UMA";
			String driverBase = "com.mysql.jdbc.Driver";
			String nombrearchivoExcel = "c:\\SQL_UMA\\FormatoDatosPARAVersalityMySQL-Julio2011.xls";
			String nombreHoja = "Cliente-Proveedor";
			 
			
			NumberFormat dosDecimales = new DecimalFormat("00");			
			//detalles = VerificaCuentas.verificarCuentas(driverBase,urlBase,usuario,clave,nombrearchivoExcel,nombreHoja);
			
			
			//ArrayList<ClientesCOficina> detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
			File file = new File(nombrearchivoExcel);
			fis = new FileInputStream(file);
			//detalles = ManejarExcel.procesarArchivoExcel(fis,nombreHoja );
			
			detalles = VerificarClientes.verificarCliente(driverBase,urlBase,usuario,clave,nombrearchivoExcel,nombreHoja);
			
			if ( detalles != null ){
				Class.forName(driverBase);	
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				conn = DriverManager.getConnection(urlBase,usuario, clave);	
				stmt = conn.createStatement();				
				
				
				Long idCabecera = null ;
				String query = "select max(id) from CLIENTE";
				ResultSet rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idCabecera = rs.getLong(1);
				}
				if (idCabecera == null)
					throw new Exception("Falla en la busqueda de id de cabecera");				
				idCabecera++;
				
				rs =null;
	
				Long idDetalle = null;
				query = "select max(id) from CLIENTE_OFICINA";
				rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idDetalle = rs.getLong(1);
				}
				if (idDetalle == null)
					throw new Exception("Falla en la busqueda de id del detalle");
				
				idDetalle++;
				
				
				
				boolean tieneDetalles = false;
				for ( ClientesCOficina detallesClientes : detalles ){
					 
						
						if ( tieneDetalles ){
							idCabecera++;
							tieneDetalles = false;
						}
						
						String fecC=detallesClientes.getFechaCreacion().toString();
						if(fecC.length()>20) fecC=fecC.substring(0,19);
						
						
						//String estado="A";
						String insertarCabecera = "insert into CLIENTE " +						
						"(ID,TIPOIDENTIFICACION_ID,IDENTIFICACION,NOMBRE_LEGAL,RAZON_SOCIAL," +
					    "REPRESENTANTE,CORPORACION_ID,FECHA_CREACION,ESTADO,TIPOCLIENTE_ID,TIPOPROVEEDOR_ID," +
					    "TIPONEGOCIO_ID,OBSERVACION,USUARIOFINAL,CONTRIBUYENTE_ESPECIAL,TIPO_PERSONA,LLEVA_CONTABILIDAD) "+
								"values ( "+
							idCabecera+","+	// --ID
							detallesClientes.getTipoidentificacionId()+","+			//--TIPO
							"'"+detallesClientes.getIdentificacion()+"'"+","+			//--TIPO
							"'"+detallesClientes.getNombreLegal()+"'"+","+			//--TIPO
							"'"+detallesClientes.getRazonSocial()+"'"+","+			//--TIPO							
							"'"+detallesClientes.getRepresentante()+"'"+","+			//--TIPO
							detallesClientes.getCorporacionId()+","+			//--TIPO							
							"'"+fecC+"'"+","+			//--TIPO
							"'"+detallesClientes.getEstado()+"'"+","+			//--TIPO
							detallesClientes.getTipoclienteId()+","+			//--TIPO
							detallesClientes.getTipoproveedorId()+","+			//--TIPO
							detallesClientes.getTiponegocioId()+","+			//--TIPO
							"'"+detallesClientes.getObservacion()+"'"+","+			//--TIPO
							"'"+detallesClientes.getUsuariofinal()+"'"+","+			//--TIPO
							"'"+detallesClientes.getContribuyenteEspecial()+"'"+","+			//--TIPO							
							"'"+detallesClientes.getTipoPersona()+"'"+","+			//--TIPO
							"'"+detallesClientes.getLlevaContabilidad()+"'"+			//--TIPO							
							")";								//--APROBADO		
					
						System.out.println("Cabecera id:>"+idCabecera);						
						stmt.executeUpdate(insertarCabecera);
					
					//INSERTA EL DETALLE
					{
						//Seteo directamente el vendedor id en cero porque este dato no me viene en el excel
						detallesClientes.setVendedorId(0L);
						
						tieneDetalles = true;
						String insertarDetalle = "insert into CLIENTE_OFICINA values ( " +
						idDetalle+","+								// --ID
						detallesClientes.getCodigo()+","+			//--CODIGO
						idCabecera+","+		//--CLIENTEID
						detallesClientes.getCiudadId()+","+			//--CIUDADID
						"'"+detallesClientes.getDireccion()+"'"+","+		//-- 
						"'"+detallesClientes.getTelefono()+"'"+","+			//-- 
						"'"+detallesClientes.getFax()+"'"+","+		//-- 
						"'"+detallesClientes.getEmail()+"'"+","+			//-- 
						"'"+fecC+"'"+","+		//-- 
						detallesClientes.getMontoCredito()+","+			//-- 
						detallesClientes.getMontoGarantia()+","+		//-- 
						"'"+detallesClientes.getCalificacion()+"'"+","+			//-- 
						"'"+detallesClientes.getObservacion()+"'"+","+		//-- 
						"'"+detallesClientes.getEstadodet()+"'"+","+			//-- 
						"'"+detallesClientes.getDescripcion()+"'"+","+		//-- 
						"'"+detallesClientes.getCodigoProveedorAuto()+"'"+","+		//-- 			
						"'"+detallesClientes.getVendedorId()+"'"+			//-- 			
						")";	
						//-- 	
						
						System.out.println("		idDetalle:>"+idDetalle);	
						
						stmt.executeUpdate(insertarDetalle);
						
										

						idDetalle++;
					}				  
					 
				}
				
					
				//conn.commit();
				stmt.close();
				conn.close();
				System.out.println ("Importacion Terminada.");  
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			hacerRollBack();
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch(Exception e ){
			hacerRollBack();
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	static void hacerRollBack(){
		try {
			if ( conn != null ){
				conn.rollback();
				conn.close();
				System.out.println("Se hace rollback y se cierra la coneccion");
			}
			if ( stmt != null ){
				stmt.close();
			}
		} catch (SQLException e1) {
			System.out.println("En el rollback: "+e1.getMessage());
			//e1.printStackTrace();
		}
	}
	
	static double dos(double valor){
		return Math.round(valor*Math.pow(10,2D))/Math.pow(10,2D);
	}

}
