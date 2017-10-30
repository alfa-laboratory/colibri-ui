package ru.colibri.ui.settings.server;

import lombok.extern.java.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

import static java.lang.String.format;
import static ru.colibri.ui.core.names.ColibriStartFlags.PLATFORM;

@Log
public class AppiumServerConfig extends AbstractServerConfig {

    private static final String DEFAULT_PATH_TEMPLATE = "src/test/resources/environment/%s/test_node.json";


    public AppiumServerConfig() {
        this.readServerConfig();
    }

    private void readServerConfig() {
        String configPath = format(DEFAULT_PATH_TEMPLATE, System.getenv().get(PLATFORM));
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(configPath));
            jsonObject = (JSONObject) jsonObject.get("configuration");
            String host = jsonObject.get("host").toString();
            setHost(host.equals("localhost") ? "0.0.0.0" : host);
            setPort(jsonObject.get("port").toString());
        } catch (IOException | ParseException e) {
            log.log(Level.SEVERE, "Ошибка во время чтения конфига сервера: ", e);
        }
    }


}
