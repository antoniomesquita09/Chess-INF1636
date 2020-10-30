package models;

public class bishop {
	private static bishop single_instance = null;
	
	private bishop() {
	}
	
	public static bishop getInstance() {
		if (single_instance == null)
			single_instance = new bishop();
		return single_instance;
	}
	
	public void move(int row, int column) {
		
	}
}
