package method;

import java.util.Properties;

public class AppProperties {

    private Properties properties;

    public AppProperties(Properties properties) {
        this.properties = properties;
    }

    public String getImagePath(String fileName) {
        return properties.getProperty(fileName);
    }
}