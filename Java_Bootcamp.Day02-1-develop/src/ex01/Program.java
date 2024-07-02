package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Program {
    static private void findRepetitions(String[] lines, Set<String> dictionary, Vector<Integer> repetitions) {
        int count = 0;
        for(String j : dictionary) {
            repetitions.add(0);
            for(int k = 0; k != lines.length; ++k) {
                if(j.equals(lines[k])) {
                    Integer value = repetitions.get(count);
                    repetitions.set(count, value + 1);
                }
            }
            ++count;
        }
    }
     static public void main(String[] args) {
         if(args.length != 2) {
             System.err.println("Указано недостаточное количество файлов.");
             System.exit(-1);
         }

         double numerator = 0;
         double denominatorA = 0, denominatorB = 0;
         Set<String> dictionary = new TreeSet<>();
         String[][] lines = new String[2][];
         Vector<Integer> repetitionsA = new Vector<>();
         Vector<Integer> repetitionsB = new Vector<>();


         for(int i = 0; i != args.length; ++i) {
             try (FileReader reader = new FileReader(args[i])) {
                 BufferedReader bufferedReader = new BufferedReader(reader);
                 lines[i] = bufferedReader.readLine().trim().split(" ");
                 dictionary.addAll(Arrays.asList(lines[i]));
             } catch (IOException e) {
                 System.out.println(e.getMessage());
             }
         }

         try(FileWriter writer = new FileWriter("dictionary.txt", true)) {
             for(String word : dictionary) {
                 writer.write(word + " ");
             }
         } catch (IOException e) {
             System.err.println(e.getMessage());
             System.exit(-1);
         }
         findRepetitions(lines[0], dictionary, repetitionsA);
         findRepetitions(lines[1], dictionary, repetitionsB);
         for(int i = 0; i != repetitionsA.size(); ++i) {
             Integer numberA = repetitionsA.get(i);
             Integer numberB = repetitionsB.get(i);
             numerator += numberA * numberB;
             denominatorA += (double) numberA * numberA;
             denominatorB += (double) numberB * numberB;
         }
         double answer = numerator / (Math.sqrt(denominatorA) * Math.sqrt(denominatorB));
         DecimalFormat df = new DecimalFormat("0.00");
         df.setRoundingMode(RoundingMode.DOWN);
         System.out.printf(df.format(answer));
     }
}
