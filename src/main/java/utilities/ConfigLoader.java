package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

	    private Properties properties = new Properties();

	    public ConfigLoader() {
	        loadProperties();
	    }

	    private void loadProperties() {
	        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
	            if (input == null) {
	                throw new RuntimeException("Sorry, unable to find config.properties");
	            }
	            properties.load(input);
	        } catch (IOException ex) {
	            throw new RuntimeException("Error loading config.properties", ex);
	        }
	    }

	    public String getProperty(String key) {
	        return properties.getProperty(key);
	    }
	
}
