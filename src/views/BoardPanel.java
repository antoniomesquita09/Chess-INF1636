package views;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.geom.Rectangle2D;


import javax.swing.JPanel;


import controllers.TileController;
import controllers.PieceController;
import models.PawnPromotionMenu;
import models.Piece;
import models.Tile;
import models.TileInterface;

public class BoardPanel extends JPanel {
	
	Tile[][] boardTiles;
	int boardSize;
	
	private static BoardPanel singleInstance = null;
	
	private BoardPanel(Tile[][] boardTiles, int boardSize){
		this.boardTiles = boardTiles;
		this.boardSize = boardSize;

		
		
		PawnPromotionMenu.createPopUpMenu();
	}
	
	public static BoardPanel getInstance(Tile[][] boardTiles, int boardSize) { 
		if(singleInstance == null) {
			singleInstance = new BoardPanel(boardTiles, boardSize);
		}
		return singleInstance;
	}
	
	public static BoardPanel getInstance() {
		return singleInstance;
	}
	
	public void paintComponent(Graphics g) {
		TileController tileController = TileController.getInstance();
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		TileInterface tileInterface;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				tileInterface = new TileInterface(j * (boardSize/8), i * (boardSize/8), (boardSize/8), (boardSize/8));
				Rectangle2D tileRect = tileInterface.getSquare();
				Tile tile = boardTiles[i][j];
				Boolean tileSelection = tileController.getTileSelection(tile);
				Boolean tileHighlighted = tileController.getTileHighlighted(tile);
				

				if (tileSelection == true) {
					g2d.setPaint(Color.CYAN);
				} else {
					if (tileHighlighted == true) {
						g2d.setPaint(Color.GREEN);
						tileController.setTileHighlighted(tile, false);
					} else {
						if ((i + j) % 2 == 0) {
							g2d.setPaint(Color.WHITE);
						} else{
							g2d.setPaint(Color.BLACK);
						}
					}
				}
				g2d.fill(tileRect);
				g2d.draw(tileRect);
				
				Piece piece = tileController.getTilePiece(tile);

				if(piece != null) {
					Image pieceImage = PieceController.getInstance().getPieceImage(piece);
					g.drawImage(pieceImage, j * (boardSize/8) + (boardSize/32), i * (boardSize/8) + (boardSize/32), null);
				}
			}
		}
	}
}
