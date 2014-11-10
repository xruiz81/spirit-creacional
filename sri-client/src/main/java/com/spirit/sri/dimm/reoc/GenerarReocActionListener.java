package com.spirit.sri.dimm.reoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.jidesoft.utils.SystemInfo;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.sri.dimm.DimmConstantes;
import com.spirit.sri.reoc.DetalleCompras;
import com.spirit.sri.reoc.Reoc;

public class GenerarReocActionListener implements ActionListener {

	Reoc reocGlobal = null;
	String nombreArchivo = "";

	public GenerarReocActionListener(){
	}

	public GenerarReocActionListener(Reoc reoc){
		this.reocGlobal = reoc;
	}

	public void actionPerformed(ActionEvent event) {
		if (reocGlobal!=null){
			try{
				eliminarNulosDetalleCompras();
				//eliminarNulosDetalleVentas();
				//eliminarNulosFacturasAnuladas();
				//if (ivaGlobal.isValid()){
				FileWriter fw = null;
				try {
					String directorioSriDimm = Parametros.getRutaCarpetaSriDimm();
					if (SystemInfo.isLinux() || SystemInfo.isUnix() || SystemInfo.isMacOSX() || SystemInfo.isAnyMac() || SystemInfo.isMacClassic())
						directorioSriDimm = System.getProperty("user.home") + System.getProperty("file.separator") + "dimm" + System.getProperty("file.separator") + ((EmpresaIf) Parametros.getEmpresa()).getNombre().toLowerCase() + System.getProperty("file.separator");
					if ( directorioSriDimm != null && !"".equals(directorioSriDimm) ){
						File directorio = new File(directorioSriDimm.trim());
						//File directorio = new File("c:\\XML_ANEXO_TRANSACCIONAL\\");
						if ( !directorio.isDirectory() ){
							if (confimarCrearDirectorio(directorioSriDimm.trim())){
								boolean directorioCreado = directorio.mkdirs();
								//directorio = new File(directorioSriDimm);
								if ( !directorioCreado ){
									throw new GenericBusinessException("Error en la creacion del directorio !!");
								}
							} else 
								throw new GenericBusinessException("No existe ruta "+directorioSriDimm+" en el Disco C");
						}
						fw = new FileWriter(new File(directorio,nombreArchivo+".xml"));
						reocGlobal.marshal(fw);
						fw.close();
						SpiritAlert.createAlert("Archivo creado con exito !!",SpiritAlert.INFORMATION);
					} else {
						SpiritAlert.createAlert("Parametro de empresa DIRECTORIOSRIDIMM no existe !!",SpiritAlert.WARNING);
					}
				} catch (IOException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error en creacion de Archivo:\n"+e.getMessage(),SpiritAlert.WARNING);
				} catch (MarshalException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error en marshal:\n"+e.getMessage(),SpiritAlert.WARNING);
				} catch (ValidationException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Error en validacion de expresion del Schema:\n"+e.getMessage(),SpiritAlert.WARNING);
				} catch(GenericBusinessException e){
					e.printStackTrace();
					SpiritAlert.createAlert(e.getMessage(),SpiritAlert.WARNING);
				} catch(Exception e){
					e.printStackTrace();
					SpiritAlert.createAlert("Error general al crear la declaracion !!",SpiritAlert.WARNING);
				} 
				finally{
					if (fw!=null)
						fw.close();
				}

				//}
				//else
				//	SpiritAlert.createAlert("Informaci\u00f3n no valida para creaci\u00f3n del archivo",SpiritAlert.WARNING);
			} catch(Exception e){
				e.printStackTrace();
				SpiritAlert.createAlert("Error al generar archivo XML", SpiritAlert.ERROR);
			}

		} else
			SpiritAlert.createAlert("No se gener\u00f3 la declaraci\u00f3n",SpiritAlert.ERROR);
	}
	
