package es.udc.intelligentsystems;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MagicSquareProblem extends SearchProblem{

    public static class MagicSquareState extends State {

        private List<List<Integer>> square;
        private int size;
        private int magicNum;

        public MagicSquareState(List<List<Integer>> square) {
            this.square = square;
            this.size = square.size();
            this.magicNum = (int) (size*(Math.pow(size,2)+1))/2;
        }

        @Override
        public String toString() {

            StringBuilder string = new StringBuilder("[ Magic square " + size + "x" + size + " ]\n");

            for (List<Integer> integers : square) {
                for (int j = 0; j < size; j++) {
                    string.append(" ").append(integers.get(j)).append(" ");
                }
                string.append("\n");
            }
            /*
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    string.append(square.get(i*size+j));
                }
            }
             */

            return string.toString();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MagicSquareState that = (MagicSquareState) o;
            return square.equals(that.square);
        }

        @Override
        public int hashCode() {
            return Objects.hash(square);
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
            if (!(row<size && col<size && number>0 && number<=msSt.magicNum && msSt.square.get(row).get(col)==0))
                return false;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(msSt.square.get(i).get(j)==number)
                        return false;
                }
            }

            return true;
        }

        @Override
        public State applyTo(State st) {
            MagicSquareState msSt = (MagicSquareState) st;

            List<Integer> aux = msSt.square.get(row);
            aux.set(col, number);

            msSt.square.set(row,aux);

            return null;
        }
    }

    public MagicSquareProblem(State initialState) {
        super(initialState);
    }

    @Override
    public boolean isGoal(State st) {
        MagicSquareState msSt = (MagicSquareState) st;
        List<List<Integer>> square = msSt.square;
        int size = msSt.size;

        int diag1 = 0, diag2=0; //sum of the diagonals
        for (int i = 0; i < size; i++) {
            diag1 += square.get(i).get(i);
            diag2 += square.get(i).get(size-1-i);
        }

        if(diag1!=diag2 || diag1!=msSt.magicNum)
            return false;

        int sumrow = 0, sumcol = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int aux = square.get(i).get(j);
                if (aux<0 || aux>(size*size));
                sumrow += aux;
                sumcol += square.get(j).get(i);
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
        int magicnum = msSt.magicNum;

        List<Action> actionlist = new ArrayList<>();
        Action act;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 1; k <= magicnum; k++) {
                    act = new MagicSquareAction(i,j,magicnum);

                    if (act.isApplicable(st))
                        actionlist.add(act);
                }
            }
        }

        return actionlist.toArray(new Action[0]);
    }


}
