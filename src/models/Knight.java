package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
	List<Tile> possibleMoviments;
	Tile[][] board;
	Piece piece;
	Piece temp;

	Knight(PlayerColor color) {
		super(color, new File("Pecas/Pecas_1/" + (color == PlayerColor.WHITE ? "b_cavalo.gif" : "p_cavalo.gif")));
	}
	
	public List<Tile> getPossibleMoviments(Tile tile) {
		possibleMoviments = new ArrayList<Tile>();
		board = Board.getInstance().getBoardTiles();
		
		int row = tile.getRow();
		int column = tile.getColumn();
		
		piece = board[row][column].getPiece();
		
		int i; int j;
		int a = 2; int b = 1;
		
		for(int w=0; w<=3; w++){
			i = row+a; j = column+b;
			addPossibleMoviment(i, j, row, column);
			i = row+b; j = column+a;
			addPossibleMoviment(i, j, row, column);
			
			if(w % 2 == 0){
				a=a*(-1); 
			}else{
				b=b*(-1);
			}
		}
				
		return possibleMoviments;
	}
	
	private void addPossibleMoviment(int i, int j, int row, int column){
		if(i>=0 && i<=7 && j>=0 && j<=7){
			Tile tile = board[i][j];
			if(Board.getInstance().getPlayerTurn() == piece.getColor()){
				temp = tile.getPiece();
				tile.setPiece(piece);
				board[row][column].setPiece(null);
				
				for(Tile[] tiles: board){
					for(Tile t: tiles){
						if(t.getPiece() != null && !(t.getPiece() instanceof King)){
							if(t.getPiece().getColor() != piece.getColor()){
								if(Board.getInstance().pieceThreatensKing(tile)){
									board[i][j].setPiece(temp);
									board[row][column].setPiece(piece);
									return;
								}
							}
						}
					}
				}
				
				tile.setPiece(temp);
				board[row][column].setPiece(piece);
			}
			
			if(tile.getPiece() != null){
				if(tile.getPiece().getColor() != piece.getColor()){
					possibleMoviments.add(tile);
				}
			} else {
				possibleMoviments.add(tile);
			}
		}
	}

}
