package com.spirit.comun.querys;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.session.BodegaSessionLocal;
import com.spirit.inventario.session.ColorSessionLocal;
import com.spirit.inventario.session.ColorSessionRemote;
import com.spirit.inventario.session.GenericoSessionLocal;
import com.spirit.inventario.session.ModeloSessionLocal;
import com.spirit.inventario.session.ModeloSessionRemote;
import com.spirit.inventario.session.PresentacionSessionLocal;
import com.spirit.inventario.session.PresentacionSessionRemote;
import com.spirit.inventario.session.ProductoSessionLocal;
import com.spirit.medios.entity.MarcaProductoData;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.session.MarcaProductoSessionLocal;

@Stateless
public class QueryHelperServer implements QueryHelperServerLocal {

	@EJB
	private TipoDocumentoSessionLocal tipoDocumentoLocal;

	@EJB
	private DocumentoSessionLocal documentoSessionLocal;

	@EJB
	private ProductoSessionLocal productoSessionLocal;

	@EJB
	private MarcaProductoSessionLocal marcaProductoSessionLocal;

	@EJB
	private ColorSessionLocal colorSessionLocal;

	@EJB
	private GenericoSessionLocal genericoSessionLocal;

	@EJB
	private ModeloSessionLocal modeloSessionLocal;

	@EJB
	private PresentacionSessionLocal presentacionSessionLocal;

	@EJB
	private BodegaSessionLocal bodegaSessionLocal;

	private HashMap<String, TipoDocumentoIf> mapaTipoDocumento = new HashMap<String, TipoDocumentoIf>();
	private HashMap<String, DocumentoIf> mapaDocumento = new HashMap<String, DocumentoIf>();
	private HashMap<Long, String> mapaProducto = new HashMap<Long, String>();
	private HashMap<Long, String> mapaBodega = new HashMap<Long, String>();

	public TipoDocumentoIf getTipoDocumento(String codigo)
			throws GenericBusinessException {
		TipoDocumentoIf tipoDocumentoIf = mapaTipoDocumento.get(codigo);
		if (tipoDocumentoIf != null)
			return tipoDocumentoIf;

		List c = (List) tipoDocumentoLocal.findTipoDocumentoByCodigo(codigo);
		if (c != null && c.size() > 0) {
			tipoDocumentoIf = (TipoDocumentoIf) c.get(0);
			mapaTipoDocumento.put(codigo, tipoDocumentoIf);
			return tipoDocumentoIf;
		} else
			return null;
	}

	public DocumentoIf getDocumento(String codigo)
			throws GenericBusinessException {
		DocumentoIf documentoIf = mapaDocumento.get(codigo);
		if (documentoIf != null)
			return documentoIf;
		List c = (List) documentoSessionLocal.findDocumentoByCodigo(codigo);
		if (c != null && c.size() >= 0) {
			documentoIf = (DocumentoIf) c.get(0);
			mapaDocumento.put(codigo, documentoIf);
			return documentoIf;
		} else {
			return null;
		}
	}

	public String getDescipcionBodega(Long bodegaId) {
		String descripcion = mapaBodega.get(bodegaId);
		if (descripcion != null)
			return descripcion;
		try {
			BodegaIf bodega = bodegaSessionLocal.getBodega(bodegaId);
			if (bodega != null) {
				descripcion = bodega.getCodigo() + " " + bodega.getNombre();
				mapaBodega.put(bodegaId, descripcion);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descripcion;
	}

	public String getDescripcionProducto(Long productoId) {
		String descripcion = mapaProducto.get(productoId);
		if (descripcion != null)
			return descripcion;
		try {
			descripcion = "";
			ProductoIf productoIf = productoSessionLocal
					.getProducto(productoId);
			if (productoIf != null) {
				descripcion += "[" + productoIf.getCodigoBarras() + "] ";
				if (productoIf.getGenericoId() != null) {
					GenericoIf generico = genericoSessionLocal
							.getGenerico(productoIf.getGenericoId());
					descripcion += "-" + generico.getNombre();
				}
				if (productoIf.getModeloId() != null) {
					ModeloIf modelo = modeloSessionLocal.getModelo(productoIf
							.getModeloId());
					descripcion += "-" + modelo.getNombre();
				}
				if (productoIf.getColorId() != null) {
					ColorIf color = colorSessionLocal.getColor(productoIf
							.getColorId());
					descripcion += "-" + color.getNombre();
				}
				if (productoIf.getPresentacionId() != null) {
					PresentacionIf presentacionIf = presentacionSessionLocal
							.getPresentacion(productoIf.getPresentacionId());
					descripcion += "-" + presentacionIf.getNombre();
				}
				/*
				 * if (productoIf.getMarcaId() != null) { MarcaProductoIf
				 * marcaProductoIf = marcaProductoSessionLocal
				 * .getMarcaProducto(productoIf.getMarcaId());
				 * descripcion+="-"+marcaProductoIf.getNombre(); }
				 */
				mapaProducto.put(productoId, descripcion);
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return descripcion;
	}

}
