package models;

public class queen {
	private static queen single_instance = null;
	
	private queen() {
	}
	
	public static queen getInstance() {
		if (single_instance == null)
			single_instance = new queen();
		return single_instance;
	}
}
