package views;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.BoardController;
import controllers.TileController;
import models.Board;
import models.Piece;
import models.PlayerColor;
import models.Tile;
import controllers.PieceController;

public class BoardFrame extends JFrame {
	
	private static BoardFrame singleInstance = null;
	
	private static Board board;
	public static int boardSize = 640;
	private static Tile[][] boardTiles;
	private BoardPanel boardPanel;
	static Tile selectedTile = null;
	static List<Tile> possibleMoviments = null;
	
	private BoardFrame(Board board){
		setSize(boardSize + 3, boardSize + 25);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BoardFrame.board = board;
		BoardFrame.boardTiles = BoardController.getInstance().getBoardTiles();
		boardPanel = BoardPanel.getInstance(boardTiles, boardSize);
		getContentPane().add(boardPanel);
		
		addMouseListener(boardClickHandler);
		buildMenu();
		
		
		
	}
	private JMenuBar buildMenu() {
		BoardController boardController = BoardController.getInstance();

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		setJMenuBar(menuBar);

		JMenuItem saveAction = new JMenuItem("Save");

		saveAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boardController.saveGame();
            }
        });

		fileMenu.add(saveAction);
		menuBar.add(fileMenu);
		return menuBar;
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

			BoardController boardController = BoardController.getInstance();
			PieceController pieceController = PieceController.getInstance();
			TileController tileController = TileController.getInstance();
	    	
	     	Tile tileClicked = tileController.getTile(row, column);
	    	
	     	if(selectedTile == null) {
				Piece pieceClicked = tileController.getTilePiece(tileClicked);
				PlayerColor pieceColor = pieceController.getPieceColor(pieceClicked);
				PlayerColor playerTurn = pieceController.getPlayerTurn();

	     		if(pieceColor == playerTurn){
		     		if(pieceClicked != null){
		     			selectedTile = tileClicked;
						tileController.getTile(row, column).setSelected(true);
		     			possibleMoviments = boardController.highlightPossibleMoviments(row, column);
		     		}
	     		}
	     	} else {
	     		if(selectedTile != tileClicked){

	     			if(possibleMoviments.contains(tileClicked)){
						boardController.updatePieceLocation(selectedTile, tileClicked);
	     			}

					boardController.roque(tileClicked, selectedTile);

		    		boardController.setRoqueState(tileController.getTile(0, 0), false);
	    			boardController.setRoqueState(tileController.getTile(7, 0), false);
	    			boardController.setRoqueState(tileController.getTile(0, 7), false);
	    			boardController.setRoqueState(tileController.getTile(7, 7), false);

	     			tileController.setTileSelection(selectedTile, false);
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
