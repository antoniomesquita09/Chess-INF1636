package models;
import java.awt.Image;
import java.util.List;

import controllers.BoardController;
import views.BoardFrame;

public class Facade {
	
	private static Facade singleInstance = null;
	private static BoardFrame boardFrame = null;
	
	private Facade(){
		
	}
	
	public static Facade getInstance(){
		if(singleInstance == null){
			singleInstance = new Facade();
		}
		return singleInstance;
	}
	
	public void initBoardFrame(Board board){
		boardFrame = BoardFrame.getInstance(board);
	}
	
	public PlayerColor getPlayerTurn(){
		return Board.getInstance().getPlayerTurn();
	}
	
	public Tile getTile(int row, int column){
		Tile[][] boardMatrix =  Board.getInstance().getBoardTiles();
		return boardMatrix[row][column];
	}
	
	public Piece getTilePiece(Tile tile){
		return tile.getPiece(); 
	}
	
	public PlayerColor getPieceColor(Piece p){
		if(p != null){
			return p.getColor();
		}
		return null;
	}
	
	public List<Tile> highlightPossibleMoviments(int row, int column){
		return BoardController.getInstance().highlightPossibleMoviments(row, column);
	}
	
	public Tile[][] getBoardTiles(){
		return Board.getInstance().getBoardTiles();
	}
	
	public void updateBoardPieceLocation(Tile firstTile, Tile secondTile){
		BoardController.getInstance().updatePieceLocation(firstTile, secondTile);
	}
	
	public void setTileSelection(Tile t, Boolean b) {
		t.setSelected(b);
	}
	
	public Boolean getTileSelection(Tile tile){
		return tile.getSelected();
	}
	
	public void setTileHighlighted(Tile tile, Boolean state){
		tile.setHighlighted(state);
	}
	
	public Boolean getTileHighlighted(Tile t){
		return t.getHighlighted();
	}
		
	public Image getPieceImage(Piece p){
		return p.getImage();
	}

}
