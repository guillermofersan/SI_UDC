package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;

import java.util.*;

public class GraphSearchStrategy implements SearchStrategy {


    @Override
    public Node[] solve(SearchProblem p) throws Exception {

        Queue<Node> frontier = new LinkedList<>();
        List<Node> explored = new ArrayList<>();

        Node currentNode;
        State currentState = p.getInitialState();
        frontier.add(new Node(currentState, null, null));
        int i=0;

        System.out.println((++i) + "-" + currentState);
        
        while (!frontier.isEmpty()){

            currentNode = frontier.poll();

            if(p.isGoal(currentNode.state)){  //If it is goal, end
                System.out.println("\n" + (++i) + "- End " + currentNode.state);
                return reconstruct_sol(currentNode);
            } else{  //If not goal, add to explored, and update frontier with the successors function
                System.out.println(currentNode);
                explored.add(currentNode);
                frontier = successors(p, frontier, currentNode, explored);

                if (!frontier.isEmpty()){
                    currentNode=frontier.peek(); //cambiamos al state siguiente para printearlo
                    System.out.println((++i) + "-" + currentNode.state);
                }

            }
        }

        throw new Exception("Could not find any solution");
    }
    
    
    public Queue<Node> successors (SearchProblem p, Queue<Node> frontier, Node currentnode, List<Node> explored){
        Action[] availableActions = p.actions(p.getInitialState());
        Node nd;

        int i=0;

        for(Action acc : availableActions){

            if(acc.isApplicable(currentnode.state)) { //Looks if the action is applicable to the current state

                nd = new Node(p.result(currentnode.state, acc),currentnode,acc);

                if (!explored.contains(nd)) { //Checks if it has been already explored
                    if (!frontier.contains(nd)) {  //Checks if the node is already in the frontier
                        frontier.add(nd); //Adds the node to the frontier if it was not there
                    }
                }
            }
        }
        return frontier;
    }
    private Node[] reconstruct_sol(Node n){ //Returns the array of the nodes which form the path to the solution
        List<Node> nodelist = new ArrayList<>();
        Node currentNode = n;

        while (currentNode!=null){
            nodelist.add(currentNode);
            currentNode = currentNode.parent;
        }

        Collections.reverse(nodelist);
        return nodelist.toArray(new Node[0]);
    }


}

