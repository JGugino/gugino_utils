package org.gugino.utils;

import java.awt.Dimension;

import org.gugino.util.enums.CloseOperations;
import org.gugino.util.windows.WindowHandler;

public class WindowTest {

	public static void main(String[] args) {
		WindowHandler.createNewWindow("Test Window", new Dimension(800, 600), CloseOperations.EXIT);
	}

}
