package com.spirit.bi.cubes.facturacion.dim;

import java.util.List;

import com.spirit.bi.Dimension;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.entity.BiDocumentoDimEJB;
import com.spirit.bi.entity.BiVendedorDimEJB;
import com.spirit.general.entity.DocumentoEJB;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.server.SpiritIf;

public class DocumentoDim extends Dimension {

	@Override
	public Object construir(Object objetoConsulta) {
		DocumentoIf documentoIf = (DocumentoIf) objetoConsulta;
		BiDocumentoDimEJB biDocumentoDimEJB = new BiDocumentoDimEJB();
		biDocumentoDimEJB.setDescripcion(documentoIf.getNombre());
		biDocumentoDimEJB.setOrigenId(documentoIf.getId());
		return biDocumentoDimEJB;
	}

	@Override
	public Object construirBlanco() {
		BiDocumentoDimEJB biDocumentoDimEJB = new BiDocumentoDimEJB();
		biDocumentoDimEJB.setDescripcion("SIN DOCUMENTO");
		biDocumentoDimEJB.setOrigenId(ID_SIN);
		return biDocumentoDimEJB;
	}

	@Override
	public Object consultar(Object origenID) {
		return EJBDataSource.getJpaManagerLocal()
				.find(DocumentoEJB.class, (Long)origenID);
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return BiDocumentoDimEJB.class;
	}

}
