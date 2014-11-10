package com.spirit.general.mdb.messages.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.GenericMessenger;

@Stateless
public class ObjectMessenger extends GenericMessenger implements Serializable, ObjectMessengerLocal{

	protected List<Object> objectList;

	public void add(Object param) {
		if (this.objectList == null) {
			this.objectList = new ArrayList<Object>();
		}
		this.objectList.add(param);
	}

	public void clear() {
		if (this.objectList != null)
			this.objectList.clear();
	}

	public List<Object> getObjects() {
		return objectList;
	}

	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}

}
