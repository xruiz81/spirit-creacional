package ChequesTransacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import chequesemitidos.ChequeEmitidoDatos;

import ImportacionAsientos.DetalleAsiento;

public class ChequesExistentes {

	static Connection conn = null;
	static List<DetalleAsiento> detalles = null;
	static Statement stmt = null;
	static boolean guardar = true;

	public static void main(String[] args) {
		try {

			String usuario = "desarrollo";
			String clave = "desarrollo";
			String urlBase = "jdbc:oracle:thin:@//110.110.2.229:1521/crea";
			String driverBase = "oracle.jdbc.driver.OracleDriver";

			Class.forName(driverBase);
			conn = DriverManager.getConnection(urlBase,usuario, clave);
			//conn.setAutoCommit(true);
			stmt = conn.createStatement();

			String queryAsientos = " select * from cheque_emitido ";
			ResultSet rs = stmt.executeQuery(queryAsientos);
			ArrayList<ChequeEmitidoDatos> chequesEmitidos= new ArrayList<ChequeEmitidoDatos>();
			while( rs.next() ){
				ChequeEmitidoDatos ce = new ChequeEmitidoDatos(rs.getLong(1),
						rs.getDate(2),rs.getLong(3),rs.getString(4),rs.getString(5),
						rs.getDouble(6),rs.getLong(8),rs.getLong(9),rs.getString(7),
						rs.getString(10),rs.getDate(11),rs.getString(12)
						);
				chequesEmitidos.add(ce);
			}
			if ( rs!=null )
				rs.close();
			rs = null;
			
			int contador = 1;
			for (ChequeEmitidoDatos ce : chequesEmitidos){

				if ( ce.getOrigen()==null || "".equals(ce.getOrigen()) )
					throw new Exception(" Cheque numero \""+ce.getNumero()+"\" no tiene origen !!");
				
				String queryInsert = "insert into cheque_transaccion values (" +
				contador+", "+
				ce.getId()+","+
				ce.getTransaccionId()+","+
				"'"+ce.getOrigen()+"')";
				
				if ( guardar ){
					stmt.executeUpdate(queryInsert);
					System.out.println(" Cheque numero \""+ce.getNumero()+"\" copiado !!");
				}

				contador++;
			}

		} catch( Exception e){
			e.printStackTrace();

		} finally{
			if ( conn != null ){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("---Error en conn : ");
					e.printStackTrace();
				}
			}
		}

	}

}
