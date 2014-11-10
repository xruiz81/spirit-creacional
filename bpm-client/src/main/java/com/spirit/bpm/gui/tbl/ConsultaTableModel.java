/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 * 
 * @author Administrador
 */
public abstract class ConsultaTableModel<T> extends AbstractTableModel
		implements TableConsultaIf<T> {

	private List<T> initialDataList = null;
	private List<T> deletedDataList = null;
	private List<T> updatedDataList = null;
	private List<T> sendToDataList = null;
	private List<MyTableColumn> tableColumnList = null;
	protected JTable tableRef = null;

	public ConsultaTableModel() {
		tableColumnList = new ArrayList<MyTableColumn>();
		initialDataList = new ArrayList<T>();
		deletedDataList = new ArrayList<T>();
		updatedDataList = new ArrayList<T>();
		sendToDataList = new ArrayList<T>();
	}

	public void setTableRef(JTable tableRef) {
		this.tableRef = tableRef;
		configurarTabla(tableRef);
		setColumns();
		tableRef.setModel(this);
		configColumns();
	}

	private void configurarTabla(JTable table) {
		// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}

	protected void addColumn(MyTableColumn myTableColumn) {
		tableColumnList.add(myTableColumn);
	}

	private void configColumns() {

		TableColumn tmp = null;
		for (MyTableColumn tableColumn : tableColumnList) {
			tmp = getTableRef().getColumn(tableColumn.getColumnName());
			tmp.setResizable(tableColumn.isResizable());
			tmp.setWidth(tableColumn.getWith() == 0 ? tmp.getWidth()
					: tableColumn.getWith());
			tmp.setPreferredWidth(tableColumn.getPreferedWidth() == 0 ? tmp
					.getPreferredWidth() : tableColumn.getPreferedWidth());
			tmp.setMinWidth(tableColumn.getMinWidth() == 0 ? tmp.getMinWidth()
					: tableColumn.getMinWidth());
			tmp.setMaxWidth(tableColumn.getMaxWith() == 0 ? tmp.getMaxWidth()
					: tableColumn.getMaxWith());
			tmp
					.setCellRenderer(tableColumn.getCellRenderer() != null ? tableColumn
							.getCellRenderer()
							: tmp.getCellRenderer());
			tmp.setCellEditor(tableColumn.getCellEditor() != null ? tableColumn
					.getCellEditor() : tmp.getCellEditor());
		}

	}

	public void refresh() {
		int last = getRowCount();
		updatedDataList.clear();
		deletedDataList.clear();
		for (T dataRow : initialDataList) {
			updatedDataList.add(dataRow);
		}
		fireTableDataChanged();
	}

	public boolean isRepetido(T dataRow) {
		return getUpdatedDataList().contains(dataRow);
	}

	public void addRow(T dataRow) throws Exception {
		if (isRepetido(dataRow)) {
			throw new Exception("Registro repetido..");
		}

		sendToDataList.add(dataRow);
		updatedDataList.add(dataRow);
		fireTableDataChanged();
	}

	public void fillData(List<T> pDataList) {
		cleanData();
		for (T dataRow : pDataList) {
			initialDataList.add(dataRow);
			updatedDataList.add(dataRow);
		}
		fireTableDataChanged();
	}

	public void cleanData() {
		if (initialDataList.size() <= 0) {
			return;
		}
		int last = getRowCount();

		initialDataList.clear();
		updatedDataList.clear();
		deletedDataList.clear();
		sendToDataList.clear();
		fireTableRowsDeleted(0, last - 1);
	}

	public void remove(int row) {
		T t = getRow(row);
		updatedDataList.remove(row);
		deletedDataList.add(t);
		fireTableRowsDeleted(row, row);
	}

	public void removeSelected() {
		int[] indices = getTableRef().getSelectedRows();
		List<T> listaTemporal = new ArrayList<T>();
		for (int i : indices) {
			T t = getRow(i);
			// System.out.println("----------SACANDO INDEX " + i + " " + t);
			listaTemporal.add(t);
			if (!updatedDataList.contains(t)) {
				deletedDataList.remove(t);
			} else {
				deletedDataList.add(t);
			}
		}
		updatedDataList.removeAll(listaTemporal);
		sendToDataList.removeAll(listaTemporal);
		fireTableRowsDeleted(indices[0], indices[indices.length - 1]);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return this.tableColumnList.get(column).isEditable();
	}

	@Override
	public String getColumnName(int column) {
		return this.tableColumnList.get(column).getColumnName();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class c = this.tableColumnList.get(columnIndex).getColumnClass();
		if (c != null) {
			return c;
		} else {
			return super.getColumnClass(columnIndex);
		}
	}

	public int getRowCount() {
		return updatedDataList.size();
	}

	public int getColumnCount() {
		return this.tableColumnList.size();
	}

	public T getRow(int row) {
		return updatedDataList.get(row);
	}

	public T getSelected() {
		if (getTableRef().getSelectedRow() >= 0) {
			return getRow(getTableRef().getSelectedRow());
		}
		return null;
	}

	public Object getValueAt(int row, int column) {
		return getValue(getRow(row), row, column);
	}

	public List<T> getDeletedDataList() {
		return deletedDataList;
	}

	public List<T> getUpdatedDataList() {
		return updatedDataList;
	}

	public List<T> getSendToDataList() {
		return sendToDataList;
	}

	public JTable getTableRef() {
		return tableRef;
	}
}
