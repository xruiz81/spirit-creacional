package com.spirit.client;

import com.spirit.client.model.SpiritResourceManager;

public class Version {
	public static String VERSION = SpiritResourceManager
	.getPropertyFromFileResource("conf/version.properties", "version");

}
