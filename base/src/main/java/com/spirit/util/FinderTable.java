package com.spirit.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class FinderTable extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4049919363781768243L;

	JTable table;

	//ArrayList model;

	ArrayList data;

	ArrayList headers;
	public FinderTable(ArrayList data, ArrayList headers){
		
		this( data, headers, null);
	}
	
	//TODO: eliminar el parametro MouseListener listener cuando se haya cambiado todos los model

	public FinderTable(ArrayList data, ArrayList headers, MouseListener listener) {
		super("Busqueda");
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 3, (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 3);
		final TableModel dataModel = new ArrayListTableModel(data, headers);

		table = new JTable(dataModel);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(600, 300));
		getContentPane().add(scrollpane);
		pack();
		setVisible(true);
    //TODO: Cuando todos los listener se hayan cambiado en los model, eliminar esta linea
		table.addMouseListener(listener);

	}
	
	public void addMouseListener(MouseListener listener){
		
		table.addMouseListener(listener);
		
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
