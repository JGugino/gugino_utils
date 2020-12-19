package org.gugino.util.windows;

import java.awt.Dimension;
import java.util.HashMap;

import org.gugino.util.IDCreator;
import org.gugino.util.enums.CloseOperations;

public final class WindowHandler {
	
	//ID creator instance
	private static IDCreator id = new IDCreator();;
	
	//A hash of all currently active windows
	private static HashMap<Integer, Window> activeWindows = new HashMap<>();
	
	//Methods for creating new windows using different arguments
	public static Window createNewWindow(String _title) {
		Window _createdWindow = new Window(id.nextID(), _title);
		_createdWindow.setVisible(true);
		return addWindow(_createdWindow);
	}
	
	public static Window createNewWindow(String _title, CloseOperations _closeOp) {
		Window _createdWindow = new Window(id.nextID(), _title, _closeOp);
		_createdWindow.setVisible(true);
		return addWindow(_createdWindow);
	}
	
	public static Window createNewWindow(String _title, Dimension _dims, CloseOperations _closeOp) {
		Window _createdWindow = new Window(id.nextID(), _title, _dims, _closeOp);
		_createdWindow.setVisible(true);
		return addWindow(_createdWindow);
	}
	
	public static Window createNewWindow(String _title, Dimension _dims) {
		Window _createdWindow = new Window(id.nextID(), _title, _dims);
		_createdWindow.setVisible(true);
		return addWindow(_createdWindow);
	}
	
	public static Window createNewWindow(String _title, int _width, int _height) {
		Window _createdWindow = new Window(id.nextID(), _title, _width, _height);
		_createdWindow.setVisible(true);
		return addWindow(_createdWindow);
	}
	
	//Method for checking if a window already exists inside the activeWindows hash and if not adds it to the hash 
	public static Window addWindow(Window _window) {
		if(activeWindows.containsKey(_window.windowID())){
			System.err.println("Window with ID: " + _window.windowID() + " already exists");
			return null;
		}
		
		System.out.println("Window with ID: " + _window.windowID() + " was successfully added to WindowHandler");
		
		return activeWindows.put(_window.windowID(), _window);
	}
	
	//Method for checking if a window exists inside the activeWindows hash and if it does removes it from the hash 
	public static void removeWindow(Window _window) {
		if(!activeWindows.containsKey(_window.windowID())){
			System.err.println("Window with ID: " + _window.windowID() + " doesn't exists");
			return;
		}
		
		activeWindows.remove(_window.windowID(), _window);
		
		System.out.println("Window with ID: " + _window.windowID() + " was successfully removed from WindowHandler");
		return;
	}
	
	//Method for updating the specified window inside the hash
	public static Window updateWindow(Window _window) {
		if(!activeWindows.containsKey(_window.windowID())){
			System.err.println("Window with ID: " + _window.windowID() + " doesn't exists");
			return null;
		}
		
		System.out.println("Window with ID: " + _window.windowID() + " was successfully updated!");
		return activeWindows.replace(_window.windowID(), _window);
	}
	
	//Gets the corresponding window based on the specified ID
	public static Window getWindowByID(int _ID) {
		if(!activeWindows.containsKey(_ID)) {
			System.err.println("No window found with ID: " + _ID);
			return null;
		}
		return activeWindows.get(_ID);
	}
}
