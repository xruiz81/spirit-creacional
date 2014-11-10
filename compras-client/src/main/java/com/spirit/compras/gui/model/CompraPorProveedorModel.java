package com.spirit.compras.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.gui.panel.JPCompraPorProveedor;
import com.spirit.compras.session.CompraSessionService;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.model.EmpresaModel;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Utilitarios;

public class CompraPorProveedorModel extends JPCompraPorProveedor {

	private static final long serialVersionUID = 7221018075236668654L;
	
	private final static String NOMBRE_MENU_COMPRA_POR_PROVEEDOR = "COMPRAS POR PROVEEDOR";
	private static Date fecha;
	private ClienteOficinaIf proveedor;
	private JDPopupFinderModel popupFinder;
	private ClienteOficinaCriteria proveedorCriteria;
	private final static String NOMBRE_ESTADO_TODAS = "TODAS";

	public CompraPorProveedorModel () {
		initListeners();
		showSaveMode();
		clean();
	}
	
	public void clean() {
		getTxtProveedor().setText("");
		getCmbFecha().setLocale(Utilitarios.esLocale);
		getCmbFecha().setSelectedItem(null);
		getCmbEstado().setSelectedItem(null);
	}
	
	private void initListeners(){		
		getCmbFecha().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				if (getCmbFecha().getSelectedItem() != null)
					fecha = (Date) ((DateComboBox) evento.getSource()).getDate();
				else
					fecha = null;
			}
		});
		
		getBtnBuscarProveedor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProveedor();
			}
		});
	}
	
	private void buscarProveedor() {
		Long idCorporacion = 0L;
		Long idCliente = 0L;
		String tipoCliente = "PR";
		String tituloVentanaBusqueda = "Proveedores";
		Vector<Integer> anchoColumnas = new Vector<Integer>();
		anchoColumnas.addElement(10);
		anchoColumnas.addElement(350);
				
		proveedorCriteria = new ClienteOficinaCriteria(tituloVentanaBusqueda, idCorporacion, idCliente, tipoCliente, "", false);
		popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), proveedorCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
		if (popupFinder.getElementoSeleccionado() != null)
			setProveedor();
	}
	
	private void setProveedor() {
		proveedor = (ClienteOficinaIf) popupFinder.getElementoSeleccionado();
		try {
			getTxtProveedor().setText(proveedor.getCodigo() + " - " + ((ClienteIf) SessionServiceLocator.getClienteSessionService().findClienteByClienteOficinaId(proveedor.getId()).iterator().next()).getNombreLegal());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showSaveMode() {
		getTxtProveedor().setEditable(false);
		getTxtProveedor().setEnabled(false);
		getBtnBuscarProveedor().setEnabled(true);
		getCmbFecha().setEnabled(true);
		getCmbEstado().setEnabled(true);
	}

	public void report() {
		try {
			Collection modelReporte = null;
			Map parameterMap = new HashMap();
			if (proveedor != null)
				parameterMap.put("proveedorId", proveedor.getId());
			if (getCmbFecha().getSelectedItem() != null)
				parameterMap.put("fecha", new java.sql.Date(getCmbFecha().getDate().getTime()));
			if (getCmbEstado().getSelectedItem() != null && !getCmbEstado().getSelectedItem().toString().equals(NOMBRE_ESTADO_TODAS))
				parameterMap.put("estado", getCmbEstado().getSelectedItem().toString().substring(0,1));
			
			//TODO: Estos reportes son por empresa o por oficina
			if (!parameterMap.isEmpty())
				modelReporte = SessionServiceLocator.getCompraSessionService().findCompraByQueryAndEmpresaId(parameterMap, Parametros.getIdEmpresa());
			else
				modelReporte = SessionServiceLocator.getCompraSessionService().findCompraByEmpresaId(Parametros.getIdEmpresa());
			 
			if (modelReporte.size() > 0) {
				String si = "Si"; 
    	        String no = "No"; 
    	        Object[] options ={si,no}; 
    			int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte de Compras por Proveedor para imprimirlo?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, no);
				if(opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/compras/RPComprasPorProveedor.jasper";
					Collection dataSourceCollection = initializeBeanCollection(modelReporte);
					JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(dataSourceCollection);
					HashMap parametrosMap = new HashMap();
					//TODO: ¿De dónde saco el nombre del proveedor para el subreporte?
					if ( Parametros.getRutaCarpetaReportes()!= null && !"".equals(Parametros.getRutaCarpetaReportes()) )
						parametrosMap.put("pathsubreport", Parametros.getRutaCarpetaReportes()+"/"+"jaspers/compras/RPComprasPorProveedorDetalle.jasper");
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");
										
					parametrosMap.put("dataSourceDetail", dataSourceDetail);
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre(NOMBRE_MENU_COMPRA_POR_PROVEEDOR).iterator().next();
					parametrosMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf empresa = (EmpresaIf) Parametros.getEmpresa();
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("usuario", Parametros.getUsuario());
					parametrosMap.put("emitido", Utilitarios.dateHoraHoy());
					if (fecha != null)
						parametrosMap.put("fecha", Utilitarios.getStringDateFromDate(fecha));
					else
						parametrosMap.put("fecha", "");
					//parametrosMap.put("nombreProveedor", proveedor.getCodigo() + " - " + ((ClienteIf) getClienteSessionService().findClienteByClienteOficinaId(proveedor.getId()).iterator().next()).getNombreLegal());
			
					ReportModelImpl.processReport(fileName, parametrosMap, new JREmptyDataSource(), true);
				}
			} else {
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		catch (ParseException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private Collection initializeBeanCollection(Collection rowCollection) {
		ArrayList reportRows = new ArrayList();
		Iterator itRows = rowCollection.iterator();

		while (itRows.hasNext()) {
			CompraIf bean = (CompraIf) itRows.next();
			reportRows.add(bean);
		}

		return reportRows;
	}
	 
}
