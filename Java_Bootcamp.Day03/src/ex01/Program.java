package ex01;

public class Program {

    public static void main(String[] args) {
        int count = 0;
        if (args.length == 0) {
            System.exit(-1);
        }

        int index = args[0].lastIndexOf(61);
        if (index == -1) {
            System.exit(-1);
        }

        String numberString = args[0].substring(index + 1);

        try {
            count = Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        Printer printer = new Printer();
        Thread egg = new Thread(new Side("Egg", count, printer), "Egg");
        Thread hen = new Thread(new Side("Hen", count, printer), "Hen");
        egg.start();
        hen.start();
    }

    public static class Side implements Runnable {
        private final String name;
        private final int count;
        private final Printer printer;

        public Side(String name, int count, Printer printer) {
            this.name = name;
            this.count = count;
            this.printer = printer;
        }

        public void run() {
            boolean type = this.name.equals("Egg");

            for(int i = 0; i != this.count; ++i) {
                this.printer.printMessage(type, this.name);
            }

        }
    }

    public static class Printer {
        private int count = 0;

        public Printer() {
        }

        public synchronized void get(String name) {
            if (this.count == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println();
                }
            }

            --this.count;
            System.out.println(name);
            this.notify();
        }

        public synchronized void put(String name) {
            if (this.count == 1) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println();
                }
            }

            ++this.count;
            System.out.println(name);
            this.notify();
        }

        public void printMessage(boolean type, String name) {
            if (type) {
                this.put(name);
            } else {
                this.get(name);
            }

        }
    }
}