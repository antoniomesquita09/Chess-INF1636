package controllers;

import java.util.List;

import models.Board;
import models.Facade;
import models.Piece;
import models.Tile;

public class BoardController {
	private static BoardController singleInstance = null;
	Facade facade;
	
	public static BoardController getInstance() {
		if (singleInstance == null){
			singleInstance = new BoardController();
			singleInstance.facade = Facade.getInstance();
		}
		return singleInstance;
	}

	public List<Tile> highlightPossibleMoviments(int row, int column) {
		List<Tile> possibleMoviments;
		Tile tile = facade.getBoardTiles()[row][column];
		Piece piece = tile.getPiece();
		possibleMoviments = piece.getPossibleMoviments(tile);
		
		for(Tile t: possibleMoviments){
			t.setHighlighted(true);
		}
		
		return possibleMoviments;
	}
		
	public void updatePieceLocation(Tile origin, Tile target){
		Piece piece = origin.getPiece();
		piece.moved();
				
		target.setPiece(piece);
		origin.setPiece(null);
		
		Board.getInstance().nextPlayer();
	}

	public Tile[][] getBoardTiles(){
		return facade.getBoardTiles();
	}

	public void setRoqueState(Tile t, Boolean b){
		t.setRoque(b);
	}
	
	public Boolean getRoqueState(Tile t){
		return t.getRoque();
	}

	public void roque(Tile tileClicked, Tile selectedTile) {
		if(getRoqueState(tileClicked) == true) {
			Board.getInstance().roque(t1, t2);
		}
	}
}
