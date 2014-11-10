package com.spirit.util;

import javax.swing.JComboBox;

import com.spirit.client.SpiritConstants;
import com.spirit.server.SpiritIf;

public class ComboBoxComponent {

	public static int getIndexToSelectItem(JComboBox jcb, Long id) {
		
		int index = -1;
		
		for(int i=0;i < jcb.getItemCount();i++){
			if (!jcb.getItemAt(i).toString().equals(SpiritConstants.getPlaceholderCharacter())) {
				SpiritIf bean = (SpiritIf) jcb.getItemAt(i);
				if (bean!=null) {
					if (bean.getId().compareTo(id) == 0){
						index = i;
						break;
					}
				}
			}
		}
		
		return index;
	}
	
	public static int getIndexToStringSelectItem(JComboBox jcb, Object o) {
		
		int index = -1;

		for(int i=0;i < jcb.getItemCount();i++){
			Object item = jcb.getItemAt(i);
			if (item!=null) {
				if (item.getClass().getName().equals(SpiritConstants.getBigDecimalClassName())) {
					if (Double.parseDouble(item.toString()) == Double.parseDouble(o.toString())) {
						index = i;
						break;
					}
				}
			}
		}
		
		return index;
	}
	
	public static boolean itemComboExiste(JComboBox jcb, Long id) {
		
		boolean existe = false;
		
		for(int i=0;i < jcb.getItemCount();i++){
			SpiritIf bean = (SpiritIf) jcb.getItemAt(i);
			if (bean!=null) {
				if (bean.getId().compareTo(id) == 0){
					existe = true;
					break;
				}
			}
		}
		
		return existe;
	}
}
