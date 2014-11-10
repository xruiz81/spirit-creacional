package com.spirit.medios.util;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import com.spirit.general.entity.TipoProveedorIf;
import com.spirit.util.MyTableCellEditorTextEditable;

public class MySheetTableMapaProveedorScrollPane extends TableScrollPane{
	  Date fechaInicial;
	  Date fechaFinal;
	  Calendar calendarFechaInicial = new GregorianCalendar();
	  Calendar calendarFechaFinal = new GregorianCalendar();
	  protected MultiTableModel totalModel;
	  protected MultiTableModel model;
	  private String[] HEADER;
	  private String[] HEADERL;
	  private String[] HEADERM;
	  private String[] HEADERR = new String[]{"GYE (TGRPS)","UIO (TGRPS)","POND. (TGRPS)","CxPR","Impactos", "Inv. Tarifa", "Inv. C/Dscto"};
	  private Object[][] ENTRIES;
	  private int numberRows;
	  private int headerColumns;
	  private int regularColumns;
	  private int widthColumns;
	  TableScrollPane table;
	    
	    public MySheetTableMapaProveedorScrollPane(Date fechaInicio, Date fechaFin, TipoProveedorIf tipoProveedor, String diarioSemanal) {
	  	    //Seteo las variables locales con las recibidas
	    	fechaInicial = new Date(fechaInicio.getYear(),fechaInicio.getMonth(),fechaInicio.getDate());
		    fechaFinal = new Date(fechaFin.getYear(),fechaFin.getMonth(),fechaFin.getDate());	    	
	    	
	    	//Si el tipo de proveedor es de radio o  prensa construyo las columnas de la izquierda
	    	if(tipoProveedor.getNombre().contains("RADIO") || tipoProveedor.getNombre().contains("TELEVISION"))
				HEADERL = new String[]{"#","Programa","Hora","GYE","UIO", "Pond.","Audiencia","V.C. Tarifa", "V.C. Negoc."};
			else if(tipoProveedor.getNombre().contains("PRENSA"))
				HEADERL = new String[]{"#","Comercial","Seccion","Ubicacion","V.C. Tarifa", "V.C. Negoc."};
	    	
	    	//Se construye la columna del medio si es que es diario o semanal
	    	if(diarioSemanal.equals("diario")){
		    	//Creo el arreglo segun el numero de dias que tenga el resumen mes
		    	HEADERM = new String[fechaFinal.getDate() - fechaInicial.getDate() + 1];
		    	
		    	//Recorro los dias del resumen mes y los pongo como cabeceras de las columnas
		    	for(int x=0;x<HEADERM.length;x++){
		    		HEADERM[x]= "D("+(fechaInicial.getDate()+x)+")";
		    	}	    		
	    	}
			else if(diarioSemanal.equals("semanal")){
				//Seteo los valores de los calendarios segun el date spinner
				calendarFechaInicial.setTime(fechaInicial);
				calendarFechaFinal.setTime(fechaFinal);
				
				//Obtener en que semana esta la fecha inicial y final
				int semanaMesInicial = calendarFechaInicial.get(Calendar.WEEK_OF_MONTH);
				int semanaMesFinal = calendarFechaFinal.get(Calendar.WEEK_OF_MONTH);
				
				//Calculo el numero de semanas que entre las dos fechas dadas
				int semanas = semanaMesFinal - semanaMesInicial + 1;
				
				//Creo el arreglo segun el numero de semanas que tenga el resumen mes
		    	HEADERM = new String[semanas];
				
				//Creo cursores para ver que dia de la semana apuntala fecah inicial y final
		    	int diaFechaFinal = fechaFinal.getDate();
		    	int diaInicialSemana = fechaInicial.getDate();
				int diaSemanaFechaActual = fechaInicial.getDay();

		    	//Recorro las seamanas del resumen mes y los pongo como cabeceras de las columnas
		    	for(int x=0;x<HEADERM.length;x++){		
		    		//Calculo los dias restantes que hay desde la fecha actual hasta el ultimo dia de esa semana
		    		int diasRestantes = 6 - diaSemanaFechaActual;
		    		//Calculo los dias que se le debe sumar a la fecha actual
		    		int diaFinalSemana= diaInicialSemana+diasRestantes;
		    		//Si el dia final de la semana calculado es mayor que la fecha final, seteo el avlor del dia final semana al de la fecha final
		    		if(diaFinalSemana>diaFechaFinal)
		    			diaFinalSemana = diaInicialSemana + (diaFechaFinal - diaInicialSemana);
		    		//Creo la columna 
		    		HEADERM[x]= "S(" + diaInicialSemana + "-" + diaFinalSemana + ")";
		    		//Calculo el nuevo dia inicial de la semana
		    		diaInicialSemana = diaInicialSemana+diasRestantes +1;
		    		//Seteo la fecha de inicio a ese dia
		    		fechaInicial.setDate(diaInicialSemana);
		    		//Saco que dia de ka Semnana esta la fecha Inicio
		    		diaSemanaFechaActual = fechaInicial.getDay();
		    	}	
	    	} 
				
	    			    	
	    	//Creo el arreglo segun el numero de elementos que
	    	HEADER = new String[HEADERL.length+HEADERM.length+HEADERR.length];
	    	
	    	for(int y=0;y<HEADER.length;y++){
		    	for(int z=0;z<HEADERL.length;z++){
		    		HEADER[y] = HEADERL[z];
		    		y++;
		    	}
		    	for(int z=0;z<HEADERM.length;z++){
		    		HEADER[y] = HEADERM[z];
		    		y++;
		    	}
		    	for(int z=0;z<HEADERR.length;z++){
		    		HEADER[y] = HEADERR[z];
		    		y++;
		    	}
	    	}
	    	
	    	//Mando a armar la tabla
	    	armarTabla();
	    }
	    
	    
	    
