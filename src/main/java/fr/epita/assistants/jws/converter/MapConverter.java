package fr.epita.assistants.jws.converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MapConverter {
    public static List<String> RleToStringList(String rlePath) throws IOException {
        Path path = Path.of(rlePath);
        return Files.readAllLines(path);
    }
}