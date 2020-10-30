package models;

public class king {
	private static king single_instance = null;
	
	private king() {
	}
	
	public static king getInstance() {
		if (single_instance == null)
			single_instance = new king();
		return single_instance;
	}
	
	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		board boardInstance = board.getInstance();
		char destinationField = boardInstance.board[rowDestination][columnDestination];
		boolean condition1 = (Math.abs(row - rowDestination) == 1) && (column == columnDestination);
		boolean condition2 = (Math.abs(column - columnDestination) == 1) && (row == rowDestination);
		boolean oneSquareStraight = condition1 || condition2;
		boolean oneSquarediagonal = (Math.abs(row - rowDestination) == 1) && (Math.abs(column - columnDestination) == 1);
		boolean kingMove = oneSquareStraight || oneSquarediagonal;
		
		if(!kingMove) return false;
		
		// Kill destination field piece logic
		if (player == "white") {
			System.out.printf("destination field: %c\n", destinationField);
			boolean filledByAlly = Character.isUpperCase(destinationField); // or empty field
			return !filledByAlly;
		}
				
		boolean filledByAlly = Character.isLowerCase(destinationField); // or empty field
		return !filledByAlly;
			
	}
}