	private boolean confimarCrearDirectorio(String directorio){
		Object[] options = {"Si","No"}; 
		int opcion = JOptionPane.showOptionDialog(
				null,"¿Ruta "+directorio+" no existe. ¿Desea crearlo?", "Confirmación"
				,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE
				,null,options,"No");
		if ( opcion == JOptionPane.YES_OPTION )
			return true;
		return false;
	}

	private void eliminarNulosDetalleCompras(){
		DetalleCompras[] detallesCompra = reocGlobal.getCompras().getDetalleCompras();
		ArrayList<DetalleCompras> listaDetalleCompra = new ArrayList<DetalleCompras>();
		for(int i=0;i<detallesCompra.length;i++){
			if (detallesCompra[i]!=null)
				listaDetalleCompra.add(detallesCompra[i]);
		}
		detallesCompra=null;
		detallesCompra = (DetalleCompras[]) listaDetalleCompra.toArray(new DetalleCompras[listaDetalleCompra.size()]);
		reocGlobal.getCompras().setDetalleCompras(detallesCompra);
	}
	
	/*public void refresacarParametroDirectrio(Set<String> parametrosEmpresa) throws GenericBusinessException{
		Map<String,Object> mapaTipo = new HashMap<String,Object>();
		mapaTipo.put("empresaId",Parametros.getIdEmpresa());
		mapaTipo.put("codigo","DIMM_SRI");
		Collection<TipoParametroIf> tipos = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByQuery(mapaTipo);
		if ( tipos.size() == 0  )
			throw new GenericBusinessException("No existe tipo de parametro DIRECTORIO !!");
		for ( TipoParametroIf tp : tipos ){
			
			Map<String,Object> mapaParametro = new HashMap<String,Object>();
			mapaParametro.put("empresaId",Parametros.getIdEmpresa());
			mapaParametro.put("tipoparametroId",tp.getId());
			mapaParametro.put("codigo","DIRECTORIOSRIDIMM");
			Collection<ParametroEmpresaIf> parametros = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByQuery(mapaParametro);
			if ( parametros.size() == 0 )
				throw new GenericBusinessException("Parametro de empresa con valor \"DIRECTORIOSRIDIMM\" no existe !!");
			else if ( parametros.size() > 1 )
				throw new GenericBusinessException("Existe mas de una parametro de Empresa llamado DIRECTORIOSRIDIMM !!");
			for (ParametroEmpresaIf pe : parametros){
				Parametros.setRutaCarpetaSriDimm(pe.getValor());
			} 
		}
		
	}*/

	/*
	private void eliminarNulosDetalleVentas(){
		DetalleVentas[] detallesVenta = ivaGlobal.getVentas().getDetalleVentas();
		ArrayList<DetalleVentas> listaDetalleVenta = new ArrayList<DetalleVentas>();
		for(int i=0;i<detallesVenta.length;i++){
			if (detallesVenta[i]!=null)
				listaDetalleVenta.add(detallesVenta[i]);
		}
		detallesVenta=null;
		detallesVenta = (DetalleVentas[]) listaDetalleVenta.toArray(new DetalleVentas[listaDetalleVenta.size()]);
		ivaGlobal.getVentas().setDetalleVentas(detallesVenta);
	}

	private void eliminarNulosFacturasAnuladas(){
		DetalleAnulados[] detallesAnulados = ivaGlobal.getAnulados().getDetalleAnulados();
		ArrayList<DetalleAnulados> listaFacturasAnuladas = new ArrayList<DetalleAnulados>();
		for(int i=0;i<detallesAnulados.length;i++){
			if (detallesAnulados[i]!=null)
				listaFacturasAnuladas.add(detallesAnulados[i]);
		}
		detallesAnulados=null;
		detallesAnulados = (DetalleAnulados[]) listaFacturasAnuladas.toArray(new DetalleAnulados[listaFacturasAnuladas.size()]);
		ivaGlobal.getAnulados().setDetalleAnulados(detallesAnulados);
	}
	 */
	
	public void setReocGlobal(Reoc reocGlobal) {
		this.reocGlobal = reocGlobal;
	}

	public Reoc getReocGlobal() {
		return reocGlobal;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
