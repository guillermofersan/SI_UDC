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

        int created = 0;

        System.out.println(" - Starting search at " + currentState);

        while (!frontier.isEmpty()){

            currentNode = frontier.pop();
            System.out.println(currentNode);

            if(p.isGoal(currentNode.state)){  //If it is goal, end
                System.out.println("\nNumber of expanded nodes = " + explored.size());
                System.out.println("Number of created  nodes = " + created);
                System.out.println("- End " + currentNode.state);

                return reconstruct_sol(currentNode);
            } else{  //If not goal, add to explored, and update frontier with the successors function

                explored.add(currentNode);
                int aux = frontier.size();
                frontier = successors(p, frontier, currentNode, explored);
                created+=frontier.size()-aux; //The amount of nodes created in the successors function is added to the total
            }
        }
        throw new Exception("Could not find any solution"); //if the frontier is empty, no solution was found
    }


    public Stack<Node> successors (SearchProblem p, Stack<Node> frontier, Node currentnode, List<Node> explored){
        Action[] availableActions = p.actions(currentnode.state);
        Node nd;

        for(Action acc : availableActions){

            if(acc.isApplicable(currentnode.state)) { //Looks if the action is applicable to the current state

                nd = new Node(p.result(currentnode.state, acc),currentnode,acc);
                if (!explored.contains(nd)) { //Checks if it has been already explored
                    if (!frontier.contains(nd)) { //adds the node to the frontier if it was not there
                        frontier.add(nd);
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