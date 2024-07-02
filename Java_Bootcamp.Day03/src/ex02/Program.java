package ex02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Program {
    static private class Summator implements Runnable {
        private final int[] array;
        private final int[] arrayIndex;
        public static int totalResult;

        Summator(int[] array, int[] arrayIndex) {
            this.array = array;
            this.arrayIndex = arrayIndex;
        }

        private synchronized int calculationResult() {
            int localResult = 0;
            for (int i : array) {
                localResult += i;
            }
            totalResult += localResult;
            return localResult;
        }

        @Override
        public void run() {
            int localResult = calculationResult();
            System.out.printf("%s: from %d to %d sum in %d\n", Thread.currentThread().getName(), arrayIndex[0], arrayIndex[1], localResult);
        }
    }

    static public ArrayList<int[]> splitArray(int[] array, int threadsCount) {
        int subSize = array.length / threadsCount;
        ArrayList<int[]> values = new ArrayList<>();

        for(int i = 0; i != threadsCount; ++i) {
            int startIndex = i * subSize;
            int endIndex = (i + 1) * subSize;

            values.add(new int[] {startIndex, endIndex});
        }

        values.get(threadsCount - 1)[1] = array.length;

        return values;
    }

    static public void main(String[] args) {
        int arraySize = 0;
        int threadsCount = 0;
        int result = 0;
        int[] array;

        if(args.length != 2) {
            System.exit(-1);
        }
        try {
            for (int i = 0; i != args.length; ++i) {
                if (args[i].contains("--arraySize=")) {
                    arraySize = Integer.parseInt(args[i].substring(12));
                } else if (args[i].contains("--threadsCount=")) {
                    threadsCount = Integer.parseInt(args[i].substring(15));
                } else {
                    throw new RuntimeException("Ошибка при передаче аргумента программе: " + '\"' + args[i] + '\"');
                }
            }
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

        array = new int[arraySize];
        Random random = new Random();
        for(int i = 0; i != arraySize; ++i) {
            array[i] = Math.abs(random.nextInt() % 1001);
            result += array[i];
        }

        System.out.println("Sum: " + result);

        ArrayList<int[]> list = splitArray(array, threadsCount);

        for(int i = 0; i != threadsCount; ++i) {
            int[] arrayInList = list.get(i);
            int[] values = Arrays.copyOfRange(array, arrayInList[0], arrayInList[1]);
            Summator summator = new Summator(values, arrayInList);
            Thread thread = new Thread(summator, "Thread " + (i + 1));
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Sum by threads: " + Summator.totalResult);
    }
}