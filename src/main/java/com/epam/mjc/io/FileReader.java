package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String data = "";
        String[] arr = new String[0];
        String name, email;
        Integer age;
        Long phone;

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            int c;
            char ch;

            while ((c=fileInputStream.read())!=-1) {
                ch = (char) c;
                data+=ch;
            }

            arr = data.split("\n");

            for (int i=0; i<arr.length; i++) {
                int colon = arr[i].indexOf(":");
                arr[i] = arr[i].substring(colon+2).trim();
            }

            name = arr[0];
            age = Integer.parseInt(arr[1]);
            email = arr[2];
            phone = Long.parseLong(arr[3]);

            return new Profile(name, age, email, phone);
        } catch (FileNotFoundException fne) {
            System.err.print(fne);
        } catch (IOException ioe){
            System.err.print(ioe);
        }

        return new Profile();
    }
    public static void main(String[] args) throws IOException {
        String dir = System.getProperty("user.home") + "\\IdeaProjects\\stage1-module6-io-task1\\src\\main\\resources";
        String fileName = "Profile.txt";
        String path = dir + File.separator + fileName;

        FileReader fileReader = new FileReader();

        fileReader.getDataFromFile(new File(path));
    }
}
