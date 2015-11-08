package com.dgc.chesstest.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.dgc.chesstest.Main;
import com.dgc.chesstest.pieces.ChessPiece;

public class SaveSystem {
	
	public static void saveGame(String saveName){
		File file = new File("C:/Labs/ChessTest/src/" + saveName + ".txt");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println("File could not be created.");
			}
		ArrayList<Object> gameData = new ArrayList<>();
		
		gameData.add(ChessBoard.chessPieces);
		gameData.add(Main.chess.winner);
		gameData.add(Main.chess.turn);
		gameData.add(Main.chess.promoting);
		
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			objectOutputStream.writeObject(gameData);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("File could not be written to.");
		}
	}

	public static void saveGame(){
		saveGame("save");
	}
	
	
	public static void loadGame(){
		loadGame("save");
	}
	
	@SuppressWarnings("unchecked")
	public static void loadGame(String saveName){
		File file = new File("C:/Labs/ChessTest/src/" + saveName + ".txt");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println("File could not be created.");
			}
		
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			Object o = objectInputStream.readObject();
			objectInputStream.close();
			if(o instanceof ArrayList){
				ArrayList<Object> gameData = ((ArrayList<Object>) o);
				ChessBoard.chessPieces = (ArrayList<ChessPiece>) gameData.get(0);
				Main.chess.winner = (String) gameData.get(1);
				Main.chess.turn = (boolean) gameData.get(2);
				Main.chess.promoting = (boolean) gameData.get(3);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("File could not be read.");
		} catch (ClassNotFoundException e) {
			System.out.println("Class could not be found.");
		}
	}

}
