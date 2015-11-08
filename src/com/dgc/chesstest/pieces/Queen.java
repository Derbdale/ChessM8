package com.dgc.chesstest.pieces;

import com.dgc.chesstest.utils.ChessBoard;

public class Queen extends ChessPiece {
	private static final long serialVersionUID = 7248573117697741040L;

	public Queen(String team, int x, int y){
		super(team, team.toLowerCase().equals("black") ? "♛" : "♕", x, y);
	}
	
	@Override
	public boolean validMove(int deltaX, int deltaY){
		//Check that the queen moved in a straight line.
		boolean moveValid = !(deltaX == 0 && deltaY == 0) && (deltaX == 0 || deltaY == 0) || !(deltaX == 0 && deltaY == 0) && (Math.abs(deltaX) == Math.abs(deltaY));
		//Check that there was no unwanted collisions.
		boolean collision = !ChessBoard.checkCollision(this.x, this.y, deltaX, deltaY);
		
		return moveValid && collision;
	}

}
