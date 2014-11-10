package com.spirit.medios.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.CorporacionIf;
import com.spirit.crm.gui.criteria.CorporacionCriteria;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.ClienteCriteria;
import com.spirit.general.gui.criteria.EmpleadoCriteria;
import com.spirit.medios.entity.CampanaIf;
import com.spirit.medios.entity.ComercialData;
import com.spirit.medios.entity.ComercialIf;
import com.spirit.medios.entity.DerechoProgramaIf;
import com.spirit.medios.entity.ProductoClienteData;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.gui.criteria.CampanaCriteria;
import com.spirit.medios.gui.panel.JPProductoComercial;
import com.spirit.util.NumberTextField;
import com.spirit.util.TextChecker;

public class ProductoComercialModel extends JPProductoComercial {
	
	private static final long serialVersionUID = -6633355195363191951L;
	private CampanaCriteria campanaCriteria;
	private EmpleadoCriteria empleadoCriteria;
	private JDPopupFinderModel popupFinder;
	private ClienteCriteria clienteCriteria;
	private CorporacionCriteria corporacionCriteria;
	protected CorporacionIf corporacionIf;
	protected ClienteIf clienteIf;
	protected EmpleadoIf creativoIf,ejecutivoIf;
	protected DerechoProgramaIf derechoProgramaIf;
	protected CampanaIf campanaIf;
	
	ArrayList lista;
	Vector productoClienteColeccion = new Vector();
	DefaultTableModel modelProductoCliente;
	Vector comercialClienteColeccion = new Vector();
	DefaultTableModel modelComercialCliente;

	final JPopupMenu  popupMenuProductoCliente = new JPopupMenu();
	final JPopupMenu  popupMenuComercialCliente = new JPopupMenu();

	private static final int MAX_LONGITUD_CODIGO_PRODUCTO = 3;
	private static final int MAX_LONGITUD_NOMBRE_PRODUCTO = 30;
	private static final int MAX_LONGITUD_CODIGO_COMERCIAL = 3;
	private static final int MAX_LONGITUD_NOMBRE_COMERCIAL= 40;
	private static final int MAX_LONGITUD_DESCRIPCION = 40 ;
	private static final int MAX_LONGITUD_VERSION = 2 ;
	private static final int MAX_LONGITUD_DURACION = 3 ;
   
	private static final String NOMBRE_ESTADO_ACTIVO = "ACTIVO";
	private static final String NOMBRE_ESTADO_INACTIVO = "INACTIVO";
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final String CODIGO_TIPO_CLIENTE = "CL";
	
	private int selectedRowTblProducto = -1;
	private int selectedRowTblComercial = -1;

	public ProductoComercialModel() {
		initKeyListeners();
	    this.showSaveMode();    
	    initListeners();
		new HotKeyComponent(this);
	}

