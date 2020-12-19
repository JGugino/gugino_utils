package org.gugino.util.enums;

public enum CloseOperations {
	DO_NOTHING(0),
	HIDE(1),
	DISPOSE(2),
	EXIT(3);

	public int operation;
	
	CloseOperations(int _operation) {
		this.operation = _operation;
	}
		
}
