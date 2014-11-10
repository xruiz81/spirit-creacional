package ActualizacionBeneficiosSociales;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Nombres {

	

	static Connection conn = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	static Statement stmt3 = null;
	
	public static void main(String[] args) {
		try{
			String usuario = "pruebas";
			String clave = "pruebas";
			String urlBase = "jdbc:oracle:thin:@//192.168.100.9:1521/crea";
			String driverBase = "oracle.jdbc.driver.OracleDriver";
			
			boolean ejecutarUpdates = true;
	
			Class.forName(driverBase);
			conn = DriverManager.getConnection(urlBase,usuario, clave);
			
			//asignarContratosId(ejecutarUpdates);
			
			redondearValores(ejecutarUpdates);
			
			conn.close();
			System.out.println("----CERRADA----");
		} catch(Exception e){
			e.printStackTrace();
			try {
				conn.close();
				System.out.println("----CERRADA----");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static void redondearValores(boolean ejecutarUpdates) throws SQLException {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		
		String queryAsientos = " select * from rol_pago_detalle_beneficios order by id";
		ResultSet rs = stmt.executeQuery(queryAsientos);
		
		int contador = 0;
		while( rs.next() ){
			contador++;
			Long id = rs.getLong(1);
			//Long contratoId = rs.getLong(2);
			//Long rolpagoId = rs.getLong(3);
			Double dt = redondeoValor( rs.getDouble(4) );
			Double dc = redondeoValor( rs.getDouble(5) );
			Double vac = redondeoValor( rs.getDouble(6) );
			Double fr = redondeoValor( rs.getDouble(7) );
			Double ap = redondeoValor( rs.getDouble(8) );
			
			String upd = " update rol_pago_detalle_beneficios " +
			" set DT = "+dt+
			" ,dc = "+dc+
			" ,vac = "+vac+
			" ,fr = "+fr+
			" ,ap = "+ap+
			" where id = "+id;
			
			System.out.println(contador+") "+upd);
			
			if (ejecutarUpdates){
				stmt2.executeUpdate(upd);
			}
		}
		
		stmt.close();
		stmt2.close();
		
	}
	
	static double redondeoValor(double valor)  {
		double valorR = 0.0;
		Double decimales = 2D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}
	
	private static void asignarContratosId(boolean ejecutarUpdates)
			throws SQLException {
		stmt = conn.createStatement();
		stmt2 = conn.createStatement();
		stmt3 = conn.createStatement();

		String queryAsientos = " select * from rol_pago_detalle_beneficios ";
		ResultSet rs = stmt.executeQuery(queryAsientos);
		
		ArrayList<String> noActualizados = new ArrayList<String>();
		
		int contador = 0;
		while( rs.next() ){
			contador++;
			Long id = rs.getLong(1); 
			String nombre = rs.getString(9); 
			
			int contadorFila = 0;
			Long idContrato = 0L;
			
			if ( nombre.contains("ALVARADO") && nombre.contains("VASCONEZ") ){
				if ( nombre.contains("MARCIA") ){
					contadorFila = 1;
					idContrato = 300L;
				} else if ( nombre.contains("MARIA") ){
					contadorFila = 1;
					idContrato = 440L;
				}
				
			} else {
				String nombreCompleto[] = nombre.split(" ");
				
				String apellido = "";
				if ( nombre.startsWith("ZAMORA") || nombre.startsWith("GANGOTENA") ){
					apellido = nombreCompleto[0];
				} else {
					apellido = nombreCompleto.length>=3?
						nombreCompleto[0]+" "+nombreCompleto[1] : nombreCompleto[0];
				}
				//System.out.println(contador+")-Nombre:"+nombre);
				
				String qi = " select c.id from contrato c,empleado e" +
						" where c.empleado_id = e.id and e.apellidos like '"+apellido+"%' " +
						" and c.tipocontrato_Id = 1 ";
				ResultSet rsi = stmt2.executeQuery(qi);
				
				while ( rsi.next() ){
					idContrato = rsi.getLong(1);
					contadorFila++;
				}
				rsi.close();
			}
			System.out.println(contador+")-"+id+"-Nombre:"+nombre+" -Id:"+idContrato);
			
			if ( contadorFila == 1 ){
				if ( ejecutarUpdates ){
					String qu = " update rol_pago_detalle_beneficios set contrato_id = "+idContrato+" " +
							" where id = "+id;
					
					stmt3.executeUpdate(qu);
				}
			} else {
				noActualizados.add(nombre);
			}

		}
		rs.close();
		
		stmt.close();
		stmt2.close();
		stmt3.close();
		
		for ( String nombre : noActualizados ){
			System.out.println("--"+nombre);
		}
	}
	
	
	
}