	private void initKeyListeners() {
		getTxtCodigoProducto().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_PRODUCTO));
        getTxtNombreProducto().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_PRODUCTO));
        getTxtCodigoComercial().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO_COMERCIAL));
        getTxtNombreComercial().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE_COMERCIAL));
        getTxtDescripcionComercial().addKeyListener(new TextChecker(MAX_LONGITUD_DESCRIPCION));
        getTxtVersionComercial().addKeyListener(new TextChecker(MAX_LONGITUD_VERSION));
    	getTxtDuracionComercial().addKeyListener(new TextChecker(MAX_LONGITUD_DURACION));
    	getTxtDuracionComercial().addKeyListener (new NumberTextField());
	}
	
	public void initListeners(){
		// Manejo los eventos de Buscar Corporación
		getBtnBuscarCorporacion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				corporacionCriteria = new CorporacionCriteria("Corporaciones", Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
									corporacionCriteria,
									JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					corporacionIf = (CorporacionIf) popupFinder.getElementoSeleccionado();
					getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
					getTxtCliente().setText("");
				}
			}
		});
		
		// Manejo los eventos de Buscar Cliente
		getBtnBuscarCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				Long idCorporacion = 0L;
				
				if (corporacionIf != null)
					idCorporacion = corporacionIf.getId();

				clienteCriteria = new ClienteCriteria("Clientes",idCorporacion,CODIGO_TIPO_CLIENTE);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
									clienteCriteria,
									JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado() != null ){
					clienteIf = (ClienteIf) popupFinder.getElementoSeleccionado();
					getTxtCliente().setText(clienteIf.getIdentificacion() + " - " + clienteIf.getNombreLegal());
					if (corporacionIf == null) {
						try {
							corporacionIf = SessionServiceLocator.getCorporacionSessionService().getCorporacion(clienteIf.getCorporacionId());
							getTxtCorporacion().setText(corporacionIf.getCodigo() + " - " + corporacionIf.getNombre());
						} catch (GenericBusinessException e) {
							SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
							e.printStackTrace();
						}
					}
					getBtnBuscarCampana().setEnabled(true);
					if(getMode()== SpiritMode.FIND_MODE)
						find();
				}
			}
		});
		
		// Manejo los eventos de Buscar Creativo
		getBtnBuscarCreativo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria("Creativos",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										empleadoCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					creativoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtCreativoProducto().setText(creativoIf.getCodigo() + " - " + creativoIf.getNombres() + ", " + creativoIf.getApellidos());
					getBtnBorrarCreativo().setEnabled(true);
				}
			}
		});
		
		getBtnBorrarCreativo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtCreativoProducto().setText("");
				getBtnBorrarCreativo().setEnabled(false);
				creativoIf = null;
			}
		});
		
		// Manejo los eventos de Buscar Ejecutivo
		getBtnBuscarEjecutivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empleadoCriteria = new EmpleadoCriteria(
			 			"Ejecutivos",Parametros.getIdEmpresa());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empleadoCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					ejecutivoIf = (EmpleadoIf) popupFinder.getElementoSeleccionado();
					getTxtEjecutivoProducto().setText(ejecutivoIf.getCodigo() + " - " + ejecutivoIf.getNombres() + ", " + ejecutivoIf.getApellidos());
					getBtnBorrarEjecutivo().setEnabled(true);
				}
			}
		});
		
		getBtnBorrarEjecutivo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				getTxtEjecutivoProducto().setText("");
				getBtnBorrarEjecutivo().setEnabled(false);
				ejecutivoIf = null;
			}
		});
		
		getBtnBuscarCampana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				campanaCriteria = new CampanaCriteria("Campañas",clienteIf.getId());
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(),
										campanaCriteria,
										JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					campanaIf = (CampanaIf) popupFinder.getElementoSeleccionado();
					getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
				}
			}
		});
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla de producto
	    JMenuItem itemEliminarProductoCliente = new JMenuItem("Eliminar");
	    popupMenuProductoCliente.add(itemEliminarProductoCliente);
	    //Añado el listener de menupopup
	    itemEliminarProductoCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		eliminarProductoCliente();
	    	}
	    });
	    
	    //Listenner de la tabla de producto cliente
	    getTblProductoCliente().addMouseListener(new MouseAdapter() {
	    	public void mouseReleased(MouseEvent evt) {
	    		if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblProductoCliente().getModel().getRowCount()>0) {
	            	popupMenuProductoCliente.show(evt.getComponent(), evt.getX(), evt.getY());
	            } else if (getTblProductoCliente().getSelectedRow()!=-1) {
	            	try {
	            		//Extraigo el objeto producto cliente de la coleccion segun el registro seleccionado de la tabla
	            		ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(getTblProductoCliente().getSelectedRow());
	            		setSelectedRowTblProducto(getTblProductoCliente().getSelectedRow());
		            	
		            	//Muestro los datos en los texte field
		            	getTxtCodigoProducto().setText(productoClienteTemp.getCodigo());
						getTxtNombreProducto().setText(productoClienteTemp.getNombre());
		            	
		            	//Muestro el estado
		            	if ("ACTIVO".equals(getTblProductoCliente().getModel().getValueAt(getTblProductoCliente().getSelectedRow(),2).toString()))
		    				getCmbEstadoProducto().setSelectedItem("ACTIVO");
		    			else
		    				getCmbEstadoProducto().setSelectedItem("INACTIVO");
		            	
		            	//Creo un mapa para buscar al creativo por codigo, nombre y apellidos
		            	String creativo = getTblProductoCliente().getModel().getValueAt(getTblProductoCliente().getSelectedRow(),3).toString();
		            	Map creativoMap = new HashMap();	            
		            	creativoMap.put("codigo", creativo.split(" - ")[0]);
		            	creativoMap.put("nombres", creativo.split(" - ")[1].split(", ")[0]);
		            	creativoMap.put("apellidos", creativo.split(" - ")[1].split(", ")[1]);
		            	creativoMap.put("empresaId", Parametros.getIdEmpresa());
						creativoIf = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(creativoMap).iterator().next();
						//Mando a mostrar la informacion del creativo
						getTxtCreativoProducto().setText(creativoIf.getCodigo() + " - " + creativoIf.getNombres() + ", " + creativoIf.getApellidos());
						
		            	//Creo un mapa para buscar al ejecutivo por codigo, nombre y apellidos
		            	String ejecutivo = getTblProductoCliente().getModel().getValueAt(getTblProductoCliente().getSelectedRow(),4).toString();
		            	Map ejecutivoMap = new HashMap();	            
		            	ejecutivoMap.put("codigo", ejecutivo.split(" - ")[0]);
		            	ejecutivoMap.put("nombres", ejecutivo.split(" - ")[1].split(", ")[0]);
		            	ejecutivoMap.put("apellidos", ejecutivo.split(" - ")[1].split(", ")[1]);
						ejecutivoMap.put("empresaId", Parametros.getIdEmpresa());
						ejecutivoIf = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().findEmpleadoByQuery(ejecutivoMap).iterator().next();
						//Mando a mostrar la informacion del ejecutivo
						getTxtEjecutivoProducto().setText(ejecutivoIf.getCodigo() + " - " + ejecutivoIf.getNombres() + ", " + ejecutivoIf.getApellidos());

					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
	    	}
	    });
		
		getBtnAgregarProductoCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarProductoCliente();			
			}
		});
			
		getBtnActualizarProductoCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarProductoCliente();			
			}
		});
		
		getBtnEliminarProductoCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarProductoCliente();			
			}
		});
		
		//Opcion Que Permite Borrar un regitsro deseado de la tabla de comercial
	    JMenuItem itemEliminarComercialCliente = new JMenuItem("Eliminar");
	    popupMenuComercialCliente.add(itemEliminarComercialCliente);
	    //Añado el listener de menupopup
	    itemEliminarComercialCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent evento) {
	    		eliminarComercialCliente();
	    	}
	    });
	    
	    //Listener de la tabla de comercial cliente
	    getTblComercialCliente().addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent evt) {
	            if (getTblComercialCliente().getSelectedRow()!=-1) {
	            	try {
	            		//Extraigo el objeto comercial cliente de la coleccion segun el registro seleccionado de la tabla
	            		ComercialIf comercialTemp= (ComercialIf) comercialClienteColeccion.get(getTblComercialCliente().getSelectedRow());
	            		setSelectedRowTblComercial(getTblComercialCliente().getSelectedRow());
		            	
		            	for(int i=0;i < getCmbDerechoProgramaComercial().getItemCount();i++){
	    					DerechoProgramaIf bean = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getItemAt(i);
							if(bean.getNombre().compareTo(getTblComercialCliente().getModel().getValueAt(getTblComercialCliente().getSelectedRow(),2).toString())==0)
								getCmbDerechoProgramaComercial().setSelectedItem(bean);
								getCmbDerechoProgramaComercial().repaint();
						}
		            	
		            	//Muestro los datos en los texte field
		            	getTxtCodigoComercial().setText(comercialTemp.getCodigo());
						getTxtNombreComercial().setText(comercialTemp.getNombre());
		            	getTxtDescripcionComercial().setText(comercialTemp.getDescripcion());
						getTxtVersionComercial().setText(comercialTemp.getVersion());
						getTxtDuracionComercial().setText(comercialTemp.getDuracion());
		            	
		            	if(comercialTemp.getEstado().equals("A")){
		            		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_ACTIVO);
		            	}else{
		            		getCmbEstado().setSelectedItem(NOMBRE_ESTADO_INACTIVO);
		            	}
						
		            	//Creo un mapa para buscar la campana por codigo, nombre
		            	String campana = getTblComercialCliente().getModel().getValueAt(getTblComercialCliente().getSelectedRow(),4).toString();
		            	Map campanaMap = new HashMap();	            
		            	campanaMap.put("codigo", campana.split(" - ")[0]);
		            	campanaMap.put("nombre", campana.split(" - ")[1]);
		            	campanaMap.put("clienteId", clienteIf.getId());
		            	
						campanaIf = (CampanaIf) SessionServiceLocator.getCampanaSessionService().findCampanaByQuery(campanaMap).iterator().next();
						//Mando a mostrar la informacion de la campana
						getTxtCampanaComercial().setText(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
						
					} catch (GenericBusinessException e) {
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
						e.printStackTrace();
					}
				}
	    	}
	        public void mouseReleased(MouseEvent evt) {
	            if ((evt.isPopupTrigger() || evt.getButton() == MouseEvent.BUTTON3) && getTblComercialCliente().getModel().getRowCount()>0) {
	            	popupMenuComercialCliente.show(evt.getComponent(), evt.getX(), evt.getY());
	            }
	        }
	    });
		
		getBtnAgregarComercialCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				agregarComercialCliente();			
			}
		});

		getBtnActualizarComercialCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				actualizarComercialCliente();			
			}
		});
		
		getBtnEliminarComercialCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				eliminarComercialCliente();			
			}
		});
	}
	
	private void agregarProductoCliente() {
		try
		{	
			setSelectedRowTblProducto(-1);
			boolean isExisteProducto = false;
			
			if (validateFieldsProductoCliente()) {
				// Si la coleccion tiene algun elemento
				if(productoClienteColeccion.size()!=0){
					//Recorro la coleccion de resumenes
	    			for(int i=0;i<productoClienteColeccion.size();i++){
	    				ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(i);

	    				//Si el producto cargado ya esta en lista, entons muestro un mensaje de error
	    				if(productoClienteTemp.getCodigo().equals(getTxtCodigoProducto().getText()) 
	    				||(productoClienteTemp.getEstado().equals(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1)) 
	    				   && productoClienteTemp.getNombre().equals(getTxtNombreProducto().getText()) 
	    				   && productoClienteTemp.getCreativoId().equals(creativoIf.getId()) 
	    				   && productoClienteTemp.getEjecutivoId().equals(ejecutivoIf.getId()))){
	    						isExisteProducto = true;
	    						break;
	    				}
	    			}
	    		}
				
				modelProductoCliente =  (DefaultTableModel) getTblProductoCliente().getModel();
				
				Vector<String> filaProductoCliente = new Vector<String>();

				//Veo que todos los campos hayan sido llenados

				//Si el Producto no existe lo inserto
				if(isExisteProducto==false) {
					ProductoClienteData data = new ProductoClienteData();
					
					//Seteo los datos del objeto resumen mes
					data.setCodigo(getTxtCodigoProducto().getText());
					data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
					data.setNombre(getTxtNombreProducto().getText());
					data.setCreativoId(creativoIf.getId());
					data.setEjecutivoId(ejecutivoIf.getId());
					
					productoClienteColeccion.add(data);

					// Agregra los valores al registro que va  ser añadido  a la tabla.
					filaProductoCliente.add(getTxtCodigoProducto().getText());
					filaProductoCliente.add(getTxtNombreProducto().getText());
					filaProductoCliente.add(getCmbEstadoProducto().getSelectedItem().toString());
					filaProductoCliente.add(creativoIf.getCodigo() + " - " + creativoIf.getNombres() + ", " + creativoIf.getApellidos());
					filaProductoCliente.add(ejecutivoIf.getCodigo() + " - " + ejecutivoIf.getNombres() + ", " + ejecutivoIf.getApellidos());
					
					modelProductoCliente.addRow(filaProductoCliente);
					cleanProducto();
				} else {
					SpiritAlert.createAlert("El Producto ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					cleanProducto();
				}
			}
		}
		catch(Exception e)
		{
			SpiritAlert.createAlert(" No se pudo ingresar el Producto Cliente !!!"
					,SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}

	private void cleanProducto() {
		if (getCmbEstadoProducto().getItemCount() > 0)
			getCmbEstadoProducto().setSelectedIndex(0);
		getTxtCodigoProducto().setText("");
		getTxtNombreProducto().setText("");
		getTxtCreativoProducto().setText("");
		getTxtEjecutivoProducto().setText("");
		getTxtCodigoProducto().grabFocus();
		creativoIf = null;
		ejecutivoIf = null;
	}
	
	private void actualizarProductoCliente() {
		try
		{	
			boolean isExisteProducto = false;

			//Si la coleccion tiene algun elemento
			if(productoClienteColeccion.size()!=0){
				//Recorro la coleccion de resumenes
    			for(int i=0;i<productoClienteColeccion.size();i++){
    				ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(i);

    				//Si el producto cargado ya esta en lista, entonces muestro un mensaje de error
    				if(productoClienteTemp.getEstado().equals(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1)) && productoClienteTemp.getNombre().equals(getTxtNombreProducto().getText()) && productoClienteTemp.getCreativoId().equals(creativoIf.getId()) && productoClienteTemp.getEjecutivoId().equals(ejecutivoIf.getId())){
    						isExisteProducto = true;
    						break;
    				}
    			}
    		}
			
			modelProductoCliente =  (DefaultTableModel) getTblProductoCliente().getModel();

			//Veo que todos los campos hayan sido llenados
			if (validateFieldsProductoCliente()) {
				//Si el Producto no existe lo actualizo
				if(isExisteProducto==false){
					//Creo el obejto donde se va a gurdar el regitro
					ProductoClienteIf data = (ProductoClienteIf) productoClienteColeccion.get(getTblProductoCliente().getSelectedRow());
					
					//Seteo los datos del objeto resumen mes
					data.setCodigo(getTxtCodigoProducto().getText());
					data.setEstado(getCmbEstadoProducto().getSelectedItem().toString().substring(0, 1));
					data.setNombre(getTxtNombreProducto().getText());
					data.setCreativoId(creativoIf.getId());
					data.setEjecutivoId(ejecutivoIf.getId());
					
					//Actualizar en la coleccion de resumenPlanMedioColeccion el registro que fue cambiado
					productoClienteColeccion.set(getTblProductoCliente().getSelectedRow(),data);
					
					//Actualizo en la tablaProductoCliente
					modelProductoCliente.setValueAt(getTxtCodigoProducto().getText(),getTblProductoCliente().getSelectedRow(),0);
					modelProductoCliente.setValueAt(getTxtNombreProducto().getText(),getTblProductoCliente().getSelectedRow(),1);
					modelProductoCliente.setValueAt(getCmbEstadoProducto().getSelectedItem().toString(),getTblProductoCliente().getSelectedRow(),2);
					modelProductoCliente.setValueAt(creativoIf.getCodigo() + " - " + creativoIf.getNombres() + ", " + creativoIf.getApellidos(),getTblProductoCliente().getSelectedRow(),3);
					modelProductoCliente.setValueAt(ejecutivoIf.getCodigo() + " - " + ejecutivoIf.getNombres() + ", " + ejecutivoIf.getApellidos(),getTblProductoCliente().getSelectedRow(),4);

					cleanProducto();
				}
				else{
					SpiritAlert.createAlert("El Producto ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					cleanProducto();
				}
			}
		}
		catch(Exception e)
		{
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla Producto Cliente !!!"
					,SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}
	
	private void eliminarProductoCliente() {
		if (getTblProductoCliente().getSelectedRow()!=-1)
		{
			try {
    			//Extraigo el objeto producto cliente de la coleccion segun el registro seleccionado de la tabla
            	ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(getTblProductoCliente().getSelectedRow());

				
				//Veo si el Producto cliente escogido existe en la base o no...
				if(productoClienteTemp.getId()!=null){
					SessionServiceLocator.getProductoClienteSessionService().deleteProductoCliente(productoClienteTemp.getId());
				}
    			
    			//Elimino el registro de la coleccion y de la Tabla
    			productoClienteColeccion.remove(getTblProductoCliente().getSelectedRow());
				modelProductoCliente.removeRow(getTblProductoCliente().getSelectedRow());
			
				cleanProducto();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al eliminar registro !!!" ,SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		else {
			SpiritAlert.createAlert("Debe elegir el registro de la tabla a eliminar !!!"
					,SpiritAlert.WARNING);
		}
	}
	
	private void agregarComercialCliente() {
		try
		{	
			setSelectedRowTblComercial(-1);
			boolean isExisteComercial = false;
			
			if (validateFieldsComercialCliente()) {
			
				//Extraigo el objeto comercial del combo
				DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getSelectedItem();

				//Si la coleccion tiene algun elemento
				if(comercialClienteColeccion.size()!=0){
					//Recorro la coleccion de comerciales
	    			for(int i=0;i<comercialClienteColeccion.size();i++){
	    				ComercialIf comercialClienteTemp = (ComercialIf) comercialClienteColeccion.get(i);

	    				//Si el comercial cargado ya esta en lista, entons muestro un mensaje de error
	    				if(comercialClienteTemp.getCodigo().equals(getTxtCodigoComercial().getText()) || (comercialClienteTemp.getDerechoprogramaId().equals(derechoPrograma.getId()) && comercialClienteTemp.getNombre().equals(getTxtNombreComercial().getText()) && comercialClienteTemp.getDescripcion().equals(getTxtDescripcionComercial().getText()) && comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && comercialClienteTemp.getDuracion().equals(getTxtDuracionComercial().getText()))){
	    						isExisteComercial = true;
	    						break;
	    				}
	    			}
	    		}
				
				modelComercialCliente =  (DefaultTableModel) getTblComercialCliente().getModel();
				
				Vector<String> filaComercialCliente = new Vector<String>();

				//Si el Comercial no existe lo inserto
				if(isExisteComercial==false){
					ComercialData data = new ComercialData();
					
					//Seteo los datos del objeto resumen mes
					data.setCodigo(getTxtCodigoComercial().getText());
					data.setNombre(getTxtNombreComercial().getText());
					data.setEmpresaId(Parametros.getIdEmpresa());
					data.setDerechoprogramaId(derechoPrograma.getId());
					data.setDescripcion(getTxtDescripcionComercial().getText());
					data.setCampanaId(campanaIf.getId());
					data.setVersion(getTxtVersionComercial().getText());
					data.setDuracion(getTxtDuracionComercial().getText());						
					
					if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
						data.setEstado(ESTADO_ACTIVO);
					}else{
						data.setEstado(ESTADO_INACTIVO);
					}
					
					comercialClienteColeccion.add(data);

					// Agregra los valores al registro que va  ser añadido  a la tabla.
					filaComercialCliente.add(getTxtCodigoComercial().getText());
					filaComercialCliente.add(getTxtNombreComercial().getText());
					filaComercialCliente.add(derechoPrograma.getNombre());
					filaComercialCliente.add(getTxtDescripcionComercial().getText());
					filaComercialCliente.add(campanaIf.getCodigo() + " - " + campanaIf.getNombre());
					filaComercialCliente.add(getTxtVersionComercial().getText());
					filaComercialCliente.add(getTxtDuracionComercial().getText());
					
					modelComercialCliente.addRow(filaComercialCliente);
					cleanComercial();
				} else {
					SpiritAlert.createAlert("El Comercial ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					cleanComercial();
				}
			}
		}
		catch(Exception e)
		{
			SpiritAlert.createAlert(" No se pudo ingresar el Comercial Cliente !!!", SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}

	private void cleanComercial() {
		if (getCmbDerechoProgramaComercial().getItemCount() > 0)
			getCmbDerechoProgramaComercial().setSelectedIndex(0);
		getTxtCodigoComercial().setText("");
		getTxtNombreComercial().setText("");
		getTxtDescripcionComercial().setText("");
		getTxtCampanaComercial().setText("");
		getTxtVersionComercial().setText("");
		getTxtDuracionComercial().setText("");
		getTxtCodigoComercial().grabFocus();
		campanaIf = null;
	}
	
	private void actualizarComercialCliente() {
		try
		{						
			boolean isExisteComercial = false;
			
			//Extraigo el objeto comercial del combo
			DerechoProgramaIf derechoPrograma = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getSelectedItem();

			//Si la coleccion tiene algun elemento
			if(comercialClienteColeccion.size()!=0){
				//Recorro la coleccion de comerciales
    			for(int i=0;i<comercialClienteColeccion.size();i++){
    				ComercialIf comercialClienteTemp = (ComercialIf) comercialClienteColeccion.get(i);

    				//Si el comercial cargado ya esta en lista, entons muestro un mensaje de error
    				if(comercialClienteTemp.getDerechoprogramaId().equals(derechoPrograma.getId()) && comercialClienteTemp.getNombre().equals(getTxtNombreComercial().getText()) && comercialClienteTemp.getDescripcion().equals(getTxtDescripcionComercial().getText()) && comercialClienteTemp.getCampanaId().equals(campanaIf.getId()) && comercialClienteTemp.getVersion().equals(getTxtVersionComercial().getText()) && comercialClienteTemp.getDuracion().equals(getTxtDuracionComercial().getText())){
    						isExisteComercial = true;
    						break;
    				}
    			}
    		}
			
			modelComercialCliente =  (DefaultTableModel) getTblComercialCliente().getModel();
			
			//Veo que todos los campos hayan sido llenados
			if (validateFieldsComercialCliente()) {
				//Si el Comercial no existe lo actualizo
				if(isExisteComercial==false){
					//Creo el obejto donde se va a gurdar el regitro
					ComercialIf data = (ComercialIf) comercialClienteColeccion.get(getTblComercialCliente().getSelectedRow());
					
					//Seteo los datos del objeto resumen mes
					data.setCodigo(getTxtCodigoComercial().getText());
					data.setNombre(getTxtNombreComercial().getText());
					data.setEmpresaId(Parametros.getIdEmpresa());
					data.setDerechoprogramaId(derechoPrograma.getId());
					data.setDescripcion(getTxtDescripcionComercial().getText());
					data.setCampanaId(campanaIf.getId());
					data.setVersion(getTxtVersionComercial().getText());
					data.setDuracion(getTxtDuracionComercial().getText());
						
					if(getCmbEstado().getSelectedItem().equals(NOMBRE_ESTADO_ACTIVO)){
						data.setEstado(ESTADO_ACTIVO);
					}else{
						data.setEstado(ESTADO_INACTIVO);
					}
					
					//Actualizar en la coleccion de comercialClienteColeccion el registro que fue cambiado
					comercialClienteColeccion.set(getTblComercialCliente().getSelectedRow(),data);
					
					//Actualizo en la tablaComercialCliente
					modelComercialCliente.setValueAt(getTxtCodigoComercial().getText(),getTblComercialCliente().getSelectedRow(),0);
					modelComercialCliente.setValueAt(getTxtNombreComercial().getText(),getTblComercialCliente().getSelectedRow(),1);
					modelComercialCliente.setValueAt(derechoPrograma.getNombre(),getTblComercialCliente().getSelectedRow(),2);
					modelComercialCliente.setValueAt(getTxtDescripcionComercial().getText(),getTblComercialCliente().getSelectedRow(),3);
					modelComercialCliente.setValueAt(campanaIf.getCodigo() + " - " + campanaIf.getNombre(),getTblComercialCliente().getSelectedRow(),4);
					modelComercialCliente.setValueAt(getTxtVersionComercial().getText(),getTblComercialCliente().getSelectedRow(),5);
					modelComercialCliente.setValueAt(getTxtDuracionComercial().getText(),getTblComercialCliente().getSelectedRow(),6);
					
					
					cleanComercial();
				} else {
					SpiritAlert.createAlert("El Comercial ya se encuentra agregado !!!", SpiritAlert.INFORMATION);
					cleanComercial();
				}
			}
		}
		catch(Exception e)
		{
			SpiritAlert.createAlert("Seleccione el registro a actualizar de la tabla Comercial !!!"
					,SpiritAlert.ERROR);
			System.out.println(e.getMessage());
		}
	}
	
	private void eliminarComercialCliente() {
		if (getTblComercialCliente().getSelectedRow()!=-1)
		{
			try {
    			//Extraigo el objeto comercial de la coleccion segun el registro seleccionado de la tabla
            	ComercialIf comercialTemp = (ComercialIf) comercialClienteColeccion.get(getTblComercialCliente().getSelectedRow());

				//Veo si el comercial cliente escogido existe en la base o no...
				if(comercialTemp.getId()!=null){
					SessionServiceLocator.getComercialSessionService().deleteComercial(comercialTemp.getId());
					
				}
    			
    			//Elimino el registro de la coleccion y de la Tabla
    			comercialClienteColeccion.remove(getTblComercialCliente().getSelectedRow());
				modelComercialCliente.removeRow(getTblComercialCliente().getSelectedRow());
				
				cleanComercial();
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al eliminar registro !!!"
						,SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
		else {
			SpiritAlert.createAlert("Debe elegir el registro de la tabla a eliminar !!!"
					,SpiritAlert.WARNING);
		}
	}

	public void save() {
		if (validateFields()) {
			insertarProductosCliente();
			insertarComercialesCliente();
			
			SpiritAlert.createAlert("Producto Comercial guardado con éxito",SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		}
	}
	
	public void update() {
		if (validateFields()) {	
			updateProductosCliente();
			updateComercialesCliente();
		
			SpiritAlert.createAlert("Producto Comercial actualizado con éxito",SpiritAlert.INFORMATION);
			this.clean();
			this.showSaveMode();
		}
	}
	
	public void delete() {
		try {
			deleteProductosCliente();
			deleteComercialesCliente();
			SpiritAlert.createAlert("Producto Comercial eliminado con éxito",SpiritAlert.INFORMATION);
		} catch (Exception e) {
			SpiritAlert.createAlert("El registro actual tiene datos referenciados y no puede ser eliminado"
					,SpiritAlert.ERROR);
		}
		
		this.clean();
		this.showSaveMode();
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		cargarComboDerechoProgramaComercial();
	}
	
	public void duplicate() {
		// TODO Auto-generated method stub
	}
	
	public void quickSearch() {
		new JDQuickSearchModel(Parametros.getMainFrame());
	}
	
	public void switchTab() {
		int selectedTab = this.getJtpProductoComercial().getSelectedIndex();
		int tabCount = this.getJtpProductoComercial().getTabCount();
		
		selectedTab++;
		
		if (selectedTab >= tabCount)
			selectedTab = 0;
		
		this.getJtpProductoComercial().setSelectedIndex(selectedTab);
	}
	
	public void addDetail() {
		if (this.getJtpProductoComercial().getSelectedIndex() == 0)
			agregarProductoCliente();
		if (this.getJtpProductoComercial().getSelectedIndex() == 1)
			agregarComercialCliente();
	}

	public void updateDetail() {
		if (this.getJtpProductoComercial().getSelectedIndex() == 0)
			actualizarProductoCliente();
		if (this.getJtpProductoComercial().getSelectedIndex() == 1)
			actualizarComercialCliente();
	}
	
	public void deleteDetail() {
		if (this.getJtpProductoComercial().getSelectedIndex() == 0)
			eliminarProductoCliente();
		if (this.getJtpProductoComercial().getSelectedIndex() == 1)
			eliminarComercialCliente();
	}
	
	//Caso especial, aquí no se hace el find, sino lo que va en el mouse click 
	public void find() {
		
		if(clienteIf != null){
			try {
				//Cargo los producto clientes pertenecientes a la campaña leida de la base
				Collection productosColeccion = SessionServiceLocator.getProductoClienteSessionService().findProductoClienteByClienteId(clienteIf.getId());
				Iterator itProductoClienteColeccion = productosColeccion.iterator();
				
				//Obtengo el modelo de la tabla para agregar los producto clientes leidos de la base
				modelProductoCliente = (DefaultTableModel) getTblProductoCliente().getModel();
				
				getCmbEstadoProducto().addItem("ACTIVO");
				getCmbEstadoProducto().addItem("INACTIVO");
				
				while(itProductoClienteColeccion.hasNext()){					
					ProductoClienteIf productoClienteTemp = (ProductoClienteIf) itProductoClienteColeccion.next();
					
					Vector<String> filaProductoCliente = new Vector<String>();

					// Agregar a la coleccion de ProductoClienteColeccion para grabar al final toda la coleccion
					productoClienteColeccion.add(productoClienteTemp);
				
					//Veo el estado del archivo leido
					String estadoProducto = "";
					//LLeno los datos del combo de estado de reunion archivo
					if ("A".equals(productoClienteTemp.getEstado()))
						estadoProducto = "ACTIVO";
					else
						estadoProducto = "INACTIVO";
					
					EmpleadoIf creativoTemp = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().getEmpleado(productoClienteTemp.getCreativoId());
					EmpleadoIf ejecutivoTemp = (EmpleadoIf) SessionServiceLocator.getEmpleadoSessionService().getEmpleado(productoClienteTemp.getEjecutivoId());
					
					// Agregra los valores al registro que va  ser añadido  a la tabla.
					filaProductoCliente.add(productoClienteTemp.getCodigo());
					filaProductoCliente.add(productoClienteTemp.getNombre());
					filaProductoCliente.add(estadoProducto);
					filaProductoCliente.add(creativoTemp.getCodigo() + " - " + creativoTemp.getNombres() + ", " + creativoTemp.getApellidos());
					filaProductoCliente.add(ejecutivoTemp.getCodigo() + " - " + ejecutivoTemp.getNombres() + ", " + ejecutivoTemp.getApellidos());
						
					// Agregra informacion a la tabla visual para el usuario.
					modelProductoCliente.addRow(filaProductoCliente);
				}
				
				//Cargo los comerciales clientes pertencientes a la campana leida de la base
				Collection comercialesColeccion = SessionServiceLocator.getComercialSessionService().findComercialByClienteIdAndByEmpresaId(clienteIf.getId(),Parametros.getIdEmpresa());
				Iterator itComercialClienteColeccion = comercialesColeccion.iterator();
				
				//Obtengo el modelo de la tabla para agregar los comerciales clientes leidos de la base
				modelComercialCliente = (DefaultTableModel) getTblComercialCliente().getModel();
				
				Collection derechoProgramaColeccion = SessionServiceLocator.getDerechoProgramaSessionService().getDerechoProgramaList();
				Iterator itDerechoProgramaColeccion = derechoProgramaColeccion.iterator();
				
				while (itDerechoProgramaColeccion.hasNext()) {
					DerechoProgramaIf derechoProgramaIf = (DerechoProgramaIf) itDerechoProgramaColeccion.next();
					getCmbDerechoProgramaComercial().addItem(derechoProgramaIf);
				}
				
				while(itComercialClienteColeccion.hasNext()){					
					ComercialIf comercialClienteTemp = (ComercialIf) itComercialClienteColeccion.next();
					
					Vector<String> filaComercialCliente = new Vector<String>();

					// Agregar a la coleccion de ComercialColeccion para grabar al final toda la coleccion
					comercialClienteColeccion.add(comercialClienteTemp);
					
					DerechoProgramaIf derechoProgramaTemp = (DerechoProgramaIf) SessionServiceLocator.getDerechoProgramaSessionService().getDerechoPrograma(comercialClienteTemp.getDerechoprogramaId());
					CampanaIf campanaTemp = (CampanaIf) SessionServiceLocator.getCampanaSessionService().getCampana(comercialClienteTemp.getCampanaId());
					
					// Agregra los valores al registro que va  ser añadido  a la tabla.
					filaComercialCliente.add(comercialClienteTemp.getCodigo());
					filaComercialCliente.add(comercialClienteTemp.getNombre());
					filaComercialCliente.add(derechoProgramaTemp.getNombre());
					filaComercialCliente.add(comercialClienteTemp.getDescripcion());
					filaComercialCliente.add(campanaTemp.getCodigo() + " - " + campanaTemp.getNombre());
					filaComercialCliente.add(comercialClienteTemp.getVersion());
					filaComercialCliente.add(comercialClienteTemp.getDuracion());
						
					// Agrega informacion a la tabla visual para el usuario.
					modelComercialCliente.addRow(filaComercialCliente);
				}
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				e.printStackTrace();
			}
			this.showUpdateMode();
		}
		else{
			SpiritAlert.createAlert("Debe escoger un Cliente para realizar la búsqueda",SpiritAlert.WARNING);
			showFindMode();
		}
	}

	public void insertarProductosCliente(){
		//Recorro ahora la coleccion de Productos de cliente
		for(int j=0;j<productoClienteColeccion.size();j++){
			ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(j);
			
			//Creo la instancia a ser insertada en la base del producto cliente 
			ProductoClienteData subData = new ProductoClienteData();
			subData.setCodigo(productoClienteTemp.getCodigo());
			subData.setNombre(productoClienteTemp.getNombre());
			subData.setEstado(productoClienteTemp.getEstado());
			subData.setCreativoId(productoClienteTemp.getCreativoId());
			subData.setEjecutivoId(productoClienteTemp.getEjecutivoId());
			subData.setClienteId(clienteIf.getId());
							
			try {
				SessionServiceLocator.getProductoClienteSessionService().addProductoCliente(subData);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la infomacion en Producto Cliente!"
						,SpiritAlert.ERROR);
			}					
		}		
	}
	
	public void insertarComercialesCliente(){
		//Recorro ahora la coleccion de Comerciales de cliente
		for(int j=0;j<comercialClienteColeccion.size();j++){
			ComercialIf comercialClienteTemp = (ComercialIf) comercialClienteColeccion.get(j);
			
			//Creo la instancia a ser insertada en la base del comercial cliente 
			ComercialData subData = new ComercialData();
			subData.setCodigo(comercialClienteTemp.getCodigo());
			subData.setNombre(comercialClienteTemp.getNombre());
			subData.setDerechoprogramaId(comercialClienteTemp.getDerechoprogramaId());
			subData.setDescripcion(comercialClienteTemp.getDescripcion());
			subData.setCampanaId(comercialClienteTemp.getCampanaId());
			subData.setVersion(comercialClienteTemp.getVersion());
			subData.setDuracion(comercialClienteTemp.getDuracion());
			subData.setEmpresaId(comercialClienteTemp.getEmpresaId());
			subData.setEstado(comercialClienteTemp.getEstado());
							
			try {
				SessionServiceLocator.getComercialSessionService().addComercial(subData);
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al guardar la infomacion en Comercial!"
						,SpiritAlert.ERROR);
			}					
		}		
	}
	
	public void updateProductosCliente(){
		//Recorro ahora la coleccion de Productos de cliente
		for(int i=0;i<productoClienteColeccion.size();i++){
			ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(i);
			
			try {
				//Veo si no han sido agregados productos nuevos a actualizar
				if(productoClienteTemp.getId()!=null)
					SessionServiceLocator.getProductoClienteSessionService().saveProductoCliente(productoClienteTemp);
				else{
					//Seteo el id del cliente para el producto
					productoClienteTemp.setClienteId(clienteIf.getId());
					SessionServiceLocator.getProductoClienteSessionService().addProductoCliente(productoClienteTemp);
				}
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al actualizar la infomación en Producto Cliente Mes!"
						,SpiritAlert.ERROR);
			}	
		}
	}
	
	public void updateComercialesCliente(){
		//Recorro ahora la coleccion de Comerciales de cliente
		for(int i=0;i<comercialClienteColeccion.size();i++){
			ComercialIf comercialClienteTemp = (ComercialIf) comercialClienteColeccion.get(i);
			
			try {
				//Veo si no han sido agregados comerciales nuevos a actualizar
				if(comercialClienteTemp.getId()!=null)
					SessionServiceLocator.getComercialSessionService().saveComercial(comercialClienteTemp);
				else
					SessionServiceLocator.getComercialSessionService().addComercial(comercialClienteTemp);
				
			} catch (GenericBusinessException e) {
				SpiritAlert.createAlert("Error al actualizar la infomación en Comercial Mes!"
						,SpiritAlert.ERROR);
			}	
		}
	}
	
	public void deleteProductosCliente(){
		//Recorro ahora la coleccion de Productos de cliente
		for(int j=0;j<productoClienteColeccion.size();j++){
			ProductoClienteIf productoClienteTemp = (ProductoClienteIf) productoClienteColeccion.get(j);
			
			try{			
				//Elimino el Producto Cliente encontrado
				SessionServiceLocator.getProductoClienteSessionService().deleteProductoCliente(productoClienteTemp.getId());
			}catch (Exception e) {
				SpiritAlert.createAlert("No se puede eliminar el registro!",SpiritAlert.ERROR);
			}		
		}
	}
	
	public void deleteComercialesCliente(){
		//Recorro ahora la coleccion de Comerciales de cliente
		for(int j=0;j<comercialClienteColeccion.size();j++){
			ComercialIf comercialClienteTemp = (ComercialIf) comercialClienteColeccion.get(j);
			try{
				//Elimino el Comercial Cliente encontrado
				SessionServiceLocator.getComercialSessionService().deleteComercial(comercialClienteTemp.getId());
			}catch (Exception e) {
				SpiritAlert.createAlert("No se puede eliminar el registro!"
						,SpiritAlert.ERROR);
			}		
		}
	}
	
	public boolean isEmpty() {
		if ("".equals(this.getTxtCorporacion().getText()) && "".equals(this.getTxtCliente().getText()))
			return true;
		else
			return false;
	}

	public boolean validateFields() {
		String strCorporacion = this.getTxtCorporacion().getText();
		String strCliente = this.getTxtCliente().getText();
		
		if ((("".equals(strCorporacion)) || (strCorporacion == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Corporación !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCorporacion().grabFocus();
			return false;
		}
		if ((("".equals(strCliente)) || (strCliente == null))) {
			SpiritAlert.createAlert("Debe seleccionar un cliente !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCliente().grabFocus();
			return false;
		}
		
		if (productoClienteColeccion.size() == 0) {
			SpiritAlert.createAlert("Debe ingresar al menos un Producto Cliente !!"
					,SpiritAlert.WARNING);
			getJtpProductoComercial().setSelectedIndex(0);
			getTxtCodigoProducto().grabFocus();
			return false;
		}
		
		return true;
	}

	public void clean() {
		
		corporacionIf = null;
		clienteIf = null;
		
		//Si la coleccion de productos no esta vacia
		if(productoClienteColeccion.size() != 0){
			//	Vacio la tabla de producto cliente 
			for(int i= getTblProductoCliente().getRowCount();i>0;--i)
				modelProductoCliente.removeRow(i-1);
		}
		//Si la coleccion de comerciales no esta vacia
		if(comercialClienteColeccion.size() != 0){
			//	Vacio la tabla de comercial
			for(int i= getTblComercialCliente().getRowCount();i>0;--i)
				modelComercialCliente.removeRow(i-1);
		}

		//Vacio las colecciones
		productoClienteColeccion = new Vector();
		comercialClienteColeccion = new Vector();
		
		getTxtCorporacion().setText("");
		getTxtCliente().setText("");
		getTxtCodigoProducto().setText("");
		getTxtNombreProducto().setText("");
		getTxtCreativoProducto().setText("");
		getTxtEjecutivoProducto().setText("");
		getTxtCodigoComercial().setText("");
		getTxtNombreComercial().setText("");
		getTxtDescripcionComercial().setText("");
		getCmbDerechoProgramaComercial().removeAllItems();
		getCmbEstadoProducto().removeAllItems();
		getTxtCampanaComercial().setText("");
		getTxtVersionComercial().setText("");
		getTxtDuracionComercial().setText("");
	}
	
	public void showSaveMode() {
		setSaveMode();
		getTxtCliente().setBackground(Parametros.saveUpdateColor);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getTxtCodigoProducto().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		getTxtNombreProducto().setEnabled(true);
		getBtnBuscarCreativo().setEnabled(true);
		getBtnBorrarCreativo().setEnabled(false);
		getBtnBuscarEjecutivo().setEnabled(true);
		getBtnBorrarEjecutivo().setEnabled(false);
		getBtnAgregarProductoCliente().setEnabled(true);
		getBtnActualizarProductoCliente().setEnabled(true);
		getTxtCodigoComercial().setEnabled(true);
		getCmbDerechoProgramaComercial().setEnabled(true);
		getTxtNombreComercial().setEnabled(true);
		getTxtDescripcionComercial().setEnabled(true);
		getBtnBuscarCampana().setEnabled(false);
		getTxtVersionComercial().setEnabled(true);
		getTxtDuracionComercial().setEnabled(true);
		getBtnAgregarComercialCliente().setEnabled(true);
		getBtnActualizarComercialCliente().setEnabled(true);		
		cargarCombos();
		
		getJtpProductoComercial().setSelectedIndex(0);
		getTxtCodigoProducto().grabFocus();
	}
	
	public void showUpdateMode() {
		setUpdateMode();
		getTxtCliente().setBackground(Parametros.saveUpdateColor);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getTxtCodigoProducto().setEnabled(true);
		getCmbEstadoProducto().setEnabled(true);
		getTxtNombreProducto().setEnabled(true);
		getBtnBuscarCreativo().setEnabled(true);
		getBtnBorrarCreativo().setEnabled(false);
		getBtnBuscarEjecutivo().setEnabled(true);
		getBtnBorrarEjecutivo().setEnabled(false);
		getBtnAgregarProductoCliente().setEnabled(true);
		getBtnActualizarProductoCliente().setEnabled(true);
		getTxtCodigoComercial().setEnabled(true);
		getCmbDerechoProgramaComercial().setEnabled(true);
		getTxtNombreComercial().setEnabled(true);
		getTxtDescripcionComercial().setEnabled(true);
		getBtnBuscarCampana().setEnabled(true);
		getTxtVersionComercial().setEnabled(true);
		getTxtDuracionComercial().setEnabled(true);
		getBtnAgregarComercialCliente().setEnabled(true);
		getBtnActualizarComercialCliente().setEnabled(true);
		
		getJtpProductoComercial().setSelectedIndex(0);
		getTxtCodigoProducto().grabFocus();
	}

	public void showFindMode() {
		getTxtCliente().setBackground(Parametros.findColor);
		getBtnBuscarCorporacion().setEnabled(true);
		getBtnBuscarCliente().setEnabled(true);
		getTxtCodigoProducto().setEnabled(false);
		getCmbEstadoProducto().setEnabled(false);
		getTxtNombreProducto().setEnabled(false);
		getBtnBuscarCreativo().setEnabled(false);
		getBtnBorrarCreativo().setEnabled(false);
		getBtnBuscarEjecutivo().setEnabled(false);
		getBtnBorrarEjecutivo().setEnabled(false);
		getBtnAgregarProductoCliente().setEnabled(false);
		getBtnActualizarProductoCliente().setEnabled(false);
		getTxtCodigoComercial().setEnabled(false);
		getCmbDerechoProgramaComercial().setEnabled(false);
		getTxtNombreComercial().setEnabled(false);
		getTxtDescripcionComercial().setEnabled(false);
		getBtnBuscarCampana().setEnabled(false);
		getTxtVersionComercial().setEnabled(false);
		getTxtDuracionComercial().setEnabled(false);
		getBtnAgregarComercialCliente().setEnabled(false);
		getBtnActualizarComercialCliente().setEnabled(false);
		
		getJtpProductoComercial().setSelectedIndex(0);
		getBtnBuscarCliente().grabFocus();
	}
		
	public boolean validateFieldsProductoCliente(){
		String strCodigo = this.getTxtCodigoProducto().getText();
		String strNombre = this.getTxtNombreProducto().getText();
		String strCreativo = this.getTxtCreativoProducto().getText();
		String strEjecutivo = this.getTxtEjecutivoProducto().getText();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Código para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getTxtCodigoProducto().grabFocus();
			return false;
		}
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getTxtNombreProducto().grabFocus();
			return false;
		}
		
		if ((("".equals(strCreativo)) || (strCreativo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Creativo para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCreativo().grabFocus();
			return false;
		}
		
		if ((("".equals(strEjecutivo)) || (strEjecutivo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Ejecutivo para el Producto del Cliente !!"
					,SpiritAlert.WARNING);
			getBtnBuscarEjecutivo().grabFocus();
			return false;
		}
		
		Collection productoCliente = productoClienteColeccion;
		boolean codigoRepetido = false;
		int i = -1;
		
		Iterator productoClienteIt = productoCliente.iterator();
		
		while (productoClienteIt.hasNext()) {
			ProductoClienteIf productoClienteIf = (ProductoClienteIf) productoClienteIt.next();
			
			i++;
			
			if (this.getMode() == SpiritMode.SAVE_MODE || this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigoProducto().getText().equals(productoClienteIf.getCodigo()))
					if (i != getSelectedRowTblProducto())
						codigoRepetido = true;
			
			if (codigoRepetido)
				break;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El Código del Producto del Cliente está duplicado !!"
					,SpiritAlert.WARNING);
			getTxtCodigoProducto().grabFocus();
			return false;
		}
		
		return true;
	}
	
	public boolean validateFieldsComercialCliente(){
		String strCodigo = this.getTxtCodigoComercial().getText();
		String strNombre = this.getTxtNombreComercial().getText();
		String strDescripcion = this.getTxtDescripcionComercial().getText();
		String strCampana = this.getTxtCampanaComercial().getText();
		String strVersion = this.getTxtVersionComercial().getText();
		String strDuracion = this.getTxtDuracionComercial().getText();
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un Código para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getTxtCodigoComercial().grabFocus();
			return false;
		}
		
		if(getCmbDerechoProgramaComercial().getSelectedIndex() == -1){
			SpiritAlert.createAlert("Debe seleccionar un Derecho Programa para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getCmbDerechoProgramaComercial().grabFocus();
			return false;
		}
		
		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un Nombre para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getTxtNombreComercial().grabFocus();
			return false;
		}
		
		if ((("".equals(strCampana)) || (strCampana == null))) {
			SpiritAlert.createAlert("Debe seleccionar una Campaña para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getBtnBuscarCampana().grabFocus();
			return false;
		}
		
		if ((("".equals(strDescripcion)) || (strDescripcion == null))) {
			SpiritAlert.createAlert("Debe ingresar una Descripción para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getTxtDescripcionComercial().grabFocus();
			return false;
		}
		
		if ((("".equals(strVersion)) || (strVersion == null))) {
			SpiritAlert.createAlert("Debe ingresar una versión para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getTxtVersionComercial().grabFocus();
			return false;
		}
		
		if ((("".equals(strDuracion)) || (strDuracion == null))) {
			SpiritAlert.createAlert("Debe ingresar una duración para el Comercial del Producto !!"
					,SpiritAlert.WARNING);
			getTxtDuracionComercial().grabFocus();
			return false;
		}
		
		Collection comercialProducto = comercialClienteColeccion;
		boolean codigoRepetido = false;
		int i = -1;
		
		Iterator comercialProductoIt = comercialProducto.iterator();
		
		while (comercialProductoIt.hasNext()) {
			ComercialIf comercialProductoIf = (ComercialIf) comercialProductoIt.next();
			
			i++;
			
			if (this.getMode() == SpiritMode.SAVE_MODE || this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigoComercial().getText().equals(comercialProductoIf.getCodigo()))
					if (i != getSelectedRowTblComercial())
						codigoRepetido = true;
			
			if (codigoRepetido)
				break;
		}
		
		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Comercial del Producto está duplicado !!"
					,SpiritAlert.WARNING);
			getTxtCodigoComercial().grabFocus();
			return false;
		}

		return true;

	}
	
	public void cargarCombos(){
			getCmbEstadoProducto().addItem(NOMBRE_ESTADO_ACTIVO);
			getCmbEstadoProducto().addItem(NOMBRE_ESTADO_INACTIVO);
			cargarComboDerechoProgramaComercial();
			//Seteo el derechoPrograma con el primero que se carga en el combo
			if ( getCmbDerechoProgramaComercial().getItemCount() > 0 )
				derechoProgramaIf = (DerechoProgramaIf) getCmbDerechoProgramaComercial().getModel().getElementAt(0);
		
			getCmbEstado().addItem(NOMBRE_ESTADO_ACTIVO);
			getCmbEstado().addItem(NOMBRE_ESTADO_INACTIVO);
			getCmbEstadoProducto().addItem(NOMBRE_ESTADO_INACTIVO);
	}
	
	private void cargarComboDerechoProgramaComercial(){
		try{
			List derechosProgramasComerciales = (List) SessionServiceLocator.getDerechoProgramaSessionService().findDerechoProgramaByEmpresaId(Parametros.getIdEmpresa());
			refreshCombo(getCmbDerechoProgramaComercial(),derechosProgramasComerciales);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public void setSelectedRowTblProducto(int row) {
		this.selectedRowTblProducto = row;
	}
	
	public int getSelectedRowTblProducto() {
		return this.selectedRowTblProducto;
	}
	
	public void setSelectedRowTblComercial(int row) {
		this.selectedRowTblComercial = row;
	}
	
	public int getSelectedRowTblComercial() {
		return this.selectedRowTblComercial;
	}

}
