package com.spirit.timeTracker.gui.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.entity.RolUsuarioIf;
import com.spirit.timeTracker.gui.model.SpiritLogin;


//TODO: Setear esta clase desde el inicio del login, ya que se debe uno seleccionar la empresa a trabajar.
public class ParametrosIniciales {
	public static Map modulosUsuarioByRoles = new LinkedHashMap();
	public static Map menusUsuarioByRoles = new LinkedHashMap();
	public static Map _SubMenusUsuarioByRoles = new LinkedHashMap();;
	public static Map funcionesSubMenuByRoles = new LinkedHashMap();
	private static long idEmpresa = 0L;
	private static String nombreEmpresa;
	private static String urlEmpresa;
	private static String logoEmpresa;
	private static String rutaServidorArchivos;
	private static long idOficina = 0L;
	private static String usuario;
	
	public ParametrosIniciales() {
		Parametros.set_SubMenusUsuarioByRoles(_SubMenusUsuarioByRoles);
		Parametros.setFuncionesSubMenuByRoles(funcionesSubMenuByRoles);
		
		try {
			if (!"S".equals(SpiritLogin._usuario.getTipousuario())) {// && !"DEVEL".equals(SpiritLogin._usuario.getTipousuario())) {
				idEmpresa = SpiritLogin._idEmpresaUsuario;
				//Linea para hacer mas generico idempresa, pero falta refactoring.
				Parametros.setIdEmpresa(idEmpresa);
				EmpresaIf empresa = SpiritLogin._empresa;
				Parametros.setEmpresa(empresa);
				nombreEmpresa = empresa.getNombre();
				Parametros.setNombreEmpresa(nombreEmpresa);
				logoEmpresa = empresa.getLogo();
				urlEmpresa = empresa.getWeb();
				if (!"A".equals(SpiritLogin._usuario.getTipousuario())) {
					idOficina = SpiritLogin._idOficinaUsuario;
					OficinaIf oficina = SpiritLogin._oficina;
					Parametros.setNombreOficina(oficina.getNombre());
				} else
					Parametros.setNombreOficina("");
			} else
				nombreEmpresa = "";
			//TODO: Eliminar variable usuario cuando nadie la referencie.
			usuario = SpiritLogin._usuario.getUsuario().toUpperCase();
			Parametros.setUsuario(SpiritLogin._usuario.getUsuario().toUpperCase());
			
			//Etxraigo los tipos de parametros segun la empresa
			Collection tipoParametroCollection = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByEmpresaId(idEmpresa);
			Iterator itTipoParametro = tipoParametroCollection.iterator();
			
			//Recorro los tipos de parametros encontrados para esta empresa
			while(itTipoParametro.hasNext()){
				//Extraigo el objeto 
				TipoParametroIf tipoParametroIf = (TipoParametroIf) itTipoParametro.next();
				//Saco el obejto parametro segun el id del tipo parametro
				
				if(!SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByTipoparametroId(tipoParametroIf.getId()).isEmpty()){
					ParametroEmpresaIf parametroIf = (ParametroEmpresaIf) SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByTipoparametroId(tipoParametroIf.getId()).iterator().next();
					
					//Veo que tipo de parametro es
					if(tipoParametroIf.getCodigo().equals("DISCOMEDIO")){
						rutaServidorArchivos = parametroIf.getValor() + ":\\\\" + nombreEmpresa + "\\\\";
					} else if (tipoParametroIf.getCodigo().equals("MONLOCAL")){
						Parametros.setCodMoneda(parametroIf.getValor());
					}else if (parametroIf.getCodigo().equals("DIRECTORIOREPORTES")){					
						Parametros.setRutaCarpetaReportes(parametroIf.getValor());					
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
	}

	//Metodo que carga todos los menus y submenus  de los modulos disponibles para este usario segun lso roles que el tenga
	public static void cargarModulos_Menus_SubMenusByRoles(Collection usuarios) {
		long start=System.currentTimeMillis();
		try {
			//Obtengo los roles del usuario logoneado
			List rolesUsuario = findRolUsuarioByUsuarioId(usuarios, SpiritLogin._usuario.getId());
			Iterator itRolesUsuario = rolesUsuario.iterator(); 
			Map rolesMap = mapearRoles(SpiritLogin._usuario.getEmpresaId());
			List rolOpcionList = (ArrayList) SessionServiceLocator.getRolOpcionSessionService().findRolOpcionAndMenuList();
			
			System.out.println("rolOpcionList:"+rolOpcionList);
			
			//Creo instancias de cada uno de los registros RolesUsuario leido de la base
			while (itRolesUsuario.hasNext()) {
				RolUsuarioIf rolUsuarioIf = (RolUsuarioIf) itRolesUsuario.next();
				//Busco el rol del usuario para ver que menus tiene habilitado
				RolIf rolIf = (RolIf) rolesMap.get(rolUsuarioIf.getRolId());
				
				//Obtengo todos los modulos que tiene habilitado a usar este usuario
				//Collection rolOpcionesModulo = getMenuSessionService().findMenuByPadreIdAndByRolId(rolIf.getId(),1L);
				List menuByRolList = (ArrayList) findMenuByRolId(rolOpcionList, rolIf.getId());
				List rolOpcionesModulo = findMenuByPadreId(menuByRolList, 1L);
				Iterator itRolOpcionesModulo = rolOpcionesModulo.iterator();
				
				//Creo la instancia de todos los modulos leidos de la base para los roles asiganado a este usuario 
				while (itRolOpcionesModulo.hasNext()) {
					MenuIf moduloIf = (MenuIf) itRolOpcionesModulo.next();
					
					//Busco la opcion (modulo) segun el idOpcion de la tabla RolOpcion en el mapa que tiene todas las opciones cargadas para este usuario
					if (modulosUsuarioByRoles.get(moduloIf.getCodigo()) == null)
						modulosUsuarioByRoles.put(moduloIf.getCodigo(),moduloIf);												
					
					//Obtengo todos los menus que tiene habilitado a usar este usuario en el modulo actual
					Collection rolOpcionesMenu = findMenuByPadreId(menuByRolList, moduloIf.getId());
					Iterator itRolOpcionesMenu = rolOpcionesMenu.iterator();
					
					//Creo la instancia de todos los menus leidos de la base
					while (itRolOpcionesMenu.hasNext()){
						
						MenuIf menuIf = (MenuIf) itRolOpcionesMenu.next();
						//	Creo un mapa donde van los submenus que tiene este menu 
						Map subMenusUsuarioByRoles = new LinkedHashMap();
						
						//Si el mapa de submenu ya existe lo mando a buscar en el mapa de menus
						if(menusUsuarioByRoles.get(menuIf.getCodigo())!=null)
							subMenusUsuarioByRoles = (LinkedHashMap) menusUsuarioByRoles.get(menuIf.getCodigo());
							
						
						
						//Obtengo todos los subMenus que tiene habilitado a usar este usuario en el menu actual
						Collection rolOpcionesSubMenu = findMenuByPadreId(menuByRolList, menuIf.getId());
						Iterator itRolOpcionesSubMenu = rolOpcionesSubMenu.iterator();
						
						//Creo la instancia de todos los submenus leidos de la base
						while (itRolOpcionesSubMenu.hasNext()) {
							
							MenuIf subMenuIf = (MenuIf) itRolOpcionesSubMenu.next();
							Map subMenusUsuarioByCategoriasAndByRoles = new LinkedHashMap();
							//Busco la opcion (subMenu) segun el idOpcion de la tabla RolOpcion en el mapa que tiene todas las ocpiones cargadas para este usuario
							if (subMenusUsuarioByRoles.get(subMenuIf.getCodigo()) == null) {
								subMenusUsuarioByRoles.put(subMenuIf.getCodigo(),subMenuIf);
								//En este mapa guardo las instancias de subMenus para cuando se escoja unpanel se pueda encontra alobjeto por su nombre
								_SubMenusUsuarioByRoles.put(subMenuIf.getNombre().toUpperCase(),subMenuIf);
							} else {
								LinkedHashMap submenuLinkedHashMap = new LinkedHashMap();
								submenuLinkedHashMap.put(subMenuIf.getCodigo(), subMenusUsuarioByRoles.get(subMenuIf.getCodigo()));
								subMenusUsuarioByCategoriasAndByRoles = submenuLinkedHashMap;
							}
							
							Collection rolOpcionesSubMenuByCategorias = findMenuByPadreId(menuByRolList, subMenuIf.getId());
							Iterator itRolOpcionesSubMenuByCategorias = rolOpcionesSubMenuByCategorias.iterator();
							
							while (itRolOpcionesSubMenuByCategorias.hasNext()) {
								
								
								
								MenuIf subMenuByCategoriaIf = (MenuIf) itRolOpcionesSubMenuByCategorias.next();
								if (subMenusUsuarioByCategoriasAndByRoles.get(subMenuByCategoriaIf.getCodigo()) == null) {
									subMenusUsuarioByCategoriasAndByRoles.put(subMenuByCategoriaIf.getCodigo(), subMenuByCategoriaIf);
									_SubMenusUsuarioByRoles.put(subMenuIf.getNombre().toUpperCase(),subMenuIf);
									Map funcionesMap = addRolFunciones(rolOpcionList, rolIf, subMenuByCategoriaIf);
									funcionesSubMenuByRoles.put(subMenuIf.getCodigo(), funcionesMap);
								}
							}

							Map funcionesMap = addRolFunciones(rolOpcionList, rolIf, subMenuIf);
							//Guardo el mapa de funciones en el mapa de _FuncionesSubMenuByRoles que contiene todas las funciones de este menu
							funcionesSubMenuByRoles.put(subMenuIf.getCodigo(), funcionesMap);
							menusUsuarioByRoles.put(subMenuIf.getCodigo(), subMenusUsuarioByCategoriasAndByRoles);
						}
						//Guardo el mapa de submenus en el mapa de menus
						menusUsuarioByRoles.put(menuIf.getCodigo(), subMenusUsuarioByRoles);
					}					
				}				
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		long fin=System.currentTimeMillis();
		System.out.println("---------------------CargarModulos_Menus_SubMenusByRoles: "+(fin-start)/1000+" seg");
	}
	
	private static List findRolUsuarioByUsuarioId(Collection usuarios, Long usuarioId) {
		List rolesUsuarioList = new ArrayList();
		Iterator it = usuarios.iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			UsuarioIf usuario = (UsuarioIf) o[0];
			RolUsuarioIf rolUsuario = (RolUsuarioIf) o[1];
			if (usuarioId.compareTo(rolUsuario.getUsuarioId()) == 0)
				rolesUsuarioList.add(rolUsuario);
			
			if (usuario.getTipousuario().equals("A") || usuario.getTipousuario().equals("S"))
				return rolesUsuarioList;
		}
		return rolesUsuarioList;
	}
	
	private static Map mapearRoles(Long idEmpresa) {
		Map rolesMap = new HashMap();
		try {	
			Iterator rolesIterator = (idEmpresa != null)?SessionServiceLocator.getRolSessionService().findRolByEmpresaId(idEmpresa).iterator():SessionServiceLocator.getRolSessionService().getRolList().iterator();
			while (rolesIterator.hasNext()) {
				RolIf rol = (RolIf) rolesIterator.next();
				rolesMap.put(rol.getId(), rol);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return rolesMap;
	}
	
	private static List findMenuByRolId(List rolOpcionList, Long rolId) {
		List menuList = new ArrayList();
		Iterator it = rolOpcionList.iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			RolOpcionIf rolOpcion = (RolOpcionIf) o[0];
			MenuIf menu = (MenuIf) o[1];
			if (rolOpcion.getRolId().compareTo(rolId) == 0)
				menuList.add(menu);
		}
		
		return menuList;
	}
	
	private static List findMenuByPadreId(List menuList, Long padreId) {
		List menuListFiltrada = new ArrayList();
		Iterator it = menuList.iterator();
		while (it.hasNext()) {
			MenuIf menu = (MenuIf) it.next();
			if (menu.getPadreId() != null && menu.getPadreId().compareTo(padreId) == 0)
				menuListFiltrada.add(menu);
		}
		
		return menuListFiltrada;
	}
	
	private static Map addRolFunciones(List rolOpcionList, RolIf rolIf, MenuIf subMenuIf) throws GenericBusinessException {
		Map funcionesMap = new LinkedHashMap();
		RolOpcionIf rolOpcionIf = findRolOpcionByRolIdAndMenuId(rolOpcionList, rolIf.getId(), subMenuIf.getId());
		
		if (rolOpcionIf != null) {
			if (rolOpcionIf.getGrabarActualizar().equals("S"))
				funcionesMap.put("GRA", true);
			else
				funcionesMap.put("GRA", false);
			
			if (rolOpcionIf.getBorrar().equals("S"))
				funcionesMap.put("BOR", true);
			else
				funcionesMap.put("BOR", false);
			
			if (rolOpcionIf.getConsultar().equals("S"))
				funcionesMap.put("CON", true);
			else
				funcionesMap.put("CON", false);
			
			if (rolOpcionIf.getAutorizar().equals("S"))
				funcionesMap.put("AUT", true);
			else
				funcionesMap.put("AUT", false);
			
			if (rolOpcionIf.getImprimir().equals("S"))
				funcionesMap.put("IMP", true);
			else
				funcionesMap.put("IMP", false);
			
			if (rolOpcionIf.getGenerarGrafico().equals("S"))
				funcionesMap.put("GEN", true);
			else
				funcionesMap.put("GEN", false);
			
			if (rolOpcionIf.getDuplicar().equals("S"))
				funcionesMap.put("DUP", true);
			else
				funcionesMap.put("DUP", false);
		}
		
		return funcionesMap;
	}

	private static RolOpcionIf findRolOpcionByRolIdAndMenuId(List rolOpcionList, Long rolId, Long menuId) {
		Iterator it = rolOpcionList.iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			RolOpcionIf ro = (RolOpcionIf) o[0];
			if (ro.getRolId().compareTo(rolId) == 0 && ro.getMenuId().compareTo(menuId) == 0)
				return ro;
		}
		
		return null;
	}
	
//TODO: Se lo comento ya que no se debe usar mas, usar el metodo de la clase Parametros.

	//public static long getIdEmpresa() {
		//return idEmpresa;
	//}

	public static void setIdEmpresa(long idEmpresa) {
		ParametrosIniciales.idEmpresa = idEmpresa;
	}

	public static long getIdOficina() {
		return idOficina;
	}

	public static void setIdOficina(long idOficina) {
		ParametrosIniciales.idOficina = idOficina;
	}

	public static String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public static void setNombreEmpresa(String nombreEmpresa) {
		ParametrosIniciales.nombreEmpresa = nombreEmpresa;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		ParametrosIniciales.usuario = usuario;
	}

	public static String getUrlEmpresa() {
		return urlEmpresa;
	}

	public static void setUrlEmpresa(String urlEmpresa) {
		ParametrosIniciales.urlEmpresa = urlEmpresa;
	}
	
	public static String getRutaServidorArchivos() {
		return rutaServidorArchivos;
	}


	public static void setRutaServidorArchivos(String rutaServidorArchivos) {
		ParametrosIniciales.rutaServidorArchivos = rutaServidorArchivos;
	}
	 
}
