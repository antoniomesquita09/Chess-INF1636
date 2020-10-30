package models;

public class rei {
	private static rei single_instance = null;
	
	private rei() {
	}
	
	public static rei getInstance() {
		if (single_instance == null)
			single_instance = new rei();
		return single_instance;
	}
}
