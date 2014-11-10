package com.spirit.seguridad.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritCursor;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.JDQuickSearchModel;
import com.spirit.general.gui.criteria.EmpresaCriteria;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolData;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolOpcionData;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.gui.criteria.AdministracionRolesCriteria;
import com.spirit.seguridad.gui.panel.JPRoles;
import com.spirit.seguridad.session.MenuSessionService;
import com.spirit.seguridad.session.RolOpcionSessionService;
import com.spirit.seguridad.session.RolSessionService;
import com.spirit.seguridad.session.RolUsuarioSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.util.Funciones;
import com.spirit.util.TextChecker;

/**
 * @author Juan Marin (Extreme Makeover: Christian Briones)
 * 
 */
public class AdministracionRolesModel extends JPRoles {

	private static final long serialVersionUID = -1438550610593759666L;

	private static final Long ID_MENU_RAIZ = 1L;	// ID del Nodo Raiz en la base
	private static final String STATUS_ACTIVO = "ACTIVO";
	private static final String STATUS_INACTIVO = "INACTIVO";
	private static final int MAX_LONGITUD_CODIGO = 5;
	private static final int MAX_LONGITUD_NOMBRE = 30;
	private static final String TIPO_USUARIO_SUPER = "S";
	private static final String TIPO_USUARIO_ADMIN = "A";
	private static final String TIPO_USUARIO_USER = "U";
	
	private EmpresaCriteria empresaCriteria;
	private JDPopupFinderModel popupFinder;
	private AdministracionRolesCriteria administracionRolesCriteria;
	private RolIf rol;
	private List<RolOpcionIf> rolOpcionColeccion = new ArrayList<RolOpcionIf>();
	private EmpresaIf empresaIf;
	
	// Creo la instancia de la raíz del menú personalizado
	DefaultMutableTreeNode rootMP = new DefaultMutableTreeNode("Raiz");
	// Contiene los nodos ya insertados en el árbol Original
	Map existeNodoMapMO = new LinkedHashMap();
	// Contiene los nodos ya insertados en el árbol Personalizado
	Map existeNodoMapMP = new LinkedHashMap();
	// Contiene los nodos con las funciones que posee cada uno de ellos
	Map existeNodoFuncionesMapMP = new LinkedHashMap();
	JTree treeOriginalCache = new JTree();
	
	Collection modulosTreeColeccionMO = new ArrayList();
	Collection opcionesRolColeccion;
	Iterator itModulosTreeColeccionMO;

	public AdministracionRolesModel() {
		initKeyListeners();
		initListeners();
		this.showSaveMode();
		// Mando a cargar el menú original de la base
		cargarMenuOriginal();
		// Creo el nodo raíz del menú personalizado
		getTreeMenuPersonalizado().setModel(new DefaultTreeModel(rootMP));
		// Guardo en el mapa el primer elemento que sería el nodo raíz con los permisos en blanco
		existeNodoFuncionesMapMP.put("RAIZ", new Funciones());
		new HotKeyComponent(this);
	}

