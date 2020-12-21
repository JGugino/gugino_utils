package org.gugino.util.windows;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

public class ContainerHolder {
	public int windowID;
	public String containerName;
	public String containerType;
	private Container container;
	
	public ContainerHolder(int _windowID, String _containerName, String _containerType, Container _container) {
		this.windowID = _windowID;
		this.containerName = _containerName;
		this.containerType = _containerType;
		this.container = _container;
	}
	
	public Container getContainer(){
		switch (containerType) {
		case "panel":
			return (JPanel)container;
		case "scrollpanel":
			return (JScrollPane)container;
		case "splitpanel":
			return (JSplitPane)container;
		case "tabbedpanel":
			return (JTabbedPane)container;
		case "toolbar":
			return (JToolBar)container;
			default:
				System.err.println("Error with component");
			return null;
		}
	}
}
