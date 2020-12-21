package org.gugino.util.windows;


import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import org.gugino.util.enums.CloseOperations;


@SuppressWarnings("serial")
public class Window extends JFrame{
	private int windowID;
	
	public Window(int _id) {
		this.windowID = _id;
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
	
	public int windowID() {
		return windowID;
	}
}
