package com.dgc.chesstest.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.dgc.chesstest.Main;
import com.dgc.chesstest.pieces.Bishop;
import com.dgc.chesstest.pieces.ChessPiece;
import com.dgc.chesstest.pieces.King;
import com.dgc.chesstest.pieces.Knight;
import com.dgc.chesstest.pieces.Pawn;
import com.dgc.chesstest.pieces.Queen;
import com.dgc.chesstest.pieces.Rook;

public class ChessBoard {
	
	public static ArrayList<ChessPiece> chessPieces = new ArrayList<>();

	public static void reset() {
		//Clear the board.
		chessPieces.clear();
		//Add the pieces in default positions.
		chessPieces.add(new Rook("Black", 0, 0));
		chessPieces.add(new Knight("Black", 1, 0));
		chessPieces.add(new Bishop("Black", 2, 0));
		chessPieces.add(new Queen("Black", 3, 0));
		chessPieces.add(new King("Black", 4, 0));
		chessPieces.add(new Bishop("Black", 5, 0));
		chessPieces.add(new Knight("Black", 6, 0));
		chessPieces.add(new Rook("Black", 7, 0));
		
		chessPieces.add(new Pawn("Black", 0, 1));
		chessPieces.add(new Pawn("Black", 1, 1));
		chessPieces.add(new Pawn("Black", 2, 1));
		chessPieces.add(new Pawn("Black", 3, 1));
		chessPieces.add(new Pawn("Black", 4, 1));
		chessPieces.add(new Pawn("Black", 5, 1));
		chessPieces.add(new Pawn("Black", 6, 1));
		chessPieces.add(new Pawn("Black", 7, 1));
		
		chessPieces.add(new Rook("White", 0, 7));
		chessPieces.add(new Knight("White", 1, 7));
		chessPieces.add(new Bishop("White", 2, 7));
		chessPieces.add(new Queen("White", 3, 7));
		chessPieces.add(new King("White", 4, 7));
		chessPieces.add(new Bishop("White", 5, 7));
		chessPieces.add(new Knight("White", 6, 7));
		chessPieces.add(new Rook("White", 7, 7));
		
		chessPieces.add(new Pawn("White", 0, 6));
		chessPieces.add(new Pawn("White", 1, 6));
		chessPieces.add(new Pawn("White", 2, 6));
		chessPieces.add(new Pawn("White", 3, 6));
		chessPieces.add(new Pawn("White", 4, 6));
		chessPieces.add(new Pawn("White", 5, 6));
		chessPieces.add(new Pawn("White", 6, 6));
		chessPieces.add(new Pawn("White", 7, 6));
	}
	
	//Find a piece from coordinate.
	public static ChessPiece findPiece(int x, int y){
		//Loop through each piece.
		for(ChessPiece piece : chessPieces){
			//Check if it is at the specified coordinates.
			if(piece.getX() == x && piece.getY() == y){
				return piece;
			}
		}
		return null;
	}
	
	public static boolean checkCollision(int startX, int startY, int deltaX, int deltaY){
		//Store the direction of movements.
		int ySign = deltaY == 0 ? 1 : deltaY / Math.abs(deltaY);
		int xSign = deltaX == 0 ? 1 : deltaX / Math.abs(deltaX);
		
		//Check if horizontal movement occurred.
		if(deltaX == 0){
			//Loop through the vertical movements.
			for(int i = 1; i < Math.abs(deltaY); ++i){
				//If a piece was found then return true as a collision has happened.
				if(findPiece(startX, startY + i*ySign) != null) return true;
			}
		}
		//Check if vertical movement occurred.
		else if(deltaY == 0){
			//Loop through the horizontal movements.
			for(int i = 1; i < Math.abs(deltaX); ++i){
				//If a piece was found then return true as a collision has happened.
				if(findPiece(startX + i * xSign, startY) != null) return true;
			}
		}else{
			//Loop through the diagonal movements.
			for(int i = 1; i < Math.abs(deltaY); ++i){
				//If a piece was found then return true as a collision has happened.
				if(findPiece(startX + i * xSign, startY + i * ySign) != null) return true;
			}
		}
		//Return false if no collision detected.
		return false;
	}
	
	public static void render(Graphics g){
		//Set the font size to 50, in font Helvetica.
		g.setFont(new Font("Helvetica", 0, 50));
		//Loop through rows and columns of chess board.
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				//If x is even and y is odd, or x is odd and y is even then colour the tile in darker, else colour white.
				if(x % 2 != 0 && y % 2 != 0 || x % 2 == 0 && y % 2 == 0) g.setColor(Color.decode("0xaaaaaa")); else g.setColor(Color.white);
				//Create a tile covering 1/8th of the window.
				g.fillRect((x)*50, (y)*50, 50, 50);
			}
		}
		
		//Set the colour to black for the chess pieces.
		g.setColor(Color.BLACK);
		
		//Loop through each chess piece and render it.
		@SuppressWarnings("unchecked")
		ArrayList<ChessPiece> currentPieces = (ArrayList<ChessPiece>) chessPieces.clone();
		for(ChessPiece piece : currentPieces){
			//Render the piece.
			piece.render(g);
		}
		
		//Check if there is a selection.
		if(Main.chess.selection[0] != -1 && Main.chess.selection[1] != -1){
			//Set the colour to red for selection border.
			g.setColor(Color.RED);
			//Draw a border around the selected tile.
			g.drawRect(Main.chess.selection[0]*50, Main.chess.selection[1]*50, 50, 50);
		}
	}

}
