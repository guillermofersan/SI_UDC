package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.State;

import java.util.Objects;

public class Node implements Comparable<Node>{

    public State state;
    public Node parent;
    public Action action;
    public float pathcost;
    public Float totalcost;

    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
    }

    public Node(State state, Node parent, Action action, Heuristic h) {
        this.state = state;
        this.parent = parent;
        this.action = action;

        if (this.action==null)
            pathcost=0;
        else
            pathcost+=action.getCost();

        totalcost = pathcost + h.evaluate(this.state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return state.equals(node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        String act;
        if (action==null)
            act = "none";
        else act = action.toString();

        return "State: " + state +
                "Action= " + act + "\n";
    }

    @Override
    public int compareTo(Node node) {
        return this.totalcost.compareTo(node.totalcost);
    }
}
