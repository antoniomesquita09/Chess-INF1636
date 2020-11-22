package models;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Piece {
	
	PlayerColor color;
	protected Image image;
	Boolean hasMoved = false;
	
	Piece(PlayerColor color, File file) {
		this.color = color;
		try {
			this.image = ImageIO.read(file);
		} catch(IOException error) {
			System.out.println(error.getMessage());
			System.exit(1);
		}
	}
	
	public Image getImage(){
		return image;
	}

	public List<Tile> getPossibleMoviments(Tile tile) {
		List<Tile> possibleMoviments = new ArrayList<Tile>();
		
		return possibleMoviments;
	}
	
	public void moved(){
		hasMoved = true;
	}
	
	public Boolean getMovedState(){
		return hasMoved;
	}
	
	public PlayerColor getColor(){
		return color;
	}

}
