import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Args parsedArgs = new Args();
        try {
            JCommander.newBuilder().addObject(parsedArgs).build().parse(args);
        }catch (ParameterException ex){
            System.out.println(ex.getMessage());
            ex.usage();
            return;
        }
        FileHelper fileHelper = new FileHelper(parsedArgs);

        //Check if path exists
        String path = parsedArgs.getPath();
        if(!new File(path).exists()) {
            System.out.println("Incorrect path");
            return;
        }

        File mainDirectory = new File(path);
        if(!mainDirectory.exists()){
            return;
        }
        try {
            if (parsedArgs.isSingleFile()) {
                fileHelper.replaceEOL(mainDirectory);
                return;
            }

            List<File> allCodeFiles = fileHelper.getAllCodeFiles(mainDirectory);
            System.out.println(allCodeFiles.size());
            for (File file : allCodeFiles) {
                System.out.println("Replacing in main:");
                System.out.println(file.getName());
                fileHelper.replaceEOL(file);
            }
        }catch (IOException e){
            System.out.println("Failed operation for file: " + e.getMessage());
        }
    }
}
