package main;

public enum Mode {
    instance;

    public SelectionMode selectionMode;
    public CrossoverMode crossoverMode;
    public MutationMode mutationMode;

    public enum SelectionMode {
        ROULETTE_WHEEL,
        TOURNAMENT_SELECTION
    }

    public enum CrossoverMode {
        CYCLE,
        HEURISTIC,
        ORDERED,
        PARTIALLY_MATCH,
        POSITION_BASED,
        RANDOM,
        SUB_TOUR_EXCHANGER
    }

    public enum MutationMode {
        DISPLACEMENT,
        EXCAHNGE,
        HEURISTIC,
        INSERTION,
        INVERSION
    }
}
