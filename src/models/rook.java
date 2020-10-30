package models;

public class rook {
	private static rook single_instance = null;
	
	private rook() {
	}
	
	public static rook getInstance() {
		if (single_instance == null)
			single_instance = new rook();
		return single_instance;
	}
}
