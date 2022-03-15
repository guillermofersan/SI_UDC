package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Strategy4 implements SearchStrategy {

    public Strategy4() {
    }

    @Override
    public Node[] solve(SearchProblem p) throws Exception{

        ArrayList<Node> explored = new ArrayList<>();
        State currentState = p.getInitialState();
        Node currentNode = new Node(currentState,null,null);
        explored.add(currentNode);

        int i = 1;

        System.out.println((i++) + " - Starting search at " + currentNode.state);

        while (!p.isGoal(currentNode.state)){

            System.out.println((i++) + " - " + currentNode.state + " is not a goal");
            Action[] availableActions = p.actions(currentNode.state);
            boolean modified = false;

            for (Action acc: availableActions) {
                Node nc = new Node(p.result(currentNode.state, acc),currentNode,acc);

                System.out.println((i++) + " - RESULT(" + currentNode.state + ","+ acc + ")=" + nc.state);

                if (!explored.contains(nc)) {
                    //currentState = sc;
                    currentNode = nc;
                    System.out.println((i++) + " - " + nc.state + " NOT explored");
                    explored.add(currentNode);
                    modified = true;
                    System.out.println((i++) + " - Current state changed to " + nc.state);
                    break;
                }
                else
                    System.out.println((i++) + " - " + nc.state + " already explored");
            }
            if (!modified) throw new Exception("No solution could be found");
        }
        System.out.println((i++) + " - END - " + currentNode.state);

        return reconstruct_sol(currentNode);
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
