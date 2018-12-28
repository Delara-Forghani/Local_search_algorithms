package ceit.aut.ac.ir;

import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> adjacents;
    private int color;

    public Node(String name) {
        this.name = name;
        adjacents = new ArrayList<>();
        this.setColor(0);

    }

    public Node(String name, int color) {
        this.name = name;
        this.setColor(color);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void addAdjacentNode(Node node) {
        adjacents.add(node);
    }




    public String getName() {
        return name;
    }

    public ArrayList<Node> getAdjacencies() {
        return adjacents;
    }


    public int getColor() {
        return color;
    }


}
