package ru.omsu.imit.course3;

import com.google.gson.Gson;
import java.io.*;

public class Client {
    public static void main(String[] args) {

    Person person = new Person("Vasya", "Pupkin", 15);
    Gson gson = new Gson();
    String gsoText = gson.toJson(person);
    System.out.println(gsoText);
    File dir1 = new File("C://SomeDir");
    File file1 = new File("C://SomeDir//Hello.txt");
    boolean created = dir1.mkdir();
    if (created)
        System.out.println("Папка создана");
    try {
        boolean createdfile = file1.createNewFile();
    }
            catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        try(FileWriter writer = new FileWriter(file1, false))
        {

            writer.write(gsoText);
            writer.append('\n');
            writer.write(gsoText);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
