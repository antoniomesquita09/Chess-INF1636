package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controllers.BoardController;
import views.BoardPanel;

public class StartMenu {
	
	static JPopupMenu pop;
	
	public static void createPopUpMenu(){
		pop = new JPopupMenu();
		pop.addPopupMenuListener(new PopupMenuListener(){
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO: stuf
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
		});
		
		JMenuItem resumeGame = new JMenuItem("Resume Game");
		resumeGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                // TODO: resume game
			}
		});
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				BoardController.getInstance().newGame();
			}
		});
		
		pop.add(resumeGame);
		pop.add(newGame);
	}
	
	public static void showMenu(int boardDimension){
		pop.show(BoardPanel.getInstance(), boardDimension/2 - 40, boardDimension/2 - 10);
	}
}
