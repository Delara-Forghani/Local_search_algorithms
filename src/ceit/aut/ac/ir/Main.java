package ceit.aut.ac.ir;

import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Input input = new Input("E:\\AI\\SearchProject\\src\\ceit\\aut\\ac\\ir\\input.txt");
        Problem problem = new Problem(input.graph);
        //SimpleHillClimbing simpleHC = new SimpleHillClimbing(input.graph);
        //StochasticHillClimbing stochasticHC = new StochasticHillClimbing(input.graph);
        // hillClimbing.checkSuccessors(input.graph);
        //FirstChoiceHillClimbing firstChoiceHC = new FirstChoiceHillClimbing(input.graph);
        //RandomRestartHillClimbing randomHC=new RandomRestartHillClimbing(input.graph);
        // SimulatedAnnealing sm = new SimulatedAnnealing(input.graph);
        Genetics genetics = new Genetics(input.graph);

    }
}
