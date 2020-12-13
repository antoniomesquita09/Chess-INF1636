package views;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controllers.TileController;
import controllers.PieceController;
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
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		TileInterface tileInterface;
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				tileInterface = new TileInterface(j * (boardSize/8), i * (boardSize/8), (boardSize/8), (boardSize/8));
				Rectangle2D tileRect = tileInterface.getSquare();
				Tile tile = boardTiles[i][j];
				Boolean tileSelection = TileController.getInstance().getTileSelection(tile);
				Boolean tileHighlighted = TileController.getTileHighlighted(tile);
				

				if (tileSelection == true) {
					g2d.setPaint(Color.CYAN);
				} else {
					if (tileHighlighted == true) {
						g2d.setPaint(Color.GREEN);
						TileController.setTileHighlighted(tile, false);
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
				
				Piece piece = TileController.getTilePiece(tile);

				if(piece != null) {
					Image pieceImage = PieceController.getInstance().getPieceImage(piece);
					g.drawImage(pieceImage, j * (boardSize/8) + (boardSize/32), i * (boardSize/8) + (boardSize/32), null);
				}
			}
		}
	}
}
