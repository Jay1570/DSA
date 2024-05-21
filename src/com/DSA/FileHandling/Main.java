package com.DSA.FileHandling;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        fileReader();
        fileWriter();
        file();
    }
    static void fileReader() {
        File f = new File("note.txt");
        try(FileReader fr = new FileReader(f)) {
            int letters = fr.read();
            while (fr.ready()) {
                System.out.println((char) letters);
                letters = fr.read();
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            while (br.ready()) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void writer() {
        try (OutputStreamWriter os = new OutputStreamWriter(System.out)) {
            os.write("hi");
            os.write(97);
            os.write(10);
            os.write('A');
            os.write("\n");
            os.write("Hello World".toCharArray());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void fileWriter() {
        File f = new File("notes.txt");
        try (FileWriter os = new FileWriter(f, true)) {
            os.write("hi");
            os.write(97);
            os.write(10);
            os.write('A');
            os.write("\n");
            os.write("Hello World".toCharArray());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void file() {
        try {
            File f = new File("notesss.txt");
            System.out.println(f.createNewFile());
            try (FileWriter fw = new FileWriter(f)) {
                fw.write("hi");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
