package es.udc.intelligentsystems;

public class MagicSquareHeuristic extends Heuristic{
    @Override
    public float evaluate(State e) {
        MagicSquareProblem.MagicSquareState state = (MagicSquareProblem.MagicSquareState) e;
        int[][] matrix = state.square;
        float counter=0;

        for (int i = 0; i < state.size; i++) {
            for (int j = 0; j < state.size; j++) {
                if (matrix[i][j]==0)
                    counter++;
            }
        }

        return counter;
    }
}
