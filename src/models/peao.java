package models;

public class peao {
	private static peao single_instance = null;
	
	private peao() {
	}
	
	public static peao getInstance() {
		if (single_instance == null)
			single_instance = new peao();
		return single_instance;
	}
	
	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		tabuleiro chess = tabuleiro.getInstance();
		char destinationField = chess.tabuleiro[rowDestination][columnDestination];
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
