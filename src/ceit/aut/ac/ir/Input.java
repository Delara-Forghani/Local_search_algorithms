package ceit.aut.ac.ir;

import java.io.*;

public class Input {
    Graph graph;

    public Input(String address) {
        File file = new File(address);
        try {
            graph = new Graph();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st;

            boolean firstExist, secondExist;
            boolean firstAdjacentExist, secondAdjacentExist;
            int firstIndex, secondIndex;

            while ((st = reader.readLine()) != null) {

                firstExist = false;
                secondExist = false;
                firstAdjacentExist = false;
                secondAdjacentExist = false;

                firstIndex = -1;
                secondIndex = -1;

                String[] stArr = st.split(" ");
                Node start = new Node(stArr[0]);
                Node end = new Node(stArr[1]);

                for (int i = 0; i < graph.nodes.size(); i++) {
                    if (graph.nodes.get(i).getName().equals(start.getName())) {

                        firstExist = true;
                        firstIndex = i;

                    } else if (graph.nodes.get(i).getName().equals(end.getName())) {


                        secondExist = true;
                        secondIndex = i;

                    }

                }
                if (!firstExist && secondExist) {

                    graph.addNode(start);
                    start.addAdjacentNode(graph.nodes.get(secondIndex));
                    graph.nodes.get(secondIndex).addAdjacentNode(start);

                } else if (firstExist && !secondExist) {
                    graph.addNode(end);
                    end.addAdjacentNode(graph.nodes.get(firstIndex));
                    graph.nodes.get(firstIndex).addAdjacentNode(end);

                } else if (firstExist && secondExist) {
                    if (!checkAdjacency(graph.nodes.get(firstIndex),graph.nodes.get(secondIndex))) {
                        graph.nodes.get(firstIndex).addAdjacentNode(graph.nodes.get(secondIndex));
                    }
                    if (!checkAdjacency(graph.nodes.get(secondIndex),graph.nodes.get(firstIndex))) {
                        graph.nodes.get(secondIndex).addAdjacentNode(graph.nodes.get(firstIndex));

                    }
                } else if (!firstExist && !secondExist) {

                    graph.addNode(start);
                    start.addAdjacentNode(end);
                    graph.addNode(end);
                    end.addAdjacentNode(start);

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean checkAdjacency(Node node, Node adjacent) {
        for (int i = 0; i < node.getAdjacencies().size(); i++) {
            if (node.getAdjacencies().get(i).getName().equals(adjacent.getName())) {
                return true;
            }
        }
        return false;
    }
}
