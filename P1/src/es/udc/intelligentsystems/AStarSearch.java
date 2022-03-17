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

        int i = 0;

        System.out.println((++i) + " - Starting search at " + currentNode.state);

        while(!frontier.isEmpty()){
            currentNode = frontier.poll();

            if (p.isGoal(currentNode.state)){
                return currentNode.state;
            }

            explored.add(currentNode);
            System.out.println(currentNode);

            Action[] availActions = p.actions(currentNode.state);

            for (Action acc: availActions) {

                State s = p.result(currentNode.state,acc);
                Node auxnode = new Node(s, currentNode, acc,h);
                if (!explored.contains(auxnode)){
                    if (!frontier.contains(auxnode)){
                        frontier.offer(auxnode);

                    } else{
                        Node aux = extractNode(auxnode,frontier);
                        frontier.remove(aux);
                        frontier.offer(auxnode);
                    }
                }
            }
        }


         throw new Exception("Not solution found");
    }

    private Node extractNode(Node n, Queue<Node> frontier) throws Exception {
        for (Node aux : frontier) {
            if (aux.equals(n)){
                return aux;
            }
        }
        throw new Exception("Error");
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