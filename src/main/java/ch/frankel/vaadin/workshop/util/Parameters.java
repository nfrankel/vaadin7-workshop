package ch.frankel.vaadin.workshop.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Parameters {

    private static final Map<String, String> PARAMETERS = new HashMap<>();

    public static void init(Properties properties) {
        if (!PARAMETERS.isEmpty()) {
            throw new RuntimeException("Parameters has already been initialized");
        }
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            PARAMETERS.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public static String getDatabaseDriver() {
        return PARAMETERS.get("db.driver");
    }

    public static String getDatabaseUrl() {
        return PARAMETERS.get("db.url");
    }

    public static String getDatabaseUsername() {
        return PARAMETERS.get("db.username");
    }

    public static String getDatabasePassword() {
        return PARAMETERS.get("db.password");
    }
}
