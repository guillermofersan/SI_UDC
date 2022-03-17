package es.udc.intelligentsystems;

import java.util.Arrays;

public class MainEj2b {
    public static void main(String[] args) throws Exception {

        int[][] matrix={
                {2,0,0},
                {0,0,0},
                {0,0,0}
        };


        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(matrix);

        SearchProblem magicSquare = new MagicSquareProblem(initialState);
        InformedSearchStrategy searcher = new AStarSearch();
        Heuristic h = new MagicSquareHeuristic();

        System.out.println("\n\n\n" + searcher.solve(magicSquare,h));

    }
}
