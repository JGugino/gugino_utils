package org.gugino.util.windows;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.gugino.util.enums.CloseOperations;
import org.gugino.util.files.FileReader;
import org.gugino.util.files.XMLTemplateParser;


@SuppressWarnings("serial")
public class Window extends JFrame{
	private int windowID;
	
	public HashMap<String, ContainerHolder> windowContainers = new HashMap<>();
	public HashMap<String, ComponentHolder> windowComponents = new HashMap<>();
	
	public Window(int _id, String _templatePath) {
		this.windowID = _id;
		loadTemplate(this, _templatePath);
	}
	
	public Window(int _id, String _title, CloseOperations _closeOperation) {
		this.setTitle(_title);
		this.setDefaultCloseOperation(_closeOperation.operation);
		this.setLocationRelativeTo(rootPane);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		this.windowID = _id;
	}
	
	public Window(int _id, String _title, Dimension _dims, CloseOperations _closeOperation) {
		this.setTitle(_title);
		this.setDefaultCloseOperation(_closeOperation.operation);
		this.setPreferredSize(_dims);
		this.setMinimumSize(_dims);
		this.setLocationRelativeTo(rootPane);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		this.windowID = _id;
	}
	
	public Window(int _id, String _title, Dimension _dims, CloseOperations _closeOperation, LayoutManager _layoutManager) {
		this.setTitle(_title);
		this.setDefaultCloseOperation(_closeOperation.operation);
		this.setPreferredSize(_dims);
		this.setMinimumSize(_dims);
		this.setLocationRelativeTo(rootPane);
		this.setLayout(_layoutManager);
		this.windowID = _id;
	}
	
	public Window(int _id, String _title, Dimension _dims) {
		this.setTitle(_title);
		this.setPreferredSize(_dims);
		this.setLocationRelativeTo(rootPane);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		this.windowID = _id;
	}
	
	public Window(int _id, String _title, int _width, int _height) {
		this.setTitle(_title);
		this.setPreferredSize(new Dimension(_width, _height));
		this.setMinimumSize(new Dimension(_width, _height));
		this.setLocationRelativeTo(rootPane);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
		this.windowID = _id;
	}
	
	private void loadTemplate(Window _window, String _templatePath) {
		InputStream _fileStream = getClass().getClassLoader().getResourceAsStream(_templatePath);
		
		XMLTemplateParser.parseXMLTemplate(_window, FileReader.readXMLFile(FileReader.getStreamAsFile(_fileStream)));
	}
	
	public Component addComponent(String _compName, Component _comp) {
		return _comp;
	}
	
	public Container addContainer(String _containerName, Container _container) {
		return _container;
	}
	
	public int windowID() {
		return windowID;
	}
}
