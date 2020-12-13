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

	public void updatePieceLocation(Tile t1, Tile t2){
		Piece p = t1.getPiece();
		p.moved();
		
		if(p instanceof Pawn){
			for(int i = 0; i<8; i++){
				if(t2 == boardTiles[0][i] || t2 == boardTiles[7][i]){
					facade.showPawnPromotionMenu(t2);
				}
			}
		}
		
		t2.setPiece(p);
		t1.setPiece(null);
		
		if(pieceThreatensKing(t2) == true){
			System.out.println("check");
			if(p.getColor() == PlayerColor.WHITE){
				kingChecked = PlayerColor.BLACK;
			}else{
				kingChecked = PlayerColor.WHITE;
			}
		}
		nextPlayer();
	}
		
	public void roque(Tile t1, Tile t2){
		if(t2.getColumn() == 7){
			updatePieceLocation(t1, boardTiles[t1.getRow()][6]);
			updatePieceLocation(t2, boardTiles[t1.getRow()][5]);
		}
		nextPlayer();
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
	
    public Boolean pieceThreatensKing(Tile t){
        List<Tile> movementOptions;
        
        if(t.getPiece() != null){
            movementOptions = t.getPiece().getPossibleMoviments(t);
            for(Tile tile: movementOptions){
                if(tile.getPiece() != null){
                    if(tile.getPiece() instanceof King){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public PlayerColor getKingChecked(){
		return kingChecked;
	}
	
	public void setKingChecked(PlayerColor playerColor){
		kingChecked = playerColor;
	}
}

	
