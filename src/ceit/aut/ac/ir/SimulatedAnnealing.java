package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulatedAnnealing extends Problem {
    private double temperature;
    private double coolingRate;
    boolean finish;

    public SimulatedAnnealing(Graph graph) {
        super(graph);
        super.setInitialization();
        temperature = 1000;
        coolingRate = 0.009;
        finish = false;
        checkSuccessors(graph);

    }

    private void checkSuccessors(Graph graph) {
        Collections.shuffle(graph.nodes);
        int currentCost = computeCost(graph);
        ArrayList<Integer> costs = new ArrayList<>();
        ArrayList<Node> changedNodes = new ArrayList<>();
        Node saveNode;
        Random random = new Random();
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

                int newCost = computeCost(graph);
                costs.add(newCost);
                saveNode = new Node(graph.nodes.get(i).getName(), graph.nodes.get(i).getColor());
                changedNodes.add(saveNode);

            }

            graph.nodes.get(i).setColor(temp);
        }

        
        while (temperature > 1) {
            int rand = random.nextInt(costs.size() - 1);
            int newCost = costs.get(rand);
            for (int i = 0; i < graph.nodes.size(); i++) {
                if (graph.nodes.get(i).getName().equals(changedNodes.get(rand).getName())) {
                    graph.nodes.get(i).setColor(changedNodes.get(rand).getColor());
                }
            }
            double acceptanceProb = acceptanceProbability(currentCost, newCost, temperature);
            temperature *= 1 - coolingRate;
            if (acceptanceProb > Math.random()) {
                checkSuccessors(graph);
            }

        }
        if (finish) {
            return;
        } else {
            for (int i = 0; i < graph.nodes.size(); i++) {
                System.out.println(graph.nodes.get(i).getName() + ": " + graph.nodes.get(i).getColor());
            }
            System.out.println("Cost Funtion: " + computeCost(graph));
            finish = true;
            return;

        }
    }


    public static double acceptanceProbability(int currentCost, int newCost, double temperature) {
        // If the new solution is better, accept it
        if (currentCost < newCost) {
            return 1.0;
        }
        // System.out.println(Math.exp((newCost - currentCost) / temperature));
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((newCost - currentCost) / temperature);
    }

}
