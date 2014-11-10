package com.truemesh.squiggle.test;

import com.spirit.contabilidad.entity.AsientoEJB;
import com.spirit.general.entity.TipoDocumentoEJB;
import com.spirit.inventario.entity.GenericoEJB;
import com.spirit.inventario.entity.ProductoEJB;
import com.truemesh.squiggle.SQTables;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.criteria.AND;
import com.truemesh.squiggle.criteria.MatchCriteria;
import com.truemesh.squiggle.criteria.OR;

public class Test {
	
	public static void main(String[] args) {
		/*SelectQuery select = new SelectQuery();
		Table asientoTable = new Table(AsientoEJB.class);
		Table tipoDocumentoTable = new Table(TipoDocumentoEJB.class);
		select.addObject(asientoTable);
		select.addJoin(asientoTable, "tipoDocumentoId", tipoDocumentoTable, "id");
		MatchCriteria egm = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "EGM");
		MatchCriteria inm = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "INM");
		select.addCriteria(new OR(egm, inm));*/
		
		/*SelectQuery select = new SelectQuery();
		Table asientoTable = new Table(AsientoEJB.class);
		Table tipoDocumentoTable = new Table(TipoDocumentoEJB.class);
		select.addObject(asientoTable);
		select.addJoin(asientoTable, "tipoDocumentoId", tipoDocumentoTable, "id");
		MatchCriteria egm = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "EGM");
		MatchCriteria inm = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "INM");
		MatchCriteria itr = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "ITR");
		MatchCriteria etr = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "ETR");
		MatchCriteria ajn = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "AJN");
		MatchCriteria ajp = new MatchCriteria(tipoDocumentoTable, "codigo", MatchCriteria.EQUALS, "AJP");
		select.addCriteria(new OR(new OR(new OR(egm, inm), new OR(itr, etr)), new OR(ajn, ajp)));
		select.addCriteria(new MatchCriteria(tipoDocumentoTable, "empresaId", MatchCriteria.EQUALS, 1L));
		System.out.println(select.getQueryString());*/
		
		String strCriterio = "POLO%";
		String strEstado = "A";
		Long strProveedorId = 28L; 
		SelectQuery select = new SelectQuery();
		Table productoTable = new Table(ProductoEJB.class);
		Table genericoTable = new Table(GenericoEJB.class);
		select.addObject(productoTable);
		select.addJoin(productoTable, "genericoId", genericoTable, "id");
		MatchCriteria codigo = new MatchCriteria(productoTable, "codigo", MatchCriteria.LIKE, strCriterio);
		MatchCriteria codigoBarras = new MatchCriteria(productoTable, "codigoBarras", MatchCriteria.LIKE, strCriterio);
		MatchCriteria nombre = new MatchCriteria(genericoTable, "nombre", MatchCriteria.LIKE, strCriterio);
		MatchCriteria estado = new MatchCriteria(productoTable, "estado", MatchCriteria.EQUALS, strEstado);
		MatchCriteria proveedorId = new MatchCriteria(productoTable, "proveedorId", MatchCriteria.EQUALS, strProveedorId);
		select.addCriteria(new AND(new AND(new OR(new OR(codigo, codigoBarras), nombre), estado), proveedorId));
		System.out.println(select.getQueryString());
	}
}
