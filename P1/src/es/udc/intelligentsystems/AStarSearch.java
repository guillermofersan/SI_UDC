package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.Node;

import java.util.*;

public class AStarSearch implements InformedSearchStrategy{


    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {
        Queue<Node> frontier = new PriorityQueue<>();
        List<Node> explored = new ArrayList<>();

        Node currentNode = new Node(p.getInitialState(), null, null, h);

        frontier.offer(currentNode);

        System.out.println(" - Starting search at " + currentNode.state);

        while(!frontier.isEmpty()){
            currentNode = frontier.poll();

            if (p.isGoal(currentNode.state)){ //If the state is goal, the search ends and state is returned
                return currentNode.state;
            }

            explored.add(currentNode);
            System.out.println(currentNode);

            Action[] availActions = p.actions(currentNode.state); //gets all available actions for that state

            for (Action acc: availActions) {

                State s = p.result(currentNode.state,acc);
                Node auxnode = new Node(s, currentNode, acc,h);
                if (!explored.contains(auxnode)){ //Checks if that state was already explored
                    if (!frontier.contains(auxnode)){ //Checks if that node is already in the frontier, if not, it is added
                        frontier.offer(auxnode);
                    } else{
                        Node aux = extractNode(auxnode,frontier);
                        // if the state was in the frontier, it is compared with the new one, the one whose f is lower gets to stay in the frontier
                        if(aux.totalcost>auxnode.totalcost){
                            frontier.remove(aux);
                            frontier.offer(auxnode);
                        }
                    }
                }
            }
        }
         throw new Exception("Not solution found");
    }

    private Node extractNode(Node n, Queue<Node> frontier) throws Exception {
        // extracts a given node from a queue
        // Precondition: a node with the same state as the one given is in the queue
        for (Node aux : frontier) {
            if (aux.equals(n)){
                return aux;
            }
        }
        throw new Exception("Error");
    }
}