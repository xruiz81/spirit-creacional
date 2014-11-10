package ImportarGenericoProducto;

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

import com.spirit.util.Utilitarios;

public class ImportarGenericoProducto {

	//PARA CARTERA
	static Connection conn = null;
	static List<ProductoGenerico> detalles = null;
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
			String nombreHoja = "prodServProveedor";
			 
			NumberFormat dosDecimales = new DecimalFormat("00");			
			detalles = VerificaProveedor.verificarCuentas(driverBase,urlBase,usuario,clave,nombrearchivoExcel,nombreHoja);
			
			
			if ( detalles != null ){
				Class.forName(driverBase);	
				//String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
				conn = DriverManager.getConnection(urlBase,usuario, clave);	
				stmt = conn.createStatement();				
				Long empresaId = new Long(1);
				Long periodoId = new Long(60);
				Long plancuentaId = new Long(1);
				int mes = 2; //1=ENERO , 2=FEBRERO, ...
				String fecha = "to_date('2010/"+dosDecimales.format(mes)+"/1', 'yyyy/mm/dd')";
				String status = "P";
				String efectivo	= "N";
				String observacion = "NINGUNA";
			//	Long oficinaId = new Long(1);
				String subtipoasientoId = null;
				String tipodocumentoId = null;
				String transaccionId = null;
				
	
				Long idProducto = null ;
				String query = "select max(id) from PRODUCTO";
				ResultSet rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idProducto = rs.getLong(1);
				}
				if (idProducto == null)
					throw new Exception("Falla en la busqueda de id de cabecera");				
				idProducto++;
				System.out.println("idCabecera: "+idProducto);
				rs =null;
	
				Long idGenerico = null;
				query = "select max(id) from GENERICO";
				rs = stmt.executeQuery(query);
				while ( rs.next() ){
					idGenerico = rs.getLong(1);
				}
				if (idGenerico == null)
					throw new Exception("Falla en la busqueda de id del detalle");
				
				idGenerico++;				
			
				
				NumberFormat formatoNumero = new DecimalFormat("0000000000");
				String queryMaxCodigo ="select max(P.CODIGO) from PRODUCTO P";
				Long secuencialCodigo = null;
				String numeroQuery = null;
				ResultSet rs2 = stmt.executeQuery(queryMaxCodigo);
				if( rs2.next() ){
					numeroQuery = rs2.getString(1);									
				}
				
				if (numeroQuery == null)				
					numeroQuery=new String("1");
				
				secuencialCodigo=new Long( numeroQuery );
				
