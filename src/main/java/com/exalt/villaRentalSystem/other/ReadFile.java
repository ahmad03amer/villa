package com.exalt.villaRentalSystem.other;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadFile {

    public static void main(String[] args) throws IOException
    {
        String fileName = "Input.txt";
        ClassLoader classLoader = new ReadFile().getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        //File is found
        System.out.println("File Found : " + file.exists());

        //Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);
    }
}
