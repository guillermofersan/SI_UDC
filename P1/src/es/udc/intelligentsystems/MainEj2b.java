package es.udc.intelligentsystems;

import java.util.Arrays;

public class MainEj2b {
    public static void main(String[] args) throws Exception {

        int[][] matrix={
                {4,9,2},
                {3,5,0},
                {0,1,0}
        };

        int[][] m2={
                {2,0,0},
                {0,0,0},
                {0,0,0}
        };


        System.out.println("Estado inicial");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" [" + matrix[i][j] + "] ");
            }
            System.out.print("\n");
        }
        System.out.print("-------------------------------------\n\n\n\n");



        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(m2);

        SearchProblem magicSquare = new MagicSquareProblem(initialState);
        InformedSearchStrategy searcher = new AStarSearch();
        Heuristic h = new MagicSquareHeuristic();


        System.out.println("\n\n\n" + searcher.solve(magicSquare,h));

    }
}
