package com.spirit.bi.cubes.facturacion;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.ejb.EntityManagerImpl;
import org.hibernate.ejb.HibernatePersistence;

import com.spirit.bi.EJBDataSource;
import com.spirit.bi.FactTable;
import com.spirit.bi.cubes.facturacion.dim.ClienteDim;
import com.spirit.bi.cubes.facturacion.dim.DocumentoDim;
import com.spirit.bi.cubes.facturacion.dim.OficinaDim;
import com.spirit.bi.cubes.facturacion.dim.ProductoDim;
import com.spirit.bi.cubes.facturacion.dim.TimeDim;
import com.spirit.bi.cubes.facturacion.dim.VendedorDim;
import com.spirit.bi.entity.BiClienteDimEJB;
import com.spirit.bi.entity.BiDocumentoDimEJB;
import com.spirit.bi.entity.BiFacturacionFactEJB;
import com.spirit.bi.entity.BiOficinaDimEJB;
import com.spirit.bi.entity.BiProductoDimEJB;
import com.spirit.bi.entity.BiTimeDimEJB;
import com.spirit.bi.entity.BiVendedorDimEJB;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.FacturaDetalleEJB;
import com.spirit.facturacion.entity.FacturaEJB;

public class FacturacionFact extends FactTable {
	private VendedorDim dimensionVendedor;
	private DocumentoDim documentoDim;
	private TimeDim timeDim;
	private ClienteDim clienteDim;
	private OficinaDim oficinaDim;
	private ProductoDim productoDim;

	@Override
	public Class getDataClass() {
		return BiFacturacionFactEJB.class;
	}

	public BiFacturacionFactEJB procesar(FacturaEJB facturaEJB,
			FacturaDetalleEJB facturaDetalleEJB) {
		BiFacturacionFactEJB biFacturacionFactEJB = new BiFacturacionFactEJB();
		BiVendedorDimEJB biVendedorDimEJB = (BiVendedorDimEJB) dimensionVendedor
				.getObjetoDimension(facturaEJB.getVendedorId());
		BiDocumentoDimEJB biDocumentoDimEJB = (BiDocumentoDimEJB) documentoDim
				.getObjetoDimension(facturaDetalleEJB.getDocumentoId());

		Date fechaFactura = new Date(facturaEJB.getFechaFactura().getTime());
		Long time = Long.parseLong(TimeDim.dateFormat.format(fechaFactura));

		BiTimeDimEJB biTimeDimEJB = (BiTimeDimEJB) timeDim
				.getObjetoDimension(time);

		BiClienteDimEJB biClienteDimEJB = (BiClienteDimEJB) clienteDim
				.getObjetoDimension(facturaEJB.getClienteoficinaId());

		BiOficinaDimEJB biOficinaDimEJB = (BiOficinaDimEJB) oficinaDim
				.getObjetoDimension(facturaEJB.getOficinaId());

		BiProductoDimEJB biProductoDimEJB = (BiProductoDimEJB) productoDim
				.getObjetoDimension(facturaDetalleEJB.getProductoId());

		biFacturacionFactEJB.setBiVendedorDimId(biVendedorDimEJB.getId());
		biFacturacionFactEJB.setBiDocumentoDimId(biDocumentoDimEJB.getId());
		biFacturacionFactEJB.setBiTimeDimId(biTimeDimEJB.getId());
		biFacturacionFactEJB.setBiClienteDimId(biClienteDimEJB.getId());
		biFacturacionFactEJB.setBiOficinaDimId(biOficinaDimEJB.getId());
		biFacturacionFactEJB.setBiProductoDimId(biProductoDimEJB.getId());

		BigDecimal descuento = facturaDetalleEJB.getDescuento();
		BigDecimal descuentoGlobal = facturaEJB.getDescuentoGlobal();
		BigDecimal subTotal = facturaDetalleEJB.getValor();

		BigDecimal valor = subTotal.subtract(descuentoGlobal.add(descuento));
		BigDecimal porcentajeComision = facturaEJB
				.getPorcentajeComisionAgencia() == null ? BigDecimal.ZERO
				: facturaEJB.getPorcentajeComisionAgencia();
		BigDecimal comision = valor.multiply(porcentajeComision).divide(new BigDecimal(100));

		biFacturacionFactEJB.setValor(valor.add(comision).add(
				facturaDetalleEJB.getIva()));

		biFacturacionFactEJB.setCantidad(facturaDetalleEJB.getCantidad());

		biFacturacionFactEJB.setOrigenId(facturaDetalleEJB.getId());
		return biFacturacionFactEJB;
	}

	@Override
	public void fill() throws Exception {
		List<FacturaEJB> facturaList = EJBDataSource.getJpaManagerLocal()
				.singleClassList(FacturaEJB.class);
		EntityManager manager = EJBDataSource.getJpaManagerLocal().getManager();
		for (FacturaEJB facturaEJB : facturaList) {

			List<FacturaDetalleEJB> listaDetalles = (List<FacturaDetalleEJB>) EJBDataSource
					.getFacturaDetalleSessionLocal()
					.findFacturaDetalleByFacturaId(facturaEJB.getId());

			for (FacturaDetalleEJB facturaDetalleEJB : listaDetalles) {
				BiFacturacionFactEJB biFacturacionFactEJB = procesar(
						facturaEJB, facturaDetalleEJB);
				manager.persist(biFacturacionFactEJB);
			}
		}
		manager.flush();
	}

	@Override
	public void update() throws Exception {
		List<Long> listaOrigenId = EJBDataSource.getJpaManagerLocal()
				.executeQueryList("SELECT origenId FROM BiFacturacionFactEJB",
						null);

		List<Long> listaId = EJBDataSource
				.getJpaManagerLocal()
				.executeQueryList(
						"SELECT FD.id FROM FacturaDetalleEJB FD,FacturaEJB F WHERE F.id=FD.facturaId AND F.estado <> 'A'",
						null);

		listaId.removeAll(listaOrigenId);
		EntityManager manager = EJBDataSource.getJpaManagerLocal().getManager();

		for (Long id : listaId) {
			FacturaDetalleEJB facturaDetalleEJB = (FacturaDetalleEJB) EJBDataSource
					.getFacturaDetalleSessionLocal().getFacturaDetalle(id);
			FacturaEJB facturaEJB = (FacturaEJB) EJBDataSource
					.getJpaManagerLocal().find(FacturaEJB.class,
							facturaDetalleEJB.getFacturaId());
			BiFacturacionFactEJB biFacturacionFactEJB = procesar(facturaEJB,
					facturaDetalleEJB);
			manager.persist(biFacturacionFactEJB);
		}
		manager.flush();
	}

	@Override
	protected void initDimentions() {
		dimensionVendedor = new VendedorDim();
		documentoDim = new DocumentoDim();
		timeDim = new TimeDim();
		clienteDim = new ClienteDim();
		oficinaDim = new OficinaDim();
		productoDim = new ProductoDim();

		addDimension(dimensionVendedor);
		addDimension(documentoDim);
		addDimension(timeDim);
		addDimension(clienteDim);
		addDimension(oficinaDim);
		addDimension(productoDim);
	}

}
