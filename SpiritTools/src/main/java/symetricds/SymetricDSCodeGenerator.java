package symetricds;

public class SymetricDSCodeGenerator {
	private String channel;

	public static void genTrigger(String tableName, String source,
			String target, String channel) {
		System.out.println("-- Tabla: " + tableName + " desde: " + source
				+ " hacia: " + target);
		System.out
				.println("insert into sym_trigger"
						+ "(source_table_name, source_node_group_id, target_node_group_id, channel_id, sync_on_insert, sync_on_update, sync_on_delete, initial_load_order, last_updated_by, last_updated_time, create_time)"
						+ "values('"
						+ tableName
						+ "', '"
						+ source
						+ "', '"
						+ target
						+ "', '"
						+ channel
						+ "', 1, 1, 1, 100, 'xxx', current_timestamp, current_timestamp);\n");
	}

	public static void genTrigersErpToPos(String tableName, String channel) {
		genTrigger(tableName, "erp", "pos", channel);
	}

	public static void genTrigersPosToErp(String tableName, String channel) {
		genTrigger(tableName, "erp", "pos", channel);
	}
	
	public static void genTrigersBiDireccional(String tableName, String channel) {
		genTrigger(tableName, "erp", "pos", channel);
		genTrigger(tableName, "pos", "erp", channel);
	}

	public static void genErpToPosTrigers() {
		String channel = "general";

		genTrigersErpToPos("BANCO", channel);
		genTrigersErpToPos("BODEGA", channel);
		genTrigersErpToPos("CAJA", channel);
		genTrigersErpToPos("CENTRO_GASTO", channel);
		genTrigersErpToPos("CIUDAD", channel);
		genTrigersErpToPos("COLOR", channel);
		genTrigersErpToPos("CRUCE_DOCUMENTO", channel);
		genTrigersErpToPos("CUENTA", channel);
		genTrigersErpToPos("CUENTA_BANCARIA", channel);
		genTrigersErpToPos("CUENTA_ENTIDAD", channel);
		genTrigersErpToPos("DEPARTAMENTO", channel);
		genTrigersErpToPos("DOCUMENTO", channel);
		genTrigersErpToPos("DONACION_TIPOPRODUCTO", channel);
		genTrigersErpToPos("EMBALAJE", channel);
		genTrigersErpToPos("EMBALAJE_PRODUCTO", channel);

		genTrigersErpToPos("EMPLEADO", channel);
		genTrigersErpToPos("EMPRESA", channel);
		genTrigersErpToPos("EVENTO_CONTABLE", channel);
		genTrigersErpToPos("FAMILIA_GENERICO", channel);
		genTrigersErpToPos("FORMA_PAGO", channel);
		genTrigersErpToPos("FUNCION_BODEGA", channel);
		
		
		genTrigersErpToPos("GENERICO", channel);
		genTrigersErpToPos("LINEA", channel);
		genTrigersErpToPos("LISTA_PRECIO", channel);
		genTrigersErpToPos("LOTE", channel);
		genTrigersErpToPos("MARCA_PRODUCTO", channel);
		genTrigersErpToPos("MEDIDA", channel);
		genTrigersErpToPos("MENU", channel);
		genTrigersErpToPos("MODELO", channel);
		genTrigersErpToPos("MODULO", channel);
		
		genTrigersErpToPos("MONEDA", channel);
		genTrigersErpToPos("MOTIVO_DOCUMENTO", channel);
		genTrigersErpToPos("MULTAS_DOCUMENTO", channel);
		
		genTrigersErpToPos("OFICINA", channel);
		genTrigersErpToPos("ORIGEN_DOCUMENTO", channel);
		genTrigersErpToPos("PAIS", channel);
		genTrigersErpToPos("PARAMETRO_EMPRESA", channel);
		genTrigersErpToPos("PERIODO", channel);
		genTrigersErpToPos("PERIODO_DETALLE", channel);
		genTrigersErpToPos("PLANTILLA", channel);
		genTrigersErpToPos("PLAN_CUENTA", channel);
		genTrigersErpToPos("PRECIO", channel);
		
		genTrigersErpToPos("PRESENTACION", channel);
		genTrigersErpToPos("PRODUCTO", channel);
		genTrigersErpToPos("PROVINCIA", channel);
		genTrigersErpToPos("PUNTOS_TIPO_PRODUCTO", channel);
		genTrigersErpToPos("PUNTO_IMPRESION", channel);
		genTrigersErpToPos("ROL", channel);
		genTrigersErpToPos("ROL_OPCION", channel);
		genTrigersErpToPos("ROL_USUARIO", channel);
		genTrigersErpToPos("SERVIDOR", channel);
		
		genTrigersErpToPos("SRI_AIR", channel);
		genTrigersErpToPos("SRI_CLIENTE_RETENCION", channel);
		genTrigersErpToPos("SRI_IDENTIF_TRANSACCION", channel);
		genTrigersErpToPos("SRI_IVA", channel);
		genTrigersErpToPos("SRI_IVA_BIEN", channel);
		genTrigersErpToPos("SRI_IVA_RETENCION", channel);
		genTrigersErpToPos("SRI_IVA_SERVICIO", channel);
		genTrigersErpToPos("SRI_PROVEEDOR_RETENCION", channel);
		genTrigersErpToPos("SRI_SUSTENTO_TRIBUTARIO", channel);
		genTrigersErpToPos("SRI_TIPO_COMPROBANTE", channel);
		genTrigersErpToPos("SRI_TIPO_TRANSACCION", channel);
		
		genTrigersErpToPos("SUBTIPO_ASIENTO", channel);
		genTrigersErpToPos("TARJETA_CREDITO", channel);
		genTrigersErpToPos("TARJETA", channel);
		genTrigersErpToPos("TARJETA_TIPO", channel);
		
		
		
		
		
		genTrigersErpToPos("TIPO_ASIENTO", channel);
		genTrigersErpToPos("TIPO_BODEGA", channel);
		genTrigersErpToPos("TIPO_CLIENTE", channel);
		genTrigersErpToPos("TIPO_CONTACTO", channel);
		genTrigersErpToPos("TIPO_CUENTA", channel);
		genTrigersErpToPos("TIPO_DOCUMENTO", channel);
		genTrigersErpToPos("TIPO_EMPLEADO", channel);
		genTrigersErpToPos("TIPO_IDENTIFICACION", channel);
		genTrigersErpToPos("TIPO_INDICADOR", channel);

		genTrigersErpToPos("TIPO_NEGOCIO", channel);
		genTrigersErpToPos("TIPO_PAGO", channel);
		genTrigersErpToPos("TIPO_PARAMETRO", channel);
		genTrigersErpToPos("TIPO_PRODUCTO", channel);
		genTrigersErpToPos("TIPO_PROVEEDOR", channel);
		genTrigersErpToPos("TIPO_RESULTADO", channel);
		genTrigersErpToPos("TIPO_TROQUELADO", channel);
		genTrigersErpToPos("USUARIO", channel);
		genTrigersErpToPos("USUARIO_CUENTA", channel);
		genTrigersErpToPos("USUARIO_DOCUMENTO", channel);
		genTrigersErpToPos("USUARIO_PUNTO_IMPRESION", channel);
		
		genTrigersErpToPos("TARJETA_TIPO", channel);

		
		
	}

	public static void main(String args[]) {
		genErpToPosTrigers();
	}
}
