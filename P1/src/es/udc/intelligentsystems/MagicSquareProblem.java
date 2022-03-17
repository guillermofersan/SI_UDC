package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagicSquareProblem extends SearchProblem{

    public static class MagicSquareState extends State {

        public int[][] square;

        public int size;
        public int magicNum;

        public MagicSquareState(int[][] square) {
            this.square = square;
            this.size = square.length;
            this.magicNum = (int) (size*(Math.pow(size,2)+1))/2;
        }

        @Override
        public String toString() {

            StringBuilder string = new StringBuilder("\n"); //[ Magic square " + size + "x" + size + " ]


            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {

                    string.append(" [").append(square[i][j]).append("] ");
                }
                string.append("\n");
            }

            return string.toString();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MagicSquareState that = (MagicSquareState) o;
            return Arrays.deepEquals(square, that.square);
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(square);
        }
    }

    public static class MagicSquareAction extends Action {

        int row, col;
        int number;

        public MagicSquareAction(int row, int pos, int number) {
            this.row = row;
            this.col = pos;
            this.number = number;
        }

        @Override
        public String toString() {
            return number + " to cell " + "[" + row + ", " + col + "]";
        }

        @Override
        public boolean isApplicable(State st) {
            if (st==null) return false;

            MagicSquareState msSt = (MagicSquareState) st;
            int size = msSt.size;
            if (!(row<size && col<size && number>0 && number<=msSt.magicNum && msSt.square[row][col]==0))
                return false;


            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(msSt.square[i][j]==number)
                        return false;
                }
            }

            int aux;

            for (int i = 0; i < size; i++) {
                int sumrow = 0, sumcol = 0;

                for (int j = 0; j < size; j++) {
                    if (i==row && j==col){
                        aux = number;
                    } else aux = msSt.square[i][j];

                    if (aux<0 || aux>(size*size))
                        return false;
                    sumrow += aux;
                    sumcol += msSt.square[j][i];
                }

                if (sumrow > msSt.magicNum || sumcol > msSt.magicNum)
                    return false;
            }

            return true;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareState msSt = (MagicSquareState) st;
            int size = msSt.size;
            int[][] square = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(i==row && j==col){
                        square[i][j]=number;
                    } else{
                        square[i][j]=msSt.square[i][j];
                    }
                }
            }

            return new MagicSquareState(square);
        }
    }

    public MagicSquareProblem(State initialState) {
        super(initialState);
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState msSt = (MagicSquareState) st;
        int[][] square = msSt.square;
        int size = msSt.size;

        int diag1 = 0, diag2=0; //sum of the diagonals
        for (int i = 0; i < size; i++) {
            diag1 += square[i][i];
            diag2 += square[i][size-1-i];
        }

        if(diag1!=diag2 || diag1!=msSt.magicNum)
            return false;

        for (int i = 0; i < size; i++) {
            int sumrow = 0, sumcol = 0;
            for (int j = 0; j < size; j++) {
                int aux = square[i][j];
                if (aux<0 || aux>(size*size))
                    return false;
                sumrow += aux;
                sumcol += square[j][i];
            }

            if (sumrow != sumcol || sumrow != diag1)
                return false;
        }
        return true;
    }

    @Override
    public Action[] actions(State st) {
        MagicSquareState msSt = (MagicSquareState) st;

        int size = msSt.size;

        List<Action> actionlist = new ArrayList<>();


        Action act;

        /*
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 1; k <= (size*size); k++) {
                    act = new MagicSquareAction(i,j,k);

                    if (act.isApplicable(st)){
                        actionlist.add(new MagicSquareAction(i,j,k));
                    }

                }
            }
        }

         */

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (msSt.square[i][j]==0){

                    for (int k = 1; k <= (size*size); k++) {
                        act = new MagicSquareAction(i,j,k);
                        if (act.isApplicable(msSt)){

                            actionlist.add(act);
                        }
                    }
                    return actionlist.toArray(new Action[0]);
                }

            }
        }

        return actionlist.toArray(new Action[0]);
    }
}
