package models;

public class king {
	private static king single_instance = null;
	
	private king() {
	}
	
	public static king getInstance() {
		if (single_instance == null)
			single_instance = new king();
		return single_instance;
	}
}
