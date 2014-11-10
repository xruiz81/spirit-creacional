package com.spirit.inventario.gui.tblmodel;

import java.math.BigDecimal;
import java.util.List;

import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.MovimientoDetalleData;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;
import com.spirit.servicelocator.ServiceLocator;

public class MovimientoDetalleRowModel {
	private DocumentoIf documentoMovimiento;
	private DocumentoIf documentoOrdenCompra;
	private ProductoIf producto;
	private LoteIf loteIf;
	private BigDecimal cantidad;
	private Long idMovimiento;
	private Long id;

	public MovimientoDetalleRowModel() {
	}

	public MovimientoDetalleRowModel(SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf) {
		try {
			addSolicitudTransferenciaDetalle(solicitudTransferenciaDetalleIf);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public MovimientoDetalleRowModel(OrdenCompraDetalleIf ordenCompraDetalleIf) {
		try {
			addOrdenCompraDetalleIf(ordenCompraDetalleIf);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MovimientoDetalleRowModel(MovimientoDetalleIf movimientoDetalleIf) {
		try {
			addMovimientoDetalleIf(movimientoDetalleIf);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private DocumentoIf getDocSave(Long id) throws GenericBusinessException {
		if (id != null) {
			return SessionServiceLocator.getDocumentoSessionService()
					.getDocumento(id);
		} else
			return null;
	}

	
	public void addSolicitudTransferenciaDetalle(
			SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf)
			throws GenericBusinessException {
		setCantidad(solicitudTransferenciaDetalleIf.getCantidad());
		setDocumentoMovimiento(ConsultasHelper.getDocumento("ETRF"));
		setDocumentoOrdenCompra(null);
		LoteIf lote=SessionServiceLocator.getLoteSessionService().getLote(solicitudTransferenciaDetalleIf.getLoteId());
		setLoteIf(lote);
		setProducto(SessionServiceLocator.getProductoSessionService()
				.getProducto(solicitudTransferenciaDetalleIf.getProductoId()));
		// setCosto(ordenCompraDetalleIf.get)PILAS
		//setPrecio(ordenCompraDetalleIf.getValor());
	}
	
	public void addOrdenCompraDetalleIf(
			OrdenCompraDetalleIf ordenCompraDetalleIf)
			throws GenericBusinessException {
		setCantidad(new BigDecimal(ordenCompraDetalleIf.getCantidad()));
		// setCosto(ordenCompraDetalleIf.get)PILAS
		setDocumentoMovimiento(ConsultasHelper.getDocumento("ICOC"));
		setDocumentoOrdenCompra(getDocSave(ordenCompraDetalleIf
				.getDocumentoId()));
		// setLoteIf(loteIf)PILAS
    	setProducto(SessionServiceLocator.getProductoSessionService()
				.getProducto(ordenCompraDetalleIf.getId()));

	}

	public void addMovimientoDetalleIf(MovimientoDetalleIf movimientoDetalleIf)
			throws GenericBusinessException {
		setCantidad(movimientoDetalleIf.getCantidad());
		setDocumentoMovimiento(getDocSave(movimientoDetalleIf.getDocumentoId()));
		setDocumentoOrdenCompra(null);
		setLoteIf(SessionServiceLocator.getLoteSessionService().getLote(
				movimientoDetalleIf.getLoteId()));
		setProducto(SessionServiceLocator.getProductoSessionService()
				.getProducto(getLoteIf().getProductoId()));
		setId(movimientoDetalleIf.getId());
		setIdMovimiento(movimientoDetalleIf.getMovimientoId());
	}

	public MovimientoDetalleIf getMovimientoDetalleIf() {
		MovimientoDetalleIf movimientoDetalleIf = new MovimientoDetalleData();
		if (getDocumentoMovimiento() != null)
			movimientoDetalleIf
					.setDocumentoId(getDocumentoMovimiento().getId());
		movimientoDetalleIf.setCantidad(getCantidad());
		if (getLoteIf() != null)
			movimientoDetalleIf.setLoteId(getLoteIf().getId());
		
		movimientoDetalleIf.setMovimientoId(getIdMovimiento());
		movimientoDetalleIf.setId(getId());
		return movimientoDetalleIf;
	}

	public DocumentoIf getDocumentoMovimiento() {
		return documentoMovimiento;
	}

	public void setDocumentoMovimiento(DocumentoIf documentoMovimiento) {
		this.documentoMovimiento = documentoMovimiento;
	}

	public DocumentoIf getDocumentoOrdenCompra() {
		return documentoOrdenCompra;
	}

	public void setDocumentoOrdenCompra(DocumentoIf documentoOrdenCompra) {
		this.documentoOrdenCompra = documentoOrdenCompra;
	}

	public ProductoIf getProducto() {
		return producto;
	}

	public void setProducto(ProductoIf producto) {
		this.producto = producto;
	}

	public LoteIf getLoteIf() {
		return loteIf;
	}

	public void setLoteIf(LoteIf loteIf) {
		this.loteIf = loteIf;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Long getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
