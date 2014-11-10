package com.spirit.inventario.gui.tblmodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.gui.helper.ConsultasHelper;

public class SolicitudTranferenciaDetalleTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<SolicitudTransferenciaDetalleRowModel> listaSolicitudTransferenciaDetalleRow = null;
	private List<SolicitudTransferenciaDetalleRowModel> listaMovimientoDetalleRowEliminados = null;
	
	private List<SolicitudTransferenciaDetalleIf> listaSolicitudTransferenciaDetalleIf = new ArrayList<SolicitudTransferenciaDetalleIf>();
	private List<SolicitudTransferenciaDetalleIf> listaSolicitudTransferenciaDetalleIfEliminados = new ArrayList<SolicitudTransferenciaDetalleIf>();

	private List<ColumnModel> listaColumnas = null;

	public SolicitudTranferenciaDetalleTableModel() {
		listaSolicitudTransferenciaDetalleRow = new ArrayList<SolicitudTransferenciaDetalleRowModel>();
		listaMovimientoDetalleRowEliminados = new ArrayList<SolicitudTransferenciaDetalleRowModel>();
		listaColumnas = new ArrayList<ColumnModel>();
		listaColumnas.add(new ColumnModel("Producto", false, 80));
		listaColumnas.add(new ColumnModel("Lote", false, 80));
		listaColumnas.add(new ColumnModel("Cantidad", false, 80));
	}

	public void addSolicitudTransferenciaDetalle(SolicitudTransferenciaDetalleIf solicitudTransferenciaDetalleIf) {
		listaSolicitudTransferenciaDetalleRow.add(new SolicitudTransferenciaDetalleRowModel(
				solicitudTransferenciaDetalleIf));
		fireTableDataChanged();
	}

	public void addRow(SolicitudTransferenciaDetalleRowModel solicitudTransferenciaDetalleRowModel) {
		listaSolicitudTransferenciaDetalleRow.add(solicitudTransferenciaDetalleRowModel);
		fireTableDataChanged();
	}

	public SolicitudTransferenciaDetalleIf getSolicitudTransferenciaDetalleIf(int row) {
		return this.listaSolicitudTransferenciaDetalleRow.get(row).getSolicitudTransferenciaDetalleIf();
	}

	public void clean() {
		int last = getRowCount();
		this.listaSolicitudTransferenciaDetalleRow.clear();
		this.listaMovimientoDetalleRowEliminados.clear();
		fireTableRowsDeleted(0, last);
	}
	
	public void cleanEliminados() {
		this.listaMovimientoDetalleRowEliminados.clear();
	}

	public void remove(int row) {
		SolicitudTransferenciaDetalleRowModel movimientoDetalleRowModel = this.listaSolicitudTransferenciaDetalleRow
				.get(row);
		this.listaSolicitudTransferenciaDetalleRow.remove(row);
		this.listaMovimientoDetalleRowEliminados.add(movimientoDetalleRowModel);
		fireTableRowsDeleted(row, row);
	}

	public List<SolicitudTransferenciaDetalleIf> getAllSolicitudTransferenciaDetalleIf() {
		listaSolicitudTransferenciaDetalleIf.clear();
		for (int i = 0; i < getRowCount(); i++) {
			listaSolicitudTransferenciaDetalleIf.add(getSolicitudTransferenciaDetalleIf(i));
		}
		return listaSolicitudTransferenciaDetalleIf;
	}
	

	public List<SolicitudTransferenciaDetalleIf> getAllSolicitudTransferenciaDetalleIfEiminados() {
		listaSolicitudTransferenciaDetalleIfEliminados.clear();
		for (SolicitudTransferenciaDetalleRowModel row : listaMovimientoDetalleRowEliminados) {
			listaSolicitudTransferenciaDetalleIfEliminados.add(row
					.getSolicitudTransferenciaDetalleIf());
		}
		return listaSolicitudTransferenciaDetalleIfEliminados;
	}

	public SolicitudTransferenciaDetalleRowModel getRow(int row) {
		return this.listaSolicitudTransferenciaDetalleRow.get(row);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return listaColumnas.get(column).isEditable();
	}

	public String getColumnName(int column) {
		return listaColumnas.get(column).getCabecera();
	}

	public int getRowCount() {
		return listaSolicitudTransferenciaDetalleRow.size();
	}

	public int getColumnCount() {
		return this.listaColumnas.size();
	}

	public Object getValueAt(int row, int column) {
		SolicitudTransferenciaDetalleRowModel solicitudTransferenciaDetalleRow = listaSolicitudTransferenciaDetalleRow
				.get(row);
		switch (column) {
		case 0:// Producto
			return ConsultasHelper.getTxtProducto(solicitudTransferenciaDetalleRow
					.getProducto());
		case 1:// Lote
			return solicitudTransferenciaDetalleRow.getLoteIf();
		case 2:
			return solicitudTransferenciaDetalleRow.getCantidad();
		default:
			return null;
		}
	}

	public List<SolicitudTransferenciaDetalleRowModel> getListaSolicitudTransferenciaDetalleRow() {
		return listaSolicitudTransferenciaDetalleRow;
	}

}
