package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StochasticHillClimbing extends Problem {


    public StochasticHillClimbing(Graph graph) {
        super(graph);
        super.setInitialization();
        checkSuccessors(graph);
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

                int tempCost = computeCost(graph);
                if (currentCost < tempCost) {
                    costs.add(tempCost);
                    saveNode = new Node(graph.nodes.get(i).getName(), graph.nodes.get(i).getColor());
                    changedNodes.add(saveNode);
                }

            }
            graph.nodes.get(i).setColor(temp);

        }


        if (costs.size() == 0) {
            for (int i = 0; i < graph.nodes.size(); i++) {
                System.out.println(graph.nodes.get(i).getName() + ": " + graph.nodes.get(i).getColor());
            }
            System.out.println("Cost Funtion: " + computeCost(graph));
            return;
        } else {
            //System.out.println(costs.size()+" " +changedNodes.size());
            Random random = new Random();
            Node result;
            int upHillLocation = random.nextInt(costs.size() - 1);
            // System.out.println(upHillLocation);
            result = changedNodes.get(upHillLocation);
            for (int i = 0; i < graph.nodes.size(); i++) {
                if (graph.nodes.get(i).getName().equals(result.getName())) {
                    graph.nodes.get(i).setColor(result.getColor());
                }
            }
            checkSuccessors(graph);

        }
    }


}