package com.dgc.chesstest.pieces;

import com.dgc.chesstest.pieces.ChessPiece;
import com.dgc.chesstest.pieces.Rook;
import com.dgc.chesstest.utils.ChessBoard;

public class King extends ChessPiece {
	private static final long serialVersionUID = 7154678655764224285L;

	public King(String team, int x, int y){
		super(team, team.toLowerCase().equals("black") ? "♚" : "♔", x, y);
	}
	
	@Override
	public boolean validMove(int deltaX, int deltaY){
		//Check if movement was 1 tile in any direction.
		boolean moveValid =  !(deltaX == 0 && deltaY == 0) && ((deltaX == 0 && (deltaY == -1 || deltaY == 1)) || (deltaX == -1 && (deltaY == -1 || deltaY == 0 || deltaY == 1)) || (deltaX == 1 && (deltaY == -1 || deltaY == 0 || deltaY == 1)));
		
		ChessPiece piece = ChessBoard.findPiece(7, y);
		if(piece != null && !ChessBoard.checkCollision(this.x, this.y, 7 - this.x, 0)){
			if(!hasMoved && !piece.hasMoved && (deltaY == 0 && (deltaX == 2 && piece instanceof Rook))){
				piece.setX(this.x+1);
				return true;
			}
		}
		piece = ChessBoard.findPiece(0, y);
		if(piece != null && !ChessBoard.checkCollision(this.x, this.y, -this.x, 0)){
			if(!hasMoved && !piece.hasMoved && (deltaY == 0 && (deltaX == -2 && piece instanceof Rook))){
				piece.setX(this.x-1);
				return true;
			}
		}
		
		//Check that there was no unwanted collisions.
		boolean collision = !ChessBoard.checkCollision(this.x, this.y, deltaX, deltaY);
		
		return moveValid && collision;
	}

}
