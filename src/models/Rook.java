package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
	List<Tile> possibleMoviments;
	Tile[][] board;
	Piece piece;
	Piece temp;
	
	Rook(PlayerColor color) {
		super(color, new File("Pecas/Pecas_1/" + (color == PlayerColor.WHITE ? "b_torre.gif" : "p_torre.gif")));
	}

	public List<Tile> getPossibleMoviments(Tile tile) {
		possibleMoviments = new ArrayList<Tile>();
		board = Board.getInstance().getBoardTiles();
		
		int row = tile.getRow();
		int column = tile.getColumn();
		
		piece = board[row][column].getPiece();
		
		int i; int j;
		
		i = row-1; j = column;
		while(i>=0) {
			if(addPossibleMoviment(i, j, row, column) == 0){break;}
			i--;
		}
		
		i = row+1; j = column;
		while(i <= 7) {
			if(addPossibleMoviment(i, j, row, column) == 0){break;}
			i++;
		}
		
		i = row; j = column-1;
		while(j>=0) {
			if(addPossibleMoviment(i, j, row, column) == 0){break;}
			j--;
		}
		
		i = row; j = column+1;
		while(j<=7) {
			if(addPossibleMoviment(i, j, row, column) == 0){break;}
			j++;
		}
			
		return possibleMoviments;
	}
	
	private int addPossibleMoviment(int i, int j, int row, int column){
		Tile tile = board[i][j];
		if(Board.getInstance().getPlayerTurn() == piece.getColor()){
			temp = tile.getPiece();
			tile.setPiece(piece);
			board[row][column].setPiece(null);
			tile.setPiece(temp);
			board[row][column].setPiece(piece);
		}
		
		if(tile.getPiece() != null){
			if(tile.getPiece().getColor() != piece.getColor()){
				this.possibleMoviments.add(tile);
			}
			return 0;
		}
		this.possibleMoviments.add(tile);
		return 1;
		
	}
}
