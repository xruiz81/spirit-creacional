package chequesemitidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ImportacionAsientos.DetalleAsiento;

public class ChequesAnulados {

	static Connection conn = null;
	static List<DetalleAsiento> detalles = null;
	static Statement stmt = null;
	static boolean guardar = false;
	
	public static void main(String[] a ){

		try {
			
			String usuario = "desarrollo";
			String clave = "desarrollo";
			String urlBase = "jdbc:oracle:thin:@//110.110.2.229:1521/crea";
			String driverBase = "oracle.jdbc.driver.OracleDriver";
			//NumberFormat dosDecimales = new DecimalFormat("00");
			
			Class.forName(driverBase);
			conn = DriverManager.getConnection(urlBase,usuario, clave);
			//conn.setAutoCommit(true);
			stmt = conn.createStatement();
			
			//DESDE ASIENTO
			String queryAsientos = 
				" select a.id,a.fecha,cb.id,ad.referencia,a.observacion,ad.glosa,sum(ad.debe),sum(ad.haber) "+
				" from log_asiento a,log_asiento_detalle ad,cuenta c,cuenta_entidad ce,cuenta_bancaria cb "+
				" where a.id = ad.log_asiento_id  "+
				" 	and a.tipo_Documento_Id is null and a.transaccion_Id is null "+
				" 	and ad.cuenta_id = c.id and c.id = ce.cuenta_id and ce.entidad_id = cb.id "+
				"   and ( ad.cuenta_Id = 9 or ad.cuenta_Id = 10 or ad.cuenta_Id = 11 ) "+
				"   and ad.haber > 0.0 "+
				"   and ad.referencia is not null "+
				" group by a.id,a.fecha,cb.id,ad.referencia,a.observacion,ad.glosa"+
				" order by a.fecha asc ";
			ResultSet rs = stmt.executeQuery(queryAsientos);
			ArrayList<ChequeEmitidoDatos> chequesEmitidosAsiento = new ArrayList<ChequeEmitidoDatos>();
			while ( rs.next() ){
				ChequeEmitidoDatos chd = new ChequeEmitidoDatos(null,
						new Date(rs.getDate(2).getTime()),rs.getLong(3),rs.getString(4)
						,rs.getString(6),rs.getDouble(8),null,rs.getLong(1),"A",rs.getString(5)
						,null,"A");
				
				try{
					Integer numeroCheque = Integer.parseInt(chd.getNumero());
					if ( numeroCheque > 0  && chd.getValor() > 0D){
						chequesEmitidosAsiento.add(chd);
					}
				} catch( Exception e ){
					
				}
			}
			
			
			if ( rs!=null )
				rs.close();
			rs = null;
			String queryPrimaryKey = "select max(id) from cheque_emitido";
			rs = stmt.executeQuery(queryPrimaryKey);
			Long id = null;
			while( rs.next() ){
				id = rs.getLong(1);
			}
			if ( id == null )
				throw new Exception("No se puede calcular id !!!");
			
			//DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			for ( ChequeEmitidoDatos chequeEmitido : chequesEmitidosAsiento ){
				boolean existe = existeCheque(chequeEmitido);
				if ( !existe  && chequeEmitido.getValor() > 0D){
					id++;
					String queryInsert = "insert into cheque_emitido values (" +
						id+","+
						"to_date('"+chequeEmitido.getFecha().toString()+"','yyyy/mm/dd'),"+
						chequeEmitido.getCuentaBancariaId()+","+
						"'"+chequeEmitido.getNumero()+"',"+
						"'"+chequeEmitido.getDetalle().trim()+"',"+
						chequeEmitido.getValor()+","+
						"'"+chequeEmitido.getEstado()+"',"+
						" null ," +
						chequeEmitido.getTransaccionId()+","+
						"'"+chequeEmitido.getBeneficiario()+ "' )";
					if ( guardar )
					stmt.executeUpdate(queryInsert);
					System.out.println(chequeEmitido);
				}
			}
			
			
			if ( rs!=null )
				rs.close();
			rs = null;
			
			System.out.println("\n------------------------------------------------------------------------------" +
					"\n------------------------------------------------------------------------------\n\n");
			
			//DESDE CARTERA
			String queryCartera = 
				" select a.id,a.fecha,cb.id,cd.preimpreso,cl.razon_social,a.tipo_documento_id,a.transaccion_id,ca.comentario,sum(ad.debe),sum(ad.haber) "+
				" from log_asiento a,log_asiento_detalle ad,cuenta c,cuenta_entidad ce,cuenta_bancaria cb, "+
				"      cartera ca,cartera_detalle cd,cliente cl, cliente_oficina co "+
				" where a.id = ad.log_asiento_id  "+
				" 	  and a.tipo_Documento_Id is not null and a.transaccion_Id is not null "+
				" 	  and ad.cuenta_id = c.id and c.id = ce.cuenta_id and ce.entidad_id = cb.id "+
				" 	  and a.transaccion_id = ca.id and a.tipo_documento_id = ca.tipodocumento_id and ca.id = cd.cartera_id "+
				" 	  and ca.clienteoficina_id = co.id and co.cliente_id = cl.id "+
				" 	  and ( ad.cuenta_Id = 9 or ad.cuenta_Id = 10 or ad.cuenta_Id = 11 ) "+
				" 	  and ( ca.tipodocumento_id = 120 or ca.tipodocumento_id = 13 ) "+
				" 	  and cd.tipopago_Id is not null "+
				" 	  and ad.haber > 0.0 "+
				"     and cd.preimpreso is not null "+
				"     and ca.estado = 'A' "+
				" group by a.id,a.fecha,cb.id,cd.preimpreso,cl.razon_social,a.tipo_documento_id,a.transaccion_id,ca.comentario"+
				" order by a.fecha asc ";
			rs = stmt.executeQuery(queryCartera);
			ArrayList<ChequeEmitidoDatos> chequesEmitidosCartera = new ArrayList<ChequeEmitidoDatos>();
			while ( rs.next() ){
				ChequeEmitidoDatos chd = new ChequeEmitidoDatos(null,
						new Date(rs.getDate(2).getTime()),rs.getLong(3),rs.getString(4)
						,rs.getString(8),rs.getDouble(10),rs.getLong(6),rs.getLong(7),"A",rs.getString(5)
						,null,"C");
				
				try{
					Integer numeroCheque = Integer.parseInt(chd.getNumero());
					if ( numeroCheque > 0  && chd.getValor() > 0D){
						chequesEmitidosCartera.add(chd);
					}
				} catch( Exception e ){
					
				}
			}
			
			
			for ( ChequeEmitidoDatos chequeEmitido : chequesEmitidosCartera ){
				boolean existe = existeCheque(chequeEmitido);
				if ( !existe  && chequeEmitido.getValor() > 0D){
					id++;
					String queryInsert = "insert into cheque_emitido values (" +
						id+", "+
						"to_date('"+chequeEmitido.getFecha().toString()+"','yyyy/mm/dd'),"+
						chequeEmitido.getCuentaBancariaId()+","+
						"'"+chequeEmitido.getNumero()+"',"+
						"'"+chequeEmitido.getDetalle()+"',"+
						chequeEmitido.getValor()+", "+
						"'"+chequeEmitido.getEstado()+"',"+
						chequeEmitido.getTipoDocumentoId()+","+
						chequeEmitido.getTransaccionId()+","+
						"'"+chequeEmitido.getBeneficiario()+"')";
					if ( guardar )
					stmt.executeUpdate(queryInsert);
					System.out.println(chequeEmitido);
				}
			}
			
			if ( rs!=null )
				rs.close();
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try{
				if ( stmt != null  )
					stmt.close();
				if ( conn!=null )
					conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	static boolean existeCheque(ChequeEmitidoDatos chequeEmitido) throws SQLException{
		boolean existe = false;
		String queryExiste = "select * from cheque_emitido where cuenta_bancaria_id = "
			+chequeEmitido.getCuentaBancariaId()+" and numero = "+chequeEmitido.getNumero();
		ResultSet rs = stmt.executeQuery(queryExiste);
		if( rs.next() ){
			existe = true;
		}
		return existe;
	}
	
	static double dos(double valor){
		return Math.round(valor*Math.pow(10,2D))/Math.pow(10,2D);
	}
	
	
}
