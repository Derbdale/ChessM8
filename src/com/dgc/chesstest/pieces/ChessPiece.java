package com.dgc.chesstest.pieces;

import java.awt.Graphics;
import java.io.Serializable;

public class ChessPiece implements Serializable{
	private static final long serialVersionUID = -4873844429263374043L;
	//Store current x position.
	protected int x;
	//Store current y position.
	protected int y;
	//Character to be displayed for the piece.
	private String displayCharacter;	
	
	//Stores if piece is still in starting position.
	protected boolean hasMoved = false;
	
	//Store the colour of the piece.
	protected String team;
	
	//Constructor for chess piece.
	public ChessPiece(String team, String displayCharacter, int x, int y){
		this.x = x;
		this.y = y;
		this.displayCharacter = displayCharacter;
		this.team = team.toLowerCase();
	}
	
	//Draw graphics to the screen.
	public void render(Graphics g){
		g.drawString(displayCharacter, x*50, y*50 + 43);
	}
	
	//Check if a move is valid.
	public boolean validMove(int deltaX, int deltaY){
		return false;
	}
	
	//Getter for x.
	public int getX() {
		return x;
	}

	//Setter for x.
	public void setX(int x) {
		hasMoved = false;
		this.x = x;
	}

	//Getter for y.
	public int getY() {
		return y;
	}

	//Setter for y.
	public void setY(int y) {
		hasMoved = false;
		this.y = y;
	}

	//Getter for team.
	public String getTeam() {
		return team;
	}

	public boolean getHasMoved() {
		return hasMoved;
	}
}