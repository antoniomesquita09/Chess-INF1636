package models;
public class Tile {
	
	private Piece piece;
	private Boolean selected = false;
	private Boolean highlighted = false;
	private int row;
	private int column;
	private Boolean roque = false;
	
	Tile(int row, int column){
		this.row = row;
		this.column = column;
		
	}
	
	public void setPiece(Piece piece){
		this.piece = piece;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public void setSelected(Boolean state){
		selected = state;
	}
	
	public Boolean getSelected(){
		return selected;
	}
	
	public void setHighlighted(Boolean state){
		highlighted = state;
	}
	
	public Boolean getHighlighted(){
		return highlighted;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}

	public void setRoque(Boolean b){
		roque = b;
	}
	
	public Boolean getRoque(){
		return roque;
	}
}
