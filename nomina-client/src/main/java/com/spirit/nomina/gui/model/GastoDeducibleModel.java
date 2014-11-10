package com.spirit.nomina.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

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
import com.spirit.nomina.entity.GastoDeducibleData;
import com.spirit.nomina.entity.GastoDeducibleIf;
import com.spirit.nomina.gui.panel.JPGastoDeducible;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class GastoDeducibleModel extends JPGastoDeducible {
	
	private static final long serialVersionUID = -5352647749640089270L;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 100;
	
	private ArrayList<GastoDeducibleIf> gastoDeducibleCollection = new ArrayList<GastoDeducibleIf>();
	private DefaultTableModel tableModel;
	private GastoDeducibleIf gastoDeducibleIf;
	
	public GastoDeducibleModel(){
		anchoColumnasTabla();
		initKeyListeners();
		setSorterTable(getTblGastoDeducible());
		getTblGastoDeducible().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showSaveMode();
		getTblGastoDeducible().addMouseListener(oMouseAdapterTblGastoDeducible);
		getTblGastoDeducible().addKeyListener(oKeyAdapterTblGastoDeducible);
		new HotKeyComponent(this);
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblGastoDeducible().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblGastoDeducible().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void iniciarComponentes(){
		
		
	}
	
	MouseListener oMouseAdapterTblGastoDeducible = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblGastoDeducible = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		int gastoDeducibleSeleccionado = getTblGastoDeducible().getSelectedRow(); 
		if ( gastoDeducibleSeleccionado != -1) {
			gastoDeducibleSeleccionado = getTblGastoDeducible().convertRowIndexToModel(gastoDeducibleSeleccionado); 
			gastoDeducibleIf = (GastoDeducibleIf)  gastoDeducibleCollection.get(gastoDeducibleSeleccionado);
			getTxtCodigo().setText(gastoDeducibleIf.getCodigo());
			getTxtNombre().setText(gastoDeducibleIf.getNombre());
			
			setUpdateMode();
		}
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		//cargarCmbDocumentos();
		cargarTabla();
		iniciarComponentes();
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection<GastoDeducibleIf> gastosDeducibles = SessionServiceLocator.getGastoDeducibleSessionService()
				.findGastoDeducibleByEmpresaId(Parametros.getIdEmpresa());
			
			tableModel = (DefaultTableModel) getTblGastoDeducible().getModel();
			for ( GastoDeducibleIf gastoDeducible : gastosDeducibles ) {
				
				Vector<String> fila = new Vector<String>();
				gastoDeducibleCollection.add(gastoDeducible);
				agregarColumnasTabla(gastoDeducible, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblGastoDeducible(), gastosDeducibles, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(GastoDeducibleIf gastoDeducible, Vector<String> fila){
		fila.add(gastoDeducible.getCodigo());
		fila.add(gastoDeducible.getNombre());
		
	}
	
	@Override
	public void refresh() {
		//cargarCmbDocumentos();
	}

	public void showFindMode() {		
		showSaveMode();
	}

	public void save() {
		try {
			if (validateFields()) {
				GastoDeducibleIf data = new GastoDeducibleData();

				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getGastoDeducibleSessionService().addGastoDeducible(data);
				SpiritAlert.createAlert("Gasto Deducible grabado con éxito",SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la información!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				
				gastoDeducibleIf.setCodigo(getTxtCodigo().getText());
				gastoDeducibleIf.setNombre(getTxtNombre().getText());
				gastoDeducibleIf.setEmpresaId(Parametros.getIdEmpresa());
				
				SessionServiceLocator.getGastoDeducibleSessionService().saveGastoDeducible(gastoDeducibleIf);
				gastoDeducibleIf = null;
				
				SpiritAlert.createAlert("Gasto Deducible actualizado con éxito",SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la información!",SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			SessionServiceLocator.getGastoDeducibleSessionService().deleteGastoDeducible(gastoDeducibleIf.getId());
			SpiritAlert.createAlert("Gasto Deducible eliminado con éxito",SpiritAlert.INFORMATION);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(
				"No se puede eliminar el registro, puede tener datos referenciados!"
				,SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void clean() {
		
		getTxtCodigo().setText("");
		getTxtNombre().setText(""); 
		gastoDeducibleCollection = null;
		gastoDeducibleCollection = new ArrayList<GastoDeducibleIf>();
		
		//Vacio la tabla
		limpiarTabla(getTblGastoDeducible());
	}

	public boolean validateFields() {
		
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		
		boolean codigoRepetido = false;
		
		try {
			Collection<GastoDeducibleIf> gastosDeducibles = SessionServiceLocator.getGastoDeducibleSessionService()
				.findGastoDeducibleByEmpresaId(Parametros.getIdEmpresa());
		
			for (GastoDeducibleIf gastoDeducible : gastosDeducibles){
				
				if (this.getMode() == SpiritMode.SAVE_MODE)
					if (getTxtCodigo().getText().equals(gastoDeducible.getCodigo()))				
						codigoRepetido = true;
				
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					if (getTxtCodigo().getText().equals(gastoDeducible.getCodigo())) 
						if (this.gastoDeducibleIf.getId().equals(gastoDeducible.getId()) == false)
							codigoRepetido = true;
			}
			
			if (codigoRepetido) {
				SpiritAlert.createAlert("El código del Gasto Decucible está duplicado !!"
						,SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			
			if ((("".equals(strCodigo)) || (strCodigo == null))) {
				SpiritAlert.createAlert("Debe ingresar un Código !!", SpiritAlert.WARNING);
				getTxtCodigo().grabFocus();
				return false;
			}
			if ((("".equals(strNombre)) || (strNombre == null))) {
				SpiritAlert.createAlert("Debe ingresar un Nombre !!", SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
			
			return true;
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return false;
	}
	
	public void showUpdateMode() {		
		setUpdateMode();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}	
}
