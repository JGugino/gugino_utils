package org.gugino.util.windows;

import java.util.HashMap;

import org.gugino.util.IDCreator;

public class Windows {
	
	//ID creator instance
	public static IDCreator id = new IDCreator();;

	public static HashMap<Integer, Window> activeWindows = new HashMap<>();
	public static HashMap<String, ComponentHolder> windowComponents = new HashMap<>();
	public static HashMap<String, ContainerHolder> windowContainers = new HashMap<>();
}
