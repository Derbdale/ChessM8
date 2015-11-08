package com.dgc.chesstest;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dgc.chesstest.utils.Renderer;
import com.dgc.chesstest.utils.ChessBoard;
import com.dgc.chesstest.utils.SaveSystem;

public class Main extends Canvas implements MouseListener, KeyListener {
	private static final long serialVersionUID = -3779016510155936155L;
	//Store a static instance of Screen.
	public static Screen screen;
	//Store a static instance of Chess Game.
	public static ChessGame chess;

	
	public static void main(String[] args) {
		//Create a new chess game.
		chess = new ChessGame();
		
		//Create a new instance of the Main class.
		new Main();
	}
	
	public Main(){
		JFrame frame = new JFrame("Chess");
		JPanel panel = (JPanel)frame.getContentPane();
		
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setLayout(null);
		
		setBounds(0,0,400,400);
		panel.add(this);
		setIgnoreRepaint(true);	
		
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addMouseListener(this);
		this.addKeyListener(this);
		
		Thread t = new Renderer(this);
		t.start();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//Forward click event to chess game.
		chess.click(e.getX(), e.getY());
		SaveSystem.saveGame("autosave");
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 83){
			SaveSystem.saveGame();
		}
		if(e.getKeyCode() == 76){
			SaveSystem.loadGame();
		}
		if(e.getKeyCode() == 82){
			ChessBoard.reset();
			chess.turn = false;
			chess.winner = "";
			chess.promoting = false;
			chess.selection = new int[] {-1, -1};
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
