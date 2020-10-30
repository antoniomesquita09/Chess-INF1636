package models;

public class bispo {
	private static bispo single_instance = null;
	
	private bispo() {
	}
	
	public static bispo getInstance() {
		if (single_instance == null)
			single_instance = new bispo();
		return single_instance;
	}
	
	public void move(int row, int column) {
		
	}
}
