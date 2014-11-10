package com.spirit.timeTracker.gui.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.EmpresaSessionService;
import com.spirit.general.session.OficinaSessionService;
import com.spirit.general.session.ParametroEmpresaSessionService;
import com.spirit.general.session.TipoParametroSessionService;
import com.spirit.servicelocator.ServiceLocator;

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
				nombreEmpresa = getEmpresaSessionService().getEmpresa(idEmpresa).getNombre();
				Parametros.setNombreEmpresa(nombreEmpresa);
				logoEmpresa = getEmpresaSessionService().getEmpresa(idEmpresa).getLogo();
				urlEmpresa = getEmpresaSessionService().getEmpresa(idEmpresa).getWeb();
				if (!"A".equals(SpiritLogin._usuario.getTipousuario())) {
					idOficina = SpiritLogin._idOficinaUsuario;
					Parametros.setNombreOficina(getOficinaSessionService().getOficina(idOficina).getNombre());
				} else
					Parametros.setNombreOficina("");
			} else
				nombreEmpresa = "";
			//TODO: Eliminar variable usuario cuando nadie la referencie.
			usuario = SpiritLogin._usuario.getUsuario().toUpperCase();
			Parametros.setUsuario(SpiritLogin._usuario.getUsuario().toUpperCase());
			
			//Etxraigo los tipos de parametros segun la empresa
			Collection tipoParametroCollection = getTipoParametroSessionService().findTipoParametroByEmpresaId(idEmpresa);
			Iterator itTipoParametro = tipoParametroCollection.iterator();
			
			//Recorro los tipos de parametros encontrados para esta empresa
			while(itTipoParametro.hasNext()){
				//Extraigo el objeto 
				TipoParametroIf tipoParametroIf = (TipoParametroIf) itTipoParametro.next();
				//Saco el obejto parametro segun el id del tipo parametro
				
				if(!getParametroEmpresaContactoSessionService().findParametroEmpresaByTipoparametroId(tipoParametroIf.getId()).isEmpty()){
					ParametroEmpresaIf parametroIf = (ParametroEmpresaIf) getParametroEmpresaContactoSessionService().findParametroEmpresaByTipoparametroId(tipoParametroIf.getId()).iterator().next();
					
					//Veo que tipo de parametro es
					if(tipoParametroIf.getCodigo().equals("DISCOMEDIO")){
						rutaServidorArchivos = parametroIf.getValor() + ":\\\\" + nombreEmpresa + "\\\\";
					} else if (tipoParametroIf.getCodigo().equals("MONLOCAL")){
						Parametros.setCodMoneda(parametroIf.getValor());
					}
				}
			}
			
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
			e.printStackTrace();
		}
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
