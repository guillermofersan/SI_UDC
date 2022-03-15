package es.udc.intelligentsystems;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        int[][] matrix={
                {4,9,2},
                {0,5,0},
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



        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(matrix);
        //MagicSquareProblem.MagicSquareState solution = new MagicSquareProblem.MagicSquareState(sol);
        SearchProblem magicSquare = new MagicSquareProblem(initialState);
        SearchStrategy searcher = new BreadthFirstSearch();
        SearchStrategy searcher2 = new DepthFirstSearch();



        //System.out.println("\n\n\n" + Arrays.toString(searcher.solve(magicSquare)));
        System.out.println("\n\n\n" + Arrays.toString(searcher2.solve(magicSquare)));


    }

}
