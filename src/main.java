
import models.board;
import models.play;

public class main {
	boolean playerA = true;
	
	public static void main(String []args) {
		// board boardInstance = board.getInstance();
		// [row][column]
		// chess.board[0][3] = "p";
		// char piece = boardInstance.board[1][0];
		play playInstance = play.getInstance();
		// playInstance.selectField('p', "black", 1, 0, 2, 0)
		// test
		
		System.out.printf("Move: %b\n",  playInstance.selectField('b', "white", 7, 2, 5, 0));
		return;
	}
}
