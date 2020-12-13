package models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class King extends Piece {
	List<Tile> possibleMoviments;
	Tile[][] board;
	Piece piece;
	Piece temp;
		
	King(PlayerColor color) {
		super(color, new File("Pecas/Pecas_1/" + (color == PlayerColor.WHITE ? "b_rei.gif" : "p_rei.gif")));
	}

	
	public List<Tile> getPossibleMoviments(Tile tile) {
		possibleMoviments = new ArrayList<Tile>();
		board = Board.getInstance().getBoardTiles();
		
		int row = tile.getRow();
		int column = tile.getColumn();
		
		piece = board[row][column].getPiece();
		
		int i; int j; int y; int z;
		
		for(y = -1; y <= 1; y++){
			z = 1;
			i = row + z; j = column + y;
			addPossibleMoviment(i, j, row, column);
			i = row - z;
			addPossibleMoviment(i, j, row, column);
		}
		y=1;
		for(z = 0; z<=1; z++){
			i = row; j = column + y;
			addPossibleMoviment(i, j, row, column);
			y=y*(-1);
		}
		
		return possibleMoviments;
	}
	
	private void addPossibleMoviment(int i, int j, int row, int column){
		Tile tile = board[i][j];
		if(i >= 0 && i <= 7 && j >= 0 && j <= 7){
				temp = tile.getPiece();
				tile.setPiece(new Pawn(this.piece.getColor()));
				board[row][column].setPiece(null);
				for(Tile[] tileList: board){
					for(Tile t: tileList){
						Piece piece = t.getPiece();
						if(piece != null){
							if(piece.getColor() != this.piece.getColor()){
								if(piece instanceof King){
									if(t.getRow()<=i+1 && t.getRow()>=i-1 && t.getColumn()<=j+1 && t.getColumn()>=j-1){
										board[i][j].setPiece(temp);
										board[row][column].setPiece(this.piece);
										return;
									}
								}
								List<Tile> optionsTemp = piece.getPossibleMoviments(t);
								for(Tile option: optionsTemp){
									if(option == tile){
										tile.setPiece(temp);
										board[row][column].setPiece(this.piece);
										return;
									}
								}
							}
						}
					}
				}
			
			if(temp != null){
				if(temp.getColor() != this.piece.getColor()){
					possibleMoviments.add(tile);
				}
			}else{
				possibleMoviments.add(tile);
			}
			tile.setPiece(temp);
			board[row][column].setPiece(this.piece);
		}
	}
}
