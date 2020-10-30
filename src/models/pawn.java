package models;

public class pawn {
	private static pawn single_instance = null;
	
	private pawn() {
	}
	
	public static pawn getInstance() {
		if (single_instance == null)
			single_instance = new pawn();
		return single_instance;
	}
	
	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		board boardInstance = board.getInstance();
		char destinationField = boardInstance.board[rowDestination][columnDestination];
		boolean emptyDestinationField = destinationField == ' ';
		if (player == "white") {
			boolean filledByAlly = Character.isUpperCase(destinationField); // or empty field
			boolean simpleMove = rowDestination == row - 1 && column == columnDestination && emptyDestinationField;
			boolean diagonalMove = rowDestination == row - 1 && (column == columnDestination + 1 || column == columnDestination - 1) && !filledByAlly && !emptyDestinationField;
			return simpleMove || diagonalMove;
		}
		boolean filledByAlly = Character.isLowerCase(destinationField); // or empty field
		boolean simpleMove = rowDestination == row + 1 && column == columnDestination && emptyDestinationField;
		boolean diagonalMove = rowDestination == row + 1 && (column == columnDestination + 1 || column == columnDestination - 1) && !filledByAlly && !emptyDestinationField;
		return simpleMove || diagonalMove;
	}
}
