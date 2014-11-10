package com.spirit.bi.cubes.facturacion.dim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spirit.bi.Dimension;
import com.spirit.bi.EJBDataSource;
import com.spirit.bi.cubes.facturacion.levels.ColorLevel;
import com.spirit.bi.cubes.facturacion.levels.GenericoLevel;
import com.spirit.bi.cubes.facturacion.levels.MarcaLevel;
import com.spirit.bi.cubes.facturacion.levels.ModeloLevel;
import com.spirit.bi.cubes.facturacion.levels.PresentacionLevel;
import com.spirit.bi.cubes.facturacion.levels.ProveedorLevel;
import com.spirit.bi.cubes.facturacion.levels.TipoProductoLevel;
import com.spirit.bi.entity.BiProductoDimEJB;
import com.spirit.bi.entity.BiVendedorDimEJB;
import com.spirit.comun.querys.QueryHelperServer;
import com.spirit.comun.querys.QueryHelperServerLocal;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.general.entity.EmpleadoEJB;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoEJB;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.TipoProductoIf;
import com.spirit.medios.entity.MarcaProductoIf;

public class ProductoDim extends Dimension {

	private PresentacionLevel presentacionHelper=new PresentacionLevel();
	private ColorLevel colorHelper=new ColorLevel();
	private GenericoLevel genericoHelper=new GenericoLevel();
	private ModeloLevel modeloHelper=new ModeloLevel();
	private MarcaLevel marcaHelper=new MarcaLevel();
	private ProveedorLevel proveedorHelper=new ProveedorLevel();
	private TipoProductoLevel tipoProductoLevel=new TipoProductoLevel();
	
	
	
	@Override
	public Object construir(Object objetoConsulta) {
		Map mapaConsulta=(Map)objetoConsulta;
		ProductoIf productoIf=(ProductoIf)mapaConsulta.get("producto");
		PresentacionIf presentacionIf=(PresentacionIf)mapaConsulta.get("presentacion");
		ColorIf colorIf=(ColorIf)mapaConsulta.get("color");
		GenericoIf genericoIf=(GenericoIf)mapaConsulta.get("generico");
		ModeloIf modeloIf=(ModeloIf)mapaConsulta.get("modelo");
		MarcaProductoIf marcaProductoIf=(MarcaProductoIf)mapaConsulta.get("marca");
		ClienteIf clienteIf=(ClienteIf)mapaConsulta.get("proveedor");
		TipoProductoIf tipoProducto=(TipoProductoIf)mapaConsulta.get("tipoProducto");
		
		BiProductoDimEJB biProductoDimEJB=new BiProductoDimEJB();
		String nombreProducto=EJBDataSource.getQueryHelperServerLocal().getDescripcionProducto(productoIf.getId());
		biProductoDimEJB.setDescripcion(nombreProducto);
		biProductoDimEJB.setOrigenId(productoIf.getId());
		
		biProductoDimEJB.setColor(colorIf.getNombre());
		biProductoDimEJB.setColorId(colorIf.getId());
		
		biProductoDimEJB.setGenerico(genericoIf.getNombre());
		biProductoDimEJB.setGenericoId(genericoIf.getId());
		
		biProductoDimEJB.setMarca(marcaProductoIf.getNombre());
		biProductoDimEJB.setMarcaId(marcaProductoIf.getId());
		
		biProductoDimEJB.setModelo(modeloIf.getNombre());
		biProductoDimEJB.setModeloId(modeloIf.getId());
		
		biProductoDimEJB.setPresentacion(presentacionIf.getNombre());
		biProductoDimEJB.setPresentacionId(presentacionIf.getId());
		
		biProductoDimEJB.setProveedor(clienteIf.getRazonSocial());
		biProductoDimEJB.setProveedorId(productoIf.getProveedorId());
		
		biProductoDimEJB.setTipoProductoId(tipoProducto.getId());
		biProductoDimEJB.setTipoProducto(tipoProducto.getNombre());
		//biProductoDimEJB.setServicio(servicio);
				
		return biProductoDimEJB;
	}

	@Override
	public Object consultar(Object origenID) {
		ProductoEJB productoIf=(ProductoEJB) EJBDataSource.getJpaManagerLocal().find(ProductoEJB.class, (Long)origenID);
		HashMap<String, Object> mapaConsulta=new HashMap<String, Object>();
		mapaConsulta.put("producto", productoIf);
		mapaConsulta.put("presentacion", presentacionHelper.getObjetoDimension(productoIf.getPresentacionId()));
		mapaConsulta.put("color",colorHelper.getObjetoDimension(productoIf.getColorId()));
		GenericoIf genericoIf=(GenericoIf)genericoHelper.getObjetoDimension(productoIf.getGenericoId());
		mapaConsulta.put("generico",genericoIf);
		mapaConsulta.put("modelo",modeloHelper.getObjetoDimension(productoIf.getModeloId()));
		mapaConsulta.put("marca",marcaHelper.getObjetoDimension(productoIf.getMarcaId()));
		mapaConsulta.put("proveedor",proveedorHelper.getObjetoDimension(productoIf.getProveedorId()));
		mapaConsulta.put("tipoProducto",tipoProductoLevel.getObjetoDimension(genericoIf.getTipoproductoId()));
		return mapaConsulta;
		
	}
	
	@Override
	public Object construirBlanco() {
		BiProductoDimEJB biProductoDimEJB=new BiProductoDimEJB();
		biProductoDimEJB.setDescripcion("SIN PRODUCTO");
		biProductoDimEJB.setOrigenId(ID_SIN);
		return biProductoDimEJB;
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return BiProductoDimEJB.class;
	}

}
