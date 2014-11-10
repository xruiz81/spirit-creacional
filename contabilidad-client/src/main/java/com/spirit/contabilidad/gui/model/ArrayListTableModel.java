package com.spirit.contabilidad.gui.model;

import java.io.Serializable;
import java.util.*;

import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

public class ArrayListTableModel extends AbstractTableModel implements
    Serializable
{
  private boolean cellEditable = false;

  protected ArrayList dataArray;

  protected ArrayList columnIdentifiers;

  public ArrayListTableModel()
  {
    this(0, 0);
  }

  public ArrayListTableModel(int rowCount, int columnCount)
  {
    this(new ArrayList(columnCount), rowCount);
  }

  public ArrayListTableModel(Object[][] data, Object[] columnNames)
  {
    DefaultTableModel m = null;
    setDataArray(data, columnNames);
  }

  public ArrayListTableModel(Object[] columnNames, int rowCount)
  {
    this(convertToArrayList(columnNames), rowCount);
  }

  public ArrayListTableModel(ArrayList columnNames, int rowCount)
  {
    setDataArray(new ArrayList(rowCount), columnNames);
  }

  public ArrayListTableModel(ArrayList data, ArrayList columnNames)
  {
    setDataArray(data, columnNames);
  }

  public int getColumnCount()
  {
    return columnIdentifiers.size();
  }

  public void removeRow(int row)
  {
    dataArray.remove(row);
    fireTableRowsDeleted(row, row);
  }

  public int getRowCount()
  {
    return dataArray.size();
  }

  public String getColumnName(int column)
  {
    Object id = null;

    if (column < columnIdentifiers.size())
    {
      id = columnIdentifiers.get(column);
    }
    return (id == null) ? super.getColumnName(column) : id.toString();
  }

  public boolean isCellEditable(int row, int column)
  {
    return cellEditable;
  }

  public Class getColumnClass(int columnIndex)
  {
    if (getRowCount() == 0)
    {
      return Object.class;
    }
    return getValueAt(0, columnIndex).getClass();
  }

  public Object getValueAt(int rowIndex, int columnIndex)
  {
    ArrayList rowArray = (ArrayList) dataArray.get(rowIndex);
    return rowArray.get(columnIndex);
  }

  public void setValueAt(Object aValue, int row, int column)
  {
    ArrayList rowArray = (ArrayList) dataArray.get(row);
    rowArray.set(column, aValue);
    fireTableCellUpdated(row, column);
  }

  protected ArrayList getDataArray()
  {
    return dataArray;
  }

  public ArrayList getData()
  {
    return new ArrayList((ArrayList) getDataArray().clone());
  } // getData()

  public ArrayList getColumnNames()
  {
    return (ArrayList) columnIdentifiers.clone();
  }

  //public void setDataVector(Vector dataVector, Vector columnIdentifiers)
  public void setDataArray(ArrayList dataArray, ArrayList columnIdentifiers)
  {
    this.dataArray = nonNullArrayList(dataArray);
    this.columnIdentifiers = nonNullArrayList(columnIdentifiers);
    justifyRows(0, getRowCount());
    fireTableStructureChanged();
  }

  public void setDataArray(Object[][] dataArray, Object[] columnIdentifiers)
  {
    setDataArray(convertToArrayList(dataArray),
                 convertToArrayList(columnIdentifiers));
  }

  private void justifyRows(int from, int to)
  {

    dataArray.ensureCapacity(getRowCount());

    for (int i = from; i < to; i++)
    {
      if (dataArray.get(i) == null)
      {
        dataArray.add(i, new ArrayList());
      }
      ((ArrayList) dataArray.get(i)).ensureCapacity(getColumnCount());
    }
  } // justifyRows()

  private static ArrayList nonNullArrayList(ArrayList al)
  {
    return (al != null) ? al : new ArrayList();
  }

  protected static ArrayList convertToArrayList(Object[] anArray)
  {
    if (anArray == null)
    {
      return null;
    }
    ArrayList al = new ArrayList(anArray.length);
    for (int i = 0; i < anArray.length; i++)
    {
      al.add(anArray[i]);
    }
    return al;
  }

  protected static ArrayList convertToArrayList(Object[][] anArray)
  {
    if (anArray == null)
    {
      return null;
    }
    ArrayList al = new ArrayList(anArray.length);
    for (int i = 0; i < anArray.length; i++)
    {
      al.add(convertToArrayList(anArray[i]));
    }
    DefaultTableModel m = null;
    return al;
  }

  public void addColumn(Object columnName)
  {
    addColumn(columnName, (List)null);
  }

  public void addColumn(Object columnName, List columnData)
  {
    columnIdentifiers.add(columnName);
    if (columnData != null)
    {
      int columnSize = columnData.size();
      if (columnSize > getRowCount())
      {
        //dataArray.setSize(columnSize);
        dataArray.ensureCapacity(columnSize);
      }
      justifyRows(0, getRowCount());
      int newColumn = getColumnCount() - 1;
      for (int i = 0; i < columnSize; i++)
      {
        List row = (List) dataArray.get(i);
        row.add(newColumn, columnData.get(i));
      }
    }
    else
    {
      justifyRows(0, getRowCount());
    }

    fireTableStructureChanged();
  } // addColumn()

  public void addColumn(Object columnName, Object[] columnData)
  {
    addColumn(columnName, convertToArrayList(columnData));
  }

  public void addRow(Object[] rowData)
  {
    addRow(convertToArrayList(rowData));
  } // addRow(Object[])

  public void addRow(ArrayList rowData)
  {
    insertRow(getRowCount(), rowData);
  } // addRow(List)

  public void insertRow(int row, ArrayList rowData)
  {
    dataArray.add(row, rowData);
    justifyRows(row, row + 1);
    fireTableRowsInserted(row, row);
  } // insertRow(int, ArrayList)

  public void moveRow(int start, int end, int to)
  {
    int shift = to - start;
    int first, last;
    if (shift < 0)
    {
      first = to;
      last = end;
    }
    else
    {
      first = start;
      last = to + end - start;
    }
    rotate(dataArray, first, last + 1, shift);

    fireTableRowsUpdated(first, last);
  } // moveRow()

  private static void rotate(ArrayList v, int a, int b, int shift)
  {
    int size = b - a;
    int r = size - shift;
    int g = gcd(size, r);
    for (int i = 0; i < g; i++)
    {
      int to = i;
      Object tmp = v.get(a + to);
      for (int from = (to + r) % size; from != i; from = (to + r) % size)
      {
        v.set(a + to, v.get(a + from));
        to = from;
      }
      v.set(a + to, tmp);
    }
  } // rotate()

  private static int gcd(int i, int j)
  {
    return (j == 0) ? i : gcd(j, i % j);
  } // gcd()

  public void setColumnCount(int columnCount)
  {
    columnIdentifiers = (ArrayList) columnIdentifiers.subList(0,
                                                              columnCount - 1);
    // columnIdentifiers.setSize(columnCount);
    justifyRows(0, getRowCount());
    fireTableStructureChanged();
  } // setColumnCount()

  public void setRowCount(int rowCount)
  {
    setNumRows(rowCount);
  } // setRowCount()

  public void setNumRows(int rowCount)
  {
    int old = getRowCount();
    if (old == rowCount)
    {
      return;
    }
    dataArray = new ArrayList(dataArray.subList(0, rowCount - 1));
    // dataArray.setSize(rowCount);
    if (rowCount <= old)
    {
      fireTableRowsDeleted(rowCount, old - 1);
    }
    else
    {
      justifyRows(old, rowCount);
      fireTableRowsInserted(old, rowCount - 1);
    }
  } // setNumRws()

  public void setColumnIdentifiers(Object[] newIdentifiers)
  {
    setColumnIdentifiers(convertToArrayList(newIdentifiers));
  }

  public void setColumnIdentifiers(ArrayList columnIdentifiers)
  {
    setDataArray(dataArray, columnIdentifiers);
  }

  public void newRowsAdded(TableModelEvent e)
  {
    justifyRows(e.getFirstRow(), e.getLastRow() + 1);
    fireTableChanged(e);
  } // newRowsAdded()

  public void newDataAvailable(TableModelEvent event)
  {
    fireTableChanged(event);
  }

  public void rowsRemoved(TableModelEvent event)
  {
    fireTableChanged(event);
  }

}