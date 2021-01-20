package be.intecbrussel.app;

import be.intecbrussel.data.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException {

        String sourcePath = "C:\\Users\\Ganuz\\Desktop\\FileIO_Opdracht\\unsorted";
        String destinationPath = "C:\\Users\\Ganuz\\sorted";

        FileUtils utils = new FileUtils();

        // Create paths list
        List<String> pathsList = utils.createPathsList(sourcePath);

        // Create sorted folders
        utils.createFolder(pathsList, destinationPath);

        File unsorted = new File("C:\\Users\\Ganuz\\Desktop\\FileIO_Opdracht\\unsorted");
        File sorted = new File("C:\\Users\\Ganuz\\sorted");

        //copy files
        FileUtils fileUtils = new FileUtils();
        fileUtils.copyFiles(sourcePath, destinationPath);

        //create summary
        File parentDir = new File("C:\\Users\\Ganuz\\sorted\\summary");
        parentDir.mkdir();
        File file = new File(parentDir, "summary.txt");
        file.createNewFile();
    }
}












