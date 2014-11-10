package com.spirit.nomina.handler;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.session.ContratoSessionLocal;
import com.spirit.nomina.session.RubroSessionLocal;

@Stateless
public class NominaUtilesSession implements NominaUtilesLocal{
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;
	
	@EJB private RubroSessionLocal rubroLocal;
	
	@EJB private ContratoSessionLocal contratoLocal;
	
	@EJB private UtilitariosSessionLocal utilitariosLocal; 
	
	@EJB private EmpleadoSessionLocal empleadoLocal;
	
	String[] mesesMayusculas = {"ENERO","FEBRERO","MARZO","ABRIL","MAYO",
			"JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"
		};
	
	public Collection<String> getMesesRolPago(Date fechaInicio,Date fechaFin){
		DecimalFormat formatoDosEnteros = new DecimalFormat("00");
		Calendar caInicio = new GregorianCalendar( fechaInicio.getYear()+1900,fechaInicio.getMonth(),fechaInicio.getDate() );
		Calendar caFin = new GregorianCalendar( fechaFin.getYear()+1900,fechaFin.getMonth(),fechaFin.getDate() );
		Collection<String> mapaMesesAnios = new ArrayList<String>();
		for ( int i=0; caInicio.getTime().compareTo( caFin.getTime() ) <= 0; caInicio.add(Calendar.MONTH, 1) ){
			mapaMesesAnios.add(formatoDosEnteros.format(caInicio.get(Calendar.MONTH)+1)
					+"-"+caInicio.get(Calendar.YEAR));
			i++;
		}
		return mapaMesesAnios;
	}
	
	public TipoRol getTipoRolByRubro(RubroIf rubroIf){ 
		if ( rubroIf == null )	//Si es que es eventual, tiene que esta en el mes
			return TipoRol.MENSUAL;
		String nombre = rubroIf.getNombre();
		if ( "P".equals(rubroIf.getTipoRubro()) ){
			if ( nombre.contains("DECIMO") ){
				if ( nombre.contains("TERCER") )
					return TipoRol.DECIMO_TERCERO;
				else if ( nombre.contains("CUARTO") )
					return TipoRol.DECIMO_CUARTO;
			} else if ( nombre.contains("APORTE") && nombre.contains("PATRONAL")){
				return TipoRol.APORTE_PATRONAL;
			} else if ( nombre.contains("FONDO") && nombre.contains("RESERVA")){
				return TipoRol.FONDO_RESERVA;
			} else if ( nombre.contains("SALARIO") && nombre.contains("MINIMO") && nombre.contains("VITAL")  )
				return TipoRol.SALARIO_MINIMO_VITAL;
		} else if ( rubroIf.getFrecuencia().equals("M") ) 
			return TipoRol.MENSUAL;
		else if ( rubroIf.getFrecuencia().equals("Q") )
			return TipoRol.QUINCENAL;
		return null;
	}
	
