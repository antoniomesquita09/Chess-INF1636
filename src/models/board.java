package models;

public class board {
	private static board single_instance = null;
	public char[][] board = {
			{'r','k','b','q','a','b','k','r'},
	        {'p','p','p','p','p','p','p','p'},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {'P','P','P','P','P','P','P','P'},
	        {'R','K','B','Q','A','B','K','R'}};
	
	public static synchronized board getInstance() {
		if (single_instance == null)
			single_instance = new board();
		return single_instance;
	}
}
