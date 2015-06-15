package info.andersw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

@WebListener
public class ApplicationInitializer implements ServletContextListener {

	private static final Logger logger = Logger
			.getLogger(ApplicationInitializer.class.getName());

	private Properties appConf;
	public static final String DEFAULT_PROPS_FILENAME = "appConf.properties";
	private static final String PROPS_FILENAME_FORMAT = "appConf_%s.properties";

	/**
	 * Use: -Denv=prod or -Denv=test
	 */
	public static final String ENVIRONMENT_NAME_KEY = "env";

	/**
	 * Setup and initialize application wide resources here.
	 * 
	 * Looks at system property 'env' and loads the corresponding
	 * appConf_[%env].properties file from classpath.
	 *
	 * @param event
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {

		/*
		 * Example use: 
		 * String apiEndpoint = appConf.getProperty("apiEndpoint");
		 * String apiKey = appConf.getProperty("apiKey");
		 * 
		 * Create connection etc
		 */
	}

	/**
	 * Tear down on application shutdown
	 *
	 * @param event
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {

		// Tear down any resources that were used during the life cycle of the
		// application
	}

	@PostConstruct
	public void initEnvironmentProps() {
		appConf = new Properties();
		String propsFilename = DEFAULT_PROPS_FILENAME;
		String environmentName = System.getProperty(ENVIRONMENT_NAME_KEY,
				"prod");
		if (environmentName != null) {
			propsFilename = String.format(PROPS_FILENAME_FORMAT,
					environmentName);
		}
		logger.log(Level.INFO, "Reading {0}", propsFilename);
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propsFilename);
		if (inputStream == null) {
			throw new RuntimeException(new FileNotFoundException(
					"Properties file " + propsFilename
							+ " not found in the classpath."));
		}
		try {
			appConf.load(inputStream);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Cannot read " + propsFilename, ex);
			throw new RuntimeException(ex);
		}
	}

}
