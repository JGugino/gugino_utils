package org.gugino.util;

public class IDCreator {

	private int currentID = 0;
	private int minimumID = 0;
	
	public IDCreator() {
		this.minimumID = 0;
		this.currentID = this.minimumID;
	}
	
	public IDCreator(int _minIDS) {
		this.minimumID = _minIDS;
		this.currentID = this.minimumID;
	}
	
	public int nextID() {
		return ++currentID;
	}
	
	public int resetIDCreator() {
		return currentID = minimumID;
	}
	
	public int currentID() {
		return currentID;
	}
}
