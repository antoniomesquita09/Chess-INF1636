package views;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import models.Facade;
import models.Piece;
import models.Tile;
import models.TileInterface;

public class BoardPanel extends JPanel {
	
	Tile[][] boardTiles;
	int boardSize;
	Facade facade;
	
	private static BoardPanel singleInstance = null;
	
	private BoardPanel(Tile[][] boardTiles, int boardSize){
		this.boardTiles = boardTiles;
		this.boardSize = boardSize;
		
		facade = Facade.getInstance();
		
		//PawnPromotionMenu.createPopUpMenu();
	}
	
	public static BoardPanel getInstance(Tile[][] boardTiles, int boardSize) { 
		if(singleInstance == null){
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
				if (facade.getTileSelection(tile) == true){
					g2d.setPaint(Color.CYAN);
				} else {
					if (facade.getTileHighlighted(tile) == true){
						g2d.setPaint(Color.GREEN);
						facade.setTileHighlighted(tile, false);
					} else {
						if ((i + j) % 2 == 0) {
							g2d.setPaint(Color.WHITE);
						} else{
							g2d.setPaint(Color.BLACK);
						}
					}
					
					if(facade.getRoqueState(tile) == true){
						g2d.setPaint(Color.YELLOW);
					}
				}
				g2d.fill(tileRect);
				g2d.draw(tileRect);
				
				Piece piece = facade.getTilePiece(tile);
				if(piece != null){
					g.drawImage(facade.getPieceImage(piece), j * (boardSize/8) + (boardSize/32), i * (boardSize/8) + (boardSize/32), null);
				}
			}
		}
	}
}
