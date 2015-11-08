package com.dgc.chesstest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.dgc.chesstest.utils.ChessBoard;

public class Screen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//Draw graphics to the screen.
	@Override
	public void paint(Graphics g) {
		//Clear the screen.
		g.clearRect(0, 0, 640, 480);
		
		//Render the chess board.
		ChessBoard.render(g);
		
		//Check if the game has been won.
		if(!Main.chess.winner.equals("")){
			//Set the colour to red.
			g.setColor(Color.RED);
			//Draw the winners name to the screen.
			g.drawString(Main.chess.winner + " wins!", 200 - (int)g.getFontMetrics().getStringBounds(Main.chess.winner + " wins!", g).getCenterX(), 200  - (int)g.getFontMetrics().getStringBounds(Main.chess.winner + " wins!", g).getCenterY());
		}
		
		//Check if a pawn is being promoted.
		if(Main.chess.promoting){
			//Set the colour to dark gray for the selection box.
			g.setColor(Color.DARK_GRAY);
			//Draw the background of the promotion selection box.
			g.fillRect(200 - 75, 200 - 95, 150, 170);
			
			//Set the colour to white.
			g.setColor(Color.WHITE);
			//Set the font to a smaller size.
			g.setFont(new Font("Helvetica", 0, 18));
			//Write promote a pawn to the top of the selection box.
			g.drawString("Promote A Pawn", 200 - (int)g.getFontMetrics().getStringBounds("Promote A Pawn", g).getCenterX(), 200 - 75);
			
			//Set the colour to gray.
			g.setColor(Color.GRAY);
			//Draw each of the backgrounds for the promotion selections.
			g.fillRect(140, 140, 50, 50);
			g.fillRect(140, 210, 50, 50);
			g.fillRect(210, 140, 50, 50);
			g.fillRect(210, 210, 50, 50);
			
			//Set the colour to white.
			g.setColor(Color.WHITE);
			//Set the font to a larger size.
			g.setFont(new Font("Helvetica", 0, 50));
			//Draw the chess piece selections to the screen.
			g.drawString("♕", 140, 183);
			g.drawString("♗", 210, 183);
			g.drawString("♘", 140, 253);
			g.drawString("♖", 210, 253);
		}
	}
	
}
