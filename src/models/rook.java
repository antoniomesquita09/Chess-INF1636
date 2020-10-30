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

	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		board boardInstance = board.getInstance();
		char destinationField = boardInstance.board[rowDestination][columnDestination];
		boolean rowStraight = row == rowDestination;
		boolean columnStraight = column == columnDestination;
		boolean straight = rowStraight||columnStraight;
		boolean incrementColumn = columnDestination > column;
		boolean incrementRow = rowDestination > row;
		
		if(!straight) return false;
		
		int a = -1;
		int b = -1;
		
		if(incrementRow) {
			 a = 1;
		}
		if(incrementColumn) {
			 b = 1;
		}
		// Move straight logic (no obstacle)
		if(rowStraight) {
			for(int j = column + b; Math.abs(j - columnDestination) > 0; j = j + b) {
				boolean filledField = boardInstance.board[row][j] != ' ';
				if(filledField) {
					return false;
				}
			}
		}
		
		// Move straight logic (no obstacle)
		if(columnStraight) {
			for(int i = row + a; Math.abs(i - rowDestination) > 0; i = i + a) {
				boolean filledField = boardInstance.board[i][column] != ' ';
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
