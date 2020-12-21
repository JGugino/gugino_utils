package org.gugino.util.windows;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

public class ContainerHolder {
	public String containerName;
	public String containerType;
	private Container container;
	
	public ContainerHolder(String _containerName, String _containerType, Container _container) {
		this.containerName = _containerName;
		this.containerType = _containerType;
		this.container = _container;
	}
	
	public Component getComponent(){
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
