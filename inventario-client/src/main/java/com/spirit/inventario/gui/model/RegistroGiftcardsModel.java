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
import com.spirit.inventario.entity.GiftcardData;
import com.spirit.inventario.entity.GiftcardIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.gui.criteria.ProductoCriteria;
import com.spirit.inventario.gui.panel.JPRegistroGiftcards;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;

public class RegistroGiftcardsModel extends JPRegistroGiftcards {
	private List<GiftcardIf> giftcardList = new ArrayList<GiftcardIf>();
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

	public RegistroGiftcardsModel() {
		initKeyListeners();
		initListeners();
		showSaveMode();
		getTblGiftcardsActivos().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//getTblGiftcardsActivos().addMouseListener(oMouseAdapterTblGiftcardsActivos);
		//getTblGiftcardsActivos().addKeyListener(oKeyAdapterTblGiftcardsActivos);
		new HotKeyComponent(this);
	}

	/*MouseListener oMouseAdapterTblGiftcardsActivos = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};

	KeyListener oKeyAdapterTblGiftcardsActivos = new KeyAdapter() {
		public void keyReleased(KeyEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};*/
	
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
		giftcardList.clear();
		producto = null;
		getTxtProducto().setText("");
	}

	public void report() {
		// TODO Auto-generated method stub

	}

	public void showSaveMode() {
		clean();
		mapearListasPrecios();
		cargarTabla();
	}
	
	private void registrarGiftcards() {
		if (validateFields()) {
			double codigoInicial = Double.parseDouble(getTxtCodigoInicial().getText());
			double codigoFinal = Double.parseDouble(getTxtCodigoFinal().getText());
			double valor = Double.parseDouble(getTxtValor().getText());
			giftcardList = generarGiftcardList(codigoInicial, codigoFinal, valor);
		}
	}

	private void cargarTabla() {
		try {
			Iterator it = SessionServiceLocator.getGiftcardSessionService().findGiftcardByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (it.hasNext()) {
				GiftcardIf giftcard = (GiftcardIf) it.next();
				Vector<String> fila = new Vector<String>();
				fila.add(giftcard.getCodigoBarras());
				fila.add(formatoDecimal.format(giftcard.getValor().doubleValue()));
				fila.add(formatoDecimal.format(giftcard.getSaldo().doubleValue()));
				if (giftcard.getEstado().equals(ESTADO_ACTIVO))
					fila.add(NOMBRE_ESTADO_ACTIVO);
				else if (giftcard.getEstado().equals(ESTADO_INACTIVO))
					fila.add(NOMBRE_ESTADO_INACTIVO);
				//giftcardList.add(giftcard);
				tableModel = (DefaultTableModel) getTblGiftcardsActivos().getModel();
				tableModel.addRow(fila);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error cargando listado de giftcards", SpiritAlert.ERROR);
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
			registrarGiftcards();
			SessionServiceLocator.getGiftcardSessionService().registrarGiftcardList(giftcardList);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error en el registro de giftcards", SpiritAlert.ERROR);
		}
		
		showSaveMode();
	}

	private List<GiftcardIf> generarGiftcardList(double codigoInicial, double codigoFinal, double valor) {
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			parameterMap.put("codigo", "DICBGF");
			Iterator it = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(parameterMap).iterator();
			String digitoInicialCodigoBarras = "0";
			if (it.hasNext())
				digitoInicialCodigoBarras = ((ParametroEmpresaIf) it.next()).getValor();
			
			while (codigoInicial <= codigoFinal) {
				GiftcardData giftcard = new GiftcardData();
				String codigoBarras = digitoInicialCodigoBarras + formatoSerial.format(codigoInicial);
				codigoBarras = codigoBarras.concat(SessionServiceLocator.getProductoSessionService().generarDigitoVerificadorCodigoBarras(codigoBarras));
				giftcard.setCodigoBarras(codigoBarras);
				giftcard.setEstado(ESTADO_INACTIVO);
				/*it = SessionServiceLocator.getGiftcardSessionService().findProductoGiftcardByEmpresaId(Parametros.getIdEmpresa()).iterator();
				if (it.hasNext()) {
					ProductoIf producto = (ProductoIf) it.next();
					giftcard.setProductoId(producto.getId());
				}*/
				giftcard.setProductoId(producto.getId());
				giftcard.setValor(BigDecimal.valueOf(valor));
				giftcard.setSaldo(BigDecimal.valueOf(valor));
				codigoInicial++;
				giftcardList.add(giftcard);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error generando lista de giftcards", SpiritAlert.ERROR);
		}
		return giftcardList;
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
			SpiritAlert.createAlert("Debe seleccionar el producto/giftcard", SpiritAlert.WARNING);
			getBtnBuscarProducto().grabFocus();
			return false;
		}

		if (valor <= 0D) {
			SpiritAlert.createAlert("Valor del giftcard debe ser mayor a cero", SpiritAlert.WARNING);
			getTxtValor().grabFocus();
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
		// TODO Auto-generated method stub

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
