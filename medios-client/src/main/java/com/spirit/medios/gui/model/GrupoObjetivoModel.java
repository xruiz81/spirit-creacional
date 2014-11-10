package com.spirit.medios.gui.model;

import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.GrupoObjetivoData;
import com.spirit.medios.entity.GrupoObjetivoIf;
import com.spirit.medios.gui.panel.JPGrupoObjetivo;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;


public class GrupoObjetivoModel extends JPGrupoObjetivo{
	
	private static final long serialVersionUID = 7353414136278714729L;
	
	private static final int MAX_LONGITUD_CODIGO = 4;
	private static final int MAX_LONGITUD_NOMBRE = 50;
	
	private static final String NOMBRE_NIVEL_ALTO = "ALTO";
	private static final String NOMBRE_NIVEL_BAJO = "BAJO";
	private static final String NOMBRE_NIVEL_TODOS = "TODOS";
	private static final String NIVEL_ALTO = "A";
	private static final String NIVEL_BAJO = "B";
	private static final String NIVEL_TODOS = "T";
	
	private Vector grupoObjetivoVector = new Vector();
	private DefaultTableModel tableModel;
	protected GrupoObjetivoIf grupoObjetivoActualizadoIf;
	private int grupoObjetivoSeleccionado;

	public GrupoObjetivoModel(){
		setSorterTable(getTblGrupoObjetivo());
		initKeyListeners();
		anchoColumnasTabla();
		cargarCombo();
		showSaveMode();
		
		getTblGrupoObjetivo().addMouseListener(oMouseAdapterTblGrupoObjetivo);
		getTblGrupoObjetivo().addKeyListener(oKeyAdapterTblGrupoObjetivo);
				
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtGuayaquil().addKeyListener(new NumberTextField());
		getTxtQuito().addKeyListener(new NumberTextField());
		getTxtOtra().addKeyListener(new NumberTextField());
	}
	
