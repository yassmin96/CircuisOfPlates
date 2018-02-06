package Parsers;

import game.player.Player;
import game.shapes.Shape;

import java.io.Serializable;
import java.util.ArrayList;

public class Memento implements Serializable {

    private ArrayList<Player> players;
    private ArrayList<Shape> shapes;
    private String level;

    public Memento(ArrayList<Player> players, ArrayList<Shape> shapes, String level) {
        this.players = players;
        this.shapes = shapes;
        this.level = level;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public String getLevel() {
        return level;
    }
}
