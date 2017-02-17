package main;

import java.text.DecimalFormat;

import base.Population;
import crossover.ICrossover;
import data.HSQLDBManager;
import data.InstanceReader;
import data.TSPLIBReader;
import mutation.IMutation;
import random.MersenneTwisterFast;
import selection.ISelection;
import selection.RouletteWheelSelection;
import selection.TournamentSelection;

public class Application {

    private double[][] distances;

    private ISelection selection;
    private ICrossover crossover;
    private IMutation mutation;

    public void startupHSQLDB() {
        HSQLDBManager.instance.startup();
        HSQLDBManager.instance.init();
    }

    public void shutdownHSQLDB() {
        HSQLDBManager.instance.shutdown();
    }

    public void printMatrix(double[][] matrix) {
        DecimalFormat decimalFormat = new DecimalFormat("000.00");

        for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < matrix[0].length; columnIndex++)
                System.out.print(decimalFormat.format(matrix[rowIndex][columnIndex]) + "\t");
            System.out.println();
        }
    }

    public void loadData() {
        System.out.println("--- GeneticAlgorithm.loadData()");
        InstanceReader instanceReader = new InstanceReader(Configuration.instance.dataFilePath);
        instanceReader.open();
        TSPLIBReader tspLibReader = new TSPLIBReader(instanceReader);

        Configuration.instance.availableCities = tspLibReader.getCities();
        System.out.println("availableCities (size) : " + Configuration.instance.availableCities.size());

        distances = tspLibReader.getDistances();
        printMatrix(distances);

        instanceReader.close();

        System.out.println();
    }

    public void initConfiguration() {
        System.out.println("--- GeneticAlgorithm.initConfiguration()");
        System.out.println();

        // TODO Config einlesen.

        Mode executionMode = new Mode();
        executionMode.selectionMode = Mode.SelectionMode.ROULETTE_WHEEL;
        executionMode.crossoverMode = Mode.CrossoverMode.CYCLE;
        executionMode.mutationMode = Mode.MutationMode.DISPLACEMENT;

        Configuration.instance.executionMode = executionMode;
    }

    public void execute() {
        System.out.println("--- GeneticAlgorithm.execute()");
        HSQLDBManager.instance.insert("hello world");

        Population population = new Population();

        ISelection selection;
        switch (Configuration.instance.executionMode.selectionMode) {
            case ROULETTE_WHEEL:
                selection = new RouletteWheelSelection(new MersenneTwisterFast(System.currentTimeMillis()));
                break;

            default:
                selection = new TournamentSelection(new MersenneTwisterFast(System.currentTimeMillis()));
                break;
        }

        selection.doSelection(population);
    }

    public static void main(String... args) {
        Application application = new Application();
        application.startupHSQLDB();
        application.loadData();
        application.initConfiguration();
        application.execute();
        application.shutdownHSQLDB();
    }
}