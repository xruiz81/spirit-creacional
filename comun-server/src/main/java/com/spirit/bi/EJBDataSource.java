package com.spirit.bi;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.facturacion.session.FacturaDetalleSessionLocal;

public class EJBDataSource {
	
	private static JPAManagerLocal jpaManagerLocal;
	private static QueryHelperServerLocal queryHelperServerLocal;
	private static FacturaDetalleSessionLocal facturaDetalleSessionLocal;
	
	public static JPAManagerLocal getJpaManagerLocal() {
		return jpaManagerLocal;
	}

	public static void setJpaManagerLocal(JPAManagerLocal jpaManagerLocal) {
		EJBDataSource.jpaManagerLocal = jpaManagerLocal;
	}

	public static QueryHelperServerLocal getQueryHelperServerLocal() {
		return queryHelperServerLocal;
	}

	public static void setQueryHelperServerLocal(
			QueryHelperServerLocal queryHelperServerLocal) {
		EJBDataSource.queryHelperServerLocal = queryHelperServerLocal;
	}

	public static FacturaDetalleSessionLocal getFacturaDetalleSessionLocal() {
		return facturaDetalleSessionLocal;
	}

	public static void setFacturaDetalleSessionLocal(
			FacturaDetalleSessionLocal facturaDetalleSessionLocal) {
		EJBDataSource.facturaDetalleSessionLocal = facturaDetalleSessionLocal;
	}
}
