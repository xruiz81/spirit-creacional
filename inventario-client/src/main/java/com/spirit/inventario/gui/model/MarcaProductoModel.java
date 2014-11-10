package com.spirit.inventario.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.crm.session.ClienteSessionService;
import com.spirit.crm.session.CorporacionSessionService;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.inventario.gui.panel.JPMarcaProducto;
import com.spirit.medios.entity.MarcaProductoData;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.session.MarcaProductoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class MarcaProductoModel extends JPMarcaProducto {
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	private CorporacionCriteria corporacionCriteria;
	private ClienteCriteria operadorNegocioCriteria;
	private JDPopupFinderModel popupFinder;
	private Vector marcaProductoVector = new Vector();
	MarcaProductoIf marcaProducto;
	private int marcaProductoSeleccionada;
	private MarcaProductoIf marcaProductoActualizadaIf;
	private CorporacionIf corporacion;
	private ClienteIf operadorNegocio;
	private DefaultTableModel tableModel;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	private static final String TIPO_PROVEEDOR = "P";
	private static final String CODIGO_TIPO_PROVEEDOR = "PR";

	public MarcaProductoModel() {
		initKeyListeners();
		initListeners();
		setSorterTable(getTblMarcaProducto());
		anchoColumnasTabla();
		getTblMarcaProducto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblMarcaProducto().addMouseListener(oMouseAdapterTblMarca);
		getTblMarcaProducto().addKeyListener(oKeyAdapterTblMarca);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void initListeners() {
		getBtnEncerarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacion = null;
				operadorNegocio = null;
				getTxtCorporacion().setText("");
				getTxtOperadorNegocio().setText("");
			}
		});
		
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventoInicio) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), corporacionCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					corporacion = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacion.getNombre());
					operadorNegocio = null;
					getTxtOperadorNegocio().setText("");
				}
			}
		});
		
		getBtnBuscarOperadorNegocio().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eventoInicio) {
				Long idCorporacion = 0L;
				if (corporacion != null)
					idCorporacion = corporacion.getId();

				operadorNegocioCriteria = new ClienteCriteria("Operadores de Negocio", idCorporacion, CODIGO_TIPO_PROVEEDOR);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), operadorNegocioCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ) {
					operadorNegocio = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtOperadorNegocio().setText(operadorNegocio.getRazonSocial());
					if (corporacion == null) {
						try {
							corporacion = SessionServiceLocator.getCorporacionSessionService().getCorporacion(operadorNegocio.getCorporacionId());
							getTxtCorporacion().setText(corporacion.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					
					cleanTable();
					cargarTabla(operadorNegocio.getId());
				}
			}
		});
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblMarcaProducto().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblMarcaProducto().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblMarcaProducto().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(70);
	}
	
	MouseListener oMouseAdapterTblMarca = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblMarca = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setMarcaProductoSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
				marcaProductoActualizadaIf = (MarcaProductoIf)  getMarcaProductoVector().get(getMarcaProductoSeleccionada());
				getTxtCodigo().setText(getMarcaProductoActualizadaIf().getCodigo());
				if (getMarcaProductoActualizadaIf().getEstado().equals(ESTADO_ACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				
				operadorNegocio = SessionServiceLocator.getClienteSessionService().getCliente(getMarcaProductoActualizadaIf().getClienteId());
				corporacion = SessionServiceLocator.getCorporacionSessionService().getCorporacion(operadorNegocio.getCorporacionId());
				getTxtCorporacion().setText(corporacion.getNombre());
				getTxtOperadorNegocio().setText(operadorNegocio.getRazonSocial());
				getTxtNombre().setText(getMarcaProductoActualizadaIf().getNombre());
				showUpdateMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
	}

	public void save() {
		try {
			if (validateFields()) {
				MarcaProductoData data = new MarcaProductoData();
				data.setCodigo(getTxtCodigo().getText());
				java.util.Date fechaCreacion = new java.util.Date();
				data.setFechaCreacion(new java.sql.Date(fechaCreacion.getYear(), fechaCreacion.getMonth(), fechaCreacion.getDate()));
				data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				data.setClienteId(operadorNegocio.getId());
				data.setNombre(getTxtNombre().getText());
				data.setTipo(TIPO_PROVEEDOR);
				data.setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getMarcaProductoSessionService().addMarcaProducto(data);
				SpiritAlert.createAlert("Marca guardada con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("marcaProducto",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setMarcaProductoActualizadaIf((MarcaProductoIf) getMarcaProductoVector().get(getMarcaProductoSeleccionada()));
				java.util.Date fechaCreacion = new java.util.Date();
				getMarcaProductoActualizadaIf().setFechaCreacion(new java.sql.Date(fechaCreacion.getYear(), fechaCreacion.getMonth(), fechaCreacion.getDate()));
				getMarcaProductoActualizadaIf().setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				getMarcaProductoActualizadaIf().setClienteId(operadorNegocio.getId());
				getMarcaProductoActualizadaIf().setNombre(getTxtNombre().getText());
				getMarcaProductoActualizadaIf().setTipo(TIPO_PROVEEDOR);
				getMarcaProductoActualizadaIf().setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getMarcaProductoSessionService().saveMarcaProducto(getMarcaProductoActualizadaIf());
				getMarcaProductoVector().setElementAt(getMarcaProductoActualizadaIf(), getMarcaProductoSeleccionada());
				setMarcaProductoActualizadaIf(null);
				SpiritAlert.createAlert("Marca actualizada con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("marcaProducto",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			MarcaProductoIf marcaProductoIf = (MarcaProductoIf) getMarcaProductoVector().get(getMarcaProductoSeleccionada());
			SessionServiceLocator.getMarcaProductoSessionService().deleteMarcaProducto(marcaProductoIf.getId());
			SpiritAlert.createAlert("Marca eliminada con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("marcaProducto",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarFilasTabla(MarcaProductoIf marcaProducto, Vector<String> fila){
		fila.add(marcaProducto.getCodigo());
		fila.add(marcaProducto.getNombre());
		if (marcaProducto.getEstado().equals(ESTADO_ACTIVO))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else
			fila.add(NOMBRE_ESTADO_INACTIVO);
	}
	
	private void cargarTabla(Long clienteId) {
		try {
			Collection marcasProducto = SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByClienteId(clienteId); 
			Iterator marcasProductoIterator = marcasProducto.iterator();
			
			if(!getMarcaProductoVector().isEmpty())
				getMarcaProductoVector().removeAllElements();
			
			while (marcasProductoIterator.hasNext()) {
				MarcaProductoIf marcaProducto = (MarcaProductoIf) marcasProductoIterator.next();
				tableModel = (DefaultTableModel) getTblMarcaProducto().getModel();
				Vector<String> fila = new Vector<String>();
				getMarcaProductoVector().add(marcaProducto);
				agregarFilasTabla(marcaProducto, fila);
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblMarcaProducto(), marcasProducto, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText()) && "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		Collection marcasProducto = null;
		boolean codigoRepetido = false;
		
		try {
			Map parameterMap = new HashMap();
			parameterMap.put("tipo", TIPO_PROVEEDOR);
			parameterMap.put("empresaId", Parametros.getIdEmpresa());
			marcasProducto = SessionServiceLocator.getMarcaProductoSessionService().findMarcaProductoByQuery(parameterMap);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator marcasProductoIt = marcasProducto.iterator();
		
		while (marcasProductoIt.hasNext()) {
			MarcaProductoIf marcaProducto = (MarcaProductoIf) marcasProductoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(marcaProducto.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(marcaProducto.getCodigo())) 
					if (getMarcaProductoActualizadaIf().getId().equals(marcaProducto.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la marca está duplicado!!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para la marca!!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if (operadorNegocio == null) {
			SpiritAlert.createAlert("Debe escoger al operador de negocio!!", SpiritAlert.WARNING);
			getBtnBuscarOperadorNegocio().grabFocus();
			return false;
		}
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la marca!!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getTxtCorporacion().setText("");
		corporacion = null;
		getTxtOperadorNegocio().setText("");
		operadorNegocio = null;
		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		cleanTable();
		setMarcaProductoActualizadaIf(null);
		setMarcaProductoSeleccionada(-1);
	}

	private void cleanTable() {
		DefaultTableModel model = (DefaultTableModel) getTblMarcaProducto().getModel();
		for(int i= this.getTblMarcaProducto().getRowCount();i>0;--i)
			model.removeRow(i-1);
		getMarcaProductoVector().clear();
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblMarcaProducto().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public Vector getMarcaProductoVector() {
		return this.marcaProductoVector;
	}
	
	public void setMarcaProductoVector(Vector marcaProductoVector) {
		this.marcaProductoVector = marcaProductoVector;
	}
	
	public int getMarcaProductoSeleccionada() {
		return this.marcaProductoSeleccionada;
	}
	
	public void setMarcaProductoSeleccionada(int marcaProductoSeleccionada) {
		this.marcaProductoSeleccionada = marcaProductoSeleccionada;
	}
	
	public MarcaProductoIf getMarcaProductoActualizadaIf() {
		return this.marcaProductoActualizadaIf;
	}
	
	public void setMarcaProductoActualizadaIf(MarcaProductoIf marcaProductoActualizadaIf) {
		this.marcaProductoActualizadaIf = marcaProductoActualizadaIf;
	}
 
}
