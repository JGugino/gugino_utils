package org.gugino.util.windows;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;

public class ComponentHolder {
	public int windowID;
	public String componentName;
	public String componentType;
	private Component component;
	public ContainerHolder parentContainer;
	
	public ComponentHolder(int _windowID, String _compName, String _compType, Component _comp, ContainerHolder _parent) {
		this.windowID = _windowID;
		this.componentName = _compName;
		this.componentType = _compType;
		this.component = _comp;
		this.parentContainer = _parent;
	}
	
	public Component getComponent(){
		switch (componentType) {
		case "button":
			return (JButton)component;
		case "textfield":
			return (JTextField)component;
		case "checkbox":
			return (JCheckBox)component;
		case "combobox":
			return (JComboBox<?>)component;
		case "list":
			return (JList<?>)component;
		case "menu":
			return (JMenu)component;
		case "radiobutton":
			return (JRadioButton)component;
		case "slider":
			return (JSlider)component;
		case "spinner":
			return (JSpinner)component;
		case "password":
			return (JPasswordField)component;
		case "textarea":
			return (JTextArea)component;
		case "label":
			return (JLabel)component;
		case "progressbar":
			return (JProgressBar)component;
		case "separator":
			return (JSeparator)component;
		case "tooltip":
			return (JToolTip)component;
			default:
				System.err.println("Error with component");
			return null;
		}
	}
}
