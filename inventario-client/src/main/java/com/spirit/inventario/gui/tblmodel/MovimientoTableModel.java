package com.spirit.inventario.gui.tblmodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.entity.MovimientoIf;
import com.spirit.inventario.helper.KardexData;
import com.spirit.inventario.helper.MovimientoConsultaData;

public class MovimientoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<MovimientoConsultaData> listaKardex = null;
	private List<ColumnModel> listaColumnas = null;

	public MovimientoTableModel() {
		listaColumnas = new ArrayList<ColumnModel>();
		listaKardex = new ArrayList<MovimientoConsultaData>();
		listaColumnas.add(new ColumnModel("Codigo", false, 80));
		listaColumnas.add(new ColumnModel("Tipo Doc.", false, 80));
		listaColumnas.add(new ColumnModel("Fecha Ingreso", false, 80));
		listaColumnas.add(new ColumnModel("Bodega", false, 80));
		listaColumnas.add(new ColumnModel("Bodega Ref", false, 80));
		listaColumnas.add(new ColumnModel("Observacion", false, 80));
		listaColumnas.add(new ColumnModel("Estado", false, 80));
	}

	public void addRow(MovimientoConsultaData kardexData) {
		listaKardex.add(kardexData);
		fireTableDataChanged();
	}

	public void refresh(List<MovimientoConsultaData> kardexDataList) {
		clean();
		for (MovimientoConsultaData kardexData : kardexDataList) {
			listaKardex.add(kardexData);
		}
		fireTableDataChanged();
	}

	public void clean() {
		int last = getRowCount();
		listaKardex.clear();
		fireTableRowsDeleted(0, last);
	}

	public void remove(int row) {
		listaKardex.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return listaColumnas.get(column).isEditable();
	}

	public String getColumnName(int column) {
		return listaColumnas.get(column).getCabecera();
	}

	public int getRowCount() {
		return listaKardex.size();
	}
	
	public MovimientoConsultaData getRow(int row) {
		return this.listaKardex.get(row);
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		MovimientoConsultaData kardexData = listaKardex.get(row);
		switch (column) {
		case 0: 
			return kardexData.getCodigo();
		case 1:// 
			return kardexData.getTipoDocumento(); //TODO
		case 2:
			return kardexData.getFechaIngreso();
		case 3:
			return kardexData.getBodega(); //TODO
		case 4:
			return kardexData.getBodegaRef(); //TODO
		case 5:
			return getEstado(kardexData.getObservacion());
		case 6:
			return getEstado(kardexData.getEstado());
		default:
			return null;
		}
	}
	
	private String getEstado(String codigo)
	{
		if(codigo.equalsIgnoreCase("I"))
			return "Inacivo";
		else if(codigo.equalsIgnoreCase("P"))
			return "Pendiente";
		else
			return codigo;
	}

}