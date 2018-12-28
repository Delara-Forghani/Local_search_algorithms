package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Random;

public class Genetics extends Problem {
    ArrayList<Graph> population;
    ArrayList<Integer> fitness;
    ArrayList<Graph> selected;
    ArrayList<Graph> newGeneration;
    Random random = new Random();

    public Genetics(Graph graph) {
        super(graph);
        super.setInitialization();
        population = new ArrayList<>();
        fitness = new ArrayList<>();
        selected = new ArrayList<>();
        newGeneration = new ArrayList<>();
        population.add(graph);
        searchGenetics(graph);


    }


    public void searchGenetics(Graph graph) {
        setPopulation(100, graph);
        for (int i = 0; i < population.size(); i++) {
            population.get(i).setFitness(fitnessFunction(population.get(i)));
        }

        tournamentSelection(4);
//        for (int i = 0; i < selected.size(); i++) {
//            System.out.println(i + " " + selected.get(i).getFitness());
//        }
        newGeneration();
        mutation(0.02);

//
//        for (int i = 0; i < newGeneration.size(); i++) {
//            for (int j = 0; j < newGeneration.get(i).nodes.size(); j++) {
//                System.out.println(newGeneration.get(i).nodes.get(j).getName() + ": " + newGeneration.get(i).nodes.get(j).getColor() + " ");
//                for (int k = 0; k < newGeneration.get(i).nodes.get(j).getAdjacencies().size(); k++) {
//                    System.out.println(newGeneration.get(i).nodes.get(j).getAdjacencies().get(k).getName() + "*" + newGeneration.get(i).nodes.get(j).getAdjacencies().get(k).getColor());
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
       // System.out.println(newGeneration.size());

    }

    void setPopulation(int num, Graph graph) {
        int rand;
        for (int i = 0; i < num; i++) {
            Graph newGraph = Graph.copyGraph(graph);
            for (int j = 0; j < newGraph.nodes.size(); j++) {
                rand = random.nextInt(3);
                newGraph.nodes.get(j).setColor(rand + 1);
            }
            population.add(newGraph);

        }


//        for (int i = 0; i < population.size(); i++) {
//            for (int j = 0; j < population.get(i).nodes.size(); j++) {
//                System.out.println(population.get(i).nodes.get(j).getName() + ": " + population.get(i).nodes.get(j).getColor() + " ");
//                for (int k = 0; k < population.get(i).nodes.get(j).getAdjacencies().size(); k++) {
//                    System.out.println(population.get(i).nodes.get(j).getAdjacencies().get(k).getName()+"*"+population.get(i).nodes.get(j).getAdjacencies().get(k).getColor());
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
    }


    int fitnessFunction(Graph graph) {
        return (computeCost(graph));
    }


    void tournamentSelection(int k) {
        Graph[] k_individuals = new Graph[k];
        for (int r = 0; r < (population.size() / k); r++) {
            Graph best = new Graph();
            for (int i = 0; i < k; i++) {
                k_individuals[i] = population.get(random.nextInt(population.size() - 1));
            }
            for (int i = 0; i < k_individuals.length; i++) {
                if (best.getFitness() < k_individuals[i].getFitness()) {
                    best = k_individuals[i];

                }
            }
            selected.add(best);

        }
        //System.out.println(selected.size());
    }

    public void newGeneration() {
        for (int i = 0; i < population.size(); i++) {
            int test1 = random.nextInt(selected.size() - 1);
            Graph father = selected.get(test1);
            // System.out.print(i + " " + test1 + " " + father);
            // System.out.print(" ");
            int test2 = random.nextInt(selected.size() - 1);
            Graph mother = selected.get(test2);
            // System.out.println(test2 + " " + mother);
            if (father != mother) {
                newGeneration.add(crossover(father, mother));
            }
        }
        System.out.println(newGeneration.size());
    }


    public Graph crossover(Graph father, Graph mother) {
        Graph childGraph = Graph.copyGraph(graph);
        for (int i = 0; i < childGraph.nodes.size(); i++) {
            int rand = random.nextInt(2);
            if (rand == 0) {
                childGraph.nodes.get(i).setColor(father.nodes.get(i).getColor());
            } else if (rand == 1) {
                childGraph.nodes.get(i).setColor(mother.nodes.get(i).getColor());
            }
        }
        return childGraph;
    }


    public void mutation(double mutationRate) {
        double mutatedGenomes = newGeneration.size() * 11 * mutationRate;
        for (int i = 0; i < mutatedGenomes; i++) {
            int childIndex = random.nextInt(newGeneration.size() - 1);
            Graph mutatedChild = newGeneration.get(childIndex);
            int genomeIndex = random.nextInt(mutatedChild.nodes.size() - 1);
            //System.out.println(genomeIndex);
            ArrayList<Integer> tempColor = new ArrayList<>();
            tempColor.add(1);
            tempColor.add(2);
            tempColor.add(3);
            for (int j = 0; j < tempColor.size(); j++) {
                if (tempColor.get(j) == mutatedChild.nodes.get(genomeIndex).getColor()) {
                    tempColor.remove(tempColor.get(j));
                    break;
                }
            }
            mutatedChild.nodes.get(genomeIndex).setColor(tempColor.get(random.nextInt(2)));
        }
    }
}


