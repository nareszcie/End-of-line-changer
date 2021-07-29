import com.sun.istack.internal.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileHelper {

    private List<String> extensions;
    private String symbol;

    FileHelper(Args args){
        extensions = args.getExtensions();
        symbol = args.getSymbol();
        if(extensions == null)
            extensions = new ArrayList<>();
    }

    List<File> getAllCodeFiles(File mainDirectory) {
        List<File> filesToChange = new ArrayList<>();
        List<File> directories = new ArrayList<>();
        directories.add(mainDirectory);
        while (!directories.isEmpty()){
            File current = directories.get(0);
            for(File f: current.listFiles()){
                if(f.isDirectory()){
                    directories.add(f);
                }else{
                    if(f.getName().split("[.]").length>1) {
                        if(checkIfExtensionIsCorrect(f))
                            filesToChange.add(f);
                    }
                }
            }
            directories.remove(current);
        }
        return filesToChange;
    }

    void replaceEOL(File file) throws IOException {
        System.out.println("Current file:"+ file.getAbsolutePath());
        List<String> contents = readDataFromFile(file);
        writeDataToFile(file, contents);
    }

    private List<String> readDataFromFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        List<String> contents = new ArrayList<>();
        while(line!=null){
            contents.add(line);
            line = br.readLine();
        }
        br.close();
        return contents;
    }

    private void writeDataToFile(File file, List<String> contents) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        System.out.println("Now in file: "+file.getAbsolutePath());
        for(String s : contents){
            bw.write(s);
            bw.write(symbol);
        }
        bw.close();
    }

    private boolean checkIfExtensionIsCorrect(File file) {
        if (extensions.size() == 0)
            return true;

        for (String ext : extensions) {
            if (file.getName().split("[.]")[1].equals(ext)) {
                return true;
            }
        }
        return false;
    }
}
