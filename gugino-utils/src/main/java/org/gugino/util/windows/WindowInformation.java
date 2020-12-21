package org.gugino.util.windows;

import java.awt.LayoutManager;
import java.util.HashMap;

import org.gugino.util.enums.CloseOperations;

public class WindowInformation {
	public int windowID;
	public String windowTitle;
	public int windowWidth;
	public int windowHeight;
	public CloseOperations closeOperation;
	public boolean resizable = false;
	public LayoutManager windowLayout;
	
	public HashMap<String, ComponentHolder> windowComponents = new HashMap<>();
	public HashMap<String, ContainerHolder> windowContainers = new HashMap<>();
}
