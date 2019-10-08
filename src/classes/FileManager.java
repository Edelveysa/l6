package classes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;


public class FileManager {
    File file = new File("");


    public static String[] readFromFile(File path) {
        String str = "";
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));) {
            int i;
            while ((i = bufferedInputStream.read()) != -1) {
                str += (char) i;
                str += "\n";
            }
        } catch (IOException ioe) {

        }
        return str.split("\n");
    }


    public static Boolean writeToFile(File path, String string) {
        try (FileOutputStream writer = new FileOutputStream(path)) {
            byte[] stream = string.getBytes();
            writer.write(stream, 0, stream.length);
            System.out.println("Сохранено.");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка. Не сохранено.");
            return false;
        }
    }

}



