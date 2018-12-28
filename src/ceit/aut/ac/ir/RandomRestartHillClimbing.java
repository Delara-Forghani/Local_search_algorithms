package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomRestartHillClimbing extends Problem {


    public RandomRestartHillClimbing(Graph graph) {
        super(graph);
        super.setInitialization();
        Random random = new Random();
        int iteration = 10;
        for (int i = 0; i < iteration; i++) {
            if (i == iteration - 1) {
                Collections.shuffle(graph.nodes);
                //  int rand = random.nextInt(graph.nodes.size() - 1);
                super.setRoot(graph.nodes.get(0));
                Node root = super.initialState;
                checkSuccessors(graph, root, true);
            }
            //int rand = random.nextInt(graph.nodes.size() - 1);
            Collections.shuffle(graph.nodes);
            super.setRoot(graph.nodes.get(0));
            Node root = super.initialState;
            checkSuccessors(graph, root, false);

        }
    }


    private void checkSuccessors(Graph graph, Node root, boolean lastIteration) {

        int currentCost = computeCost(graph);
        ArrayList<Integer> costs = new ArrayList<>();
        ArrayList<Node> changedNodes = new ArrayList<>();
        Node saveNode;

        ArrayList<Integer> changeRootColor = new ArrayList<>();
        changeRootColor.add(1);
        changeRootColor.add(2);
        changeRootColor.add(3);
        int rColor = graph.nodes.get(0).getColor();
        for (int i = 0; i < changeRootColor.size(); i++) {
            if (changeRootColor.get(i).intValue() == rColor) {
                changeRootColor.remove(changeRootColor.get(i));
            }
        }

        for (int i = 0; i < changeRootColor.size(); i++) {
            graph.nodes.get(0).setColor(changeRootColor.get(i));
            costs.add(computeCost(graph));
            saveNode = new Node(root.getName(), root.getColor());
            changedNodes.add(saveNode);
            graph.nodes.get(0).setColor(rColor);
        }

        for (int i = 1; i < graph.nodes.size(); i++) {
            ArrayList<Integer> tempColor = new ArrayList<>();
            tempColor.add(1);
            tempColor.add(2);
            tempColor.add(3);
            int temp = graph.nodes.get(i).getColor();
            for (int j = 0; j < tempColor.size(); j++) {
                if (tempColor.get(j) == temp) {
                    tempColor.remove(tempColor.get(j));
                }
            }

            for (int j = 0; j < tempColor.size(); j++) {
                graph.nodes.get(i).setColor(tempColor.get(j));


                costs.add(computeCost(graph));
                saveNode = new Node(graph.nodes.get(i).getName(), graph.nodes.get(i).getColor());
                changedNodes.add(saveNode);

            }
            graph.nodes.get(i).setColor(temp);

        }


        int maxNeighbor = Collections.max(costs);
        if (currentCost >= maxNeighbor) {
            if (lastIteration) {
                for (int i = 0; i < graph.nodes.size(); i++) {
                    System.out.println(graph.nodes.get(i).getName() + ": " + graph.nodes.get(i).getColor());
                }
                System.out.println("Cost Funtion: " + computeCost(graph));
                return;
            }
        } else {
            Node result = null;
            for (int i = 0; i < costs.size(); i++) {
                if (costs.get(i) == maxNeighbor) {
                    result = changedNodes.get(i);
                    break;
                }
            }
            for (int i = 0; i < graph.nodes.size(); i++) {
                if (graph.nodes.get(i).getName().equals(result.getName())) {
                    graph.nodes.get(i).setColor(result.getColor());
                }
            }
            checkSuccessors(graph, root, false);
        }
    }
}
