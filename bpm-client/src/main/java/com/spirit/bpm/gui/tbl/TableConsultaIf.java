/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spirit.bpm.gui.tbl;

import java.util.List;

/**
 *
 * @author Administrador
 */
public interface TableConsultaIf<T> {

    void addRow(T dataRow)  throws Exception;

    void cleanData();

    int getColumnCount();

    String getColumnName(int column);

    int getRowCount();

    Object getValueAt(int row, int column);

    boolean isCellEditable(int row, int column);

    void remove(int row);

    Object getValue(T dataRow, int row,int column);

    void fillData(List<T> pDataList);

    void setColumns();

}
