package models;

public class bishop {
	private static bishop single_instance = null;
	
	private bishop() {
	}
	
	public static bishop getInstance() {
		if (single_instance == null)
			single_instance = new bishop();
		return single_instance;
	}
	
	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		board boardInstance = board.getInstance();
		char destinationField = boardInstance.board[rowDestination][columnDestination];
		boolean incrementRow = rowDestination > row;
		boolean incrementColumn = columnDestination > column;
		boolean diagonal = Math.abs(row - rowDestination) == Math.abs(column - columnDestination);

		if(!diagonal) return false;

		int a = -1;
		int b = -1;

		if(incrementRow) {
			 a = 1;
		}
		if(incrementColumn) {
			 b = 1;
		}

		// Move diagonal logic (no obstacle)
		for(int i = row + a; Math.abs(i - rowDestination) > 0; i = i + a) {
			for(int j = column + b; Math.abs(j - columnDestination) > 0; j = j + b) {
				boolean filledField = boardInstance.board[i][j] != ' ';
				if(filledField) {
					return false;
				}
			}	
		}
		
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
