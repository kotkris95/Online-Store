package onlineShop;


import java.util.Properties;

public class PropertiesWrapper {
    private final Properties properties;

    public PropertiesWrapper(Properties properties) {
        this.properties = properties;
    }

    public String getDataStorage() {
        return properties.getProperty("data.storage");
    }

    public String getLogger() {
        return properties.getProperty("logger.type");
    }
}
