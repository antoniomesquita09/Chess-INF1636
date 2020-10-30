package models;

public class cavalo {
	private static cavalo single_instance = null;
	
	private cavalo() {
	}
	
	public static cavalo getInstance() {
		if (single_instance == null)
			single_instance = new cavalo();
		return single_instance;
	}
}
