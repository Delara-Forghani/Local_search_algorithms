package ceit.aut.ac.ir;

import java.util.ArrayList;

public class Graph {
    ArrayList<Node> nodes;
    private int fitness;

    public Graph() {
        nodes = new ArrayList<>();
        fitness = 0;
    }


    public static Graph copyGraph(Graph graph) {
        Graph newGraph = new Graph();
        for (int i = 0; i < graph.nodes.size(); i++) {
            String newName = graph.nodes.get(i).getName();
            Node node = new Node(newName);
            newGraph.addNode(node);
        }
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.get(i).getAdjacencies().size(); j++) {
                for (int k = 0; k < newGraph.nodes.size(); k++) {
                    if (newGraph.nodes.get(k).getName().equals(graph.nodes.get(i).getAdjacencies().get(j).getName())) {
                        newGraph.nodes.get(i).addAdjacentNode(newGraph.nodes.get(k));
                    }
                }
            }
        }

//        for (int i = 0; i < graph.nodes.size(); i++) {
//            System.out.println(graph.nodes.get(i) + " , " + newGraph.nodes.get(i));
//        }
        return newGraph;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public Node getNode(String name) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getName().equals(name)) {
                return nodes.get(i);
            }
        }
        return null;
    }

    void setFitness(int fitness) {
        this.fitness = fitness;
    }

    int getFitness() {
        return fitness;
    }
}
