package com.spirit.general.gui.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.seguridad.entity.RolIf;
import com.spirit.seguridad.entity.RolOpcionIf;
import com.spirit.seguridad.entity.RolUsuarioIf;

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
		
		if (!"S".equals(SpiritLogin._usuario.getTipousuario())) {// && !"DEVEL".equals(SpiritLogin._usuario.getTipousuario())) {
			idEmpresa = SpiritLogin._idEmpresaUsuario;
			//Linea para hacer mas generico idempresa, pero falta refactoring.
			Parametros.setIdEmpresa(idEmpresa);
			EmpresaIf empresa = SpiritLogin._empresa;
			Parametros.setEmpresa(empresa);
			nombreEmpresa = empresa.getNombre();
			Parametros.setNombreEmpresa(nombreEmpresa);
			logoEmpresa = empresa.getLogo();
			Parametros.setLogoEmpresa(logoEmpresa);
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
		//Collection tipoParametroCollection = getTipoParametroSessionService().findTipoParametroByEmpresaId(idEmpresa);
		//Iterator itTipoParametro = tipoParametroCollection.iterator();
		Collection parametroEmpresaList = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaAndTipoParametroByEmpresaId(idEmpresa);
		Iterator parametroEmpresaIt = parametroEmpresaList.iterator();
		boolean exitoDiscoMedio = false,exitoRepoMedio = false;
		while (parametroEmpresaIt.hasNext()) {
			Object[] o = (Object[]) parametroEmpresaIt.next();
			ParametroEmpresaIf parametroIf = (ParametroEmpresaIf) o[0];
			TipoParametroIf tipoParametroIf = (TipoParametroIf) o[1];
			if(tipoParametroIf.getCodigo().equals("DISCOMEDIO")){
				exitoDiscoMedio = true;
				//Se pone la ruta en que se guardan los archivos
				//rutaServidorArchivos = parametroIf.getValor() + ":\\\\" + nombreEmpresa + "\\\\";
				if (parametroIf.getCodigo().equals("URLCARPETASERVIDOR"))
					Parametros.setUrlCarpetaServidor(parametroIf.getValor());
				else{
					SpiritAlert.createAlert("No existe Parametro de Empresa \"URLCARPETASERVIDOR\", debe crearlo", SpiritAlert.WARNING);
					//System.exit(0);
				}
			} else if ( tipoParametroIf.getCodigo().equals("REPOMEDIO") ){
				//Es la ruta para leer en el servidor desde una maquina con windows
				exitoRepoMedio = true;
				if (parametroIf.getCodigo().equals("RUTAWINDOWSCARPETASERVIDOR"))
					Parametros.setRutaWindowsCarpetaServidor(parametroIf.getValor());
				else{
					SpiritAlert.createAlert("No existe Parametro de Empresa \"RUTAWINDOWSCARPETASERVIDOR\", debe crearlo", SpiritAlert.WARNING);
				}
				
			} else if (tipoParametroIf.getCodigo().equals("MONLOCAL")){
				Parametros.setCodMoneda(parametroIf.getValor());
			} else if ( tipoParametroIf.getCodigo().equals("DIRECTORIO") ){
				if (parametroIf.getCodigo().equals("DIRECTORIOREPORTES"))
					Parametros.setRutaCarpetaReportes(parametroIf.getValor());
				if (parametroIf.getCodigo().equals("DIRECTORIOSRIDIMM"))
					Parametros.setRutaCarpetaSriDimm(parametroIf.getValor());
				/*else 
					SpiritAlert.createAlert("No existe Parametro de Empresa \"DIRECTORIOREPORTES\", debe crearlo", SpiritAlert.WARNING);*/
			} else if(tipoParametroIf.getCodigo().equals("PORCENTAJE")){
				if (parametroIf.getCodigo().equals("PORCENTAJECOMISION")){
					Parametros.setPorcentajeComision(Double.valueOf(parametroIf.getValor()));
				}
			} else if ( tipoParametroIf.getCodigo().equals("IMPRESION") ){
				if (parametroIf.getCodigo().equals("VISTAPREVIA")){
					boolean habilitar = "S".equals(parametroIf.getValor()); 
					Parametros.setVistaPreviaImpresion(habilitar);
				}
			} 
			
			if ( tipoParametroIf.getCodigo().equals("CRM") ){
				if (parametroIf.getCodigo().equals("EXPRESUPUESTOPRODGAV")){
					Parametros.setExcluirProductoTipoGastosVarios(parametroIf.getValor());
				}
			}				
		}
		if (!usuario.equals("SUPER") && !exitoDiscoMedio){
			SpiritAlert.createAlert("No existe Tipo de Parametro \"DISCOMEDIO\", debe crearlo", SpiritAlert.WARNING);
			//System.exit(0);
		}
		if (!usuario.equals("SUPER") && !exitoRepoMedio){
			SpiritAlert.createAlert("No existe Tipo de Parametro \"REPOMEDIO\", debe crearlo", SpiritAlert.WARNING);
			//System.exit(0);
		}	
		
		ParametroEmpresaIf parametroIVA = findParametroEmpresaByCodigo(parametroEmpresaList, "IVA");
		if (parametroIVA != null)
			Parametros.setIVA(Double.valueOf(parametroIVA.getValor()));
		
		ParametroEmpresaIf parametroICE = findParametroEmpresaByCodigo(parametroEmpresaList, "ICE");
		if (parametroICE != null)
			Parametros.setICE(Double.valueOf(parametroICE.getValor()));
		
		ParametroEmpresaIf defaultLocale = findParametroEmpresaByCodigo(parametroEmpresaList, "LOCALE");
		if (defaultLocale != null) {
			Parametros.setDefaultLocale(defaultLocale.getValor());
			//setModelLocale();
		}
		
		ParametroEmpresaIf parametroMaximaLongitudPreimpresoEstablecimiento = findParametroEmpresaByCodigo(parametroEmpresaList, "MAXLONG_PREIMP_ESTABLECIMIENTO");
		if (parametroMaximaLongitudPreimpresoEstablecimiento != null)
			Parametros.setMaximaLongitudPreimpresoEstablecimiento(Integer.valueOf(parametroMaximaLongitudPreimpresoEstablecimiento.getValor()));
		
		ParametroEmpresaIf parametroMaximaLongitudPreimpresoPuntoEmision = findParametroEmpresaByCodigo(parametroEmpresaList, "MAXLONG_PREIMP_PUNTOEMISION");
		if (parametroMaximaLongitudPreimpresoPuntoEmision != null)
			Parametros.setMaximaLongitudPreimpresoPuntoEmision(Integer.valueOf(parametroMaximaLongitudPreimpresoPuntoEmision.getValor()));
		
		ParametroEmpresaIf parametroMaximaLongitudPreimpresoSecuencial = findParametroEmpresaByCodigo(parametroEmpresaList, "MAXLONG_PREIMP_SECUENCIAL");
		if (parametroMaximaLongitudPreimpresoSecuencial != null)
			Parametros.setMaximaLongitudPreimpresoSecuencial(Integer.valueOf(parametroMaximaLongitudPreimpresoSecuencial.getValor()));
		
		ParametroEmpresaIf parametroMaximaLongitudPreimpresoAutorizacion = findParametroEmpresaByCodigo(parametroEmpresaList, "MAXLONG_PREIMP_AUTORIZACION");
		if (parametroMaximaLongitudPreimpresoAutorizacion != null)
			Parametros.setMaximaLongitudPreimpresoAutorizacion(Integer.valueOf(parametroMaximaLongitudPreimpresoAutorizacion.getValor()));
		
		ParametroEmpresaIf patronPreimpreso = findParametroEmpresaByCodigo(parametroEmpresaList, "PATRON_PREIMPRESO");
		if (patronPreimpreso != null)
			Parametros.setPatronPreimpreso(patronPreimpreso.getValor());
		
		ParametroEmpresaIf maxLongitudClienteDireccionNotaCredito = findParametroEmpresaByCodigo(parametroEmpresaList, "MAX_LONG_CLIENTE_DIRECCION_NC");
		if (maxLongitudClienteDireccionNotaCredito != null)
			Parametros.setMaxLongitudClienteDireccionNotaCredito(Integer.valueOf(maxLongitudClienteDireccionNotaCredito.getValor()));		
		
		/*ParametroEmpresaIf parametroCarpetaReportes = findParametroEmpresaByCodigo(parametroEmpresaList, "DIRECTORIOREPORTES");
		if (parametroCarpetaReportes != null)
			Parametros.setRutaCarpetaReportes(parametroCarpetaReportes.getValor());*/ 
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		if(Parametros.getEmpresa() != null)
			queryMap.put("empresaId", ((EmpresaIf) Parametros.getEmpresa()).getId());
		
		queryMap.put("predeterminado", SpiritConstants.getOptionYes().substring(0,1));
		
		try {
			Iterator<PlanCuentaIf> it = SessionServiceLocator.getPlanCuentaSessionService().findPlanCuentaByQuery(queryMap).iterator();
			if (it.hasNext()) {
				PlanCuentaIf planCuentaPredeterminado = it.next();
				Parametros.setPlanCuentaPredeterminado(planCuentaPredeterminado);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al establecer plan de cuentas predeterminado", SpiritAlert.ERROR);
		}
		
		try {
			Iterator<MonedaIf> it = SessionServiceLocator.getMonedaSessionService().findMonedaByPredeterminada(SpiritConstants.getOptionYes().substring(0,1)).iterator();
			if (it.hasNext()) {
				MonedaIf monedaPredeterminada = it.next();
				Parametros.setMonedaPredeterminada(monedaPredeterminada);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al establecer moneda predeterminada", SpiritAlert.ERROR);
		}
	}
	
	private void setModelLocale() {
		String[] defaultLocale = Parametros.getDefaultLocale().split(SpiritConstants.getSplitCharacter());
		Locale locale = Locale.getDefault();
		locale = new Locale(defaultLocale[0], defaultLocale[1]); 
		Locale.setDefault(locale);	
	}

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
			Iterator menusIt = menusUsuarioByRoles.keySet().iterator();
			while (menusIt.hasNext()) {
				String menuCodigo = (String) menusIt.next();
				Map submenusMap = (Map) menusUsuarioByRoles.get(menuCodigo);
				List<MenuIf> menuList = new ArrayList<MenuIf>();
				Iterator submenusIt = submenusMap.keySet().iterator();
				while (submenusIt.hasNext()) {
					String submenuCodigo = (String) submenusIt.next();
					MenuIf submenu = (MenuIf) submenusMap.get(submenuCodigo);
					menuList.add(submenu);
				}
				Collections.sort(menuList, menuByFavoritoSorter);
				Iterator menuListIt = menuList.iterator();
				Map submenusSortedMap = new LinkedHashMap();
				while(menuListIt.hasNext()) {
					MenuIf submenu = (MenuIf) menuListIt.next();
					submenusSortedMap.put(submenu.getCodigo(), submenu);
				}
				menusUsuarioByRoles.put(menuCodigo, submenusSortedMap);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		
		long fin=System.currentTimeMillis();
		System.out.println("---------------------CargarModulos_Menus_SubMenusByRoles: "+(fin-start)/1000+" seg");
	}
	
	private static Comparator<MenuIf> menuByFavoritoSorter = new Comparator<MenuIf>() {
		public int compare(MenuIf menu1, MenuIf menu2) {
			if (menu1.getFavorito() == null)
				return 1;
			if (menu2.getFavorito() ==  null)
				return -1;
			return menu1.getFavorito().compareTo(menu2.getFavorito());
		}
	};
	
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
	
	private static Map mapearRolOpcion() {
		Map rolOpcionMap = new HashMap();
		try {	
			Iterator rolOpcionIterator = SessionServiceLocator.getRolOpcionSessionService().getRolOpcionList().iterator();
			while (rolOpcionIterator.hasNext()) {
				RolOpcionIf rolOpcion = (RolOpcionIf) rolOpcionIterator.next();
				rolOpcionMap.put(rolOpcion.getId(), rolOpcion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error!", SpiritAlert.ERROR);
		}
		
		return rolOpcionMap;
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
	
	private static ParametroEmpresaIf findParametroEmpresaByTipoParametroId(Collection parametroEmpresaList, Long tipoParametroId) {
		Iterator it = parametroEmpresaList.iterator();
		while (it.hasNext()) {
			ParametroEmpresaIf pe = (ParametroEmpresaIf) it.next();
			if (pe.getTipoparametroId().compareTo(tipoParametroId) == 0)
				return pe;
		}
		
		return null;
	}
	
	private static ParametroEmpresaIf findParametroEmpresaByCodigo(Collection parametroEmpresaList, String codigo) {
		Iterator it = parametroEmpresaList.iterator();
		while (it.hasNext()) {
			Object[] o = (Object[]) it.next();
			ParametroEmpresaIf pe = (ParametroEmpresaIf) o[0];
			if (pe.getCodigo().equals(codigo))
				return pe;
		}
		
		return null;
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
	
	/*
	public static MenuSessionService getMenuSessionService() {
		try {
			return (MenuSessionService) ServiceLocator.getService(ServiceLocator.MENUSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	public static RolSessionService getRolSessionService() {
		try {
			return (RolSessionService) ServiceLocator.getService(ServiceLocator.ROLSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static RolUsuarioSessionService getRolUsuarioSessionService() {
		try {
			return (RolUsuarioSessionService) ServiceLocator.getService(ServiceLocator.ROLUSUARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static RolOpcionSessionService getRolOpcionSessionService() {
		try {
			return (RolOpcionSessionService) ServiceLocator.getService(ServiceLocator.ROLOPCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}	
	
	public static TipoParametroSessionService getTipoParametroSessionService() {
		try {
			return (TipoParametroSessionService) ServiceLocator.getService(ServiceLocator.TIPOPARAMETROSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static ParametroEmpresaSessionService getParametroEmpresaContactoSessionService() {
		try {
			return (ParametroEmpresaSessionService) ServiceLocator.getService(ServiceLocator.PARAMETROEMPRESASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}*/
}
