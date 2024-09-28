package fancode.utilities;

import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfig {

	Properties properties;

	String path = "config.properties";

	public ReadConfig() {
		try {
			properties = new Properties();

			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBaseUrl() {
		String value = properties.getProperty("base_url");

		if(value!=null)
			return value;
		else
			throw new RuntimeException("browser not specified in config file.");
	}
}
