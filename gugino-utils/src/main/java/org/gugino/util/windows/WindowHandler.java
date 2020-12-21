package org.gugino.util.windows;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.InputStream;

import org.gugino.util.enums.CloseOperations;
import org.gugino.util.files.FileReader;
import org.gugino.util.files.XMLTemplateParser;

public final class WindowHandler {            
	
	//Constructors for creating new windows using different arguments
	public void createNewWindow(String _template) {
		loadTemplate(_template);
	}
	
	public  Window createNewWindow(String _title, CloseOperations _closeOp) {
		Window _createdWindow = new Window(Windows.id.nextID(), _title, _closeOp);
		Windows.activeWindows.put(_createdWindow.windowID(), _createdWindow);
		return _createdWindow;
	}
	
	public Window createNewWindow(String _title, Dimension _dims, CloseOperations _closeOp) {
		Window _createdWindow = new Window(Windows.id.nextID(), _title, _dims, _closeOp);
		Windows.activeWindows.put(_createdWindow.windowID(), _createdWindow);
		return _createdWindow;
	}
	
	public Window createNewWindow(String _title, Dimension _dims, CloseOperations _closeOp, LayoutManager _layout) {
		Window _createdWindow = new Window(Windows.id.nextID(), _title, _dims, _closeOp, _layout);
		Windows.activeWindows.put(_createdWindow.windowID(), _createdWindow);
		return _createdWindow;
	}
	
	public Window createNewWindow(String _title, Dimension _dims) {
		Window _createdWindow = new Window(Windows.id.nextID(), _title, _dims);
		Windows.activeWindows.put(_createdWindow.windowID(), _createdWindow);
		return _createdWindow;
	}
	
	public  Window createNewWindow(String _title, int _width, int _height) {
		Window _createdWindow = new Window(Windows.id.nextID(), _title, _width, _height);
		Windows.activeWindows.put(_createdWindow.windowID(), _createdWindow);
		return _createdWindow;
	}


	private void loadTemplate(String _templatePath) {
		InputStream _fileStream = getClass().getClassLoader().getResourceAsStream(_templatePath);
		
		XMLTemplateParser _xmlParser = new XMLTemplateParser();
		
		_xmlParser.parseXMLTemplate(FileReader.readXMLFile(FileReader.getStreamAsFile(_fileStream)));
	}
}
