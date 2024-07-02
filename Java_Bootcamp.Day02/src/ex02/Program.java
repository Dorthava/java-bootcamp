package ex02;

import java.io.*;
import java.util.Scanner;

public class Program {
    private File currentFile;

    public static long getDirectorySize(File directory) {
        long size = 0;
        File[] files = directory.listFiles();
        if(files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    size += getDirectorySize(file);
                } else {
                    size += file.length();
                }

            }
        }
        return size;
    }

    private void handleLs() {
        if(currentFile.exists() && currentFile.isDirectory()) {
            File[] files = currentFile.listFiles();
            if(files != null) {
                for (File file : files) {
                    long size = file.isDirectory() && file.exists() ? getDirectorySize(file) : file.length();
                    System.out.printf("%s %s KB", file.getName(), size);
                    System.out.println();
                }
            }
        } else {
            System.err.println("Неверный путь.");
            System.exit(-1);
        }
    }

    private void handleCd(String dirName) {
        File newPath = new File(currentFile, dirName);
        if(!newPath.exists() || !newPath.isDirectory()) { return; }
        try {
            currentFile = newPath.getCanonicalFile();
            System.out.println(currentFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleMv(String filename, String other) {
        File thisFile;
        try {
            thisFile = new File(currentFile, filename).getCanonicalFile();
            if(!thisFile.exists() || !thisFile.isFile()) {
                System.err.println("Указанного файла не существует или же это название директории!");
                return;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        File otherFile = new File(currentFile, other);
        if(!otherFile.exists()) {
            if(!thisFile.renameTo(new File(currentFile, other))) {
                System.out.println("Не удалось переименовать файл!");
            }
        } else {
            if(otherFile.isDirectory()) {
                try(FileInputStream fileInputStream = new FileInputStream(thisFile.getAbsolutePath())) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    try(FileOutputStream fileOutputStream = new FileOutputStream(new File(otherFile, thisFile.getName()))) {
                        int i;

                        while((i = bufferedInputStream.read())!= -1){
                            fileOutputStream.write(i);
                        }
                        if(!thisFile.delete()) {
                            System.out.println("Ошибка при переносе файла.");
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.err.println("Неверно передан путь!");
            }
        }
    }

    public Program(String basePath) {
        currentFile = new File(basePath);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String[] words = scanner.nextLine().trim().split(" ");
            if(words.length == 1 && words[0].equals("exit")) break;
            switch (words[0]) {
                case "ls":
                    if(words.length == 1) {
                        handleLs();
                    }
                    break;
                case "mv":
                    if(words.length == 3) {
                        System.out.println(words[1]);
                        handleMv(words[1], words[2]);
                    }
                    break;
                case "cd":
                    if(words.length == 2) {
                        handleCd(words[1]);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    static public void main(String[] args) {
        if(args.length != 1 && args[0].trim().split("=").length != 2) {
            System.err.println("Не был корректно передан изначальный путь!");
            System.exit(-1);
        }

        Program program = new Program(args[0].trim().split("=")[1]);
        program.run();
    }
}
