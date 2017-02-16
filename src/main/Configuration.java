package main;

import random.MersenneTwisterFast;

import java.util.Random;

public enum Configuration {
    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");

    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String dataFilePath = dataDirectory + "TSP280.txt";

    public String databaseFile = dataDirectory + "datastore.db";

    public Random randomSeed = new MersenneTwisterFast(System.currentTimeMillis());
    public int tourBorder = 33; // Number of tours given back by the doSelection method in percent
}