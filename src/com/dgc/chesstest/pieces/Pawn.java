package com.dgc.chesstest.pieces;

import com.dgc.chesstest.utils.ChessBoard;

public class Pawn extends ChessPiece {
	private static final long serialVersionUID = 4561547057818122278L;
	public boolean lastMoveDouble = false;
	private int moveId = 0;
	
	private static int nextMoveId = 0;

	public Pawn(String team, int x, int y){
		super(team, team.toLowerCase().equals("black") ? "♟" : "♙", x, y);
	}
	
	@Override
	public boolean validMove(int deltaX, int deltaY){
		ChessPiece toCapture = ChessBoard.findPiece(this.x + deltaX, this.y + deltaY);
		Pawn enPassant = ChessBoard.findPiece(this.x + deltaX, this.y) instanceof Pawn ? (Pawn)ChessBoard.findPiece(this.x + deltaX, this.y) : null;
		enPassant = toCapture == null && enPassant != null && enPassant.lastMoveDouble ? enPassant : null;
		//Check that the pawn moved forward 2 from original position, 1 from other position, or diagonally to capture.
		boolean moveValid = !(deltaX == 0 && deltaY == 0) && (deltaX == 0 || Math.abs(deltaX) == 1 && ((team.equals("black") && deltaY == 1 && (toCapture != null && toCapture.team.equals("white"))) || (team.equals("white") && deltaY == -1 && (toCapture != null && toCapture.team.equals("black"))))) && ((team.equals("black") && ((this.y == 1 && (deltaY <= 2 && deltaY > 0)) || deltaY == 1)) || team.equals("white") &&((this.y == 6 && (deltaY >= -2 && deltaY < 0)) || deltaY == -1));
		
		if(team.equals("black") && deltaY == 1 && (enPassant != null && enPassant.team.equals("white") && nextMoveId == enPassant.moveId) || team.equals("white") && deltaY == -1 && (enPassant != null && enPassant.team.equals("black") && nextMoveId == enPassant.moveId)){
			ChessBoard.chessPieces.remove(enPassant);
			moveId = ++nextMoveId;
			return true;
		}
		
		//Check that there was no unwanted collisions.
		boolean collision = !ChessBoard.checkCollision(this.x, this.y, deltaX, deltaX == 0 && deltaY != 0 ? deltaY + (deltaY)/Math.abs(deltaY) : deltaY);
		
		lastMoveDouble = moveValid && collision && Math.abs(deltaY) == 2;
		if(moveValid && collision) moveId = ++nextMoveId;;
		
		return moveValid && collision;
	}

}
