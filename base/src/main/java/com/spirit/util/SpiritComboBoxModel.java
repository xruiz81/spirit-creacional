package com.spirit.util;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

public class SpiritComboBoxModel extends AbstractListModel implements MutableComboBoxModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3256721767031649584L;

	private Object selectedItem;

	private List aList;

	public SpiritComboBoxModel(List arrayList) {
		aList = arrayList;
	}

	public Object getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Object newValue) {
		selectedItem = newValue;
	}

	public int getSize() {
		return aList.size();
	}

	public Object getElementAt(int i) {
		return (aList.get(i));
	}

	public void addElement(Object obj) {
		aList.add(obj);
		
	}

	public void removeElement(Object obj) {
		aList.remove(obj);
		
	}

	public void insertElementAt(Object obj, int index) {
		// TODO Auto-generated method stub
		
	}

	public void removeElementAt(int index) {
		// TODO Auto-generated method stub
		aList.remove(index);
	}
	
	public void removeAllItems(){
		aList.clear();
	}
}