package models;

public class knight {
	private static knight single_instance = null;
	
	private knight() {
	}
	
	public static knight getInstance() {
		if (single_instance == null)
			single_instance = new knight();
		return single_instance;
	}
}
