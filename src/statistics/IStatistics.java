package statistics;

import java.util.ArrayList;

public interface IStatistics {
    void writeCSVFile(ArrayList<Double> data,String filename);
    void buildPlotRFile(String workingDirectory,String filename);
    void buildMeasureRFile(String workingDirectory,String... filenames);
    void buildBoxPlotRFile(String workingDirectory,String... filenames);
    void buildTTestRFile(String workingDirectory,String filename01,String filename02);
    void buildHistogramRFile(String workingDirectory,String filename,int numberOfBreaks,boolean isPrinted);
    void buildStripChartRFile(String workingDirectory,String filename);
    void buildMostFrequentFitnessValuesRFile(String workingDirectory,String filename,int numberOfFitnessValues);
}