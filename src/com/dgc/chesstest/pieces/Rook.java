package com.dgc.chesstest.pieces;

import com.dgc.chesstest.utils.ChessBoard;

public class Rook extends ChessPiece {
	private static final long serialVersionUID = -5339797629225654593L;

	public Rook(String team, int x, int y){
		super(team, team.toLowerCase().equals("black") ? "♜" : "♖", x, y);
	}
	
	@Override
	public boolean validMove(int deltaX, int deltaY){
		//Check that the rook moved in a straight line vertically or horizontally.
		boolean moveValid = !(deltaX == 0 && deltaY == 0) && (deltaX == 0 || deltaY == 0);
		//Check that there was no unwanted collisions.
		boolean collision = !ChessBoard.checkCollision(this.x, this.y, deltaX, deltaY);
		
		return moveValid && collision;
	}

}
