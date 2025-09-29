package testapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TestApp {
    
    public static void main(String[] args) {
        Path directoryPath = Paths.get("D:\\"); // Replace with your directory path

        try (Stream<Path> files = Files.list(directoryPath)) {
            files.filter(Files::isRegularFile).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.toString());
        }


    }
}
