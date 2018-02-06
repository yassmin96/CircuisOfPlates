package Parsers;

import game.player.Player;
import game.shapes.Shape;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Stream {

    public void save(ArrayList<Player> players, ArrayList<Shape> shapes,
                     String level, String game) {
        try {
            FileOutputStream fileOut = new FileOutputStream("resources" + File.separator + "savedGames" + File.separator + game + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            Memento savedGame = new Memento(players, shapes, level);
            out.writeObject(savedGame);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in Saving");
        }
    }

    public Memento load(String game) {
        try {
            FileInputStream fileIn = new FileInputStream("resources" + File.separator + "savedGames" + File.separator + game + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Memento loadedGame = (Memento) in.readObject();
            in.close();
            fileIn.close();
            return loadedGame;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in Loading");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found");
        }
    }
}
