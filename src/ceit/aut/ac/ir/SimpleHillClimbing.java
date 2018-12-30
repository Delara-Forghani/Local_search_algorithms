package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimpleHillClimbing extends Problem {
    private int frontiersNum;
    private int exploredNum;

    public SimpleHillClimbing(Graph graph) {
        super(graph);
        super.setInitialization();
        frontiersNum = 0;
        exploredNum = 0;
        checkSuccessors(graph);
        System.out.println("Frontiers: " + frontiersNum);
        System.out.println("Explored: " + exploredNum);

    }


    private void checkSuccessors(Graph graph) {

        int currentCost = computeCost(graph);
        ArrayList<Integer> costs = new ArrayList<>();
        ArrayList<Node> changedNodes = new ArrayList<>();
        Node saveNode;
        for (int i = 0; i < graph.nodes.size(); i++) {

            ArrayList<Integer> tempColor = new ArrayList<>();
            tempColor.add(1);
            tempColor.add(2);
            tempColor.add(3);
            int temp = graph.nodes.get(i).getColor();
            for (int j = 0; j < tempColor.size(); j++) {
                if (tempColor.get(j).intValue() == temp) {
                    tempColor.remove(tempColor.get(j));
                }
            }

            for (int j = 0; j < tempColor.size(); j++) {
                graph.nodes.get(i).setColor(tempColor.get(j).intValue());


                costs.add(computeCost(graph));
                saveNode = new Node(graph.nodes.get(i).getName(), graph.nodes.get(i).getColor());
                changedNodes.add(saveNode);
                frontiersNum++;
            }
            graph.nodes.get(i).setColor(temp);
            exploredNum++;
        }


        int maxNeighbor = Collections.max(costs);
        if (currentCost >= maxNeighbor) {
            for (int i = 0; i < graph.nodes.size(); i++) {
                System.out.println(graph.nodes.get(i).getName() + ": " + graph.nodes.get(i).getColor());
            }
            System.out.println("Cost Funtion: " + computeCost(graph));
            return;
        } else {
            Node result = null;
            for (int i = 0; i < costs.size(); i++) {
                if (costs.get(i).intValue() == maxNeighbor) {
                    result = changedNodes.get(i);
                    break;
                }
            }
            for (int i = 0; i < graph.nodes.size(); i++) {
                if (graph.nodes.get(i).getName().equals(result.getName())) {
                    graph.nodes.get(i).setColor(result.getColor());
                }
            }
            checkSuccessors(graph);
        }
    }

}
