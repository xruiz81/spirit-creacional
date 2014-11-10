package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.entity.ListaPrecioIf;
import com.spirit.facturacion.entity.PrecioIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.panel.JPRegistroTarjetaAfiliacion;
import com.spirit.pos.entity.TarjetaData;
import com.spirit.pos.entity.TarjetaIf;
import com.spirit.pos.entity.TarjetaTipoIf;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;

public class RegistroTarjetaAfiliacionModel extends JPRegistroTarjetaAfiliacion {
	private List<TarjetaIf> loyaltyCardList = new ArrayList<TarjetaIf>();
	private ProductoIf producto;
	private DefaultTableModel tableModel;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
	private static final int MAX_LONGITUD_VALOR = 22;
	private static final int MAX_LONGITUD_CODIGO = 13;
	private DecimalFormat formatoSerial = new DecimalFormat("00000000000");
	private JDPopupFinderModel popupFinder;
	private ProductoCriteria productoCriteria;
	private Map listasPreciosMap = new HashMap();
	private Map tiposTarjetaMap = new HashMap();

	public RegistroTarjetaAfiliacionModel() {
		initKeyListeners();
		initListeners();
		showSaveMode();
		getTblTarjetasAfiliacionActivas().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnBuscarProducto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProducto();
			}
		});
	}
	
	private void buscarProducto() {
		try {
			String mmpg = "MG";
			productoCriteria = new ProductoCriteria("Producto", 0L, "","", "", false, mmpg);
			popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),productoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
			if (popupFinder.getElementoSeleccionado() != null) {
				producto = (ProductoIf) popupFinder.getElementoSeleccionado();
				GenericoIf generico = SessionServiceLocator.getGenericoSessionService().getGenerico(producto.getGenericoId());
				getTxtProducto().setText(generico.getNombre());
				Map aMap = new HashMap();
				aMap.put("listaprecioId", findListaPrecioByCodigo("OFI").getId());
				aMap.put("productoId", producto.getId());
				aMap.put("estado", "A");
				Iterator it = SessionServiceLocator.getPrecioSessionService().findPrecioByQuery(aMap).iterator();
				if (it.hasNext()) {
					PrecioIf precio = (PrecioIf) it.next();
					getTxtValor().setText(formatoDecimal.format(precio.getPrecio().doubleValue()));
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error buscando producto", SpiritAlert.WARNING);
		}
	}
		
	private ListaPrecioIf findListaPrecioByCodigo(String codigo) {
		ListaPrecioIf listaPrecio = (ListaPrecioIf) listasPreciosMap.get(codigo);
		return listaPrecio;
	}
	
	private void mapearListasPrecios() {
		try {
			Iterator it = SessionServiceLocator.getListaPrecioSessionService().findListaPrecioByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				ListaPrecioIf listaPrecio = (ListaPrecioIf) it.next();
				listasPreciosMap.put(listaPrecio.getCodigo(), listaPrecio);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear teclas de configuración", SpiritAlert.ERROR);
		}
	}
	
	private void mapearTiposTarjeta() {
		try {
			List l = (List) SessionServiceLocator.getTarjetaTipoSessionService().findTarjetaTipoByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbTipoTarjeta(), l);
			Iterator it = l.iterator();
			while (it.hasNext()) {
				TarjetaTipoIf tipoTarjeta = (TarjetaTipoIf) it.next();
				tiposTarjetaMap.put(tipoTarjeta.getId(), tipoTarjeta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al mapear teclas de configuración", SpiritAlert.ERROR);
		}
	}
	
	private void initKeyListeners() {
		getTxtCodigoInicial().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtCodigoFinal().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtValor().addKeyListener(new TextChecker(MAX_LONGITUD_VALOR));
		getTxtValor().addKeyListener(new NumberTextFieldDecimal());
	}

	public void clean() {
		getTxtCodigoInicial().setText("");
		getTxtCodigoFinal().setText("");
		getTxtValor().setText("");
		loyaltyCardList.clear();
		producto = null;
		getTxtProducto().setText("");
	}

	public void report() {
		// TODO Auto-generated method stub

	}

	public void showSaveMode() {
		clean();
		mapearListasPrecios();
		mapearTiposTarjeta();
		cargarTabla();
	}
	
	private void registrarTarjetasAfiliacion() {
		if (validateFields()) {
			double codigoInicial = Double.parseDouble(getTxtCodigoInicial().getText());
			double codigoFinal = Double.parseDouble(getTxtCodigoFinal().getText());
			double valor = Double.parseDouble(getTxtValor().getText());
			loyaltyCardList = generarTarjetaAfiliacionList(codigoInicial, codigoFinal, valor);
		}
	}

	private void cargarTabla() {
		try {
			Iterator it = SessionServiceLocator.getTarjetaSessionService().findTarjetaByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				TarjetaIf tarjeta = (TarjetaIf) it.next();
				Vector<String> fila = new Vector<String>();
				fila.add(tarjeta.getCodigo());
				fila.add(((TarjetaTipoIf) tiposTarjetaMap.get(tarjeta.getTipoId())).getNombre());
				Map parameterMap = new HashMap();
				parameterMap.put("codigo", "APD");
				parameterMap.put("empresaId", Parametros.getIdEmpresa());
				Iterator parametroIt = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
				String apd = (parametroIt.hasNext())?((ParametroEmpresaIf)parametroIt.next()).getValor():"";
				fila.add(formatoDecimal.format(apd.equals("D")?tarjeta.getDineroAcumulado().doubleValue():tarjeta.getPuntosAcumulados().doubleValue()));
				if (tarjeta.getEstado().equals(ESTADO_ACTIVO))
					fila.add(NOMBRE_ESTADO_ACTIVO);
				else if (tarjeta.getEstado().equals(ESTADO_INACTIVO))
					fila.add(NOMBRE_ESTADO_INACTIVO);
				tableModel = (DefaultTableModel) getTblTarjetasAfiliacionActivas().getModel();
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error cargando listado de tarjetas de afiliación", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		// TODO Auto-generated method stub

	}

	public void duplicate() {
		// TODO Auto-generated method stub

	}

	public void find() {
		// TODO Auto-generated method stub

	}

	public void save() {
		try {
			registrarTarjetasAfiliacion();
			SessionServiceLocator.getTarjetaSessionService().registrarTarjetaList(loyaltyCardList);
			SpiritAlert.createAlert("Tarjetas de afiliación registradas con éxito", SpiritAlert.INFORMATION);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error en el registro de tarjetas de afiliación", SpiritAlert.ERROR);
		}
		
		showSaveMode();
	}

	private List<TarjetaIf> generarTarjetaAfiliacionList(double codigoInicial, double codigoFinal, double valor) {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", "DICBLC");
			Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
			String digitoInicialCodigoBarras = "0";
			if (it.hasNext())
				digitoInicialCodigoBarras = ((ParametroEmpresaIf) it.next()).getValor();
			
			while (codigoInicial <= codigoFinal) {
				TarjetaData tarjeta = new TarjetaData();
				String codigoBarras = digitoInicialCodigoBarras + formatoSerial.format(codigoInicial);
				codigoBarras = codigoBarras.concat(SessionServiceLocator.getProductoSessionService().generarDigitoVerificadorCodigoBarras(codigoBarras));
				tarjeta.setCodigo(codigoBarras);
				tarjeta.setEstado(ESTADO_INACTIVO);
				tarjeta.setProductoId(producto.getId());
				tarjeta.setPuntosAcumulados(BigDecimal.ZERO);
				tarjeta.setPuntosAcumuladosStatus(BigDecimal.ZERO);
				tarjeta.setPuntosComprometidos(BigDecimal.ZERO);
				tarjeta.setPuntosUtilizados(BigDecimal.ZERO);
				tarjeta.setTipoId(((TarjetaTipoIf) getCmbTipoTarjeta().getSelectedItem()).getId());
				tarjeta.setDineroAcumulado(BigDecimal.ZERO);
				tarjeta.setDineroAcumuladoStatus(BigDecimal.ZERO);
				tarjeta.setDineroComprometido(BigDecimal.ZERO);
				tarjeta.setDineroUtilizado(BigDecimal.ZERO);
				tarjeta.setEmpresaId(Parametros.getIdEmpresa());
				codigoInicial++;
				loyaltyCardList.add(tarjeta);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error generando lista de tarjetas de afiliación", SpiritAlert.ERROR);
		}
		return loyaltyCardList;
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	public boolean validateFields() {
		String codigoInicial = getTxtCodigoInicial().getText();
		String codigoFinal = getTxtCodigoFinal().getText();
		double valor = Double.parseDouble(getTxtValor().getText());

		if ((codigoInicial.equals("") || codigoInicial == null) && (codigoFinal.equals("") || codigoFinal == null)) {
			SpiritAlert.createAlert("Debe ingresar el código inicial", SpiritAlert.WARNING);
			getTxtCodigoInicial().grabFocus();
			return false;
		}

		if (!codigoFinal.equals("") && codigoFinal != null && Double.parseDouble(codigoInicial) > Double.parseDouble(codigoFinal)) {
			SpiritAlert.createAlert("Código inicial debe ser menor a código final", SpiritAlert.WARNING);
			getTxtCodigoFinal().grabFocus();
			return false;
		}
		
		if (producto == null) {
			SpiritAlert.createAlert("Debe seleccionar el producto/tarjeta de afiliación", SpiritAlert.WARNING);
			getBtnBuscarProducto().grabFocus();
			return false;
		}

		return true;
	}

	public void addDetail() {
		// TODO Auto-generated method stub

	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		mapearTiposTarjeta();
	}

	public void showFindMode() {
		// TODO Auto-generated method stub

	}

	public void showUpdateMode() {
		// TODO Auto-generated method stub

	}

	public void updateDetail() {
		// TODO Auto-generated method stub

	}
	
	public void deleteDetail() {
		
	}
}
