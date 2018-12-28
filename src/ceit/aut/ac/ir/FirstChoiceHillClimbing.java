package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Random;

class FirstChoiceHillClimbing extends Problem {

    FirstChoiceHillClimbing(Graph graph) {
        super(graph);
        super.setInitialization();
        checkSuccessors(graph);

    }

    private void checkSuccessors(Graph graph) {

        int currentCost = computeCost(graph);
        boolean foundUpHill = false;
        for (int i = 0; i < graph.nodes.size(); i++) {
            foundUpHill = false;
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
                    foundUpHill = true;
                    break;
                }

            }
            if(foundUpHill){
                break;
            }


            graph.nodes.get(i).setColor(temp);

        }

        if (foundUpHill) {
//            for (int i = 0; i < graph.nodes.size(); i++) {
//                if (graph.nodes.get(i).getName().equals(changedNode.getName())) {
//                    graph.nodes.get(i).setColor(changedNode.getColor());
//                }
//            }
            checkSuccessors(graph);
        } else {
            for (int i = 0; i < graph.nodes.size(); i++) {
                System.out.println(graph.nodes.get(i).getName() + ": " + graph.nodes.get(i).getColor());
            }
            System.out.println("Cost Funtion: " + computeCost(graph));
            return;
        }
    }


}
