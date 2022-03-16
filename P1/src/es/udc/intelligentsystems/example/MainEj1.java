package es.udc.intelligentsystems.example;

import es.udc.intelligentsystems.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;

import java.util.Arrays;

public class MainEj1 {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new GraphSearchStrategy();
        System.out.println(Arrays.toString(buscador.solve(aspiradora)));
    }
}
