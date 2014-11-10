package ImportarCartera;

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

import com.spirit.util.Utilitarios;

public class ImportarCartera {

	//PARA CARTERA
	static Connection conn = null;
	static List<CarteraDetalleImportadaData> detalles = new ArrayList<CarteraDetalleImportadaData>();
	static Statement stmt = null;
	
	public static void main(String[] a ){

		try {
			
			/*String usuario = "desarrollo";
			String clave = "desarrollo";
			String urlBase = "jdbc:oracle:thin:@//110.110.2.229:1521/crea";
			String driverBase = "oracle.jdbc.driver.OracleDriver";
			String nombrearchivoExcel = "c:\\ImportacionAsientos\\DIARIOS SEPTIEMBRE.xls";
			String nombreHoja = "AsientoDetalle";
			NumberFormat dosDecimales = new DecimalFormat("00");			
			detalles = VerificaCuentas.verificarCuentas(driverBase,urlBase,usuario,clave,nombrearchivoExcel,nombreHoja);	*/
			
			String usuario = "root";
			String clave = "master";
			String urlBase = "jdbc:mysql://localhost:3306/SPIRIT_UMA";
			String driverBase = "com.mysql.jdbc.Driver";
			String nombrearchivoExcel = "c:\\SQL_UMA\\FormatoDatosPARAVersalityMySQL-Julio2011.xls";
			String nombreHoja = "ctasxCOB";
			String tipoCartera = "'C'";
			nombreHoja = "ctasXpag";
			tipoCartera = "'P'";
			NumberFormat dosDecimales = new DecimalFormat("00");			
			detalles = VerificaCuentas.verificarCuentas(driverBase,urlBase,usuario,clave,nombrearchivoExcel,nombreHoja);
			
			
			if ( detalles != null  && detalles.size() > 0){
				Class.forName(driverBase);	
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				conn = DriverManager.getConnection(urlBase,usuario, clave);	
				stmt = conn.createStatement();				
				int mes = 12; //1=ENERO , 2=FEBRERO, ...
				String anio = "2010";
				//String fecha = "to_date('2011/"+dosDecimales.format(mes)+"/1', 'yyyy/mm/dd')";
				
				String baseNumero = "UMA-FAC-"+dosDecimales.format(mes)+"-"+anio+"-";
				if(nombreHoja.equals("ctasXpag")) baseNumero = "UMA-COM-"+dosDecimales.format(mes)+"-"+anio+"-";
				
				NumberFormat formatoNumero = new DecimalFormat("00000");
	
				Long idCabecera = null ;
				String query = "select max(id) from CARTERA";
				ResultSet rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idCabecera = rs.getLong(1);
				}
				if (idCabecera == null)
					throw new Exception("Falla en la busqueda de id de cabecera");				
				idCabecera++;
				System.out.println("idCabecera: "+idCabecera);
				rs =null;
	
				Long idDetalle = null;
				query = "select max(id) from CARTERA_DETALLE";
				rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idDetalle = rs.getLong(1);
				}
				if (idDetalle == null)
					throw new Exception("Falla en la busqueda de id del detalle");
				
				idDetalle++;				
				
				rs =null;
				
				Long secuencialIdCabecera = null;
				query = "select max(a.CODIGO) from CARTERA a where a.CODIGO like 'UMA-FAC-"+dosDecimales.format(mes)+"-2011%'";
				if(nombreHoja.equals("ctasXpag")) query = "select max(a.CODIGO) from CARTERA a where a.CODIGO like 'UMA-COM-"+dosDecimales.format(mes)+"-2011%'";
				
				rs = stmt.executeQuery(query);
				String numeroQuery = "";
				while ( rs.next() ){
					numeroQuery = rs.getString(1);
				}
				
				if (numeroQuery == null){
					secuencialIdCabecera=new Long("1");
				}else if ( numeroQuery.split("-").length !=4 ){
					throw new Exception("Formato de Numero de cabecera no es de la forma 1-2-3-4..");
				}else{
					String secuencialIdCabeceraString = numeroQuery.split("-")[4];				
					secuencialIdCabecera = new Long( secuencialIdCabeceraString );				
					secuencialIdCabecera++;
					System.out.println("secuencialIdCabecera: "+secuencialIdCabecera);
				}				
				
				System.out.println("secuencialIdCabecera: "+secuencialIdCabecera);
				
				rs =null;
				
				int numeroCabecera = -1;
				boolean tieneDetalles = false;
				//for ( CarteraCDetalle detalleAsiento : detalles ){
				for(int i=0; i<detalles.size(); i++){			
					CarteraDetalleImportadaData detalleAsiento = (CarteraDetalleImportadaData) detalles.get(i);

					//mes = detalleAsiento.getFechaCartera().getMonth()+1;
					
					//baseNumero = "UMA-FAC-"+dosDecimales.format(mes)+"-2011-";
					//if(nombreHoja.equals("ctasXpag")) baseNumero = "UMA-COM-"+dosDecimales.format(mes)+"-2011-";
					

					if (tieneDetalles) {
						idCabecera++;
						tieneDetalles = false;
					}
					// idCabecera=detalleAsiento.getId();

					Long oficinaId = null;
					// query ="select id from OFICINA OF WHERE
					// OF.NOMBRE='"+detalleAsiento.getOficina()+"' AND
					// OF.EMPRESA_ID='"+detalleAsiento.getEmpresa()+"'";
					query = "select id from OFICINA OF WHERE OF.NOMBRE='"
							+ detalleAsiento.getOficina() + "'";
					
					System.out.println("query Oficina:  " + query);
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						oficinaId = rs.getLong(1);
					}

					Long idClienteOficina = null;
					query = "select CO.id from CLIENTE CL, CLIENTE_OFICINA CO WHERE CO.CLIENTE_ID=CL.ID AND CL.NOMBRE_LEGAL='"
							+ detalleAsiento.getClienteruc() + "'";
					rs = stmt.executeQuery(query);
					
					while (rs.next()) {
						idClienteOficina = rs.getLong(1);
					}

					Long idTipoDocumento = null;
					query = "select id from TIPO_DOCUMENTO where NOMBRE = '"
							+ detalleAsiento.getTipodoc() + "'";
					rs = stmt.executeQuery(query);
					
					while (rs.next()) {
						idTipoDocumento = rs.getLong(1);
					}

					String estado = "N";
					String insertarCabecera = "insert into CARTERA values ( "
							+ idCabecera
							+ ","
							+ // --ID
							tipoCartera
							+","
							+ // --TIPO
							oficinaId
							+ ","
							+ // --OFICINA_ID
							idTipoDocumento
							+ ","
							+ // --TIPODOC_ID
							"'" + baseNumero
							+ formatoNumero.format(secuencialIdCabecera) + "',"
							+ // --CODIGO
							"null" + "," + // --REFERENCIA_ID NULL
							"'" + idClienteOficina + "'," + // --CLIENTEOFICINA_ID
							"'" + detalleAsiento.getPreimpreso() + "'," + // --PREIMPRESO
																			// NULL
							1 + "," + // --USUARIO_ID
							"null" + "," + // --VENDEDOR_ID NULL
							1 + "," + // --MONEDA_ID " +
							"'" + detalleAsiento.getFechaCartera() + "'," + // --FECHA_EM
							detalleAsiento.getSaldo() + "," + // --VALOR
							detalleAsiento.getSaldo() + "," + // --SALDO
							"null" + "," + // --FCHA ULTIMA ACTUALIZACION
							"'" + estado + "'," + // --ESTADO
							"null" + "," + // --COMENTARIO
							"null" + "," + // --APROBADO
							"'2010-12-31'," + // --FECHA CREACION
							"null" + ")"; // --ACTIVAR RETROCOMPATIBILIDAD

					System.out.println("Cabecera id:  " + insertarCabecera);
					stmt.executeUpdate(insertarCabecera);

					secuencialIdCabecera++;

					Long idDocumento = null;
					String autorizacion = "null";
					query = "select id from DOCUMENTO where NOMBRE = '"
							+ detalleAsiento.getTipodoc() + "'";
					rs = stmt.executeQuery(query);
					
					while (rs.next()) {
						idDocumento = rs.getLong(1);
					}

					// INSERTA EL DETALLE
					{
						tieneDetalles = true;
						
						if(detalleAsiento.getAutorizacion() != null && !detalleAsiento.getAutorizacion().equals("")){
							autorizacion = detalleAsiento.getAutorizacion();
						}else{
							autorizacion = "null";
						}
						
						String insertarDetalle = "insert into CARTERA_DETALLE values ( "
								+ idDetalle
								+ ","
								+ // --ID
								idCabecera
								+ ","
								+ // --CARTERAID
								idDocumento
								+ ","
								+ // --DOCUMENTOID
								"null,"
								+ // --SECUENCIAL
								"null,"
								+ // --LINEAID
								"null,"
								+ // --TAIPOPAGO_ID
								"null,"
								+ // --CUENTABANCARIA " +
								"null,"
								+ // --REFERNCIA " +
								"'"
								+ detalleAsiento.getPreimpreso()
								+ "',"
								+ // --PREIMPRESO NULL
								"null,"
								+ // --PREIMPRESO " +
								"'"
								+ detalleAsiento.getFechaCartera()
								+ "',"
								+ "'"
								+ detalleAsiento.getFechaCartera()
								+ "',"
								+ "null,"
								+ "null,"
								+ // --DEPOSITO " +
								detalleAsiento.getSaldo()
								+ ","
								+ // --VALOR
								detalleAsiento.getSaldo()
								+ ","
								+ // --SALDO
								"null,"
								+ "'S'"
								+ ","
								+ autorizacion
								+ ","
								+ // --SALDO
								"null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ // --COMENTARIO
								"null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ // --COMENTARIO
								"null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ // --COMENTARIO
								"null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null,"
								+ "null," + //--COMENTARIO
								"null" + ")";
						//;		//--HABER 

						System.out.println("detalle id:  " + insertarDetalle);
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
