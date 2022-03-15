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

        Node parent = null;
        Node currentNode;
        Action acc = null;
        State currentState = p.getInitialState();
        frontier.add(new Node(currentState, parent, acc));

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);
        
        while (true){

            if (frontier.isEmpty()){ //If you reach nothing in the frontier, you have not found any solution
                throw new Exception("Could not find any solution");
            }  else{  //You poll the Queue (frontier), and pick the first element
                currentNode = frontier.poll();
            }


            if(p.isGoal(currentNode.state)){  //If it is goal, end
                System.out.println((i++) + "- " + currentNode.state + "is goal");
                break;
            } else{  //If not goal, add to explored, and update frontier with the successors function
                System.out.println((i++) + "- " + currentNode.state + " is not goal");
                explored.add(currentNode);
                frontier = successors(p, frontier, currentNode, explored);

                if (!frontier.isEmpty()){
                    currentNode=frontier.peek(); //cambiamos al state siguiente para printearlo
                    System.out.println((i++) + "- Actual state changed to " + currentNode.state);
                }

            }
        }
        System.out.println((i++) + "- End " + currentNode.state);
    return reconstruct_sol(currentNode);
    }
    
    
    public Queue<Node> successors (SearchProblem p, Queue<Node> frontier, Node currentnode, List<Node> explored){
        System.out.println("Expanding frontier:");
        Action[] availableActions = p.actions(p.getInitialState());
        State st;
        Node nd;

        int i=0;

        for(Action acc : availableActions){

            if(acc.isApplicable(currentnode.state)) { //Looks if the action is applicable to the current state
                //st = p.result(currentnode.state, acc);
                nd = new Node(p.result(currentnode.state, acc),currentnode,acc);
                System.out.println("\t-" + (i++) + " - RESULT(" + nd.state + "," + acc + ") = " + nd.state);

                if (!explored.contains(nd)) { //Checks if it has been already explored
                    System.out.println("\t-" + (i++) + " - " + nd.state + " NOT explored");
                    boolean isinFrontier = false;

                    for (Node node : frontier) {
                        if (node.equals(nd)) {
                            isinFrontier = true;
                            break;
                        }
                    }

                    if (!isinFrontier) { //adds the node to the frontier if it was not there
                        System.out.println("\t-" + (i++) + " - " + nd.state + " NOT in the frontier");
                        frontier.add(nd);
                    }
                }else{
                    System.out.println("\t-" +(i++) + " - " + currentnode.state + " already explored");
                }
            }
        }
        return frontier;
    }
    private Node[] reconstruct_sol(Node n){
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

