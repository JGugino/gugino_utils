package org.gugino.utils;

import org.gugino.util.windows.WindowHandler;

public class WindowTest {

	public static void main(String[] args) {
		WindowHandler _handler = new WindowHandler();
		
		_handler.createNewWindow("./test_template.xml");
	}

}
