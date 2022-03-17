package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.Node;

import java.util.*;

public class DepthFirstSearch implements SearchStrategy{

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        Stack<Node> frontier = new Stack<>();
        List<Node> explored = new ArrayList<>();

        Node currentNode;
        State currentState = p.getInitialState();
        frontier.add(new Node(currentState, null, null));

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentState);

        while (true){

            if (frontier.isEmpty()){ //If you reach nothing in the frontier, you have not found any solution
                throw new Exception("Could not find any solution");
            }  else{  //You poll the Queue (frontier), and pick the first element
                currentNode = frontier.pop();
            }


            if(p.isGoal(currentNode.state)){  //If it is goal, end

                break;
            } else{  //If not goal, add to explored, and update frontier with the successors function

                explored.add(currentNode);
                frontier = successors(p, frontier, currentNode, explored);

                if (!frontier.isEmpty()){
                    currentNode=frontier.peek(); //cambiamos al state siguiente para printearlo
                    System.out.println(currentNode);
                }

            }
        }
        System.out.println(i + "- End " + currentNode.state);
        return reconstruct_sol(currentNode);
    }


    public Stack<Node> successors (SearchProblem p, Stack<Node> frontier, Node currentnode, List<Node> explored){
        Action[] availableActions = p.actions(currentnode.state);
        State st;
        Node nd;

        int i=0;

        for(Action acc : availableActions){

            if(acc.isApplicable(currentnode.state)) { //Looks if the action is applicable to the current state

                nd = new Node(p.result(currentnode.state, acc),currentnode,acc);
                //System.out.printf(nd.state.toString());

                if (!explored.contains(nd)) { //Checks if it has been already explored
                    if (!frontier.contains(nd)) { //adds the node to the frontier if it was not there
                        frontier.add(nd);
                    }
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
