package com.spirit.compras.gui.model;

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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCache;
import com.spirit.client.model.SpiritMode;
import com.spirit.compras.entity.GastoData;
import com.spirit.compras.entity.GastoIf;
import com.spirit.compras.gui.panel.JPGasto;
import com.spirit.compras.session.GastoSessionService;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.enums.TipoGasto;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class GastoModel extends JPGasto {

	private static final long serialVersionUID = 3257283617339029297L;	
	protected TableModel dataModel;
	GastoIf gastoIf;
	boolean isPopup = false;

	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private ArrayList<GastoIf> gastosCollection = new ArrayList<GastoIf>();
	private DefaultTableModel tableModel;

	public GastoModel() {
		initKeyListeners();
		setSorterTable(getTblGasto());
		iniciarComponentes();
		anchoColumnasTabla();
		getTblGasto().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.showSaveMode();
		getTblGasto().addMouseListener(oMouseAdapterTblPais);
		getTblGasto().addKeyListener(oKeyAdapterTblPais);
		
		new HotKeyComponent(this);
	}
	
	private void iniciarComponentes(){
		cargarCmbTipo();
	}

	private void cargarCmbTipo() {
		TipoGasto[] tipos = TipoGasto.values();
		DefaultComboBoxModel modeloCmbTipo = new DefaultComboBoxModel(tipos);
		getCmbTipo().setModel(modeloCmbTipo);
	}
	
	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblGasto().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(70);
		anchoColumna = getTblGasto().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(350);
	}
	
	MouseListener oMouseAdapterTblPais = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
	};
	
	KeyListener oKeyAdapterTblPais = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			selectedRow = ((JTable)evt.getSource()).convertRowIndexToModel(selectedRow); 
			gastoIf = gastosCollection.get(selectedRow);
			getTxtCodigo().setText(gastoIf.getCodigo());
			getTxtNombre().setText(gastoIf.getNombre());
			getCmbTipo().setSelectedItem(TipoGasto.getTipoGastoByLetra(gastoIf.getTipo()));
			showUpdateMode();
		} else
			gastoIf = null;
	}

	public void save() {
		try {
			if (validateFields()) {
				GastoData data = new GastoData();
				
				data.setCodigo(this.getTxtCodigo().getText());
				data.setNombre(this.getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				TipoGasto tipoGasto = (TipoGasto) getCmbTipo().getSelectedItem();
				if ( tipoGasto != null )
					data.setTipo(tipoGasto.getLetra());

				SessionServiceLocator.getGastoSessionService().addGasto(data);
				SpiritAlert.createAlert("Gasto guardado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("pais",null);
				showSaveMode();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la informacin!",SpiritAlert.ERROR);
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				
				gastoIf.setCodigo(getTxtCodigo().getText());
				gastoIf.setNombre(getTxtNombre().getText());
				TipoGasto tipoGasto = (TipoGasto) getCmbTipo().getSelectedItem();
				if ( tipoGasto != null )
					gastoIf.setTipo(tipoGasto.getLetra());
				
				SessionServiceLocator.getGastoSessionService().saveGasto(gastoIf);
				
				SpiritAlert.createAlert("Gasto actualizado con éxito",SpiritAlert.INFORMATION);
				SpiritCache.setObject("pais",null);
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al actualizar la informacin!",SpiritAlert.ERROR);
		}		
	}

	public void delete() {
		try {
			SessionServiceLocator.getGastoSessionService().deleteGasto(gastoIf.getId());
			SpiritAlert.createAlert("Gasto eliminado con éxito",SpiritAlert.INFORMATION);
			SpiritCache.setObject("pais",null);
			showSaveMode();
		} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	private void agregarColumnasTabla(GastoIf gastoIf, Vector<Object> fila){
		fila.add(gastoIf.getCodigo());
		fila.add(gastoIf.getNombre());		
		fila.add( TipoGasto.getTipoGastoByLetra( gastoIf.getTipo() ) );
	}
	
	private void cargarTabla() {
		try {			
			Collection<GastoIf> gastos = SessionServiceLocator.getGastoSessionService()
				.findGastoByEmpresaId(Parametros.getIdEmpresa()); 
			
			gastosCollection = null;
			gastosCollection = new ArrayList<GastoIf>();
			
			for (GastoIf g : gastos) {
				
				tableModel = (DefaultTableModel) getTblGasto().getModel();
				Vector<Object> fila = new Vector<Object>();
				gastosCollection.add(g);
				
				agregarColumnasTabla(g, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblGasto(), gastos, 0);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
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
		TipoGasto tg = (TipoGasto) getCmbTipo().getSelectedItem();
		
		Collection<GastoIf> gastos = null;
		boolean codigoRepetido = false;
		
		try {
			gastos = SessionServiceLocator.getGastoSessionService().findGastoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
			return false;
		}

		for (GastoIf g: gastos){
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(g.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(g.getCodigo())) 
					if (gastoIf.getId().equals(g.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Gasto está duplicado !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Gasto !!",SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Gasto !!",SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if ( tg == null ){
			SpiritAlert.createAlert("Debe elegir un tipo para el Gasto !!",SpiritAlert.WARNING);
			getCmbTipo().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		
		gastoIf = null;
		
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		getCmbTipo().setSelectedItem(null);
		
		//Vacio la tabla
		limpiarTabla(getTblGasto());
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
		getTblGasto().grabFocus();
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}

 
}
