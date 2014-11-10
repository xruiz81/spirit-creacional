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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CiudadData;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.PaisIf;
import com.spirit.general.entity.ProvinciaIf;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.panel.JPCiudad;
import com.spirit.general.session.CiudadSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.SpiritComboBoxModel;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class CiudadModel extends JPCiudad {
	
	private static final long serialVersionUID = 3256728364084836656L;
	private static final int MAX_LONGITUD_CODIGO = 3;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	
	private Vector ciudadVector = new Vector();
	private DefaultTableModel tableModel;
	private int ciudadSeleccionada;
	protected CiudadIf ciudadIf;
	protected ProvinciaIf provinciaIf;
	protected PaisIf paisIf;

	public CiudadModel() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTblCiudad().addMouseListener(oMouseAdapterTblCiudad);
		getTblCiudad().addKeyListener(oKeyAdapterTblCiudad);
		setSorterTable(getTblCiudad());
		getTblCiudad().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.showSaveMode();
		initListeners();
		new HotKeyComponent(this);
	}
	
	public void initListeners(){
		getCmbPais().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					if(getCmbPais().getSelectedItem() != null){
						paisIf = (PaisIf)getCmbPais().getSelectedItem();
						ArrayList provinciaList = (ArrayList) SessionServiceLocator.getProvinciaSessionService().findProvinciaByPaisId(paisIf.getId());
						if(provinciaList.size() > 0){
							Collections.sort((ArrayList)provinciaList,ordenadorProvincia);
							SpiritComboBoxModel cmbProvinciaModel;
							cmbProvinciaModel = new SpiritComboBoxModel(provinciaList);
							getCmbProvincia().setModel(cmbProvinciaModel);
							provinciaIf = (ProvinciaIf)provinciaList.iterator().next();
							getCmbProvincia().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbProvincia(), provinciaIf.getId()));
							
						}
						getCmbProvincia().repaint();
					}
				} catch (GenericBusinessException e1) {
					e1.printStackTrace();
				}
			}	
		});
	}
	
	MouseListener oMouseAdapterTblCiudad = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblCiudad = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};

	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		try {
			//Obtengo la instancia del objeto seleccionado de la tabla
			if (((JTable)evt.getSource()).getSelectedRow() != -1) {
				int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
				setCiudadSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow)); 
				ciudadIf = (CiudadIf)  getCiudadVector().get(getCiudadSeleccionada());
				provinciaIf = SessionServiceLocator.getProvinciaSessionService().getProvincia(ciudadIf.getProvinciaId());
				
				getTxtCodigo().setText(ciudadIf.getCodigo());
				getTxtNombre().setText(ciudadIf.getNombre());
				getCmbProvincia().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbProvincia(), ciudadIf.getProvinciaId()));
				getCmbProvincia().repaint();
				getCmbPais().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbPais(), provinciaIf.getPaisId()));
				getCmbPais().repaint();
				showUpdateMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void save() {
		if (validateFields()) {
			CiudadData data = new CiudadData();

			data.setCodigo(this.getTxtCodigo().getText());
			data.setNombre(this.getTxtNombre().getText());
			data.setProvinciaId(((ProvinciaIf) this.getCmbProvincia().getSelectedItem()).getId());

			try {
				CiudadModel.getCiudadSessionService().addCiudad(data);
				showSaveMode();
				SpiritAlert.createAlert("Ciudad guardada con éxito!", SpiritAlert.INFORMATION);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la informaci\u00f3n!", SpiritAlert.ERROR);
			}
		} 
	}

	public void update() {
		try{
			if (validateFields()) {
				setCiudadActualizadaIf((CiudadIf) getCiudadVector().get(getCiudadSeleccionada()));
				
				getCiudadActualizadaIf().setCodigo(getTxtCodigo().getText());
				getCiudadActualizadaIf().setNombre(getTxtNombre().getText());
				getCiudadActualizadaIf().setProvinciaId(((ProvinciaIf) getCmbProvincia().getSelectedItem()).getId());
				this.clean();
				
				getCiudadVector().setElementAt(getCiudadActualizadaIf(), getCiudadSeleccionada());
				
				for (int i=0;i<getCiudadVector().size();i++) {
					setCiudadActualizadaIf((CiudadIf) getCiudadVector().get(i));
					getCiudadSessionService().saveCiudad(getCiudadActualizadaIf());
					setCiudadActualizadaIf(null);
				}
				
				showSaveMode();
				SpiritAlert.createAlert("Ciudad actualizada con éxito!", SpiritAlert.INFORMATION);
			}
			
		} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al actualizar informaci\u00f3n!", SpiritAlert.ERROR);
		}
	}
	
	public void delete() {
		try {
			CiudadIf ciudadEliminada = (CiudadIf) getCiudadVector().get(getCiudadSeleccionada());
			getCiudadSessionService().deleteCiudad(ciudadEliminada.getId());
			showSaveMode();
			SpiritAlert.createAlert("Ciudad eliminada con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public boolean isEmpty() {

		if ("".equals(this.getTxtCodigo().getText())
				&& "".equals(this.getTxtNombre().getText())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {

		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
		ProvinciaIf provincia = (ProvinciaIf) this.getCmbProvincia().getSelectedItem();
		
		Collection ciudad = null;
		boolean codigoRepetido = false;
		
		try {
			ciudad = getCiudadSessionService().getCiudadList();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator ciudadIt = ciudad.iterator();
		
		while (ciudadIt.hasNext()) {
			CiudadIf ciudadIf = (CiudadIf) ciudadIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(ciudadIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(ciudadIf.getCodigo())) 
					if (getCiudadActualizadaIf().getId().equals(ciudadIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El c\u00f3digo de la ciudad está repetido !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert
					.createAlert("Debe ingresar un codigo para la Ciudad !!", SpiritAlert.WARNING);
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert
					.createAlert("Debe ingresar un nombre para la Ciudad !!", SpiritAlert.WARNING);
			return false;
		}
		
		if (provincia == null) {
			SpiritAlert
					.createAlert("Debe seleccionar una Provincia para la Ciudad !!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}

	public void clean() {
		this.getCmbProvincia().setSelectedItem(null);
		this.getCmbProvincia().removeAllItems();
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		
		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblCiudad().getModel();
		for(int i= this.getTblCiudad().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showFindMode() {
		showSaveMode();
	}
	
	public void refresh(){
		cargarComboProvincias();
		cargarComboPais();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	public void cargarCombos(){
		cargarComboProvincias();
		cargarComboPais();
	}
	
	Comparator<PaisIf> ordenadorPais = new Comparator<PaisIf>(){
		public int compare(PaisIf p1, PaisIf p2) {
			return p1.getNombre().compareTo(p2.getNombre());
		}		
	};
	
	Comparator<ProvinciaIf> ordenadorProvincia = new Comparator<ProvinciaIf>(){
		public int compare(ProvinciaIf p1, ProvinciaIf p2) {
			return p1.getNombre().compareTo(p2.getNombre());
		}		
	};
	
	private void cargarComboProvincias(){
		try {
			List<ProvinciaIf> provinciaLista = (ArrayList) SessionServiceLocator.getProvinciaSessionService().getProvinciaList();
			Collections.sort(provinciaLista,ordenadorProvincia);
			SpiritComboBoxModel cmbProvinciaModel = new SpiritComboBoxModel(provinciaLista);
			getCmbProvincia().setModel(cmbProvinciaModel);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void cargarComboPais(){
		try {
			List<PaisIf> paisesLista = (ArrayList)SessionServiceLocator.getPaisSessionService().getPaisList();
			Collections.sort(paisesLista,ordenadorPais);			
			SpiritComboBoxModel cmbModelPais = new SpiritComboBoxModel(paisesLista);
			getCmbPais().setModel(cmbModelPais);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	public void showSaveMode() {
		setSaveMode();
		getCmbProvincia().setEnabled(true);
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getTxtCodigo().grabFocus();
		
		clean();
		cargarCombos();
		cargarTabla();
	}
	
	private void cargarTabla() {		
		try {
			Collection ciudad = getCiudadSessionService().getCiudadList();
			Iterator ciudadIterator = ciudad.iterator();
			
			if(!getCiudadVector().isEmpty()){
				getCiudadVector().removeAllElements();
			}
			
			while (ciudadIterator.hasNext()) {
				CiudadIf ciudadIf = (CiudadIf) ciudadIterator.next();
				
				tableModel = (DefaultTableModel) getTblCiudad().getModel();
				Vector<String> fila = new Vector<String>();
				getCiudadVector().add(ciudadIf);
				
				agregarColumnasTabla(ciudadIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblCiudad(), ciudad, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(CiudadIf ciudadIf, Vector<String> fila){
		
		fila.add(ciudadIf.getCodigo());
		fila.add(ciudadIf.getNombre());
		
		try {
			provinciaIf = SessionServiceLocator.getProvinciaSessionService().getProvincia(ciudadIf.getProvinciaId());
			fila.add(provinciaIf.getNombre());
			PaisIf pais = SessionServiceLocator.getPaisSessionService().getPais(provinciaIf.getPaisId());
			fila.add(pais.getNombre());
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
	}

	public Vector getCiudadVector() {
		return ciudadVector;
	}

	public void setCiudadVector(Vector ciudadVector) {
		this.ciudadVector = ciudadVector;
	}

	public int getCiudadSeleccionada() {
		return ciudadSeleccionada;
	}

	public void setCiudadSeleccionada(int ciudadSeleccionada) {
		this.ciudadSeleccionada = ciudadSeleccionada;
	}

	public CiudadIf getCiudadActualizadaIf() {
		return ciudadIf;
	}

	public void setCiudadActualizadaIf(CiudadIf ciudadIf) {
		this.ciudadIf = ciudadIf;
	}
	
	public static CiudadSessionService getCiudadSessionService() {
		try {
			return (CiudadSessionService) ServiceLocator
					.getService(ServiceLocator.CIUDADSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

}
