package ceit.aut.ac.ir;

import java.util.ArrayList;
import java.util.Random;

public class Genetics extends Problem {
    ArrayList<Graph> population;
    ArrayList<Integer> fitness;
    ArrayList<Graph> selected;
    ArrayList<Graph> newGeneration;
    Random random = new Random();

    public Genetics(Graph graph, int numOfIteration) {
        super(graph);
        super.setInitialization();
        population = new ArrayList<>();
        fitness = new ArrayList<>();
        selected = new ArrayList<>();
        population.add(graph);
        searchGenetics(graph, numOfIteration);


    }


    public void searchGenetics(Graph graph, int num) {
        setPopulation(1000, graph);
        for (int j = 0; j < num; j++) {
            for (int i = 0; i < population.size(); i++) {
                population.get(i).setFitness(fitnessFunction(population.get(i)));
            }
            //System.out.println("best fitness in this generation: " + bestFitness());
            //System.out.println("worst fitness in this generation: " + worstFitness());
            System.out.println("average fitness in this generation: " + averageFitness());
            tournamentSelection(4);
            newGeneration();
            mutation(0.02);
            recurrence();
        }


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

    }

    public void newGeneration() {
        newGeneration = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            int test1 = random.nextInt(selected.size() - 1);
            Graph father = selected.get(test1);
            int test2 = random.nextInt(selected.size() - 1);
            Graph mother = selected.get(test2);
            if (father != mother) {
                newGeneration.add(crossover(father, mother));
            }
        }
       // System.out.println(newGeneration.size());
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


    public void recurrence() {
        population = newGeneration;
    }

    public int bestFitness() {
        int best = 0;
        for (int i = 0; i < population.size(); i++) {
            if (best < population.get(i).getFitness()) {
                best = population.get(i).getFitness();
            }

        }
        return best;
    }

    public int worstFitness() {
        int worst = 100;
        for (int i = 0; i < population.size(); i++) {
            if (worst > population.get(i).getFitness()) {
                worst = population.get(i).getFitness();
            }

        }
        return worst;
    }


    public int averageFitness() {
        int average = 0;
        for (int i = 0; i < population.size(); i++) {

            average += population.get(i).getFitness();

        }
        average = average / population.size();

        return average;
    }

}


