package com.dgc.chesstest.pieces;

import com.dgc.chesstest.utils.ChessBoard;

public class Bishop extends ChessPiece {
	private static final long serialVersionUID = -50205357486305000L;

	public Bishop(String team, int x, int y){
		super(team, team.toLowerCase().equals("black") ? "♝" : "♗", x, y);
	}
	
	@Override
	public boolean validMove(int deltaX, int deltaY){
		//Check that the bishop moved diagonally.
		boolean moveValid = !(deltaX == 0 && deltaY == 0) && (Math.abs(deltaX) == Math.abs(deltaY));
		//Check that there was no unwanted collisions.
		boolean collision = !ChessBoard.checkCollision(this.x, this.y, deltaX, deltaY);
		
		return moveValid && collision;
	}

}
