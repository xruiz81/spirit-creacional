package com.spirit.inventario.gui.helper;

import com.spirit.client.SpiritAlert;
import com.spirit.compras.session.CompraSessionService;
import com.spirit.compras.session.OrdenCompraDetalleSessionService;
import com.spirit.compras.session.OrdenCompraSessionService;
import com.spirit.general.session.DocumentoSessionService;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.general.session.ModuloSessionService;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.general.session.TipoDocumentoSessionService;
import com.spirit.inventario.session.BodegaSessionService;
import com.spirit.inventario.session.ColorSessionService;
import com.spirit.inventario.session.GenericoSessionService;
import com.spirit.inventario.session.LoteSessionService;
import com.spirit.inventario.session.ModeloSessionService;
import com.spirit.inventario.session.MovimientoDetalleSessionService;
import com.spirit.inventario.session.MovimientoSessionService;
import com.spirit.inventario.session.PresentacionSessionService;
import com.spirit.inventario.session.ProductoSessionService;
import com.spirit.inventario.session.SolicitudTransferenciaDetalleSessionService;
import com.spirit.inventario.session.SolicitudTransferenciaSessionService;
import com.spirit.inventario.session.StockSessionService;
import com.spirit.medios.session.MarcaProductoSessionService;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class SessionServiceLocator {
	public static MovimientoSessionService getMovimientoSessionService() {
		try {
			return (MovimientoSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MovimientoDetalleSessionService getMovimientoDetalleSessionService() {
		try {
			return (MovimientoDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.MOVIMIENTODETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ModuloSessionService getModuloSessionService() {
		try {
			return (ModuloSessionService) ServiceLocator
					.getService(ServiceLocator.MODULOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static TipoDocumentoSessionService getTipoDocumentoSessionService() {
		try {
			return (TipoDocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.TIPODOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenCompraSessionService getOrdenCompraSessionService() {
		try {
			return (OrdenCompraSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENCOMPRASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OrdenCompraDetalleSessionService getOrdenCompraDetalleSessionService() {
		try {
			return (OrdenCompraDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.ORDENCOMPRADETALLESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static CompraSessionService getCompraSessionService() {
		try {
			return (CompraSessionService) ServiceLocator
					.getService(ServiceLocator.COMPRASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static BodegaSessionService getBodegaSessionService() {
		try {
			return (BodegaSessionService) ServiceLocator
					.getService(ServiceLocator.BODEGASESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static GenericoSessionService getGenericoSessionService() {
		try {
			return (GenericoSessionService) ServiceLocator
					.getService(ServiceLocator.GENERICOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static DocumentoSessionService getDocumentoSessionService() {
		try {
			return (DocumentoSessionService) ServiceLocator
					.getService(ServiceLocator.DOCUMENTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ProductoSessionService getProductoSessionService() {
		try {
			return (ProductoSessionService) ServiceLocator
					.getService(ServiceLocator.PRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static LoteSessionService getLoteSessionService() {
		try {
			return (LoteSessionService) ServiceLocator
					.getService(ServiceLocator.LOTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudTransferenciaSessionService getSolicitudTransferenciaSessionService() {
		try {
			return (SolicitudTransferenciaSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUD_TRANSFERENCIA_SESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static SolicitudTransferenciaDetalleSessionService getSolicitudTransferenciaDetalleSessionService() {
		try {
			return (SolicitudTransferenciaDetalleSessionService) ServiceLocator
					.getService(ServiceLocator.SOLICITUD_TRANSFERENCIA_DETALLE_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static OficinaSessionService getOficinaSessionService() {
		try {
			return (OficinaSessionService) ServiceLocator
					.getService(ServiceLocator.OFICINASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static EmpresaSessionService getEmpresaSessionService() {
		try {
			return (EmpresaSessionService) ServiceLocator
					.getService(ServiceLocator.EMPRESASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MenuSessionService getMenuSessionService() {
		try {
			return (MenuSessionService) ServiceLocator
					.getService(ServiceLocator.MENUSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static StockSessionService getStockSessionService() {
		try {
			return (StockSessionService) ServiceLocator
					.getService(ServiceLocator.STOCKSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static MarcaProductoSessionService getMarcaProductoSessionService() {
		try {
			return (MarcaProductoSessionService) ServiceLocator
					.getService(ServiceLocator.MARCAPRODUCTOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static ColorSessionService getColorSessionService() {
		try {
			return (ColorSessionService) ServiceLocator
					.getService(ServiceLocator.COLORSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static ModeloSessionService getModeloSessionService() {
		try {
			return (ModeloSessionService) ServiceLocator
					.getService(ServiceLocator.MODELOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static PresentacionSessionService getPresentacionSessionService() {
		try {
			return (PresentacionSessionService) ServiceLocator
					.getService(ServiceLocator.PRESENTACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert(
					"Se ha producido un error de comunicación con el servidor",
					SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	
	

}