	public RubroIf getRubroByTipoRol(TipoRol tipoRol) throws GenericBusinessException{
		RubroIf rubroIf = null;
		if ( tipoRol == TipoRol.QUINCENAL ){
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("codigo", "AQ");
			mapa.put("tipoRubro", TipoRubro.QUINCENA.getLetra());
			mapa.put("frecuencia", "Q");
			//mapa.put("nombre", "ANTICIPO%QUINCENA%");
			mapa.put("nombre", "%QUINCENA%");
			Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
			if ( rubros.size() == 0 )
				throw new GenericBusinessException("No existe Rubro que contenga palabras ANTICIPO QUINCENA !!");
			else if ( rubros.size() > 1 )
				throw new GenericBusinessException("Existe mas de un Rubro llamado ANTICIPO QUINCENA !!");
			rubroIf = rubros.iterator().next();
		} else if ( tipoRol == TipoRol.DECIMO_TERCERO ){
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("tipoRubro", TipoRubro.PROVISION.getLetra());
			mapa.put("nombre", "DECIMO%TERCERO%");
			Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
			if ( rubros.size() == 0 )
				throw new GenericBusinessException("No existe Rubro llamado DECIMO TERCERO !!");
			else if ( rubros.size() > 1 )
				throw new GenericBusinessException("Existe mas de un Rubro llamado DECIMO TERCERO !!");
			rubroIf = rubros.iterator().next();
		} else if ( tipoRol == TipoRol.DECIMO_CUARTO ){
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("tipoRubro", TipoRubro.PROVISION.getLetra());
			mapa.put("nombre", "DECIMO%CUARTO%");
			Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
			if ( rubros.size() == 0 )
				throw new GenericBusinessException("No existe Rubro llamado DECIMO CUARTO !!");
			else if ( rubros.size() > 1 )
				throw new GenericBusinessException("Existe mas de un Rubro llamado DECIMO CUARTO !!");
			rubroIf = rubros.iterator().next();
		} else if ( tipoRol == TipoRol.FONDO_RESERVA ){
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("tipoRubro", TipoRubro.PROVISION.getLetra());
			mapa.put("nombre", "FONDO%RESERVA%");
			Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
			if ( rubros.size() > 1 )
					throw new GenericBusinessException("Existe mas de un Rubro llamado FONDO DE RESERVA");
			if ( rubros.size() == 1 )	
				rubroIf = rubros.iterator().next();
		} else if ( tipoRol == TipoRol.APORTE_PATRONAL ){
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("tipoRubro", TipoRubro.PROVISION.getLetra());
			mapa.put("nombre", "APORTE%PATRONAL%");
			Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
			if ( rubros.size() > 1 )
					throw new GenericBusinessException("Existe mas de un Rubro llamado APORTE PATRONAL");
			if ( rubros.size() == 1 )
				rubroIf = rubros.iterator().next();
		} else if ( tipoRol == TipoRol.VACACIONES ){
			Map<String, String> mapa = new HashMap<String, String>();
			mapa.put("tipoRubro", TipoRubro.PROVISION.getLetra());
			mapa.put("nombre", "VACACIONES%");
			Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
			if ( rubros.size() > 1 )
				throw new GenericBusinessException("Existe mas de un Rubro llamado VACACIONES");
			if ( rubros.size() == 1 )
				rubroIf = rubros.iterator().next();
		}else
			throw new GenericBusinessException("Busqueda de Rubro para tipo de Rol "+tipoRol+" no implementado");
		return rubroIf;
	}
	
	public RubroIf buscarRubroUnicoByNombreByTipoRubro(String nombre,String tipoRubro) throws GenericBusinessException{
		Map<String, String> mapa = new HashMap<String, String>();
		//mapa.put("tipoRubro", TipoRubro.PROVISION.getLetra());
		mapa.put("tipoRubro", tipoRubro);
		mapa.put("nombre", nombre);
		Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
		if ( rubros.size() == 1 )
			return rubros.iterator().next();
		else 
			return null;
	}
	
	public RubroIf verificarRubrosEnMapa(Map<Long,RubroIf> mapaRubros,Map<Long,String> mapaTipoRubros,Long idRubro)
	throws GenericBusinessException {
		RubroIf rubroIf = null;
		if ( !mapaRubros.containsKey(idRubro) ){
			rubroIf = rubroLocal.getRubro(idRubro);
			mapaRubros.put(rubroIf.getId(), rubroIf);
			if ( mapaTipoRubros != null )
				mapaTipoRubros.put(rubroIf.getId(), rubroIf.getTipoRubro());
		} else {
			rubroIf = mapaRubros.get(idRubro);
		}
		return rubroIf;
	}
	
	public String verificarEmpleadoEnMapa(Long contratoId,Map<Long, String> mapaContratoIdNombreEmpleado)
	throws GenericBusinessException {
		String nombre = mapaContratoIdNombreEmpleado.get(contratoId);
		if ( nombre == null ){
			ContratoIf contratoIf = contratoLocal.getContrato(contratoId);
			EmpleadoIf empleadoIf = empleadoLocal.getEmpleado(contratoIf.getEmpleadoId());
			//mapaContratoIdNombreEmpleado.put(rolPagoDetalle.getContratoId(), empleadoIf.getNombres()+" "+empleadoIf.getApellidos());
			nombre = empleadoIf.getApellidos()+" "+empleadoIf.getNombres();
			mapaContratoIdNombreEmpleado.put(contratoId, nombre);
		}
		return nombre;
	}
	
	public String[] getMesesMayusculas() {
		return mesesMayusculas;
	}
	
}
