package com.dgc.chesstest;

import com.dgc.chesstest.pieces.*;
import com.dgc.chesstest.utils.ChessBoard;

public class ChessGame {
	//Keep track of whose turn it is.
	public boolean turn = false;
	//Check if a piece is currently being promoted.
	public boolean promoting = false;
	
	//Store the winners name, or blank string if no winner.
	public String winner = "";
	
	//Store the current selected tile.
	public int[] selection = {-1, -1};
	
	public ChessGame(){
		ChessBoard.reset();
	}
	
	//Handle clicking on the chess board.
	public void click(int x, int y){
		//Convert the click coordinates from window to array.
		int cellX = x / 50;
		int cellY = y / 50;
		
		//Check if there is a winner.
		if(!this.winner.equals("")) return;
		
		//Check if a piece is being promoted.
		if(this.promoting){
			//Check if the click is in the bounds of queen.
			if(x >= 143 && x <= 193 && y >= 165 && y <= 215){
				String team = ChessBoard.findPiece(this.selection[0], this.selection[1]).getTeam();
				ChessBoard.chessPieces.remove(ChessBoard.findPiece(this.selection[0], this.selection[1]));
				ChessBoard.chessPieces.add(new Queen(team, this.selection[0], this.selection[1]));
				this.promoting = false;
			}
			//Check if the click is in the bounds of queen.
			if(x >= 213 && x <= 263 && y >= 165 && y <= 215){
				String team = ChessBoard.findPiece(this.selection[0], this.selection[1]).getTeam();
				ChessBoard.chessPieces.remove(ChessBoard.findPiece(this.selection[0], this.selection[1]));
				ChessBoard.chessPieces.add(new Bishop(team, this.selection[0], this.selection[1]));
				this.promoting = false;
			}
			//Check if the click is in the bounds of queen.
			if(x >= 143 && x <= 193 && y >= 235 && y <= 285){
				String team = ChessBoard.findPiece(this.selection[0], this.selection[1]).getTeam();
				ChessBoard.chessPieces.remove(ChessBoard.findPiece(this.selection[0], this.selection[1]));
				ChessBoard.chessPieces.add(new Knight(team, this.selection[0], this.selection[1]));
				this.promoting = false;
			}
			//Check if the click is in the bounds of queen.
			if(x >= 213 && x <= 263 && y >= 235 && y <= 285){
				String team = ChessBoard.findPiece(this.selection[0], this.selection[1]).getTeam();
				ChessBoard.chessPieces.remove(ChessBoard.findPiece(this.selection[0], this.selection[1]));
				ChessBoard.chessPieces.add(new Rook(team, this.selection[0], this.selection[1]));
				this.promoting = false;
			}
			//Change turns and reset selected tile if the piece was promoted.
			if(this.promoting == false){
				//Change turn.
				this.turn = !this.turn;
				//Reset selected tile.
				this.selection[0] = -1;
				this.selection[1] = -1;
			}
			return;
		}
		
		//Check if a tile is currently selected
		if(this.selection[0] == -1 && this.selection[1] == -1 && ChessBoard.findPiece(cellX, cellY) != null){
			//Check if the selected piece was for the correct player.
			if(this.turn && ChessBoard.findPiece(cellX, cellY).getTeam().equals("black")){
				//Set selected tile to this tile.
				this.selection[0] = cellX;
				this.selection[1] = cellY;
			}else if(!this.turn && ChessBoard.findPiece(cellX, cellY).getTeam().equals("white")){
				//Set selected tile to this tile.
				this.selection[0] = cellX;
				this.selection[1] = cellY;
			}
		}else{
			ChessPiece piece = ChessBoard.findPiece(selection[0], selection[1]);
			if(piece != null){
				//Store whether a move was valid.
				boolean validMove = piece.validMove(cellX - piece.getX(), cellY - piece.getY());
				//Check valid moves for each piece.
				
				//Check if move was valid.
				if(validMove){
					//Check if a piece is in the clicked cell.
					if(ChessBoard.findPiece(cellX, cellY) != null){
						//Check if the piece clicked can be captured.
						if(ChessBoard.findPiece(cellX, cellY).getTeam().equals("white") && !this.turn){
							validMove = false;
						}
						//Check if the piece clicked can be captured.
						if(ChessBoard.findPiece(cellX, cellY).getTeam().equals("black") && this.turn){
							validMove = false;
						}
						//Check if the piece captured is a king, and set a winner.
						if(validMove && ChessBoard.findPiece(cellX, cellY) instanceof King){
							this.winner = ChessBoard.findPiece(cellX, cellY).getTeam().equals("white") ? "Black" : "White";
						}
					}
					
					//Check if move is still valid.
					if(validMove){
						//Set the clicked cells value to the piece being moved.
						
						
						if(ChessBoard.findPiece(cellX, cellY) != null){
							ChessBoard.chessPieces.remove(ChessBoard.findPiece(cellX, cellY));
						}
						
						piece.setX(cellX);
						piece.setY(cellY);
						
						if(piece instanceof Pawn){
							if(piece.getTeam().equals("white") && piece.getY() == 0 || piece.getTeam().equals("black") && piece.getY() == 7){
								//Set promoting to true.
								this.promoting = true;
								//Set selection to this tile.
								this.selection[0] = cellX;
								this.selection[1] = cellY;
								return;
							}
						}
						//Change turn.
						this.turn = !this.turn;
					}
				}
				//Remove selection if selected tile clicked, or valid move played out.
				if(cellX == this.selection[0] && cellY == this.selection[1] || validMove) this.selection = new int[] {-1, -1};
			}
		}
	}
	
}
