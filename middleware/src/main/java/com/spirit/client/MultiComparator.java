package com.spirit.client;

import java.util.Comparator;
import java.util.Vector;

public class MultiComparator implements Comparator {
	Vector comparators;
	public MultiComparator() {
		comparators = new Vector();
	}
	public void addComparator(Comparator c) {
		comparators.add(c);
	}
	public void removeComparator(Comparator c) {
		comparators.remove(c);
	}
	public int compare(Object obj1, Object obj2) {
		int result, i;
		Comparator c;
		result = 0;
		for(i = 0; i < comparators.size(); i++) {
			c = (Comparator)(comparators.elementAt(i));
			result = c.compare(obj1,obj2);
			if (result != 0) break;
		}
		return result;
	}
}
