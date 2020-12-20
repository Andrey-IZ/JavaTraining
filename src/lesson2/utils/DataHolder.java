package lesson2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class DataHolder {
    private String fileName = null;
    private String text;

    public DataHolder() {
    }

    public static String[] splitByWords(String text) {
        return text.toLowerCase(Locale.ROOT).split("\\W+");
    }

    public static Collection<String> loadFromFile(String fileName) throws IOException {
        var classloader = Thread.currentThread().getContextClassLoader();
        var is = classloader.getResourceAsStream(fileName);
        if (is == null)
            return null;
        var streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        List<String> lines = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            lines.add(line);
        }
        return lines;
//
//        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//            return stream.collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new FileNotFoundException(e.getMessage());
//        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
