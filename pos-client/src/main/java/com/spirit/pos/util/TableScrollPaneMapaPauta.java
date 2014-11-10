package com.spirit.pos.util;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.jidesoft.grid.AbstractMultiTableModel;
import com.jidesoft.grid.MultiTableModel;
import com.jidesoft.grid.TableCustomizer;
import com.jidesoft.grid.TableModelWrapperUtils;
import com.jidesoft.grid.TableScrollPane;

public class TableScrollPaneMapaPauta {
	protected MultiTableModel totalModel;
	protected MultiTableModel model;
	private String[] HEADER;
	private String[] FOOTER;
	private Object[][] ENTRIES;
	private TableScrollPane table;
	private int headerColumns;
	private int footerColumns;
	private int filas;
	
	public TableScrollPaneMapaPauta(String[] cabecerasFijas, String[] cabecerasVariables, Object[][] datosPrensa, int numeroFilas){
		HEADER = cabecerasFijas;
		FOOTER = cabecerasVariables;
		ENTRIES = datosPrensa;
		headerColumns = HEADER.length - FOOTER.length - 1;
	    footerColumns = FOOTER.length;
	    filas = numeroFilas;
				
	    model = new TimeSheetTableModelEx();
	    totalModel = new TimeSheetTotalTableModel(model);
	    
	    table = new TableScrollPane(model, totalModel, true) {

            public TableCustomizer getTableCustomizer() {
                return new TableCustomizer() {
                    public void customize(JTable table) {
                        //((JideTable) table).setRowAutoResizes(true);
                        //table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        table.setRowHeight(20);
                    }
                };
            }
        };
        
        for (int i = 0; i < footerColumns; i++) {
       	 	table.getMainTable().getColumnModel().getColumn(i).setPreferredWidth(70);
        }
        
        JLabel label = new JLabel("TOTAL: ");
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        label.setVerticalAlignment(SwingConstants.TOP);
        table.setCorner(JScrollPane.LOWER_LEFT_CORNER, label);
    }
	
	class TimeSheetTableModelEx extends AbstractMultiTableModel {
		
		public String getColumnName(int column) {
            return HEADER[column];
        }

        public int getColumnCount() {
            return HEADER.length;
        }

        public int getRowCount() {
            return filas;
        }

        public Class getColumnClass(int columnIndex) {
            return String.class;
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (rowIndex >= ENTRIES.length) {
                // skip for now
            }
            else if (columnIndex == 0) {
                // no editable
            }
            else if (columnIndex == HEADER.length - 1) { //last column
                // no editable
            }
            else {
                ENTRIES[rowIndex][columnIndex] = aValue;
                if (columnIndex >= headerColumns && columnIndex <= (HEADER.length-2)) {
                    // update column total
                    ((AbstractTableModel) totalModel).fireTableCellUpdated(0, columnIndex - (headerColumns));
                    // update row total
                    fireTableCellUpdated(rowIndex, getColumnCount() - 1);
                }
            }
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return "" + (TableModelWrapperUtils.getRowAt(table.getRowHeaderTable().getModel(), rowIndex) + 1);
            }
            else if (rowIndex >= ENTRIES.length) {
                if (columnIndex >= headerColumns && columnIndex <= (HEADER.length-2)) {
                    return "";
                }
                else if (columnIndex == HEADER.length - 1) {
                    return "";
                }
                else {
                    return "";
                }
            }
            else if (columnIndex == HEADER.length - 1) { //last column
                double total = 0.0;
                for (int i = headerColumns; i <= (HEADER.length-2); i++) {
                    try {
                        try {
                            total += Double.parseDouble((String) ENTRIES[rowIndex][i]);
                        }
                        catch (NumberFormatException e) {
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return "" + total;
            }
            else {
                return ENTRIES[rowIndex][columnIndex];
            }
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex >= headerColumns && columnIndex != HEADER.length - 1; // only cells in the main table are editable.
        }

        public int getTableIndex(int columnIndex) {
            return 0;
        }

        public int getColumnType(int column) {
            if (column <= (headerColumns-1)) {
                return HEADER_COLUMN;
            }
            else if (column == HEADER.length - 1) {
                return FOOTER_COLUMN;
            }
            else {
                return REGULAR_COLUMN;
            }
        }
    }
	
	class TimeSheetTotalTableModel extends AbstractMultiTableModel {
        TableModel _model;

        public TimeSheetTotalTableModel(TableModel model) {
            _model = model;
        }

        public String getColumnName(int column) {
            return FOOTER[column];
        }

        public int getColumnCount() {
            return footerColumns;
        }

        public int getRowCount() {
            return 1;
        }

        public Class getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            double total = 0.0;
            for (int i = 0; i < _model.getRowCount(); i++) {
                try {
                    total += Double.parseDouble((String) _model.getValueAt(i, columnIndex + headerColumns));
                }
                catch (NumberFormatException e) {
                }
            }
            return "" + total;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public int getColumnType(int column) {
            return REGULAR_COLUMN;
        }

        public int getTableIndex(int columnIndex) {
            return 0;
        }
    }
	
	public TableScrollPane getTable() {
		return table;
	}

	public void setTable(TableScrollPane table) {
		this.table = table;
	}
}

