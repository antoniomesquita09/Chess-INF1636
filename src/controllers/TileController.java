package controllers;

import java.util.List;

import models.Board;
import models.Facade;
import models.Piece;
import models.Tile;

public class TileController {
	private static TileController singleInstance = null;
	Facade facade;
	
	public static TileController getInstance() {
		if (singleInstance == null) {
			singleInstance = new TileController();
			singleInstance.facade = Facade.getInstance();
		}
		return singleInstance;
	}
	
	public Tile getTile(int row, int column) {
		return facade.getTile(row, column);
	}
	
	public Tile[][] getBoardTiles() {
		return facade.getBoardTiles();
	}

	public Piece getTilePiece(Tile tile) {
		return facade.getTilePiece(tile);
	}

	public void setTileSelection(Tile t, Boolean b) {
		facade.setTileSelection(t, b);
	}

	public Boolean getTileSelection(Tile t) {
		return facade.getTileSelection(t);
	}

	public Boolean getTileHighlighted(Tile t) {
		return facade.getTileHighlighted(t);
	}

	public void setTileHighlighted(Tile tile, Boolean state) {
		facade.setTileHighlighted(tile, state);
	}
}
