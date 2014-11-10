package ImportacionAsientos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertarDetalleAsientos {

	public static void main(String[] args) 
	throws ClassNotFoundException, SQLException
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
	
			String url = "jdbc:oracle:thin:@//110.110.2.45:1521/des";
			Connection conn = DriverManager.getConnection(url,"spirit", "spirit");
	
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			ResultSet rsMgAsientos = stmt.executeQuery(
					"select c.ID,m.id,m.CUENTA,m.debe,m.haber " +
					"from mg_asiento_inicial m,cuenta c " +
			"where  m.CUENTA = c.CODIGO order by m.ID");
	
			//6285 - spirit
			Long secuencial = new Long(6285);
			while (rsMgAsientos.next()) {
				Long cuentaId = rsMgAsientos.getLong(1);
				//String cuentaCodigo = rsMgAsientos.getString(2);
				Double debe = rsMgAsientos.getDouble(4);
				Double haber = rsMgAsientos.getDouble(5);
	
				String insert = "insert into asiento_detalle values ( " +
				secuencial+","+			// --ID
				cuentaId+","+			//--CUENTA_ID
				"4220,"+				//--ASIENTO_ID
				"'ASIENTO INICIAL',"+	//--REFERENCIA
				"'ASIENTO INICIAL',"+	//--GLOSA
				"null,"+				//--CENTROGASTO_ID
				"null,"+				//--EMPLEADO_ID " +
				"null,"+				//--DEPARTAMENTO_ID " +
				"null,"+				//--LINEA_ID " +
				"null,"+				//--CLIENTE_ID " +
				debe+","+				//--DEBE " +
				haber+")";				//--HABER 
				
				stmt2.executeUpdate( insert );
				secuencial++;
			}
			stmt.close();
			stmt2.close();
			System.out.println ("Ok.");  
		} catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println ("error 1: "+e.toString());  
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println ("error 2: "+e.toString());  
		}
	}

	static double dos(double valor){
		return Math.round(valor*Math.pow(10,2D))/Math.pow(10,2D);
	}
}
