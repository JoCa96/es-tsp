package test;

import main.Application;

public class AbstractTest {
	public AbstractTest()
	{
		Application application = new Application();
        application.startupHSQLDB();
        application.loadData();
        application.initConfiguration();
        application.execute();
        application.shutdownHSQLDB();
	}
}
