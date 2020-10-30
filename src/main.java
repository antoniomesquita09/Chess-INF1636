
import models.tabuleiro;

public class main {
	boolean playerA = true;
	
	public static void main(String []args) {
		tabuleiro chess = tabuleiro.getInstance();
		// [row][column]
		// chess.tabuleiro[0][3] = "p";
		char piece = chess.tabuleiro[7][0];
		
		System.out.printf("Result %b\n", Character.isLowerCase(piece));
		return;
	}
}
