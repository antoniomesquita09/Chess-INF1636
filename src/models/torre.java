package models;

public class torre {
	private static torre single_instance = null;
	
	private torre() {
	}
	
	public static torre getInstance() {
		if (single_instance == null)
			single_instance = new torre();
		return single_instance;
	}
}
