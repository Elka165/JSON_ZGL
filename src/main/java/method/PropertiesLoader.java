package method;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {

    public static AppProperties loadProperties() {
        try (InputStream resourceAsStream = PropertiesLoader.class.getResourceAsStream("/app.properties")) {
            if (resourceAsStream == null) {
                System.out.println("Plik ustawień aplikacji nie może byc znaleziony! \n" +
                        "Parametr metody getResourceAsStream(name) nie jest spójny z nazwą pliku properties " +
                        "lub plik properties nie znajduję się w Resources \n" +
                        "Wyjdź z programu, popraw i uruchom ponownie \n");
            } else {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                return new AppProperties(properties);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return null;
    }

    public Path loadImages(String propertyName) {
        String filePath;
        AppProperties appProperties = PropertiesLoader.loadProperties();
        filePath = appProperties.getImagePath(propertyName);
        Path path = Paths.get(filePath).toAbsolutePath();
        return path;
    }
}
