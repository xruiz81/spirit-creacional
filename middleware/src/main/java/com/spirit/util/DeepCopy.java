package com.spirit.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepCopy {
	public static Object copy(Object orig) {
		Object obj = null;
		try {
			FastByteArrayOutputStream fbos = new FastByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(fbos);
			out.writeObject(orig);
			out.flush();
			out.close();
			ObjectInputStream in = new ObjectInputStream(fbos.getInputStream());
			obj = in.readObject();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return obj;
	}
}

