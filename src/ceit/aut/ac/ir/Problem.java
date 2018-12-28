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


    public void setInitialization() {
        graph.nodes.get(0).setColor(1);
        graph.nodes.get(1).setColor(2);
        graph.nodes.get(2).setColor(3);
        graph.nodes.get(3).setColor(1);
        graph.nodes.get(4).setColor(2);
        graph.nodes.get(5).setColor(3);
        graph.nodes.get(6).setColor(1);
        graph.nodes.get(7).setColor(2);
        graph.nodes.get(8).setColor(3);
        graph.nodes.get(9).setColor(1);
        graph.nodes.get(10).setColor(2);


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

//        Random random = new Random();
//        for (int i = 0; i < node.getAdjacencies().size(); i++) {
//            if (!checkColor(node, node.getAdjacencies().get(i))) {
//                return;
//            }
//        }

//        ArrayList<Integer> colors = new ArrayList<Integer>();
////        colors.add(1);
////        colors.add(2);
////        colors.add(3);
////        for (int i = 0; i < node.getAdjacencies().size(); i++) {
////            colors.remove(node.getAdjacencies().get(i).getColor());
////        }
////        if (colors.size() == 0) {
////            node.setColor(random.nextInt(3));
////            cost++;
////        } else {
////            node.setColor(colors.get(0));
////            cost++;
////        }


    }


    public boolean goalTest() {
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.get(i).getAdjacencies().size(); j++) {
                if (!checkColor(graph.nodes.get(i), graph.nodes.get(i).getAdjacencies().get(j))) {
                    return false;
                }
            }
        }
        return true;
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

    public void setCostFunction(int cost) {
        this.cost = cost;
    }

    public int getCostFunction() {
        return cost;
    }

    public int heuristicFunction() {

        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.get(i).getAdjacencies().size(); j++) {
                if (!checkColor(graph.nodes.get(i), graph.nodes.get(i).getAdjacencies().get(j))) {
                    cost++;
                }
            }

        }
        return cost;
    }
}
