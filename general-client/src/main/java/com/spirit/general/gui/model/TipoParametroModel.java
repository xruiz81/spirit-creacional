package com.spirit.general.gui.model;

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
import java.util.Iterator;
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
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoParametroData;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPTipoParametro;
import com.spirit.util.NumberTextFieldDecimal;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class TipoParametroModel extends JPTipoParametro {

	private static final long serialVersionUID = -2576354588605762621L;
	
	private static final String TIPO_CARACTER = "C - CARACTER";
	private static final String TIPO_STRING = "S - STRING";
	private static final String TIPO_NUMERO = "N - NUMERO";
	private static final String TIPO_FECHA = "F - FECHA";
	
	private static final int MAX_LONGITUD_CODIGO = 11;
	private static final int MAX_LONGITUD_DESCRIPCION = 40;
	private static final int MAX_LONGITUD_MASCARA_CARACTER = 1;
	private static final int MAX_LONGITUD_MASCARA_STRING = 20;
	
	private Vector tipoParametroVector = new Vector();
	private DefaultTableModel tableModel;
	private int tipoParametroSeleccionado;
	private TipoParametroIf tipoParametroIf;
	private KeyListener actualKeyListener;
	
	public TipoParametroModel() {
		initKeyListeners();
		setSorterTable(getTblTipoParametroDetalles());
		anchoColumnasTabla();
		getTblTipoParametroDetalles().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}

	private void initListeners() {
		getCmbTipoParametro().addActionListener(comboTipoParametroListener);
		getTblTipoParametroDetalles().addMouseListener(oMouseAdapterTblTipoParametro);
		getTblTipoParametroDetalles().addKeyListener(oKeyAdapterTblTipoParametro);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtDescripcion().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION));
		actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_CARACTER);
		getTxtMascara().addKeyListener(actualKeyListener);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblTipoParametroDetalles().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblTipoParametroDetalles().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(300);
		anchoColumna = getTblTipoParametroDetalles().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblTipoParametroDetalles().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(50);
	}

	public void cargarCombo(){
		getCmbTipoParametro().addItem(TIPO_CARACTER);
		getCmbTipoParametro().addItem(TIPO_STRING);
		getCmbTipoParametro().addItem(TIPO_NUMERO);
		getCmbTipoParametro().addItem(TIPO_FECHA);
	}
	
	public void save() {
		try {
			if (validateFields()) {
				
				TipoParametroData tipoParametro = new TipoParametroData();
				
				tipoParametro.setCodigo(getTxtCodigo().getText());
				tipoParametro.setNombre(getTxtDescripcion().getText());
				if (!getTxtMascara().equals("")) tipoParametro.setMascara(getTxtMascara().getText());
				tipoParametro.setTipo(getCmbTipoParametro().getSelectedItem().toString().substring(0,1));
				tipoParametro.setEmpresaId(Parametros.getIdEmpresa());
					
				SessionServiceLocator.getTipoParametroSessionService().addTipoParametro(tipoParametro);
				
				SpiritAlert.createAlert("Tipo de Parámetro guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoParametro",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la informacin!",
					SpiritAlert.ERROR);
		}
		
	}
	
	public void update() {
		try {
			if (validateFields()) {
				setTipoParametroActualizadoIf((TipoParametroIf) getTipoParametroVector().get(getTipoParametroSeleccionado()));
				
				getTipoParametroActualizadoIf().setCodigo(getTxtCodigo().getText());
				getTipoParametroActualizadoIf().setNombre(getTxtDescripcion().getText());
				if (!getTxtMascara().equals("")) getTipoParametroActualizadoIf().setMascara(getTxtMascara().getText());
				getTipoParametroActualizadoIf().setTipo(getCmbTipoParametro().getSelectedItem().toString().substring(0,1));
				getTipoParametroActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getTipoParametroSessionService().saveTipoParametro(getTipoParametroActualizadoIf());
				getTipoParametroVector().setElementAt(getTipoParametroActualizadoIf(), getTipoParametroSeleccionado());
				setTipoParametroActualizadoIf(null);
				
				SpiritAlert.createAlert("Tipo de Parámetro actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("tipoParametro",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la informacin!",
					SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		
		try {
			TipoParametroIf tipoParametroIf = (TipoParametroIf) getTipoParametroVector().get(getTipoParametroSeleccionado());
			SessionServiceLocator.getTipoParametroSessionService().deleteTipoParametro(tipoParametroIf.getId());
			SpiritAlert.createAlert("Tipo de Parámetro eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("tipoParametro",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("El Tipo de Parámetro tiene datos referenciados y no puede ser eliminado!",
					SpiritAlert.ERROR);
		}
		
		this.showSaveMode();
	}	

	public boolean validateFields() {
		
		String strCodigo = getTxtCodigo().getText();
		String strDescripcion = getTxtDescripcion().getText();
		
		Collection tipoParametros = null;
		boolean codigoRepetido = false;
		
		try {
			tipoParametros = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		Iterator tipoParametroIt = tipoParametros.iterator();
		
		while (tipoParametroIt.hasNext()) {
			TipoParametroIf tipoParametroIf = (TipoParametroIf) tipoParametroIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(tipoParametroIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(tipoParametroIf.getCodigo())) 
					if (this.tipoParametroIf.getId().equals(tipoParametroIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El cdigo del Tipo de Parámetro está duplicado !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un cdigo para el Tipo de Parámetro !!",
					SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strDescripcion)) || (strDescripcion == null))) {
			SpiritAlert.createAlert("Debe ingresar una descripcin para el Tipo de Parámetro !!",
					SpiritAlert.WARNING);
			getTxtDescripcion().grabFocus();
			return false;
		}
		
		if (getCmbTipoParametro().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Tipo para el Tipo de Parámetro !!",
					SpiritAlert.WARNING);
			getCmbTipoParametro().grabFocus();
			return false;
		}
		
		return true;
	}

	public boolean isEmpty() {
		return false;
	}

	public void clean() {
		
		getTxtCodigo().setText("");
		getTxtCodigo().setEnabled(true);
		getTxtDescripcion().setText("");
		getTxtDescripcion().setEnabled(true);
		getTxtMascara().setText("");
		getTxtMascara().setEnabled(true);
		getCmbTipoParametro().removeAllItems();
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblTipoParametroDetalles().getModel();
		for(int i= this.getTblTipoParametroDetalles().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showFindMode() {
		showSaveMode();
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarCombo();
		cargarTabla();	
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {			
			Collection<TipoParametroIf> tipoParametro = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByEmpresaId(Parametros.getIdEmpresa());
			
			if(!getTipoParametroVector().isEmpty()){
				getTipoParametroVector().removeAllElements();
			}
			
			for (TipoParametroIf tipoParametroIf : tipoParametro) {
				
				tableModel = (DefaultTableModel) getTblTipoParametroDetalles().getModel();
				Vector<String> fila = new Vector<String>();
				getTipoParametroVector().add(tipoParametroIf);
				
				agregarColumnasTabla(tipoParametroIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblTipoParametroDetalles(), tipoParametro, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(TipoParametroIf tipoParametroIf, Vector<String> fila){
		
		fila.add(tipoParametroIf.getCodigo());
		fila.add(tipoParametroIf.getNombre());
		
		if((TIPO_CARACTER.substring(0, 1)).equals(tipoParametroIf.getTipo()))
			fila.add(TIPO_CARACTER);
		else if((TIPO_STRING.substring(0, 1)).equals(tipoParametroIf.getTipo()))
			fila.add(TIPO_STRING);
		else if((TIPO_NUMERO.substring(0, 1)).equals(tipoParametroIf.getTipo()))
			fila.add(TIPO_NUMERO);
		else if((TIPO_FECHA.substring(0, 1)).equals(tipoParametroIf.getTipo()))
			fila.add(TIPO_FECHA);
		
		fila.add(tipoParametroIf.getMascara());
	}
	
	MouseListener oMouseAdapterTblTipoParametro = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblTipoParametro = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setTipoParametroSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			tipoParametroIf = (TipoParametroIf)  getTipoParametroVector().get(getTipoParametroSeleccionado());
			getTxtCodigo().setText(tipoParametroIf.getCodigo());
			getTxtDescripcion().setText(tipoParametroIf.getNombre());
			
			for(int i=0;i < getCmbTipoParametro().getItemCount();i++){
				String bean = (String) getCmbTipoParametro().getItemAt(i);
				if((bean.substring(0,1)).compareTo(tipoParametroIf.getTipo())==0)
					getCmbTipoParametro().setSelectedItem(bean);
			}
			
			getTxtMascara().setText(tipoParametroIf.getMascara());		
			showUpdateMode();
		}
	}
	
	ActionListener comboTipoParametroListener = new ActionListener() {
		public void actionPerformed(ActionEvent evento) {

			if(getCmbTipoParametro().getSelectedItem() == TIPO_CARACTER){
				getTxtMascara().removeKeyListener(actualKeyListener);
				getTxtMascara().setEnabled(true);
				getTxtMascara().setText("");
				actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_CARACTER);
				getTxtMascara().addKeyListener(actualKeyListener);
			}
			else if(getCmbTipoParametro().getSelectedItem() == TIPO_STRING){
				getTxtMascara().removeKeyListener(actualKeyListener);
				getTxtMascara().setEnabled(true);
				getTxtMascara().setText("");
				actualKeyListener = new TextChecker(MAX_LONGITUD_MASCARA_STRING);
				getTxtMascara().addKeyListener(actualKeyListener);
			}
			else if(getCmbTipoParametro().getSelectedItem() == TIPO_NUMERO){
				getTxtMascara().removeKeyListener(actualKeyListener);
				getTxtMascara().setEnabled(true);
				getTxtMascara().setText("");
				actualKeyListener = new NumberTextFieldDecimal();
				getTxtMascara().addKeyListener(actualKeyListener);
			}
			else if(getCmbTipoParametro().getSelectedItem() == TIPO_FECHA){
				getTxtMascara().removeKeyListener(actualKeyListener);
				getTxtMascara().setText("DD-MM-YYYY");
				getTxtMascara().setEnabled(false);
			}
		}
	};	

	public Vector getTipoParametroVector() {
		return tipoParametroVector;
	}
	
	public void setTipoParametroVector(Vector tipoParametroVec) {
		tipoParametroVector = tipoParametroVec;
	}
	
	public int getTipoParametroSeleccionado() {
		return tipoParametroSeleccionado;
	}
	
	public void setTipoParametroSeleccionado(int selectedTipoParametro) {
		tipoParametroSeleccionado = selectedTipoParametro;
	}
	
	public TipoParametroIf getTipoParametroActualizadoIf() {
		return tipoParametroIf;
	}
	
	public void setTipoParametroActualizadoIf(TipoParametroIf tipoParametroIf) {
		this.tipoParametroIf = tipoParametroIf;
	}
	
}
