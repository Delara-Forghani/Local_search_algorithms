package ceit.aut.ac.ir;

import java.util.ArrayList;

public class Graph {
    ArrayList<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void setGraph(Graph graph) {
        for (int i = 0; i < graph.nodes.size(); i++) {
            Node node = new Node(graph.nodes.get(i).getName(), graph.nodes.get(i).getColor());
//            for (int j = 0; j < graph.nodes.get(i).getAdjacencies().size(); j++) {
//                node.addAdjacentNode(new Node(graph.nodes.get(i).getAdjacencies().));
//            }
            nodes.add(node);
        }
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
}
