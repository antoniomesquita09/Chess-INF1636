package models;

public class queen {
	private static queen single_instance = null;
	
	private queen() {
	}
	
	public static queen getInstance() {
		if (single_instance == null)
			single_instance = new queen();
		return single_instance;
	}
	public boolean move(String player, int row, int column, int rowDestination, int columnDestination) {
		//queen = rook + bishop
		bishop bishopInstance = bishop.getInstance();
		boolean bishopCondition = bishopInstance.move(player, row, column, rowDestination, columnDestination);
		rook rookInstace = rook.getInstance();
		boolean rookCondition = rookInstace.move(player, row, column, rowDestination, columnDestination);
		return bishopCondition || rookCondition ;
	}
}
