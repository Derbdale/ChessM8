package com.dgc.chesstest.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.dgc.chesstest.Main;

public class Renderer extends Thread {
	
	private BufferedImage image;
	private BufferStrategy strategy;
	Graphics2D g;
	Graphics buffer;
	
	public Renderer(Main m){
		image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		
		m.createBufferStrategy(2);
		strategy = m.getBufferStrategy();
		g = (Graphics2D) strategy.getDrawGraphics();
		buffer = image.getGraphics();
		g.setFont(new Font("Helvetica", 50, 50));
	}
	
	@Override
	public void run(){
		while(true){
			g = (Graphics2D) strategy.getDrawGraphics();
			image.flush();
			image.getGraphics().clearRect(0, 0, 640, 480);
			//Clear the screen.
			buffer.clearRect(0, 0, 640, 480);
			
			//Render the chess board.
			ChessBoard.render(buffer);
			
			//Check if the game has been won.
			if(!Main.chess.winner.equals("")){
				//Set the colour to red.
				buffer.setColor(Color.RED);
				//Draw the winners name to the screen.
				buffer.drawString(Main.chess.winner + " wins!", 200 - (int)buffer.getFontMetrics().getStringBounds(Main.chess.winner + " wins!", g).getCenterX(), 200  - (int)buffer.getFontMetrics().getStringBounds(Main.chess.winner + " wins!", g).getCenterY());
			}
			
			//Check if a pawn is being promoted.
			if(Main.chess.promoting){
				//Set the colour to dark gray for the selection box.
				buffer.setColor(Color.DARK_GRAY);
				//Draw the background of the promotion selection box.
				buffer.fillRect(200 - 75, 200 - 95, 150, 170);
				
				//Set the colour to white.
				buffer.setColor(Color.WHITE);
				//Set the font to a smaller size.
				buffer.setFont(new Font("Helvetica", 0, 18));
				//Write promote a pawn to the top of the selection box.
				buffer.drawString("Promote A Pawn", 200 - (int)buffer.getFontMetrics().getStringBounds("Promote A Pawn", g).getCenterX(), 200 - 75);
				
				//Set the colour to gray.
				buffer.setColor(Color.GRAY);
				//Draw each of the backgrounds for the promotion selections.
				buffer.fillRect(140, 140, 50, 50);
				buffer.fillRect(140, 210, 50, 50);
				buffer.fillRect(210, 140, 50, 50);
				buffer.fillRect(210, 210, 50, 50);
				
				//Set the colour to white.
				buffer.setColor(Color.WHITE);
				//Set the font to a larger size.
				buffer.setFont(new Font("Helvetica", 0, 50));
				//Draw the chess piece selections to the screen.
				buffer.drawString("♕", 140, 183);
				buffer.drawString("♗", 210, 183);
				buffer.drawString("♘", 140, 253);
				buffer.drawString("♖", 210, 253);
			}
			g.drawImage(image, null, null);
			g.dispose();
			strategy.show();
		}
	}
	
}
