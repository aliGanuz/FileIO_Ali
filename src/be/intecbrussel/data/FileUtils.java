package be.intecbrussel.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class FileUtils {


    //get files
    public List<String> createPathsList(String sourcePath) {

        List<String> pathsList = new ArrayList<>();

        try (Stream<Path> walk = Files.walk(Paths.get(sourcePath))) {
            pathsList = walk.filter(Files::isRegularFile)
                    .map(e -> e.toString()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathsList;
    }


    //get extensions
    public String getExtension(String file) {
        String extension = null;
        if (file != null) {
            extension = file.substring(file.lastIndexOf(".") + 1);
        }
        return extension;
    }


    //create folders and directories
    public void createFolder(List<String> paths, String destination) {
        Set<String> sortedFolder = new TreeSet<>();

        for (String path : paths) {
            sortedFolder.add(getExtension(path));
        }
        //print files with extensions in order
        sortedFolder.forEach(System.out::println);

        for (String extension : sortedFolder) {
            String newPath = Paths.get(destination)
                    .resolve(extension)
                    .toString();
            if (!new File(newPath).exists()) {
                new File(newPath).mkdir();
            }
        }
    }


    //copy files into the sorted
    public void copyFiles(String sourcePath, String destinationPath) {
        try {
            Files.walk(Paths.get(sourcePath))
                    .forEach(source -> {
                        Path target = Paths.get(destinationPath, source.toString()
                                .substring(sourcePath.length()));
                        try {
                            Files.copy(source, target, REPLACE_EXISTING);
                            System.out.println("Files copied successfully!");
                        } catch (IOException e) {
                            System.out.println("Unable to copy file");
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





