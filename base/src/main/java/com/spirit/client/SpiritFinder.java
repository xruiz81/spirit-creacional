package com.spirit.client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.spirit.client.model.SpiritModel;


public class SpiritFinder {

	private JFrame view;
	
	private SpiritModel model;

	private int page = 0;

	private Criteria criteria;

	public SpiritFinder(SpiritModel model, Criteria criteria ){
		
		this.model = model;
		this.criteria = criteria;
				
	}
	
	public List next() {
		
		return find(page++);
	}

	private List find(int page) {
		List result = new ArrayList();//find(criteria);
		System.out.println("Next:" + result);

		if(result!= null && !result.isEmpty()){
			/*
			List data = criteria.getModel(result);
			List headers = criteria.getHeaders();
			FinderTable finderTable = new FinderTable((ArrayList)data, (ArrayList)headers);
			
			finderTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("seleccion objeto");
				}
			});
			*/
			
			
			//pedir a criteria los campos de la tabla.
			// crear la tabla.
			//anadir los datos
			//set numero de pagina
			
			
			
		}
		return null;
	}
	

	public List before() {
		if ( page > 1 ){
			page-=1;
			return find (page);
		}
		return null;
	}

	public List first() {
		page = 1;
		find(page);
		return null;
	}

	public List last() {

		return null;
	}

}
