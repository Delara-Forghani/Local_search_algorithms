package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Random;

public class Problem {
    public Node initialState;
    public Graph graph;
    public int cost;


    public Problem(Graph graph) {
        this.graph = graph;
        initialState = graph.getNode("A");
        cost = 0;
    }


    public void setRoot(Node node) {
        initialState = node;
    }

    public void setInitialization() {
        Random random = new Random();
        for (int i = 0; i < graph.nodes.size(); i++) {
            graph.nodes.get(i).setColor(random.nextInt(3) + 1);
        }
//        graph.nodes.get(0).setColor(1);
//        graph.nodes.get(1).setColor(2);
//        graph.nodes.get(2).setColor(3);
//        graph.nodes.get(3).setColor(1);
//        graph.nodes.get(4).setColor(2);
//        graph.nodes.get(5).setColor(3);
//        graph.nodes.get(6).setColor(1);
//        graph.nodes.get(7).setColor(2);
//        graph.nodes.get(8).setColor(3);
//        graph.nodes.get(9).setColor(1);
//        graph.nodes.get(10).setColor(2);


    }

    public boolean checkColor(Node node, Node adjacent) {

        if (node.getColor() == adjacent.getColor()) {
            return false;
        }

        return true;
    }

    public void successorFunction(Graph graph) {

        for (int i = 0; i < graph.nodes.size(); i++) {
            ArrayList<Integer> tempColor = new ArrayList<>();
            tempColor.remove(graph.nodes.get(i).getColor());
            for (int j = 0; j < tempColor.size(); j++) {
                graph.nodes.get(i).setColor(tempColor.get(j));

            }

        }

    }


    public int computeCost(Graph graph) {
        int count = 0;
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.get(i).getAdjacencies().size(); j++) {
                if (checkColor(graph.nodes.get(i), graph.nodes.get(i).getAdjacencies().get(j))) {
                    count++;
                }
            }

        }
        return count / 2;
    }


}
