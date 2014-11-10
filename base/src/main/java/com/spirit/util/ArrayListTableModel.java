/*
 * Created on 09-jul-2004
 */

package com.spirit.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * <p>
 * A table model similar to <code>DefaultTableModel</code> but based on
 * <code>ArayList</code> instead of <code>Vector</code>.
 * </p>
 * <p>
 * This model haves the same methods of <code>DefaultTableModel</code> and
 * adss an impplementation of <code>getColumnClass(int colNum)</code> method.
 * </p>
 * 
 * @author Francesc Rosés
 */
public class ArrayListTableModel extends AbstractTableModel implements Serializable {
	private boolean cellEditable = false;

	/**
	 * The <code>ArrayList</code> of <code>Arraylist</code> s of
	 * <code>Object</code> values.
	 */
	protected ArrayList dataArray;

	/** The <code>ArrayList</code> of column identifiers. */
	protected ArrayList columnIdentifiers;

	/**
	 * Constructs a default <code>ArrayListTableModel</code> which is a table
	 * of zero columns and zero rows.
	 */
	public ArrayListTableModel() {
		this(0, 0);
	}

	/**
	 * Constructs an <code>ArrayListTableModel</code> with
	 * <code>rowCount</code> and <code>columnCount</code> of
	 * <code>null</code> object values.
	 * 
	 * @param rowCount
	 *            the number of rows the table holds
	 * @param columnCount
	 *            the number of columns the table holds
	 * 
	 * @see #setValueAt
	 */
	public ArrayListTableModel(int rowCount, int columnCount) {
		this(new ArrayList(columnCount), rowCount);
	}

	/**
	 * Constructs a <code>DefaultTableModel</code> and initializes the table
	 * by passing <code>data</code> and <code>columnNames</code> to the
	 * <code>setDataVector</code> method. The first index in the
	 * <code>Object[][]</code> array is the row index and the second is the
	 * column index.
	 * 
	 * @param data
	 *            The data of the table
	 * @param columnNames
	 *            The names of the columns
	 */
	public ArrayListTableModel(Object[][] data, Object[] columnNames) {
		DefaultTableModel m = null;
		setDataArray(data, columnNames);
	}

	/**
	 * Constructs an <code>ArrayListTableModel</code> with as many columns as
	 * there are elements in <code>columnNames</code> and
	 * <code>rowCount</code> of <code>null</code> object values. Each
	 * column's name will be taken from the <code>columnNames</code> array.
	 * 
	 * @param columnNames
	 *            <code>array</code> containing the names of the new columns;
	 *            if this is <code>null</code> then the model has no columns
	 * @param rowCount
	 *            the number of rows the table holds
	 * @see #setDataArray(ArrayList, ArrayList)
	 * @see #setValueAt(Object, int, int)
	 */
	public ArrayListTableModel(Object[] columnNames, int rowCount) {
		this(convertToArrayList(columnNames), rowCount);
	}

	/**
	 * Constructs an <code>ArrayListTableModel</code> with as many columns as
	 * there are elements in <code>columnNames</code> and
	 * <code>rowCount</code> of <code>null</code> object values. Each
	 * column's name will be taken from the <code>columnNames</code> vector.
	 * 
	 * @param columnNames
	 *            <code>ArrayList</code> containing the names of the new
	 *            columns; if this is <code>null</code> then the model has no
	 *            columns
	 * @param rowCount
	 *            the number of rows the table holds
	 * @see #setDataArray(ArrayList, ArrayList)
	 * @see #setValueAt(Object, int, int)
	 */
	public ArrayListTableModel(ArrayList columnNames, int rowCount) {
		setDataArray(new ArrayList(rowCount), columnNames);
	}

	/**
	 * Constructs an <code>ArrayListTableModel</code> and initializes the
	 * table by passing <code>data</code> and <code>columnNames</code> to
	 * the <code>setDataVector</code> method.
	 * 
	 * @param data
	 *            the data of the table
	 * @param columnNames
	 *            <code>ArrayList</code> containing the names of the new
	 *            columns
	 * @see #getDataArray()
	 * @see #setDataArray(ArrayList, ArrayList)
	 */
	public ArrayListTableModel(ArrayList data, ArrayList columnNames) {
		setDataArray(data, columnNames);
	}

	/**
	 * Returns the number of columns in this data table.
	 * 
	 * @return the number of columns in the model
	 */
	public int getColumnCount() {
		return columnIdentifiers.size();
	}

