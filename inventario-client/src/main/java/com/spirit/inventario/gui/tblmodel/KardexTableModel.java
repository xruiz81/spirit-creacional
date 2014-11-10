package com.spirit.inventario.gui.tblmodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.helper.KardexData;

public class KardexTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<KardexData> listaKardex = null;
	private List<ColumnModel> listaColumnas = null;

	public KardexTableModel() {
		
		listaColumnas = new ArrayList<ColumnModel>();
		listaKardex = new ArrayList<KardexData>();
		listaColumnas.add(new ColumnModel("Sucursal", false, 80));
		listaColumnas.add(new ColumnModel("Bodega", false, 80));
		listaColumnas.add(new ColumnModel("Lote", false, 80));
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Fecha", false, 80));
		listaColumnas.add(new ColumnModel("Movimiento", false, 80));
		listaColumnas.add(new ColumnModel("Cantidad", false, 80));
		listaColumnas.add(new ColumnModel("C.U.", false, 80));
		listaColumnas.add(new ColumnModel("S.V.", false, 80));
		listaColumnas.add(new ColumnModel("Acum.", false, 80));
	}

	public void addRow(KardexData kardexData) {
		listaKardex.add(kardexData);
		fireTableDataChanged();
	}

	public void refresh(List<KardexData> kardexDataList) {
		clean();
		for (KardexData kardexData : kardexDataList) {
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

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		KardexData kardexData = listaKardex.get(row);
		switch (column) {
		case 0: // SUCURSAL
			return kardexData.getSucursal();
		case 1:// 
			return kardexData.getBodega();
		case 2:
			return kardexData.getLote();
		case 3:
			return kardexData.getProducto();
		case 4:
			return kardexData.getFecha();
		case 5:
			return kardexData.getMovimiento();
		case 6:
			return kardexData.getCantidad();
		case 7:
			return kardexData.getTotal();
		case 8:
			return kardexData.getDiferencia();
		case 9:
			return kardexData.getDifAnterior();
		default:
			return null;
		}
	}

	public List<KardexData> getListaKardex() {
		return listaKardex;
	}

}