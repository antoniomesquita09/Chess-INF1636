package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
	List<Tile> possibleMoviments;
	Tile[][] board;
	Piece piece;
	Piece temp;
	
	Bishop(PlayerColor color) {
		super(color, new File("Pecas/Pecas_1/" + (color == PlayerColor.WHITE ? "b_bispo.gif" : "p_bispo.gif")));
	}
	
	public List<Tile> getPossibleMoviments(Tile tile) {
		possibleMoviments = new ArrayList<Tile>();
		board = Board.getInstance().getBoardTiles();
		
		int row = tile.getRow();
		int column = tile.getColumn();
		
		piece = board[row][column].getPiece();
		
		int i = row - 1; int j = column - 1;
		while(i >= 0 && j >= 0){
			if(addPossibleMoviment(i, j, row, column) == 0) break;
			i--; j--;
		}
		
		i = row-1; j = column+1;
		while(i>=0 && j<=7){
			if(addPossibleMoviment(i, j, row, column) == 0) break;
			i--; j++;
		}
		
		i = row+1; j = column-1;
		while(i<=7 && j>=0){
			if(addPossibleMoviment(i, j, row, column) == 0) break;
			i++; j--;
		}
		
		i = row+1; j = column+1;
		while(i<=7 && j<=7){
			if(addPossibleMoviment(i, j, row, column) == 0) break;
			i++; j++;
		}
		
		return possibleMoviments;
	}
	
	private int addPossibleMoviment(int i, int j, int row, int column){
		PlayerColor player = Board.getInstance().getPlayerTurn();
		Tile tile = board[i][j];
		temp = tile.getPiece();
		
		if(player == piece.getColor()){
			tile.setPiece(piece);
			tile.setPiece(null);
			
			for(Tile[] tiles: board){
				for(Tile t: tiles){
					if(tile.getPiece() != null && !(tile.getPiece() instanceof King)){
						if(tile.getPiece().getColor() != piece.getColor()){
							if(Board.getInstance().pieceThreatensKing(tile)){
								board[i][j].setPiece(temp);
								board[row][column].setPiece(piece);
								if(temp != null){
									return 0; //break here
								}
								return 1;
							}
						}
					}
				}
			}
			
			tile.setPiece(temp);
			board[row][column].setPiece(piece);
		}
		
		if(temp != null){
			if(temp.getColor() != piece.getColor()){
				this.possibleMoviments.add(tile);
			}
			return 0;
		}
		this.possibleMoviments.add(tile);
		return 1;
		
	}
}
