package models;

public class play {
	private static play single_instance = null;
	
	private play() {
	}
	
	public static play getInstance() {
		if (single_instance == null)
			single_instance = new play();
		return single_instance;
	}
	
	public boolean selectPiece(String player, int row, int column) {
		tabuleiro chess = tabuleiro.getInstance();
		char field = chess.tabuleiro[row][column];
		boolean filledByAlly = false;
		if (player == "white") {
			filledByAlly = Character.isUpperCase(field);
		} else {			
			filledByAlly = Character.isLowerCase(field);
		}
		return filledByAlly;
	}
	
	public boolean selectField(char piece, String player, int row, int column, int rowDestination, int columnDestination) {
		if (piece == 'p' || piece == 'P') {
			peao pawn = peao.getInstance();
			pawn.move(player, row, column, rowDestination, columnDestination);
		}
		return false;
	}
}
