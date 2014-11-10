package com.spirit.client;

import java.awt.Frame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.spirit.nomina.handler.DatoObservacion;

public class ObservacionesModel extends JDObservaciones{

	private static final long serialVersionUID = 4479615891818699929L;
	private String nuevaLinea = "\n";
	
	public ObservacionesModel(Frame owner,String titulo,Collection<DatoObservacion> observaciones) {
		super(owner);
		setTitle(titulo);
		try {
			ordenarByNombreEmpleado(observaciones);
			cargarObservaciones(observaciones);
		} catch (Exception e) {
			SpiritAlert.createAlert("Error al cargar Observaciones !!", SpiritAlert.ERROR);
		}
		this.pack();
        setModal(false);
        setVisual();
	}
	
	private void cargarObservaciones( Collection<DatoObservacion> observaciones ){
		StringBuilder sbObservaciones = new StringBuilder();
		for ( DatoObservacion observacion : observaciones ){
			String etiqueta = observacion.getEtiquetaTitulo() != null ? observacion.getEtiquetaTitulo() : "Nombre: ";   
			sbObservaciones.append(etiqueta+observacion.getContenidoTitulo()+nuevaLinea);
			Collection<String> obs = observacion.getDescripcionError();
			for ( String ob : obs ){
				sbObservaciones.append("Descripcion: "+ob+nuevaLinea+nuevaLinea);
			}
		}
		getTxtObservaciones().append(sbObservaciones.toString());
	}
	
	private void ordenarByNombreEmpleado(Collection<DatoObservacion> observaciones){
		Collections.sort((ArrayList<DatoObservacion>)observaciones, ordenarPoNombre);
	}
	
	Comparator<DatoObservacion> ordenarPoNombre = new Comparator<DatoObservacion>(){

		public int compare(DatoObservacion o1, DatoObservacion o2) {
			return o1.getContenidoTitulo().compareTo(o2.getContenidoTitulo());
		}
		
	};
	
	
	private void setVisual(){
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width/6), //- getWidth()) / 6, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3
        		);
		setVisible(true);
        repaint();
	}

}
