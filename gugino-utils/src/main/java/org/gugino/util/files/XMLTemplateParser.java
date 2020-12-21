package org.gugino.util.files;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.gugino.util.enums.CloseOperations;
import org.gugino.util.windows.ContainerHolder;
import org.gugino.util.windows.Window;
import org.gugino.util.windows.WindowHandler;
import org.gugino.util.windows.WindowInformation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTemplateParser {
	
	public static WindowInformation windowInfo;
	
	public static void parseXMLTemplate(Window _window, Document _document) {
		windowInfo = new WindowInformation();
		windowInfo.windowID = _window.windowID();
		
		/*Parsing Process:
		 * First look for window tag  and pull window attributes
		 * then look for main layout tag and pull type attribute
		 * then check for panels then children of those panels
		 * */
		
		NodeList windowNodesList = _document.getElementsByTagName("Window");
		
		if(windowNodesList.getLength() <= 0) {System.err.println("No window tag found!"); return;
		}else if(windowNodesList.getLength() > 1) { System.err.println("You can only have one window tag!"); return;}
		
		windowSetup(_window, windowNodesList.item(0));
		
	}
	
	private static void windowSetup(Window _window, Node _node) {
		Element _windowNode = ((Element) _node);
		
		//Finds and assigns title attribute for window
		if(!_windowNode.hasAttribute("title")) {windowInfo.windowTitle = "Default Title";}
		else {windowInfo.windowTitle = _windowNode.getAttribute("title");}
		
		//Finds and assigns width attribute for window
		if(!_windowNode.hasAttribute("width")) {windowInfo.windowWidth = 800;}
		else {windowInfo.windowWidth = Integer.parseInt(_windowNode.getAttribute("width"));}
		
		//Finds and assigns height attribute for window
		if(!_windowNode.hasAttribute("height")) {windowInfo.windowHeight = 600;}
		else {windowInfo.windowHeight = Integer.parseInt(_windowNode.getAttribute("height"));}
				
		//Finds and assigns close operation attribute for window
		if(!_windowNode.hasAttribute("closeOP")) {windowInfo.closeOperation = CloseOperations.EXIT;}
		else {
			switch(_windowNode.getAttribute("closeOp")) {
			case "DO_NOTHING":
				windowInfo.closeOperation = CloseOperations.DO_NOTHING;
				break;
			case "HIDE":
				windowInfo.closeOperation = CloseOperations.HIDE;
				break;
			case "DISPOSE":
				windowInfo.closeOperation = CloseOperations.DISPOSE;
				break;
			case "EXIT":
				windowInfo.closeOperation = CloseOperations.EXIT;
				break;
			default:
				System.err.println("Unknown close operation!");
				break;
			}
		}
		
		//Finds and assigns resize attribute for window
		if(!_windowNode.hasAttribute("resize")) {windowInfo.resizable = true;}
		else {
			switch(_windowNode.getAttribute("resize")) {
			case "TRUE":
				windowInfo.resizable = true;
				break;
				
			case "FALSE":
				windowInfo.resizable = false;
				break;
			}
		}
		
		//Setting up layout
		NodeList _layoutElement = _windowNode.getElementsByTagName("Layout");
		
		if(_layoutElement.getLength() <= 0) {System.err.println("You must have a layout tag"); return;}
		if(_layoutElement.getLength() > 1) {System.err.println("You can only have one layout tag"); return;}
	
		Element _layout = (Element)_layoutElement.item(0);
		
		if(!_layout.hasAttribute("type")) {windowInfo.windowLayout = new BorderLayout();}
		else {
			String _containerName = "";
			switch(_layout.getAttribute("type")) {
			case "BorderLayout":
				int hgap = 0;
				int vgap = 0;
				
				if(_layout.hasAttribute("hgap")) hgap = Integer.parseInt(_layout.getAttribute("hgap"));
				if(_layout.hasAttribute("vgap")) vgap = Integer.parseInt(_layout.getAttribute("vgap"));
				
				windowInfo.windowLayout = new BorderLayout(hgap, vgap);
				break;
			case "BoxLayout":
				Container _container = null;
				int _pageAxis = 0;
				
				if(_layout.hasAttribute("axis")) _pageAxis = Integer.parseInt(_layout.getAttribute("axis"));
				
				NodeList _containerNodes = _layout.getElementsByTagName("Panel");
				
				if(_containerNodes.getLength() > 0) {
					for (int _p = 0; _p < _containerNodes.getLength(); _p++) {
						Element _panelElement = (Element) _containerNodes.item(_p);
						
						if(_panelElement.hasAttribute("layoutContainer")) {
							_container = new JPanel();
							_containerName = _panelElement.getAttribute("layoutContainer");
							
							if(_panelElement.hasAttribute("name")) {
								_containerName = _panelElement.getAttribute("name");
							}
							
							windowInfo.windowLayout = new BoxLayout(_container, _pageAxis);
							
							ContainerHolder _containerHolder = new ContainerHolder(_containerName, "panel", _container);
							windowInfo.windowContainers.put(_containerName, _containerHolder);
						}
					}
				}
				
				if(_container == null) System.err.println("Failed to create layout container");
				break;
			case "CardLayout":
				
				break;
			case "FlowLayout":
				
				break;
			case "GridBagLayout":
				
				break;
			case "GridLayout":
				
				break;
			case "GroupLayout":
				
				break;
			case "SpringLayout":
				
				break;
				
				default:
					System.err.println("Unknown layout type");
					break;
			}
						
			_window.windowContainers.put(_containerName, windowInfo.windowContainers.get(_containerName));
			
			_window.setTitle(windowInfo.windowTitle);
			_window.setPreferredSize(new Dimension(windowInfo.windowWidth, windowInfo.windowHeight));
			_window.setMinimumSize(new Dimension(windowInfo.windowWidth, windowInfo.windowHeight));
			_window.setDefaultCloseOperation(windowInfo.closeOperation.operation);
			_window.setLocationRelativeTo(_window.getRootPane());
			_window.setResizable(windowInfo.resizable);
			_window.setLayout(windowInfo.windowLayout);
			_window.pack();
			_window.setVisible(true);
		}
	}
}
