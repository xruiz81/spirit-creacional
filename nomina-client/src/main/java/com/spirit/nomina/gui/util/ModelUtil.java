package com.spirit.nomina.gui.util;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRolFormaPago;
import com.spirit.util.Utilitarios;

public abstract class ModelUtil extends SpiritModelImpl{

	
	public static void establecerCmbAnio(JComboBox combo){
		Calendar cal = new GregorianCalendar();
		combo.setSelectedItem(String.valueOf(cal.get(Calendar.YEAR)));
	}
	
	
	public static void cargarCmbTipoRol(JComboBox combo,TipoRolIf inicial,Object objectoSeleccionado){
		
		try {
			Collection<TipoRolIf> tiposRolCollection =  SessionServiceLocator.getTipoRolSessionService().findTipoRolByEmpresaId(Parametros.getIdEmpresa());
			Vector<TipoRolIf> tiposRol = new Vector<TipoRolIf>(tiposRolCollection);
			tiposRol.add(0, inicial);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposRol);
			combo.setModel(comboModel);
			refreshCombo(combo, (List)tiposRolCollection);
			combo.setSelectedItem(objectoSeleccionado);
			combo.repaint();
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}
	
	public static void cargarCmbTipoContrato(JComboBox combo){
		try {
			Collection<TipoContratoIf> tiposContratoCollection =  SessionServiceLocator.getTipoContratoSessionService().findTiposContratosUsados(Parametros.getIdEmpresa());
			Vector<TipoContratoIf> tiposContrato = new Vector<TipoContratoIf>(tiposContratoCollection);
			DefaultComboBoxModel comboModel = new DefaultComboBoxModel(tiposContrato);
			combo.setModel(comboModel);
			refreshCombo(combo, (List)tiposContratoCollection);
			/*for (int i=0 ; i<comboModel.getSize() ; i++){
				TipoContratoIf tc = (TipoContratoIf) getCmbTipoContrato().getItemAt(i);
				if ( "RD".equals(tc.getCodigo()) ){
					tipoContratoIf = tc;
					getCmbTipoContrato().setSelectedIndex(i);
					break;
				}
			}	
			getCmbTipoContrato().repaint();*/
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("Error en la cargar de los tipos de rol", SpiritAlert.WARNING);
			e.printStackTrace();
		};
	}
	
	public static Vector<Object> crearFilaTablaRolPagoDetalleAportesDecimosPagos(String nombreMes,
			TipoRolFormaPago formaPago,Map<String, Object> mapa,Map<Long, TipoPagoIf> tipoPagoMap,
			Map<Long, String> cuentaBancariaMap)
	throws GenericBusinessException {
		String nombreEmpleado = (String)mapa.get("nombreEmpleado");
		
		Double valor = null;
		if ( formaPago == TipoRolFormaPago.PERIODO ){
			valor = (Double)mapa.get(nombreMes);
			valor = valor != null ? valor : (Double)mapa.get("total") ; 
		} else 
			valor = (Double)mapa.get("total") ;
		valor = Utilitarios.redondeoValor( valor );
		
		Double anticipos = Utilitarios.redondeoValor( mapa.get("anticipos") != null ? (Double)mapa.get("anticipos") : 0D );
		Double total = Utilitarios.redondeoValor( valor - anticipos );
		Collection<RolPagoDetalleIf> detalles =  (Collection<RolPagoDetalleIf>)mapa.get("detallesRolPago");
		RolPagoDetalleIf detalle = null;
		if ( detalles.size() > 0 )
			detalle = detalles.iterator().next();

		Vector<Object> fila = new Vector<Object>();
		fila.add(false);
		fila.add(nombreEmpleado);
		fila.add(valor);
		fila.add(anticipos);
		fila.add(total);
		fila.add(detalle!=null?tipoPagoMap.get(detalle.getTipoPagoId()):" - ");
		fila.add(detalle!=null?cuentaBancariaMap.get(detalle.getCuentaBancariaId()):"");
		fila.add(detalle!=null?detalle.getPreimpreso():"");
		return fila;
	}
	
	public static String nombreCuentaBanco(BancoIf banco ,CuentaBancariaIf cuentaBancaria){
		return banco.getNombre() + ", Cta. " + cuentaBancaria.getCuenta();
	}
	
}