				rs =null;				
				int numeroCabecera = -1;
				boolean tieneDetalles = false;
				for ( ProductoGenerico productoGeneridodatos : detalles ){
										
						if ( tieneDetalles ){
							idGenerico++;
							tieneDetalles = false;
						}
						
						String queryGenerico ="select CL.id from GENERICO CL WHERE CL.NOMBRE='"+productoGeneridodatos.getNombre()+"'";
						Long idExiste=null;
						
						ResultSet rs3 = stmt.executeQuery(queryGenerico);
						if( rs3.next() ){
							idExiste = rs3.getLong(1);									
						}
						else{
						
							String insertarCabecera = "insert into GENERICO values ( "+
							idGenerico+","+	// --ID
							"'"+productoGeneridodatos.getCodigogen()+"',"+			//--CODIGOGENERICO
							"'"+productoGeneridodatos.getNombre()+"',"+			//--NOMBRE(30)
							"'"+productoGeneridodatos.getAbreviado()+"',"+			//--ABREVIADO(20)							
							"'"+productoGeneridodatos.getNombreGenerico()+"',"+	//--NOMBRE GENERICO(30)
							"'S'"+","+										//--CAMBIO DESCRIPCION
							"'"+empresaId+"',"+			//--EMPRESA ID
							"'"+productoGeneridodatos.getTipoproductoId()+"',"+	//--TIPO PROD
							"null"+","+										//--MEDIDA ID
							"''"+","+											//--PARTIDA ARANCELARIA	
							"'2010-02-24 10:17:52'"+","+						//--FECHA CREACION
							"'"+productoGeneridodatos.getReferencia()+"',"+		//--REFERENCIA
							"'"+productoGeneridodatos.getUsaLote()+"',"+			//--USALOTE
							"'"+productoGeneridodatos.getLineaId()+"',"+			//--LINEA ID
							"'"+productoGeneridodatos.getIva()+"',"+				//--IVA
							"'"+productoGeneridodatos.getIce()+"',"+				//--ICE
							"'"+productoGeneridodatos.getOtroImpuesto()+"',"+		//--OTRO IMPUESTO
							"'"+productoGeneridodatos.getServicio()+"',"+			//--SERVICIO
							"'"+productoGeneridodatos.getFamiliaGenericoId()+"',"+	//--FAM. GENERICO
							"'"+productoGeneridodatos.getSerie()+"',"+				//--SERIE
							"'"+productoGeneridodatos.getAfectastock()+"',"+		//--AFECTA STOCK
							"'"+productoGeneridodatos.getEstado()+"',"+			//--ESTADO
							"'"+productoGeneridodatos.getCobraIva()+"',"+			//--COBRA IVA
							"'"+productoGeneridodatos.getCobraIce()+"',"+			//--COBRA ICE
							"'"+productoGeneridodatos.getMediosProduccion()+"',"+	//--MEDIOS PRODUCCION						
							"'"+productoGeneridodatos.getLlevaInventario()+"',"+	//--LLEVA INVENTARIO
							"'"+productoGeneridodatos.getAceptaDescuento()+"'"+	//--ACEPTA DSCTO.	
							")";
							
							System.out.println("Cabecera id:  "+insertarCabecera);						
							stmt.executeUpdate(insertarCabecera);						
							tieneDetalles = true;						
						}
						
					System.out.println("SECUENCIAL CODIGO PRODUCTO:"+formatoNumero.format(secuencialCodigo));	
						
					//INSERTA EL DETALLE (PRODUCTO... se crea un producto por proveedor)
					{
						
						System.out.println("--->"+productoGeneridodatos.getProveedorIdentificacion());
						String queryClienteId ="select C.id from CLIENTE C WHERE C.IDENTIFICACION='"+productoGeneridodatos.getProveedorIdentificacion()+"'";
						Long clienteId=null;
						
						ResultSet rs4 = stmt.executeQuery(queryClienteId);
						if( rs4.next() ){
							clienteId = rs4.getLong(1);									
						}
						
						Long idGenericoDetalle=null;
						if(idExiste!=null) idGenericoDetalle=idExiste;
						else idGenericoDetalle=idGenerico;
													
						String insertarDetalle = "insert into PRODUCTO values ( " +
						idProducto+","+							//--ID
						idGenericoDetalle+","+					//--GENERICO_ID
						"null"+","+								//--PRESENTACION_ID
						clienteId+","+							//--PROVEEDOR ID
						"'L'"+","+								//--ORIGEN PRODUCTO						
						"''"+","+									//--CODIGO BARRAS	
						"0.00"+","+								//--COSTO
						"'2010-02-24 10:17:52'"+","+				//--fecha creacion
						"0.00"+","+								//--REBATE
						"'S'"+","+								//--ACEPTAPROMOCION
						"'S'"+","+								//--PERMITE VENTA
						"'S'"+","+								//--ACEPTADEV
						"'S'"+","+								//--CAMBIOPRECIO
						"'"+productoGeneridodatos.getEstadoprod()+"',"+		//--ESTADOPROD
						"'"+formatoNumero.format(secuencialCodigo)+"',"+		//--CODIGOPROD 	
						"0.00"+","+										//--MARGEN MINIMO
						"''"+","+											//--SUBPROVEEDOR			
						"0.00"+","+										//--COSTOLIFO
						"0.00"+","+										//--COSTOFIFO
						"0.00"+","+										//--PESO BRUTO
						"0.00"+","+										//--PESO NETO
						"null"+","+										//--COLOR ID
						"null"+","+										//--MARCA ID
						"null"+","+										//--MODELO ID
						"'N'"+")";										//--GENERAR CODIGO BARRAS
						;		//--HABER 
		
						System.out.println("insertarDetalle id:  "+insertarDetalle);	
						
						stmt.executeUpdate(insertarDetalle);	
						
						
						
						idProducto++;
						secuencialCodigo++;
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