	    public void armarTabla(){
	    	numberRows = 20;
	    	headerColumns = HEADERL.length;
	    	regularColumns =  HEADERM.length + HEADERR.length;
	    	widthColumns = 70;
	    	
	    	
	    	
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
	            table.getRowHeaderTable().getColumnModel().getColumn(i).setCellEditor(new MyTableCellEditorTextEditable(false));
	        }
	        for (int j = 0; j < HEADERM.length; j++) {
	       	 	table.getMainTable().getColumnModel().getColumn(j).setPreferredWidth(60);
	            table.getMainTable().getColumnModel().getColumn(j).setCellEditor(new MyTableCellEditorTextEditable(false));
	            table.getMainTable().getColumnModel().getColumn(j).setCellRenderer(dtcrColumn);
	        }
	        for (int k = HEADERM.length; k < HEADERM.length + HEADERR.length; k++) {
	       	 	table.getMainTable().getColumnModel().getColumn(k).setPreferredWidth(100);
	            table.getMainTable().getColumnModel().getColumn(k).setCellEditor(new MyTableCellEditorTextEditable(false));
	            table.getMainTable().getColumnModel().getColumn(k).setCellRenderer(dtcrColumn);
	        }
	        
	        
	        table.getRowHeaderTable().setBackground(new Color(255, 254, 203));
	        table.getMainTable().setBackground(new Color(255, 254, 203));
	        
	        
	        JLabel label = new JLabel("Total: ");
	        label.setHorizontalAlignment(SwingConstants.TRAILING);
	        label.setVerticalAlignment(SwingConstants.TOP);
	        table.setCorner(JScrollPane.LOWER_LEFT_CORNER, label);

	        table.getColumnFooterTable().setBackground(new Color(178, 178, 142));
	        for (int i = 0; i < HEADERM.length; i++) {
	        	table.getColumnFooterTable().getColumnModel().getColumn(i).setPreferredWidth(60);
	        	table.getColumnFooterTable().getColumnModel().getColumn(i).setCellEditor(new MyTableCellEditorTextEditable(false));
	            table.getColumnFooterTable().getColumnModel().getColumn(i).setCellRenderer(dtcrColumn);	
	        }
	        for (int j = HEADERM.length; j < HEADERM.length + HEADERR.length; j++) {
	        	table.getColumnFooterTable().getColumnModel().getColumn(j).setPreferredWidth(100);
	        	table.getColumnFooterTable().getColumnModel().getColumn(j).setCellEditor(new MyTableCellEditorTextEditable(false));
	            table.getColumnFooterTable().getColumnModel().getColumn(j).setCellRenderer(dtcrColumn);	
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



		public String[] getHEADERM() {
			return HEADERM;
		}
	}