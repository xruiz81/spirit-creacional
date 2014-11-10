package com.spirit.inventario.gui.tblmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.spirit.compras.entity.OrdenCompraDetalleIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.MovimientoDetalleData;
import com.spirit.inventario.entity.MovimientoDetalleIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.gui.helper.ConsultasHelper;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class MovimientoDetalleTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<MovimientoDetalleRowModel> listaMovimientoDetalleRow = null;
	private List<MovimientoDetalleRowModel> listaMovimientoDetalleRowEliminados = null;
	private List<ColumnModel> listaColumnas = null;

	public MovimientoDetalleTableModel() {
		listaMovimientoDetalleRow = new ArrayList<MovimientoDetalleRowModel>();
		listaMovimientoDetalleRowEliminados = new ArrayList<MovimientoDetalleRowModel>();
		listaColumnas = new ArrayList<ColumnModel>();
		listaColumnas.add(new ColumnModel("Doc. Movimiento", false, 80));
		listaColumnas.add(new ColumnModel("Doc. Orden Compra", false, 80));
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Lote", false, 80));
		listaColumnas.add(new ColumnModel("Cantidad", false, 80));
	}

	public void addMovimientoDetalle(MovimientoDetalleIf movimientoDetalleIf) {
		listaMovimientoDetalleRow.add(new MovimientoDetalleRowModel(
				movimientoDetalleIf));
		fireTableDataChanged();
	}

	public void addRow(MovimientoDetalleRowModel movimientoDetalleRowModel) {
		listaMovimientoDetalleRow.add(movimientoDetalleRowModel);
		fireTableDataChanged();
	}

	public void addOrdenCompraDetalle(OrdenCompraDetalleIf ordenCompraDetalleIf) {
		listaMovimientoDetalleRow.add(new MovimientoDetalleRowModel(
				ordenCompraDetalleIf));
		fireTableDataChanged();
	}
	
	public void addSolicitudTransferenciaDetalle(SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf) {
		listaMovimientoDetalleRow.add(new MovimientoDetalleRowModel(
				solicitudTransferenciaDetalleIf));
		fireTableDataChanged();
	}

	public MovimientoDetalleIf getMovimientoDetalleIf(int row) {
		return this.listaMovimientoDetalleRow.get(row).getMovimientoDetalleIf();
	}

	public void clean() {
		int last = getRowCount();
		this.listaMovimientoDetalleRow.clear();
		this.listaMovimientoDetalleRowEliminados.clear();
		fireTableRowsDeleted(0, last);
	}
	
	public void cleanEliminados() {
		this.listaMovimientoDetalleRowEliminados.clear();
	}

	public void remove(int row) {
		MovimientoDetalleRowModel movimientoDetalleRowModel = this.listaMovimientoDetalleRow
				.get(row);
		this.listaMovimientoDetalleRow.remove(row);
		this.listaMovimientoDetalleRowEliminados.add(movimientoDetalleRowModel);
		fireTableRowsDeleted(row, row);
	}

	private List<MovimientoDetalleIf> listaMovimientoDetalleIf = new ArrayList<MovimientoDetalleIf>();
	private List<MovimientoDetalleIf> listaMovimientoDetalleIfEliminados = new ArrayList<MovimientoDetalleIf>();

	public List<MovimientoDetalleIf> getAllMovimientoDetalleIf() {
		listaMovimientoDetalleIf.clear();
		for (int i = 0; i < getRowCount(); i++) {
			listaMovimientoDetalleIf.add(getMovimientoDetalleIf(i));
		}
		return listaMovimientoDetalleIf;
	}
	

	public List<MovimientoDetalleIf> getAllMovimientoDetalleIfEiminados() {
		listaMovimientoDetalleIfEliminados.clear();
		for (MovimientoDetalleRowModel row : listaMovimientoDetalleRowEliminados) {
			listaMovimientoDetalleIfEliminados.add(row
					.getMovimientoDetalleIf());
		}
		return listaMovimientoDetalleIfEliminados;
	}

	public MovimientoDetalleRowModel getRow(int row) {
		return this.listaMovimientoDetalleRow.get(row);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return listaColumnas.get(column).isEditable();
	}

	public String getColumnName(int column) {
		return listaColumnas.get(column).getCabecera();
	}

	public int getRowCount() {
		return listaMovimientoDetalleRow.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		MovimientoDetalleRowModel movimientoDetalleRow = listaMovimientoDetalleRow
				.get(row);
		switch (column) {
		case 0:// Doc Movimiento
			return movimientoDetalleRow.getDocumentoMovimiento();
		case 1:// Doc Orden
			// return movimientoDetalleIf.get
			return movimientoDetalleRow.getDocumentoOrdenCompra();
		case 2:// Producto
			// movimientoDetalleIf.get
			return ConsultasHelper.getTxtProducto(movimientoDetalleRow
					.getProducto());
		case 3:// Lote
			return movimientoDetalleRow.getLoteIf();
		case 4:
			return movimientoDetalleRow.getCantidad();
		default:
			return null;
		}
	}

	public List<MovimientoDetalleRowModel> getListaMovimientoDetalleRow() {
		return listaMovimientoDetalleRow;
	}

}
