package models;

public class knight {
	private static knight single_instance = null;
	
	private knight() {
	}
	
	public static knight getInstance() {
		if (single_instance == null)
			single_instance = new knight();
		return single_instance;
	}
	
	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		board boardInstance = board.getInstance();
		char destinationField = boardInstance.board[rowDestination][columnDestination];
		boolean condition1 = (Math.abs(row - rowDestination) == 2) && (Math.abs(column - columnDestination) == 1);
		boolean condition2 = (Math.abs(row - rowDestination) == 1) && (Math.abs(column - columnDestination) == 2);
		boolean Lmove = condition1 || condition2;
		
		if(!Lmove) return false;
		
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
