package org.gugino.test;

import java.awt.Dimension;


import org.gugino.util.enums.CloseOperations;
import org.gugino.util.windows.WindowHandler;

public class Start {

	public static void main(String[] args) {
		WindowHandler.createNewWindow("Window", new Dimension(800, 600), CloseOperations.EXIT);
	}
}
