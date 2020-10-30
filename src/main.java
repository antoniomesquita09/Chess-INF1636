
import models.board;

public class main {
	boolean playerA = true;
	
	public static void main(String []args) {
		board boardInstance = board.getInstance();
		// [row][column]
		// chess.board[0][3] = "p";
		char piece = boardInstance.board[1][0];
		
		System.out.printf("Result %b\n", Character.isLowerCase(piece));
		return;
	}
}
