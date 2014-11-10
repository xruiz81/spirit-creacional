package ImportacionAsientos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ImportarDatosAsientosExcel {

	//PARA ASIENTOS MANUALES
	static Connection conn = null;
	static List<DetalleAsiento> detalles = null;
	static Statement stmt = null;
	
	public static void main(String[] a ){

		try {
			
			String usuario = "spirit";
			String clave = "spirit";
			String urlBase = "jdbc:mysql://zeus:3306/SPIRIT_PRODUCCION";
			String driverBase = "com.mysql.jdbc.Driver";
			String nombrearchivoExcel = "c:\\ImportacionAsientos\\asiento_apertura_para_importar.xls";
			String nombreHoja = "AsientoDetalle";
			NumberFormat dosDecimales = new DecimalFormat("00");
			
			detalles = VerificaCuentas.verificarCuentas(driverBase,urlBase,usuario,clave,nombrearchivoExcel,nombreHoja);

			if ( detalles != null ){
				Class.forName(driverBase);
	
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				conn = DriverManager.getConnection(urlBase,usuario, clave);
	
				stmt = conn.createStatement();
				
				Long empresaId = new Long(40);
				Long periodoId = new Long(1);
				Long plancuentaId = new Long(1);
				int mes = 1; //1=ENERO , 2=FEBRERO, ...
				String fecha = "'2010-"+dosDecimales.format(mes)+"-01'";
				String status = "P";
				String efectivo	= "N";
				String observacion = "NINGUNA";
				Long oficinaId = new Long(1);
				String subtipoasientoId = null;
				String tipodocumentoId = null;
				String transaccionId = null;
				
				String baseNumero = "AXS-PC-"+dosDecimales.format(mes)+"-2010-";
				NumberFormat formatoNumero = new DecimalFormat("00000");
	
				Long idCabecera = null ;
				String query = "select max(ID) from ASIENTO";
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
				query = "select max(ID) from ASIENTO_DETALLE";
				rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idDetalle = rs.getLong(1);
				}
				if (idDetalle == null)
					throw new Exception("Falla en la busqueda de id del detalle");
				idDetalle++;
				System.out.println("idDetalla: "+idDetalle);
				rs =null;
				
				Long secuencialIdCabecera = null;
				query = "select max(a.NUMERO) from ASIENTO a where a.NUMERO like 'AXS-PC-"+dosDecimales.format(mes)+"-2010%'";
				rs = stmt.executeQuery(query);
				String numeroQuery = "";
				while ( rs.next() ){
					numeroQuery = rs.getString(1);
				}
				String secuencialIdCabeceraString = "";
				if (numeroQuery == null)
					secuencialIdCabeceraString = "0";
					//throw new Exception("Falla en la obtencion de secuencial de id de cabecera");
				if ( numeroQuery != null && numeroQuery.split("-").length != 5 )
					throw new Exception("Formato de Numero de cabecera no es de la forma 1-2-3-4-5");
				if (numeroQuery != null)
					secuencialIdCabeceraString = numeroQuery.split("-")[4];
				secuencialIdCabecera = new Long( secuencialIdCabeceraString );
				secuencialIdCabecera++;
				System.out.println("secuencialIdCabecera: "+secuencialIdCabecera);
				rs =null;
				
				int numeroCabecera = -1;
				boolean tieneDetalles = false;
				for ( DetalleAsiento detalleAsiento : detalles ){
					
					int numeroDetalle = detalleAsiento.getNumero();
					//Se inserta la cabecera
					if ( numeroCabecera != numeroDetalle ){
						
						if ( tieneDetalles ){
							idCabecera++;
							tieneDetalles = false;
						}
						
						String insertarCabecera = "insert into ASIENTO values ( " +
							idCabecera+","+			// --ID
							"'"+baseNumero+formatoNumero.format(secuencialIdCabecera)+"',"+		//--numero
							empresaId+","+			//--EMPRESA_ID
							periodoId+","+			//--PERIODO_ID
							plancuentaId+","+		//--PLAN_CUENTA_ID
							fecha+","+				//--FECHA
							"'"+status+"',"+		//--STATUS
							"'"+efectivo+"',"+		//--EFECTIVO 
							subtipoasientoId+","+	//--SUBTIPO_ASIENTO 
							"'"+observacion+"',"+	//--OBSERVACION
							oficinaId+","+			//--OFICINA_ID " +
							tipodocumentoId+","+	//--TIPO_DOCUMENTO
							transaccionId+","+		//--TRANSACCION_ID
							40+","+					//--ELABORADO_POR_ID
							"null,"+				//--AUTORIZADO_POR_ID
							"null"+")";				//--CARTERA_AFECTA_ID		
					
						stmt.executeUpdate(insertarCabecera);
						System.out.println("Cabecera id:"+idCabecera+ " - CREADA");
						numeroCabecera = numeroDetalle;
						secuencialIdCabecera++;
						
					}
		
					Long idCuenta = null;
					query = "select ID from CUENTA where CODIGO = '"+detalleAsiento.getCuenta()+"'";
					rs = stmt.executeQuery(query);
					while ( rs.next() ){
						idCuenta = rs.getLong(1);
					}
					if ( idCuenta == null )
						throw new Exception("No se encontro id de cuenta con codigo: "+detalleAsiento.getCuenta());
					
					//INSERTA EL DETALLE
					{
						tieneDetalles = true;
						String insertarDetalle = "insert into ASIENTO_DETALLE values ( " +
						idDetalle+","+								// --ID
						idCuenta+","+								//--CUENTA_ID
						idCabecera+","+								//--ASIENTO_ID
						"'"+detalleAsiento.getReferencia()+"',"+	//--REFERENCIA
						"'"+detalleAsiento.getGlosa()+"',"+			//--GLOSA
						"null,"+									//--CENTROGASTO_ID
						"null,"+									//--EMPLEADO_ID " +
						"null,"+									//--DEPARTAMENTO_ID " +
						"null,"+									//--LINEA_ID " +
						"null,"+									//--CLIENTE_ID " +
						dos( detalleAsiento.getDebe() )+","+		//--DEBE " +
						dos( detalleAsiento.getHaber() )+")";		//--HABER 
		
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
