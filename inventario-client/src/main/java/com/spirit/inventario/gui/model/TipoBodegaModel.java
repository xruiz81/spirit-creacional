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
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.TipoBodegaData;
import com.spirit.inventario.entity.TipoBodegaIf;
import com.spirit.inventario.gui.panel.JPTipoBodega;
import com.spirit.inventario.session.TipoBodegaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ArrayListTableModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoBodegaModel extends JPTipoBodega {

	private static final long serialVersionUID = -8603607678208194686L;
	private TableModel dataModel;
	private TipoBodegaIf tipoBodega;
	private ArrayList lista;
	private boolean isPopup = false;
	
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	
	private Vector tipoBodegaVector = new Vector();
	private int tipoBodegaSeleccionado;
	private TipoBodegaIf tipoBodegaActualizadoIf;
	private DefaultTableModel tableModel;
	
	public TipoBodegaModel() {
		initKeyListeners();
		setSorterTable(getTblTipoBodega());
		anchoColumnasTabla();
		getTblTipoBodega().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblTipoBodega().addMouseListener(oMouseAdapterTblTipoBodega);
		getTblTipoBodega().addKeyListener(oKeyAdapterTblTipoBodega);
		new HotKeyComponent(this);
	}
		
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoBodega().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblTipoBodega().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblTipoBodega = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoBodega = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoBodegaSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoBodegaActualizadoIf = (TipoBodegaIf)  getTipoBodegaVector().get(getTipoBodegaSeleccionado());
			getTxtCodigo().setText(getTipoBodegaActualizadoIf().getCodigo());
			getTxtNombre().setText(getTipoBodegaActualizadoIf().getNombre());
			showUpdateMode();
		}
	}
	
	private void cargarTabla() {
		try {			
			Collection tipoBodega = SessionServiceLocator.getTipoBodegaSessionService().findTipoBodegaByEmpresaId(Parametros.getIdEmpresa()); 
			Iterator tipoBodegaIterator = tipoBodega.iterator();
			
			if(!getTipoBodegaVector().isEmpty()){
				getTipoBodegaVector().removeAllElements();
			}
			
			while (tipoBodegaIterator.hasNext()) {
				TipoBodegaIf tipoBodegaIf = (TipoBodegaIf) tipoBodegaIterator.next();
				
				tableModel = (DefaultTableModel) getTblTipoBodega().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoBodegaVector().add(tipoBodegaIf);
				
				agregarColumnasTabla(tipoBodegaIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoBodega(), tipoBodega, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoBodegaIf tipoBodegaIf, Vector<String> fila){
		
		fila.add(tipoBodegaIf.getCodigo());
		fila.add(tipoBodegaIf.getNombre());		
	}
	
	private ArrayList getModel(ArrayList listaTipoBodega) {
		ArrayList data = new ArrayList();
		Iterator it = listaTipoBodega.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			TipoBodegaIf bean = (TipoBodegaIf) it.next();
            
			fila.add(bean.getCodigo()); 
            fila.add(bean.getNombre()); 
          
            data.add(fila);
		}
		return data;
	}
	
	private Map buildQuery() {
		Map aMap = new HashMap();
		
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText());
		else
			aMap.put("codigo", "%");
		
		if ("".equals(getTxtNombre().getText()) == false)
			aMap.put("nombre", "%" + getTxtNombre().getText() + "%");
		else
			aMap.put("nombre", "%");
		
		if (Long.valueOf(Parametros.getIdEmpresa()).compareTo(0L) != 0)
			aMap.put("empresaId", Parametros.getIdEmpresa());
		
		return aMap;
	}
	
	public void save() {
		try {
			if (validateFields()) {
				TipoBodegaData data = new TipoBodegaData();

				data.setCodigo(this.getTxtCodigo().getText().toUpperCase());
				data.setNombre(this.getTxtNombre().getText().toUpperCase());
				data.setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getTipoBodegaSessionService().addTipoBodega(data);
				SpiritAlert.createAlert("Tipo de bodega guardado con éxito",SpiritAlert.INFORMATION);
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
				setTipoBodegaActualizadoIf((TipoBodegaIf) getTipoBodegaVector().get(getTipoBodegaSeleccionado()));
				
				getTipoBodegaActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoBodegaActualizadoIf().setNombre(getTxtNombre().getText());
				getTipoBodegaActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				
				getTipoBodegaVector().setElementAt(getTipoBodegaActualizadoIf(), getTipoBodegaSeleccionado());
				
				SessionServiceLocator.getTipoBodegaSessionService().saveTipoBodega(getTipoBodegaActualizadoIf());
				setTipoBodegaActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de bodega actualizado con éxito",SpiritAlert.INFORMATION);	
				this.showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomacion!",SpiritAlert.ERROR);
		}	
	}
	
	public void delete() {
		try {
			TipoBodegaIf tipoBodegaIf = (TipoBodegaIf) getTipoBodegaVector().get(getTipoBodegaSeleccionado());
			SessionServiceLocator.getTipoBodegaSessionService().deleteTipoBodega(tipoBodegaIf.getId());
			SpiritAlert.createAlert("Tipo de bodega eliminado con éxito",SpiritAlert.INFORMATION);
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
				
				lista = (ArrayList) SessionServiceLocator.getTipoBodegaSessionService().findTipoBodegaByQuery(buildQuery());
			
				if (lista.size() != 0) {
				
				ArrayList headers = new ArrayList();
				headers.add("Codigo");
				headers.add("Nombre");

				ArrayList data = getModel(lista);
				
				dataModel = new ArrayListTableModel(data, headers);	
				
				} else {
					dataModel = new ArrayListTableModel();
					SpiritAlert.createAlert("No se encontraron registros para los datos seteados",SpiritAlert.WARNING);
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
			         ) {
				return true;
			} else {
				return false;
			}
	}

	public void clean() {

		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoBodega().getModel();
		
		for(int i= this.getTblTipoBodega().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();
		getTxtCodigo().grabFocus();
	}

	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getTblTipoBodega().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void find(Long idEmpresa) {
		try {
			
			if(this.isPopup) {
				
				lista = (ArrayList) SessionServiceLocator.getTipoBodegaSessionService().findTipoBodegaByQuery(buildQuery());
			
				if (lista.size() != 0) {
				
				ArrayList headers = new ArrayList();
				headers.add("Codigo");
				headers.add("Nombre");

				ArrayList data = getModel(lista);

				dataModel = new ArrayListTableModel(data, headers);	
				
				} else {
					dataModel = new ArrayListTableModel();
					SpiritAlert.createAlert("No se encontraron registros para los datos seteados",SpiritAlert.WARNING);
				}	
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de información",SpiritAlert.ERROR);
		}
	}

	public Vector getTipoBodegaVector() {
		return this.tipoBodegaVector;
	}
	
	public void setTipoBodegaVector(Vector tipoBodegaVector) {
		this.tipoBodegaVector = tipoBodegaVector;
	}
	
	public int getTipoBodegaSeleccionado() {
		return this.tipoBodegaSeleccionado;
	}
	
	public void setTipoBodegaSeleccionado(int tipoBodegaSeleccionado) {
		this.tipoBodegaSeleccionado = tipoBodegaSeleccionado;
	}
	
	public TipoBodegaIf getTipoBodegaActualizadoIf() {
		return this.tipoBodegaActualizadoIf;
	}
	
	public void setTipoBodegaActualizadoIf(TipoBodegaIf tipoBodegaActualizadoIf) {
		this.tipoBodegaActualizadoIf = tipoBodegaActualizadoIf;
	}
	
	public boolean validateFields() {
		if ("".equals(getTxtCodigo().getText())) {
			SpiritAlert.createAlert("No se ha ingresado el código del tipo de bodega !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;			
		}
		
		if ("".equals(getTxtNombre().getText())) {
			SpiritAlert.createAlert("No se ha ingresado la descripción del tipo de bodega !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;			
		}
		
		return true;
	}

	 
}
