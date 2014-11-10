package com.spirit.facturacion.gui.model;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.table.TableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.facturacion.entity.PrecioHistoricoIf;
import com.spirit.facturacion.gui.panel.JPPrecioHistorico;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.util.FinderTable;

public class PrecioHistoricoModel extends JPPrecioHistorico {
	private static final long serialVersionUID = 3286076857787733674L;
	protected FinderTable finderTable;
	PrecioHistoricoIf preciohistorico;
	ArrayList lista;

   /*private static final int MAX_LONGITUD_PRECIO_ID = NUMBER(10) ;
   private static final int MAX_LONGITUD_FECHAINI = DATE ;
   private static final int MAX_LONGITUD_FECHAFIN = DATE ;
   private static final int MAX_LONGITUD_PRECIO = NUMBER(22) ;*/

	public PrecioHistoricoModel() {
            /*getTxtPrecioId().addKeyListener(new TextChecker(MAX_LONGITUD_PRECIO_ID));
            getTxtFechaini().addKeyListener(new TextChecker(MAX_LONGITUD_FECHAINI));
            getTxtFechafin().addKeyListener(new TextChecker(MAX_LONGITUD_FECHAFIN));
            getTxtPrecio().addKeyListener(new TextChecker(MAX_LONGITUD_PRECIO));*/
	    this.showSaveMode();
		new HotKeyComponent(this);
	}

