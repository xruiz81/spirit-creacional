package com.spirit.contabilidad.gui.tblmodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.gui.tblmodel.ColumnModel;
import com.spirit.contabilidad.gui.data.MovimientoCuentaData;


public class MovimientoCuentasTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<MovimientoCuentaData> listaMovCuentas = null;
	private List<ColumnModel> listaColumnas = null;

	public MovimientoCuentasTableModel() {
		
		listaColumnas = new ArrayList<ColumnModel>();
		listaMovCuentas = new 

ArrayList<MovimientoCuentaData>();
		listaColumnas.add(new ColumnModel("Asiento", false, 

80));
		listaColumnas.add(new ColumnModel("Comprobante", false, 

80));
		listaColumnas.add(new ColumnModel("Fecha", false, 80));
		listaColumnas.add(new ColumnModel("Glosa", false, 80));
		listaColumnas.add(new ColumnModel("Débitos", false, 

80));
		listaColumnas.add(new ColumnModel("Créditos", false, 

80));
		listaColumnas.add(new ColumnModel("Asiento", false, 

0));		 
	}

	public void addRow(MovimientoCuentaData movimientoCuentaData) {
		listaMovCuentas.add(movimientoCuentaData);
		fireTableDataChanged();
	}

	public void refresh(List<MovimientoCuentaData> 

movimientoCuentaDataList) {
		clean();
		for (MovimientoCuentaData movimientoCuentaData : 

movimientoCuentaDataList) {
			listaMovCuentas.add(movimientoCuentaData);
		}
		fireTableDataChanged();
	}

	public void clean() {
		int last = getRowCount();
		listaMovCuentas.clear();
		fireTableRowsDeleted(0, last);
	}

	public void remove(int row) {
		listaMovCuentas.remove(row);
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
		return listaMovCuentas.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		MovimientoCuentaData movimientoCuentaData = 

listaMovCuentas.get(row);
		switch (column) {
		case 0: // SUCURSAL
			return movimientoCuentaData.getNumeroAsiento();
		case 1:// 
			return 

movimientoCuentaData.getCodigoComprobante();
		case 2:
			return movimientoCuentaData.getFechaAsiento();
		case 3:
			return movimientoCuentaData.getGlosa();
		case 4:
			return 

movimientoCuentaData.getDebe().doubleValue();
		case 5:
			return 

movimientoCuentaData.getHaber().doubleValue();	
		case 6:
			return movimientoCuentaData.getAsiento();	

		 
		default:
			return null;
		}
	}

	public List<MovimientoCuentaData> getListaMovimientosCuentas() 

{
		return listaMovCuentas;
	}

}