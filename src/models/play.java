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
		board boardInstance = board.getInstance();
		char field = boardInstance.board[row][column];
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
			pawn pawnInstance = pawn.getInstance();
			return pawnInstance.move(player, row, column, rowDestination, columnDestination);
		}
		if (piece == 'r' || piece == 'R') {
			rook rookInstace = rook.getInstance();
			// TODO: rookInstace.move(player, row, column, rowDestination, columnDestination);
		}
		if (piece == 'b' || piece == 'B') {
			bishop bishopInstance = bishop.getInstance();
			return bishopInstance.move(player, row, column, rowDestination, columnDestination);
		}
		if (piece == 'k' || piece == 'K') { // Knight
			knight knigthInstance = knight.getInstance();
			// TODO: knigthInstance.move(player, row, column, rowDestination, columnDestination);
		}
		if (piece == 'q' || piece == 'Q') {
			queen queenInstance = queen.getInstance();
			// TODO: queenInstance.move(player, row, column, rowDestination, columnDestination);
		}
		if (piece == 'a' || piece == 'A') { // King
			king kingInstance = king.getInstance();
			// TODO: kingInstance.move(player, row, column, rowDestination, columnDestination);
		}
		return false;
	}
}
