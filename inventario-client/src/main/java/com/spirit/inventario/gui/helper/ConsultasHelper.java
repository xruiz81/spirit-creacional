package com.spirit.inventario.gui.helper;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;

public class ConsultasHelper {
	private static HashMap<String, TipoDocumentoIf> mapaTipoDocumento = new HashMap<String, TipoDocumentoIf>();
	private static HashMap<String, DocumentoIf> mapaDocumento = new HashMap<String, DocumentoIf>();
	private static HashMap<Long, String> mapaProducto = new HashMap<Long, String>();

	public synchronized static String getTxtProducto(ProductoIf productoIf) {
		if (productoIf == null)
			return "-";
		String descripcion = mapaProducto.get(productoIf.getId());
		if (descripcion != null)
			return descripcion;
		try {
			descripcion = "";
			if (productoIf != null) {
				descripcion += "[" + productoIf.getCodigoBarras() + "] ";
				if (productoIf.getGenericoId() != null) {
					GenericoIf generico = SessionServiceLocator
							.getGenericoSessionService().getGenerico(
									productoIf.getGenericoId());
					descripcion += "-" + generico.getNombre();
				}
				if (productoIf.getModeloId() != null) {
					ModeloIf modelo = SessionServiceLocator
							.getModeloSessionService().getModelo(
									productoIf.getModeloId());
					descripcion += "-" + modelo.getNombre();
				}
				if (productoIf.getColorId() != null) {
					ColorIf color = SessionServiceLocator
							.getColorSessionService().getColor(
									productoIf.getColorId());
					descripcion += "-" + color.getNombre();
				}
				if (productoIf.getPresentacionId() != null) {
					PresentacionIf presentacionIf = SessionServiceLocator
							.getPresentacionSessionService().getPresentacion(
									productoIf.getPresentacionId());
					descripcion += "-" + presentacionIf.getNombre();
				}
				mapaProducto.put(productoIf.getId(), descripcion);
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return descripcion;
	}

	public synchronized static TipoDocumentoIf getTipoDocumento(String codigo) {
		TipoDocumentoIf tipoDocumentoIf = mapaTipoDocumento.get(codigo);
		if (tipoDocumentoIf != null)
			return tipoDocumentoIf;
		try {
			List c = (List) SessionServiceLocator
					.getTipoDocumentoSessionService()
					.findTipoDocumentoByCodigo(codigo);
			if (c != null && c.size() >= 0) {
				tipoDocumentoIf = (TipoDocumentoIf) c.get(0);
				mapaTipoDocumento.put(codigo, tipoDocumentoIf);
				return tipoDocumentoIf;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public synchronized static DocumentoIf getDocumento(String codigo) {
		DocumentoIf documentoIf = mapaDocumento.get(codigo);
		if (documentoIf != null)
			return documentoIf;
		try {
			List c = (List) SessionServiceLocator.getDocumentoSessionService()
					.findDocumentoByCodigo(codigo);
			if (c != null && c.size() >= 0) {
				documentoIf = (DocumentoIf) c.get(0);
				mapaDocumento.put(codigo, documentoIf);
				return documentoIf;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
