package method;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DateLoader {


    public List addToList(){
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Path path = propertiesLoader.loadImages("datePath");
        List<String> allLinesAsString = new ArrayList<>();
        try {
            allLinesAsString = Files.readAllLines(path);

        } catch (IOException e) {
            e.printStackTrace();

        }

        return allLinesAsString;
    }


}
