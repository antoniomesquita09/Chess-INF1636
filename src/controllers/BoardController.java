package controllers;

import java.io.File;
import java.util.List;

import models.Board;
import models.Facade;
import models.Pawn;
import models.Piece;
import models.PlayerColor;
import models.Tile;

import views.StartMenu;
import views.BoardFrame;

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

	public void initGame() {
		Board board = Board.getInstance();
		BoardFrame boardFrame = BoardFrame.getInstance(board);
		facade.initBoardFrame(board);
		StartMenu.createPopUpMenu();
		StartMenu.showMenu(boardFrame.getBoardSize());
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
		
		if(piece instanceof Pawn){
			for(int i = 0; i<8; i++){
				if(target == facade.getBoardTiles()[0][i] || target == facade.getBoardTiles()[7][i]){
					facade.showPawnPromotionMenu(target);
				}
			}
		}
				
		target.setPiece(piece);
		origin.setPiece(null);
		
		if(Board.getInstance().pieceThreatensKing(target) == true){
			System.out.println("check");
			if(piece.getColor() == PlayerColor.WHITE){
				setKingChecked(PlayerColor.BLACK);
			}else{
				setKingChecked(PlayerColor.WHITE);
			}
		}
		
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
	
	public void setKingChecked(PlayerColor playerColor){
		Board.getInstance().setKingChecked(playerColor);
	}

	public void roque(Tile tileClicked, Tile selectedTile) {
		if(getRoqueState(tileClicked) == true) {
			Board.getInstance().roque(selectedTile, tileClicked);
		}
	}

	public void newGame() {
		Board.getInstance().initNewGame();
	}

	public void saveGame() {
		facade.saveGame();
	}	

	public void resumeGame(File file) {
		facade.resumeGame(file);
	}	
}
