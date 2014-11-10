package com.spirit.inventario.gui.model;

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
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.inventario.entity.FamiliaGenericoData;
import com.spirit.inventario.entity.FamiliaGenericoIf;
import com.spirit.inventario.gui.panel.JPFamiliaGenerico;
import com.spirit.inventario.session.FamiliaGenericoSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class FamiliaGenericoModel extends JPFamiliaGenerico {

	private static final int MAX_LONGITUD_CODIGO = 10;
	private static final int MAX_LONGITUD_NOMBRE = 40;
	
	private DefaultTableModel tableModel;
	private Vector familiaGenericoVector = new Vector();
	private int familiaGenericoSeleccionada;
	private FamiliaGenericoIf familiaGenericoActualizadaIf;
	
	public FamiliaGenericoModel() {
		initKeyListeners();
		setSorterTable(getTblFamiliaGenerico());
		anchoColumnasTabla();
		getTblFamiliaGenerico().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblFamiliaGenerico().addMouseListener(oMouseAdapterTblFamiliaGenerico);
		getTblFamiliaGenerico().addKeyListener(oKeyAdapterTblFamiliaGenerico);
		new HotKeyComponent(this);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblFamiliaGenerico().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblFamiliaGenerico().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(270);
	}
	
	MouseListener oMouseAdapterTblFamiliaGenerico = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			 enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblFamiliaGenerico = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setFamiliaGenericoSeleccionada(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			familiaGenericoActualizadaIf = (FamiliaGenericoIf) getFamiliaGenericoVector().get(getFamiliaGenericoSeleccionada());
			getTxtCodigo().setText(getFamiliaGenericoActualizadaIf().getCodigo());
			getTxtNombre().setText(getFamiliaGenericoActualizadaIf().getNombre());
			getTxtCodigo().setEnabled(false);
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				FamiliaGenericoData data = new FamiliaGenericoData();
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				SessionServiceLocator.getFamiliaGenericoSessionService().addFamiliaGenerico(data);
				this.showSaveMode();
				SpiritAlert.createAlert("Familia de genérico guardada con éxito", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al guardar la información!", SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		try {	
			if (validateFields()) {
				setFamiliaGenericoActualizadaIf((FamiliaGenericoIf) getFamiliaGenericoVector().get(getFamiliaGenericoSeleccionada()));
				getFamiliaGenericoActualizadaIf().setNombre(getTxtNombre().getText());
				getFamiliaGenericoVector().setElementAt(getFamiliaGenericoActualizadaIf(), getFamiliaGenericoSeleccionada());
				SessionServiceLocator.getFamiliaGenericoSessionService().saveFamiliaGenerico(getFamiliaGenericoActualizadaIf());
				setFamiliaGenericoActualizadaIf(null);
				this.showSaveMode();
				SpiritAlert.createAlert("Familia de genérico actualizada con éxito", SpiritAlert.INFORMATION);
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			FamiliaGenericoIf familiaGenericoIf = (FamiliaGenericoIf) getFamiliaGenericoVector().get(getFamiliaGenericoSeleccionada());
			SessionServiceLocator.getFamiliaGenericoSessionService().deleteFamiliaGenerico(familiaGenericoIf.getId());
			this.showSaveMode();
			SpiritAlert.createAlert("Familia de genérico eliminada con éxito", SpiritAlert.INFORMATION);
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			this.showSaveMode();
		}
	}

	public void clean() {
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");

		//Vacio la tabla
		DefaultTableModel model = (DefaultTableModel) getTblFamiliaGenerico().getModel();
		
		for(int i= this.getTblFamiliaGenerico().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		clean();
		cargarTabla();		
		getTxtCodigo().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

	private void cargarTabla() {
		try {
			if (!SessionServiceLocator.getFamiliaGenericoSessionService().findFamiliaGenericoByEmpresaId(Parametros.getIdEmpresa()).isEmpty()) {
				Collection familiasGenericos = SessionServiceLocator.getFamiliaGenericoSessionService().findFamiliaGenericoByEmpresaId(Parametros.getIdEmpresa()); 
				Iterator familiasGenericosIterator = familiasGenericos.iterator();
				
				if(!getFamiliaGenericoVector().isEmpty()){
					getFamiliaGenericoVector().removeAllElements();
				}
				
				while (familiasGenericosIterator.hasNext()) {
					FamiliaGenericoIf familiaGenericoIf = (FamiliaGenericoIf) familiasGenericosIterator.next();
					
					tableModel = (DefaultTableModel) getTblFamiliaGenerico().getModel();
					Vector<String> fila = new Vector<String>();
					getFamiliaGenericoVector().add(familiaGenericoIf);
					
					agregarColumnasTabla(familiaGenericoIf, fila);
					
					tableModel.addRow(fila);
				}
				Utilitarios.scrollToCenter(getTblFamiliaGenerico(), familiasGenericos, 0);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(FamiliaGenericoIf familiaGenericoIf, Vector<String> fila) {	
		fila.add(familiaGenericoIf.getCodigo());
		fila.add(familiaGenericoIf.getNombre());
	}
	
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();

		Collection familiasGenericos = null;
		boolean codigoRepetido = false;
		
		try {
			familiasGenericos = SessionServiceLocator.getFamiliaGenericoSessionService().findFamiliaGenericoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator familiasGenericosIt = familiasGenericos.iterator();
		
		while (familiasGenericosIt.hasNext()) {
			FamiliaGenericoIf familiaGenericoIf = (FamiliaGenericoIf) familiasGenericosIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(familiaGenericoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(familiaGenericoIf.getCodigo())) 
					if (familiaGenericoActualizadaIf.getId().equals(familiaGenericoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código de la Familia de Genérico está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para la Familia de Genérico !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para la Familia de Genérico !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public Vector getFamiliaGenericoVector() {
		return this.familiaGenericoVector;
	}
	
	public void setFamiliaGenericoVector(Vector familiaGenericoVector) {
		this.familiaGenericoVector = familiaGenericoVector;
	}
	
	public int getFamiliaGenericoSeleccionada() {
		return this.familiaGenericoSeleccionada;
	}
	
	public void setFamiliaGenericoSeleccionada(int familiaGenericoSeleccionada) {
		this.familiaGenericoSeleccionada = familiaGenericoSeleccionada;
	}
	
	public FamiliaGenericoIf getFamiliaGenericoActualizadaIf() {
		return this.familiaGenericoActualizadaIf;
	}
	
	public void setFamiliaGenericoActualizadaIf(FamiliaGenericoIf familiaGenericoActualizadaIf) {
		this.familiaGenericoActualizadaIf = familiaGenericoActualizadaIf;
	}
	
	
}