	private void anchoColumnasTabla() {
		TableColumn anchoColumna = getTblGrupoObjetivo().getColumnModel().getColumn(0);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblGrupoObjetivo().getColumnModel().getColumn(1);
		anchoColumna.setPreferredWidth(230);
		anchoColumna = getTblGrupoObjetivo().getColumnModel().getColumn(2);
		anchoColumna.setPreferredWidth(40);
		anchoColumna = getTblGrupoObjetivo().getColumnModel().getColumn(3);
		anchoColumna.setPreferredWidth(50);
		anchoColumna = getTblGrupoObjetivo().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);	
		anchoColumna = getTblGrupoObjetivo().getColumnModel().getColumn(4);
		anchoColumna.setPreferredWidth(50);
	}
	
	public void cargarCombo(){
		getCmbNivel().addItem(NOMBRE_NIVEL_ALTO);
		getCmbNivel().addItem(NOMBRE_NIVEL_BAJO);
		getCmbNivel().addItem(NOMBRE_NIVEL_TODOS);
		getCmbNivel().setSelectedIndex(-1);
	}
	
	MouseListener oMouseAdapterTblGrupoObjetivo = new MouseAdapter() {
		public void mouseReleased(MouseEvent evt) {
			enableSelectedRowForUpdate(evt);
		}
    };
    
    KeyListener oKeyAdapterTblGrupoObjetivo = new KeyAdapter() {
		 public void keyReleased(KeyEvent evt) {
			 enableSelectedRowForUpdate(evt);
		 }
	};
	
	private void enableSelectedRowForUpdate(ComponentEvent evt) {
		//Obtengo la instancia del objeto seleccionado de la tabla
		if (((JTable)evt.getSource()).getSelectedRow() != -1) {
			int selectedRow = ((JTable)evt.getSource()).getSelectedRow();
			setGrupoObjetivoSeleccionado(((JTable)evt.getSource()).convertRowIndexToModel(selectedRow));
			grupoObjetivoActualizadoIf = (GrupoObjetivoIf)  getGrupoObjetivoVector().get(getGrupoObjetivoSeleccionado());

			getTxtCodigo().setText(grupoObjetivoActualizadoIf.getCodigo());
			getTxtNombre().setText(grupoObjetivoActualizadoIf.getNombre());
			
			if(grupoObjetivoActualizadoIf.getNivelSocioEconomico().equals(NIVEL_ALTO))
				getCmbNivel().setSelectedItem(NOMBRE_NIVEL_ALTO);
			else if(grupoObjetivoActualizadoIf.getNivelSocioEconomico().equals(NIVEL_BAJO))
				getCmbNivel().setSelectedItem(NOMBRE_NIVEL_BAJO);
			else if(grupoObjetivoActualizadoIf.getNivelSocioEconomico().equals(NIVEL_TODOS))
				getCmbNivel().setSelectedItem(NOMBRE_NIVEL_TODOS);
			
			getCmbNivel().repaint();
			getTxtGuayaquil().setText(grupoObjetivoActualizadoIf.getCiudad1().toString());
			getTxtQuito().setText(grupoObjetivoActualizadoIf.getCiudad2().toString());
			
			if(grupoObjetivoActualizadoIf.getCiudad3() != null) getTxtOtra().setText(grupoObjetivoActualizadoIf.getCiudad3().toString());
			else getTxtOtra().setText("");
			
			showUpdateMode();
		}
	}
	
	public void save() {
		try {
			if (validateFields()) {
				GrupoObjetivoData data = new GrupoObjetivoData();

				data.setCodigo(getTxtCodigo().getText());
				data.setNombre(getTxtNombre().getText());
				data.setEmpresaId(Parametros.getIdEmpresa());
				
				if(getCmbNivel().getSelectedItem().equals(NOMBRE_NIVEL_ALTO))
					data.setNivelSocioEconomico(NIVEL_ALTO);
				else if(getCmbNivel().getSelectedItem().equals(NOMBRE_NIVEL_BAJO))
					data.setNivelSocioEconomico(NIVEL_BAJO);
				else if(getCmbNivel().getSelectedItem().equals(NOMBRE_NIVEL_TODOS))
					data.setNivelSocioEconomico(NIVEL_TODOS);
				
				data.setCiudad1(BigDecimal.valueOf(Double.parseDouble(getTxtGuayaquil().getText())));
				data.setCiudad2(BigDecimal.valueOf(Double.parseDouble(getTxtQuito().getText())));
				if(!getTxtOtra().getText().equals("")) data.setCiudad3(BigDecimal.valueOf(Double.parseDouble(getTxtOtra().getText())));
								
				SessionServiceLocator.getGrupoObjetivoSessionService().addGrupoObjetivo(data);
				SpiritAlert.createAlert("Grupo Objetivo grabado con éxito", SpiritAlert.INFORMATION);
				showSaveMode();
			}
		}catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Error al guardar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void delete() {
		try {
			GrupoObjetivoIf grupoObjetivoIf = (GrupoObjetivoIf) getGrupoObjetivoVector().get(getGrupoObjetivoSeleccionado());
			SessionServiceLocator.getGrupoObjetivoSessionService().deleteGrupoObjetivo(grupoObjetivoIf.getId());
			SpiritAlert.createAlert("Grupo Objetivo eliminado con éxito", SpiritAlert.INFORMATION);
			showSaveMode();
			} 
		catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede eliminar el registro!", SpiritAlert.ERROR);
			showSaveMode();
		}
	}

	public void update() {
		try {
			if (validateFields()) {
				setGrupoObjetivoActualizadoIf((GrupoObjetivoIf) getGrupoObjetivoVector().get(getGrupoObjetivoSeleccionado()));
				
				getGrupoObjetivoActualizadoIf().setCodigo(getTxtCodigo().getText());
				getGrupoObjetivoActualizadoIf().setNombre(getTxtNombre().getText());
				getGrupoObjetivoActualizadoIf().setEmpresaId(Parametros.getIdEmpresa());
				
				if(getCmbNivel().getSelectedItem().equals(NOMBRE_NIVEL_ALTO))
					getGrupoObjetivoActualizadoIf().setNivelSocioEconomico(NIVEL_ALTO);
				else if(getCmbNivel().getSelectedItem().equals(NOMBRE_NIVEL_BAJO))
					getGrupoObjetivoActualizadoIf().setNivelSocioEconomico(NIVEL_BAJO);
				else if(getCmbNivel().getSelectedItem().equals(NOMBRE_NIVEL_TODOS))
					getGrupoObjetivoActualizadoIf().setNivelSocioEconomico(NIVEL_TODOS);
				
				getGrupoObjetivoActualizadoIf().setCiudad1(BigDecimal.valueOf(Double.parseDouble(getTxtGuayaquil().getText())));
				getGrupoObjetivoActualizadoIf().setCiudad2(BigDecimal.valueOf(Double.parseDouble(getTxtQuito().getText())));
				if(!getTxtOtra().getText().equals("")) getGrupoObjetivoActualizadoIf().setCiudad3(BigDecimal.valueOf(Double.parseDouble(getTxtOtra().getText())));
								
				SessionServiceLocator.getGrupoObjetivoSessionService().saveGrupoObjetivo(getGrupoObjetivoActualizadoIf());
				getGrupoObjetivoVector().setElementAt(getGrupoObjetivoActualizadoIf(), getGrupoObjetivoSeleccionado());
				setGrupoObjetivoActualizadoIf(null);
				
				SpiritAlert.createAlert("Grupo Objetivo actualizado con éxito", SpiritAlert.INFORMATION);	
				showSaveMode();
			}
		
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar la infomación!", SpiritAlert.ERROR);
		}
	}

	public void clean() {
		getTxtCodigo().setText("");
		getTxtNombre().setText("");
		getCmbNivel().setSelectedIndex(-1);
		getTxtGuayaquil().setText("");
		getTxtQuito().setText("");
		getTxtOtra().setText("");
		
		DefaultTableModel model = (DefaultTableModel) getTblGrupoObjetivo().getModel();
		for(int i= this.getTblGrupoObjetivo().getRowCount();i>0;--i)
			model.removeRow(i-1);
	}

	public void showSaveMode() {
		setSaveMode();
		clean();
		cargarTabla();
		
		getTxtCodigo().grabFocus();
	}
	
	private void cargarTabla() {
		try {
			Collection grupoObjetivo = SessionServiceLocator.getGrupoObjetivoSessionService().findGrupoObjetivoByEmpresaId(Parametros.getIdEmpresa());
			Iterator grupoObjetivoIterator = grupoObjetivo.iterator();
			
			if(!getGrupoObjetivoVector().isEmpty()){
				getGrupoObjetivoVector().removeAllElements();
			}
			
			while (grupoObjetivoIterator.hasNext()) {
				GrupoObjetivoIf grupoObjetivoIf = (GrupoObjetivoIf) grupoObjetivoIterator.next();
				
				tableModel = (DefaultTableModel) getTblGrupoObjetivo().getModel();
				Vector<String> fila = new Vector<String>();
				getGrupoObjetivoVector().add(grupoObjetivoIf);
				
				agregarColumnasTabla(grupoObjetivoIf, fila);
				
				tableModel.addRow(fila);
			}
			Utilitarios.scrollToCenter(getTblGrupoObjetivo(), grupoObjetivo, 0);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error al cargar la tabla", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
	
	private void agregarColumnasTabla(GrupoObjetivoIf grupoObjetivoIf, Vector<String> fila){
		fila.add(grupoObjetivoIf.getCodigo());
		fila.add(grupoObjetivoIf.getNombre());
		
		if(grupoObjetivoIf.getNivelSocioEconomico().equals(NIVEL_ALTO))
			fila.add(NOMBRE_NIVEL_ALTO);
		else if(grupoObjetivoIf.getNivelSocioEconomico().equals(NIVEL_BAJO))
			fila.add(NOMBRE_NIVEL_BAJO);
		else if(grupoObjetivoIf.getNivelSocioEconomico().equals(NIVEL_TODOS))
			fila.add(NOMBRE_NIVEL_TODOS);
		
		fila.add(grupoObjetivoIf.getCiudad1().toString());
		fila.add(grupoObjetivoIf.getCiudad2().toString());
		
		if(grupoObjetivoIf.getCiudad3() != null)
			fila.add(grupoObjetivoIf.getCiudad3().toString());
		else fila.add("");
	}

	public boolean validateFields() {
		String strCodigo = getTxtCodigo().getText();
		String strNombre = getTxtNombre().getText();
		
		Collection grupoObjetivo = null;
		boolean codigoRepetido = false;
		
		try {
			grupoObjetivo = SessionServiceLocator.getGrupoObjetivoSessionService().findGrupoObjetivoByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		Iterator grupoObjetivoIt = grupoObjetivo.iterator();
		
		while (grupoObjetivoIt.hasNext()) {
			GrupoObjetivoIf grupoObjetivoIf = (GrupoObjetivoIf) grupoObjetivoIt.next();
			
			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(grupoObjetivoIf.getCodigo()))				
					codigoRepetido = true;
			
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(grupoObjetivoIf.getCodigo())) 
					if (getGrupoObjetivoActualizadoIf().getId().equals(grupoObjetivoIf.getId()) == false)
						codigoRepetido = true;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Grupo Objetivo está duplicado !!", SpiritAlert.WARNING);
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
			getTxtCodigo().grabFocus();
			return false;
		}
		if (getCmbNivel().getSelectedIndex() == -1) {
			SpiritAlert.createAlert("Debe seleccionar un Nivel !!", SpiritAlert.WARNING);
			getCmbNivel().grabFocus();
			return false;
		}
		if ((("".equals(getTxtGuayaquil().getText())) || (getTxtGuayaquil().getText() == null))) {
			SpiritAlert.createAlert("Debe la cantidad para Guayaquil !!", SpiritAlert.WARNING);
			getTxtGuayaquil().grabFocus();
			return false;
		}
		if ((("".equals(getTxtQuito().getText())) || (getTxtQuito().getText() == null))) {
			SpiritAlert.createAlert("Debe la cantidad para Quito !!", SpiritAlert.WARNING);
			getTxtGuayaquil().grabFocus();
			return false;
		}		
				
		return true;
	}
	
	public int getGrupoObjetivoSeleccionado() {
		return grupoObjetivoSeleccionado;
	}

	public void setGrupoObjetivoSeleccionado(int grupoObjetivoSeleccionado) {
		this.grupoObjetivoSeleccionado = grupoObjetivoSeleccionado;
	}

	public Vector getGrupoObjetivoVector() {
		return grupoObjetivoVector;
	}

	public void setGrupoObjetivoVector(Vector grupoObjetivoVector) {
		this.grupoObjetivoVector = grupoObjetivoVector;
	}

	public GrupoObjetivoIf getGrupoObjetivoActualizadoIf() {
		return grupoObjetivoActualizadoIf;
	}

	public void setGrupoObjetivoActualizadoIf(
			GrupoObjetivoIf grupoObjetivoActualizadoIf) {
		this.grupoObjetivoActualizadoIf = grupoObjetivoActualizadoIf;
	}	
}
