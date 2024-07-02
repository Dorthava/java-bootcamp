package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        List<Signature> signatures = new ArrayList<>();
        try(FileInputStream fin = new FileInputStream("src/ex00/signatures.txt")) {
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            String content = new String(buffer);
            String[] lines = content.split("\n");
            for(String line : lines) {
                String[] parts = line.split(", ");
                signatures.add(new Signature(parts[0].trim(), parts[1].trim()));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String filename = scanner.nextLine();
            if(filename.equals("42")) {
                scanner.close();
                break;
            }
            try(FileInputStream fin = new FileInputStream(filename)) {
                byte[] buffer = new byte[8];
                fin.read(buffer);
                StringBuilder signature = new StringBuilder();
                for (byte b : buffer) {
                    signature.append(String.format("%02X ", b));
                }
                String type = null;
                String value = signature.toString().trim();
                boolean result = false;
                for(Signature i : signatures) {
                    if(i.getSignatureInByte().length() <= value.length() && i.getSignatureInByte().equals(value.substring(0, i.getSignatureInByte().length()))) {
                        result = true;
                        type = i.getType();
                        break;
                    }
                }
                if(result) {
                    try (FileOutputStream outputStream = new FileOutputStream("result.txt", true)) {
                        byte[] typeResult = type.concat("\n").getBytes();
                        outputStream.write(typeResult);
                        System.out.println("PROCESSED");
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }

                } else {
                    System.out.println("UNDEFINED");
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
