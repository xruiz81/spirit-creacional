package com.spirit.general.mdb.classlistener;

public class StringUtils {
	public static String revertCamelCase(String s) {
		char[] chars = s.toCharArray();
		String buffer = "";
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				buffer += "_" + String.valueOf(c).toUpperCase();
			} else if (Character.isLowerCase(c)) {
				buffer += String.valueOf(c).toUpperCase();
			}
		}
		return buffer;
	}
}
