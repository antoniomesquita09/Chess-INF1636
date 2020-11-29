package views;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controllers.BoardController;
import controllers.TileController;

import models.Board;
import models.Facade;
import models.Tile;

public class BoardFrame extends JFrame {
	
	private static BoardFrame singleInstance = null;
	
	private static Board board;
	public static int boardSize = 640;
	private static Tile[][] boardTiles;
	private BoardPanel boardPanel;
	static Tile selectedTile = null;
	static List<Tile> possibleMoviments = null;
	Facade facade;
	
	private BoardFrame(Board board){
		setSize(boardSize + 3, boardSize + 25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BoardFrame.board = board;
		BoardFrame.boardTiles = BoardController.getInstance().getBoardTiles();
		boardPanel = BoardPanel.getInstance(boardTiles, boardSize);
		getContentPane().add(boardPanel);
		
		addMouseListener(boardClickHandler);
	}

	
	public static BoardFrame getInstance(Board board){
		if(singleInstance == null){
			singleInstance = new BoardFrame(board);
			singleInstance.setTitle("Chess Game");
			singleInstance.setVisible(true);
		}
		return singleInstance;
	}
	
	public MouseAdapter boardClickHandler = new MouseAdapter() {
	     @Override
	     public void mouseClicked(MouseEvent event) {
	     	Point clickPoint = event.getPoint();
	    	
	     	int column = (int) (clickPoint.getX()/(boardSize/8));
	     	int row = (int) (clickPoint.getY()/(boardSize/8));
	    	
	     	Tile tileClicked = BoardController.getInstance().getTile(row, column);
	    	
	     	if(selectedTile == null) {
				Piece pieceClicked = BoardController.getTilePiece(tileClicked);
				PlayerColor pieceColor = PieceController.getInstance().getPieceColor(pieceClicked);
				PlayerColor playerTurn = PieceController.getPlayerTurn();

	     		if(pieceColor == playerTurn){
		     		if(pieceClicked != null){
		     			selectedTile = tileClicked;
						TileController.getInstance().getTile(row, column).setSelected(true);
		     			possibleMoviments = BoardController.getInstance().highlightPossibleMoviments(row, column);
		     		}
	     		}
	     	} else {
	     		if(selectedTile != tileClicked){
	    			
	     			if(possibleMoviments.contains(tileClicked)){
						BoardController.updatePieceLocation(selectedTile, tileClicked);
	     			}
	    			    			
	     			TileController.setTileSelection(selectedTile, false);
	     			selectedTile = null;
	     			possibleMoviments = null;
	     		}
	     	}
	     	boardPanel.repaint();
         }
	};
	
	public int getBoardSize(){
		return boardSize;
	}
}