	private ArrayList getModel(ArrayList listaPrecioHistorico) {
		ArrayList data = new ArrayList();
		Iterator it = listaPrecioHistorico.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			PrecioHistoricoIf bean = (PrecioHistoricoIf) it.next();
            fila.add(bean.getPrecioId()); 
            fila.add(bean.getFechaini()); 
            fila.add(bean.getFechafin()); 
            fila.add(bean.getPrecio()); 
		data.add(fila);
		}
		return data;
	}

	public void save() {

		/*if (validateFields()) {
			PrecioHistoricoData data = new PrecioHistoricoData();
			try {

				data.setPrecioId(this.getTxtPrecioId().getText().toUpperCase());
				data.setFechaini(this.getTxtFechaini().getText().toUpperCase());
				data.setFechafin(this.getTxtFechafin().getText().toUpperCase());
				data.setPrecio(this.getTxtPrecio().getText().toUpperCase());
				getPrecioHistoricoSessionService().addPrecioHistorico(data);
				this.clean();
				this.showSaveMode();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la infomacion!");
			}

		} else {

			SpiritAlert.createAlert("Se debe llenar todos los campos!");

		}*/
	}

	/*public static PrecioHistoricoSessionService getPrecioHistoricoSessionService() {
		try {
			return (PrecioHistoricoSessionService) ServiceLocator
					.getService(ServiceLocator.PRECIOHISTORICOSESSION_SERVICE);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	private Map buildQuery() {
		Map aMap = new HashMap();
		/*if (!("".equals(getTxtPrecioId().getText()))) {
			aMap.put("precioId", getTxtPrecioId().getText().toUpperCase());
		}
		if (!("".equals(getTxtFechaini().getText()))) {
			aMap.put("fechaini", getTxtFechaini().getText().toUpperCase());
		}
		if (!("".equals(getTxtFechafin().getText()))) {
			aMap.put("fechafin", getTxtFechafin().getText().toUpperCase());
		}
		if (!("".equals(getTxtPrecio().getText()))) {
			aMap.put("precio", getTxtPrecio().getText().toUpperCase());
		}*/
		return aMap;

	}

	public void find() {
		/*try {
			lista = (ArrayList) getPrecioHistoricoSessionService().findPrecioHistoricoByQuery(buildQuery());
			if (lista.size() != 0) {
				
				ArrayList headers = new ArrayList();

				headers.add("Codigo");
				headers.add("Nombre");
				ArrayList data = getModel(lista);

				finderTable = new FinderTable(data, headers);
				
				finderTable.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						getObjectFromFinderTable(e);
					}
				});
				
				finderTable.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						isFinderTableVisible = false;
					}
				});
				
				isFinderTableVisible = true;
			} else {
				SpiritAlert.createAlert("No se encontraron registros");
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la busqueda de informacion");
		}*/
	}

	public void delete() {
		/*try {
			getPrecioHistoricoSessionService().deletePrecioHistorico(preciohistorico.getId());
			this.clean();
			this.showSaveMode();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al eliminar informacion!");
		}*/
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void addDetail() {
		// TODO Auto-generated method stub
		
	}

	public void updateDetail() {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDetail() {
		
	}

	public void update() {

				/*preciohistorico.setPrecioId(this.getTxtPrecioId().getText());
				preciohistorico.setFechaini(this.getTxtFechaini().getText());
				preciohistorico.setFechafin(this.getTxtFechafin().getText());
				preciohistorico.setPrecio(this.getTxtPrecio().getText());

		try {
			getPrecioHistoricoSessionService().savePrecioHistorico(preciohistorico);
			this.showSaveMode();
			this.clean();

		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error al actualizar informacion!");
		}*/
	}

	public boolean isEmpty() {
		if (true
		    /*&& "".equals(this.getTxtPrecioId().getText())
		    && "".equals(this.getTxtFechaini().getText())
		    && "".equals(this.getTxtFechafin().getText())
		    && "".equals(this.getTxtPrecio().getText())*/
                ) {

			return true;
		} else {
			return false;
		}
	}

	public boolean validateFields() {

		if (false
		   /*|| "".equals(this.getTxtPrecioId().getText())
		    || "".equals(this.getTxtFechaini().getText())
		    || "".equals(this.getTxtFechafin().getText())
		    || "".equals(this.getTxtPrecio().getText())*/
	          ) {
			return false;
		} else {
			return true;
		}

	}

	public void clean() {

		/*this.getTxtPrecioId().setText("");
		this.getTxtFechaini().setText("");
		this.getTxtFechafin().setText("");
		this.getTxtPrecio().setText("");
		this.repaint();*/
	}

	public void showFindMode() {
		/*getTxtPrecioId().setEnabled(true);
		getTxtFechaini().setEnabled(true);
		getTxtFechafin().setEnabled(true);
		getTxtPrecio().setEnabled(true);*/

		
	}

	public void showSaveMode() {
		setSaveMode();
		/*getTxtPrecioId().setEnabled(true);
		getTxtFechaini().setEnabled(true);
		getTxtFechafin().setEnabled(true);
		getTxtPrecio().setEnabled(true);*/
	}

	public void showUpdateMode() {
		setUpdateMode();
		/*getTxtPrecioId().setEnabled(true);
		getTxtFechaini().setEnabled(true);
		getTxtFechafin().setEnabled(true);
		getTxtPrecio().setEnabled(true);*/
	}

	public FinderTable getFinderTable() {
		return finderTable;
	}

	public void setFinderTable(FinderTable table) {
		this.finderTable = table;
	}

	public void getObjectiFromFinderTable(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TableModel model = (TableModel) getFinderTable().getTable().getModel();
			int rowNumber = getFinderTable().getTable().getSelectedRow();
			getFinderTable().dispose();
			setUpdateMode();
			preciohistorico = (PrecioHistoricoIf) lista.get(rowNumber);
			/*getTxtPrecioId().setText(preciohistorico .getPrecioId().toUpperCase());
			getTxtFechaini().setText(preciohistorico .getFechaini().toUpperCase());
			getTxtFechafin().setText(preciohistorico .getFechafin().toUpperCase());
			getTxtPrecio().setText(preciohistorico .getPrecio().toUpperCase());*/
		}	
	}

	public boolean isFinderTableVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getListSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList getDataForTable(ArrayList list) {
		// TODO Auto-generated method stub
		return null;
	}

	public TableModel getTableModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList getListModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setComponentsOfPopup(int rowTablePopup) {
		// TODO Auto-generated method stub
		
	}

	public int getListSize(Map aMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getListSize(Long idFiltroBusqueda) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getListSize(Map aMap, Long idFiltroBusqueda) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
}
