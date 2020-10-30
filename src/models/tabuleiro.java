package models;

public class tabuleiro {
	private static tabuleiro single_instance = null;
	public char[][] tabuleiro = {
			{'r','k','b','q','a','b','k','r'},
	        {'p','p','p','p','p','p','p','p'},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {' ',' ',' ',' ',' ',' ',' ',' '},
	        {'P','P','P','P','P','P','P','P'},
	        {'R','K','B','Q','A','B','K','R'}};
	
	public static synchronized tabuleiro getInstance() {
		if (single_instance == null)
			single_instance = new tabuleiro();
		return single_instance;
	}
}
