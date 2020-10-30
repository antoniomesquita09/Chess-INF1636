package models;

public class rainha {
	private static rainha single_instance = null;
	
	private rainha() {
	}
	
	public static rainha getInstance() {
		if (single_instance == null)
			single_instance = new rainha();
		return single_instance;
	}
}
