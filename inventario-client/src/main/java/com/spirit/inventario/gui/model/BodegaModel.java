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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.gui.controller.GeneralFinder;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.general.util.DateHelperClient;
import com.spirit.inventario.entity.BodegaData;
import com.spirit.inventario.entity.BodegaIf;
import com.spirit.inventario.entity.FuncionBodegaIf;
import com.spirit.inventario.entity.TipoBodegaIf;
import com.spirit.inventario.gui.criteria.FuncionBodegaCriteria;
import com.spirit.inventario.gui.criteria.TipoBodegaCriteria;
import com.spirit.inventario.gui.panel.JPBodega;
import com.spirit.inventario.session.BodegaSessionService;
import com.spirit.inventario.session.TipoBodegaSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.FinderTable;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class BodegaModel extends JPBodega {
	
	private static final long serialVersionUID = -2050101724253858931L;

	protected int mode;
	public boolean isFinderTableVisible = false;
	private JDPopupFinderModel popupFinder;
	private TipoBodegaCriteria tipoBodegaCriteria;
	private FuncionBodegaCriteria funcionBodegaCriteria;
	protected FinderTable finderTable;
	FuncionBodegaIf funcionBodega;
	TipoBodegaIf tipoBodega;
	ArrayList lista;
	java.util.Date fechaCreacion = new java.util.Date();	
	private Vector bodegasVector = new Vector();
	private DefaultTableModel tableModel;
	private int bodegaSeleccionada;
	private BodegaIf bodegaActualizadaIf;	
	private static final int MAX_LONGITUD_CODIGO = 5;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";

	public BodegaModel() {
		anchoColumnasTabla();
		getTblBodega().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		initKeyListeners();
		setSorterTable(getTblBodega());
		initButtonListeners();
    	showSaveMode();
    	getTblBodega().addMouseListener(oMouseAdapterTblBodega);
		getTblBodega().addKeyListener(oKeyAdapterTblBodega);
		
    	new HotKeyComponent(this);
	}
	
	public BodegaModel(boolean isPopUp) {
		initKeyListeners();
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblBodega().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(30);
		anchoColumna = getTblBodega().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblBodega().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblBodega().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(150);
		anchoColumna = getTblBodega().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(30);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void initButtonListeners(){
		getBtnBuscarFuncionBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				funcionBodegaCriteria = new FuncionBodegaCriteria("Funcion Bodega");
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
						funcionBodegaCriteria,
						JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					funcionBodega = (FuncionBodegaIf) popupFinder.getElementoSeleccionado();
					getTxtFuncionBodega().setText(funcionBodega.getCodigo()
							+ " - " + funcionBodega.getNombre());
				}
			}
		});
		
		getBtnBuscarTipoBodega().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				tipoBodegaCriteria = new TipoBodegaCriteria("Tipo Bodega");
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
						tipoBodegaCriteria,
						JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					tipoBodega = (TipoBodegaIf) popupFinder.getElementoSeleccionado();
					getTxtTipoBodega().setText(tipoBodega.getCodigo() + " - " + tipoBodega.getNombre());
				}
			}
		});
	}
	
	MouseListener oMouseAdapterTblBodega = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblBodega = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setBodegaSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			setBodegaActualizadaIf((BodegaIf)  getBodegasVector().get(getBodegaSeleccionada()));
			
			try {
				getTxtCodigo().setText(getBodegaActualizadaIf().getCodigo());
				getTxtNombre().setText(getBodegaActualizadaIf().getNombre());
				
				getCmbOficina().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbOficina(), getBodegaActualizadaIf().getOficinaId()));
				getCmbOficina().repaint();
				
				tipoBodega = (TipoBodegaIf) SessionServiceLocator.getTipoBodegaSessionService().getTipoBodega(getBodegaActualizadaIf().getTipoBodegaId());
				getTxtTipoBodega().setText(tipoBodega.getCodigo() + " - " + tipoBodega.getNombre());
				
				String fecha = Utilitarios.getFechaUppercase(getBodegaActualizadaIf().getFechaCreacion());
				getTxtFechaCreacion().setText(fecha);
				
				funcionBodega = SessionServiceLocator.getFuncionBodegaSessionService().getFuncionBodega(getBodegaActualizadaIf().getFuncionBodegaId());
				getTxtFuncionBodega().setText(funcionBodega.getCodigo() + " - " + funcionBodega.getNombre());
				
				if(getBodegaActualizadaIf().getEstado().equals(ESTADO_ACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
				else if (getBodegaActualizadaIf().getEstado().equals(ESTADO_INACTIVO))
					getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
				
			} catch (GenericBusinessException e1) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e1.printStackTrace();
			}
			
			showUpdateMode();
		}
	}

	public void save() {

		if (validateFields()) {
			BodegaData data = new BodegaData();
			try {

				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setOficinaId(((OficinaIf) this.getCmbOficina().getSelectedItem()).getId());
				data.setFuncionBodegaId(funcionBodega.getId());
				data.setTipoBodegaId(tipoBodega.getId());
				data.setFechaCreacion(DateHelperClient.getTimeStamp(fechaCreacion));
				data.setEstado(this.getCmbEstado().getSelectedItem().toString()
						.substring(0, 1));
				SessionServiceLocator.getBodegaSessionService().addBodega(data);
				SpiritAlert.createAlert("Bodega guardada con éxito !!!", SpiritAlert.INFORMATION);
				this.clean();
				this.showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
			}
		} 
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getBodegaSessionService().deleteBodega(bodegaActualizadaIf.getId());
			SpiritAlert.createAlert("Bodega eliminada con éxito !!!", SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		} catch (Exception e) {
			SpiritAlert.createAlert("Error al eliminar informacion!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		bodegaActualizadaIf.setNombre(this.getTxtNombre().getText());
		bodegaActualizadaIf.setOficinaId(((OficinaIf) this.getCmbOficina().getSelectedItem()).getId());
		bodegaActualizadaIf.setFuncionBodegaId(funcionBodega.getId());
		bodegaActualizadaIf.setTipoBodegaId(tipoBodega.getId());
		bodegaActualizadaIf.setEstado(this.getCmbEstado().getSelectedItem().toString().substring(0, 1));

		try {
			SessionServiceLocator.getBodegaSessionService().saveBodega(bodegaActualizadaIf);
			SpiritAlert.createAlert("Bodega actualizada con éxito !!!", SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar información!",SpiritAlert.ERROR);
		}
	}

	public boolean validateFields() {		
		if ("".equals(this.getTxtCodigo().getText())) {
			SpiritAlert.createAlert("Debe ingresar el código de la bodega !!!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		Collection bodegas = null;
		boolean codigoRepetido = false;
		
		try {
			bodegas = SessionServiceLocator.getBodegaSessionService().findBodegaByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator bodegasIt = bodegas.iterator();
		
		while (bodegasIt.hasNext()) {
			BodegaIf bodegaIf = (BodegaIf) bodegasIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(bodegaIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(bodegaIf.getCodigo())) 
					if (bodegaActualizadaIf.getId().equals(bodegaIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la bodega está repetido !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtNombre().getText())) {
			SpiritAlert.createAlert("Debe ingresar el nombre de la bodega !!!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (this.getCmbEstado().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar el estado de la bodega !!!", SpiritAlert.WARNING);
			getCmbEstado().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtFuncionBodega().getText())) {
			SpiritAlert.createAlert("Debe ingresar la función de la bodega !!!", SpiritAlert.WARNING);
			getBtnBuscarFuncionBodega().grabFocus();
			return false;
		}
		
		if (this.getCmbOficina().getSelectedItem() == null) {
			SpiritAlert.createAlert("Debe seleccionar la oficina para la bodega !!!", SpiritAlert.WARNING);
			getCmbOficina().grabFocus();
			return false;
		}
		
		if ("".equals(this.getTxtTipoBodega().getText())) {
			SpiritAlert.createAlert("Debe ingresar el tipo de la bodega !!!", SpiritAlert.WARNING);
			getBtnBuscarTipoBodega().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.getCmbEstado().setSelectedItem(null);
		this.getTxtFuncionBodega().setText("");
		this.getCmbOficina().setSelectedItem(null);
		this.getTxtTipoBodega().setText("");
		this.getTxtFechaCreacion().setText(null);
		
		if(getMode()==SpiritMode.SAVE_MODE || getMode()==SpiritMode.UPDATE_MODE){
			this.getCmbEstado().removeAllItems();
			this.getCmbOficina().removeAllItems();
		}
		
		DefaultTableModel model = (DefaultTableModel) getTblBodega().getModel();
		for(int i= this.getTblBodega().getRowCount();i>0;--i)
			model.removeRow(i-1);
		
		this.repaint();
	}

	public void showSaveMode() {
		setSaveMode();

		clean();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtFuncionBodega().setEnabled(true);
		getTxtFuncionBodega().setEditable(false);
		getTxtTipoBodega().setEnabled(true);
		getTxtTipoBodega().setEditable(false);
		getTxtFechaCreacion().setText(Utilitarios.fechaAhora());
		getTxtFechaCreacion().setEnabled(true);
		getTxtFechaCreacion().setEditable(false);
		getCmbOficina().setEnabled(true);
		
		cargarCombos();	
		cargarTabla();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection bodegas = SessionServiceLocator.getBodegaSessionService().findBodegaByEmpresaId(Parametros.getIdEmpresa());
			Iterator bodegasIterator = bodegas.iterator();
			
			if(!getBodegasVector().isEmpty()){
				getBodegasVector().removeAllElements();
			}
			
			while (bodegasIterator.hasNext()) {
				BodegaIf bodegasIf = (BodegaIf) bodegasIterator.next();
				
				tableModel = (DefaultTableModel) getTblBodega().getModel();
				Vector<String> fila = new Vector<String>();
				getBodegasVector().add(bodegasIf);
				
				agregarColumnasTabla(bodegasIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblBodega(), bodegas, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(BodegaIf bodegasIf, Vector<String> fila){
		fila.add(bodegasIf.getCodigo());
		fila.add(bodegasIf.getNombre());
		
		try {
			OficinaIf oficina = SessionServiceLocator.getOficinaSessionService().getOficina(bodegasIf.getOficinaId());
			fila.add(oficina.getCodigo() + " - " + oficina.getNombre());
			
			TipoBodegaIf tipoBodega = (TipoBodegaIf) SessionServiceLocator.getTipoBodegaSessionService().getTipoBodega(bodegasIf.getTipoBodegaId());
			fila.add(tipoBodega.getCodigo() + " - " + tipoBodega.getNombre());
		
			if(bodegasIf.getEstado().equals(ESTADO_ACTIVO))
				fila.add(NOMBRE_ESTADO_ACTIVO);
			else if(bodegasIf.getEstado().equals(ESTADO_INACTIVO))
				fila.add(NOMBRE_ESTADO_INACTIVO);
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarCombos() {
		cargarComboOficina();
		
		getCmbEstado().setEnabled(true);
		getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
		getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
	}
	
	private void cargarComboOficina(){
		List oficinas = (List) GeneralFinder.findOficinasByEmpresa(Parametros.getIdEmpresa());
		refreshCombo(getCmbOficina(), oficinas);
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbEstado().setEnabled(true);
		getTxtFuncionBodega().setEnabled(true);
		getCmbOficina().setEnabled(true);
		getTxtTipoBodega().setEnabled(true);
		getTxtFechaCreacion().setEnabled(false);
	}

	 

	public void refresh() {
		cargarComboOficina();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public BodegaIf getBodegaActualizadaIf() {
		return bodegaActualizadaIf;
	}

	public void setBodegaActualizadaIf(BodegaIf bodegaActualizadaIf) {
		this.bodegaActualizadaIf = bodegaActualizadaIf;
	}

	public int getBodegaSeleccionada() {
		return bodegaSeleccionada;
	}

	public void setBodegaSeleccionada(int bodegaSeleccionada) {
		this.bodegaSeleccionada = bodegaSeleccionada;
	}

	public Vector getBodegasVector() {
		return bodegasVector;
	}

	public void setBodegasVector(Vector bodegasVector) {
		this.bodegasVector = bodegasVector;
	}
}
