package controllers;

import java.util.List;

import models.Board;
import models.Facade;
import models.Piece;
import models.Tile;

public class PieceController {
	private static PieceController singleInstance = null;
	Facade facade;
	
	public static PieceController getInstance() {
		if (singleInstance == null){
			singleInstance = new PieceController();
			singleInstance.facade = Facade.getInstance();
		}
		return singleInstance;
	}
	
	public PlayerColor getPieceColor(Piece p){
		return facade.getPieceColor(p);
	}

    public PlayerColor getPlayerTurn() {
        return facade.getPlayerTurn();
    }
}
