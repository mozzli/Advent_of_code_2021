package calendar;

import java.util.ArrayList;

public class Cave {

    String name;
    boolean big;
    ArrayList<String> neighbours = new ArrayList<>();

    public Cave(String name, boolean big) {
        this.name = name;
        this.big = big;
    }

    public void addNeighbour(String neighbour) {
        neighbours.add(neighbour);
    }

    public boolean checkIfDeadEndWithDoubleCaves(ArrayList<String> coordinates, boolean wasInCave) {
        return !big && coordinates.contains(name) && wasInCave;
    }

    public boolean checkIfDeadEnd(ArrayList<String> coordinates) {
        return !big && coordinates.contains(name);
    }

    public ArrayList<String> getNeighbours() {
        return neighbours;
    }

    public boolean wasInCave(ArrayList<String> coordinates) {
        return !big && coordinates.contains(name);
    }
}