	/**
	 * <p>
	 * Removes the row at <code>row</code> from the model. Notification of the
	 * row being removed will be sent to all the listeners.
	 * </p>
	 * 
	 * @param row
	 *            the row index of the row to be removed
	 * @exception ArrayIndexOutOfBoundsException
	 *                if the row was invalid
	 */
	public void removeRow(int row) {
		dataArray.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 * Returns the number of rows in this data table.
	 * 
	 * @return the number of rows in the model
	 */
	public int getRowCount() {
		return dataArray.size();
	}

	/**
	 * Returns the column name.
	 * 
	 * @return a name for this column using the string value of the appropriate
	 *         member in <code>columnIdentifiers</code>. If
	 *         <code>columnIdentifiers</code> does not have an entry for this
	 *         index, returns the default name provided by the superclass
	 */
	public String getColumnName(int column) {
		Object id = null;
		// This test is to cover the case when
		// getColumnCount has been subclassed by mistake ...
		if (column < columnIdentifiers.size()) {
			id = columnIdentifiers.get(column);
		}
		return (id == null) ? super.getColumnName(column) : id.toString();
	}

	/**
	 * Returns true regardless of parameter values.
	 * 
	 * @param row
	 *            the row whose value is to be queried
	 * @param column
	 *            the column whose value is to be queried
	 * @return true
	 * @see #setValueAt
	 */
	public boolean isCellEditable(int row, int column) {
		return cellEditable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int columnIndex) {
		if (getRowCount() == 0) {
			return Object.class;
		}
		return getValueAt(0, columnIndex).getClass();
	}

	/**
	 * Returns an attribute value for the cell at <code>row</code> and
	 * <code>column</code>.
	 * 
	 * @param row
	 *            the row whose value is to be queried
	 * @param column
	 *            the column whose value is to be queried
	 * @return the value Object at the specified cell
	 * @exception ArrayIndexOutOfBoundsException
	 *                if an invalid row or column was given
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList rowArray = (ArrayList) dataArray.get(rowIndex);
		return rowArray.get(columnIndex);
	}

	/**
	 * Sets the object value for the cell at <code>column</code> and
	 * <code>row</code>.<code>aValue</code> is the new value. This method
	 * will generate a <code>tableChanged</code> notification.
	 * 
	 * @param aValue
	 *            the new value; this can be null
	 * @param row
	 *            the row whose value is to be changed
	 * @param column
	 *            the column whose value is to be changed
	 * @exception ArrayIndexOutOfBoundsException
	 *                if an invalid row or column was given
	 */
	public void setValueAt(Object aValue, int row, int column) {
		ArrayList rowArray = (ArrayList) dataArray.get(row);
		rowArray.set(column, aValue);
		fireTableCellUpdated(row, column);
	}

	protected ArrayList getDataArray() {
		return dataArray;
	}

	/**
	 * @return A cloned copy of <code>dataArray</code>.
	 */
	public ArrayList getData() {
		return new ArrayList((ArrayList) getDataArray().clone());
	} // getData()

	public ArrayList getColumnNames() {
		return (ArrayList) columnIdentifiers.clone();
	}

	//public void setDataVector(Vector dataVector, Vector columnIdentifiers)
	public void setDataArray(ArrayList dataArray, ArrayList columnIdentifiers) {
		this.dataArray = nonNullArrayList(dataArray);
		this.columnIdentifiers = nonNullArrayList(columnIdentifiers);
		justifyRows(0, getRowCount());
		fireTableStructureChanged();
	}

	/**
	 * Replaces the value in the <code>dataArray</code> instance variable with
	 * the values in the array <code>dataArray</code>. The first index in the
	 * <code>Object[][]</code> array is the row index and the second is the
	 * column index. <code>columnIdentifiers</code> are the names of the new
	 * columns.
	 * 
	 * @param dataArray
	 *            the new data array
	 * @param columnIdentifiers
	 *            the names of the columns
	 * @see #setDataArray(ArrayList, ArrayList)
	 */
	public void setDataArray(Object[][] dataArray, Object[] columnIdentifiers) {
		setDataArray(convertToArrayList(dataArray),
				convertToArrayList(columnIdentifiers));
	}

	private void justifyRows(int from, int to) {
		// Sometimes the DefaultTableModel is subclassed
		// instead of the AbstractTableModel by mistake.
		// Set the number of rows for the case when getRowCount
		// is overridden.
		dataArray.ensureCapacity(getRowCount());

		for (int i = from; i < to; i++) {
			if (dataArray.get(i) == null) {
				dataArray.add(i, new ArrayList());
			}
			((ArrayList) dataArray.get(i)).ensureCapacity(getColumnCount());
		}
	} // justifyRows()

	private static ArrayList nonNullArrayList(ArrayList al) {
		return (al != null) ? al : new ArrayList();
	}

	/**
	 * Returns an <code>ArrayList</code> that contains the same objects as the
	 * array.
	 * 
	 * @param anArray
	 *            the array to be converted
	 * @return the new <code>ArrayList</code>; if <code>anArray</code> is
	 *         <code>null</code>, returns <code>null</code>
	 */
	protected static ArrayList convertToArrayList(Object[] anArray) {
		if (anArray == null) {
			return null;
		}
		ArrayList al = new ArrayList(anArray.length);
		for (int i = 0; i < anArray.length; i++) {
			al.add(anArray[i]);
		}
		return al;
	}

	/**
	 * Returns an <code>ArrayList</code> of <code>ArrayList</code> s that
	 * contains the same objects as the array.
	 * 
	 * @param anArray
	 *            the double array to be converted
	 * @return the new <code>ArrayList</code> of <code>ArrayList</code>s;
	 *         if <code>anArray</code> is <code>null</code>, returns
	 *         <code>null</code>
	 */
	protected static ArrayList convertToArrayList(Object[][] anArray) {
		if (anArray == null) {
			return null;
		}
		ArrayList al = new ArrayList(anArray.length);
		for (int i = 0; i < anArray.length; i++) {
			al.add(convertToArrayList(anArray[i]));
		}
		DefaultTableModel m = null;
		return al;
	}

	/**
	 * Adds a column to the model. The new column will have the identifier
	 * <code>columnName</code>, which may be null. This method will send a
	 * <code>tableChanged</code> notification message to all the listeners.
	 * This method is a cover for <code>addColumn(Object, Vector)</code> which
	 * uses <code>null</code> as the data vector.
	 * 
	 * @param columnName
	 *            the identifier of the column being added
	 */
	public void addColumn(Object columnName) {
		addColumn(columnName, (List) null);
	}

	/**
	 * Adds a column to the model. The new column will have the identifier
	 * <code>columnName</code>, which may be null. <code>columnData</code>
	 * is the optional vector of data for the column. If it is <code>null</code>
	 * the column is filled with <code>null</code> values. Otherwise, the new
	 * data will be added to model starting with the first element going to row
	 * 0, etc. This method will send a <code>tableChanged</code> notification
	 * message to all the listeners.
	 * 
	 * @param columnName
	 *            the identifier of the column being added
	 * @param columnData
	 *            optional data of the column being added
	 */
	public void addColumn(Object columnName, List columnData) {
		columnIdentifiers.add(columnName);
		if (columnData != null) {
			int columnSize = columnData.size();
			if (columnSize > getRowCount()) {
				//dataArray.setSize(columnSize);
				dataArray.ensureCapacity(columnSize);
			}
			justifyRows(0, getRowCount());
			int newColumn = getColumnCount() - 1;
			for (int i = 0; i < columnSize; i++) {
				List row = (List) dataArray.get(i);
				row.add(newColumn, columnData.get(i));
			}
		} else {
			justifyRows(0, getRowCount());
		}

		fireTableStructureChanged();
	} // addColumn()

	/**
	 * Adds a column to the model. The new column will have the identifier
	 * <code>columnName</code>.<code>columnData</code> is the optional
	 * array of data for the column. If it is <code>null</code> the column is
	 * filled with <code>null</code> values. Otherwise, the new data will be
	 * added to model starting with the first element going to row 0, etc. This
	 * method will send a <code>tableChanged</code> notification message to
	 * all the listeners.
	 * 
	 * @see #addColumn(Object, List)
	 */
	public void addColumn(Object columnName, Object[] columnData) {
		addColumn(columnName, convertToArrayList(columnData));
	}

	/**
	 * Adds a row to the end of the model. The new row will contain
	 * <code>null</code> values unless <code>rowData</code> is specified.
	 * Notification of the row being added will be generated.
	 * 
	 * @param rowData
	 *            optional data of the row being added
	 */
	public void addRow(Object[] rowData) {
		addRow(convertToArrayList(rowData));
	} // addRow(Object[])

	/**
	 * Adds a row to the end of the model. The new row will contain
	 * <code>null</code> values unless <code>rowData</code> is specified.
	 * Notification of the row being added will be generated.
	 * 
	 * @param rowData
	 *            optional data of the row being added
	 */
	public void addRow(ArrayList rowData) {
		insertRow(getRowCount(), rowData);
	} // addRow(List)

	/**
	 * Inserts a row at <code>row</code> in the model. The new row will
	 * contain <code>null</code> values unless <code>rowData</code> is
	 * specified. Notification of the row being added will be generated.
	 * 
	 * @param row
	 *            the row index of the row to be inserted
	 * @param rowData
	 *            optional data of the row being added
	 * @exception ArrayIndexOutOfBoundsException
	 *                if the row was invalid
	 */
	public void insertRow(int row, ArrayList rowData) {
		dataArray.add(row, rowData);
		justifyRows(row, row + 1);
		fireTableRowsInserted(row, row);
	} // insertRow(int, ArrayList)

	/**
	 * Moves one or more rows from the inlcusive range <code>start</code> to
	 * <code>end</code> to the <code>to</code> position in the model. After
	 * the move, the row that was at index <code>start</code> will be at index
	 * <code>to</code>. This method will send a <code>tableChanged</code>
	 * notification message to all the listeners.
	 * <p>
	 * 
	 * <pre>
	 * 
	 *  
	 *   
	 *    
	 *     
	 *      
	 *       
	 *        
	 *         
	 *          
	 *           
	 *            
	 *             
	 *               Examples of moves:
	 *               
	 *             
	 *            
	 *           
	 *          
	 *         
	 *        
	 *       
	 *      
	 *     
	 *    
	 *   
	 *  
	 * <p>
	 * 
	 *  
	 *   
	 *    
	 *     
	 *      
	 *       
	 *        
	 *         
	 *          
	 *           
	 *            
	 *             
	 *               1. moveRow(1,3,5);
	 *                       a|B|C|D|e|f|g|h|i|j|k   - before
	 *                       a|e|f|g|h|B|C|D|i|j|k   - after
	 *               
	 *             
	 *            
	 *           
	 *          
	 *         
	 *        
	 *       
	 *      
	 *     
	 *    
	 *   
	 *  
	 * <p>
	 * 
	 *  
	 *   
	 *    
	 *     
	 *      
	 *       
	 *        
	 *         
	 *          
	 *           
	 *            
	 *             
	 *               2. moveRow(6,7,1);
	 *                       a|b|c|d|e|f|G|H|i|j|k   - before
	 *                       a|G|H|b|c|d|e|f|i|j|k   - after
	 *               
	 *             
	 *            
	 *           
	 *          
	 *         
	 *        
	 *       
	 *      
	 *     
	 *    
	 *   
	 *  
	 * <p> 
	 *  </pre>
	 * 
	 * @param start
	 *            the starting row index to be moved
	 * @param end
	 *            the ending row index to be moved
	 * @param to
	 *            the destination of the rows to be moved
	 * @exception ArrayIndexOutOfBoundsException
	 *                if any of the elements would be moved out of the table's
	 *                range
	 *  
	 */
	public void moveRow(int start, int end, int to) {
		int shift = to - start;
		int first, last;
		if (shift < 0) {
			first = to;
			last = end;
		} else {
			first = start;
			last = to + end - start;
		}
		rotate(dataArray, first, last + 1, shift);

		fireTableRowsUpdated(first, last);
	} // moveRow()

	private static void rotate(ArrayList v, int a, int b, int shift) {
		int size = b - a;
		int r = size - shift;
		int g = gcd(size, r);
		for (int i = 0; i < g; i++) {
			int to = i;
			Object tmp = v.get(a + to);
			for (int from = (to + r) % size; from != i; from = (to + r) % size) {
				v.set(a + to, v.get(a + from));
				to = from;
			}
			v.set(a + to, tmp);
		}
	} // rotate()

	private static int gcd(int i, int j) {
		return (j == 0) ? i : gcd(j, i % j);
	} // gcd()

	/**
	 * Sets the number of columns in the model. If the new size is greater than
	 * the current size, new columns are added to the end of the model with
	 * <code>null</code> cell values. If the new size is less than the current
	 * size, all columns at index <code>columnCount</code> and greater are
	 * discarded.
	 * 
	 * @param columnCount
	 *            the new number of columns in the model
	 * 
	 * @see #setColumnCount
	 */
	public void setColumnCount(int columnCount) {
		columnIdentifiers = (ArrayList) columnIdentifiers.subList(0,
				columnCount - 1);
		// columnIdentifiers.setSize(columnCount);
		justifyRows(0, getRowCount());
		fireTableStructureChanged();
	} // setColumnCount()

	/**
	 * Sets the number of rows in the model. If the new size is greater than the
	 * current size, new rows are added to the end of the model If the new size
	 * is less than the current size, all rows at index <code>rowCount</code>
	 * and greater are discarded.
	 * <p>
	 * 
	 * @see #setColumnCount
	 */
	public void setRowCount(int rowCount) {
		setNumRows(rowCount);
	} // setRowCount()

	/**
	 * Obsolete as of Java 2 platform v1.3. Please use <code>setRowCount</code>
	 * instead.
	 */
	/*
	 * Sets the number of rows in the model. If the new size is greater than the
	 * current size, new rows are added to the end of the model If the new size
	 * is less than the current size, all rows at index <code> rowCount </code>
	 * and greater are discarded. <p>
	 * 
	 * @param rowCount the new number of rows
	 * 
	 * @see #setRowCount
	 */
	public void setNumRows(int rowCount) {
		int old = getRowCount();
		if (old == rowCount) {
			return;
		}
		dataArray = new ArrayList(dataArray.subList(0, rowCount - 1));
		// dataArray.setSize(rowCount);
		if (rowCount <= old) {
			fireTableRowsDeleted(rowCount, old - 1);
		} else {
			justifyRows(old, rowCount);
			fireTableRowsInserted(old, rowCount - 1);
		}
	} // setNumRws()

	/**
	 * Replaces the column identifiers in the model. If the number of
	 * <code>newIdentifier</code> s is greater than the current number of
	 * columns, new columns are added to the end of each row in the model. If
	 * the number of <code>newIdentifier</code> s is less than the current
	 * number of columns, all the extra columns at the end of a row are
	 * discarded.
	 * <p>
	 * 
	 * @param newIdentifiers
	 *            array of column identifiers. If <code>null</code>, set the
	 *            model to zero columns
	 * @see #setNumRows
	 */
	public void setColumnIdentifiers(Object[] newIdentifiers) {
		setColumnIdentifiers(convertToArrayList(newIdentifiers));
	}

	/**
	 * Replaces the column identifiers in the model. If the number of
	 * <code>newIdentifier</code> s is greater than the current number of
	 * columns, new columns are added to the end of each row in the model. If
	 * the number of <code>newIdentifier</code> s is less than the current
	 * number of columns, all the extra columns at the end of a row are
	 * discarded.
	 * <p>
	 * 
	 * @param columnIdentifiers
	 *            vector of column identifiers. If <code>null</code>, set the
	 *            model to zero columns
	 * @see #setNumRows
	 */
	public void setColumnIdentifiers(ArrayList columnIdentifiers) {
		setDataArray(dataArray, columnIdentifiers);
	}

	/**
	 * Ensures that the new rows have the correct number of columns. This is
	 * accomplished by using the <code>setSize</code> method in
	 * <code>Vector</code> which truncates vectors which are too long, and
	 * appends <code>null</code> s if they are too short. This method also
	 * sends out a <code>tableChanged</code> notification message to all the
	 * listeners.
	 * 
	 * @param e
	 *            this <code>TableModelEvent</code> describes where the rows
	 *            were added. If <code>null</code> it assumes all the rows
	 *            were newly added
	 * @see #getDataVector
	 */
	public void newRowsAdded(TableModelEvent e) {
		justifyRows(e.getFirstRow(), e.getLastRow() + 1);
		fireTableChanged(e);
	} // newRowsAdded()

	/**
	 * Equivalent to <code>fireTableChanged</code>.
	 * 
	 * @param event
	 *            the change event
	 *  
	 */
	public void newDataAvailable(TableModelEvent event) {
		fireTableChanged(event);
	}

	/**
	 * Equivalent to <code>fireTableChanged</code>.
	 * 
	 * @param event
	 *            the change event
	 *  
	 */
	public void rowsRemoved(TableModelEvent event) {
		fireTableChanged(event);
	}

}