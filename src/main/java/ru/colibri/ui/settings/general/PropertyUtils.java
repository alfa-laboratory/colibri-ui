package ru.colibri.ui.settings.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.colibri.ui.core.names.ColibriStartFlags;
import ru.colibri.ui.core.settings.AppSettings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.regex.Pattern.compile;

@Slf4j
@Service
public class PropertyUtils {
    private final static Pattern propertyPattern = compile("(#)([A-Za-z0-9_-]+?)(#)");

    @Autowired
    private AppSettings appSettings;

    public static Properties readProperty(String... filePaths) {
        Properties props = new Properties();
        for (String path : filePaths) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), UTF_8)) {
                props.load(inputStreamReader);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка загрузки настроек драйвера: ", e);
            }
        }
        return props;
    }

    public String injectProperties(String text) {
        Map<String, String> userProfile = appSettings.getUserProfile();
        Matcher matcher = propertyPattern.matcher(text);
        if (!matcher.find()) {
            return text;
        }
        String propertyKey = matcher.group(2);
        String propertyValue = userProfile.get(propertyKey);
        if (propertyValue == null) {
            log.error("Couldn't find property {} for user {}", propertyKey, System.getenv().getOrDefault(ColibriStartFlags.USER, "user not defined"));
            return text;
        }
        return matcher.replaceAll(propertyValue);
    }
}
