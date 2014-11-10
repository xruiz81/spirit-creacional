package com.spirit.bi.cubes.facturacion.dim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.spirit.bi.Dimension;
import com.spirit.bi.entity.BiTimeDimEJB;

public class TimeDim extends Dimension {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	@Override
	public Object construir(Object objetoConsulta) {
		Date fechaFactura = null;
		try {
			fechaFactura = dateFormat.parse(objetoConsulta.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaFactura);
		BiTimeDimEJB biTimeDimEJB = new BiTimeDimEJB();
		int mes = cal.get(Calendar.MONTH) + 1;
		int dia=cal.get(Calendar.DAY_OF_MONTH);
		int semestre=(mes >= 1 && mes <= 6) ? 1 : 2;
		
		biTimeDimEJB.setFecha(new java.sql.Date(fechaFactura.getTime()));
		biTimeDimEJB.setOrigenId((Long) objetoConsulta);
		
		biTimeDimEJB.setSemestreId(semestre);
		biTimeDimEJB.setSemestre("S"+semestre);

		biTimeDimEJB.setAnioId(cal.get(Calendar.YEAR));
		biTimeDimEJB.setAnio(String.valueOf(biTimeDimEJB.getAnioId()));
		
		biTimeDimEJB.setDiaId(dia);
		biTimeDimEJB.setDia("D"+biTimeDimEJB.getDiaId());

		biTimeDimEJB.setMesId(mes);
		biTimeDimEJB.setMes(cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));
		
		biTimeDimEJB.setSemanaId(cal.get(Calendar.WEEK_OF_YEAR));
		biTimeDimEJB.setSemana("SM"+biTimeDimEJB.getSemanaId());
		
		biTimeDimEJB.setQuarterId(getQuarter(mes));
		biTimeDimEJB.setQuarter("Q"+biTimeDimEJB.getQuarterId());
		
		biTimeDimEJB.setTrimestreId(getTrimestre(mes));
		biTimeDimEJB.setTrimestre("T"+biTimeDimEJB.getTrimestreId());
		
		biTimeDimEJB.setQuincenaId(getQuincena(dia));
		biTimeDimEJB.setQuincena("QN"+biTimeDimEJB.getQuincenaId());
		
		

		return biTimeDimEJB;
	}

	private int getTrimestre(int mes) {
		if (mes >= 1 && mes < 4) {
			return 1;
		} else if (mes > 4 && mes <= 8)
			return 2;
		else
			return 3;
	}

	private int getQuarter(int mes) {
		if (mes >= 1 && mes <= 3) {
			return 1;
		} else if (mes > 3 && mes <= 6)
			return 2;
		else if (mes > 6 && mes <= 9)
			return 3;
		else
			return 4;
	}

	private int getQuincena(int dia) {
		if (dia >= 1 && dia <= 15)
			return 1;
		else
			return 2;
	}

	@Override
	public Object construirBlanco() {
		BiTimeDimEJB biTimeDimEJB = new BiTimeDimEJB();
		biTimeDimEJB.setOrigenId(ID_SIN);
		biTimeDimEJB.setAnioId(0);
		return biTimeDimEJB;
	}

	@Override
	public Object consultar(Object origenID) {
		return origenID;
	}

	@Override
	public Class getDataClass() {
		// TODO Auto-generated method stub
		return BiTimeDimEJB.class;
	}

}
