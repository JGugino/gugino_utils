package org.gugino.util.files;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.gugino.util.IDCreator;
import org.gugino.util.enums.CloseOperations;
import org.gugino.util.windows.ComponentHolder;
import org.gugino.util.windows.ContainerHolder;
import org.gugino.util.windows.Window;
import org.gugino.util.windows.WindowInformation;
import org.gugino.util.windows.Windows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTemplateParser {
	
	public WindowInformation windowInfo;
	
	public void parseXMLTemplate(Document _document) {
		windowInfo = new WindowInformation();
		
		Window _createdWindow = new Window(Windows.id.nextID());
		windowInfo.windowID = _createdWindow.windowID();
		
		/*Parsing Process:
		 * First look for window tag  and pull window attributes
		 * then look for main layout tag and pull type attribute
		 * then check for panels then children of those panels
		 * */
		
		NodeList windowNodesList = _document.getElementsByTagName("Window");
		
		if(windowNodesList.getLength() <= 0) {System.err.println("No window tag found!"); return;
		}else if(windowNodesList.getLength() > 1) { System.err.println("You can only have one window tag!"); return;}
		
		windowSetup(_createdWindow, windowNodesList.item(0));
		
	}
	
	private void windowSetup(Window _window, Node _node) {
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
		if(!_windowNode.hasAttribute("closeOp")) { windowInfo.closeOperation = CloseOperations.EXIT;}
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
			String _layoutType =  _layout.getAttribute("type");
			if(_layoutType.equals("BorderLayout")) {
				int hgap = 0;
				int vgap = 0;
				
				if(_layout.hasAttribute("hgap")) hgap = Integer.parseInt(_layout.getAttribute("hgap"));
				if(_layout.hasAttribute("vgap")) vgap = Integer.parseInt(_layout.getAttribute("vgap"));
				
				windowInfo.windowLayout = new BorderLayout(hgap, vgap);
			}else if(_layoutType.equals("BoxLayout")) {
				int _pageAxis = 0;
				
				if(_layout.hasAttribute("axis")) _pageAxis = Integer.parseInt(_layout.getAttribute("axis"));
				
				windowInfo.windowLayout = new BoxLayout(_window.getContentPane(), _pageAxis);
			}else if(_layoutType.equals("CardLayout")) {
				
			}else if(_layoutType.equals("FlowLayout")) {
				
			}else if(_layoutType.equals("GridBagLayout")) {
				
			}else if(_layoutType.equals("GroupLayout")) {
				
			}else if(_layoutType.equals("SpringLayout")) {
				
			}else {
				System.err.println("Unknown layout type");
			}
			
			//Assigns all vlaue's for the window
			_window.getContentPane().setLayout(windowInfo.windowLayout);
			_window.setTitle(windowInfo.windowTitle);
			_window.setPreferredSize(new Dimension(windowInfo.windowWidth, windowInfo.windowHeight));
			_window.setMinimumSize(new Dimension(windowInfo.windowWidth, windowInfo.windowHeight));
			_window.setDefaultCloseOperation(windowInfo.closeOperation.operation);
			_window.setLocationRelativeTo(_window.getRootPane());
			_window.setResizable(windowInfo.resizable);
			
			IDCreator _id = new IDCreator();

			//Applies the layout to the content pane of the window's JFrame
			NodeList _layoutPanels = _layout.getElementsByTagName("Panel");
			if(_layoutPanels.getLength() > 0) {
				for (int _p = 0; _p < _layoutPanels.getLength(); _p++) {
					Element _panel = (Element) _layoutPanels.item(_p);
					JPanel _createdPanel = new JPanel();
					String _containerName = "";
					
					if(!_panel.hasAttribute("name")) _containerName = "panel_"+_id.nextID();
					else _containerName = _panel.getAttribute("name");

					ContainerHolder _container = new ContainerHolder(_window.windowID(), _containerName, "panel", _createdPanel);
					
					Windows.windowContainers.put(_containerName, _container);
					if(windowInfo.windowLayout instanceof BorderLayout) {
						if(_panel.hasAttribute("borderLayoutPos")) {
							switch(_panel.getAttribute("borderLayoutPos")) {
							case "LINE_START":
								_window.getContentPane().add(_createdPanel, BorderLayout.LINE_START);	
								break;
							case "CENTER":
								_window.getContentPane().add(_createdPanel, BorderLayout.CENTER);	
								break;
							case "LINE_END":
								_window.getContentPane().add(_createdPanel, BorderLayout.LINE_END);	
								break;
							case "PAGE_START":
								_window.getContentPane().add(_createdPanel, BorderLayout.PAGE_START);	
								break;
							case "PAGE_END":
								_window.getContentPane().add(_createdPanel, BorderLayout.PAGE_END);	
								break;
								default:
									_window.getContentPane().add(_createdPanel, BorderLayout.CENTER);
									break;
							}
						}
					}else {
						_window.getContentPane().add(_createdPanel);
					}
					
					addWindowComponents(_window, _panel, _container);
				}	
			}
		}
		
		//Packs and sets the window to visible if it isn't already
		if(!_window.isVisible()){
			_window.pack();
			_window.setVisible(true);
		}
	}
	
	private void addWindowComponents(Window _window, Element _container, ContainerHolder _holder) {
		IDCreator _id = new IDCreator();

		NodeList _containerChildren = _container.getChildNodes();
		
		for (int i = 0; i < _containerChildren.getLength(); i++) {
			Node _node = _containerChildren.item(i);
			switch(_node.getNodeName()) {
			case "Label":
				if(!Windows.windowComponents.containsKey(((Element) _node).getAttribute("name"))) {
					JLabel _createdComponent = new JLabel();
					String _componentName = "";
					
					if(!((Element) _node).hasAttribute("name")) _componentName = "label_"+_id.nextID();
					else _componentName = ((Element) _node).getAttribute("name");

					_createdComponent.setText(_node.getTextContent());
					
					ComponentHolder _component = new ComponentHolder(_window.windowID(), _componentName, "label", _createdComponent, _holder);
					
					Windows.windowComponents.put(_componentName, _component);
					_holder.getContainer().add(_createdComponent);
				}else {
					System.err.println("Component with name " + ((Element) _node).getAttribute("name") + " already exists");
				}
				break;
			case "Button":
				if(!Windows.windowComponents.containsKey(((Element) _node).getAttribute("name"))) {
					JButton _createdComponent = new JButton();
					String _componentName = "";
					
					if(!((Element) _node).hasAttribute("name")) _componentName = "button_"+_id.nextID();
					else _componentName = ((Element) _node).getAttribute("name");

					if(((Element) _node).hasAttribute("width") && ((Element) _node).hasAttribute("height")) _createdComponent.setPreferredSize(new Dimension(Integer.parseInt(((Element) _node).getAttribute("width")), Integer.parseInt(((Element) _node).getAttribute("height"))));
					
					_createdComponent.setText(_node.getTextContent());
					
					ComponentHolder _component = new ComponentHolder(_window.windowID(), _componentName, "button", _createdComponent, _holder);
					
					Windows.windowComponents.put(_componentName, _component);
					_holder.getContainer().add(_createdComponent);
				}else {
					System.err.println("Component with name " + ((Element) _node).getAttribute("name") + " already exists");
				}
				break;
			case "TextField":
				if(!Windows.windowComponents.containsKey(((Element) _node).getAttribute("name"))) {
					JTextField _createdComponent = new JTextField();
					String _componentName = "";
					
					if(!((Element) _node).hasAttribute("name")) _componentName = "textfield_"+_id.nextID();
					else _componentName = ((Element) _node).getAttribute("name");

					if(((Element) _node).hasAttribute("width") && ((Element) _node).hasAttribute("height")) _createdComponent.setPreferredSize(new Dimension(Integer.parseInt(((Element) _node).getAttribute("width")), Integer.parseInt(((Element) _node).getAttribute("height"))));
					
					_createdComponent.setText(_node.getTextContent());
					
					ComponentHolder _component = new ComponentHolder(_window.windowID(), _componentName, "textfield", _createdComponent, _holder);
					
					Windows.windowComponents.put(_componentName, _component);
					_holder.getContainer().add(_createdComponent);
				}else {
					System.err.println("Component with name " + ((Element) _node).getAttribute("name") + " already exists");
				}
				break;
			case "CheckBox":
				if(!Windows.windowComponents.containsKey(((Element) _node).getAttribute("name"))) {
					JCheckBox _createdComponent = new JCheckBox();
					String _componentName = "";
					
					if(!((Element) _node).hasAttribute("name")) _componentName = "checkbox_"+_id.nextID();
					else _componentName = ((Element) _node).getAttribute("name");
					
					if(((Element)_node).hasAttribute("selected")) {
						String _value = ((Element) _node).getAttribute("selected");
						
						if(_value.equals("TRUE")) {
							_createdComponent.setSelected(true);
						}
						
						if(_value.equals("FALSE")) {
							_createdComponent.setSelected(false);
						}
					}
					
					_createdComponent.setText(_node.getTextContent());
					
					ComponentHolder _component = new ComponentHolder(_window.windowID(), _componentName, "checkbox", _createdComponent, _holder);
					
					Windows.windowComponents.put(_componentName, _component);
					_holder.getContainer().add(_createdComponent);
				}else {
					System.err.println("Component with name " + ((Element) _node).getAttribute("name") + " already exists");
				}
				break;
				
			case "ComboBox":
				if(!Windows.windowComponents.containsKey(((Element) _node).getAttribute("name"))) {
					NodeList _listNodes = _node.getChildNodes();
					
					JComboBox<String> _createdComponent = new JComboBox<>();
					String _componentName = "";
					
					for(int _s = 0; _s < _listNodes.getLength(); _s++ ) {
						Node _string = _listNodes.item(_s);
						_createdComponent.insertItemAt(_string.getTextContent(), _s);
						if(_string.hasAttributes()) {
							if(((Element) _string).hasAttribute("selected")) {
								_createdComponent.setSelectedIndex(_s);
							}
						}
					}
					
					if(!((Element) _node).hasAttribute("name")) _componentName = "combobox_"+_id.nextID();
					else _componentName = ((Element) _node).getAttribute("name");

					ComponentHolder _component = new ComponentHolder(_window.windowID(), _componentName, "combobox", _createdComponent, _holder);
					
					Windows.windowComponents.put(_componentName, _component);
					_holder.getContainer().add(_createdComponent);
				}else {
					System.err.println("Component with name " + ((Element) _node).getAttribute("name") + " already exists");
				}
				break;
			}
			
			_window.pack();
		}
	}
}
