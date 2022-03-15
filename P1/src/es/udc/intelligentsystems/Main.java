package es.udc.intelligentsystems;

import es.udc.intelligentsystems.example.GraphSearchStrategy;
import es.udc.intelligentsystems.example.VacuumCleanerProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        int[][] matrix={
                {4,9,2},
                {3,5,0},
                {0,1,0}
        };

        int[][] sol={
                {4,9,2},
                {3,5,7},
                {8,1,6}
        };


        System.out.println("Estado inicial");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" [" + sol[i][j] + "] ");
            }
            System.out.print("\n");
        }
        System.out.print("-------------------------------------\n\n\n\n");



        MagicSquareProblem.MagicSquareState initialState = new MagicSquareProblem.MagicSquareState(matrix);
        MagicSquareProblem.MagicSquareState solution = new MagicSquareProblem.MagicSquareState(sol);
        SearchProblem magicSquare = new MagicSquareProblem(initialState);
        SearchStrategy searcher = new BreadthFirstSearch();

        //System.out.println(Arrays.toString(magicSquare.actions(initialState)));

        //System.out.println(magicSquare.isGoal(solution));

        System.out.println("\n\n\n" + Arrays.toString(searcher.solve(magicSquare)));


    }

}
