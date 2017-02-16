package main;

import base.City;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Random;

public enum Configuration {
    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");

    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String dataFilePath = dataDirectory + "TSP280.txt";

    public String databaseFile = dataDirectory + "datastore.db";

    public Random randomSeed = new MersenneTwisterFast(System.currentTimeMillis());

    public ArrayList<City> availableCities;
    public int tourBorder = 33; // Number of tours given back by the doSelection method in percent

    public int populataionSize = 50;
}