	private void initListeners() {
		getCbGrabarActualizar().addActionListener(new CheckBoxHandler());
		getCbBorrar().addActionListener(new CheckBoxHandler());
		getCbConsultar().addActionListener(new CheckBoxHandler());
		getCbAutorizar().addActionListener(new CheckBoxHandler());
		getCbImprimir().addActionListener(new CheckBoxHandler());
		getCbDuplicar().addActionListener(new CheckBoxHandler());
		getCbGenerarGrafico().addActionListener(new CheckBoxHandler());
		
		getBtnEmpresa().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				empresaCriteria = new EmpresaCriteria();
				Vector<Integer> anchoColumnas = new Vector<Integer>();
				anchoColumnas.add(100);
				anchoColumnas.add(100);
				anchoColumnas.add(300);
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), empresaCriteria, JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, anchoColumnas, null);
				if (popupFinder.getElementoSeleccionado() != null) {
					empresaIf = (EmpresaIf) popupFinder.getElementoSeleccionado();
					getTxtEmpresa().setText(empresaIf.getNombre());
				}
			}
		});
	}

	private void initKeyListeners() {
		getTxtCodigo().addKeyListener(new TextChecker(MAX_LONGITUD_CODIGO));
		getTxtNombre().addKeyListener(new TextChecker(MAX_LONGITUD_NOMBRE));
		getTxtEmpresa().setEditable(false);
		if(((UsuarioIf)Parametros.getUsuarioIf()).getTipousuario().equals(TIPO_USUARIO_SUPER)){
			getTxtEmpresa().setEnabled(true);
			getBtnEmpresa().setEnabled(true);
		}else{
			getTxtEmpresa().setEnabled(false);
			getBtnEmpresa().setEnabled(false);
		}
	}

	public void cargarMenuOriginal() {
		try {
			MyDefaultTreeModel treeModel = generateTreeModel();
			getTreeMenuOriginal().setModel(treeModel);
			treeOriginalCache.setModel((MyDefaultTreeModel) treeModel.clone());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}
		
	public class MyDefaultTreeModel extends DefaultTreeModel implements Cloneable {
		public MyDefaultTreeModel(TreeNode root) {
			super(root);
		}
		
		public Object clone() {
	        MyDefaultTreeModel obj=null;
	        try {
	        	
	            obj = (MyDefaultTreeModel) super.clone();
	        } catch(CloneNotSupportedException ex) {
	        	SpiritAlert.createAlert("No se puede duplicar", SpiritAlert.ERROR);
	        }
	        
	        return obj;
	    }
	}
	
	private MyDefaultTreeModel generateTreeModel() throws GenericBusinessException {
		MyDefaultTreeModel defaultTreeModel = new MyDefaultTreeModel(new DefaultMutableTreeNode("Raiz") {
			{
				// Creo la colección con los módulos existentes
				modulosTreeColeccionMO.clear();
				generarModulosTreeMenuColeccionMO(this, SessionServiceLocator.getMenuSessionService().getMenuListSortedByCodigo());
				//modulosTreeColeccionMO = getMenuSessionService().getMenuListSortedByCodigo();
				itModulosTreeColeccionMO =  modulosTreeColeccionMO.iterator();
				
				Iterator itModulosTreeColeccionMO = modulosTreeColeccionMO.iterator();
				// Creo instancia de cada uno de los módulos
				while (itModulosTreeColeccionMO.hasNext()) {
					MenuIf moduloIfMO = (MenuIf) itModulosTreeColeccionMO.next();
					if (moduloIfMO.getNivel() == 1) {
						// Creo el nodo módulo del menú original
						DefaultMutableTreeNode moduloNodoMO = new DefaultMutableTreeNode(moduloIfMO.getNombre() + " ("+ moduloIfMO.getCodigo() + ")");
						// Añado el nodo al mapa de nodos existentes en el menú original
						existeNodoMapMO.put(moduloIfMO.getCodigo(),moduloNodoMO);
						// Añado el nodo módulo al nodo raíz
						add(moduloNodoMO);
						// Añado los nodos de este nodo
						insertarNodosHijoSeleccionado(moduloIfMO.getId(),moduloNodoMO,existeNodoMapMO,modulosTreeColeccionMO);
					}
				}
			}
		});
		
		return defaultTreeModel;
	}
		
	private void generarModulosTreeMenuColeccionMO(DefaultMutableTreeNode nodoRaiz, Collection modulosColeccion) {
		Iterator itModulosColeccion = modulosColeccion.iterator();

		while (itModulosColeccion.hasNext()) {
			MenuIf menuPadreIf = (MenuIf) itModulosColeccion.next();
			if (!menuPadreIf.getCodigo().equals("RAIZ") && menuPadreIf.getPadreId().compareTo(ID_MENU_RAIZ) == 0) {
				modulosTreeColeccionMO.add(menuPadreIf);
				setearHijosMenu(menuPadreIf.getId(), modulosColeccion);
			}
		}
	}
	
	private void setearHijosMenu(Long menuPadreId, Collection modulosColeccion) {
		Iterator itModulosColeccion = modulosColeccion.iterator();
		
		while (itModulosColeccion.hasNext()) {
			MenuIf menuHijoIf = (MenuIf) itModulosColeccion.next();
			if (menuHijoIf.getPadreId() != null && menuPadreId != null) {
				if (menuHijoIf.getPadreId().compareTo(menuPadreId) == 0) {
					modulosTreeColeccionMO.add(menuHijoIf);
					setearHijosMenu(menuHijoIf.getId(), modulosColeccion);
				}
			}
		}
	}
		
	private void insertarNodosHijoSeleccionado(Long menuPadreId, DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap, Collection modulosColeccion) {
		Iterator itModulosTreeColeccionMO = modulosColeccion.iterator();

		while (itModulosTreeColeccionMO.hasNext()) {
			MenuIf nodoMenuIf = (MenuIf) itModulosTreeColeccionMO.next();
			// Añado el nodo hijo a su padre
			if (nodoMenuIf.getPadreId() != null && menuPadreId != null) {
				if (nodoMenuIf.getPadreId().compareTo(menuPadreId) == 0) {
					DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodoMenuIf.getNombre() + " (" + nodoMenuIf.getCodigo()+ ")");
					String splitNodoHijo = (nodoHijo.toString().split("\\("))[1];
					String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
					// Guardo en el mapa de nodos existentes en el menú el nodo hijo actual
					existeNodoMap.put(codigoNodoHijo, nodoHijo);
					// Añado al nodo seleccionado en el árbol de la derecha el hijo encontrado
					nodoSeleccionado.add(nodoHijo);
					insertarNodosHijoSeleccionado(nodoMenuIf.getId(), nodoHijo, existeNodoMap, modulosColeccion);
				}
			}
		}
	}
		
	private void insertarNodosHijoSeleccionado (DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap) {
		Long idNodoSeleccionado;
		if (nodoSeleccionado.isRoot()) {
			idNodoSeleccionado = ID_MENU_RAIZ;
		}
		// Si el nodo no es root, extraigo el codigo de este
		else {
			String splitNodoSeleccionado = (nodoSeleccionado.toString().split("\\("))[1];
			String codigoNodoSeleccionado = splitNodoSeleccionado.substring(0, (splitNodoSeleccionado.length() - 1));
			// Obtengo el id del nodo seleccionado segun el codigo de este
			idNodoSeleccionado = findMenuByCodigo(codigoNodoSeleccionado).getId();
		}

		// Creo la coleccion con los modulos existentes
		Collection nodosHijoNodoSeleccionado = findMenuByPadreId(idNodoSeleccionado);
		Iterator itNodosHijoNodoSeleccionado = nodosHijoNodoSeleccionado.iterator();

		// Creo instancia de cada uno de los modulos
		while (itNodosHijoNodoSeleccionado.hasNext()) {
			MenuIf nodoMenuIf = (MenuIf) itNodosHijoNodoSeleccionado.next();

			// Añado el nodo hijo a su padre
			DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(nodoMenuIf.getNombre() + " (" + nodoMenuIf.getCodigo()+ ")");
			String splitNodoHijo = (nodoHijo.toString().split("\\("))[1];
			String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));

			// Guardo en el mapa de nodos existentes en el menú el nodo hijo actual
			existeNodoMap.put(codigoNodoHijo, nodoHijo);

			// Añado al nodo seleccionado en el árbol de la derecha el hijo encontrado
			nodoSeleccionado.add(nodoHijo);

			// Mando a buscar si el nodo hijo tiene hijos
			insertarNodosHijoSeleccionado(nodoHijo, existeNodoMap);
		}
	}
	
	private MenuIf findMenuByCodigo(String codigoNodoSeleccionado) {
		Iterator it = modulosTreeColeccionMO.iterator();
		
		while (it.hasNext()) {
			MenuIf menu = (MenuIf) it.next();
			if (menu.getCodigo().equals(codigoNodoSeleccionado))
				return menu;
		}
		
		return null;
	}
	
	private Collection findMenuByPadreId(Long idNodoSeleccionado) {
		Collection menuColeccionByPadreId = new ArrayList();
		Iterator it = modulosTreeColeccionMO.iterator();
		
		while (it.hasNext()) {
			MenuIf menu = (MenuIf) it.next();
			if (menu != null && menu.getPadreId() != null && idNodoSeleccionado != null)
				if (menu.getPadreId().compareTo(idNodoSeleccionado) == 0)
					menuColeccionByPadreId.add(menu);
		}
		
		return menuColeccionByPadreId;
	}

	public void save() {
		if (validateFields()) {
			try {
				RolIf rol = registrarRol();
				DefaultMutableTreeNode nodoRaiz = (DefaultMutableTreeNode) getTreeMenuPersonalizado().getModel().getRoot();
				nodosToRolOpcionCollection(nodoRaiz);
				addNodoRaizToRolOpcionCollection();
				SessionServiceLocator.getRolSessionService().procesarRol(rol, rolOpcionColeccion);
				SpiritAlert.createAlert("Rol guardado con éxito", SpiritAlert.INFORMATION);

				this.clean();
				this.showSaveMode();

			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Ocurrió un error al guardar el Rol", SpiritAlert.ERROR);
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (validateFields()) {
			try {
				RolIf rol = registrarRolForUpdate();
				DefaultMutableTreeNode nodoRaiz = (DefaultMutableTreeNode) getTreeMenuPersonalizado().getModel().getRoot();
				nodosToRolOpcionCollection(nodoRaiz);
				addNodoRaizToRolOpcionCollection();
				SessionServiceLocator.getRolSessionService().actualizarRol(rol, rolOpcionColeccion);
				SpiritAlert.createAlert("Rol actualizado con éxito", SpiritAlert.INFORMATION);

			} catch (Exception e) {
				if (e instanceof GenericBusinessException)
					SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
				else
					SpiritAlert.createAlert("Error al actualizar el Rol!", SpiritAlert.ERROR);
			}
			
			this.clean();
			this.showSaveMode();
		}
	}
	
	public void delete() {
		try {
			SessionServiceLocator.getRolSessionService().eliminarRol(rol.getId());
			SpiritAlert.createAlert("Rol eliminado con éxito", SpiritAlert.INFORMATION);
		} catch (Exception e) {
			if (e instanceof GenericBusinessException)
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			else
				SpiritAlert.createAlert("Error al eliminar el Rol!", SpiritAlert.ERROR);
		}
		
		this.clean();
		this.showSaveMode();
	}
	
	private void nodosToRolOpcionCollection(DefaultMutableTreeNode nodo) {
		if (nodo.getChildCount() >= 0) {
			for (Enumeration e = nodo.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode nodoHijoMP = (DefaultMutableTreeNode) e.nextElement();
				String splitNodoHijoMP = (nodoHijoMP.toString().split("\\("))[1];
				String codigoNodoHijoMP = splitNodoHijoMP.substring(0, (splitNodoHijoMP.length() - 1));
				// Obtengo el id del nodo hijo segun el codigo de este
				Long idNodoHijoMP = findMenuByCodigo(codigoNodoHijoMP).getId();
				RolOpcionIf modelRolOpcion = new RolOpcionData();
				//modelRolOpcion.setRolId(idRol);
				modelRolOpcion.setMenuId(idNodoHijoMP);
				Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get(codigoNodoHijoMP);
				setFuncionesRolOpcion(modelRolOpcion, oFunciones);
				rolOpcionColeccion.add(modelRolOpcion);
				// Mando a insertar los hijos del nodo hijo si es que tiene
				nodosToRolOpcionCollection(nodoHijoMP);
			}
		}
	}
	
	private void addNodoRaizToRolOpcionCollection() {
		try {
			RolOpcionIf modelRolOpcion = new RolOpcionData();
			MenuIf menuRaiz;
			menuRaiz = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByCodigo("RAIZ").iterator().next();
			modelRolOpcion.setMenuId(menuRaiz.getId());
			Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get("RAIZ");
			setFuncionesRolOpcion(modelRolOpcion, oFunciones);
			rolOpcionColeccion.add(modelRolOpcion);
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void setFuncionesRolOpcion(RolOpcionIf modelRolOpcion, Funciones oFunciones) {
		if (oFunciones.getGrabarActualizar())
			modelRolOpcion.setGrabarActualizar("S");
		else
			modelRolOpcion.setGrabarActualizar("N");
		
		if (oFunciones.getBorrar())
			modelRolOpcion.setBorrar("S");
		else
			modelRolOpcion.setBorrar("N");
		
		if (oFunciones.getConsultar())
			modelRolOpcion.setConsultar("S");
		else
			modelRolOpcion.setConsultar("N");
		
		if (oFunciones.getAutorizar())
			modelRolOpcion.setAutorizar("S");
		else
			modelRolOpcion.setAutorizar("N");
		
		if (oFunciones.getImprimir())
			modelRolOpcion.setImprimir("S");
		else
			modelRolOpcion.setImprimir("N");
		
		if (oFunciones.getGenerarGrafico())
			modelRolOpcion.setGenerarGrafico("S");
		else
			modelRolOpcion.setGenerarGrafico("N");
		
		if (oFunciones.getDuplicar())
			modelRolOpcion.setDuplicar("S");
		else
			modelRolOpcion.setDuplicar("N");
		
		if (!oFunciones.getGrabarActualizar()
				&& !oFunciones.getBorrar()
				&& !oFunciones.getConsultar()
				&& !oFunciones.getAutorizar()
				&& !oFunciones.getImprimir()
				&& !oFunciones.getGenerarGrafico()
				&& !oFunciones.getDuplicar())
			modelRolOpcion.setNinguno("S");
		else
			modelRolOpcion.setNinguno("N");
	}
	
	private RolIf registrarRol() throws GenericBusinessException {

		RolData data = new RolData();
		
		data.setCodigo(this.getTxtCodigo().getText());
		data.setNombre(this.getTxtNombre().getText());
		data.setStatus(this.getCmbStatus().getSelectedItem().toString().substring(0, 1));
				
		if(((UsuarioIf)Parametros.getUsuarioIf()).getTipousuario().equals(TIPO_USUARIO_SUPER)){
			data.setEmpresaId(empresaIf.getId());
			data.setTipoRolUsuario(TIPO_USUARIO_ADMIN);
		}else{
			data.setEmpresaId(Parametros.getIdEmpresa());
			data.setTipoRolUsuario(TIPO_USUARIO_USER);
		}
		return data;
	}
	
	private RolIf registrarRolForUpdate() {

		rol.setCodigo(this.getTxtCodigo().getText());
		rol.setNombre(this.getTxtNombre().getText());
		rol.setStatus(this.getCmbStatus().getSelectedItem().toString().substring(0, 1));
		
		if(((UsuarioIf)Parametros.getUsuarioIf()).getTipousuario().equals(TIPO_USUARIO_SUPER)){
			rol.setEmpresaId(empresaIf.getId());
			rol.setTipoRolUsuario(TIPO_USUARIO_ADMIN);			
		}else{
			rol.setEmpresaId(Parametros.getIdEmpresa());
			rol.setTipoRolUsuario(TIPO_USUARIO_USER);
		}
		
		return rol;
	}

	// Valido que todos los campos hayan sido llenados
	public boolean validateFields() {
		String strCodigo = this.getTxtCodigo().getText();
		String strNombre = this.getTxtNombre().getText();
				
		Collection rol = null;
		boolean codigoRepetido = false;
		
		if(((UsuarioIf)Parametros.getUsuarioIf()).getTipousuario().equals(TIPO_USUARIO_SUPER)){
			if (empresaIf == null) {
				SpiritAlert.createAlert("Debe seleccionar una Empresa para el Rol si el usuario es Super!", SpiritAlert.WARNING);
				getTxtNombre().grabFocus();
				return false;
			}
		}
		
		try {
			rol = SessionServiceLocator.getRolSessionService().findRolByEmpresaId(Parametros.getIdEmpresa());
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		Iterator rolIt = rol.iterator();

		while (rolIt.hasNext()) {
			RolIf rolIf = (RolIf) rolIt.next();

			if (this.getMode() == SpiritMode.SAVE_MODE)
				if (getTxtCodigo().getText().equals(rolIf.getCodigo()))
					codigoRepetido = true;

			if (this.getMode() == SpiritMode.UPDATE_MODE)
				if (getTxtCodigo().getText().equals(rolIf.getCodigo()))
					if (this.rol.getId().equals(rolIf.getId()) == false)
						codigoRepetido = true;
		}

		if (codigoRepetido) {
			SpiritAlert.createAlert("El código del Rol está duplicado !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}
		
		if ((("".equals(strCodigo)) || (strCodigo == null))) {
			SpiritAlert.createAlert("Debe ingresar un código para el Rol !!", SpiritAlert.WARNING);
			getTxtCodigo().grabFocus();
			return false;
		}

		if ((("".equals(strNombre)) || (strNombre == null))) {
			SpiritAlert.createAlert("Debe ingresar un nombre para el Rol !!", SpiritAlert.WARNING);
			getTxtNombre().grabFocus();
			return false;
		}
		
		if (existeNodoMapMP.size() == 0) {
			SpiritAlert.createAlert("Por favor escoja los menús con sus funciones para el Rol a crear!", SpiritAlert.WARNING);
			return false;
		}
		
		return true;
	}
	
	public void report() {
		// TODO Auto-generated method stub
		
	}
	
	public void refresh() {
		
	}
	
	public void duplicate() {
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

	private ArrayList getModel(ArrayList listaRoles) {
		ArrayList data = new ArrayList();
		Iterator it = listaRoles.iterator();

		while (it.hasNext()) {
			ArrayList fila = new ArrayList();
			RolIf bean = (RolIf) it.next();

			fila.add(bean.getCodigo());
			fila.add(bean.getNombre());

			data.add(fila);
		}
		return data;
	}

	private Map buildQuery() {
		Map aMap = new HashMap();
		if ("".equals(getTxtCodigo().getText()) == false)
			aMap.put("codigo", getTxtCodigo().getText() + "%");
		else
			aMap.put("codigo", "%");
		
		if ("".equals(getTxtNombre().getText()) == false)
			aMap.put("nombre", getTxtNombre().getText() + "%");
		else
			aMap.put("nombre", "%");
		
		if (TIPO_USUARIO_ADMIN.equals(((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario())) {
			aMap.put("tipoRolUsuario", TIPO_USUARIO_USER);
			aMap.put("empresaId", Parametros.getIdEmpresa());
		}else if (TIPO_USUARIO_SUPER.equals(((UsuarioIf) Parametros.getUsuarioIf()).getTipousuario())) {
			aMap.put("tipoRolUsuario", TIPO_USUARIO_ADMIN);
		}

		return aMap;
	}

	public void find() {
		try {
			Map mapa = buildQuery();
			administracionRolesCriteria = new AdministracionRolesCriteria();
			administracionRolesCriteria.setQueryBuilded(mapa);
			if (administracionRolesCriteria.getResultListSize() > 0) {
				popupFinder = new JDPopupFinderModel(Parametros.getMainFrame(), administracionRolesCriteria, JDPopupFinderModel.BUSQUEDA_TODOS);
				if ( popupFinder.getElementoSeleccionado()!=null ){
					setCursor(SpiritCursor.hourglassCursor);
					getSelectedObject();
					setCursor(SpiritCursor.normalCursor);
				}						
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.WARNING);
			}
		} catch (Exception e) {
			SpiritAlert.createAlert("Error en la búsqueda de información", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		if ("".equals(this.getTxtCodigo().getText()) && "".equals(this.getTxtNombre().getText()))
			return true;
		else
			return false;
	}

	public void clean() {
		this.getTxtCodigo().setEnabled(true);
		this.getTxtCodigo().setText("");
		this.getTxtNombre().setText("");
		this.cleanCheckBox();
		// Seteo el rol a nulo
		rol = null;
		empresaIf = null;
		getTxtEmpresa().setText("");
		this.getCmbStatus().setSelectedItem(null);
		this.getCmbStatus().removeAllItems();
		// Quito momentáneamente el listener del árbol de la derecha
		getTreeMenuPersonalizado().removeTreeSelectionListener(oTreeSelectionListener);

		// Limpio los mapas después de guardar los registros en la base
		existeNodoFuncionesMapMP.clear();
		if (this.getMode() != SpiritMode.FIND_MODE)
			existeNodoMapMO.clear();
		existeNodoMapMP.clear();
		rolOpcionColeccion.clear();

		cargarMenuOriginal();
		rootMP = new DefaultMutableTreeNode("Raiz");
		// Creo el nodo raíz del menú personalizado
		getTreeMenuPersonalizado().setModel(new DefaultTreeModel(rootMP));
		// Guardo en el mapa el primer elemento que sería el nodo raíz con los permisos en blanco
		existeNodoFuncionesMapMP.put("RAIZ", new Funciones());
		// Agrego nuevamente el listener al árbol personalizado
		getTreeMenuPersonalizado().addTreeSelectionListener(oTreeSelectionListener);
	}

	private void cleanCheckBox() {
		// Seteo en blanco los check box
		getCbGrabarActualizar().setSelected(false);
		getCbBorrar().setSelected(false);
		getCbConsultar().setSelected(false);
		getCbAutorizar().setSelected(false);
		getCbImprimir().setSelected(false);
		getCbGenerarGrafico().setSelected(false);
		getCbDuplicar().setSelected(false);
	}

	// Clase que maneja los eventos como respuesta a la seleccion de un checkbox
	private class CheckBoxHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Obtengo la ruta al nodo que ha sido seleccionado
			TreePath pathNodoSeleccionadoMP = getTreeMenuPersonalizado().getSelectionPath();

			// Veo si es que ha sido seleccionado algun nodo para asignarle los
			// permisos correspondiente
			if (pathNodoSeleccionadoMP == null) {
				// Si es seleccionado un checkbox y no ha sido escogido ningun
				// nodo, le quito la seleccion al mismo
				if (getCbGrabarActualizar().isSelected())
					getCbGrabarActualizar().setSelected(false);
				if (getCbBorrar().isSelected())
					getCbBorrar().setSelected(false);
				if (getCbConsultar().isSelected())
					getCbConsultar().setSelected(false);
				if (getCbAutorizar().isSelected())
					getCbAutorizar().setSelected(false);
				if (getCbImprimir().isSelected())
					getCbImprimir().setSelected(false);
				if (getCbGenerarGrafico().isSelected())
					getCbGenerarGrafico().setSelected(false);
				if (getCbDuplicar().isSelected())
					getCbDuplicar().setSelected(false);
				// Mando a imprimir un mensaje diciendo que no es posible
				// asignar perimsos sin que haya sido elegido un nodo
				SpiritAlert.createAlert("Por favor seleccione el elemento a asignar permisos!", SpiritAlert.INFORMATION);
			} else {
				// Extraigo el nodo seleccionado de cada de la ruta escogida
				DefaultMutableTreeNode nodoSeleccionadoMP = (DefaultMutableTreeNode) pathNodoSeleccionadoMP.getLastPathComponent();
				// Extraigo el codigo del nodo seleccionado para guardarlo en el
				// mapa
				String codigoNodoSeleccionadoMP;
				if (!nodoSeleccionadoMP.isRoot()) {
					String splitNodoSeleccionadoMP = (nodoSeleccionadoMP.toString().split("\\("))[1];
					codigoNodoSeleccionadoMP = splitNodoSeleccionadoMP.substring(0, (splitNodoSeleccionadoMP.length() - 1));
				} else
					codigoNodoSeleccionadoMP = "RAIZ";
				// Objeto que guarda los permisos asignados a cada nodo
				Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get(codigoNodoSeleccionadoMP);

				// Trabajo segun el check box que ha sido seleccionado
				if (e.getActionCommand().equals("Grabar / Actualizar")) {
					if (getCbGrabarActualizar().isSelected())
						oFunciones.setGrabarActualizar(true);
					else
						oFunciones.setGrabarActualizar(false);
				}
				if (e.getActionCommand().equals("Borrar")) {
					if (getCbBorrar().isSelected())
						oFunciones.setBorrar(true);
					else
						oFunciones.setBorrar(false);
				}
				if (e.getActionCommand().equals("Consultar")) {
					if (getCbConsultar().isSelected())
						oFunciones.setConsultar(true);
					else
						oFunciones.setConsultar(false);
				}
				if (e.getActionCommand().equals("Autorizar")) {
					if (getCbAutorizar().isSelected())
						oFunciones.setAutorizar(true);
					else
						oFunciones.setAutorizar(false);
				}
				if (e.getActionCommand().equals("Imprimir")) {
					if (getCbImprimir().isSelected())
						oFunciones.setImprimir(true);
					else
						oFunciones.setImprimir(false);
				}
				if (e.getActionCommand().equals("Generar Gráfico")) {
					if (getCbGenerarGrafico().isSelected())
						oFunciones.setGenerarGrafico(true);
					else
						oFunciones.setGenerarGrafico(false);
				}
				if (e.getActionCommand().equals("Duplicar")) {
					if (getCbDuplicar().isSelected())
						oFunciones.setDuplicar(true);
					else
						oFunciones.setDuplicar(false);
				}

				existeNodoFuncionesMapMP.put(codigoNodoSeleccionadoMP, oFunciones);

				// Mando a ver si el nodo con lso permisos seteados actualmente
				// tiene hijos para setearle los mismo permisos a sus hijos
				setearPermisosNodosHijo(codigoNodoSeleccionadoMP, oFunciones);
			}
		}
	}

	// Funcion que setea los permisos de las funciones que tiene un nodo padre a sus nodos hijos
	private void setearPermisosNodosHijo(String codigoNodoSeleccionadoMP, Funciones oFunciones) {
		// Obtengo el nodo seleccionado del menu personalizado para ver si tienen hijos
		DefaultMutableTreeNode nodoSeleccionadoMP = (DefaultMutableTreeNode) existeNodoMapMP.get(codigoNodoSeleccionadoMP);

		if (nodoSeleccionadoMP.getChildCount() >= 0) {
			for (Enumeration e = nodoSeleccionadoMP.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode nodoHijoMP = (DefaultMutableTreeNode) e.nextElement();
				String splitNodoHijoMP = (nodoHijoMP.toString().split("\\("))[1];
				String codigoNodoHijoMP = splitNodoHijoMP.substring(0, (splitNodoHijoMP.length() - 1));

				// Obtengo el objeto Funcion del nodo hijo del seleccionado
				Funciones oFuncionesTemp = (Funciones) existeNodoFuncionesMapMP.get(codigoNodoHijoMP);
				// Seteo sus propiedades con las del padre
				oFuncionesTemp.setGrabarActualizar(oFunciones.getGrabarActualizar());
				oFuncionesTemp.setBorrar(oFunciones.getBorrar());
				oFuncionesTemp.setConsultar(oFunciones.getConsultar());
				oFuncionesTemp.setAutorizar(oFunciones.getAutorizar());
				oFuncionesTemp.setImprimir(oFunciones.getImprimir());
				oFuncionesTemp.setGenerarGrafico(oFunciones.getGenerarGrafico());
				oFuncionesTemp.setDuplicar(oFunciones.getDuplicar());

				// Veo si el nodo hijos tiene mas hijos
				setearPermisosNodosHijo(codigoNodoHijoMP, oFunciones);
			}
		}
	}

	// Clase que controla el evento de seleccion del arbol de la derecha para
	// mostrar los permisos que tiene un nodo
	TreeSelectionListener oTreeSelectionListener = new TreeSelectionListener() {
		public void valueChanged(TreeSelectionEvent evt) {
			// Obtengo la ruta al nodo que ha sido seleccionado
			TreePath pathNodoSeleccionado = evt.getNewLeadSelectionPath();

			// Extraigo el nodo seleccionado de la ruta escogida
			if (pathNodoSeleccionado != null) {
				DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) pathNodoSeleccionado.getLastPathComponent();
				// Extraigo el codigo del nodo seleccionado
				String codigoNodoSeleccionado;
				if (!nodoSeleccionado.isRoot()) {
					String splitNodoSeleccionado = (nodoSeleccionado.toString().split("\\("))[1];
					codigoNodoSeleccionado = splitNodoSeleccionado.substring(0, (splitNodoSeleccionado.length() - 1));
				} else
					codigoNodoSeleccionado = "RAIZ";

				// Objeto que contiene los permisos asignados del nodo seleccionado
				Funciones oFunciones = (Funciones) existeNodoFuncionesMapMP.get(codigoNodoSeleccionado);

				// Muestro los permisos en pantalla
				getCbGrabarActualizar().setSelected(oFunciones.getGrabarActualizar());
				getCbBorrar().setSelected(oFunciones.getBorrar());
				getCbConsultar().setSelected(oFunciones.getConsultar());
				getCbAutorizar().setSelected(oFunciones.getAutorizar());
				getCbImprimir().setSelected(oFunciones.getImprimir());
				getCbGenerarGrafico().setSelected(oFunciones.getGenerarGrafico());
				getCbDuplicar().setSelected(oFunciones.getDuplicar());
			}
		}
	};

	public void cargarCombos() {
		getCmbStatus().addItem(STATUS_ACTIVO);
		getCmbStatus().addItem(STATUS_INACTIVO);
	}

	public void showSaveMode() {
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		setSaveMode();
		getCmbStatus().setEnabled(true);
		cargarCombos();

		// Manejo el evento del combo de Estado
		getCmbStatus().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					// Si el rol es uno leido de la base, valido que si está asignado a un usuario o se pueda cambiar su estado a inactivo
					if (rol != null) {
						Collection rolesUsuarioCollection = SessionServiceLocator.getRolUsuarioSessionService().findRolUsuarioByRolId(rol.getId());
						if (rolesUsuarioCollection.size() > 0 && getCmbStatus().getSelectedItem().equals(STATUS_INACTIVO)) {
							getCmbStatus().setSelectedItem(STATUS_ACTIVO);
							SpiritAlert.createAlert("El rol no puede ser inactivo debido a que está asignado a un Usuario!", SpiritAlert.WARNING);
						}
					}
				} catch (GenericBusinessException e) {
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					e.printStackTrace();
				}
			}
		});

		// Hago el arbol del menu personalizado de seleccion multiple
		getTreeMenuPersonalizado().getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Controlo el evento de cuando es seleccionado un nodo del menú personalizado
		getTreeMenuPersonalizado().addTreeSelectionListener(oTreeSelectionListener);

		// Manejo el evento del Boton Agregar al Menu Personalizado
		getBtnAgregar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				// Quito momentaneamente el listener del arbol de la derecha
				getTreeMenuPersonalizado().removeTreeSelectionListener(oTreeSelectionListener);
				// Get the model of tree Menu Original
				DefaultTreeModel modelTreeMenuOrigen = (DefaultTreeModel) getTreeMenuOriginal().getModel();
				// Get the model of tree Menu Personalizado
				DefaultTreeModel modelTreeMenuDestino = (DefaultTreeModel) getTreeMenuPersonalizado().getModel();
				// Get paths of all selected nodes
				TreePath[] pathsNodosSeleccionadoMO = getTreeMenuOriginal().getSelectionPaths();
				// Mando a llamar a la funcion que me permite mover los nodos de
				// un arbol a otro
				moverNodosEntreArboles(modelTreeMenuOrigen, modelTreeMenuDestino, pathsNodosSeleccionadoMO, existeNodoMapMO, existeNodoMapMP);
				// Expando el arbol de la derecha
				expandAll(getTreeMenuPersonalizado(), new TreePath(modelTreeMenuDestino.getRoot()), true);
				agregarFuncionToNodo((DefaultMutableTreeNode) getTreeMenuPersonalizado().getModel().getRoot());
				// Agrego nuevamente el listener del arbol personalizado
				getTreeMenuPersonalizado().addTreeSelectionListener(oTreeSelectionListener);
				// Mando a blanqeuar lso checkbox en el caso de que el que fue
				// removido haya tenido alguno(s) chequeado
				cleanCheckBox();
			}
		});

		// Manejo el evento del Boton Quitar del Menu Personalizado
		getBtnQuitar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				// Quito momentaneamente el listener del arbol de la derecha
				getTreeMenuPersonalizado().removeTreeSelectionListener(oTreeSelectionListener);
				// Get the model of tree Menu Original
				DefaultTreeModel modelTreeMenuOrigen = (DefaultTreeModel) getTreeMenuPersonalizado().getModel();
				// Get the model of tree Menu Personalizado
				DefaultTreeModel modelTreeMenuDestino = (DefaultTreeModel) getTreeMenuOriginal().getModel();
				// Get paths of all selected nodes
				TreePath[] pathsNodosSeleccionadoMO = getTreeMenuPersonalizado().getSelectionPaths();
				// Mando a llamar a la funcion que me permite mover los nodos de
				// un arbol a otro
				moverNodosEntreArboles(modelTreeMenuOrigen, modelTreeMenuDestino, pathsNodosSeleccionadoMO, existeNodoMapMP, existeNodoMapMO);
				// Agrego nuevamente el listener del arbol personalizado
				getTreeMenuPersonalizado().addTreeSelectionListener(oTreeSelectionListener);
				// Mando a blanqeuar lso checkbox en el caso de que el que fue
				// removido haya tenido alguno(s) chequeado
				cleanCheckBox();
			}
		});
		getBtnAgregar().setEnabled(true);
		getBtnQuitar().setEnabled(true);
		getCbAutorizar().setEnabled(true);
		getCbBorrar().setEnabled(true);
		getCbConsultar().setEnabled(true);
		getCbGenerarGrafico().setEnabled(true);
		getCbGrabarActualizar().setEnabled(true);
		getCbImprimir().setEnabled(true);
		getCbDuplicar().setEnabled(true);
		getTxtCodigo().grabFocus();
	}
	
	public void showUpdateMode() {
		getTxtCodigo().setBackground(Parametros.saveUpdateColor);
		getTxtNombre().setBackground(Parametros.saveUpdateColor);
		setUpdateMode();
		getTxtCodigo().setEnabled(false);
		getTxtNombre().setEnabled(true);
		getCmbStatus().setEnabled(true);
		getBtnAgregar().setEnabled(true);
		getBtnQuitar().setEnabled(true);
		getCbAutorizar().setEnabled(true);
		getCbBorrar().setEnabled(true);
		getCbConsultar().setEnabled(true);
		getCbGenerarGrafico().setEnabled(true);
		getCbGrabarActualizar().setEnabled(true);
		getCbImprimir().setEnabled(true);
		getCbDuplicar().setEnabled(true);
	}
	
	public void showFindMode() {
		getTxtCodigo().setBackground(Parametros.findColor);
		getTxtNombre().setBackground(Parametros.findColor);
		getTxtCodigo().setEnabled(true);
		getTxtNombre().setEnabled(true);
		getCmbStatus().setEnabled(false);
		getBtnAgregar().setEnabled(false);
		getBtnQuitar().setEnabled(false);
		getCbAutorizar().setEnabled(false);
		getCbBorrar().setEnabled(false);
		getCbConsultar().setEnabled(false);
		getCbGenerarGrafico().setEnabled(false);
		getCbGrabarActualizar().setEnabled(false);
		getCbImprimir().setEnabled(false);
		getCbDuplicar().setEnabled(false);
		getTxtCodigo().grabFocus();
	}

	// Funcion que me permite cada vez que agrego nodos al arbol de la
	// izquierda, setearle su respectivo objeto (enblanco) con sus funciones
	private void agregarFuncionToNodo(DefaultMutableTreeNode nodo) {
		if (nodo.getChildCount() >= 0) {
			for (Enumeration e = nodo.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode nodoHijoMP = (DefaultMutableTreeNode) e.nextElement();
				String splitNodoHijoMP = (nodoHijoMP.toString().split("\\("))[1];
				String codigoNodoHijoMP = splitNodoHijoMP.substring(0, (splitNodoHijoMP.length() - 1));

				// Añado al mapa el nodo con su objeto que contiene los permisos
				// de este
				if (existeNodoFuncionesMapMP.get(codigoNodoHijoMP) == null)
					existeNodoFuncionesMapMP.put(codigoNodoHijoMP, new Funciones());

				agregarFuncionToNodo(nodoHijoMP);
			}
		}
	}

	private void moverNodosEntreArboles(DefaultTreeModel modelTreeMenuOrigen, DefaultTreeModel modelTreeMenuDestino,
			TreePath[] pathsNodosSeleccionadoMO, Map existeNodoMO, Map existeNodoMD) {
		// Si no ha sido seleccionado ningún nodo, se muestra un mensaje de aviso
		if (pathsNodosSeleccionadoMO != null) {
			// Extraigo el nodo raíz del menú destino
			DefaultMutableTreeNode rootMD = (DefaultMutableTreeNode) modelTreeMenuDestino.getRoot();

			// Navego por cada una de las rutas de los nodos seleccionados
			for (int indiceNodosSeleccionadosMO = 0; indiceNodosSeleccionadosMO < pathsNodosSeleccionadoMO.length; indiceNodosSeleccionadosMO++) {
				DefaultMutableTreeNode nodoActualMD = new DefaultMutableTreeNode();
				DefaultMutableTreeNode nodoSeleccionadoMO = (DefaultMutableTreeNode) pathsNodosSeleccionadoMO[indiceNodosSeleccionadosMO].getLastPathComponent();
				DefaultMutableTreeNode nodoPadreSeleccionadoMO = new DefaultMutableTreeNode();
				String codigoNodoSeleccionadoMO = "";
				if (!nodoSeleccionadoMO.isRoot()) {
					String splitNodoSeleccionadoMO = (nodoSeleccionadoMO.toString().split("\\("))[1];
					codigoNodoSeleccionadoMO = splitNodoSeleccionadoMO.substring(0, (splitNodoSeleccionadoMO.length() - 1));
				}
				// Variables que guardan el codigo del nodoActual
				String codigoNodoActualMD = "";

				if (nodoSeleccionadoMO.isRoot()) {
					// Limpio el mapa y la raiz del menu origen
					existeNodoMO.clear();
					modelTreeMenuOrigen.setRoot(new DefaultMutableTreeNode("Raiz"));
					// Limpio el mapa y la raiz del menu destino
					existeNodoMD.clear();
					modelTreeMenuDestino.setRoot(new DefaultMutableTreeNode("Raiz"));
					// Inserto el nodo raiz en el mapa destino
					existeNodoMD.put("RAIZ",(DefaultMutableTreeNode) modelTreeMenuDestino.getRoot());
					// Inserto todos los hijos del nodo raiz en el menu destino
					insertarNodosHijoSeleccionado((DefaultMutableTreeNode) modelTreeMenuDestino.getRoot(), existeNodoMD);
				} else {
					// Navego por cada uno de los nodos de las rutas de los nodos seleccionados
					for (int j = 1; j < pathsNodosSeleccionadoMO[indiceNodosSeleccionadosMO].getPathCount(); j++) {
						nodoActualMD = new DefaultMutableTreeNode(pathsNodosSeleccionadoMO[indiceNodosSeleccionadosMO].getPath()[j]);
						String splitNodoActualMD = (nodoActualMD.toString().split("\\("))[1];
						codigoNodoActualMD = splitNodoActualMD.substring(0, (splitNodoActualMD.length() - 1));
						// Si el nodo no existe, lo agrego al mapa, lo inserto
						// en el arbol y lo seteo como posible nodo padre
						if (existeNodoMD.get(codigoNodoActualMD) == null) {
							existeNodoMD.put(codigoNodoActualMD, nodoActualMD);
							// Si el nodo actual no es hijo del nodo raiz, lo
							// añado dentro del nodo que le corresponde
							if (j > 1)
								modelTreeMenuDestino.insertNodeInto(nodoActualMD, nodoPadreSeleccionadoMO,0);
							else {
								modelTreeMenuDestino.insertNodeInto(nodoActualMD, rootMD, 0);
								existeNodoMD.put("RAIZ", rootMD);
							}
							// Si no existe en elmapa lo seteo cmo un posible
							// nodo padre del nodo que ha sido seleccionado
							nodoPadreSeleccionadoMO = nodoActualMD;
						}
						// Si el nodo existe igual lo busco en el map y el
						// encontrado lo seteo como posible padre
						else
							nodoPadreSeleccionadoMO = (DefaultMutableTreeNode) existeNodoMD.get(codigoNodoActualMD);

						// Si el nodo es el nodo seleccionado,lo extraigo del
						// mapa, remuevo los hijos que tenia insertados, y mando
						// a insertar TODOS los que tiene en la base
						if (codigoNodoActualMD.equals(codigoNodoSeleccionadoMO) && existeNodoMO.get(codigoNodoActualMD) != null) {
							nodoSeleccionadoMO = (DefaultMutableTreeNode) existeNodoMD.get(codigoNodoSeleccionadoMO);
							nodoSeleccionadoMO.removeAllChildren();
							insertarNodosHijoSeleccionado(nodoSeleccionadoMO, existeNodoMD);
						}
					}
					// Veo si el nodo no ha sido ya removido del arbol de del
					// menu de origen
					if (existeNodoMO.get(codigoNodoActualMD) != null) {
						// Mando a quitar los nodos padres cuyos hijos hayan
						// sido quitados y el se encuentre solitario
						quitarNodoPadreSinHijos(modelTreeMenuOrigen,nodoSeleccionadoMO, existeNodoMO);
						// Retiro del menu Origen y del Mapa el nodo(s) que fue
						// seleccionado
						
						modelTreeMenuOrigen.removeNodeFromParent((DefaultMutableTreeNode) existeNodoMO.get(codigoNodoSeleccionadoMO));
						removerNodosHijoSeleccionado((DefaultMutableTreeNode) existeNodoMO.get(codigoNodoSeleccionadoMO),existeNodoMO);
						existeNodoMO.remove(codigoNodoSeleccionadoMO);
						// Remuevo el nodo seleccionado del mapa de nodos y
						// permisos
						if (existeNodoFuncionesMapMP.get(codigoNodoSeleccionadoMO) != null)
							existeNodoFuncionesMapMP.remove(codigoNodoSeleccionadoMO);

					}
				}
			}
			// Mando a cargar los nodos añadidos del Menu Destino
			modelTreeMenuDestino.reload();
		}
	}

	// Metodo que chequea si un nodo padre se ha quedado sin hijos para quitarlo
	// del mapa y del arbol
	private void quitarNodoPadreSinHijos(DefaultTreeModel modelTreeMenuOrigen, DefaultMutableTreeNode nodoSeleccionadoMO, Map existeNodoMO) {
		// Si el nodo padre del seleccionado es diferente del de la raiz
		if (!((DefaultMutableTreeNode) nodoSeleccionadoMO.getParent()).isRoot()) {
			/*String splitNodoSeleccionadoMO = (nodoSeleccionadoMO.toString().split("\\("))[1];
			String codigoNodoPadreSeleccionadoMO = splitNodoSeleccionadoMO.substring(0, (splitNodoSeleccionadoMO.length() - 1));
			DefaultMutableTreeNode nodoPadreSeleccionadoMO = (DefaultMutableTreeNode) existeNodoMO.get(codigoNodoPadreSeleccionadoMO);*/
			String splitNodoPadreSeleccionadoMO = (nodoSeleccionadoMO.getParent().toString().split("\\("))[1];
			String codigoNodoPadreSeleccionadoMO = splitNodoPadreSeleccionadoMO.substring(0, (splitNodoPadreSeleccionadoMO.length() - 1));
			DefaultMutableTreeNode nodoPadreSeleccionadoMO = (DefaultMutableTreeNode) existeNodoMO.get(codigoNodoPadreSeleccionadoMO);

			// Veo cuando todos los nodos hijos han sido sacado y solo queda el
			// nodo padre sin hijos, lo saco del mapa y del arbol para que el
			// padre no quede como una simple hoja
			if (nodoPadreSeleccionadoMO.getChildCount() == 1) {
				// LLamo a la funcion nuevamente para ver si el nodo padre es
				// hijo único, para remover al padre de él
				quitarNodoPadreSinHijos(modelTreeMenuOrigen, nodoPadreSeleccionadoMO, existeNodoMO);

				// Remuevo el nodo padre sin hijos del arbol
				modelTreeMenuOrigen.removeNodeFromParent(nodoPadreSeleccionadoMO);

				// Retiro del mapa de nodos existentes en el menu el padre del nodo seleccionado
				existeNodoMO.remove(codigoNodoPadreSeleccionadoMO);

				// Remuevo el mismo nodo del mapa de nodos y permisos
				if (existeNodoFuncionesMapMP.get(codigoNodoPadreSeleccionadoMO) != null)
					existeNodoFuncionesMapMP.remove(codigoNodoPadreSeleccionadoMO);
			}
		}
	}

	// Método recursivo que recibe un nodo busca los hijos de éste en la base
	private void removerNodosHijoSeleccionado(DefaultMutableTreeNode nodoSeleccionado, Map existeNodoMap) {
		if (nodoSeleccionado.getChildCount() >= 0) {
			for (Enumeration e = nodoSeleccionado.children(); e.hasMoreElements();) {
				// Veo el nodo Hijo del nodo recibido
				DefaultMutableTreeNode nodoHijo = (DefaultMutableTreeNode) e.nextElement();
				String splitNodoHijo = (nodoHijo.toString().split("\\("))[1];
				String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));

				// LLamo a la funcion nuevamente para ver si el nodo tiene más hijos
				removerNodosHijoSeleccionado(nodoHijo, existeNodoMap);

				// Retiro del mapa de nodos existentes en el menu el nodo hijo actual
				existeNodoMap.remove(codigoNodoHijo);

				// Remuevo el nodo hijo del mapa de nodos y permisos
				if (existeNodoFuncionesMapMP.get(codigoNodoHijo) != null)
					existeNodoFuncionesMapMP.remove(codigoNodoHijo);

			}
		}
	}

	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		// Traverse children
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}

		// Expansion or collapse must be done bottom-up
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

	private void getSelectedObject() {
		rol = (RolIf) popupFinder.getElementoSeleccionado();

		cargarCombos();

		getTxtCodigo().setText(rol.getCodigo());
		getTxtNombre().setText(rol.getNombre());
		
		try {
			if(((UsuarioIf)Parametros.getUsuarioIf()).getTipousuario().equals(TIPO_USUARIO_SUPER)){
				empresaIf = SessionServiceLocator.getEmpresaSessionService().getEmpresa(rol.getEmpresaId());
				getTxtEmpresa().setText(empresaIf.getNombre());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
		}
		
		if ((STATUS_ACTIVO.substring(0, 1)).equals(rol.getStatus()))
			getCmbStatus().setSelectedItem(STATUS_ACTIVO);
		else
			getCmbStatus().setSelectedItem(STATUS_INACTIVO);

		// Quito momentaneamente el listener del arbol de la derecha
		getTreeMenuPersonalizado().removeTreeSelectionListener(oTreeSelectionListener);

		armarMenuPersonalizadoByIdRol(rol.getId(), ID_MENU_RAIZ);

		// Mando a cargar las funciones del nodo raiz si este tiene
		cargarFuncionesNodoRaiz(rol.getId());

		// Mando a expandir el árbol personalizado
		expandAll(getTreeMenuPersonalizado(), new TreePath(((DefaultTreeModel) getTreeMenuPersonalizado().getModel()).getRoot()), true);

		// Agrego nuevamente el listener al arbol personalizado
		getTreeMenuPersonalizado().addTreeSelectionListener(oTreeSelectionListener);

		showUpdateMode();
	}

	private void armarMenuPersonalizadoByIdRol(Long idRol, Long idMenuPadre) {
		try {
			// Get the model of tree Menu Personalizado
			DefaultTreeModel modelTreeMenuPersonalizado = (DefaultTreeModel) getTreeMenuPersonalizado().getModel();
			// Get the model of tree Menu Origen
			DefaultTreeModel modelTreeMenuOrigen = (DefaultTreeModel) getTreeMenuOriginal().getModel();
			DefaultMutableTreeNode nodoPadreMP = new DefaultMutableTreeNode();
			DefaultMutableTreeNode nodoPadreMO = new DefaultMutableTreeNode();

			// Obtengo el objeto del MenuPadre
			MenuIf menuPadreIf = (MenuIf) SessionServiceLocator.getMenuSessionService().getMenu(idMenuPadre);

			// Declaro las variables para el nodo padre y su codigo
			if (menuPadreIf.getCodigo().equals("RAIZ")) {
				nodoPadreMP = (DefaultMutableTreeNode) modelTreeMenuPersonalizado.getRoot();
			} else {
				nodoPadreMP = (DefaultMutableTreeNode) existeNodoMapMP.get(menuPadreIf.getCodigo());
			}

			// Obtengo todas las opciones de menú según el id del Rol recibido y del menú padre
			Collection rolOpcionesMenu = SessionServiceLocator.getMenuSessionService().findMenuByPadreIdAndByRolId(idRol,idMenuPadre);
			//Collection rolOpcionesMenu = findMenuByPadreIdAndByRolId(idRol,idMenuPadre);
			Iterator itRolOpcionesMenu = rolOpcionesMenu.iterator();

			// Creo instancia de cada uno de los registros RolOpcion leído de la base
			while (itRolOpcionesMenu.hasNext()) {
				// Obtengo la opcion de Menu de la base segun el MENU_ID leido de la tabla ROL_OPCION
				MenuIf menuHijoIf = (MenuIf) itRolOpcionesMenu.next();
				// Creo el nodo hijo y extraigo su codigo
				DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(menuHijoIf.getNombre() + " (" + menuHijoIf.getCodigo() + ")");
				String splitNodoHijo = (nodoHijo.toString().split("\\("))[1];
				String codigoNodoHijo = splitNodoHijo.substring(0, (splitNodoHijo.length() - 1));
				// Inserto el nodohijo leido de la base en el map del menú personalizado
				existeNodoMapMP.put(codigoNodoHijo, nodoHijo);
				// Inserto el nodo hijo debajo del nodo padre
				modelTreeMenuPersonalizado.insertNodeInto(nodoHijo, nodoPadreMP, 0);
				// Obtengo el idRolOpcion para poder buscar las funciones que tiene el nodo hijo
				Map parameters = new HashMap();
				parameters.put("rolId", idRol);
				parameters.put("menuId", menuHijoIf.getId());
				RolOpcionIf rolOpcionIf = (RolOpcionIf) SessionServiceLocator.getRolOpcionSessionService().findRolOpcionByQuery(parameters).iterator().next();
				// Creo la instancia que contiene todas las funciones de este nodo
				Funciones oFunciones = new Funciones();

				// Creo instancia de cada uno de los registros RolOpcionFuncion
				// leido de la base para el idRolOpcion enviado
				if (rolOpcionIf != null) {
					if (rolOpcionIf.getNinguno().equals("N")) {
						if (rolOpcionIf.getGrabarActualizar().equals("S"))
							oFunciones.setGrabarActualizar(true);
						if (rolOpcionIf.getBorrar().equals("S"))
							oFunciones.setBorrar(true);
						if (rolOpcionIf.getConsultar().equals("S"))
							oFunciones.setConsultar(true);
						if (rolOpcionIf.getAutorizar().equals("S"))
							oFunciones.setAutorizar(true);
						if (rolOpcionIf.getImprimir().equals("S"))
							oFunciones.setImprimir(true);
						if (rolOpcionIf.getGenerarGrafico().equals("S"))
							oFunciones.setGenerarGrafico(true);
						if (rolOpcionIf.getDuplicar().equals("S"))
							oFunciones.setDuplicar(true);
					}

					// Guardo el objeto función en el mapa con el código del nodo al que le pertenece
					existeNodoFuncionesMapMP.put(codigoNodoHijo, oFunciones);
				}

				// Veo si el nodo hijo tiene hijos
				armarMenuPersonalizadoByIdRol(idRol, menuHijoIf.getId());
				// Retiro del menú Origen y del Mapa el nodo hijo que fue leído de la base, SIEMPRE Y CUANDO EL PADRE DE ESTE NO TENGA MAS HIJOS
				DefaultMutableTreeNode nodoHijoMO = (DefaultMutableTreeNode) existeNodoMapMO.get(codigoNodoHijo);
				if (nodoHijoMO != null) {
					if (nodoHijoMO.getChildCount() == 0) {
						DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) existeNodoMapMO.get(codigoNodoHijo);
						if (dmt != null)
							modelTreeMenuOrigen.removeNodeFromParent(dmt);
						existeNodoMapMO.remove(codigoNodoHijo);
					}
				}
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	private void cargarFuncionesNodoRaiz(Long idRol) {
		try {
			// Get the model of tree Menu Personalizado
			DefaultTreeModel modelTreeMenuPersonalizado = (DefaultTreeModel) getTreeMenuPersonalizado().getModel();
			DefaultMutableTreeNode nodoPadreMP = new DefaultMutableTreeNode();
			// Ingreso el nodo raiz en el mapa de menu personalizado
			nodoPadreMP = (DefaultMutableTreeNode) modelTreeMenuPersonalizado.getRoot();
			existeNodoMapMP.put("RAIZ", nodoPadreMP);
			// Obtengo el idRolOpcion del nodo Raiz para poder buscar sus funciones
			
			Iterator rolOpcionIterator = SessionServiceLocator.getRolOpcionSessionService().findRolOpcionByMenuCodigoAndByRolId("RAIZ", idRol).iterator();

			if (rolOpcionIterator.hasNext()) {
				RolOpcionIf rolOpcionIf = (RolOpcionIf) rolOpcionIterator.next();
			
				// Creo la instancia que contiene todas las funciones del nodo Raiz
				Funciones oFunciones = new Funciones();

				if (rolOpcionIf != null) {
					if (rolOpcionIf.getNinguno().equals("N")) {
						if (rolOpcionIf.getGrabarActualizar().equals("S"))
							oFunciones.setGrabarActualizar(true);
						if (rolOpcionIf.getBorrar().equals("S"))
							oFunciones.setBorrar(true);
						if (rolOpcionIf.getConsultar().equals("S"))
							oFunciones.setConsultar(true);
						if (rolOpcionIf.getAutorizar().equals("S"))
							oFunciones.setAutorizar(true);
						if (rolOpcionIf.getImprimir().equals("S"))
							oFunciones.setImprimir(true);
						if (rolOpcionIf.getGenerarGrafico().equals("S"))
							oFunciones.setGenerarGrafico(true);
						if (rolOpcionIf.getDuplicar().equals("S"))
							oFunciones.setDuplicar(true);
					}

					existeNodoFuncionesMapMP.put("RAIZ", oFunciones);
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	 
}

