import models.Board;
import models.Facade;

public class main {	
	public static void main(String []args) {
		Board board = Board.getInstance();
		board.init();
		Facade.getInstance().initBoardFrame(board);
	}
}
