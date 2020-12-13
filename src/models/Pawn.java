package models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
	List<Tile> possibleMoviments;
	Tile[][] board;
	Piece piece;
	Piece temp;
	
	Pawn(PlayerColor color) {
		super(color, new File("Pecas/Pecas_1/" + (color == PlayerColor.WHITE ? "b_peao.gif" : "p_peao.gif")));
	}
	
	public List<Tile> getPossibleMoviments(Tile tile) {
		possibleMoviments = new ArrayList<Tile>();
		board = Board.getInstance().getBoardTiles();
				
		int row = tile.getRow();
		int column = tile.getColumn();
		
		piece = board[row][column].getPiece();
		int i = row + 1; 
		int j = column;
		
		if(color == PlayerColor.WHITE){
			i = row - 1;
			if(hasMoved == false){
				if(board[i-1][j].getPiece() == null){
					addPossibleMoviment(i-1, j, row, column);
				}
			}
		} else{
			if(hasMoved == false){
				if(board[i+1][j].getPiece() == null){
					addPossibleMoviment(i+1, j, row, column);
				}
			}
		}
		
		
		if(row >= 0 && row <= 7){
			if(board[i][j].getPiece() == null){
				addPossibleMoviment(i, j, row, column);
			}
			if(column<7){
				j = column + 1;
				if(board[i][j].getPiece() != null && (board[i][j].getPiece().getColor() != piece.getColor() )){
					addPossibleMoviment(i, j, row, column);
				}
			}
			if(column>0){
				j = column - 1;
				if(board[i][j].getPiece() != null && (board[i][j].getPiece().getColor() != piece.getColor() )){
					addPossibleMoviment(i, j, row, column);
				}
			}
		}

		return possibleMoviments;
	}
	
	private void addPossibleMoviment(int i, int j, int row, int column){
		PlayerColor playerColor = Board.getInstance().getPlayerTurn();
		Tile tile = board[i][j];
		if(playerColor == piece.getColor()){
			temp = tile.getPiece();
			tile.setPiece(piece);
			board[row][column].setPiece(null);		
			tile.setPiece(temp);
			board[row][column].setPiece(piece);
		}
		
		possibleMoviments.add(tile);
	}
}
