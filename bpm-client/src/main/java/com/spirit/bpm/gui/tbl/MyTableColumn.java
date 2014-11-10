/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Contabilidad
 */
public class MyTableColumn {

    private String columnName;
    private boolean editable = false;
    private int minWidth = 0;
    private int maxWith = 0;
    private int with = 0;
    private int preferedWidth = 0;
    private boolean resizable = true;
    private TableCellRenderer cellRenderer;
    private TableCellEditor cellEditor;
    private Class columnClass;

    public MyTableColumn(String columnName, boolean editable) {
        this.columnName = columnName;
        this.editable = editable;
    }

    public MyTableColumn(String columnName, int minWidth, int maxWith, int with, int preferedWidth) {
        this.columnName = columnName;
        this.minWidth = minWidth;
        this.maxWith = maxWith;
        this.with = with;
        this.preferedWidth = preferedWidth;
    }

    public MyTableColumn(String columnName, int minWidth, int maxWith, int with, int preferedWidth, Class columnClass) {
        this.columnName = columnName;
        this.minWidth = minWidth;
        this.maxWith = maxWith;
        this.with = with;
        this.preferedWidth = preferedWidth;
        this.columnClass = columnClass;
    }

    public MyTableColumn(String columnName, int maxWith) {
        this.columnName = columnName;
        this.maxWith = maxWith;
    }

    public MyTableColumn(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @return the minWidth
     */
    public int getMinWidth() {
        return minWidth;
    }

    /**
     * @return the maxWith
     */
    public int getMaxWith() {
        return maxWith;
    }

    /**
     * @return the preferedWidth
     */
    public int getPreferedWidth() {
        return preferedWidth;
    }

    /**
     * @return the resizable
     */
    public boolean isResizable() {
        return resizable;
    }

    /**
     * @return the cellRenderer
     */
    public TableCellRenderer getCellRenderer() {
        return cellRenderer;
    }

    /**
     * @return the cellEditor
     */
    public TableCellEditor getCellEditor() {
        return cellEditor;
    }

    /**
     * @return the with
     */
    public int getWith() {
        return with;
    }

    public void setCellRenderer(TableCellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
    }

    public void setCellEditor(TableCellEditor cellEditor) {
        this.cellEditor = cellEditor;
    }

    public Class getColumnClass() {
        return columnClass;
    }

    public void setColumnClass(Class columnClass) {
        this.columnClass = columnClass;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @param minWidth the minWidth to set
     */
    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    /**
     * @param maxWith the maxWith to set
     */
    public void setMaxWith(int maxWith) {
        this.maxWith = maxWith;
    }

    /**
     * @param with the with to set
     */
    public void setWith(int with) {
        this.with = with;
    }

    /**
     * @param preferedWidth the preferedWidth to set
     */
    public void setPreferedWidth(int preferedWidth) {
        this.preferedWidth = preferedWidth;
    }

    /**
     * @param resizable the resizable to set
     */
    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }
}
