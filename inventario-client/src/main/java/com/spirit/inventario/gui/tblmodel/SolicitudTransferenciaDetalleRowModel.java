package com.spirit.inventario.gui.tblmodel;

import java.math.BigDecimal;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.LoteIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleData;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class SolicitudTransferenciaDetalleRowModel {
	private ProductoIf producto;
	private LoteIf loteIf;
	private BigDecimal cantidad;
	private Long idSolicitudTransferencia;
	private Long id;

	public SolicitudTransferenciaDetalleRowModel() {
	}

	public SolicitudTransferenciaDetalleRowModel(SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf) {
		try {
			addSolicitudTransferenciaDetalleIf(solicitudTransferenciaDetalleIf);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addSolicitudTransferenciaDetalleIf(SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf)
			throws GenericBusinessException {
		setCantidad(solicitudTransferenciaDetalleIf.getCantidad());
		setLoteIf(SessionServiceLocator.getLoteSessionService().getLote(
				solicitudTransferenciaDetalleIf.getLoteId()));
		setProducto(SessionServiceLocator.getProductoSessionService()
				.getProducto(getLoteIf().getProductoId()));
		setId(solicitudTransferenciaDetalleIf.getId());
		setIdSolicitudTransferencia(solicitudTransferenciaDetalleIf.getSolicitudTransferenciaId());
	}

	public SolicitudTransferenciaDetalleIf getSolicitudTransferenciaDetalleIf() {
		SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf = new SolicitudTransferenciaDetalleData();
		solicitudTransferenciaDetalleIf.setCantidad(getCantidad());
		if (getLoteIf() != null)
			solicitudTransferenciaDetalleIf.setLoteId(getLoteIf().getId());
		solicitudTransferenciaDetalleIf.setProductoId(getProducto().getId());
		solicitudTransferenciaDetalleIf.setSolicitudTransferenciaId(getIdSolicitudTransferencia());
		solicitudTransferenciaDetalleIf.setId(getId());
		return solicitudTransferenciaDetalleIf;
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

	public Long getIdSolicitudTransferencia() {
		return idSolicitudTransferencia;
	}

	public void setIdSolicitudTransferencia(Long id) {
		this.idSolicitudTransferencia = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
