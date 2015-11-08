package com.dgc.chesstest.pieces;

public class Knight extends ChessPiece {
	private static final long serialVersionUID = -1798945156717259896L;

	public Knight(String team, int x, int y){
		super(team, team.toLowerCase().equals("black") ? "♞" : "♘", x, y);
	}
	
	@Override
	public boolean validMove(int deltaX, int deltaY){
		//Check that the knight moved 1 in a single direction, and 2 in the other.
		boolean moveValid = !(deltaX == 0 && deltaY == 0) && ((deltaX == 1 && (deltaY == -2 || deltaY == 2)) || (deltaX == -1 && (deltaY == -2 || deltaY == 2)) || (deltaY == 1 && (deltaX == -2 || deltaX == 2)) || (deltaY == -1 && (deltaX == -2 || deltaX == 2)));
		
		return moveValid;
	}

}
