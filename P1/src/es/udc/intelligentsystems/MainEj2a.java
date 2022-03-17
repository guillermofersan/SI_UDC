package es.udc.intelligentsystems;

import java.util.Arrays;

public class MainEj2a {

    public static void main(String[] args) throws Exception {

        int[][] matrix={
                {4,9,2},
                {3,5,0},
                {0,1,0}
        };

        int[][] matrix2={
                {2,0,0},
                {0,0,0},
                {0,0,0}
        };



        MagicSquareProblem.MagicSquareState initialState1 = new MagicSquareProblem.MagicSquareState(matrix);
        SearchProblem magicSquareproblem1 = new MagicSquareProblem(initialState1);

        MagicSquareProblem.MagicSquareState initialState2 = new MagicSquareProblem.MagicSquareState(matrix2);
        SearchProblem magicSquareproblem2 = new MagicSquareProblem(initialState2);


        SearchStrategy bSearcher = new BreadthFirstSearch();
        SearchStrategy dSearcher = new DepthFirstSearch();



        System.out.println(Arrays.toString(bSearcher.solve(magicSquareproblem1)));
        System.out.println("\n\n\n----------------------\n\n\n");
        System.out.println(Arrays.toString(dSearcher.solve(magicSquareproblem2)));


    }

}
