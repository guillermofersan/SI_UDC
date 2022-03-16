package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.Node;

import java.util.*;

public class AStarSearch implements InformedSearchStrategy{


    @Override
    public State solve(SearchProblem p, Heuristic h) throws Exception {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        List<Node> explored = new ArrayList<>();

        Node parent = null;
        Node currentNode;
        Action acc = null;
        State currentState = p.getInitialState();
        frontier.add(new Node(currentState, parent, acc, h));

        int i = 0;

        System.out.println((++i) + " - Starting search at " + currentState);

        while (true){

            if (frontier.isEmpty()){ //If you reach nothing in the frontier, you have not found any solution
                throw new Exception("Could not find any solution");
            }  else{  //You poll the Queue (frontier), and pick the first element
                currentNode = frontier.poll();
            }


            if(p.isGoal(currentNode.state)){  //If it is goal, end
                System.out.println("is goal");
                break;
            } else{  //If not goal, add to explored, and update frontier with the successors function
                System.out.println(" is not goal");
                explored.add(currentNode);

                //frontier = successors(p, frontier, currentNode, explored);
                List<Node> aux = successors(p, currentNode, h);
                for (Node n: aux) {
                    if (explored.contains(n)){
                        continue;
                    } else if (frontier.contains(n)) {
                        //Node node = null; //Obtain the node from the frontier which state is equal to n.state

                        Node node = null;

                        for (Node auxn:frontier) {
                            if (auxn.equals(n)){
                                node = auxn;
                                break;
                            }
                        }
                        if(node.totalcost>n.totalcost){
                            frontier.remove(node);
                        } else continue;
                    }
                    frontier.add(n);
                }

                if (!frontier.isEmpty()){
                    currentNode=frontier.peek(); //cambiamos al state siguiente para printearlo
                    System.out.println((++i) + " Actual state changed to " + currentNode.state);
                }
            }
        }
        System.out.println((++i) + "- End " + currentNode.state);
        //return reconstruct_sol(currentNode);
        return currentNode.state;
    }



    public List<Node> successors (SearchProblem p, Node currentnode, Heuristic h){
        System.out.println("Expanding frontier: {");
        Action[] availableActions = p.actions(currentnode.state);

        Node nd;
        List<Node> aux = new ArrayList<>();

        int i=0;

        for(Action acc : availableActions){
            if(acc.isApplicable(currentnode.state)) { //Looks if the action is applicable to the current state

                nd = new Node(p.result(currentnode.state, acc),currentnode,acc,h);
                aux.add(nd);

                System.out.println("-" + (++i) + " - RESULT:" + currentnode.state + acc + ":" + nd.state);
            }
        }
        System.out.println("\n}");
        return aux;
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