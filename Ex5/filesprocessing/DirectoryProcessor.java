package filesprocessing;

import sections.*;
import subsections.*;

import java.io.*;
import java.util.*;

/**
 * Acts as the manager of the program. Gets a directory of files and a command file as arguments,
 * and prints the files' names according to the command file.
 * @author Oren Motiei
 */
public class DirectoryProcessor {

    /*
     * @param path: the path to a Source directory.
     * @return: an array list of all the files contained in the source directory.
     */
    private static ArrayList<File> getFilesInSourceDir(String path) {
        ArrayList<File> files = new ArrayList<>();
        try {
            File f = new File(path);
            if (!f.isDirectory())
                throw new FileNotFoundException();
            File[] directory = f.listFiles();
            for (File file: directory) {
                if (file.isFile())
                    files.add(file);
            }
        } catch (FileNotFoundException e) {
            System.err.print("ERROR: Incorrect path to Source directory\n");
        }
        return files;
    }


    /**
     * Runs the files processing program.
     * @param args First argument represents the path to the source directory, and the second argument
     *             represents the path to the command file.
     */
    public static void main(String[] args)  {
        try (Reader commandFile = new FileReader(args[1])){
            if (args.length > 2)
                throw new IndexOutOfBoundsException();

            ArrayList<File> files = getFilesInSourceDir(args[0]);
            CommandReader commandReader = new CommandReader(commandFile);
            ArrayList<Section> sections = commandReader.parseFile();

            for (Section section: sections) {
                FiltersFactory filter = new FiltersFactory(section, files);
                ArrayList<File> filteredFiles = filter.executeFilter();
                OrdersFactory order = new OrdersFactory(section, filteredFiles);
                order.executeOrder();
                for (File file: filteredFiles)
                    System.out.println(file.getName());
            }

        } catch (SectionException  e) {
            System.err.print(e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.print("ERROR: Incorrect path to Commands file\n");
        } catch (IndexOutOfBoundsException e) {
            System.err.print("ERROR: Wrong usage. Should receive 2 arguments\n");
        } catch (IOException e) {
            System.err.print("ERROR: Problem while accessing the Commands file\n");
        }
    }

}
