import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Utility {
    public static boolean fileExists(String filePath){
        return Files.exists(Paths.get(filePath));
    }


}