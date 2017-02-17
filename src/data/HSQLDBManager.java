package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import main.Configuration;

public enum HSQLDBManager {
    instance;

    private Connection connection;
    private String driverName = "jdbc:hsqldb:";
    private String username = "sa";
    private String password = "";

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String databaseURL = driverName + Configuration.instance.databaseFile;
            connection = DriverManager.getConnection(databaseURL,username,password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void init() {
        dropTables();
        createTables();
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTables() {
        System.out.println("--- dropTables");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE dataset,scenarios");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder.toString());

        update(sqlStringBuilder.toString());
    }

    public void createTables() {
        String statement = "CREATE TABLE dataSet(" +
                "id BIGINT NOT NULL AUTO_INCREMENT," +
                "scenarioId VARCHAR(20) NOT NULL," +
                "value INT NOT NULL," +
                "PRIMARY KEY (id));";
        update(statement);

        statement = "CREATE TABLE scenarios(" +
                "id VARCHAR(20) NOT NULL," +
                "selectionType VARCHAR(20) NOT NULL," +
                "crossoverType VARCHAR(20) NOT NULL," +
                "mutationType VARCHAR(20) NOT NULL," +
                "crossoverPropability REAL," +
                "mutationPropability REAL);";
        update (statement);
    }

    public void addScenario(String scenarioId, String selectionType, String mutationType, float crossoverProbability, float mutationProbability) {
        String statement = "INSERT INTO scenarios VALUES (" +
                scenarioId + "," +
                selectionType + "," +
                mutationType + "," +
                crossoverProbability + "," +
                mutationProbability + ");";
        update(statement);
    }

    public void addDataSet(String scenarioId, ArrayList<Integer> dataSet) {
        String statement;
        for (Integer entry : dataSet) {
            statement = "INSERT INTO dataSet(scenarioId, value) VALUES ( " +
                    scenarioId + "," +
                    entry + ");";
            update(statement);
        }
    }

    public ArrayList<String> getScenarioIds() {
        String query = "SELECT id FROM scenarios";
        ArrayList<String> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            do {
                output.add(result.getString(1));
            } while (result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public ArrayList<Integer> getDataSet(String scenarioId) {
        String query = "SELECT value FROM dataSet WHERE scenarioId =" + scenarioId;
        ArrayList<Integer> output = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            do {
                output.add(result.getInt(1));
            } while (result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public HashMap<String, Object> getScenarioProperties(String scenarioId) {
        String query = "SELECT selectionType,crossoverType,mutationType,crossoverPropability,mutationPropability FROM " +
                "scenarios WHERE scenarioId = " + scenarioId;
        HashMap<String, Object> output = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            do {
                output.put("selectionType", result.getString(1));
                output.put("crossoverType", result.getString(2));
                output.put("mutationType", result.getString(3));
                output.put("crossoverPropability", result.getString(4));
                output.put("mutationPropability", result.getString(5));
            } while (result.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
    public String buildSQLStatement(long id,String test) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO data (id,test) VALUES (");
        stringBuilder.append(id).append(",");
        stringBuilder.append("'").append(test).append("'");
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void insert(String test) {
        update(buildSQLStatement(System.nanoTime(),test));
    }

    public ArrayList<Integer> get(Double id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT test FROM data WHERE id = ");
        stringBuilder.append(id);

        String sqlStatement = stringBuilder.toString();
        ArrayList<Integer> outList = new ArrayList<Integer>
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sqlStatement);
            do {
                outList.add(new Integer(result.getInt()));
            } while (result.next());

            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return outList;
    }
    **/
    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}