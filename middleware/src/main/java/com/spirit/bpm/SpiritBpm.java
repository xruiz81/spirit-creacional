package com.spirit.bpm;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;

public class SpiritBpm {
	public static JbpmConfiguration jbpmConfiguration = null; 
	
	public static void iniciarBpm(){
		jbpmConfiguration = JbpmConfiguration.getInstance();
	}
	
	/*public static JbpmConfiguration jbpmConfiguration = null; 

	static {
		System.out.println("**********************************************");
		jbpmConfiguration = JbpmConfiguration.getInstance();
		System.out.println("**********************************************");
	}*/

}
