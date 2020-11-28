package models;

import java.util.List;

public class Board {
	private static Board singleInstance = null;
	private static Tile[][] boardTiles = new Tile [8][8];
	Facade facade;
	public PlayerColor kingChecked = null;
	private PlayerColor playerTurn = PlayerColor.WHITE;
	
	public PlayerColor getPlayerTurn(){
		return playerTurn;
	}

	public void nextPlayer() {
		if (playerTurn == PlayerColor.WHITE){
			playerTurn = PlayerColor.BLACK;
		} else {
			playerTurn = PlayerColor.WHITE;
		}
	}
	
	public Tile[][] getBoardTiles() {
		return boardTiles;
	}
	
	private Board(){}
	
	public static Board getInstance() {
		if (singleInstance == null){
			singleInstance = new Board();
		}
		return singleInstance;
	}

	public void init(){
		initTiles();
		initPlayerPieces();
	}

	private void initTiles (){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				boardTiles[i][j] = new Tile(i, j);
			}
		}
	}

	private void initPlayerPieces() {
		for(int i = 0; i < 8; i++){
			boardTiles[0][i].setPiece(initFirstRowPiece(i, PlayerColor.BLACK));
			boardTiles[7][i].setPiece(initFirstRowPiece(i, PlayerColor.WHITE));
			boardTiles[1][i].setPiece(new Pawn(PlayerColor.BLACK));
			boardTiles[6][i].setPiece(new Pawn(PlayerColor.WHITE));
		}
	}

	private Piece initFirstRowPiece(int tileIndex, PlayerColor color) {
		if (tileIndex == 0 || tileIndex == 7){
			return new Rook(color);
		} else if (tileIndex == 1 || tileIndex == 6) {
			return new Knight(color);
		}
		else if (tileIndex == 2 || tileIndex == 5) {
			return new Bishop(color);
		}
		else if (tileIndex == 3) {
			return new Queen(color);
		}
		else if (tileIndex == 4) {
			return new King(color);
		}
		return null;
	}
}
