package com.spirit.util;
/*
 * @(#)TableScrollPaneDemo.java
 *
 * Copyright 2002 - 2005 JIDE Software Inc. All rights reserved.
 */

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.jidesoft.grid.AbstractMultiTableModel;
import com.jidesoft.grid.MultiTableModel;
import com.jidesoft.grid.SortTableHeaderRenderer;
import com.jidesoft.grid.TableCustomizer;
import com.jidesoft.grid.TableScrollPane;

/**
 * Demoed Component: {@link com.jidesoft.grid.TableScrollPane}
 * <br>
 * Required jar files: jide-common.jar, jide-grids.jar
 * <br>
 * Required L&F: any L&F
 */
public class MySheetTableScrollPane extends TableScrollPane{

    protected MultiTableModel totalModel;
    protected MultiTableModel model;
    private String[] HEADER;
    private Object[][] ENTRIES;
    private int numberRows;
    private int headerColumns;
    private int regularColumns;
    private int widthColumns;
    TableScrollPane table;
    
    public MySheetTableScrollPane(String[] columnNames,int rows,int hColumns,int rColumns,int wColumns) {
    	HEADER = columnNames;
    	numberRows = rows;
    	headerColumns = hColumns;
    	regularColumns = rColumns;
    	widthColumns = wColumns;
    	
    	
    	ENTRIES = new Object[numberRows][headerColumns+regularColumns];
    	
        model = new SheetTableModelEx();
        totalModel = new SheetTotalTableModel(model);
        
        
        
        table = new TableScrollPane(model, totalModel, true) {
            public TableCustomizer getTableCustomizer() {
                return new TableCustomizer() {
                    public void customize(JTable table) {
                    	table.setRowHeight(20);
                    }
                };
            }
        };
        
        //Setea que los valores de estas columnas se ubiquen en el lado derecho de las celdas de la tabla
		DefaultTableCellRenderer dtcrColumn = new DefaultTableCellRenderer();
		dtcrColumn.setHorizontalAlignment(JTextField.RIGHT);
		
		//Seteo que las tabals no sean re-ordenables
		table.setSortable(false);
		table.setSortable(false);
		
        //para evitar que mueva las columnas
        table.getRowHeaderTable().getTableHeader().setReorderingAllowed(false);
        table.getMainTable().getTableHeader().setReorderingAllowed(false);
        
        //Seteo el ancho de las columnas
        table.getRowHeaderTable().getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getRowHeaderTable().getColumnModel().getColumn(0).setCellRenderer(new SortTableHeaderRenderer());
        
        for (int i = 1; i < headerColumns; i++) {
       	 	table.getRowHeaderTable().getColumnModel().getColumn(i).setPreferredWidth(widthColumns);
            table.getRowHeaderTable().getColumnModel().getColumn(i).setCellEditor(new MyTableCellEditorToUpperCase());
        }
        for (int j = 0; j < regularColumns; j++) {
       	 	table.getMainTable().getColumnModel().getColumn(j).setPreferredWidth(widthColumns);
            table.getMainTable().getColumnModel().getColumn(j).setCellEditor(new MyTableCellEditorNumber());
            table.getMainTable().getColumnModel().getColumn(j).setCellRenderer(dtcrColumn);
        }
        
        
        table.getRowHeaderTable().setBackground(new Color(255, 254, 203));
        table.getMainTable().setBackground(new Color(255, 254, 203));
        
        
        JLabel label = new JLabel("Total: ");
        label.setHorizontalAlignment(SwingConstants.TRAILING);
        label.setVerticalAlignment(SwingConstants.TOP);
        table.setCorner(JScrollPane.LOWER_LEFT_CORNER, label);

        table.getColumnFooterTable().setBackground(new Color(178, 178, 142));
        for (int k = 0; k < regularColumns; k++) {
        	table.getColumnFooterTable().getColumnModel().getColumn(k).setPreferredWidth(widthColumns);
        	table.getColumnFooterTable().getColumnModel().getColumn(k).setCellEditor(new MyTableCellEditorNumber());
            table.getColumnFooterTable().getColumnModel().getColumn(k).setCellRenderer(dtcrColumn);	
        }
        
    }
    
    class SheetTableModelEx extends AbstractMultiTableModel {
   
		public String getColumnName(int column) {
            return HEADER[column];
        }

        public int getColumnCount() {
            return HEADER.length;
        }

        public int getRowCount() {
            return numberRows;
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
             else {
                 ENTRIES[rowIndex][columnIndex] = aValue;
                 if (columnIndex >= headerColumns) {
                     // update column total
                     ((AbstractTableModel) totalModel).fireTableCellUpdated(0, columnIndex - headerColumns);
                     // update row total
                     fireTableCellUpdated(rowIndex, getColumnCount() - 1);
                 }
             }
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return new Integer(rowIndex + 1);
            }
            else if (rowIndex >= ENTRIES.length) {
                return "";
            }
            else {
                return ENTRIES[rowIndex][columnIndex];
            }
        }
        
        

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex >= 1; // only cells in the main table are editable.
        }

        public int getTableIndex(int columnIndex) {
            return 0;
        }

        public int getColumnType(int column) {
            if (column <= headerColumns - 1) {
                return HEADER_COLUMN;
            }
            else {
                return REGULAR_COLUMN;
            }
        }
    }

    class SheetTotalTableModel extends AbstractMultiTableModel {
   
		TableModel _model;

        public SheetTotalTableModel(TableModel model) {
            _model = model;
        }

        public int getColumnCount() {
            return regularColumns;
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
                	if(_model.getValueAt(i, columnIndex + headerColumns)!=null)
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