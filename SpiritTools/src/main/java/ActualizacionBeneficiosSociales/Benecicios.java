package ActualizacionBeneficiosSociales;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Benecicios {

	static Connection conn = null;
	static Statement stmtQ1 = null;
	static Statement stmtQ2 = null;
	
	static Statement stmtU1 = null;
	
	public static void main(String[] args) {
		try{
			String usuario = "pruebas";
			String clave = "pruebas";
			String urlBase = "jdbc:oracle:thin:@//192.168.100.9:1521/crea";
			String driverBase = "oracle.jdbc.driver.OracleDriver";
			
			boolean ejecutarUpdates = true;
	
			Class.forName(driverBase);
			conn = DriverManager.getConnection(urlBase,usuario, clave);
			//conn.setAutoCommit(true);

			stmtQ1 = conn.createStatement();
			
			String queryAsientos = " select * from rol_pago_detalle_beneficios " +
					" where rol_pago_id = 339 " +
					//" and contrato_id = 33 " +
					" order by id ";
			
			ResultSet rs = stmtQ1.executeQuery(queryAsientos);
			
			ArrayList<String> observaciones = new ArrayList<String>();
			//ResultSet rsi = null; 
			
			Long dtId = 53L;
			Long dcId = 55L;
			Long frId = 54L;
			
			Long apId = 44L;
			Long ieceId = 42L;
			Long secapId = 43L; 
			
			
			while( rs.next() ){
				Long id = rs.getLong(1);
				Long contratoId = rs.getLong(2);
				Long rolpagoId = rs.getLong(3);
				Double dt = rs.getDouble(4);	//53
				Double dc = rs.getDouble(5);	//55
				Double vac = rs.getDouble(6);
				Double fr = rs.getDouble(7);
				Double ap = rs.getDouble(8);
				
				System.out.println(rs.getRow()+") id:"+id+" -ci:"+contratoId+" -rpi:"+rolpagoId+
						" -dt:"+dt+" -dc:"+dc+" -vac:"+vac+" -fr:"+fr+" -ap:"+ap);
				
				//-----DECIMO TERCERO
				//decimoTercero(ejecutarUpdates, observaciones, dtId, contratoId,
				//		rolpagoId, dt);

				
				//-----FONDO RESERVA
				//fondoReserva(ejecutarUpdates, observaciones, frId, contratoId,
				//		rolpagoId, fr);
				
				
				//-----DECIMO CUARTO
				//decimoCuarto(ejecutarUpdates, observaciones, dcId,
				//		contratoId, rolpagoId, dc);
				
				//-----APORTE PATRONAL CON IECE Y SECAP
				//aportePatronalSinIeceSecap(ejecutarUpdates, observaciones, apId, 
				//		ieceId, secapId, contratoId, rolpagoId, ap);
				
			}
			rs.close();
			
			conn.close();
			System.out.println("----CERRADO-----");
		} catch(Exception e){
			e.printStackTrace();
			try {
				conn.close();
				System.out.println("----CERRADO-----");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	}



	private static void decimoCuarto(boolean ejecutarUpdates,
			ArrayList<String> observaciones, Long dcId,
			Long contratoId, Long rolpagoId, Double dc) throws SQLException {
		
		Statement stmtQ1 = conn.createStatement();
		Statement stmtU1 = conn.createStatement();
		
		ResultSet rsi;
		int contadorFila;
		Long idDetalle;
		String qdc = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+dcId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdc);

		contadorFila = 0;
		idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1 && ejecutarUpdates){
				String upd = " update rol_pago_detalle " +
				"set valor = "+dc+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			}else {
				if (contadorFila != 1){
					observaciones.add(" DC - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+dcId);
				} else {
					observaciones.add(" DC - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+dcId);
				}

			}
		}
		rsi.close();
		
		stmtQ1.close();
		stmtU1.close();
		
	}

	private static void decimoTercero(boolean ejecutarUpdates,
			ArrayList<String> observaciones, Long dtId, Long contratoId,
			Long rolpagoId, Double dt) throws SQLException {
		
		Statement stmtQ1 = conn.createStatement();
		Statement stmtU1 = conn.createStatement();
		
		ResultSet rsi;
		String qdt = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+dtId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdt);

		int contadorFila = 0;
		Long idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1){
				String upd = " update rol_pago_detalle " +
				"set valor = "+dt+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			} else {
				if (contadorFila != 1){
					observaciones.add(" DT - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubroId = "+dtId);
				} else {
					observaciones.add(" DT - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+dtId);
				}
			}
		}
		rsi.close();
		stmtQ1.close();
		stmtU1.close();
	}
	
	private static void fondoReserva(boolean ejecutarUpdates,
			ArrayList<String> observaciones, Long frId, Long contratoId,
			Long rolpagoId, Double fr) throws SQLException {
		
		Statement stmtQ1 = conn.createStatement();
		Statement stmtU1 = conn.createStatement();
		
		ResultSet rsi;
		String qdt = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+frId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdt);

		int contadorFila = 0;
		Long idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1){
				String upd = " update rol_pago_detalle " +
				"set valor = "+fr+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			} else {
				if (contadorFila != 1){
					observaciones.add(" FR - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubroId = "+frId);
				} else {
					observaciones.add(" FR - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+frId);
				}
			}
		}
		rsi.close();
		stmtQ1.close();
		stmtU1.close();
	}
	
	private static void aportePatronalConIeceSecap(boolean ejecutarUpdates,
			ArrayList<String> observaciones, Long apId,Long ieceId, Long secapId, Long contratoId,
			Long rolpagoId, Double ap) throws SQLException {
		
		Statement stmtQ1 = conn.createStatement();
		Statement stmtU1 = conn.createStatement();
		
		Double aporteaPatronal = redondeoValor( ap * 91.76954733 /100 );
		Double iece = redondeoValor( ap * 4.115226337 /100);
		Double secap = redondeoValor( ap * 4.115226337 /100 );
		
		
		ResultSet rsi;
		
		//VALOR DE APORTA PATRONAL
		String qdt = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+apId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdt);

		int contadorFila = 0;
		Long idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1){
				String upd = " update rol_pago_detalle " +
				"set valor = "+aporteaPatronal+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			} else {
				if (contadorFila != 1){
					observaciones.add(" APORTE PATRONAL - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubroId = "+apId);
				} else {
					observaciones.add(" APORTE PATRONAL - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+apId);
				}
			}
		}
		
		//VALOR DE IECE
		qdt = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+ieceId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdt);

		contadorFila = 0;
		idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1){
				String upd = " update rol_pago_detalle " +
				"set valor = "+iece+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			} else {
				if (contadorFila != 1){
					observaciones.add(" IECE - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubroId = "+ieceId);
				} else {
					observaciones.add(" IECE - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+ieceId);
				}
			}
		}
		
		//VALOR DE SECAP
		qdt = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+secapId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdt);

		contadorFila = 0;
		idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1){
				String upd = " update rol_pago_detalle " +
				"set valor = "+secap+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			} else {
				if (contadorFila != 1){
					observaciones.add(" SECAP - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubroId = "+secapId);
				} else {
					observaciones.add(" SECAP - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+secapId);
				}
			}
		}
		
		
		rsi.close();
		stmtQ1.close();
		stmtU1.close();
	}
	
	private static void aportePatronalSinIeceSecap(boolean ejecutarUpdates,
			ArrayList<String> observaciones, Long apId,Long ieceId, Long secapId, Long contratoId,
			Long rolpagoId, Double ap) throws SQLException {
		
		Statement stmtQ1 = conn.createStatement();
		Statement stmtU1 = conn.createStatement();
		
		Double aporteaPatronal = redondeoValor( ap );
		
		ResultSet rsi;
		
		//VALOR DE APORTA PATRONAL
		String qdt = " select * from rol_pago_detalle where " +
		" contrato_Id = "+contratoId+
		" and rolpago_Id = "+rolpagoId+
		" and rubro_Id = "+apId+
		" and rubro_Eventual_Id is null ";
		rsi = stmtQ1.executeQuery(qdt);

		int contadorFila = 0;
		Long idDetalle = 0L;
		while ( rsi.next() ){
			idDetalle = rsi.getLong(1);
			contadorFila++;
		}
		
		if (ejecutarUpdates){
			if (contadorFila == 1){
				String upd = " update rol_pago_detalle " +
				"set valor = "+aporteaPatronal+" where id = "+idDetalle;
				System.out.println("-U: "+upd);
				stmtU1.executeUpdate(upd);
			} else {
				if (contadorFila != 1){
					observaciones.add(" APORTE PATRONAL - CONTADORFILA :"+contadorFila+":" +
							" contrato_Id = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubroId = "+apId);
				} else {
					observaciones.add(" APORTE PATRONAL - contratoId = "+contratoId+
							" and rolpago_Id = "+rolpagoId+
							" and rubro_Id = "+apId);
				}
			}
		}
		
		rsi.close();
		stmtQ1.close();
		stmtU1.close();
	}
	
	static double redondeoValor(double valor)  {
		double valorR = 0.0;
		Double decimales = 2D;
		valorR = BigDecimal.valueOf(valor).setScale(decimales.intValue(),BigDecimal.ROUND_HALF_UP).doubleValue();
		return valorR;
	}
	
}
