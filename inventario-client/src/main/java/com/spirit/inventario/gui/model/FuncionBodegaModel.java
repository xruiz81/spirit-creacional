package com.spirit.inventario.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.FuncionBodegaData;
import com.spirit.inventario.entity.FuncionBodegaIf;
import com.spirit.inventario.gui.panel.JPFuncionBodega;
import com.spirit.inventario.session.FuncionBodegaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ArrayListTableModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class FuncionBodegaModel extends JPFuncionBodega {
	
	private static final long serialVersionUID = -7773616374889113105L;
	private FuncionBodegaIf funcionBodega;
	private TableModel dataModel;
	private ArrayList lista;
	private boolean isPopup = false;
	
	private static final int MAX_LONGITUD_CODIGO = 2;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String ESTADO_ACTIVO = NOMBRE_ESTADO_ACTIVO.substring(0,1);
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_INACTIVO = NOMBRE_ESTADO_INACTIVO.substring(0,1);
	
	private Vector funcionBodegaVector = new Vector();
	private int funcionBodegaSeleccionada;
	private FuncionBodegaIf funcionBodegaActualizadaIf;
	private DefaultTableModel tableModel;

	public FuncionBodegaModel() {
		initKeyListeners();
		setSorterTable(getTblFuncionBodega());
		anchoColumnasTabla();
		getTblFuncionBodega().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblFuncionBodega().addMouseListener(oMouseAdapterTblFuncionBodega);
		getTblFuncionBodega().addKeyListener(oKeyAdapterTblFuncionBodega);		
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblFuncionBodega().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblFuncionBodega().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
		anchoColumna = getTblFuncionBodega().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
	}
	
	MouseListener oMouseAdapterTblFuncionBodega = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblFuncionBodega = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setFuncionBodegaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			funcionBodegaActualizadaIf = (FuncionBodegaIf)  getFuncionBodegaVector().get(getFuncionBodegaSeleccionada());
			getTxtCodigo().setText(getFuncionBodegaActualizadaIf().getCodigo());
			getTxtNombre().setText(getFuncionBodegaActualizadaIf().getNombre());
			
			if (ESTADO_ACTIVO.equals(getFuncionBodegaActualizadaIf().getEstado()))
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
			else
				getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
			
			showUpdateMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection funcionBodega = SessionServiceLocator.getFuncionBodegaSessionService().findFuncionBodegaByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator funcionBodegaIterator = funcionBodega.iterator();
			
			if(!getFuncionBodegaVector().isEmpty()){
				getFuncionBodegaVector().removeAllElements();
			}
			
			while (funcionBodegaIterator.hasNext()) {
				FuncionBodegaIf funcionBodegaIf = (FuncionBodegaIf) funcionBodegaIterator.next();
				
				tableModel = (DefaultTableModel) getTblFuncionBodega().getModel();
				Vector<String> fila = new Vector<String>();
				getFuncionBodegaVector().add(funcionBodegaIf);
				
				agregarColumnasTabla(funcionBodegaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblFuncionBodega(), funcionBodega, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(FuncionBodegaIf funcionBodegaIf, Vector<String> fila){
		
		fila.add(funcionBodegaIf.getCodigo());
		fila.add(funcionBodegaIf.getNombre());
		
		if (ESTADO_ACTIVO.equals(funcionBodegaIf.getEstado()))
			fila.add(NOMBRE_ESTADO_ACTIVO);
		else
			fila.add(NOMBRE_ESTADO_INACTIVO);
	}

	private ArrayList getModel(ArrayList listaFuncionBodega) {
		ArrayList data = new ArrayList();
		Iterator it = listaFuncionBodega.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			FuncionBodegaIf bean = (FuncionBodegaIf) it.next();
            
			fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
          
		data.add(fila);
		}
		return data;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		if (!("".equals(getTxtCodigo().getText()))) {
			aMap.put("codigo", getTxtCodigo().getText());
		}
		else
			aMap.put("codigo", "%");
		if (!("".equals(getTxtNombre().getText()))) {
			aMap.put("nombre", getTxtNombre().getText());
		}
		else
			aMap.put("nombre", "%");
		if (getCmbEstado().getSelectedItem()!=null) {
			aMap.put("estado", getCmbEstado().getSelectedItem().toString()
					.substring(0, 1));
		}
		else
			aMap.put("estado", "%");
		return aMap;
	}
	
	
	public void save() {
		try {
			if (validateFields()) {
				FuncionBodegaData data = new FuncionBodegaData();

				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setEmpresaId(Parametros.getIdEmpresa());
				data.setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				
				SessionServiceLocator.getFuncionBodegaSessionService().addFuncionBodega(data);
				SpiritAlert.createAlert("Función de bodega guardada con éxito",SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setFuncionBodegaActualizadaIf((FuncionBodegaIf) getFuncionBodegaVector().get(getFuncionBodegaSeleccionada()));
				
				getFuncionBodegaActualizadaIf().setCodigo(getTxtCodigo().getText());
				getFuncionBodegaActualizadaIf().setNombre(getTxtNombre().getText());
				getFuncionBodegaActualizadaIf().setEmpresaId(Parametros.getIdEmpresa());
				getFuncionBodegaActualizadaIf().setEstado(getCmbEstado().getSelectedItem().toString().substring(0,1));
				
				getFuncionBodegaVector().setElementAt(getFuncionBodegaActualizadaIf(), getFuncionBodegaSeleccionada());
				
				SessionServiceLocator.getFuncionBodegaSessionService().saveFuncionBodega(getFuncionBodegaActualizadaIf());
				setFuncionBodegaActualizadaIf(null);
				
				SpiritAlert.createAlert("Función de bodega actualizada con éxito",SpiritAlert.INFORMATION);	
				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomación!",SpiritAlert.ERROR);
		}	
	}

	public void delete() {
		try {
			FuncionBodegaIf funcionBodegaIf = (FuncionBodegaIf) getFuncionBodegaVector().get(getFuncionBodegaSeleccionada());
			SessionServiceLocator.getFuncionBodegaSessionService().deleteFuncionBodega(funcionBodegaIf.getId());
			SpiritAlert.createAlert("Función de bodega eliminada con éxito",SpiritAlert.INFORMATION);
			this.showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}
	
	public void find() {
		try {

			if(this.isPopup) {
				
				Map mapa  = buildQuery();
				int tamanoLista = SessionServiceLocator.getFuncionBodegaSessionService()
									.getFuncionBodegaListSize(mapa,Parametros.getIdEmpresa());
				if (tamanoLista > 0) {
				
					ArrayList headers = new ArrayList();
					headers.add("Codigo");
					headers.add("Nombre");

					lista = (ArrayList) SessionServiceLocator.getFuncionBodegaSessionService()
								.findFuncionBodegaByQueryAndByIdEmpresa(buildQuery(),Parametros.getIdEmpresa());
					ArrayList data = getModel(lista);

					dataModel = new ArrayListTableModel(data, headers);	
				
				} else {
					dataModel = new ArrayListTableModel();
					SpiritAlert.createAlert("No se encontraron registros para los datos seteados",SpiritAlert.INFORMATION);
				}	
				
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de información",SpiritAlert.ERROR);
		}
	}

	public boolean isEmpty() {
		if (true
			    && "".equals(this.getTxtCodigo().getText())
			    && "".equals(this.getTxtNombre().getText())
			    && (this.getCmbEstado().getSelectedItem()== null)
			         ) {
				return true;
			} else {
				return false;
			}
	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbEstado().setSelectedItem(null);
		
		if(getMode()==SpiritMode.SAVE_MODE || getMode()==SpiritMode.UPDATE_MODE){
			//mando a limpiar los combos
			this.getCmbEstado().removeAllItems();
		}
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblFuncionBodega().getModel();
		
		for(int i= this.getTblFuncionBodega().getRowCount();i>0;--i)
			model.removeRow(i-1);

	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbEstado().setEnabled(true);
		
		clean();
		cargarCombos();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTblFuncionBodega().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void find(Long idEmpresa) {
		try {
			
			if(this.isPopup) {
				
				lista = (ArrayList) SessionServiceLocator.getFuncionBodegaSessionService().findFuncionBodegaByQueryAndByIdEmpresa(buildQuery(),Parametros.getIdEmpresa());
			
				if (lista.size() != 0) {
				
					ArrayList headers = new ArrayList();
					headers.add("Codigo");
					headers.add("Nombre");

					ArrayList data = getModel(lista);

					dataModel = new ArrayListTableModel(data, headers);	
				
				} else {
					dataModel = new ArrayListTableModel();
					SpiritAlert.createAlert("No se encontraron registros para los datos seteados",SpiritAlert.INFORMATION);
				}		
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de información",SpiritAlert.ERROR);
		}
	}

	public void cargarCombos(){
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
	}
		
	public Vector getFuncionBodegaVector() {
		return this.funcionBodegaVector;
	}
	
	public void setFuncionBodegaVector(Vector funcionBodegaVector) {
		this.funcionBodegaVector = funcionBodegaVector;
	}
	
	public int getFuncionBodegaSeleccionada() {
		return this.funcionBodegaSeleccionada;
	}
	
	public void setFuncionBodegaSeleccionada(int funcionBodegaSeleccionada) {
		this.funcionBodegaSeleccionada = funcionBodegaSeleccionada;
	}
	
	public FuncionBodegaIf getFuncionBodegaActualizadaIf() {
		return this.funcionBodegaActualizadaIf;
	}
	
	public void setFuncionBodegaActualizadaIf(FuncionBodegaIf funcionBodegaActualizadaIf) {
		this.funcionBodegaActualizadaIf = funcionBodegaActualizadaIf;
	}

	public boolean validateFields() {
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("No se ha ingresado el código del tipo de valor !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("No se ha ingresado la descripción del tipo de valor !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}
		
		if (getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado de la función de la bodega !!",SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		
		return true;
	}
	 
}
